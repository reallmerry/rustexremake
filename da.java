/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class da {
    public String x = "";
    public boolean j = true;
    public int p = 2048;
    public String d = "";
    private final String r = "launcher_config.properties";
    public boolean k = false;
    public boolean q = true;
    public t ALLATORIxDEMO = t.NEVER;

    public da() {
        da a2;
    }

    public void j() throws IOException {
        da c2;
        ka b2 = new ka(c2);
        Throwable throwable = null;
        Object var3_4 = null;
        try (FileInputStream a2 = new FileInputStream("launcher_config.properties");){
            b2.load(a2);
            c2.x = ((Properties)b2).getProperty("gamePath1");
            if (!((Properties)b2).getProperty("ram").isEmpty()) {
                c2.p = Integer.parseInt(((Properties)b2).getProperty("ram"));
            }
            c2.j = Boolean.parseBoolean(((Properties)b2).getProperty("autoRam"));
            c2.d = ((Properties)b2).getProperty("lang");
            if (!((Properties)b2).getProperty("startup").isEmpty()) {
                c2.k = Boolean.parseBoolean(((Properties)b2).getProperty("startup"));
            }
            if (!((Properties)b2).getProperty("hidetray").isEmpty()) {
                c2.q = Boolean.parseBoolean(((Properties)b2).getProperty("hidetray"));
            }
            if (!((Properties)b2).getProperty("autoexit").isEmpty()) {
                c2.ALLATORIxDEMO = t.values()[Integer.parseInt(((Properties)b2).getProperty("autoexit"))];
            }
        } catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
        System.out.println("Loaded config");
    }

    public void ALLATORIxDEMO() throws IOException {
        da c2;
        Properties b2 = new Properties();
        b2.setProperty("gamePath1", c2.x);
        b2.setProperty("ram", String.valueOf(c2.p));
        b2.setProperty("autoRam", String.valueOf(c2.j));
        b2.setProperty("lang", String.valueOf(c2.d));
        b2.setProperty("startup", String.valueOf(c2.k));
        b2.setProperty("hidetray", String.valueOf(c2.q));
        b2.setProperty("autoexit", String.valueOf(c2.ALLATORIxDEMO.ordinal()));
        Throwable throwable = null;
        Object var3_4 = null;
        try (FileOutputStream a2 = new FileOutputStream("launcher_config.properties");){
            b2.store(a2, "Configuration");
        } catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
        System.out.println("Saved config");
    }
}

