package ch8DynamicProgramming;

/**
 * Created by kevint on 5/1/2016.
 *
 * Problem: Imagine a robot sitting on the upper left corner of grid with r rows
 *          and c columns. The robot can only move in two directions, right and
 *          down, but certain cells are "off limits" such that the robot cannot
 *          step on them. Design an algorithm to find a path for the robot from the
 *          top left to the bottom right.
 *
 * Hint 1: For the robot to reach the last cell, it must find a path to the second
 *         to last cells. For it to find a path to the second-to-last cells, it must
 *         find a path to the third-to-last cells.
 *
 * Hint 2: Simplify this problem a bit by first figuring out if there's a path.
 *         Then modify your algorithm to track that path.
 */
public class RobotGridProblem {
    public static void main(String[] args) {
        boolean[][] maze = {
                {true, true, true},
//                {true, false, true},
                {true, true, true}
        };

        System.out.println(maze.length); // should signify the number of rows
        System.out.println(maze[0].length);
    }
}
