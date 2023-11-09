/*
 * Alexander Gonzalez Ramirez
 * Program #1 - Classes and Java Review
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Project1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Album[] albums = new Album[3]; // Objects for the three albums
        Album albumMethods = new Album(); // Object for methods

        final String HEADER_FMT = "------------------------------";
        String albumTitle, performer;
        int songTotal, genre;


        System.out.println("Three albums will be required");

        // Prompt user to enter album data to assign to variables for three albums
        for(int i = 0; i<3; i++){
            System.out.println(HEADER_FMT);
            System.out.println("Enter the title of the album: ");
            albumTitle = scanner.nextLine();

            System.out.println("Enter the performer who made this album: ");
            performer = scanner.nextLine();

            // Check if user input is an integer and clears next line
            while(true){
                try{
                    System.out.println("""
                            Enter which genre this album is:
                            1.) Hip-Hop
                            2.) Easy Listening
                            3.) Orchestral
                            4.) Your Parents
                            5.) Theatre
                            """);
                    genre = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch(InputMismatchException e){
                    System.out.println("Error: Enter a number.");
                    scanner.nextLine();
                }
            }// end of first while loop

            // Check if user input is an integer, 10 minimum, and clears next line
            while(true){
                try{
                    System.out.println("How many songs are in this album?");
                    songTotal = scanner.nextInt();
                    scanner.nextLine();
                    if (albumMethods.isLong(songTotal)) {
                        break;
                    } else {
                        System.out.println("Error: The number of songs must be at least 10.");
                    }
                } catch(InputMismatchException e){
                    System.out.println("Error: Enter a number.");
                    scanner.nextLine();
                }

            }// end of second while loop

            // Assign the album data to the attributes for select album i
            albums[i] = new Album(albumTitle, performer, genre, songTotal);

        } // end of for loop

        // Close scanner
        scanner.close();

        // Display all three albums and their attributes to the user
        for(int j = 0; j<3; j++){
            System.out.println(HEADER_FMT);
            System.out.println("ALBUM #" + (j+1));
            System.out.println("Title: " + albums[j].getTitle());
            System.out.println("Performer: " + albums[j].getPerformer());
            System.out.println("Genre: " + albums[j].getGenre());
            System.out.println("Total Songs: " + albums[j].getTotal());
        }// end of for loop
    }// end of main method
}// end of Project1 class
