# dataset-generator

Welcome to the Dataset Generator open-source project authored by Pat Dixon, Alia Rezvi, Mohammed Almakki, Grace Mower, Zaid Taiyab, Deepak Dalai, (DPAS Inc), 2025.

Please read the 'Documentation.pdf' file for an introduction and instructions on how to run/modify the application.

The 'generator' directory holds the application and all external library licenses/notices can be found in the 'generator\main\libraries' directory.

The 'documents' directory holds relevant information about the current dataset generator's variables and calculations.

The 'NetBeans.zip' file is an older, modified version of the project which allows for GUI configuration using the NetBeans GUI editor. Details can be found in the 'Documentation.pdf' file.

---

## Memory Usage Configuration

The Dataset Generator can be run using different methods depending on the amount of memory required. By default, the application uses approximately **2–4 GB of memory**, but higher limits can be specified for larger datasets.

| Method | Memory Used |
|------|------------|
| Double-click `generator.jar` | Default (~2–4 GB) |
| `java -jar generator.jar` | Default (~2–4 GB) |
| `java -Xmx8192m -jar generator.jar` | 8 GB |
| `java -Xmx12288m -jar generator.jar` | 12 GB |
| `java -Xmx16384m -jar generator.jar` | 16 GB |

### Notes
- Use higher memory limits for large dataset generation.
- Ensure your system has enough available RAM before assigning higher values.
- The `-Xmx` flag sets the maximum JVM heap size.
