import java.util.*;

/*
	Calls on:
		map 
		floor
*/ 

public class MapGeneration{
	public Random rand = new Random();
	
	private Tiles[][] map;
	private Tiles[] listMonsters;
	private int[][] connectorList;
	private int[] entranceMap;
	private int monsterTotal = 0;
	private int monsterCounter;
	private String x;
	int i, j, a, b, n =0;
	
	private int r_Row; 
	private int r_Col;
		
	
	public Tiles[][] generateMap(){
		Tiles[][] map = new Tiles[50][50];
		map = generateBase(map);
		map = generateRealism(map);
		map = generateExit(map);
		map = generateMonsters(map);
		map = generateChests(map);
		map = generateTraders(map);
		map = generateHero(map);
		return map;
	}
	
	
	// Generates the basic map and the spawn room location.
	public Tiles[][] generateBase(Tiles[][] map){
		for(i =0; i < 50; i++){
			for(j=0; j < 50; j++){
				map[i][j] = Tiles.WALL;
			}
		}
		
	
		
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if(i == 0 || j == 0 || i == 1 || j == 1 || i == 48|| j == 48 || i == 49 || j ==49){
					map[i][j] = Tiles.EDGE;
				}
			}
		}
		
		for(int k =0; k<200; k++){
			int width = rand.nextInt(3)+2;
			int height = rand.nextInt(3)+2;
			int xpos = rand.nextInt(45)+2;
			int ypos = rand.nextInt(45)+2;
			for(int i=0; i<width; i++){
				for(int j=0; j<height; j++){
					if (map[xpos+i][ypos+j] == Tiles.WALL){
						map[xpos+i][ypos+j] = Tiles.CORRIDOR;
					}
				}
			}
		} return map;
	}		
	
	public Tiles[][] generateRealism(Tiles[][] map){
		for(int i = 0; i < 50; i++){
			for(int j = 0; j <50; j++){
				int change = rand.nextInt(20);
				if (map[i][j] == Tiles.WALL){
					if ((change == 0)||(change == 1)){
						map[i][j] = Tiles.CORRIDOR;
					}
				} else if(map[i][j] == Tiles.CORRIDOR){
					if (change == 50){
						map[i][j] = Tiles.WALL;
					}
				}
			}
		} return map;
	}
	
	
	// Places Monsters.
	public Tiles[][] generateMonsters(Tiles[][] map){
		CoreClass Core1 = new CoreClass();
		int floor = Core1.getFloor();
		setMonsterTotal(floor);
		monsterTotal = getMonsterTotal();
		System.out.println(floor);
		
		Tiles[] listMonsters = new Tiles[monsterTotal];
		if ((floor >= 0) && (floor <= 4)){
			monsterCounter = 10 + (floor*5);
			for (int goblinCounter = 0; goblinCounter < monsterCounter-1; goblinCounter++){
				while(true){
					int r_Row = rand.nextInt(46)+2;
					int r_Col = rand.nextInt(46)+2;
					if  (map[r_Row][r_Col] == Tiles.CORRIDOR){
						map[r_Row][r_Col]= Tiles.GOBLIN;
						break;
					}
				}
			}
		}
		if ((floor >= 2) && (floor < 10)){
			monsterCounter = (floor);
			for (int ghostCounter = 0; ghostCounter < monsterCounter-1; ghostCounter++){
				while(true){
					r_Row = rand.nextInt(46)+2;
					r_Col = rand.nextInt(46)+2;
					if  (map[r_Row][r_Col] == Tiles.CORRIDOR){
						map[r_Row][r_Col]= Tiles.GHOST;
						break;
					}
				}
			}
		}
		
		if ((floor >= 3) && (floor <= 7)){
			monsterCounter = 7 + (floor*5);
			for (int spiderCounter = 0; spiderCounter < monsterCounter-1; spiderCounter++){
				while(true){
					r_Row = rand.nextInt(46)+2;
					r_Col = rand.nextInt(46)+2;
					if  (map[r_Row][r_Col] == Tiles.CORRIDOR){
						map[r_Row][r_Col]= Tiles.SPIDER;
						break;
					}
				}
			}
		}
		
		if ((floor >= 6) && (floor < 10)){
			monsterCounter = (floor - 5) * 3;
			for (int trollCounter = 0; trollCounter < monsterCounter-1; trollCounter++){
				while(true){
					int r_Row = rand.nextInt(46)+2;
					int r_Col = rand.nextInt(46)+2;
					if  (map[r_Row][r_Col] == Tiles.CORRIDOR){
						map[r_Row][r_Col] = Tiles.TROLL; 
						break;
					}
				}
			}
		}
		
		if (floor == 10){
			while(true){
				int r_Row = rand.nextInt(46)+2;
				int r_Col = rand.nextInt(46)+2;
				if  (map[r_Row][r_Col] == Tiles.CORRIDOR){
					map[r_Row][r_Col]= Tiles.DEMON;
					break;
				}
			}			
		} return map;
	}
	
	// Returns the number of monsters present on the current floor.
	public void setMonsterTotal(int floor){
		if ((floor >= 0) && (floor <= 4)){monsterTotal = monsterTotal + 10 + (floor*10);}
		if ((floor >= 2) && (floor <= 10)){monsterTotal = monsterTotal + (floor*2);}
		if ((floor >= 3) && (floor <= 7)){monsterTotal = monsterTotal + 7 + (floor*5);}
		if ((floor >= 6) && (floor <= 10)){monsterTotal = monsterTotal + floor - 6;}
		if (floor == 10){monsterTotal = monsterTotal + 1;}
	}
	
	// Randomly places the exit.
	public Tiles[][] generateChests(Tiles[][] map){
		int n = 0;
		while (n < (rand.nextInt(12)+2)){
			int r_Row = rand.nextInt(46)+2;
			int r_Col = rand.nextInt(46)+2;
			if (map[r_Row][r_Col] == Tiles.CORRIDOR){
				map[r_Row][r_Col] = Tiles.CHEST;
				n++;
			}
		}return map;
	}
	
	public Tiles[][] generateTraders(Tiles[][] map){
		while(true){
			int r_Row = rand.nextInt(46)+2;
			int r_Col = rand.nextInt(46)+2;
			if((map[r_Row][r_Col] == Tiles.WALL)&&(map[r_Row+1][r_Col] == Tiles.CORRIDOR)){
				map[r_Row][r_Col] = Tiles.TRADER;
				return map;
			}
		}
	}
	
	public Tiles[][] generateHero(Tiles[][] map){
	String Spawn = 
	    ("   "+
		 " H "+
		 "   ");
		int randRow = rand.nextInt(40)+5;
		int randCol = rand.nextInt(40)+5;
		for (i = 0; i < 3; i++){
			for(j = 0; j < 3; j++){
				switch(Spawn.charAt(n)){
					case('#'):
						map[randRow+i][randCol+j] = Tiles.WALL;
						break;
					case(' '):
						map[randRow+i][randCol+j] = Tiles.CORRIDOR;
						break;
					case('H'):
						map[randRow+i][randCol+j] = Tiles.HERO;
						break;
				}
				n++;
			}
		} return map;
	}
	
	// Randomly places the exit.
	public Tiles[][] generateExit(Tiles[][] map){
		while(true){
			int r_Row = rand.nextInt(46)+2;
			int r_Col = rand.nextInt(46)+2;
			if ((map[r_Row+1][r_Col] != Tiles.WALL) && (map[r_Row-1][r_Col] != Tiles.WALL) &&
			(map[r_Row][r_Col+1] != Tiles.WALL) && (map[r_Row][r_Col-1] != Tiles.WALL)){
				if (map[r_Row][r_Col] == Tiles.CORRIDOR){
					map[r_Row][r_Col] = Tiles.EXIT;
					return map;
				}
			}
		}
	}
	
	// Returns total number of monsters
	public int getMonsterTotal(){return monsterTotal;}

	public void setConnectorList(){connectorList = connectorList;}
	
	public int[][] getConnectorList(){return connectorList;}
	
}
