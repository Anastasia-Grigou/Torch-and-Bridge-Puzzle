import java.util.ArrayList;
import java.lang.*; 

public class State 
{
	private ArrayList<Integer> left;
	private ArrayList<Integer> right;
	private int costh;
	private int costf;
	private State father = null;
	
	public State () {
		this.left = new ArrayList<Integer>();
		this.right = new ArrayList<Integer>();
		this.costh = 0;
	}
	
	public State (ArrayList<Integer> left ,ArrayList<Integer> right) {
		this.left = new ArrayList<Integer>();
		this.right = new ArrayList<Integer> ();
		this.costh = 0;
		for (int i = 0; i< left.size(); i++)
		{
			this.left.add(left.get(i));
		}
		for (int i = 0; i< right.size(); i++)
		{
			this.right.add(right.get(i));
		}
		
	}
	
	//getters
	
	public ArrayList<Integer> getLeft()
	{
		return left;
	}
	
	public ArrayList<Integer> getRight()
	{
		return right;
	}

	public State getFather ()
	{
		return father;
	}

	
	//setters
	
	public void setLeft(ArrayList<Integer> left)
	{
		this.left = left;
	}
	
	public void setRight(ArrayList<Integer> right)
	{
		this.right = right;
	}

	public void setFather(State father)
	{
		this.father = father;
	}

	//getChildren : Generates the children-states of this state
	
	public ArrayList<State> getChildren(boolean torch) 
	{
		ArrayList<State> children = new ArrayList<State>();
		State child = new State(this.left,this.right);
		
		
		if(torch == true) {
			for (int r1=0; r1 < this.right.size(); r1++) {
				
				if(this.right.get(r1) != 0) {
					
				for(int r2=r1+1; r2 < right.size(); r2++) {
					
					if(this.right.get(r2) != 0) {
				
					child.StartToEnd(r1,r2);
					
					child.setFather(this);
					children.add(child);
					child = new State(this.left,this.right);
					}
					
				}
				}
				
			}
		}
		
		else {
			for(int l =0; l < this.left.size(); l++) {
				if(this.left.get(l) != 0) {
				child.EndToStart(l);
				
				child.setFather(this);
				children.add(child);
				child = new State(this.left,this.right);
				}
			}

		}

		return children;
	}
	
	//StartToEnd : Moves players from start to end
	
	public void StartToEnd (int r1, int r2) {
		
		this.left.set(r1,this.right.get(r1));
		this.left.set(r2,this.right.get(r2));
		
		
		this.right.set(r1,0);
		this.right.set(r2,0);
		
	}
	
	//EndToStart : Moves players from end to start
	
	public void EndToStart (int l) {
		
		this.right.set(l, this.left.get(l));
		
		this.left.set(l,0);
		
	}
	
	//isTerminal : Checks whether a state is terminal
	
	public boolean isTerminal() {
		
		for(int a = 0; a < this.right.size(); a++) {
			if (this.right.get(a) != 0) {
				return false;
			}
		}
		return true;
	}
	
	//heuristic : Evaluates what the cost is until the final state
	
	public int heuristic(State currentState,boolean torch)
	{

		if(torch != true ) {
			
			int maxs = 0;
			for(int i=0; i< currentState.right.size(); i++) {
				if(currentState.right.get(i) > maxs && currentState.right.get(i) != 0 ) {
					maxs = currentState.right.get(i);
				}
			}
			
			this.costh = maxs;
		}
		else {
			int mins = 1000000;
			int maxs = 0;
			for(int i=0; i< currentState.left.size(); i++) {
				if(currentState.left.get(i) < mins && currentState.left.get(i) != 0) {
					mins = currentState.left.get(i);
				}
			}
			for(int j=0; j< currentState.right.size(); j++) {
				if(currentState.right.get(j) > maxs && currentState.right.get(j) != 0) {
					maxs = currentState.right.get(j);
				}
			}
			if (mins > maxs){
				maxs = mins;
			}
		
			this.costh = mins + maxs;
		}

		return costh;
	}
	
	//findCost : Evaluates the cost for each move
	
	public int findCost(State newState,State currentState,boolean torch)
	{
	
		if(torch == true) {
			
			int maxs = 0;
			for(int i=0; i< currentState.right.size(); i++) {
				if(currentState.right.get(i) != newState.right.get(i) ) {
					if (currentState.right.get(i) > maxs ) {
						maxs = currentState.right.get(i);
					}
				}
			}
			this.costf = maxs;
			
		}
		else {
			int mins = 1000000;
			for(int i=0; i< currentState.left.size(); i++) {
				if(currentState.left.get(i) != newState.left.get(i) ) {
					if (currentState.left.get(i) < mins && currentState.left.get(i) != 0 ) {
						mins = currentState.left.get(i);
					}
				}
			}
			
			this.costf = mins;
			
		}
	
		return costf;
	}

	
}