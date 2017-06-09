package com.simplyalec.dropzone.screenshot;

import com.simplyalec.dropzone.screenshot.util.createTrayIcon;
import com.simplyalec.dropzone.screenshot.util.dropzoneConnect;
import com.simplyalec.dropzone.screenshot.util.messages;

import java.awt.*;

/**
 * Created by Alec Dusheck on 6/8/2017.
 */
public class DropzoneScreenshot {

    public static Boolean enabled = true;

    public static void main(String[] args) {
        //Startup
        if (!SystemTray.isSupported()) {
            messages.displayMessage("Your machine dose not support the TrayIcon, please make sure you are running Windows.");
            System.exit(0);
        }
        if(!dropzoneConnect.checkCreds("alec", "test")){
            messages.displayMessage("Username/Password combination incorrect. Change it in settings and restart.");
            enabled = false;
        }
        createTrayIcon.create();
    }



}
