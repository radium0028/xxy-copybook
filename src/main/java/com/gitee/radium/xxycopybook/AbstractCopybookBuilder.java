package com.gitee.radium.xxycopybook;

import com.gitee.radium.xxycopybook.cell.AbstractCell;
import com.gitee.radium.xxycopybook.material.CopybookData;
import com.gitee.radium.xxycopybook.material.CopybookTemplate;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class AbstractCopybookBuilder {
    /**
     * 要生成的字帖
     */
    Copybook copybook;
    /**
     * 模板信息
     */
    CopybookTemplate templateBean;
    /**
     * 字帖数据内容
     */
    CopybookData copybookData;

    public AbstractCopybookBuilder(CopybookTemplate copybookTemplate, CopybookData copybookData) {
        this.templateBean = copybookTemplate;
        this.copybookData = copybookData;
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