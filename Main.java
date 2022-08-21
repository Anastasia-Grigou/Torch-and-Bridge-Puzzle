import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		
		int Time = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("How many are the people?");
		
		int N = scan.nextInt();
		
		ArrayList <Integer> times = new ArrayList<Integer>(N);
		
		for(int i = 0; i < N; i++) {
			System.out.println("Give time for player " + (i+1) );
			times.add(scan.nextInt());
		}
		
		System.out.println("Give Time " );
		int time = scan.nextInt();
		
		ArrayList<Integer> nullArray = new ArrayList<Integer>();
		for(int j = 0; j < N; j++) {
			nullArray.add(0);
		}
		
		SpaceSearcher spaceSearcher = new SpaceSearcher();
		
		State initialState = new State(nullArray, times);
		State terminalState = new State();
		
		terminalState = spaceSearcher.Astar(initialState);
		
		
		
		if(time < spaceSearcher.getTotalTime()) {
			System.out.println("Could not find solution");
		} 
		else {
			System.out.println("Found solution in " + spaceSearcher.getTotalTime() + " minutes");
		}
		
	}
}