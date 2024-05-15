# Report: Class Assignment 2

## Index

# Table of Contents

1. [Introduction](#1-introduction)
2. [First Part](#2-first-part)
  - [Creating Issues in GitHub Main Task](#21-creating-issues-in-github-main-task)
  - [Creating a New Branch](#22-creating-a-new-branch)
  - [Creating the README.md File](#23-creating-readmemd-file)
  - [Converting the Basic Version to Gradle](#24-converting-the-basic-version-to-gradle)
  - [Deleting and Copying Source Folders](#25-deleting-and-copying-source-folders)
  - [Adding the org.siouan.frontend Gradle Plugin](#26-adding-the-gradle-plugin-orgsiouanfrontend)
  - [Adding a Task to build.gradle (Copy)](#27-adding-a-task-to-buildgradle-copy)
  - [Adding a Task to build.gradle (Delete)](#28-adding-a-task-to-buildgradle-delete)
  - [Committing Code and Merging with the Main Branch](#29-committing-code-and-merging-with-the-main-branch)
  - [Updating the README.md File](#210-updating-the-readmemd-file)
  - [Tagging Your Repository](#211-tagging-your-repository)
3. [Second Part](#3-second-part)
  - [Alternative Technological Solution for the Build Automation Tool: Apache Maven](#31-alternative-technological-solution-for-the-build-automation-tool-apache-maven)
  - [Implementation of the Alternative](#32-implementation-of-the-alternative)

## Part 2: Building Tools with Gradle

### 1. Introduction:
This report outlines the process of converting the basic version of the Tutorial application to Gradle from Maven.

## 2. First Part:

### 2.1. Creating Issues in GitHub Main Task:

* Example issues created:
  * #12 CA2_Part2: Create README.md file;
  * #13 Extract and unzip the provided file into the directory "CA2/Part2/" of your repository;
  * #14 Replace the current src directory with the src directory (and its subdirectories) from the tutorial's basic folder;
  * #15 Integrate the org.siouan.frontend Gradle plugin into the project;
  * #16 Implement a task in build.gradle for copying and deleting files;
  * #17 CA2_Part2: Update README.md;
  

### 2.2. Creating a New Branch:
* Create a new branch named "tut-basic-gradle" in your repository:
  ```bash
  git branch tut-basic-gradle
  ```
* To switch to this branch:
  ```bash
  git checkout tut-basic-gradle
  ```

### 2.3. Creating README.md File:
* Create a README.md file in the folder "CA2/Part2":
  ```bash
  cd CA2
  cd Part1
  touch README_CA3_PART1.md
  ```
* Document all your work in the README.md file.
* Stage the changes:
  ```bash
  git add .
  ```
* Commit the changes, mentioning the issues created in GitHub:
  ```bash
  git commit -m "[docs] Create README.md file, closes #12"
  ```
* Push the changes to your remote repository:
  ```bash
  git push
  ```

### 2.4. Converting the Basic Version to Gradle:
* Follow the instructions provided in the tutorial's readme file to start a new Gradle Spring Boot project with specified dependencies.
* Extract the react-and-spring-data-rest-basic.zip generated to the CA2/Part2 folder.
* Navigate to the folder containing the "react-and-spring-data-rest-basic.zip" file and extract it to the Part2 folder:
  ```bash
  unzip react-and-spring-data-rest-basic.zip -d ./home/miguelcouto/repos/devops23-24/CA2/part2
  ```
* Check available Gradle tasks inside the react-and-spring-data-rest-basic folder:
  ```bash
  ./gradlew tasks
  ```
* Document all your work in the README.md file.
* Stage the changes:
  ```bash
  git add .
  ```
* Commit the changes, mentioning the tasks created in GitHub:
  ```bash
  git commit -m "[feat] Extracted the generated zip file, closes #13"
  ```
* Push the changes to your remote repository:
  ```bash
  git push 
  ```

### 2.5. Deleting and Copying Source Folders:
* Delete the 'src' folder and all its contents recursively in the 'react-and-spring-data-rest-basic' folder:
  ```bash
  rm -R src
  ```
* Copy the src folder (and all its subfolders) from the basic folder of the tutorial into the 'react-and-spring-data-rest-basic' folder:
  ```bash
  cp -R ../../../CA1/basic/src/ ./
  ```
* Copy the files webpack.config.js and package.json:
  ```bash
  cp ../../../CA1/basic/webpack.config.js ./
  cp ../../../CA1/basic/package.json ./
  ```
* Delete the folder src/main/resources/static/built/:
  ```bash
  rm -R src/main/resources/static/built/
  ```
* Experiment with the application by running:
  ```bash
  ./gradlew bootRun
  ```
* Document all your work in the README.md file.
* Stage the changes:
  ```bash
  git add *
  ```
* Commit the changes, mentioning the tasks created in GitHub:
  ```bash
  git commit -m "[feat] Deleted src folder and copy other src folder, closes #14"
  ```
* Push the changes to your remote repository:
  ```bash
  git push
  ```

### 2.6. Adding the Gradle Plugin org.siouan.frontend:
* Add the org.siouan.frontend plugin to the project to manage the frontend.
* Add the following commands in the build.gradle file:
  ```bash
  plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.4'
    id 'io.spring.dependency-management' version '1.1.4'
    id 'org.siouan.frontend-jdk17' version '8.0.0'
  }
  ```
* Add the following code in build.gradle to set up the plugin:
  ```bash
  frontend {
    nodeVersion = "16.20.2"
    assembleScript = "run build"
    cleanScript = "run clean"
    checkScript = "run check"
  }
  ```
  * Update the scripts section/object in package.json to configure the execution of webpack:

  ```bash
  "scripts": {
  "webpack": "webpack",
  "build": "npm run webpack",
  "check": "echo Checking frontend",
  "clean": "echo Cleaning frontend",
  "lint": "echo Linting frontend",
  "test": "echo Testing frontend"
    },
  ```

* Execute in your terminal:
  ```bash
  ./gradlew build
  ```
* Document all your work in the README.md file.
* Stage the changes:
  ```bash
  git add .
  ```
* Commit the changes, mentioning the tasks created in GitHub:
  ```bash
  git commit -m "[feat] Added the gradle plugin to the project, closes #15"
  ```
* Push the changes to your remote repository:
  ```bash
  git push
  ```

### 2.7. Adding a Task to build.gradle (Copy):
* Add a task to copy the generated JAR to a folder named "dist" at the project root level.
* Add the following task to build.gradle:
  ```bash
  task copyJar(type: Copy, dependsOn: build) {
  from 'build/libs/'
	  into 'dist'
	  include '*.jar'
  }
  ```
  
* Stage the changes:
  ```bash
  git add .
  ```
* Commit the changes, mentioning the tasks created in GitHub:
  ```bash
  git commit -m "[feat] #16 Added a task to build.gradle (Copy)"
  ```
* Push the changes to your remote repository:
  ```bash
  git push
  ```
  
### 2.8. Adding a Task to build.gradle (Delete):
* Add a task to delete all files generated by webpack before the clean task runs.
* Add the following task to build.gradle:

  ```bash
  task deleteWebpackFiles(type: Delete) {
  delete 'src/main/resources/static/built'
  }
  clean.dependsOn deleteWebpackFiles
  ```

* Execute in your terminal:
  ```bash
  ./gradlew build
  ```
* Document all your work in the README.md file.
* Stage the changes:
  ```bash
  git add .
  ```
* Commit the changes, mentioning the tasks created in GitHub:
  ```bash
  git commit -m "[feat] Added a task to build.gradle (Delete), closes #16"
  ```
* Push the changes to your remote repository:
  ```bash
  git push
  ```

### 2.9. Committing Code and Merging with the Main Branch:
* Navigate to the main branch:

  ```bash
  git checkout main
  ```
* Merge the 'tut-basic-gradle' branch into the 'main' branch:
  ```bash
  git merge --no-ff tut-basic-gradle
  ```
* Push the merge:
  ```bash
  git push
  ```
### 2.10. Updating the README.md File:

* Stage the changes:
  ```bash
  git add .
  ```
* Commit the changes, mentioning the issues created in GitHub:
  ```bash
  git commit -m "[docs] Update README.md, closes #17"
  ```
* Push the changes to your remote repository:
  ```bash
  git push
  ```

### 2.11. Tagging Your Repository:
* Tag your repository:
  ```bash
  git tag -a ca2-part2 -m "End of CA2-Part2"
  ```
* Commit your tag:
  ```bash
  git push origin ca2-part2
  ```
## 3. Second Part:
### 3.1. Alternative Technological Solution for the Build Automation Tool: Apache Maven

* Maven and Gradle are two popular build automation tools for Java-based projects. Below is a comparison between them:

| Apache Maven                                                                                                                                                                                                                                                     | vs  | Gradle                                                                                                                                                                                                                   |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Maven is used for generating Java-based projects.                                                                                                                                                                                                       | vs  | Gradle allows developing domain-specific language projects in an easy and straightforward manner.                                                                                                                     |
| Maven utilizes XML (Extensible Markup Language) to create project structures.                                                                                                                                                                                           | vs  | Gradle creates a project structure using a Groovy-based DSL (Domain-Specific Language).                                                                                                                               |
| Supported programming languages for Maven include Java, Scala, Ruby, and C#.                                                                                                                                                                | vs  | Gradle supports Java, Groovy, C, and C++ among other languages.                                                                                                                         |
| Maven uses XML files to maintain the list of components, project dependencies, and other necessities.                                                                                                                                            | vs  | Gradle projects maintain their configuration distinctly.                                                                                                 |
| Maven aims to complete projects within a given timeline.                                                                                                                                                               | vs  | Gradle focuses on incorporating new functionality into projects.                                                                                                                      |
| Maven's project creation time is generally longer due to the absence of build caching.                                                                                                                                                               | vs  | Gradle's performance is better as it tracks ongoing tasks rather than input or output tasks.                                                              |
| Maven has limited IDE support compared to Gradle.                                                                                                                                                             | vs  | Gradle offers extensive IDE support, making it highly customizable.                                                                                          |
| Java compilation is compulsory in Maven.                                                                                                                                                                                                           | vs  | Java compilation is optional in Gradle.                                                                                                                                                            |
| Maven is well-established with a large user base.                                                                                                                                                                                                      | vs  | Gradle, while newer, is gaining traction among developers.                                                                                                                        |
| Maven allows adding new dependencies for project creation, but managing dependencies can be challenging due to XML complexities.                                                                                                                                                               | vs  | Gradle simplifies dependency management, avoiding XML complexities.                                                                                          |

* Maven offers simplicity with its XML configuration, while Gradle provides flexibility and faster build times. Developers can choose between these tools based on their specific needs and preferences.

### 3.2 Implementation of the Alternative:

| Task                                                               | Gradle            | Maven                         |
|--------------------------------------------------------------------|-------------------|-------------------------------|
| Check available tasks by running:                                  | ```bash ./gradlew tasks ```  | ```bash mvn help:describe -Dcmd=clean ``` |
| Execute a project:                                                 | ```bash ./gradlew bootRun ``` | ```bash mvn spring-boot:run ```          |
| Compile the source code, run the tests and package the project:    | ```bash ./gradlew build ```  | ```bash mvn package ```                 |

* Adding the "frontend-maven-plugin" plugin to your Maven project:
* Open the "pom.xml" file of your Maven project and add the following code inside the "build/plugins" section:
  ```xml
  <build>
    <plugins>
      <plugin>
        <groupId>org.siouan.frontend</groupId>
        <artifactId>frontend-maven-plugin</artifactId>
        <version>1.9.3</version>
        <configuration>
          <nodeVersion>v16.20.0</nodeVersion>
          <workingDirectory>src/main/frontend</workingDirectory>
          <installDirectory>target</installDirectory>
          <webpack.version>5.68.2</webpack.version>
          <npm.version>8.3.0</npm.version>
        </configuration>
        <executions>
          <execution>
            <id>install node and npm</id>
            <goals>
              <goal>install-node-and-npm</goal>
            </goals>
          </execution>
          <execution>
            <id>npm install</id>
            <goals>
              <goal>npm</goal>
            </goals>
            <configuration>
              <arguments>install</arguments>
            </configuration>
          </execution>
          <execution>
            <id>webpack build</id>
            <goals>
              <goal>webpack</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
  ```

* The above command installs Node and NPM at the specified version and runs 'npm install' and 'webpack' to build the frontend. Note that the working directory is specified as "src/main/frontend" and the install directory is specified as "target".

* Add the following lines to configure webpack in package.json:
  ```json
  "scripts": {
    "webpack": "webpack",
    "build": "npm run webpack",
    "check": "echo Checking frontend",
    "clean": "echo Cleaning frontend",
    "lint": "echo Linting frontend",
    "test": "echo Testing frontend"
  },
  ```

* Adding the task to copy the generated JAR file to a folder named "dist" at the root level of the project in Maven:
* Add the following code to the pom.xml file:
  ```xml
  <build>
      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-resources-plugin</artifactId>
              <version>3.2.0</version>
              <executions>
                  <execution>
                      <id>copy-jar-to-dist-folder</id>
                      <phase>package</phase>
                      <goals>
                          <goal>copy-resources</goal>
                      </goals>
                      <configuration>
                          <outputDirectory>${project.basedir}/dist</outputDirectory>
                          <resources>
                              <resource>
                                  <directory>${project.build.directory}</directory>
                                  <includes>
                                      <include>*.jar</include>
                                  </includes>
                              </resource>
                          </resources>
                      </configuration>
                  </execution>
              </executions>
          </plugin>
      </plugins>
  </build>
  ```

* Adding the task to delete files generated by Webpack before the clean task runs in Maven:
* Add the following code to the pom.xml file:
  ```xml
  <build>
      <plugins>
          <plugin>
              <artifactId>maven-clean-plugin</artifactId>
              <version>3.1.0</version>
              <executions>
                  <execution>
                      <id>delete-webpack-files</id>
                      <phase>clean</phase>
                      <goals>
                          <goal>clean</goal>
                      </goals>
                      <configuration>
                          <filesets>
                              <fileset>
                                  <directory>${project.build.directory}/classes/static/built</directory>
                                  <excludes>
                                      <exclude>*.*</exclude>
                                  </excludes>
                              </fileset>
                          </filesets>


                      </configuration>
                  </execution>
              </executions>
          </plugin>
      </plugins>
  </build>
  ```

* Note that it is necessary to adjust the path of the folder containing the files generated by Webpack in your project.

