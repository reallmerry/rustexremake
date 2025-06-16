/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javafx.application.Platform
 *  javafx.scene.web.WebEngine
 *  javafx.scene.web.WebView
 *  javafx.stage.DirectoryChooser
 *  javafx.stage.Window
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class ea
extends i {
    public static final String urlService = String.valueOf(aa.z) + "/service";
    public final k func;
    private final e winutils;
    private da cfg;
    private ja updater1;
    private Thread updater1thread;
    private String twofatoken;
    private boolean linktypeemail = false;
    private String linkadress = "";
    private Process gameProcess;
    private boolean gameStopped = false;
    public String installpath = "";
    private int code_event = 0;

    public ea(WebEngine b2, WebView a2) {
        ea c2;
        c2.func = new k(c2);
        c2.winutils = new e(c2);
        c2.cfg = aa.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    @Override
    public String getPagePath() {
        return "web/launcher/main.html";
    }

    @Override
    public void loadedWeb() {
        ea f2;
        super.loadedWeb();
        aa e2 = aa.ALLATORIxDEMO();
        f2.func.setUsername(e2.ALLATORIxDEMO().j());
        f2.func.setStartButton(n.ERROR);
        f2.func.setProgress("", -1);
        f2.func.setLang(aa.e);
        f2.func.settingRamSlider(512, m.l, f2.cfg.p);
        f2.func.setAutoRam(f2.cfg.j);
        f2.func.setHideTray(f2.cfg.q);
        f2.func.setStartup(f2.cfg.k);
        f2.func.setGameExitOption(f2.cfg.ALLATORIxDEMO);
        f2.func.toggleButton(x.saveNewPassword, false);
        f2.func.toggleLoading(false);
        ba d2 = e2.ALLATORIxDEMO();
        oa c2 = d2.ALLATORIxDEMO;
        f2.func.setGameOnline(aa.f);
        f2.func.setEmailLink(c2.k);
        f2.func.setDiscordLink(c2.q);
        f2.func.setTwoFa(c2.r);
        File b2 = new File(System.getProperty("user.dir"));
        Path a2 = Path.of((String)b2.getParent(), (String[])new String[0]);
        if (!f2.cfg.x.isEmpty()) {
            a2 = Paths.get(f2.cfg.x, new String[0]);
        }
        f2.cfg.x = a2.toString();
        f2.func.setInstallationPath(a2);
        f2.updater1 = new ja(f2);
        ja.j = String.valueOf(f2.cfg.x) + "/rustexremake/gamefiles/";
        ja.p = new String[]{"http://mirror1.koptojop.studio:23000/download/rustexremake/", "http://koptojop.studio/rustexremake/"};
        f2.updater1thread = new w(f2);
        f2.updater1thread.start();
    }

    @Override
    public void dispose() {
        ea a2;
        super.dispose();
        if (a2.updater1thread != null) {
            a2.updater1thread.interrupt();
            a2.updater1thread = null;
        }
    }

    public Object webAction(String k2, Object j2) {
        ea l2;
        if (k2.equals("gameExitOption")) {
            t f2;
            l2.cfg.ALLATORIxDEMO = f2 = t.values()[(Integer)j2];
        }
        if (k2.equals("gameFolder")) {
            b e2 = b.values()[(Integer)j2];
            if (e2 == b.OPEN_FOLDER) {
                File b2 = new File(String.valueOf(l2.cfg.x) + "/rustexremake/working_dir");
                if (!b2.exists()) {
                    b2.mkdirs();
                }
                f.ALLATORIxDEMO(b2);
            }
            if (e2 == b.SCREENSHOOTS) {
                File a2 = new File(String.valueOf(l2.cfg.x) + "/rustexremake/working_dir/screenshots");
                if (!a2.exists()) {
                    a2.mkdirs();
                }
                f.ALLATORIxDEMO(a2);
            }
            if (e2 == b.CHECK_FILES) {
                ja.j = String.valueOf(l2.cfg.x) + "/rustexremake/gamefiles/";
                l2.func.toggleStartButton(false);
                l2.func.toggleInputElements(false);
                l2.updater1.ALLATORIxDEMO(new y(l2));
            }
            if (e2 == b.DELETE_GAME) {
                l2.func.openDeleteGame();
            }
            if (e2 == b.CREATE_LNK) {
                l2.winutils.createShortcut("Rustex Remake", "rustexremake");
                l2.func.showMessage("launcher.createlnk");
            }
        }
        if (k2.equals("deleteGame")) {
            ja.j = String.valueOf(l2.cfg.x) + "/rustexremake/gamefiles/";
            l2.func.toggleStartButton(false);
            l2.func.toggleInputElements(false);
            l2.updater1.ALLATORIxDEMO(new z(l2, j2));
        }
        if (k2.equals("ramSlider")) {
            l2.cfg.p = (Integer)j2;
        }
        if (k2.equals("ramCheckAuto")) {
            if (l2.func.isAutoRam()) {
                int h2 = m.l / 2;
                if (m.l <= 6144) {
                    h2 = (int)((double)m.l / 2.5);
                }
                if (h2 > 6144) {
                    h2 = 6144;
                }
                l2.func.settingRamSlider(-1, -1, h2);
            }
            l2.cfg.j = l2.func.isAutoRam();
        }
        if (k2.equals("clickPlay")) {
            aa.y = "";
            n g2 = l2.func.getButtonState();
            if (g2 == n.DOWNLOAD) {
                Path c2 = m.ALLATORIxDEMO("kjstudio");
                if (!l2.cfg.x.isEmpty()) {
                    c2 = Paths.get(l2.cfg.x, new String[0]);
                }
                l2.func.setInstallationPath(c2);
            }
            if (g2 == n.PLAY) {
                l2.cfg.x = l2.installpath;
                ja.j = String.valueOf(l2.cfg.x) + "/rustexremake/gamefiles/";
                l2.func.toggleStartButton(false);
                l2.func.toggleInputElements(false);
                l2.updater1.ALLATORIxDEMO(new q(l2));
            }
            if (g2 == n.UPDATE) {
                l2.cfg.x = l2.installpath;
                ja.j = String.valueOf(l2.cfg.x) + "/rustexremake/gamefiles/";
                l2.func.toggleStartButton(false);
                l2.func.toggleInputElements(false);
                l2.updater1.ALLATORIxDEMO(new p(l2));
            }
            if (g2 == n.EXIT) {
                l2.stopGame();
                l2.func.toggleStartButton(false);
            }
        }
        if (k2.equals("downloadChangeButton")) {
            DirectoryChooser i2 = new DirectoryChooser();
            i2.setTitle(l2.func.langObjMap.get("modalDownloadGameChangeFolder"));
            File d2 = i2.showDialog((Window)aa.ALLATORIxDEMO().l);
            if (d2 != null) {
                l2.func.setInstallationPath(d2.toPath());
            }
        }
        if (k2.equals("clickDownload")) {
            if (((Boolean)j2).booleanValue()) {
                l2.winutils.createShortcut("Rustex Remake", "rustexremake");
            }
            l2.cfg.x = l2.installpath;
            ja.j = String.valueOf(l2.cfg.x) + "/rustexremake/gamefiles/";
            l2.func.toggleInputElements(false);
            l2.func.toggleStartButton(false);
            l2.updater1.ALLATORIxDEMO(new c(l2));
        }
        if (k2.equals("saveNewPassword")) {
            l2.processContinue("changepassQ", null);
        }
        if (k2.equals("twofatoggle")) {
            l2.processContinue("toggle2faQ", null);
        }
        if (k2.equals("continueCode")) {
            if (l2.code_event == 1) {
                l2.processContinue("changepass", new String[]{l2.func.getInputValue("newPassword"), l2.func.getInputValue("confirmationCode")});
            }
            if (l2.code_event == 2) {
                l2.processContinue("link", new String[]{String.valueOf(l2.linktypeemail), "true", l2.linkadress, l2.func.getInputValue("confirmationCode")});
            }
            if (l2.code_event == 3) {
                l2.processContinue("link", new String[]{String.valueOf(l2.linktypeemail), "true", l2.linkadress, l2.func.getInputValue("confirmationCode")});
            }
            if (l2.code_event == 4) {
                l2.processContinue("link", new String[]{String.valueOf(l2.linktypeemail), "true", l2.linkadress, l2.func.getInputValue("confirmationCode")});
            }
            if (l2.code_event == 5) {
                l2.processContinue("toggle2fa", new String[]{String.valueOf(!aa.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO.r), l2.func.getInputValue("confirmationCode")});
            }
        }
        if (k2.equals("emailLink")) {
            l2.processContinue("link", new String[]{"true", "false", l2.func.getInputValue("bindingEmailModalInput")});
        }
        if (k2.equals("discordLink")) {
            l2.processContinue("link", new String[]{"false", "false", l2.func.getInputValue("bindingDiscordModalInput")});
        }
        return null;
    }

    public void processContinue(String d2, String[] c2) {
        ea e2;
        try {
            e2.func.toggleInputElements(false);
            e2.func.removeFormErrorMessage();
            ba a2 = aa.ALLATORIxDEMO().ALLATORIxDEMO();
            new Thread(new j(e2, d2, a2, c2)).start();
        } catch (Exception b2) {
            b2.printStackTrace();
            Platform.runLater(() -> {
                ea a2;
                a2.func.toggleInputElements(true);
                a2.func.displayFormErrorMessage(b2.getLocalizedMessage());
            });
        }
    }

    public void startGame() {
        ea n2;
        System.out.println("Start game");
        ba m2 = aa.ALLATORIxDEMO().ALLATORIxDEMO();
        String l2 = null;
        try {
            Object i2 = aa.ALLATORIxDEMO("web/utils", "start", new String[]{"run", m2.j(), m2.ALLATORIxDEMO()});
            if (!i2.toString().startsWith("L")) {
                if (i2.toString().equals("39")) {
                    Platform.runLater(() -> {
                        ea a2;
                        a2.func.displayFormErrorMessage("Kicked: Invalid session. Error 39");
                    });
                    return;
                }
                System.out.println("Get game token error  Error: " + i2.toString());
                Platform.runLater(() -> {
                    ea a2;
                    a2.func.displayFormErrorMessage(String.valueOf(ua.ALLATORIxDEMO("launcher.game.cantrun")) + " Error: " + i2.toString());
                });
                return;
            }
            l2 = i2.toString().substring(1);
        } catch (Exception j2) {
            j2.printStackTrace();
            n2.func.displayFormErrorMessage(String.valueOf(ua.ALLATORIxDEMO("launcher.game.cantrun")) + " Error: start");
            return;
        }
        n2.gameStopped = false;
        n2.func.removeFormErrorMessage();
        n2.func.setStartButton(n.PLAY);
        n2.func.setProgress("", -1);
        n2.func.toggleStartButton(false);
        String k2 = new File(String.valueOf(ja.j) + "java/bin/javaw.exe").getAbsolutePath();
        String h2 = String.valueOf(ja.j) + "game.jar";
        String g2 = String.valueOf(ja.j) + "natives";
        String f2 = "net.minecraft.client.main.RustexMain";
        String e2 = String.valueOf(ja.j) + "jarlibs-other.jar";
        String d2 = String.valueOf(ja.j) + "jarlibs-base.jar";
        String c2 = new File(String.valueOf(n2.cfg.x) + "/rustexremake/working_dir").getAbsolutePath();
        ArrayList<String> b2 = new ArrayList<String>();
        b2.add(k2);
        b2.add("-Djava.library.path=" + g2);
        b2.add("-XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump");
        b2.add("-Xms" + n2.cfg.p + "m");
        b2.add("-Xmx" + n2.cfg.p + "m");
        b2.add("-XX:+UnlockExperimentalVMOptions");
        b2.add("-XX:+UseG1GC");
        b2.add("-XX:G1NewSizePercent=20");
        b2.add("-XX:G1ReservePercent=20");
        b2.add("-XX:MaxGCPauseMillis=50");
        b2.add("-XX:G1HeapRegionSize=32M");
        b2.add("-cp");
        b2.add(String.valueOf(h2) + ";" + d2 + ";" + e2);
        b2.add(f2);
        b2.add(new File(String.valueOf(ja.j) + "/res").getAbsolutePath());
        b2.add("\"" + c2 + "\"");
        b2.add(l2);
        b2.add(m2.j());
        b2.add(n2.cfg.d);
        b2.add("185.207.214.85:25566,185.207.214.85:25567,185.207.214.85:25568,185.207.214.85:25571");
        if (!new File(c2).exists()) {
            new File(c2).mkdirs();
        }
        ExecutorService a2 = Executors.newSingleThreadExecutor();
        a2.submit(() -> {
            block8: {
                ea k2;
                ProcessBuilder j2 = new ProcessBuilder(b2);
                j2.directory(new File(c2));
                try {
                    String g2;
                    k2.gameProcess = j2.start();
                    Platform.runLater(() -> {
                        ea a2;
                        a2.func.setStartButton(n.EXIT);
                        a2.func.toggleStartButton(true);
                    });
                    BufferedReader h2 = new BufferedReader(new InputStreamReader(k2.gameProcess.getInputStream()));
                    String e2 = "LnchMsg";
                    boolean d2 = false;
                    boolean c2 = false;
                    while ((g2 = h2.readLine()) != null) {
                        String a2;
                        void f2;
                        if (!c2 && f2.contains("LWJGL Version:")) {
                            Platform.runLater(() -> aa.ALLATORIxDEMO().ALLATORIxDEMO(true));
                        }
                        if (!f2.startsWith("LnchMsg") || !(a2 = f2.substring(e2.length() + 1).replace("/n", "")).equals("Launch Success")) continue;
                        System.out.println("Launch Success");
                        d2 = true;
                        if (k2.cfg.ALLATORIxDEMO != t.ON_START) continue;
                        Platform.runLater(() -> aa.ALLATORIxDEMO().d());
                    }
                    BufferedReader b2 = new BufferedReader(new InputStreamReader(k2.gameProcess.getErrorStream()));
                    while ((g2 = b2.readLine()) != null) {
                        System.out.println("Game Error: " + g2);
                    }
                    k2.gameProcess.waitFor();
                    System.out.println("Game process has ended");
                    if (!k2.gameStopped && !d2) {
                        Platform.runLater(() -> {
                            ea a2;
                            a2.func.displayFormErrorMessage(String.valueOf(ua.ALLATORIxDEMO("launcher.game.cantrun")) + " Error: " + 2);
                        });
                    }
                    Platform.runLater(() -> {
                        ea a2;
                        a2.func.setStartButton(n.PLAY);
                        a2.func.toggleStartButton(true);
                    });
                    if (k2.cfg.ALLATORIxDEMO == t.ON_END_GAME && d2) {
                        Platform.runLater(() -> aa.ALLATORIxDEMO().d());
                    } else {
                        Platform.runLater(() -> aa.ALLATORIxDEMO().ALLATORIxDEMO(false));
                    }
                } catch (Exception i2) {
                    System.out.println("Can't run process");
                    i2.printStackTrace();
                    if (k2.gameStopped) break block8;
                    Platform.runLater(() -> {
                        ea a2;
                        a2.func.displayFormErrorMessage(String.valueOf(ua.ALLATORIxDEMO("launcher.game.cantrun")) + " Error: " + 1);
                    });
                }
            }
        });
        a2.shutdown();
    }

    public void stopGame() {
        ea a2;
        if (a2.gameProcess != null) {
            a2.gameStopped = true;
            a2.gameProcess.destroy();
            System.out.println("Game process has been stopped");
        }
    }

    /*
     * WARNING - void declaration
     */
    private String sendRequestServiceServer(String j2) throws Exception {
        int c2;
        String i2 = urlService;
        HttpURLConnection h2 = null;
        URL g2 = new URL(i2);
        h2 = (HttpURLConnection)g2.openConnection();
        h2.setRequestMethod("POST");
        h2.setDoOutput(true);
        byte[] f2 = j2.getBytes("UTF-8");
        h2.setRequestProperty("Content-Length", String.valueOf(f2.length));
        h2.getOutputStream().write(f2);
        int e2 = h2.getResponseCode();
        System.out.println("Response Code: " + e2);
        StringBuilder d2 = new StringBuilder();
        while ((c2 = h2.getInputStream().read()) != -1) {
            void b2;
            d2.append((char)b2);
        }
        if (h2 != null) {
            h2.disconnect();
        }
        String a2 = d2.toString();
        if (h2.getResponseCode() == 201) {
            a2 = "captcha#" + a2;
        }
        return a2;
    }

    public void onTypedNewPassword() {
        String b2;
        String d2;
        ea f2;
        boolean e2 = true;
        f2.func.setErrorMessagePassword("");
        if (e2) {
            String c2 = f2.func.getInputValue("newPassword");
            na a2 = xb.ALLATORIxDEMO().ALLATORIxDEMO(c2);
            if (a2 != na.p) {
                e2 = false;
                if (!c2.isEmpty()) {
                    if (a2 == na.d) {
                        f2.func.setErrorMessagePassword(ua.ALLATORIxDEMO("auth.field.password.short"));
                    } else if (a2 == na.r) {
                        f2.func.setErrorMessagePassword(ua.ALLATORIxDEMO("auth.field.password.long"));
                    } else if (a2 == na.k) {
                        f2.func.setErrorMessagePassword(ua.ALLATORIxDEMO("auth.field.password.chars"));
                    }
                }
            }
        }
        if (e2 && !(d2 = f2.func.getInputValue("repeatPassword")).equals(b2 = f2.func.getInputValue("newPassword"))) {
            f2.func.setErrorMessagePassword(ua.ALLATORIxDEMO("auth.field.password.match"));
            e2 = false;
        }
        f2.func.toggleButton(x.saveNewPassword, e2);
    }

    public void onTypedCode() {
        ea c2;
        boolean b2 = true;
        c2.func.setCodeError("");
        sa a2 = xb.ALLATORIxDEMO().ALLATORIxDEMO(c2.func.getInputValue("confirmationCode"), o.lashcodechar);
        if (a2 != sa.j) {
            b2 = false;
            if (!c2.func.getInputValue("confirmationCode").isEmpty()) {
                if (a2 == sa.p) {
                    c2.func.setCodeError(ua.ALLATORIxDEMO("auth.field.code.short"));
                } else if (a2 == sa.d) {
                    c2.func.setCodeError(ua.ALLATORIxDEMO("auth.field.code.long"));
                } else if (a2 == sa.k) {
                    c2.func.setCodeError(ua.ALLATORIxDEMO("auth.field.code.chars"));
                } else if (a2 == sa.r) {
                    c2.func.setCodeError(ua.ALLATORIxDEMO("auth.field.code.wrong"));
                } else {
                    c2.func.setCodeError(ua.ALLATORIxDEMO("auth.unknownexc"));
                }
            }
        }
        c2.func.toggleButton(x.bindingButtonNext, b2);
    }

    public void onTypedDiscord() {
        ea c2;
        boolean b2 = true;
        String a2 = c2.func.getInputValue("bindingDiscordModalInput");
        if (a2.length() > 48 || a2.isEmpty() || a2.equals(aa.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO.q)) {
            b2 = false;
        }
        c2.func.toggleButton(x.bindingDiscordModalNext, b2);
    }

    public void onTypedEmail() {
        ea c2;
        boolean b2 = true;
        String a2 = c2.func.getInputValue("bindingEmailModalInput");
        if (a2.isEmpty() || a2.equals(aa.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO.k)) {
            b2 = false;
        }
        if (b2 && xb.ALLATORIxDEMO().ALLATORIxDEMO(a2) != ia.r) {
            b2 = false;
        }
        c2.func.toggleButton(x.bindingEmailModalNext, b2);
    }

    public void setLang(String a2) {
        ea b2;
        b2.func.setLang(a2);
    }

    public void checkedHideTray(boolean a2) {
        b.cfg.q = a2;
        aa.ALLATORIxDEMO().j(a2);
    }

    public void checkedStartup(boolean a2) {
        ea b2;
        b2.cfg.k = a2;
        if (a2) {
            b2.winutils.setStartup(aa.s, aa.c, "KJST Launcher");
        } else {
            b2.winutils.removeStartup(aa.s, "KJST Launcher");
        }
    }

    public void openLinkDiscord() {
        m.ALLATORIxDEMO("https://discord.gg/gp4PrYc");
    }

    public void openLogs() {
        f.ALLATORIxDEMO(new File("logs"));
    }

    public void logout() {
        new File("session").delete();
        new ra().init();
    }

    public void openLinkSupport() {
        if (aa.e.equals("ru_ru")) {
            m.ALLATORIxDEMO("https://forms.gle/bJR11WCSHWTfaq4o8");
        } else {
            m.ALLATORIxDEMO("https://forms.gle/mu8X3D1wUn6AmATw6");
        }
    }
}

