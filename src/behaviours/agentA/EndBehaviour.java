package behaviours.agentA;

import agents.AgentA;
import jade.core.behaviours.OneShotBehaviour;

public class EndBehaviour extends OneShotBehaviour {

	AgentA agent;

	public EndBehaviour(AgentA agent) {
		super();
		this.agent = agent;
	}
	
	@Override
	public void action() {
		System.out.println(agent.getAID().getLocalName() + " : Game ended! Number of guesses: " + agent.getCount());
		agent.doDelete();
	}
}
