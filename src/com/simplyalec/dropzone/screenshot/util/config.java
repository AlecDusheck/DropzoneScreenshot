package com.simplyalec.dropzone.screenshot.util;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Alec Dusheck on 6/9/2017.
 */
public class config {

    public static String username = "ds";
    public static String password = "dsd";

    public static HashMap<Integer, String> keybinds = new HashMap<Integer, String>();


    public static void load() {

        //We define these later.
        Properties prop = new Properties();
        InputStream input = null;

        try {
            //Make sure config exists, if it dosen't, make it.
            File f = new File("config.properties");
            if(!f.exists()){
                file.createFile("config.properties");
            }

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