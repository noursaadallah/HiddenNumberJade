package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import behaviours.agentB.*;

public class AgentB extends Agent{
	
	private static final String BEHAVIOUR_INIT = "init";
	private static final String BEHAVIOUR_PLAY = "play";
	private static final String BEHAVIOUR_END = "end";
	private static final String BEHAVIOUR_START = "start";
	
	public static AID IDENTIFIER = new AID("agentB", AID.ISLOCALNAME);

	public int inf;
	public int sup;
	public int count;
	
	@Override
	public void setup(){
		FSMBehaviour fsmb = new FSMBehaviour(this);
		
		//States
		fsmb.registerFirstState(new StartBehaviour(this), BEHAVIOUR_START);
		fsmb.registerState(new InitBehaviour(this), BEHAVIOUR_INIT);
		fsmb.registerState(new PlayBehaviour(this), BEHAVIOUR_PLAY);
		fsmb.registerLastState(new EndBehaviour(this), BEHAVIOUR_END);
		
		//Transitions
		fsmb.registerDefaultTransition(BEHAVIOUR_START, BEHAVIOUR_INIT);
		fsmb.registerDefaultTransition(BEHAVIOUR_INIT, BEHAVIOUR_PLAY);
		fsmb.registerTransition(BEHAVIOUR_PLAY, BEHAVIOUR_PLAY, 1);
		fsmb.registerTransition(BEHAVIOUR_PLAY, BEHAVIOUR_END, 0);
		
		addBehaviour(fsmb);
	}
	
	public int play() {
		count++;
		int guess = (int) (Math.random() * (sup-inf)) + inf;
		return guess;
	}

	public int getInf() {
		return inf;
	}

	public void setInf(int inf) {
		this.inf = inf;
	}

	public int getSup() {
		return sup;
	}

	public void setSup(int sup) {
		this.sup = sup;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
