/*
 * Alexander Gonzalez Ramirez
 * Program #5 - The easy way
 */

import java.io.*;
import java.util.Stack;

public class Program5 {
    public static int precedence(char operand) {
        if (operand == '+' || operand == '-') {
            return 1;
        } else if (operand == '*' || operand == '/' || operand == '%') {
            return 2;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        File program4Out = new File("Program5.out");
        String filePath = "src/Program3.txt";
        String currentLine, result;
        StringBuilder output, fixed;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(program4Out))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                writer.write("Alexander Gonzalez Ramirez" + "\n");
                while ((currentLine = reader.readLine()) != null){
                    try{
                        output = new StringBuilder();
                        boolean isValid = false;
                        int openParen = 0;
                        int closedParen = 0;

                        for (int i = 0; i < currentLine.length(); i++){
                            char element = currentLine.charAt(i);
                            if (Character.isWhitespace(element)) continue;
                            if (Character.isLetterOrDigit(element) || element == '.') {
                                output.append(element);
                            }
                            else if (element == '(') {
                                stack.push(element);
                                openParen++;
                            }
                            else if (element == ')') {
                                closedParen++;
                                while (stack.peek() != '(') {
                                    output.append(stack.pop());
                                }
                                stack.pop();
                                openParen--;
                            }
                            else {
                                char[] characters = {'(', ')', '.', '+', '*', '/', '–', '%'};
                                for (char c : characters) {
                                    if (element == c) {
                                        isValid = true;
                                        break;
                                    }
                                }

                                while (!stack.empty() && stack.peek() != '(' && precedence(element) <= precedence(stack.peek())) {
                                    output.append(stack.pop());
                                }
                                stack.push(element);
                            }
                        }

                        while (!stack.empty()) output.append(stack.pop());

                        if (openParen > closedParen){
                            result = String.format("%-26s%-5s%-1s%n", currentLine, "->", "unmatched parens");
                        }
                        else if (!isValid){
                            result = String.format("%-26s%-5s%-1s%n", currentLine, "->", "invalid character");
                        }
                        else{
                            fixed = new StringBuilder();
                            for (int i = 0; i < output.length(); i++) {
                                char currentChar = output.charAt(i);
                                char nextChar = (i < output.length() - 1) ? output.charAt(i + 1) : '\0';

                                fixed.append(currentChar);

                                if (currentChar == '.' || (Character.isDigit(currentChar) && nextChar == '.')) {
                                    fixed.append(nextChar);
                                    i++;
                                } else {
                                    fixed.append(' ');
                                }
                            }
                            result = String.format("%-26s%-5s%-1s%n", currentLine, "->", fixed);
                        }
                        writer.write(result);

                    } catch (IllegalArgumentException e) {
                        writer.write(currentLine + " -> " + e.getMessage() + "\n");
                    }
                }
                reader.close();
                writer.close();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}
