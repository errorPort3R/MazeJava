package com.theironyard.javawithclojure.jhporter;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Main
{
    static final int SIZE = 10;
    static int startIterator = 0;
    static int endIterator = 0;

    static Room [] [] createRooms()
    {
        Room [] [] rooms = new Room[SIZE] [SIZE];
        for (Room[] row :rooms)
        {
            System.out.print(" _̲");
        }
        System.out.println();
        for(int row = 0;row<SIZE; row++)
        {
            for (int col = 0; col < SIZE; col++)
            {
                rooms[row] [col] = new Room(row,col);
            }
        }
        return rooms;
    }

    static ArrayList<Room> possibleNeighbors(Room[][] rooms, int row, int col)
    {
        ArrayList<Room> neighbors = new ArrayList<>();

        if (row>0)
        {
            neighbors.add(rooms[row-1][col]);
        }
        if (row<(SIZE -1))
        {
            neighbors.add(rooms[row+1][col]);
        }

        if (col>0)
        {
            neighbors.add(rooms[row][col-1]);
        }
        if (col<(SIZE-1))
        {
            neighbors.add(rooms[row][col+1]);
        }

        neighbors = neighbors.stream()
                .filter(room -> !room.wasVisited)
                .collect(Collectors.toCollection(ArrayList<Room>::new));

        return neighbors;
    }

    static Room randomNeighbor(Room[][] rooms, int row, int col)
    {
        ArrayList<Room> neighbors = possibleNeighbors(rooms, row, col);
        if  (neighbors.size()>0)
        {
            Random r = new Random();
            int index = r.nextInt(neighbors.size());
            return neighbors.get(index);
        }
        return null;
    }

    static void tearDownWall(Room current, Room next)
    {
        //going up
        if(next.row < current.row)
        {
            next.hasBottom = false;
        }
        //going down
        else if(next.row > current.row)
        {
            current.hasBottom = false;
        }
        //going left
        else if(next.col < current.col)
        {
            next.hasRight = false;
        }
        //going right
        else if(next.col > current.col)
        {
            current.hasRight = false;
        }
    }

    static boolean createMaze(Room [][] rooms, Room room)
    {
        if (startIterator==0)
        {
            room.isStartRoom = true;
        }
        room.wasVisited = true;
        startIterator++;
        Room nextRoom = randomNeighbor(rooms, room.row, room.col);
        if (nextRoom == null)
        {
            if (endIterator==0)
            {
                room.isEndRoom = true;
            }
            endIterator++;
            return false;

        }
        tearDownWall(room, nextRoom);
        while (createMaze(rooms, nextRoom));
        return true;

    }

    public static void main(String[] args)
    {
        Room [] [] rooms = createRooms();
        createMaze(rooms, rooms[0][0]);
        for(Room[] row :rooms)
        {
            System.out.print("|");
            for(Room room :row)
           {
               if(room.isEndRoom&&room.hasBottom)
               {
                   System.out.print(room.hasBottom ? "x̲" : " ");
                   System.out.print(room.hasRight ? "|" : " ");
               }
               else if(room.isEndRoom&&room.hasRight)
               {
                   System.out.print(room.hasRight ? "x|" : " ");
               }
               else if (room.isStartRoom&&room.hasBottom)
               {
                   System.out.print(room.hasBottom ? "o̲" : " ");
                   System.out.print(room.hasRight ? "|" : " ");
               }
               else if (room.isStartRoom&&room.hasRight)
               {
                   System.out.print(room.hasRight ? "o|" : " ");
               }
               else
               {
                   System.out.print(room.hasBottom ? "_̲" : " ");
                   System.out.print(room.hasRight ? "|" : " ");
               }
           }
            System.out.println();
        }
    }
}
