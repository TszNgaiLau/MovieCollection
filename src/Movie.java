import java.lang.reflect.Array;

public class Movie {
    private String title;
    private String cast;
    private String director;
    private String overView;
    private int runtime;
    private double userRating;
    public Movie(String title, String cast, String director, String overView, int runtime, double userRating) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.overView = overView;
        this.runtime = runtime;
        this.userRating = userRating;
    }
    public String getTitle() {
        return title;
    }
    public String getCast() {
        return cast;
    }
    public String getDirector() {
        return director;
    }
    public String getOverView() {
        return overView;
    }
    public int getRuntime() {
        return runtime;
    }
    public double getUserRating() {
        return userRating;
    }
}
