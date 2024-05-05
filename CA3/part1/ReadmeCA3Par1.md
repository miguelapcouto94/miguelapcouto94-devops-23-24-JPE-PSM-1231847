# Technical Report for CA3 Part 1: Virtualization with Vagrant

# Table of Contents

In this report, I document the procedures undertaken to fulfill Part 1 of our assignment, which entails practicing the
utilization of VirtualBox on a Ubuntu Virtual Machine (VM).

## Introduction

The primary objective of this assignment segment is to gain proficiency in utilizing VirtualBox to create and manage
VMs, particularly focusing on Ubuntu. Within this VM environment, we aimed to execute previous course projects
seamlessly. The tasks involved encompassed the creation of the VM, cloning of our individual repository into it, and the
subsequent attempt to build and run two specific projects: the basic Spring Boot project and the gradle_basic_demo
project.

To ensure successful project execution, it was imperative to install all requisite dependencies, including Git, JDK,
Maven, and Gradle. Additionally, awareness of potential hurdles, especially when executing
components of the gradle_basic_demo project due to the absence of a desktop interface (Ubuntu Server), was essential.

This report comprehensively elucidates the steps undertaken, challenges encountered, and the solutions devised
throughout the process. Each phase is detailed meticulously, aiming to provide a comprehensive understanding of the
entire procedure.

# Creating Issues in GitHub Main Task:

- #18 CA3_Part1: Created README.md;

## Setup and Configuration

This section provides a detailed guide on configuring the virtual machine using VirtualBox, focusing on setting up the
environment for project development. Additionally, it covers the installation of essential development tools required to
execute the projects effectively.

### Creating the Virtual Machine

#### 1. **Download and Install VirtualBox

