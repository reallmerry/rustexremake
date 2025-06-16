/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class wa {
    private String r;
    private String k;
    private String q;
    private Map<String, String> ALLATORIxDEMO = new HashMap<String, String>();

    public wa() {
        wa a2;
    }

    public wa s(String a2) {
        wa b2;
        b2.r = a2;
        return b2;
    }

    public wa z(String a2) {
        wa b2;
        b2.k = a2;
        return b2;
    }

    public wa j(String a2) {
        wa b2;
        b2.q = a2;
        return b2;
    }

    public wa ALLATORIxDEMO(String c2, String b2) {
        wa d2;
        try {
            d2.ALLATORIxDEMO.put(URLEncoder.encode(c2, "UTF-8"), URLEncoder.encode(b2, "UTF-8"));
        } catch (UnsupportedEncodingException a2) {
            throw new RuntimeException("UTF-8 encoding not supported", a2);
        }
        return d2;
    }

    public String ALLATORIxDEMO(String c2) {
        String a2;
        block3: {
            try {
                wa d2;
                a2 = URLDecoder.decode(d2.ALLATORIxDEMO.get(c2), "UTF-8");
                if (a2 != null) break block3;
                return "";
            } catch (Exception b2) {
                return "";
            }
        }
        return a2;
    }

    public String ALLATORIxDEMO() {
        wa c2;
        StringBuilder b2 = new StringBuilder();
        if (c2.r != null) {
            b2.append(c2.r).append("://");
        }
        if (c2.k != null) {
            b2.append(c2.k);
        }
        if (c2.q != null) {
            b2.append(c2.q);
        }
        if (!c2.ALLATORIxDEMO.isEmpty()) {
            b2.append("?");
            String a3 = c2.ALLATORIxDEMO.entrySet().stream().map(a2 -> String.valueOf((String)a2.getKey()) + "=" + (String)a2.getValue()).collect(Collectors.joining("&"));
            b2.append(a3);
        }
        return b2.toString();
    }

    public static wa ALLATORIxDEMO(String k2) {
        wa j2 = new wa();
        String[] i2 = k2.split("\\?", 2);
        String h2 = i2[0];
        String[] g2 = h2.split("://", 2);
        if (g2.length > 1) {
            j2.s(g2[0]);
            String[] d2 = g2[1].split("/", 2);
            if (!d2[0].isEmpty()) {
                j2.z(d2[0]);
            }
            if (d2.length > 1 && !d2[1].isEmpty()) {
                j2.j("/" + d2[1]);
            }
        } else {
            String[] e2 = g2[0].split("/", 2);
            if (!e2[0].isEmpty()) {
                j2.z(e2[0]);
            }
            if (e2.length > 1 && !e2[1].isEmpty()) {
                j2.j("/" + e2[1]);
            }
        }
        if (i2.length > 1) {
            String[] f2;
            String[] stringArray = f2 = i2[1].split("&");
            int n2 = f2.length;
            int n3 = 0;
            while (n3 < n2) {
                String c2 = stringArray[n3];
                String[] b2 = c2.split("=");
                if (b2.length > 1) {
                    try {
                        j2.ALLATORIxDEMO(URLDecoder.decode(b2[0], "UTF-8"), URLDecoder.decode(b2[1], "UTF-8"));
                    } catch (UnsupportedEncodingException a2) {
                        throw new RuntimeException("UTF-8 encoding not supported", a2);
                    }
                }
                ++n3;
            }
        }
        return j2;
    }
}

