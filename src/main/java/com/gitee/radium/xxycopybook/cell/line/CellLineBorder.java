package com.gitee.radium.xxycopybook.cell.line;

import cn.hutool.core.convert.Convert;
import com.gitee.radium.xxycopybook.cell.AbstractCell;
import com.gitee.radium.xxycopybook.cell.AbstractCellDecorator;
import com.gitee.radium.xxycopybook.cell.StrokeForCell;

import java.awt.*;
import java.util.Optional;

/**
 * @author radium
 */
public class CellLineBorder extends AbstractCellDecorator {

    public CellLineBorder(AbstractCell abstractCell) {
        super(abstractCell);
    }

    public CellLineBorder(AbstractCell abstractCell, Color color) {
        super(abstractCell, color);
    }

    public CellLineBorder(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(abstractCell, basicStroke);
    }

    public CellLineBorder(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
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
        //图像内画线。
        Integer lineWidth = Convert.toInt(basicStroke.getLineWidth()) / 2;
        //画边框
        graphics2D.drawLine(0, 0 + lineWidth, width, 0 + lineWidth);
        graphics2D.drawLine(0 + lineWidth, 0, 0 + lineWidth, width);
        //这里要减掉线宽，要不然就出格了。
        graphics2D.drawLine(width - lineWidth, 0, width - lineWidth, width);
        graphics2D.drawLine(0, height - lineWidth, width, width - lineWidth);
    }
}