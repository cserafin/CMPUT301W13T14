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

Go to the root directory of the prj, and give ./test permission to execute
(ie. 'chmod +x test' or 'chmod 777 test'). Run by simply typing './test' 
followed by the following arguements:

'run X': runs XTest.class

'all': runs all JUnit tests on all classes

'build': builds the Android files (essentially an alias for 'ant debug')

<none>: runs some integration tests from TestMaster
