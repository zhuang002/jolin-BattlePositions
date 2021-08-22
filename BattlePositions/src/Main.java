import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

	static int n;
	static int v;
	static ArrayList<Allocation> allocations=new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(reader.readLine());
		int v=Integer.parseInt(reader.readLine());
		
		int w = Integer.parseInt(reader.readLine());
		
				
		for (int i=0;i<w;i++) {
			String[] line = reader.readLine().split(" ");
			addWave(Integer.parseInt(line[0])-1, Integer.parseInt(line[1])-1,Integer.parseInt(line[2]));
		}
		
		Collections.sort(allocations, (x,y)->x.position-y.position);
		
		int previousPosition=0;
		int previousTroops=0;
		int currentTroops=0;
		int count=0;
		for (Allocation alloc:allocations) {
			currentTroops+=alloc.troopChange;
			if (currentTroops<v && previousTroops>=v) {
				count+=alloc.position-previousPosition;
			} else if (currentTroops>=v && previousTroops<v) {
				previousPosition=alloc.position;
			}
			previousTroops=currentTroops;
		}
		
		System.out.println(n-count);
		
	}
	private static void addWave(int start, int end, int troops) {
		// TODO Auto-generated method stub
		allocations.add(new Allocation(start,troops));
		allocations.add(new Allocation(end+1,-troops));
	}

}

class Allocation {
	int position=0;
	int troopChange=0;
	
	public Allocation(int p,int chg) {
		this.position=p;
		this.troopChange=chg;
	}
}