DROP DATABASE IF EXISTS platform;
CREATE DATABASE platform;
GRANT ALL PRIVILEGES ON dockerdemo.* TO 'platform'@'%' IDENTIFIED BY 'platform';
GRANT ALL PRIVILEGES ON dockerdemo.* TO 'platform'@'localhost' IDENTIFIED BY 'platform';