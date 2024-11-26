package io.github.radium.xxycopybook;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import io.github.radium0028.xxycopybook.BaseCopybook;
import io.github.radium0028.xxycopybook.Copybook;
import io.github.radium0028.xxycopybook.CopybookDirector;
import io.github.radium0028.xxycopybook.cell.AbstractCellDecorator;
import io.github.radium0028.xxycopybook.cell.StrokeForCell;
import io.github.radium0028.xxycopybook.dict.LineStyle;
import io.github.radium0028.xxycopybook.material.CopybookData;
import io.github.radium0028.xxycopybook.material.CopybookStyle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 语文课本写字表，人教版一年级下，2024年1月第8次印刷
 */
public class YuwenShiZiBiao
{
    private static final String outputPath = AbstractCellDecorator.class.getClassLoader().getResource("fonts").getFile() +"/../../output/";
    private static final Logger logger = LoggerFactory.getLogger(YuwenShiZiBiao.class);
    @BeforeAll
    static void before() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //注册所有字体文件
        URL fonts = AbstractCellDecorator.class.getClassLoader().getResource("fonts");
        try {
            logger.debug("fonts.getFile(): {}", fonts.getFile());
            logger.debug("font path: {}", fonts.toURI().getRawPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String file = fonts.getFile();
        File fontsPath = new File(fonts.getFile());

        Optional.ofNullable(fontsPath).ifPresent(fp -> {
            Arrays.stream(fp.listFiles()).forEach(fontFile -> {
                try {
                    Font font = Font.createFont(Font.PLAIN, fontFile);
                    logger.debug("registering font {}", font);
                    ge.registerFont(font);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        boolean isHeadless = ge.isHeadlessInstance();
        logger.debug("GraphicsEnvironment headless?"+isHeadless);

        Font[] availableFonts = ge.getAllFonts();
        logger.debug("============Fonts in GraphicsEnvironment  are "+availableFonts.length + "============");

        for (Font font : availableFonts) {
            String fontName = font.getFontName();
           // logger.debug(fontName);
        }
        logger.debug("==============================end before（）===============");
    }

    /**
     * 第1课
     */
    @Test
    void constructHF_1() {
        //需要些的字
        String text = "春,冬,风,雪,花,飞,入";
        String pinyin = "chūn,dōng,fēng,xuě,huā,fēi,rù";

        construct(text, pinyin,"1");
    }



    private  void construct( String text, String pinyin,String title) {
        //字体名字
        String fontName = "KaiTi";
//        String fontName = "Ramega ZhangQingpingYingbiXingshu";

        CopybookStyle.CopybookStyleBuilder copybookStyleBuilder = CopybookStyle.builder()
                .textLineStroke(StrokeForCell.LONG_DOTTED_LINE)
                .cellMargin(new Integer[]{10, 0, 0, 0})
                //单元格使用一个边框+田字格样式。
                .textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.TIAN,LineStyle.XCELL));

        //设置页头高度为80
        copybookStyleBuilder.headerHeight(200);
        //设置页尾高度为50
        copybookStyleBuilder.footerHeight(200);

        //拼音设置
        copybookStyleBuilder.showPinyin(true)
                .pinyinFont(new Font("Mengshen-Handwritten",Font.PLAIN,50))
                .pinyinCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.PINYINCELL));
        copybookStyleBuilder.pinyinLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)
                .put(LineStyle.PINYINCELL.getValue(), StrokeForCell.DOTTED_LINE)
                .put(LineStyle.XCELL.getValue(), StrokeForCell.DOTTED_LINE)
                .build()
        );
        //给边框格一个加粗的边线
        copybookStyleBuilder.textLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)
                .build());
        //设置字体
        copybookStyleBuilder.font(new Font(fontName, Font.PLAIN, 140));

        //设置页边距
        copybookStyleBuilder.pagePadding(new Integer[]{10,10,10,200});

        //完整的文字显示
        int wordCount = text.length();
        logger.info("完整的文字显示 wordCount: {}", wordCount);
        copybookStyleBuilder.fullWordNum(Integer.valueOf(wordCount));

        //临摹的文字显示几个
        copybookStyleBuilder.copyWordNum(0);
        copybookStyleBuilder.emptyCellNum(0).rowCellNum(wordCount).rowNum(wordCount).rowCellNum(wordCount);
        //生成模板的样式数据
        CopybookStyle copybookStyle = copybookStyleBuilder.build();
        logger.info("字帖样式数据（CopybookTemplate）："+String.valueOf(copybookStyle));

        //设置标题、汉字信息
        CopybookData copybookData = CopybookData.builder()
                .title("一年级下-" + title)
                .wordList(CollUtil.toList(text.split(",")))
                .pinyinList(CollUtil.toList(pinyin.split(",")))
                .build();
        logger.info("字帖汉字数据（CopybookData）："+String.valueOf(copybookData));

        BaseCopybook baseCopybook = new BaseCopybook(copybookStyle, copybookData);
        CopybookDirector director = new CopybookDirector(baseCopybook);
        try {
            Copybook construct = director.buildCopybook();
            List<BufferedImage> bufferedImage = construct.exportImage();
            CollUtil.forEach(bufferedImage, (v, i) -> {
                //输出图像
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                try {
                    ImageIO.write(v, "png", output);

                    //图像写到硬盘
                    String imgName =  StrUtil.format("constructHF_"+ title+"_{}.png", i);
                    File constructFile = new File(outputPath + imgName);
                    //write
                    FileUtil.writeBytes(output.toByteArray(), constructFile);
                    logger.debug("Write file to " + constructFile.getAbsolutePath());
                    //open for user view
                    Desktop.getDesktop().open(constructFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}