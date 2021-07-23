package at.uibk.dps.ee.deploy;

import at.uibk.dps.ee.io.afcl.AfclReader;
import at.uibk.dps.ee.io.spec.SpecificationProviderFile;

public class bla {

  public static void main(String[] args) {

    String specString = FileStringConverter.readSpecString(
        "/home/fedor/Repositories/gitHub/EE-Demo/demoWfs/sixAtomics.yaml",
        "/home/fedor/Repositories/gitHub/EE-Demo/typeMappings/sixAtomics.json");

    System.out.println(specString);
    
  }

}
