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

public class LocalVariableTableAttribute
extends BCIRenumberedAttribute {
    private static CPUTF8 attributeName;
    private final int localVariableTableLength;
    private final int[] startPcs;
    private final int[] lengths;
    private int[] nameIndexes;
    private int[] descriptorIndexes;
    private final int[] indexes;
    private final CPUTF8[] names;
    private final CPUTF8[] descriptors;
    private int codeLength;

    public static void setAttributeName(CPUTF8 cpUTF8Value) {
        attributeName = cpUTF8Value;
    }

    public LocalVariableTableAttribute(int localVariableTableLength, int[] startPcs, int[] lengths, CPUTF8[] names, CPUTF8[] descriptors, int[] indexes) {
        super(attributeName);
        this.localVariableTableLength = localVariableTableLength;
        this.startPcs = startPcs;
        this.lengths = lengths;
        this.names = names;
        this.descriptors = descriptors;
        this.indexes = indexes;
    }

    @Override
    protected int getLength() {
        return 2 + 10 * this.localVariableTableLength;
    }

    @Override
    protected ClassFileEntry[] getNestedClassFileEntries() {
        ArrayList<CPUTF8> nestedEntries = new ArrayList<CPUTF8>();
        nestedEntries.add(this.getAttributeName());
        for (int i2 = 0; i2 < this.localVariableTableLength; ++i2) {
            nestedEntries.add(this.names[i2]);
            nestedEntries.add(this.descriptors[i2]);
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
        this.nameIndexes = new int[this.localVariableTableLength];
        this.descriptorIndexes = new int[this.localVariableTableLength];
        for (int i2 = 0; i2 < this.localVariableTableLength; ++i2) {
            this.names[i2].resolve(pool);
            this.descriptors[i2].resolve(pool);
            this.nameIndexes[i2] = pool.indexOf(this.names[i2]);
            this.descriptorIndexes[i2] = pool.indexOf(this.descriptors[i2]);
        }
    }

    public void setCodeLength(int length) {
        this.codeLength = length;
    }

    @Override
    public String toString() {
        return "LocalVariableTable: " + this.localVariableTableLength + " variables";
    }

    @Override
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.localVariableTableLength);
        for (int i2 = 0; i2 < this.localVariableTableLength; ++i2) {
            dos.writeShort(this.startPcs[i2]);
            dos.writeShort(this.lengths[i2]);
            dos.writeShort(this.nameIndexes[i2]);
            dos.writeShort(this.descriptorIndexes[i2]);
            dos.writeShort(this.indexes[i2]);
        }
    }
}

