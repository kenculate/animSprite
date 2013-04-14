/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package atlasgen;

/**
 *
 * @author ken
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class imagePanel extends JPanel{

    public Image image;

    public imagePanel() {
//       new ImageIcon    
    }

    public void setImage(String imgStr)
    {
        try {
            image = ImageIO.read(new File(imgStr));
//            g2.drawImage(img, 0, 0,this);
//            this.scrollRectToVisible(new Rectangle(image.getWidth(this), image.getHeight(this)));
            repaint();
        }
        catch (IOException e) {

        }
    }

    public int getImageWidth()
    {
        return image.getWidth(null);
    }

    public int getImageheight()
    {
        return image.getHeight(null);
    }
    @Override
//    public void paint(Graphics g) {
//        BufferedImage img = null;
//        Graphics2D g2 = (Graphics2D)g;
////            img = ImageIO.read(new File("./generatedShader/teapot-maya.jpg"));
////            g2.drawImage(img, 0, 0,this);
//            g.drawImage(image, 0, 0, 200, 200, this);
//
//    }
     
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        int iw = this.getWidth();
        int ih = this.getHeight();
//        int iw = AtlasGenView.imageWidth;
//        int ih = AtlasGenView.imageheight;
        int ix = AtlasGenView.imageSplitX;
        int iy = AtlasGenView.imageSplitY;
        int isx = (int)(iw/ix);
        int isy = (int)(ih/iy);

        if (image != null)
        {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);

            int sx = this.getWidth();
            int sy = this.getHeight();

            
            float nx = (float)(iw/sx);
            float ny = (float)(iw/sx);
            System.out.print(iw + " " + ih  + " " + sx + " " + sy  + " " + nx + " X " + ny + "\n");
            int frame = 1;
            if (ix > 0 && iy > 0)
            {
                for(int j=0; j<iy; j++)
                {
                    for(int i=0; i<ix; i++)
                    {
                        g.setFont(new Font("Arial", Font.BOLD, 24));
                        g.setColor(Color.white);
                        g.drawRect(i*isx, j*isy, isx, isy);
//                        g.drawRect((int)(i*isx*nx), (int)(j*isy*ny), (int)(isx*nx), (int)(isy*ny));
                        
                        g.setColor(new Color(0.2f, 0.2f, 1.0f));
                        g.drawString(Integer.toString(frame-1), i*isx+(isx/2), j*isy+(isy/2));
                        frame++;
                    }
                }
            }
        }
        else
        {
            for(int i=0; i<ix; i++)
            {
                for(int j=0; j<iy; j++)
                {
                    g.drawRect(i*isx, j*isy, isx, isy);
                }
            }
        }
    }

}
