/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ta {
    public static String k = "localization/";
    private Map<String, String> q = new HashMap<String, String>();
    private final String ALLATORIxDEMO;

    public ta(String a2) throws IOException {
        ta b2;
        b2.ALLATORIxDEMO = String.valueOf(k) + a2 + ".lang";
        b2.ALLATORIxDEMO();
    }

    private void ALLATORIxDEMO() throws IOException {
        ta e2;
        Properties d2 = new Properties();
        try {
            Throwable throwable = null;
            Object var3_6 = null;
            try (FileInputStream a3 = new FileInputStream(e2.ALLATORIxDEMO);){
                d2.load(new InputStreamReader((InputStream)a3, StandardCharsets.UTF_8));
                d2.forEach((b2, a2) -> {
                    ta c2;
                    String string = c2.q.put((String)b2, (String)a2);
                });
            } catch (Throwable throwable2) {
                if (throwable == null) {
                    throwable = throwable2;
                } else if (throwable != throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
        } catch (FileNotFoundException c2) {
            System.err.println("Localization file not found: " + e2.ALLATORIxDEMO);
            throw c2;
        } catch (IOException b3) {
            System.err.println("Error reading localization file: " + b3.getMessage());
            throw b3;
        }
    }

    public String ALLATORIxDEMO(String a2) {
        ta b2;
        return b2.q.getOrDefault(a2, a2);
    }
}

