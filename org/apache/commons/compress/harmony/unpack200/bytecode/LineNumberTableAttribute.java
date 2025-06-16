/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;

public class LineNumberTableAttribute
extends BCIRenumberedAttribute {
    private static CPUTF8 attributeName;
    private final int lineNumberTableLength;
    private final int[] startPcs;
    private final int[] lineNumbers;

    public static void setAttributeName(CPUTF8 cpUTF8Value) {
        attributeName = cpUTF8Value;
    }

    public LineNumberTableAttribute(int lineNumberTableLength, int[] startPcs, int[] lineNumbers) {
        super(attributeName);
        this.lineNumberTableLength = lineNumberTableLength;
        this.startPcs = startPcs;
        this.lineNumbers = lineNumbers;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    protected int getLength() {
        return 2 + 4 * this.lineNumberTableLength;
    }

    @Override
    protected ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.getAttributeName()};
    }

    @Override
    protected int[] getStartPCs() {
        return this.startPcs;
    }

    @Override
    protected void resolve(ClassConstantPool pool) {
        super.resolve(pool);
    }

    @Override
    public String toString() {
        return "LineNumberTable: " + this.lineNumberTableLength + " lines";
    }

    @Override
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.lineNumberTableLength);
        for (int i2 = 0; i2 < this.lineNumberTableLength; ++i2) {
            dos.writeShort(this.startPcs[i2]);
            dos.writeShort(this.lineNumbers[i2]);
        }
    }
}

