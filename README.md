# multi-threaded-data-processing

Assignment was to :

Provide a java project using Eclipse demonstrating your knowledge/skills with concurrency and multi-threading:  
* This application will be given a path to a csv, comma-separated values, file that it must process.
* This file will be several 100s of GBs of data and will contain multiple entries with 10 fields each.
* The 10 fields that repeat over and over will be considered a record that needs to be processed.  We will refer to these fields as Field1, Field2, ..... to Field10.
* For the sake of this exercise assume that the processing of each records will take between 3 and 10 seconds.  Please simulate this with a sleep.
* Please create a multi-threaded application that will process this file as quickly as possible.  Assume a dedicated 8 core machine with 16 GB of ram.
* Please comment the code explaining why you've written the code the way you have and feel free to discuss other ways you considered writing the code.  This is your chance to illustrate your understanding of mutli-threaded and concurrancy programming.

This project uses Gradle.  
It also uses some Spring stuff, but can be removed easily.

To generate the right files for eclipse, just type:  
./gradlew eclipse  
Then import the project as an existing project.
