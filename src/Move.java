/** 	
*		Class - Move
*		This just stores the current move, it's seperated into a class so that functionality can be added to store wining squares during a check in the model and it can be animated onto the gui
*		@author Garrett Hawes
*/

public class Move{
	private int row;
	private int col;
	
	/** 	
	*		Construct move, 
	*		@param row 							Row for the move
	* 		@param col							Col for the move
	*/
	public Move(int row, int col){
		this.row=row;
		this.col=col;
	}
	
	/** 	
	*		Get the row for this move  
	*		@return								Row for move 
	*/
	public int getRow(){
		return row;
	}
	
	/** 	
	*		Get the column for this move  
	*		@return								Column for move 
	*/
	public int getCol(){
		return col;
	}
	public String toString(){
		return "Row: "+row+" Col: "+col+"\n";
	}
}