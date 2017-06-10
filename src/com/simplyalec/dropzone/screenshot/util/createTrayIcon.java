package com.simplyalec.dropzone.screenshot.util;

import com.simplyalec.dropzone.screenshot.DropzoneScreenshot;
import com.simplyalec.dropzone.screenshot.changeBind;
import com.simplyalec.dropzone.screenshot.screenshotThead;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alec Dusheck on 6/8/2017.
 */
public class createTrayIcon {
    public static void create() {
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(getTrayIcon());
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        MenuItem info = new MenuItem("Dropzone v0.1");
        MenuItem takeOfAllDisplays = new MenuItem("Take screenshot (All displays)");
        MenuItem changeHotKeyAllDisplays = new MenuItem("Change hotkey | Take screenshot of all displays");
        Menu changeHotkey = new Menu("Change hotkey");
        MenuItem changeLogin = new MenuItem("Change login");
        MenuItem exit = new MenuItem("Exit");

        ArrayList<MenuItem> listOfMons = new ArrayList<MenuItem>();

        HashMap<MenuItem, GraphicsDevice> itemConnectedToDisplay = new HashMap<MenuItem, GraphicsDevice>();

        //Create takescreenShots
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (GraphicsDevice screens : ge.getScreenDevices()) {
            MenuItem scr = new MenuItem("Take screenshot (" + screens.getIDstring() + ")");
            listOfMons.add(scr);
            itemConnectedToDisplay.put(scr, screens);
        }

        //Create menu items for hotkey.
        for (GraphicsDevice screens : ge.getScreenDevices()) {
            MenuItem scr = new MenuItem("Change hotkey | Take screenshot (" + screens.getIDstring() + ")");
            changeHotkey.add(scr);
            scr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeBind bindGUI = new changeBind("screenShot" + screens.getIDstring());
                }
            });
        }

        //Change settings
        info.setEnabled(false);

        if (!DropzoneScreenshot.enabled) {
            takeOfAllDisplays.setEnabled(false);
            takeOfAllDisplays.setLabel(takeOfAllDisplays.getLabel() + " (Unable to connect)");
            for (MenuItem mons : listOfMons) {
                mons.setEnabled(false);
                mons.setLabel(mons.getLabel() + " (Unable to connect)");
            }
        }

        //Add components to pop-up menu
        popup.add(info);
        popup.addSeparator();
        for (MenuItem mons : listOfMons) {
            popup.add(mons);
        }
        popup.add(takeOfAllDisplays);
        popup.addSeparator();
        changeHotkey.add(changeHotKeyAllDisplays);
        popup.add(changeHotkey);
        popup.addSeparator();
        popup.add(changeLogin);
        popup.add(exit);

        trayIcon.setPopupMenu(popup);


        //Create click listeners
        for (MenuItem mons : listOfMons) {
            mons.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //takeScreenshot.take(itemConnectedToDisplay.get(mons));
                    //messages.displayMessage("Screenshot taken of display " + itemConnectedToDisplay.get(mons).getIDstring() + "!");
                    screenshotThead st = new screenshotThead(itemConnectedToDisplay.get(mons));
                }
            });
        }
        takeOfAllDisplays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //takeScreenshot.takeOfAll();
                //messages.displayMessage("Screenshot taken of all displays!");
                screenshotThead st = new screenshotThead();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        changeHotKeyAllDisplays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBind bindGUI = new changeBind("screenShotAllDisp");
            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }

    public static Image getTrayIcon() {
        try {
            File file = new File("src/dropzone_trayicon.png");
            Image img = ImageIO.read(file);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
