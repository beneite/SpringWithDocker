-- Create database if it does not exist
CREATE DATABASE IF NOT EXISTS userManagementDb;
USE userManagementDb;

-- Create users table
CREATE TABLE IF NOT EXISTS usersTable (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userFirstName VARCHAR(20) NOT NULL,
    userLastName VARCHAR(20) NOT NULL,
    userEmail VARCHAR(60) NOT NULL UNIQUE
);

-- Insert sample users
INSERT INTO usersTable (userEmail, userFirstName, userLastName) VALUES
('Aditya.Mishra@gmail.com', 'Aditya', 'Mishra'),
('Umang.Kamal@yopmail.com', 'Umang', 'Kamal'),
('Mukesh.Rathod@yopmail.com', 'Mukesh', 'Rathod');

-- creating config table to store config data
CREATE TABLE IF NOT EXISTS config (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    config_key VARCHAR(50) UNIQUE NOT NULL,   -- e.g., "bands", "designations"
    config_values TEXT NOT NULL              -- e.g., JSON or comma-separated list
);

-- insert data into config table
INSERT INTO config (id, config_key, config_values)
VALUES
(1, 'bands', JSON_ARRAY('L1','L2','L3','L4','L5','L6','L7','L8','L9','L10')),
(2, 'designations', JSON_ARRAY('Cloud Engineer-1','Cloud Engineer-2' ,'Cloud Engineer-3', 'Senior Manager','SDET-1','SDET-2','QA Analyst','SDET Lead','SDET Manager','SDET Director', 'SDE-1','SDE-2', 'SDE Lead', 'SDE Manager', 'SDE Director')),
(3, 'departments', JSON_ARRAY('QA','DEV', 'Product', 'Program', 'Infra', 'Cloud', 'DevOps')),
(4, 'businessUnits', JSON_ARRAY('Runner', 'Logistics', 'Zodiac'));

-- Create users table
CREATE TABLE IF NOT EXISTS employee (

    employeeId INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    company_email VARCHAR(150) NOT NULL UNIQUE,
    personal_email VARCHAR(150) NOT NULL UNIQUE,
    date_of_joining DATE NOT NULL,
    department VARCHAR(100) NOT NULL,
    business_unit VARCHAR(100) NOT NULL,
    designation VARCHAR(100) NOT NULL,
    employee_band VARCHAR(10) NOT NULL
);

-- Insert sample employees
INSERT INTO employee (first_name, last_name, company_email, personal_email, date_of_joining, department, business_unit,designation, employee_band)
VALUES
('Ashish', 'Mishra', 'ashish.mishra@valve.com', 'ashish.mishra@gmail.com' , '2022-03-15', 'QA', 'Runner' , 'SDET-1', 'L2');