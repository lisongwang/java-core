This project aim at providing the latest Java-8 version of all the exercise answers
in <<Thinking in Java 4th Edition>>.

As we know that this book was written for jdk-1.6 almost 15 years ago. Many exercises 
in this book was suggested to implement by old API. Especially in Annotation chapter,
the old annotation process api was removed since jdk-1.7 thus we need to move on
to the new annotation api for those exercises. The lambda expression has also taken
place for the annoymous inner class when possible. And the stream api has maken use in
Container in Depth chapter as well.

At last, all the exercises were written totally by my own opinion without any reference
to the original exercise solutions along with this book. Simple exercises were combined
in one file as possible. You may also see some extension for the exercises that i
thought necessary especially in the Concurrency chapter. In I/O chapter, many exercises
need the file location as input from main function. The code organization in this 
project is based on resuing pattern that any class refered by more than one exercises
is placed in specific package with a self-explained package name. There is one more
util package hold the classes refered by exercises from different chapters.

The project was developed on jdk-1.8.0_211 and built by maven-3.6.2. My IDE is 
IntelliJ IDEA-2019.2.3. You may need some particular setup when running the exercises developed in pluggible annotation processor.
