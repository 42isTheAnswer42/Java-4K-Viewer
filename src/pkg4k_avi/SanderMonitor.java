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
import com.sun.javafx.event.EventQueue;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBuffer;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javax.imageio.ImageIO;

import javax.media.jai.JAI;
import javax.media.jai.LookupTableJAI;
import javax.media.jai.RenderedOp;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javafx.scene.media.*;

public class SanderMonitor {

    static Action escapeAction;

    static double width;
    static double height;
    double height2;
    boolean hasSecondMonitor = false;
    static JFrame frame;
    String iccCmd;
    int running = 1;
    int screen;
    JPanel contentPane;
    static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    static GraphicsDevice[] gd = ge.getScreenDevices();
    BufferedImage colorspaceImg;
    BufferedImage img2;
    BufferedImage result;
    static MediaPlayer player;
    Group root;
    Scene scene;
    ICC_ColorSpace ics = new ICC_ColorSpace(ICC_Profile.getInstance(ColorSpace.CS_LINEAR_RGB));

    public static void addesc() {
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);

        escapeAction = new AbstractAction() {
            // close the frame when the user presses escape
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                player.setAutoPlay(false);
                player.setMute(true);
                player.dispose();

                frame.removeAll();
                frame.dispose();

                System.out.println("cheeekk");
            }
        };
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);

    }

    public static void getScreenSize(int screen) {
        int screen2 = screen;
        Rectangle2D result = new Rectangle2D.Double();
        GraphicsEnvironment localGE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (GraphicsDevice gd : localGE.getScreenDevices()) {
            for (GraphicsConfiguration graphicsConfiguration : gd.getConfigurations()) {

                GraphicsDevice[] gs = ge.getScreenDevices();

                DisplayMode mode = gs[screen].getDisplayMode();
                width = mode.getWidth();
                height = mode.getHeight();

                result.union(result, graphicsConfiguration.getBounds(), result);

                System.out.println("width: " + width + "   height: " + height);
                //f.setSize(result.getWidth(), result.getHeight());

            }

        }

    }

    public void monitorCheck() {

    }

    public void refreshFrame(final String uri) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        for (GraphicsDevice curGs : gs) {
            DisplayMode mode = curGs.getDisplayMode();
            // System.out.println(gs);
            System.out.println("refresh frame values");

            width = +mode.getWidth();
            height = mode.getHeight();
            System.out.println(width + "Breite");
            System.out.println(height + "höhe");

        }

        frame = new JFrame();

        int widt = (int) width;
        int heigt = (int) height;
        frame.setSize(widt, heigt);

        //  frame.setBackground(new Color(0, 0, 0, 0));
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        frame.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

        BufferedImage img2;

        try {
            System.out.println("URI:    " + uri);
            img2 = ImageIO.read(new File(uri));
            result = ImageIO.read(new File(uri));
            //colorspaceImg=cco.filter(img2, result);
            // File outputfile = new File("_"+this.iccCmd.substring(0)+((int)(running*10*Math.random()))+".jpg");
            //	ImageIO.write(result, "jpg", outputfile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        running++;

        //.filter(image, buffer);
        // ANIMATED GIF IMPLEMENTATION
        String gif = "gif";
        String gif2 = "GIF";

        String ext = uri.substring(uri.lastIndexOf(".") + 1);

        if (ext.equals(gif) || ext.equals(gif2)) {
            System.out.println(ext + " ext muss gif");
            System.out.println("----------------->GIF FIRED");
            frame.removeAll();
            getScreenSize(this.screen);
            frame = new JFrame();

            frame.setSize(widt, heigt);

            Icon myImgIcon = new ImageIcon(uri);

            JLabel imageLbl = new JLabel(myImgIcon);

            frame.add(imageLbl, BorderLayout.CENTER);
            frame.setVisible(false);

        }

        String avi = "avi";
        String mp4 = "mp4";

        // TIFF IMPLEMENTATION        
        String ext4 = uri.substring(uri.lastIndexOf(".") + 1);
        System.out.println(ext + "  ext muss TIF");

        String tif = "tif";
        String tif2 = "tiff";

        String ext2 = uri.substring(uri.lastIndexOf(".") + 1);
        System.out.println(ext + "  ext muss TIF");
        if (ext2.toLowerCase().equals(tif) || ext2.toLowerCase().equals(tif2)) {

            FileSeekableStream stream = null;
            try {
                stream = new FileSeekableStream(uri);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

            // Store the input stream in a ParameterBlock to be sent to
            // the operation registry, and eventually to the TIFF
            // decoder.
            ParameterBlock params = new ParameterBlock();
            params.add(stream);

            // Specify to TIFF decoder to decode images as they are and
            // not to convert unsigned short images to byte images.
            TIFFDecodeParam decodeParam = new TIFFDecodeParam();
            decodeParam.setDecodePaletteAsShorts(true);

            // Create an operator to decode the TIFF file.
            RenderedOp image1 = JAI.create("tiff", params);

            // Find out the first image's data type.
            int dataType = image1.getSampleModel().getDataType();
            RenderedOp image2 = null;
            if (dataType == DataBuffer.TYPE_BYTE) {
                // Display the byte image as it is.
                System.out.println("TIFF image is type byte.");
                image2 = image1;
            } else if (dataType == DataBuffer.TYPE_USHORT) {

                // Convert the unsigned short image to byte image.
                System.out.println("TIFF image is type ushort.");

                // Setup a standard window-level lookup table. */
                byte[] tableData = new byte[0x10000];
                for (int i = 0; i < 0x10000; i++) {
                    tableData[i] = (byte) (i >> 8);
                }

                // Create a LookupTableJAI object to be used with the
                // "lookup" operator.
                LookupTableJAI table = new LookupTableJAI(tableData);

                // Create an operator to lookup image1.
                image2 = JAI.create("lookup", image1, table);

            } else {
                System.out.println("TIFF image is type " + dataType
                        + ", and will not be displayed.");
                System.exit(0);
            }

            result = image2.getAsBufferedImage();
            frame.add(new Component() {

                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    g.drawImage(result, 0, 0, getWidth(), getHeight(), this);

                }
            });
            frame.setVisible(false);

        } //AVI IMPLEMENTATION
        else if (ext4.toLowerCase().equals(avi) || ext2.toLowerCase().equals(mp4)) {
            System.out.println("AVI-URI:  " + uri);

            System.out.println("AVI-URI:  " + uri);
            File file = new File(uri);
            String uri2 = "file://" + uri;
            try {

                //OLD IMPLEMENTATION
//					                URL mediaURL = new URL(uri2);
//					                Player mediaPlayer = Manager.createRealizedPlayer(mediaURL);
//					            //get components for video and playback controls
//					            Component video = mediaPlayer.getVisualComponent();
//					            Component controls = mediaPlayer.getControlPanelComponent();
//					            frame.add(video,BorderLayout.CENTER);
//					            frame.add(controls,BorderLayout.SOUTH);
//					            mediaPlayer.start();
//					           
                frame = new JFrame();
                getScreenSize(screen);
                frame.setSize(widt, heigt);

                //  frame.setBackground(new Color(0, 0, 0, 0));
                frame.setUndecorated(true);
                frame.getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
                frame.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

                JFXPanel jFXPanel = new JFXPanel();

                // frame.removeAll();
                frame.add(jFXPanel);
                System.out.println("NEW PANE L CREATED");

                Platform.setImplicitExit(false);
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        root = new Group();
                        scene = new Scene(root, widt, heigt);

                        // create media player
                        File file = new File(uri);
                        String MEDIA_URL = file.toURI().toString();
                        player = new MediaPlayer(new Media(MEDIA_URL));

                        player.setCycleCount(MediaPlayer.INDEFINITE);
                        player.setAutoPlay(true);
                        System.out.println("autoplay started");
                        // create mediaView and add media player to the viewer
                        MediaView mediaView = new MediaView(player);
                        ((Group) scene.getRoot()).getChildren().add(mediaView);

                        jFXPanel.setScene(scene);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
            frame.setVisible(true);

            addesc();

            frame.addKeyListener(new KeyAdapter() {

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

        } else {
            frame.add(new Component() {

                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    g.drawImage(result, 0, 0, getWidth(), getHeight(), this);

                }
            });
        }
        frame.setVisible(true);

    }

    public static void showOnScreen(int screen, final JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ColorSpace cs = gs[screen].getDefaultConfiguration().getColorModel().getColorSpace();
        String Colorspace = cs.toString();
        System.out.println(" Colorspace:" + Colorspace);

        System.out.println(" Colorspace:");
        if (screen > -1 && screen < gs.length) {
            gs[screen].setFullScreenWindow(frame);
        } else if (gs.length > 0) {
            gs[0].setFullScreenWindow(frame);
        } else {
            throw new RuntimeException("No Screens Found");
        }

    }

    public SanderMonitor(String uri, int screen2, String mode) {
        //this.getScreenSize();
        String pictureUri = uri;

        this.screen = screen2;

        int screen = screen2;
        this.refreshFrame(pictureUri);
        this.ics = ics;

        // Hier wird auf dem ersten =0 oder zweitem =1 Screen angezeigt...
        if (screen < 2) {
            SanderMonitor.showOnScreen(screen, frame);
        } else {
            SanderMonitor.showOnScreen(0, frame);

            SanderMonitor.showOnScreen(1, frame);
        }

        frame.setVisible(false);

        System.out.println("Show on screen is done");
        //this.frame.setVisible(false);
        // this.frame.removeAll();

    }

    public SanderMonitor(String uri) {
        this.getScreenSize();
        String pictureUri = uri;

        this.refreshFrame(pictureUri);
        frame.setVisible(false);
        // Hier wird auf dem ersten =0 oder zweitem =1 Screen angezeigt...

        SanderMonitor.showOnScreen(1, frame);
        frame.setVisible(false);

        System.out.println("Show on 2nd screen is done");
        //this.frame.setVisible(false);
        // this.frame.removeAll();

    }

    private void getScreenSize() {
        // TODO Auto-generated method stub

    }

    public SanderMonitor() {

        this.getScreenSize();

    }

    public SanderMonitor(JFrame frame3) {

        this.getScreenSize();
        int width = 0;
        int height = 0;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        for (GraphicsDevice curGs : gs) {
            DisplayMode mode = curGs.getDisplayMode();
            width = +mode.getWidth();
            height = mode.getHeight();

        }
        this.getScreenSize();
        this.frame = frame3;
    }

    public SanderMonitor(JFrame frame3, int screen) {

        this.getScreenSize();
        int width = 0;
        int height = 0;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        for (GraphicsDevice curGs : gs) {
            DisplayMode mode = curGs.getDisplayMode();
            width += mode.getWidth();
            height = mode.getHeight();

        }

        this.getScreenSize();
        this.frame = frame3;

        // Hier wird auf dem ersten =0 oder zweitem =1 Screen angezeigt...
        if (screen < 2) {
            SanderMonitor.showOnScreen(screen, this.frame);
        } else {
            SanderMonitor.showOnScreen(0, this.frame);

            SanderMonitor.showOnScreen(1, frame3);
        }

        frame.setVisible(true);

        System.out.println("Show on screen is done");
        ;

    }

    public void killScreen() {

        this.frame.setVisible(false);
        this.frame.removeAll();

    }

    

}
