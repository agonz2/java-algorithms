import java.io.*;
import java.util.ArrayList;

public class Program8 {
    public static void main(String[] args) {
        File analysis = new File("analysis.txt");
        NodeClass Nodes = new NodeClass();
        ArrayList<String> StringList = new ArrayList<>();

        String filePath = "src/dracula.txt";

        // Read the Dracula file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder processString;
            StringBuilder secondString;

            String[] splitText;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                try {
                    // Split and put each word into string array
                    splitText = currentLine.split("[\\s\\n]+");

                    // Iterate through each index in string array
                    checkString:
                    for (String text : splitText) {
                        String mainString;
                        int dashCount = 0;
                        processString = new StringBuilder();
                        secondString = new StringBuilder();

                        // If word appears more than once, end loop
                        for (String word : StringList) {
                            if (text.equals(word)) continue checkString;
                        }

                        // Iterate through each character in text
                        for (int j = 0; j < text.length(); j++) {
                            char c = text.charAt(j);

                            if (c == '-') dashCount++;

                            // If character is a letter add it to string otherwise end loop
                            if (!Character.isLetter(c)) continue;

                            // Add characters to new string if two dashes are found
                            if (dashCount >= 2){
                                secondString.append(c);
                                continue;
                            }

                            processString.append(c);
                        }

                        // Convert to all lowercase string and store
                        mainString = processString.toString().toLowerCase();
                        StringList.add(mainString);

                        // Store second string also if minimum dashes are found
                        if (dashCount >= 2) StringList.add(secondString.toString().toLowerCase());

                    }

                } catch (IllegalArgumentException e) {System.out.println(e.getMessage());}
            }

            reader.close();

        } catch (IOException e) {System.out.println(e.getMessage());}

        // Pass each word in StringList into the BST tree
        for (String word : StringList) {
            Nodes.insert(word);
        }

        // Write out analysis on file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(analysis))) {
            int treeDepth = Nodes.getTreeDepth();

            String total = String.format("%-14s%-3s%-5s%n", "Total Nodes", ":", Nodes.getNodeTotal());
            String height = String.format("%-14s%-3s%-5s%n", "Tree Height", ":", treeDepth);
            String maximum = String.format("%-14s%-3s%-5s%n", "Maximum Nodes", ":", (int)(Math.pow(2,treeDepth) - 1));

            writer.write(
                    "Alexander Gonzalez Ramirez\n" +
                    "BST TREE ANALYSIS\n" +
                    "\n" + total + height + maximum
            );
            writer.close();
        } catch (IllegalArgumentException | IOException e) {System.out.println(e.getMessage());}

    }
}
