package com.gitee.radium.xxycopybook.dict;

import lombok.AllArgsConstructor;

/**
 * 页面表格线段的格式枚举
 */
@AllArgsConstructor
public enum LineStyle implements EnumInterface<String, String> {
    //边框
    BORDER("BORDER", "com.gitee.radium.xxycopybook.cell.line.CellLineBorder"),
    //田字格
    TIAN("TIAN", "com.gitee.radium.xxycopybook.cell.line.CellLineTian"),
    //X线
    XCELL("XCELL", "com.gitee.radium.xxycopybook.cell.line.CellLineX"),
    //拼音格
    PINYINCELL("PINYINCELL", "com.gitee.radium.xxycopybook.cell.line.CellLinePinyin"),
    //3格的拼音线，两条横线分成三格
    PINYIN3CELL("PINYINCELL", "com.gitee.radium.xxycopybook.cell.line.CellLinePinyin3"),
    //只画一条上横线
    BORDER_TOP("BORDER_TOP", "com.gitee.radium.xxycopybook.cell.line.CellLineBorderTop");

    private String code;
    private String value;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}