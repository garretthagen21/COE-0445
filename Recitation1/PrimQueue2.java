

public class PrimQueue2 <T> implements Moves, SimpleQueue{
	
	
	//private T [] arrayQ2;
	
	
	private int numMoves;
	private int logicalSize;
	private T[] arrayQ2;
	
	public PrimQueue2(int size) {
		@SuppressWarnings("unchecked")
		T [] temp = (T[])new Object[size];
		arrayQ2 = temp;
		numMoves=0;
		logicalSize=0;
	}
	
	
	
	public int getMoves() {
	// return the value of the moves variable
		return numMoves;
	}
	
	
	
	public void setMoves(int val) {
	// initialize the moves variable to val
		numMoves=val;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean offer(Object element) { // or add() or enqueue()				//add to end of array
	// add a new element at the logical end of the Queue
	// return true if add is successful and false otherwise
		
		
		try {
			checkResize();
			arrayQ2[logicalSize] = (T)element; 
			logicalSize++;
			numMoves++;
			return true;
		}
		catch(Exception e) {
			
		}
		
		
		return false;
	}

	public T poll() { // or remove() or dequeue()					//delete 1st item in array;
		// remove the element at the logical front of the Queue
		// return the element or null if the Queue is empty
		if(!isEmpty()) {
		  T tempVal = arrayQ2[0];
		  
		  for(int i=0;i<logicalSize;i++) {
			  arrayQ2[i] = arrayQ2[i+1];
			  numMoves++;
		  }
		  	logicalSize--;
			arrayQ2[logicalSize] = null;
			//numMoves++;
			
		    return tempVal;
		}
		return null;
			
		
	}
	public T peek() { // or getFront()
		// get and return element at logical front of the Queue
		// do not remove the element
		// return null if Queue is empty
		if(!isEmpty()) {					//get last item
			return arrayQ2[0];
		}
		return null;
		
		
	}
	@SuppressWarnings("unchecked")
	public void checkResize(){
		
		if(arrayQ2.length == logicalSize) {
			
			T [] tempArray = (T[])new Object[(2*arrayQ2.length)];
			
			for(int i=0; i<logicalSize;i++) {
				tempArray[i] = arrayQ2[i];
			}
			arrayQ2=tempArray;
		}
		
		
		
	}


	public boolean isEmpty() {							//
		// return true if Queue is empty; false otherwise
		if(logicalSize==0) {
			return true;
		}
		return false;
		
	}
	public void clear() {
		// clear all contents from Queue and set to empty
		for(int i=0;i<arrayQ2.length;i++) {
			arrayQ2[i]=null;
		}
		logicalSize=0;
		
		
	}



	
	
}