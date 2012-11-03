package teddySoft;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.PixelGrabber;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter {
    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.tiff) ||
                extension.equals(Utils.tif) ||
                extension.equals(Utils.gif) ||
                extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg) ||
                extension.equals(Utils.png)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Image Files";
    }
    
    public static Image getInputImage(){
    	Image scaleImage;
		JFileChooser fcOpen = new JFileChooser (new File ("."));
        fcOpen.addChoosableFileFilter(new ImageFilter());
        fcOpen.setAcceptAllFileFilterUsed(false);
		fcOpen.showOpenDialog (null);
		File input = fcOpen.getSelectedFile();
		if (input != null){
			try{
				BufferedImage image = ImageIO.read(input);
				int width = image.getWidth();
				int height = image.getHeight();
				double ratio = (width + 0.0)/(height+0.0);
				if (ratio < 1.0)
					scaleImage = image.getScaledInstance((int)(128*ratio), 128, Image.SCALE_DEFAULT);
				else
					scaleImage = image.getScaledInstance(128, (int)(128/ratio), Image.SCALE_DEFAULT);
			}
			catch (IOException ioe){
				scaleImage = null;
				System.out.println("Cannot convert input to image");
			}
			
			return scaleImage;
		}
		else{
			return null;
		}
    } 
}