//Harry Joshi
//June, 21, 2023
//Connect 4
//Creates a playable two player Connect 4 game

//imports nedded packages
import javax.swing.JLabel; 

public class Chip extends JLabel {
  
  //constructor (does not do anything though)
  public Chip() {  
  }

  //takes in the original chip label passed in from the MyFrame GUI class, and basically clones it, so that you can add it to a new slot on the GUI board
  public Chip(Chip oldChip) {  
    setIcon(oldChip.getIcon());
  }
}