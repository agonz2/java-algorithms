/*
 * Alexander Gonzalez Ramirez
 * Program #3 - Stacks: Infix to Postfix
 */

import java.io.*;

public class Program3 {
    public static void main(String[] args) {
        ArrayStackClass stack= new ArrayStackClass(32);
        File program3Out = new File("Program3");
        String filePath = "/Users/alexgr/IdeaProjects/Program3/resources/Program3.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(program3Out))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                writer.write("Alexander Gonzalez Ramirez" + "\n");
                while ((line = reader.readLine()) != null){
                    try{
                        String result = stack.InfixToPostfix(line);

                        writer.write(line + " -> "  + result + "\n");
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
