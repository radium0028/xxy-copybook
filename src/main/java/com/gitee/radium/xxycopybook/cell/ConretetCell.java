package com.gitee.radium.xxycopybook.cell;

import com.gitee.radium.xxycopybook.dict.TemplateSize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * 被装饰的单元格，这里就处理个透明背景
 *
 * @author radium
 */
public class ConretetCell extends AbstractCell {
    public ConretetCell() {
        super(Color.BLACK);
    }

    public ConretetCell(int width, int height) {
        super(Color.BLACK);
        this.width = width;
        this.height = height;
    }

    public ConretetCell(Color color) {
        super(Optional.ofNullable(color).orElse(Color.BLACK));
    }

    public ConretetCell(Color color, int width, int height) {
        super(Optional.ofNullable(color).orElse(Color.BLACK));
        this.width = width;
        this.height = height;
    }

    @Override
    public BufferedImage draw() {
        Integer width = Optional.ofNullable(this.width).orElse(TemplateSize.CELL_WIDTH.getValue());
        Integer height = Optional.ofNullable(this.height).orElse(TemplateSize.CELL_HEIGHT.getValue());

//        log.debug("ConreteCell，width:{},height:{}",width,height);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = image.createGraphics();
        //透明背景
        image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g.dispose();
        return image;
    }
}