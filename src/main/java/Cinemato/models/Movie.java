package main.java.Cinemato.models;

public class Movie {

    private int Id;
    private String Title;
    private String Director;
    private String Genre;
    private String Description;
    private String Year;
    private String Rating;
    private String Duration_min;

    public Movie(int id, String title, String director, String genre, String description, String year, String rating, String duration_min) {
        Id = id;
        Title = title;
        Director = director;
        Genre = genre;
        Description = description;
        Year = year;
        Rating = rating;
        Duration_min = duration_min;
    }

    public String getTitle() {
        return Title;
    }

    public String getDirector() {
        return Director;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDescription() {
        return Description;
    }

    public String getYear() {
        return Year;
    }

    public String getRating() {
        return Rating;
    }

    public String getDuration_min() {
        return Duration_min;
    }

    public String getId() {
        return Integer.toString(Id);
    }


}
