public class DrawMap
{
	final int BASE_CAVE_VIEW_RANGE = 100;
	final int BASE_OUTSIDE_VIEW_RANGE = 100;
	private int torchStrength = 0;
	boolean [][] visited;
	
	public void setDrawMap(){
		boolean[][] visited = new boolean[50][50];
		for(int i = 0; i < 50; i++){
			for(int j = 0; j < 50; j++){
				visited[i][j] = false;
			}			
		}
		setVisited(visited);
	}
	
	public void drawMaze(Tiles[][] map){
		String x = "\n \n";
		
		CoreClass findHero = new CoreClass();
		int[] heroMap = findHero.getHeroLocation(map);
		int heroRow = heroMap[0];
		int heroCol = heroMap[1];
		for (int r=0; r <50; r++){
			for (int c=0; c<50; c++) {
				int Vdistance = (heroRow-r);
				int Hdistance = (heroCol-c);
				//torchStrength = getTorchStrength();
				int checker = ((Vdistance*Vdistance) + (Hdistance*Hdistance));
				if (checker <= ((BASE_CAVE_VIEW_RANGE+torchStrength)*(BASE_CAVE_VIEW_RANGE+torchStrength))) {
			
					switch(map[r][c]){
						// Add more Tiles here.
						
						case HERO : x = x + 'H'; break;
						
						case WALL : x = x + '+'; break;
						case EDGE : x = x + '+'; break;
						case CORRIDOR : x = x + ' '; break;
						case CONNECTOR : x = x + 'C'; break;
						
						case ENTRANCE: x = x + '^'; break;
						case EXIT :x = x + 'V'; break;
						case BODY : x = x + 'B'; break;
						case CHEST : x = x + 'n'; break;
						
						case TROLL : x = x + 'T'; break;
						case GOBLIN : x = x + 'g'; break;
						case GHOST : x = x + 'Y'; break;
						case SPIDER : x = x + 'S'; break;
						case DEMON : x = x + 'D'; break; 
						
					}
			//	visited = getVisited();
				//visited[r][c] = true;
				//setVisited(visited);
				//} else if ((visited[r][c] == true) && (map[r][c] == Tiles.WALL)){
				//	switch(map[r][c]){
						//case WALL : x = x + '+'; break;
					//	case EXIT : x = x + 'V'; break;
					//}
				} else {
					x = x + ' ';
				}
			}
			x = x + '\n'; 
		}
		x = x +'\n';
		System.out.print (x);
	}
	
	public void setVisited(boolean[][] visited){
		visited = visited;
	}
	
	public boolean[][] getVisited(){return visited;}
}
