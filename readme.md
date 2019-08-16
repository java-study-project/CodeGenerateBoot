# 通用代码生成工具说明

## 特性介绍
1. 模板参数自定义：在配置文件中可以自定义模板参数，并且在模板中直接使用
2. 数据源模板参数使用自定义：系统基于表元数据类Table以及字段元数据Column可以对需要的参数进行配置化指定，在module.yml配置
3. 模板扩展参数配置化，扩展代码实现：对于模板的值以及字段的转换，提供了通用的转换器配置使用，也可以自行代码扩展ITemplateParamExtend
4. 模板文件配置化（文件名、目录自定义）创建template.yml能够对模板进行文件名的配置，以及生成路径的配置
5. 参数话选择模板及配置：在启动类中能够注入模板配置的路径，灵活切换模板和配置
6. 支持单表、主从表代码生成：默认提供的单表和主从表的前后端代码实现

## 架构图
![](http://ww3.sinaimg.cn/large/006tNc79gy1g61kzegpgyj30k20fxgnh.jpg)

## 使用流程
1. 修改application.properties指定到对应数据源
2. 创建模板工程文件夹，编写module.yml配置，指定模板文件夹，模板参数，输出文件夹等
3. 编写tables.yml配置，指定生成的表，以及主从表关系和引用id
4. 在指定文件夹编写生成模板文件
5. 编写template.yml制定代码生成器生成文件名以及对应路径
6. 启动项目类中修改注入的modulepath环境变量，指定到当前模板工程文件夹
7. 启动程序生成文件

## 目录说明及介绍
```sh
├── GenerateApplication.java
├── config
│   ├── GenerateConfig.java : 主要配置类
│   └── entity: 具体配置类
│       ├── CommonConfigData.java
│       ├── ModuleConfigData.java
│       ├── SelectConfigData.java
│       ├── TableConfigData.java
│       ├── TemplateConfigData.java
│       └── TemplateFileConfigData.java
├── entity：具体配置对应实体类
│   ├── DetailTableData.java
│   ├── ModuleData.java
│   ├── SelectData.java
│   ├── SelectItem.java
│   ├── TableData.java
│   └── TemplateData.java
├── jdbc：数据库元数据获取连接类
│   ├── Connection.java
│   ├── dao
│   │   ├── ColumnsDao.java
│   │   └── TableDao.java
│   └── metadata
│       ├── Column.java
│       └── Table.java
├── main：主流程：注入模板路径，获取表，封装模板参数，生成文件，移动文件
│   └── GenerateMain.java
├── template：模板工具：模板解析类，模板参数创建类
│   ├── FreemarkerParamGenerate.java
│   ├── FreemarkerTemplateParser.java
│   ├── entity：配置实体类
│   │   ├── ConverterParam.java
│   │   ├── FieldsTemplateParam.java
│   │   └── MainTemplateParam.java
│   └── param：参数扩展转换类
│       ├── convert
│       │   ├── ColumnMetadataParamConvert.java
│       │   ├── CommonConfigParamConvert.java
│       │   ├── ModuleConfigParamConvert.java
│       │   ├── TableConfigParamConvert.java
│       │   └── TableMetadataParamConvert.java
│       └── extend
│           ├── EnglishStringParamExtend.java
│           ├── FlagConfigExistParamAndTypeExtend.java
│           ├── FlagConfigExistParamExtend.java
│           ├── ITemplateParamExtend.java
│           └── MysqlColumnTypeParamExtend.java
└── utils：相关工具类
    ├── FileUtils.java
    ├── MyBeanUtils.java
    ├── StringUtils.java
    └── YumUtils.java
```

```sh
resources
├── application.properties 配置文件
├── mapping 数据源配置
│   ├── ColumnsMapper.xml
│   └── TableMapper.xml
└── template 模板文件
    ├── basic
    │   ├── base 基础模板文件夹
    │   ├── common 通用模板文件夹
    │   ├── detail 明细模板文件夹
    │   ├── main 主从表主表模板文件夹
    │   ├── module.yml 模块配置
    │   ├── single 单表模板文件夹
    │   ├── tables.yml 生成表配置
    │   ├── template.yml 模板文件名路径配置
```

## 开发帮助说明
[帮助文档](help.md)

## 未来特性

可视化配置及使用：1. bs架构，2. cs架构

数据源配置化：当前只支持一个数据源，每次切换需要修改配置


    
 