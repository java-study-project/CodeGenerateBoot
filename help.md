# 代码生成器代码结构说明

## 代码相关文件说明

### 主流程说明
1. 加载容器阶段，GenerateConfig通过config类的初始化的时候，指定modulepath通过@Bean加载对应配置的信息，放到spring容器中，并且注入全局启动类中
    1. ModuleConfigData
    2. TableConfigData
    3. TemplateConfigData
    4. singleTemplateFile、mainTemplateFile、detailTemplateFile
2. 装载GenerateMain对应的配置类，模板参数通用处理类、数据库连接
    1. 数据库元数据加载：通过连接类，直接读取数据库，存储到临时区域（优先单表，明细表，主表）
    2. 通过模板参数创建处理类，以及元数据，生成模板参数，以及其中的扩展点
    3. 调用生成文件服务，将对应的文件生成，并且通过模板对应的路径配置，实现文件路径转移
3. 模板参数生成，期间要读取配置的转换器信息，主模板，属性模板，通用模板，通过在容器中直接获取的方式进行获取
    1. 常量参数设定：全局常量参数设定
    2. 一级参数生成：模块参数设置(优先级： 固定参数 > 模块扩展参数)、表参数设置(优先级： 固定参数 > 表扩展参数 > 模块扩展参数)、表元数据参数设置
    3. 二级参数fields生成：字段设定，列元素转换器
    4. 参数扩展设定：对于抬头和明细的参数进行扩展化
    5. 二级参数details（带明细表时候设定）获取明细表，获取临时模板参数，设定到明细中

### 主要类描述

#### 1. 配置包装数据
GenerateConfig：全局元数据配置信息，通过@Bean的方式，在config初始化的时候，就加载配置，后续通过spring直接注入
1. CommonConfigData - 暂未使用
2. ModuleConfigData - ModuleData - "module.yml"
3. SelectConfigData - Map<String, SelectData> List<SelectItem> - "select.yml"
4. TableConfigData - Set<TableData> mainTables，singleTables，Set<DetailTableData> detailTables - "tables.yml"
5. TemplateConfigData - Map<String, TemplateData> - "template.yml"
6. TemplateFileConfigData - List<File> fileList - "模块下面的所有文件"

#### 2. 元数据
1. ModuleData：模块元数据
2. SelectData：下拉框元数据
3. SelectItem：下拉框选项元数据
4. TableData：基本表元素剧
5. DetailTableData：抬头表元数据
6. TemplateData：模板元素局

#### 3. 配置文件
1. application.properties：数据库配置，数据源配置，mybatis配置
2. module.yml：模块配置
3. tables.yml：表配置
4. template.yml：模板配置
5. templatefile：模板文件

#### 4. 模板流程工具类：
1. FreemarkerTemplateParser：模板解析器：解析模板参数，解析模板，创建文件（静态单例进行代码生成和转移）
2. FreemarkerParamGenerate：获取模板参数，设定表参数，设定扩展参数，设定其他参数等等设定模板参数功能


#### 5. 工具类
1. FileUtils：读取文件，新增文件，附加文件，删除文件
2. MyBeanUtils：转换对象，拷贝数据
3. StringUtils：字符串替换分隔符，并把单词首字母大写、字符串转换为java字段规范格式（驼峰），以'_'分隔单词、将首字母转换为小写、字符串首字符大写、是否是空字符串
4. YumUtils：读取yml转换为map，读取yml2代文件，转换为定义的类、读取yml3带文件，转换为对应的类

#### 6. 元数据获原理
1. 使用mybatis的mapper，读取resource下面的：ColumnsMapper.xml、TableMapper.xml
2. 对应Spring组件Connection组合了两个dao：ColumnsDao、TableDao
3. 分别对表和字段的数据进行获取：Column、Table
4. 最终在主流程中，读取配置对应的表，获取对应的表数据
    1. Map<String, Table> tableMetadataMap
    2. Map<String, List<Column>> tableColumnMetadataMap

