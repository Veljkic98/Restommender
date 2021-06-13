package pro.restommender.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.stereotype.Repository;

import pro.restommender.model.MyRule;


@Repository
public class MyRuleRepository {

  public MyRule add(MyRule rule) throws IOException {
    String fileName = "../drools-kjar/src/main/resources/sbnz/" + rule.getName() + ".drl";
    File file = new File(fileName);
    FileWriter fileWriter = new FileWriter(file, true);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.print(rule.getContent());
    printWriter.close();
    fileWriter.close();
    updateKjar();
    return rule;
  }

  private void updateKjar() {
    InvocationRequest request = new DefaultInvocationRequest();
    request.setDebug(false);
    String pomPath = "../drools-kjar/pom.xml";
    File pomFile = new File(pomPath);
    request.setPomFile(pomFile);
    request.setGoals(Arrays.asList("clean", "install"));

    try {
      Invoker invoker = new DefaultInvoker();
      invoker.setMavenHome( new File("../drools-kjar"));
      invoker.execute(request);
    } catch (MavenInvocationException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
