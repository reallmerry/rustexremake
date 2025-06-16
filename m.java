/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
import com.sun.management.OperatingSystemMXBean;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public enum m {
    y("windows"),
    e("linux"),
    w("osx"),
    g("universal");

    private final String b;
    public static final m u;
    public static final int l;
    public static final int a;
    public static final String i;
    public static final String h;
    public static final String v;
    public static final Charset t;
    public static final int o;
    public static final String x;
    public static final String j;
    public static final Pattern p;
    private static final String[] d;
    private static final String[] r;
    private static final Pattern k;
    private static volatile /* synthetic */ int[] q;

    static {
        u = m.ALLATORIxDEMO(System.getProperty("os.name"));
        i = File.pathSeparator;
        h = File.separator;
        v = System.lineSeparator();
        k = Pattern.compile("^(?<key>.*?):\\s+(?<value>\\d+) kB?$");
        String j2 = System.getProperty("native.encoding");
        String i2 = System.getProperty("hmcl.native.encoding");
        Charset h2 = Charset.defaultCharset();
        try {
            if (i2 != null) {
                h2 = Charset.forName(i2);
            } else {
                if (j2 != null && !j2.equalsIgnoreCase(h2.name())) {
                    h2 = Charset.forName(j2);
                }
                if (h2 == StandardCharsets.UTF_8 || h2 == StandardCharsets.US_ASCII) {
                    h2 = StandardCharsets.UTF_8;
                } else if ("GBK".equalsIgnoreCase(h2.name()) || "GB2312".equalsIgnoreCase(h2.name())) {
                    h2 = Charset.forName("GB18030");
                }
            }
        } catch (UnsupportedCharsetException f2) {
            f2.printStackTrace();
        }
        t = h2;
        if (u == y) {
            String d2;
            String g2 = null;
            int e2 = -1;
            try {
                Process c2 = Runtime.getRuntime().exec(new String[]{"cmd", "ver"});
                Throwable throwable = null;
                Object var7_10 = null;
                try (BufferedReader b2 = new BufferedReader(new InputStreamReader(c2.getInputStream(), t));){
                    Matcher a3 = Pattern.compile("(?<version>[0-9]+\\.[0-9]+\\.(?<build>[0-9]+)(\\.[0-9]+)?)]$").matcher(b2.readLine().trim());
                    if (a3.find()) {
                        g2 = a3.group("version");
                        e2 = Integer.parseInt(a3.group("build"));
                    }
                } catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
                c2.destroy();
            } catch (Throwable c2) {
                // empty catch block
            }
            if (g2 == null) {
                g2 = System.getProperty("os.version");
            }
            if ((d2 = System.getProperty("os.name")).equals("Windows 10") && e2 >= 22000) {
                d2 = "Windows 11";
            }
            x = d2;
            j = g2;
            o = e2;
        } else {
            x = System.getProperty("os.name");
            j = System.getProperty("os.version");
            o = -1;
        }
        l = m.ALLATORIxDEMO().map(r::z).map(a2 -> (int)(a2 / 1024L / 1024L)).orElse(1024);
        int n2 = a = l >= 32768 ? 8192 : (int)(Math.round(1.0 * (double)l / 4.0 / 128.0) * 128L);
        if (u == y) {
            p = Pattern.compile("[/\"<>|?*:\\\\]");
            d = new String[]{"aux", "com1", "com2", "com3", "com4", "com5", "com6", "com7", "com8", "com9", "con", "lpt1", "lpt2", "lpt3", "lpt4", "lpt5", "lpt6", "lpt7", "lpt8", "lpt9", "nul", "prn"};
            Arrays.sort(d);
            r = new String[]{"clock$"};
        } else {
            p = null;
            d = null;
            r = null;
        }
    }

    private m(String a2) {
        m b2;
        b2.b = a2;
    }

    public String ALLATORIxDEMO() {
        m a2;
        return a2.b;
    }

    public static m ALLATORIxDEMO(String a2) {
        if (a2 == null) {
            return g;
        }
        if ((a2 = a2.trim().toLowerCase(Locale.ROOT)).contains("win")) {
            return y;
        }
        if (a2.contains("mac")) {
            return w;
        }
        if (a2.contains("solaris") || a2.contains("linux") || a2.contains("unix") || a2.contains("sunos")) {
            return e;
        }
        return g;
    }

    public static void ALLATORIxDEMO(String d2) {
        if (Desktop.isDesktopSupported()) {
            Desktop c2 = Desktop.getDesktop();
            try {
                URI a2 = new URI(d2);
                c2.browse(a2);
            } catch (Exception b2) {
                b2.printStackTrace();
            }
        } else {
            System.out.println("Desktop API not Support");
        }
    }

    public static Optional<r> ALLATORIxDEMO() {
        if (u == e) {
            try {
                long h2 = 0L;
                long f2 = 0L;
                long e2 = 0L;
                for (String d2 : Files.readAllLines(Paths.get("/proc/meminfo", new String[0]))) {
                    Matcher c2 = k.matcher(d2);
                    if (!c2.find()) continue;
                    String b2 = c2.group("key");
                    String a2 = c2.group("value");
                    if ("MemAvailable".equals(b2)) {
                        f2 = Long.parseLong(a2) * 1024L;
                    }
                    if ("MemFree".equals(b2)) {
                        h2 = Long.parseLong(a2) * 1024L;
                    }
                    if (!"MemTotal".equals(b2)) continue;
                    e2 = Long.parseLong(a2) * 1024L;
                }
                if (e2 > 0L) {
                    return Optional.of(new r(e2, f2 > 0L ? f2 : h2));
                }
            } catch (IOException i2) {
                i2.printStackTrace();
            }
        }
        try {
            java.lang.management.OperatingSystemMXBean j2 = ManagementFactory.getOperatingSystemMXBean();
            if (j2 instanceof OperatingSystemMXBean) {
                OperatingSystemMXBean g2 = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
                return Optional.of(new r(g2.getTotalPhysicalMemorySize(), g2.getFreePhysicalMemorySize()));
            }
        } catch (NoClassDefFoundError j2) {
            // empty catch block
        }
        return Optional.empty();
    }

    public static void ALLATORIxDEMO() {
        System.gc();
        try {
            System.runFinalization();
            System.gc();
        } catch (NoSuchMethodError noSuchMethodError) {
            // empty catch block
        }
    }

    public static Path ALLATORIxDEMO(String c2) {
        String b2 = System.getProperty("user.home", ".");
        switch (m.ALLATORIxDEMO()[u.ordinal()]) {
            case 2: {
                return Paths.get(b2, "." + c2).toAbsolutePath();
            }
            case 1: {
                String a2 = System.getenv("APPDATA");
                return Paths.get(a2 == null ? b2 : a2, "." + c2).toAbsolutePath();
            }
            case 3: {
                return Paths.get(b2, "Library", "Application Support", c2).toAbsolutePath();
            }
        }
        return Paths.get(b2, c2).toAbsolutePath();
    }

    public static boolean ALLATORIxDEMO(String d2) {
        if (d2.isEmpty()) {
            return false;
        }
        if (d2.equals(".")) {
            return false;
        }
        if (d2.indexOf(47) != -1 || d2.indexOf(0) != -1) {
            return false;
        }
        if (u == y) {
            String a2;
            char c2 = d2.charAt(d2.length() - 1);
            if (c2 == '.') {
                return false;
            }
            if (Character.isWhitespace(c2)) {
                return false;
            }
            int b2 = d2.indexOf(46);
            String string = a2 = b2 == -1 ? d2 : d2.substring(0, b2);
            if (Arrays.binarySearch(d, a2.toLowerCase(Locale.ROOT)) >= 0) {
                return false;
            }
            if (Arrays.binarySearch(r, d2.toLowerCase(Locale.ROOT)) >= 0) {
                return false;
            }
            if (p.matcher(d2).find()) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ int[] ALLATORIxDEMO() {
        if (q != null) {
            return q;
        }
        int[] nArray = new int[m.values().length];
        try {
            nArray[m.e.ordinal()] = 2;
        } catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[m.w.ordinal()] = 3;
        } catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[m.g.ordinal()] = 4;
        } catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[m.y.ordinal()] = 1;
        } catch (NoSuchFieldError noSuchFieldError) {}
        q = nArray;
        return nArray;
    }
}

