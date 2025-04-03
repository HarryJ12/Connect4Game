//Harry Joshi
//June, 21, 2023
//Connect 4
//Creates a playable two player Connect 4 game

//imports needed packages
import java.io.File;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.util.ArrayList;
import javax.swing.JTextField;  

class MyFrame extends JFrame implements ActionListener {

  //sets up instance variables for all the buttons on the GUI bord
  static JButton btn1; static JButton btn2; static JButton btn3; static JButton btn4; static JButton btn5; static JButton btn6; static JButton btn7;

  //sets up instance variables for all the slots on the GUI bord
  JPanel panel1_1; JPanel panel1_2; JPanel panel1_3; JPanel panel1_4; JPanel panel1_5; JPanel panel1_6; JPanel panel1_7;
  JPanel panel2_1; JPanel panel2_2; JPanel panel2_3; JPanel panel2_4; JPanel panel2_5; JPanel panel2_6; JPanel panel2_7;
  JPanel panel3_1; JPanel panel3_2; JPanel panel3_3; JPanel panel3_4; JPanel panel3_5; JPanel panel3_6; JPanel panel3_7;
  JPanel panel4_1; JPanel panel4_2; JPanel panel4_3; JPanel panel4_4; JPanel panel4_5; JPanel panel4_6; JPanel panel4_7;
  JPanel panel5_1; JPanel panel5_2; JPanel panel5_3; JPanel panel5_4; JPanel panel5_5; JPanel panel5_6; JPanel panel5_7;
  JPanel panel6_1; JPanel panel6_2; JPanel panel6_3; JPanel panel6_4; JPanel panel6_5; JPanel panel6_6; JPanel panel6_7;

  //sets up instance variables for the button group, the static textfield, the 2 different panel lists, font, static chip labels, and the static count (which will control who's turn it is)
  ButtonGroup group;
  static JTextField textField;
  JPanel [] panels;
  static JPanel [][] finalPanelsList;
  static Chip redLabel;
  static Chip blueLabel;
  Font customFont;
  static int count; 

  MyFrame() {
    try {  //uses an imported font package and sets it up for use in the program
      customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font.ttf")).deriveFont(50f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(customFont);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (FontFormatException e) {
      e.printStackTrace();
    } 

    //creates a text field and sets the text (using the custom font) as the first players turn
    textField = new JTextField();
    textField.setPreferredSize(new Dimension(743,81));
    textField.setEditable(false);
    textField.setText("TURN: PLAYER RED");
    textField.setHorizontalAlignment(JTextField.CENTER);
    textField.setFont(customFont);
    this.add(textField);  

    //resizes the red chip image icon and sets it to the red label
    ImageIcon red = new ImageIcon("red.png");
    red = new ImageIcon("red.png"); 
    Image redScaled = red.getImage().getScaledInstance(86, 86,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon redFinal = new ImageIcon(redScaled);
    redLabel = new Chip(); 
    redLabel.setIcon(redFinal);  

    //resizes the blue chip image icon and sets it to the blue label
    ImageIcon blue = new ImageIcon("blue.png");
    blue = new ImageIcon("blue.png"); 
    Image blueScaled = blue.getImage().getScaledInstance(86, 86,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon blueFinal = new ImageIcon(blueScaled);
    blueLabel = new Chip(); 
    blueLabel.setIcon(blueFinal);

    
    JPanel [] panels = { //creates a panels array with all of the panels in it
    panel1_1, panel1_2, panel1_3, panel1_4, panel1_5, panel1_6, panel1_7,
    panel2_1, panel2_2, panel2_3, panel2_4, panel2_5, panel2_6, panel2_7,
    panel3_1, panel3_2, panel3_3, panel3_4, panel3_5, panel3_6, panel3_7,
    panel4_1, panel4_2, panel4_3, panel4_4, panel4_5, panel4_6, panel4_7,
    panel5_1, panel5_2, panel5_3, panel5_4, panel5_5, panel5_6, panel5_7,
    panel6_1, panel6_2, panel6_3, panel6_4, panel6_5, panel6_6, panel6_7
    };

    //goes through the panels array and adds each panel to the JFrame after changing it's characteristics
    for (int i = 0; i < panels.length; i++) {
      panels[i] = new JPanel();
      panels[i].setLayout(new FlowLayout(FlowLayout.CENTER));
      panels[i].setBackground(Color.black);
      panels[i].setPreferredSize(new Dimension(98, 96));
      this.add(panels[i]); 
    }  

    //adds each of the pannels in the panel array to a final panels 2D array
    int panelNum = 0;
    finalPanelsList = new JPanel [6][7];
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        finalPanelsList[i][j] = panels[panelNum];
          panelNum++;
      }
    }

    //creates all of the buttons for the tic tac toe board ad sets their names 
    btn1 = new JButton("Column 1");
    btn2 = new JButton("Column 2");
    btn3 = new JButton("Column 3");
    btn4 = new JButton("Column 4");
    btn5 = new JButton("Column 5");
    btn6 = new JButton("Column 6");
    btn7 = new JButton("Column 7");

    //adds action listener to all of the buttons
    btn1.addActionListener(this);
    btn2.addActionListener(this);
    btn3.addActionListener(this);
    btn4.addActionListener(this);
    btn5.addActionListener(this);
    btn6.addActionListener(this);
    btn7.addActionListener(this);

    //adds all the buttons to a button group
    group = new ButtonGroup();
    group.add(btn1); 
    group.add(btn2);
    group.add(btn3);
    group.add(btn4); 
    group.add(btn5);
    group.add(btn6);
    group.add(btn7);

    //adds all of the buttons to the JFrame
    this.add(btn1);
    this.add(btn2);
    this.add(btn3);
    this.add(btn4);
    this.add(btn5);
    this.add(btn6);
    this.add(btn7);

    //sets the title of the JFrame and changes the characteristics of it
    ImageIcon icon = new ImageIcon("c4.png"); 
    this.setIconImage(icon.getImage()); 
    this.setTitle("Connect 4"); 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    this.getContentPane().setBackground(new Color(66, 148, 242)); 
    this.setSize(765, 794);
    this.setResizable(false);
    this.setUndecorated(false);
    this.setVisible(true);
  }

