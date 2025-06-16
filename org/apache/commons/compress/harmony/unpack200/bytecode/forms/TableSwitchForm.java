/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import java.util.Arrays;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.apache.commons.compress.harmony.unpack200.bytecode.forms.SwitchForm;

public class TableSwitchForm
extends SwitchForm {
    public TableSwitchForm(int opcode, String name) {
        super(opcode, name);
    }

    @Override
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int codeLength) {
        int caseCount = operandManager.nextCaseCount();
        int defaultPc = operandManager.nextLabel();
        int caseValue = -1;
        caseValue = operandManager.nextCaseValues();
        int[] casePcs = new int[caseCount];
        Arrays.setAll(casePcs, i2 -> operandManager.nextLabel());
        int[] labelsArray = new int[caseCount + 1];
        labelsArray[0] = defaultPc;
        System.arraycopy(casePcs, 0, labelsArray, 1, caseCount + 1 - 1);
        byteCode.setByteCodeTargets(labelsArray);
        int lowValue = caseValue;
        int highValue = lowValue + caseCount - 1;
        int padLength = 3 - codeLength % 4;
        int rewriteSize = 1 + padLength + 4 + 4 + 4 + 4 * casePcs.length;
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
        int lowbyteIndex = rewriteIndex;
        this.setRewrite4Bytes(lowValue, lowbyteIndex, newRewrite);
        int highbyteIndex = rewriteIndex += 4;
        this.setRewrite4Bytes(highValue, highbyteIndex, newRewrite);
        rewriteIndex += 4;
        for (int index = 0; index < caseCount; ++index) {
            newRewrite[rewriteIndex++] = -1;
            newRewrite[rewriteIndex++] = -1;
            newRewrite[rewriteIndex++] = -1;
            newRewrite[rewriteIndex++] = -1;
        }
        byteCode.setRewrite(newRewrite);
    }
}

