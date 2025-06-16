/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.pack200;

import java.util.List;
import org.apache.commons.compress.harmony.pack200.CPClass;
import org.apache.commons.compress.harmony.pack200.CPUTF8;
import org.apache.commons.compress.harmony.pack200.ConstantPoolEntry;

public class CPSignature
extends ConstantPoolEntry
implements Comparable {
    private final CPUTF8 signatureForm;
    private final List<CPClass> classes;
    private final String signature;
    private final boolean formStartsWithBracket;

    public CPSignature(String signature, CPUTF8 signatureForm, List<CPClass> classes) {
        this.signature = signature;
        this.signatureForm = signatureForm;
        this.classes = classes;
        this.formStartsWithBracket = signatureForm.toString().startsWith("(");
    }

    public int compareTo(Object arg0) {
        if (this.signature.equals(((CPSignature)arg0).signature)) {
            return 0;
        }
        if (this.formStartsWithBracket && !((CPSignature)arg0).formStartsWithBracket) {
            return 1;
        }
        if (((CPSignature)arg0).formStartsWithBracket && !this.formStartsWithBracket) {
            return -1;
        }
        if (this.classes.size() - ((CPSignature)arg0).classes.size() != 0) {
            return this.classes.size() - ((CPSignature)arg0).classes.size();
        }
        if (this.classes.size() > 0) {
            for (int i2 = this.classes.size() - 1; i2 >= 0; --i2) {
                CPClass compareClass;
                CPClass cpClass = this.classes.get(i2);
                int classComp = cpClass.compareTo(compareClass = ((CPSignature)arg0).classes.get(i2));
                if (classComp == 0) continue;
                return classComp;
            }
        }
        return this.signature.compareTo(((CPSignature)arg0).signature);
    }

    public List<CPClass> getClasses() {
        return this.classes;
    }

    public int getIndexInCpUtf8() {
        return this.signatureForm.getIndex();
    }

    public CPUTF8 getSignatureForm() {
        return this.signatureForm;
    }

    public String getUnderlyingString() {
        return this.signature;
    }

    public String toString() {
        return this.signature;
    }
}

