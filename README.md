1. Install Requirements
- MySQL Server
- Java JDK
- MySQL Connector/J
2. Database Setup
Run the following SQL:
CREATE DATABASE studentdb;
USE studentdb;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50)
);
CREATE TABLE students ( 
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50), 
age INT, 
course VARCHAR(50), 
subjects TEXT, 
total INT, 
percentage DOUBLE, 
result VARCHAR(10) 
);
3. Compile
javac -cp ".;lib/mysql-connector-j-9.6.0.jar" *.java
4. Run
java -cp ".;lib/mysql-connector-j-9.6.0.jar" LoginForm
