
/*PS1 Java Innovative Exam 
Following program is submitted by-
Roll no 14 Ishita Pawar
Roll no 17 Shaivi Puranik
Roll no 20 Anushka Rao*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Sia2 implements ActionListener 
{
	JFrame frame = new JFrame("Memory Game");
	
	JPanel field = new JPanel();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();

	JPanel start_screen = new JPanel();
	JPanel end_screen = new JPanel();
	JPanel instruct_screen = new JPanel();
	JPanel about_screen = new JPanel();
	
	JButton card[] = new JButton[20]; //game buttons

	JButton start = new JButton("Start");
    	JButton over = new JButton("Exit");

    	JButton easy = new JButton("Easy");
    	JButton hard = new JButton("Hard");

    	JButton inst = new JButton("Instructions");
    	JButton redo = new JButton("Play Again");
    	JButton goBack = new JButton("Main Menu");
	JButton aboutUs = new JButton("About us");
    
    	Random randomGenerator = new Random();
    	private boolean incorrect = false;
	JLabel winner;
	Boolean game_over = false;
	int level=0;
	int score=0;
	
	String[] board;
	Boolean shown = true; //start of the game
	int temp=30;
	int temp2=30;
	String a[]=new String[10];
	boolean diff=true; //true is easy and false is hard
	
	private JLabel label = new JLabel("Enter number of cards from 1 to 10");
	private JTextField text = new JTextField(10);
	private JTextArea instructM = new JTextArea("Instructions ->\nWhen the game begins, the screen will be filled\nwith pairs of buttons.\nMemorize their placement.\nOnce you press any button, they will all clear.\n Your goal is to click the matching pair of buttons.\nWhen you finish that, you win.\nEvery correct match gives you 10 points and\nincorrect deducts 5 points.\n\n GOOD LUCK! :) \n");
	private JLabel aboutinfo1 = new JLabel("Game Developed by -> ** SIA **");	
	private JLabel aboutinfo2 = new JLabel("* Shaivi * Ishita * Anushka *");	

	
	public Sia2()
	{
		frame.setSize(500,300);
		frame.setLocation(500,300);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start_screen.setLayout(new BorderLayout());
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p4.setLayout(new FlowLayout(FlowLayout.CENTER));

		start_screen.add(p1, BorderLayout.NORTH);
		start_screen.add(p3, BorderLayout.CENTER);
		start_screen.add(p2, BorderLayout.SOUTH);
		p3.add(p4, BorderLayout.CENTER);
		p1.add(label);
		p1.add(text);

		p4.add(easy, BorderLayout.NORTH);
		p4.add(hard, BorderLayout.NORTH);
		p4.add(inst, BorderLayout.CENTER);
			
		start.addActionListener(this);
		start.setEnabled(true);
		p2.add(start);
		over.addActionListener(this);
		over.setEnabled(true);
		p2.add(over);
		p2.add(aboutUs);
		p2.setBackground(Color.MAGENTA);
		p4.setBackground(Color.ORANGE);
		easy.addActionListener(this);
		easy.setEnabled(true);
		hard.addActionListener(this);
		hard.setEnabled(true);
		inst.addActionListener(this);
		inst.setEnabled(true);
		aboutUs.addActionListener(this);
		aboutUs.setEnabled(true);
		
		frame.add(start_screen, BorderLayout.CENTER);
		frame.setVisible(true);

	}//constructor Ends



	public void setUpGame(int x,Boolean what)
	{
		level=x;
		clearMain();
		
		board = new String[2*x];
		for(int i=0;i<(x*2);i++)
		{
			card[i] = new JButton("");
			card[i].setBackground(new Color(220, 220, 220));
			card[i].addActionListener(this);
			card[i].setEnabled(true);
			field.add(card[i]);
		
		}
		
		String[] b ={":)", "B)" ,"(*.*)","(o.o)", "uwu", "~(*o*)~", "(^_^)" , ":P" , "XD", "[0.0]"};//hard level
		String[] c = {"111","222","333","444","555","666","777","888","999","000"};//easy level
		
		if(what) 
			a=c;   //if what is true, make the game easy and use c
		else 
			a=b;      //otherwise make it hard and use b
			
		for(int i=0;i<x;i++)
		{
			for(int z=0;z<2;z++)
			{
				while(true)
				{	
					int y = randomGenerator.nextInt(x*2);
					if(board[y]==null)
					{
						card[y].setText(a[i]);
						board[y]=a[i];
						break;
					}
				}
			}
					
		}

		createBoard();
		
	}//SetUpGame end


	public void hideAllCards(int x)      //this sets all the boxes blank
	{           
		for(int i=0;i<(x*2);i++)
		{
		 	card[i].setText("??");		
		}
		shown=false;
	}


	public void flipCard(int i)
	{	//this will switch the current spot to either blank or what it should have
		if(board[i]!="done")
		{	
			//when a match is correctly chosen, it will no longer switch when pressed
			if(card[i].getText()=="??")
			{
				card[i].setText(board[i]);
				
			}
			else
			{
			card[i].setText("??");
			
			}
		}
	}


	void waitABit()
	{	//this was an attempt at fixing the glitch
		try{
			Thread.sleep(5);
		} catch(Exception e){	
		}
	}

	public boolean checkWin()
	{	//checks if every spot is labeled as done
		for(int i=0;i<(level*2);i++)
		{
			if (board[i]!="done")return false;
		}
		winner();
		return true;
	}

	public void winner()
	{
		
			start_screen.remove(field);
			start_screen.add(end_screen, BorderLayout.CENTER);
			end_screen.add(new Label("You Win!!"), BorderLayout.NORTH);
			end_screen.add(new TextField("Score: " + score), BorderLayout.SOUTH);
			end_screen.add(goBack);
			goBack.setEnabled(true);
			goBack.addActionListener(this);
		
	}


	public void goToMainScreen()
	{
		new Sia2();
	}

	public void createBoard()
	{	//this is just gui stuff to show the board
		field.setLayout(new BorderLayout());
		start_screen.add(field, BorderLayout.CENTER);
		
		field.setLayout(new GridLayout(5,4,2,2));
		field.setBackground(Color.BLUE);
		field.requestFocus();
	}



	public void clearMain()
	{	//clears the main menu so i can add the board or instructions
		start_screen.remove(p1);
		start_screen.remove(p2);
		start_screen.remove(p3);

       		start_screen.revalidate();
       		start_screen.repaint();
	}


	public void actionPerformed(ActionEvent click)
	{
		Object source = click.getSource();
		
			if(incorrect)
			{
				flipCard(temp2);
				flipCard(temp);
				score=score-5; //points deduction
				temp=(level*2);
				temp2=30;
				incorrect=false;
			}


			if(source==start)
			{ 	//start sets level and difficulty and calls method to set up game
				
					String slevel = text.getText();
					if(slevel.isEmpty())
					{
						level=1;
					}
					else
					{
						level = Integer.parseInt(text.getText());
						if(level>10)
						{
							level=10;
						}
					}
			
				setUpGame(level, diff); //level between easy and hard, diff is true or false
			}



		if(source==over)
		{//quits
			System.exit(0);
		}

		if(source==inst)
		{	//this sets the instruction screen
			clearMain();
	        
			start_screen.add(instruct_screen, BorderLayout.NORTH);
			
			JPanel one = new JPanel();
			one.setLayout(new FlowLayout(FlowLayout.CENTER));
			one.setBackground(Color.YELLOW);
			JPanel two = new JPanel();
			two.setLayout(new FlowLayout(FlowLayout.CENTER));

			instruct_screen.setLayout(new BorderLayout());
			instruct_screen.add(one, BorderLayout.NORTH);
			instruct_screen.add(two, BorderLayout.SOUTH);
			
			one.add(instructM);
			two.add(goBack);
			goBack.addActionListener(this);
			goBack.setEnabled(true);
		}

		if(source==aboutUs)
		{	//this sets the about screen
			clearMain();
	        
			start_screen.add(about_screen, BorderLayout.NORTH);
			
			JPanel one = new JPanel();
			one.setLayout(new BorderLayout());
			one.setBackground(Color.PINK);
			JPanel one2 = new JPanel();
			one2.setLayout(new BorderLayout());
			one2.setBackground(Color.YELLOW);
			JPanel two = new JPanel();
			two.setLayout(new FlowLayout(FlowLayout.CENTER));
			about_screen.setLayout(new BorderLayout());
			about_screen.add(one, BorderLayout.NORTH);
			about_screen.add(one2, BorderLayout.CENTER);
			about_screen.add(two, BorderLayout.SOUTH);
			
			one.add(aboutinfo1, BorderLayout.NORTH);
			one2.add(aboutinfo2, BorderLayout.CENTER);
			two.add(goBack);
			goBack.addActionListener(this);
			goBack.setEnabled(true);
		}
		

		if(source==goBack)
		{	
			//back to main screen
		    	frame.dispose();  
		    	goToMainScreen();
		}


		if(source==easy)
		{
			diff=true;
			easy.setForeground(Color.RED);
			hard.setForeground(Color.BLACK);

		}
		else if(source==hard)
		{
			diff=false;
			hard.setForeground(Color.RED);
			easy.setForeground(Color.BLACK);
		}
		
		for(int i =0;i<(level*2);i++) 
		{	
			//gameplay when a button is pressed, points calculation
			if(source==card[i])
			{
				if(shown)
				{
					hideAllCards(level);	//if first time, hides cards
				}
				else
				{	//otherwise play and match the card
					flipCard(i);
					if(temp>=(level*2))
					{
						temp=i;
					}
					else
					{
						if((board[temp]!=board[i])||(temp==i))
						{
							temp2=i;
							incorrect=true; //when pair is not matched
							
						}
						else
						{	//when pair is match
							board[i]="done";
							board[temp]="done";
							score=score+10; //points are added
							checkWin();
							temp=(level*2);
						}
						
					}
				
				}
				
			}
			
		}
		

	}


	public static void main(String[] args) 
	{
		new Sia2();       // Calling the class construtor.
	}
	
}

//END OF PROGRAM