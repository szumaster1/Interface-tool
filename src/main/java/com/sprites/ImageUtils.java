/**
* Copyright (c) Kyle Fricilone
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.*;

/**
 * 
 * Created by Kyle Fricilone on Jun 15, 2017.
 */
public class ImageUtils {

	public static BufferedImage horizontalFlip(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage flippedImage = new BufferedImage(w, h, img.getType());
		Graphics2D g = flippedImage.createGraphics();
		g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
		g.dispose();
		return flippedImage;
	}

	public static BufferedImage verticalFlip(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage flippedImage = new BufferedImage(w, h, img.getColorModel().getTransparency());
		Graphics2D g = flippedImage.createGraphics();
		g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
		g.dispose();
		return flippedImage;
	}

	public static BufferedImage getScaledImage(BufferedImage src, double factor) {
		int finalw = src.getWidth();
		int finalh = src.getHeight();
		if (src.getWidth() > src.getHeight()) {
			factor = ((double) src.getHeight() / (double) src.getWidth());
			finalh = (int) (finalw * factor);
		}

		else {
			factor = ((double) src.getWidth() / (double) src.getHeight());
			finalw = (int) (finalh * factor);
		}

		BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(src, 0, 0, finalw, finalh, null);
		g2.dispose();
		return resizedImg;
	}

	public static BufferedImage makeColorTransparent(BufferedImage im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {

			public int markerRGB = color.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					return 0x00FFFFFF & rgb;
				} else {
					return rgb;
				}
			}
		};

		return imageToBufferedImage(
				Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(im.getSource(), filter)));
	}

	public static BufferedImage imageToBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		return bimage;
	}
	
	public static BufferedImage convert(BufferedImage src, int bufImgType) {
	    BufferedImage img= new BufferedImage(src.getWidth(), src.getHeight(), bufImgType);
	    Graphics2D g2d= img.createGraphics();
	    g2d.drawImage(src, 0, 0, null);
	    g2d.dispose();
	    return img;
	}
	
    public static BufferedImage createColoredBackground(BufferedImage image, java.awt.Color color) {
    	BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g2d = copy.createGraphics();
    	g2d.setColor(color); // Or what ever fill color you want...
    	g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
    	g2d.drawImage(image, 0, 0, null);
    	g2d.dispose();    	
    	return copy;
    }

	/**
	 * used to resize images
	 * @param img
	 * @param newW
	 * @param newH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		if(newW <= 0)
			newW = img.getWidth();
		if(newH <= 0)
			newH = img.getHeight();
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return dimg;
	}

	/**
	 * recolours the full image
	 * @param image
	 * @param col
	 * @return
	 */
	public static BufferedImage colorImage(BufferedImage image, Color col) {
		int width = image.getWidth();
		int height = image.getHeight();
		WritableRaster raster = image.getRaster();

		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				int[] pixels = raster.getPixel(xx, yy, (int[]) null);
				pixels[0] = col.getRed();
				pixels[1] = col.getGreen();
				pixels[2] = col.getBlue();
				raster.setPixel(xx, yy, pixels);
			}
		}
		return image;
	}

}