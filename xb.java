/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
public class xb {
    private static final nb ALLATORIxDEMO = new nb();

    public xb() {
        xb a2;
    }

    public static nb ALLATORIxDEMO() {
        return ALLATORIxDEMO;
    }

    public static String j(String c2) {
        if (c2 == null) {
            return null;
        }
        String b2 = c2 = c2.toLowerCase();
        String[] a2 = (b2 = b2.replaceAll("\\s+", "")).split("@");
        if (a2.length != 2) {
            return c2;
        }
        if (!a2[1].equals("gmail.com")) {
            return c2;
        }
        b2 = String.valueOf(a2[0].replaceAll("([.])\\1+", "$1")) + "@" + a2[1];
        return b2;
    }

    public static String ALLATORIxDEMO(String a2) {
        return a2.replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t").replace("\b", "\\b").replace("\f", "\\f").replace("'", "\\'").replace("\"", "\\\"");
    }
}

