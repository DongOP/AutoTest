## 概述
AutoAPITest是一款Java语言开发的接口自动化测试框架，用于接口的日常监控。

## 当前版本
- V0.0.1
- 发布日期：2022.08.13

## 最新功能特性
- 支持了图形化的测试报告
- 日志打印
- Excel表驱动数据
- 邮件发送功能
- 定时触发功能


## 项目架构
![Framework](https://img-blog.csdnimg.cn/2af447fd84964086b7363db1a5dc344b.png)


## 项目目录结构

```
─AutoAPITest 项目开发目录
├─report 测试报告和日志生成目录
│  ├─Log 框架运行日志文件目录
│  └─TestReport.html 测报告文件
├─src
│  ├─main...listener 测试报告监听器目录
|  ├─main...utils 工具类目录
|  └─main...resource 
|        ├─log4j.properties 日志配置文件
|        ├─TestData.xlsx 接口测试数据
|        └─testng.xml testng配置文件
|  └─test...testcase 测试用例目录 
├─AutoAPIScript.bat 用于触发每日监控的 bat 脚本
├─pom.xml maven工程配置文件
└─ReadMe.md 项目介绍文件
```
## 测试报告
### 一、使用 Extentreports
1. 新建testng.xml，建好suite和监听器ExtentTestNGIReporterListener
2. 右键testng.xml执行，或者使用命令行：mvn clean test -DsuiteXmlFile=D:/Work/APITest/AutoTest/AutoAPITest/src/main/resources/testng.xml
3. 在 .\AutoAPITest\report 里面找到 xxxx.html，用 chrome 打开
#### 报告截图：
![Report](https://img-blog.csdnimg.cn/a81681daecdd4248b0a9931821b0e2d8.png)

### 二、使用 Allure
1. 配置 Allure 环境变量
2. 生成报告  
allure generate D:/Work\APITest/AutoTest/AutoAPITest/target/allure-results -o D:/Work\APITest/AutoTest/AutoAPITest/target/allure-reports/ --clean
3. 打开报告  
allure open -h 127.0.0.1 -p 8883 D:/Work\APITest/AutoTest/AutoAPITest/target/allure-reports/ 


