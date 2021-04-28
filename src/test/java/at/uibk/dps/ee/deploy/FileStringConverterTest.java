package at.uibk.dps.ee.deploy;

import static org.junit.Assert.*;
import org.junit.Test;

public class FileStringConverterTest {

  protected static final String filePathConfig = "./src/test/resources/singleAtomicConfig.xml";
  protected static final String afclFilePath = "./src/test/resources/singleAtomic.yaml";
  protected static final String typeMappingsPath = "./src/test/resources/singleAtomic.json";
  protected static final String inputFilePath = "./src/test/resources/inputSingleAtomic.json";
  
  @Test
  public void testReadInput() {
    String inputString = FileStringConverter.readInputFile(inputFilePath);
    assertEquals(expectedInput, inputString);
  }
  
  @Test
  public void testReadConfig() {
    String configString = FileStringConverter.readModuleConfiguration(filePathConfig);
    String actualExpected = FileStringConverter.formatString(expectedConfigString);
    assertEquals(actualExpected, configString);
  }

  @Test
  public void testSpecRead() {
    String specString = FileStringConverter.readSpecString(afclFilePath, typeMappingsPath);
    String expectedFormatted = FileStringConverter.formatString(expectedSpecString);
    assertEquals(expectedFormatted, specString);
  }

  protected static final String expectedInput = "{\"a\":3,\"b\":17,\"wait\":2000}";
  
  protected static final String expectedSpecString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
      + "<specification xsi:schemaLocation=\"http://opendse.sourceforge.net http://opendse.sourceforge.net/schema.xsd\" xmlns=\"http://opendse.sourceforge.net\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
      + "  <architecture>\n" + "    <resource id=\"Enactment Engine (Local Machine)\"/>\n"
      + "  </architecture>\n" + "  <application>\n" + "    <task id=\"addition_Demo\">\n"
      + "      <attributes>\n"
      + "        <attribute name=\"TypeID\" type=\"STRING\">Addition</attribute>\n"
      + "        <attribute name=\"UsageType\" type=\"STRING\">User</attribute>\n"
      + "      </attributes>\n" + "    </task>\n"
      + "    <communication id=\"single Atomic/wait\">\n" + "      <attributes>\n"
      + "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n"
      + "        <attribute name=\"JsonKey\" type=\"STRING\">wait</attribute>\n"
      + "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n"
      + "      </attributes>\n" + "    </communication>\n"
      + "    <communication id=\"addition_Demo/secondSummand\">\n" + "      <attributes>\n"
      + "        <attribute name=\"Content\" type=\"STRING\">10</attribute>\n"
      + "        <attribute name=\"DataAvailable\" type=\"BOOL\">true</attribute>\n"
      + "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n"
      + "        <attribute name=\"NodeType\" type=\"STRING\">Constant</attribute>\n"
      + "      </attributes>\n" + "    </communication>\n"
      + "    <communication id=\"addition_Demo/sum\">\n" + "      <attributes>\n"
      + "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n"
      + "        <attribute name=\"JsonKey\" type=\"STRING\">result</attribute>\n"
      + "        <attribute name=\"Leaf\" type=\"BOOL\">true</attribute>\n"
      + "      </attributes>\n" + "    </communication>\n"
      + "    <communication id=\"single Atomic/a\">\n" + "      <attributes>\n"
      + "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n"
      + "        <attribute name=\"JsonKey\" type=\"STRING\">a</attribute>\n"
      + "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n"
      + "      </attributes>\n" + "    </communication>\n"
      + "    <dependency id=\"single Atomic/wait--addition_Demo\" source=\"single Atomic/wait\" destination=\"addition_Demo\" orientation=\"DIRECTED\">\n"
      + "      <attributes>\n"
      + "        <attribute name=\"JsonKey\" type=\"STRING\">waitTimeIn</attribute>\n"
      + "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n"
      + "      </attributes>\n" + "    </dependency>\n"
      + "    <dependency id=\"addition_Demo/secondSummand--addition_Demo\" source=\"addition_Demo/secondSummand\" destination=\"addition_Demo\" orientation=\"DIRECTED\">\n"
      + "      <attributes>\n"
      + "        <attribute name=\"JsonKey\" type=\"STRING\">secondSummand</attribute>\n"
      + "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n"
      + "      </attributes>\n" + "    </dependency>\n"
      + "    <dependency id=\"addition_Demo--addition_Demo/sum\" source=\"addition_Demo\" destination=\"addition_Demo/sum\" orientation=\"DIRECTED\">\n"
      + "      <attributes>\n"
      + "        <attribute name=\"JsonKey\" type=\"STRING\">sum</attribute>\n"
      + "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n"
      + "      </attributes>\n" + "    </dependency>\n"
      + "    <dependency id=\"single Atomic/a--addition_Demo\" source=\"single Atomic/a\" destination=\"addition_Demo\" orientation=\"DIRECTED\">\n"
      + "      <attributes>\n"
      + "        <attribute name=\"JsonKey\" type=\"STRING\">firstSummand</attribute>\n"
      + "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n"
      + "      </attributes>\n" + "    </dependency>\n" + "    <functions>\n"
      + "      <function anchor=\"single Atomic/wait\">\n" + "        <attributes>\n"
      + "          <attribute name=\"ID\" type=\"STRING\">func0</attribute>\n"
      + "        </attributes>\n" + "      </function>\n" + "    </functions>\n"
      + "  </application>\n" + "  <mappings>\n"
      + "    <mapping id=\"addition_Demo--Enactment Engine (Local Machine)\" source=\"addition_Demo\" target=\"Enactment Engine (Local Machine)\">\n"
      + "      <attributes>\n"
      + "        <attribute name=\"EnactmentMode\" type=\"STRING\">Local</attribute>\n"
      + "        <attribute name=\"ImplementationId\" type=\"STRING\">native</attribute>\n"
      + "      </attributes>\n" + "    </mapping>\n" + "  </mappings>\n" + "  <routings>\n"
      + "    <routing source=\"single Atomic/wait\">\n"
      + "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + "    </routing>\n"
      + "    <routing source=\"addition_Demo/secondSummand\">\n"
      + "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + "    </routing>\n"
      + "    <routing source=\"addition_Demo/sum\">\n"
      + "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + "    </routing>\n"
      + "    <routing source=\"single Atomic/a\">\n"
      + "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + "    </routing>\n"
      + "  </routings>\n" + "</specification>";

