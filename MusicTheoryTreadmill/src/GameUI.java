
public class GameUI {
	
	private static final String GAME_OPTION_1_INTERVAL="Interval Trainer";
	private static final String GAME_OPTION_2_SCALE="Scale Builder";
	private static final String [] GAME_OPTIONS= {GAME_OPTION_1_INTERVAL,GAME_OPTION_2_SCALE};
	private static String gameSelected ;
	
	Menu menu = new Menu(System.in,System.out);
	
	public String startGame() {
		
		System.out.println("Which Exercise?");
		gameSelected = (String) menu.getChoiceFromOptions(GAME_OPTIONS);
		return gameSelected;
		
	}

		
	

}
