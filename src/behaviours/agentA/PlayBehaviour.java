package behaviours.agentA;

import agents.AgentA;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class PlayBehaviour extends OneShotBehaviour {

	AgentA agent;
	int hint; // 0 => OK || -1 => Too low || 1 => Too High
	
	
	public PlayBehaviour(AgentA agent) {
		super();
		this.agent = agent;
	}
	
	@Override
	public void action() {
		//wait for a guess
		agent.doWait();
		ACLMessage msg = agent.receive();
		//AgentLogger.log(message);
		int guess = -1;
		try{
			guess = Integer.parseInt(msg.getContent());
		} catch(Exception ex){
			System.out.println("Error parsing guess message");
			agent.doDelete();
		}
		
		// response => evaluate guess and send hint
		hint = agent.play(guess);
		ACLMessage rsp = new ACLMessage(ACLMessage.INFORM);
		rsp.setContent(hint+"");
		rsp.addReceiver(new AID("agentB",AID.ISLOCALNAME));
		agent.send(rsp);
	}
	
	@Override
	public int onEnd(){
		return Math.abs(hint); // 1 or -1 => keep playing | 0 => end
	}
	
}
