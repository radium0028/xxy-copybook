# xxy-copybook

#### 介绍
在孩子刚上小学的时候就想给孩子弄个字帖生成器，让孩子多练练字，因为他爸爸写字不好看，就想让孩子写字能漂亮点，种种原因啊，孩子都4年级了才给字帖生成器写完，希望还来得及。
\
这个是我从字帖生成的核心类库，给大家开源出来，希望也能帮我一些想我一样的朋友。
\
我的B站地址：https://space.bilibili.com/1159595523
\
掘金地址：https://juejin.cn/user/3505696643488151

#### 主要功能
- 支持定义字帖表格样式。田字格、横线、边框多种样式可自定义搭配使用。
- 支持扩展自定义字帖单元格样式。
- 支持拼音字帖。
- 自定义使用的字体。
- 文字过多时，可自动分页生成多页字帖。
- 多种使用样式，可以一字一行，也可以一行多字。

#### 软件架构
- 字帖单元格使用装饰器模式设计，要实现自己的线段样式需要实现AbstractCellDecorator，要实现文字样式需要实现AbstractCellTextDecorator。
在cell.line中是已经实现的线段装饰，cell.text是已经实现的文字装饰。
- 字帖的生成使用建造者模式，CopybookDirector是建造者的导向器，要实现自己的模板需要实现AbstractCopybookBuilder。

- 核心样式的装饰器UML导图
  ![装饰器类图](https://images.gitee.com/uploads/images/2022/0710/143837_3cb6be19_18180.png "1657432805444-ebb79f40-a413-4f7f-bc1d-8e71e13287a3.png")

- 所有类的UML导图
![输入图片说明](https://images.gitee.com/uploads/images/2022/0710/143733_77a4fa35_18180.png "1657432965905-df5b05c8-74d1-425b-92c3-82d58afe049a.png")

  
#### 使用说明
Maven仓库坐标：
```xml
<dependency>
  <groupId>io.github.radium0028</groupId>
  <artifactId>xxy-copybook</artifactId>
  <version>1.0-Release</version>
</dependency>
```

**大家可以将自己喜欢的字体放到resources/fonts文件夹内，因为版权问题，我这里不能提供字体了，大家自己收集吧。**
\
希望尽快使用起来的小伙伴可以看一下这两个类：
- CopybookTemplate用来设置字帖样式，包括字帖的背景色，默认的边线等。
- CopybookData用来设置字帖数据，这里放置字帖上要显示的文字，拼音，字帖头、尾的内容。
一个基本的字帖生成代码：
```java
//设置显示的文字
String text = "屈渊孟甫韩愈禹锡仲龚";
//字体名字
String fontName = "嗡阿吽－田英章钢笔楷书简";

CopybookTemplate.CopybookTemplateBuilder copybookTemplateBuilder = CopybookTemplate.builder()
        .emptyCellNum(2)
        .textLineStroke(StrokeForCell.LINE)
        //单元格使用一个边框+田字格样式。
        .textCellLineStyle(CollUtil.toList(LineStyle.BORDER, LineStyle.TIAN));
//给边框格一个加粗的边线
copybookTemplateBuilder.textLineStrokeMap(MapUtil
        .builder(LineStyle.BORDER.getValue(), StrokeForCell.LINE_BOLD)
        .build());
Font font = new Font(fontName, Font.PLAIN, 140);
copybookTemplateBuilder.font(font);
//设置模板数据
CopybookTemplate copybookStyle = copybookTemplateBuilder.pagePadding(new Integer[]{10,10,10,200}).build();
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
    FileUtil.writeBytes(output.toByteArray(),
            new File(outputPath+"construct.png"));
} catch (Exception e) {
    e.printStackTrace();
}
```
#### 总体使用流程是这样的：
1. 通过CopybookTemplate设置模板样式。
2. 通过CopybookData设置模板数据。
3. 使用模板的BaseCopybook来生成字帖。
更多使用方法可以查看test中的AppTest。

#### 参与贡献
个人闲暇时间的作品，还有很多不完善的地方，欢迎大家提交代码。
1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 实例
- 基础字帖
![基础字帖](https://images.gitee.com/uploads/images/2022/0710/142530_436ee55b_18180.png "construct.png")
- 带拼音的字帖
![有拼音的字帖](https://images.gitee.com/uploads/images/2022/0710/142559_20512dd0_18180.png "constructPinyin0.png")
- 设置字帖的头尾内容
![设置字帖头尾内容](https://images.gitee.com/uploads/images/2022/0710/142623_e0b58c23_18180.png "constructHeaderAndFooter0.png")
- 自动多页字帖
  - 第一页
![多页字帖第一页](https://images.gitee.com/uploads/images/2022/0710/142649_dcd15968_18180.png "constructMore0.png")
  - 第二页
![多页字帖第二页](https://images.gitee.com/uploads/images/2022/0710/142707_0e9e788e_18180.png "constructMore1.png")