**: Download and install VirtualBox from [Oracle's website](https://www.virtualbox.org/)

#### 2. **Setting Up a New VM**:

- Open VirtualBox and click on "New" to create a new virtual machine.
- Name your VM (e.g., "MiguelVM") and choose "Linux" as the type and "Ubuntu (64-bit)" as the version.
- Allocate memory (RAM): Recommended to set at least 2048 MB for adequate performance.
- Create a virtual hard disk: Choose VDI (VirtualBox Disk Image) and allocate at least 10 GB of space. Set the storage
  on the physical hard disk as dynamically allocated.

#### 3. **Install Ubuntu**:

- Download the Ubuntu Server ISO file from [Ubuntu's official site](https://ubuntu.com/download/server).
- Mount the ISO file to your VM: Within VirtualBox, select your VM, navigate to "Settings," then go to "Storage."
  Select "Empty" under Controller: IDE, then click on the disk icon adjacent to "Optical Drive" and choose "Choose a
  disk file..." Locate and select your downloaded ISO file.
- Start the VM and proceed with the on-screen instructions to install Ubuntu. During installation, opt for the standard
  utilities for a server setup. If prompted, install the OpenSSH server to enable remote connections.

#### 4. **Virtualization and Networking Setup**:

4.1 **Create a Host-Only Network**:

- Open your VM application;
- Navigate to the **Host Network Manager**.
- Click on **Create** to add a new host-only network.
- Name and configure this network within my VM's network settings.

4.2 **Setup Networking for the VM**:

- Set Network Adapter 2 to a Host-only Adapter in the VM's settings.
- Verify the IP range for the host-only network (e.g., 192.168.56.1/24).
- Assign an appropriate IP from this range to the adapter, such as 192.168.56.5

#### 5. Initial VM Setup

- Start your VM and log in.
- Update package repositories:
  ```bash
  sudo apt update
  ```
- Install necessary network tools:
  ```bash
  sudo apt install net-tools
  ```
- Configure the network interface by editing the Netplan configuration file:
  ```bash
  sudo nano /etc/netplan/01-netcfg.yaml
  ```

-This is how the `01-netcfg.yaml`should look like:

    network:
        version: 2
        renderer: networkd
        ethernets:
            enp0s3:
                dhcp4: yes
            enp0s8:
                addresses:
                    - 192.168.56.5/24

- Apply the changes with:
  ```bash
  sudo netplan apply
  ```

#### 6. Additional Utilities

- **SSH Setup**:
    - Install OpenSSH Server:
      ```bash
      sudo apt install openssh-server
      ```
    - Enable password authentication in the SSH configuration.
    - Restart SSH service:
      ```bash
      sudo systemctl restart ssh
      ```

- **FTP Setup**:
    - Install `vsftpd`:
      ```bash
      sudo apt install vsftpd
      ```
    - Enable write access in the FTP configuration.
        ```bash
        sudo nano /etc/vsftpd.conf
        ```
    - Uncomment the line **write_enable=YES**

    - Restart FTP service:
      ```bash
      sudo systemctl restart vsftpd
      ```

#### Software Installation

Once the Ubuntu server is up and running, proceed with installing the necessary software for your development
environment:

1. **Update Your System**:

- Open a terminal and run the following commands to update your system:
  ``` bash
  sudo apt update
  sudo apt upgrade
  ```

2. **Install Essential Tools**:

- **Git**: To clone and manage your project repositories.
  ``` bash
  sudo apt install git
  ```
- **Java Development Kit (JDK)**: Essential for running Java applications.
  ``` bash
  sudo apt install openjdk-17-jdk openjdk-17-jre
  ```
    - The installed JDK version 17 was installed in order for the projects (namingly, CA2 Part2 built with java 17) to
      be able to run


- **Maven**: For building and managing Java-based projects.
  ``` bash  
  sudo apt install maven
  ```
- **Gradle**: For building and automating Java projects.
  ```bash
  wget https://services.gradle.org/distributions/gradle-8.6-bin.zip
  sudo mkdir /opt/gradle
  sudo unzip -d /opt/gradle gradle-8.6-bin.zip
  echo "export GRADLE_HOME=/opt/gradle/gradle-8.6" >> ~/.bashrc
  echo "export PATH=$PATH:$GRADLE_HOME/bin" >> ~/.bashrc
  source ~/.bashrc
  ```
    - The version 8.6 was chosen for compatibility purposes with Gradle-built projects

3. **Verify Installations**:

- Ensure all installations are correct by checking the versions of the installed software:
  ``` bash
  git --version
  java -version
  mvn -version
  gradle -version
  ```

By following these instructions, the virtual machine will be equipped with the essential tools and configurations
required to clone the repository and execute the projects. This configuration establishes a consistent development
environment mirroring real-world server setups, offering a sturdy framework for ongoing development and educational
purposes.

## Project Migration and Setup

This section outlines the essential procedures for cloning your project repositories into the virtual machine and
configuring the environment for project execution. It specifically addresses the "spring boot tutorial basic project"
and the "gradle_basic_demo" project from earlier assignments.

#### Cloning indivual repository into the VM

1. **Open a Terminal in Your VM**:
    - Access the terminal through your VM's interface. If you are using SSH to connect to the VM, ensure it's set up
      during the Ubuntu installation.
      ssh-keygen -t ed25519 -C 1231847@isep.ipp.pt
        cat ~/.ssh/id_ed25519.pub
    - Copy the public key and add it to your GitHub account to enable SSH access.
    - Clone your repository using the SSH URL provided by GitHub.
    - Navigate to the directory where you want to store your projects.
    - Use the git command to clone your repository. Replace `<repository-url>` with the URL of your GitHub repository:
      ``` bash
      git clone
        ```
    - Enter your directory containing the projects:
    - Navigate to the project directory:
      ``` bash
      cd directory/with/spring-boot-tutorial-basic
      ```
2. **Clone Your Repository**:
    - Navigate to a directory where you want to store your projects, such as `/home/username/projects`.
    - Use the git command to clone your repository. Replace `<repository-url>` with the URL of your GitHub repository:
      ``` bash
      git clone git@github.com:miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847.git
      ```
    - Enter your directory containing the projects:
      ``` bash
      cd dirctory/holding/projects
      ```

#### Setting Up the Projects

1.
    - Configure Maven Wrapper and Gradle Wrapper to give executing permissions:
      ```bash
       chmod +x mvnw
       chmod +x gradlew
       ```
2. **CA1**:
    - Navigate to the project directory:
      ``` bash
      cd directory/with/spring-boot-tutorial-basic
      ```
    - Build the project using Maven:
      ``` bash
      ./mvnw clean install
      ```
    - Run the project:
      ``` bash
      ./mvnw spring-boot:run
      ```
    - Check that the application is running correctly by accessing it from your host machine’s web browser using the
      VM’s IP address and the port configured in the project.
   ```bash
    ip addr
    ```
    - put the IP and the port 8080 on the browser address.


3. **CA2 Part1**:
    - Navigate to the Gradle project directory:
      ``` bash
      cd directory/holding/gradle_basic_demo
      ```
    - Before building, ensure all Gradle dependencies are set up properly. Since some functionalities might not work due
      to the lack of a desktop environment, adjust the `build.gradle` if necessary.

    - Build the project using Gradle and run the server:
      ``` bash
      ./gradlew build
      ./gradlew runServer
      ```
    - Build the project in your computer terminal and run the client:
      ``` bash
      ./gradlew runClient --args="192.168.56.5 59001"
      ```
    - The project should run smoothly


5. **CA2 Part2**:
    - Navigate to the basic folder:
   ``` bash
   cd directory/of/basic/project
   ```
    - Run with gradle:
   ``` bash
   ./gradlew build
    ./gradlew bootRun
   ```
    - Check your VM's IP:
   ```bash
   ip addr
   ```
    - Write `VM'sIP.8080` on your browser address. The application should run smoothly.

### Troubleshooting and Issues

In this section, we outline common problems that may arise during the setup and use of the virtualized environment,
along with recommended solutions to ensure smooth operation of our development projects.

#### Network Configuration Errors

- **Symptom**: Inability to connect to the VM from the host machine.
- **Solution**: Verify the network adapter settings in VirtualBox or UTM. Ensure the network mode is set to 'Bridged'
  or 'NAT' with appropriate port forwarding configured.

#### Software Dependency Issues

- **Symptom**: Project fails to build due to missing or conflicting dependencies.
- **Solution**: Check the project's documentation for dependency requirements and ensure all dependencies are correctly
  installed. Use virtual environments where applicable to isolate and manage dependencies effectively.

#### Performance Issues

- **Symptom**: Applications run slowly or are unresponsive.
- **Solution**: Increase the VM’s allocated memory and CPU resources in VirtualBox or UTM settings. Ensure our host
  machine has sufficient resources to share.

#### Access Permissions

- **Symptom**: Permission denied errors when attempting to execute scripts or access certain files.
- **Solution**: Adjust the file permissions using `chmod` or change the ownership with `chown` if necessary. Ensure we
  are operating with the appropriate user privileges.

### Conclusion

Upon completion of CA3 Part 1, we will have successfully set up a virtualized development environment using VirtualBox
or UTM and migrated two key projects into this environment. This exercise not only enhances our understanding of
virtualization technology but also equips us with valuable skills in configuring and managing isolated development
environments. These skills are crucial in a professional setting where development and testing environments often need
to mirror production environments closely.

### Version Control and Documentation

Maintaining a detailed history of changes and proper documentation is essential for project management and future
reference. This section provides guidelines on how to manage our version control commitments and documentation
effectively.

#### Committing Changes to Your Repository

- **Regular Commits**: Make frequent commits to your repository with descriptive commit messages that clearly explain
  the changes or enhancements made.
  ```
  git add .
  git commit -m "Describe your changes here"
  git push origin main
  ```

#### Tagging for Release

- **Tagging the Final Submission**: Once you complete your assignment, tag your repository to mark the version of the
  project that corresponds to your submission.
  ```
  git tag -a ca3-part1 -m "Complete CA3 Part 1"
  git push origin --tags
  ```

#### Documentation

- **Readme File**: Document all steps, configurations, and notable decisions in the `readme.md` file located in
  the `/ca3/part1/` directory of your repository.
- **Use Markdown**: Employ Markdown syntax to format your documentation clearly and
  professionally. [Markdown Guide](https://en.wikipedia.org/wiki/Markdown).

By following these practices, we ensure that our project is not only well-documented for grading purposes but also
prepared for any further development