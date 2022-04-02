package com.gitee.radium.xxycopybook.cell;

import cn.hutool.core.util.ReUtil;

import java.awt.*;
import java.util.Optional;

public abstract class AbstractCellTextDecorator extends AbstractCellDecorator {
    /**
     * 字体信息
     */
    protected Font font;
    /**
     * 字体颜色
     */
    protected Color fontColor;

    /**
     * 要写的文字
     */
    protected String text;

    public AbstractCellTextDecorator(AbstractCell abstractCell) {
        super(abstractCell);
    }

    public AbstractCellTextDecorator(AbstractCell abstractCell, Font font, Color color) {
        super(abstractCell);
        this.font = font;
        this.fontColor = color;
    }

    public AbstractCellTextDecorator(AbstractCell abstractCell, Color color) {
        super(abstractCell, color);
    }

    public AbstractCellTextDecorator(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(abstractCell, basicStroke);
    }

    public AbstractCellTextDecorator(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
        super(abstractCell, color, basicStroke);
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void setText(String text) {
        String s = Optional.ofNullable(text).orElse("");
        if (ReUtil.isMatch("[\\u4e00-\\u9fa5]", s)) {
            if (s.length() != 1) {
                throw new RuntimeException("最多设置一个文字");
            }
        }
        this.text = s;

    }
}