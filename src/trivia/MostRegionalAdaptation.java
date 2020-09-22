package trivia;

import database.databaseManager;

import java.sql.ResultSet;

public class MostRegionalAdaptation {
    public static String mostRegionalAdaptation(String nconst) {
        int max = 0;
        String currenttconst = " ";
        int numlocalizations=0;
        try {
            ResultSet rs1 = databaseManager.runQuery("select tconst FROM principles WHERE nconst ='"+nconst+"';");
            while(rs1.next()){
                String tconst = rs1.getString("tconst");
                ResultSet rs2 = databaseManager.runQuery("select count(region) as total FROM akas WHERE titleID='"+tconst+"';");
                rs2.next();
                numlocalizations = rs2.getInt("total");
                if(numlocalizations > max){
                    max = numlocalizations;
                    currenttconst = tconst;
                }
            }

        } catch (Exception ignored) {}


       return databaseManager.tconstToTitle(currenttconst);
    }
}
