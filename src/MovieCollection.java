import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class MovieCollection {
    ArrayList<Movie> movieList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public MovieCollection() {
        importData();
        menu();
    }
    public void importData() {
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
        ArrayList<Movie> namesMatched = new ArrayList<>();
        System.out.print("Enter a title search term: ");
        String name = scan.nextLine().toLowerCase();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getTitle().toLowerCase().contains(name)) {
                namesMatched.add(movieList.get(i));
            }
        }
        if (namesMatched.isEmpty()) {
            System.out.println("No movie titles match that search term!");
        } else {
            for (int i = 0; i < namesMatched.size(); i++) {
                for (int j = 0; j < namesMatched.size(); j++) {
                    if (namesMatched.get(i).getTitle().compareTo(namesMatched.get(j).getTitle()) < 0) {
                        Movie temp = namesMatched.get(i);
                        namesMatched.set(i, namesMatched.get(j));
                        namesMatched.set(j, temp);
                    }
                }
            }
            printTitle(namesMatched);
            System.out.print("Which movie would you like to learn more about?\nEnter number: ");
            int learn = scan.nextInt();
            scan.nextLine();
            learn--;
            System.out.println(
                    "Title: " + namesMatched.get(learn).getTitle() +
                    "\nRuntime: " + namesMatched.get(learn).getRuntime() + " minutes" +
                    "\nDirected by: " + namesMatched.get(learn).getDirector() +
                    "\nCast: " + namesMatched.get(learn).getCast() +
                    "\nOverview: " + namesMatched.get(learn).getOverView() +
                    "\nUser rating: " + namesMatched.get(learn).getUserRating()
            );
        }
    }

    public void searchCast() {
        ArrayList<Movie> namesMatched = new ArrayList<>();
        ArrayList<String> cast = new ArrayList<>();
        System.out.print("Enter a person to search for (first or last name): ");
        String name = scan.nextLine();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getCast().toLowerCase().contains(name)) {
                namesMatched.add(movieList.get(i));
            }
        }
        if (namesMatched.isEmpty()) {
            System.out.println("No results match your search");
        } else {
            for (int i = 0; i < namesMatched.size(); i++) {
                String[] splitData = namesMatched.get(i).getCast().split("\\|");
                for (int j = 0; j < splitData.length; j++) {
                    if (!cast.contains(splitData[j]) && splitData[j].toLowerCase().contains(name)) {
                        cast.add(splitData[j]);
                    }
                }
            }
            for (int i = 0; i < cast.size(); i++) {
                for (int j = 0; j < cast.size(); j++) {
                    if (cast.get(i).compareTo(cast.get(j)) < 0) {
                        String temp = cast.get(i);
                        cast.set(i, cast.get(j));
                        cast.set(j, temp);
                    }
                }
            }
            printCast(cast);
            System.out.print("Which would you like to see all movies for?\nEnter number: ");
            int per = scan.nextInt();
            scan.nextLine();
            per--;
            ArrayList<Movie> castIn = new ArrayList<>();
            for (int i = 0; i < namesMatched.size(); i++) {
                if (namesMatched.get(i).getCast().contains(cast.get(per))) {
                    castIn.add(namesMatched.get(i));
                }
            }
            for (int i = 0; i < castIn.size(); i++) {
                for (int j = 0; j < castIn.size(); j++) {
                    if (castIn.get(i).getTitle().compareTo(castIn.get(j).getTitle()) < 0) {
                        Movie temp = castIn.get(i);
                        castIn.set(i, castIn.get(j));
                        castIn.set(j, temp);
                    }
                }
            }
            printCastIn(castIn);
            int p = scan.nextInt();
            scan.nextLine();
            p--;
            System.out.println(
                    "Title: " + castIn.get(p).getTitle() +
                            "\nRuntime: " + castIn.get(p).getRuntime() + " minutes" +
                            "\nDirected by: " + castIn.get(p).getDirector() +
                            "\nCast: " + castIn.get(p).getCast() +
                            "\nOverview: " + castIn.get(p).getOverView() +
                            "\nUser rating: " + castIn.get(p).getUserRating()
            );
        }
    }
    public void printTitle(ArrayList<Movie> mo) {
        for (int i = 0; i < mo.size(); i++) {
            System.out.println((i + 1) + ". " + mo.get(i).getTitle());
        }
    }
    public void printCast(ArrayList<String> mo) {
        for (int i = 0; i < mo.size(); i++) {
            System.out.println((i + 1) + ". " + mo.get(i));
        }
    }
    public void printCastIn(ArrayList<Movie> ca) {
        for (int i = 0; i < ca.size(); i++) {
            System.out.println((i + 1) + ". " + ca.get(i).getTitle());
        }
    }


}
