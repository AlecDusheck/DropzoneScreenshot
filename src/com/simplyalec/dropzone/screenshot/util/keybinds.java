package com.simplyalec.dropzone.screenshot.util;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Alec Dusheck on 6/9/2017.
 */
public class keybinds {

    public static HashMap<Integer, String> keybinds = new HashMap<Integer, String>();

    public static void load(){

        try{
            //Make sure keybinds exists, if it dosen't, make it.
            File f = new File("keybinds.list");
            if(!f.exists()){
                file.createFile("keybinds.list");
                loadDefaults();
            }

            //Load file
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("keybinds.list"));

            HashMap<Integer, String> keybinds = new HashMap<Integer, String>();

            Object readMap = ois.readObject();
            if(readMap != null && readMap instanceof HashMap) {
                keybinds.putAll((HashMap) readMap);
            }
            ois.close();

            //Make sure the hashmap contains stuff.
            if(keybinds.get("screenShotDisp1") == null){
                loadDefaults();
            }

        }catch (Exception e){
            //If we for some reason cannot access the config.
            messages.displayMessage("An error occurred while reading the config file. Check file permissions.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void loadDefaults(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("keybinds.list"));

            HashMap<Integer, String> keybinds = new HashMap<Integer, String>();
            //TODO fill this out
            keybinds.put(4, "screenShot\\Display0");

            oos.writeObject(keybinds);
            oos.close();
        }catch (Exception e){
            //If we for some reason cannot access the config.
            messages.displayMessage("An error occurred while reading the config file. Check file permissions.");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
