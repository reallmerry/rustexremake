/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javafx.scene.web.WebEngine
 *  javafx.scene.web.WebView
 */
import java.io.File;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public abstract class i {
    public WebView webView;
    public WebEngine webEngine;
    public boolean isLoaded = false;

    public i() {
        i a2;
    }

    public abstract String getPagePath();

    public i init() {
        i c2;
        if (aa.g != null) {
            aa.g.dispose();
        }
        aa.g = c2;
        File b2 = new File(c2.getPagePath());
        String a2 = b2.toURI().toString();
        c2.isLoaded = false;
        c2.webView = aa.ALLATORIxDEMO().ALLATORIxDEMO();
        c2.webEngine = c2.webView.getEngine();
        c2.webEngine.load(a2);
        return c2;
    }

    public void dispose() {
    }

    public void loadedWeb() {
        i a2;
        a2.isLoaded = true;
        aa.ALLATORIxDEMO().ALLATORIxDEMO(a2.webView);
    }

    public void exit() {
    }
}

