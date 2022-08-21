import java.util.ArrayList;
import java.util.HashSet;

public class SpaceSearcher
{
	private ArrayList<State> states;
	private ArrayList<State> activeStates;
	private HashSet<State> closedSet;
	private ArrayList<Integer> cost;
	private ArrayList<Integer> cost1;
	private ArrayList<Integer> cost2;
	private ArrayList<Integer> activeCost2;
	private ArrayList<Integer> activeCosts;
	public int finalcost;
	
	SpaceSearcher()
	{
		this.states = null;
		this.closedSet = null;
	}
	
	//Astar Algorithm Implementation with close set
	
	public State Astar(State initialState)
	{
		int tcost = 0;
		int t1 = 0;
		int t = 0;
		finalcost = 0;
		boolean torch = true;
		boolean flag = true;
		this.states = new ArrayList<State>();
		this.activeStates = new ArrayList<State>();
		this.closedSet = new HashSet<State>();
		this.cost = new ArrayList<Integer>();
		this.activeCosts = new ArrayList<Integer>();
		this.states.add(initialState);
		this.cost1 = new ArrayList<Integer>();
		this.cost2 = new ArrayList<Integer>();
		this.activeCost2 = new ArrayList<Integer>();
		while(this.states.size() > 0) {
			
			State currentState = this.states.remove(0);
			if(currentState.isTerminal())
			{
				return currentState;
			}
			if(!closedSet.contains(currentState))
			{


				this.states.clear();
				this.cost.clear();
				this.cost1.clear();
				this.cost2.clear();
				this.closedSet.add(currentState);
				this.states.addAll(currentState.getChildren(torch));
				
				for(int p = 0; p< states.size(); p++) {
					this.activeStates.add(states.get(p));
				}
				this.activeStates.remove(currentState);
				
				for (int s1 = 0; s1< states.size(); s1 ++) {
					cost1.add(states.get(s1).heuristic(states.get(s1), torch));
					cost2.add(states.get(s1).findCost(states.get(s1),currentState, torch));
					int x = cost1.get(s1) + cost2.get(s1);
					cost.add(x);
				}
				
				
				for (int n=0; n< cost.size(); n++) {
					this.activeCosts.add(cost.get(n));
				}
				
				
				int min1 = 10000000;
				for(int r=0; r < activeCosts.size(); r++){
					if(activeCosts.get(r) <= min1) {
						min1 = activeCosts.get(r);
						t1 = r;
					}
				}
	


				for(int y=0; y<states.size(); y++) {
					if(states.get(y) == activeStates.get(t1)) {
						torch = !torch;
						flag = false;
					}
				}

				
				if (flag == true){
					finalcost -= tcost;
				}
				
				this.states.clear();
				
				this.states.add(activeStates.get(t1));
				this.activeCosts.remove(activeCosts.get(t1));
				
			
				tcost = states.get(0).findCost(states.get(0),currentState, !torch);

				finalcost += tcost;

				flag = true;
			}
			
		}
		return null;
	}
	
	public int getTotalTime() {
		return finalcost;
	}
}