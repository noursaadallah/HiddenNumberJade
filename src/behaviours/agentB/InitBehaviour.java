package behaviours.agentB;

import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class InitBehaviour extends OneShotBehaviour {

	AgentB agent;

	public InitBehaviour(AgentB agent) {
		super();
		this.agent = agent;
	}
	
	@Override
	public void action() {
		//wait for message containing inf and sup from AgentA
		agent.doWait();
		ACLMessage msg = agent.receive();
		//AgentLogger.log(message);
		
		agent.setCount(0);
		
		if (msg != null && msg.getContent() != null){
			String[] tab = msg.getContent().split(";");
			try{
				agent.setInf(Integer.parseInt(tab[0]));
				agent.setSup(Integer.parseInt(tab[1]));
			} catch(Exception ex){
				System.out.println(ex.getMessage());
				agent.doDelete();
			}
		}
	}
}
