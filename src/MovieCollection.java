import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MovieCollection {
    public MovieCollection() {
        importData();
        menu();
    }
    public void importData() {
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overView = splitData[3];
                int runTime = Integer.parseInt(splitData[4]);
                double userRating = Double.parseDouble(splitData[5]);
                Movie i = new Movie(title, cast, director, overView, runTime, userRating);
                movieList.add(i);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
    public void searchTitles() {
        System.out.println("Enter a title search term: ");

    }
}
