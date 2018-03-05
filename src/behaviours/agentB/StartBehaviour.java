package behaviours.agentB;

import agents.AgentA;
import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class StartBehaviour extends OneShotBehaviour{

	AgentB agent;

	public StartBehaviour(AgentB agent) {
		super();
		this.agent = agent;
	}
	
	@Override
	public void action() {
		// wait 15 seconds before starting the game
		agent.doWait(15000);
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setContent("START GAME");
		msg.addReceiver(AgentA.IDENTIFIER);
		agent.send(msg);
	}
}
