package io.github.radium0028.xxycopybook.material;


import io.github.radium0028.xxycopybook.cell.StrokeForCell;
import io.github.radium0028.xxycopybook.dict.LineStyle;
import io.github.radium0028.xxycopybook.dict.TemplateSize;
import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 这里是字帖的样式信息，用来定义字帖样式模板的尺寸，位置，颜色等信息。
 *
 * @author radium
 */
@Data
@Builder
public class CopybookStyle {
    //region 一些基本设置
    /**
     * 边框线默认样式
     */
    @Builder.Default
    BasicStroke borderStroke = StrokeForCell.LINE_BOLD;
    /**
     * 文字格默认样式，在没有配置textCellLineStyle时生效。
     */
    @Builder.Default
    BasicStroke textLineStroke = StrokeForCell.LINE;
    /**
     * 拼音格默认样式，在没有设置pinyinCellLineStyle时生效
     */
    @Builder.Default
    BasicStroke pinyinLineStroke = StrokeForCell.LINE;
    /**
     * 文字表格线样式集合
     * 对文字格设置多种样式
     */
    List<LineStyle> textCellLineStyle;
    /**
     * 拼音表格线样式
     * 对拼音格设置多种样式
     */
    List<LineStyle> pinyinCellLineStyle;
    /**
     * 模板底图
     */
    private String backgroundFile;
    /**
     * 模板背景颜色
     */
    @Builder.Default
    private Color backgroundColor = Color.white;
    /**
     * 模板宽度
     */
    @Builder.Default
    private Integer width = TemplateSize.A4_WIDTH.getValue();
    /**
     * 模板高度
     */
    @Builder.Default
    private Integer height = TemplateSize.A4_HEIGHT.getValue();
    /**
     * 表格头高度
     */
    @Builder.Default
    private Integer headerHeight = 0;
    /**
     * 表格头的下边距
     */
    @Builder.Default
    private Integer footerHeight = 0;
    /**
     * 表格尾部的高度
     */
    @Builder.Default
    private Integer headerMarginBottom = 0;
    /**
     * 表格尾部的上边距
     */
    @Builder.Default
    private Integer footerMarginTop = 0;
    //endregion
    /**
     * 距离页面内边距，和css一样的顺序上、右、下、左。
     * 目前只有上和左有效
     */
    @Builder.Default
    private Integer[] pagePadding = new Integer[]{10, 10, 10, 10};
    /**
     * 每个格子的外边距，和css一样的顺序，上、右、下、左。
     */
    @Builder.Default
    private Integer[] cellMargin = new Integer[]{0, 0, 0, 0};
    /**
     * 每行格子的数量
     */
    @Builder.Default
    private Integer rowCellNum = 11;
    /**
     * 每页有多少行
     */
    @Builder.Default
    private Integer rowNum = 16;
    /**
     * 完整的文字显示几个
     */
    @Builder.Default
    private Integer fullWordNum = 1;
    //endregion
    /**
     * 临摹的文字显示几个
     */
    @Builder.Default
    private Integer copyWordNum = 8;
    /**
     * 空格显示几个
     */
    @Builder.Default
    private Integer emptyCellNum = 2;
    /**
     * 是否显示拼音
     */
    @Builder.Default
    private boolean showPinyin = false;
    /**
     * 文字字体
     */
    @Builder.Default
    private Font font = new Font("宋体", Font.PLAIN, 140);
    /**
     * 拼音的字体
     */
    @Builder.Default
    private Font pinyinFont = new Font("黑体", Font.PLAIN, 50);
    /**
     * 文字颜色
     */
    @Builder.Default
    private Color textColor = Color.BLACK;
    /**
     * 临摹的文字颜色
     */
    @Builder.Default
    private Color copyTextColor = Color.BLACK;
    /**
     * 拼音的默认颜色
     */
    @Builder.Default
    private Color pinyinColor = Color.BLACK;
    /**
     * 文字单元格宽度
     */
    @Builder.Default
    private Integer textCellWidth = TemplateSize.CELL_WIDTH.getValue();
    /**
     * 文字单元格高度
     */
    @Builder.Default
    private Integer textCellHeight = TemplateSize.CELL_HEIGHT.getValue();
    /**
     * 拼音单元格宽度
     */
    @Builder.Default
    private Integer pinyinCellWidth = TemplateSize.PINYIN_WIDTH.getValue();
    //endregion

    //region 单元格高度和宽甸设置
    /**
     * 拼音单元格高度
     */
    @Builder.Default
    private Integer pinyinCellHeight = TemplateSize.PINYIN_HEIGHT.getValue();
    /**
     * 边框线的默认颜色
     */
    @Builder.Default
    private Color borderColor = Color.RED;
    /**
     * 文字格线段的颜色
     */
    @Builder.Default
    private Color textLineColor = Color.RED;
    /**
     * 临摹格线段的颜色
     */
    @Builder.Default
    private Color copyLineColor = Color.RED;
    //endregion

