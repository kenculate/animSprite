/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package atlasgen;

/**
 *
 * @author ken
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class scrollImagePanel extends JPanel{

    public Image bkgImage;

    public scrollImagePanel() {
//       new ImageIcon
        
    }
    public void setImage(String imgStr)
    {
        try {
            bkgImage = ImageIO.read(new File(imgStr));
            this.setAutoscrolls(true);
            ImageIcon ii = new ImageIcon(bkgImage);
            JLabel jl = new JLabel(ii);
            JScrollPane js = new JScrollPane(jl);
            add(js);
            
//            this.setPreferredSize(new Dimension(1024, 1024));
//            this.setSize(bkgImage.getWidth(this), bkgImage.getHeight(this));
            this.setSize(1024,1024);
//            g2.drawImage(img, 0, 0,this);
//            this.scrollRectToVisible(new Rectangle(bkgImage.getWidth(this), bkgImage.getHeight(this)));
//            this.add(bkgImage);
//            this.imageUpdate(bkgImage, 0, 0, 0, 100, 100);
            repaint();
        }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public int getImageWidth()
    {
        return bkgImage.getWidth(null);
    }

    public int getImageheight()
    {
        return bkgImage.getHeight(null);
    }
    @Override
//    public void paint(Graphics g) {
//        BufferedImage img = null;
//        Graphics2D g2 = (Graphics2D)g;
////            img = ImageIO.read(new File("./generatedShader/teapot-maya.jpg"));
////            g2.drawImage(img, 0, 0,this);
//            g.drawImage(bkgImage, 0, 0, 200, 200, this);
//
//    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2 = (Graphics2D)g;

        int iw = AtlasGenView.imageWidth;
        int ih = AtlasGenView.imageheight;
        int ix = AtlasGenView.imageSplitX;
        int iy = AtlasGenView.imageSplitY;

        g2.drawImage(bkgImage, 0, 0, null);
//        g.drawImage(bkgImage, 0, 0, iw, ih, null);
//        g.drawImage(bkgImage, 0, 0, this.getWidth(), this.getHeight(), null);

        int isx = (int)(iw/ix);
        int isy = (int)(ih/iy);
//        System.out.print(isx + " X " + isy + "\n");
//        if (ix > 0 && iy > 0)
//        {
//            for(int i=0; i<ix; i++)
//            {
//                for(int j=0; j<iy; j++)
//                {
//                    g.drawRect(i*isx, j*isy, isx, isy);
//                }
//            }
//        }
    }

}
