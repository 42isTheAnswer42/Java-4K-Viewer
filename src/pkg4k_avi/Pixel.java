/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4k_avi;

/**
 *
 * @author SilberPfeil
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Pixel extends Canvas {

    private int WIDTH;
    private int HEIGHT;
    public int screen;
    int pX, pY;
    private static final Random random = new Random();
    public JFrame frame;
    public Thread t;
    int size;

    public Pixel(int screen, Thread t) {
        this.screen = screen;
        this.t = t;

    }

    public void getScreen() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        DisplayMode mode = gs[this.screen].getDisplayMode();
        // System.out.println(gs);
        System.out.println("refresh frame values");

        WIDTH = mode.getWidth();
        HEIGHT = mode.getHeight();
        System.out.println(WIDTH + "Breite");
        System.out.println(HEIGHT + "höhe");

    }

    public static void showOnScreen(int screen, final JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

        if (screen > -1 && screen < gs.length) {
            gs[screen].setFullScreenWindow(frame);
        } else if (gs.length > 0) {
            gs[0].setFullScreenWindow(frame);
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

    @Override
    public void paint(Graphics g) {

        Color bl = new Color(0, 0, 0);
        this.setBackground(bl);
        Color xc = randomColor();

        this.getScreen();

        super.paint(g);
        Color bl1 = new Color(0, 0, 0);
        Font s = g.getFont();

        g.setColor(xc);
        String setting = "Pixel set at X-Position:" + this.pX + ", Y-Position:" + this.pY + " with a Size of:" + this.size + " x " + this.size + ". Values of RGB-Color are chosen by random.";
        g.fillRect(this.pX, this.pY, this.size, this.size);
        g.drawString(setting, 20, 50);
        g.setColor(xc);

        this.frame.setFocusable(true);

        this.frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("You pressed ESCAPE");
                    frame.setVisible(false);

                    frame.removeAll();
                    frame.dispose();
                } else {

                }
            }

        });

    }

    private Color randomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public Pixel(int WIDTH, int HEIGHT, String pXe, String pYe, int screen, JFrame s, String size) {

        this.frame = s;
        this.size = Integer.parseInt(size);
        this.screen = screen;
        this.pX = Integer.parseInt(pXe);
        this.pY = Integer.parseInt(pYe);
        System.out.println(this.pX + "" + this.pY + "" + this.size);

        //  g.fillRect(x, y-9, 10, 10);
        //       System.out.println(x +  "   "+ y +"   PAINT DONE");
        Color bl = new Color(0, 0, 0);
        this.setBackground(bl);

        this.setVisible(true);
        this.setEnabled(true);

        this.frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("You pressed ESCAPE");
                    frame.setVisible(false);

                    frame.removeAll();
                    frame.dispose();
                } else {

                }
            }

        });

    }

}
