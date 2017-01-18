package pkg4k_avi;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import static pkg4k_avi.StartGui.WIDTH;

public class DrawRoom extends JPanel {

    Image image;
    int WIDTH;
    int HEIGHT;

        public DrawRoom(String krass, int screen ) {
            this.image = Toolkit.getDefaultToolkit().getImage(krass); 
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		if (screen>1)screen=1;
		
		DisplayMode mode = gs[screen].getDisplayMode();
			
		  this.WIDTH = mode.getWidth();
		  this.HEIGHT = mode.getHeight();
            
        }

        public void paintComponent(Graphics g){
            g.drawImage(this.image,0,0,this.WIDTH,this.HEIGHT, this);
            System.out.println("paintComponent got cwlled");
        }

        
    @Override
        public void paint(Graphics g){
            g.drawImage(this.image,0,0,this.WIDTH,this.HEIGHT, this);
              System.out.println("paint got called");
        }

        
        public void changeImage(String whichImage){
            this.image = Toolkit.getDefaultToolkit().getImage(whichImage);
            this.repaint();
            
        }
    }