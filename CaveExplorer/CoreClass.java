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
	private Tiles[][] map;

	private char x;
	private int gold = 0;
	private int heroRow;
	private int heroCol;
	private int floor = 1;
	private int EXP = 0;
	
	private String weapon = "Fists";
	int torchStrength = 0;
	int mapCols = 0;
	int mapRows = 0;

	private int[] entranceMap;
	public Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		CoreClass game = new CoreClass();
		game.startGame();
	}
	
	
	public void startGame(){
		MapGeneration newMap = new MapGeneration();
		DrawMap draw = new DrawMap();
		map = newMap.generateMap();
		draw.setDrawMap();
		while(true){
			draw.drawMaze(map);
			getGameplay(map);
		}
	}
	
	
	// Gets user input (WASD) and returns the updated map.
	public Tiles[][] getGameplay(Tiles[][] map){
		int[] heroMap = getHeroLocation(map);
		int heroRow = heroMap[0];
		int heroCol = heroMap[1];
		int z = 0;
		
		// Information displayed to the player here:
		System.out.print("Gold: " + getGold() + "     ");
		System.out.print("EXP: " + getEXP() + "     ");
		System.out.println("Weapon: " + getWeapon());
		
		
		
		try{
			System.out.print("(W = up / A = left / S = Down / D = Right)" + '\n' + "What direction do you want to move: ");
		    String userInput = sc.nextLine();
			char direction = userInput.charAt(0);
			if (direction == 'q'){
				Ending ending = Ending.TRAPPED;
				gameOver(ending);
			}
			if ((direction == 'w') || (direction == 'a') || (direction == 's') || (direction == 'd')){
				int a = 0; int b = 0; int c = 0; int d = 0;
				switch (direction){
				case 'w': a = -1; b = -2; break;
				case 'a': c = -1; d = -2; break;
				case 's': a =  1; b =  2; break;
				case 'd': c =  1; d =  2; break;
				} 
				if (map[heroRow+a][heroCol+c] == Tiles.WALL){
					if (map[heroRow+b][heroCol+d] == Tiles.WALL){
						a = 0;
					} else if (map[heroRow+b][heroCol+d] == Tiles.TROLL){
						map[heroRow+b][heroCol+d] = Tiles.BODY;
					} else if (map[heroRow+b][heroCol+d] == Tiles.EXIT){
						a = 0;
					} else {
						map[heroRow][heroCol] = Tiles.CORRIDOR;
						map[heroRow+a][heroCol+c] = Tiles.HERO;
						map[heroRow+b][heroCol+d] = Tiles.WALL;
						}
				/* Add new pickups, enemies etc here. 
				} else if () {  */
				} else if (map[heroRow+a][heroCol+c] == Tiles.BODY){
					System.out.println("Eww... It's all over your shoes.");
					map[heroRow+a][heroCol+c] = Tiles.HERO;
					map[heroRow][heroCol] = Tiles.CORRIDOR;
				} else if (map[heroRow+a][heroCol+c] == Tiles.CORRIDOR){
					map[heroRow+a][heroCol+c] = Tiles.HERO;
					map[heroRow][heroCol] = Tiles.CORRIDOR;
				} else if (map[heroRow+a][heroCol+c] == Tiles.CHEST){
					map[heroRow+a][heroCol+c] = Tiles.HERO;
					map[heroRow][heroCol] = Tiles.CORRIDOR;
					OpenChest chest = new OpenChest();
					int floor = getFloor();
					int earnedGold = chest.getNewGold(floor);
					setGold(earnedGold);
				} else if (map[heroRow+a][heroCol+c] == Tiles.EXIT){
					gameOver(Ending.WIN);
				} else { System.out.println("What did you do...?");
				} 
				
				//entranceMap = getEntranceMap();
				int entranceRow = entranceMap[0];
				int entranceCol = entranceMap[1];
				if (map[entranceRow][entranceCol] == Tiles.CORRIDOR){
					map[entranceRow][entranceCol] = Tiles.ENTRANCE;
				} return map;
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
			case EATEN: 
				System.out.println("You were gobbled up by a troll!");
				break;
			case WIN: 
				System.out.println("You made it to the next floor...");
				resetTorchStrength();
				System.out.println("Your torch burns low as you move down the stairs");
				incrementFloor();
				System.out.println("Now entering floor " + (floor));
				System.out.println("It sounds like there are more trolls down here...");
				startGame();
		}
		System.out.println ("You made it to floor " + floor);
		System.out.println ("Press any key to play again...");
		resetFloor();	
		resetTorchStrength();
		resetGold();
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
	
	// Returns mapRows.
	public int getMapRows(){return mapRows;}
	
	// Returns mapCols
	public int getMapCols(){return mapCols;}
	
	// Adds new gold to current gold.
	public void setGold(int earnedGold){gold = gold + earnedGold;}
	
	// Returns Current Gold.
	public int getGold(){return gold;}
	
	// Resets Gold to 0 
	public void resetGold(){gold = 0;}
	
	// Returns Current EXP.
	public int getEXP(){return EXP;}
	
	// Returns Current Weapon
	public String getWeapon(){return weapon;}
		
		
		
}