package io.github.radium0028.xxycopybook.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class ImageUtil {
    public static void saveImage(BufferedImage image, String path) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileUtil.writeBytes(output.toByteArray(),
                new File(path));
    }

    /**
     * 创建一个图像，如果没有color，则创建的是透明背景
     *
     * @param width
     * @param height
     * @param color
     * @return
     */
    public static BufferedImage createImage(int width, int height, Color... color) {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = image.createGraphics();
//        //消除文字锯齿
//        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//        //消除画图锯齿
//        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (CollUtil.isEmpty(Arrays.stream(color).iterator())) {
            //透明背景
            image = graphics.getDeviceConfiguration().createCompatibleImage(width,
                    height, Transparency.TRANSLUCENT);
        } else {
            //画一个白色的背景
            graphics.setBackground(color[0]);
//            try {
//                BufferedImage img = ImageIO.read(new File("/Users/Think/Documents/dev/xxy-copybook/src/main/resources/00024-2406186233.png"));
//                if(img != null){
//                    image = img;
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            //通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
            graphics.clearRect(0, 0, image.getWidth(), image.getHeight());
        }
        graphics.dispose();
        return image;
    }

    /**
     * 一个黑色的圆点。2*2像素大小的一个格，点的位置在右下角【1，1】，方便后边平铺使用。
     *
     * @param color 可选参数。索引0是点的颜色，索引1是背景色。
     * @return
     */
    public static BufferedImage createPoint(Color... color) {
        Color pointColor = Color.BLACK;
        Color backgroupColor = Color.WHITE;
        if (CollUtil.isNotEmpty(Arrays.stream(color).iterator())) {
            if (color.length >= 1) {
                pointColor = color[0];
            }
            if (color.length >= 2) {
                backgroupColor = color[1];
            }
        }
        pointColor = Optional.ofNullable(pointColor).orElse(Color.BLACK);
        backgroupColor = Optional.ofNullable(backgroupColor).orElse(Color.WHITE);
        int size = 1;
        //创建图片对象
        //
        BufferedImage image = ImageUtil.createImage(3, 3, backgroupColor);
        Graphics2D graphics = image.createGraphics();
        //消除文字锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //消除画图锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //绘制圆形
        graphics.setColor(pointColor);
        Ellipse2D.Double ellipse = new Ellipse2D.Double(1, 1, 1, 1);

        graphics.fill(ellipse);
        graphics.dispose();
        return image;
    }

    public static BufferedImage copyImage(BufferedImage basicImage) {
        BufferedImage bimage2 = new BufferedImage(basicImage.getWidth(), basicImage.getHeight(), basicImage.getType());
        bimage2.setData(basicImage.getData());
        return bimage2;
    }


}