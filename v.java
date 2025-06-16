/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;
import org.json.JSONObject;

public class v {
    private Consumer<Integer> q;
    private int ALLATORIxDEMO = -1;

    public v(Consumer<Integer> a2) {
        v b2;
        b2.q = a2;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void ALLATORIxDEMO(String k2, String j2) throws IOException {
        URL i2 = new URL(k2);
        HttpURLConnection h2 = (HttpURLConnection)i2.openConnection();
        int g2 = h2.getContentLength();
        try {
            Throwable throwable = null;
            Object var7_8 = null;
            try {
                BufferedInputStream f2 = new BufferedInputStream(h2.getInputStream());
                try {
                    try (FileOutputStream e2 = new FileOutputStream(j2);){
                        int c2;
                        byte[] d2 = new byte[8192];
                        long a2 = 0L;
                        while ((c2 = f2.read(d2, 0, 8192)) != -1) {
                            v l2;
                            void b2;
                            e2.write(d2, 0, (int)b2);
                            l2.ALLATORIxDEMO((int)((a2 += (long)b2) * 100L / (long)g2));
                        }
                    }
                    if (f2 == null) return;
                } catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    if (f2 == null) throw throwable;
                    f2.close();
                    throw throwable;
                }
                f2.close();
                return;
            } catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        } finally {
            h2.disconnect();
        }
    }

    private void ALLATORIxDEMO(int a2) {
        v b2;
        if (Math.abs(a2 - b2.ALLATORIxDEMO) >= 1) {
            b2.q.accept(a2);
            b2.ALLATORIxDEMO = a2;
        }
    }

    /*
     * WARNING - void declaration
     */
    public static JSONObject ALLATORIxDEMO(String g2) throws Exception {
        HttpURLConnection f2 = null;
        URL e2 = new URL(g2);
        f2 = (HttpURLConnection)e2.openConnection();
        f2.setRequestMethod("GET");
        f2.setRequestProperty("Content-Type", "application/json; utf-8");
        f2.setRequestProperty("Accept", "application/json");
        f2.setDoOutput(true);
        Throwable throwable = null;
        Object var4_5 = null;
        try (BufferedReader d2 = new BufferedReader(new InputStreamReader(f2.getInputStream(), "utf-8"));){
            String b2;
            StringBuilder c2 = new StringBuilder();
            while ((b2 = d2.readLine()) != null) {
                void a2;
                c2.append(a2.trim());
            }
            return new JSONObject(c2.toString());
        } catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
    }
}

