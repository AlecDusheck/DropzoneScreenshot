package com.simplyalec.dropzone.screenshot;

import com.simplyalec.dropzone.screenshot.util.*;

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

        //Load config
        //config.load();

        //Make sure the username and password is filled out.
        if(config.username == null | config.password == null){
            messages.displayMessage("Please enter your Dropzone username and password in the settings, then restart Dropzone Screenshot.");
            //enabled = false;
        }

        //Check to make sure the password and username works.
        if (!dropzoneConnect.checkCreds(config.password, config.password)) {
            messages.displayMessage("Username/Password combination incorrect. Change it in settings and restart.");
            //enabled = false;
        }

        //Load keybinds
        keybinds.load();

        //Create trayicon.
        createTrayIcon.create();
    }
}

