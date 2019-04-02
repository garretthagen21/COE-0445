// CS 0445 Spring 2018
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.


	// Create a new MyStringBuilder which contains the contents of the
	// String argument.
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length(); i++)
			{													// P (firstC)      I	       G (lastC)
				CNode newNode = new CNode(s.charAt(i));			//First Node-->Next node-->currNode  
				currNode.next = newNode;							//				    Nodes added here ^
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		
		if(s.length == 0 || s == null) {		//There is no word
			firstC = null;
			lastC = null;
			length = 0;
		}
		else {	
				
			firstC = new CNode(s[0]); 
			length = 1;
			CNode currNode = firstC; //Only one char in the list
			
			for(int i=1; i < s.length; i++) {
				CNode newNode = new CNode(s[i]);	//Add nodes at the  right 
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
		   
			lastC = currNode;		//Wait until we put everything in the list to determine where the last character is
		
		}
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
		lastC = null;
		length = 0;
	
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		
		if (b == null || b.length() == 0) { 					// Special case for empty String builder
			return this;
		}
		else
		{
			CNode currBNode = b.firstC;    					//next node of original stringbuilder is the first node of b next is null
		    CNode currNode = new CNode(currBNode.data);		//make a new node that just contains the data & next is null
		                          							//point the end of the OG linked list to the node we just made
		    connectLists(currNode);
		    currBNode = currBNode.next;  					//Increment to next B node
			
		    while(currBNode != null)
			{	
		    		CNode newNode = new CNode(currBNode.data);		//new Node is made from the data in B         
				currNode.next = newNode;
				currNode = newNode;
				length++;										//Make the length longer everytime we add one
				currBNode = currBNode.next;						//Iterate through linked List
			}
		    this.lastC = currNode;
		}
		return this;
	}
	
	
	
	
	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
			
		
			if (s == null || s.length() == 0) { // Special case for empty String
				return this;
			}
			else
			{
				CNode currNode = new CNode(s.charAt(0));						//next node of original stringbuilder is the first node of b
				connectLists(currNode);	
				lastC = currNode;											
				
				for (int i = 1; i < s.length(); i++)
				{													                              // P (firstC)      I	       G (lastC)
					CNode newNode = new CNode(s.charAt(i));			        //First Node-->Next node-->currNode  
					currNode.next = newNode;
					currNode = newNode;
					length++;
				}
				lastC = currNode;
			}
			return this;
		}
		
	

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		if (c == null || c.length == 0) { // Special case for empty String builder
			return this;
		}
		else
		{
			CNode currNode = new CNode(c[0]);						//next node of original stringbuilder is the first node of b
			connectLists(currNode);									//checks to see if there is anything to add to in the OG list& if there is it bridges the gap
			lastC = currNode;										//See where to connect the list
			
					   								//next node of original stringbuilder is the first node of b
						                           //length is original length + next node
		
			for (int i = 1; i < c.length; i++)
			{													                              // P (firstC)      I	       G (lastC)
				CNode newNode = new CNode(c[i]);			        //First Node-->Next node-->currNode  
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		return this;
	}
		
		
		
	

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
		
		    CNode currNode = new CNode(c);			        //First Node-->Next node-->currNode  
			connectLists(currNode);			//checks to see if there is anything to add to & if there is it bridges the gap
			lastC = currNode;
            return this;
	
	}
	private void connectLists(CNode newNode) {
		if( length > 0 ) {
		  lastC.next = newNode;			//bridges the gap from the end of the old list and the beginning of the new one 
		 }
		
		else {							//the old list is empty and this will be the first node
			firstC = newNode;
		}
	 length++;						//were adding to the list in both cases
	}


	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index) throws IndexOutOfBoundsException
	{
		assert (index<length) && (firstC != null);
		
		CNode currNode = firstC; //Index 0
		
		for(int i = 1; i <= index;i++) {
			currNode = currNode.next;
		}
		
		return currNode.data;
	
	}
	
	private CNode nodeAt(int index) throws IndexOutOfBoundsException			//Just like charAt but returns the entire node
	{
		assert (index<length) && (firstC != null);
		
		CNode currNode = firstC; //Index 0
		
		for(int i = 1; i <= index;i++) {
			currNode = currNode.next;
		}
		
		return currNode;
	
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{
		if(firstC == null || end<=start) {
			return this;
		}
		
		
		CNode  startNode; //need this to link to final node
		CNode endNode;
		
		
		if(end>=length) {
			end = length;
			endNode = null;
			lastC = endNode;
		}
		else {
		    endNode = nodeAt(end);
		}
		
		
	    if(start > 0) {
			startNode = nodeAt(start-1); //go to the link before the first node we want to delete 
			startNode.next = endNode;	//delete everything in between by pointing at our end node
			
		}
		else {		//we are starting at character 0 which means everything up until our end node is deleted
			startNode = endNode;   //delete everything up to our end Node
			firstC = startNode;
		}
		
	    //return cases
		if(isEmpty()) {
			firstC = null;
			lastC = null;
			length = 0;
			return this;
		}
		
		
		length = length - (end-start) + 1;
		return this;
		
												
	
		
		
		
		
		
	}

	
	private boolean isEmpty() {
		if(firstC.equals(lastC))
			return true;
		return false;
	}
	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if(index >= length || index < 0) 
			return this;
		
		
		CNode prevNode;
		
		
		if(index>0) { //Node is not firstC
			prevNode = nodeAt(index-1);
			
			if(index<(length-1))	{		//Index is not lastC
			  prevNode.next = nodeAt(index+1);
			}
			else {				//Index is lastC
				prevNode.next = null;
				lastC = prevNode;			//Drop the last character off the list
			}
		}
		
		else {	//Index is 0
			firstC = nodeAt(index+1);			//firstC is now the next character
		}
		
		length--;
	    return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		
		
		
		if (str.length()>length)		//the string is longer than our char list
			return -1;
			
		
		
		
		//boolean stringMatch = true;
		int matchIndex;		//First match
		int prevStartIndex = 0;
		int stringEndIndex = 0;
		int i = 0;
		final int stringLength = str.length();	//length of string  3
		final char firstChar = str.charAt(0);	//starting character  w
	
		
		
		
		matchIndex = findFirstIndex(prevStartIndex,firstChar);   //Find the first index where the first character of the string appears  0
		prevStartIndex = matchIndex;		//We set our prev index as the bottom of the search range for the next time around  0
		stringEndIndex = matchIndex + stringLength -1;	//searches from 0 to 2 for "who"   2
		
		while(i < stringLength) {
			
			
			if(stringEndIndex >= length) {		//were past the end
				return -1;
			}
		  
			
			char currStrChar = str.charAt(i);	
			char currNodeChar = charAt(matchIndex+i);	
			
			
			
			if(currNodeChar == currStrChar) {		//so far so good keep going
				i++;
			}
			else{		//Mismatch character in the string, start over using the next index
				i=0;
				matchIndex = findFirstIndex(prevStartIndex+1,firstChar);
				
				if (matchIndex == -1) {
					return -1;
				}
				stringEndIndex = matchIndex + stringLength - 1;	
				prevStartIndex = matchIndex;
			  }
			
		}
		
		return matchIndex;
		
	}
	
	private int findFirstIndex(int startIndex, char c) {
		
		for(int i=startIndex;i<length;i++) {
			if(charAt(i)==c) {
				return i;
			}
		}
		
		
		return -1;
		
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		if(offset==length )
			return append(str);
		if(offset>length)
			return this;
		
		//Turn the string into a linked list
		CNode firstStrNode = new CNode(str.charAt(0));
		CNode currNode = firstStrNode;
		length++;
	
		for (int i = 1; i < str.length(); i++)
		{													// P (firstC)      I	       G (lastC)
			CNode newNode = new CNode(str.charAt(i));			//First Node-->Next node-->currNode  
			currNode.next = newNode;							//				    Nodes added here ^
			currNode = newNode;
			length++;
		}
		CNode lastStrNode = currNode;		//Store the last node of string
		
		
		if(offset>0) {			//in the middle somewhere
		   CNode frontLink = nodeAt(offset-1);
		   CNode endLink = frontLink.next;
		   frontLink.next = firstStrNode;
		   lastStrNode.next = endLink;
		}
		 else {	   //Insert at front
			 lastStrNode.next = firstC;
			 firstC = firstStrNode;
		 }
		
	   return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		if(offset==length )
			return append(c);
		if(offset>length)
			return this;

		
		CNode charNode = new CNode(c);
		length++;
		
		if(offset>0) {			//in the middle somewhere
		   CNode frontLink = nodeAt(offset-1);
		   CNode endLink = frontLink.next;
		   frontLink.next = charNode;
		   charNode.next = endLink;
		}
		 else {	   //Insert at front
			 charNode.next = firstC;
			 firstC = charNode;
		 }
		
	   return this;
		
		
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
	
	if(offset==length )
		return append(c);
	if(offset>length)
		return this;
	
	//Turn the string into a linked list
	CNode firstCharNode = new CNode(c[0]);
	CNode currNode = firstCharNode;
	length++;
	
	
	for (int i = 1; i < c.length; i++)
	{													// P (firstC)      I	       G (lastC)
		CNode newNode = new CNode(c[i]);			//First Node-->Next node-->currNode  
		currNode.next = newNode;							//				    Nodes added here ^
		currNode = newNode;
		length++;
	}
	CNode lastCharNode = currNode;		//Store the last node of string
	
	
	if(offset>0) {			//in the middle somewhere
	   CNode frontLink = nodeAt(offset-1);
	   CNode endLink = nodeAt(offset);
	   frontLink.next = firstCharNode;
	   lastCharNode.next = endLink;
	}
	 else {	   //Insert at front
		 lastCharNode.next = firstC;
		 firstC = firstCharNode;
	 }
	
   return this;
}
	


	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder replace(int start, int end, String str)
	{
		if(start >= length || end <= start )
			return this;
		
		
		if (end >length) 
			end = length;
		
		CNode firstLink;
	
		//Setup and special cases are done
		if (start>1)
		  firstLink = nodeAt(start-1);
		else
		   firstLink = firstC;
		
		CNode endLink = nodeAt(end);
	
		
		CNode firstStrNode = new CNode(str.charAt(0));
		CNode currNode = firstStrNode;
		
		for (int i = 1; i < str.length(); i++)
		{													// P (firstC)      I	       G (lastC)
			CNode newNode = new CNode(str.charAt(i));			//First Node-->Next node-->currNode  
			currNode.next = newNode;							//				    Nodes added here ^
			currNode = newNode;
		}
		CNode lastStrNode = currNode;
		
		
	if(end == length) {		//all the way until the end
		if (start==0)
			firstC = firstStrNode;		//We replace the entire list
		else
		   firstLink.next = firstStrNode;	//We replace from start until the end
		
	  lastC = lastStrNode;	
	}
	else {	
		if (start<1)	 //at front 
			firstC = firstStrNode;
		else      //in the middle
		   firstLink.next = firstStrNode;
		
	 
      lastStrNode.next = endLink;		//either way we do not go to the end of the list
     
	}	

length = length  - (end - start) + str.length();

		
return this;
	
	
	}
	
	

	// Reverse the characters in the current MyStringBuilder and then
	// return the current MyStringBuilder.
	public MyStringBuilder reverse()
	{
		CNode tempLast = firstC;
		CNode currNode = firstC;		
		CNode prevNode = null;		//Nothing to the left of firstNode
		CNode nextNode = null;		
		
	while(currNode != null) {
		nextNode = currNode.next;				//Get the node (temp) to the right before we lose the pointer to it
		currNode.next = prevNode;			//The reverse the direction it is pointing
		prevNode = currNode;					//Iterate the previous node
		currNode = nextNode;					//Iterate the currentNode
	}	
		
		lastC = firstC;					//lastC is the original firstC
		firstC = prevNode;				//firstC is now the original end of the list
								
	     return this;
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		CNode startNode = nodeAt(start);
		CNode endNode = nodeAt(end);
		CNode currNode = startNode;
		char [] subChar = new char [end-start];
		int i = 0;
		
		while(currNode != endNode) {
			subChar[i] = currNode.data;
			currNode = currNode.next;
			i++;
		}
		
       return new String(subChar);
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		char [] c = null;
		
		if(length == 0) {
			return "";
		}
		else {
			c = new char[length];
			CNode currNode = firstC;
			int i = 0;
			while(currNode != null){				//Iterate through the linked list and add each character to an array
			c[i] = currNode.data;
			i++;
			currNode = currNode.next;
			}
		}
			return new String(c);
	}

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)		//First node to enter the list
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)	 
		{
			data = c;
			next = n;
		}
	}
}
