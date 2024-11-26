package io.github.radium0028.xxycopybook.cell;

import java.awt.*;

/**
 * 定义一些画笔
 */
public class StrokeForCell {
    /**
     * 虚线
     */
    public final static BasicStroke DOTTED_LINE = new BasicStroke(1,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            5f,
            new float[]{5, 2},
            0);
    /**
     * 实线
     */
    public final static BasicStroke LINE = new BasicStroke(1,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER
    );

    /**
     * 加粗实线
     */
    public final static BasicStroke LINE_BOLD = new BasicStroke(2,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER
    );

    /**
     * 长虚线
     */
    public final static BasicStroke LONG_DOTTED_LINE = new BasicStroke(1,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10f,
            new float[]{12, 4},
            0);

}