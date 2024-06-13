# DevOps Technical Report | Class Assignment 5

# Table of Contents
- [CI/CD Pipelines with Jenkins - Part 1](#cicd-pipelines-with-jenkins---part-1)
  - [Setup](#setup)
  - [Introduction](#introduction)
  - [Requirements](#requirements)
  - [Implementation](#implementation)
- [CI/CD Pipelines with Jenkins - Part 2](#cicd-pipelines-with-jenkins---part-2)
    - [Introduction](#introduction-1)
    - [Requirements](#requirements-1)
    - [Implementation](#implementation-1)
## CI/CD Pipelines with Jenkins - Part 1

### Setup

1. **Start Jenkins Docker Container:**
   Start the Jenkins Docker container with the following command:
   ```bash
   docker run -d -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home --name=jenkins jenkins/jenkins:lts-jdk17
   ```
   This command starts a Jenkins container with port 8080 exposed for web access and port 50000 for build agents. The `-v jenkins-data:/var/jenkins_home` flag ensures persistent storage for Jenkins data.

2. **Access Jenkins Web Interface:**
    - Open a web browser and go to `http://localhost:8080`. This is where Jenkins is running.
    - Wait for Jenkins to start up fully. You can monitor the startup process by checking the logs of the Docker container (`docker logs -f jenkins`).

3. **Retrieve Jenkins Admin Password:**
    - Access the Jenkins container to retrieve the initial admin password:
      ```bash
      docker exec -it jenkins bash
      cd /var/jenkins_home/secrets
      cat initialAdminPassword
      ```
    - Copy the generated password. You'll need this password to unlock Jenkins during the setup process.

4. **Jenkins Initial Setup:**
    - In your web browser, after Jenkins has started, you will be prompted to unlock Jenkins. Paste the admin password obtained from the previous step.
    - Follow the prompts to install suggested plugins. Choose the default plugin installation.
    - After installation, Jenkins will prompt you to create the first admin user. You can choose to skip this step for now.
    - Set the Jenkins URL to `http://localhost:8080`.
    - Complete the setup wizard.

5. **Restart Jenkins:**
    - Once setup is complete, restart Jenkins to apply any changes:
      ```bash
      docker restart jenkins
      ```

### Introduction
The primary objective of this assignment is to implement a Jenkins pipeline for the "gradle basic demo" project.


### Requirements
The pipeline should include the following stages:
1. Checkout: To retrieve the code from the repository.
2. Assemble: Compile and generate archive files without running tests.
3. Test: Execute unit tests and publish the results in Jenkins using the `junit` step.
    - Ensure there are sufficient unit tests in the project.
4. Archive: Store the generated archive files in Jenkins.


### Implementation
1. Add a Jenkinsfile to your repository (`CA5/gradle_basic_demo`). Specify the repository URL and branch correctly in the 'Checkout' stage, and the directory of the `gradlew` script in the subsequent stages. Ensure the `gradlew` script is executable.

```groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out from repository'
                git url: 'https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847', branch: 'main'
            }
        }
        stage('Assemble') {
            steps {

                dir('CA2/part1/gradle_basic_demo') {
                    echo 'Assembling...'
                    sh 'chmod +x gradlew'
                    sh './gradlew clean assemble'
                }
            }
        }
        stage('Test') {
            steps {

                dir('CA2/part1/gradle_basic_demo') {
                    echo 'Running Tests...'
                    sh './gradlew test'
                    junit 'build/test-results/test/*.xml'
                }
            }
            post {
                always {

                    junit 'CA2/part1/gradle_basic_demo/build/test-results/test/*.xml'
                }
            }
        }
        stage('Archive') {
            steps {

                dir('CA2/part1/gradle_basic_demo') {
                    echo 'Archiving artifacts...'

                    archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
                }
            }
        }
    }
}
}
```


2. Configure a new Jenkins pipeline:
    - Navigate to Jenkins and create a new item.
    - Choose 'Pipeline' and configure it to use 'Pipeline script from SCM'.
    - Select 'Git' as SCM, provide the repository URL, specify the branch.
    - Set the 'Script Path' to `CA5/gradle_basic_demo/Jenkinsfile`.
    - Save the pipeline configuration.

3. Run the pipeline and review the console output to ensure all stages execute successfully.


<a href="https://ibb.co/98WwbQW"><img src="https://i.ibb.co/0DrBCHr/Screenshot-from-2024-06-13-10-02-09.png" alt="Screenshot-from-2024-06-13-10-02-09" border="0"></a>

<a href="https://ibb.co/cYnwWcP"><img src="https://i.ibb.co/nPJBXsT/Screenshot-from-2024-06-13-10-01-41.png" alt="Screenshot-from-2024-06-13-10-01-41" border="0"></a>


## CI/CD Pipelines with Jenkins - Part 2

### Introduction
This part of the assignment focuses on creating a Jenkins pipeline to build a Docker image of a Spring Boot application and push it to Docker Hub.

### Requirements
The pipeline should include the following stages:
1. Checkout: Retrieve the code from the repository.
2. Assemble: Compile and generate archive files with the application.
3. Test: Execute unit tests and publish the results in Jenkins using the `junit` step.
4. Javadoc: Generate and publish Javadoc in Jenkins.
5. Archive: Store the generated archive files in Jenkins.
6. Publish Image: Build a Docker image with Tomcat and the WAR file, and publish it to Docker Hub.

### Implementation
1. Add a Jenkinsfile to your repository (`CA5/react-and-spring-data-rest-basic/`). Specify the repository URL and branch correctly in the 'Checkout' stage, and the directory of the `gradlew` script in the subsequent stages. Ensure the `gradlew` script is executable.

```groovy
pipeline {
    agent any

    environment {
        DOCKERHUB_TOKEN = credentials('tokendocker')
        IMAGE_NAME = "${env.DOCKERHUB_TOKEN_USR}/jenkins-docker"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                git branch: 'main', url: 'https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847.git'
            }
        }

        stage('Assemble') {
            steps {
                dir('CA2/part2/react-and-spring-data-rest-basic') {
                    echo 'Assembling...'
                    sh 'chmod +x gradlew'
                    sh './gradlew clean assemble'
                }
            }
        }

        stage('Test') {
            steps {
                dir('CA2/part2/react-and-spring-data-rest-basic') {
                    echo 'Testing...'
                    sh './gradlew test'
                    junit 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Javadoc') {
            steps {
                dir('CA2/part2/react-and-spring-data-rest-basic') {
                    echo 'Generating Javadocs...'
                    sh './gradlew javadoc'
                    publishHTML(target: [
                            reportDir  : 'build/docs/javadoc',
                            reportFiles: 'index.html',
                            reportName : 'Javadoc'])
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('CA2/part2/react-and-spring-data-rest-basic') {
                    echo 'Building Docker Image...'
                    script {
                        dockerImage = docker.build("${IMAGE_NAME}:${BUILD_NUMBER}", "-f dockerfile .")
                    }
                }
            }
        }

        stage('Docker Login') {
            steps {
                echo 'Logging in to Docker Hub...'
                sh 'echo $DOCKERHUB_TOKEN_PSW | docker login -u $DOCKERHUB_TOKEN_USR --password-stdin'
            }
        }

        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker Image...'
                sh 'docker push $IMAGE_NAME:$BUILD_NUMBER'
            }
        }

        stage('Archiving') {
            steps {
                dir('CA2/part2/react-and-spring-data-rest-basic') {
                    echo 'Archiving...'
                    archiveArtifacts 'build/libs/*'
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            sh 'docker rmi $IMAGE_NAME:$BUILD_NUMBER'
            sh 'docker logout'
        }
    }
}
}
```

2. Configure Docker credentials in Jenkins:
    - Go to 'Manage Jenkins' -> 'Manage Credentials' -> 'Global credentials' -> 'Add Credentials'.
    - Select 'Username with password' as the kind, enter your Docker Hub username and password, and provide an ID.

<a href="https://ibb.co/bWWCr6V"><img src="https://i.ibb.co/4mmXjFL/Screenshot-from-2024-06-13-11-34-57.png" alt="Screenshot-from-2024-06-13-11-34-57" border="0"></a>

3. Install necessary Jenkins plugins:
    - Install 'Docker Pipeline', 'Docker', and 'HTML Publisher' plugins from 'Manage Jenkins' -> 'Manage Plugins' -> 'Available'.

4. Create a new Jenkins pipeline:
    - Go to Jenkins, create a new item, select 'Pipeline', and configure it to use 'Pipeline script from SCM'.
    - Choose 'Git' as SCM, provide the repository URL, specify the branch.
    - Set the 'Script Path' to `CA5/react-and-spring-data-rest-basic/Jenkinsfile`.
    - Save the pipeline configuration.

5. Add a Dockerfile to your repository (`CA2/part2`) to define the Docker image configuration.

6. Run the pipeline and validate the execution of all stages, checking the console output for each stage.

<a href="https://ibb.co/RDpLnKn"><img src="https://i.ibb.co/S3XqTgT/Screenshot-from-2024-06-13-11-08-17.png" alt="Screenshot-from-2024-06-13-11-08-17" border="0"></a>

<a href="https://ibb.co/QrhD99w"><img src="https://i.ibb.co/D5mr442/Screenshot-from-2024-06-13-11-07-53.png" alt="Screenshot-from-2024-06-13-11-07-53" border="0"></a>

7. Verify that the Docker image is successfully pushed to Docker Hub.

<a href="https://ibb.co/VxPy6bk"><img src="https://i.ibb.co/hct50rT/Screenshot-from-2024-06-13-11-25-28.png" alt="Screenshot-from-2024-06-13-11-25-28" border="0"></a>

This completes the setup and execution of the CI/CD pipelines using Jenkins for both parts of the assignment.

## Final Steps
- Mark your repository with the tag `ca5`.