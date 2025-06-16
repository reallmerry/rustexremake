/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.compress.harmony.unpack200.CpBands;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationDefaultAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.RuntimeVisibleorInvisibleAnnotationsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.RuntimeVisibleorInvisibleParameterAnnotationsAttribute;

public class MetadataBandGroup {
    private static CPUTF8 rvaUTF8;
    private static CPUTF8 riaUTF8;
    private static CPUTF8 rvpaUTF8;
    private static CPUTF8 ripaUTF8;
    private final String type;
    private final CpBands cpBands;
    private List<Attribute> attributes;
    public int[] param_NB;
    public int[] anno_N;
    public CPUTF8[][] type_RS;
    public int[][] pair_N;
    public CPUTF8[] name_RU;
    public int[] T;
    public CPInteger[] caseI_KI;
    public CPDouble[] caseD_KD;
    public CPFloat[] caseF_KF;
    public CPLong[] caseJ_KJ;
    public CPUTF8[] casec_RS;
    public String[] caseet_RS;
    public String[] caseec_RU;
    public CPUTF8[] cases_RU;
    public int[] casearray_N;
    public CPUTF8[] nesttype_RS;
    public int[] nestpair_N;
    public CPUTF8[] nestname_RU;
    private int caseI_KI_Index;
    private int caseD_KD_Index;
    private int caseF_KF_Index;
    private int caseJ_KJ_Index;
    private int casec_RS_Index;
    private int caseet_RS_Index;
    private int caseec_RU_Index;
    private int cases_RU_Index;
    private int casearray_N_Index;
    private int T_index;
    private int nesttype_RS_Index;
    private int nestpair_N_Index;
    private Iterator<CPUTF8> nestname_RU_Iterator;
    private int anno_N_Index;
    private int pair_N_Index;

    public static void setRiaAttributeName(CPUTF8 cpUTF8Value) {
        riaUTF8 = cpUTF8Value;
    }

    public static void setRipaAttributeName(CPUTF8 cpUTF8Value) {
        ripaUTF8 = cpUTF8Value;
    }

    public static void setRvaAttributeName(CPUTF8 cpUTF8Value) {
        rvaUTF8 = cpUTF8Value;
    }

    public static void setRvpaAttributeName(CPUTF8 cpUTF8Value) {
        rvpaUTF8 = cpUTF8Value;
    }

    public MetadataBandGroup(String type, CpBands cpBands) {
        this.type = type;
        this.cpBands = cpBands;
    }

    private AnnotationsAttribute.Annotation getAnnotation(CPUTF8 type, int pairCount, Iterator<CPUTF8> namesIterator) {
        CPUTF8[] elementNames = new CPUTF8[pairCount];
        AnnotationsAttribute.ElementValue[] elementValues = new AnnotationsAttribute.ElementValue[pairCount];
        for (int j2 = 0; j2 < elementNames.length; ++j2) {
            elementNames[j2] = namesIterator.next();
            int t2 = this.T[this.T_index++];
            elementValues[j2] = new AnnotationsAttribute.ElementValue(t2, this.getNextValue(t2));
        }
        return new AnnotationsAttribute.Annotation(pairCount, type, elementNames, elementValues);
    }

    private Attribute getAttribute(int numAnnotations, CPUTF8[] types, int[] pairCounts, Iterator<CPUTF8> namesIterator) {
        AnnotationsAttribute.Annotation[] annotations = new AnnotationsAttribute.Annotation[numAnnotations];
        Arrays.setAll(annotations, i2 -> this.getAnnotation(types[i2], pairCounts[i2], namesIterator));
        return new RuntimeVisibleorInvisibleAnnotationsAttribute(this.type.equals("RVA") ? rvaUTF8 : riaUTF8, annotations);
    }

