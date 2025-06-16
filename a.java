/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.Enumeration;
import java.util.function.Consumer;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class a {
    private Consumer<Integer> q;
    private int ALLATORIxDEMO = -1;

    public a(Consumer<Integer> a2) {
        a b2;
        b2.q = a2;
    }

    private int ALLATORIxDEMO(String d2) throws IOException {
        int c2 = 0;
        Throwable throwable = null;
        Object var4_5 = null;
        try (ZipFile b2 = new ZipFile(d2);){
            Enumeration<ZipArchiveEntry> a2 = b2.getEntries();
            while (a2.hasMoreElements()) {
                a2.nextElement();
                ++c2;
            }
        } catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
        return c2;
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void ALLATORIxDEMO(String n2, String m2) throws IOException {
        a o2;
        int l2 = o2.ALLATORIxDEMO(n2);
        int k2 = 0;
        byte[] j2 = new byte[16384];
        Throwable throwable = null;
        Object var7_8 = null;
        try (ZipFile i2 = new ZipFile(n2);){
            Enumeration<ZipArchiveEntry> h2 = i2.getEntries();
            while (true) {
                block26: {
                    if (!h2.hasMoreElements()) {
                        return;
                    }
                    ZipArchiveEntry g2 = h2.nextElement();
                    File f2 = new File(m2, g2.getName());
                    if (g2.isDirectory()) {
                        Files.createDirectories(f2.toPath(), new FileAttribute[0]);
                    } else {
                        File e2 = f2.getParentFile();
                        if (!e2.exists()) {
                            Files.createDirectories(e2.toPath(), new FileAttribute[0]);
                        }
                        Throwable throwable2 = null;
                        Object var14_17 = null;
                        try {
                            InputStream d2 = i2.getInputStream(g2);
                            try {
                                try (FileOutputStream c2 = new FileOutputStream(f2);){
                                    int b2;
                                    while ((b2 = d2.read(j2)) > 0) {
                                        void a2;
                                        c2.write(j2, 0, (int)a2);
                                    }
                                }
                                if (d2 == null) break block26;
                            } catch (Throwable throwable3) {
                                if (throwable2 == null) {
                                    throwable2 = throwable3;
                                } else if (throwable2 != throwable3) {
                                    throwable2.addSuppressed(throwable3);
                                }
                                if (d2 == null) throw throwable2;
                                d2.close();
                                throw throwable2;
                            }
                            d2.close();
                        } catch (Throwable throwable4) {
                            if (throwable2 == null) {
                                throwable2 = throwable4;
                                throw throwable2;
                            }
                            if (throwable2 == throwable4) throw throwable2;
                            throwable2.addSuppressed(throwable4);
                            throw throwable2;
                        }
                    }
                }
                o2.ALLATORIxDEMO((int)((float)(++k2 * 100) / (float)l2));
            }
        } catch (Throwable throwable5) {
            if (throwable == null) {
                throwable = throwable5;
                throw throwable;
            }
            if (throwable == throwable5) throw throwable;
            throwable.addSuppressed(throwable5);
            throw throwable;
        }
    }

    private void ALLATORIxDEMO(int a2) {
        a b2;
        if (Math.abs((a2 = Math.min(a2, 100)) - b2.ALLATORIxDEMO) >= 1) {
            b2.q.accept(a2);
            b2.ALLATORIxDEMO = a2;
        }
    }
}

