package io.github.radium0028.xxycopybook.dict;

import lombok.AllArgsConstructor;

/**
 * 字体
 */
@AllArgsConstructor
public enum FontEnum implements EnumInterface<String, String> {
    SX_YB("SX_YB", "全新硬笔行书简"),
    HKSYT("HKSYT", "华康饰艺体W5"),
    SX_YGYGB("SX_YGYGB", "叶根友钢笔行书升级版"),
    KS_TYZ("KS_TYZ", "嗡阿吽－田英章钢笔楷书简"),
    KS_PZH("KS_PZH", "庞中华简体 V2007"),
    XS_ZQCWX("XS_ZQCWX", "钟齐陈伟勋硬笔行书字库");

    private String code;
    private String name;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return name;
    }
}