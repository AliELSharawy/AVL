import java.io.*;
import java.util.*;

public class Demo {
    public static void main(String[] args) throws IOException {

        // file deceleration: adjust file location
        Dictionary dictionary = null;
        File dictionaryFile = new File("F:\\ali\\CSED\\level 2 term 2\\Data Structure 2\\data structure 2 lab\\lab 1 AVL Tree & Dictionary DS\\dictionary.txt");
        File deletionFile = new File("F:\\ali\\CSED\\level 2 term 2\\Data Structure 2\\data structure 2 lab\\lab 1 AVL Tree & Dictionary DS\\deletions.txt");
        File queriesFile = new File("F:\\ali\\CSED\\level 2 term 2\\Data Structure 2\\data structure 2 lab\\lab 1 AVL Tree & Dictionary DS\\queries.txt");


//        dictionary.deleteBatch(deletionFile);
        System.out.println("PRESS 1 TO LOAD DICTIONARY");
        System.out.println("PRESS 2 TO PRINT DICTIONARY SIZE");
        System.out.println("PRESS 3 TO ADD NEW WORD TO DICTIONARY");
        System.out.println("PRESS 4 TO SEARCH FOR A WORD");
        System.out.println("PRESS 5 TO REMOVE A WORD");
        System.out.println("PRESS 6 FOR BATCH LOOK-UP");
        System.out.println("PRESS 7 FOR BATCH DELETION ");
        System.out.println("PRESS 0 TO EXIT");

        Scanner scanner = new Scanner(System.in);
        boolean loaded = false;

        while (true) {
            int no = scanner.nextInt();
            scanner.nextLine();
            switch (no) {
                case 0:
                    break;
                case 1:
                    dictionary = new Dictionary(dictionaryFile);
                    loaded = true;
                    System.out.println("LOADING FINISHED");
                    break;
                case 2:
                    if (loaded)
                        System.out.println(dictionary.getSize());
                    break;
                case 3:
                    if(loaded) {
                        String word = scanner.nextLine();
                        System.out.println(dictionary.insertWord(word));
                    }
                    break;
                case 4:
                    if (loaded) {
                        String word = scanner.nextLine();
                        System.out.println(dictionary.searchWord(word));
                    }
                    break;
                case 5:
                    if (loaded) {
                        String word = scanner.nextLine();
                        System.out.println(dictionary.deleteWord(word));
                    }
                    break;
                case 6:
                    if (loaded) {
                        dictionary.lookUpBatch(queriesFile);
                    }
                    break;
                case 7:
                    if (loaded) {
                        dictionary.deleteBatch(deletionFile);
                    }
                    break;
                default:
                    System.out.println("Unexpected Operation");
                    break;
            }
        }

    }
}
