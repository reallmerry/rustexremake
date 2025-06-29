/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import java.util.Arrays;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.apache.commons.compress.harmony.unpack200.bytecode.forms.SwitchForm;

public class LookupSwitchForm
extends SwitchForm {
    public LookupSwitchForm(int opcode, String name) {
        super(opcode, name);
    }

    @Override
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int codeLength) {
        int caseCount = operandManager.nextCaseCount();
        int defaultPc = operandManager.nextLabel();
        int[] caseValues = new int[caseCount];
        Arrays.setAll(caseValues, i2 -> operandManager.nextCaseValues());
        int[] casePcs = new int[caseCount];
        Arrays.setAll(casePcs, i2 -> operandManager.nextLabel());
        int[] labelsArray = new int[caseCount + 1];
        labelsArray[0] = defaultPc;
        System.arraycopy(casePcs, 0, labelsArray, 1, caseCount + 1 - 1);
        byteCode.setByteCodeTargets(labelsArray);
        int padLength = 3 - codeLength % 4;
        int rewriteSize = 1 + padLength + 4 + 4 + 4 * caseValues.length + 4 * casePcs.length;
        int[] newRewrite = new int[rewriteSize];
        int rewriteIndex = 0;
        newRewrite[rewriteIndex++] = byteCode.getOpcode();
        for (int index = 0; index < padLength; ++index) {
            newRewrite[rewriteIndex++] = 0;
        }
        newRewrite[rewriteIndex++] = -1;
        newRewrite[rewriteIndex++] = -1;
        newRewrite[rewriteIndex++] = -1;
        newRewrite[rewriteIndex++] = -1;
        int npairsIndex = rewriteIndex;
        this.setRewrite4Bytes(caseValues.length, npairsIndex, newRewrite);
        rewriteIndex += 4;
        for (int caseValue : caseValues) {
            this.setRewrite4Bytes(caseValue, rewriteIndex, newRewrite);
            rewriteIndex += 4;
            newRewrite[rewriteIndex++] = -1;
            newRewrite[rewriteIndex++] = -1;
            newRewrite[rewriteIndex++] = -1;
            newRewrite[rewriteIndex++] = -1;
        }
        byteCode.setRewrite(newRewrite);
    }
}

