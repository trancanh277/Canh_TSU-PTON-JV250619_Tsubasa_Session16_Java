package baitap05;

public class Movie {
    private int movieId;
    private String title;
    private String director;
    private int releaseYear;

    public Movie() {
    }

    public Movie(int movieId, String title, String director, int releaseYear) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void display() {
        System.out.printf("ID: %d | Tên phim: %s | Đạo diễn: %s | Năm: %d%n",
                movieId, title, director, releaseYear);
    }
}