  protected static final String expectedConfigString = "<configuration>\n"
      + "  <module class=\"at.uibk.dps.ee.control.modules.EnactmentAgentModule\">\n"
      + "    <property name=\"pauseOnStart\">false</property>\n" + "  </module>\n"
      + "  <module class=\"at.uibk.dps.ee.io.modules.InputReaderFileModule\">\n"
      + "    <property name=\"filePath\">./inputData/inputSingleAtomic.json</property>\n"
      + "  </module>\n" + "  <module class=\"at.uibk.dps.ee.io.modules.LoggingModule\">\n"
      + "    <property name=\"pathToConfigFile\">./logging/config/logback.xml</property>\n"
      + "  </module>\n" + "  <module class=\"at.uibk.dps.ee.io.modules.OutputPrinterModule\"/>\n"
      + "  <module class=\"at.uibk.dps.ee.io.modules.SpecificationInputModule\">\n"
      + "    <property name=\"filePathAfcl\">./demoWfs/singleAtomic.yaml</property>\n"
      + "    <property name=\"filePathMappingFile\">./typeMappings/singleAtomic.json</property>\n"
      + "  </module>\n"
      + "  <module class=\"at.uibk.dps.ee.visualization.modules.EnactmentViewerModule\">\n"
      + "    <property name=\"closeOnTerminate\">false</property>\n"
      + "    <property name=\"updatePeriodMs\">100</property>\n" + "  </module>\n"
      + "  <module class=\"at.uibk.dps.sc.core.modules.SchedulerModule\">\n"
      + "    <property name=\"schedulingMode\">SingleOption</property>\n"
      + "    <property name=\"mappingsToPick\">1</property>\n" + "  </module>\n"
      + "</configuration>";
}
