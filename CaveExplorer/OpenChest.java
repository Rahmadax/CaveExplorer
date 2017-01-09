import java.util.*;

public class OpenChest
{
	int floor;
	public Random rand = new Random();
	
	
	public int getNewGold(int floor){
		int GoldMulti = rand.nextInt(3);
		int GoldBase = rand.nextInt(5);
		return (GoldBase + (floor*GoldMulti));
	}
	
	//public getNewLoot(floor){
		
	//
	
}