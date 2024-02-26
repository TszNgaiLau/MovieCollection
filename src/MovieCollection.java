import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        System.out.println("Enter a title search term: ");
        String name = scan.nextLine();
        for (int i = 0; i < movieList.size(); i++) {
            if (name.indexOf(movieList.get(i).getTitle()) != -1) {
                namesMatched.add(movieList.get(i));
            }
        }
        if (namesMatched.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            for (int i = 1; i < namesMatched.size(); i++) {
                Movie low = new Movie(namesMatched.get(i).getTitle(),
                        namesMatched.get(i).getCast(),
                        namesMatched.get(i).getDirector(),
                        namesMatched.get(i).getOverView(),
                        namesMatched.get(i).getRuntime(),
                        namesMatched.get(i).getUserRating());
                int first = i;
                while (first > 0 && low.getTitle().compareTo(namesMatched.get(i - 1).getTitle()) < 0) {
                    Movie mo = new Movie(namesMatched.get(first - 1).getTitle(),
                            namesMatched.get(first - 1).getCast(),
                            namesMatched.get(first - 1).getDirector(),
                            namesMatched.get(first - 1).getOverView(),
                            namesMatched.get(first - 1).getRuntime(),
                            namesMatched.get(first - 1).getUserRating());
                    namesMatched.set(first, mo);
                    first--;
                }
                namesMatched.set(first, low);
            }
            printTitle(namesMatched);
            System.out.print("Which movie would you like to learn more about?\nEnter number: ");
            int learn = scan.nextInt();
            scan.nextLine();
            System.out.println("Title: " + namesMatched.get(learn).getTitle() +
                    "\nRuntime: " + namesMatched.get(learn).getRuntime() + " minutes" +
                    "\nDirected by: " + namesMatched.get(learn).getDirector() +
                    "\nCast: " + namesMatched.get(learn).getCast() +
                    "\nOverview: " + namesMatched.get(learn).getOverView() +
                    "\nUser rating: " + namesMatched.get(learn).getUserRating());
        }
    }

    public void searchCast() {
        ArrayList<Movie> namesMatched = new ArrayList<>();
        ArrayList<String> cast = new ArrayList<>();
        System.out.println("Enter a title search term: ");
        String name = scan.nextLine();
        for (int i = 0; i < movieList.size(); i++) {
            if (name.indexOf(movieList.get(i).getCast()) != -1) {
                namesMatched.add(movieList.get(i));
            }
        }
        if (namesMatched.size() == 0) {
            System.out.println("No results match your search");
        }
        for (int i = 0; i < namesMatched.size(); i++) {
            String[] splitData = namesMatched.get(i).getCast().split("\\|");
            for (int j = 0; j < splitData.length; j++) {
                if (splitData[j].compareTo(name) != -1) {
                    cast.add(splitData[j]);
                }
            }
        }
        for (int i = 0; i < cast.size(); i++) {
            int count = 0;
            for (int j = 0; j < cast.size(); j++) {
                if (cast.get(i).equals(cast.get(j))) {
                    count++;
                    if (count == 2) {
                        cast.remove(j);
                        j--;
                    }
                }
            }
        }
        for (int i = 1; i < cast.size(); i++) {
            String least = cast.get(i);
            int smallestWord = i;
            while (smallestWord > 0 && least.compareTo(cast.get(smallestWord - 1)) < 0) {
                cast.set(smallestWord, cast.get(smallestWord - 1));
                smallestWord--;
            }
            cast.set(smallestWord, least);
        }
        printCast(cast);
        System.out.print("Which would you like to see all movies for?\nEnter number: ");
        int per = scan.nextInt();
        scan.nextLine();
        for ( )


    }
    public void printTitle(ArrayList<Movie> mo) {
        for (int i = 1; i <= mo.size(); i++) {
            System.out.println(i + ". " + mo.get(i).getTitle());
        }
    }
    public void printCast(ArrayList<String> mo) {
        for (int i = 1; i <= mo.size(); i++) {
            System.out.println(i + ". " + mo.get(i));
        }
    }


}
