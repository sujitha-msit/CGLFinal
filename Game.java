import java.util.ArrayList;

public class Game {
    public Board b;
    public ArrayList<Cell> liveCells;
    public int dimensions;
    public Game(ArrayList<Cell> liveCells,int dimensions)
    {
        this.liveCells=liveCells;
        this.dimensions=dimensions;
        b=new Board(this.liveCells,this.dimensions);
    }
    public void startGame()
    {
      
        System.out.println("************This is the current Board*********************");
        b.createBoard();
        System.out.println(b);
        int i=1;
        while(i<4)
        {
            System.out.println("************This is the Generation "+i+"*********************");
            b.generateBoard();
            i++;
            System.out.println(b);
        }

    }
    
}
