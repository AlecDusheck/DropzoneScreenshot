package com.simplyalec.dropzone.screenshot.util;

import java.io.*;

/**
 * Created by Alec Dusheck on 6/9/2017.
 */
public class file {

    public static void createFile(String name){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(name), "utf-8"));
            writer.write("");
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (Exception e) {
                messages.displayMessage("An internal error occurred. (" + e.toString() + ").");
                System.exit(0);}
        }
    }
}
