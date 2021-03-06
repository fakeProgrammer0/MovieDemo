```
title: MovieDemo
tags: Spring Boot,Spring Cloud
```

# 0. 目录

* [1.Brief Intro](#1brief-intro)
* [2.项目启动](#2项目启动)
   * [2.0. 环境 & 工具：maven, vue, npm, IDEA, mysql, ...](#20-环境-工具maven-vue-npm-idea-mysql)
   * [2.1. 导入数据库](#21-导入数据库)
   * [2.2. maven项目导入IDEA](#22-maven项目导入idea)
   * [2.3. Maven打包项目](#23-maven打包项目)
   * [2.4. 前端](#24-前端)
   * [2.5. 启动项目](#25-启动项目)
* [3.关于Spring Boot你需要知道这些](#3关于spring-boot你需要知道这些)
   * [3.1. pom.xml：Maven项目管理依赖的配置文件](#31-pomxmlmaven项目管理依赖的配置文件)
   * [3.2. application.yml / application.properties：Spring Boot的配置文件](#32-applicationyml-applicationpropertiesspring-boot的配置文件)
   * [3.3. RestController：后端响应、处理HTTP请求的接口](#33-restcontroller后端响应-处理http请求的接口)
   * [3.4. Mapper：Mybatis读写数据库的接口](#34-mappermybatis读写数据库的接口)
   * [3.5. 一种简单的Spring Boot后端架构](#35-一种简单的spring-boot后端架构)
* [4.Spring Cloud微服务架构](#4spring-cloud微服务架构)
   * [4.1. 项目架构](#41-项目架构)
   * [4.2. 项目的组件和API](#42-项目的组件和api)
* [#Tips](#tips)

# 1.Brief Intro
一个简单的SpringCloud微服务Demo项目（toy example）
* 功能：用户可以从网站查询到电影信息，并将电影添加都收藏夹
* 技术：前端vue + 后端Spring Cloud + 数据库mysql
* 数据：来源于豆瓣top 250电影，仅用于测试学习
* 用处：功能虽然简单，这个小型的玩具项目还是可以用来学习Spring Cloud配置时测试、演示功能

【注】因为这篇readme定位是教程类型，所以可能写得比较啰嗦。本人水平有限，如果有写错的地方，大家可以提交修改请求或发布issue（毕竟我真的很菜，还请多多指教_(:з」∠)_ <br/>

如果你对Spring Boot还不是很熟悉的话，建议先读一下袁强师兄写的[Demo](https://github.com/acjdxhs/demo)，并配置一下相关环境

<img src="img/MovieDemo Architecture.png" border="10">
<img src="img/Home.png">
<img src="img/Desc.png">
<img src="img/Favorite.png">

# 2.项目启动
### 2.0. 环境 / 工具：maven, vue, npm, IDEA, mysql, ...
### 2.1. 导入数据库
1. 在MoiveDemo的目录下，进入CMD（有关CMD的操作，戳[tips](#tips)），进入mysql
<img src="img/mysql.png">

```sql
# 运行文件中的sql语句，新建数据库表格，导入数据
mysql> source microservice.sql; 

# 用以下命令熟悉microservice数据库的schema：
# 查看当前数据库
mysql> select database(); 

# 当前数据库的表格
mysql> show tables;

# 查看movie表格的字段、key等信息
mysql> desc movie; 
```

2. 具体的sql语句
   * movie表格：MovieDemo/ms-movie-service/movie_schema.sql
   * user表格：MovieDemo/ms-user-service/user_schema.sql

### 2.2. maven项目导入IDEA
1. 用IDEA [import Project]，选中项目目录下的pom.xml文件
2. 勾选自动导入Maven项目，这样IDEA就会在项目打开后自动导入pom.xml中配置的SpringBoot依赖、插件等（需要连接网络）
3. 后面一直Next即可
4. 修改application.yml中的mysql用户、密码
5. 重复1-4，导入ms-movie-service，ms-user-service，ms-gateway，ms-discover-eureka

【注意】这几个微服务项目用的mysql配置是和**mysql 8.0.11**版本一致的，并且使用了ssl。mysql版本不同，配置是不一样，如果踩坑遇bug了，请自行搜索或查阅官方文档<br>
<img src="img/import project.png">
<img src="img/maven1.png">
<img src="img/maven2.png">

### 2.3. Maven打包项目
在Maven项目路径下，cmd输入：

``` bash
# path: MovieDemo/ms-discover-eureka/
mvn clean compile
mvn clean package
```

即可把项目打包成Jar<br>
可以看到项目目录下，生成了target文件夹，target目录下包含了项目的Jar包<br>
**打包项目：ms-discovery-eureka, ms-gateway**<br>
tips: 也可以在IDEA的Terminal窗口中使用CMD<br>
<img src="img/IDEA terminal.png">

### 2.4. 前端
CMD进入vue-element-admin路径，安装依赖包

```bash
# path: MovieDemo/vue-element-admin
# 安装依赖包
npm install
```

【注】前端是从开源的Vue项目vue-element-admin稍微改改就拿来用的。毕竟没有前端，自己写的后端只能用postman和浏览器地址栏来测试响应的JSON，很尴尬的_(:з」∠)_ 因为本人前端知识几乎为0，所以项目改得很粗糙，例如登录验证只能用admin这个账号……将就着用吧。有兴趣的话，可移步vue-element-admin的github仓库和vuejs的官网阅读文档：
* [vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)
* [vuejs](https://cn.vuejs.org/)

### 2.5. 启动项目
1. 服务发现组件
   1. 在系统hosts文件中添加这一行：127.0.0.1 peer1 peer2<br>
   2. 在CMD中进入target文件夹，运行ms-discovery-eureka：

```bash
# path: MovieDemo/ms-discovery-eureka/target

# 启动eureka服务发现组件peer1
java -jar microservice-discovery-eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1

# 打开另一个CMD，启动peer2
java -jar microservice-discovery-eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
```

2. 启动ms-gateway的Jar包
3. 直接在IDEA中启动ms-movie-service和ms-user-service
4. CMD进入vue-element-admin，启动前端

```bash
# path: vue-element-admin
npm run dev
```

5. 随便看看，点一点，enjoy yourself

# 3.关于Spring Boot你需要知道这些
* pom.xml 依赖文件
* application.yml 或 application.properties 配置文件
* 3.3 RestController响应HTTP方法
* mybatis mapper：java接口 / xml文件

### 3.1. pom.xml：Maven项目管理依赖的配置文件
包含了Spring Boot, Spring Cloud的各种依赖、插件等。pom.xml文件就好比是Spring Boot项目的“科技管理中心”。举个例子，只要在pom.xml文件中加入mybatis和mysql的依赖，IDEA就会自动导入mybatis和mysql的java库，之后就可以很方便地在整个项目中使用mybatis注解来访问mysql数据库了

```xml
<dependency>
	<groupId>org.mybatis.spring.boot</groupId>
	<artifactId>mybatis-spring-boot-starter</artifactId>
	<version>1.3.2</version>
</dependency>

<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>8.0.11</version>
	<scope>runtime</scope>
</dependency>
```

### 3.2. application.yml / application.properties：Spring Boot的配置文件
配置项目所需各种属性参数，例如Spring Boot的数据库信息、端口号、日志等等等，各种依赖（比如eureka, zuul）的配置参数。两者只是格式不同而已，内容上是等价的。按个人偏好选其中一个即可
* yml（yet another makeup language）采用严格的缩进形式，属于标记语言
* properties 采用面向对象语言的属性访问方式
<img src="img/application.png">

### 3.3. RestController：后端响应、处理HTTP请求的接口
当你熟悉了面向对象编程的逻辑之后，开始接触Spring Boot时你可能会摸不着头脑。整个项目的启动类XXApplication.java的main方法只有一行：

```java
public static void main(String[] args)
{
    SpringApplication.run(XXApplication.class, args);
}
```

<p>
完全没有像平常编程一样的逻辑：新建类，调用类的方法，各个类之间相互协作……
其实呀，像Spring Boot这种黑箱子，帮我们做了很多事情，只要写少量的代码就可以提供后端的服务了，Controller（或RestController）正是Spring Boot这个黑箱子和外界交互的接口。
</p>

在类的定义前标注@Controller，这个控制器类就可以响应HTTP方法了<br>
而@RestController = @Controller + @ResponseBody，被标注为@RestController的类，方法接受的参数、返回值都是JSON格式的，很方便<br>
<br/>
- [ ] 占坑，后续会有重要更新
<br/>

### 3.4. Mapper：Mybatis读写数据库的接口
创建mapper接口，在抽象方法的定义上标注@Select, @Insert, @Delete, @Update等注解，并填写相关的sql语句，即可读写数据库。具体见：MovieDemo/ms-movie-service/src/main/.../mapper/MovieMapper.java中的代码

### 3.5. 一种简单的Spring Boot后端架构
controller响应HTTP请求，调用service层的服务处理请求，service层调用mapper层的接口读写数据库
<img src="img/springboot architecture.png">

# 4.Spring Cloud微服务架构
### 4.1. 项目架构
后端拆解为：
* 微服务发现组件 service discovery
* 网关 gateway
* 两个微服务：movie-service和user-service
<img src="img/MovieDemo Architecture.png">

### 4.2. 项目的组件和API

|componet|port num|api|
|:-:|:-:|-|
|frontend|9752||
|eureka-server|peer1 8761<br> peer2 8762||
|gateway|8080|路由<br>/movie/** -> microservice-movie-service<br>/user/** -> microservice-user-service|
|ms-movie-service|8010|GET /moviesInfo <br>GET /moviesCount <br>GET /desc<br>PUT /collection<br>GET /collection<br>DELETE /collection<br>GET /collection/collections|
|ms-user-service|8000|POST /login<br>POST /signUp<br>GET /id<br>|

# #Tips
便捷地让CMD进入某一路径下：在文件浏览器\[windows explorer]窗口中，按住\[shift]，鼠标右键，点击\[Open cmd here]即可

<img src="img/cmd.png">