preconditions :java 1.8 and maven 3.8.5
1. installation of preconditions
	install and add to environment variable jdk 1.8
	variable name:JAVA_HOME
	value:<instalation directory>\java\jre1.8.0_331 (e.g. : C:\Program Files\Java\jdk1.8.0_202)

	download: https://www.oracle.com/mx/java/technologies/javase/javase8-archive-downloads.html
	depending on SO version each installer works little different but at the end creates the same file tree


	install and add to path environment variable maven 3.8.5
	maven is just a unzip file the unzip path is consider as installation directory and the valie of path should be keep do not override just concat the new variable
	download: https://maven.apache.org/download.cgi
	variable name: path
	value: <instalation directory>\apache-maven-3.8.5\bin (e.g. : ~;C:\Program Files\apache-maven-3.8.5\bin)

2. running the test suit 
	the scripts added to run the test suite is available for linux and windows version both scripts just need to be executed
	windows script : runTestSuite.bat  the default browser is configured as firefox
	linux script : runTestSuite.sh the default browser is configured as firefox
	
	also there is tree different script for the tree browser supported to this test suite

3. reports
	the reports out put is on a directory Results and HTML report is named index.html, the XML report name is testng-results.xml