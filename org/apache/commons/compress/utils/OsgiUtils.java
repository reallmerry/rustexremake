/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

public class OsgiUtils {
    private static final boolean inOsgiEnvironment;

    private static boolean isBundleReference(Class<?> clazz) {
        for (Class<?> c2 = clazz; c2 != null; c2 = c2.getSuperclass()) {
            if (c2.getName().equals("org.osgi.framework.BundleReference")) {
                return true;
            }
            for (Class<?> ifc : c2.getInterfaces()) {
                if (!OsgiUtils.isBundleReference(ifc)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isRunningInOsgiEnvironment() {
        return inOsgiEnvironment;
    }

    static {
        Class<?> classloaderClass = OsgiUtils.class.getClassLoader().getClass();
        inOsgiEnvironment = OsgiUtils.isBundleReference(classloaderClass);
    }
}

