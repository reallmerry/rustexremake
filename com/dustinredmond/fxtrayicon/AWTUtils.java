/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javafx.application.Platform
 *  javafx.event.ActionEvent
 *  javafx.event.Event
 *  javafx.scene.control.MenuItem
 */
package com.dustinredmond.fxtrayicon;

import java.awt.MenuItem;
import java.util.StringJoiner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;

public class AWTUtils {
    protected static MenuItem convertFromJavaFX(javafx.scene.control.MenuItem fxItem) {
        String errors;
        MenuItem awtItem = new MenuItem(fxItem.getText());
        StringJoiner sj = new StringJoiner(",");
        if (fxItem.getGraphic() != null) {
            sj.add("setGraphic()");
        }
        if (fxItem.getAccelerator() != null) {
            sj.add("setAccelerator()");
        }
        if (fxItem.getCssMetaData().size() > 0) {
            sj.add("getCssMetaData().add()");
        }
        if (fxItem.getOnMenuValidation() != null) {
            sj.add("setOnMenuValidation()");
        }
        if (fxItem.getStyle() != null) {
            sj.add("setStyle()");
        }
        if (!(errors = sj.toString()).isEmpty()) {
            throw new UnsupportedOperationException(String.format("The following methods were called on the passed JavaFX MenuItem (%s), these methods are notsupported by FXTrayIcon.", errors));
        }
        if (fxItem.getOnAction() != null) {
            awtItem.addActionListener(e2 -> Platform.runLater(() -> fxItem.getOnAction().handle((Event)new ActionEvent())));
        }
        awtItem.setEnabled(!fxItem.isDisable());
        return awtItem;
    }
}

