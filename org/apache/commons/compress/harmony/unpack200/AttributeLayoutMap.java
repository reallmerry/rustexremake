/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
import org.apache.commons.compress.harmony.unpack200.NewAttributeBands;

public class AttributeLayoutMap {
    private final Map<Integer, AttributeLayout> classLayouts = new HashMap<Integer, AttributeLayout>();
    private final Map<Integer, AttributeLayout> fieldLayouts = new HashMap<Integer, AttributeLayout>();
    private final Map<Integer, AttributeLayout> methodLayouts = new HashMap<Integer, AttributeLayout>();
    private final Map<Integer, AttributeLayout> codeLayouts = new HashMap<Integer, AttributeLayout>();
    private final Map<Integer, AttributeLayout>[] layouts = new Map[]{this.classLayouts, this.fieldLayouts, this.methodLayouts, this.codeLayouts};
    private final Map<AttributeLayout, NewAttributeBands> layoutsToBands = new HashMap<AttributeLayout, NewAttributeBands>();

    private static AttributeLayout[] getDefaultAttributeLayouts() throws Pack200Exception {
        return new AttributeLayout[]{new AttributeLayout("ACC_PUBLIC", 0, "", 0), new AttributeLayout("ACC_PUBLIC", 1, "", 0), new AttributeLayout("ACC_PUBLIC", 2, "", 0), new AttributeLayout("ACC_PRIVATE", 0, "", 1), new AttributeLayout("ACC_PRIVATE", 1, "", 1), new AttributeLayout("ACC_PRIVATE", 2, "", 1), new AttributeLayout("LineNumberTable", 3, "NH[PHH]", 1), new AttributeLayout("ACC_PROTECTED", 0, "", 2), new AttributeLayout("ACC_PROTECTED", 1, "", 2), new AttributeLayout("ACC_PROTECTED", 2, "", 2), new AttributeLayout("LocalVariableTable", 3, "NH[PHOHRUHRSHH]", 2), new AttributeLayout("ACC_STATIC", 0, "", 3), new AttributeLayout("ACC_STATIC", 1, "", 3), new AttributeLayout("ACC_STATIC", 2, "", 3), new AttributeLayout("LocalVariableTypeTable", 3, "NH[PHOHRUHRSHH]", 3), new AttributeLayout("ACC_FINAL", 0, "", 4), new AttributeLayout("ACC_FINAL", 1, "", 4), new AttributeLayout("ACC_FINAL", 2, "", 4), new AttributeLayout("ACC_SYNCHRONIZED", 0, "", 5), new AttributeLayout("ACC_SYNCHRONIZED", 1, "", 5), new AttributeLayout("ACC_SYNCHRONIZED", 2, "", 5), new AttributeLayout("ACC_VOLATILE", 0, "", 6), new AttributeLayout("ACC_VOLATILE", 1, "", 6), new AttributeLayout("ACC_VOLATILE", 2, "", 6), new AttributeLayout("ACC_TRANSIENT", 0, "", 7), new AttributeLayout("ACC_TRANSIENT", 1, "", 7), new AttributeLayout("ACC_TRANSIENT", 2, "", 7), new AttributeLayout("ACC_NATIVE", 0, "", 8), new AttributeLayout("ACC_NATIVE", 1, "", 8), new AttributeLayout("ACC_NATIVE", 2, "", 8), new AttributeLayout("ACC_INTERFACE", 0, "", 9), new AttributeLayout("ACC_INTERFACE", 1, "", 9), new AttributeLayout("ACC_INTERFACE", 2, "", 9), new AttributeLayout("ACC_ABSTRACT", 0, "", 10), new AttributeLayout("ACC_ABSTRACT", 1, "", 10), new AttributeLayout("ACC_ABSTRACT", 2, "", 10), new AttributeLayout("ACC_STRICT", 0, "", 11), new AttributeLayout("ACC_STRICT", 1, "", 11), new AttributeLayout("ACC_STRICT", 2, "", 11), new AttributeLayout("ACC_SYNTHETIC", 0, "", 12), new AttributeLayout("ACC_SYNTHETIC", 1, "", 12), new AttributeLayout("ACC_SYNTHETIC", 2, "", 12), new AttributeLayout("ACC_ANNOTATION", 0, "", 13), new AttributeLayout("ACC_ANNOTATION", 1, "", 13), new AttributeLayout("ACC_ANNOTATION", 2, "", 13), new AttributeLayout("ACC_ENUM", 0, "", 14), new AttributeLayout("ACC_ENUM", 1, "", 14), new AttributeLayout("ACC_ENUM", 2, "", 14), new AttributeLayout("SourceFile", 0, "RUNH", 17), new AttributeLayout("ConstantValue", 1, "KQH", 17), new AttributeLayout("Code", 2, "", 17), new AttributeLayout("EnclosingMethod", 0, "RCHRDNH", 18), new AttributeLayout("Exceptions", 2, "NH[RCH]", 18), new AttributeLayout("Signature", 0, "RSH", 19), new AttributeLayout("Signature", 1, "RSH", 19), new AttributeLayout("Signature", 2, "RSH", 19), new AttributeLayout("Deprecated", 0, "", 20), new AttributeLayout("Deprecated", 1, "", 20), new AttributeLayout("Deprecated", 2, "", 20), new AttributeLayout("RuntimeVisibleAnnotations", 0, "*", 21), new AttributeLayout("RuntimeVisibleAnnotations", 1, "*", 21), new AttributeLayout("RuntimeVisibleAnnotations", 2, "*", 21), new AttributeLayout("RuntimeInvisibleAnnotations", 0, "*", 22), new AttributeLayout("RuntimeInvisibleAnnotations", 1, "*", 22), new AttributeLayout("RuntimeInvisibleAnnotations", 2, "*", 22), new AttributeLayout("InnerClasses", 0, "", 23), new AttributeLayout("RuntimeVisibleParameterAnnotations", 2, "*", 23), new AttributeLayout("class-file version", 0, "", 24), new AttributeLayout("RuntimeInvisibleParameterAnnotations", 2, "*", 24), new AttributeLayout("AnnotationDefault", 2, "*", 25)};
    }

