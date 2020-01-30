package sample;

import java.util.Arrays;

public class Board {
    public double percOfMines = .2;
    public int width, height, numMines;
    public String[][] mines;
    Board(){
        width = 10;
        height = 10;
        mines = new String[width][height];
        numMines = (int) (width * height * percOfMines);
        fillBoard();
    }
    Board(int w, int h){
        width = w;
        height = h;
        mines = new String[w][h];
        numMines = (int) (w * h * percOfMines);
        fillBoard();
    }

    void fillBoard(){
        for(int i = 0;i<width;i++){
            Arrays.fill(mines[i], "F");
        }

        //randomize mine locations
        for(int i = 0; i<numMines;i++){
            mines[(int)(Math.random()*width)][(int)(Math.random()*height)] = "T";
        }
    }
}
