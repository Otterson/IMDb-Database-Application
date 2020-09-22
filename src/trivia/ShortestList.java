package trivia;

import database.databaseManager;

import java.sql.ResultSet;

public class ShortestList {
    public static String shortestList(int startYear, int endYear){
        //query for the list
        int gap = endYear - startYear + 1;
        String query = "WITH temp1 AS (select primaryname, primarytitle\n" +
                "from basics join principles using(tconst) join name_basics using(nconst)\n" +
                "where startyear >= '" + Integer.toString(startYear) + "' and startyear <= '" + Integer.toString(endYear)+ "'\n" +
                "),\n" +
                "temp AS (select primaryname, startyear, COUNT(*) AS num\n" +
                "from basics join principles using(tconst) join name_basics using(nconst)\n" +
                "where startyear >= '" + Integer.toString(startYear) + "' and startyear <= '" + Integer.toString(endYear)+ "'\n" +
                "GROUP BY primaryname, startyear)\n" +
                "select primaryname, primarytitle\n" +
                "from temp1\n" +
                "where primaryname IN\n" +
                "(select primaryname\n" +
                "from temp GROUP BY primaryname HAVING COUNT(primaryname) = '" + Integer.toString(gap) + "' AND SUM(num) = '" + Integer.toString(gap) + "')" +
                "ORDER BY primaryname;";
        String resultString = "";
        try {
            ResultSet result = databaseManager.runQuery(query);

          //  while (result.next()) {
            //    resultString += result.getString("primaryname") + "\n";
            //}
            while (result.next()) {
                resultString += result.getString("primaryname") + " ---- " + result.getString("primarytitle")+ "\n";}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultString;
    }

}
