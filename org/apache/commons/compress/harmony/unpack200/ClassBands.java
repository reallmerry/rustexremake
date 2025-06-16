/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
import org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap;
import org.apache.commons.compress.harmony.unpack200.BandSet;
import org.apache.commons.compress.harmony.unpack200.CpBands;
import org.apache.commons.compress.harmony.unpack200.IMatcher;
import org.apache.commons.compress.harmony.unpack200.IcBands;
import org.apache.commons.compress.harmony.unpack200.IcTuple;
import org.apache.commons.compress.harmony.unpack200.MetadataBandGroup;
import org.apache.commons.compress.harmony.unpack200.NewAttributeBands;
import org.apache.commons.compress.harmony.unpack200.Segment;
import org.apache.commons.compress.harmony.unpack200.SegmentOptions;
import org.apache.commons.compress.harmony.unpack200.SegmentUtils;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantValueAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.DeprecatedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.EnclosingMethodAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LineNumberTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTypeTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SignatureAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;

public class ClassBands
extends BandSet {
    private int[] classFieldCount;
    private long[] classFlags;
    private long[] classAccessFlags;
    private int[][] classInterfacesInts;
    private int[] classMethodCount;
    private int[] classSuperInts;
    private String[] classThis;
    private int[] classThisInts;
    private ArrayList<Attribute>[] classAttributes;
    private int[] classVersionMajor;
    private int[] classVersionMinor;
    private IcTuple[][] icLocal;
    private List<Attribute>[] codeAttributes;
    private int[] codeHandlerCount;
    private int[] codeMaxNALocals;
    private int[] codeMaxStack;
    private ArrayList<Attribute>[][] fieldAttributes;
    private String[][] fieldDescr;
    private int[][] fieldDescrInts;
    private long[][] fieldFlags;
    private long[][] fieldAccessFlags;
    private ArrayList<Attribute>[][] methodAttributes;
    private String[][] methodDescr;
    private int[][] methodDescrInts;
    private long[][] methodFlags;
    private long[][] methodAccessFlags;
    private final AttributeLayoutMap attrMap;
    private final CpBands cpBands;
    private final SegmentOptions options;
    private final int classCount;
    private int[] methodAttrCalls;
    private int[][] codeHandlerStartP;
    private int[][] codeHandlerEndPO;
    private int[][] codeHandlerCatchPO;
    private int[][] codeHandlerClassRCN;
    private boolean[] codeHasAttributes;

    public ClassBands(Segment segment) {
        super(segment);
        this.attrMap = segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        this.cpBands = segment.getCpBands();
        this.classCount = this.header.getClassCount();
        this.options = this.header.getOptions();
    }

    private int getCallCount(int[][] methodAttrIndexes, long[][] flags, int context) {
        int n2;
        int callCount = 0;
        int[][] nArray = methodAttrIndexes;
        int n3 = nArray.length;
        for (n2 = 0; n2 < n3; ++n2) {
            int[] element;
            for (int index : element = nArray[n2]) {
                AttributeLayout layout = this.attrMap.getAttributeLayout(index, context);
                callCount += layout.numBackwardsCallables();
            }
        }
        int layoutsUsed = 0;
        long[][] lArray = flags;
        n2 = lArray.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            long[] flag;
            for (long element : flag = lArray[i2]) {
                layoutsUsed = (int)((long)layoutsUsed | element);
            }
        }
        for (int i3 = 0; i3 < 26; ++i3) {
            if ((layoutsUsed & 1 << i3) == 0) continue;
            AttributeLayout layout = this.attrMap.getAttributeLayout(i3, context);
            callCount += layout.numBackwardsCallables();
        }
        return callCount;
    }

    public ArrayList<Attribute>[] getClassAttributes() {
        return this.classAttributes;
    }

    public int[] getClassFieldCount() {
        return this.classFieldCount;
    }

    public long[] getClassFlags() {
        if (this.classAccessFlags == null) {
            int i2;
            long mask = 32767L;
            for (i2 = 0; i2 < 16; ++i2) {
                AttributeLayout layout = this.attrMap.getAttributeLayout(i2, 0);
                if (layout == null || layout.isDefaultLayout()) continue;
                mask &= (long)(~(1 << i2));
            }
            this.classAccessFlags = new long[this.classFlags.length];
            for (i2 = 0; i2 < this.classFlags.length; ++i2) {
                this.classAccessFlags[i2] = this.classFlags[i2] & mask;
            }
        }
        return this.classAccessFlags;
    }

    public int[][] getClassInterfacesInts() {
        return this.classInterfacesInts;
    }

    public int[] getClassMethodCount() {
        return this.classMethodCount;
    }

    public int[] getClassSuperInts() {
        return this.classSuperInts;
    }

    public int[] getClassThisInts() {
        return this.classThisInts;
    }

    public int[] getClassVersionMajor() {
        return this.classVersionMajor;
    }

    public int[] getClassVersionMinor() {
        return this.classVersionMinor;
    }

    public int[][] getCodeHandlerCatchPO() {
        return this.codeHandlerCatchPO;
    }

    public int[][] getCodeHandlerClassRCN() {
        return this.codeHandlerClassRCN;
    }

    public int[] getCodeHandlerCount() {
        return this.codeHandlerCount;
    }

    public int[][] getCodeHandlerEndPO() {
        return this.codeHandlerEndPO;
    }

    public int[][] getCodeHandlerStartP() {
        return this.codeHandlerStartP;
    }

    public boolean[] getCodeHasAttributes() {
        return this.codeHasAttributes;
    }

    public int[] getCodeMaxNALocals() {
        return this.codeMaxNALocals;
    }

    public int[] getCodeMaxStack() {
        return this.codeMaxStack;
    }

    public ArrayList<Attribute>[][] getFieldAttributes() {
        return this.fieldAttributes;
    }

    public int[][] getFieldDescrInts() {
        return this.fieldDescrInts;
    }

    public long[][] getFieldFlags() {
        if (this.fieldAccessFlags == null) {
            int i2;
            long mask = 32767L;
            for (i2 = 0; i2 < 16; ++i2) {
                AttributeLayout layout = this.attrMap.getAttributeLayout(i2, 1);
                if (layout == null || layout.isDefaultLayout()) continue;
                mask &= (long)(~(1 << i2));
            }
            this.fieldAccessFlags = new long[this.fieldFlags.length][];
            for (i2 = 0; i2 < this.fieldFlags.length; ++i2) {
                this.fieldAccessFlags[i2] = new long[this.fieldFlags[i2].length];
                for (int j2 = 0; j2 < this.fieldFlags[i2].length; ++j2) {
                    this.fieldAccessFlags[i2][j2] = this.fieldFlags[i2][j2] & mask;
                }
            }
        }
        return this.fieldAccessFlags;
    }

    public IcTuple[][] getIcLocal() {
        return this.icLocal;
    }

    public ArrayList<Attribute>[][] getMethodAttributes() {
        return this.methodAttributes;
    }

    public String[][] getMethodDescr() {
        return this.methodDescr;
    }

    public int[][] getMethodDescrInts() {
        return this.methodDescrInts;
    }

    public long[][] getMethodFlags() {
        if (this.methodAccessFlags == null) {
            int i2;
            long mask = 32767L;
            for (i2 = 0; i2 < 16; ++i2) {
                AttributeLayout layout = this.attrMap.getAttributeLayout(i2, 2);
                if (layout == null || layout.isDefaultLayout()) continue;
                mask &= (long)(~(1 << i2));
            }
            this.methodAccessFlags = new long[this.methodFlags.length][];
            for (i2 = 0; i2 < this.methodFlags.length; ++i2) {
                this.methodAccessFlags[i2] = new long[this.methodFlags[i2].length];
                for (int j2 = 0; j2 < this.methodFlags[i2].length; ++j2) {
                    this.methodAccessFlags[i2][j2] = this.methodFlags[i2][j2] & mask;
                }
            }
        }
        return this.methodAccessFlags;
    }

    public ArrayList<List<Attribute>> getOrderedCodeAttributes() {
        return Stream.of(this.codeAttributes).map(ArrayList::new).collect(Collectors.toCollection(ArrayList::new));
    }

    public long[] getRawClassFlags() {
        return this.classFlags;
    }

    private void parseClassAttrBands(InputStream in) throws IOException, Pack200Exception {
        int i3;
        String[] cpUTF8 = this.cpBands.getCpUTF8();
        String[] cpClass = this.cpBands.getCpClass();
        this.classAttributes = new ArrayList[this.classCount];
        Arrays.setAll(this.classAttributes, i2 -> new ArrayList());
        this.classFlags = this.parseFlags("class_flags", in, this.classCount, Codec.UNSIGNED5, this.options.hasClassFlagsHi());
        int classAttrCount = SegmentUtils.countBit16(this.classFlags);
        int[] classAttrCounts = this.decodeBandInt("class_attr_count", in, Codec.UNSIGNED5, classAttrCount);
        int[][] classAttrIndexes = this.decodeBandInt("class_attr_indexes", in, Codec.UNSIGNED5, classAttrCounts);
        int callCount = this.getCallCount(classAttrIndexes, new long[][]{this.classFlags}, 0);
        int[] classAttrCalls = this.decodeBandInt("class_attr_calls", in, Codec.UNSIGNED5, callCount);
        AttributeLayout deprecatedLayout = this.attrMap.getAttributeLayout("Deprecated", 0);
        AttributeLayout sourceFileLayout = this.attrMap.getAttributeLayout("SourceFile", 0);
        int sourceFileCount = SegmentUtils.countMatches(this.classFlags, (IMatcher)sourceFileLayout);
        int[] classSourceFile = this.decodeBandInt("class_SourceFile_RUN", in, Codec.UNSIGNED5, sourceFileCount);
        AttributeLayout enclosingMethodLayout = this.attrMap.getAttributeLayout("EnclosingMethod", 0);
        int enclosingMethodCount = SegmentUtils.countMatches(this.classFlags, (IMatcher)enclosingMethodLayout);
        int[] enclosingMethodRC = this.decodeBandInt("class_EnclosingMethod_RC", in, Codec.UNSIGNED5, enclosingMethodCount);
        int[] enclosingMethodRDN = this.decodeBandInt("class_EnclosingMethod_RDN", in, Codec.UNSIGNED5, enclosingMethodCount);
        AttributeLayout signatureLayout = this.attrMap.getAttributeLayout("Signature", 0);
        int signatureCount = SegmentUtils.countMatches(this.classFlags, (IMatcher)signatureLayout);
        int[] classSignature = this.decodeBandInt("class_Signature_RS", in, Codec.UNSIGNED5, signatureCount);
        int backwardsCallsUsed = this.parseClassMetadataBands(in, classAttrCalls);
        AttributeLayout innerClassLayout = this.attrMap.getAttributeLayout("InnerClasses", 0);
        int innerClassCount = SegmentUtils.countMatches(this.classFlags, (IMatcher)innerClassLayout);
        int[] classInnerClassesN = this.decodeBandInt("class_InnerClasses_N", in, Codec.UNSIGNED5, innerClassCount);
        int[][] classInnerClassesRC = this.decodeBandInt("class_InnerClasses_RC", in, Codec.UNSIGNED5, classInnerClassesN);
        int[][] classInnerClassesF = this.decodeBandInt("class_InnerClasses_F", in, Codec.UNSIGNED5, classInnerClassesN);
        int flagsCount = 0;
        int[][] nArray = classInnerClassesF;
        int n2 = nArray.length;
        for (int i4 = 0; i4 < n2; ++i4) {
            int[] element;
            for (int element2 : element = nArray[i4]) {
                if (element2 == 0) continue;
                ++flagsCount;
            }
        }
        int[] classInnerClassesOuterRCN = this.decodeBandInt("class_InnerClasses_outer_RCN", in, Codec.UNSIGNED5, flagsCount);
        int[] classInnerClassesNameRUN = this.decodeBandInt("class_InnerClasses_name_RUN", in, Codec.UNSIGNED5, flagsCount);
        AttributeLayout versionLayout = this.attrMap.getAttributeLayout("class-file version", 0);
        int versionCount = SegmentUtils.countMatches(this.classFlags, (IMatcher)versionLayout);
        int[] classFileVersionMinorH = this.decodeBandInt("class_file_version_minor_H", in, Codec.UNSIGNED5, versionCount);
        int[] classFileVersionMajorH = this.decodeBandInt("class_file_version_major_H", in, Codec.UNSIGNED5, versionCount);
        if (versionCount > 0) {
            this.classVersionMajor = new int[this.classCount];
            this.classVersionMinor = new int[this.classCount];
        }
        int defaultVersionMajor = this.header.getDefaultClassMajorVersion();
        int defaultVersionMinor = this.header.getDefaultClassMinorVersion();
        int backwardsCallIndex = backwardsCallsUsed;
        int limit = this.options.hasClassFlagsHi() ? 62 : 31;
        AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
        int[] counts = new int[limit + 1];
        List[] otherAttributes = new List[limit + 1];
        for (i3 = 0; i3 < limit; ++i3) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(i3, 0);
            if (layout == null || layout.isDefaultLayout()) continue;
            otherLayouts[i3] = layout;
            counts[i3] = SegmentUtils.countMatches(this.classFlags, (IMatcher)layout);
        }
        for (i3 = 0; i3 < counts.length; ++i3) {
            if (counts[i3] <= 0) continue;
            NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i3]);
            otherAttributes[i3] = bands.parseAttributes(in, counts[i3]);
            int numBackwardsCallables = otherLayouts[i3].numBackwardsCallables();
            if (numBackwardsCallables <= 0) continue;
            int[] backwardsCalls = new int[numBackwardsCallables];
            System.arraycopy(classAttrCalls, backwardsCallIndex, backwardsCalls, 0, numBackwardsCallables);
            bands.setBackwardsCalls(backwardsCalls);
            backwardsCallIndex += numBackwardsCallables;
        }
        int sourceFileIndex = 0;
        int enclosingMethodIndex = 0;
        int signatureIndex = 0;
        int innerClassIndex = 0;
        int innerClassC2NIndex = 0;
        int versionIndex = 0;
        this.icLocal = new IcTuple[this.classCount][];
        for (int i5 = 0; i5 < this.classCount; ++i5) {
            ClassFileEntry value;
            long result;
            long flag = this.classFlags[i5];
            if (deprecatedLayout.matches(this.classFlags[i5])) {
                this.classAttributes[i5].add(new DeprecatedAttribute());
            }
            if (sourceFileLayout.matches(flag)) {
                result = classSourceFile[sourceFileIndex];
                value = sourceFileLayout.getValue(result, this.cpBands.getConstantPool());
                if (value == null) {
                    String className = this.classThis[i5].substring(this.classThis[i5].lastIndexOf(47) + 1);
                    className = className.substring(className.lastIndexOf(46) + 1);
                    char[] chars = className.toCharArray();
                    int index = -1;
                    for (int j2 = 0; j2 < chars.length; ++j2) {
                        if (chars[j2] > '-') continue;
                        index = j2;
                        break;
                    }
                    if (index > -1) {
                        className = className.substring(0, index);
                    }
                    value = this.cpBands.cpUTF8Value(className + ".java", true);
                }
                this.classAttributes[i5].add(new SourceFileAttribute((CPUTF8)value));
                ++sourceFileIndex;
            }
            if (enclosingMethodLayout.matches(flag)) {
                CPClass theClass = this.cpBands.cpClassValue(enclosingMethodRC[enclosingMethodIndex]);
                CPNameAndType theMethod = null;
                if (enclosingMethodRDN[enclosingMethodIndex] != 0) {
                    theMethod = this.cpBands.cpNameAndTypeValue(enclosingMethodRDN[enclosingMethodIndex] - 1);
                }
                this.classAttributes[i5].add(new EnclosingMethodAttribute(theClass, theMethod));
                ++enclosingMethodIndex;
            }
            if (signatureLayout.matches(flag)) {
                result = classSignature[signatureIndex];
                value = (CPUTF8)signatureLayout.getValue(result, this.cpBands.getConstantPool());
                this.classAttributes[i5].add(new SignatureAttribute((CPUTF8)value));
                ++signatureIndex;
            }
            if (innerClassLayout.matches(flag)) {
                this.icLocal[i5] = new IcTuple[classInnerClassesN[innerClassIndex]];
                for (int j3 = 0; j3 < this.icLocal[i5].length; ++j3) {
                    IcTuple icTuple;
                    int icTupleCIndex = classInnerClassesRC[innerClassIndex][j3];
                    int icTupleC2Index = -1;
                    int icTupleNIndex = -1;
                    String icTupleC = cpClass[icTupleCIndex];
                    int icTupleF = classInnerClassesF[innerClassIndex][j3];
                    String icTupleC2 = null;
                    String icTupleN = null;
                    if (icTupleF != 0) {
                        icTupleC2Index = classInnerClassesOuterRCN[innerClassC2NIndex];
                        icTupleNIndex = classInnerClassesNameRUN[innerClassC2NIndex];
                        icTupleC2 = cpClass[icTupleC2Index];
                        icTupleN = cpUTF8[icTupleNIndex];
                        ++innerClassC2NIndex;
                    } else {
                        IcTuple[] icAll;
                        IcBands icBands = this.segment.getIcBands();
                        for (IcTuple element : icAll = icBands.getIcTuples()) {
                            if (!element.getC().equals(icTupleC)) continue;
                            icTupleF = element.getF();
                            icTupleC2 = element.getC2();
                            icTupleN = element.getN();
                            break;
                        }
                    }
                    this.icLocal[i5][j3] = icTuple = new IcTuple(icTupleC, icTupleF, icTupleC2, icTupleN, icTupleCIndex, icTupleC2Index, icTupleNIndex, j3);
                }
                ++innerClassIndex;
            }
            if (versionLayout.matches(flag)) {
                this.classVersionMajor[i5] = classFileVersionMajorH[versionIndex];
                this.classVersionMinor[i5] = classFileVersionMinorH[versionIndex];
                ++versionIndex;
            } else if (this.classVersionMajor != null) {
                this.classVersionMajor[i5] = defaultVersionMajor;
                this.classVersionMinor[i5] = defaultVersionMinor;
            }
            for (int j4 = 0; j4 < otherLayouts.length; ++j4) {
                if (otherLayouts[j4] == null || !otherLayouts[j4].matches(flag)) continue;
                this.classAttributes[i5].add((Attribute)otherAttributes[j4].get(0));
                otherAttributes[j4].remove(0);
            }
        }
    }

    private int parseClassMetadataBands(InputStream in, int[] classAttrCalls) throws Pack200Exception, IOException {
        int numBackwardsCalls = 0;
        String[] RxA = new String[]{"RVA", "RIA"};
        AttributeLayout rvaLayout = this.attrMap.getAttributeLayout("RuntimeVisibleAnnotations", 0);
        AttributeLayout riaLayout = this.attrMap.getAttributeLayout("RuntimeInvisibleAnnotations", 0);
        int rvaCount = SegmentUtils.countMatches(this.classFlags, (IMatcher)rvaLayout);
        int riaCount = SegmentUtils.countMatches(this.classFlags, (IMatcher)riaLayout);
        int[] RxACount = new int[]{rvaCount, riaCount};
        int[] backwardsCalls = new int[]{0, 0};
        if (rvaCount > 0) {
            ++numBackwardsCalls;
            backwardsCalls[0] = classAttrCalls[0];
            if (riaCount > 0) {
                ++numBackwardsCalls;
                backwardsCalls[1] = classAttrCalls[1];
            }
        } else if (riaCount > 0) {
            ++numBackwardsCalls;
            backwardsCalls[1] = classAttrCalls[0];
        }
        MetadataBandGroup[] mbgs = this.parseMetadata(in, RxA, RxACount, backwardsCalls, "class");
        List<Attribute> rvaAttributes = mbgs[0].getAttributes();
        List<Attribute> riaAttributes = mbgs[1].getAttributes();
        int rvaAttributesIndex = 0;
        int riaAttributesIndex = 0;
        for (int i2 = 0; i2 < this.classFlags.length; ++i2) {
            if (rvaLayout.matches(this.classFlags[i2])) {
                this.classAttributes[i2].add(rvaAttributes.get(rvaAttributesIndex++));
            }
            if (!riaLayout.matches(this.classFlags[i2])) continue;
            this.classAttributes[i2].add(riaAttributes.get(riaAttributesIndex++));
        }
        return numBackwardsCalls;
    }

    private void parseCodeAttrBands(InputStream in, int codeFlagsCount) throws IOException, Pack200Exception {
        int i2;
        long[] codeFlags = this.parseFlags("code_flags", in, codeFlagsCount, Codec.UNSIGNED5, this.segment.getSegmentHeader().getOptions().hasCodeFlagsHi());
        int codeAttrCount = SegmentUtils.countBit16(codeFlags);
        int[] codeAttrCounts = this.decodeBandInt("code_attr_count", in, Codec.UNSIGNED5, codeAttrCount);
        int[][] codeAttrIndexes = this.decodeBandInt("code_attr_indexes", in, Codec.UNSIGNED5, codeAttrCounts);
        int callCount = 0;
        int[][] nArray = codeAttrIndexes;
        int n2 = nArray.length;
        for (int i3 = 0; i3 < n2; ++i3) {
            int[] element;
            for (int index : element = nArray[i3]) {
                AttributeLayout layout = this.attrMap.getAttributeLayout(index, 3);
                callCount += layout.numBackwardsCallables();
            }
        }
        int[] codeAttrCalls = this.decodeBandInt("code_attr_calls", in, Codec.UNSIGNED5, callCount);
        AttributeLayout lineNumberTableLayout = this.attrMap.getAttributeLayout("LineNumberTable", 3);
        int lineNumberTableCount = SegmentUtils.countMatches(codeFlags, (IMatcher)lineNumberTableLayout);
        int[] lineNumberTableN = this.decodeBandInt("code_LineNumberTable_N", in, Codec.UNSIGNED5, lineNumberTableCount);
        int[][] lineNumberTableBciP = this.decodeBandInt("code_LineNumberTable_bci_P", in, Codec.BCI5, lineNumberTableN);
        int[][] lineNumberTableLine = this.decodeBandInt("code_LineNumberTable_line", in, Codec.UNSIGNED5, lineNumberTableN);
        AttributeLayout localVariableTableLayout = this.attrMap.getAttributeLayout("LocalVariableTable", 3);
        AttributeLayout localVariableTypeTableLayout = this.attrMap.getAttributeLayout("LocalVariableTypeTable", 3);
        int lengthLocalVariableNBand = SegmentUtils.countMatches(codeFlags, (IMatcher)localVariableTableLayout);
        int[] localVariableTableN = this.decodeBandInt("code_LocalVariableTable_N", in, Codec.UNSIGNED5, lengthLocalVariableNBand);
        int[][] localVariableTableBciP = this.decodeBandInt("code_LocalVariableTable_bci_P", in, Codec.BCI5, localVariableTableN);
        int[][] localVariableTableSpanO = this.decodeBandInt("code_LocalVariableTable_span_O", in, Codec.BRANCH5, localVariableTableN);
        CPUTF8[][] localVariableTableNameRU = this.parseCPUTF8References("code_LocalVariableTable_name_RU", in, Codec.UNSIGNED5, localVariableTableN);
        CPUTF8[][] localVariableTableTypeRS = this.parseCPSignatureReferences("code_LocalVariableTable_type_RS", in, Codec.UNSIGNED5, localVariableTableN);
        int[][] localVariableTableSlot = this.decodeBandInt("code_LocalVariableTable_slot", in, Codec.UNSIGNED5, localVariableTableN);
        int lengthLocalVariableTypeTableNBand = SegmentUtils.countMatches(codeFlags, (IMatcher)localVariableTypeTableLayout);
        int[] localVariableTypeTableN = this.decodeBandInt("code_LocalVariableTypeTable_N", in, Codec.UNSIGNED5, lengthLocalVariableTypeTableNBand);
        int[][] localVariableTypeTableBciP = this.decodeBandInt("code_LocalVariableTypeTable_bci_P", in, Codec.BCI5, localVariableTypeTableN);
        int[][] localVariableTypeTableSpanO = this.decodeBandInt("code_LocalVariableTypeTable_span_O", in, Codec.BRANCH5, localVariableTypeTableN);
        CPUTF8[][] localVariableTypeTableNameRU = this.parseCPUTF8References("code_LocalVariableTypeTable_name_RU", in, Codec.UNSIGNED5, localVariableTypeTableN);
        CPUTF8[][] localVariableTypeTableTypeRS = this.parseCPSignatureReferences("code_LocalVariableTypeTable_type_RS", in, Codec.UNSIGNED5, localVariableTypeTableN);
        int[][] localVariableTypeTableSlot = this.decodeBandInt("code_LocalVariableTypeTable_slot", in, Codec.UNSIGNED5, localVariableTypeTableN);
        int backwardsCallIndex = 0;
        int limit = this.options.hasCodeFlagsHi() ? 62 : 31;
        AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
        int[] counts = new int[limit + 1];
        List[] otherAttributes = new List[limit + 1];
        for (i2 = 0; i2 < limit; ++i2) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(i2, 3);
            if (layout == null || layout.isDefaultLayout()) continue;
            otherLayouts[i2] = layout;
            counts[i2] = SegmentUtils.countMatches(codeFlags, (IMatcher)layout);
        }
        for (i2 = 0; i2 < counts.length; ++i2) {
            if (counts[i2] <= 0) continue;
            NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i2]);
            otherAttributes[i2] = bands.parseAttributes(in, counts[i2]);
            int numBackwardsCallables = otherLayouts[i2].numBackwardsCallables();
            if (numBackwardsCallables <= 0) continue;
            int[] backwardsCalls = new int[numBackwardsCallables];
            System.arraycopy(codeAttrCalls, backwardsCallIndex, backwardsCalls, 0, numBackwardsCallables);
            bands.setBackwardsCalls(backwardsCalls);
            backwardsCallIndex += numBackwardsCallables;
        }
        int lineNumberIndex = 0;
        int lvtIndex = 0;
        int lvttIndex = 0;
        for (int i4 = 0; i4 < codeFlagsCount; ++i4) {
            if (lineNumberTableLayout.matches(codeFlags[i4])) {
                LineNumberTableAttribute lnta = new LineNumberTableAttribute(lineNumberTableN[lineNumberIndex], lineNumberTableBciP[lineNumberIndex], lineNumberTableLine[lineNumberIndex]);
                ++lineNumberIndex;
                this.codeAttributes[i4].add(lnta);
            }
            if (localVariableTableLayout.matches(codeFlags[i4])) {
                LocalVariableTableAttribute lvta = new LocalVariableTableAttribute(localVariableTableN[lvtIndex], localVariableTableBciP[lvtIndex], localVariableTableSpanO[lvtIndex], localVariableTableNameRU[lvtIndex], localVariableTableTypeRS[lvtIndex], localVariableTableSlot[lvtIndex]);
                ++lvtIndex;
                this.codeAttributes[i4].add(lvta);
            }
            if (localVariableTypeTableLayout.matches(codeFlags[i4])) {
                LocalVariableTypeTableAttribute lvtta = new LocalVariableTypeTableAttribute(localVariableTypeTableN[lvttIndex], localVariableTypeTableBciP[lvttIndex], localVariableTypeTableSpanO[lvttIndex], localVariableTypeTableNameRU[lvttIndex], localVariableTypeTableTypeRS[lvttIndex], localVariableTypeTableSlot[lvttIndex]);
                ++lvttIndex;
                this.codeAttributes[i4].add(lvtta);
            }
            for (int j2 = 0; j2 < otherLayouts.length; ++j2) {
                if (otherLayouts[j2] == null || !otherLayouts[j2].matches(codeFlags[i4])) continue;
                this.codeAttributes[i4].add((Attribute)otherAttributes[j2].get(0));
                otherAttributes[j2].remove(0);
            }
        }
    }

    private void parseCodeBands(InputStream in) throws Pack200Exception, IOException {
        AttributeLayout layout = this.attrMap.getAttributeLayout("Code", 2);
        int codeCount = SegmentUtils.countMatches(this.methodFlags, (IMatcher)layout);
        int[] codeHeaders = this.decodeBandInt("code_headers", in, Codec.BYTE1, codeCount);
        boolean allCodeHasFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        if (!allCodeHasFlags) {
            this.codeHasAttributes = new boolean[codeCount];
        }
        int codeSpecialHeader = 0;
        for (int i3 = 0; i3 < codeCount; ++i3) {
            if (codeHeaders[i3] != 0) continue;
            ++codeSpecialHeader;
            if (allCodeHasFlags) continue;
            this.codeHasAttributes[i3] = true;
        }
        int[] codeMaxStackSpecials = this.decodeBandInt("code_max_stack", in, Codec.UNSIGNED5, codeSpecialHeader);
        int[] codeMaxNALocalsSpecials = this.decodeBandInt("code_max_na_locals", in, Codec.UNSIGNED5, codeSpecialHeader);
        int[] codeHandlerCountSpecials = this.decodeBandInt("code_handler_count", in, Codec.UNSIGNED5, codeSpecialHeader);
        this.codeMaxStack = new int[codeCount];
        this.codeMaxNALocals = new int[codeCount];
        this.codeHandlerCount = new int[codeCount];
        int special = 0;
        for (int i4 = 0; i4 < codeCount; ++i4) {
            int header = 0xFF & codeHeaders[i4];
            if (header < 0) {
                throw new IllegalStateException("Shouldn't get here");
            }
            if (header == 0) {
                this.codeMaxStack[i4] = codeMaxStackSpecials[special];
                this.codeMaxNALocals[i4] = codeMaxNALocalsSpecials[special];
                this.codeHandlerCount[i4] = codeHandlerCountSpecials[special];
                ++special;
                continue;
            }
            if (header <= 144) {
                this.codeMaxStack[i4] = (header - 1) % 12;
                this.codeMaxNALocals[i4] = (header - 1) / 12;
                this.codeHandlerCount[i4] = 0;
                continue;
            }
            if (header <= 208) {
                this.codeMaxStack[i4] = (header - 145) % 8;
                this.codeMaxNALocals[i4] = (header - 145) / 8;
                this.codeHandlerCount[i4] = 1;
                continue;
            }
            if (header <= 255) {
                this.codeMaxStack[i4] = (header - 209) % 7;
                this.codeMaxNALocals[i4] = (header - 209) / 7;
                this.codeHandlerCount[i4] = 2;
                continue;
            }
            throw new IllegalStateException("Shouldn't get here either");
        }
        this.codeHandlerStartP = this.decodeBandInt("code_handler_start_P", in, Codec.BCI5, this.codeHandlerCount);
        this.codeHandlerEndPO = this.decodeBandInt("code_handler_end_PO", in, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerCatchPO = this.decodeBandInt("code_handler_catch_PO", in, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerClassRCN = this.decodeBandInt("code_handler_class_RCN", in, Codec.UNSIGNED5, this.codeHandlerCount);
        int codeFlagsCount = allCodeHasFlags ? codeCount : codeSpecialHeader;
        this.codeAttributes = new List[codeFlagsCount];
        Arrays.setAll(this.codeAttributes, i2 -> new ArrayList());
        this.parseCodeAttrBands(in, codeFlagsCount);
    }

    private void parseFieldAttrBands(InputStream in) throws IOException, Pack200Exception {
        int i2;
        this.fieldFlags = this.parseFlags("field_flags", in, this.classFieldCount, Codec.UNSIGNED5, this.options.hasFieldFlagsHi());
        int fieldAttrCount = SegmentUtils.countBit16(this.fieldFlags);
        int[] fieldAttrCounts = this.decodeBandInt("field_attr_count", in, Codec.UNSIGNED5, fieldAttrCount);
        int[][] fieldAttrIndexes = this.decodeBandInt("field_attr_indexes", in, Codec.UNSIGNED5, fieldAttrCounts);
        int callCount = this.getCallCount(fieldAttrIndexes, this.fieldFlags, 1);
        int[] fieldAttrCalls = this.decodeBandInt("field_attr_calls", in, Codec.UNSIGNED5, callCount);
        this.fieldAttributes = new ArrayList[this.classCount][];
        for (int i3 = 0; i3 < this.classCount; ++i3) {
            this.fieldAttributes[i3] = new ArrayList[this.fieldFlags[i3].length];
            for (int j2 = 0; j2 < this.fieldFlags[i3].length; ++j2) {
                this.fieldAttributes[i3][j2] = new ArrayList();
            }
        }
        AttributeLayout constantValueLayout = this.attrMap.getAttributeLayout("ConstantValue", 1);
        int constantCount = SegmentUtils.countMatches(this.fieldFlags, (IMatcher)constantValueLayout);
        int[] field_constantValue_KQ = this.decodeBandInt("field_ConstantValue_KQ", in, Codec.UNSIGNED5, constantCount);
        int constantValueIndex = 0;
        AttributeLayout signatureLayout = this.attrMap.getAttributeLayout("Signature", 1);
        int signatureCount = SegmentUtils.countMatches(this.fieldFlags, (IMatcher)signatureLayout);
        int[] fieldSignatureRS = this.decodeBandInt("field_Signature_RS", in, Codec.UNSIGNED5, signatureCount);
        int signatureIndex = 0;
        AttributeLayout deprecatedLayout = this.attrMap.getAttributeLayout("Deprecated", 1);
        for (int i4 = 0; i4 < this.classCount; ++i4) {
            for (int j3 = 0; j3 < this.fieldFlags[i4].length; ++j3) {
                ClassFileEntry value;
                String type;
                int colon;
                String desc;
                long result;
                long flag = this.fieldFlags[i4][j3];
                if (deprecatedLayout.matches(flag)) {
                    this.fieldAttributes[i4][j3].add(new DeprecatedAttribute());
                }
                if (constantValueLayout.matches(flag)) {
                    result = field_constantValue_KQ[constantValueIndex];
                    desc = this.fieldDescr[i4][j3];
                    colon = desc.indexOf(58);
                    type = desc.substring(colon + 1);
                    if (type.equals("B") || type.equals("S") || type.equals("C") || type.equals("Z")) {
                        type = "I";
                    }
                    value = constantValueLayout.getValue(result, type, this.cpBands.getConstantPool());
                    this.fieldAttributes[i4][j3].add(new ConstantValueAttribute(value));
                    ++constantValueIndex;
                }
                if (!signatureLayout.matches(flag)) continue;
                result = fieldSignatureRS[signatureIndex];
                desc = this.fieldDescr[i4][j3];
                colon = desc.indexOf(58);
                type = desc.substring(colon + 1);
                value = (CPUTF8)signatureLayout.getValue(result, type, this.cpBands.getConstantPool());
                this.fieldAttributes[i4][j3].add(new SignatureAttribute((CPUTF8)value));
                ++signatureIndex;
            }
        }
        int backwardsCallIndex = this.parseFieldMetadataBands(in, fieldAttrCalls);
        int limit = this.options.hasFieldFlagsHi() ? 62 : 31;
        AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
        int[] counts = new int[limit + 1];
        List[] otherAttributes = new List[limit + 1];
        for (i2 = 0; i2 < limit; ++i2) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(i2, 1);
            if (layout == null || layout.isDefaultLayout()) continue;
            otherLayouts[i2] = layout;
            counts[i2] = SegmentUtils.countMatches(this.fieldFlags, (IMatcher)layout);
        }
        for (i2 = 0; i2 < counts.length; ++i2) {
            if (counts[i2] <= 0) continue;
            NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i2]);
            otherAttributes[i2] = bands.parseAttributes(in, counts[i2]);
            int numBackwardsCallables = otherLayouts[i2].numBackwardsCallables();
            if (numBackwardsCallables <= 0) continue;
            int[] backwardsCalls = new int[numBackwardsCallables];
            System.arraycopy(fieldAttrCalls, backwardsCallIndex, backwardsCalls, 0, numBackwardsCallables);
            bands.setBackwardsCalls(backwardsCalls);
            backwardsCallIndex += numBackwardsCallables;
        }
        for (i2 = 0; i2 < this.classCount; ++i2) {
            for (int j4 = 0; j4 < this.fieldFlags[i2].length; ++j4) {
                long flag = this.fieldFlags[i2][j4];
                int othersAddedAtStart = 0;
                for (int k2 = 0; k2 < otherLayouts.length; ++k2) {
                    if (otherLayouts[k2] == null || !otherLayouts[k2].matches(flag)) continue;
                    if (otherLayouts[k2].getIndex() < 15) {
                        this.fieldAttributes[i2][j4].add(othersAddedAtStart++, (Attribute)otherAttributes[k2].get(0));
                    } else {
                        this.fieldAttributes[i2][j4].add((Attribute)otherAttributes[k2].get(0));
                    }
                    otherAttributes[k2].remove(0);
                }
            }
        }
    }

    private void parseFieldBands(InputStream in) throws IOException, Pack200Exception {
        this.fieldDescrInts = this.decodeBandInt("field_descr", in, Codec.DELTA5, this.classFieldCount);
        this.fieldDescr = this.getReferences(this.fieldDescrInts, this.cpBands.getCpDescriptor());
        this.parseFieldAttrBands(in);
    }

    private int parseFieldMetadataBands(InputStream in, int[] fieldAttrCalls) throws Pack200Exception, IOException {
        int backwardsCallsUsed = 0;
        String[] RxA = new String[]{"RVA", "RIA"};
        AttributeLayout rvaLayout = this.attrMap.getAttributeLayout("RuntimeVisibleAnnotations", 1);
        AttributeLayout riaLayout = this.attrMap.getAttributeLayout("RuntimeInvisibleAnnotations", 1);
        int rvaCount = SegmentUtils.countMatches(this.fieldFlags, (IMatcher)rvaLayout);
        int riaCount = SegmentUtils.countMatches(this.fieldFlags, (IMatcher)riaLayout);
        int[] RxACount = new int[]{rvaCount, riaCount};
        int[] backwardsCalls = new int[]{0, 0};
        if (rvaCount > 0) {
            backwardsCalls[0] = fieldAttrCalls[0];
            ++backwardsCallsUsed;
            if (riaCount > 0) {
                backwardsCalls[1] = fieldAttrCalls[1];
                ++backwardsCallsUsed;
            }
        } else if (riaCount > 0) {
            backwardsCalls[1] = fieldAttrCalls[0];
            ++backwardsCallsUsed;
        }
        MetadataBandGroup[] mb2 = this.parseMetadata(in, RxA, RxACount, backwardsCalls, "field");
        List<Attribute> rvaAttributes = mb2[0].getAttributes();
        List<Attribute> riaAttributes = mb2[1].getAttributes();
        int rvaAttributesIndex = 0;
        int riaAttributesIndex = 0;
        for (int i2 = 0; i2 < this.fieldFlags.length; ++i2) {
            for (int j2 = 0; j2 < this.fieldFlags[i2].length; ++j2) {
                if (rvaLayout.matches(this.fieldFlags[i2][j2])) {
                    this.fieldAttributes[i2][j2].add(rvaAttributes.get(rvaAttributesIndex++));
                }
                if (!riaLayout.matches(this.fieldFlags[i2][j2])) continue;
                this.fieldAttributes[i2][j2].add(riaAttributes.get(riaAttributesIndex++));
            }
        }
        return backwardsCallsUsed;
    }

    private MetadataBandGroup[] parseMetadata(InputStream in, String[] RxA, int[] RxACount, int[] backwardsCallCounts, String contextName) throws IOException, Pack200Exception {
        MetadataBandGroup[] mbg = new MetadataBandGroup[RxA.length];
        for (int i2 = 0; i2 < RxA.length; ++i2) {
            mbg[i2] = new MetadataBandGroup(RxA[i2], this.cpBands);
            String rxa = RxA[i2];
            if (rxa.indexOf(80) >= 0) {
                mbg[i2].param_NB = this.decodeBandInt(contextName + "_" + rxa + "_param_NB", in, Codec.BYTE1, RxACount[i2]);
            }
            int pairCount = 0;
            if (!rxa.equals("AD")) {
                mbg[i2].anno_N = this.decodeBandInt(contextName + "_" + rxa + "_anno_N", in, Codec.UNSIGNED5, RxACount[i2]);
                mbg[i2].type_RS = this.parseCPSignatureReferences(contextName + "_" + rxa + "_type_RS", in, Codec.UNSIGNED5, mbg[i2].anno_N);
                int[][] nArray = mbg[i2].pair_N = this.decodeBandInt(contextName + "_" + rxa + "_pair_N", in, Codec.UNSIGNED5, mbg[i2].anno_N);
                int n2 = nArray.length;
                for (int i3 = 0; i3 < n2; ++i3) {
                    int[] element;
                    for (int element2 : element = nArray[i3]) {
                        pairCount += element2;
                    }
                }
                mbg[i2].name_RU = this.parseCPUTF8References(contextName + "_" + rxa + "_name_RU", in, Codec.UNSIGNED5, pairCount);
            } else {
                pairCount = RxACount[i2];
            }
            mbg[i2].T = this.decodeBandInt(contextName + "_" + rxa + "_T", in, Codec.BYTE1, pairCount + backwardsCallCounts[i2]);
            int ICount = 0;
            int DCount = 0;
            int FCount = 0;
            int JCount = 0;
            int cCount = 0;
            int eCount = 0;
            int sCount = 0;
            int arrayCount = 0;
            int atCount = 0;
            block14: for (int element : mbg[i2].T) {
                char c2 = (char)element;
                switch (c2) {
                    case 'B': 
                    case 'C': 
                    case 'I': 
                    case 'S': 
                    case 'Z': {
                        ++ICount;
                        continue block14;
                    }
                    case 'D': {
                        ++DCount;
                        continue block14;
                    }
                    case 'F': {
                        ++FCount;
                        continue block14;
                    }
                    case 'J': {
                        ++JCount;
                        continue block14;
                    }
                    case 'c': {
                        ++cCount;
                        continue block14;
                    }
                    case 'e': {
                        ++eCount;
                        continue block14;
                    }
                    case 's': {
                        ++sCount;
                        continue block14;
                    }
                    case '[': {
                        ++arrayCount;
                        continue block14;
                    }
                    case '@': {
                        ++atCount;
                    }
                }
            }
            mbg[i2].caseI_KI = this.parseCPIntReferences(contextName + "_" + rxa + "_caseI_KI", in, Codec.UNSIGNED5, ICount);
            mbg[i2].caseD_KD = this.parseCPDoubleReferences(contextName + "_" + rxa + "_caseD_KD", in, Codec.UNSIGNED5, DCount);
            mbg[i2].caseF_KF = this.parseCPFloatReferences(contextName + "_" + rxa + "_caseF_KF", in, Codec.UNSIGNED5, FCount);
            mbg[i2].caseJ_KJ = this.parseCPLongReferences(contextName + "_" + rxa + "_caseJ_KJ", in, Codec.UNSIGNED5, JCount);
            mbg[i2].casec_RS = this.parseCPSignatureReferences(contextName + "_" + rxa + "_casec_RS", in, Codec.UNSIGNED5, cCount);
            mbg[i2].caseet_RS = this.parseReferences(contextName + "_" + rxa + "_caseet_RS", in, Codec.UNSIGNED5, eCount, this.cpBands.getCpSignature());
            mbg[i2].caseec_RU = this.parseReferences(contextName + "_" + rxa + "_caseec_RU", in, Codec.UNSIGNED5, eCount, this.cpBands.getCpUTF8());
            mbg[i2].cases_RU = this.parseCPUTF8References(contextName + "_" + rxa + "_cases_RU", in, Codec.UNSIGNED5, sCount);
            mbg[i2].casearray_N = this.decodeBandInt(contextName + "_" + rxa + "_casearray_N", in, Codec.UNSIGNED5, arrayCount);
            mbg[i2].nesttype_RS = this.parseCPUTF8References(contextName + "_" + rxa + "_nesttype_RS", in, Codec.UNSIGNED5, atCount);
            mbg[i2].nestpair_N = this.decodeBandInt(contextName + "_" + rxa + "_nestpair_N", in, Codec.UNSIGNED5, atCount);
            int nestPairCount = 0;
            for (int element : mbg[i2].nestpair_N) {
                nestPairCount += element;
            }
            mbg[i2].nestname_RU = this.parseCPUTF8References(contextName + "_" + rxa + "_nestname_RU", in, Codec.UNSIGNED5, nestPairCount);
        }
        return mbg;
    }

    private void parseMethodAttrBands(InputStream in) throws IOException, Pack200Exception {
        int i2;
        this.methodFlags = this.parseFlags("method_flags", in, this.classMethodCount, Codec.UNSIGNED5, this.options.hasMethodFlagsHi());
        int methodAttrCount = SegmentUtils.countBit16(this.methodFlags);
        int[] methodAttrCounts = this.decodeBandInt("method_attr_count", in, Codec.UNSIGNED5, methodAttrCount);
        int[][] methodAttrIndexes = this.decodeBandInt("method_attr_indexes", in, Codec.UNSIGNED5, methodAttrCounts);
        int callCount = this.getCallCount(methodAttrIndexes, this.methodFlags, 2);
        this.methodAttrCalls = this.decodeBandInt("method_attr_calls", in, Codec.UNSIGNED5, callCount);
        this.methodAttributes = new ArrayList[this.classCount][];
        for (int i3 = 0; i3 < this.classCount; ++i3) {
            this.methodAttributes[i3] = new ArrayList[this.methodFlags[i3].length];
            for (int j2 = 0; j2 < this.methodFlags[i3].length; ++j2) {
                this.methodAttributes[i3][j2] = new ArrayList();
            }
        }
        AttributeLayout methodExceptionsLayout = this.attrMap.getAttributeLayout("Exceptions", 2);
        int count = SegmentUtils.countMatches(this.methodFlags, (IMatcher)methodExceptionsLayout);
        int[] numExceptions = this.decodeBandInt("method_Exceptions_n", in, Codec.UNSIGNED5, count);
        int[][] methodExceptionsRS = this.decodeBandInt("method_Exceptions_RC", in, Codec.UNSIGNED5, numExceptions);
        AttributeLayout methodSignatureLayout = this.attrMap.getAttributeLayout("Signature", 2);
        int count1 = SegmentUtils.countMatches(this.methodFlags, (IMatcher)methodSignatureLayout);
        int[] methodSignatureRS = this.decodeBandInt("method_signature_RS", in, Codec.UNSIGNED5, count1);
        AttributeLayout deprecatedLayout = this.attrMap.getAttributeLayout("Deprecated", 2);
        int methodExceptionsIndex = 0;
        int methodSignatureIndex = 0;
        for (int i4 = 0; i4 < this.methodAttributes.length; ++i4) {
            for (int j3 = 0; j3 < this.methodAttributes[i4].length; ++j3) {
                long flag = this.methodFlags[i4][j3];
                if (methodExceptionsLayout.matches(flag)) {
                    int n2 = numExceptions[methodExceptionsIndex];
                    int[] exceptions = methodExceptionsRS[methodExceptionsIndex];
                    CPClass[] exceptionClasses = new CPClass[n2];
                    for (int k2 = 0; k2 < n2; ++k2) {
                        exceptionClasses[k2] = this.cpBands.cpClassValue(exceptions[k2]);
                    }
                    this.methodAttributes[i4][j3].add(new ExceptionsAttribute(exceptionClasses));
                    ++methodExceptionsIndex;
                }
                if (methodSignatureLayout.matches(flag)) {
                    long result = methodSignatureRS[methodSignatureIndex];
                    String desc = this.methodDescr[i4][j3];
                    int colon = desc.indexOf(58);
                    String type = desc.substring(colon + 1);
                    if (type.equals("B") || type.equals("H")) {
                        type = "I";
                    }
                    CPUTF8 value = (CPUTF8)methodSignatureLayout.getValue(result, type, this.cpBands.getConstantPool());
                    this.methodAttributes[i4][j3].add(new SignatureAttribute(value));
                    ++methodSignatureIndex;
                }
                if (!deprecatedLayout.matches(flag)) continue;
                this.methodAttributes[i4][j3].add(new DeprecatedAttribute());
            }
        }
        int backwardsCallIndex = this.parseMethodMetadataBands(in, this.methodAttrCalls);
        int limit = this.options.hasMethodFlagsHi() ? 62 : 31;
        AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
        int[] counts = new int[limit + 1];
        for (int i5 = 0; i5 < limit; ++i5) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(i5, 2);
            if (layout == null || layout.isDefaultLayout()) continue;
            otherLayouts[i5] = layout;
            counts[i5] = SegmentUtils.countMatches(this.methodFlags, (IMatcher)layout);
        }
        List[] otherAttributes = new List[limit + 1];
        for (i2 = 0; i2 < counts.length; ++i2) {
            if (counts[i2] <= 0) continue;
            NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i2]);
            otherAttributes[i2] = bands.parseAttributes(in, counts[i2]);
            int numBackwardsCallables = otherLayouts[i2].numBackwardsCallables();
            if (numBackwardsCallables <= 0) continue;
            int[] backwardsCalls = new int[numBackwardsCallables];
            System.arraycopy(this.methodAttrCalls, backwardsCallIndex, backwardsCalls, 0, numBackwardsCallables);
            bands.setBackwardsCalls(backwardsCalls);
            backwardsCallIndex += numBackwardsCallables;
        }
        for (i2 = 0; i2 < this.methodAttributes.length; ++i2) {
            for (int j4 = 0; j4 < this.methodAttributes[i2].length; ++j4) {
                long flag = this.methodFlags[i2][j4];
                int othersAddedAtStart = 0;
                for (int k3 = 0; k3 < otherLayouts.length; ++k3) {
                    if (otherLayouts[k3] == null || !otherLayouts[k3].matches(flag)) continue;
                    if (otherLayouts[k3].getIndex() < 15) {
                        this.methodAttributes[i2][j4].add(othersAddedAtStart++, (Attribute)otherAttributes[k3].get(0));
                    } else {
                        this.methodAttributes[i2][j4].add((Attribute)otherAttributes[k3].get(0));
                    }
                    otherAttributes[k3].remove(0);
                }
            }
        }
    }

    private void parseMethodBands(InputStream in) throws IOException, Pack200Exception {
        this.methodDescrInts = this.decodeBandInt("method_descr", in, Codec.MDELTA5, this.classMethodCount);
        this.methodDescr = this.getReferences(this.methodDescrInts, this.cpBands.getCpDescriptor());
        this.parseMethodAttrBands(in);
    }

    private int parseMethodMetadataBands(InputStream in, int[] methodAttrCalls) throws Pack200Exception, IOException {
        int i3;
        int backwardsCallsUsed = 0;
        String[] RxA = new String[]{"RVA", "RIA", "RVPA", "RIPA", "AD"};
        int[] rxaCounts = new int[]{0, 0, 0, 0, 0};
        AttributeLayout rvaLayout = this.attrMap.getAttributeLayout("RuntimeVisibleAnnotations", 2);
        AttributeLayout riaLayout = this.attrMap.getAttributeLayout("RuntimeInvisibleAnnotations", 2);
        AttributeLayout rvpaLayout = this.attrMap.getAttributeLayout("RuntimeVisibleParameterAnnotations", 2);
        AttributeLayout ripaLayout = this.attrMap.getAttributeLayout("RuntimeInvisibleParameterAnnotations", 2);
        AttributeLayout adLayout = this.attrMap.getAttributeLayout("AnnotationDefault", 2);
        AttributeLayout[] rxaLayouts = new AttributeLayout[]{rvaLayout, riaLayout, rvpaLayout, ripaLayout, adLayout};
        Arrays.setAll(rxaCounts, i2 -> SegmentUtils.countMatches(this.methodFlags, (IMatcher)rxaLayouts[i2]));
        int[] backwardsCalls = new int[5];
        int methodAttrIndex = 0;
        for (int i4 = 0; i4 < backwardsCalls.length; ++i4) {
            if (rxaCounts[i4] > 0) {
                ++backwardsCallsUsed;
                backwardsCalls[i4] = methodAttrCalls[methodAttrIndex];
                ++methodAttrIndex;
                continue;
            }
            backwardsCalls[i4] = 0;
        }
        MetadataBandGroup[] mbgs = this.parseMetadata(in, RxA, rxaCounts, backwardsCalls, "method");
        List[] attributeLists = new List[RxA.length];
        int[] attributeListIndexes = new int[RxA.length];
        for (i3 = 0; i3 < mbgs.length; ++i3) {
            attributeLists[i3] = mbgs[i3].getAttributes();
            attributeListIndexes[i3] = 0;
        }
        for (i3 = 0; i3 < this.methodFlags.length; ++i3) {
            for (int j2 = 0; j2 < this.methodFlags[i3].length; ++j2) {
                for (int k2 = 0; k2 < rxaLayouts.length; ++k2) {
                    if (!rxaLayouts[k2].matches(this.methodFlags[i3][j2])) continue;
                    int n2 = k2;
                    int n3 = attributeListIndexes[n2];
                    attributeListIndexes[n2] = n3 + 1;
                    this.methodAttributes[i3][j2].add((Attribute)attributeLists[k2].get(n3));
                }
            }
        }
        return backwardsCallsUsed;
    }

    @Override
    public void read(InputStream in) throws IOException, Pack200Exception {
        int classCount = this.header.getClassCount();
        this.classThisInts = this.decodeBandInt("class_this", in, Codec.DELTA5, classCount);
        this.classThis = this.getReferences(this.classThisInts, this.cpBands.getCpClass());
        this.classSuperInts = this.decodeBandInt("class_super", in, Codec.DELTA5, classCount);
        int[] classInterfaceLengths = this.decodeBandInt("class_interface_count", in, Codec.DELTA5, classCount);
        this.classInterfacesInts = this.decodeBandInt("class_interface", in, Codec.DELTA5, classInterfaceLengths);
        this.classFieldCount = this.decodeBandInt("class_field_count", in, Codec.DELTA5, classCount);
        this.classMethodCount = this.decodeBandInt("class_method_count", in, Codec.DELTA5, classCount);
        this.parseFieldBands(in);
        this.parseMethodBands(in);
        this.parseClassAttrBands(in);
        this.parseCodeBands(in);
    }

    @Override
    public void unpack() {
    }
}

