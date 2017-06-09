package com.simplyalec.dropzone.screenshot.util;

import javax.swing.*;

/**
 * Created by Alec Dusheck on 6/8/2017.
 */
public class messages {
    public static void displayMessage(String message){
        //Create window
        JFrame win = new JFrame();
        JOptionPane.showMessageDialog(win, message);

    }
}