    public List<Attribute> getAttributes() {
        block5: {
            block6: {
                Iterator<CPUTF8> name_RU_Iterator;
                block7: {
                    if (this.attributes != null) break block5;
                    this.attributes = new ArrayList<Attribute>();
                    if (this.name_RU == null) break block6;
                    name_RU_Iterator = Arrays.asList(this.name_RU).iterator();
                    if (!this.type.equals("AD")) {
                        this.T_index = 0;
                    }
                    this.caseI_KI_Index = 0;
                    this.caseD_KD_Index = 0;
                    this.caseF_KF_Index = 0;
                    this.caseJ_KJ_Index = 0;
                    this.casec_RS_Index = 0;
                    this.caseet_RS_Index = 0;
                    this.caseec_RU_Index = 0;
                    this.cases_RU_Index = 0;
                    this.casearray_N_Index = 0;
                    this.nesttype_RS_Index = 0;
                    this.nestpair_N_Index = 0;
                    this.nestname_RU_Iterator = Arrays.asList(this.nestname_RU).iterator();
                    if (!this.type.equals("RVA") && !this.type.equals("RIA")) break block7;
                    for (int i2 = 0; i2 < this.anno_N.length; ++i2) {
                        this.attributes.add(this.getAttribute(this.anno_N[i2], this.type_RS[i2], this.pair_N[i2], name_RU_Iterator));
                    }
                    break block5;
                }
                if (!this.type.equals("RVPA") && !this.type.equals("RIPA")) break block5;
                this.anno_N_Index = 0;
                this.pair_N_Index = 0;
                for (int element : this.param_NB) {
                    this.attributes.add(this.getParameterAttribute(element, name_RU_Iterator));
                }
                break block5;
            }
            if (this.type.equals("AD")) {
                for (int element : this.T) {
                    this.attributes.add(new AnnotationDefaultAttribute(new AnnotationsAttribute.ElementValue(element, this.getNextValue(element))));
                }
            }
        }
        return this.attributes;
    }

    private Object getNextValue(int t2) {
        switch (t2) {
            case 66: 
            case 67: 
            case 73: 
            case 83: 
            case 90: {
                return this.caseI_KI[this.caseI_KI_Index++];
            }
            case 68: {
                return this.caseD_KD[this.caseD_KD_Index++];
            }
            case 70: {
                return this.caseF_KF[this.caseF_KF_Index++];
            }
            case 74: {
                return this.caseJ_KJ[this.caseJ_KJ_Index++];
            }
            case 99: {
                return this.casec_RS[this.casec_RS_Index++];
            }
            case 101: {
                String enumString = this.caseet_RS[this.caseet_RS_Index++] + ":" + this.caseec_RU[this.caseec_RU_Index++];
                return this.cpBands.cpNameAndTypeValue(enumString);
            }
            case 115: {
                return this.cases_RU[this.cases_RU_Index++];
            }
            case 91: {
                int arraySize = this.casearray_N[this.casearray_N_Index++];
                AnnotationsAttribute.ElementValue[] nestedArray = new AnnotationsAttribute.ElementValue[arraySize];
                for (int i2 = 0; i2 < arraySize; ++i2) {
                    int nextT = this.T[this.T_index++];
                    nestedArray[i2] = new AnnotationsAttribute.ElementValue(nextT, this.getNextValue(nextT));
                }
                return nestedArray;
            }
            case 64: {
                CPUTF8 type = this.nesttype_RS[this.nesttype_RS_Index++];
                int numPairs = this.nestpair_N[this.nestpair_N_Index++];
                return this.getAnnotation(type, numPairs, this.nestname_RU_Iterator);
            }
        }
        return null;
    }

    private Attribute getParameterAttribute(int numParameters, Iterator<CPUTF8> namesIterator) {
        RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation[] parameterAnnotations = new RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation[numParameters];
        for (int i2 = 0; i2 < numParameters; ++i2) {
            int numAnnotations = this.anno_N[this.anno_N_Index++];
            int[] pairCounts = this.pair_N[this.pair_N_Index++];
            AnnotationsAttribute.Annotation[] annotations = new AnnotationsAttribute.Annotation[numAnnotations];
            Arrays.setAll(annotations, j2 -> this.getAnnotation(this.type_RS[this.anno_N_Index - 1][j2], pairCounts[j2], namesIterator));
            parameterAnnotations[i2] = new RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation(annotations);
        }
        return new RuntimeVisibleorInvisibleParameterAnnotationsAttribute(this.type.equals("RVPA") ? rvpaUTF8 : ripaUTF8, parameterAnnotations);
    }
}

