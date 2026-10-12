# Program Warning Investigation

This report documents the investigation and resolution of program warnings for the **Resolve Program Warnings** user story and tasks #34–#37.

## Test environment

- Date: October 1, 2026
- Branch: `Resolve-Warnings`
- Baseline commit: `d13c662`
- Operating system: Windows 11
- Java: Eclipse Temurin OpenJDK 17.0.20.1
- Maven: 3.9.12
- Maven Compiler Plugin: 3.16.0
- Maven Shade Plugin: 3.6.2
- IDE verification: Visual Studio Code

## Task #34: Identify existing warnings

The updated team code was built from a clean checkout state with:

```bash
mvn clean package
```

Java compilation completed without warnings. The Maven Shade Plugin reported two groups of overlapping resources while creating the executable JAR:

1. The application and its runtime dependency JARs each contained `META-INF/MANIFEST.MF`.
2. `checker-qual-3.12.0.jar` and `commons-csv-1.10.0.jar` both contained `META-INF/LICENSE.txt`.

The application still built and launched, but the warnings showed that Maven was selecting only one copy of each overlapping resource.

A stricter Java compiler check was also performed:

```bash
mvn clean compile "-Dmaven.compiler.showWarnings=true" "-Dmaven.compiler.compilerArgs=-Xlint:all"
```

The strict compilation completed successfully with no Java source warnings.

## Task #35: Investigate warning causes

### Duplicate manifests

Each dependency JAR normally contains its own manifest. When the dependencies are combined into one executable JAR, only one file can occupy `META-INF/MANIFEST.MF`.

This overlap did not indicate a Java source or dataset-generation defect. The project already used `ManifestResourceTransformer` to create the final executable manifest with `generator.Main` as the entry point, so the dependency manifests were unnecessary in the shaded artifact.

### Duplicate license files

The two overlapping license resources were not identical:

- Checker Framework Qualifiers used the MIT License.
- Apache Commons CSV used the Apache License 2.0.

Before the fix, the final executable JAR retained the Checker Framework license but did not retain the Commons CSV license at the same path. Although this did not affect application execution, it was a packaging and license-preservation concern.

## Task #36: Resolve applicable warnings

The Maven Shade configuration in `generator/main/pom.xml` was updated to:

1. Exclude dependency copies of `META-INF/MANIFEST.MF`.
2. Continue using `ManifestResourceTransformer` to create one executable application manifest.
3. Use `AppendingTransformer` to combine the distinct `META-INF/LICENSE.txt` resources instead of discarding one.

No Java source files, dependency versions, launch scripts, dataset calculations, application paths, or operating-system-specific settings were changed. The portability behavior implemented for cross-IDE and cross-platform operation was therefore preserved.

## Task #37: Regression testing

### Build and compiler checks

- `mvn clean package` completed with `BUILD SUCCESS` and no Maven warnings.
- Strict `-Xlint:all` compilation completed with no Java warnings.
- The executable artifact was created at `generator/main/target/dataset-generator.jar`.
- The final JAR contained exactly one `META-INF/MANIFEST.MF`.
- The final manifest contained `Main-Class: generator.Main`.
- The merged `META-INF/LICENSE.txt` contained both the MIT and Apache 2.0 license text.
- `META-INF/NOTICE.txt` remained present.

### Application and dataset-generation checks

- The packaged JAR launched successfully without startup warnings or exceptions.
- The existing `config/NoState` sample was used for the regression test.
- Dataset generation completed successfully in 32 seconds.
- The generated CSV was 99,645,787 bytes and contained 721,502 parseable records.
- The generated CSV contained 29 header fields.
- All records were valid CSV records, with zero malformed-line parsing errors.
- Ordinary records contained 28 fields, while lab-sampling records contained the optional trailing `Lab_Tensile` value as the 29th field. This is consistent with the application's sparse lab-output behavior.
- The application closed normally with exit code 0.

### Visual Studio Code check

- The project was built successfully from the VS Code integrated terminal.
- The Maven build displayed no `[WARNING]` messages.
- The VS Code Problems panel reported: `No problems have been detected in the workspace.`

Messages such as `skip non existing resourceDirectory`, `No sources to compile`, and `No tests to run` are Maven informational messages, not program warnings.

## Final status

All warnings identified in the current team code were investigated and resolved. No unresolved compiler, packaging, startup, runtime, or VS Code warnings remain from this investigation.

The repository does not currently contain automated test sources, so regression coverage consisted of clean and strict builds, artifact inspection, application launch, NoState dataset generation, CSV structural validation, and VS Code verification.
