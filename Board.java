/*These are few packages we import as part of our program
* as we are using ArrayList we import the ArrayList from utility package.
*/
import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import javafx.scene.layout.Pane; 	//Pane is used to display the grid and all the grids are added to the Pane
import javafx.scene.paint.Color;	//To set the color of the grid of the life cell.
import javafx.scene.shape.Rectangle;//Importing Rectangle from the Javafx packages


/**
 * <p> Title: Board. </p>
 * 
 * <p> Description: A component of the Conveys Game application </p>
 * 
 * <p> Copyright: Sujitha J © 2020 </p>
 * 
 * @author Sujitha J
 * 
 * @version 0.01	2020-12-22	Initial baseline where created only dummy methods
 * @version 0.02	2020-12-23	Generated create and Generate methods with arrays
 * @version 0.03	2020-08-24	Generated generateBoard with ArrayList
 * @version 0.04	2020-08-    Yet to be developed with graphics
 * 
 */
/**********
	 * This class defines the boolean board with 2d-array with given dimensions
	 * In the constructor will be passing an ArrayList of live cells in the board represents with  
	 * String "*" .we create a board and calculate the count of live cells around it.
	 * For a live Cell "*" to be alive in the next generation it should have either 2 or 3
     *  live cells around it 
	 * If the count of livecells around the livecell is less than 2 it will die
       with under population condition.
     * If the count of livecells around the livecell is greater than 3 it will die with
       over population condition.
     * For a dead cell which is represented as "." in the board will be alive if the livecells
       around it is exactly 3.
     * we will print the board with the help of toString method.
     * once generating the nextgeneration board,it will pass the  nextgeneration board 
       as the input for the upcoming generation board.
     * 
	 * 
	 * 
	 */


public class Board {
    public boolean[][] board;
    public List<Cell> liveCells;
    public int dimensions;
    public Scanner scan;
    public Scanner scanner_Line;
    /*****
     * @param ArrayList used an list so that no need to specify the length of the array 
     * and we are storing the Cell 
     * @param liveCells
     * @param dimensions
     */
    public Board(ArrayList<Cell> liveCells,int dimensions)
    {   this.dimensions=dimensions;
        this.board=new boolean[this.dimensions][this.dimensions];
        this.liveCells=liveCells;
    }
    /**
     * 
     * @param file is the name ofthe file.
     * @param dimensions are the board dimensions
     */
    public Board(String file,int dimensions)
    {
    	this.dimensions=dimensions;
    	this.board=new boolean[this.dimensions][this.dimensions];
    	int firstValue=0; //To store the first value from the file
    	int secondValue=0;//To store the second value from the file
    	
    	this.liveCells=new ArrayList<Cell>();
    	try {
    	scan = new Scanner(new File(file));//opening the scanner to read the file with the file name
    	while (scan.hasNextLine())
    	{
    		String inputLine = scan.nextLine().trim();
    		scanner_Line = new Scanner(inputLine);
    		if (scanner_Line.hasNextInt()) {
				firstValue = scanner_Line.nextInt();//Scanning the first value
    		if (scanner_Line.hasNextInt()) //Scanning the second value
				secondValue = scanner_Line.nextInt();//storing in  the seccond value
    		}
    		
    		
    		this.liveCells.add(new Cell(firstValue,secondValue));//Creating the livecells from the file and adding it to the list.
    		
    		
    		}
    	}catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	System.out.print(liveCells);
    	
    }
    /**
     * Printing the board in the console.
     */
    public String toString()
    {
        String s="";
        for(int i=0;i<dimensions;i++)
        {
            for(int j=0;j<dimensions;j++)
            {
                if(board[i][j])
                {
                    s+="*";
                }else{
                    s+=".";
                }
            }s+="\n";
        }s.strip();
        return s;
    }
    /***
     * 
     * @param window passing the window in to the arguments 
     * displaying it on the screen.
     */
    public void fillgraphics(Pane window)
    {
    	for(int i=0;i<board.length;i++)
    	{
    		for(int j=0;j<board.length;j++)
    		{
    			if(board[i][j])
    			{
    				   // create a rectangle 
    		        Rectangle rectangle = new Rectangle(i*6, j*6, 6,6); //i and j represents the position of the rectangle on the board
    		  
    		        // set fill for rectangle 
    		        rectangle.setFill(Color.RED); //fills the rectangle with red color in the live cells
    		        window.getChildren().add(rectangle);//adding it to the Pane.
    			}
    		}
    	}
    }
/**
 * Creating the board with livecells from the constructor
 */
    public void createBoard()
    {
    	
        for(int i=0;i<this.liveCells.size();i++)
            board[liveCells.get(i).getRow()][liveCells.get(i).getColumn()]=true;
       
    }
    /**
     * Creating the next generation with the live cells from the current generation
     * calculating the live cells around it .If the live cell have more than 3 live cells around it will die due to over population
     * if the live cell has less than 2 live cells around it will die due to under population
     * if the dead cell has exactly three alive cells around it .It will be alive in the next generation
     * and the live cells will be alive in the next generation only  if it has 2 0r 3 live cells around it.
     */

    public void generateBoard()
    {
        int count=0;
        boolean[][] nextboard=new boolean[this.dimensions][this.dimensions];
        List<Cell> nextCells=new ArrayList<Cell>();
        try {
            
            for(int i=0;i<liveCells.size();i++)
                {
                    count=liveCountAround(this.liveCells.get(i).getRow(),this.liveCells.get(i).getColumn());
                    if(count<2 || count>3)
                        nextboard[this.liveCells.get(i).getRow()][this.liveCells.get(i).getColumn()]=false;
                    else if(count==2 || count==3)
                    {
                        nextboard[this.liveCells.get(i).getRow()][this.liveCells.get(i).getColumn()]=true;
                        nextCells.add(new Cell(this.liveCells.get(i).getRow(),this.liveCells.get(i).getColumn()));
                    }
                    for(int k=-1;k<=1;k++)
                        for(int j=-1;j<=1;j++)
                            if(!board[this.liveCells.get(i).getRow()+k][this.liveCells.get(i).getColumn()+j])
                            {
                                count=liveCountAround(this.liveCells.get(i).getRow()+k,this.liveCells.get(i).getColumn()+j);
                                if(count==3)
                                {
                                nextboard[this.liveCells.get(i).getRow()+k][this.liveCells.get(i).getColumn()+j]=true;
                                nextCells.add(new Cell(this.liveCells.get(i).getRow()+k,this.liveCells.get(i).getColumn()+j));//when the dead cells have 3 alive cells around it will be alive int he next generation and stored in the next generaion livecells.
                                }
                            }    
                }
            } catch (Exception e) 
            {
            
            }finally{
                this.board=nextboard;
                this.liveCells=nextCells;
            }
    }
    
/**
 * 
 * @param row indicates the row of the cell
 * @param column indicatees the column of the cell.
 * @return the count of the live cells around a cell
 */
    public int liveCountAround(int row,int column)
    {
        int count=0;
        try{
        
        for(int k=-1;k<=1;k++)
            for(int j=-1;j<=1;j++)
                if(j==0 && k==0)
                {}else{
                	if(row+k>=0 && row+k<=dimensions && column+j>=0 && column+j<=dimensions)
                    if(board[row+k][column+j])
                    count++;
                }
        
    }catch(Exception e)
    {}finally{return count;}
}
   
    
}
