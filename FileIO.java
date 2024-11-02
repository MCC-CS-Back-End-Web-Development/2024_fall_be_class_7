import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileIO {
    public static void main(String... args) {
        BufferedWriter writer = null;
        String file = readFile();
        try {
            writer = new BufferedWriter(new FileWriter("C:\\dev\\code\\mcc\\2024_fall_be_class\\2024_fall_be_class_7\\output.txt"));
            writer.write(file);
            writer.write("\n\nEvan was here\n");
            writer.write("BE Class rules\n");
            writer.write("BE teacher is better \n than FE teacher");
            String myName = "Evan Stohlmann";
            String[] names = myName.split(" ");
            writer.write("\n\n\nFirst name is " + names[0]);
            writer.write("\nLast name is " + names[1]);
        } catch (IOException e) {
            System.out.println("Encountered exception.");
        } finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void handleExceptions() throws FileNotFoundException {
        String[] myArray = new String[10];
        Scanner scanner = null;
        try {
            File file = new File("C:\\nowhere\\test.txt");
            scanner = new Scanner(file);
//            int here = 8/0;
            String giveMe = myArray[10];
            System.out.println("Hello There");
        } catch (IndexOutOfBoundsException|ArithmeticException e) {
            System.out.println("Encountered " + e.getClass());
            if(e instanceof ArithmeticException){
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Encountered generic exception");
        } finally {
            System.out.println("FINALLY");
        }
        System.out.println("Hello from outside try block.");
    }

    public static String readFile() {
        String filePath = "C:\\dev\\code\\mcc\\2024_fall_be_class\\2024_fall_be_class_7\\colors.txt";
        System.out.println(Files.exists(Paths.get(filePath)));
        StringBuilder contents = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()){
                String line = scanner.next();
                System.out.println("Here is my line: " + line);
                contents.append(line).append("\n");
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }

        System.out.println("\n\nFile content:\n" + contents);
        return contents.toString();
    }
}
