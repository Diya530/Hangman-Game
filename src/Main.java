import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String filePath = "C:\\Users\\tiger\\IdeaProjects\\Hangman Game\\words.txt";
        ArrayList<String> words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line ;
            while ((line = reader.readLine()) != null){
                words.add(line.trim());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Could not find file");
        }catch (IOException e){
            System.out.println("Something went wrong");
        }catch (IllegalArgumentException e){
            System.out.println("Illegal argument");
        }
        Random random = new Random();

        if (words.isEmpty()) {
            System.out.println("Word list is empty! Exiting program.");
            return;
        }

        String word = words.get(random.nextInt(words.size()));

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuess = 0;

        for (int i = 0; i <word.length(); i++){
            wordState.add('_');
        }

        System.out.println("*****************************");
        System.out.println("Welcome to Java Hangman Game!");
        System.out.println("*****************************");

        while (wrongGuess<6){
            System.out.println(getHangmanArt(wrongGuess));
            System.out.println("Word: ");

            for (char c : wordState){
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.println("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (word.indexOf(guess) >= 0){
                System.out.println("Correct guess!");

                for (int i =0; i < word.length(); i++){
                    if (word.charAt(i)== guess){
                        wordState.set(i,guess);
                    }
                }
                if (!wordState.contains('_')){
                    System.out.println(getHangmanArt(wrongGuess));
                    System.out.println("YOU WIN!");
                    System.out.println("The word was: " + word);
                    break;
                }
            }
            else {
                wrongGuess++;
                System.out.println("Wrong guess!");
            }
        }
        if (wrongGuess>= 6){
            System.out.println(getHangmanArt(wrongGuess));
            System.out.println("GAME OVER!");
            System.out.println("The word was: " + word);
        }

        scanner.close();

    }
    static String getHangmanArt(int wrongGuess){
        return switch (wrongGuess){
            case 0 -> """
                     _______
                        |



                      """;
            case 1 -> """
                     _______
                        |
                        O


                      """;
            case 2 -> """
                     _______
                        |
                        O
                        |

                      """;
            case 3 -> """
                     _______
                        |
                        O
                      / |

                      """;
            case 4 -> """
                     _______
                        |
                        O
                      / | \\

                      """;
            case 5 -> """
                     _______
                        |
                        O
                      / | \\
                       /
                      """;
            case 6 -> """
                     _______
                        |
                        O
                      / | \\
                       / \\
                      """;

            default -> "";
        };
    }

}
