CMPUT301W13T14
==============

Android Group Project

This project was written by Kenneth Armstrong, Peter Maidens, Chris Serafin
and Kimberly Kramer. It is released under the MIT license, which should be
included in license.txt.

To run the project from the command line, you will need ant installed. 

Go to the root directory of the prj, and type 'ant debug' to build the files.

To start an AVD, type 'android avd' (after including Android SDK in path)

To install the files on the AVD, type 'ant install'. Note that this step
can be combined with building, ie. 'ant debug install'.


To run the tests, you will also need ant installed, as well as Bash.

Go to the root directory of the prj, and type './test'. You may need
to make this file executable by typing 'chmod 777 test'. Note that
this will give all users full permission, so you may want to use 
something like 'chmod +x test' instead.    