    public AttributeLayoutMap() throws Pack200Exception {
        for (AttributeLayout defaultAttributeLayout : AttributeLayoutMap.getDefaultAttributeLayouts()) {
            this.add(defaultAttributeLayout);
        }
    }

    public void add(AttributeLayout layout) {
        this.getLayout(layout.getContext()).put(layout.getIndex(), layout);
    }

    public void add(AttributeLayout layout, NewAttributeBands newBands) {
        this.add(layout);
        this.layoutsToBands.put(layout, newBands);
    }

    public void checkMap() throws Pack200Exception {
        for (Map<Integer, AttributeLayout> map : this.layouts) {
            Collection<AttributeLayout> c2 = map.values();
            if (!(c2 instanceof List)) {
                c2 = new ArrayList<AttributeLayout>(c2);
            }
            List layouts = (List)c2;
            for (int j2 = 0; j2 < layouts.size(); ++j2) {
                AttributeLayout layout1 = (AttributeLayout)layouts.get(j2);
                for (int j22 = j2 + 1; j22 < layouts.size(); ++j22) {
                    AttributeLayout layout2 = (AttributeLayout)layouts.get(j22);
                    if (!layout1.getName().equals(layout2.getName()) || !layout1.getLayout().equals(layout2.getLayout())) continue;
                    throw new Pack200Exception("Same layout/name combination: " + layout1.getLayout() + "/" + layout1.getName() + " exists twice for context: " + AttributeLayout.contextNames[layout1.getContext()]);
                }
            }
        }
    }

    public NewAttributeBands getAttributeBands(AttributeLayout layout) {
        return this.layoutsToBands.get(layout);
    }

    public AttributeLayout getAttributeLayout(int index, int context) {
        Map<Integer, AttributeLayout> map = this.getLayout(context);
        return map.get(index);
    }

    public AttributeLayout getAttributeLayout(String name, int context) {
        Map<Integer, AttributeLayout> map = this.getLayout(context);
        for (AttributeLayout layout : map.values()) {
            if (!layout.getName().equals(name)) continue;
            return layout;
        }
        return null;
    }

    private Map<Integer, AttributeLayout> getLayout(int context) {
        return this.layouts[context];
    }
}

