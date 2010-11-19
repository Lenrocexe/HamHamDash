package jgame.platform;
import jgame.*;
import jgame.impl.JGameError;
import jgame.JGPoint;
import jgame.JGImage;

import java.awt.*;
import java.util.*;
import java.awt.image.*;
import java.net.*;
import java.lang.reflect.*;
/** Some handy utilities for loading an manipulating images, bypassing Java's
 * object disoriented way of handling images; used internally by jgame. */
class JREImage implements JGImage {

	// use default component here?
	static Component output_comp=null;
	static MediaTracker mediatracker=null;
	DummyObserver observer = new DummyObserver();

	static Hashtable loadedimages = new Hashtable(); /* filenames => Images */

	class DummyObserver implements ImageObserver {
		public DummyObserver() {}
		public boolean imageUpdate(Image img, int infoflags,
		int x,int y, int width,int height) { return true; }
	}

	public void setComponent(Component comp) {
		output_comp=comp;
		mediatracker = new MediaTracker(output_comp);
		loadedimages = new Hashtable();
	}

	public Image img=null;

	/** used only for platforms using textures, otherwise null */
	public Object texture=null, stretched_texture=null;

	/** Create new image */
	JREImage (Image img) { this.img=img; }
	/** Create handle to image functions. */
	JREImage () {}

	/** Load image from resource path (using getResource).  Note that GIFs are
	 * loaded as _translucent_ indexed images.   Images are cached: loading
	 * an image with the same name twice will get the cached image the second
	 * time.  If you want to remove an image from the cache, use purgeImage.
	* Throws JGError when there was an error. */
	public JGImage loadImage(String imgfile) {
		Image img = (Image)loadedimages.get(imgfile);
		if (img==null) {
			URL imgurl = getClass().getResource(imgfile);
			if (imgurl==null) throw new JGameError(
					"File "+imgfile+" not found.",true);
			img = output_comp.getToolkit().createImage(imgurl);
			loadedimages.put(imgfile,img);
		}
		try {
			ensureLoaded(img);
		} catch (Exception e) {
			throw new JGameError("Error loading image "+imgfile );
		}
		return new JREImage(img);
	}

	/** Behaves like loadImage(String).  Returns null if there was an error. */
	public static JGImage loadImage(URL imgurl) {
		Image img = (Image)loadedimages.get(imgurl);
		if (img==null) {
			img = output_comp.getToolkit().createImage(imgurl);
			loadedimages.put(imgurl,img);
		}
		try {
			ensureLoaded(img);
		} catch (Exception e) {
			System.err.println("Error loading image "+imgurl);
			return null;
		}
		return new JREImage(img);
	}

	/** Purge image with the given resourcename from the cache. */
	public void purgeImage(String imgfile) {
		if (loadedimages.containsKey(imgfile)) loadedimages.remove(imgfile);
	}


	public JGPoint getSize() {
		return new JGPoint(
			img.getWidth(observer),
			img.getHeight(observer) );
	}

	/** true means the image has some transparent pixels below the given
	 * alpha threshold, false means image is completely opaque. It actually
	 * checks the alpha channel pixel for pixel.  */
	public boolean isOpaque(int alpha_thresh) {
		int [] alpha = getPixels();
		for (int i=0; i<alpha.length; i++) {
			if ( ((alpha[i]>>24)&0xff) < alpha_thresh)
				return false;
		}
		return true;
	}

	/** Get plain old rgba8 pixels.  
	* Why isn't there a built in method for this? */
	public int [] getPixels() {
		JGPoint size = getSize();
		int [] buffer = new int [size.x * size.y];
		PixelGrabber grabber = new PixelGrabber(img, 0, 0,
				size.x,size.y, buffer, 0, size.x);
		grabPixels(grabber);
		return buffer;
	}

	public int [] getPixels(int x,int y,int width,int height) {
		int [] buffer = new int [width * height];
		PixelGrabber grabber = new PixelGrabber(img, x, y,
				width,height, buffer, 0, width);
		grabPixels(grabber);
		return buffer;
	}

