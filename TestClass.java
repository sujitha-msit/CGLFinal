import java.util.ArrayList;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ArrayList<Cell> liveCells=new ArrayList<Cell>();
        System.out.println("Please enter the dimensions of the array");
        int dimensions=scan.nextInt();
       Cell c1=new Cell(2,3);
        liveCells.add(c1);
        liveCells.add(new Cell(3,3));
        liveCells.add(new Cell(3,5));
        liveCells.add(new Cell(4,2));
        liveCells.add(new Cell(1,0));
        System.out.println(liveCells);
        Game game=new Game(liveCells,dimensions);
        
        game.startGame();

    }
}
