import java.util.*;

public class TraderAI
{
	public Random rand = new Random();
	
	public Tiles[][] moveTrader(Tiles[][] map) {
		int a = 0; int b = 0; int i = 0; 
		int j = 0; int k = 0; int l = 0;
		boolean nearFire = false;
		int r_Move;
		for(i = 2; i < 48; i++){
			for(j = 2; j < 48; j++){
				if (map[i][j] == Tiles.TRADER){
					while (true){
						r_Move = rand.nextInt(14);
						switch(r_Move){
							case (0):
							a = 1; b = 0; break;
							case (1):
							a = -1; b = 0; break;
							case (2):
							a = 0; b = 1; break;
							case (3): 
							a = 0; b = -1; break;
							default:
							a = 0; b = 0; break;
						}
						
						for (k = -2; k <= 2; k++){
							for (l = -2; l<= 2; l++){
								if (map[i+a+k][j+b+l] == Tiles.TRADERFIRE){
									nearFire = true;
								}
							}
						}
						if ((map[i+a][j+b] == Tiles.CORRIDOR) && (nearFire == true)){
							map[i+a][j+b] = Tiles.TRADER;
							map[i][j] = Tiles.CORRIDOR;
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
