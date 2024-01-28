# Virtual Bank App

## Overview

The Virtual Bank App is a Java Spring Boot application that provides a virtual banking experience. Users can create deposits, request credits, perform multi-currency transfers, and manage their profile.

## Features

- **Account Management:**
  - Open new deposits.
  - Request credit, processed by bank employees.
  - View account balances and transaction history.
  - Multi-currency transfers to other accounts.

- **Financial Operations:**
  - Suspend and resume deposits.
  - Withdraw profits when the deposit term is reached.

- **User Profile:**
  - Edit personal information.
  - Register and manage user profiles.

## Technologies Used

- **Backend:**
  - Java Spring Boot
  - RSA256 encryption for secure communication

- **Database:**
  - MySQL (for data storage)


## Server 
(Docker required!)
<br />
<br />
To start the server navigate to package Bank -> Scripts. <br />
To create bank server run "create_bank_server" script. <br />
To run bank server run "run_bank_server" script. <br />
To stop bank server run "stop_bank_server" script. <br />

Server will start on port 6000 and also locally on port 6000. <br />
MYSQL Database is initialized with random data to show functionalities. <br />
Database will start on port 3306 and locally on port 3303. <br />

## Client app
To run GUI Client, navigate to "Client" package, then run script "run_client_app" <br />
<br />

## Employee app
To run GUI Employee, navigate to "Employee" package, then run script "run_employee_app" <br />
<br />

### How to run scripts in terminal (unix):
Navigate to appropriate location <br />
Change script mode using "chmod +x <script_name>"<br />
Type "./<script_name>"
<br />
