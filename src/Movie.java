import java.lang.reflect.Array;

public class Movie {
    private String title;
    private String[] cast;
    private String director;
    private String overView;
    private int runtime;
    private double userRating;
    public Movie(String title, String[] cast, String director, String overView, int runtime, double userRating) {
        this.title = title;
        this.director = director;
        this.overView = overView;
        this.runtime = runtime;
        this.userRating = userRating;
    }

}
