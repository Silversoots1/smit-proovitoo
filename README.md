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
 - mysql -u dev -p your_database_name < /path/to/your/database_dump.sql

Deploy the WAR File
 - Copy the WAR File to tomcat/webapps/

Start Tomcat: Start the Tomcat server.
 - tomcat/bin/startup.sh

Access the Application
 - http://localhost:8080/smit-proovitoo/LoginServlet

Database Configuration can be changed
 - in location src\main\resources\db.properties
