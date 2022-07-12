package io.github.radium0028.xxycopybook;


import io.github.radium0028.xxycopybook.cell.AbstractCell;

import java.util.HashMap;
import java.util.Map;

/**
 * 每行的单元格数据，包括文字单元格和拼音单元格。
 * rowList中的索引是列索引
 * Cell数组中索引0的是文字单元格，索引1的是拼音单元格
 */
public class RowData {
    private Map<Integer, AbstractCell[]> columnList;
    private int maxColumn;

    public RowData() {
        columnList = new HashMap<>();
        maxColumn = 0;
    }

    public AbstractCell[] pull(int rowIndex) {
        return columnList.get(rowIndex);
    }

    /**
     * 返回多少列
     *
     * @return
     */
    public int columnCount() {
        return maxColumn;
    }

    /**
     * 插入文字行。
     *
     * @param textAbstractCell
     */
    public void push(AbstractCell textAbstractCell) {
        columnList.put(maxColumn, new AbstractCell[]{textAbstractCell});
        maxColumn++;
    }

    /**
     * 插入文字行和注音行
     *
     * @param textAbstractCell
     * @param pinyinAbstractCell
     */
    public void push(AbstractCell textAbstractCell, AbstractCell pinyinAbstractCell) {
        columnList.put(maxColumn, new AbstractCell[]{textAbstractCell, pinyinAbstractCell});
        maxColumn++;
    }
}