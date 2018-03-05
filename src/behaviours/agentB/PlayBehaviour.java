package behaviours.agentB;

import agents.AgentA;
import agents.AgentB;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class PlayBehaviour extends OneShotBehaviour{
	
	AgentB agent;
	int result;

	public PlayBehaviour(AgentB agent) {
		super();
		this.agent = agent;
		result = -1;
	}
	
	@Override
	public void action() {
		int guess = agent.play();
		ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
		msg.setContent(guess + "");
		msg.addReceiver(AgentA.IDENTIFIER);
		agent.send(msg);
		
		// get response (hint) from agentA 
		agent.doWait();
		
		ACLMessage rsp = agent.receive();
		if (rsp != null && rsp.getContent() != null){
			//AgentLogger.log(reponse);
			try{
				int hint = Integer.parseInt(rsp.getContent());
				if (hint == -1){
					agent.setInf(guess);
				} else if (hint == 1) {
					agent.setSup(guess);
				}
				
				result = Math.abs(result);
				
			} catch (Exception e) {
				System.out.println(agent.getAID().getLocalName() + " : Error parsing response content");
				agent.doDelete();
			}
		}
	}
	
	@Override
	public int onEnd() {
		return result;
	}
	
}
