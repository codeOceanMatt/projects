import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ScaleBuilder {
	
	public ScaleBuilder() {
		
	}
	
	String 			userenteredScale;
	String          scaleToUse;
	private String  enteredNotesInScale;
	int             rootNote;
	private int     count;
	private int     totalWins=0;
	private int     totalTimesPlayed=0;
	String []       correctAnswers;
	String []       userAnswers;
	
	Map<String,String> scales =  new HashMap<>();	
	Scanner scan = new Scanner(System.in);
	
public void runScaleBuilder() {
		initializeKeys();
		printGreeting();
		while(true) {			
			generateKey();
			correctAnswers=scales.get(scaleToUse).trim().split(" ");
			System.out.print(scaleToUse+" ");
			
			enteredNotesInScale=scan.nextLine();
			userAnswers=enteredNotesInScale.trim().split(" ");
			if(enteredNotesInScale.equalsIgnoreCase("q")) {
				System.out.println(totalWins+" out of "+totalTimesPlayed);
				break;		
			}
			totalTimesPlayed++;					
			determineCorrect();		
			}		
			}
public void printGreeting() {
	System.out.println("Welcome to the SCALE BULDER!!!");
	System.out.println("You will be given a root note and scale type.\ntype out the scale from the root through an octave\n"
						+ "Use capital letters for notes (A,D...) and use [b] for flat and [#] for sharps (Bb,C#).\n"
						+ "Enter [q or Q] to quit and see score ");
	System.out.println("Please enter the notes in the following scale: \n");
}
public void initializeKeys() {
	scales.put("C Major", "C D E F G A B C");
	scales.put("A Minor", "A B C D E F G A");
	scales.put("G Major", "G A B C D E F# G");
	scales.put("E Minor", "E F# G A B C D E");
	scales.put("F Major", "F G A Bb C D E F");
	scales.put("D Minor", "D E F G A Bb C D");
	scales.put("Bb Major", "Bb C D Eb F G A Bb");
	scales.put("G Minor", "G A Bb C D Eb F G");
	scales.put("D Major", "D E F# G A B C# D");
	scales.put("B minor", "B C# D E F# G A B");
}
public void generateKey() {
	List<String> keysToChooseFrom=new ArrayList<>(scales.keySet());
	Random pickScale= new Random();
	scaleToUse=keysToChooseFrom.get(pickScale.nextInt(keysToChooseFrom.size()));
}
public void determineCorrect() {
	for(int i =0;i<userAnswers.length;i++) {
		if(userAnswers[i].equals(correctAnswers[i])) {
		count++;
		}
	}
	if(count==8) {
		totalWins++;
		System.out.println("CORRECT!\n");
		count=0;
	}	
	else {
		System.out.println("Incorrect! You missed " + (8-count)+" notes");
		System.out.println("Your Answer:    "+enteredNotesInScale);
		System.out.println("Correct Answer: "+scales.get(scaleToUse)+"\n");
		count=0;
		
	}
	
}
}
