# SearchPoint (基于Spring Boot和Spring Data JPA)


### 运行方法
* 将日志文件放置在`src\main\resources`目录下，更改`src\main\java\com\smart\go\service\ReadAndExactDataService.java`中29:79的文件名。
  > 日志文件格式为UTF-8,**如果不是则需要转码**，win可以用记事本转码，linux可以用enca。
* `src\test\java\com\smart\go\GoApplicationTests.java`运行54:5的TestReadExtract()方法可以分析日志并将结果存入相应数据库。
* `src\test\java\com\smart\go\GoApplicationTests.java`运行44:5的TestBuildMoveInfo()可以将数据库目前所有表关联起来形成一张新的人员活动信息表。
  > 项目使用了log4j，如果需要日志文件则可在`src\main\resources\log4j2.xml`中22:17和23:17开启控制台打印日志或输出日志到本地文件(本地文件存储路径在8:1可以设置)。日志默认关闭，对运行速度影响较大。                         
 <br> 2020.4.27

 ***
 
 <br>
