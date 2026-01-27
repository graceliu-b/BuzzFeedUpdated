
/* Irene Feng 10/12/2022
A question class with Answers.
*/
import java.util.Scanner;

public class Question {
    // Fields
    String label;
    Answer[] possibleAnswers = new Answer[4];

    Question(String label) {
        this.label = label;
    }

    // ask a question, and return the category that corresponds to the answer
    Category ask(Scanner sc) {
        System.out.println(this.label);
        // prints out all the answer choices
        for (int i = 0; i < this.possibleAnswers.length; i++) {
            String choice = Integer.toString(i + 1);
            System.out.println("[" + choice + "]:" +
                    this.possibleAnswers[i].label);
        }
        int ans = 0;
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Invalid input. Please try again and enter an integer");
        }
        ans = sc.nextInt();

        return possibleAnswers[ans - 1].cat;
    }

}
