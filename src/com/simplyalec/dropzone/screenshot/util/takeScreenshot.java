package com.simplyalec.dropzone.screenshot.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

/**
 * Created by Alec Dusheck on 6/8/2017.
 */
public class takeScreenshot {
    public static void take(GraphicsDevice display) {
        try {
            //Get screen bounds
            Rectangle screenBounds = display.getDefaultConfiguration().getBounds();

            //Define robot
            Robot robot = new Robot();

            //Create image using robot and the screenbounds.
            BufferedImage screenShot = robot.createScreenCapture(screenBounds);

            //Create file name.
            String fileName = "tempFile" + UUID.randomUUID() + ".png";

            //Save image.
            ImageIO.write(screenShot, "png", new File(fileName));

            File f = new File(fileName);

            //Upload image.
            dropzoneConnect.uploadFile(f);

            //Delete file.
            f.delete();
        } catch (Exception e) {
            messages.displayMessage("An error occurred while taking screenshot. (" + e.toString() + ").");
            e.printStackTrace();
            return;
        }
    }

    public static void takeOfAll() {
        try {

            //Define graphics env
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            //Get screens
            GraphicsDevice[] screens = ge.getScreenDevices();

            //Get all the screenbounds, and combine them.
            Rectangle allScreenBounds = new Rectangle();
            for (GraphicsDevice screen : screens) {
                Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();

                allScreenBounds.width += screenBounds.width;
                allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
            }

            //Define robot
            Robot robot = new Robot();

            //Create image using robot and the screenbounds.
            BufferedImage screenShot = robot.createScreenCapture(allScreenBounds);

            //Create file name.
            String fileName = "tempFile" + UUID.randomUUID() + ".png";

            //Save image.
            ImageIO.write(screenShot, "png", new File(fileName));

            //Upload image.
            dropzoneConnect.uploadFile(new File(fileName));

            //Delete file.
            File f = new File(fileName);
            f.delete();

            //TODO upload
        } catch (Exception e) {
            messages.displayMessage("An error occurred while taking screenshot. (" + e.toString() + ").");
            e.printStackTrace();
            return;
        }
    }
}
