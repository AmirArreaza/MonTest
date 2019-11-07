# MonTest

Test assignement which propuse is to create a sandbox Java banking application that can be used for:

    ● Sending money between two predefined accounts with a positive starting balance
    ● Requesting account balance and list of transactions

## The application

The solution exposes three REST web services:

    1.- To list all the accounts 'GET' http://localhost:4567/Accounts
    2.- To get an account statement 'GET' http://localhost:4567/Accounts/:AccountNumber
    3.- To send money between accounts 'POST' 
    http://localhost:4567/Accounts/:Amount/:AccountNumberOrigin/:AccountNumberDestination
    
This system using a relation database hosted in a Heroku PostgreSQL service with the following data:

## Database

Host - ec2-54-243-203-179.compute-1.amazonaws.com
Database - d92pjrvcc5nvff
User - nrqnpozxicaiez
Port - 5432
Password - RoMm-XhDF-ig-2zXJdDoislBQl
URI - postgres://nrqnpozxicaiez:RoMm-XhDF-ig-2zXJdDoislBQl@ec2-54-243-203-179.compute-1.amazonaws.com:5432/d92pjrvcc5nvff
Heroku CLI - heroku pg:psql flying-fairly-4151 --app demo-appamir

## Contents

Inside the repository you can find the jar application which can be executed by the following command:
    Java -jar Monese.jar

On execution it will launch the local server on LocalHost:4567 and after 5 second it will execute an 
end to end transaction between accounts

the repository has the test package with a couple of test in the different areas of the project