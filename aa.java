/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javafx.application.Application
 *  javafx.application.Platform
 *  javafx.concurrent.Worker$State
 *  javafx.event.Event
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.control.Label
 *  javafx.scene.control.MenuItem
 *  javafx.scene.image.Image
 *  javafx.scene.input.KeyCode
 *  javafx.scene.input.KeyCodeCombination
 *  javafx.scene.input.KeyCombination
 *  javafx.scene.input.KeyCombination$Modifier
 *  javafx.scene.input.KeyEvent
 *  javafx.scene.input.MouseEvent
 *  javafx.scene.layout.Pane
 *  javafx.scene.layout.StackPane
 *  javafx.scene.paint.Color
 *  javafx.scene.paint.Paint
 *  javafx.scene.web.WebEngine
 *  javafx.scene.web.WebView
 *  javafx.stage.Screen
 *  javafx.stage.Stage
 *  netscape.javascript.JSObject
 */
import com.dustinredmond.fxtrayicon.FXTrayIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.SwingUtilities;
import netscape.javascript.JSObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class aa
extends Application {
    private static final String ba = "70c818d66d68c364087a7919d0dfefa0c3f2d3cc191acbc6241a127e42d09435";
    private static final boolean ra = false;
    public static boolean ja = false;
    public static int f = -1;
    private static aa n;
    public static boolean m;
    public static String z;
    public static String s;
    public static String c;
    public static String y;
    public static String e;
    private String w = "";
    public static i g;
    public String b = "";
    private boolean u;
    public Stage l;
    private double a;
    private double i;
    private Label h;
    public StackPane v;
    private boolean t = false;
    public Pane o;
    public WebView x;
    private static File j;
    private static File p;
    private static File d;
    private final ca r;
    private ba k;
    public da q;
    private static final Logger ALLATORIxDEMO;

    static {
        m = true;
        z = "http://185.207.214.85:20480";
        s = "";
        c = "";
        y = "";
        e = "en_us";
        j = new File("app.lock");
        p = new File("show.lock");
        d = new File("exit.lock");
        ALLATORIxDEMO = LogManager.getLogger(aa.class);
    }

    public aa() {
        aa a2;
        a2.r = new ca(a2);
        a2.k = new ba("test", "000", "123123");
        a2.q = new da();
    }

    public static aa ALLATORIxDEMO() {
        return n;
    }

    public static void main(String[] stringArray) throws IOException {
        String[] e2;
        block9: {
            System.out.println("\n################################################\n#                                              #\n#        ## #   #    ## ### ### ##  ###        #\n#       # # #   #   # #  #  # # # #  #         #\n#       ### #   #   ###  #  # # ##   #         #\n#       # # ### ### # #  #  ### # # ###        #\n#                                              #\n# Obfuscation by Allatori Obfuscator v8.7 DEMO #\n#                                              #\n#           http://www.allatori.com            #\n#                                              #\n################################################\n");
            if (j.exists()) {
                try {
                    BasicFileAttributes d2 = Files.readAttributes(j.toPath(), BasicFileAttributes.class, new LinkOption[0]);
                    long b2 = System.currentTimeMillis() - d2.lastModifiedTime().toMillis();
                    if (b2 >= 10000L) break block9;
                    try {
                        p.createNewFile();
                    } catch (Exception a2) {
                        a2.printStackTrace();
                    }
                    System.exit(1);
                } catch (Exception c2) {
                    c2.printStackTrace();
                    System.exit(1);
                }
            }
        }
        System.setErr(new qa("STDERR", System.err));
        System.setOut(new qa("STDOUT", System.out));
        if (e2.length > 0) {
            if (m.u == m.y) {
                s = String.valueOf(e2[0]) + "\\updater.exe";
                c = e2[0];
            } else {
                s = String.valueOf(e2[0]) + "\\updater";
                c = e2[0];
            }
            s = s.replace("--execp ", "").replace("\"", "");
            s = s.replace("--execp=", "").replace("\"", "");
            c = c.replace("--execp ", "").replace("\"", "");
            c = c.replace("--execp=", "").replace("\"", "");
            if (e2.length > 1) {
                y = e2[1];
                y = y.replace("--launch ", "").replace("\"", "");
                y = y.replace("--launch=", "").replace("\"", "");
                System.out.println("launch " + y);
            }
        }
        System.out.println("Exec path " + s);
        System.setProperty("prism.lcdtext", "false");
        aa.launch((String[])e2);
    }

    public void p() {
        aa f2;
        ta.k = "web/localization/";
        Locale e2 = Locale.getDefault();
        String d2 = e2.getLanguage();
        String c2 = e2.getCountry();
        System.out.println("Language: " + d2 + ", Country: " + c2);
        e = String.valueOf(d2.toLowerCase()) + "_" + c2.toLowerCase();
        if (!f2.q.d.isEmpty()) {
            e = f2.q.d;
        }
        if (!f2.w.equals(e)) {
            try {
                ua.q = new ta(e);
            } catch (IOException b2) {
                try {
                    e = "en_us";
                    ua.q = new ta("en_us");
                } catch (IOException a2) {
                    a2.printStackTrace();
                }
            }
        }
        f2.w = e;
        System.out.println("Set locale to " + e);
    }

    /*
     * WARNING - void declaration
     */
    private void w() {
        aa h2;
        String[] g2 = new String[]{};
        String f2 = "wmic diskdrive get serialnumber";
        try {
            String b2;
            Process e2 = Runtime.getRuntime().exec(f2);
            BufferedReader c2 = new BufferedReader(new InputStreamReader(e2.getInputStream()));
            h2.b = "";
            while ((b2 = c2.readLine()) != null) {
                void a2;
                h2.b = String.valueOf(h2.b) + (String)a2;
            }
        } catch (IOException d2) {
            h2.b = "5555";
            d2.printStackTrace();
        }
        h2.b = String.valueOf(h2.b.hashCode());
    }

    /*
     * Unable to fully structure code
     */
    public static Object ALLATORIxDEMO(String u, String t, String[] s) throws IOException, InterruptedException {
        q = System.getProperty("os.name").toLowerCase();
        p = new ArrayList<String>();
        o = new File(u);
        n = o.getAbsolutePath();
        if (q.contains("win")) {
            k = String.valueOf(n) + File.separator + t + "-win.exe";
        } else if (q.contains("nix") || q.contains("nux")) {
            l = String.valueOf(n) + File.separator + t + "-nux";
        } else {
            m = String.valueOf(n) + File.separator + t + "-mac";
        }
        if (q.contains("win")) {
            p.add("cmd.exe");
            p.add("/c");
            p.add(m);
        } else if (q.contains("nix") || q.contains("nux") || q.contains("mac")) {
            h = new ProcessBuilder(new String[]{"chmod", "+x", m});
            f = h.start();
            f.waitFor();
            p.add("./" + m);
        } else {
            throw new UnsupportedOperationException("Unsupported operating system");
        }
        var12_12 = s;
        var11_15 = s.length;
        f = 0;
        while (f < var11_15) {
            j = var12_12[f];
            p.add(j);
            ++f;
        }
        r = new ProcessBuilder(p);
        r.directory(o);
        r.redirectErrorStream(true);
        i = r.start();
        g = new StringBuilder();
        var11_16 = null;
        var12_12 = null;
        try {
            d = i.getInputStream();
            try {
                c = new BufferedReader(new InputStreamReader(d));
                try {
                    while ((b = c.readLine()) != null) {
                        g.append(String.valueOf(a) + "\n");
                    }
                } finally {
                    if (c != null) {
                        c.close();
                    }
                }
                ** if (d == null) goto lbl-1000
            } catch (Throwable var12_13) {
                if (var11_16 == null) {
                    var11_16 = var12_13;
                } else if (var11_16 != var12_13) {
                    var11_16.addSuppressed(var12_13);
                }
                if (d != null) {
                    d.close();
                }
                throw var11_16;
            }
lbl-1000:
            // 1 sources

            {
                d.close();
            }
lbl-1000:
            // 2 sources

            {
            }
        } catch (Throwable var12_14) {
            if (var11_16 == null) {
                var11_16 = var12_14;
            } else if (var11_16 != var12_14) {
                var11_16.addSuppressed(var12_14);
            }
            throw var11_16;
        }
        e = i.waitFor();
        if (e != 0) {
            return "CODE" + e + " " + g.toString().trim();
        }
        return g.toString().trim();
    }

    private boolean ALLATORIxDEMO() {
        aa d2;
        if (!d2.b.isEmpty() && !d2.ALLATORIxDEMO().k.equals("000")) {
            try {
                SecretKeySpec b2 = f.ALLATORIxDEMO(d2.b);
                String[] a2 = new String[]{f.j(d2.ALLATORIxDEMO().r, b2), f.j(d2.ALLATORIxDEMO().k, b2), f.j(d2.ALLATORIxDEMO().q, b2)};
                f.ALLATORIxDEMO("session", a2);
                return true;
            } catch (Exception c2) {
                c2.printStackTrace();
            }
        }
        return false;
    }

    private String[] ALLATORIxDEMO() {
        String[] d2;
        aa e2;
        if (!e2.b.isEmpty() && new File("session").exists() && (d2 = f.ALLATORIxDEMO("session")) != null && d2.length == 3) {
            try {
                SecretKeySpec b2 = f.ALLATORIxDEMO(e2.b);
                String[] a2 = new String[]{f.ALLATORIxDEMO(d2[0], b2), f.ALLATORIxDEMO(d2[1], b2), f.ALLATORIxDEMO(d2[2], b2)};
                return a2;
            } catch (Exception c2) {
                c2.printStackTrace();
            }
        }
        return null;
    }

    public void j(boolean a3) {
        aa b2;
        b2.t = a3;
        System.out.println("Set hideTray to " + a3);
        b2.l.setOnCloseRequest(a2 -> {
            aa b2;
            if (a3) {
                a2.consume();
                b2.l.hide();
            } else {
                b2.d();
            }
        });
    }

    public void ALLATORIxDEMO(boolean a2) {
        aa b2;
        if (a2) {
            b2.l.hide();
        } else {
            b2.l.show();
        }
    }

    public void e() {
        aa g2;
        try {
            g2.q.j();
        } catch (IOException iOException) {
            // empty catch block
        }
        g2.v = new StackPane();
        g2.o = new Pane();
        g2.o.setStyle("-fx-background-color: black;");
        g2.o.setVisible(true);
        Scene f2 = new Scene((Parent)g2.v, g2.a, g2.i);
        g2.v.getChildren().add((Object)new Pane());
        g2.v.getChildren().add((Object)g2.o);
        g2.l.setScene(f2);
        try {
            FXTrayIcon c2 = new FXTrayIcon(g2.l, new File("web/kjlogo16.png").toURL());
            c2.setApplicationTitle("KJST Launcher");
            c2.show();
            MenuItem a2 = new MenuItem("\u0412\u044b\u0445\u043e\u0434");
            SwingUtilities.invokeLater(() -> {
                aa a2;
                c2.getMenuItem(1).setLabel(ua.ALLATORIxDEMO("popup.exit"));
                c2.getMenuItem(1).addActionListener(new ma(a2));
            });
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
        g2.p();
        g2.w();
        String[] d2 = g2.ALLATORIxDEMO();
        if (d2 != null) {
            g2.k = new ba(d2[0], d2[1], d2[2]);
        }
        Image b2 = null;
        b2 = new Image(new File("web/kjlogo.png").toURI().toString());
        g2.l.getIcons().add((Object)b2);
        g2.j(g2.ALLATORIxDEMO().q);
        if (g2.u) {
            g2.l.setMaximized(true);
        }
        if (!g2.ALLATORIxDEMO().k.equals("000") && m) {
            ((o)new o().init()).switchContext(u.preLogin);
        } else {
            new ra().init();
        }
        if (!g2.l.isShowing()) {
            g2.l.show();
        }
    }

    public void ALLATORIxDEMO(WebView a2) {
        aa b2;
        b2.v.getChildren().set(1, (Object)b2.o);
        a2.opacityProperty().set(1.0);
    }

    public WebView ALLATORIxDEMO() {
        aa d3;
        d3.o.setVisible(false);
        Scene c3 = d3.v.getScene();
        c3.setFill((Paint)Color.web((String)"#000000"));
        WebView b3 = new WebView();
        b3.setStyle("-fx-background-color: black;");
        b3.setContextMenuEnabled(false);
        b3.opacityProperty().set(0.0);
        d3.v.getChildren().set(0, (Object)b3);
        WebEngine a2 = b3.getEngine();
        if (d3.x != null) {
            d3.v.getChildren().set(1, (Object)d3.x);
            d3.x.addEventFilter(MouseEvent.ANY, Event::consume);
            d3.x.addEventFilter(KeyEvent.ANY, Event::consume);
        }
        d3.x = b3;
        a2.getLoadWorker().stateProperty().addListener((d2, c2, b2) -> {
            if (b2 == Worker.State.SUCCEEDED) {
                aa e2;
                JSObject a2 = (JSObject)a2.executeScript("window");
                a2.setMember("java", (Object)e2.r);
                a2.executeScript("console.log = function(message)\n{\n    java.log(message);\n};");
                a2.executeScript("window.onerror = function (message, source, lineno, colno, error) {\n    java.logError(message, source, lineno, colno, error);\n    return true;\n};");
                a2.setMember("javaConnector", (Object)g);
                a2.executeScript("loadedJC();");
                g.loadedWeb();
            }
        });
        if (ja) {
            c3.setOnKeyPressed(b2 -> {
                if (new KeyCodeCombination(KeyCode.R, new KeyCombination.Modifier[]{KeyCombination.CONTROL_DOWN}).match(b2)) {
                    String a2 = (String)a2.executeScript("document.documentElement.outerHTML");
                    a2.reload();
                }
            });
        }
        d3.ALLATORIxDEMO(d3.l);
        return b3;
    }

    public void start(Stage l2) {
        aa m2;
        block8: {
            if (j.exists()) {
                try {
                    BasicFileAttributes h2 = Files.readAttributes(j.toPath(), BasicFileAttributes.class, new LinkOption[0]);
                    long f2 = System.currentTimeMillis() - h2.lastModifiedTime().toMillis();
                    if (f2 >= 10000L) break block8;
                    try {
                        p.createNewFile();
                    } catch (Exception d2) {
                        d2.printStackTrace();
                    }
                    System.exit(1);
                } catch (Exception k2) {
                    k2.printStackTrace();
                    System.exit(1);
                }
            }
        }
        try {
            j.createNewFile();
        } catch (Exception j2) {
            j2.printStackTrace();
            System.exit(1);
        }
        l2.setOnCloseRequest(a2 -> {
            boolean bl = j.delete();
        });
        ScheduledExecutorService i2 = Executors.newScheduledThreadPool(1);
        i2.scheduleAtFixedRate(() -> {
            try {
                j.setLastModified(System.currentTimeMillis());
            } catch (Exception a2) {
                a2.printStackTrace();
            }
        }, 0L, 5L, TimeUnit.SECONDS);
        ScheduledExecutorService g2 = Executors.newScheduledThreadPool(1);
        g2.scheduleAtFixedRate(() -> {
            try {
                aa b2;
                if (d.exists()) {
                    d.delete();
                    b2.d();
                }
                if (p.exists()) {
                    p.delete();
                    Platform.runLater(() -> {
                        aa a2;
                        a2.ALLATORIxDEMO(false);
                    });
                }
            } catch (Exception a2) {
                a2.printStackTrace();
            }
        }, 0L, 1L, TimeUnit.SECONDS);
        m2.l = l2;
        n = m2;
        l2.setTitle("Koptojop Studio Launcher");
        m2.a = 1280.0;
        m2.i = 720.0;
        m2.u = false;
        double e2 = Screen.getPrimary().getBounds().getWidth();
        double c3 = Screen.getPrimary().getBounds().getHeight();
        if (m2.a >= e2 || m2.i >= c3) {
            m2.u = true;
            m2.a = (int)e2 - 32;
            m2.i = (int)c3 - 78;
        }
        l2.widthProperty().addListener((c2, b2, a2) -> {
            aa d2;
            d2.ALLATORIxDEMO(l2);
        });
        l2.heightProperty().addListener((c2, b2, a2) -> {
            aa d2;
            d2.ALLATORIxDEMO(l2);
        });
        m2.h = new Label("");
        m2.h.setTextFill((Paint)Color.WHITE);
        StackPane b3 = new StackPane();
        b3.setStyle("-fx-background-color: #353535; -fx-font-size: 48px;");
        b3.getChildren().add((Object)m2.h);
        Scene a3 = new Scene((Parent)b3, m2.a, m2.i);
        l2.setScene(a3);
        l2.setMinWidth(m2.a + 16.0);
        l2.setMinHeight(m2.i + 39.0);
        new Thread(() -> {
            aa e2;
            fa d2 = new fa(e2);
            try {
                if (!new File("siletcheck").exists()) {
                    e2.j("Checking web assets...");
                }
                long b2 = System.currentTimeMillis();
                d2.ALLATORIxDEMO(true);
                System.out.println(System.currentTimeMillis() - b2);
                if (System.currentTimeMillis() - b2 > 2000L) {
                    new File("siletcheck").deleteOnExit();
                } else {
                    new File("siletcheck").createNewFile();
                }
                Platform.runLater(() -> {
                    aa a2;
                    a2.e();
                });
            } catch (Exception c2) {
                e2.j("Cant load gui assets");
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException a2) {
                    a2.printStackTrace();
                }
                c2.printStackTrace();
                System.exit(0);
            }
        }).start();
    }

    public void j(String a2) {
        aa b2;
        Platform.runLater(() -> {
            aa a2;
            a2.h.setText(a2);
        });
        if (!b2.l.isShowing()) {
            Platform.runLater(() -> {
                aa a2;
                a2.l.show();
            });
        }
    }

    public void d() {
        try {
            aa b2;
            b2.q.ALLATORIxDEMO();
        } catch (IOException a2) {
            a2.printStackTrace();
        }
        g.exit();
        j.delete();
        Platform.exit();
        System.exit(0);
    }

    public void ALLATORIxDEMO(Stage f2) {
        aa g2;
        double e2 = f2.widthProperty().doubleValue();
        double d2 = f2.heightProperty().doubleValue();
        double c2 = e2 / 1296.0;
        double b2 = d2 / 759.0;
        double a2 = Math.min(c2, b2);
        if (g2.x != null) {
            g2.x.setZoom(a2);
        }
    }

    public ba ALLATORIxDEMO() {
        aa a2;
        return a2.k;
    }

    public void ALLATORIxDEMO(ba b2, boolean a2) {
        c2.k = b2;
        f.j("session");
        m = a2;
        if (a2) {
            aa c2;
            c2.ALLATORIxDEMO();
        }
    }

    public da ALLATORIxDEMO() {
        aa a2;
        return a2.q;
    }
}

