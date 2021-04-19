package at.uibk.dps.ee.deploy;

import net.sf.opendse.io.SpecificationWriter;
import net.sf.opendse.model.Application;
import net.sf.opendse.model.Architecture;
import net.sf.opendse.model.Dependency;
import net.sf.opendse.model.Link;
import net.sf.opendse.model.Mapping;
import net.sf.opendse.model.Mappings;
import net.sf.opendse.model.Resource;
import net.sf.opendse.model.Specification;
import net.sf.opendse.model.Task;

public class ToyClass {

  public static void main(String[] args) {
    
    Application<Task, Dependency> appl = new Application<>();
    Task task = new Task("task");
    appl.addVertex(task);
    
    Architecture<Resource, Link> arch = new Architecture<>();
    Resource res = new Resource("res");
    arch.addVertex(res);
    
    Mappings<Task, Resource> mappings = new Mappings<>();
    Mapping<Task, Resource> m = new Mapping<Task, Resource>("m", task, res);
    mappings.add(m);
    
    Specification spec = new Specification(appl, arch, mappings);
    
    SpecificationWriter writer = new SpecificationWriter();
    
    writer.write(spec, "./testSpec.xml");
    
  }
  
}
