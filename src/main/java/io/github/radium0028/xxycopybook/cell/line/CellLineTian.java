package io.github.radium0028.xxycopybook.cell.line;

import io.github.radium0028.xxycopybook.cell.AbstractCell;
import io.github.radium0028.xxycopybook.cell.AbstractCellDecorator;
import io.github.radium0028.xxycopybook.cell.StrokeForCell;

import java.awt.*;
import java.util.Optional;

/**
 * 田字格的装饰器
 */
public class CellLineTian extends AbstractCellDecorator {
    
    public CellLineTian(AbstractCell abstractCell) {
        super(abstractCell);
    }

    public CellLineTian(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(abstractCell, basicStroke);
    }

    public CellLineTian(AbstractCell abstractCell, Color color) {
        super(abstractCell, color);
    }

    public CellLineTian(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
        super(abstractCell, color, basicStroke);
    }

    @Override
    protected void drawLine(Graphics2D graphics2d) {
        //默认虚线
        BasicStroke basicStroke = Optional.ofNullable(this.basicStroke).orElse(StrokeForCell.DOTTED_LINE);
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setStroke(basicStroke);

        Color color = Optional.ofNullable(this.color).orElse(abstractCell.getColor());
        graphics2d.setColor(color);

        Integer width = Optional.ofNullable(this.width).orElse(abstractCell.getWidth());
        Integer height = Optional.ofNullable(this.height).orElse(abstractCell.getHeight());

//        log.debug("CellLineTian，width:{},height:{}",width,height);
        graphics2d.drawLine(0, height / 2, width, height / 2);
        graphics2d.drawLine(width / 2, 0, width / 2, height);
    }
}