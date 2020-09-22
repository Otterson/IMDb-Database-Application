package trivia;


import database.databaseManager;
import jdk.nashorn.internal.scripts.JO;
import trivia.entities.Entity;
import trivia.entities.Movie;
import trivia.entities.Person;
import trivia.gui.UIController;
import trivia.gui.panels.SimilairMovieScreen;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.*;

import static java.lang.System.exit;
import static trivia.gui.UIController.findLeastActivity;
import static trivia.gui.UIController.showResults;

public class Main {
    public static void main(String[] args) {
        int qn = UIController.getQuestion();

        switch (qn){
            default: Main.entitySearch();break;
            case 1: Main.shortestPath();break;
            case 2: Main.leastActive();break;
            case 3: Main.mostLocalized();break;
            case 4: Main.reccomendMovie();break;
        }
    }
    public static void entitySearch(){
        try{
            List<Entity> restrictions = UIController.queryRestrictions();
            UIController.showLoadingScreen();
            List<Entity> results;
            if (UIController.getSearchTarget().equals("Movies")) {
                results = Movie.parseResultSet(databaseManager.runQuery(restrictions, Arrays.asList("primarytitle", "startyear")));
            } else {
                results = Person.parseResultSet(databaseManager.runQuery(restrictions, Arrays.asList("primaryname", "birthyear")));
            }
            showResults(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shortestPath(){
        //System.out.print(ShortestPath.shortestPath("nm0000102","nm0000062",new ArrayList<String>()));
        try {
            String nconst1="",nconst2="";
            JOptionPane.showMessageDialog(null,"Enter First Person Details");
            nconst1 = UIController.getUniquePersonID();
            JOptionPane.showMessageDialog(null,"Enter Second Person Details");
            nconst2=UIController.getUniquePersonID();
            List<String> exclusions = new ArrayList<String>();
            int choice = JOptionPane.showOptionDialog(null,
                    "Do you want to exclude someone from the search?",
                    "Add exclusions?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, null, null);
            while(choice == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,"Enter information about the person to exclude");
                exclusions.add(UIController.getUniquePersonID());
                JOptionPane.showOptionDialog(null,
                        "Do you want to exclude another person from the search?",
                        "Add exclusion?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
            }
            UIController.showResults(ShortestPath.shortestPath(nconst1,nconst2,exclusions));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leastActive(){
        try {
            showResults(UIController.findLeastActivity());
        } catch (Exception ignored){}
    }

    private static void reccomendMovie() {
        UIController.showSimilairMovieScreen();
        UIController.showResults(SimilairMovieScreen.getResults());
    }

    private static void mostLocalized() {
        try{
            String nconst;
            JOptionPane.showMessageDialog(null, "Enter The Person Of Interest");
            nconst = UIController.getUniquePersonID();
            String result = "The work by " + databaseManager.nconstToName(nconst)+ ", which had the most regional " +
                    "adaptations was:\n" +MostRegionalAdaptation.mostRegionalAdaptation(nconst);
            UIController.showResults(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
