package agents;

import behaviours.agentA.*;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;

public class AgentA extends Agent{

	private static final String BEHAVIOUR_INIT = "init";
	private static final String BEHAVIOUR_PLAY = "play";
	private static final String BEHAVIOUR_END = "end";
	
	public int inf;
	public int sup;
	private int hidden;
	private int count;
	
	public static AID  IDENTIFIER = new AID("agentA",AID.ISLOCALNAME);
	
	@Override
	public void setup(){
		FSMBehaviour fsmb = new FSMBehaviour(this);
		
		//States
		fsmb.registerFirstState(new InitBehaviour(this), BEHAVIOUR_INIT);
		fsmb.registerState(new PlayBehaviour(this), BEHAVIOUR_PLAY);
		fsmb.registerLastState(new EndBehaviour(this), BEHAVIOUR_END);
		
		//Transitions
		fsmb.registerDefaultTransition(BEHAVIOUR_INIT, BEHAVIOUR_PLAY);
		fsmb.registerTransition(BEHAVIOUR_PLAY, BEHAVIOUR_PLAY, 1);
		fsmb.registerTransition(BEHAVIOUR_PLAY, BEHAVIOUR_END, 0);
		
		addBehaviour(fsmb);
		
	}
	
	public int play(int guess) {
		count++;
		if(guess == hidden ) return 0;
		else if(guess > hidden ) return 1;
		else return -1; //(guess < hidden)
	}
	
	public int getHidden() {
		return hidden;
	}
	
	public void initHidden() {
		hidden = (int) (Math.random() * (sup-inf)) + inf;
		System.out.println(getLocalName() + " : hidden number = " + hidden);
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
	
	public void initGame() {
		setCount(0);
		setInf(0);
		setSup(100);
		initHidden();
	}
}
