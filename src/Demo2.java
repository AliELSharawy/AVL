import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo2 {
    public static void main(String[] args) throws IOException {

        AVLTree<String> avlTree = new AVLTree<>();
        StringGenerator stringGenerator = new StringGenerator();
        List<String> stringCollector = new ArrayList<String>();
        String currentString;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            currentString = stringGenerator.generateRandomString(40);
            stringCollector.add(currentString);
            avlTree.insert(currentString);
        }
        System.out.print("Time Consumed for insertion 100000 random input= ");
        System.out.println(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            avlTree.remove(stringCollector.get(i));
        }
        System.out.print("Time Consumed for deletion 100000 random input = ");
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
