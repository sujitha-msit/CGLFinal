/**
 * <p> Title: Cell. </p>
 * 
 * <p> Description: A component of the Conveys Game application </p>
 * 
 * <p> Copyright: Sujitha J © 2020 </p>
 * 
 * @author Sujitha J
 * 
 * @version 0.01	2020-12-22	Initial baseline where created only dummy methods
 * @version 0.02	2020-12-23	the Cell is intialized with the constructor.and getter and setter methods.
 * @version 0.03	
 * 
 * 
 */
/*************
 * This class has row and column and will be intialized with the help of constructor.
 */
public class Cell {
    private int row;
    private int column;

    public Cell(int row,int column)
    {
        this.row=row;
        this.column=column;
    }
/**
 * 
 * @return the row of the particular cell as the cell is private variable can't access it from other file.
 */
    public int getRow()
    {
        return this.row;
    }
    
    /**
     * 
     * @return the column of the particular cell as the cell is private variable can't access it from the file.
     */
    public int getColumn()
    {
        return this.column;
    }

    
}
