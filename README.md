# LibraryDatabaseApplication
Library Database Application for DT211C4 Advanced Database Assignment 2

Alan Haverty

C12410858

14/12/2015

##Installation
#####Creates
Execute the sql create file to setup the tables

Then execute the sql insert file to setup some basic example data

Ensure the username and password used to execute the sql is also matching in the application.properties file!

#####Configuring the application
The executable jar should then be ran with the path to the application.properties file
e.g. java -jar -DpropertiesFilepath="fullpath\to\application.properties" alan\_haverty\_c12410858\_database\_assignment\_2.jar

The application.properties file consists of:

servername=localhost

username=libraryadmin

password=libraryadmin

portnumber=1521

#####Using the application
First select a branch to work with

Then, using the tabbed pages, either work with a particular patron or use the branches administration features.

#####Demonstrating the audit trigger

######Fees Due/Late return trigger
A trigger was implemented, for updating a borrowers fee after they return a book after the loan's due date.

The fee is calculated at 1 unit per day late for simplicity.

The steps to demonstrate this trigger are as follows:

1. Select the #1 branch, Bolton Street
2. Select Patron #1 - David
3. David should have a fee due of 5.00 after a fresh database creation
4. David also has a book yet to return. Selecting this book from the 'Return Book' list and clicking submit should return the book.
5. A popup should appear to confirm that the book was returned.
6. The fee should have also risen from 5.00 to reflect the fee applied for the latest late return.

######Double booking prevention/constraint trigger
This trigger prevents a patron from borrowing a bookcopy of a book they already have on loan (Note, trigger catches same books, not only bookCopies)

The steps to demonstrate this trigger are as follows:

1. Select the #1 branch, Bolton Street
2. Select Patron #1 - David
3. David should have a book ready for return in his return list names 'To kill a Mockingbird'
4. The same book is also available to checkout from the dropdown list above (Note: This is a different copy to the one David currently has on loan)
5. While the copy is physically available to loan, the trigger prevents David from taking the book. (Try selecting 'To Kill a Mockingbird' from the checkout dropdown and submit.


##List Of Assignment Content
1. 13 java class files are used, consisting of POJO files, helper classes, Database helpers and a java swing MVC application.
2. An application.properties file is also included in order to define the properties for the database to connect to
3. A runnable jar of the 13 class files is also included
4. Screenshots of each view are included
5. A screenshot of the erd designed for this assignment is included
