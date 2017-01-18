package pkg4k_avi;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.EventQueue;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;

import javax.media.jai.JAI;
import javax.media.jai.LookupTableJAI;
import javax.media.jai.RenderedOp;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
import java.awt.Component;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;
import static pkg4k_avi.SanderMonitor.frame;
import static pkg4k_avi.StartGui.counterZ;

public class StartGui extends JFrame {

    String krass;
    static BufferedImage[] bufferedImages;
    PlanarImage planarimage;
    boolean noisemovieSet = false;
    Timer timer4;
    static BufferedImage bimage;
    TimerTask actiono;
    static boolean besetzt = false;
    GraphicsEnvironment ge = GraphicsEnvironment
            .getLocalGraphicsEnvironment();
    GraphicsDevice[] gs = ge.getScreenDevices();
    static JFrame frame;
    String noisePath;
    int picCount = 0;
    static JFrame frame3;
    BufferedImage result4;
    BufferedImage blaa;
    JTextPane textPane;
    static int counter = 0;

    static int counterZ = 0;
    String animationPath;
    boolean canceled = false;
    boolean isFromFolder = true;
    FileSeekableStream stream = null;
    Timer timer2 = new Timer();
    ;
         TimerTask actionoe;
    static File Order;
    public ListSelectionListener listSelectionListener2;
    DrawRoom drawRoom;
    /*
		*
		*
		*

		This Strings need to be set by your own:

		>matlabM is the M-File with your MatlabCOde that needs to be run.name it correctly

		>matlabPath is the Path where your MatlabFIles and Images are located. Set it correctly.

		>PHOTOPATH2 is the Path of your PhotoPath, it needs to be on the root directory of your
		systems harddrive.

		>photoTemp is the Path of your PhotoPath, it needs to be on the root directory of your
		systems harddrive.


     */
    String matlabM = "mainv2"; //Really just write the part before the dot, when you want to run thismatlab.m write thismatlab
    static String matlabPath = "/";//"Users/SilberPfeil/Documents/workspace/DropBox4k_new/4K-ImageProcessing";
    static String matlabWin = "D:/Dropbox/Master/4K_Viewer/Content/MatlabWorkspace/4K-ImageProcessing/";
    static String matlabPathWin = matlabWin.replaceAll(
            "[/\\\\]+", Matcher.quoteReplacement(System.getProperty("file.separator")));
    //"4K-ImageProcessing\";";

    // String relativePath = new File("img/").getAbsolutePath();
    String PHOTOPATH;
    ;//"/Users/SilberPfeil/documents/";
		static String photoTemp = new File("/matlab/Results/").getAbsolutePath().toString();//Users/SilberPfeil/Dropbox/4K_Viewer/Content/MatlabWorkspace/4K-ImageProcessing/Results";
    static String photoWin = "/matlab/Results/";//Dropbox/Master/4K_Viewer/Content/MatlabWorkspace/4K-ImageProcessing/Results";

    static String photoTempWin = photoWin.replaceAll(
            "[/\\\\]+", Matcher.quoteReplacement(System.getProperty("file.separator")));

    private Timer timer = new Timer();
    static File dir;
    private static boolean wPressed = false;
    boolean firstOpen = true;
    static int WIDTH;
    static int HEIGHT;
    public Thread t;
    DefaultListModel model1;

    static boolean locked = false;
    SanderMonitor sander = new SanderMonitor(frame);
    static JTextPane screensize = new JTextPane();
    String PicturePath;
    final ButtonGroup group;
    final ButtonGroup group2;
    String animPath = new File("matlab/NoiseImages").getAbsolutePath().toString();
    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{"avi",
        "gif", "mp4", "MP4", "png", "bmp", "jpg", "jpeg", "tiff", "JPG", "BMP", "GIF", "PNG", "JPEG", "TIFF", "tif", "TIF"// and other formats you need
    };
    static final String[] EXTENSIONSDIA = new String[]{
        "gif", "png", "bmp", "jpg", "jpeg", "tiff", "JPG", "BMP", "GIF", "PNG", "JPEG", "TIFF", "tif", "TIF"// and other formats you need
    };
    // filter to identify images based on their extensions
    static FilenameFilter IMAGE_FILTER;

    //Hat Pfad über ausgewähltes Foto gespeichert
    private String uri = new File("matlab/Results").getAbsolutePath().toString();
    private JPanel contentPane;
    private JLabel lblNewLabel;
    JRadioButton birdButton4;
    JRadioButton birdButton3;
    JRadioButton birdButton2;
    JRadioButton birdButton;
    final JComboBox comboBox;
    private JList jList1;
    JTextField timerrr;

    JScrollPane scroll;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StartGui framee = new StartGui();
                    framee.setVisible(true);

                    updateScreen(0);
                    File r = new File("matlab/Results");
                    String p = r.getAbsolutePath().toString();

