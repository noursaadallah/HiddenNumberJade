package main;

import agents.AgentA;
import agents.AgentB;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Launcher {
	public static void main(String[] args) {
		Runtime runtime = Runtime.instance();
		Profile config = new ProfileImpl("localhost", 8888, null);
		config.setParameter("gui", "true");
		AgentContainer mc = runtime.createMainContainer(config);
		AgentController ac;
		try {
			ac = mc.createNewAgent("agentA", AgentA.class.getName(), null);
			ac.start();
			ac = mc.createNewAgent("agentB", AgentB.class.getName(), null);
			ac.start();
		} catch (StaleProxyException e) {
		}
	}
}
