package behaviours.agentA;

import agents.AgentA;
import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class InitBehaviour extends OneShotBehaviour {

	AgentA agent;

	public InitBehaviour(AgentA agent) {
		super();
		this.agent = agent;
	}
	
	@Override
	public void action() {
		//wait for a message from agentB to start the game
		agent.doWait();
		ACLMessage msg = agent.receive();
		//AgentLogger.log(msg);
		
		//Init game : inf , sup, hidden and count
		agent.initGame();
		
		//Send info to agent B : inf and sup
		ACLMessage rsp = new ACLMessage(ACLMessage.INFORM);
		rsp.setContent(agent.getInf() + ";" + agent.getSup());
		rsp.addReceiver(AgentB.IDENTIFIER);
		agent.send(rsp);
	}
}
