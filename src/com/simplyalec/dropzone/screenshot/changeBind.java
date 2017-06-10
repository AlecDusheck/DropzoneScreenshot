package com.simplyalec.dropzone.screenshot;

import javax.swing.*;

/**
 * Created by Alec Dusheck on 6/9/2017.
 */
public class changeBind extends JFrame{
    private String bindToChange = null;

    public changeBind(String bindToChange){
        super("Change bind... (" + bindToChange + ").");
        this.bindToChange = bindToChange;
    }
}
