package com.theironyard.javawithclojure.jhporter;

/**
 * Created by jeffryporter on 7/13/16.
 */
public class Room
{
    int row;
    int col;
    boolean wasVisited = false;
    boolean hasBottom = true;
    boolean hasRight = true;
    boolean isStartRoom = false;
    boolean isEndRoom = false;

    public Room(int row, int col)
    {
        this.row = row;
        this.col = col;
    }


}
