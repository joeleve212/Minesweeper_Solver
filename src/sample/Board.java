package sample;

import java.util.Arrays;

public class Board {
    public double percOfMines = .15;
    public int width, height, numMines;
    public String[][] mines;
    Board(){
        width = 10;
        height = 10;
        mines = new String[width][height];
        numMines = (int) (width * height * percOfMines);
        fillBoard();
    }

    void fillBoard(){
        for(int i = 0;i<width;i++){
            Arrays.fill(mines[i], "false");
        }

        //randomize mine locations
        for(int i = 0; i<numMines;i++){
            mines[(int)(Math.random()*width)][(int)(Math.random()*height)] = "true";
        }
    }
}
