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
public class YiNianJiXia
{
    private static final String outputPath = AbstractCellDecorator.class.getClassLoader().getResource("fonts").getFile() +"/../../output/";
    private static final Logger logger = LoggerFactory.getLogger(YiNianJiXia.class);
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
    /**
     * 第2课
     */
    @Test
    void constructHF_2() {
        //需要些的字
        String text = "姓,什,么,双,国,王,方";
        String pinyin = "xìng,shén,me,shuāng,guó,wáng,fāng";

        construct(text, pinyin,"2");
    }


    /**
     * 第3课
     */
    @Test
    void constructHF_3() {
        //需要些的字
        String text = "青,清,气,晴,情,请,生";
        String pinyin = "xìng,shén,me,shuāng,guó,wáng,fāng";

        construct(text, pinyin,"3");
    }

    /**
     * 第4课
     */
    @Test
    void constructHF_4() {
        //需要些的字
        String text = "字,左,右,红,时,动,万";
        String pinyin = "zì,zuǒ,yòu,hóng,shí,dòng,wàn";

        construct(text, pinyin,"4");
    }


    /**
     * 第5课
     */
    @Test
    void constructHF_5() {
        //需要些的字
        String text = "字,左,右,红,时,动,万";
        String pinyin = "zì,zuǒ,yòu,hóng,shí,dòng,wàn";

        construct(text, pinyin,"5");
    }

    /**
     * 第11课
     */
    @Test
    void constructHF_11() {
        //需要些的字
        String text = "首,采,无,树,爱,尖,角";
        String pinyin = "shǒu,cǎi,wú,shù,ài,jiān,jiǎo";

        construct(text, pinyin,"11");
    }
    /**
     * 第12课
     */
    @Test
    void constructHF_12() {
        //需要些的字
        String text = "亮,机,台,放,鱼,朵,美";
        String pinyin = "liàng,jī,tái,fàng,yú,duǒ,měi";

        construct(text, pinyin,"12");
    }
    /**
     * 第13课
     */
    @Test
    void constructHF_13() {
        //需要些的字
        String text = "过,这,呀,边,吗,吧,加";
        String pinyin = "guò,zhè,yā,biān,má,bā,jiā";

        construct(text, pinyin,"13");
    }


    private  void construct( String text, String pinyin,String title) {
        //字体名字
        String fontName = "KaiTi";
//        String fontName = "Ramega ZhangQingpingYingbiXingshu";

        CopybookStyle.CopybookStyleBuilder copybookTemplateBuilder = CopybookStyle.builder()
                .textLineStroke(StrokeForCell.LONG_DOTTED_LINE)
                .cellMargin(new Integer[]{10, 0, 0, 0})
                //单元格使用一个边框+田字格样式。
                .textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.TIAN,LineStyle.XCELL));

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

        //设置页边距
        copybookTemplateBuilder.pagePadding(new Integer[]{10,10,10,200});

        //生成模板的样式数据
        CopybookStyle copybookStyle = copybookTemplateBuilder.build();
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
                    String imgName =  StrUtil.format("YiNianJiXia_HF_"+ title+"_{}.png", i);
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