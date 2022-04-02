package com.gitee.radium.xxycopybook.material;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 模板的数据信息，存放要写入的字帖的文字、拼音、标题头等信息。
 *
 * @author radium
 */
@Builder
@Data
public class CopybookData {
    /**
     * 标题
     */
    @Builder.Default
    String title = "X字帖自动生成";
    @Builder.Default
    String subtitle = "姓名：       日期：      ";
    /**
     * 作者
     */
    @Builder.Default
    String author = "Radium";
    /**
     * 二维码图片的地址
     */
    String qrcode;
    /**
     * 版权
     */
    String copyright;

    List<String> wordList;

    List<String> pinyinList;
}