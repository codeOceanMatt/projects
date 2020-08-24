

import java.util.Random;
import java.util.Scanner;

public class Treadmill {
	
	private static String GAME_CHOICE;
	private static IntervalTrainer newGameInterval= new IntervalTrainer();
	private static ScaleBuilder newGameScale = new ScaleBuilder() ;
	

	public static void main(String[] args) {
		GameUI user = new GameUI();
		GAME_CHOICE=user.startGame();
		determineGame(GAME_CHOICE);
		
		}
	
	public static void determineGame(String game) {
		switch (game) {
		case("Interval Trainer"):

			newGameInterval.runIntervalGame();
			break;
		
		case("Scale Builder"):
			
			newGameScale.runScaleBuilder();
			break;
		
		}
		
	}
}
	
