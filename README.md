# Repo details 
This repository/project is to showcase the Web Browser automation using below tools/libraries:-
* Selenium + JAVA using Page Factory Model
* TestNG
* Maven
* Allure

# Getting Started / Prerequisite:
* Java Installed on System
* nodejs/npm - latest 
* maven - latest 
* Allure Command Line - latest 
* IDE - preferred one "IntelliJ"

# How to Build and Test
* Clone the repository/project
* Open the cloned project into IDE i.e IntelliJ
* Open IDE Terminal
* Execute maven commands as per the action required e.g
  * "mvn clean install" - To run and install all maven dependencies and execute the test methods and package the project
  * "mvn clean test" - To run test etc.

# How to Contribute
* Create your own feature branch
* Do required changes and then commit + push the same
* Create PR for the master branch

**Note**: Make sure you are always keeping your branch inline with "master" branch code

# Setting Up Allure in Local.

#### Download the Latest Allure Commandline utility - https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/

#### Extract the Utility to the
```console
C:\Allure
```

#### Add the following to the *PATH* Environment Variable
```console
C:\Allure\bin
```

#### To generate the report. Use the following commands
```console
mvn site
allure serve .\target\allure-results
```
