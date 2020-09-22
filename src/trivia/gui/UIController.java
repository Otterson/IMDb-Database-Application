package trivia.gui;

import database.databaseManager;
import trivia.ShortestList;
import trivia.entities.Entity;
import trivia.entities.Movie;
import trivia.gui.panels.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UIController {
    private static JFrame window;
    private static String searchTarget;
    private static List<Entity> restrictions;
    private static int startYear;
    private static int endYear;
    public static int questionNember;
    private static Boolean takingInput;

    static {
        window = new JFrame("Trivia Search!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        restrictions = new ArrayList<>();
    }
    public static void setQuestionNember(int i){
        UIController.questionNember = i;
    }

    public static void setSearchTarget(String searchTarget) {
        UIController.searchTarget = searchTarget;
    }

    public static String getSearchTarget() {
        return UIController.searchTarget;
    }

    public static void addRestriction(Entity restriction) {
        restrictions.add(restriction);
    }

    public static void setYears(int startYear, int endYear) {
        UIController.startYear = startYear;
        UIController.endYear = endYear;
    }

    public static void signalInputEnd() {
        synchronized (takingInput) {
            takingInput.notify();
        }
        takingInput = false;
    }

    public static void clearRestrictions() {
        restrictions.clear();
    }

    public static void showRealStart() {
        window.setContentPane(new StartScreen().getPanel());
        window.pack();
        window.setVisible(true);
    }

    public static void showStartScreen() {
        window.setContentPane(new EntitySearchScreen().getPanel());
        window.pack();
        window.setVisible(true);
    }

    public static void showRestrictionTypeScreen() {
        window.setContentPane(new RestrictionTypeScreen().getPanel());
        window.pack();
        window.setVisible(true);
    }

    public static void showMovieRestrictionScreen() {
        window.setContentPane(new MovieRestrictionScreen().getPanel());
        window.pack();
        window.setVisible(true);
    }

    public static void showPersonRestrictionScreen() {
        window.setContentPane(new PersonRestrictionScreen().getPanel());
        window.pack();
        window.setVisible(true);
    }

    public static void showYearRangeScreen(){
        window.setContentPane(new YearRangeWindow().getPanel());
        window.pack();
        window.setVisible(true);
    }

    public static void showLoadingScreen() {
        window.setContentPane(new LoadingScreen().getPanel());
        window.pack();
        window.setVisible(true);
    }

    public static List<Entity> getRestrictions() {
        return restrictions;
    }

    public static void showResults(List<Entity> results) {
        StringBuilder matches = new StringBuilder();
       String result;
            if (questionNember == 4) {
                for (int i = 0; i < 5 && i < results.size(); i++) {
                    Entity e = results.get(i);
                    matches.append(((Movie) e).similair_toString() + "\n");
                }
                result = "<html>" +
                        Arrays.stream(matches.toString().split("\n"))
                                .map(line -> "<p>" + line + ": Common Director </p>").collect(Collectors.joining(""))
                        + "</html>";
            }
             else {
                for(Entity e: results){ matches.append(e.toString() + "\n");}
                    result = "<html>" +
                            Arrays.stream(matches.toString().split("\n"))
                                    .map(line -> "<p>" + line + "</p>").collect(Collectors.joining(""))
                            + "</html>";
            }


        window.setContentPane(new MatchResultScreen(result).getPanel());
        window.pack();
        window.setVisible(true);
    }

    public static void showResults(String results) {
        String result = "<html>" +
                Arrays.stream(results.split("\n"))
                        .map(line -> "<p>" + line + "</p>").collect(Collectors.joining(""))
                + "</html>";
        window.setContentPane(new MatchResultScreen(result).getPanel());
        window.pack();
        window.setVisible(true);
    }

    public static String getUniquePersonID() throws Exception{
        List<String> nconsts = new ArrayList<>();
        while(nconsts.size()!=1){
            if(nconsts.size()!=0){
                JOptionPane.showMessageDialog(null, "multiple people match this, readd more infomration");
            }
            nconsts.clear();
            restrictions.clear();
            takingInput = true;
            showPersonRestrictionScreen();
            synchronized (takingInput) {
                takingInput.wait(); // wait until GUI says input is done being taken
            }
            showLoadingScreen();
            ResultSet rs = databaseManager.runQuery(restrictions, Collections.singletonList("nconst"),10);
            while(rs.next()&&nconsts.size()<5)
                nconsts.add(rs.getString("nconst"));
        }
        return nconsts.get(0);
    }

    public static List<Entity> queryRestrictions() throws InterruptedException {
        restrictions.clear();
        takingInput = true;
        showStartScreen();
        synchronized (takingInput) {
            takingInput.wait(); // wait until GUI says input is done being taken
        }
        return restrictions;
    }

    public static String findLeastActivity() throws InterruptedException {
        takingInput = true;
        showYearRangeScreen();
        synchronized (takingInput) {
            takingInput.wait();
        }
        showLoadingScreen();
        return ShortestList.shortestList(UIController.startYear,UIController.endYear); //give input to function
    }

    public static void showSimilairMovieScreen(){
        window.setContentPane(new SimilairMovieScreen().getPanel());
        window.pack();
        window.setVisible(true);
        synchronized (takingInput) {
            try {
                takingInput.wait();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public static int getQuestion() {
        takingInput = true;
        showRealStart();
        synchronized (takingInput) {
            try {
                takingInput.wait();
            } catch (InterruptedException ignored) {
            }
            return UIController.questionNember;
        }
    }
}