	private void grabPixels(PixelGrabber grabber) {
		try {
			grabber.grabPixels();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		//if (grabber.getColorModel() != ColorModel.getRGBdefault()) {
		//	System.err.println("Warning: found other colormodel than default.");
		//}
	}

	/** for angle, only increments of 90 are allowed */
	public JGImage rotate(int angle) {
		Image rot = null;
		JGPoint size = getSize();
		int [] buffer = getPixels();
		int [] rotate = new int [size.x * size.y];
		int angletype = (angle/90) & 3;
		if (angletype==0) return this;
		if (angletype==1) {
			/* 1 2 3 4    9 5 1
			 * 5 6 7 8 => a 6 2
			 * 9 a b c    b 7 3 
			 *            c 8 4 */
			for(int y = 0; y < size.y; y++) {
				for(int x = 0; x < size.x; x++) {
					rotate[x*size.y + (size.y-1-y) ] =
							buffer[(y*size.x)+x];
				}
			}
			return new JREImage(output_comp.createImage(
				new MemoryImageSource(size.y,size.x,
					rotate, 0, size.y) ) );
		}
		if (angletype==3) {
			/* 1 2 3 4    4 8 c
			 * 5 6 7 8 => 3 7 b
			 * 9 a b c    2 6 a 
			 *            1 5 9 */
			for(int y = 0; y < size.y; y++) {
				for(int x = 0; x < size.x; x++) {
					rotate[(size.x-x-1)*size.y + y] =
							buffer[(y*size.x)+x];
				}
			}
			return new JREImage( output_comp.createImage(
				new MemoryImageSource(size.y,size.x,
					rotate, 0, size.y) ) );
		}
		if (angletype==2) {
			/* 1 2 3 4    c b a 9
			 * 5 6 7 8 => 8 7 6 5
			 * 9 a b c    4 3 2 1 */
			for(int y = 0; y < size.y; y++) {
				for(int x = 0; x < size.x; x++) {
					rotate[((size.y-y-1)*size.x)+(size.x-x-1)] =
							buffer[(y*size.x)+x];
				}
			}
		}
		return new JREImage( output_comp.createImage(
			new MemoryImageSource(size.x,size.y,
				rotate, 0, size.x) ) );
	}

	public JGImage flip(boolean horiz,boolean vert) {
		Image flipped = null;
		JGPoint size = getSize();
		int [] buffer = getPixels();
		int [] flipbuf = new int [size.x * size.y];
		if (vert && !horiz) {
			for(int y = 0; y < size.y; y++) {
				for(int x = 0; x < size.x; x++) {
					flipbuf[(size.y-y-1)*size.x + x] =
							buffer[(y*size.x)+x];
				}
			}
		} else if (horiz && !vert) {
			for(int y = 0; y < size.y; y++) {
				for(int x = 0; x < size.x; x++) {
					flipbuf[y*size.x + (size.x-x-1)] =
							buffer[(y*size.x)+x];
				}
			}
		} else if (horiz && vert) {
			for(int y = 0; y < size.y; y++) {
				for(int x = 0; x < size.x; x++) {
					flipbuf[(size.y-y-1)*size.x + (size.x-x-1)] =
							buffer[(y*size.x)+x];
				}
			}
		}
		return new JREImage(output_comp.createImage(
			new MemoryImageSource(size.x,size.y,
				flipbuf, 0, size.x) ) );
	}
	/** Returns a smoothly scaled image using getScaledInstance.  This method
	 * has interesting behaviour.  The scaled image retains its type
	 * (indexed/rgb and bitmask/translucent), and the algorithm tries to scale
	 * smoothly within these constraints.  For indexed, interpolated pixels
	 * are rounded to the existing indexed colours.  For bitmask, the
	 * behaviour depends on the platform.  On WinXP/J1.2 I found that the
	 * colour _behind_ each transparent pixel is used to interpolate between
	 * nontransparent and transparent pixels.  On BSD/J1.4 I found that the
	 * colours of transparent pixels are never used, and only the
	 * nontransparent pixels are used when interpolating a region with mixed
	 * transparent/nontransparent pixels.
	 */
	public JGImage scale(int width, int height) {
		//BufferedImage dstimg = createCompatibleImage(width,height);
		//BufferedImage srcimg = toBuffered(img);
		Image scaledimg=img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
		try {
			/* this is necessary for scaled images too */
			ensureLoaded(scaledimg);
		} catch (Exception e) {
			System.err.println("Error scaling image.");
		}
		return new JREImage(scaledimg);
	}
	public static void ensureLoaded(Image img) throws Exception {
		//System.err.println("In ensureloaded");
		mediatracker.addImage(img,0);
		try {
			mediatracker.waitForAll();
			if (mediatracker.getErrorsAny()!=null) {
				mediatracker.removeImage(img);
				throw new Exception("Error loading image");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mediatracker.removeImage(img);
		//System.err.println("Out ensureloaded");
	}

	public JGImage crop(int x,int y, int width,int height) {
		JGPoint size = getSize();
		int [] buffer = getPixels(x,y, width,height);
		return new JREImage( output_comp.createImage(
			new MemoryImageSource(width,height,
				buffer, 0, width) ) );
	}


	/* extra methods */

//	/** Create display-compatible bitmask BufferedImage version of image for
//	* faster drawing; returns the identical object if it was
//	* already a bitmask BufferedImage.  For X11, you have to do this so that
//	* you get pixmaps instead of ximages.  The alpha channel is thresholded
//	* using the given value between 0 and 255.  The borders around the image
//	* are rendered to bg_col. 
//	*/
//	public BufferedImage toBuffered(Image img,int alpha_thresh,Color bg_col) {
//		if (img instanceof BufferedImage) {
//			// XXX in theory, we should also check if display compatible
//			if ( ((BufferedImage)img).getColorModel().getTransparency()
//			== Transparency.BITMASK ) {
//				return (BufferedImage)img;
//			}
//		}
//		img = toCompatibleBitmask(img,alpha_thresh,bg_col,false);
//		/* see the developer's almanac,
//		* http://javaalmanac.com/egs/java.awt.image/Image2Buf.html,
//		* for docs */
//		Point size = getSize(img);
//		BufferedImage bimage = createCompatibleImage(size.x,size.y,
//			Transparency.BITMASK);
//		Graphics g = bimage.createGraphics();
//		g.drawImage(img, 0, 0, null);
//		g.dispose();
//		return bimage;
//	}


	/** Turn a (possibly) translucent or indexed image into a
	* display-compatible bitmask image using the given alpha threshold and
	* render-to-background colour, or display-compatible translucent image.
	* The alpha values in the
	* image are set to either 0 (below threshold) or 255 (above threshold).
	* The render-to-background colour bg_col is used to determine how the
	* pixels overlapping transparent pixels should be rendered.  The fast
	* algorithm just sets the colour behind the transparent pixels in the
	* image (for bitmask source images); the slow algorithm actually
	* renders the image to a background of bg_col (for translucent sources).
	*
	* @param thresh alpha threshold between 0 and 255
	* @param fast use fast algorithm (only set bg_col behind transp. pixels)
	* @param bitmask true=use bitmask, false=use translucent */
	public JGImage toDisplayCompatible(int thresh,JGColor bg_col,
	boolean fast, boolean bitmask) {
		Color bgcol = new Color(bg_col.r,bg_col.g,bg_col.b);
		int bgcol_rgb = (bgcol.getRed()<<16) | (bgcol.getGreen()<<8)
						| bgcol.getBlue();
		JGPoint size = getSize();
		int [] buffer = getPixels();
		// render image to bg depending on bgcol
		BufferedImage img_bg;
		if (bitmask) {
			img_bg=createCompatibleImage(size.x,size.y,Transparency.BITMASK);
		} else {
		   img_bg=createCompatibleImage(size.x,size.y,Transparency.TRANSLUCENT);
		}
		int [] bg_buf;
		if (!fast) {
			Graphics g=img_bg.getGraphics();
			g.setColor(bgcol);
			// the docs say I could use bgcol in the drawImage as an
			// equivalent to the following two lines, but this
			// doesn't handle translucency properly and is _slower_
			g.fillRect(0,0,size.x,size.y);
			g.drawImage(img,0,0,null);
			bg_buf = new JREImage(img_bg).getPixels();
		} else {
			bg_buf=buffer;
		}
		//g.dispose();
		//ColorModel rgb_bitmask = ColorModel.getRGBdefault();
		//rgb_bitmask = new PackedColorModel(
		//		rgb_bitmask.getColorSpace(),25,0xff0000,0x00ff00,0x0000ff,
		//		0x1000000, false, Transparency.BITMASK, DataBuffer.TYPE_INT);
//		ColorSpace space, int bits, int rmask, int gmask, int bmask, int amask, boolean isAlphaPremultiplied, int trans, int transferType) 
		int [] thrsbuf = new int [size.x * size.y];
		for(int y = 0; y < size.y; y++) {
			for(int x = 0; x < size.x; x++) {
				if ( ( (buffer[y*size.x+x] >> 24) & 0xff ) >= thresh) {
					thrsbuf[y*size.x+x]=bg_buf[y*size.x+x]|(0xff<<24);
				} else {
					// explicitly set the colour of the transparent pixel.
					// This makes a difference when scaling!
					//thrsbuf[y*size.x+x]=bg_buf[y*size.x+x]&~(0xff<<24);
					thrsbuf[y*size.x+x]=bgcol_rgb;
				}
			}
		}
		return new JREImage( output_comp.createImage(
			new MemoryImageSource(size.x,size.y,
				//rgb_bitmask,
				img_bg.getColorModel(), // display compatible bitmask
				bitmask ? thrsbuf : bg_buf, 0, size.x) ) );
	}

	/** Create empty image with given alpha mode that should be efficient on
	* this display */
	public static BufferedImage createCompatibleImage(int width,int height,
	int transparency) {
		//try {
		GraphicsEnvironment ge =
				GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gs = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gs.getDefaultConfiguration();
		// always use bitmask transparency
		BufferedImage bimage = gc.createCompatibleImage(
				width, height, transparency);
		return bimage;
		//} catch (HeadlessException e) { // this exception is not in 1.2
			// The system does not have a screen
		//	e.printStackTrace();
		//	return null;
		//}
	}

	/** Create RGBA-8 image from ints (each int = AABBGGRR).
	* why isn't there a built in method to do this? */
	public static BufferedImage createRGBA8Image(int[] pix,
	int width,int height) {
		BufferedImage img = new BufferedImage(width,height,
			BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0,0,width,height, pix, 0, width);
		return img;
	}

	// the following is ugly version-conditional execution.  What we really
	// want is to have a 1.4 and a <1.4 version of the following two methods.
	// Drawbacks are now that IMAGE_INCOMPATIBLE is coded as a hard number and 
	// applets cannot do reflection on volatile images for some reason and
	// hence do not have acceleration.

	/** Create possibly volatile scratch image for fast painting.  A scratch
	 * image can become invalidated, when this happens any actions involving
	 * it are ignored, and it needs to be recreated. Use isScratchImageValid()
	 * to check this. */
	public static Image createScratchImage(int width, int height) {
		try {
			Image img = (Image)tryMethod(output_comp,"createVolatileImage",
				new Object[] {new Integer(width),new Integer(height)} );
			if (img==null) {
				// no such method -> create regular image
				return output_comp.createImage(width,height);
			}
			//if (img.validate(output_comp.getGraphicsConfiguration())
			//== VolatileImage.IMAGE_INCOMPATIBLE) {
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			Integer valid = (Integer)tryMethod(img,"validate",new Object[] {
				gc });
				//output_comp.getGraphicsConfiguration() });
			if (valid.intValue() == 2) { // I checked, IMAGE_INCOMPATIBLE=2
				// Hmm, somehow it didn't work. Create regular image.
				return output_comp.createImage(width,height);
			}
			return img;
		} catch (java.security.AccessControlException e) {
			// we're not allowed to do this (we're probably an applet)
			return output_comp.createImage(width,height);
		}
	}

	public static boolean isScratchImageValid(Image img) {
		try {
			Boolean lost=(Boolean)tryMethod(img,"contentsLost",new Object[]{});
			if (lost==null) return true; // method does not exist->not volatile
			//System.out.println("volatile");
			return !lost.booleanValue();
		} catch (java.security.AccessControlException e) {
			// we're not allowed to do this (we're probably an applet)
			// we assume we created a nonvolatile image
			return true;
		}
	}

	/** Try to execute given method on given object.  Handles invocation
	 * target exceptions. XXX nearly duplicates tryMethod in JGEngine.
	 * @return null means method does not exist or returned null/void */
	static Object tryMethod(Object o,String name,Object [] args) {
		try {
			Method met=JREEngine.getMethod(o.getClass(),name,args);
			if (met==null) return null;
			return met.invoke(o,args);
		} catch (InvocationTargetException ex) {
			Throwable ex_t = ex.getTargetException();
			ex_t.printStackTrace();
			return null;
		} catch (IllegalAccessException ex) {
			System.err.println("Unexpected exception:");
			ex.printStackTrace();
			return null;
		}
	}


}

