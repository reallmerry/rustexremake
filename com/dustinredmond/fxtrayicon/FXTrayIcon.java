/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  javafx.application.Platform
 *  javafx.event.ActionEvent
 *  javafx.event.Event
 *  javafx.event.EventHandler
 *  javafx.scene.control.Menu
 *  javafx.scene.control.MenuItem
 *  javafx.stage.Stage
 */
package com.dustinredmond.fxtrayicon;

import com.dustinredmond.fxtrayicon.AWTUtils;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FXTrayIcon {
    private final SystemTray tray;
    private Stage parentStage;
    private String appTitle;
    private final TrayIcon trayIcon;
    private boolean showing;
    private final PopupMenu popupMenu = new PopupMenu();
    private boolean addExitMenuItem = true;
    private final ActionListener stageShowListener = e2 -> {
        if (this.parentStage != null) {
            Platform.runLater(() -> ((Stage)this.parentStage).show());
        }
    };

    public FXTrayIcon(Stage parentStage, URL iconImagePath) {
        if (!SystemTray.isSupported()) {
            throw new UnsupportedOperationException("SystemTray icons are not supported by the current desktop environment.");
        }
        this.tray = SystemTray.getSystemTray();
        Platform.setImplicitExit((boolean)false);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException exception) {
            // empty catch block
        }
        try {
            Image iconImage = ImageIO.read(iconImagePath).getScaledInstance(16, 16, 4);
            this.parentStage = parentStage;
            this.trayIcon = new TrayIcon(iconImage, parentStage.getTitle(), this.popupMenu);
        } catch (IOException e3) {
            throw new IllegalStateException("Unable to read the Image at the provided path.");
        }
    }

    public void show() {
        SwingUtilities.invokeLater(() -> {
            try {
                this.tray.add(this.trayIcon);
                this.showing = true;
                String miTitle = this.appTitle != null ? this.appTitle : (this.parentStage == null || this.parentStage.getTitle() == null || this.parentStage.getTitle().isEmpty() ? "Show application" : this.parentStage.getTitle());
                java.awt.MenuItem miStage = new java.awt.MenuItem(miTitle);
                miStage.setFont(Font.decode(null).deriveFont(1));
                miStage.addActionListener(e2 -> Platform.runLater(() -> {
                    if (this.parentStage != null) {
                        this.parentStage.show();
                    }
                }));
                this.popupMenu.add(miStage);
                if (this.addExitMenuItem) {
                    java.awt.MenuItem miExit = new java.awt.MenuItem("Exit program");
                    miExit.addActionListener(e2 -> {
                        this.tray.remove(this.trayIcon);
                        Platform.exit();
                    });
                    this.popupMenu.add(miExit);
                }
                this.trayIcon.addActionListener(this.stageShowListener);
            } catch (AWTException e3) {
                throw new IllegalStateException("Unable to add TrayIcon", e3);
            }
        });
    }

    public void setOnAction(EventHandler<ActionEvent> e2) {
        this.trayIcon.removeActionListener(this.stageShowListener);
        this.trayIcon.addActionListener(al -> Platform.runLater(() -> e2.handle((Event)new ActionEvent())));
    }

    public void setOnClick(EventHandler<ActionEvent> e2) {
        if (this.trayIcon.getMouseListeners().length >= 1) {
            this.trayIcon.removeMouseListener(this.trayIcon.getMouseListeners()[0]);
        }
        this.trayIcon.addMouseListener(this.getPrimaryClickListener(e2));
    }

    public void showMinimal() {
        try {
            this.tray.add(this.trayIcon);
            this.showing = true;
            this.trayIcon.addActionListener(this.stageShowListener);
        } catch (AWTException e2) {
            throw new IllegalStateException("Unable to add TrayIcon", e2);
        }
    }

    public void addExitItem(boolean addExitMenuItem) {
        this.addExitMenuItem = addExitMenuItem;
    }

    public void removeMenuItem(int index) {
        EventQueue.invokeLater(() -> this.popupMenu.remove(index));
    }

    public void removeMenuItem(MenuItem fxMenuItem) {
        EventQueue.invokeLater(() -> {
            java.awt.MenuItem toBeRemoved = null;
            for (int i2 = 0; i2 < this.popupMenu.getItemCount(); ++i2) {
                java.awt.MenuItem awtItem = this.popupMenu.getItem(i2);
                if (!awtItem.getLabel().equals(fxMenuItem.getText()) && !awtItem.getName().equals(fxMenuItem.getText())) continue;
                toBeRemoved = awtItem;
            }
            if (toBeRemoved != null) {
                this.popupMenu.remove(toBeRemoved);
            }
        });
    }

    public void addSeparator() {
        EventQueue.invokeLater(this.popupMenu::addSeparator);
    }

    public void insertSeparator(int index) {
        EventQueue.invokeLater(() -> this.popupMenu.insertSeparator(index));
    }

    public void addMenuItem(MenuItem menuItem) {
        if (menuItem instanceof Menu) {
            this.addMenu((Menu)menuItem);
            return;
        }
        if (!this.isUnique(menuItem)) {
            throw new UnsupportedOperationException("Menu Item labels must be unique.");
        }
        EventQueue.invokeLater(() -> this.popupMenu.add(AWTUtils.convertFromJavaFX(menuItem)));
    }

    private void addMenu(Menu menu) {
        EventQueue.invokeLater(() -> {
            java.awt.Menu awtMenu = new java.awt.Menu(menu.getText());
            menu.getItems().forEach(subItem -> awtMenu.add(AWTUtils.convertFromJavaFX(subItem)));
            this.popupMenu.add(awtMenu);
        });
    }

    public java.awt.MenuItem getMenuItem(int index) {
        return this.popupMenu.getItem(index);
    }

    public void setTrayIconTooltip(String tooltip) {
        EventQueue.invokeLater(() -> this.trayIcon.setToolTip(tooltip));
    }

    public void setApplicationTitle(String title) {
        this.appTitle = title;
    }

    public void hide() {
        EventQueue.invokeLater(() -> {
            this.tray.remove(this.trayIcon);
            this.showing = false;
            Platform.setImplicitExit((boolean)true);
        });
    }

    public boolean isShowing() {
        return this.showing;
    }

    private boolean isUnique(MenuItem fxItem) {
        if (this.popupMenu.getItemCount() == 0) {
            return true;
        }
        for (int i2 = 0; i2 < this.popupMenu.getItemCount(); ++i2) {
            if (!this.popupMenu.getItem(i2).getName().equals(fxItem.getText())) continue;
            return false;
        }
        return true;
    }

    private MouseListener getPrimaryClickListener(final EventHandler<ActionEvent> e2) {
        return new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
                Platform.runLater(() -> e2.handle((Event)new ActionEvent()));
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        };
    }

    public void showInfoMessage(String caption, String message) {
        this.trayIcon.displayMessage(caption, message, TrayIcon.MessageType.INFO);
    }

    public void showInfoMessage(String message) {
        this.showInfoMessage(null, message);
    }

    public void showWarningMessage(String caption, String message) {
        this.trayIcon.displayMessage(caption, message, TrayIcon.MessageType.WARNING);
    }

    public void showWarningMessage(String message) {
        this.showWarningMessage(null, message);
    }

    public void showErrorMessage(String caption, String message) {
        this.trayIcon.displayMessage(caption, message, TrayIcon.MessageType.ERROR);
    }

    public void showErrorMessage(String message) {
        this.showErrorMessage(null, message);
    }

    public void showMessage(String caption, String message) {
        this.trayIcon.displayMessage(caption, message, TrayIcon.MessageType.NONE);
    }

    public void showMessage(String message) {
        this.showMessage(null, message);
    }

    public static boolean isSupported() {
        return Desktop.isDesktopSupported() && SystemTray.isSupported();
    }
}

