package com.demo.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class CropImageUtils
{
    public static final BufferedImage scale(BufferedImage bi, int width, int height, boolean flag)
    {
        try
        {
            double ratio = 0.0D;
            int w = bi.getWidth();
            int h = bi.getHeight();
            ratio = new Integer(w).doubleValue() / new Integer(h).doubleValue();
            int wDest = 0;
            int hDest = 0;
            if (flag)
            {
                if (ratio * height <= width)
                {
                    hDest = height;
                    wDest = (int) (ratio * height);

                    Image itemp = bi.getScaledInstance(wDest, hDest, 4);
                    BufferedImage image = new BufferedImage(width, height, 1);
                    Graphics2D g = image.createGraphics();
                    g.setColor(new Color(Integer.parseInt("CCCCCC", 16)));
                    g.fillRect(0, 0, width, height);
                    g.drawImage(itemp, (width - wDest) / 2, 0, wDest, hDest, Color.white, null);
                    g.dispose();

                    return image;
                }

                wDest = width;
                hDest = (int) (wDest / ratio);

                Image itemp = bi.getScaledInstance(wDest, hDest, 4);
                BufferedImage image = new BufferedImage(width, height, 1);
                Graphics2D g = image.createGraphics();
                g.setColor(new Color(Integer.parseInt("CCCCCC", 16)));
                g.fillRect(0, 0, width, height);
                g.drawImage(itemp, 0, (height - hDest) / 2, wDest, hDest, Color.white, null);
                g.dispose();

                return image;
            }

            Image itemp = bi.getScaledInstance(width, height, 4);
            BufferedImage image = new BufferedImage(width, height, 1);
            Graphics2D g = image.createGraphics();
            g.setColor(new Color(Integer.parseInt("CCCCCC", 16)));
            g.fillRect(0, 0, width, height);
            g.drawImage(itemp, 0, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
            itemp = image;
            return image;
        } catch (Exception e)
        {
            LoggerUtils.error("缩放图片出错" + e.getMessage());
        }
        return null;
    }

    public static final BufferedImage cut(BufferedImage bi, int x, int y, int width, int height)
    {
        try
        {
            int srcWidth = bi.getHeight();
            int srcHeight = bi.getWidth();
            if ((srcWidth > 0) && (srcHeight > 0))
            {
                ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
                Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(bi.getSource(), cropFilter));

                BufferedImage tag = new BufferedImage(width, height, 1);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, width, height, null);
                g.dispose();

                return tag;
            }
        } catch (Exception e)
        {
            LoggerUtils.error("切割图片出错" + e.getMessage());
        }
        return null;
    }

    public static BufferedImage rotateImage(BufferedImage bi, int degree, int x, int y)
    {
        try
        {
            int w = bi.getWidth();
            int h = bi.getHeight();
            int type = bi.getColorModel().getTransparency();
            BufferedImage img = new BufferedImage(w, h, type);
            Graphics2D graphics2d = img.createGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2d.rotate(Math.toRadians(degree), x, y);
            graphics2d.drawImage(bi, 0, 0, null);
            graphics2d.dispose();
            return img;
        } catch (Exception e)
        {
            LoggerUtils.error("旋转图片出错" + e.getMessage());
        }
        return null;
    }

    public static void rotate90DX(InputStream in, File tempFile, String imageType)
    {
        try
        {
            BufferedImage bufferedimage = ImageIO.read(in);
            int width = bufferedimage.getWidth();
            int height = bufferedimage.getHeight();
            BufferedImage dstImage = null;
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.translate(height, 0.0D);
            dstImage = new BufferedImage(height, width, bufferedimage.getType());
            affineTransform.rotate(Math.toRadians(90.0D));
            AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, 1);
            BufferedImage bi = affineTransformOp.filter(bufferedimage, dstImage);
            ImageIO.write(bi, imageType.toUpperCase(), tempFile);
        } catch (Exception e)
        {
            LoggerUtils.error(e);
        }
    }

    public static final int getInt(String text)
    {
        return (int) Float.parseFloat(text);
    }

    public static void cropzoom(InputStream is, File tempFile, String imageType, String imageRotate, String viewPortW, String viewPortH, String imageX, String imageY, String imageW, String imageH, String selectorX, String selectorY,
            String selectorW, String selectorH)
    {
        try
        {
            BufferedImage totalImage = new BufferedImage(getInt(viewPortW) + getInt(imageW) * 2, getInt(viewPortH) + getInt(imageH) * 2, 1);

            BufferedImage bi = ImageIO.read(is);

            BufferedImage tmp1 = scale(bi, getInt(imageW), getInt(imageH), false);

            Graphics2D g = totalImage.createGraphics();
            g.setColor(new Color(Integer.parseInt("CCCCCC", 16)));
            g.fillRect(0, 0, getInt(viewPortW) + getInt(imageW) * 2, getInt(viewPortH) + getInt(imageH) * 2);
            g.drawImage(tmp1, getInt(imageW) + getInt(imageX), getInt(imageH) + getInt(imageY), getInt(imageW), getInt(imageH), Color.white, null);

            g.dispose();

            BufferedImage tmp2 = rotateImage(totalImage, getInt(imageRotate), getInt(imageW) + getInt(imageX) + getInt(imageW) / 2, getInt(imageH) + getInt(imageY) + getInt(imageH) / 2);

            BufferedImage tmp3 = cut(tmp2, getInt(imageW), getInt(imageH), getInt(viewPortW), getInt(viewPortH));

            BufferedImage tmp4 = cut(tmp3, getInt(selectorX), getInt(selectorY), getInt(selectorW), getInt(selectorH));

            ImageIO.write(tmp4, imageType.toUpperCase(), tempFile);
        } catch (Exception e)
        {
            LoggerUtils.error("裁剪图片出错" + e.getMessage());
        }
    }
}