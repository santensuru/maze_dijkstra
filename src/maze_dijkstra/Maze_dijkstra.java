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
    
//    static char[][] map = {
//        {'0', '3', '0', '0', '0', '0', '0'},
//        {'0', '1', '0', '1', '1', '1', '0'},
//        {'0', '1', '0', '1', '0', '1', '0'},
//        {'0', '1', '1', '1', '0', '1', '0'},
//        {'0', '0', '1', '0', '0', '1', '0'},
//        {'0', '1', '1', '1', '1', '1', '0'},
//        {'0', '0', '0', '0', '0', '2', '0'}
//    };
//    
//    static char[][] sign = {
//        {'0', '0', '0', '0', '0', '0', '0'},
//        {'0', '0', '0', '0', '0', '0', '0'},
//        {'0', '0', '0', '0', '0', '0', '0'},
//        {'0', '0', '0', '0', '0', '0', '0'},
//        {'0', '0', '0', '0', '0', '0', '0'},
//        {'0', '0', '0', '0', '0', '0', '0'},
//        {'0', '0', '0', '0', '0', '0', '0'}
//    };
    
    static char[][] map = {
        {'0', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '1', '0', '1', '1', '1', '0', '0', '0', '0', '0', '0'},
        {'0', '1', '0', '1', '0', '1', '0', '0', '0', '0', '0', '0'},
        {'0', '1', '1', '1', '0', '1', '0', '1', '1', '1', '0', '0'},
        {'0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0', '0'},
        {'0', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1', '0'},
        {'0', '1', '0', '1', '1', '1', '1', '1', '0', '0', '1', '0'},
        {'0', '1', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0'},
        {'0', '1', '1', '1', '0', '1', '1', '0', '0', '1', '1', '0'},
        {'0', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '0'},
        {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0'},
        {'0', '0', '0', '0', '0', '2', '0', '0', '0', '0', '0', '0'}
    };
    
    static char[][] sign = {
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}
    };
    
    static ArrayList<Pair<String, String>> path = new ArrayList<>();
    
    public static ArrayList<Pair<String, String>> dijkstra(char[][] map, char[][] sign) {
        
        ArrayList<ArrayList<Pair<String, String>>> paths = new ArrayList<>();
        
        char[] currPos;
        
        Pair<String, String> currPoint = new Pair<String, String>("5", "11");
        
        ArrayList<Pair<String,String>> currPath = new ArrayList<>();
        
        currPath.add(currPoint);
        
        paths.add(currPath);
        
        currPos = doRecursive(paths, map, sign, currPoint, paths.size()-1, (char)0x01);
        
//        System.out.println((int)currPos[0]);
        
//        System.out.println(paths.get(currPos));
        
        return paths.get((int)currPos[0]);
    }
    
    private static char[] doRecursive(ArrayList<ArrayList<Pair<String, String>>> paths, char[][] map, char[][] sign, Pair<String, String> point, int n, char depth) {
        int x = Integer.parseInt(point.getLeft());
        int y = Integer.parseInt(point.getRight());
        
        char[] currPos = new char[2];
        currPos[0] = 0x00;
        currPos[1] = 0x00;
        
        char[] nextPos = new char[2];
        nextPos[0] = 0x00;
        nextPos[1] = 0x00;
        
//        System.out.println(paths.get(n));   }
        
        // left
        if (x-1 >= 0 && Integer.parseInt(String.valueOf(map[y][x-1])) > 0 && (sign[y][x-1] > depth || sign[y][x-1] == 0x00)) {
            ArrayList<Pair<String, String>> currPath = (ArrayList<Pair<String, String>>) paths.get(n).clone();
            
            Pair<String, String> currPoint = new Pair<String, String>(String.valueOf(x-1),String.valueOf(y));
            currPath.add(currPoint);
            
            paths.add(currPath);
            
            sign[y][x-1] = depth;
            
            if (Integer.parseInt(String.valueOf(map[y][x-1])) == 3) {
                if ((currPos[1] != 0 && depth < currPos[1]) || currPos[1] == 0x00) {
                    currPos[0] = (char)(paths.size()-1);
                    currPos[1] = depth;
                }
//                System.out.println(currPos);
            } else {
                nextPos = doRecursive(paths, map, sign, currPoint, paths.size()-1, (char)(depth+1));
                if ((nextPos[1] <= currPos[1] && currPos[1] != 0x00 && nextPos[1] != 0x00) || (currPos[1] == 0x00 && nextPos[1] != 0x00)) {
                    currPos[0] = nextPos[0];
                    currPos[1] = nextPos[1];
                }
            }
        }
        
        // right
        if (x+1 <= 11 && Integer.parseInt(String.valueOf(map[y][x+1])) > 0 && (sign[y][x+1] > depth || sign[y][x+1] == 0x00)) {
            ArrayList<Pair<String, String>> currPath = (ArrayList<Pair<String, String>>) paths.get(n).clone();
            
            Pair<String, String> currPoint = new Pair<String, String>(String.valueOf(x+1),String.valueOf(y));
            currPath.add(currPoint);
            
            paths.add(currPath);
            
            sign[y][x+1] = depth;
            
            if (Integer.parseInt(String.valueOf(map[y][x+1])) == 3) {
                if ((currPos[1] != 0 && depth < currPos[1]) || currPos[1] == 0x00) {
                    currPos[0] = (char)(paths.size()-1);
                    currPos[1] = depth;
                }
//                System.out.println(currPos);
            } else {
                nextPos = doRecursive(paths, map, sign, currPoint, paths.size()-1, (char)(depth+1));
                if ((nextPos[1] <= currPos[1] && currPos[1] != 0x00 && nextPos[1] != 0x00) || (currPos[1] == 0x00 && nextPos[1] != 0x00)) {
                    currPos[0] = nextPos[0];
                    currPos[1] = nextPos[1];
                }
            }
        }
        
        // up
        if (y-1 >= 0 && Integer.parseInt(String.valueOf(map[y-1][x])) > 0 && (sign[y-1][x] > depth || sign[y-1][x] == 0x00)) {
            ArrayList<Pair<String, String>> currPath = (ArrayList<Pair<String, String>>) paths.get(n).clone();
            
            Pair<String, String> currPoint = new Pair<String, String>(String.valueOf(x),String.valueOf(y-1));
            currPath.add(currPoint);
            
            paths.add(currPath);
            
            sign[y-1][x] = depth;
            
            if (Integer.parseInt(String.valueOf(map[y-1][x])) == 3) {
                if ((currPos[1] != 0 && depth < currPos[1]) || currPos[1] == 0x00) {
                    currPos[0] = (char)(paths.size()-1);
                    currPos[1] = depth;
                }
//                System.out.println((int)currPos[0] + " " + (int)currPos[1]);
            } else {
                nextPos = doRecursive(paths, map, sign, currPoint, paths.size()-1, (char)(depth+1));
                if ((nextPos[1] <= currPos[1] && currPos[1] != 0x00 && nextPos[1] != 0x00) || (currPos[1] == 0x00 && nextPos[1] != 0x00)) {
                    currPos[0] = nextPos[0];
                    currPos[1] = nextPos[1];
                }
            }
        }
        
        // down
        if (y+1 <= 11 && Integer.parseInt(String.valueOf(map[y+1][x])) > 0 && (sign[y+1][x] > depth || sign[y+1][x] == 0x00)) {
            ArrayList<Pair<String, String>> currPath = (ArrayList<Pair<String, String>>) paths.get(n).clone();
            
            Pair<String, String> currPoint = new Pair<String, String>(String.valueOf(x),String.valueOf(y+1));
            currPath.add(currPoint);
            
            paths.add(currPath);
            
            sign[y+1][x] = depth;
            
            if (Integer.parseInt(String.valueOf(map[y+1][x])) == 3) {
                if ((currPos[1] != 0 && depth < currPos[1]) || currPos[1] == 0x00) {
                    currPos[0] = (char)(paths.size()-1);
                    currPos[1] = depth;
                }
//                System.out.println(currPos);
            } else {
                nextPos = doRecursive(paths, map, sign, currPoint, paths.size()-1, (char)(depth+1));
                if ((nextPos[1] <= currPos[1] && currPos[1] != 0x00 && nextPos[1] != 0x00) || (currPos[1] == 0x00 && nextPos[1] != 0x00)) {
                    currPos[0] = nextPos[0];
                    currPos[1] = nextPos[1];
                }
            }
        }
        
//        System.out.println((int)currPos[0]);
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
        
        int stateY = 11;
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
        
        for (int i=0; i<12; i++) {
            for (int j=0; j<12; j++) {
                System.out.print(map[i][j] + " ");    
            }
            System.out.println("");
        }
        
        System.out.println("");
        
//        for (int i=0; i<12; i++) {
//            for (int j=0; j<12; j++) {
//                System.out.print((int)sign[i][j] + " ");    
//            }
//            System.out.println("");
//        }
        
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
