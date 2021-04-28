package at.uibk.dps.ee.deploy;

public class ImplementationExpTest extends ImplementationExperiment {

  protected static final String filePathConfig = "./src/test/resources/singleAtomicConfig.xml";
  protected static final String afclFilePath = "./src/test/resources/singleAtomic.yaml";
  protected static final String typeMappingsPath = "./src/test/resources/singleAtomic.json";
  protected static final String inputFilePath = "./src/test/resources/inputSingleAtomic.json";

  public ImplementationExpTest() {
    super(afclFilePath, typeMappingsPath, filePathConfig);
  }

  @Override
  protected void actualRun() {
    for (int i = 0; i < 10; i++) {
      implementWithInput(inputFilePath);
    }
  }

  public static void main(String[] args) {
    ImplementationExpTest test = new ImplementationExpTest();
    test.runExperiment();
  }



}
