
/*
 * Irene Feng Nov 2022
 * This is the class where we create the Quiz and run it. It has the main method.  
 */

/*
 * Irene Feng Nov 2022
 * This is the class where we create the Quiz and run it. It has the main method.  
 */
import java.util.Scanner;
import java.util.HashMap;
public class Quiz {
        static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) throws Exception {
                // Create Categories
                Category iced = new Category("Iced Water", "You are relaxed and enjoy life.");
                Category boiling = new Category("Boiling Water", "You are competitive and energetic.");
                Category tap = new Category("Tap Water", "You are easygoing, versatile, and consistent.");
                Category bottled = new Category("Bottled Water", "You are independent and always organized.");
                FileSave fs = new FileSave("Results.csv");
                HashMap<String, Integer> categoryHashMap = fs.getResults();
                if(categoryHashMap.isEmpty()){
                categoryHashMap.put("Iced Water", 0);
                categoryHashMap.put("Boiling Water",0);
                categoryHashMap.put("Tap Water",0);
                categoryHashMap.put("Bottled Water",0);
                }
                // Create Questions
                Question q1 = new Question("What kind of energy do you look for in friends?");
                // Attach Answers to Questions
                q1.possibleAnswers[0] = new Answer("Vibrant, active, and full of life! We challenge one another, pursue our passions, and “boring” will never define our relationship.", boiling); //possibleAnswers is an array of Answer objects. 
                q1.possibleAnswers[1] = new Answer("Friends that are just there. Reliable, consistent, an everyday person; we can always count on each other.",
                                tap); // creates an answer instance
                q1.possibleAnswers[2] = new Answer("Chill, laid-back, stylish. We take a step back from stress and appreciate the finer moments of life", iced);
                q1.possibleAnswers[3] = new Answer("Someone independent that knows themselves well. Adaptable, clear-headed, and our friendship isn’t influenced by external factors.", bottled);

                Question q2 = new Question("What is the best thing you can add to water?");
                q2.possibleAnswers[0] = new Answer("Nothing! Water is good as it is.", tap);
                q2.possibleAnswers[1] = new Answer("A fresh lemon slice.", iced);
                q2.possibleAnswers[2] = new Answer("Some carbon dioxide", bottled);
                q2.possibleAnswers[3] = new Answer("Tea.", boiling);

                Question q3 = new Question("What is a flaw you perceive in yourself?");
                q3.possibleAnswers[0] = new Answer("My perfectionism rubs off as “picky” and unnecessary on some people.", bottled);
                q3.possibleAnswers[1] = new Answer("It’s hard for me to listen attentively as ideas keep popping into my head.", boiling);
                q3.possibleAnswers[2] = new Answer("I can be insensitive without realizing.", iced);
                q3.possibleAnswers[3] = new Answer("I wish I didn't blend into the background so much and be able to set stricter boundaries", tap);

                Question q4 = new Question("Which ice cream flavor is your favorite?");
                q4.possibleAnswers[0] = new Answer("Vanilla.", tap);
                q4.possibleAnswers[1] = new Answer("Mint Chocolate Chip", iced);
                q4.possibleAnswers[2] = new Answer("Matcha, covered with matcha powder", boiling);
                q4.possibleAnswers[3] = new Answer("Cookie dough.", bottled);

                Question q5 = new Question("What is your favorite electronic device?");
                q5.possibleAnswers[0] = new Answer("A computer. Even though it’s on the bulky side, it has superior performance.", boiling);
                q5.possibleAnswers[1] = new Answer("A Phone, because it’s portable and convenient.", bottled);
                q5.possibleAnswers[2] = new Answer("A TV. I like watching movies and shows at home, especially with others. ", tap);
                q5.possibleAnswers[3] = new Answer("A laptop. Sleek and good for focus mode.", iced);

                   Question q6 = new Question("What's your favorite show/movie?");
                q6.possibleAnswers[0] = new Answer("Harry Potter.", bottled);
                q6.possibleAnswers[1] = new Answer("Tom and Jerry.", iced);
                q6.possibleAnswers[2] = new Answer("Masterchef. ", boiling);
                q6.possibleAnswers[3] = new Answer("Peppa Pig.", tap );

                   Question q7 = new Question("What's your ideal number of siblings?");
                q7.possibleAnswers[0] = new Answer("0", bottled);
                q7.possibleAnswers[1] = new Answer("1", iced);
                q7.possibleAnswers[2] = new Answer("2", boiling);
                q7.possibleAnswers[3] = new Answer("3", tap );

                Question q8 = new Question("If you had a pet, what pet would you have?");
                q8.possibleAnswers[0] = new Answer("dog", tap);
                q8.possibleAnswers[1] = new Answer("cat", bottled);
                q8.possibleAnswers[2] = new Answer("goldfish",iced);
                q8.possibleAnswers[3] = new Answer("parrot", boiling);

            
                // ... more questions here

                // For each question, ask, read input, store answer.
                gameIntro();
                Question[] qList = { q1, q2, q3, q4, q5, q6, q7, q8}; //qList is an array of Question objects: "q1" and "q2"
                for (Question q : qList) { //":" goes through all items in the array qList. q is temporary; created in this line but only used in the for loop; like saying "for(____)" e,g, ___--> i=0;
                        Category c = q.ask(sc);
                        c.points++;
                }
                // Get most common category from the questions asked
                // Return Category
                Category[] cList = { iced,boiling,tap,bottled};
                // these need to be in the same order or the points will be incorrect!
                int index = getMostPopularCatIndex(cList);
                Category result = cList[index];
                System.out.println("If you were a type of water, you would be " + cList[index].label + ". ");
                System.out.println(cList[index].description);

                String key = result.label;
                categoryHashMap.put(key, categoryHashMap.get(key)+1);
                fs.updateResult(key, categoryHashMap.get(key));
                System.out.println(categoryHashMap);
               
        }

        public static void gameIntro() {
                System.out.println("Which Type of Water Are You?");
                System.out.println("You get to choose numbers 1-4 for every question. Enter '1' to play!");
               
                boolean inputValid = false;
                while (!inputValid){
                        if(!sc.hasNextInt()){
                                sc.next();
                                System.out.print("Invalid input. Please enter '1' to start: ");
                        }else{
                                int ans = sc.nextInt();
                                if (ans==1){
                                        inputValid = true;
                                }else{
                                        System.out.print("Invalid input. Please enter 1 to start: ");
                                }
                        }
                }
        }            

        // returns the index that is the max
        // the tie breaker is the first Category that has the count is the "max" :/ 
        public static int getMostPopularCatIndex(Category[] counts) {
                int maxCount = 0;
                int maxIndex = 0;
                for (int i = 0; i < counts.length; i++) {
                        if (counts[i].points > maxCount) {
                                maxCount = counts[i].points;
                                maxIndex = i;
                        }
                }
                return maxIndex;
        }
}
