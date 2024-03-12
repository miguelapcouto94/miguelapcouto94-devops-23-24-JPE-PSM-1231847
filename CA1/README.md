# README

## Class Assignment 1
### CA1 - Version Control with Git
# Table of Contents

1. [Introduction](#introduction)
2. [First Part](#first-part)
    - [Login in your GitHub account](#1-login-in-your-github-account)
    - [Create an empty repository in GitHub](#2-create-an-empty-repository-in-github)
    - [Grant admin rights to professors](#3-grant-admin-rights-to-professors)
    - [Create issues in GitHub main task](#4-create-issues-in-github-main-task)
    - [Download the tutorial](#5-download-the-tutorial)
    - [Add a new field to the app](#6-add-a-new-field-to-the-app)
3. [Second Part](#second-part)
    - [Implement a simple scenario illustrating a simple git workflow](#7-implement-a-simple-scenario-illustrating-a-simple-git-workflow)
    - [Create branches for fixing bugs](#8-create-branches-for-fixing-bugs)
4. [Analysis of the alternative](#analysis-of-the-alternative)
5. [Implementation of the alternative](#implementation-of-the-alternative)


### Introduction:
This README provides detailed instructions and guidelines for completing the Class Assignment 1 (CA1) - Version Control with Git. The assignment involves setting up a simple software project for registering company employees and conducting tutorials on version control using Git.

The instructions are divided into two main parts:

  - First Part: Setting up the project, creating a repository, downloading necessary resources, and implementing specific features.

  - Second Part: Demonstrating a simple Git workflow, including branching, implementing new features, fixing bugs, and tagging versions.

Each step is accompanied by detailed instructions on what needs to be done, including command-line commands and explanations where necessary.

## First Part:

### 1. Login in your GitHub account

[GitHub home page](https://github.com//);

### 2. Create an empty repository in GitHub with the following name "devops-23-24-jpe-psm-1231847.":

- Log in to your GitHub account and navigate to the homepage.
- In the top right corner, click on your avatar and select "Your repositories" from the dropdown menu.
- On the "Your repositories" page, click the green "New" button located in the top right corner.
- On the "Create a new repository" page, fill in the following details:
  - Repository name: "devops-23-24-jpe-psm-1231847"
  - Visibility: Check the "Private" option to make the repository private.
-   Create a repository;
-   Copy the repository link;
-   On command line, navigate to the desired folder to save the tutorial, in this case it was used:

    > cd c:/
    > 
    > cd /home/miguelcouto/repos

-   Create a folder to keep your repository:

    > mkdir devOps

-   Initialize your local repository:

    > cd devops 
    > 
    >git init

-   Create a new folder named CA1:

    > mkdir CA1


### 3. Grant to your professors admin rights:


- Go to your repository on GitHub. 
- Click on "Settings" tab at the top of the repository.
- In the settings menu, select "Manage access" from the sidebar.
- Invite Collaborators:
    - Under "Collaborators", type the email address of the professor you want to invite.
    - Press Enter to add the collaborator.
    - Select the collaborator's permission level. If you want them to have admin access, choose "Admin".
    - Click on "Add [Collaborator]" to send the invitation.

    
### 4. Create issue(s) in GitHub main task:

-   At the top of the repository page, click on the "Issues" tab.
-   On the right side of the page, click on the "New issue" button.
-  In the "Title" field, enter a descriptive title for the issue.
- If necessary, fill in the description with more details about the issue.
 -  In the "Assignees" field, select "Assign yourself" to assign the issue to yourself.
-   Issue example:
-   [#1](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/1 "Create CA1 folder and copy tutorial React.js and Sprint Data REST application code."): Create a folder "CA1" and copy React.js tutorial and Sprint Data REST application code.
-   [#2](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/2 "Add a new field to the application (JobYears"): Add a new field to the application(JobYears)
-   [#3](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/3 "Create email atribute and respective tests"): Create email attribute and respective tests
-   [#4](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/4 "Fix email validation"): Fix email validation
-   [#5](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/5 "Create and update the README.md document"): Create and update the README.md document



### 5. Download the tutorial:

-   Access the website:  [Tutorial React.js and Spring Data REST](https://spring.io/guides/tutorials/react-and-spring-data-rest/);
-   Read the content;
-   Select: Go to repo -> Code -> HTTPS -> Copied link;
-   With "Terminal", validate its location:

    > pwd

-   Navigate to the folder where you keep your projects:

    > cd /home/miguelcouto/repos/

-   Clone the tutorial repository:

    > git clone  [https://github.com/spring-guides/tut-react-and-spring-data-rest.git](https://github.com/spring-guides/tut-react-and-spring-data-rest.git)

-   Into class assignment folder (CA1), download the example web app´s code.

    > mv ./tut-react-and-spring-data-rest/* ./Devops/CA1/

- Remove the .git directory from the copied content:
     >  rm -rf ./CA1/.git

-  Replace the contents of the .gitignore file with the new content generated from gitignore.io
    > echo "<new_content_generated_from_gitignore_io>" > ./CA1/.gitignore
-   Connect the remote repository that you previously created in GitHub to your local repository.

    > git remote add origin https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847.git

-   Check the state of the working directory:

    > git status

-   Add the changed files and directories to the next commit:

    > git add *
    
-   Commit changes:

    > git commit -m "Initial commit, closes  [#1](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/1  "Create CA1 folder and copy tutorial React.js and Sprint Data REST application code.")"

-   Push the current branch to remote repository and set the remote as upstream

    > git push --set-upstream origin main

-   Create the tag for the initial version as v1.1.0 

    > git tag -a v1.1.0 

-   Push the tag:

    > git push origin v1.1.0


### 6. Add a new field to the app:

-   Add a new field to record the employee's years with the company (int jobYears);
-   Add validations for the new attribute;
-   Add unit test to the new field;
-   Update app.js class, and DatabaseLoader.java class to include the new field.
-   Run the application using the Maven build tool

> ./mvnw spring-boot:run


-   In your browser, fill the following URL: [localhost:8080](http://localhost:8080)
-   Debug the server and client parts: in client, use Chrome and its "React Developer Tools" extension; in server, use an IDE like IntelliJ.
-   Commit and push your changes:

    > git add * 
    > 
    > git commit -m "Update Class Employee and test class, closes  [#2](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/2  "Add a new field to the application")"
    >
    >git push

-   Tag your version 1.2.0:

    > git tag -a v1.2.0

-   Push the tag:

    > git push origin v1.2.0
    
-   Mark your repository with the tag ca1-part1.

    > git tag -a ca1-part1 
    > 
    > git push origin ca1-part1


## Second Part

### 7. **Implement a simple scenario illustrating a simple git workflow:**

- Create a new branch "email-field":

> git branch email-field

- Checkout the branch:
> git checkout email-field

- Add the suggested changes:
  - Add validation and unit tests to the email field;
  - Debug the server and client parts of the solution;
- Push the branch:
>git push --set-upstream origin email-field

- Add all the changes:
>git add *
> 
> git commit -m "Create email attribute and respective tests, closes  [#3](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/3  "Create email atribute and respective tests")" 
> 
>git push
>
- Return to the main branch to merge:
> git checkout main 
> 
> git merge --no-ff email-field
> 
> git push origin main

- Tag the version:
> git tag -a v1.3.0 
> 
> git push origin v1.3.0


### 8. **Create Branches for Fixing Bugs:**

- Create a branch for fixing invalid email. 
> git branch fix-invalid-email

- Checkout the branch:
 > git checkout fix-invalid-email

 - Add validation (has @ and .) to the email field;
 - Debug the server and client parts of the solution;
 - Push the branch:
> git push --set-upstream origin fix-invalid-email

- Add all changes:
> git add .
> 
> git commit -m "Fix email validation, closes  [#4](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/4  "Fix email validation")"
> 
> git checkout main
> 
> git merge --no-ff fix-invalid-email
> 
> git push origin main

- Tag the version: 
> git tag -a v1.3.1 
> 
> git push origin v1.3.1

- Mark your repository with the tag ca1-part2
> git tag -a ca1-part2
> 
> git push origin ca1-part2

### 9. Produce a Technical Report Document:

- Create a README.md file, where the technical report of this class assignment will be done:
>touch README.md
- Make the necessary changes to the README.md file to include the technical report of the class assignment.
- Commit and push the README.md to the remote repository:

>git add * 
> 
> git commit -m "Create and update the README.md document, closes [#5](https://github.com/miguelapcouto94/miguelapcouto94-devops-23-24-JPE-PSM-1231847/issues/5  "Create and update the README.md document")"
>
> git push
## Analysis of the alternative:

## Comparison of Mercurial and Git:
Mercurial (Hg) is a distributed version control system (DVCS) that serves as an alternative to Git. It shares many similarities with Git but offers a different approach to version control. In this section, we will analyze how Mercurial compares to Git in terms of version control features and describe how Mercurial could be used to achieve the same goals as presented in this assignment.



| Feature             | Mercurial (Hg)                                          | Git                                                     |
|---------------------|---------------------------------------------------------|---------------------------------------------------------|
| **Model**           | Distributed: Each user has a complete copy of the repository, enabling offline work and independent branching and merging. | Distributed: Similar to Mercurial, Git also follows a distributed model, allowing each user to have a full copy of the repository.|
| **Branching**       | Mercurial has lightweight branches that are easy to create and manage. Branches are typically used for short-term, experimental changes. | Git provides powerful branching and merging capabilities, allowing for complex branching strategies such as feature branching, release branching, and hotfix branching. |
| **Performance**     | Mercurial's performance is generally comparable to Git, offering efficient operations for common version control tasks. | Git is known for its fast performance, especially when it comes to operations like commit, merge, and checkout. |
| **History Tracking**| Mercurial tracks changes and history both locally and remotely, allowing users to view the complete history of the repository. | Git also tracks changes and history locally and remotely, providing a comprehensive view of the project's development over time. |
| **Conflict Handling**| Mercurial automatically merges changes and flags conflicts when necessary, making it easy for users to resolve conflicts during the merge process. | Git automatically merges changes and identifies conflicts, requiring users to manually resolve conflicts by editing the affected files. |
| **Windows Support** | Mercurial offers good support for Windows users, with native tools like TortoiseHg providing a user-friendly interface. | Git provides decent support for Windows users, although some third-party tools may be required for a smoother experience compared to native Unix-based systems. |
| **Community**       | Mercurial has an active community, but it's generally smaller compared to Git. However, it still offers ample resources and support for users. | Git boasts a large and vibrant community with extensive resources, including documentation, tutorials, forums, and third-party tools, making it one of the most widely used version control systems. |

Overall, both Mercurial and Git are powerful version control systems with their own strengths and weaknesses. The choice between them often depends on factors such as personal preference, team familiarity, project requirements, and specific use cases.








## Implementation of the alternative:

Below is a comparison of Mercurial commands with their Git counterparts, ensuring a clear understanding of how to perform similar version control operations using Mercurial.

| Command                                  | Git                    | Mercurial          |
|------------------------------------------|------------------------|--------------------|
| Initialize local repository              | >git init              | > hg init project  |
| Clone repository from browser            | >git clone  <url>      | > hg clone <url>    |
| Connect to remote repository             | >git remote add origin | > hg pull -f <url> |
| Commit changes to the repository         | >git commit            | >hg commit -m      |
| Check the state of the working directory | > git status           | > hg status        |
| Add all changes                          | > git add *            | > hg addremove     |
| Create a tag                             | > git tag              | > hg tag           |
| Share changes                            | > git push             | > hg push          |
| Merge branches                          | > git merge            | > hg merge         |
| Create branches                         | > git branch           | > hg branch        |
| Checkout a branch                       | > git checkout         | > hg update        |

This comparison helps understand how Mercurial commands align with those of Git, facilitating a seamless transition for users familiar with Git's workflow.



