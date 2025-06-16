/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class g {
    private long j;
    private long p;
    private Consumer<Integer> d;
    private double r = 0.0;
    private int k;
    private long q;
    private long ALLATORIxDEMO;

    public g(Consumer<Integer> a2) {
        g b2;
        b2.d = a2;
    }

    public String j(File e2) throws NoSuchAlgorithmException, IOException {
        g f2;
        f2.j = f2.ALLATORIxDEMO(e2);
        f2.p = 0L;
        MessageDigest d2 = MessageDigest.getInstance("SHA-256");
        f2.j(e2, d2);
        byte[] c2 = d2.digest();
        StringBuilder b2 = new StringBuilder();
        byte[] byArray = c2;
        int n2 = c2.length;
        int n3 = 0;
        while (n3 < n2) {
            byte a2 = byArray[n3];
            b2.append(String.format("%02x", a2));
            ++n3;
        }
        return b2.toString();
    }

    private long ALLATORIxDEMO(File d2) {
        File[] c2 = d2.listFiles();
        long b2 = 0L;
        if (c2 != null) {
            File[] fileArray = c2;
            int n2 = c2.length;
            int n3 = 0;
            while (n3 < n2) {
                g e2;
                File a2 = fileArray[n3];
                b2 = a2.isDirectory() ? (b2 += e2.ALLATORIxDEMO(a2)) : ++b2;
                ++n3;
            }
        }
        return b2;
    }

    private void j(File d2, MessageDigest c2) throws IOException {
        File[] b2 = d2.listFiles();
        if (b2 == null) {
            return;
        }
        File[] fileArray = b2;
        int n2 = b2.length;
        int n3 = 0;
        while (n3 < n2) {
            g e2;
            File a2 = fileArray[n3];
            if (a2.isDirectory()) {
                e2.j(a2, c2);
            } else {
                e2.ALLATORIxDEMO(a2, c2);
                ++e2.p;
                e2.ALLATORIxDEMO((int)(e2.p * 100L / e2.j));
            }
            ++n3;
        }
    }

    /*
     * WARNING - void declaration
     */
    private void ALLATORIxDEMO(File f2, MessageDigest e2) throws IOException {
        Throwable throwable = null;
        Object var4_5 = null;
        try (InputStream d2 = Files.newInputStream(f2.toPath(), new OpenOption[0]);){
            int b2;
            byte[] c2 = new byte[1024];
            while ((b2 = d2.read(c2)) != -1) {
                void a2;
                e2.update(c2, 0, (int)a2);
            }
        } catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
    }

    private void ALLATORIxDEMO(int a2) {
        g b2;
        if (Math.abs(a2 - b2.k) >= 1) {
            b2.d.accept(a2);
            b2.k = a2;
        }
    }

    /*
     * WARNING - void declaration
     */
    public static String ALLATORIxDEMO(File i2) throws NoSuchAlgorithmException, IOException {
        MessageDigest h2 = MessageDigest.getInstance("SHA-256");
        Throwable throwable = null;
        Object var3_4 = null;
        try (InputStream d2 = Files.newInputStream(i2.toPath(), new OpenOption[0]);){
            int a2;
            byte[] c2 = new byte[1024];
            while ((a2 = d2.read(c2)) != -1) {
                void b2;
                h2.update(c2, 0, (int)b2);
            }
        } catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
        byte[] g2 = h2.digest();
        StringBuilder f2 = new StringBuilder();
        byte[] byArray = g2;
        int n2 = g2.length;
        int n3 = 0;
        while (n3 < n2) {
            byte e2 = byArray[n3];
            f2.append(String.format("%02x", e2));
            ++n3;
        }
        return f2.toString();
    }

    public List<File> ALLATORIxDEMO(List<File> g2, List<String> f2) throws NoSuchAlgorithmException, IOException {
        g h2;
        if (g2.size() != f2.size()) {
            throw new IllegalArgumentException("Size of files list must match size of hash list.");
        }
        h2.q = h2.ALLATORIxDEMO(g2);
        h2.ALLATORIxDEMO = 0L;
        ArrayList<File> e2 = new ArrayList<File>();
        int d2 = 0;
        while (d2 < g2.size()) {
            MessageDigest a2;
            String b2;
            File c2 = g2.get(d2);
            if (!h2.ALLATORIxDEMO(c2, b2 = f2.get(d2), a2 = MessageDigest.getInstance("SHA-256"))) {
                e2.add(c2);
            }
            ++d2;
        }
        return e2;
    }

    private long ALLATORIxDEMO(List<File> c2) {
        long b2 = 0L;
        for (File a2 : c2) {
            b2 += a2.length();
        }
        return b2;
    }

    /*
     * WARNING - void declaration
     */
    private boolean ALLATORIxDEMO(File j2, String i2, MessageDigest h2) throws IOException {
        Throwable throwable = null;
        Object var5_6 = null;
        try (InputStream e2 = Files.newInputStream(j2.toPath(), new OpenOption[0]);){
            int a2;
            byte[] c2 = new byte[1024];
            while ((a2 = e2.read(c2)) != -1) {
                g k2;
                void b2;
                h2.update(c2, 0, (int)b2);
                k2.ALLATORIxDEMO += (long)b2;
                k2.ALLATORIxDEMO((int)(k2.ALLATORIxDEMO * 100L / k2.q));
            }
        } catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
        byte[] g2 = h2.digest();
        StringBuilder f2 = new StringBuilder();
        byte[] byArray = g2;
        int n2 = g2.length;
        int n3 = 0;
        while (n3 < n2) {
            byte d2 = byArray[n3];
            f2.append(String.format("%02x", d2));
            ++n3;
        }
        return f2.toString().equals(i2);
    }
}

