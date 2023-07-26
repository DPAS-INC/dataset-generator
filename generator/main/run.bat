javac -cp ".;libraries\*" src\generator\*.java
java -Xmx6144m -cp "src;libraries\*" generator.Main
