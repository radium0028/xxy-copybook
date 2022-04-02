package com.gitee.radium.xxycopybook.cell.line;


import com.gitee.radium.xxycopybook.cell.AbstractCell;
import com.gitee.radium.xxycopybook.cell.AbstractCellDecorator;
import com.gitee.radium.xxycopybook.cell.StrokeForCell;

import java.awt.*;
import java.util.Optional;

/**
 * X形状的虚线的装饰器
 */
public class CellLineX extends AbstractCellDecorator {
    public CellLineX(AbstractCell abstractCell) {
        super(abstractCell);
    }

    public CellLineX(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(abstractCell, basicStroke);
    }

    public CellLineX(AbstractCell abstractCell, Color color) {
        super(abstractCell, color);
    }

    public CellLineX(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
        super(abstractCell, color, basicStroke);
    }

    @Override
    protected void drawLine(Graphics2D graphics2D) {
        //默认虚线
        BasicStroke basicStroke = Optional.ofNullable(this.basicStroke).orElse(StrokeForCell.DOTTED_LINE);
        //羽化一点
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(basicStroke);

        Color color = Optional.ofNullable(this.color).orElse(abstractCell.getColor());
        graphics2D.setColor(color);

        Integer width = Optional.ofNullable(this.width).orElse(abstractCell.getWidth());
        Integer height = Optional.ofNullable(this.height).orElse(abstractCell.getHeight());

//        log.debug("CellLineX，width:{},height:{}",width,height);
        graphics2D.drawLine(0, 0, width, height);
        graphics2D.drawLine(width, 0, 0, height);
    }
}