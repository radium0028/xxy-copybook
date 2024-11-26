package io.github.radium0028.xxycopybook.cell;

import io.github.radium0028.xxycopybook.BaseCopybook;
import io.github.radium0028.xxycopybook.dict.TemplateSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * 被装饰的单元格，这里就处理个透明背景
 *
 * @author radium
 */
public class ConretetCell extends AbstractCell {
    private static final Logger logger = LoggerFactory.getLogger(ConretetCell.class);

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
       // logger.debug("绘制田字格（ConreteCell），width:{},height:{}",width,height);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = image.createGraphics();
        //透明背景
        image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g.dispose();
        return image;
    }

    @Override
    public String toString() {
        return "ConretetCell{}"
            + "width=" + width
            +", height=" + height
            +", color=" + color
            +", basicStroke=" + basicStroke
            + '}';
    }
}