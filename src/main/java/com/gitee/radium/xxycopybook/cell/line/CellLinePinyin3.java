package com.gitee.radium.xxycopybook.cell.line;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import com.gitee.radium.xxycopybook.cell.AbstractCell;
import com.gitee.radium.xxycopybook.cell.AbstractCellDecorator;
import com.gitee.radium.xxycopybook.cell.StrokeForCell;

import java.awt.*;
import java.util.Optional;

/**
 * 拼音格3虚线
 */
public class CellLinePinyin3 extends AbstractCellDecorator {

    public CellLinePinyin3(AbstractCell abstractCell) {
        super(abstractCell);
    }

    public CellLinePinyin3(AbstractCell abstractCell, Color color) {
        super(abstractCell, color);
    }

    public CellLinePinyin3(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(abstractCell, basicStroke);
    }

    public CellLinePinyin3(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
        super(abstractCell, color, basicStroke);
    }

    @Override
    protected void drawLine(Graphics2D graphics2D) {
        //画笔颜色
        Color color = Optional.ofNullable(this.color).orElse(abstractCell.getColor());
        graphics2D.setColor(color);

        BasicStroke basicStroke = Optional.ofNullable(this.basicStroke).orElse(StrokeForCell.DOTTED_LINE);
        graphics2D.setStroke(basicStroke);

        Integer width = Optional.ofNullable(this.width).orElse(abstractCell.getWidth());
        Integer height = Optional.ofNullable(this.height).orElse(abstractCell.getHeight());
//        log.debug("CellLinePinyin，width:{},height:{}",width,height);

        double line1Y = NumberUtil.div((float) this.height, 3f);
        double line2Y = NumberUtil.div((float) this.height, 3f) * 2;

        //画两条横线
        graphics2D.drawLine(0, Convert.toInt(line1Y), width, Convert.toInt(line1Y));
        graphics2D.drawLine(0, Convert.toInt(line2Y), width, Convert.toInt(line2Y));
    }
}