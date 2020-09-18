package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Missionaries and Cannibals problem");
        System.out.print("\n==== Search Method: Breadth-First search ====");
        System.out.print("\nStart (y/N) [and press ENTER]: ");

        String optionStr = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            optionStr = in.readLine();
        } catch (IOException e) {
            System.out.println("[ERROR] Fail to read the typed option.");
            e.printStackTrace();
        }

        State initialState = new State (3, 0, 3, 0, Position.LEFT);

        switch(optionStr) {
            case "y":
                executeBFS(initialState);
                break;
            case "N":
                break;
            default:
                System.out.println("[ERROR] Invalid search option.");
        }
    }

    private static void executeBFS(State initialState) {
        BFS search = new BFS();
        State solution = search.exec(initialState);
        printSolution(solution);
    }

    private static void printSolution(State solution) {
        if (null == solution) {
            System.out.print("\nNo solution found.");
        } else {
            System.out.println("\nSolution Format:(CannibalLeft,MissionaryLeft,Boat,CannibalRight,MissionaryRight)");
            System.out.println("\nSolution:");
           // System.out.println("\nSolution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): ");
            List<State> path = new ArrayList<State>();
            State state = solution;
            while (null != state) {
                path.add(state);
                state = state.getParentState();
            }

            int depth = path.size() - 1;
            for (int i = depth; i >= 0; i--) {
                state = path.get(i);
                if (state.isGoal()) {
                    System.out.print(state.toString());
                } else {
                    System.out.print(state.toString() + " -> ");
                }
            }
            System.out.println("\nDepth: " + depth);
        }
    }

}
