package com.flowershop;

import com.flowershop.model.Message;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class FlowershopApp {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(FlowershopApp.class, args);

		try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();  // instanciranje rule engine-a
    	    KieContainer kContainer = ks.getKieClasspathContainer();  // generisemo kie kontejner, na osnovu kie servisa
        	KieSession kSession = kContainer.newKieSession("ksession-rules"); //iz kie kontejnera dobijamo novu kie sesiju

            // go ! 
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);  // ubacujemo cinjenicu u radnu memoriju
            int firedRules = kSession.fireAllRules();  // aktiviramo pravilo

			System.out.println("--------------------------");
			System.out.println("number of fired rules: " + firedRules);

			kSession.getAgenda().getAgendaGroup("promotions").setFocus();
			firedRules = kSession.fireAllRules();

			System.out.println("--------------------------");
			System.out.println("number of fired rules: " + firedRules);
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}


}
