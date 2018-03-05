package behaviours.agentB;

import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;

public class EndBehaviour extends OneShotBehaviour {

	AgentB agent;

	public EndBehaviour(AgentB agent) {
		super();
		this.agent = agent;
	}
	
	@Override
	public void action() {
		System.out.println(agent.getAID().getLocalName() + " : Game ended! Number of guesses : " + agent.getCount());
		agent.doDelete();
	}
}
