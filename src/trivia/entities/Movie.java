package trivia.entities;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Entity {
    public String name;
    String releaseDate;
    String duration;
    String genres;
    String rating;
    String primaryActor;

    public Movie(String name) {
        this.name = name;
    }

    public Movie(String name, String releaseDate, String duration, String genres, String rating) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.genres = genres;
        this.rating = rating;
    }

    public String similair_toString(){return name+", "+releaseDate+", "+primaryActor; }

    public Movie(String title, String releaseDate, String primaryActor) {
        this.name = title;
        this.releaseDate = releaseDate;
        this.primaryActor=primaryActor;
    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public String getQuery() {
        return "primarytitle='" + name + "'";
    }

    @Override
    public String toString() {
        return name + " released in " + releaseDate;
    }

    public Movie(String name, String releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public static List<Entity> parseResultSet(ResultSet results) {
        ArrayList<Entity> movies = new ArrayList<>();
        try {
            while (results.next()) {
                String title = "";
                String releaseDate = "";
                try {
                    title = results.getString("primarytitle");
                } catch (Exception ignored) {
                }
                try {
                    releaseDate = results.getString("startyear");
                } catch (Exception ignored) {
                }
                movies.add(new Movie(title, releaseDate));
            }
        } catch (Exception ignored) {
        }
        return movies;
    }
}
