package gameoflifeboard;

import java.util.Scanner;

public class GameOfLifeBoard {
    
    private final int numRowsAndColumns;
    private final char[][] board;
    private final char[][] nextBoard;
    public static final char LIVE = 'X';
    public static final char DEAD = 'O';

    Scanner kb = new Scanner(System.in);

    
        //creates a board of a desired size
        //initializes initial board state
        //initializes nextBoard to empty board of the same size
    public GameOfLifeBoard(int dimensions){
	
        board = new char[dimensions][dimensions];
        nextBoard = new char[dimensions][dimensions];
        
        numRowsAndColumns = dimensions;
        
            //creates blank board with rows and colums equal to dimensions
        for(int i = 0; i < dimensions; i++){
            for(int j = 0; j < dimensions; j++){
                board[i][j] = DEAD;
            }
        }
        
            //creates blank nextBoard with rows and columns equal to dimensions
        for(int i = 0; i < dimensions; i++){
            for(int j = 0; j < dimensions; j++){
                nextBoard[i][j] = DEAD;
            }
        }
        
    }

        //alows the user to create cells that are alive at the start of the game
    public void initialAlive(){

        int row = 0;
        int column = 0;
        
        System.out.println("Enter the indices of the initially alive cells:");
        
        try{
            while(row != -1){
                System.out.println("\nEnter the row of the cell that is alive, or -1 to stop inputting indices>");
                row = kb.nextInt();
                System.out.println("Enter the column of the cell that is alive, or -1 to stop inputting indices>");
                column = kb.nextInt();

                this.board[row-1][column-1] = LIVE;
            }
        } catch (IndexOutOfBoundsException e){}
    }
    
        //
    public void generateNextStep(){
        
        for(int i = 0; i < numRowsAndColumns; i++){
            for(int j = 0; j < numRowsAndColumns; j++){
                int numNeighbors = getNeighborCount(i,j);
                
                if(this.board[i][j] == LIVE){
                    if(numNeighbors < 2){
                        this.nextBoard[i][j] = DEAD;
                    }else if(numNeighbors > 3){
                        this.nextBoard[i][j] = DEAD;
                    }else{
                        this.nextBoard[i][j] = LIVE;
                    }  
                }else{
                    if(numNeighbors == 3){
                        this.nextBoard[i][j] = LIVE;
                    }else{
                        this.nextBoard[i][j] = DEAD;
                    }
                }
          
            }
        }
        
        for(int i = 0; i < numRowsAndColumns; i++){
            for(int j = 0; j < numRowsAndColumns; j++){
                this.board[i][j] = this.nextBoard[i][j];
            }
        }
    }
    
        //returns the neighbors that an individual cell has
    public int getNeighborCount(int row, int col){
	int numNeighbors = 0;
	
        try{
            if(board[row-1][col-1] == LIVE){
                numNeighbors++;
            }        
        } catch(IndexOutOfBoundsException e){}
        
        try{
            if(board[row-1][col] == LIVE){
                numNeighbors++;
            }        
        } catch(IndexOutOfBoundsException e){}
        
        try{
            if(board[row-1][col+1] == LIVE){
                numNeighbors++;
            }        
        } catch(IndexOutOfBoundsException e){}
        
        try{
            if(board[row][col-1] == LIVE){
                numNeighbors++;
            }        
        } catch(IndexOutOfBoundsException e){}
        
        try{
            if(board[row][col+1] == LIVE){
                numNeighbors++;
            }        
        } catch(IndexOutOfBoundsException e){}
        
        try{
            if(board[row+1][col-1] == LIVE){
                numNeighbors++;
            }        
        } catch(IndexOutOfBoundsException e){}
        
        try{
            if(board[row+1][col] == LIVE){
                numNeighbors++;
            }        
        } catch(IndexOutOfBoundsException e){}
        
        try{
            if(board[row+1][col+1] == LIVE){
                numNeighbors++;
            }        
        } catch(IndexOutOfBoundsException e){}
        
	return numNeighbors;
    }

        //prints formated game board
    public void printBoard(){
	
        for(int i = 0; i < numRowsAndColumns; i++){
            for(int j = 0; j < numRowsAndColumns; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        
    }
	

    public static void main(String[] args) {
        
        String next = "y";
        
        Scanner kb2 = new Scanner(System.in);
        
        System.out.println("Welcome to Conway's Game of Life!");
        
        GameOfLifeBoard game1 = new GameOfLifeBoard(10);    //creates a game board of given size
        
        game1.initialAlive();
        game1.printBoard();
        
        while(next.equalsIgnoreCase("y")){
            System.out.println("Keep going? Enter 'Y' or 'N' ");
            next = kb2.next();
            
            game1.generateNextStep();
            game1.printBoard();
        }
        
    }
    
}
