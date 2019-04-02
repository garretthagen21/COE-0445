

public class PrimQueue1 <T> implements Moves,SimpleQueue{
	
	
	//private Object [] arrayQ1;
	private int numMoves;
	private int logicalSize;
	private T[] arrayQ1;
	
	public PrimQueue1(int size) {
		@SuppressWarnings("unchecked")
		T [] temp = (T[])new Object[size];
		arrayQ1 = temp;
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
	public boolean offer(Object element) { // or add() or enqueue()			//add to beginning
	// add a new element at the logical end of the Queue
	// return true if add is successful and false otherwise
		
		//logicalSize++;
		checkResize();
		
		try {
			
			for(int i=logicalSize-1; i > -1;i--) {
				arrayQ1[i+1] = arrayQ1[i];		//shift right
				//System.out.println(arrayQ1[i]);	
				numMoves++;
			}
			arrayQ1[0] = (T)element;		//add at beginning
			numMoves++;
			logicalSize++;
			return true;
		}
		catch(Exception e) {
			
		}
		
		
		return false;
	}

	public T poll() { // or remove() or dequeue()
		// remove the element at the logical front of the Queue
		// return the element or null if the Queue is empty
	
		
		if(!isEmpty()) {
		  T tempVal = arrayQ1[logicalSize-1];//remove from end
		  //System.out.println("removing" + tempVal);
		  arrayQ1[logicalSize-1] = null;			
		  logicalSize--;						//decrease logical size
		  numMoves++;
		  return tempVal;
		}
		return null;
			
		
	}
	public T peek() { // or getFront()
		// get and return element at logical front of the Queue
		// do not remove the element
		// return null if Queue is empty
		if(!isEmpty()) {
			return arrayQ1[0];
		}
		return null;
		
		
	}
	@SuppressWarnings("unchecked")
	public void checkResize(){
		
		if(arrayQ1.length == logicalSize) {
			
			T [] tempArray = (T[])new Object[(2*arrayQ1.length)];
			
			for(int i=0; i<logicalSize;i++) {
				tempArray[i] = arrayQ1[i];
			}
			arrayQ1=tempArray;
		}
		
		
		
	}


	public boolean isEmpty() {
		// return true if Queue is empty; false otherwise
		if(logicalSize==0) {
			return true;
		}
		return false;
		
	}
	public void clear() {
		// clear all contents from Queue and set to empty
		for(int i=0;i<arrayQ1.length;i++) {
			arrayQ1[i]=null;
		}
		logicalSize=0;
		
		
	}



	
	
}