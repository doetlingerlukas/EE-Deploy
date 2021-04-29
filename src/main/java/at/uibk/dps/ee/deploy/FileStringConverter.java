package at.uibk.dps.ee.deploy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import at.uibk.dps.ee.io.afcl.AfclReader;
import at.uibk.dps.ee.io.resources.ResourceGraphProviderFile;
import at.uibk.dps.ee.io.spec.SpecificationProviderFile;
import at.uibk.dps.ee.model.graph.EnactmentGraphProvider;
import at.uibk.dps.ee.model.graph.ResourceGraphProvider;
import net.sf.opendse.io.SpecificationWriter;
import net.sf.opendse.model.Specification;

/**
 * The {@link FileStringConverter} is used to convert files into the strings
 * expected by the Apollo server.
 * 
 * @author Fedor Smirnov
 */
public final class FileStringConverter {

  /**
   * No constructor.
   */
  private FileStringConverter() {}

  /**
   * Create the specification from the provided files and return it as a string.
   * 
   * @param filePathAfcl the path to the afcl file describing the application
   * @param filePathTypeMappings the path to the type mappings file
   * @return the specification string
   */
  public static String readSpecString(String filePathAfcl, String filePathTypeMappings) {
    EnactmentGraphProvider eGraphProv = new AfclReader(filePathAfcl);
    ResourceGraphProvider rGraphProv = new ResourceGraphProviderFile(filePathTypeMappings);
    SpecificationProviderFile specProv =
        new SpecificationProviderFile(eGraphProv, rGraphProv, filePathTypeMappings);

    Specification spec = specProv.getSpecification();
    SpecificationWriter writer = new SpecificationWriter();
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    writer.write(spec, stream);
    return stream.toString(StandardCharsets.UTF_8);
  }

  /**
   * Read the config file at the specified location and return it as a string.
   * 
   * @param configPath the file path of the config file
   * @return the string read from the file
   */
  public static String readModuleConfiguration(String configPath) {
    return readFile(configPath);
  }

  /**
   * Read the input file at the specified location and return it as a string.
   * 
   * @param inputPath the file path of the input file
   * @return the string read from the file
   */
  public static String readInputFile(String inputPath) {
    return readFile(inputPath);
  }

 

  /**
   * Read the config file at the specified location and return it as a string.
   * 
   * @param configPath the file path of the config file
   * @return the string read from the file
   */
  protected static String readFile(String filePath) {
    try {
      return Files.readString(Paths.get(filePath));
    } catch (IOException ioExc) {
      throw new IllegalArgumentException("IoException when reading the file " + filePath, ioExc);
    }
  }
}
