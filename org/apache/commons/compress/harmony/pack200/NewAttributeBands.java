/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Label
 */
package org.apache.commons.compress.harmony.pack200;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.AttributeDefinitionBands;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.BandSet;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.ConstantPoolEntry;
import org.apache.commons.compress.harmony.pack200.CpBands;
import org.apache.commons.compress.harmony.pack200.IntList;
import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.pack200.PackingUtils;
import org.apache.commons.compress.harmony.pack200.SegmentHeader;
import org.objectweb.asm.Label;

public class NewAttributeBands
extends BandSet {
    protected List<AttributeLayoutElement> attributeLayoutElements;
    private int[] backwardsCallCounts;
    private final CpBands cpBands;
    private final AttributeDefinitionBands.AttributeDefinition def;
    private boolean usedAtLeastOnce;
    private Integral lastPIntegral;

    public NewAttributeBands(int effort, CpBands cpBands, SegmentHeader header, AttributeDefinitionBands.AttributeDefinition def) throws IOException {
        super(effort, header);
        this.def = def;
        this.cpBands = cpBands;
        this.parseLayout();
    }

    public void addAttribute(NewAttribute attribute) {
        this.usedAtLeastOnce = true;
        ByteArrayInputStream stream = new ByteArrayInputStream(attribute.getBytes());
        for (AttributeLayoutElement attributeLayoutElement : this.attributeLayoutElements) {
            attributeLayoutElement.addAttributeToBand(attribute, stream);
        }
    }

    public String getAttributeName() {
        return this.def.name.getUnderlyingString();
    }

    private BHSDCodec getCodec(String layoutElement) {
        if (layoutElement.indexOf(79) >= 0) {
            return Codec.BRANCH5;
        }
        if (layoutElement.indexOf(80) >= 0) {
            return Codec.BCI5;
        }
        if (layoutElement.indexOf(83) >= 0 && layoutElement.indexOf("KS") < 0 && layoutElement.indexOf("RS") < 0) {
            return Codec.SIGNED5;
        }
        if (layoutElement.indexOf(66) >= 0) {
            return Codec.BYTE1;
        }
        return Codec.UNSIGNED5;
    }

    public int getFlagIndex() {
        return this.def.index;
    }

    private StringReader getStreamUpToMatchingBracket(StringReader reader) throws IOException {
        int read;
        StringBuilder sb = new StringBuilder();
        int foundBracket = -1;
        while (foundBracket != 0 && (read = reader.read()) != -1) {
            char c2 = (char)read;
            if (c2 == ']') {
                ++foundBracket;
            }
            if (c2 == '[') {
                --foundBracket;
            }
            if (foundBracket == 0) continue;
            sb.append(c2);
        }
        return new StringReader(sb.toString());
    }

    public boolean isUsedAtLeastOnce() {
        return this.usedAtLeastOnce;
    }

    public int[] numBackwardsCalls() {
        return this.backwardsCallCounts;
    }

    @Override
    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        for (AttributeLayoutElement attributeLayoutElement : this.attributeLayoutElements) {
            attributeLayoutElement.pack(outputStream);
        }
    }

    private void parseLayout() throws IOException {
        String layout = this.def.layout.getUnderlyingString();
        if (this.attributeLayoutElements == null) {
            AttributeLayoutElement e2;
            this.attributeLayoutElements = new ArrayList<AttributeLayoutElement>();
            StringReader reader = new StringReader(layout);
            while ((e2 = this.readNextAttributeElement(reader)) != null) {
                this.attributeLayoutElements.add(e2);
            }
            this.resolveCalls();
        }
    }

    private List<LayoutElement> readBody(StringReader reader) throws IOException {
        LayoutElement e2;
        ArrayList<LayoutElement> layoutElements = new ArrayList<LayoutElement>();
        while ((e2 = this.readNextLayoutElement(reader)) != null) {
            layoutElements.add(e2);
        }
        return layoutElements;
    }

    private int readInteger(int i2, InputStream inputStream) {
        int result = 0;
        for (int j2 = 0; j2 < i2; ++j2) {
            try {
                result = result << 8 | inputStream.read();
                continue;
            } catch (IOException e2) {
                throw new UncheckedIOException("Error reading unknown attribute", e2);
            }
        }
        if (i2 == 1) {
            result = (byte)result;
        }
        if (i2 == 2) {
            result = (short)result;
        }
        return result;
    }

    private AttributeLayoutElement readNextAttributeElement(StringReader reader) throws IOException {
        reader.mark(1);
        int next = reader.read();
        if (next == -1) {
            return null;
        }
        if (next == 91) {
            return new Callable(this.readBody(this.getStreamUpToMatchingBracket(reader)));
        }
        reader.reset();
        return this.readNextLayoutElement(reader);
    }

    private LayoutElement readNextLayoutElement(StringReader reader) throws IOException {
        int nextChar = reader.read();
        if (nextChar == -1) {
            return null;
        }
        switch (nextChar) {
            case 66: 
            case 72: 
            case 73: 
            case 86: {
                return new Integral(new String(new char[]{(char)nextChar}));
            }
            case 70: 
            case 83: {
                return new Integral(new String(new char[]{(char)nextChar, (char)reader.read()}));
            }
            case 80: {
                reader.mark(1);
                if (reader.read() != 79) {
                    reader.reset();
                    this.lastPIntegral = new Integral("P" + (char)reader.read());
                    return this.lastPIntegral;
                }
                this.lastPIntegral = new Integral("PO" + (char)reader.read(), this.lastPIntegral);
                return this.lastPIntegral;
            }
            case 79: {
                reader.mark(1);
                if (reader.read() != 83) {
                    reader.reset();
                    return new Integral("O" + (char)reader.read(), this.lastPIntegral);
                }
                return new Integral("OS" + (char)reader.read(), this.lastPIntegral);
            }
            case 78: {
                char uint_type = (char)reader.read();
                reader.read();
                String str = this.readUpToMatchingBracket(reader);
                return new Replication("" + uint_type, str);
            }
            case 84: {
                UnionCase c2;
                String int_type = String.valueOf((char)reader.read());
                if (int_type.equals("S")) {
                    int_type = int_type + (char)reader.read();
                }
                ArrayList<UnionCase> unionCases = new ArrayList<UnionCase>();
                while ((c2 = this.readNextUnionCase(reader)) != null) {
                    unionCases.add(c2);
                }
                reader.read();
                reader.read();
                reader.read();
                List<LayoutElement> body = null;
                reader.mark(1);
                char next = (char)reader.read();
                if (next != ']') {
                    reader.reset();
                    body = this.readBody(this.getStreamUpToMatchingBracket(reader));
                }
                return new Union(int_type, unionCases, body);
            }
            case 40: {
                int number = this.readNumber(reader);
                reader.read();
                return new Call(number);
            }
            case 75: 
            case 82: {
                StringBuilder string = new StringBuilder("").append((char)nextChar).append((char)reader.read());
                char nxt = (char)reader.read();
                string.append(nxt);
                if (nxt == 'N') {
                    string.append((char)reader.read());
                }
                return new Reference(string.toString());
            }
        }
        return null;
    }

    private UnionCase readNextUnionCase(StringReader reader) throws IOException {
        Integer nextTag;
        reader.mark(2);
        reader.read();
        int next = reader.read();
        char ch = (char)next;
        if (ch == ')' || next == -1) {
            reader.reset();
            return null;
        }
        reader.reset();
        reader.read();
        ArrayList<Integer> tags = new ArrayList<Integer>();
        do {
            if ((nextTag = this.readNumber(reader)) == null) continue;
            tags.add(nextTag);
            reader.read();
        } while (nextTag != null);
        reader.read();
        reader.mark(1);
        ch = (char)reader.read();
        if (ch == ']') {
            return new UnionCase(tags);
        }
        reader.reset();
        return new UnionCase(tags, this.readBody(this.getStreamUpToMatchingBracket(reader)));
    }

    private Integer readNumber(StringReader stream) throws IOException {
        int i2;
        boolean negative;
        stream.mark(1);
        char first = (char)stream.read();
        boolean bl = negative = first == '-';
        if (!negative) {
            stream.reset();
        }
        stream.mark(100);
        int length = 0;
        while ((i2 = stream.read()) != -1 && Character.isDigit((char)i2)) {
            ++length;
        }
        stream.reset();
        if (length == 0) {
            return null;
        }
        char[] digits = new char[length];
        int read = stream.read(digits);
        if (read != digits.length) {
            throw new IOException("Error reading from the input stream");
        }
        return Integer.parseInt((negative ? "-" : "") + new String(digits));
    }

    private String readUpToMatchingBracket(StringReader reader) throws IOException {
        int read;
        StringBuilder sb = new StringBuilder();
        int foundBracket = -1;
        while (foundBracket != 0 && (read = reader.read()) != -1) {
            char c2 = (char)read;
            if (c2 == ']') {
                ++foundBracket;
            }
            if (c2 == '[') {
                --foundBracket;
            }
            if (foundBracket == 0) continue;
            sb.append(c2);
        }
        return sb.toString();
    }

    public void renumberBci(IntList bciRenumbering, Map<Label, Integer> labelsToOffsets) {
        for (AttributeLayoutElement attributeLayoutElement : this.attributeLayoutElements) {
            attributeLayoutElement.renumberBci(bciRenumbering, labelsToOffsets);
        }
    }

    private void resolveCalls() {
        for (int i2 = 0; i2 < this.attributeLayoutElements.size(); ++i2) {
            AttributeLayoutElement element = this.attributeLayoutElements.get(i2);
            if (!(element instanceof Callable)) continue;
            Callable callable = (Callable)element;
            List body = callable.body;
            for (LayoutElement layoutElement : body) {
                this.resolveCallsForElement(i2, callable, layoutElement);
            }
        }
        int backwardsCallableIndex = 0;
        for (AttributeLayoutElement attributeLayoutElement : this.attributeLayoutElements) {
            Callable callable;
            if (!(attributeLayoutElement instanceof Callable) || !(callable = (Callable)attributeLayoutElement).isBackwardsCallable) continue;
            callable.setBackwardsCallableIndex(backwardsCallableIndex);
            ++backwardsCallableIndex;
        }
        this.backwardsCallCounts = new int[backwardsCallableIndex];
    }

    private void resolveCallsForElement(int i2, Callable currentCallable, LayoutElement layoutElement) {
        block7: {
            block8: {
                if (!(layoutElement instanceof Call)) break block8;
                Call call = (Call)layoutElement;
                int index = call.callableIndex;
                if (index == 0) {
                    call.setCallable(currentCallable);
                } else if (index > 0) {
                    for (int k2 = i2 + 1; k2 < this.attributeLayoutElements.size(); ++k2) {
                        AttributeLayoutElement el = this.attributeLayoutElements.get(k2);
                        if (!(el instanceof Callable) || --index != 0) continue;
                        call.setCallable((Callable)el);
                        break block7;
                    }
                } else {
                    for (int k3 = i2 - 1; k3 >= 0; --k3) {
                        AttributeLayoutElement el = this.attributeLayoutElements.get(k3);
                        if (!(el instanceof Callable) || ++index != 0) continue;
                        call.setCallable((Callable)el);
                        break block7;
                    }
                }
                break block7;
            }
            if (!(layoutElement instanceof Replication)) break block7;
            List children = ((Replication)layoutElement).layoutElements;
            for (LayoutElement child : children) {
                this.resolveCallsForElement(i2, currentCallable, child);
            }
        }
    }

    public abstract class LayoutElement
    implements AttributeLayoutElement {
        protected int getLength(char uint_type) {
            int length = 0;
            switch (uint_type) {
                case 'B': {
                    length = 1;
                    break;
                }
                case 'H': {
                    length = 2;
                    break;
                }
                case 'I': {
                    length = 4;
                    break;
                }
                case 'V': {
                    length = 0;
                }
            }
            return length;
        }
    }

    public static interface AttributeLayoutElement {
        public void addAttributeToBand(NewAttribute var1, InputStream var2);

        public void pack(OutputStream var1) throws IOException, Pack200Exception;

        public void renumberBci(IntList var1, Map<Label, Integer> var2);
    }

    public class Callable
    implements AttributeLayoutElement {
        private final List<LayoutElement> body;
        private boolean isBackwardsCallable;
        private int backwardsCallableIndex;

        public Callable(List<LayoutElement> body) {
            this.body = body;
        }

        @Override
        public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
            for (AttributeLayoutElement attributeLayoutElement : this.body) {
                attributeLayoutElement.addAttributeToBand(attribute, inputStream);
            }
        }

        public void addBackwardsCall() {
            int[] nArray = NewAttributeBands.this.backwardsCallCounts;
            int n2 = this.backwardsCallableIndex;
            nArray[n2] = nArray[n2] + 1;
        }

        public List<LayoutElement> getBody() {
            return this.body;
        }

        public boolean isBackwardsCallable() {
            return this.isBackwardsCallable;
        }

        @Override
        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            for (AttributeLayoutElement attributeLayoutElement : this.body) {
                attributeLayoutElement.pack(outputStream);
            }
        }

        @Override
        public void renumberBci(IntList bciRenumbering, Map<Label, Integer> labelsToOffsets) {
            for (AttributeLayoutElement attributeLayoutElement : this.body) {
                attributeLayoutElement.renumberBci(bciRenumbering, labelsToOffsets);
            }
        }

        public void setBackwardsCallable() {
            this.isBackwardsCallable = true;
        }

        public void setBackwardsCallableIndex(int backwardsCallableIndex) {
            this.backwardsCallableIndex = backwardsCallableIndex;
        }
    }

    public class Integral
    extends LayoutElement {
        private final String tag;
        private final List band = new ArrayList();
        private final BHSDCodec defaultCodec;
        private Integral previousIntegral;
        private int previousPValue;

        public Integral(String tag) {
            this.tag = tag;
            this.defaultCodec = NewAttributeBands.this.getCodec(tag);
        }

        public Integral(String tag, Integral previousIntegral) {
            this.tag = tag;
            this.defaultCodec = NewAttributeBands.this.getCodec(tag);
            this.previousIntegral = previousIntegral;
        }

        @Override
        public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
            Integer val = null;
            int value = 0;
            if (this.tag.equals("B") || this.tag.equals("FB")) {
                value = NewAttributeBands.this.readInteger(1, inputStream) & 0xFF;
            } else if (this.tag.equals("SB")) {
                value = NewAttributeBands.this.readInteger(1, inputStream);
            } else if (this.tag.equals("H") || this.tag.equals("FH")) {
                value = NewAttributeBands.this.readInteger(2, inputStream) & 0xFFFF;
            } else if (this.tag.equals("SH")) {
                value = NewAttributeBands.this.readInteger(2, inputStream);
            } else if (this.tag.equals("I") || this.tag.equals("FI") || this.tag.equals("SI")) {
                value = NewAttributeBands.this.readInteger(4, inputStream);
            } else if (!(this.tag.equals("V") || this.tag.equals("FV") || this.tag.equals("SV"))) {
                if (this.tag.startsWith("PO") || this.tag.startsWith("OS")) {
                    char uint_type = this.tag.substring(2).toCharArray()[0];
                    int length = this.getLength(uint_type);
                    value = NewAttributeBands.this.readInteger(length, inputStream);
                    val = attribute.getLabel(value += this.previousIntegral.previousPValue);
                    this.previousPValue = value;
                } else if (this.tag.startsWith("P")) {
                    char uint_type = this.tag.substring(1).toCharArray()[0];
                    int length = this.getLength(uint_type);
                    value = NewAttributeBands.this.readInteger(length, inputStream);
                    val = attribute.getLabel(value);
                    this.previousPValue = value;
                } else if (this.tag.startsWith("O")) {
                    char uint_type = this.tag.substring(1).toCharArray()[0];
                    int length = this.getLength(uint_type);
                    value = NewAttributeBands.this.readInteger(length, inputStream);
                    val = attribute.getLabel(value += this.previousIntegral.previousPValue);
                    this.previousPValue = value;
                }
            }
            if (val == null) {
                val = value;
            }
            this.band.add(val);
        }

        public String getTag() {
            return this.tag;
        }

        public int latestValue() {
            return (Integer)this.band.get(this.band.size() - 1);
        }

        @Override
        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            PackingUtils.log("Writing new attribute bands...");
            byte[] encodedBand = NewAttributeBands.this.encodeBandInt(this.tag, NewAttributeBands.this.integerListToArray(this.band), this.defaultCodec);
            outputStream.write(encodedBand);
            PackingUtils.log("Wrote " + encodedBand.length + " bytes from " + this.tag + "[" + this.band.size() + "]");
        }

        @Override
        public void renumberBci(IntList bciRenumbering, Map<Label, Integer> labelsToOffsets) {
            if (this.tag.startsWith("O") || this.tag.startsWith("PO")) {
                this.renumberOffsetBci(this.previousIntegral.band, bciRenumbering, labelsToOffsets);
            } else if (this.tag.startsWith("P")) {
                Object label;
                for (int i2 = this.band.size() - 1; i2 >= 0 && !((label = this.band.get(i2)) instanceof Integer); --i2) {
                    if (!(label instanceof Label)) continue;
                    this.band.remove(i2);
                    Integer bytecodeIndex = labelsToOffsets.get(label);
                    this.band.add(i2, bciRenumbering.get(bytecodeIndex));
                }
            }
        }

        private void renumberOffsetBci(List relative, IntList bciRenumbering, Map<Label, Integer> labelsToOffsets) {
            Object label;
            for (int i2 = this.band.size() - 1; i2 >= 0 && !((label = this.band.get(i2)) instanceof Integer); --i2) {
                if (!(label instanceof Label)) continue;
                this.band.remove(i2);
                Integer bytecodeIndex = labelsToOffsets.get(label);
                Integer renumberedOffset = bciRenumbering.get(bytecodeIndex) - (Integer)relative.get(i2);
                this.band.add(i2, renumberedOffset);
            }
        }
    }

    public class Replication
    extends LayoutElement {
        private final Integral countElement;
        private final List<LayoutElement> layoutElements = new ArrayList<LayoutElement>();

        public Replication(String tag, String contents) throws IOException {
            LayoutElement e2;
            this.countElement = new Integral(tag);
            StringReader stream = new StringReader(contents);
            while ((e2 = NewAttributeBands.this.readNextLayoutElement(stream)) != null) {
                this.layoutElements.add(e2);
            }
        }

        @Override
        public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
            this.countElement.addAttributeToBand(attribute, inputStream);
            int count = this.countElement.latestValue();
            for (int i2 = 0; i2 < count; ++i2) {
                for (AttributeLayoutElement attributeLayoutElement : this.layoutElements) {
                    attributeLayoutElement.addAttributeToBand(attribute, inputStream);
                }
            }
        }

        public Integral getCountElement() {
            return this.countElement;
        }

        public List<LayoutElement> getLayoutElements() {
            return this.layoutElements;
        }

        @Override
        public void pack(OutputStream out) throws IOException, Pack200Exception {
            this.countElement.pack(out);
            for (AttributeLayoutElement attributeLayoutElement : this.layoutElements) {
                attributeLayoutElement.pack(out);
            }
        }

        @Override
        public void renumberBci(IntList bciRenumbering, Map<Label, Integer> labelsToOffsets) {
            for (AttributeLayoutElement attributeLayoutElement : this.layoutElements) {
                attributeLayoutElement.renumberBci(bciRenumbering, labelsToOffsets);
            }
        }
    }

    public class UnionCase
    extends LayoutElement {
        private final List<LayoutElement> body;
        private final List<Integer> tags;

        public UnionCase(List<Integer> tags) {
            this.tags = tags;
            this.body = Collections.EMPTY_LIST;
        }

        public UnionCase(List<Integer> tags, List<LayoutElement> body) {
            this.tags = tags;
            this.body = body;
        }

        @Override
        public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
            for (LayoutElement element : this.body) {
                element.addAttributeToBand(attribute, inputStream);
            }
        }

        public List<LayoutElement> getBody() {
            return this.body;
        }

        public boolean hasTag(long l2) {
            return this.tags.contains((int)l2);
        }

        @Override
        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            for (LayoutElement element : this.body) {
                element.pack(outputStream);
            }
        }

        @Override
        public void renumberBci(IntList bciRenumbering, Map<Label, Integer> labelsToOffsets) {
            for (LayoutElement element : this.body) {
                element.renumberBci(bciRenumbering, labelsToOffsets);
            }
        }
    }

    public class Union
    extends LayoutElement {
        private final Integral unionTag;
        private final List<UnionCase> unionCases;
        private final List<LayoutElement> defaultCaseBody;

        public Union(String tag, List<UnionCase> unionCases, List<LayoutElement> body) {
            this.unionTag = new Integral(tag);
            this.unionCases = unionCases;
            this.defaultCaseBody = body;
        }

        @Override
        public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
            this.unionTag.addAttributeToBand(attribute, inputStream);
            long tag = this.unionTag.latestValue();
            boolean defaultCase = true;
            for (UnionCase unionCase : this.unionCases) {
                if (!unionCase.hasTag(tag)) continue;
                defaultCase = false;
                unionCase.addAttributeToBand(attribute, inputStream);
            }
            if (defaultCase) {
                for (LayoutElement layoutElement : this.defaultCaseBody) {
                    layoutElement.addAttributeToBand(attribute, inputStream);
                }
            }
        }

        public List<LayoutElement> getDefaultCaseBody() {
            return this.defaultCaseBody;
        }

        public List<UnionCase> getUnionCases() {
            return this.unionCases;
        }

        public Integral getUnionTag() {
            return this.unionTag;
        }

        @Override
        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            this.unionTag.pack(outputStream);
            for (UnionCase unionCase : this.unionCases) {
                unionCase.pack(outputStream);
            }
            for (LayoutElement element : this.defaultCaseBody) {
                element.pack(outputStream);
            }
        }

        @Override
        public void renumberBci(IntList bciRenumbering, Map<Label, Integer> labelsToOffsets) {
            for (UnionCase unionCase : this.unionCases) {
                unionCase.renumberBci(bciRenumbering, labelsToOffsets);
            }
            for (LayoutElement element : this.defaultCaseBody) {
                element.renumberBci(bciRenumbering, labelsToOffsets);
            }
        }
    }

    public class Call
    extends LayoutElement {
        private final int callableIndex;
        private Callable callable;

        public Call(int callableIndex) {
            this.callableIndex = callableIndex;
        }

        @Override
        public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
            this.callable.addAttributeToBand(attribute, inputStream);
            if (this.callableIndex < 1) {
                this.callable.addBackwardsCall();
            }
        }

        public Callable getCallable() {
            return this.callable;
        }

        public int getCallableIndex() {
            return this.callableIndex;
        }

        @Override
        public void pack(OutputStream outputStream) {
        }

        @Override
        public void renumberBci(IntList bciRenumbering, Map<Label, Integer> labelsToOffsets) {
        }

        public void setCallable(Callable callable) {
            this.callable = callable;
            if (this.callableIndex < 1) {
                callable.setBackwardsCallable();
            }
        }
    }

    public class Reference
    extends LayoutElement {
        private final String tag;
        private List<ConstantPoolEntry> band;
        private boolean nullsAllowed = false;

        public Reference(String tag) {
            this.tag = tag;
            this.nullsAllowed = tag.indexOf(78) != -1;
        }

        @Override
        public void addAttributeToBand(NewAttribute attribute, InputStream inputStream) {
            int index = NewAttributeBands.this.readInteger(4, inputStream);
            if (this.tag.startsWith("RC")) {
                this.band.add(NewAttributeBands.this.cpBands.getCPClass(attribute.readClass(index)));
            } else if (this.tag.startsWith("RU")) {
                this.band.add(NewAttributeBands.this.cpBands.getCPUtf8(attribute.readUTF8(index)));
            } else if (this.tag.startsWith("RS")) {
                this.band.add(NewAttributeBands.this.cpBands.getCPSignature(attribute.readUTF8(index)));
            } else {
                this.band.add(NewAttributeBands.this.cpBands.getConstant(attribute.readConst(index)));
            }
        }

        public String getTag() {
            return this.tag;
        }

        @Override
        public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
            int[] ints = this.nullsAllowed ? NewAttributeBands.this.cpEntryOrNullListToArray(this.band) : NewAttributeBands.this.cpEntryListToArray(this.band);
            byte[] encodedBand = NewAttributeBands.this.encodeBandInt(this.tag, ints, Codec.UNSIGNED5);
            outputStream.write(encodedBand);
            PackingUtils.log("Wrote " + encodedBand.length + " bytes from " + this.tag + "[" + ints.length + "]");
        }

        @Override
        public void renumberBci(IntList bciRenumbering, Map<Label, Integer> labelsToOffsets) {
        }
    }
}

