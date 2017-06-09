package com.simplyalec.dropzone.screenshot.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Alec Dusheck on 6/9/2017.
 */
public class config {

    public static String username = null;
    public static String password = null;

    public static HashMap<Integer, String> keybinds = new HashMap<Integer, String>();


    public void load() {

        //We define these later.
        Properties prop = new Properties();
        InputStream input = null;

        try {
            //Define inputsteam.
            input = new FileInputStream("config.properties");

            //Load properties
            prop.load(input);

            //Get properties and set them as global variables.
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch (IOException e) {
            //If we for some reason cannot access the config.
            messages.displayMessage("An error occurred while reading the config file. Check file permissions.");
            System.exit(0);
        } finally {
            //Close stream.
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    //If we for some reason cannot access the config.
                    messages.displayMessage("An internal error occurred. (" + e.toString() + ").");
                    System.exit(0);
                }
            }
        }
    }
}