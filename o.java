/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javafx.application.Platform
 *  netscape.javascript.JSObject
 */
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.application.Platform;
import netscape.javascript.JSObject;
import org.json.JSONObject;

public class o
extends i {
    public static final String urlService = String.valueOf(aa.z) + "/service";
    private u switchContextTo = null;
    public String twofatoken = "";
    private static String lastpass = "";
    public boolean conError = false;
    public static String lashcodechar = "F";

    public o() {
        o a2;
    }

    public void switchContext(u a2) {
        o b2;
        if (b2.isLoaded) {
            b2.webEngine.executeScript("switchContext('" + a2.name() + "');");
        } else {
            b2.switchContextTo = a2;
        }
    }

    @Override
    public void loadedWeb() {
        o a2;
        super.loadedWeb();
        if (a2.switchContextTo != null) {
            a2.switchContext(a2.switchContextTo);
            a2.switchContextTo = null;
        }
    }

    public void errorInteract() {
        o a2;
        if (a2.conError) {
            if (aa.e.equals("ru_ru")) {
                m.ALLATORIxDEMO("https://forms.gle/6YidpxtbceW1RvWR8");
            } else {
                m.ALLATORIxDEMO("https://forms.gle/bQWaSTKSatkziATU6");
            }
        }
    }

    @Override
    public String getPagePath() {
        return "web/welcome.html";
    }

    public String translate(String a2) {
        return ua.ALLATORIxDEMO(a2);
    }

    public void openBuyLink() {
        if (aa.e.equals("ru_ru")) {
            m.ALLATORIxDEMO("https://forms.gle/bVyaVWx15qZbEBzE6");
        } else {
            m.ALLATORIxDEMO("https://forms.gle/rFbgaqHsgfsh7din8");
        }
    }

    public void processContinue(JSObject k2) {
        o l2;
        try {
            l2.webView.getEngine().executeScript("function jsObjectToJSON(jsObject) { return JSON.stringify(jsObject); }");
            String i2 = (String)k2.eval("jsObjectToJSON(this);");
            Boolean h2 = (Boolean)l2.webEngine.executeScript("isEmailCode();");
            Boolean g2 = (Boolean)l2.webEngine.executeScript("isEnterNewPassword();");
            String f2 = l2.getInputValue("email");
            String e2 = l2.getInputValue("code");
            String d2 = l2.getInputValue("code2");
            String c2 = l2.getInputValue("newpasswordRec");
            JSONObject b2 = new JSONObject(i2);
            String a2 = (String)b2.get("act");
            l2.toggleInputElements(false);
            l2.showLoadingAnimation();
            l2.removeFormErrorMessage();
            new Thread(new l(l2, a2, g2, c2, d2, h2, f2, e2)).start();
        } catch (Exception j2) {
            j2.printStackTrace();
            Platform.runLater(() -> {
                o a2;
                a2.toggleInputElements(true);
                a2.hideLoadingAnimation();
                a2.displayFormErrorMessage(j2.getLocalizedMessage());
            });
        }
    }

    public String getInputValue(String c2) {
        o d2;
        String b2 = "getInputValue('" + c2 + "')";
        Object a2 = d2.webEngine.executeScript(b2);
        return a2 != null ? a2.toString() : "";
    }

    public void hideLoadingAnimation() {
        o a2;
        a2.webEngine.executeScript("toggleLoading(false);");
    }

    public void toggleInputElements(boolean a2) {
        o b2;
        b2.webEngine.executeScript("toggleInputElements(" + a2 + ");");
    }

    public void toggleRegisterButton(boolean a2) {
        o b2;
        b2.webEngine.executeScript("toggleRegisterButton(" + a2 + ");");
    }

    public void removeFormErrorMessage() {
        o a2;
        a2.webEngine.executeScript("hideFormErrorMessage();");
    }

    public void showLoadingAnimation() {
        o a2;
        a2.webEngine.executeScript("toggleLoading(true);");
    }

    public void displayFormErrorMessage(String b2) {
        o c2;
        c2.conError = false;
        if (b2.startsWith("Kicked:")) {
            ra a2 = new ra();
            a2.setKick(b2.replace("Kicked: ", ""));
            a2.init();
            return;
        }
        c2.webEngine.executeScript("displayFormErrorMessage('" + xb.ALLATORIxDEMO(ua.ALLATORIxDEMO(b2)) + "');");
        if (b2.equals("Connection refused: connect")) {
            c2.conError = true;
            c2.webEngine.executeScript("document.getElementById('errorContainerButton').style.visibility = 'visible'");
        }
    }

    public String getNickname() {
        return aa.ALLATORIxDEMO().ALLATORIxDEMO().j();
    }

    public String getToken() {
        return aa.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO();
    }

    public void switchToLogin() {
        o a2;
        if (((Boolean)a2.webEngine.executeScript("isEmailCode();")).booleanValue()) {
            a2.webEngine.executeScript("setEmailCode(false);");
        } else {
            new ra().init();
        }
    }

    public void onTyped() {
        o i2;
        i2.toggleRegisterButton(false);
        i2.clearAllFieldErrors();
        nb h2 = xb.ALLATORIxDEMO();
        boolean g2 = true;
        String f2 = (String)i2.webEngine.executeScript("getCurrentContext();");
        if (f2.equals("plsChangePass") && ((Boolean)i2.webEngine.executeScript("isEnterNewPassword();")).booleanValue()) {
            sa b2;
            na d2 = h2.ALLATORIxDEMO(i2.getInputValue("newpasswordRec"));
            if (d2 != na.p) {
                g2 = false;
                if (!i2.getInputValue("newpasswordRec").isEmpty()) {
                    if (d2 == na.d) {
                        i2.highlightFieldError("newpasswordRec", ua.ALLATORIxDEMO("auth.field.password.short"));
                    } else if (d2 == na.r) {
                        i2.highlightFieldError("newpasswordRec", ua.ALLATORIxDEMO("auth.field.password.long"));
                    } else if (d2 == na.k) {
                        i2.highlightFieldError("newpasswordRec", ua.ALLATORIxDEMO("auth.field.password.chars"));
                    }
                }
            }
            if (d2 == na.p && !i2.getInputValue("newpasswordRec").equals(i2.getInputValue("repeatNewpasswordRec"))) {
                g2 = false;
                i2.highlightFieldError("repeatNewpasswordRec", ua.ALLATORIxDEMO("auth.field.password.match"));
            }
            if ((b2 = h2.ALLATORIxDEMO(i2.getInputValue("code2"), lashcodechar)) != sa.j) {
                g2 = false;
                if (!i2.getInputValue("code2").isEmpty()) {
                    if (b2 == sa.p) {
                        i2.highlightFieldError("code2", ua.ALLATORIxDEMO("auth.field.code.short"));
                    } else if (b2 == sa.d) {
                        i2.highlightFieldError("code2", ua.ALLATORIxDEMO("auth.field.code.long"));
                    } else if (b2 == sa.k) {
                        i2.highlightFieldError("code2", ua.ALLATORIxDEMO("auth.field.code.chars"));
                    } else if (b2 == sa.r) {
                        i2.highlightFieldError("code2", ua.ALLATORIxDEMO("auth.field.code.wrong"));
                    } else {
                        i2.highlightFieldError("code2", ua.ALLATORIxDEMO("auth.unknownexc"));
                    }
                }
            }
        }
        if (f2.equals("plsLinkEmail")) {
            sa a2;
            String e2 = i2.getInputValue("email");
            ia c2 = h2.ALLATORIxDEMO(e2);
            if (c2 != ia.r) {
                g2 = false;
                if (!e2.isEmpty() && c2 == ia.k) {
                    i2.highlightFieldError("email", ua.ALLATORIxDEMO("auth.field.email.invalid_format"));
                }
            }
            if (((Boolean)i2.webEngine.executeScript("isEmailCode();")).booleanValue() && (a2 = h2.ALLATORIxDEMO(i2.getInputValue("code"), lashcodechar)) != sa.j) {
                g2 = false;
                if (!i2.getInputValue("code").isEmpty()) {
                    if (a2 == sa.p) {
                        i2.highlightFieldError("code", ua.ALLATORIxDEMO("auth.field.code.short"));
                    } else if (a2 == sa.d) {
                        i2.highlightFieldError("code", ua.ALLATORIxDEMO("auth.field.code.long"));
                    } else if (a2 == sa.k) {
                        i2.highlightFieldError("code", ua.ALLATORIxDEMO("auth.field.code.chars"));
                    } else if (a2 == sa.r) {
                        i2.highlightFieldError("code", ua.ALLATORIxDEMO("auth.field.code.wrong"));
                    } else {
                        i2.highlightFieldError("code", ua.ALLATORIxDEMO("auth.unknownexc"));
                    }
                }
            }
        }
        if (g2) {
            i2.toggleRegisterButton(true);
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

    public void clearAllFieldErrors() {
        o b2;
        String a2 = "clearAllFieldErrors();";
        b2.webEngine.executeScript(a2);
    }

    public void highlightFieldError(String c2, String b2) {
        o d2;
        String a2 = String.format("highlightFieldError('%s', '%s');", c2, b2);
        d2.webEngine.executeScript(a2);
    }

    @Override
    public void exit() {
    }
}

