/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4k_avi;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author SilberPfeil
 */
public class rePainter {
    JFrame frame;
    BufferedImage het;
    
    
    rePainter(JFrame frame){
    this.frame=frame;
    }
    
    synchronized void repaintt(){
    this.frame.repaint();
    }
}
