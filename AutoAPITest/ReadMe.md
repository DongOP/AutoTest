
##  测试报告
### 一、使用 Extentreports
1. 新建testng.xml，建好suite和监听器ExtentTestNGIReporterListener
2. 右键testng.xml执行，或者使用命令行：mvn clean test -DsuiteXmlFile=D:/Work/APITest/AutoTest/AutoAPITest/src/main/resources/testng.xml
3. 在 .\AutoAPITest\report 里面找到 xxxx.html，用 chrome 打开

### 二、使用 Allure
1. 配置 Allure 环境变量
2. 生成报告  
allure generate D:/Work\APITest/AutoTest/AutoAPITest/target/allure-results -o D:/Work\APITest/AutoTest/AutoAPITest/target/allure-reports/ --clean
3. 打开报告  
allure open -h 127.0.0.1 -p 8883 D:/Work\APITest/AutoTest/AutoAPITest/target/allure-reports/ 
