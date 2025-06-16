/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;

public class RuntimeVisibleorInvisibleAnnotationsAttribute
extends AnnotationsAttribute {
    private final int numAnnotations;
    private final AnnotationsAttribute.Annotation[] annotations;

    public RuntimeVisibleorInvisibleAnnotationsAttribute(CPUTF8 name, AnnotationsAttribute.Annotation[] annotations) {
        super(name);
        this.numAnnotations = annotations.length;
        this.annotations = annotations;
    }

    @Override
    protected int getLength() {
        int length = 2;
        for (int i2 = 0; i2 < this.numAnnotations; ++i2) {
            length += this.annotations[i2].getLength();
        }
        return length;
    }

    @Override
    protected ClassFileEntry[] getNestedClassFileEntries() {
        ArrayList<Object> nested = new ArrayList<Object>();
        nested.add(this.attributeName);
        for (AnnotationsAttribute.Annotation annotation : this.annotations) {
            nested.addAll(annotation.getClassFileEntries());
        }
        return nested.toArray(ClassFileEntry.NONE);
    }

    @Override
    protected void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        for (AnnotationsAttribute.Annotation annotation : this.annotations) {
            annotation.resolve(pool);
        }
    }

    @Override
    public String toString() {
        return this.attributeName.underlyingString() + ": " + this.numAnnotations + " annotations";
    }

    @Override
    protected void writeBody(DataOutputStream dos) throws IOException {
        int size = dos.size();
        dos.writeShort(this.numAnnotations);
        for (int i2 = 0; i2 < this.numAnnotations; ++i2) {
            this.annotations[i2].writeBody(dos);
        }
        if (dos.size() - size != this.getLength()) {
            throw new Error();
        }
    }
}

