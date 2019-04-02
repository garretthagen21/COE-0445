import java.util.*;
//import javax.xml.soap.*;

public class RandIndexQueue<T> implements Indexable<T>,MyQ<T>,Shufflable	{
	
	private T [] linkedArray;	
	private int [] randomArray;
	private int logicalSize;
	private int numMoves;
	private Node firstNode;
	private Node lastNode;
	Random randNum = new Random(); 
	
	private class Node{				//Inner Node class for Linked List
		
		public Node nextNode;
		//public Node previousNode;
		public T dataPortion;
		
	
			
		private Node(T dataPortion,Node nextNode) {
			this.nextNode = nextNode; //first "neighbor to move in" has no one to the right of him
			//this.previousNode = previousNode;
			this.dataPortion = dataPortion;
		}
		
			
	}
	
	
	//ADD AT BACK REMOVE AT FRONT:       (BACK) ADD HERE----->REMOVE HERE (FRONT)
	//LINKED LIST POINTS THIS WAY:        first node-->
	//  *(You move in here = You are now the first address aka Node)* --> (You learn this guys address aka Node)
	
	
	public RandIndexQueue(int actualSize) {	
		@SuppressWarnings("unchecked")
		T [] temp = (T[])new Object[actualSize];
		linkedArray = temp;
		logicalSize = 0;
		firstNode = null;
	}


	
	 public void shuffle() {
		
		 getRandomArray();
		 T newDataOrder [] = (T[]) new Object[logicalSize];
		 
		
		for (int i = 0; i < logicalSize; i++)
		{
			
			newDataOrder[i]=((Node)linkedArray[randomArray[i]]).dataPortion;				//Make a temporary array containing all of the dataPortions in a random order
		   
		}
		
		for(int i = 0; i<logicalSize;i++) {
			((Node)linkedArray[i]).dataPortion = newDataOrder[i];							//Put the rearranged data back into the OG node array
		}
		
		
	}
	 
	 public void getRandomArray() {
		 	
		  	randomArray = new int[logicalSize];
			int randomArrayItems=0;
			int randInt;
			int numCount=0;
			boolean canAdd=true;
			
			randomArray[0]=randNum.nextInt(logicalSize);		//add the first item
			randomArrayItems++;
			
			while(randomArrayItems < randomArray.length) {
				canAdd=true;
				randInt = randNum.nextInt(logicalSize);
				
				for(int j=0;j<randomArrayItems;j++) {		//Make sure there are no duplicates
					if(randomArray[j] == randInt) {
						canAdd = false;
					}
				}
				
				if(canAdd) {
					randomArray[randomArrayItems]=randInt;
					randomArrayItems++;
				}
				
			}
			//System.out.print("Random Array length = "+randomArray.length+" randomItems = "+randomArrayItems+ " Logical Size = "+logicalSize+"\n");
			/*for(int i =0;i<randomArray.length;i++) {
				
				System.out.print("RA: "+randomArray[i]+ " ");				//DEBUG
			}
			System.out.println();*/
			
		 
	 }

	public boolean offer(T item) {
		   checkResize();	//check to see if the array is full
				try {
					Node currentNode = new Node(item,firstNode);		// currentNode is object that is made up of [data + nextNode]
					currentNode.nextNode = firstNode;			   //assign the next node to the the neighbor to the right when newNode moved in
					firstNode = currentNode;	                      //we are now the first node because we moved in most recently
					logicalSize++;
					toArray();  								    //update array
					numMoves++;
					//System.out.println("Logical Size is:" +logicalSize);
					return true;
				}
				catch(Exception e){
					return false;
				}
	}

	@Override
	public T poll() {
		
		if(!isEmpty()) {
			Node tempNode = (Node)linkedArray[logicalSize-1];
			//System.out.println("Logical Size is:" +logicalSize);
			T tempNodeData = tempNode.dataPortion;
			tempNode = null;
			logicalSize--;
			numMoves++;       
			toArray();
			return tempNodeData;
		}
		return null;
	}
	
	public String toString(){
		if(!isEmpty()) {
			StringBuilder arrayString = new StringBuilder();
			arrayString.append("Contents: ");
			for(int i=logicalSize-1;i>-1;i--) {
				Node tempStringNode = (Node)linkedArray[i];
				T tempData = tempStringNode.dataPortion;
				arrayString.append(tempData+" ");
			}
			
			return arrayString.toString();
			
			
		}
		
		
		
		return null;
		
	}

	@Override
	public T peek() {			//TODO Check this 
		
		/*if(!isEmpty()) {
			T tempNodeData = ((Node)linkedArray[logicalSize-1]).dataPortion;
			return tempNodeData;
		}*/
		if(!isEmpty()) {
			T tempNodeData = ((Node)linkedArray[0]).dataPortion;
			return tempNodeData;
		}
		
		return null;
		
	}


	@Override
	public boolean isFull() {
		checkResize();
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(logicalSize==0) {
			return true;
		}
		return false;
	}

	
	public void clear() {
		
		for(int i=0;i<logicalSize;i++) {
			linkedArray[i] = null;
		}
		firstNode = null;
		logicalSize = 0;
	}

	
	public int getMoves() {
		
		return numMoves;
	}

	
	public void setMoves(int moves) {
		
		numMoves = moves;
	}

	
	public T get(int i)throws IndexOutOfBoundsException {
		
		int getNodeIndex = logicalSize - (i+1);		
		T tempNodeData = ((Node)linkedArray[getNodeIndex]).dataPortion;
		return tempNodeData;
		
	}

	
	public void set(int i, T item) throws IndexOutOfBoundsException {
		int setNodeIndex = logicalSize - (i+1);	
		((Node)linkedArray[setNodeIndex]).dataPortion = item;
		toArray();
		
	}

	
	public int size() {
		
		return logicalSize;
	}
	
	
	public void toArray() {						//Turn the linked list into an array
	
		Node indexNode = firstNode;
		int j=0;
		while((indexNode != null) && (j < logicalSize)) {
			linkedArray[j] = (T)indexNode;
			indexNode = indexNode.nextNode;
			 j++;
		}
	}
	
	

	@SuppressWarnings("unchecked")
	public void checkResize(){							//Double the array size
		
		if(linkedArray.length == logicalSize) {
			
			T [] tempArray = (T[])new Object[(2*linkedArray.length)];
			
			for(int i=0; i<logicalSize;i++) {
				tempArray[i] = linkedArray[i];
			}
			linkedArray=tempArray;
		}
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}