/*
 * Alexander Gonzalez Ramirez
 * Program #6 - Sorting Arrays
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Program6 {
    public static void intBubbleSort(int[] array) {
        int temp;
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[i]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void intSelectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min]) min = j;
            }
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }

    public static void stringBubbleSort(String[] array) {
        String temp;
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[j] != null && (array[i] == null || array[j].compareTo(array[i]) < 0)) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void stringSelectionSort(String[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (array[j] != null && (array[min] == null || array[j].compareTo(array[min]) < 0)) min = j;
            }
            String temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        // Program vars
        File results = new File("results");
        final double BILLION = 1_000_000_000.0;
        double startTime, endTime, diff;

        // FileReader vars, directory removed before submission
        String numberFile = "NumbersInFile.txt";
        String stringFile = "StringsInFIle";

        // String file vars
        String[] bubbleString = new String[10_000];
        String[] selectionString = new String[10_000];
        ArrayList<String> stringList = new ArrayList<>();

        // Integer file vars
        int[] bubbleInteger = new int[20_000];
        int[] selectionInteger = new int[20_000];
        ArrayList<Integer> integerList = new ArrayList<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(results))) {
            int totalItems = 0;
            String currentLine;

            writer.write("Alexander Gonzalez Ramirez" + "\n");

            try (BufferedReader reader = new BufferedReader(new FileReader(numberFile))) {
                for (int i = 0; (currentLine = reader.readLine()) != null; i++){
                    bubbleInteger[i] = Integer.parseInt(currentLine);
                    selectionInteger[i] = Integer.parseInt(currentLine);
                    integerList.add(Integer.parseInt(currentLine));
                    totalItems++;
                }

                writer.write("Total Integers: " + totalItems + "\n");
                totalItems = 0;

                // Bubble Sort
                startTime = System.nanoTime();
                intBubbleSort(bubbleInteger);
                endTime = System.nanoTime();
                diff = (endTime - startTime)/BILLION;
                writer.write(String.format("%-22s%-5s%-1s%-1s%n", "Bubble Sort Time", ":", diff, " seconds"));

                // Selection Sort
                startTime = System.nanoTime();
                intSelectionSort(selectionInteger);
                endTime = System.nanoTime();
                diff = (endTime - startTime)/BILLION;
                writer.write(String.format("%-22s%-5s%-1s%-1s%n", "Selection Sort Time", ":", diff, " seconds"));

                // Collection Sort
                startTime = System.nanoTime();
                Collections.sort(integerList);
                endTime = System.nanoTime();
                diff = (endTime - startTime)/BILLION;
                writer.write(String.format("%-22s%-5s%-1s%-1s%n", "Collection Sort Time", ":", diff, " seconds"));
                reader.close();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(stringFile))) {
                for (int i = 0; (currentLine = reader.readLine()) != null; i++){
                    bubbleString[i] = currentLine;
                    selectionString[i] = currentLine;
                    stringList.add(currentLine);
                    totalItems++;
                }
                writer.write("\n" + "Total Strings: " + totalItems + "\n");

                // Bubble Sort
                startTime = System.nanoTime();
                stringBubbleSort(bubbleString);
                endTime = System.nanoTime();
                diff = (endTime - startTime)/BILLION;
                writer.write(String.format("%-22s%-5s%-1s%-1s%n", "Bubble Sort Time", ":", diff, " seconds"));

                // Selection Sort
                startTime = System.nanoTime();
                stringSelectionSort(selectionString);
                endTime = System.nanoTime();
                diff = (endTime - startTime)/BILLION;
                writer.write(String.format("%-22s%-5s%-1s%-1s%n", "Selection Sort Time", ":", diff, " seconds"));

                // Collection Sort
                startTime = System.nanoTime();
                Collections.sort(stringList);
                endTime = System.nanoTime();
                diff = (endTime - startTime)/BILLION;
                writer.write(String.format("%-22s%-5s%-1s%-1s%n", "Collection Sort Time", ":", diff, " seconds"));
                reader.close();
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}