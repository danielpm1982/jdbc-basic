# jdbc-basic
This is a simple Maven JDBC-MySQL console application with a full-CRUD DAO implemented using Java SE

[**Description of this repository**]<br>
This is a simple Maven JDBC console application, which demonstrates how to connect with an external DBMS (MySQL) and how to implement a full-CRUD DAO by simply using Java SE native DB Connection API. No JPA or Spring Data used.  

The domain classes (entities) of this app, as well as the respective DB tables, model a one to one total relationship (both ways) between Client and Address. 

You can recreate the schema easily at your MySQL from the schema.sql script attached at the root of this project. Just use the Server/Data import option of Workbench or simply run the script. It will create the schema, including the tables, and insert some sample data.

For testing this app, just run the main method of the Main class, using your IDE. Or, at your Terminal, run the start.bat file. You should have Maven, as well as Java 14, installed globally on your Operating System. As well as a MySQL process started and running at your localhost 3306 port (default). In case the username / password of your MySQL local instance is not root / root, first change that at the DAO class according to your own settings before running this app. Also, if your JDK has a different version, change that accordingly at the POM file.

This repository is a single-project repo with Git version control.

Some of my repositories are for backup only, each of them being a collection of tens of projects inside one same repo (with no individual version control for each project), and others are single-project repositores (with effective version control for that single project). As a distinction between them, the backup repos are named in uppercase with underscores (e.g. SPRING3) while the single-project ones are named in lowercase with dashes (e.g. springboot2-ac-di).

See all my public repositories at:<br>
https://github.com/danielpm1982?tab=repositories .

[**Copyright© License**]<br>
© 2020 Daniel Pinheiro Maia All Rights Reserved<br>
This GitHub repository - and all code (software) available inside - is exclusively for academic and individual learning purposes, and is **NOT AVAILABLE FOR COMMERCIAL USE**, nor has warranty of any type. You're authorized to fork, clone, run, test, modify, branch and merge it, at your own risk and using your own GitHub account, for individual learning purposes only, but you're **NOT ALLOWED to distribute, sublicense and/or sell copies of the whole or of parts of it** without explicit and written consent from its owner / author. You can fork this repository to your individual account at GitHub, clone it to your personal notebook or PC, analyse, run and test its code, modify and extend it locally or remotely (exclusively at your own GitHub account and as a forked repository), as well as send issues or pull-requests to this parent (original) repository for eventual approval. GitHub is in charge of explicitly showing whom this respository has been forked from. **If you wish to use any of this repository content in any way other than what is expressed above, or publish it anyway or anywhere other than as a forked repository at your own GitHub account, please contact this repository owner / author** using GitHub or the contact info below. For the meaning of the technical terms used at this license, please refer to GitHub documentation, at: <br> https://help.github.com/en/github .

[**Owner and Author of this GitHub Repository**]<br>
Daniel Pinheiro Maia<br>
[danielpm1982.com](http://www.danielpm1982.com)<br>
danielpm1982@gmail.com<br>
[linkedin.com/in/danielpm1982](https://www.linkedin.com/in/danielpm1982)<br>
Brazil<br>
.
