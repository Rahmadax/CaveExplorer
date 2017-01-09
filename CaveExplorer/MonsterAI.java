/*
	MonsterAI Coupling 
	Relies on:
		map
		maze dimensions (2)

*/ 

public class MonsterAI
{
	
	public Random rand = new Random();
	
	public Tiles[][] monsterAI(Tiles[][] map){
		listMonsters = findMonsters(map);
		heroLocation = findHero(map);
	}
	
	public int[][] MonsterAIInit(map){
		TrollMaze TrollMaze1 = new TrollMaze();
		int mazeRows = TrollMaze1.getMapRows();
		int mazeCols = TrollMaze1.getMapCols();
		
		int[][] monsterList = new int[][mazeCols]; 
		
		for (int i = 0; i < mazeRows - 1; i++){
			for (int j = 0; j < mazeCols - 1; j++){
				int monster = 0;
				switch(map[i][j]){
					case(Tiles.GOBLIN): 
						monster = 1;
						break;
					case(Tiles.GHOST):
						monster = 2;
						break;
					case(Tiles.SPIDER): 
						monster = 3;
						break;
					case(Tiles.TROLL):
						monster = 4;
						break;
					case(Tiles.DEMON): 
						monster = 5;
						break;
				}
				if (monster != 0){
					monsterList[TrollID][0] = monster;
					monsterList[TrollID][1] = i;
					monsterList[TrollID][2] = j;	
				}
			}
		}
	}
	
	public Tiles[][] pathfindGoblins(Tiles[][] map){
		
	}
	
	public Tiles[][] pathfindGhosts(Tiles[][] map){
		case(1):case(2):case(3):
			if(map[trollRow-1][trollCol] == Tiles.HERO){
				map[trollRow][trollCol] = Tiles.CORRIDOR;
				map[trollRow-1][trollCol] = Tiles.TROLL;
			} else if (map[trollRow+1][trollCol] == Tiles.HERO){
				map[trollRow][trollCol] = Tiles.CORRIDOR;
				map[trollRow+1][trollCol] = Tiles.TROLL;
			} else if (map[trollRow][trollCol-1] == Tiles.HERO){
				map[trollRow][trollCol] = Tiles.CORRIDOR;
				map[trollRow][trollCol-1] = Tiles.TROLL;
			} else if (map[trollRow][trollCol+1] == Tiles.HERO){
				map[trollRow][trollCol] = Tiles.CORRIDOR;
				map[trollRow][trollCol+1] = Tiles.TROLL;
			}
		break;
		case(4):
		while (true){
			int r_Row = rand.nextInt(mazeRows-2)+1;
			int r_Col = rand.nextInt(mazeCols-2)+1;
			if (map[r_Row][r_Col] == Tiles.CORRIDOR){
				map[r_Row][r_Col] == Tiles.GHOST;
			}
		} break;
	}
	
	public Tiles[][] pathfindSpiders(Tiles[][] map){
		
	}
	
	public Tiles[][] pathfindTrolls(Tiles[][] map){
		trollMap = findTrolls(map);
		heroMap = getHeroLocation(map);
		int heroRow = heroMap[0][0];
		int heroCol = heroMap[0][1];
		trollNumber = getTrollNumber();
		for (int trollID = 0; trollID < trollNumber; trollID++){
			int trollRow = trollMap[trollID][0];
			int trollCol = trollMap[trollID][1]; 
			int a = 0; int b = 0;
			int Vdistance = (heroRow-trollRow);
			int Hdistance = (heroCol-trollCol);
			int checker = ((Vdistance*Vdistance) + (Hdistance*Hdistance));
			if (trollRow == 0){
				continue;
			} else {
				if (checker <= 1){
					if(map[trollRow-1][trollCol] == Tiles.HERO){
						map[trollRow][trollCol] = Tiles.CORRIDOR;
						map[trollRow-1][trollCol] = Tiles.TROLL;
					} else if (map[trollRow+1][trollCol] == Tiles.HERO){
						map[trollRow][trollCol] = Tiles.CORRIDOR;
						map[trollRow+1][trollCol] = Tiles.TROLL;
					} else if (map[trollRow][trollCol-1] == Tiles.HERO){
						map[trollRow][trollCol] = Tiles.CORRIDOR;
						map[trollRow][trollCol-1] = Tiles.TROLL;
					} else if (map[trollRow][trollCol+1] == Tiles.HERO){
						map[trollRow][trollCol] = Tiles.CORRIDOR;
						map[trollRow][trollCol+1] = Tiles.TROLL;
					} else {continue;}
				} else if ((heroRow < trollRow) && (map[trollRow-1][trollCol] != Tiles.WALL) && (V == 4)) {
					map[trollRow][trollCol] = Tiles.CORRIDOR;
					map[trollRow-1][trollCol] = Tiles.TROLL;
				} else if ((heroRow > trollRow) && (map[trollRow+1][trollCol] != Tiles.WALL) && (V == 4)){
					map[trollRow][trollCol] = Tiles.CORRIDOR;
					map[trollRow+1][trollCol] = Tiles.TROLL;
				} else if ((heroCol < trollCol) && (map[trollRow][trollCol-1] != Tiles.WALL) && (H == 4)){
					map[trollRow][trollCol] = Tiles.CORRIDOR;
					map[trollRow][trollCol-1] = Tiles.TROLL;
				} else if ((heroCol > trollCol) && (map[trollRow][trollCol+1] != Tiles.WALL) && (H == 4)){
					map[trollRow][trollCol] = Tiles.CORRIDOR;
					map[trollRow][trollCol+1] = Tiles.TROLL;
						
				// Ambient monster movement
				} 
			}
		}return map;
	}
	
	public Tiles[][] pathfindDemons(Tiles[][] map){
		
	}	
		
	public Tiles[][] ambientMovement(Tiles[][] map){
		Tiles[] monsterList = getMonsterList();
		switch(rand.nextInt(7)){
		case(0): a = -1; b = 0; break;
		case(1): a = 1; b = 0; break;
		case(2): a = 0; b = -1; break; 
		case(3): a = 0; b = 1; break;
		case(4):case(5):case(6): break;
		}
		if (map[trollRow + a][trollCol + b] == Tiles.CORRIDOR){
			map[trollRow][trollCol] = Tiles.CORRIDOR;
			map[trollRow + a][trollCol + b] = Tiles.X;
		} else {
			continue;
		} return map; 
	}
	
	
}
