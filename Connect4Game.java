//Harry Joshi
//June, 21, 2023
//Connect 4
//Creates a playable two player Connect 4 game

class Connect4Game {
  static String [][] board;  //internal board static instance variable
  
  public static void main(String[] args) {    
    //Outputs game instructions
    System.out.println("\nWelcome to Connect4");
    System.out.println("IMPORTANT: Resize browser window as necessary!");
    System.out.println("\nGrab a friend and start playing, rules are simple.");
    System.out.println("1) First, decide who goes first and what color each player will have.");
    System.out.println("2) Players must alternate turns, and only one disc can be dropped in each turn.");
    System.out.println("3) On your turn, drop one of your colored discs from the top into any of the seven slots using the buttons.");
    System.out.println("4) The game ends when there is a 4-in-a-row or a stalemate.");
    System.out.println("5) The starter of the previous game goes second on the next game (stop & run to play again).");
    System.out.println("\nHAVE FUN!");

    MyFrame myFrame = new MyFrame();  //creates a game board 

    //creates the internal tic tac toe board and sets every slot in it to null
    board = new String[6][7];  
    for (int i = 0; i < board.length; i++) {
      for (int j = 0;j < board[i].length; j++) {
        board[i][j] = null; 
      }
    } 
  }  

  //method to add different colored peices to the internal board depending on the slot column passed in from the button clicker
  public static void addPiece(int column, String color) { 
    int count = 0;
    if (board[0][column-1]==null) {
      for (int row = 5; row >= 0; row--) {
        if (board[row][column-1] == null) {
          MyFrame.addChip(row, column-1, color);
          if (color == "R") {
            board[row][column-1] = "R";
          }
          else {
            board[row][column-1] = "B"; 
          }
          MyFrame.changeTurn();  //changes the turn in GUI MyFrame class
          break;
        }
      }
    }
  } 

  //checks if there is a winner or tie every turn
  public static void checkForWinner(int column, String winningColor) {  
    boolean someoneWon = false;  //boolean to check if someone won
    boolean tieGame = true;  //boolean to check if there is a tie game

    //runs through every possible win condition
    for (int i = 0; i < board.length; i++) {  
      if (board[i][column-1] != null && board[i][column-1] == winningColor) { 

        //downward win check
        int winningStreak = 3;
        for (int winRow = i+1; winRow < board.length; winRow++) { 
          if (board[winRow][column-1] == winningColor) {
            winningStreak--;
            if (winningStreak == 0) {
              someoneWon = true;
            }
          } else {
            winningStreak = 4;
          }
        }

        //horizontal win check
        winningStreak = 4;
        for (int winColumn = (column-1) - 3; winColumn <= (column-1) + 3 ; winColumn++) {
          if (winColumn < 0) 
            continue;
          if (winColumn >= board[i].length)
            break;
          
          if (board[i][winColumn] != null && board[i][winColumn] == winningColor) {
            winningStreak--;
            if (winningStreak == 0) {
              someoneWon = true;
            }
          } else {
            winningStreak = 4;
          }
        }  

        //left diagonal win check
        winningStreak = 4;
        for (int winRow = i - 3, winColumn = (column-1) - 3; winRow <= i + 3 && winColumn <= (column-1) + 3; winRow++, winColumn++) {
          if (winRow < 0 || winColumn < 0)
            continue;
          if (winRow >= board.length || winColumn >= board[i].length)
            break;

          if (board[winRow][winColumn] != null && board[winRow][winColumn] == winningColor) {
            winningStreak--;
            if (winningStreak == 0) {
              someoneWon = true;
            }
          } else {
            winningStreak = 4;
          }
        }

        //right diagonal check
        winningStreak = 4;
        for (int winRow = i - 3, winColumn = (column-1) + 3; winRow <= i + 3 && winColumn >= (column-1) - 3; winRow++, winColumn--) {
          if (winRow < 0 || winColumn >= board[i].length)
            continue;
          if (winRow >= board.length || winColumn < 0)
            break;

          if (board[winRow][winColumn] != null && board[winRow][winColumn] == winningColor) {
            winningStreak--;
            if (winningStreak == 0) {
              someoneWon = true;
            }
          } else {
            winningStreak = 4;
          }
        }        
      }
    }

    //if someone wins, sends this information to MyFrame GUI class
    if (someoneWon == true) {  
      MyFrame.winnerFound(winningColor);  
    }

    //calculates if there is a tie (all the slots are full, and still no winner),
    for (int row = 0; row < board.length; row++) { 
      for (int col = 0; col < board[row].length; col++) { 
        if (board[row][col] == null) {
         tieGame = false;  
        }
      } 
    }
    //sends tie information to MyFrame GUI class
    if (tieGame == true) {
      MyFrame.tieGame(); 
    }
  }
}  