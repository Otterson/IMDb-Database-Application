package database;

import trivia.entities.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class databaseManager {
    private static Connection databaseConnection;
    private static final String JDBC_URL = "jdbc:postgresql://db-315.cse.tamu.edu/boundless_team_6";
    private static final String USER_NAME = "reginaldfrank77_906";
    private static final String PASSWORD = "studentpwd";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            databaseConnection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static ResultSet runQuery(String query) throws SQLException {
        return databaseConnection.createStatement().executeQuery(query);
    }

    public static ResultSet runQuery(List<Entity> restrictionList, List<String> selections) {
        StringBuilder query = new StringBuilder("SELECT DISTINCT ");
        for (String sel : selections) {
            query.append(sel).append(",");
        }
        query = new StringBuilder(query.substring(0, query.length() - 1) +
                " from principles join basics using (tconst) join name_basics using(nconst) where ");
        for (Entity e : restrictionList)
            query.append(e.getQuery()).append(" AND ");
        query = new StringBuilder(query.substring(0, query.length() - 5));
        ResultSet answer = null;
        try {
            answer = runQuery(query.toString() + ";");
        } catch (Exception e) {
            System.out.print("unable to contact database"); //assuming sql is correct
            System.exit(-1);
        }
        return answer;
    }

    public static ResultSet runQuery(List<Entity> restrictionList, List<String> selections, int limit) {
        StringBuilder query = new StringBuilder("SELECT DISTINCT ");
        for (String sel : selections) {
            query.append(sel).append(",");
        }
        query = new StringBuilder(query.substring(0, query.length() - 1) +
                " from principles join basics using (tconst) join name_basics using(nconst) where ");
        for (Entity e : restrictionList)
            query.append(e.getQuery()).append(" AND ");
        query = new StringBuilder(query.substring(0, query.length() - 5));
        ResultSet answer = null;
        try {
            answer = runQuery(query.toString() + "limit "+limit+";");
        } catch (Exception e) {
            System.out.print("unable to contact database"); //assuming sql is correct
            System.exit(-1);
        }
        return answer;
    }

    public static String nconstToName(String nconst){
        try {
            ResultSet rs = runQuery("select primaryname from name_basics where nconst ='"+nconst+"';");
            rs.next();
            return rs.getString("primaryname");
        } catch (Exception ignored) {}
        return "";
    }

    public static String tconstToTitle(String tconst) {
        try {
            ResultSet rs = runQuery("select primarytitle from basics where tconst = '"+tconst+"';");
            rs.next();
            return rs.getString("primarytitle");
        } catch (Exception ignored) {}
        return "";
    }
}
