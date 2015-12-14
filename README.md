# LibraryDatabaseApplication
Library Database Application for DT211C4 Advanced Database Assignment 2

Alan Haverty

C12410858

14/12/2015

##Installation
#####Creates
Execute the sql create file to setup the tables

Then execute the sql insert file to setup some basic example data

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

Select the #1 branch, Bolton Street

Select Patron #1 - David

David should have a fee due of 5.00 after a fresh database creation

David also has a book yet to return. Selecting this book from the 'Return Book' list and clicking submit should return the book.

A popup should appear to confirm that the book was returned.

The fee should have also risen from 5.00 to reflect the fee applied for the latest late return.

######Double booking prevention/constraint trigger
This trigger prevents a patron from borrowing a bookcopy of a book they already have on loan (Note, trigger catches same books, not only bookCopies)

The steps to demonstrate this trigger are as follows:

Select the #1 branch, Bolton Street

Select Patron #1 - David

David should have a book ready for return in his return list names 'To kill a Mockingbird'

The same book is also available to checkout from the dropdown list above (Note: This is a different copy to the one David currently has on loan)

While the copy is physically available to loan, the trigger prevents David from taking the book. (Try selecting 'To Kill a Mockingbird' from the checkout dropdown and submit.