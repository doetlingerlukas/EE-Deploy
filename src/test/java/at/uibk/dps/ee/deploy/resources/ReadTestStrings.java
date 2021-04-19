package at.uibk.dps.ee.deploy.resources;

/**
 * Class with the strings used to test the implementation run interface. 
 * 
 * @author Fedor Smirnov
 */
public final class ReadTestStrings {

  /**
   * No constructor
   */
  private ReadTestStrings() {
  }
  
  
  public static final String configString = "<configuration>\n" + 
      "  <module class=\"at.uibk.dps.ee.control.modules.EnactmentAgentModule\">\n" + 
      "    <property name=\"pauseOnStart\">false</property>\n" + 
      "  </module>\n" + 
      "  <module class=\"at.uibk.dps.ee.enactables.modules.FunctionsModule\">\n" + 
      "    <property name=\"logTime\">true</property>\n" + 
      "  </module>\n" + 
      "  <module class=\"at.uibk.dps.ee.io.modules.InputReaderFileModule\">\n" + 
      "    <property name=\"filePath\">./inputData/sixAtomic.json</property>\n" + 
      "  </module>\n" + 
      "  <module class=\"at.uibk.dps.ee.io.modules.OutputPrinterModule\"/>\n" + 
      "  <module class=\"at.uibk.dps.ee.io.modules.SpecificationInputModule\">\n" + 
      "    <property name=\"filePathAfcl\">./demoWfs/sixAtomics.yaml</property>\n" + 
      "    <property name=\"filePathMappingFile\">./typeMappings/sixAtomics.json</property>\n" + 
      "  </module>\n" + 
      "  <module class=\"at.uibk.dps.ee.visualization.modules.EnactmentViewerModule\">\n" + 
      "    <property name=\"closeOnTerminate\">false</property>\n" + 
      "    <property name=\"updatePeriodMs\">100</property>\n" + 
      "  </module>\n" + 
      "  <module class=\"at.uibk.dps.sc.core.modules.SchedulerModule\">\n" + 
      "    <property name=\"schedulingMode\">SingleOption</property>\n" + 
      "    <property name=\"mappingsToPick\">1</property>\n" + 
      "  </module>\n" + 
      "</configuration>\n";
  
  public static final String inputString = "{\n" + 
      "  \"input1\" : 3,\n" + 
      "  \"input2\" : 2,\n" + 
      "  \"input3\" : 7,\n" + 
      "  \"input4\" : 11,\n" + 
      "  \"input5\" : -8,\n" + 
      "  \"input6\" : 1,\n" + 
      "  \"wait1\":0,\n" + 
      "  \"wait2\":0\n" + 
      "}";
  
