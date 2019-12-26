package main.java.Cinemato.models;

import javafx.util.StringConverter;

import java.sql.Time;
import java.sql.Date;

public class Screening {



    private int Id;
    private int MovieId;
    private String Date;
    private String StartTime;

    public Screening(int id, int movieId, String date, String startTime) {
        Id = id;
        MovieId = movieId;
        Date = date;
        StartTime = startTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getMovieId() {
        return MovieId;
    }

    public void setMovieId(int movieId) {
        MovieId = movieId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String toStringMessage() {
        return Id + "&" + MovieId + "&" + Date + "&" + StartTime;
    }

    public String toString() {
        return Date;
    }
}
