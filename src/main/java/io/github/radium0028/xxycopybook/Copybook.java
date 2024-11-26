package io.github.radium0028.xxycopybook;

import io.github.radium0028.xxycopybook.cell.AbstractCell;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * CopyBook ，字帖类
 * @author radium
 */
public class Copybook {

    /**
     * 字帖的单元格
     */
    @Getter
    @Setter
    private AbstractCell textAbstractCell;
    @Getter
    @Setter
    private AbstractCell pinyinAbstractCell;
    @Setter
    private List<BufferedImage> bufferedImage;

    /**
     * 输出第一张图片
     *
     * @return
     */
    public BufferedImage exportFirstImage() {
        if (bufferedImage == null) {
            return null;
        }
        return bufferedImage.get(0);
    }

    /**
     * 输出图片图片内容
     *
     * @return
     */
    public List<BufferedImage> exportImage() {
        if (bufferedImage == null) {
            return null;
        }
        return bufferedImage;
    }

    /**
     * 输出pdf
     *
     * @return
     */
    public File exportPdf() {
        if (bufferedImage == null) {
            return null;
        }
        return null;
    }
}