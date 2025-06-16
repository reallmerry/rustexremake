/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.BandSet;
import org.apache.commons.compress.harmony.unpack200.IcTuple;
import org.apache.commons.compress.harmony.unpack200.Segment;
import org.apache.commons.compress.harmony.unpack200.SegmentUtils;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry;

public class IcBands
extends BandSet {
    private IcTuple[] icAll;
    private final String[] cpUTF8;
    private final String[] cpClass;
    private Map<String, IcTuple> thisClassToTuple;
    private Map<String, List<IcTuple>> outerClassToTuples;

    public IcBands(Segment segment) {
        super(segment);
        this.cpClass = segment.getCpBands().getCpClass();
        this.cpUTF8 = segment.getCpBands().getCpUTF8();
    }

    public IcTuple[] getIcTuples() {
        return this.icAll;
    }

    public IcTuple[] getRelevantIcTuples(String className, ClassConstantPool cp) {
        HashSet<IcTuple> relevantTuplesContains = new HashSet<IcTuple>();
        ArrayList<IcTuple> relevantTuples = new ArrayList<IcTuple>();
        List<IcTuple> relevantCandidates = this.outerClassToTuples.get(className);
        if (relevantCandidates != null) {
            for (int index = 0; index < relevantCandidates.size(); ++index) {
                IcTuple tuple = relevantCandidates.get(index);
                relevantTuplesContains.add(tuple);
                relevantTuples.add(tuple);
            }
        }
        List<ClassFileEntry> entries = cp.entries();
        for (int eIndex = 0; eIndex < entries.size(); ++eIndex) {
            ConstantPoolEntry entry = (ConstantPoolEntry)entries.get(eIndex);
            if (!(entry instanceof CPClass)) continue;
            CPClass clazz = (CPClass)entry;
            IcTuple relevant = this.thisClassToTuple.get(clazz.name);
            if (relevant == null || !relevantTuplesContains.add(relevant)) continue;
            relevantTuples.add(relevant);
        }
        ArrayList<IcTuple> tuplesToScan = new ArrayList<IcTuple>(relevantTuples);
        ArrayList<IcTuple> tuplesToAdd = new ArrayList<IcTuple>();
        while (tuplesToScan.size() > 0) {
            int index;
            tuplesToAdd.clear();
            for (index = 0; index < tuplesToScan.size(); ++index) {
                IcTuple aRelevantTuple = (IcTuple)tuplesToScan.get(index);
                IcTuple relevant = this.thisClassToTuple.get(aRelevantTuple.outerClassString());
                if (relevant == null || aRelevantTuple.outerIsAnonymous()) continue;
                tuplesToAdd.add(relevant);
            }
            tuplesToScan.clear();
            for (index = 0; index < tuplesToAdd.size(); ++index) {
                IcTuple tuple = (IcTuple)tuplesToAdd.get(index);
                if (!relevantTuplesContains.add(tuple)) continue;
                relevantTuples.add(tuple);
                tuplesToScan.add(tuple);
            }
        }
        relevantTuples.sort((arg0, arg1) -> {
            Integer index1 = arg0.getTupleIndex();
            Integer index2 = arg1.getTupleIndex();
            return index1.compareTo(index2);
        });
        return relevantTuples.toArray(IcTuple.EMPTY_ARRAY);
    }

    @Override
    public void read(InputStream in) throws IOException, Pack200Exception {
        int innerClassCount = this.header.getInnerClassCount();
        int[] icThisClassInts = this.decodeBandInt("ic_this_class", in, Codec.UDELTA5, innerClassCount);
        String[] icThisClass = this.getReferences(icThisClassInts, this.cpClass);
        int[] icFlags = this.decodeBandInt("ic_flags", in, Codec.UNSIGNED5, innerClassCount);
        int outerClasses = SegmentUtils.countBit16(icFlags);
        int[] icOuterClassInts = this.decodeBandInt("ic_outer_class", in, Codec.DELTA5, outerClasses);
        String[] icOuterClass = new String[outerClasses];
        for (int i2 = 0; i2 < icOuterClass.length; ++i2) {
            icOuterClass[i2] = icOuterClassInts[i2] == 0 ? null : this.cpClass[icOuterClassInts[i2] - 1];
        }
        int[] icNameInts = this.decodeBandInt("ic_name", in, Codec.DELTA5, outerClasses);
        String[] icName = new String[outerClasses];
        for (int i3 = 0; i3 < icName.length; ++i3) {
            icName[i3] = icNameInts[i3] == 0 ? null : this.cpUTF8[icNameInts[i3] - 1];
        }
        this.icAll = new IcTuple[icThisClass.length];
        int index = 0;
        for (int i4 = 0; i4 < icThisClass.length; ++i4) {
            String icTupleC = icThisClass[i4];
            int icTupleF = icFlags[i4];
            String icTupleC2 = null;
            String icTupleN = null;
            int cIndex = icThisClassInts[i4];
            int c2Index = -1;
            int nIndex = -1;
            if ((icFlags[i4] & 0x10000) != 0) {
                icTupleC2 = icOuterClass[index];
                icTupleN = icName[index];
                c2Index = icOuterClassInts[index] - 1;
                nIndex = icNameInts[index] - 1;
                ++index;
            }
            this.icAll[i4] = new IcTuple(icTupleC, icTupleF, icTupleC2, icTupleN, cIndex, c2Index, nIndex, i4);
        }
    }

    @Override
    public void unpack() throws IOException, Pack200Exception {
        IcTuple[] allTuples = this.getIcTuples();
        this.thisClassToTuple = new HashMap<String, IcTuple>(allTuples.length);
        this.outerClassToTuples = new HashMap<String, List<IcTuple>>(allTuples.length);
        for (IcTuple tuple : allTuples) {
            IcTuple result = this.thisClassToTuple.put(tuple.thisClassString(), tuple);
            if (result != null) {
                throw new Error("Collision detected in <thisClassString, IcTuple> mapping. There are at least two inner clases with the same name.");
            }
            if ((tuple.isAnonymous() || tuple.outerIsAnonymous()) && !tuple.nestedExplicitFlagSet()) continue;
            String key = tuple.outerClassString();
            this.outerClassToTuples.computeIfAbsent(key, k2 -> new ArrayList()).add(tuple);
        }
    }
}

