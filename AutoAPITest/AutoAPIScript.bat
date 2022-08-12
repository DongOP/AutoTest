@echo off
echo.
echo [INFO] Start Auto API Test Task.
echo.

cd /d D:\Work\APITest\AutoTest\AutoAPITest

call mvn clean test -DsuiteXmlFile=D:/Work/APITest/AutoTest/AutoAPITest/src/main/resources/testng.xml

rem pause