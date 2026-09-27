# Dataset Generator

Dataset Generator is a Java desktop application for creating simulated process datasets. The project was authored by Pat Dixon, Alia Rezvi, Mohammed Almakki, Grace Mower, Zaid Taiyab, Deepak Dalai, and DPAS Inc.

## Requirements

- Java Development Kit (JDK) 17
- Apache Maven 3.9 or newer

## Quick start

From the repository root:

```bash
cd generator/main
mvn clean package
java -Xmx6144m -jar target/dataset-generator.jar
```

Windows users can instead run:

```powershell
cd generator/main
.\run.bat
```

macOS and Linux users can run:

```bash
cd generator/main
./run.sh
```

The Maven build works from the command line and can be imported by VS Code, Eclipse, or IntelliJ IDEA.

See [Building and Running](documents/BUILDING_AND_RUNNING.md) for complete operating-system and IDE instructions.

## Project structure

- `generator/main/src` — Java source code
- `generator/main/config` — sample input configurations
- `generator/main/data` — generated datasets; ignored by Git
- `generator/main/libraries` — legacy dependency copies and license notices
- `documents` — application, variable, and calculation documentation

The Maven build creates the executable application at:

```text
generator/main/target/dataset-generator.jar
```

Older versioned JAR files remain in the repository for historical reference. New source builds should use the JAR produced in `target/`.

## Memory configuration

The launch scripts use a 6 GB maximum Java heap. To select a different limit, run the JAR directly:

```bash
java -Xmx8192m -jar target/dataset-generator.jar
```

Common values include:

| Option | Maximum heap |
| --- | ---: |
| `-Xmx4096m` | 4 GB |
| `-Xmx6144m` | 6 GB |
| `-Xmx8192m` | 8 GB |
| `-Xmx12288m` | 12 GB |
| `-Xmx16384m` | 16 GB |

Do not assign more memory than the computer can safely provide.
