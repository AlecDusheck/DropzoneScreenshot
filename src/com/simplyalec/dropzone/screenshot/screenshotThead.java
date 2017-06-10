package com.simplyalec.dropzone.screenshot;

import com.simplyalec.dropzone.screenshot.util.takeScreenshot;

import java.awt.*;

/**
 * Created by Alec Dusheck on 6/10/2017.
 */
public class screenshotThead extends Thread{
    public screenshotThead(GraphicsDevice display){
        try {
            currentThread().sleep(1000);
            takeScreenshot.take(display);
            stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public screenshotThead(){
        try {
            currentThread().sleep(1000);
            takeScreenshot.takeOfAll();
            stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
