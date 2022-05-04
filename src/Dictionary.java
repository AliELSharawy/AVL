import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Dictionary {

    private final File file;
    AVLTree<String> tree = new AVLTree<String>();

    public Dictionary(File file) throws FileNotFoundException {
        this.file = file;
        Scanner reader = new Scanner(file);

        // insert words of dictionary file in the avl tree
        while (reader.hasNextLine())
            tree.insert(reader.nextLine());

    }

    public int getSize(){
        return tree.size();
    }

    public String insertWord(String word) throws IOException {
        if(tree.insert(word)){
            writeFile(this.file);
            return "Word inserted successfully";
        }
        return "ERROR: Word already in dictionary!";
    }

    public String searchWord(String word){
        if(tree.contains(word)) return "YES";
        return "NO";
    }

    public String deleteWord(String word) throws IOException {
        if(tree.remove(word)){
            writeFile(file);
            return "Word deleted successfully";
        }
        return "Error! Word not found";
    }

    public void lookUpBatch(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int counter = 0;
        while (reader.hasNextLine()){
            String str = reader.nextLine();
            if(tree.contains(str)){
                System.out.println(str + "\t-> YES");
                counter++;
            }
            else
                System.out.println(str + "\t-> NO");
        }
        System.out.printf("Number of words found = %d",counter);
    }

    public void deleteBatch(File file) throws IOException {
        Scanner reader = new Scanner(file);
        while(reader.hasNext()){
            String str = reader.nextLine();
            if(tree.remove(str)) {
                System.out.println(str + " deleted successfully");
            }
            else
                System.out.println("Error! " + str + " can't be deleted");
        }
        writeFile(this.file);
    }

    public void writeFile(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file.getPath());
        tree.writeWordsInOrderTraversal(fileWriter);
        fileWriter.close();
    }

}