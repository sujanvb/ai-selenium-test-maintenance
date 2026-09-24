# Project Requirements

This project requires the following software for local/manual execution.

## Required Software

| Software | Version | Purpose |
|---|---|---|
| Java JDK | 21 | Compile and run the Selenium/TestNG automation |
| Apache Maven | 3.9.12 | Build the project and execute tests |
| Python | 3.14.7 | Run the local demo application using `http.server` |
| Google Chrome | Current supported version | Browser used by Selenium |

## Java

Install **JDK 21** and make sure `java` is available from the command line.

Verify:

```bash
java -version
```

The project is configured for Java 21 in `pom.xml`.

## Maven

Install **Apache Maven 3.9.12**.

Verify:

```bash
mvn -version
```

Maven downloads the Java dependencies defined in `pom.xml`, including:

- Selenium Java 4.35.0
- TestNG 7.11.0

No separate download of these Java libraries is required.

## Python

Install **Python 3.14.7**.

Verify:

```bash
python --version
```

Python is only used to host the sample web application locally:

```bash
python -m http.server 8000
```

No Python packages are required.

## Google Chrome

Install Google Chrome.

The Selenium project uses Selenium Manager to manage the browser driver.

## Jenkins Execution

If the project is executed through the included `Jenkinsfile`, Java, Maven, and Python do not need to be pre-installed globally on the Jenkins machine.

The Jenkins pipeline automatically downloads the configured versions into:

```text
<PROJECT_PATH>\tools\
```

The currently configured versions are:

```text
Java    21.0.10
Maven   3.9.12
Python  3.14.7
```

The Jenkins machine still needs to be capable of running the required Windows processes and have access to the internet for the initial tool downloads.

## Quick Verification

Before running the project manually, verify:

```bash
java -version
mvn -version
python --version
```

Then start the application and run:

```bash
mvn test
```
