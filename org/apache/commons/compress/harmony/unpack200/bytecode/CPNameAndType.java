/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.compress.harmony.unpack200.SegmentUtils;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry;

public class CPNameAndType
extends ConstantPoolEntry {
    CPUTF8 descriptor;
    transient int descriptorIndex;
    CPUTF8 name;
    transient int nameIndex;
    private boolean hashCodeComputed;
    private int cachedHashCode;

    public CPNameAndType(CPUTF8 name, CPUTF8 descriptor, int globalIndex) {
        super((byte)12, globalIndex);
        this.name = Objects.requireNonNull(name, "name");
        this.descriptor = Objects.requireNonNull(descriptor, "descriptor");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CPNameAndType other = (CPNameAndType)obj;
        if (!this.descriptor.equals(other.descriptor)) {
            return false;
        }
        return this.name.equals(other.name);
    }

    private void generateHashCode() {
        this.hashCodeComputed = true;
        int PRIME = 31;
        int result = 1;
        result = 31 * result + this.descriptor.hashCode();
        this.cachedHashCode = result = 31 * result + this.name.hashCode();
    }

    @Override
    protected ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.name, this.descriptor};
    }

    @Override
    public int hashCode() {
        if (!this.hashCodeComputed) {
            this.generateHashCode();
        }
        return this.cachedHashCode;
    }

    public int invokeInterfaceCount() {
        return 1 + SegmentUtils.countInvokeInterfaceArgs(this.descriptor.underlyingString());
    }

    @Override
    protected void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.descriptorIndex = pool.indexOf(this.descriptor);
        this.nameIndex = pool.indexOf(this.name);
    }

    @Override
    public String toString() {
        return "NameAndType: " + this.name + "(" + this.descriptor + ")";
    }

    @Override
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.nameIndex);
        dos.writeShort(this.descriptorIndex);
    }
}

