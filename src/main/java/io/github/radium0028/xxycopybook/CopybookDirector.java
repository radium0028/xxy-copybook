package io.github.radium0028.xxycopybook;

import io.github.radium0028.xxycopybook.cell.AbstractCell;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author radium
 */
@AllArgsConstructor
public class CopybookDirector {
    @Setter
    AbstractCopybookBuilder abstractCopybookBuilder;

    public Copybook buildCopybook() throws Exception {
        //一个基础模板样式
        BufferedImage basic = abstractCopybookBuilder.createBasic();
        BufferedImage header = abstractCopybookBuilder.createHeader();
        BufferedImage footer = abstractCopybookBuilder.createFooter();
        AbstractCell textAbstractCell = abstractCopybookBuilder.createTextCell();
        AbstractCell pinyinAbstractCell = abstractCopybookBuilder.createPinyinCell();
        List<RowData> rowDataList = abstractCopybookBuilder.createRow(textAbstractCell, pinyinAbstractCell);
        List<BufferedImage> rowImage = abstractCopybookBuilder.builderRow(rowDataList);
        List<BufferedImage> bufferedImages = abstractCopybookBuilder.builderPage(basic, header, footer, rowImage);
        abstractCopybookBuilder.copybook = new Copybook();
        abstractCopybookBuilder.copybook.setPinyinAbstractCell(pinyinAbstractCell);
        abstractCopybookBuilder.copybook.setTextAbstractCell(textAbstractCell);
        abstractCopybookBuilder.copybook.setBufferedImage(bufferedImages);

        return abstractCopybookBuilder.build();
    }
}