# QAChallenge
1.	Follow the below mentioned steps to install the required apps to be able to run the code:
  a) Install jdk1.8.xx (URL: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
    o	set the java path in environment variable 
    o	create ‘JAVA_HOME’ in system variable and the path (eg. C:\Program Files\Java\jdk1.8.0_121)
    o	Edit the ‘Path’ in system variable and add “%JAVA_HOME%\bin;”
  b) Apache-maven-3.3.9(URL: https://maven.apache.org/download.cgi)
    o	download apache-maven-3.3.9-bin.zip from : https://maven.apache.org/download.cgi
    o	unzip it and place it in your c: or any other drive 
    o	set the maven path in environment variable 
    o	create ‘M2_HOME’ in system variable and the path (eg. C:\Jars\apache-maven-3.3.9)
    o	Edit the ‘Path’ in system variable and add “%M2_HOME%\bin;”
  c) install git (URL: https://git-scm.com/downloads)
    This completes the initial set up.
    
2.	To run the scripts, follow below mentioned steps:
    o	Create a folder in your drive 
    o	Open the git bash. Navigate to newly created folder and enter the following two commands -  
  	To clone the apis repository : 
  git clone https://github.com/subhashinitripathi/QAChallenge-apis.git

  	To clone the test case repository : 
  git clone https://github.com/subhashinitripathi/QAChallenge.git

    o	Open the command prompt and go inside your folder. You can see two folder
      	QAChallenge-apis
      	QAChallenge
    o	Go inside the QAChallenge-apis and execute the following command
        mvn clean install
    o	To run the test case, go inside the QAChallenge and execute the following command to run the SignUpMonthlyPage test case: 
         mvn -PsignUpMonthlyPage -Dapp.url=https://test-dot-naturalcycles-ncapp.appspot.com -Dconfif.file=localhost.properties clean install
     
     This command will run the test case on Chrome browser. If you want to run the test on different browsers, then you can use below command:
        mvn -PsignUpMonthlyPage -Dapp.url=https://test-dot-naturalcycles-ncapp.appspot.com -Ddefault.browser=firefox -Dconfif.file=localhost.properties clean install

3.	Commands to run test cases:
    o	Test script for Yearly Plan:	
    mvn -PsignUpYearlyPage -Dapp.url=https://test-dot-naturalcycles-ncapp.appspot.com -Dconfif.file=localhost.properties clean install
    o	Test script for Free Trial version :
    mvn -PsignUpTrialPage -Dapp.url=https://test-dot-naturalcycles-ncapp.appspot.com -Dconfif.file=localhost.properties clean install
    o	Test script to register already registered email id:
     mvn -PregisterExistingEmailId -Dapp.url=https://test-dot-naturalcycles-ncapp.appspot.com -Dconfif.file=localhost.properties clean install
    o	Test script for monthly Plan :
    mvn -PsignUpMonthlyPage -Dapp.url=https://test-dot-naturalcycles-ncapp.appspot.com -Dconfif.file=localhost.properties clean install
    o	Test script to validate credit card details:
    mvn -PverifyCreditCardDetails -Dapp.url=https://test-dot-naturalcycles-ncapp.appspot.com -Dcard.no= 4111111111111112 -Dconfif.file=localhost.properties clean install

  note: This test case can also be executed to validate cvv number and expiration date. Please replace -Dcard.no with -Dexpiration.date=<<date>> and for cvv , please replace -Dcvv.no=<<cvvno.>>

4.	All test case support chrome, firefox and internet explorer. 

5.	All test can run on Android devices. For Android devices, Appium set is required.











