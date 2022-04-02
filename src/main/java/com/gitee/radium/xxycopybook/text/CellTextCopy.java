package com.gitee.radium.xxycopybook.text;

import cn.hutool.core.util.NumberUtil;
import com.gitee.radium.xxycopybook.cell.AbstractCell;
import com.gitee.radium.xxycopybook.cell.AbstractCellTextDecorator;
import com.gitee.radium.xxycopybook.utils.ImageUtil;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * 描红文本
 *
 * @author radium
 */
public class CellTextCopy extends AbstractCellTextDecorator {
    public CellTextCopy(AbstractCell abstractCell) {
        super(abstractCell);
    }

    public CellTextCopy(AbstractCell abstractCell, Color color) {
        super(abstractCell, color);
    }

    public CellTextCopy(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(abstractCell, basicStroke);
    }

    public CellTextCopy(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
        super(abstractCell, color, basicStroke);
    }

    public CellTextCopy(AbstractCell abstractCell, Font font, Color color) {
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

        //计算宽度
        FontMetrics fm = graphics2D.getFontMetrics();
        Rectangle2D rc = fm.getStringBounds(this.text, graphics2D);
        float offset = (float) NumberUtil.div((float) (width - rc.getWidth()), 2f);

        //计算高度
        FontRenderContext context = graphics2D.getFontRenderContext();
        LineMetrics lineMetrics = font.getLineMetrics(text, context);
        float y = (height + lineMetrics.getAscent() - lineMetrics.getDescent() - lineMetrics.getLeading()) / 2;

        GlyphVector v = font.createGlyphVector(graphics2D.getFontRenderContext(), text);
        Shape shape = v.getOutline();

        graphics2D.translate(
                offset,
                y
        );
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.WHITE);
        //这里拿到CopybookTemplate中的描边颜色，是通过cell的fontcolor传递的
        BufferedImage textureImage = ImageUtil.createPoint(this.fontColor);
        // 图片是125x125的小图，实际填充时的循环填充直到充满
        // anchor用于指定图片的哪一部分用于填充
        Rectangle anchor = new Rectangle(0, 0, textureImage.getWidth(), textureImage.getHeight());
        Paint paint = new TexturePaint(textureImage, anchor);
        // 设置填充
        graphics2D.setPaint(paint);
        graphics2D.fill(shape);
    }
}