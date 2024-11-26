package io.github.radium0028.xxycopybook;

import io.github.radium0028.xxycopybook.cell.AbstractCell;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author radium
 */
@AllArgsConstructor
public class CopybookDirector {
    private static final Logger logger = LoggerFactory.getLogger(CopybookDirector.class);

    @Setter
    AbstractCopybook abstractCopybook;

    public Copybook buildCopybook() throws Exception {
        //一个基础模板样式
        BufferedImage basic = abstractCopybook.createBasic();
        //header & footer
        BufferedImage header = abstractCopybook.createHeader();
        BufferedImage footer = abstractCopybook.createFooter();
        //text cell (田字格）
        AbstractCell textAbstractCell = abstractCopybook.createTextCell();
        //Pinyin cell （三线拼音格）
        AbstractCell pinyinAbstractCell = abstractCopybook.createPinyinCell();

        //行数据
        List<RowData> rowDataList = abstractCopybook.createRow(textAbstractCell, pinyinAbstractCell);
        List<BufferedImage> rowImage = abstractCopybook.builderRow(rowDataList);


        //创建页面Image
        List<BufferedImage> bufferedImages = abstractCopybook.builderPage(basic, header, footer, rowImage);

        abstractCopybook.copybook = new Copybook();
        abstractCopybook.copybook.setPinyinAbstractCell(pinyinAbstractCell);
        abstractCopybook.copybook.setTextAbstractCell(textAbstractCell);
        abstractCopybook.copybook.setBufferedImage(bufferedImages);

        return abstractCopybook.build();
    }
}