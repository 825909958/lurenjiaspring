1.跨库大事务拆分，相关联的小事务拆分在一起，外层不用事务，查询语句拆到外层

2.path

```
byte[] bytes =ResourceUtil.readBytes("test.pdf");
byte[] bytes1 = FileUtil.readBytes("test.pdf");
```

3.maven 

>  1.profile
>
> 2.导入第三方jar, 并通过pom配置<resipoty>加载进仓库,再声明版本，导入依赖
>
> 3.声明的变量会被webpom变量覆盖，webpom最后加载

4.集合操作

> public static void main(String[] args) {   List<Map<String, String>> maps = new ArrayList<>();   HashMap<String, String> map = new HashMap<>();   HashMap<String, String> map1 = new HashMap<>();   map.put("1", "1");   map1.put("2", "2");   maps.add(map);   maps.add(map1);   Map<String, String> stringMap = maps.stream().flatMap(i -> i.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));   System.out.println("maps = " + maps);   System.out.println("stringMap = " + stringMap); }

5.BeanUntil用法

> BeanUtil.copyProperties(surveyCertApplication1, surveyCertApplication2, CopyOptions.create().setOverride(false).setIgnoreNullValue(true).setIgnoreError(true));

6.sqlServer sql字符用法

> 1.cast (dict_id as int)   字符串转int
>
> 2.yyyy-mm-dd hh:mm:ss转为yyyy-mm-dd
>
>   Select convert(char(10),getdate(),126)  ；
>
> 3.DateDiff (interval,date1,date2)             interval=day
>
> 4.DateAdd (interval,number,date)	

7.数据库设计

> 2.数据库设计，-》数据源 如何打通，以及考虑后来查询效率
>
> ​    2.1比如1对多，数量这种  可以放在1的表，而不是在通过连表去计算个数或者（累加）金额数
>
> ​    2.2 不同业务建不同表，防止单表数量过大

8.js

> var item = feeCodes.find({FEE_CODE: value});

9.spring Bean Scope设置cglib代理解决抽象接口在父类的问题，因为我们抽出来的方法是通过父类的接口调用，但是自己类没有接口所有用cglib解决（代理类的类型为实际类型）

10.![31f390cb18dc6e7c29850fcd9bf4b17](D:\project\lurenjiaspring\md\up.assets\31f390cb18dc6e7c29850fcd9bf4b17.png)

11.配置参数优先级
> 在 Java 应用程序中，JVM 参数可以通过多种方式进行配置，例如在操作系统命令行中指定、在应用程序启动脚本中设置、在 JBoss 服务器的配置文件中定义等。
> 在使用 Spring 框架和 Maven 构建项目时，我们还可以在 Spring 的配置文件和 pom.xml 文件中指定一些 JVM 参数，例如内存大小、JVM 选项等。

以下是 Java 应用程序中常见的 JVM 参数按照优先级排序：

操作系统命令行中指定的参数，例如 -Xmx1024m 参数用来指定最大堆内存大小。
应用程序启动脚本中设置的参数，例如在启动脚本中使用 JAVA_OPTS 环境变量设置的参数。
JBoss 服务器的配置文件中定义的参数，例如在 standalone.xml 文件中设置的参数。
Spring 的配置文件中指定的参数，例如使用 <jvm-args> 标签指定的参数。
Maven 的 pom.xml 文件中指定的参数，例如使用 maven-compiler-plugin 插件指定的参数。

12.多数据源大事物死锁问题

13.不同的servlet支持的spring技术不一样。