  public static final String specString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
      "<specification xsi:schemaLocation=\"http://opendse.sourceforge.net http://opendse.sourceforge.net/schema.xsd\" xmlns=\"http://opendse.sourceforge.net\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" + 
      "  <architecture>\n" + 
      "    <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "  </architecture>\n" + 
      "  <application>\n" + 
      "    <task id=\"addition_Demo2\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"TypeID\" type=\"STRING\">Addition</attribute>\n" + 
      "        <attribute name=\"UsageType\" type=\"STRING\">User</attribute>\n" + 
      "      </attributes>\n" + 
      "    </task>\n" + 
      "    <task id=\"addition_Demo3\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"TypeID\" type=\"STRING\">Addition</attribute>\n" + 
      "        <attribute name=\"UsageType\" type=\"STRING\">User</attribute>\n" + 
      "      </attributes>\n" + 
      "    </task>\n" + 
      "    <task id=\"addition_Demo4\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"TypeID\" type=\"STRING\">Addition</attribute>\n" + 
      "        <attribute name=\"UsageType\" type=\"STRING\">User</attribute>\n" + 
      "      </attributes>\n" + 
      "    </task>\n" + 
      "    <task id=\"addition_Demo5\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"TypeID\" type=\"STRING\">Addition</attribute>\n" + 
      "        <attribute name=\"UsageType\" type=\"STRING\">User</attribute>\n" + 
      "      </attributes>\n" + 
      "    </task>\n" + 
      "    <task id=\"addition_Demo1\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"TypeID\" type=\"STRING\">Addition</attribute>\n" + 
      "        <attribute name=\"UsageType\" type=\"STRING\">User</attribute>\n" + 
      "      </attributes>\n" + 
      "    </task>\n" + 
      "    <communication id=\"addition_Demo1/sum\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"addition_Demo4/sum\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"sixAtomics/input5\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">input5</attribute>\n" + 
      "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"addition_Demo5/sum\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">result</attribute>\n" + 
      "        <attribute name=\"Leaf\" type=\"BOOL\">true</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"sixAtomics/input6\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">input6</attribute>\n" + 
      "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"sixAtomics/input1\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">input1</attribute>\n" + 
      "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"sixAtomics/input2\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">input2</attribute>\n" + 
      "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"sixAtomics/input3\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">input3</attribute>\n" + 
      "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"addition_Demo3/sum\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"sixAtomics/input4\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">input4</attribute>\n" + 
      "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"addition_Demo2/sum\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"sixAtomics/wait2\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">wait2</attribute>\n" + 
      "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <communication id=\"sixAtomics/wait1\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"DataType\" type=\"STRING\">Number</attribute>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">wait1</attribute>\n" + 
      "        <attribute name=\"Root\" type=\"BOOL\">true</attribute>\n" + 
      "      </attributes>\n" + 
      "    </communication>\n" + 
      "    <dependency id=\"sixAtomics/input6--addition_Demo3\" source=\"sixAtomics/input6\" destination=\"addition_Demo3\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">secondSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"addition_Demo3/sum--addition_Demo5\" source=\"addition_Demo3/sum\" destination=\"addition_Demo5\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">firstSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"addition_Demo5--addition_Demo5/sum\" source=\"addition_Demo5\" destination=\"addition_Demo5/sum\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">sum</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/wait1--addition_Demo5\" source=\"sixAtomics/wait1\" destination=\"addition_Demo5\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">waitTimeIn</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/input2--addition_Demo1\" source=\"sixAtomics/input2\" destination=\"addition_Demo1\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">secondSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"addition_Demo4/sum--addition_Demo5\" source=\"addition_Demo4/sum\" destination=\"addition_Demo5\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">secondSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/input1--addition_Demo1\" source=\"sixAtomics/input1\" destination=\"addition_Demo1\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">firstSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"addition_Demo4--addition_Demo4/sum\" source=\"addition_Demo4\" destination=\"addition_Demo4/sum\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">sum</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/wait2--addition_Demo3\" source=\"sixAtomics/wait2\" destination=\"addition_Demo3\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">waitTimeIn</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"addition_Demo1--addition_Demo1/sum\" source=\"addition_Demo1\" destination=\"addition_Demo1/sum\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">sum</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/wait2--addition_Demo4\" source=\"sixAtomics/wait2\" destination=\"addition_Demo4\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">waitTimeIn</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/input4--addition_Demo2\" source=\"sixAtomics/input4\" destination=\"addition_Demo2\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">secondSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"addition_Demo2--addition_Demo2/sum\" source=\"addition_Demo2\" destination=\"addition_Demo2/sum\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">sum</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"addition_Demo1/sum--addition_Demo4\" source=\"addition_Demo1/sum\" destination=\"addition_Demo4\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">firstSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"addition_Demo2/sum--addition_Demo4\" source=\"addition_Demo2/sum\" destination=\"addition_Demo4\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">secondSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/input5--addition_Demo3\" source=\"sixAtomics/input5\" destination=\"addition_Demo3\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">firstSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/wait1--addition_Demo1\" source=\"sixAtomics/wait1\" destination=\"addition_Demo1\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">waitTimeIn</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/input3--addition_Demo2\" source=\"sixAtomics/input3\" destination=\"addition_Demo2\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">firstSummand</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"sixAtomics/wait1--addition_Demo2\" source=\"sixAtomics/wait1\" destination=\"addition_Demo2\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">waitTimeIn</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <dependency id=\"addition_Demo3--addition_Demo3/sum\" source=\"addition_Demo3\" destination=\"addition_Demo3/sum\" orientation=\"DIRECTED\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"JsonKey\" type=\"STRING\">sum</attribute>\n" + 
      "        <attribute name=\"Type\" type=\"STRING\">Data</attribute>\n" + 
      "      </attributes>\n" + 
      "    </dependency>\n" + 
      "    <functions>\n" + 
      "      <function anchor=\"addition_Demo1/sum\">\n" + 
      "        <attributes>\n" + 
      "          <attribute name=\"ID\" type=\"STRING\">func0</attribute>\n" + 
      "        </attributes>\n" + 
      "      </function>\n" + 
      "    </functions>\n" + 
      "  </application>\n" + 
      "  <mappings>\n" + 
      "    <mapping id=\"addition_Demo1--Enactment Engine (Local Machine)\" source=\"addition_Demo1\" target=\"Enactment Engine (Local Machine)\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"EnactmentMode\" type=\"STRING\">Local</attribute>\n" + 
      "        <attribute name=\"ImplementationId\" type=\"STRING\">native</attribute>\n" + 
      "      </attributes>\n" + 
      "    </mapping>\n" + 
      "    <mapping id=\"addition_Demo3--Enactment Engine (Local Machine)\" source=\"addition_Demo3\" target=\"Enactment Engine (Local Machine)\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"EnactmentMode\" type=\"STRING\">Local</attribute>\n" + 
      "        <attribute name=\"ImplementationId\" type=\"STRING\">native</attribute>\n" + 
      "      </attributes>\n" + 
      "    </mapping>\n" + 
      "    <mapping id=\"addition_Demo4--Enactment Engine (Local Machine)\" source=\"addition_Demo4\" target=\"Enactment Engine (Local Machine)\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"EnactmentMode\" type=\"STRING\">Local</attribute>\n" + 
      "        <attribute name=\"ImplementationId\" type=\"STRING\">native</attribute>\n" + 
      "      </attributes>\n" + 
      "    </mapping>\n" + 
      "    <mapping id=\"addition_Demo2--Enactment Engine (Local Machine)\" source=\"addition_Demo2\" target=\"Enactment Engine (Local Machine)\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"EnactmentMode\" type=\"STRING\">Local</attribute>\n" + 
      "        <attribute name=\"ImplementationId\" type=\"STRING\">native</attribute>\n" + 
      "      </attributes>\n" + 
      "    </mapping>\n" + 
      "    <mapping id=\"addition_Demo5--Enactment Engine (Local Machine)\" source=\"addition_Demo5\" target=\"Enactment Engine (Local Machine)\">\n" + 
      "      <attributes>\n" + 
      "        <attribute name=\"EnactmentMode\" type=\"STRING\">Local</attribute>\n" + 
      "        <attribute name=\"ImplementationId\" type=\"STRING\">native</attribute>\n" + 
      "      </attributes>\n" + 
      "    </mapping>\n" + 
      "  </mappings>\n" + 
      "  <routings>\n" + 
      "    <routing source=\"addition_Demo1/sum\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"addition_Demo4/sum\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"sixAtomics/input5\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"addition_Demo5/sum\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"sixAtomics/input6\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"sixAtomics/input1\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"sixAtomics/input2\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"sixAtomics/input3\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"addition_Demo3/sum\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"sixAtomics/input4\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"addition_Demo2/sum\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"sixAtomics/wait2\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "    <routing source=\"sixAtomics/wait1\">\n" + 
      "      <resource id=\"Enactment Engine (Local Machine)\"/>\n" + 
      "    </routing>\n" + 
      "  </routings>\n" + 
      "</specification>";
}
