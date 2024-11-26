package io.github.radium0028.xxycopybook.cell;

import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 单元格的基类
 * 定义单元格的尺寸、大小、画笔等属性。
 * 暴露画图方法。
 *
 * @author radium
 */
public abstract class AbstractCell {
    @Getter
    protected Integer width;
    @Getter
    protected Integer height;
    @Getter
    protected Color color;
    @Getter
    protected BasicStroke basicStroke;

    public AbstractCell(Color color) {
        this.color = color;
    }

    /**
     * 画出图像
     *
     * @return
     */
    public abstract BufferedImage draw();

    @Override
    public String toString() {
        return "AbstractCell{" +
                "width=" + width +
                ", height=" + height +
                ", color=" + color +
                ", basicStroke=" + basicStroke +
                '}';
    }
}