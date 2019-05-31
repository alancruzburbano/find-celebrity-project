# find-celebrity-project
Sample project to read data from CSV files and implements an algorithm to find the celebrity according to the following:

    -In a team of n people, a celebrity is known by everyone but he/she doesn't know anybody.

**Prerequisites:** [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 

## Getting Started

To install the  application, do the following:

1) Download the source using git clone command

```bash
git clone https://github.com/alancruzburbano/find-celebrity-project.git
cd find-celebrity-project
```
In the source folder project you will find 2 CSV files, one for the distribution matrix and the second with the names of people.
In application.properties file you can find some important configurations over the CSV file path to read, also there you can set the number of persons (n) in the group. Take in mind that the number in the property must be the same  number of rows and columns in the matrix-data.csv and lines in people-name.csv files.

To run the server application, just execute the following command:
 
```bash
./gradlew bootRun
```
