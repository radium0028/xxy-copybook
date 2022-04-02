package com.gitee.radium.xxycopybook.dict;

import lombok.AllArgsConstructor;

/**
 * 模板常用的尺寸枚举
 */
@AllArgsConstructor
public enum TemplateSize implements EnumInterface<String, Integer> {
    //A4纸张的宽度
    A4_WIDTH("A4_WIDTH", 2480),
    //A4纸张的高度
    A4_HEIGHT("A4_HEIGHT", 3508),
    //单元格的默认尺寸
    CELL_WIDTH("CELL_WIDTH", 180),
    CELL_HEIGHT("CELL_HEIGHT", 180),
    //拼音格的牧人尺寸
    PINYIN_WIDTH("PINYIN_WIDTH", 180),
    PINYIN_HEIGHT("PINYIN_HEIGHT", 80),
    ;

    private String code;
    private Integer value;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}