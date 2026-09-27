# Building and Running Dataset Generator

This guide describes one consistent Maven workflow for Windows, macOS, Linux, VS Code, Eclipse, IntelliJ IDEA, and the command line.

## Requirements

Install the following tools:

1. Java Development Kit (JDK) 17
2. Apache Maven 3.9 or newer
3. Git, if cloning the repository from GitHub

Maven installation instructions are available in the [official Maven installation guide](https://maven.apache.org/install). Maven requires a JDK and must be available on the system `PATH`.

Confirm the installations in a new terminal:

```bash
java --version
javac --version
mvn --version
```

Both Java commands should report version 17. Maven should report that it is using Java 17.

## Project directory

The Maven project is located at:

```text
generator/main
```

All build commands in this guide must be run from that directory unless the command explicitly supplies the POM location.

From the repository root:

```bash
cd generator/main
```

## Build from the command line

Run a clean build:

```bash
mvn clean package
```

Maven compiles the Java 17 sources, downloads the declared dependencies, and creates one executable JAR containing the application and its runtime dependencies:

```text
target/dataset-generator.jar
```

Run the application:

```bash
java -Xmx6144m -jar target/dataset-generator.jar
```

The first Maven build may take longer because Maven downloads plugins and dependencies into the user's local Maven cache.

## Launch scripts

The launch scripts perform a clean Maven build before starting the application. This ensures that recently edited source files are included in the JAR.

### Windows

Open PowerShell or Command Prompt in `generator/main`, then run:

```powershell
.\run.bat
```

### macOS and Linux

Open a terminal in `generator/main`, then run:

```bash
./run.sh
```

If the checkout does not preserve executable permission, run this once:

```bash
chmod +x run.sh
./run.sh
```

## Visual Studio Code

1. Install JDK 17 and Maven.
2. Install Microsoft's [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack). It includes Maven support.
3. Open the `generator/main` directory in VS Code.
4. Allow the Java extensions to import `pom.xml` and finish downloading dependencies.
5. Open `src/generator/Main.java`.
6. Select **Run Java** above the `main` method, or run `mvn clean package` in the integrated terminal.

VS Code automatically detects Maven projects containing a `pom.xml`. See the official [Java build tools in VS Code](https://code.visualstudio.com/docs/java/java-build) documentation for Maven Explorer details.

## Eclipse

1. Install JDK 17 and Maven.
2. Select **File > Import**.
3. Select **Maven > Existing Maven Projects**.
4. Choose the repository's `generator/main` directory.
5. Confirm that Eclipse detects `pom.xml`, then finish the import.
6. Wait for dependency resolution to complete.
7. Open `src/generator/Main.java`.
8. Select **Run As > Java Application**.

Maven's `pom.xml` is the authoritative build configuration. The existing Eclipse `.project` and `.classpath` files are retained for compatibility but should not replace the Maven dependency configuration.

## IntelliJ IDEA

1. Install JDK 17 and Maven.
2. Select **Open** from the welcome screen or **File > Open**.
3. Select `generator/main/pom.xml`.
4. Choose **Open as Project** if prompted.
5. Set the project SDK to JDK 17.
6. Wait for Maven synchronization to finish.
7. Open `src/generator/Main.java` and run `generator.Main`.

JetBrains also documents this process in its [Maven project import guide](https://www.jetbrains.com/guide/java/tutorials/working-with-maven/importing-a-project/).

## Configuration and output locations

The application resolves its home directory consistently even if an IDE supplies a different working directory.

Default locations are:

```text
generator/main/config   Sample configuration folders
generator/main/data     Generated dataset files
```

The configuration folder chooser only accepts folders located inside `generator/main/config`.

Relative paths entered when downloading configurations are resolved from `generator/main`. Absolute paths remain absolute.

For a nonstandard installation, explicitly set the application home before `-jar`:

### Windows PowerShell

```powershell
java '-Ddataset.generator.home=C:\path\to\generator\main' -Xmx6144m -jar target\dataset-generator.jar
```

### macOS and Linux

```bash
java -Ddataset.generator.home=/path/to/generator/main -Xmx6144m -jar target/dataset-generator.jar
```

The selected directory should contain the `config` folder. The application creates `data` when generation starts if it does not already exist.

## Memory settings

The default launch scripts set the maximum Java heap to 6 GB:

```text
-Xmx6144m
```

For a smaller or larger limit, launch the JAR directly and change the value. For example, an 8 GB limit is:

```bash
java -Xmx8192m -jar target/dataset-generator.jar
```

Do not configure a heap larger than the available system memory.

## Troubleshooting

### `java`, `javac`, or `mvn` is not recognized

Close and reopen the terminal after installation. Confirm that the JDK and Maven `bin` directories are on `PATH`, and check `JAVA_HOME` if Maven cannot locate Java.

### Maven uses the wrong Java version

Run:

```bash
mvn --version
```

The displayed Java version must be 17. Correct `JAVA_HOME` or the IDE's Maven runner JDK if necessary.

### Dependencies cannot be downloaded

The first build requires access to Maven Central. Check the network, proxy, firewall, and Maven `settings.xml`, then retry:

```bash
mvn clean package
```

### The GUI does not open on Linux

The application uses Java Swing and requires a graphical desktop session. It cannot display its interface in a headless terminal or server session.

### Build output appears stale

Run a clean package rather than reusing an older JAR:

```bash
mvn clean package
```

### Configuration folder is rejected

Place or copy the configuration folder under `generator/main/config`, then select that folder again.

## Files that should not be committed

The following paths contain generated output and are ignored by Git:

```text
build/
generator/main/bin/
generator/main/data/
generator/main/target/
```

Source code, `pom.xml`, scripts, configuration examples, and documentation should remain tracked.
