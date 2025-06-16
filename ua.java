/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ua {
    public static ta q;
    public static ConcurrentHashMap<String, ta> ALLATORIxDEMO;

    static {
        ALLATORIxDEMO = new ConcurrentHashMap();
    }

    public ua() {
        ua a2;
    }

    public static String ALLATORIxDEMO(String a2) {
        return q.ALLATORIxDEMO(a2);
    }

    public static String ALLATORIxDEMO(String c2, String b2) {
        ta a2 = ALLATORIxDEMO.get(c2);
        if (a2 == null) {
            a2 = ALLATORIxDEMO.get("en_us");
        }
        return a2.ALLATORIxDEMO(b2);
    }

    public static boolean ALLATORIxDEMO(String a2) {
        if (a2 == null) {
            return false;
        }
        return ALLATORIxDEMO.containsKey(a2);
    }

    public static void ALLATORIxDEMO() {
        File[] e2;
        File f2 = new File(ta.k);
        if (f2.exists() && f2.isDirectory() && (e2 = f2.listFiles()) != null) {
            File[] fileArray = e2;
            int n2 = e2.length;
            int n3 = 0;
            while (n3 < n2) {
                File d2 = fileArray[n3];
                if (d2.isFile()) {
                    String c2 = d2.getName();
                    int b2 = c2.lastIndexOf(".");
                    if (b2 > 0) {
                        c2 = c2.substring(0, b2);
                    }
                    try {
                        ALLATORIxDEMO.put(c2, new ta(c2));
                    } catch (IOException a2) {
                        a2.printStackTrace();
                    }
                }
                ++n3;
            }
        }
    }
}

