package trivia.entities;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Person implements Entity {
    String name;
    String birthYear;
    String deathYear;
    String topProfessions;
    String relatedJob;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String birthYear, String deathYear, String topProfessions, String relatedJob) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.topProfessions = topProfessions;
        this.relatedJob = relatedJob;
    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public String getQuery() {
        return "primaryname='" + name + "'";
    }

    @Override
    public String toString() {
        return name;
    }

    public Person(String name, String birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public static List<Entity> parseResultSet(ResultSet results) {
        ArrayList<Entity> movies = new ArrayList<>();
        try {
            while (results.next()) {
                String name = "";
                String birthYear = "";
                try {
                    name = results.getString("primaryname");
                } catch (Exception ignored) {
                }
                try {
                    birthYear = results.getString("birthyear");
                } catch (Exception ignored) {
                }
                movies.add(new Person(name, birthYear));
            }
        } catch (Exception ignored) {
        }
        return movies;
    }
}
