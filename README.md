REST Web Services with JavaEE 7 Using

*   Java 8
*   Jersey
*   JTA/JPA (Eclipselink)
*   EJB3.1
*   CDI
*   Jackson (for JSON Processing)
*   Project Lombok

Well tested to run on Glassfish/Payara Server

The project builds with Gradle, you can run on the command line: _gradlew build_ and it will generate a war in /build/libs.

If you want to deploy it directly to your web server (glassfish/payara) you need to

*   Create a file on your application root named _gradle.properties_
*   Create a property named 'serverHome' where your server is installed. for example:

    **_serverHome = C:\\\\developer\\\\payara41_**

Now you can use a task named **deploy** _(gradlew deploy)_ to deploy your application, just make sure your server is up!