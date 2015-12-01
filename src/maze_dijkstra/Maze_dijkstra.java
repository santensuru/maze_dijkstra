/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_dijkstra;

import java.util.ArrayList;

/**
 *
 * @author santen-suru
 */
public class Maze_dijkstra {
    
    static char[][] map = {
        {'0', '3', '0', '0', '0', '0', '0'},
        {'0', '1', '0', '1', '1', '1', '0'},
        {'0', '1', '0', '1', '0', '1', '0'},
        {'0', '1', '1', '1', '0', '1', '0'},
        {'0', '0', '1', '0', '0', '1', '0'},
        {'0', '1', '1', '1', '1', '1', '0'},
        {'0', '0', '0', '0', '0', '2', '0'}
    };
    
    static char[][] sign = {
        {'0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0'}
    };
    
    static ArrayList<Pair<String, String>> path = new ArrayList<>();
    
    public static ArrayList<Pair<String, String>> dijkstra(char[][] map, char[][] sign) {
        
        ArrayList<ArrayList<Pair<String, String>>> paths = new ArrayList<>();
        
        int currPos = 0;
        
        Pair<String, String> currPoint = new Pair<String, String>("5", "6");
        
        ArrayList<Pair<String,String>> currPath = new ArrayList<>();
        
        currPath.add(currPoint);
        
        paths.add(currPath);
        
        currPos = doRecursive(paths, map, sign, currPoint, paths.size()-1);
        
//        System.out.println(currPos);
        
//        System.out.println(paths.get(currPos));
        
        return paths.get(currPos);
    }
    
    private static int doRecursive(ArrayList<ArrayList<Pair<String, String>>> paths, char[][] map, char[][] sign, Pair<String, String> point, int n) {
        int x = Integer.parseInt(point.getLeft());
        int y = Integer.parseInt(point.getRight());
        
        int currPos = 0;
        
//        System.out.println(paths.get(n));   }
        
        // left
        if (x-1 >= 0 && Integer.parseInt(String.valueOf(map[y][x-1])) > 0 && Integer.parseInt(String.valueOf(sign[y][x-1])) == 0) {
            ArrayList<Pair<String, String>> currPath = (ArrayList<Pair<String, String>>) paths.get(n).clone();
            
            Pair<String, String> currPoint = new Pair<String, String>(String.valueOf(x-1),String.valueOf(y));
            currPath.add(currPoint);
            
            paths.add(currPath);
            
            sign[y][x-1] = '1';
            
            if (Integer.parseInt(String.valueOf(map[y][x-1])) == 3) {
                currPos += paths.size()-1;
//                System.out.println(currPos);
            } else {
                currPos += doRecursive(paths, map, sign, currPoint, paths.size()-1);
            }
        }
        
        // right
        if (x+1 <= 6 && Integer.parseInt(String.valueOf(map[y][x+1])) > 0 && Integer.parseInt(String.valueOf(sign[y][x+1])) == 0) {
            ArrayList<Pair<String, String>> currPath = (ArrayList<Pair<String, String>>) paths.get(n).clone();
            
            Pair<String, String> currPoint = new Pair<String, String>(String.valueOf(x+1),String.valueOf(y));
            currPath.add(currPoint);
            
            paths.add(currPath);
            
            sign[y][x+1] = '1';
            
            if (Integer.parseInt(String.valueOf(map[y][x+1])) == 3) {
                currPos += paths.size()-1;
//                System.out.println(currPos);
            } else {
                currPos += doRecursive(paths, map, sign, currPoint, paths.size()-1);
            }
        }
        
        // up
        if (y-1 >= 0 && Integer.parseInt(String.valueOf(map[y-1][x])) > 0 && Integer.parseInt(String.valueOf(sign[y-1][x])) == 0) {
            ArrayList<Pair<String, String>> currPath = (ArrayList<Pair<String, String>>) paths.get(n).clone();
            
            Pair<String, String> currPoint = new Pair<String, String>(String.valueOf(x),String.valueOf(y-1));
            currPath.add(currPoint);
            
            paths.add(currPath);
            
            sign[y-1][x] = '1';
            
            if (Integer.parseInt(String.valueOf(map[y-1][x])) == 3) {
                currPos += paths.size()-1;
//                System.out.println(currPos);
            } else {
                currPos += doRecursive(paths, map, sign, currPoint, paths.size()-1);
            }
        }
        
        // down
        if (y+1 <= 6 && Integer.parseInt(String.valueOf(map[y+1][x])) > 0 && Integer.parseInt(String.valueOf(sign[y+1][x])) == 0) {
            ArrayList<Pair<String, String>> currPath = (ArrayList<Pair<String, String>>) paths.get(n).clone();
            
            Pair<String, String> currPoint = new Pair<String, String>(String.valueOf(x),String.valueOf(y+1));
            currPath.add(currPoint);
            
            paths.add(currPath);
            
            sign[y+1][x] = '1';
            
            if (Integer.parseInt(String.valueOf(map[y+1][x])) == 3) {
                currPos += paths.size()-1;
//                System.out.println(currPos);
            } else {
                currPos += doRecursive(paths, map, sign, currPoint, paths.size()-1);
            }
        }
        return currPos;
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ArrayList<Pair<String, String>> currDijkstra;
        currDijkstra = dijkstra(map, sign);
        
        for (int j=0; j<currDijkstra.size(); ++j) {
            System.out.print(currDijkstra.get(j).getRight() + ", " + currDijkstra.get(j).getLeft());
            if (j != currDijkstra.size()-1) {
                System.out.print(" --> ");
            } else {
                System.out.println("");
            }
        }
        
        int stateY = 6;
        int stateX = 5;
        
        for (int j=0; j<currDijkstra.size(); ++j) {
            if (Integer.parseInt(String.valueOf(currDijkstra.get(j).getRight())) < stateY) {
                System.out.print("^ ");
                stateY = Integer.parseInt(String.valueOf(currDijkstra.get(j).getRight()));
            } else if (Integer.parseInt(String.valueOf(currDijkstra.get(j).getRight())) > stateY) {
                System.out.print("v ");
                stateY = Integer.parseInt(String.valueOf(currDijkstra.get(j).getRight()));
            } else if (Integer.parseInt(String.valueOf(currDijkstra.get(j).getLeft())) < stateX) {
                System.out.print("< ");
                stateX = Integer.parseInt(String.valueOf(currDijkstra.get(j).getLeft()));
            } else if (Integer.parseInt(String.valueOf(currDijkstra.get(j).getLeft())) > stateX) {
                System.out.print("> ");
                stateX = Integer.parseInt(String.valueOf(currDijkstra.get(j).getLeft()));
            }
            if (j == currDijkstra.size()-1) {
                System.out.println("");
            }
        }
        
        for (int i=0; i<7; i++) {
            for (int j=0; j<7; j++) {
                System.out.print(map[i][j] + " ");    
            }
            System.out.println("");
        }
        
//        for (int i=0; i<7; i++) {
//            for (int j=0; j<7; j++) {
//                System.out.print(map[j][i]);
//                
//            }
//            System.out.println("");
//        }
//        
//        for (int i=0; i<7; i++) {
//            for (int j=0; j<7; j++) {
//                System.out.print(sign[j][i]);
//                
//            }
//            System.out.println("");
//        }
    }
    
}
