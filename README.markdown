# ENGLISH - Liferay 6.2 modified with a fix to support JSON Web Services and Java 8

This version fixes the following bugs:

[https://issues.liferay.com/browse/LPS-59203]()

[https://issues.liferay.com/browse/LPS-52076]()

Those errors are solved in the Liferay EE 6.2 and 7.0 versions, so I decided to correct the CE edition and recompile Liferay 6.2.

To compile Liferay I created a Vagrant VM because Liferay can be compiled only with JDK 7:


```sh
apt-get update && sudo apt-get upgrade
apt-get software-properties-common
apt-get install software-properties-common
apt-get autoremove
add-apt-repository ppa:webupd8team/java
apt-get update
apt-get install oracle-java7-installer
update-java-alternatives -s java-7-oracle
apt-get install ant
```

Follow the instructions at the following link (do not care about the Maven repo section):

[building-maven-artifacts-from-source](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/building-maven-artifacts-from-source)

Source code is adapted to use JODD 3.6.4-SNAPSHOT instead of the previous version not compatible with Java 8.

**to rebuild without clean you can use the following:**

```sh
ant -f build-dist.xml build-dist-tomcat
```

-----

# ITALIANO - Liferay 6.2 Modificato con fix per supporto JSON WEB SERVICES e Java8

A causa dei problemi descritti di seguito:

[https://issues.liferay.com/browse/LPS-59203]()

[https://issues.liferay.com/browse/LPS-52076]()

risolti solamente nella versione EE di Liferay 6.2 e nella versione 7.0, si è dovuto procedere alla correzione del codice sorgente di Liferay 6.2 CE e ricompilazione dello stesso. 

Questo repository contiene i sorgenti aggiornati utilizzati da CHeMaPS e le istruzioni per la ricompilazione.

Per compilare utilizzare JDK 7 oppure eseguire la VM Vagrant e seguire le seguenti istruzioni:

```sh
apt-get update && sudo apt-get upgrade
apt-get software-properties-common
apt-get install software-properties-common
apt-get autoremove
add-apt-repository ppa:webupd8team/java
apt-get update
apt-get install oracle-java7-installer
update-java-alternatives -s java-7-oracle
apt-get install ant
```

Seguire la guida al seguente link evitando la sezione relativa al repo Maven, non utile ai nostri scopi:

[building-maven-artifacts-from-source](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/building-maven-artifacts-from-source)

Il sorgente è stato adattato per utilizzare la libreria JODD 3.6.4-SNAPSHOT invece della precedente non compatibile con Java 8.

**Per ricreare il tomcat senza rifare il clean, utilizzare il seguente comando:** 

```sh
ant -f build-dist.xml build-dist-tomcat
```
