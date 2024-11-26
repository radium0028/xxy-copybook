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
 * Unit test for simple App.
 */
public class AppTest
{
    private static final String outputPath = AbstractCellDecorator.class.getClassLoader().getResource("fonts").getFile() +"/../../output/";
//    private static final String fontPath =   "/Users/Think/Documents/dev/xxy-copybook/src/main/resources/fonts";
    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);
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
     * 快速的创建一个字帖模板。
     */
    @Test
    void construct() {
        //需要些的字
        String text = "绿遍山原白满川子规声里雨如烟";
        //字体名字
        String fontName = "KaiTi";

        CopybookStyle.CopybookStyleBuilder copybookTemplateBuilder = CopybookStyle.builder()
                .emptyCellNum(2)
                .textLineStroke(StrokeForCell.LINE)
                //单元格使用一个边框+田字格样式。
                .textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.TIAN))
                //.textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.PINYIN3CELL))
                ;
        //给边框格一个加粗的边线
        copybookTemplateBuilder.textLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)

                .build());
        Font font = new Font(fontName, Font.PLAIN, 140);
        copybookTemplateBuilder.font(font);
        //设置模板数据
        CopybookStyle copybookStyle = copybookTemplateBuilder.pagePadding(new Integer[]{10,10,10,200}).build();
        CopybookData copybookData = CopybookData.builder()
                .author("Radium")
                .wordList(CollUtil.toList(text.split("")))
                .build();

        BaseCopybook baseCopybook = new BaseCopybook(copybookStyle, copybookData);
        CopybookDirector director = new CopybookDirector(baseCopybook);
        try {
            Copybook construct = director.buildCopybook();
            BufferedImage bufferedImage = construct.exportFirstImage();
            //输出图像
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", output);
            File constructFile = new File(outputPath+"construct.png");
            FileUtil.writeBytes(output.toByteArray(), constructFile);
            logger.debug("Write file to " + constructFile.getAbsolutePath());
            Desktop.getDesktop().open(constructFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void constructPinyin() {
        //需要些的字
        String text = "潮,称,盐,笼,罩,蒙,薄,雾,昂,沸,贯,旧,恢,潮,称,盐,笼,罩,蒙,薄,雾,昂,沸,贯,旧,恢";
        String pinyin = "cháo,chēng,yán,lóng,zhào,méng,báo,wù,áng,fèi,guàn,jiù,huī,cháo,chēng,yán,lóng,zhào,méng,báo,wù,áng,fèi,guàn,jiù,huī";
        //字体名字
        String fontName = "瑞美加张清平硬笔行书";
        CopybookStyle.CopybookStyleBuilder copybookTemplateBuilder = CopybookStyle.builder()
                .textLineStroke(StrokeForCell.DOTTED_LINE)
                .cellMargin(new Integer[]{10, 0, 0, 0})
                //单元格使用一个边框+田字格样式。
                .textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.TIAN));

        //拼音设置
        copybookTemplateBuilder.showPinyin(true)
                .pinyinFont(new Font("Aa扁黑拼音体 (非商业使用)",Font.PLAIN,50))
                .pinyinCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.PINYINCELL));
        copybookTemplateBuilder.pinyinLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)
                .put(LineStyle.PINYINCELL.getValue(), StrokeForCell.DOTTED_LINE).build());
        //给边框格一个加粗的边线
        copybookTemplateBuilder.textLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)
                .build());
        //设置字体
        copybookTemplateBuilder.font(new Font(fontName, Font.PLAIN, 140));
        //设置模板数据
        CopybookStyle copybookStyle = copybookTemplateBuilder.pagePadding(new Integer[]{10,10,10,200}).build();
        CopybookData copybookData = CopybookData.builder()
                .author("Radium")
                .wordList(CollUtil.toList(text.split(",")))
                .pinyinList(CollUtil.toList(pinyin.split(",")))
                .build();

        BaseCopybook baseCopybook = new BaseCopybook(copybookStyle, copybookData);
        CopybookDirector director = new CopybookDirector(baseCopybook);
        try {
            Copybook construct = director.buildCopybook();
            java.util.List<BufferedImage> bufferedImage = construct.exportImage();
            CollUtil.forEach(bufferedImage, (v,i) -> {
                //输出图像
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                try {
                    ImageIO.write(v, "png", output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileUtil.writeBytes(output.toByteArray(),
                        new File(outputPath + StrUtil.format("constructPinyin{}.png",i)));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试一个多页字帖
     */
    @Test
    void constructMore() {
        //需要些的字
        String text = "锄禾日当午汗滴禾下土谁知盘中餐粒粒皆辛苦李白";
        //字体名字
        String fontName = "KaiTi";

        CopybookStyle.CopybookStyleBuilder copybookTemplateBuilder = CopybookStyle.builder()
                .emptyCellNum(2)
                .textLineStroke(StrokeForCell.LINE)
                //单元格使用一个边框+田字格样式+对角线。
                .textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.TIAN,LineStyle.XCELL));
        //设置田字格的线是虚线
        //给边框格一个加粗的边线
        //给田字格设置成虚线
        //对角线也是虚线
        copybookTemplateBuilder.textLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)
                .put(LineStyle.TIAN.getValue(), StrokeForCell.DOTTED_LINE)
                .put(LineStyle.XCELL.getValue(), StrokeForCell.DOTTED_LINE)
                .build());
        Font font = new Font(fontName, Font.PLAIN, 140);
        copybookTemplateBuilder.font(font);
        //设置模板数据
        CopybookStyle copybookStyle = copybookTemplateBuilder.pagePadding(new Integer[]{10,10,10,200}).build();
        CopybookData copybookData = CopybookData.builder()
                .author("Radium")
                .wordList(CollUtil.toList(text.split("")))
                .build();

        BaseCopybook baseCopybook = new BaseCopybook(copybookStyle, copybookData);
        CopybookDirector director = new CopybookDirector(baseCopybook);
        try {
            Copybook construct = director.buildCopybook();
            java.util.List<BufferedImage> bufferedImage = construct.exportImage();
            CollUtil.forEach(bufferedImage, (v,i) -> {
                //输出图像
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                try {
                    ImageIO.write(v, "png", output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileUtil.writeBytes(output.toByteArray(),
                        new File(outputPath + StrUtil.format("constructMore{}.png",i)));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试画页头和页尾
     */
    @Test
    void constructHeaderAndFooter() {
        //需要些的字
        String text = "潮,称,盐,笼,罩,蒙";
        String pinyin = "cháo,chēng,yán,lóng,zhào,méng";
        //字体名字
        String fontName = "方正仿宋-简体";
        CopybookStyle.CopybookStyleBuilder copybookTemplateBuilder = CopybookStyle.builder()
                .textLineStroke(StrokeForCell.DOTTED_LINE)
                .cellMargin(new Integer[]{10, 0, 0, 0})
                //单元格使用一个边框+田字格样式。
                .textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.TIAN));

        //设置页头高度为80
        copybookTemplateBuilder.headerHeight(200);
        //设置页尾高度为50
        copybookTemplateBuilder.footerHeight(200);

        //拼音设置
        copybookTemplateBuilder.showPinyin(true)
                .pinyinFont(new Font("Mengshen-Handwritten",Font.PLAIN,50))
                .pinyinCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.PINYINCELL));
        copybookTemplateBuilder.pinyinLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)
                .put(LineStyle.PINYINCELL.getValue(), StrokeForCell.DOTTED_LINE)
                .put(LineStyle.XCELL.getValue(), StrokeForCell.DOTTED_LINE)
                .build()
                );
        //给边框格一个加粗的边线
        copybookTemplateBuilder.textLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)
                .build());
        //设置字体
        copybookTemplateBuilder.font(new Font(fontName, Font.PLAIN, 140));
        //设置模板数据
        CopybookStyle copybookStyle = copybookTemplateBuilder.pagePadding(new Integer[]{10,10,10,200}).build();
        CopybookData copybookData = CopybookData.builder()
                .title("一年级下学期12")
                .author("Radium")
                .wordList(CollUtil.toList(text.split(",")))
                .pinyinList(CollUtil.toList(pinyin.split(",")))
                .build();

        BaseCopybook baseCopybook = new BaseCopybook(copybookStyle, copybookData);
        CopybookDirector director = new CopybookDirector(baseCopybook);
        try {
            Copybook construct = director.buildCopybook();
            List<BufferedImage> bufferedImage = construct.exportImage();
            CollUtil.forEach(bufferedImage, (v,i) -> {
                //输出图像
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                try {
                    ImageIO.write(v, "png", output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileUtil.writeBytes(output.toByteArray(),
                        new File(outputPath + StrUtil.format("constructHeaderAndFooter{}.png",i)));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试一个多页字帖
     */
    @Test
    void constructMoreAndHF() {
        //需要些的字
        String text = "白日衣衫尽黄河入海";
        //字体名字
        String fontName = "Ramega ZhangQingpingYingbiXingshu";

        CopybookStyle.CopybookStyleBuilder copybookTemplateBuilder = CopybookStyle.builder()
                .emptyCellNum(2)
                .textLineStroke(StrokeForCell.LINE)
                //单元格使用一个边框+田字格样式+对角线。
                .textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.TIAN,LineStyle.XCELL));
        //设置田字格的线是虚线
        //给边框格一个加粗的边线
        //给田字格设置成虚线
        //对角线也是虚线
        copybookTemplateBuilder.textLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)
                .put(LineStyle.TIAN.getValue(), StrokeForCell.DOTTED_LINE)
                .put(LineStyle.XCELL.getValue(), StrokeForCell.DOTTED_LINE)
                .build());

        //设置页头高度为80
        copybookTemplateBuilder.headerHeight(200);
        //设置页尾高度为50
        copybookTemplateBuilder.footerHeight(200);


        Font font = new Font(fontName, Font.PLAIN, 120);
        copybookTemplateBuilder.font(font);
        //设置模板数据
        CopybookStyle copybookStyle = copybookTemplateBuilder.build();
        CopybookData copybookData = CopybookData.builder()
                .title("一年级语文下12课")
                .author("Radium")
                .wordList(CollUtil.toList(text.split("")))
                .build();

        BaseCopybook baseCopybook = new BaseCopybook(copybookStyle, copybookData);
        CopybookDirector director = new CopybookDirector(baseCopybook);
        try {
            Copybook construct = director.buildCopybook();
            java.util.List<BufferedImage> bufferedImage = construct.exportImage();
            CollUtil.forEach(bufferedImage, (v,i) -> {
                //输出图像
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                try {
                    ImageIO.write(v, "png", output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileUtil.writeBytes(output.toByteArray(),
                        new File(outputPath + StrUtil.format("constructMoreAdnHF{}.png",i)));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 一年级下语文-14课
     */
    @Test
    void construct_14() {
        //需要些的字
        String text = "直呀边呢吗吧加";
        //字体名字
        String fontName = "方正仿宋-简体";

        CopybookStyle.CopybookStyleBuilder copybookTemplateBuilder = CopybookStyle.builder()
                .emptyCellNum(2)
                .textLineStroke(StrokeForCell.LINE)
                //单元格使用一个边框+田字格样式。
                .textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.TIAN))
                //.textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.PINYIN3CELL))
                ;
        //给边框格一个加粗的边线
        copybookTemplateBuilder.textLineStrokeMap(MapUtil
                .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)

                .build());
        Font font = new Font(fontName, Font.PLAIN, 140);
        copybookTemplateBuilder.font(font);
        //设置模板数据
        CopybookStyle copybookStyle = copybookTemplateBuilder.pagePadding(new Integer[]{10,10,10,200}).build();
        CopybookData copybookData = CopybookData.builder()
                .author("Radium")
                .wordList(CollUtil.toList(text.split("")))
                .build();

        BaseCopybook baseCopybook = new BaseCopybook(copybookStyle, copybookData);
        CopybookDirector director = new CopybookDirector(baseCopybook);
        try {
            Copybook construct = director.buildCopybook();
            BufferedImage bufferedImage = construct.exportFirstImage();
            //输出图像
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", output);
            File constructFile = new File(outputPath+"construct.png");
            FileUtil.writeBytes(output.toByteArray(), constructFile);
            logger.debug("Write file to " + constructFile.getAbsolutePath());
            Desktop.getDesktop().open(constructFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}