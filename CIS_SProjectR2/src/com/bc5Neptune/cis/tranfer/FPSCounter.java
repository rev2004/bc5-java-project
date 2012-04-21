/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.tranfer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HuynhTanPhu
 */
public class FPSCounter {
    /*end milisecond */

    public long end = 0;
    /* frame per second */
    public int FPS = 100;
    /* start miliseconde */
    public long start = 0;
    /* current frame */
    public long currentFPS = 0;
    /* 1000 = 1s, may be change */
    public static final int FramePer = 1000;

    public void BeginCount() {
        currentFPS++;
    }

    public FPSCounter() {
        start = System.currentTimeMillis();
    }

    public void setFPS(int fps) {
        this.FPS = fps;
    }

    public void EndCount() {
        end = System.currentTimeMillis();
        if (currentFPS > FPS) {
            if (end - start < FramePer) {
                try {
                    Thread.sleep(FramePer - (end - start));
                } catch (InterruptedException ex) {
                    Logger.getLogger(FPSCounter.class.getName()).log(Level.SEVERE, null, ex);
                }
                start = System.currentTimeMillis();
            }
            currentFPS = 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FPSCounter obj = new FPSCounter();
        while (true) {
            obj.BeginCount();
            System.out.println("Debug");
            obj.EndCount();
        }

    }
}