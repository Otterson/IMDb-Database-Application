package trivia.gui.panels;

import database.databaseManager;
import trivia.entities.Entity;
import trivia.entities.Movie;
import trivia.gui.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimilairMovieScreen {
    private JTextField title;
    private JTextField actor;
    private JTextField year;
    private JLabel titleLabel;
    private JLabel yearLabel;
    private JButton search_button;
    private JButton back_button;
    private JPanel SimilairMovieScreen;
    private JLabel actorLabel;
    private static List<Entity> result_set;
    public String actorSearch;
    public String titleSearch;

    public void parseResultSet(ResultSet results) {
        ArrayList<Entity> movies = new ArrayList<>();
        try {
            while (results.next()) {
                String title = "";
                String releaseDate = "";
                String primaryActor = "";
                try {
                    title = results.getString("primarytitle");
                   // System.out.println("title: "+title);
                } catch (Exception ignored) {
                }
                try {
                    releaseDate = results.getString("startyear");
                    //System.out.println("ReleaseDate: "+releaseDate);

                } catch (Exception ignored) {
                }
                try {
                    primaryActor = results.getString("primaryName");
                    //System.out.println("primaryName: "+primaryActor);

                } catch (Exception ignored) {
                }
                Movie to_add = new Movie(title, releaseDate, primaryActor);
                boolean in_list = false;
                for(int i = 0; i<movies.size();i++){
                    Movie to_comp = (Movie)movies.get(i);
                    if(to_comp.name.equals(to_add.name) || titleSearch.equals(to_add.name)){
                        in_list=true;
                        break;
                    }
                }
                if(!in_list){
                    movies.add(to_add);
                }
            }

        } catch (Exception ignored) {
        }
        result_set=movies;
    }

    public static List<Entity> getResults(){return result_set;}

    public SimilairMovieScreen() {
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet firstQueue = null;
                try {
                    System.out.print("");
                    System.out.println("Query 1");
                    actorSearch = actor.getText();
                    titleSearch = title.getText();
                    firstQueue = databaseManager.runQuery("SELECT directors from principles join basics using (tconst) join name_basics using(nconst) join crew_text2 using(tconst) where primaryTitle like '"+title.getText()+"' AND startyear like'"+ year.getText()+"' AND primaryname like'"+ actor.getText()+"';" );

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Error Connecting to Database Query 1");
                }
                try {
                    System.out.println("Query 2");
                    if(firstQueue.next()){
                        String director = firstQueue.getString(1).substring(1,firstQueue.getString(1).length()-1);
                        String query = "Select primaryTitle, startYear, primaryName from principles join crew_text2 using (tconst) join name_basics using(nconst) join basics using(tconst) where CAST(directors AS text) like '%"+director+"%' AND titleType like 'movie';";
                        System.out.println("");
                        firstQueue = databaseManager.runQuery(query);

                    }else{
                        System.out.println("Queue is empty");
                    }
                    System.out.println("Query completed");
//
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Error Connecting to Database Query 2");
                }
                parseResultSet(firstQueue);
                UIController.signalInputEnd();
            }
        });
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.showStartScreen();
            }
        });
    }

    public JPanel getPanel() {
        return SimilairMovieScreen;
    }

    private void addRestriction() {
        UIController.addRestriction(new Movie(title.getText()));
    }
}