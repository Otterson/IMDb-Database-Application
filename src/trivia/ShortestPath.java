package trivia;

import database.databaseManager;
import javafx.util.Pair;
import trivia.entities.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ShortestPath {
    private static final String nToTPath = "C:\\Users\\socce\\IdeaProjects\\CSCE315Proj1\\nToT.txt";
    private static final String tToNPath = "C:\\Users\\socce\\IdeaProjects\\CSCE315Proj1\\tToN.txt";
    private static Map<String, String[]> ntot, tton;
    static{
        try{
            BufferedReader nFile = new BufferedReader(new FileReader(nToTPath));
            BufferedReader tFile = new BufferedReader(new FileReader(tToNPath));
            ntot = new HashMap<>();
            tton = new HashMap<>();
            String line;
            while((line = nFile.readLine()) != null) {
                String[] a = line.split(" ");
                if(a.length!=2)
                    continue;
                String[] b = a[1].split(",");
                if(!ntot.containsKey(a[1]))
                    ntot.put(a[0],b);
            }
            while((line = tFile.readLine()) != null) {
                String[] a = line.split(" ");
                if(a.length!=2)
                    continue;
                String[] b = a[1].split(",");
                if(!tton.containsKey(a[1]))
                    tton.put(a[0],b);
            }
            nFile.close();
            tFile.close();
        } catch (Exception ignored) {}
    }
    //Pair<nconst::Parent,tconst::Connection>
    private static Map<String, Pair<String,String>> bfs(String start, String target, List<String> exclude){
        Queue<String> q = new LinkedList<>();
        Map<String,Pair<String,String>> bfsTree = new HashMap<>();
        Set<String> discovered = new HashSet<>();
        discovered.add(start);
        discovered.addAll(exclude);
        bfsTree.put(start,new Pair(start,null));
        q.add(start);
        while(!q.isEmpty()){
            String current = q.remove();
            for(String movie : ntot.get(current)){
                for(String neighbor : tton.get(movie)){
                    if(!discovered.contains(neighbor)){
                        discovered.add(neighbor);
                        q.add(neighbor);
                        bfsTree.put(neighbor,new Pair<String,String>(current,movie));
                        if(neighbor.equals(target))return bfsTree;
                    }
                }
            }
        }
        return null; // there is no path
    }

    public static String shortestPath(String nconst1, String nconst2, List<String> exclude) {
        Map<String, Pair<String,String>> bfsTree = bfs(nconst2,nconst1,exclude);
        if(bfsTree == null)
            return "No path exists";
        StringBuilder answer = new StringBuilder();
        String walker = nconst1;
        while(bfsTree.get(walker).getValue()!=null){
            answer.append(databaseManager.nconstToName(walker)).append("\n>Acted in '")
                    .append(databaseManager.tconstToTitle(bfsTree.get(walker).getValue())).append("' with:\n");
            walker = bfsTree.get(walker).getKey();
        }
        answer.append(databaseManager.nconstToName(walker));
        return answer.toString();
    }
}