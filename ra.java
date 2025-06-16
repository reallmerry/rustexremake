/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javafx.application.Platform
 *  netscape.javascript.JSObject
 */
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import javafx.application.Platform;
import netscape.javascript.JSObject;
import org.json.JSONObject;

public class ra
extends i {
    public String kickmsg;
    public int tiks = 1;
    public static final String urlLogin = String.valueOf(aa.z) + "/login";
    public HashSet<String> leakedpasswords = new HashSet();
    public HashSet<String> registred = new HashSet();
    public static String twofaToken = "fff";
    public static String lashcodechar = "";
    public static String lastpass = "";
    public int bantime = 0;
    public boolean conError = false;

    public ra() {
        ra a2;
    }

    @Override
    public String getPagePath() {
        return "web/register.html";
    }

    public void setKick(String a2) {
        b.kickmsg = a2;
    }

    public void updateJS() {
        ra a2;
        if (a2.bantime > 0) {
            --a2.bantime;
            a2.toggleRegisterButton(false);
            a2.webEngine.executeScript("document.getElementById('registerButton').value = '" + a2.formatTime(a2.bantime) + "'");
            if (a2.bantime <= 0) {
                a2.webEngine.executeScript("document.getElementById('registerButton').value = I18n('auth.button.continue')");
                a2.toggleRegisterButton(true);
            }
        }
    }

    public String formatTime(int c2) {
        int b2 = c2 / 60;
        int a2 = c2 % 60;
        return String.format("%02d:%02d", b2, a2);
    }

    public void onTyped() {
        ra l2;
        l2.toggleRegisterButton(false);
        l2.clearAllFieldErrors();
        nb k2 = xb.ALLATORIxDEMO();
        boolean j2 = true;
        if (l2.isRecovery()) {
            if (l2.isRecoveryCode()) {
                sa c2;
                na f2 = k2.ALLATORIxDEMO(l2.getInputValue("newpasswordRec"));
                if (f2 != na.p) {
                    j2 = false;
                    if (!l2.getInputValue("newpasswordRec").isEmpty()) {
                        if (f2 == na.d) {
                            l2.highlightFieldError("newpasswordRec", ua.ALLATORIxDEMO("auth.field.password.short"));
                        } else if (f2 == na.r) {
                            l2.highlightFieldError("newpasswordRec", ua.ALLATORIxDEMO("auth.field.password.long"));
                        } else if (f2 == na.k) {
                            l2.highlightFieldError("newpasswordRec", ua.ALLATORIxDEMO("auth.field.password.chars"));
                        }
                    }
                }
                if (f2 == na.p && !l2.getInputValue("newpasswordRec").equals(l2.getInputValue("repeatNewpasswordRec"))) {
                    j2 = false;
                    l2.highlightFieldError("repeatNewpasswordRec", ua.ALLATORIxDEMO("auth.field.password.match"));
                }
                if ((c2 = k2.ALLATORIxDEMO(l2.getInputValue("twofacodeRec"), lashcodechar)) != sa.j) {
                    j2 = false;
                    if (!l2.getInputValue("twofacodeRec").isEmpty()) {
                        if (c2 == sa.p) {
                            l2.highlightFieldError("twofacodeRec", ua.ALLATORIxDEMO("auth.field.code.short"));
                        } else if (c2 == sa.d) {
                            l2.highlightFieldError("twofacodeRec", ua.ALLATORIxDEMO("auth.field.code.long"));
                        } else if (c2 == sa.k) {
                            l2.highlightFieldError("twofacodeRec", ua.ALLATORIxDEMO("auth.field.code.chars"));
                        } else if (c2 == sa.r) {
                            l2.highlightFieldError("twofacodeRec", ua.ALLATORIxDEMO("auth.field.code.wrong"));
                        } else {
                            l2.highlightFieldError("twofacodeRec", ua.ALLATORIxDEMO("auth.unknownexc"));
                        }
                    }
                }
            } else {
                pa e2 = k2.ALLATORIxDEMO(l2.getInputValue("usernameRec"));
                if (e2 != pa.p) {
                    j2 = false;
                    if (!l2.getInputValue("usernameRec").isEmpty()) {
                        if (e2 == pa.d) {
                            l2.highlightFieldError("usernameRec", ua.ALLATORIxDEMO("auth.field.username.short"));
                        } else if (e2 == pa.r) {
                            l2.highlightFieldError("usernameRec", ua.ALLATORIxDEMO("auth.field.username.long"));
                        } else if (e2 == pa.k) {
                            l2.highlightFieldError("usernameRec", ua.ALLATORIxDEMO("auth.field.username.chars"));
                        } else {
                            l2.highlightFieldError("usernameRec", ua.ALLATORIxDEMO("auth.unknownexc"));
                        }
                    }
                }
                if (l2.getInputValue("addresRec").isEmpty()) {
                    j2 = false;
                }
            }
        } else if (l2.isLogin()) {
            if (l2.isLoginCode()) {
                sa i2 = k2.ALLATORIxDEMO(l2.getInputValue("twofacode"), lashcodechar);
                if (i2 != sa.j) {
                    j2 = false;
                    if (!l2.getInputValue("twofacode").isEmpty()) {
                        if (i2 == sa.p) {
                            l2.highlightFieldError("twofacode", ua.ALLATORIxDEMO("auth.field.code.short"));
                        } else if (i2 == sa.d) {
                            l2.highlightFieldError("twofacode", ua.ALLATORIxDEMO("auth.field.code.long"));
                        } else if (i2 == sa.k) {
                            l2.highlightFieldError("twofacode", ua.ALLATORIxDEMO("auth.field.code.chars"));
                        } else if (i2 == sa.r) {
                            l2.highlightFieldError("twofacode", ua.ALLATORIxDEMO("auth.field.code.wrong"));
                        } else {
                            l2.highlightFieldError("twofacode", ua.ALLATORIxDEMO("auth.unknownexc"));
                        }
                    }
                }
            } else {
                na b2;
                pa h2 = k2.ALLATORIxDEMO(l2.getInputValue("username"));
                if (h2 != pa.p) {
                    j2 = false;
                    if (!l2.getInputValue("username").isEmpty()) {
                        if (h2 == pa.d) {
                            l2.highlightFieldError("username", ua.ALLATORIxDEMO("auth.field.username.short"));
                        } else if (h2 == pa.r) {
                            l2.highlightFieldError("username", ua.ALLATORIxDEMO("auth.field.username.long"));
                        } else if (h2 == pa.k) {
                            l2.highlightFieldError("username", ua.ALLATORIxDEMO("auth.field.username.chars"));
                        } else {
                            l2.highlightFieldError("username", ua.ALLATORIxDEMO("auth.unknownexc"));
                        }
                    }
                }
                if ((b2 = k2.ALLATORIxDEMO(l2.getInputValue("password"))) != na.p) {
                    j2 = false;
                    if (!l2.getInputValue("password").isEmpty()) {
                        if (b2 == na.d) {
                            l2.highlightFieldError("password", ua.ALLATORIxDEMO("auth.field.password.short"));
                        } else if (b2 == na.r) {
                            l2.highlightFieldError("password", ua.ALLATORIxDEMO("auth.field.password.long"));
                        } else if (b2 == na.k) {
                            l2.highlightFieldError("password", ua.ALLATORIxDEMO("auth.field.password.chars"));
                        }
                    }
                }
            }
        } else {
            na a2;
            String d2;
            pa g2 = k2.ALLATORIxDEMO(l2.getInputValue("newusername"));
            if (g2 != pa.p) {
                j2 = false;
                if (!l2.getInputValue("newusername").isEmpty()) {
                    if (g2 == pa.d) {
                        l2.highlightFieldError("newusername", ua.ALLATORIxDEMO("auth.field.username.short"));
                    } else if (g2 == pa.r) {
                        l2.highlightFieldError("newusername", ua.ALLATORIxDEMO("auth.field.username.long"));
                    } else if (g2 == pa.k) {
                        l2.highlightFieldError("newusername", ua.ALLATORIxDEMO("auth.field.username.chars"));
                    } else {
                        l2.highlightFieldError("newusername", ua.ALLATORIxDEMO("auth.unknownexc"));
                    }
                }
            }
            if (!(d2 = l2.getInputValue("newusername")).isEmpty() && l2.registred.contains(d2)) {
                j2 = false;
                l2.highlightFieldError("newusername", ua.ALLATORIxDEMO("auth.field.username.taken"));
            }
            if ((a2 = k2.ALLATORIxDEMO(l2.getInputValue("newpassword"))) != na.p) {
                j2 = false;
                if (!l2.getInputValue("newpassword").isEmpty()) {
                    if (a2 == na.d) {
                        l2.highlightFieldError("newpassword", ua.ALLATORIxDEMO("auth.field.password.short"));
                    } else if (a2 == na.r) {
                        l2.highlightFieldError("newpassword", ua.ALLATORIxDEMO("auth.field.password.long"));
                    } else if (a2 == na.k) {
                        l2.highlightFieldError("newpassword", ua.ALLATORIxDEMO("auth.field.password.chars"));
                    }
                }
            }
            if (a2 == na.p && !l2.getInputValue("newpassword").equals(l2.getInputValue("repeatNewpassword"))) {
                j2 = false;
                l2.highlightFieldError("repeatNewpassword", ua.ALLATORIxDEMO("auth.field.password.match"));
            }
        }
        if (j2) {
            l2.toggleRegisterButton(true);
        }
    }

    /*
     * WARNING - void declaration
     */
    private String sendRequestLoginServer(String j2) throws Exception {
        int c2;
        String i2 = urlLogin;
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

    public String translate(String a2) {
        return ua.ALLATORIxDEMO(a2);
    }

    public void processContinue(JSObject e2) {
        ra f2;
        try {
            f2.webView.getEngine().executeScript("function jsObjectToJSON(jsObject) { return JSON.stringify(jsObject); }");
            String c2 = (String)e2.eval("jsObjectToJSON(this);");
            JSONObject b2 = new JSONObject(c2);
            String a2 = (String)b2.get("act");
            f2.toggleInputElements(false);
            f2.clearAllFieldErrors();
            f2.showLoadingAnimation();
            f2.removeFormErrorMessage();
            new Thread(new la(f2, a2, b2)).start();
        } catch (Exception d2) {
            d2.printStackTrace();
            Platform.runLater(() -> {
                ra a2;
                a2.toggleInputElements(true);
                a2.hideLoadingAnimation();
                a2.displayFormErrorMessage(d2.getLocalizedMessage());
            });
        }
    }

    public void startCaptcha(String b2) {
        ra c2;
        c2.toggleInputElements(true);
        c2.hideLoadingAnimation();
        c2.toggleRegisterButton(false);
        try {
            c2.bantime = (int)Long.parseLong(b2);
        } catch (Exception a2) {
            a2.printStackTrace();
        }
    }

    public void removeFormErrorMessage() {
        ra a2;
        a2.webEngine.executeScript("hideFormErrorMessage();");
    }

    public void displayFormErrorMessage(String a2) {
        ra b2;
        b2.conError = false;
        b2.webEngine.executeScript("displayFormErrorMessage('" + xb.ALLATORIxDEMO(ua.ALLATORIxDEMO(a2)) + "');");
        if (a2.equals("Connection refused: connect")) {
            b2.conError = true;
            b2.webEngine.executeScript("document.getElementById('errorContainerButton').style.visibility = 'visible'");
        }
    }

    public void errorInteract() {
        ra a2;
        if (a2.conError) {
            if (aa.e.equals("ru_ru")) {
                m.ALLATORIxDEMO("https://forms.gle/6YidpxtbceW1RvWR8");
            } else {
                m.ALLATORIxDEMO("https://forms.gle/bQWaSTKSatkziATU6");
            }
        }
    }

    @Override
    public void loadedWeb() {
        ra a2;
        super.loadedWeb();
        if (!aa.ALLATORIxDEMO().ALLATORIxDEMO().k.equals("000")) {
            a2.webEngine.executeScript("document.getElementById('username').value = '" + aa.ALLATORIxDEMO().ALLATORIxDEMO().r + "';");
            a2.webEngine.executeScript("document.getElementById('password').value = '" + aa.ALLATORIxDEMO().ALLATORIxDEMO().q + "';");
        }
        a2.removeFormErrorMessage();
        if (a2.kickmsg != null) {
            a2.displayFormErrorMessage(a2.kickmsg);
            a2.kickmsg = null;
        }
        a2.onTyped();
    }

    public void highlightFieldError(String c2, String b2) {
        ra d2;
        String a2 = String.format("highlightFieldError('%s', '%s');", c2, b2);
        d2.webEngine.executeScript(a2);
    }

    public String getInputValue(String c2) {
        ra d2;
        String b2 = "getInputValue('" + c2 + "')";
        Object a2 = d2.webEngine.executeScript(b2);
        return a2 != null ? a2.toString() : "";
    }

    public void clearAllFieldErrors() {
        ra b2;
        String a2 = "clearAllFieldErrors();";
        b2.webEngine.executeScript(a2);
    }

    public void support() {
        if (aa.e.equals("ru_ru")) {
            m.ALLATORIxDEMO("https://forms.gle/UwdoQa45cuGGcJbq5");
        } else {
            m.ALLATORIxDEMO("https://forms.gle/kQADqXP2TTMYkWKg8");
        }
    }

    public boolean isLogin() {
        ra a2;
        return (Boolean)a2.webEngine.executeScript("isLogin()");
    }

    public boolean isLoginCode() {
        ra a2;
        return (Boolean)a2.webEngine.executeScript("isLoginCode()");
    }

    public boolean isRecovery() {
        ra a2;
        return (Boolean)a2.webEngine.executeScript("isRecovery()");
    }

    public boolean isRecoveryCode() {
        ra a2;
        return (Boolean)a2.webEngine.executeScript("isRecoveryCode()");
    }

    public void showLoadingAnimation() {
        ra a2;
        a2.webEngine.executeScript("toggleLoading(true);");
    }

    public void switchToLogin() {
        ra a2;
        a2.webEngine.executeScript("handleSwitchToLogin();");
    }

    public void switchToLoginCode() {
        ra a2;
        a2.webEngine.executeScript("handleSwitchToLoginCode();");
    }

    public void setRecoveryLogin() {
        ra a2;
        a2.webEngine.executeScript("setRecoveryLogin();");
    }

    public void switchToRecovery() {
        ra a2;
        a2.webEngine.executeScript("handleSwitchToRecovery();");
    }

    public void switchToRecoveryCode() {
        ra a2;
        a2.webEngine.executeScript("handleSwitchToRecoveryCode();");
    }

    public void hideLoadingAnimation() {
        ra a2;
        a2.webEngine.executeScript("toggleLoading(false);");
    }

    public void toggleInputElements(boolean a2) {
        ra b2;
        b2.webEngine.executeScript("toggleInputElements(" + a2 + ");");
    }

    public void toggleRegisterButton(boolean a2) {
        ra b2;
        if (a2 && b2.bantime > 0) {
            return;
        }
        b2.webEngine.executeScript("toggleRegisterButton(" + a2 + ");");
    }

    @Override
    public void exit() {
    }
}

