package io.github.radium0028.xxycopybook;

import io.github.radium0028.xxycopybook.cell.AbstractCell;
import io.github.radium0028.xxycopybook.material.CopybookData;
import io.github.radium0028.xxycopybook.material.CopybookStyle;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 字帖的抽象类，定义了字帖的基本构成和公共方法
 */
public abstract class AbstractCopybook {
    /**
     * 要生成的字帖
     */
    Copybook copybook;
    /**
     * 模板信息
     */
    CopybookStyle copybookStyle;
    /**
     * 字帖数据内容
     */
    CopybookData copybookData;

    public AbstractCopybook(CopybookStyle copybookStyleTemplate, CopybookData copybookDataTemplate) {
        this.copybookStyle = copybookStyleTemplate;
        this.copybookData = copybookDataTemplate;
    }

    /**
     * 构建一个基础的布局
     */
    public abstract BufferedImage createBasic();

    /**
     * 构建文字单元格
     *
     * @return 返回文字格的单元格
     */
    public abstract AbstractCell createTextCell();

    /**
     * 构建拼音单元格
     *
     * @return 返回拼音格的单元格样式
     */
    public abstract AbstractCell createPinyinCell();

    /**
     * 创建每行的Cell
     *
     * @return 返回行内容
     */
    public abstract List<RowData> createRow(AbstractCell textAbstractCell, AbstractCell pinyinAbstractCell) throws Exception;

    /**
     * 组合行数据集
     *
     * @param rowDataList
     * @return 返回的是每行的数据
     */
    public abstract List<BufferedImage> builderRow(List<RowData> rowDataList);

    /**
     * 创建头部内容
     */
    public abstract BufferedImage createHeader();

    /**
     * 创建尾部信息
     */
    public abstract BufferedImage createFooter();

    /**
     * 使用行图像，组合出页图像。
     *
     * @param basicImage  页面底图框架
     * @param headerImage 头部信息
     * @param footerImage 尾部信息
     * @param rowsList    每行的字帖信息
     * @return 返回的就是每页的图像的数据
     */
    public abstract List<BufferedImage> builderPage(
            BufferedImage basicImage,
            BufferedImage headerImage,
            BufferedImage footerImage,
            List<BufferedImage> rowsList);

    public Copybook build() {
        return copybook;
    }
}