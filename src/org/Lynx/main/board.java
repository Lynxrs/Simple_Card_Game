package org.Lynx.main;

import java.util.ArrayList;

public class board {
    public ArrayList<ArrayList<Object>> Board = new ArrayList<>();
    public ArrayList<ArrayList<Object>> getboard(){
        return Board;
    }

    public void display(){
        int i=0;
        int bs = Board.size();
        while(i!=bs){
            Main.print(Board.get(i));
            i++;
        }

    }
    public void Boardgen(ArrayList<ArrayList<Object>> b,int x, int y){
        int i=0;
        int f=0;
        while(i != x){
            b.add(new ArrayList<Object>());
            f=0;
            while(f!=y) {
                b.get(i).add(0);
                f++;
            }
            i++;
        }

    }
}
