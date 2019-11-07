# MonTest

Test assignment whose purpose is to create a sandbox Java banking application that can be used for::

    ● Sending money between two predefined accounts with a positive starting balance
    ● Requesting account balance and list of transactions

## The application

The solution exposes three REST web services:

    1.- To list all the accounts 'GET' http://localhost:4567/Accounts
    2.- To get an account statement 'GET' http://localhost:4567/Accounts/:AccountNumber
    3.- To send money between accounts 'POST' 
    http://localhost:4567/Accounts/:Amount/:AccountNumberOrigin/:AccountNumberDestination
    
This system uses a relational PostgreSQL database hosted in my Heroku server with the following connection data:

### Database

Host - ec2-54-243-203-179.compute-1.amazonaws.com
Database - d92pjrvcc5nvff
User - nrqnpozxicaiez
Port - 5432
Password - RoMm-XhDF-ig-2zXJdDoislBQl
URI - postgres://nrqnpozxicaiez:RoMm-XhDF-ig-2zXJdDoislBQl@ec2-54-243-203-179.compute-1.amazonaws.com:5432/d92pjrvcc5nvff
Heroku CLI - heroku pg:psql flying-fairly-4151 --app demo-appamir

### Repository Contents

Inside the repository, you will find the jar application. This can be executed using the following command: 
Java -jar Monese.jar

On execution, it will launch the local server on LocalHost:4567 and after 5 seconds it will execute an end-to-end 
transaction between accounts.

The repository contains the test package with a couple more tests in different areas of the project.