### 扩展点
1. 模板参数转换器（例子：将a参数，转换为b、c、d或者其他的参数）
    1. 字段元素局参数配置
    2. 通用参数配置
    3. 模块配置参数配置
    4. 表配置参数配置
    5. 表元数据参数配置
2. 模板参数扩展转换器（例子：将a参数，扩展为a_java、a_c++）
    1. 模板参数扩展接口
    2. flag配置存在参数和类型扩展
    3. flag配置存在参数扩展
    4. 英文字符参数扩展
    5. 数据库字段类型参数扩展
3. 模板参数，全部都在ModuleData中含有，是模块的全局配置
    1. 扩展参数转换类，在mainDataConfig中获取
    2. 属性模板参数
    3. 主要模板参数


### 配置参考模板map报文
```json
{
	tableName,	{"_TF", "_class", "_LXX", "_LDX", "_en", "_i18n"}
	simpleName,	{"_TF", "_class", "_LXX", "_LDX", "_en", "_i18n"}
	tableComment,
	auth,
	crateDate:"yyyy年MM月dd日"
	module,
	server,	{"_TF", "_class", "_LXX", "_LDX", "_en", "_i18n"}
	serverCn,
	orderByFiled,
	importExcel,
	exportExcel,
	refId,	{"_TF", "_class", "_LXX", "_LDX", "_en", "_i18n"}
	refNo, {"_TF", "_class", "_LXX", "_LDX", "_en", "_i18n"}
	functionNo,
	i18nCloumnKey,
	l: "{",
	r: "
	fields: [
		{
			columnName,	{"_TF", "_class", "_LXX", "_LDX", "_en", "_i18n"}
			columnComment,
			dataType,	{"_java", "_import", "_isNum", "_isImport"}
			characterMaximumLength,
			isNullable
			ComboAble,
			ListHidden,
			DetailHidden,
			baseField,
			OpTimeAble,
			i18NCommonAble,
			QueryAble
		}
	]
	details:[
	    {detailJson}
	    ......
	]
}
```

### 模板语法参考
```js
// 模板语法
// ${key}
// <#list fields as item>${item.xxx}</#list>
// <#list fields as item>...<#break></#list>
// <#list fields as item><#if item_index = 0>...</#if></#list>
// <#list fields as item><#if item_has_next>...</#if></#list>
// <#list fields as item><#if !item_has_next>...</#if></#list>
// <#if statement>...<#elseif statement>...<#else>...</#if>
// <#list seq?chunk(4) as row> 分段处理
<#list fields as item><#if !item.ListHidden><#if item.ComboAble><#if !item_has_next></#if></#if></#if></#list>
${item.}
```

### 推荐配置说明
  优先级：
  
    application.properties > module.yml > tables.yml > template.yml > 具体模板：按照文件夹，以及文件名进行区分，所有都有一套生成模式，都会生成
 
 application.properties
   
    数据源信息，mybatis配置
 
 module.yml
 ```sh
    作者
     模块名
     需要生成的表
     生成目录
     扩展参数
        是否第一次生成
        通用国际化的key
        排序的树形
        是否需要导入excel
        是否需要导出
        是否是搜索帮助
        是否下拉框
        是否查询条件
        是否不可用
        是否列表隐藏
        是否明细隐藏
        是否子类隐藏
        基础属性
        操作时间
        国际化通用下拉框
     模板路径
        单表模板文件夹
        带明细的主表模板文件夹
        明细表模板文件夹
     一级模板参数
        模块配置
        表配置
        元数据配置
     属性模板参数
        含有字段添加
     扩展模板参数
        字符转换
        db类型转换
        扩展属性转换
        查询条件转换
 ```
 
 tables.yml
 ```sh
 主表配置
    表名
    简写
    扩展参数
 明细表配置
    json：表名，简写，抬头表名，关联id
 ```
 template.yml
 ```sh
 模板文件名
    需要修改的名字
    拷贝的相对路径
    使用模式：附加，新增
 ```


