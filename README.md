# xxy-copybook

#### 介绍
一个生成字帖的类库。目前可以生成多张图片样式的字帖文件。
CopybookTemplate用来设置字帖样式。
CopybookData用来设置字帖数据。

#### 软件架构
- 字帖单元格使用装饰器模式设计，要实现自己的线段样式需要实现AbstractCellDecorator，要实现文字样式需要实现AbstractCellTextDecorator。
在cell.line中是已经实现的线段装饰，cell.text是已经实现的文字装饰。
- 字帖的生成使用建造者模式，CopybookDirector是建造者的导向器，要实现自己的模板需要实现AbstractCopybookBuilder。

#### 安装教程

1.  maven clean package install

#### 使用说明
详细使用方法参见test中的AppTest

1. 通过CopybookTemplate设置模板样式。
2. 通过CopybookData设置模板数据。
3. 使用模板的BaseCopybook来生成字帖。

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request