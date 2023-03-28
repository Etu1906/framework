#!/bin/bash.
clear;
cd framework/build;
javac -d framework ../src/*.java;
cd framework;
jar -cf ../fw.jar .;
cd ..;
cp fw.jar  ../../test-framework/WEB-INF/lib/;
cd /home/ralph/ralph/apache-tomcat-8.5.82/webapps/framework3/test-framework/src;
javac -d ../WEB-INF/classes *.java;
cd ../../test-framework;
jar -cf framework4.war .;
cp  framework4.war /home/ralph/ralph/apache-tomcat-8.5.82/webapps;
cd /home/ralph/ralph/apache-tomcat-8.5.82/webapps/framework3;
