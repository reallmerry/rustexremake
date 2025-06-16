/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.IOUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class f {
    public f() {
        f a2;
    }

    public static SecretKeySpec ALLATORIxDEMO(String c2) throws Exception {
        byte[] b2 = c2.getBytes("UTF-8");
        MessageDigest a2 = MessageDigest.getInstance("SHA-1");
        b2 = a2.digest(b2);
        b2 = Arrays.copyOf(b2, 16);
        return new SecretKeySpec(b2, "AES");
    }

    public static String j(String c2, SecretKeySpec b2) throws Exception {
        Cipher a2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
        a2.init(1, b2);
        return Base64.getEncoder().encodeToString(a2.doFinal(c2.getBytes("UTF-8")));
    }

    public static String ALLATORIxDEMO(String c2, SecretKeySpec b2) throws Exception {
        Cipher a2 = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        a2.init(2, b2);
        return new String(a2.doFinal(Base64.getDecoder().decode(c2)));
    }

    public static void ALLATORIxDEMO(String e2, String[] d2) {
        try {
            Throwable throwable = null;
            Object var3_5 = null;
            try (PrintWriter b2 = new PrintWriter(new FileWriter(e2));){
                String[] stringArray = d2;
                int n2 = d2.length;
                int n3 = 0;
                while (n3 < n2) {
                    String a2 = stringArray[n3];
                    b2.println(a2);
                    ++n3;
                }
            } catch (Throwable throwable2) {
                if (throwable == null) {
                    throwable = throwable2;
                } else if (throwable != throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
        } catch (IOException c2) {
            c2.printStackTrace();
        }
    }

    public static void ALLATORIxDEMO(File c2) {
        if (Desktop.isDesktopSupported()) {
            Desktop b2 = Desktop.getDesktop();
            try {
                b2.open(c2);
            } catch (IOException a2) {
                a2.printStackTrace();
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public static String[] ALLATORIxDEMO(String f2) {
        ArrayList<void> e2 = new ArrayList<void>();
        try {
            Throwable throwable = null;
            Object var3_5 = null;
            try (BufferedReader c2 = new BufferedReader(new FileReader(f2));){
                String b2;
                while ((b2 = c2.readLine()) != null) {
                    void a2;
                    e2.add(a2);
                }
            } catch (Throwable throwable2) {
                if (throwable == null) {
                    throwable = throwable2;
                } else if (throwable != throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
        } catch (IOException d2) {
            d2.printStackTrace();
        }
        return e2.toArray(new String[0]);
    }

    public static boolean j(String b2) {
        File a2 = new File(b2);
        if (a2.exists()) {
            return a2.delete();
        }
        System.out.println("File not extis.");
        return false;
    }

    public static void j(String i2, String h2) throws IOException {
        File g2 = new File(h2);
        if (!g2.exists()) {
            g2.mkdirs();
        }
        Throwable throwable = null;
        Object var4_5 = null;
        try (ZipFile f2 = new ZipFile(new File(i2));){
            Enumeration<ZipArchiveEntry> e2 = f2.getEntries();
            while (e2.hasMoreElements()) {
                ZipArchiveEntry d2 = e2.nextElement();
                File c2 = new File(g2, d2.getName());
                if (d2.isDirectory()) {
                    c2.mkdirs();
                    continue;
                }
                c2.getParentFile().mkdirs();
                Throwable throwable2 = null;
                Object var10_13 = null;
                try {
                    InputStream b2 = f2.getInputStream(d2);
                    try {
                        try (FileOutputStream a2 = new FileOutputStream(c2);){
                            IOUtils.copy(b2, (OutputStream)a2);
                        }
                        if (b2 == null) continue;
                    } catch (Throwable throwable3) {
                        if (throwable2 == null) {
                            throwable2 = throwable3;
                        } else if (throwable2 != throwable3) {
                            throwable2.addSuppressed(throwable3);
                        }
                        if (b2 != null) {
                            b2.close();
                        }
                        throw throwable2;
                    }
                    b2.close();
                } catch (Throwable throwable4) {
                    if (throwable2 == null) {
                        throwable2 = throwable4;
                    } else if (throwable2 != throwable4) {
                        throwable2.addSuppressed(throwable4);
                    }
                    throw throwable2;
                }
            }
        } catch (Throwable throwable5) {
            if (throwable == null) {
                throwable = throwable5;
            } else if (throwable != throwable5) {
                throwable.addSuppressed(throwable5);
            }
            throw throwable;
        }
    }

    public static void ALLATORIxDEMO(Path a2) throws IOException {
        if (!Files.exists(a2, new LinkOption[0]) || !Files.isDirectory(a2, new LinkOption[0])) {
            return;
        }
        Files.walkFileTree(a2, new d());
    }

    public static void ALLATORIxDEMO(String e2, String d2) throws IOException {
        ClassLoader c2 = f.class.getClassLoader();
        Throwable throwable = null;
        Object var4_5 = null;
        try (InputStream b2 = c2.getResourceAsStream(e2);){
            if (b2 == null) {
                throw new IOException("\u0424\u0430\u0439\u043b " + e2 + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 JAR-\u0444\u0430\u0439\u043b\u0435.");
            }
            Path a2 = Path.of((String)d2, (String[])new String[]{e2});
            Files.copy(b2, a2, StandardCopyOption.REPLACE_EXISTING);
        } catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
    }

    public static boolean ALLATORIxDEMO(String f2) {
        if (!new File(f2 = f2.replaceAll("/", "\\\\")).exists()) {
            return true;
        }
        f2 = "\"" + f2 + "\"";
        String e2 = System.getProperty("os.name").toLowerCase();
        ProcessBuilder d2 = null;
        int c2 = 0;
        try {
            if (e2.contains("win")) {
                d2 = new ProcessBuilder("cmd", "/c", "rmdir", "/s", "/q", f2);
            } else if (e2.contains("nix") || e2.contains("nux") || e2.contains("mac")) {
                d2 = new ProcessBuilder("rm", "-rf", f2);
            } else {
                System.out.println("Sorry, unsupported operating system");
            }
            Process a2 = d2.start();
            c2 = a2.waitFor();
        } catch (IOException | InterruptedException b2) {
            b2.printStackTrace();
        }
        if (new File(f2).exists()) {
            System.out.println("exitCode " + c2);
            return false;
        }
        return true;
    }
}

