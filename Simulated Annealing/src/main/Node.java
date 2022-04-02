package main;

import java.util.ArrayList;
import java.util.Random;

public class Node implements Comparable<Node> {
    private static final int N=8; //8 queens
    public Queen[] state; //the node's state
    private ArrayList<Node> neighbours;
    private int hn; //heuristic score

    public Node() {
        state = new Queen[N]; //empty state
        neighbours = new ArrayList<Node>(); //empty neighbour list
    }

    public Node(Node n) {
        state = new Queen[N];
        neighbours = new ArrayList<Node>();
        for(int i = 0; i < N; i++)
            state[i] = new Queen(n.state[i].getRow(), n.state[i].getColumn());
        hn = 0;
    }

    public ArrayList<Node> generateNeighbours(Node startState) {
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 1; j < N; j++){
                neighbours.add(count, new Node(startState));
                neighbours.get(count).state[i].moveDown(j);
                //make sure to compute its hn value
                neighbours.get(count).computeHeuristic();
                count++;
            }
        }
        return neighbours;
    }

    public Node getRandomNeighbour(Node startState) {
        Random gen = new Random();
        int col = gen.nextInt(N);
        int d = gen.nextInt(N-1)+1;
        Node neighbour = new Node(startState);
        neighbour.state[col].moveDown(d);
        neighbour.computeHeuristic();
        return neighbour;
    }

    public int computeHeuristic() {
        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N; j++) {
                if(state[i].canAttack(state[j])) {
                    hn++;
                }
            }
        }
        return hn;
    }

    //compares based on heuristic values
    public int compareTo(Node n) {
        if (this.hn < n.hn) {
            return -1;
        }

        if (this.hn > n.hn) {
            return 1;
        }

        return 0;
    }

    public void setState(Queen[] s) {
        for (int i=0; i<N; i++) {
            state[i]= new Queen(s[i].getRow(), s[i].getColumn());
        }
    }

    public Queen[] getState() {
        return state;
    }

    public int getHn() {
        return this.hn;
    }

    public String toString() {
        String result = "";
        String[][] board = new String[N][N];

        // empty spaces
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                board[i][j] = "* ";
            }
        }

        //place the queens on the board
        for (int i = 0; i < N; i++) {
            board[state[i].getRow()][state[i].getColumn()] = "Q ";
        }

        //print values
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                result+=board[i][j];
            }
            result += "\n";
        }

        return result;
    }
}