  //returns the current players turn
  public static String getTurn() {
    if (count%2==0) {
      return "R";
    } else {
      return "B";
    }
  }

  //returns the previous players turn
  public static String getOLDTurn() {
    if (getTurn() == "R") {
       return "B";
    } else {
       return "R";
    }
  }

  //changes the text field to the next person's turn
  public static void changeTurn() {
    count++;
    if (count%2==0)
    textField.setText("TURN: PLAYER RED");
    else {
      textField.setText("TURN: PLAYER BLUE");
    }
  }

  //adds the right colored chip in the GUI slot
  public static void addChip(int row, int column, String color) {
    if (color == "R") {
      finalPanelsList[row][column].add(new Chip(redLabel));
      finalPanelsList[row][column].revalidate(); 
    } else {
      finalPanelsList[row][column].add(new Chip(blueLabel));
      finalPanelsList[row][column].revalidate(); 
    }
  }

  //calls the add piece method and the check winner method in the game (internal board) if any button is clicked 
  public void actionPerformed(ActionEvent e) {  
    if (e.getSource() == btn7) {
      Connect4Game.addPiece(7, getTurn());
      Connect4Game.checkForWinner(7, getOLDTurn());
    }
    
    if (e.getSource() == btn6) {
      Connect4Game.addPiece(6, getTurn());
      Connect4Game.checkForWinner(6, getOLDTurn());
    }

    if (e.getSource() == btn5) {
      Connect4Game.addPiece(5, getTurn());
      Connect4Game.checkForWinner(5, getOLDTurn());
    }

    if (e.getSource() == btn4) {
      Connect4Game.addPiece(4, getTurn());
      Connect4Game.checkForWinner(4, getOLDTurn());
    }

    if (e.getSource() == btn3) {
      Connect4Game.addPiece(3, getTurn());
      Connect4Game.checkForWinner(3, getOLDTurn());
    }

    if (e.getSource() == btn2) {
      Connect4Game.addPiece(2, getTurn());
      Connect4Game.checkForWinner(2, getOLDTurn());
    }

    if (e.getSource() == btn1) {
      Connect4Game.addPiece(1, getTurn());
      Connect4Game.checkForWinner(1, getOLDTurn());
    }
  }

  //if winner is found, this method is called; it displays the winners name and disables all the buttons, ending the game
  public static void winnerFound(String color) {
    String text = "";
    
    if (color == "R") {
      text = "RED";
    }
    if (color == "B") {
      text = "BLUE";
    }
    
    textField.setText("PLAYER " + text + " WON!");
    btn1.setEnabled(false);
    btn2.setEnabled(false);
    btn3.setEnabled(false);
    btn4.setEnabled(false);
    btn5.setEnabled(false);
    btn6.setEnabled(false);
    btn7.setEnabled(false);    
  }

  //if a tie occurs, this method is called; it displays the tie message name and disables all the buttons, ending the game
  public static void tieGame() {
    textField.setText("TIE GAME!");
    btn1.setEnabled(false);
    btn2.setEnabled(false);
    btn3.setEnabled(false);
    btn4.setEnabled(false);
    btn5.setEnabled(false);
    btn6.setEnabled(false);
    btn7.setEnabled(false);    
  }
}