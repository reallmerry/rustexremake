/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javafx.application.Application
 *  javafx.beans.value.ObservableValue
 *  javafx.concurrent.Task
 *  javafx.geometry.Pos
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.control.ProgressBar
 *  javafx.scene.layout.VBox
 *  javafx.stage.Stage
 */
import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class xa
extends Application {
    private ProgressBar ALLATORIxDEMO = new ProgressBar();

    public xa() {
        xa a2;
    }

    public static void main(String[] stringArray) {
        String[] a2;
        System.out.println("\n################################################\n#                                              #\n#        ## #   #    ## ### ### ##  ###        #\n#       # # #   #   # #  #  # # # #  #         #\n#       ### #   #   ###  #  # # ##   #         #\n#       # # ### ### # #  #  ### # # ###        #\n#                                              #\n# Obfuscation by Allatori Obfuscator v8.7 DEMO #\n#                                              #\n#           http://www.allatori.com            #\n#                                              #\n################################################\n");
        xa.launch((String[])a2);
    }

    public void start(Stage d2) {
        xa e2;
        d2.setTitle("Folder Integrity Checker");
        VBox c2 = new VBox(20.0);
        c2.setAlignment(Pos.CENTER);
        c2.getChildren().add((Object)e2.ALLATORIxDEMO);
        Task<String> b2 = e2.ALLATORIxDEMO(new File("C:\\rustexremake\\testserver"));
        e2.ALLATORIxDEMO.progressProperty().bind((ObservableValue)b2.progressProperty());
        b2.setOnSucceeded(a2 -> System.out.println("Hash: " + (String)b2.getValue()));
        b2.setOnFailed(a2 -> System.out.println("An error occurred"));
        new Thread((Runnable)b2).start();
        Scene a3 = new Scene((Parent)c2, 400.0, 300.0);
        d2.setScene(a3);
        d2.show();
    }

    public Task<String> ALLATORIxDEMO(File a2) {
        xa b2;
        return new ya(b2, a2);
    }
}

