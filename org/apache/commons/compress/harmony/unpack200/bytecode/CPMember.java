/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;

public class CPMember
extends ClassFileEntry {
    List<Attribute> attributes;
    short flags;
    CPUTF8 name;
    transient int nameIndex;
    protected final CPUTF8 descriptor;
    transient int descriptorIndex;

    public CPMember(CPUTF8 name, CPUTF8 descriptor, long flags, List<Attribute> attributes) {
        this.name = Objects.requireNonNull(name, "name");
        this.descriptor = Objects.requireNonNull(descriptor, "descriptor");
        this.flags = (short)flags;
        this.attributes = attributes == null ? Collections.EMPTY_LIST : attributes;
    }

    @Override
    protected void doWrite(DataOutputStream dos) throws IOException {
        dos.writeShort(this.flags);
        dos.writeShort(this.nameIndex);
        dos.writeShort(this.descriptorIndex);
        int attributeCount = this.attributes.size();
        dos.writeShort(attributeCount);
        for (int i2 = 0; i2 < attributeCount; ++i2) {
            Attribute attribute = this.attributes.get(i2);
            attribute.doWrite(dos);
        }
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
        CPMember other = (CPMember)obj;
        if (!this.attributes.equals(other.attributes)) {
            return false;
        }
        if (!this.descriptor.equals(other.descriptor)) {
            return false;
        }
        if (this.flags != other.flags) {
            return false;
        }
        return this.name.equals(other.name);
    }

    @Override
    protected ClassFileEntry[] getNestedClassFileEntries() {
        int attributeCount = this.attributes.size();
        ClassFileEntry[] entries = new ClassFileEntry[attributeCount + 2];
        entries[0] = this.name;
        entries[1] = this.descriptor;
        for (int i2 = 0; i2 < attributeCount; ++i2) {
            entries[i2 + 2] = this.attributes.get(i2);
        }
        return entries;
    }

    @Override
    public int hashCode() {
        int PRIME = 31;
        int result = 1;
        result = 31 * result + this.attributes.hashCode();
        result = 31 * result + this.descriptor.hashCode();
        result = 31 * result + this.flags;
        result = 31 * result + this.name.hashCode();
        return result;
    }

    @Override
    protected void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.nameIndex = pool.indexOf(this.name);
        this.descriptorIndex = pool.indexOf(this.descriptor);
        this.attributes.forEach(attribute -> attribute.resolve(pool));
    }

    @Override
    public String toString() {
        return "CPMember: " + this.name + "(" + this.descriptor + ")";
    }
}

