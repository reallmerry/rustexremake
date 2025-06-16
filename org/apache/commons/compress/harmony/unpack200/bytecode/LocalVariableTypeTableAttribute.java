/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;

public class LocalVariableTypeTableAttribute
extends BCIRenumberedAttribute {
    private static CPUTF8 attributeName;
    private final int localVariableTypeTableLength;
    private final int[] startPcs;
    private final int[] lengths;
    private int[] nameIndexes;
    private int[] signatureIndexes;
    private final int[] indexes;
    private final CPUTF8[] names;
    private final CPUTF8[] signatures;
    private int codeLength;

    public static void setAttributeName(CPUTF8 cpUTF8Value) {
        attributeName = cpUTF8Value;
    }

    public LocalVariableTypeTableAttribute(int localVariableTypeTableLength, int[] startPcs, int[] lengths, CPUTF8[] names, CPUTF8[] signatures, int[] indexes) {
        super(attributeName);
        this.localVariableTypeTableLength = localVariableTypeTableLength;
        this.startPcs = startPcs;
        this.lengths = lengths;
        this.names = names;
        this.signatures = signatures;
        this.indexes = indexes;
    }

    @Override
    protected int getLength() {
        return 2 + 10 * this.localVariableTypeTableLength;
    }

    @Override
    protected ClassFileEntry[] getNestedClassFileEntries() {
        ArrayList<CPUTF8> nestedEntries = new ArrayList<CPUTF8>();
        nestedEntries.add(this.getAttributeName());
        for (int i2 = 0; i2 < this.localVariableTypeTableLength; ++i2) {
            nestedEntries.add(this.names[i2]);
            nestedEntries.add(this.signatures[i2]);
        }
        return nestedEntries.toArray(ClassFileEntry.NONE);
    }

    @Override
    protected int[] getStartPCs() {
        return this.startPcs;
    }

    @Override
    public void renumber(List<Integer> byteCodeOffsets) throws Pack200Exception {
        int[] unrenumberedStartPcs = Arrays.copyOf(this.startPcs, this.startPcs.length);
        super.renumber(byteCodeOffsets);
        int maxSize = this.codeLength;
        for (int index = 0; index < this.lengths.length; ++index) {
            int startPc = this.startPcs[index];
            int revisedLength = -1;
            int indexOfStartPC = unrenumberedStartPcs[index];
            int encodedLength = this.lengths[index];
            int stopIndex = indexOfStartPC + encodedLength;
            if (stopIndex < 0) {
                throw new Pack200Exception("Error renumbering bytecode indexes");
            }
            if (stopIndex == byteCodeOffsets.size()) {
                revisedLength = maxSize - startPc;
            } else {
                int stopValue = byteCodeOffsets.get(stopIndex);
                revisedLength = stopValue - startPc;
            }
            this.lengths[index] = revisedLength;
        }
    }

    @Override
    protected void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.nameIndexes = new int[this.localVariableTypeTableLength];
        this.signatureIndexes = new int[this.localVariableTypeTableLength];
        for (int i2 = 0; i2 < this.localVariableTypeTableLength; ++i2) {
            this.names[i2].resolve(pool);
            this.signatures[i2].resolve(pool);
            this.nameIndexes[i2] = pool.indexOf(this.names[i2]);
            this.signatureIndexes[i2] = pool.indexOf(this.signatures[i2]);
        }
    }

    public void setCodeLength(int length) {
        this.codeLength = length;
    }

    @Override
    public String toString() {
        return "LocalVariableTypeTable: " + this.localVariableTypeTableLength + " varaibles";
    }

    @Override
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.localVariableTypeTableLength);
        for (int i2 = 0; i2 < this.localVariableTypeTableLength; ++i2) {
            dos.writeShort(this.startPcs[i2]);
            dos.writeShort(this.lengths[i2]);
            dos.writeShort(this.nameIndexes[i2]);
            dos.writeShort(this.signatureIndexes[i2]);
            dos.writeShort(this.indexes[i2]);
        }
    }
}

