import java.util.*;

public class TraderAI
{
	public Random rand = new Random();
	
	public Tiles[][] moveTrader(Tiles[][] map) {
		int vertical = 0; int horizontal = 0; 
		int row = 0; int col = 0; 
		int k = 0; int l = 0;
		boolean nearFire = false;
		int r_Move;
		for(row = 2; row < 48; row++){
			for(col = 2; col < 48; col++){
				if (map[row][col] == Tiles.TRADER){
					while (true){
						r_Move = rand.nextInt(20);
						switch(r_Move){
							case (0):
							vertical = 1; horizontal = 0; break;
							case (1):
							vertical = -1; horizontal = 0; break;
							case (2):
							vertical = 0; horizontal = 1; break;
							case (3): 
							vertical = 0; horizontal = -1; break;
							default:
							vertical = 0; horizontal = 0; break;
						}
						
						for (k = -2; k <= 2; k++){
							for (l = -2; l<= 2; l++){
								if (map[row+vertical+k][col+horizontal+l] == Tiles.TRADERFIRE){
									nearFire = true;
								}
							}
						}
						if ((map[row+vertical][col+horizontal] == Tiles.CORRIDOR) && (nearFire == true)){
							map[row+vertical][col+horizontal] = Tiles.TRADER;
							map[row][col] = Tiles.CORRIDOR;
							return map;
							
						} else {
							return map;
						}
					}
				}
			}
		} return map;
	}
	
}
