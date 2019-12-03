# panic_button


UPDATE FROM GIT
============================================================================
NOTE: to change to another branch:

	{develop}\projects\{project name}>git fetch --all
	{develop}\projects\{project name}>git checkout {branch_name}

If any change is published at the [github] remote repo, it has to be _pulled_ to the local GIT, to do so:

    {develop}\projects\fabric-r01f>git pull
	{develop}\projects\fabric-r01fAspect>git pull
    {develop}\projects\fabric-r01fBusinessServices>git pull
    {develop}\projects\fabric-r01fCOREServices>git pull
    {develop}\projects\fabric-r01fUI>git pull
    {develop}\projects\zuzenean-panic-button>git-pull

... now just compile

    {develop}\projects\fabric-r01f>mvn clean install -Dmaven.test.skip=true
	{develop}\projects\fabric-r01fAspect>mvn clean install -Dmaven.test.skip=true
    {develop}\projects\fabric-r01fBusinessServices> mvn clean install -Dmaven.test.skip=true
    {develop}\projects\fabric-r01fCOREServices> mvn clean install -Dmaven.test.skip=true
    {develop}\projects\fabric-r01fUI> mvn clean install -Dmaven.test.skip=true
    {develop}\projects\zuzenean-panic-button> mvn clean install -Dmaven.test.skip=true



[PRE] SETUP THE ENVIRONMENT
============================================================================
Follow the instructions at [Development environment install guide (no IDE)](https://github.com/opendata-euskadi/fabric-r01f/tree/master/docs/dev_env_install.md)


[A] - CLONE GITHUB REPOS
==================================================================================
In a NEW cmd window

	c:\>cd {develop}
	{develop}>pci-env.cmd
	{develop}>cd projects
	{develop}\projects>git clone https://github.com/opendata-euskadi/fabric-r01f.git
	{develop}\projects>git clone https://github.com/opendata-euskadi/fabric-r01fAspect.git
	{develop}\projects>git clone https://github.com/opendata-euskadi/fabric-r01fBusinessServices.git
	{develop}\projects>git clone https://github.com/opendata-euskadi/fabric-r01fCOREServices.git
	{develop}\projects>git clone https://github.com/opendata-euskadi/fabric-r01fUI.git
	{develop}\projects>git clone https://github.com/opendata-euskadi/zuzenean-panic-button.git

	After this, the contents of {develop}\projects should be:
		/.gitconfig
		/[fabric-r01f]
		/[fabric-r01fAspect]
		/[fabric-r01fBusinessServices]
		/[fabric-r01fCOREServices]
		/[fabric-r01fUI]
		/[zuzenean-panic-button]

[B] - COMPILE PROJECTS
==================================================================================
	c:\>cd {develop}\projects\fabric-r01f
	{develop}\projects\fabric-r01f>mvn clean install

	c:\>cd {develop}\projects\fabric-r01fAspect
	{develop}\projects\fabric-r01f>mvn clean install

	c:\>cd {develop}\projects\fabric-r01fBusinessServices
	{develop}\projects\fabric-r01fBusinessServices>mvn clean install -Dmaven.test.skip=true

	c:\>cd {develop}\projects\fabric-r01fCOREServices
	{develop}\projects\fabric-r01fCOREServices>mvn clean install -Dmaven.test.skip=true

	c:\>cd {develop}\projects\fabric-r01fUI
	{develop}\projects\fabric-r01fUI>mvn clean install -Dmaven.test.skip=true

	c:\>cd {develop}\projects\zuzenean-panic-button
	{develop}\projects\zuzenean-panic-button>mvn clean install -Dmaven.test.skip=true


[C] - DEPLOY TO TOMCAT
==================================================================================
The generated WAR artifact named [pb01PanicButtonUIWar.war] should be at:

	{develop}\projects\zuzenean-panic-button\pb01PanicButtonUI\pb01PanicButtonUIWar\target

... just:

a) copy it to `{develop}\app-server\apache-tomcat-9.0.22\webapps`

b) start tomcat

If the tomcat server is started, the app **will NOT deploy correctly** since [Tomcat] needs two additional libraries, the [DB driver] and the [AspectJ weaver] for run-time aspect weaving

**The DB driver**

1) download the **[platform independent] ZIP** version from https://dev.mysql.com/downloads/connector/j/

2) extract the **[mysql-connector-java-xxx.jar]** file to `{develop}\app-server\apache-tomcat-9.0.22\lib`

**The AspectJ weaver**

1) download the **aspectj-1.9.4.jar** from http://www.eclipse.org/downloads/download.php?file=/tools/aspectj/aspectj-1.9.4.jar

2) using winzip or 7z open the jar file and navigate to `files\lib\` where there should exists 3 files: `aspectjrt.jar`, `aspectjtools.jar` and `aspectjweaver.jar`

3) extract these jar files to  `{develop}\app-server\apache-tomcat-9.0.22\lib`

4) Modify the [tomcat] environment config command file `{develop}\app-server\tomcat9-env.cmd` to add the following line:

    set "JAVA_OPTS=%JAVA_OPTS% -javaagent:%CATALINA_HOME%/lib/aspectjweaver.jar -Daj.weaving.verbose=true


[D] - CONFIGURATION
==================================================================================

If the tomcat server is started, the app **will NOT deploy correctly** since some config is needed but the application

1) create a folder `{develop}\config\panic-button\default\x47b`

2) At `{develop}\app-server\apache-tomcat-9.0.22\bin` create a file named `setenv.bat` (**beware! it's .bat not .cmd**) with the following content

    set CLASSPATH=%DEVELOP_HOME%\config\panic-button

3) Create a file `{develop}\config\panic-button\default\x47b\x47b.notifier.properties.xml` with the following content:

```xml
<notifier>
	<!-- ============================================= -->
	<!-- EMAIL NOTIFIER                                -->
	<!-- ============================================= -->
	<email>
		<smtp>
			<host>__the SMTP server host__</host>
		</smtp>
		<aws>
			<!-- simple email service -->
			<ses>
				<accessKey>__access key__</accessKey>
				<accessSecret>__access secret__</accessSecret>
			</ses>
		</aws>
	</email>

	<!-- ============================================= -->
	<!-- SMS NOTIFIER                                -->
	<!-- ============================================= -->
	<sms>
		<!-- AWS SNS (simple notification service) -->
		<aws>
			<!-- simple email service -->
			<sns>
				<accessKey>__sns access key__</accessKey>
				<accessSecret>__sns access secret__</accessSecret>
			</sns>
		</aws>
	</sms>

	<!-- ============================================= -->
	<!-- VOICE NOTIFIER                                -->
	<!-- ============================================= -->
	<voice>
		<!-- Twilio notifier -->
		<twilio>
			<accountSID>__accountSID__</accountSID>
			<authToken>__auth token__</authToken>
			<voicePhoneNumber>__phone__</voicePhoneNumber>
		</twilio>
	</voice>
</notifier>
```



