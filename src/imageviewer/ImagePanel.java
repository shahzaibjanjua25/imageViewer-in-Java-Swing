/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author shahz
 */
 public class ImagePanel extends JPanel {
            public Image image;
            double rotationAngle = 0.0;
            public double zoomFactor = 1.0;

            public ImagePanel() {
                setPreferredSize(new Dimension(600, 400));
            }

            public void setImage(Image image) {
                this.image = image;
                repaint();
            }
@Override

protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        int imageWidth = (int) (zoomFactor * image.getWidth(this));
        int imageHeight = (int) (zoomFactor * image.getHeight(this));
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        g2d.translate(x + imageWidth / 2, y + imageHeight / 2);
        g2d.rotate(Math.toRadians(rotationAngle));
        g2d.translate(-imageWidth / 2, -imageHeight / 2);
        g2d.drawImage(image, 0, 0, imageWidth, imageHeight, this);
        g2d.dispose();
    }
}



        }
    

