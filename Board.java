import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class Board {
    public boolean[][] board;
    public List<Cell> liveCells;
    public int dimensions;
    public Scanner scan;
    public Scanner scanner_Line;
    
    public Board(ArrayList<Cell> liveCells,int dimensions)
    {   this.dimensions=dimensions;
        this.board=new boolean[this.dimensions][this.dimensions];
        this.liveCells=liveCells;
    }
    public Board(String f,int dimensions)
    {
    	this.dimensions=dimensions;
    	this.board=new boolean[this.dimensions][this.dimensions];
    	int firstValue=0;
    	int secondValue=0;
    	
    	this.liveCells=new ArrayList<Cell>();
    	try {
    	scan = new Scanner(new File(f));
    	while (scan.hasNextLine())
    	{
    		String inputLine = scan.nextLine().trim();
    		scanner_Line = new Scanner(inputLine);
    		if (scanner_Line.hasNextInt()) {
				firstValue = scanner_Line.nextInt();
    		if (scanner_Line.hasNextInt()) 
				secondValue = scanner_Line.nextInt();
    		}
    		
    		
    		this.liveCells.add(new Cell(firstValue,secondValue));
    		
    		
    		}
    	}catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	System.out.print(liveCells);
    	
    }
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
    public void fillgraphics(Pane window)
    {
    	for(int i=0;i<board.length;i++)
    	{
    		for(int j=0;j<board.length;j++)
    		{
    			if(board[i][j])
    			{
    				   // create a rectangle 
    		        Rectangle rectangle = new Rectangle(i*6, j*6, 6,6); 
    		  
    		        // set fill for rectangle 
    		        rectangle.setFill(Color.RED); 
    		        window.getChildren().add(rectangle);
    			}
    		}
    	}
    }

    public void createBoard()
    {
    	
        for(int i=0;i<this.liveCells.size();i++)
            board[liveCells.get(i).getRow()][liveCells.get(i).getColumn()]=true;
       
    }

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
                                nextCells.add(new Cell(this.liveCells.get(i).getRow()+k,this.liveCells.get(i).getColumn()+j));
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

    public int liveCountAround(int row,int column)
    {
        int count=0;
        try{
        
        for(int k=-1;k<=1;k++)
            for(int j=-1;j<=1;j++)
                if(j==0 && k==0)
                {}else{
                    if(board[row+k][column+j])
                    count++;
                }
        
    }catch(Exception e)
    {}finally{return count;}
}
   
    
}
