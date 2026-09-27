@echo off
setlocal

rem Always run relative to this script, regardless of the caller's directory.
cd /d "%~dp0"

echo Building Dataset Generator with Maven...
call mvn clean package
if errorlevel 1 exit /b 1

java -Xmx6144m -jar "target\dataset-generator.jar" %*
