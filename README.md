## Server 
To start the server navigate to package Bank -> Scripts. <br />
To create bank server run "create_bank_server" script. <br />
To run bank server run "run_bank_server" script. <br />
To stop bank server run "stop_bank_server" script. <br />

Server will start on port 6000 and also locally on port 6000. <br />
MYSQL Database is initialized with random data to show functionalities. <br />
Database will start on port 3306 and locally on port 3303. <br />

#### Mannually starting server
Bank server is a multi-container application which consists of bank_app and mysql_db service <br />
To manually create server, run docker-compose build -> docker-compose up in Bank package.
<br />

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
