package io.github.radium0028.xxycopybook.material;

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
    String title = "";
    @Builder.Default
    String subtitle = "姓名：        ";
    /**
     * 作者
     */
    @Builder.Default
    String author = "";
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

    @Override
    public String toString() {
        return "CopybookData{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", author='" + author + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", copyright='" + copyright + '\'' +
                ", wordList=" + wordList +
                ", pinyinList=" + pinyinList +
                '}';
    }
}