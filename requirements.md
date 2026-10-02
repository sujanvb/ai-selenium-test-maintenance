# Project Requirements

> **Note:** These requirements are written for **Windows**.

This project requires the following software for local/manual execution.

## Required Software

| Software | Minimum | Tested With | Purpose |
|---|---|---|---|
| Java JDK | 21 | 21.0.10 | Compile and run the Selenium/TestNG automation |
| Apache Maven | 3.9+ | 3.9.12 | Build the project and execute tests |
| Python | 3.x (any recent 3.x) | 3.14.7 | Run the local demo application using `http.server` |
| Google Chrome | Current supported version | Current supported version | Browser used by Selenium |
| Git | Any recent version | — | Clone the repository and switch branches |

## Java

Install **JDK 21 or newer** and make sure `java` is available from the command line.

Verify:

```bash
java -version
```

The project is configured for Java 21 in `pom.xml`.

Make sure `JAVA_HOME` is set and points to the JDK install — Maven and the Jenkins pipeline both rely on it.

## Maven

Install **Apache Maven 3.9 or newer**.

Verify:

```bash
mvn -version
```

Maven downloads the Java dependencies defined in `pom.xml`, including:

- Selenium Java 4.35.0
- TestNG 7.11.0

No separate download of these Java libraries is required, but Maven needs internet access to **Maven Central** the first time it resolves dependencies.

## Python

Install **Python 3** (any recent 3.x release).

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

The Selenium project uses Selenium Manager to manage the browser driver. Selenium Manager needs internet access on first run to download the matching ChromeDriver.

## Git

Git is required to clone the repository and switch between the `master` and `v2-ai-migration` branches.

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

The Jenkins machine still needs:

- To be capable of running the required Windows processes (the pipeline uses `powershell` and `bat` steps).
- Internet access for the initial tool downloads, Maven Central, and Selenium Manager.
- **Google Chrome installed** — the pipeline does not install Chrome for you.

**Chrome display requirements depend on your Jenkins agent.** The current `DriverScript.java` does not pass a `--headless` option, so Chrome is launched in normal (headed) mode. This means the agent needs an interactive desktop session (or a virtual display) available to it. If your agent has no display, add `--headless=new` (and related flags, if needed) to the `ChromeOptions` in `DriverScript.java` before running on that agent.

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
