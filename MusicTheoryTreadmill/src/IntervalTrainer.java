import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class IntervalTrainer {
	// INTERVAL TRAINING -------------

	public IntervalTrainer() {

	}
////**********************************( Notes in Order (Two Octaves used flats instead of sharps)*****************

	private static String[] notes = { "A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B",
			"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab" };

	// ***********************************( INTERVALS TO BE GENERATED FROM
	// )*************************

	private static String[] intervals = { "m2", "M2", "m3", "M3", "P4", "A4", "P5", "m6", "M6", "m7", "M7" };

	// *************( INITIALIZE HASHMAP of alternative notes that user can use for
	// answers) ***********************

	private static Map<String, String> noteEquivalent = new HashMap<>();
	private String noteGenerated;
	private String intervalGenerated;
	private String userAnswer;
	private String correctAnswer;
	int            indexOfNoteSelected;
	double         fraction;
	double         percentage;
	static boolean quitGame;
	static boolean userQuit;
	boolean        isCorrect;

	private int correctTotal;
	private int globalTotal;

	Random rand = new Random();
	Scanner scan = new Scanner(System.in);

	public void runIntervalGame() {
		fillNoteEquivalentMap();
		printGreeting();
		loopGame();

	}

	private void selectStartingNote() {
		indexOfNoteSelected = rand.nextInt(11);
		noteGenerated = notes[indexOfNoteSelected];
		intervalGenerated = intervals[rand.nextInt(intervals.length)];
		System.out.println(noteGenerated + " " + intervalGenerated + "\n");
//		userAnswer = scan.nextLine();
		correctAnswer = "";

	}

	private void loopGame() {
		while (true) {
//			determineQuit();
			selectStartingNote();
			
			userAnswer = getUserAnswer();
//			quitGame=determineQuit();
			if (userAnswer.equalsIgnoreCase("Q")) {
				fraction = (double) correctTotal / globalTotal;
				percentage = fraction * 100;
				System.out.println("Final Score of " + correctTotal + "/" + globalTotal + "\n%" + (int) percentage);

				scan.close();
				break;
			}
			else {
			
			globalTotal++;
			
			determineCorrect();
			displayIfRightOrWrong(isCorrect);
			isCorrect=false;
			}
		}

	}

	private void determineCorrect() {

		switch (intervalGenerated) {

		case ("m2"):{
			correctAnswer += notes[indexOfNoteSelected + 1];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;
			} 

			// -----------------------------M2(Major
			// second)-------------------------------------------------------------

		case ("M2"):{
			correctAnswer += notes[indexOfNoteSelected + 2];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

			

				break;
			}

			// ------------------------------ m3(minor
			// third)------------------------------------------------------------

		case ("m3"):{
			correctAnswer += notes[indexOfNoteSelected + 3];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;
			} 

			// ------------------------------M3(Major
			// third)------------------------------------------------------------

		case ("M3"):{
			correctAnswer += notes[indexOfNoteSelected + 4];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;
			} 

				
			// -------------------------------P4(Perfect
			// fourth)------------------------------------------------------------

		case ("P4"):{
			correctAnswer += notes[indexOfNoteSelected + 5];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;

			} 

				

			// -------------------------------A4(Augmented
			// fourth)------------------------------------------------------------

		case ("A4"):{
			correctAnswer += notes[indexOfNoteSelected + 6];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;
			} 

				
			

			// -------------------------------P5(Perfect
			// fifth)------------------------------------------------------------

		case ("P5"):{
			correctAnswer += notes[indexOfNoteSelected + 7];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;
			} 
			// --------------------------------m6(minor
			// sixth)------------------------------------------------------------

		case ("m6"):{
			correctAnswer += notes[indexOfNoteSelected + 8];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;
			} 

			// --------------------------------M6(Major
			// sixth)------------------------------------------------------------

		case ("M6"):{
			correctAnswer += notes[indexOfNoteSelected + 9];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;
			} 
			// --------------------------------m7(minor
			// seventh)------------------------------------------------------------

		case ("m7"):{
			correctAnswer += notes[indexOfNoteSelected + 10];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;
			} 

			// --------------------------------- M7(Major
			// seventh)------------------------------------------------------------

		case ("M7"):{
			correctAnswer += notes[indexOfNoteSelected + 11];
			if (userAnswer.equalsIgnoreCase(correctAnswer)
					|| userAnswer.equalsIgnoreCase(noteEquivalent.get(correctAnswer))) 
				correctTotal++;
				isCorrect = true;

				break;
			} 
		}

	}

	private void fillNoteEquivalentMap() {
		noteEquivalent.put("Bb", "A#");
		noteEquivalent.put("Db", "C#");
		noteEquivalent.put("Eb", "D#");
		noteEquivalent.put("Gb", "F#");
		noteEquivalent.put("Ab", "G#");

	}

	private void printGreeting() {
		System.out.println("WELCOME TO THE INTERVAL TRAINER!\n"
				+ "Notes must be capitalized to count, use [b] for flat and [#] for sharp\n\nm-minor\nM-major\nA-augmented\nGET STARTED!\n");
	}

	private String getUserAnswer() {
		return userAnswer = scan.nextLine();
	}

	private void displayIfRightOrWrong(boolean isRight) {

		if (isRight) {
			System.out.println("Correct!");
			System.out.println("***************************************\n");
		}
		else 
		System.out.println("Incorrect!");
		System.out.println("***************************************\n");
	}

}