                    framee.updatePath(p);
                    framee.updateList(p, r);
                    ;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Create the frame.
     *
     * @throws IOException
     */
    @SuppressWarnings("empty-statement")
    public StartGui() throws IOException {

        //.filter(image, buffer);
        //  ICC_Profile ip = ICC_Profile.getInstance( ColorSpace.CS_sRGB );
        setTitle("4k Viewer Gui");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 882);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //JcontentPane contentPane = new JcontentPane();
        //contentPane.add(contentPane, BorderLayout.NORTH);
        System.out.println("MATLAAAB PAAAATH WIN: " + matlabPathWin);
        JButton btnNewButton = new JButton("Choose picture path");

// Set PicturePath by using a FileChooser
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                isFromFolder = false;
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {

                    PicturePath = fc.getSelectedFile().getAbsolutePath();
                    Order = fc.getSelectedFile();

                    photoTemp = PicturePath;
                    PHOTOPATH = PicturePath;
                    // Hier wird nach Dateiauswahl der Pfad in einem uri-String gespeichert

                    System.out.println("Nach cut heisst der string : " + PicturePath);
                    //	SanderMonitor S=new SanderMonitor(uri);
                    //	S.addesc();
                    System.out.println("PHOTOPATH:" + photoTemp);

// Updating the JAVA GUI with chosen Path-String
                    updatePath(photoTemp);
                    updateList(photoTemp, Order);
                    //	 jList1.addListSelectionListener(listSelectionListener2);

                }
            }
        });

        btnNewButton.setBounds(19, 135, 253, 59);

        // Adding the BUtton to the contentPane
        //contentPane.add(btnNewButton);
        contentPane.add(btnNewButton);

        group2 = new ButtonGroup();

        //Create Radio Button Group
        ///////////////////////////////     DO NOT CHANGE /////////////////////////////
        final JRadioButton adobeRGB = new JRadioButton("Adobe_RGB");

        adobeRGB.setActionCommand("ColorSpace.CS_LINEAR_RGB");
        adobeRGB.setSelected(true);
        adobeRGB.setBounds(10, 330, 290, 25);
        group2.add(adobeRGB);

        //Group the radio buttons.
        //   contentPane.add(adobeRGB);
        JRadioButton sRGB = new JRadioButton("sRGB");

        sRGB.setActionCommand("ColorSpace.CS_sRGB");
        sRGB.setSelected(false);
        sRGB.setBounds(10, 350, 290, 25);
        group2.add(sRGB);

        //Group the radio buttons.
        // contentPane.add(sRGB);
        JRadioButton noneRGB = new JRadioButton("XYZ");

        noneRGB.setActionCommand("ColorSpace.CS_CIEXYZ");
        noneRGB.setSelected(false);
        noneRGB.setBounds(10, 370, 290, 25);
        group2.add(noneRGB);

        //Group the radio buttons.
        //   contentPane.add(noneRGB);
