package com.gitee.radium.xxycopybook.cell;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 装饰器的抽象类，这里结合模板方法，控制画笔的关闭
 *
 * @author radium
 */
public abstract class AbstractCellDecorator extends AbstractCell {
    protected AbstractCell abstractCell;

    public AbstractCellDecorator(AbstractCell abstractCell) {
        super(Color.BLACK);
        this.abstractCell = abstractCell;
        this.basicStroke = abstractCell.basicStroke;
        this.width = abstractCell.width;
        this.height = abstractCell.height;
    }

    public AbstractCellDecorator(AbstractCell abstractCell, Color color) {
        super(color);
        this.abstractCell = abstractCell;
        this.basicStroke = abstractCell.basicStroke;
        this.width = abstractCell.width;
        this.height = abstractCell.height;
    }

    public AbstractCellDecorator(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(Color.BLACK);
        this.abstractCell = abstractCell;
        this.basicStroke = basicStroke;
        this.width = abstractCell.width;
        this.height = abstractCell.height;
    }

    public AbstractCellDecorator(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
        super(color);
        this.abstractCell = abstractCell;
        this.basicStroke = basicStroke;
        this.width = abstractCell.width;
        this.height = abstractCell.height;
    }

    /**
     * 这里结合模板方法，又装饰器的抽象类关闭画板资源。
     * 实现类应避免实现draw方法。
     *
     * @return
     */
    @Override
    public BufferedImage draw() {
        if (abstractCell != null) {
            BufferedImage draw = this.abstractCell.draw();
            Graphics2D graphics2D = draw.createGraphics();

            this.drawLine(graphics2D);

            graphics2D.dispose();
            return draw;
        }
        return null;
    }

    /**
     * 具体的装饰器需要实现这个方法，来自己画线。
     *
     * @param graphics2D
     */
    protected abstract void drawLine(Graphics2D graphics2D);
}