Install Java
 - sudo apt update
 - sudo apt install openjdk-17-jre

Install Apache Tomcat

Install MySQL
 - sudo apt update
 - sudo apt install mysql-server
 - sudo mysql_secure_installation
 - sudo systemctl start mysql
 - sudo mysql -u root -p
 - CREATE DATABASE proovitoo;
 - CREATE USER 'dev'@'localhost' IDENTIFIED BY '1234';
 - GRANT ALL PRIVILEGES ON proovitoo.* TO 'dev'@'localhost';
 - FLUSH PRIVILEGES;
 - EXIT;

Import the Database Dump
 - https://github.com/Silversoots1/smit-proovitoo/blob/815d822bee612c4b667b93ea10dc0c4054f55607/database_dump/mysql.sql
 - mysql -u dev -p proovitoo < /path/to/your/mysql.sql

Deploy the WAR File
 - https://github.com/Silversoots1/smit-proovitoo/blob/fdfe7a92ad5f735e044749a6189acb6640f0bfda/target/smit-proovitoo.war
 - Copy the WAR File to tomcat/webapps/

Start Tomcat: Start the Tomcat server.
 - tomcat/bin/startup.sh

Access the Application
 - http://localhost:8080/smit-proovitoo/LoginServlet

Database Configuration can be changed
 - in location src\main\resources\db.properties