///////////////////////////////     DO NOT CHANGE /////////////////////////////
// IMPLEMENTATION OF AUTOPLAY CHANGE LISTENER, FIRES WHEN U SELECT A FILE FROM LIST
        ActionListener changeListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // TODO Auto-generated method stubAbstractButton aButton = (AbstractButton)changEvent.getSource();
                AbstractButton aButton = (AbstractButton) e.getSource();
                ButtonModel aModel = aButton.getModel();

                boolean armed = aModel.isArmed();
                boolean pressed = aModel.isPressed();
                boolean selected = aModel.isSelected();
                System.out.println("Changed: " + armed + "/" + pressed + "/"
                        + selected);

                photoTemp = PHOTOPATH;

                uri = photoTemp + "/" + jList1.getSelectedValue().toString();
                System.out.println("uri:" + uri);

                //Get selected screen Number
                int screen = comboBox.getSelectedIndex();
                System.out.println(screen);
                String avi = "avi";
                String mp4 = "mp4";

                String temp = uri;
                String ext = uri.substring(uri.lastIndexOf(".") + 1);;
                temp = temp.substring(0, temp.lastIndexOf("_original"));

                //Getting the RadioButton options, that refer  to the filename
                String f = group.getSelection().getActionCommand();
                if ((ext.toLowerCase().equals(mp4)) || ((ext.toLowerCase().equals(avi)))) {
                    f = "_original";
                }

                String mode = group2.getSelection().getActionCommand();

                String mode3 = mode;
                mode = mode.toLowerCase();
                String sRGB = "colorspace.cs_srgb";
                String addobeRGB = "colorspace.cs_linear_rgb";
                System.out.println(mode3);
                if (mode.equals("colorspace.cs_srgb")) {

                    mode3 = "_sRGB";

                } else {
                    mode3 = "_AdobeRGB";
                }
                //Extend the filename to search as selected

                //Extend the filename to search as selected
                temp = temp + f + "." + ext;
                //temp=temp+f+"."+ext;
                System.out.println("TEMPAAAAAAAAAAA : " + temp);
                System.out.println(uri);
                uri = temp;

                try {
                    refreshImageIcon();
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                try {
                    sander.frame.dispose();
                    if (screen < 2) {
                        if (!firstOpen) {
                            //  	sander.frame.removeAll();
                            sander.frame.dispose();
                        }
                        // Display Picture (uri) on selected Screen
                        sander = new SanderMonitor(uri, screen, mode);
                    } else {
                        SanderMonitor s = new SanderMonitor(uri, 1, mode);
                        sander = new SanderMonitor(uri, 0, mode);
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                sander.addesc();
                sander.frame.setVisible(true);
                firstOpen = false;

                /// ADDING ESCAPE LSITENER TO FRAME THAT CLOSES BY PRESSING ESCAPE
                sander.frame.addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyPressed(KeyEvent ke) {
                        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            System.out.println("You pressed ESCAPE");
                            sander.frame.setVisible(false);

                            sander.frame.removeAll();
                            sander.frame.dispose();
                        } else {

                        }
                    }

                });

            }
        };

        model1 = new DefaultListModel();

        System.out.println(photoTemp + "   vor FOTOFILTER");
        //System.out.println(relativePath+"  relativePath");
        File o = new File("/matlab/Results");
        IMAGE_FILTER = new FilenameFilter() {

            @Override
            public boolean accept(File Order, final String name) {
                for (final String ext : EXTENSIONS) {

                    if ((name.endsWith("original." + ext))) {
                        return (true);
                    }
                }//&& (!(name.endsWith("sRGB_original." + ext))) && (!(name.endsWith("AdobeRGB_original." + ext)))
                return (false);
            }
        };

        try {
            File[] files = o.listFiles(IMAGE_FILTER);

            for (File fz : files) {
                model1.addElement(fz.getName());
                updatePath(Order.getAbsolutePath());;
            }
        } catch (Exception e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }

        // File representing the folde	 that you select using a FileChooser
        jList1 = new JList(model1);

        JScrollPane scroll = new JScrollPane(jList1);
        listSelectionListener2 = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                // frame.dispose();
                System.out.print("First index: " + listSelectionEvent.getFirstIndex());
                System.out.print(", Last index: " + listSelectionEvent.getLastIndex());
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                long time = 10000;
                JList list = (JList) listSelectionEvent.getSource();
                adjust = listSelectionEvent.getValueIsAdjusting();
                System.out.println(", Adjusting? " + adjust);
                if (((list.hasFocus())) && (!adjust)) {

                    try {
                        uri = PHOTOPATH + "/" + list.getSelectedValue().toString();
                        System.out.println("uri:" + uri);
                    } catch (Exception e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }

                    //Get selected screen Number
                    int screen = comboBox.getSelectedIndex();
                    System.out.println(screen);

                    String temp = uri;
                    String ext = uri.substring(uri.lastIndexOf(".") + 1);;
                    String avi = "avi";
                    String mp4 = "mp4";
                    System.out.println("EXT    :" + ext);
                    String f = group.getSelection().getActionCommand();
                    if (ext.toLowerCase().equals(avi) || ext.toLowerCase().equals(mp4)) {
                        birdButton3.setVisible(false);
                        birdButton.setVisible(false);
                        birdButton2.setVisible(false);
                        birdButton4.setSelected(true);

                        f = "_original";

                    } else {
                        birdButton3.setVisible(true);
                        birdButton.setVisible(true);
                        birdButton2.setVisible(true);
                        birdButton4.setSelected(true);
                    }

                    try {
                        temp = uri.substring(0, uri.lastIndexOf("_original"));
                    } catch (Exception e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                        temp = uri.substring(0, uri.lastIndexOf("_vid"));
                    }

                    //Getting the RadioButton options, that refer  to the filename
                    String mode = group2.getSelection().getActionCommand();

                    String mode3 = mode;
                    mode = mode.toLowerCase();
                    String sRGB = "colorspace.cs_srgb";
                    String addobeRGB = "colorspace.cs_linear_rgb";
                    System.out.println(mode3);
                    if (mode.equals("colorspace.cs_srgb")) {

                        mode3 = "_sRGB";

                    } else {
                        mode3 = "_AdobeRGB";
                    }

                    //Extend the filename to search as selected
                    String tempw = temp + f + "." + ext;
                    //Extend the filename to search as selected
                    //	temp=temp+f+"."+ext;
                    System.out.println("TEMPFirstPress : " + tempw);

                    uri = tempw;
                    System.out.println(uri);
                    try {
                        refreshImageIcon();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    try {
                        if (screen < 2) {
                            if (!firstOpen) {
                                //  	sander.frame.removeAll();
                                sander.frame.dispose();
                            }
                            // Display Picture (uri) on selected Screen
                            sander = new SanderMonitor(uri, screen, mode);
                        } else {
                            SanderMonitor s = new SanderMonitor(uri, 1, mode);
                            sander = new SanderMonitor(uri, 0, mode);
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    sander.addesc();
                    sander.frame.setVisible(true);
                    firstOpen = false;

                    sander.frame.addKeyListener(new KeyAdapter() {

                        @Override
                        public void keyPressed(KeyEvent ke) {
                            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                System.out.println("You pressed ESCAPE");
                                sander.frame.setVisible(false);

                                sander.frame.removeAll();
                                sander.frame.dispose();
                            } else {

                            }
                        }

                    });

                }
            }
        };
        jList1.addListSelectionListener(listSelectionListener2);

        scroll.setBounds(19, 357, 553, 141);
        //	scroll.
        contentPane.add(scroll);

        JLabel lblOrChooseExamples = new JLabel("1. Run Matlab ImageProcessing");
        lblOrChooseExamples.setBackground(Color.DARK_GRAY);
        lblOrChooseExamples.setBounds(9, 6, 392, 16);
        contentPane.add(lblOrChooseExamples);

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBounds(276, 135, 296, 59);
        textPane.setText(PicturePath);
        contentPane.add(textPane);

        final JTextPane screensize = new JTextPane();

        screensize.setBounds(16, 255, 265, 25);
        contentPane.add(screensize);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Screen1", "Screen2",}));
        comboBox.setBounds(17, 279, 247, 39);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int screen = comboBox.getSelectedIndex();
                System.out.println(screen);

                updateScreen(screen);
                int ccc = screen + 1;

                screensize.setText("Screen" + ccc + " width: " + WIDTH + " || height: " + HEIGHT);

            }
        });
        contentPane.add(comboBox);

        JLabel lblChooseScreen = new JLabel("Insert Values for ");
        lblChooseScreen.setBounds(293, 255, 126, 16);
        contentPane.add(lblChooseScreen);

        JSeparator separator = new JSeparator();
        separator.setBounds(9, 400, 477, 3);
        contentPane.add(separator);

        JButton btnBearbeitenUndIn = new JButton("Start Matlab and edit pictures");
        btnBearbeitenUndIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    launchMatlab();
                } catch (MatlabConnectionException | MatlabInvocationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnBearbeitenUndIn.setBounds(18, 24, 254, 59);
        contentPane.add(btnBearbeitenUndIn);

//CIECAM BUTTONS CREATION AND SETTING
//Create ButtonGroup
        group = new ButtonGroup();

        //Create Radio Button Group
        birdButton4 = new JRadioButton("Original");

        birdButton4.setActionCommand("_original");
        birdButton4.setSelected(true);
        birdButton4.setBounds(17, 555, 290, 25);

        birdButton4.addActionListener(changeListener);
        group.add(birdButton4);

        birdButton = new JRadioButton("CieCam02 AVERAGE");

        birdButton.setActionCommand("_original");
        birdButton.setSelected(false);
        birdButton.setBounds(17, 579, 290, 25);

        //Group the radio buttons.
        birdButton.addActionListener(changeListener);
        group.add(birdButton);

        birdButton3 = new JRadioButton("CieCam02 DARK");

        birdButton3.setActionCommand("_CIECAM_dark");
        birdButton3.setSelected(false);
        birdButton3.setBounds(17, 629, 290, 25);

        //Group the radio buttons.
        birdButton3.addActionListener(changeListener);
        group.add(birdButton3);

        birdButton2 = new JRadioButton("CieCam02 DIM");

        birdButton2.setActionCommand("_CIECAM_dim");
        birdButton2.setSelected(false);
        birdButton2.setBounds(17, 602, 290, 25);
        birdButton2.addActionListener(changeListener);

        //Group the radio buttons.
        group.add(birdButton2);
        //Register a listener for the radio buttons.

        contentPane.add(birdButton);
        contentPane.add(birdButton2);
        contentPane.add(birdButton3);
        contentPane.add(birdButton4);
        /*
		JCheckBox checkBox = new JCheckBox("CIECAM02 AVERAGE");
		checkBox.setSelected(false);
		checkBox.setBounds(215, 38, 79, 25);
		contentPane.add(checkBox);

		JCheckBox checkBox_1 = new JCheckBox("CIECAM02 DIM");
		checkBox_1.setSelected(false);
		checkBox_1.setBounds(218, 68, 79, 25);
		contentPane.add(checkBox_1);

		JCheckBox checkBox_2 = new JCheckBox("CIECAM02 DARK");
		checkBox_2.setSelected(true);
		checkBox_2.setBounds(218, 98, 113, 25);
		contentPane.add(checkBox_2);
		,
		JLabel label = new JLabel("Erweiterte Bildanpassungen");
		label.setBackground(Color.DARK_GRAY);
		label.setBounds(205, 13, 162, 16);
		contentPane.add(label);

         */

        ///// IMPLEMENTIERUG DER ZEITABFRAGE FÜR DIE DIASHOW
        final JTextField timerrr = new JTextField();
        timerrr.setBackground(getForeground());
        timerrr.setText("1000");;
        timerrr.setEditable(true);
        timerrr.setBounds(290, 670, 50, 59);
        contentPane.add(timerrr);

        /// IMPLEMENTIERUNG DER DIASHOW BUTTONS
        JButton btnNoise = new JButton("Start Diashow, set time before ");

        btnNoise.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eddd) {
                canceled = false;

                int screen = comboBox.getSelectedIndex();
                System.out.println(screen);

                updateScreen(screen);
                int ccc = screen + 1;

                screensize.setText("Screen" + ccc + " width: " + WIDTH + " || height: " + HEIGHT);

                isFromFolder = false;
                /*  	JFileChooser fc = new JFileChooser(	);
            	fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION)
                   PicturePath=fc.getSelectedFile().getAbsolutePath();
                    Order=fc.getSelectedFile(); */

                noisePath = PHOTOPATH;;//"/Users/SilberPfeil/documents/";

                String ext = "tiff";
                String ext3 = "tif";
                IMAGE_FILTER = new FilenameFilter() {

                    @Override
                    public boolean accept(File Order, final String name) {
                        for (final String extt : EXTENSIONSDIA) {
                            if ((name.endsWith(extt)) || (name.endsWith(ext3)) || (name.endsWith(ext))) {
                                return (true);
                            }
                        }//&& (!(name.endsWith("sRGB_original." + ext))) && (!(name.endsWith("AdobeRGB_original." + ext)))
                        return (false);
                    }
                };
                counter = 0;
                File o = new File(noisePath);
                File[] files = o.listFiles(IMAGE_FILTER);
                for (File f2 : files) {

                    counter = counter + 1;
                }

                //   String krass= files[0].getAbsolutePath();
                //          System.out.println(krass);
                String mode = "";
                frame = new JFrame();
                timer2 = new Timer();
                //   timer2.cancel(); //this will cancel the current task. if there is no active task, nothing happens
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice[] gs = ge.getScreenDevices();

                TimerTask actiono;
                actiono = new TimerTask() {
                    @Override
                    public void run() {

                        if (!canceled) {
                            counterZ = counterZ + 1;
                            if (counterZ >= counter) {
                                counterZ = 0;
                            };
                            try {
                                stream = null;

                                krass = files[counterZ].getAbsolutePath();
                                //      System.out.println("updatdKRASS   "+krass);
                                stream = new FileSeekableStream(krass);
                            } catch (IOException edff) {
                                edff.printStackTrace();
                                System.exit(0);
                            }
                            RenderedOp image2 = null;
                            String ext4 = krass.substring(krass.lastIndexOf(".") + 1);
                            System.out.println(ext + "  ext muss TIF");

                            String tif = "tif";
                            String tif2 = "tiff";

                            String ext2 = krass.substring(krass.lastIndexOf(".") + 1);
                            System.out.println(ext + "  ext muss TIF");
                            if (ext4.toLowerCase().equals(tif) || ext4.toLowerCase().equals(tif2)) {

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

                                if (dataType == DataBuffer.TYPE_BYTE) {
                                    // Display the byte image as it is.
                                    System.out.println("TIFF image is type byte.");
                                    image2 = image1;
                                    blaa = image2.getAsBufferedImage();
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
                                    blaa = image2.getAsBufferedImage();
                                } else {
                                    System.out.println("TIFF image is type " + dataType
                                            + ", and will not be displayed.");
                                    //System.exit(0);
                                }
                            } else {
                                try {
                                    System.out.println("URI:    " + krass);
                                    blaa = ImageIO.read(new File(krass));

                                    //colorspaceImg=cco.filter(img2, result);
                                    // File outputfile = new File("_"+this.iccCmd.substring(0)+((int)(running*10*Math.random()))+".jpg");
                                    //	ImageIO.write(result, "jpg", outputfile);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            };

                            try {
                                if (ext4.toLowerCase().equals(tif) || ext4.toLowerCase().equals(tif2) && (picCount == 0)) {
                                    frame.add(new Component() {

                                        @Override
                                        public void paint(Graphics g) {
                                            super.paint(g);
                                            g.drawImage(blaa, 0, 0, getWidth(), getHeight(), this);

                                        }
                                    });
                                    picCount = 1;
                                } else if (ext4.toLowerCase().equals(tif) || ext4.toLowerCase().equals(tif2) && (picCount == 1)) {

                                    frame.repaint();
                                } else if (picCount == 0) {

                                    String krassk = files[counterZ].getAbsolutePath();

                                    frame.add(new Component() {

                                        @Override
                                        public void paint(Graphics g) {
                                            super.paint(g);
                                            g.drawImage(blaa, 0, 0, getWidth(), getHeight(), this);

                                        }

                                    });
                                } else {
                                    frame.repaint();
                                }
                                System.out.println(krass + "ENDE KRAAAAAASSSSSSS + CounterZ" + counterZ);
                                System.out.println(krass + "ENDE KRAAAAAASSSSSSS");
                            } catch (Exception xxx) {
                                xxx.printStackTrace();
                            }

                            System.out.println("ZEILE 961 :     counter: " + counter + "  CounterZ:  " + counterZ);
                            System.out.println("ZEILE 961 :     canceled: " + canceled);

                            if (!canceled) {
                                frame.setVisible(true);
                            }

                            if (screen > -1 && screen < gs.length && (!canceled)) {
                                gs[screen].setFullScreenWindow(frame);

                            } else if (gs.length > 0 && (!canceled)) {

                                gs[screen].setFullScreenWindow(frame);
                            } else {
                                throw new RuntimeException("No Screens Found");
                            }
                            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.addKeyListener(new KeyAdapter() {

                                @Override
                                public void keyPressed(KeyEvent ke) {
                                    if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                        System.out.println("You pressed ESCAPE");
                                        canceled = true;
                                        timer2.cancel();
                                        counter = 0;
                                        picCount = 0;

                                        try {
                                            timer2.cancel();
                                            frame.setVisible(false);
                                            System.out.println("caneeld? " + canceled);
                                        } catch (Throwable ex) {
                                            Logger.getLogger(StartGui.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        frame.removeAll();
                                        frame.dispose();
                                        frame.getContentPane().repaint();

                                    } else {

                                    }

                                }

                            });

                        }
                    }
                };
                String time4Dia = timerrr.getText();
                int timT = Integer.parseInt(time4Dia);

                System.out.println(timT + "   timT");

                int delay = 0; // delay for 5 sec.
                int period = 1000; // repeat every sec.

                time4Dia = timerrr.getText();
                timT = Integer.parseInt(time4Dia);

                System.out.println(timT + "   timT");
                timer2.scheduleAtFixedRate(actiono, delay, timT);

                /*  try {
			File[] files = o.listFiles(IMAGE_FILTER);

			for(File fz : files) {
			model1.addElement(fz.getName());
			 //   updatePath(Order.getAbsolutePath());;
                        String krass= fz.getAbsolutePath();
                      System.out.println(krass);
                        String mode="";


                        	 BufferedImage img2;
                                img2 = ImageIO.read(new File(krass));

                                int d=0;
                             sander=new SanderMonitor(krass);

                	}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} */
            }
        });

        btnNoise.setBounds(19, 670, 253, 59);
        contentPane.add(btnNoise);

        JButton btnAnim = new JButton("Play (noise-)movie from pics");
        btnAnim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canceled = false;

                int screen = comboBox.getSelectedIndex();
                System.out.println(screen);

                int ccc = screen + 1;

                screensize.setText("Screen" + ccc + " width: " + WIDTH + " || height: " + HEIGHT);

                isFromFolder = false;

                if (!noisemovieSet) {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int result = fc.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        animPath = fc.getSelectedFile().getAbsolutePath();
                    }
                    Order = fc.getSelectedFile();
                    noisemovieSet = true;
                    btnAnim.setText("Play");

                }

                animationPath = animPath;;//"/Users/SilberPfeil/documents/";
                ;

                String ext = "tiff";
                String ext3 = "tif";
                IMAGE_FILTER = new FilenameFilter() {

                    @Override
                    public boolean accept(File Order, final String name) {
                        for (final String extt : EXTENSIONSDIA) {
                            if ((name.endsWith(extt)) || (name.endsWith(ext3)) || (name.endsWith(ext))) {
                                return (true);
                            }
                        }//&& (!(name.endsWith("sRGB_original." + ext))) && (!(name.endsWith("AdobeRGB_original." + ext)))
                        return (false);
                    }
                };
                counterZ = 0;
                counter = 0;

                File o = new File(animationPath);
                File[] files = o.listFiles(IMAGE_FILTER);
                for (File f2 : files) {
                    String krass = f2.getAbsolutePath();
                    System.out.println(krass);
                    counter = counter + 1;
                }

                bufferedImages = new BufferedImage[counter];
                counter = 0;
                for (File f2 : files) {
                    String krass = f2.getAbsolutePath();
                    System.out.println(krass);

                    counter = counter + 1;

                    planarimage = JAI.create("fileload", krass);
                    bufferedImages[counter - 1] = planarimage.getAsBufferedImage();
                    ;
                }

                String mode = "";
                JFrame frame = new JFrame();
                timer4 = new Timer();
                //   timer2.cancel(); //this will cancel the current task. if there is no active task, nothing happens
                GraphicsEnvironment ge = GraphicsEnvironment
                        .getLocalGraphicsEnvironment();
                final GraphicsDevice[] gs = ge.getScreenDevices();
                frame.setUndecorated(true);
                frame.getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
                frame.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

                if (screen > 1) {
                    screen = 1;
                }

                DisplayMode mosde = gs[screen].getDisplayMode();

                WIDTH = mosde.getWidth();
                HEIGHT = mosde.getHeight();
                frame.setSize(WIDTH, HEIGHT);

                stream = null;

                krass = files[counterZ].getAbsolutePath();
                planarimage = JAI.create("fileload", krass);
                bimage = planarimage.getAsBufferedImage();

                frame.add(new Component() {

                    @Override
                    public void paint(Graphics g) {
                        super.paint(g);
                        g.drawImage(bimage, 0, 0, getWidth(), getHeight(), this);

                    }
                });

                if (screen > -1 && screen < gs.length) {
                    gs[screen].setFullScreenWindow(frame);

                } else if (gs.length > 0) {

                    gs[screen].setFullScreenWindow(frame);
                } else {
                    throw new RuntimeException("No Screens Found");
                }
                // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyPressed(KeyEvent ke) {
                        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            System.out.println("You pressed ESCAPE");
                            canceled = true;
                            timer4.cancel();
                            counter = 0;
                            picCount = 0;

                            try {

                            } catch (Throwable ex) {
                                Logger.getLogger(StartGui.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            frame.setVisible(false);

                            frame.removeAll();
                            frame.dispose();
                            frame.getContentPane().repaint();

                        } else {

                        }

                    }

                });

                rePainter p = new rePainter(frame);

                int screenw = comboBox.getSelectedIndex();

                actionoe = new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            if (!canceled && (!besetzt)) {

                                //    bimage=bufferedImages[counterZ];
                                //    gs[screenw].setFullScreenWindow( frame );
                                StartGui.raiseCounterAAndPic();
                            }
                            p.repaintt();

                        } catch (Exception r) {
                            r.printStackTrace();
                        }
                    }
                };
                String time4Dia = timerrr.getText();
                int timT = Integer.parseInt(time4Dia);

                System.out.println(timT + "   timT");

                int delay = 0; // delay for 5 sec.
                int period = 1000 / 30; // repeat every sec.

                timer4.scheduleAtFixedRate(actionoe, delay, timT);

            }
        });

        btnAnim.setBounds(348, 670, 253, 59);
        contentPane.add(btnAnim);

        final JTextField textField1 = new JTextField();
        textField1.setBackground(getForeground());
        textField1.setText("X");;
        textField1.setEditable(true);
        textField1.setBounds(431, 240, 50, 39);
        contentPane.add(textField1);

        final JTextField textField2 = new JTextField();
        textField2.setBackground(getForeground());
        textField2.setText("Y");;
        textField2.setEditable(true);
        textField2.setBounds(475, 240, 50, 39);
        contentPane.add(textField2);

        final JTextField textField3 = new JTextField();
        textField3.setBackground(getForeground());
        textField3.setText("Size");;
        textField3.setEditable(true);
        textField3.setBounds(521, 240, 51, 39);
        contentPane.add(textField3);

        JButton btPX = new JButton("Adress Pixel @ (X, Y)");

        btPX.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int screen = comboBox.getSelectedIndex();
                System.out.println(screen);
                int width, height;
                SanderMonitor.getScreenSize(screen);
                System.out.println(firstOpen + ": firstOpen");

                if (!firstOpen) {
                    SanderMonitor.frame.removeAll();
                    SanderMonitor.frame.dispose();
                }
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

                String pX = textField1.getText();
                String pY = textField2.getText();
                String size = textField3.getText();
                //   Thread t=new Thread ();
                frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setSize(WIDTH, HEIGHT);
                Color bl = new Color(0, 0, 0);
                frame.setBackground(bl);
                //SanderMonitor PxTEST= new SanderMonitor(frame3, screen);
                //    frame3.add(new PixelCanvas(screen, t));
                if (screen > 1) {
                    screen = 1;
                }
                Pixel set = new Pixel(WIDTH, HEIGHT, pX, pY, screen, frame, size);
                Canvas canvas = new Canvas();

                canvas.setSize(WIDTH, HEIGHT);
                canvas.setBackground(Color.BLACK);
                canvas.setVisible(true);
                canvas.setFocusable(false);
                //frame.add(canvas);
                frame.getContentPane().add(set);
                //frame3.setBackground(bl);

                sander = new SanderMonitor(frame, screen);

                autoCloseFrame();;

            }

        });

        btPX.setBounds(276, 279, 296, 39);
        contentPane.add(btPX);

        
        
        
        JButton btnDisplay = new JButton("Display (Esc to close)");

        btnDisplay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!firstOpen) {
                    //  	sander.frame.removeAll();
                    sander.frame.dispose();
                }

                //Get File out of list, and check for further options down
                try {

                    System.out.println("displaying von PHOTOTEMP:" + photoTemp);
                    uri = photoTemp + "/" + jList1.getSelectedValue().toString();
                    System.out.println("uri:" + uri);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                //Get selected screen Number
                int screen = comboBox.getSelectedIndex();
                System.out.println(screen);

                String temp = uri;
                String ext = uri.substring(uri.lastIndexOf(".") + 1);;
                temp = temp.substring(0, temp.lastIndexOf("_original"));

                //Getting the RadioButton options, that refer  to the filename
                String f = group.getSelection().getActionCommand();
                String mode = group2.getSelection().getActionCommand();

                String mode3 = mode;
                mode = mode.toLowerCase();
                String sRGB = "colorspace.cs_srgb";
                String addobeRGB = "colorspace.cs_linear_rgb";
                System.out.println(mode3);
                if (mode.equals("colorspace.cs_srgb")) {

                    mode3 = "_sRGB";

                } else {
                    mode3 = "_AdobeRGB";
                }

                //Extend the filename to search as selected
                temp = temp + f + "." + ext;
                System.out.println("TEEEEEMP : " + temp);
                System.out.println(uri);

                uri = temp;

                try {
                    refreshImageIcon();
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                try {
                    if (screen < 2) {
                        // Display Picture (uri) on selected Screen
                        sander = new SanderMonitor(uri, screen, mode);
                    } else {
                        SanderMonitor s = new SanderMonitor(uri, 1, mode);
                        sander = new SanderMonitor(uri, 0, mode);
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                //sander.addesc();
                sander.frame.setVisible(true);
                firstOpen = false;

                sander.frame.addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyPressed(KeyEvent ke) {
                        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            System.out.println("You pressed ESCAPE");
                            sander.frame.setVisible(false);

                            sander.frame.removeAll();
                            sander.frame.dispose();
                        } else {

                        }
                    }

                });

                //       JOptionPane.showConfirmDialog(frame, "You Selected : " + jList1.getSelectedValue(), "Display",
                //              JOptionPane.PLAIN_MESSAGE);
            }
        });

        btnDisplay.setBounds(319, 504, 253, 39);
        //	contentPane.add(btnDisplay);

        int screen = comboBox.getSelectedIndex();

        updateScreen(screen);
        int ccc = screen + 1;

        screensize.setText("Screen" + ccc + " width: " + WIDTH + " || height: " + HEIGHT);

        JLabel lblChossePicturepath = new JLabel("2. Choose PicturePath to Show the Pictures in the List down");
        lblChossePicturepath.setBackground(Color.DARK_GRAY);
        lblChossePicturepath.setBounds(9, 107, 392, 16);
        contentPane.add(lblChossePicturepath);

        JLabel lblSelectPicture = new JLabel("4. Select picture from List (ESC to close) ");
        lblSelectPicture.setBackground(Color.DARK_GRAY);
        lblSelectPicture.setBounds(9, 329, 392, 16);
        contentPane.add(lblSelectPicture);

        JLabel lblSetCiecam = new JLabel("5. Set CIECAM if necessary");
        lblSetCiecam.setBackground(Color.DARK_GRAY);
        lblSetCiecam.setBounds(9, 527, 392, 16);
        contentPane.add(lblSetCiecam);

        JLabel lblDetectYour = new JLabel("3. Detect your desired screen: 2nd is normally extern");
        lblDetectYour.setBackground(Color.DARK_GRAY);
        lblDetectYour.setBounds(9, 225, 392, 16);
        contentPane.add(lblDetectYour);

        lblNewLabel = new JLabel((String) null);
        lblNewLabel.setBounds(276, 510, 296, 144);

        ImageIcon background = new ImageIcon(uri);
        JLabel label = new JLabel();

        lblNewLabel.setIcon(background);

        contentPane.add(lblNewLabel);
    }

    public static void setLock() {

        locked = true;
    }

    public static void setLockNeg() {

        locked = false;

    }

    public void launchMatlab() throws MatlabConnectionException, MatlabInvocationException {
        // create proxy
        MatlabProxyFactoryOptions options
                = new MatlabProxyFactoryOptions.Builder()
                        .setUsePreviouslyControlledSession(true)
                        .build();
        MatlabProxyFactory factory = new MatlabProxyFactory(options);
        MatlabProxy proxy = factory.getProxy();

        // call builtin function
        proxy.eval("disp('hello matlab')");

        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath);

        String path = new File("/matlab")
                .getAbsolutePath();
        System.out.println("PATH: " + path);
        // call user-defined function (must be on the path);
        String cmd = "addpath('";
        String end = "')";

        String changeFolder = "cd('";
        String end2 = "')";
        String cd = changeFolder + basePath + "/matlab" + end2;
        String cmdFull = cmd + basePath + "/matlab" + end;

        String mainDir = "MainDir=('" + basePath + "/matlab" + end;
        System.out.println(cmdFull);
        System.out.println(" mainDi    " + mainDir + "---------------");

        // System.out.println("addpath('/Users/SilberPfeil/Documents/workspace/DropBox4k_new/4K-ImageProcessing/')");
        proxy.eval(cmdFull);

        System.out.println(" cd    " + cd + "---------------");
        proxy.eval(cd);

        proxy.eval(mainDir);
        System.out.println(cmdFull + "  -----------------------------------   CMDFULL");
        proxy.feval(matlabM);

        // close connection
        proxy.disconnect();
    }

    public static void updateScreen(int screen) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if (screen > 1) {
            screen = 1;
        }

        DisplayMode mode = gs[screen].getDisplayMode();
        // System.out.println(gs);
        System.out.println("refresh frame values");

        WIDTH = mode.getWidth();
        HEIGHT = mode.getHeight();
        int ccc = screen + 1;

        screensize.setText("Screen" + ccc + " Breite: " + WIDTH + " || Höhe: " + HEIGHT);

    }

    public void updatePath(String fEEEE) {

        textPane.setText(fEEEE);
        PHOTOPATH = fEEEE;

    }

    public void addListener() {
        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                // frame.dispose();
                System.out.print("First index: " + listSelectionEvent.getFirstIndex());
                System.out.print(", Last index: " + listSelectionEvent.getLastIndex());
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                System.out.println(", Adjusting? " + adjust);
                if (!adjust) {

                    JList jlist1 = (JList) listSelectionEvent.getSource();

                    try {
                        uri = PHOTOPATH + "/" + jlist1.getSelectedValue().toString();
                    } catch (Exception e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                        uri = "error";
                    }
                    System.out.println("uri:" + uri);

                    //Get selected screen Number
                    int screen = comboBox.getSelectedIndex();
                    System.out.println(screen);
                    String mp4 = "mp4";
                    String avi = "avi";

                    String temp = uri;
                    String ext = uri.substring(uri.lastIndexOf(".") + 1);;
                    temp = temp.substring(0, temp.lastIndexOf("_original"));

                    //Getting the RadioButton options, that refer  to the filename
                    String f = group.getSelection().getActionCommand();

                    if ((ext.toLowerCase().equals(mp4)) || ((ext.toLowerCase().equals(avi)))) {
                        f = "_original";
                    }
                    //Extend the filename to search as selected
                    temp = temp + f + "." + ext;
                    System.out.println("TEMPPPPPP : " + temp + ext + "EXTENSINO");
                    System.out.println(uri);

                    String mode3 = group2.getSelection().getActionCommand();
                    String mode = mode3;

                    mode = mode.toLowerCase();
                    String sRGB = "colorspace.cs_srgb";
                    String addobeRGB = "colorspace.cs_linear_rgb";
                    System.out.println(mode3);
                    if (mode.equals("colorspace.cs_srgb")) {

                        mode3 = "_sRGB";

                    } else {
                        mode3 = "_AdobeRGB";
                    }

                    System.out.println("TEEEEEMP : " + temp);
                    System.out.println(uri);
                    uri = temp;
                    try {
                        refreshImageIcon();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    try {

                        if (screen < 2) {
                            if (!firstOpen) {
                                //  	sander.frame.removeAll();
                                sander.frame.dispose();
                            }
                            // Display Picture (uri) on selected Screen
                            sander = new SanderMonitor(uri, screen, mode);
                        } else {
                            SanderMonitor s = new SanderMonitor(uri, 1, mode);
                            sander = new SanderMonitor(uri, 0, mode3);
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    sander.addesc();
                    sander.frame.setVisible(true);
                    firstOpen = false;

                    sander.frame.addKeyListener(new KeyAdapter() {

                        @Override
                        public void keyPressed(KeyEvent ke) {
                            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                System.out.println("You pressed ESCAPE");
                                sander.frame.setVisible(false);

                                sander.frame.removeAll();
                                sander.frame.dispose();
                            } else {

                            }
                        }

                    });

                }
            }
        };
    }

    public void updateList(String fss, File Order) {
        IMAGE_FILTER = new FilenameFilter() {

            @Override
            public boolean accept(File Order, final String name) {
                for (final String ext : EXTENSIONS) {

                    if ((name.endsWith("original." + ext)) || (name.endsWith("vid." + ext))) {
                        return (true);
                    }
                }
                //&& (!(name.endsWith("sRGB_original." + ext))) && (!(name.endsWith("AdobeRGB_original." + ext)))
                return (false);
            }
        };
        System.out.println("ORDER" + Order.getAbsolutePath());
        model1.removeAllElements();
        //HIER WIRD DER FOTOPFAD FESTGELEGT PER HARDCODE...DA FÜR MAC BITTE UNTERE SCHREIBEWEISE NUTZEN DIE VON WINDOWS ABWEICHT
        File o = new File(fss);
        System.out.println(fss);
        File[] files = o.listFiles(IMAGE_FILTER);

        for (File f : files) {
            model1.addElement(f.getName());
            System.out.println(f);
        }
        jList1.removeAll();
        jList1.setModel(model1);

//	scroll.removeAll();
        //scroll.add(jList1);
        addListener();

    }

    public void refreshImageIcon() throws IOException {

        String tif = "tif";
        String tif2 = "tiff";

        String ext2 = uri.substring(uri.lastIndexOf(".") + 1);

        if (ext2.toLowerCase().equals(tif) || ext2.toLowerCase().equals(tif2)) {

            FileSeekableStream stream = null;
            try {
                stream = new FileSeekableStream(uri);
            } catch (IOException e) {
                e.printStackTrace();

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

            BufferedImage result = image2.getAsBufferedImage();

            ImageIcon background = new ImageIcon(result);
            //JLabel label = new JLabel();

            background.setImage(background.getImage().getScaledInstance(200, 113, Image.SCALE_DEFAULT));
            lblNewLabel.setIcon(background);
            lblNewLabel.updateUI();
            contentPane.add(lblNewLabel);
            contentPane.repaint();
            System.out.println("REPAINTED");

        } else {

            try {
                ImageIcon background = new ImageIcon(uri);
                //JLabel label = new JLabel();

                background.setImage(background.getImage().getScaledInstance(200, 113, Image.SCALE_DEFAULT));
                lblNewLabel.setIcon(background);
                lblNewLabel.updateUI();
                contentPane.add(lblNewLabel);
                contentPane.repaint();
                System.out.println("REPAINTED");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public synchronized void autoCloseFrame() {
        this.timer.cancel(); //this will cancel the current task. if there is no active task, nothing happens
        this.timer = new Timer();

        TimerTask action = new TimerTask() {
            public void run() {
                sander.frame.dispose();
            }

        };

        this.timer.schedule(action, 5000); //this starts the task
    }

    public static synchronized void raiseCounterAAndPic() {
        if (!besetzt) {
            besetzt = true;
            counterZ = counterZ + 1;
            if (counterZ >= counter) {
                counterZ = 0;
            };
            bimage = bufferedImages[counterZ];

            besetzt = false;
        }
    }

}
