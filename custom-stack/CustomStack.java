import java.io.*;

public class CustomStack {
    public static void main(String[] args) {
        MyLinkedStack stack = new MyLinkedStack();
        File program4Out = new File("Program4");
        String filePath = "Program3.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(program4Out))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                writer.write("Alexander Gonzalez Ramirez" + "\n");
                while ((line = reader.readLine()) != null){
                    try{
                        String result = String.format("%-26s%-5s%-1s%n", line, "->", stack.InfixToPostfix(line));

                        writer.write(result);
                    } catch (IllegalArgumentException e) {
                        writer.write(line + " -> " + e.getMessage() + "\n");
                    }
                }
                writer.close();
            }
        }
        catch (IOException e) {
            String message = e.getMessage();
            System.out.println(message);
        }
        System.exit(0);
    }
}