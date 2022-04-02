package com.gitee.radium.xxycopybook.cell.line;

import com.gitee.radium.xxycopybook.cell.AbstractCell;
import com.gitee.radium.xxycopybook.cell.AbstractCellDecorator;

import java.awt.*;

/**
 * 空白的拼音格
 */
public class CellLinePinyin extends AbstractCellDecorator {

    public CellLinePinyin(AbstractCell abstractCell) {
        super(abstractCell);
    }

    public CellLinePinyin(AbstractCell abstractCell, Color color) {
        super(abstractCell, color);
    }

    public CellLinePinyin(AbstractCell abstractCell, BasicStroke basicStroke) {
        super(abstractCell, basicStroke);
    }

    public CellLinePinyin(AbstractCell abstractCell, Color color, BasicStroke basicStroke) {
        super(abstractCell, color, basicStroke);
    }

    @Override
    protected void drawLine(Graphics2D graphics2D) {
    }
}