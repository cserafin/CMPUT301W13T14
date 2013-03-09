CMPUT301W13T14
==============

Android Group Project

This project was written by Kenneth Armstrong, Peter Maidens, Chris Serafin
and Kimberly Kramer. It is released under the MIT license, which should be
included in license.txt.


TO RUN:
------
To run the project from the command line, you will need ant installed. 

Go to the root directory of the prj, and type 'ant debug' to build the files.

To start an AVD, type 'android avd' (after including Android SDK in path)

To install the files on the AVD, type 'ant install'. Note that this step
can be combined with building, ie. 'ant debug install'.


TO TEST:
-------
NOTE: The JUnit tests will likely not work in Eclipse, to the fact that they
sit outside the source code directory. This was done to seperate the test 
code from the application code.

To run the tests, you will also need ant installed, as well as Bash.

Go to the root directory of the prj, and give ./test permission to execute
(ie. 'chmod +x test' or 'chmod 777 test'). Run by simply typing './test' 
followed by the following arguments:

'run X': runs XTest.class

'all': runs all JUnit tests on all classes

'build': builds the Android files (essentially an alias for 'ant debug')

'build install': builds plus installs APK on running emulator

'clean': clean the class files from the tests

'clean all': clean both test class files and Android build stuff

<none> || 'intg': runs some integration tests from TestMaster class
