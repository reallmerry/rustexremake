/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry;

public class CPFieldRef
extends ConstantPoolEntry {
    CPClass className;
    transient int classNameIndex;
    private final CPNameAndType nameAndType;
    transient int nameAndTypeIndex;
    private boolean hashCodeComputed;
    private int cachedHashCode;

    public CPFieldRef(CPClass className, CPNameAndType descriptor, int globalIndex) {
        super((byte)9, globalIndex);
        this.className = className;
        this.nameAndType = descriptor;
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
        CPFieldRef other = (CPFieldRef)obj;
        if (!Objects.equals(this.className, other.className)) {
            return false;
        }
        return Objects.equals(this.nameAndType, other.nameAndType);
    }

    private void generateHashCode() {
        this.hashCodeComputed = true;
        int PRIME = 31;
        int result = 1;
        result = 31 * result + (this.className == null ? 0 : this.className.hashCode());
        this.cachedHashCode = result = 31 * result + (this.nameAndType == null ? 0 : this.nameAndType.hashCode());
    }

    @Override
    protected ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.className, this.nameAndType};
    }

    @Override
    public int hashCode() {
        if (!this.hashCodeComputed) {
            this.generateHashCode();
        }
        return this.cachedHashCode;
    }

    @Override
    protected void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.nameAndTypeIndex = pool.indexOf(this.nameAndType);
        this.classNameIndex = pool.indexOf(this.className);
    }

    @Override
    public String toString() {
        return "FieldRef: " + this.className + "#" + this.nameAndType;
    }

    @Override
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.classNameIndex);
        dos.writeShort(this.nameAndTypeIndex);
    }
}

