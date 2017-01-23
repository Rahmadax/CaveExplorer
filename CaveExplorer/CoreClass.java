import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.awt.Color;
import sheffield.*;
import java.io.Console;
import java.util.Scanner;

// Primary class. 

public class CoreClass
{
	public Random rand = new Random();
	
	private Tiles[][] map;

	private int gold = 0;
	private int floor = 1;
	private int EXP = 0;
	private int pickaxe = 2;
	private int HP = 10;
	private int maxHP = 10;
	private String announcement = "blank";
	private String currentMessage;
	private boolean firstMove = true;
	
	private String weapon = "Fists";
	int torchStrength = 0;

	private int[] entranceMap;
	public Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		CoreClass game = new CoreClass();
		game.startGame();
	}
	
	
	public void startGame(){
		MapGeneration newMap = new MapGeneration();
		DrawMap draw = new DrawMap();
		TraderAI trade = new TraderAI();
		map = newMap.generateMap();
		draw.drawMaze(map);
		while(true){
			draw.drawMaze(map);
			getGameplay(map);
			trade.moveTrader(map);
		}
	}
	
	
	// Gets user input (WASD) and returns the updated map.
	public Tiles[][] getGameplay(Tiles[][] map){
		int[] heroMap = getHeroLocation(map);
		int heroRow = heroMap[0];
		int heroCol = heroMap[1];
		String quality = "";
		
		// Information displayed to the player here:
		// First Line
		System.out.print("HP: " + getHP() + " / " + getMaxHP() + "   ");
		if (getHP() == 0) {
			gameOver(Ending.DIED);
		}
		System.out.print("EXP: " + EXP + "   ");
		System.out.print("Gold: " + getGold() + "   ");
		System.out.print("Exit: " + getCompassDirections(map));
		System.out.print("\n");
		// Second Line
		System.out.print("Weapon: " + getWeapon() + "   ");
		pickaxe = getPickaxe();
		if (pickaxe > 0){
			if (pickaxe == 6){
				quality = "Perfect";
			} else if ((pickaxe == 4)||(pickaxe == 5)){
				quality = "Good";
			} else if ((pickaxe == 2)||(pickaxe == 3)){
				quality = "Damaged";
			} else if (pickaxe == 1){
				quality = "Very Damaged";
			}
			System.out.println("Pickaxe: " + quality);
		} else { 
			System.out.println("");
		}
		announcement = getMessage();
		if (announcement != "blank"){
			System.out.print(announcement);
			announcement = "blank";
		}
		System.out.print("\n");
		System.out.print("\n");
		
		
		try{
			System.out.print("(W = up / A = left / S = Down / D = Right)" + '\n' + "What direction do you want to move: ");
		    String userInput = sc.nextLine();
			char direction = userInput.charAt(0);
			if (direction == 'q'){
				gameOver(Ending.TRAPPED);
			} else if ((direction == 'w') || (direction == 'a') || (direction == 's') || (direction == 'd')){
				int a = 0; int b = 0; int c = 0; int d = 0;
				switch (direction){
				case 'w': a = -1; b = -2; break;
				case 'a': c = -1; d = -2; break;
				case 's': a =  1; b =  2; break;
				case 'd': c =  1; d =  2; break;
				} 
				if (map[heroRow+a][heroCol+c] == Tiles.CORRIDOR){
					map[heroRow+a][heroCol+c] = Tiles.HERO;
					map[heroRow][heroCol] = Tiles.CORRIDOR; 
				
				} else if (map[heroRow+a][heroCol+c] == Tiles.ENTRANCE){
					announcement = ("Time to return to the surface?");
					
				} else if ((map[heroRow+a][heroCol+c] == Tiles.WALL) && (pickaxe > 0)){
					map[heroRow+a][heroCol+c] = Tiles.CORRIDOR;
					pickaxe--;
					if (pickaxe == 0){
						announcement = "Your pickaxe breaks on the rock.";
					}
					
				} else if ((map[heroRow+a][heroCol+c] == Tiles.EDGE) && (pickaxe > 0)) {
					announcement = ("This wall is too hard to break.");
					
				} else if (map[heroRow+a][heroCol+c] == Tiles.BOX) {
					announcement = ("Hey! Don't touch that!");
					
				}else if (map[heroRow+a][heroCol+c] == Tiles.TRADER){
					announcement = ("He wants to trade");
	
				} else if (map[heroRow+a][heroCol+c] == Tiles.FIRE){
					HP = HP - 1;
					announcement = ("Ouch! That burns.");
					
				} else if (map[heroRow+a][heroCol+c] == Tiles.CHEST){
					map[heroRow+a][heroCol+c] = Tiles.HERO;
					map[heroRow][heroCol] = Tiles.CORRIDOR;
					OpenChest chest = new OpenChest();
					int floor = getFloor();
					int earnedGold = chest.getNewGold(floor);
					setGold(earnedGold);
					announcement = ("You earned: " + earnedGold + " gold.");
					if (rand.nextInt(3) == 0){
						setPickaxe();
					}
					
				} else if (map[heroRow+a][heroCol+c] == Tiles.EXIT){
					gameOver(Ending.WIN);
					
				} 
				
				if (firstMove == true){
					int entranceRow = heroMap[0];
					int entranceCol = heroMap[1];
					map[entranceRow][entranceCol] = Tiles.ENTRANCE;
					firstMove = false;
				}
				
			}
		}catch(Exception e){
			System.out.println("Waiting...");
		} return (map);
	}
	
	
	public void gameOver(Ending ending){	
		floor = getFloor();
		switch(ending){
			case TRAPPED:	
				System.out.println ("You were Trapped! You are stuck in the cave forever!");
				break;
			case DIED: 
				System.out.println("You died!");
				break;
			case WIN: 
				announcement = ("Down to floor " + (floor+1) + "...");
				try{
					System.out.println ("Press any key to play again...");
					String restart = sc.nextLine();
					char restart2 = restart.charAt(0);
				}catch(Exception e){
				}
				resetTorchStrength();
				incrementFloor();
				firstMove = false;
				startGame();
		}
		System.out.println ("You made it to floor " + floor);
		try{
		System.out.println ("Press any key to play again...");
		String restart = sc.nextLine();
		char restart2 = restart.charAt(0);
		}catch(Exception e){
		}
		HP = maxHP;
		resetFloor();	
		resetTorchStrength();
		resetGold();
		resetPickaxe();
		firstMove = true;
		startGame();
	}
	
	public int[] getHeroLocation(Tiles[][] map){
		int[] heroMap = new int[2];
		for (int heroRow = 1; heroRow < 50 - 2; heroRow++){
			for (int heroCol = 1; heroCol < 50 - 2; heroCol++){
				if (map[heroRow][heroCol] == Tiles.HERO){
					heroMap[0] = heroRow;
					heroMap[1] = heroCol;
					return heroMap;
				} else {
					continue;
				}
			}
		} return heroMap;
	}
	
	public int[] getExitLocation(Tiles[][] map){
		int[] exitMap = new int[2];
		for (int row = 1; row < 50 - 2; row++){
			for (int col = 1; col < 50 - 2; col++){
				if (map[row][col] == Tiles.EXIT){
					exitMap[0] = row;
					exitMap[1] = col;
					return exitMap;
				} else {
					continue;
				}
			}
		} return exitMap;
	}
	
	public String getCompassDirections(Tiles[][] map){
		int[] heroMap = getHeroLocation(map);
		int[] exitMap = getExitLocation(map);
		if ((exitMap[0] > heroMap[0]) && (exitMap[1] > heroMap[1])){
			return "South East";
		} else if ((exitMap[0] < heroMap[0]) && (exitMap[1] > heroMap[1])) {
			return "North East";
		} else if ((exitMap[0] > heroMap[0]) && (exitMap[1] < heroMap[1])){
			return "South West";
		} else if ((exitMap[0] < heroMap[0]) && (exitMap[1] < heroMap[1])){
			return "North West";
		} else if ((exitMap[0] < heroMap[0]) && (exitMap[1] == heroMap[1])){
			return "North";
		} else if ((exitMap[0] > heroMap[0]) && (exitMap[1] == heroMap[1])){
			return "South";
		} else if ((exitMap[0] == heroMap[0]) && (exitMap[1] < heroMap[1])){
			return "West";
		} else if ((exitMap[0] == heroMap[0]) && (exitMap[1] > heroMap[1])){
			return "East";
		} else {
			return "Error";
		}
	}
		
	
	// Retruns torchStrength
	public int getTorchStrength(){return torchStrength;}
	
	// Increments torchStrength by 1
	public void incrementTorchStrength(){torchStrength+=1;}
	
	// Retruns torchStrength
	public void resetTorchStrength(){torchStrength = 0;}

	// Increments floor by 1.
	public void incrementFloor(){floor++;}
	
	// Sets floor back to 1.
	public void resetFloor(){floor = 1;}
	
	// Returns the current floor.
	public int getFloor(){return floor;}
	
	// Adds new gold to current gold.
	public void setGold(int earnedGold){gold = gold + earnedGold;}
	
	// Returns Current Gold.
	public int getGold(){return gold;}

	// Resets Gold to 0.
	public void resetGold(){gold = 0;}
	
	// Returns Current Weapon.
	public String getWeapon(){return weapon;}
	
	// Returns Pickaxe value.
	public int getPickaxe(){return pickaxe;}
	
	// Sets pickaxe to default value.(2)
	public void resetPickaxe(){pickaxe = 2;}
	
	// Sets pickaxe value to maximum.(6)
	public void setPickaxe(){pickaxe = 6;}
		
	public void setMessage(String currentMessage){announcement = currentMessage;}
	
	public String getMessage(){return announcement;}
	
	public void resetMessage(){announcement = "blank";}
	
	public int getHP(){ return HP;}
		
	public int getMaxHP() {return maxHP;}
	
	
}
