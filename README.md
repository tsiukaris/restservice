# Concurrency Task
## Getting Started
Having installed Maven on your machine run in root directory of the project
```
mvn package
```
OR
```
mvn install
```
Then you can run
```
mvn exec:java -Dexec.mainClass=tsiukaris.Main
```
to start the programm. Then follow instructions described below. 


## What this project does
There are two threads to consider:
* First thread scan incoming from console text numerals (values can be from "one" to "nine thousand nine hundred ninety nine") 
and stores it to the custom-made Collection with use of TreeSet in it. 
* Numerals should be typed once at a time (for example type "one hundred thirty five" and press Enter, type "one" and press Enter and so on).
Access to TreeSet is allowed throw synchronized methods.
* Second thread every 5 seconds removes from MyCollection the minimal element and show it in console.
