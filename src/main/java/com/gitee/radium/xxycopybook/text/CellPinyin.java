package com.gitee.radium.xxycopybook.text;

import cn.hutool.core.util.NumberUtil;
import com.gitee.radium.xxycopybook.cell.AbstractCell;
import com.gitee.radium.xxycopybook.cell.AbstractCellTextDecorator;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.util.Optional;

/**
 * @author radium
 */
public class CellPinyin extends AbstractCellTextDecorator {
    public CellPinyin(AbstractCell abstractCell) {
        super(abstractCell);
    }

    public CellPinyin(AbstractCell abstractCell, Color color) {
        super(abstractCell, color);
    }

    public CellPinyin(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(abstractCell, basicStroke);
    }

    public CellPinyin(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
        super(abstractCell, color, basicStroke);
    }

    public CellPinyin(AbstractCell abstractCell, Font font, Color color) {
        super(abstractCell, font, color);
    }

    @Override
    protected void drawLine(Graphics2D graphics2D) {
        if (font == null) {
            throw new NullPointerException("字体未设置");
        }
        graphics2D.setFont(font);
        graphics2D.setColor(fontColor);

        Integer width = Optional.ofNullable(this.width).orElse(abstractCell.getWidth());
        Integer height = Optional.ofNullable(this.height).orElse(abstractCell.getHeight());

        //抗据此
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 计算文字长度，计算居中的x点坐标

        //计算宽度
        FontMetrics fm = graphics2D.getFontMetrics();
        Rectangle2D rc = fm.getStringBounds(this.text, graphics2D);
        float offset = (float) NumberUtil.div((float) (width - rc.getWidth()), 2f);

        //计算高度
        FontRenderContext context = graphics2D.getFontRenderContext();
        LineMetrics lineMetrics = font.getLineMetrics(text, context);
        float y = (height + lineMetrics.getAscent() - lineMetrics.getDescent() - lineMetrics.getLeading()) / 2;

        graphics2D.drawString(text, offset, y);
    }
}