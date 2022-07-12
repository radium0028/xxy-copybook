package io.github.radium0028.xxycopybook.cell.line;

import cn.hutool.core.convert.Convert;
import io.github.radium0028.xxycopybook.cell.AbstractCell;
import io.github.radium0028.xxycopybook.cell.AbstractCellDecorator;
import io.github.radium0028.xxycopybook.cell.StrokeForCell;

import java.awt.*;
import java.util.Optional;

/**
 * @author radium
 */
public class CellLineBorderTop extends AbstractCellDecorator {

    public CellLineBorderTop(AbstractCell abstractCell) {
        super(abstractCell);
    }

    public CellLineBorderTop(AbstractCell abstractCell, Color color) {
        super(abstractCell, color);
    }

    public CellLineBorderTop(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(abstractCell, basicStroke);
    }

    public CellLineBorderTop(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
        super(abstractCell, color, basicStroke);
    }

    @Override
    protected void drawLine(Graphics2D graphics2D) {
        //画笔颜色
        Color color = Optional.ofNullable(this.color).orElse(abstractCell.getColor());
        graphics2D.setColor(color);

        BasicStroke basicStroke = Optional.ofNullable(this.basicStroke).orElse(StrokeForCell.LINE_BOLD);
        graphics2D.setStroke(basicStroke);

        Integer width = Optional.ofNullable(this.width).orElse(abstractCell.getWidth());
        Integer height = Optional.ofNullable(this.height).orElse(abstractCell.getHeight());
//        log.debug("CellLineBorder，width:{},height:{}",width,height);
        //对坐标便宜线段宽度的一半，做到在图像内画线。
        Integer lineWidth = Convert.toInt(basicStroke.getLineWidth()) / 2;
        //画边框
        graphics2D.drawLine(0, 0 + lineWidth, width, 0 + lineWidth);
    }
}