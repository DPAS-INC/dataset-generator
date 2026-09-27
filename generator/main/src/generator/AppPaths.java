package generator;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Resolves application directories independently of an IDE's working directory.
 */
final class AppPaths {

   private static final String HOME_PROPERTY = "dataset.generator.home";
   private static final Path BASE_DIRECTORY = locateBaseDirectory();

   private AppPaths() {
   }

   static File configDirectory() {
      return BASE_DIRECTORY.resolve("config").toFile();
   }

   static File dataDirectory() {
      return BASE_DIRECTORY.resolve("data").toFile();
   }

   static File resolveAgainstBase(String pathText) {
      Path path = Paths.get(pathText);
      if (!path.isAbsolute()) {
         path = BASE_DIRECTORY.resolve(path);
      }
      return path.normalize().toFile();
   }

   private static Path locateBaseDirectory() {
      String configuredHome = System.getProperty(HOME_PROPERTY);
      if (configuredHome != null && !configuredHome.isBlank()) {
         return Paths.get(configuredHome).toAbsolutePath().normalize();
      }

      Path workingDirectory = Paths.get("").toAbsolutePath().normalize();
      Set<Path> candidates = new LinkedHashSet<>();
      candidates.add(workingDirectory);
      candidates.add(workingDirectory.resolve("generator").resolve("main"));

      Path codeLocation = findCodeLocation();
      for (Path candidate = codeLocation; candidate != null; candidate = candidate.getParent()) {
         candidates.add(candidate);
      }

      for (Path candidate : candidates) {
         if (Files.isDirectory(candidate.resolve("config"))) {
            return candidate;
         }
      }

      return workingDirectory;
   }

   private static Path findCodeLocation() {
      try {
         CodeSource source = Main.class.getProtectionDomain().getCodeSource();
         if (source == null || source.getLocation() == null) {
            return null;
         }

         Path location = Paths.get(source.getLocation().toURI()).toAbsolutePath().normalize();
         return Files.isRegularFile(location) ? location.getParent() : location;
      } catch (URISyntaxException | SecurityException e) {
         return null;
      }
   }
}
