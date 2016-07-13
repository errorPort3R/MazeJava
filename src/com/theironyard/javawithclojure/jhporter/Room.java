package com.theironyard.javawithclojure.jhporter;

/**
 * Created by jeffryporter on 7/13/16.
 */
public class Room
{
    int row;
    int col;
    boolean wasvisited = false;
    boolean hasBottom = true;
    boolean hasRight = true;

    public Room(int row, int col)
    {
        this.row = row;
        this.col = col;
    }


}
