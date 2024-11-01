import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class HashTable{
    String[] array;
    int[] counterArray;
    int size, count;

    HashTable(int capacity){
        this.array = new String[capacity];
        this.counterArray = new int[capacity];
        this.size = capacity;
        this.count = 0;
    }

    // Custom hash calculation using prime number
    private int hashFunc(String key) {
        int hash = 0;
        for (int n = 0; n < key.length(); n++){
            hash = (hash * 37 + key.charAt(n)) % size;
        }
        return (hash * hash) % size;
    }

    // Rehash to a larger size that is the next prime number doubled the current size
    private void rehash(){
        int newSize = getPrime(size * 2);
        String [] newArray = new String[newSize];
        int[] newCounter = new int[newSize];
        int newCount = 0;

        for (int i = 0; i < size; i++){
            if (array[i] != null){
                int index = hashFunc(array[i]);

                while (newArray[index] != null){
                    index = (index + 1) % newSize;
                }

                newArray[index] = array[i];
                newCounter[index] = counterArray[i];
                newCount++;
            }
        }

        array = newArray;
        counterArray = newCounter;
        size = newSize;
        count = newCount;

    }

    // Insert into hash table, using linear probing if a slot is occupied
    public void insert(String word){
        final double LOAD_FACTOR = 0.75;
        int index = hashFunc(word);

        while (array[index] != null){
            if (array[index].equals(word)){
                counterArray[index]++;
                return;
            }
            index = (index + 1) % size;
        }

        array[index] = word;
        counterArray[index] = 1;
        count++;

        // Rehash if current load factor is more than 0.75
        if ((double) count / size > LOAD_FACTOR){
            rehash();
        }
    }

    // Check if number is a prime number
    private boolean primeCheck(int num){
        if (num <= 1){
            return false;
        }

        for(int i = 2; i <= Math.sqrt(num); i++){
            if (num % i == 0) return false;
        }

        return true;
    }

    // Return the next available largest prime number
    private int getPrime(int num){
        while(!primeCheck(num)){
            num++;
        }
        return num;
    }

    // Return the hash table size
    public int getSize(){
        return size;
    }

    // Return the hash table load factor
    public double getLoadFactor(){
        return (double) count / size;
    }

    public double setHashTable(ArrayList<String> words, HashTable Hash){
        double startTime, endTime, diff;
        final double BILLION = 1_000_000_000.0;
        startTime = System.nanoTime();

        for (String word : words) {
            Hash.insert(word);
        }

        endTime = System.nanoTime();

        diff = (endTime - startTime)/BILLION;
        return diff;
    }
}

public class HashMapsAnalyzer {

    // Returns time duration of hashmap, includes a count to keep track of duplicates
    private static double setHashMap(ArrayList<String> words, HashMap<String, Integer> HM){
        double startTime, endTime, diff;
        final double BILLION = 1_000_000_000.0;

        startTime = System.nanoTime();

        // Inserts words from file into hashmap, incrementing the counter based on occurrences
        for (String word : words) {
            HM.put(word, HM.getOrDefault(word,0) + 1);
        }

        endTime = System.nanoTime();

        diff = (endTime - startTime)/BILLION;
        return diff;
    }

    // Returns the size of the hashmap
    private static int getSize(ArrayList<String> words, HashMap<String, Integer> HM){
        for (String word : words) {
            HM.put(word, HM.getOrDefault(word,0) + 1);
        }
        return HM.size();
    }

    // Returns formatted string to write into the results file
    private static String writerFormat(ArrayList<String> words, HashMap<String, Integer> HM, int capacity, int choice, double htLoad){
        int size = getSize(words, HM);
        double hashTime = setHashMap(words, HM);
        double loadFactor = (double) size / capacity;
        String format = "";

        if (choice == 1){ // Time string
            format = String.format("%-15s%-5s%-1s%-1s%n", "Time", ":", hashTime, " seconds");
        } else if (choice == 2){ // Size string
            format = String.format("%-15s%-5s%-1s%n", "Size/Nodes", ":", size);
        } else if (choice == 3) { // Load factor string
            format = String.format("%-15s%-5s%-1s%n", "Load Factor", ":", loadFactor);
        } else if (choice == 4) { // Comparison string
            if (loadFactor > htLoad){
                format = "The custom hash table has fewer collisions than this hash map due to a larger load factor\n";
            } else if (loadFactor < htLoad) {
                format = "This hash map has fewer collisions than the custom hash table due to a smaller load factor\n";
            } else if (loadFactor == htLoad){
                format = "This hash map has the same possible collisions as the custom hash table due to the same load factor\n";
            }

        }
        return format;
    }

    public static void main(String[] args) {
        String filePath = "src/dracula.txt";
        File results = new File("results.txt");

        final int CAPACITY_ONE = 10_000;
        final int CAPACITY_TWO = 20_000;
        final float LOAD_FACTOR = 0.75f;

        HashTable Hash = new HashTable(CAPACITY_ONE);
        HashMap<String, Integer> DefaultHashMap = new HashMap<>();
        HashMap<String, Integer> HashMap1 = new HashMap<>(CAPACITY_ONE, LOAD_FACTOR);
        HashMap<String, Integer> HashMap2 = new HashMap<>(CAPACITY_TWO, LOAD_FACTOR);

        ArrayList<String> StringList = new ArrayList<>();
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(results))) {
            double hashTableTime = Hash.setHashTable(StringList, Hash);
            double hashTableLF = Hash.getLoadFactor();
            int hashTableSize = Hash.getSize();

            writer.write("Alexander Gonzalez Ramirez\n" + "\n[CUSTOM HASH TABLE]\n");
            writer.write(String.format("%-15s%-5s%-1s%-1s%n", "Time", ":", hashTableTime, " seconds"));
            writer.write(String.format("%-15s%-5s%-1s%n", "Size/Nodes", ":", hashTableSize));
            writer.write(String.format("%-15s%-5s%-1s%n", "Load Factor", ":", hashTableLF));

            writer.write(
                    "\n[DEFAULT HASH MAP]\n" +
                    writerFormat(StringList, DefaultHashMap, 16, 1, hashTableLF) +
                    writerFormat(StringList, DefaultHashMap, 16, 2, hashTableLF) +
                    "Resized capacity from default capacity of 16 not available in java.util.HashMap methods to determine load factor\n" +

                    "\n[HASH MAP 2]\n" +
                    writerFormat(StringList, HashMap1, CAPACITY_ONE, 1, hashTableLF) +
                    writerFormat(StringList, HashMap1, CAPACITY_ONE, 2, hashTableLF) +
                    writerFormat(StringList, HashMap1, CAPACITY_ONE, 3, hashTableLF) +
                    writerFormat(StringList, HashMap1, CAPACITY_ONE, 4, hashTableLF) +

                    "\n[HASH MAP 3]\n" +
                    writerFormat(StringList, HashMap2, CAPACITY_TWO, 1, hashTableLF) +
                    writerFormat(StringList, HashMap2, CAPACITY_TWO, 2, hashTableLF) +
                    writerFormat(StringList, HashMap2, CAPACITY_TWO, 3, hashTableLF) +
                    writerFormat(StringList, HashMap2, CAPACITY_TWO, 4, hashTableLF)
            );
            writer.close();
        } catch (IllegalArgumentException | IOException e) {System.out.println(e.getMessage());}
    }
}
