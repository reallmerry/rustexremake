/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javafx.application.Platform
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import javafx.application.Platform;
import org.json.JSONObject;

public class ja {
    public static String j;
    public static String[] p;
    private ea d;
    public boolean r = false;
    public LinkedBlockingQueue<Runnable> k = new LinkedBlockingQueue();
    public List<va> q = new ArrayList<va>();
    public boolean ALLATORIxDEMO = false;

    public ja(ea a2) {
        ja b2;
        b2.d = a2;
    }

    public void ALLATORIxDEMO(Runnable a2) {
        ja b2;
        b2.k.add(a2);
    }

    public void o() {
        ja d2;
        File c2 = new File(j);
        if (!c2.exists()) {
            c2.mkdirs();
        }
        va[] vaArray = va.values();
        int n2 = vaArray.length;
        int n3 = 0;
        while (n3 < n2) {
            va a2 = vaArray[n3];
            a2.j();
            if (a2.q == null) {
                d2.r = true;
            }
            ++n3;
        }
        if (d2.r) {
            Platform.runLater(() -> {
                ja a2;
                System.out.println("Need install");
                a2.d.func.setStartButton(n.DOWNLOAD);
            });
        } else {
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.setStartButton(n.PLAY);
            });
        }
        Platform.runLater(() -> {
            if (aa.y.equals("rustexremake")) {
                ja b2;
                aa.y = "";
                n a2 = b2.d.func.getButtonState();
                if (a2 == n.PLAY) {
                    b2.d.webAction("clickPlay", null);
                }
            }
        });
        while (!Thread.interrupted()) {
            try {
                d2.c();
                Thread.sleep(50L);
            } catch (InterruptedException b2) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void c() {
        ja b2;
        while (!b2.k.isEmpty()) {
            Runnable a2 = b2.k.poll();
            if (a2 == null) continue;
            a2.run();
        }
    }

    public void j(boolean c2) {
        boolean a2;
        ja d2;
        d2.ALLATORIxDEMO = false;
        Platform.runLater(() -> {
            ja a2;
            a2.d.func.setProgress(ua.ALLATORIxDEMO("launcher.update.deleting"), 0);
        });
        File b2 = new File(j);
        if (c2) {
            b2 = b2.getParentFile();
        }
        if (!(a2 = f.ALLATORIxDEMO(b2.getAbsolutePath()))) {
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.displayFormErrorMessage("launcher.update.deletingerror");
                a2.d.func.setProgress("Error", 0);
                a2.d.func.setStartButton(n.ERROR);
            });
            return;
        }
        Platform.runLater(() -> {
            ja a2;
            a2.d.func.setProgress("", -1);
            a2.d.func.showMessage("launcher.update.deletingsuccess");
            a2.d.func.setStartButton(n.DOWNLOAD);
        });
    }

    private boolean j() {
        if (aa.ja) {
            return false;
        }
        String[] h2 = new String[]{"http://mirror1.koptojop.studio:23000/download/launcher/", "http://koptojop.studio/launcher/", "http://5.39.249.147/launcher/", "http://rustexremakehost.site/launcher/"};
        String g2 = "app.jar";
        JSONObject f2 = null;
        JSONObject e2 = null;
        try {
            String b2 = new String(Files.readAllBytes(Paths.get(String.valueOf(g2) + ".json", new String[0])));
            e2 = new JSONObject(b2);
        } catch (IOException c2) {
            c2.printStackTrace();
        }
        String[] stringArray = h2;
        int n2 = h2.length;
        int n3 = 0;
        while (n3 < n2) {
            String d2 = stringArray[n3];
            try {
                f2 = v.ALLATORIxDEMO(String.valueOf(d2) + g2 + ".json");
                if (f2 != null) {
                    break;
                }
            } catch (Exception a2) {
                a2.printStackTrace();
                f2 = null;
            }
            ++n3;
        }
        return f2 != null && e2 != null && !e2.get("hash").equals(f2.get("hash"));
    }

    public void ALLATORIxDEMO(boolean h2) {
        ja i2;
        File g2 = new File(j);
        if (!g2.exists()) {
            g2.mkdirs();
        }
        try {
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.setProgress(ua.ALLATORIxDEMO("launcher.update.checkupdate"), 0);
            });
            Thread.sleep(2000L);
            i2.q = new ArrayList<va>();
            if (!i2.ALLATORIxDEMO) {
                if (!i2.ALLATORIxDEMO()) {
                    System.out.println("Lost json");
                    Platform.runLater(() -> {
                        ja a2;
                        a2.d.func.displayFormErrorMessage("launcher.update.checkerror");
                    });
                    return;
                }
                ArrayList<va> e2 = new ArrayList<va>();
                va[] vaArray = va.values();
                int n2 = vaArray.length;
                int n3 = 0;
                while (n3 < n2) {
                    va c2 = vaArray[n3];
                    if (!c2.ALLATORIxDEMO()) {
                        i2.q.add(c2);
                    } else {
                        e2.add(c2);
                    }
                    ++n3;
                }
                ArrayList d2 = new ArrayList();
                va[] vaArray2 = va.values();
                int n4 = vaArray2.length;
                n2 = 0;
                while (n2 < n4) {
                    va a2 = vaArray2[n2];
                    if (a2.j()) {
                        e2.remove((Object)a2);
                    }
                    ++n2;
                }
                List<va> b2 = i2.ALLATORIxDEMO(e2);
                i2.q.addAll(b2);
            }
            i2.ALLATORIxDEMO = false;
            if (i2.q.size() > 0) {
                Thread.sleep(1000L);
                Platform.runLater(() -> {
                    ja a2;
                    a2.d.func.setProgress(ua.ALLATORIxDEMO("launcher.update.updatefound"), 0);
                });
                if (i2.r) {
                    i2.h();
                } else if (h2) {
                    Thread.sleep(1000L);
                    i2.h();
                } else {
                    Platform.runLater(() -> {
                        ja a2;
                        a2.d.func.setStartButton(n.UPDATE);
                    });
                }
            } else {
                if (i2.j()) {
                    Platform.runLater(() -> {
                        ja a2;
                        a2.d.func.displayFormErrorMessage(ua.ALLATORIxDEMO("launcher.foundupdate"));
                        a2.d.func.setStartButton(n.PLAY);
                    });
                    return;
                }
                Platform.runLater(() -> {
                    ja a2;
                    a2.d.func.setStartButton(n.PLAY);
                    a2.d.startGame();
                });
            }
        } catch (Exception f2) {
            f2.printStackTrace();
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.displayFormErrorMessage("launcher.update.checkerror");
            });
        }
    }

    public void h() {
        ja n2;
        File m2 = new File(j);
        if (!m2.exists()) {
            m2.mkdirs();
        }
        try {
            int k2 = n2.q.size();
            int j2 = 0;
            for (va g2 : n2.q) {
                int f2 = ++j2;
                Consumer<Integer> e2 = a2 -> {
                    ja b2;
                    Platform.runLater(() -> {
                        ja a2;
                        a2.d.func.setProgress(String.valueOf(ua.ALLATORIxDEMO("launcher.update.file")) + " " + va2.p + " " + f2 + "/" + k2, (int)a2);
                    });
                };
                v d2 = new v(e2);
                int c2 = 0;
                String[] stringArray = p;
                int n3 = p.length;
                int n4 = 0;
                while (n4 < n3) {
                    String b2 = stringArray[n4];
                    ++c2;
                    try {
                        d2.ALLATORIxDEMO(String.valueOf(b2) + g2.p, String.valueOf(j) + g2.p);
                        break;
                    } catch (Exception a3) {
                        a3.printStackTrace();
                        if (c2 >= b2.length()) {
                            a3.printStackTrace();
                            Platform.runLater(() -> {
                                ja a2;
                                a2.d.func.displayFormErrorMessage("launcher.update.fileerror");
                                a2.d.func.setProgress("Error", 0);
                                a2.d.func.setStartButton(n.ERROR);
                            });
                            return;
                        }
                        ++n4;
                    }
                }
                Thread.sleep(1000L);
            }
            Thread.sleep(1000L);
            if (n2.ALLATORIxDEMO(n2.q).size() > 0) {
                Platform.runLater(() -> {
                    ja a2;
                    a2.d.func.displayFormErrorMessage("launcher.update.filecorrupted");
                    a2.d.func.setProgress("Error", 0);
                    a2.d.func.setStartButton(n.ERROR);
                });
                return;
            }
            Thread.sleep(1000L);
            va[] vaArray = va.values();
            int n5 = vaArray.length;
            int n6 = 0;
            while (n6 < n5) {
                va h2 = vaArray[n6];
                if (h2.j() && n2.q.contains((Object)h2) && !n2.ALLATORIxDEMO(h2)) {
                    return;
                }
                ++n6;
            }
            System.out.println("updte cur meta");
            for (va i2 : n2.q) {
                i2.z();
            }
            n2.q.clear();
            Thread.sleep(1000L);
            System.out.println("success update");
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.setProgress("", -1);
                a2.d.func.showMessage("launcher.update.success");
                a2.ALLATORIxDEMO = true;
                a2.d.func.setStartButton(n.PLAY);
            });
        } catch (Exception l2) {
            l2.printStackTrace();
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.displayFormErrorMessage("launcher.update.unknown");
                a2.d.func.setProgress("Error", 0);
                a2.d.func.setStartButton(n.ERROR);
            });
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean ALLATORIxDEMO(va k2) {
        ja l2;
        try {
            File i2 = new File(String.valueOf(j) + k2.p + ".json");
            Thread.sleep(1000L);
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.setProgress(String.valueOf(ua.ALLATORIxDEMO("launcher.update.deletingold")) + " " + k2.ALLATORIxDEMO(), 0);
            });
            boolean h2 = f.ALLATORIxDEMO(String.valueOf(j) + k2.ALLATORIxDEMO());
            if (!h2) {
                i2.delete();
                Platform.runLater(() -> {
                    ja a2;
                    a2.d.func.displayFormErrorMessage(String.valueOf(ua.ALLATORIxDEMO("launcher.update.deletingerror")) + " " + k2.ALLATORIxDEMO());
                    a2.d.func.setProgress("Error deleting " + k2.ALLATORIxDEMO(), 0);
                    a2.d.func.setStartButton(n.ERROR);
                });
                return false;
            }
            Thread.sleep(1000L);
            try {
                Consumer<Integer> e2 = a2 -> {
                    ja b2;
                    Platform.runLater(() -> {
                        ja a2;
                        a2.d.func.setProgress(String.valueOf(ua.ALLATORIxDEMO("launcher.update.unzip")) + " " + va2.p, (int)a2);
                    });
                };
                a c2 = new a(e2);
                System.out.println("Start unzip " + k2.p);
                c2.ALLATORIxDEMO(String.valueOf(j) + k2.p, String.valueOf(j) + k2.ALLATORIxDEMO());
            } catch (Exception d2) {
                i2.delete();
                d2.printStackTrace();
                Platform.runLater(() -> {
                    ja a2;
                    a2.d.func.displayFormErrorMessage(String.valueOf(ua.ALLATORIxDEMO("launcher.update.unziperror")) + " " + va2.p);
                    a2.d.func.setProgress("Error unzip " + va2.p, 0);
                    a2.d.func.setStartButton(n.ERROR);
                });
                return false;
            }
            Thread.sleep(1000L);
            try {
                Consumer<Integer> g2 = a2 -> {
                    ja b2;
                    Platform.runLater(() -> {
                        ja a2;
                        a2.d.func.setProgress(String.valueOf(ua.ALLATORIxDEMO("launcher.update.checkfiles")) + " " + k2.ALLATORIxDEMO(), (int)a2);
                    });
                };
                g b2 = new g(g2);
                System.out.println("Check folder " + k2.ALLATORIxDEMO());
                String a3 = b2.j(new File(String.valueOf(j) + k2.ALLATORIxDEMO()));
                if (!a3.equals(k2.k.get("folderHash"))) {
                    i2.delete();
                    Platform.runLater(() -> {
                        ja a2;
                        a2.d.func.displayFormErrorMessage(String.valueOf(ua.ALLATORIxDEMO("launcher.update.filescorrupted")) + " " + k2.ALLATORIxDEMO());
                        a2.d.func.setProgress("Error files corrupted " + k2.ALLATORIxDEMO(), 0);
                        a2.d.func.setStartButton(n.ERROR);
                    });
                    return false;
                }
            } catch (Exception f2) {
                i2.delete();
                f2.printStackTrace();
                Platform.runLater(() -> {
                    ja a2;
                    a2.d.func.displayFormErrorMessage(String.valueOf(ua.ALLATORIxDEMO("launcher.update.filescorrupted")) + " " + k2.ALLATORIxDEMO());
                    a2.d.func.setProgress("Error files corrupted " + k2.ALLATORIxDEMO(), 0);
                    a2.d.func.setStartButton(n.ERROR);
                });
                return false;
            }
            Thread.sleep(1000L);
            System.out.println("del zip " + k2.p);
            new File(String.valueOf(j) + k2.p).delete();
            return true;
        } catch (Exception j2) {
            j2.printStackTrace();
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.displayFormErrorMessage("launcher.update.unknown");
                a2.d.func.setProgress("Error while unzip", 0);
                a2.d.func.setStartButton(n.ERROR);
            });
            return false;
        }
    }

    public boolean ALLATORIxDEMO(va h2, List<va> g2) {
        ja i2;
        File f2 = new File(String.valueOf(j) + h2.p + ".json");
        try {
            Consumer<Integer> c2 = a2 -> {
                ja b2;
                Platform.runLater(() -> {
                    ja a2;
                    a2.d.func.setProgress(String.valueOf(ua.ALLATORIxDEMO("launcher.update.checkfiles")) + " " + h2.ALLATORIxDEMO(), (int)a2);
                });
            };
            g b2 = new g(c2);
            String a3 = b2.j(new File(String.valueOf(j) + h2.ALLATORIxDEMO()));
            if (!a3.equals(h2.k.get("folderHash"))) {
                f2.delete();
            }
        } catch (Exception e2) {
            f2.delete();
        }
        if (!f2.exists()) {
            boolean d2 = f.ALLATORIxDEMO(String.valueOf(j) + h2.ALLATORIxDEMO());
            if (!d2) {
                f2.delete();
                Platform.runLater(() -> {
                    ja a2;
                    a2.d.func.displayFormErrorMessage(String.valueOf(ua.ALLATORIxDEMO("launcher.update.deletingerror")) + " " + h2.ALLATORIxDEMO());
                    a2.d.func.setProgress("Error", 0);
                    a2.d.func.setStartButton(n.ERROR);
                });
                return false;
            }
            i2.q.add(h2);
            g2.add(h2);
        }
        return true;
    }

    public void l() {
        ja g2;
        g2.q = new ArrayList<va>();
        if (!g2.ALLATORIxDEMO()) {
            System.out.println("Lost json");
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.displayFormErrorMessage("launcher.update.checkerror");
            });
            return;
        }
        ArrayList<va> f2 = new ArrayList<va>();
        va[] vaArray = va.values();
        int n2 = vaArray.length;
        int n3 = 0;
        while (n3 < n2) {
            va d2 = vaArray[n3];
            if (!d2.j()) {
                f2.add(d2);
            }
            ++n3;
        }
        List<va> e2 = g2.ALLATORIxDEMO(f2);
        g2.q.addAll(e2);
        va[] vaArray2 = va.values();
        int n4 = vaArray2.length;
        n2 = 0;
        while (n2 < n4) {
            va b2 = vaArray2[n2];
            if (b2.j() && g2.ALLATORIxDEMO(b2, e2)) {
                return;
            }
            ++n2;
        }
        if (e2.size() > 0) {
            for (va c2 : e2) {
                File a2 = new File(String.valueOf(j) + c2.p + ".json");
                a2.delete();
            }
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.showMessage("launcher.update.checkerror");
                a2.d.func.setStartButton(n.UPDATE);
                a2.d.func.setProgress("", -1);
            });
        } else {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException interruptedException) {
                // empty catch block
            }
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.showMessage("launcher.update.checksuccess");
                a2.ALLATORIxDEMO = true;
                a2.d.func.setProgress("", -1);
                a2.d.func.setStartButton(n.PLAY);
            });
        }
    }

    private List<va> ALLATORIxDEMO(List<va> l2) {
        ja m2;
        ArrayList<va> k2 = new ArrayList<va>();
        ArrayList<File> j2 = new ArrayList<File>();
        ArrayList<String> i2 = new ArrayList<String>();
        ArrayList<va> h2 = new ArrayList<va>();
        for (va f2 : l2) {
            h2.add(f2);
            j2.add(new File(String.valueOf(j) + f2.p));
            i2.add(f2.k.getString("hash"));
        }
        Consumer<Integer> g2 = a2 -> {
            ja b2;
            Platform.runLater(() -> {
                ja a2;
                a2.d.func.setProgress(ua.ALLATORIxDEMO("launcher.update.checkintegrity"), (int)a2);
            });
        };
        g e2 = new g(g2);
        try {
            List<File> c2 = e2.ALLATORIxDEMO(j2, i2);
            if (c2.size() > 0) {
                for (File b2 : c2) {
                    int a3 = j2.indexOf(b2);
                    k2.add((va)((Object)h2.get(a3)));
                }
                return k2;
            }
        } catch (Exception d2) {
            d2.printStackTrace();
            return l2;
        }
        return k2;
    }

    private boolean ALLATORIxDEMO() {
        va[] vaArray = va.values();
        int n2 = vaArray.length;
        int n3 = 0;
        while (n3 < n2) {
            va a2 = vaArray[n3];
            a2.ALLATORIxDEMO();
            if (a2.k == null) {
                return false;
            }
            ++n3;
        }
        return true;
    }
}