    //region 线段颜色的设置
    /**
     * 拼音格线段的颜色
     */
    @Builder.Default
    private Color pinyinLineColor = Color.RED;
    /**
     * 文字表格线样式，配合textCellLineStyle使用。
     * 配合textCellLineStyle使用，这里的key是textCellLineStyle的value
     */
    private Map<String, BasicStroke> textLineStrokeMap;
    /**
     * 拼音表格线样式，配合pinyinCellLineStyle是用。
     * 配合pinyinCellLineStyle使用，这里的key是pinyinCellLineStyle的value
     */
    private Map<String, BasicStroke> pinyinLineStrokeMap;
    /**
     * 文字表格线颜色
     * 配合textCellLineStyle使用，这里的key是textCellLineStyle的value
     */
    private Map<String, Color> textLineColorMap;
    //endregion

    //region 线段样式的设置
    /**
     * 拼音表格线颜色
     * 配合pinyinCellLineStyle使用，这里的key是pinyinCellLineStyle的value
     */
    private Map<String, Color> pinyinLineColorMap;

    //region 一些快速提取页面边距的方法。
    public Integer getPagePaddingTop() {
        if (pagePadding != null && pagePadding.length >= 1) {
            return pagePadding[0];
        }
        return null;
    }

    public Integer getPagePaddingRight() {
        if (pagePadding != null && pagePadding.length >= 2) {
            return pagePadding[1];
        }
        return null;
    }
    //endregion

    public Integer getPagePaddingBottom() {
        if (pagePadding != null && pagePadding.length >= 3) {
            return pagePadding[2];
        }
        return null;
    }

    public Integer getPagePaddingLeft() {
        if (pagePadding != null && pagePadding.length >= 4) {
            return pagePadding[3];
        }
        return null;
    }

    //region 一些快速提取表格边距的方法。
    public Integer getCellMarginTop() {
        if (cellMargin != null && cellMargin.length >= 1) {
            return cellMargin[0];
        }
        return null;
    }

    public Integer getCellMarginRight() {
        if (cellMargin != null && cellMargin.length >= 2) {
            return cellMargin[1];
        }
        return null;
    }

    public Integer getCellMarginBottom() {
        if (cellMargin != null && cellMargin.length >= 3) {
            return cellMargin[2];
        }
        return null;
    }

    public Integer getCellMarginLeft() {
        if (cellMargin != null && cellMargin.length >= 4) {
            return cellMargin[3];
        }
        return null;
    }

    @Override
    public String toString() {

        Iterator<LineStyle> iterator = textCellLineStyle.iterator();
        StringBuilder textCellLineStyleStrBuffer = new StringBuilder();
        while (iterator.hasNext()) {
            LineStyle lineStyle = iterator.next();
            textCellLineStyleStrBuffer.append(lineStyle.toString()).append(";");
        }

        Iterator<LineStyle> pinyinIterator = pinyinCellLineStyle.iterator();
        StringBuilder pinyinLineStyleStrBuffer = new StringBuilder();
        while (pinyinIterator.hasNext()) {
            LineStyle lineStyle = pinyinIterator.next();
            pinyinLineStyleStrBuffer.append(lineStyle.toString()).append(";");
        }

        return "CopybookTemplate{" +
                "边框线默认样式：borderStroke=" + borderStroke +
                ", 文字格默认样式：textLineStroke=" + textLineStroke +
                ", 拼音格默认样式：pinyinLineStroke=" + pinyinLineStroke +
                ", 文字表格线样式集合：textCellLineStyle=" + textCellLineStyleStrBuffer.toString() +
                ", 拼音表格线样式：pinyinCellLineStyle=" + pinyinLineStyleStrBuffer.toString() +
                ", backgroundFile='" + backgroundFile + '\'' +
                ", backgroundColor=" + backgroundColor +
                ", width=" + width +
                ", height=" + height +
                ", headerHeight=" + headerHeight +
                ", footerHeight=" + footerHeight +
                ", headerMarginBottom=" + headerMarginBottom +
                ", footerMarginTop=" + footerMarginTop +
                ", pagePadding=" + Arrays.toString(pagePadding) +
                ", cellMargin=" + Arrays.toString(cellMargin) +
                ", 每行格子的数量？rowCellNum=" + rowCellNum +
                ", 每页有多少行？rowNum=" + rowNum +
                ", 完整的文字显示fullWordNum个=" + fullWordNum +
                ", 临摹的文字显示几个？copyWordNum=" + copyWordNum +
                ", 空格显示几个？emptyCellNum=" + emptyCellNum +
                ", showPinyin=" + showPinyin +
                ", ====================================font=" + font +
                ", pinyinFont=" + pinyinFont +
                ", textColor=" + textColor +
                ", copyTextColor=" + copyTextColor +
                ", pinyinColor=" + pinyinColor +
                ", textCellWidth=" + textCellWidth +
                ", textCellHeight=" + textCellHeight +
                ", pinyinCellWidth=" + pinyinCellWidth +
                ", pinyinCellHeight=" + pinyinCellHeight +
                ", borderColor=" + borderColor +
                ", textLineColor=" + textLineColor +
                ", copyLineColor=" + copyLineColor +
                ", pinyinLineColor=" + pinyinLineColor +
                ", textLineStrokeMap=" + textLineStrokeMap +
                ", pinyinLineStrokeMap=" + pinyinLineStrokeMap +
                ", textLineColorMap=" + textLineColorMap +
                ", pinyinLineColorMap=" + pinyinLineColorMap +
                '}';
    }
}