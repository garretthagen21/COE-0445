

// CS 0445 Spring 2018
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder2 using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
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


	// Create a new MyStringBuilder2 which contains the contents of the
	// String argument.
	// Constructor to make a new MyStringBuilder2 from a String.  The constructor
	// itself is NOT recursive – however, it calls a recursive method to do the
	// actual set up work.  This should be your general approach for all of the
	// methods, since the recursive methods typically need extra parameters that
	// are not given in the specification.

	public MyStringBuilder2(String s){
		
	      if (s != null && s.length() > 0)
	    	  			makeBuilder(s, 0);
	      else { // no String so initialize empty MyStringBuilder
	    	  length = 0;
	      firstC = null;
	      lastC = null;
	      }
	//System.out.println(length);
	}

	 

	// Recursive method to set up a new MyStringBuilder2 from a String

	private void makeBuilder(String s, int pos){

	      // Recursive case – we have not finished going through the String
	      if (pos < s.length()-1) {

	            // Note how this is done – we make the recursive call FIRST, then
	            // add the node before it.  In this way the LAST node we add is
	            // the front node, and it enables us to avoid having to make a
	            // special test for the front node.  However, many of your
	            // methods will proceed in the normal front to back way.

	            makeBuilder(s, pos+1);
	            firstC = new CNode(s.charAt(pos), firstC);
	            length++;
	      }

	      else if (pos == s.length()-1) {	// Special case for last char in String                             
	            firstC = new CNode(s.charAt(pos));	// This is needed since lastC must be
	            lastC = firstC;						//Set point to this node
	            length++;

	      }

	      else  // This case should never be reached, due to the way the constructor is set up.  However, I included it as a safeguard (in case some other method calls this one)
	      {    
	            length = 0;
	            firstC = null;
	            lastC = null;

	      }

	}
	//Char array

	public MyStringBuilder2(char [] c){
		
	      if (c != null && c.length > 0)
	    	  			makeBuilder(c, 0);
	      else { // no String so initialize empty MyStringBuilder
	    	  length = 0;
	      firstC = null;
	      lastC = null;
	      }
	//System.out.println(length);
	}

	 

	// Recursive method to set up a new MyStringBuilder2 from a String

	private void makeBuilder(char [] c, int pos){

	      // Recursive case – we have not finished going through the String
	      if (pos < c.length-1) {

	            // Note how this is done – we make the recursive call FIRST, then
	            // add the node before it.  In this way the LAST node we add is
	            // the front node, and it enables us to avoid having to make a
	            // special test for the front node.  However, many of your
	            // methods will proceed in the normal front to back way.

	            makeBuilder(c, pos+1);
	            firstC = new CNode(c[pos], firstC);
	            length++;
	      }

	      else if (pos == c.length-1) {	// Special case for last char in String                             
	            firstC = new CNode(c[pos]);	// This is needed since lastC must be
	            lastC = firstC;						//Set point to this node
	            length++;

	      }

	      else  // This case should never be reached, due to the way the constructor is set up.  However, I included it as a safeguard (in case some other method calls this one)
	      {    
	            length = 0;
	            firstC = null;
	            lastC = null;

	      }

	}

	// Create a new empty MyStringBuilder2
	public MyStringBuilder2()
	{
		firstC = null;
		lastC = null;
		length = 0;
	
	}

	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
			
		
			if (b.firstC == null) { // Special case for empty String
				return this;
			}
			else
			{
				if(firstC != null) {			//Our current stringbuilder is not empty
				CNode endNode = lastC;		//save this to connect to new builder
				CNode bridgeNode = appendBuilder(b.firstC);
				endNode.next = bridgeNode;
				}
				else {
					firstC = appendBuilder(b.firstC);
				}
			}
			return this;
		}
		
	private CNode appendBuilder(CNode oldBuild) {
		CNode frontNode = null;
		
		if(oldBuild.next != null) {			//last to execute
			CNode currNode = appendBuilder(oldBuild.next);		//currentNode is the node that is returned aka the node to the right of it
			frontNode = new CNode(oldBuild.data,currNode);		//make a new node that will be returned and is the "next" of the node below it on the stack
			length++;
		}
		else if(oldBuild.next == null) {		//We reach the last node of the builder we are adding
			frontNode = new CNode(oldBuild.data); 
			lastC = frontNode;
			length++;
		}
		return frontNode;
	}
	
	
	
	// Append String s to the end of the current MyStringBuilder2, and return
	// the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{
			if (s == null || s.length() == 0) { // Special case for empty String
				return this;
			}
			else
			{
				if(firstC != null) {
				CNode endNode = lastC;
				CNode bridgeNode = appendString(s,0);
				endNode.next = bridgeNode;
				}
				else {
					firstC = appendString(s,0);
				}
			}
			return this;
		}
		
	private CNode appendString(String s,int pos) {
		CNode frontNode = null;
		
		if(pos < s.length()-1) {			//last to execute
			CNode currNode = appendString(s,pos+1);
			frontNode = new CNode(s.charAt(pos),currNode);
			length++;
		}
		else if(pos == s.length()-1) {
			frontNode = new CNode(s.charAt(pos)); 
			lastC = frontNode;
			length++;
		}
		return frontNode;
	}

	// Append char array c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c)
	{
		
			if (c.length == 0) { // Special case for empty String
				return this;
			}
			else
			{
				if(firstC != null) {
				CNode endNode = lastC;
				CNode bridgeNode = appendCharArray(c,0);
				endNode.next = bridgeNode;
				}
				else {
					firstC = appendCharArray(c,0);
				}
			}
			return this;
		}
		
	private CNode appendCharArray(char [] c,int pos) {
		CNode frontNode = null;
		
		if(pos < c.length-1) {			//last to execute
			CNode currNode = appendCharArray(c,pos+1);
			frontNode = new CNode(c[pos],currNode);
			length++;
		}
		else if(pos == c.length-1) {
			frontNode = new CNode(c[pos]); 
			lastC = frontNode;
			length++;
		}
		return frontNode;
	}
		
		
		
	

	// Append char c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{
		    CNode currNode = new CNode(c);			        //First Node-->Next node-->currNode  
	                                  			
		    if(firstC != null)
		    		lastC.next = currNode;
		    else
		    		firstC = currNode;
		    
			lastC = currNode;
			length++;
            return this;
	
	}
	

	// Return the character at location "index" in the current MyStringBuilder2.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index) throws IndexOutOfBoundsException
	{
		assert (index<length) && (firstC != null);
		
		 CNode nodeLocate = findNode(firstC,0,index);
		 
		 return nodeLocate.data;
	
	}
	private CNode findNode(CNode currNode,int counter,int i) {
		CNode foundNode; 
		
		if(counter < i)
		   foundNode = findNode(currNode.next,counter+1,i);
		else
			foundNode=currNode; //When we reach it set foundNode equal to the currentNode before we work back down the stack
				
		
		return foundNode;
		
		
	}
	
	//TODO: Delete
	private CNode nodeAt(int index) throws IndexOutOfBoundsException			//Just like charAt but returns the entire node
	{
		assert (index<length) && (firstC != null);
		
		CNode theNode = findNode(firstC,0,index); //Index 0
		return theNode;
	
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder2, and return the current MyStringBuilder2.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
	// only remove up until the end of the MyStringBuilder2. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end)
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
		if(firstC == null)
			return true;
		return false;
	}
	// Delete the character at location "index" from the current
	// MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder2 as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{
		if(index >= length || index < 0) 
			return this;
		
		
		CNode prevNode;
		
		
		if(index>0) { //Node is not firstC
			prevNode = nodeAt(index-1);
			
			if(index<(length-1))	{		//Index is not lastC
			  CNode bridgeNode = prevNode.next.next;
			  prevNode.next = bridgeNode;
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

	// Find and return the index within the current MyStringBuilder2 where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		
		
		
		if (str.length()>length)		//the string is longer than our char list
			return -1;
			
		
		
		
		int matchIndex;		//First match
		int prevStartIndex = 0;
		int stringEndIndex = 0;
		final char firstChar = str.charAt(0);	//starting character  w
	
		
		
		
		matchIndex = findInitialCharIndex(prevStartIndex,firstChar);   //Find the first index where the first character of the string appears  0
		prevStartIndex = matchIndex;		//We set our prev index as the bottom of the search range for the next time around  0
		stringEndIndex = matchIndex + str.length() -1;	//searches from 0 to 2 for "who"   2
		matchIndex = findIndexOf(0,matchIndex,stringEndIndex,prevStartIndex,str,firstChar);
		return matchIndex;
		
	}
	private int findIndexOf(int i,int matchIndex,int stringEndIndex,int prevStartIndex,String s,char firstChar) {
		//int matchIndex;
		//System.out.println(matchIndex);
		if(stringEndIndex >= length) {		//were past the end    --> base case
			return -1;
		}
		
		char currStrChar = s.charAt(i);	
		char currNodeChar = charAt(matchIndex+i);
		//System.out.println(currStrChar + " " +currNodeChar);
		
		if(currNodeChar == currStrChar) {			//So far so good
			if( i < s.length()-1)	{									//The characters match and we have reached the end of the word
				 matchIndex = findIndexOf(i+1,matchIndex,stringEndIndex,prevStartIndex,s,firstChar);
			}
			else {
				return matchIndex;		//We found the match
			}
			
		}
		else {		//Move the initial matchIndex and start recursing again -- The characters do not match
			
			matchIndex = findInitialCharIndex(prevStartIndex+1,firstChar);
				if(matchIndex !=-1) {										//If matchIndex is -1 that indicates there is no matches left and we should stop looking
					stringEndIndex = matchIndex + s.length() -1;
				    prevStartIndex = matchIndex;
					matchIndex = findIndexOf(0,matchIndex,stringEndIndex,prevStartIndex,s,firstChar);	//Recurse
				}
		
		}
		
		return matchIndex;
	}
	
	private int findInitialCharIndex(int index,char target) {
		int location;
		
		if(index<length-1) {		//not at the end of the array
			if(charAt(index) == target)
				location = index;
			else
				location = findInitialCharIndex(index+1,target);
			
		}
		else {
			location = -1;
		}
		
		return location;
		
	}
	

	// Insert String str into the current MyStringBuilder2 starting at index
	// "offset" and return the current MyStringBuilder2.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
	{
		if(offset==length )
			return append(str);
		if(offset>length)
			return this;
		
		//Turn the string into a linked list
        CNode firstStrNode = stringToList(str,0);
		CNode lastStrNode = getEndNode(firstStrNode);		//Store the last node of string
		length = length + str.length();
		
		
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
	
	private CNode stringToList(String s,int pos) {
		CNode frontNode = null;
		
		if(pos < s.length()-1) {			//last to execute
			CNode currNode = stringToList(s,pos+1);
			frontNode = new CNode(s.charAt(pos),currNode);
			//length++;
		}
		else if(pos == s.length()-1) {
			frontNode = new CNode(s.charAt(pos)); 
			//length++;
		}
		return frontNode;
	}
	
	private CNode getEndNode(CNode currNode) {
		CNode endNode;
		if(currNode.next != null)
			endNode = getEndNode(currNode.next);
		else
		   endNode = currNode;
		
		return endNode;
		
	}

	// Insert character c into the current MyStringBuilder2 at index
	// "offset" and return the current MyStringBuilder2.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
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

	// Insert char array c into the current MyStringBuilder2 starting at index
	// index "offset" and return the current MyStringBuilder2.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c)
	{
	
	if(offset==length )
		return append(c);
	if(offset>length)
		return this;
	
	
	CNode firstCharNode = charArrayToList(c,0);		
	CNode lastCharNode = getEndNode(firstCharNode);
	length = c.length + length;
	
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
	
	private CNode charArrayToList(char [] c,int pos) {
		CNode frontNode = null;
		
		if(pos < c.length-1) {			//last to execute
			CNode currNode = charArrayToList(c,pos+1);
			frontNode = new CNode(c[pos],currNode);
			//length++;
		}
		else if(pos == c.length-1) {
			frontNode = new CNode(c[pos]); 
			//length++;
		}
		return frontNode;
	}


	// Return the length of the current MyStringBuilder2
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder2, then insert String "str" into the current
	// MyStringBuilder2 starting at index "start", then return the current
	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder2, only delete until the
	// end of the MyStringBuilder2, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder2 replace(int start, int end, String str)
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
	
		CNode firstStrNode = stringToList(str,0);
		CNode lastStrNode = getEndNode(firstStrNode);
		
		
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
	
	

	// Reverse the characters in the current MyStringBuilder2 and then
	// return the current MyStringBuilder2.
	public MyStringBuilder2 reverse()
	{
		CNode tempLast = lastC;
		CNode currNode = firstC;		
		CNode prevNode = null;		//Nothing to the left of firstNode
		CNode nextNode = null;		
			
		recursiveReverse(prevNode,currNode,nextNode);
		lastC = firstC;					//lastC is the original firstC
		firstC = tempLast;				//firstC is now the original end of the list
								
	     return this;
	}
	private void recursiveReverse(CNode prevNode, CNode currNode, CNode nextNode) {
		
		if(currNode == null) {
			return;
		}
			
		else {
			nextNode = currNode.next;
			currNode.next = prevNode;
			prevNode = currNode;
			currNode = nextNode;
			recursiveReverse(prevNode,currNode,nextNode);
		}
	
	
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end)
	{
		CNode currNode = nodeAt(start);
		CNode endNode = nodeAt(end);
		char [] subChar = new char [end-start];
		subChar = recursiveSubstring(currNode,endNode,subChar,0);
		
        return new String(subChar);
	}
	
	private char [] recursiveSubstring(CNode currNode,CNode endNode, char [] sub,int count) {
		
		if(currNode.next != endNode) {		//Recurse until the last node
			sub[count] = currNode.data;
			return recursiveSubstring(currNode.next,endNode,sub,count+1);
		}
		else {	//we have reached the last node
			sub[count] = currNode.data;
		}
		
		return sub;
	}

	// Return the entire contents of the current MyStringBuilder2 as a String
	// Again note that the specified method is not actually recursive – rather it
	// calls a recursive method to do the work.  Note that in this case we also
	// create a char array before the recursive call, then pass it as a
	// parameter, then construct and return a new string from the char array.
	// Carefully think about the parameters you will be passing to your recursive
	// methods.  Through them you must be able to move through the list and
	// reduce the "problem size" with each call.

	public String toString()
	{
	      char [] c = new char[length];
	      getString(c, 0, firstC);
	      return (new String(c));

	}

	 
	// Here we need the char array to store the characters, the pos value to
	// indicate the current index in the array and the curr node to access
	// the data in the actual MyStringBuilder2.  Note that these rec methods
	// are private – the user of the class should not be able to call them.
	private void getString(char [] c, int pos, CNode curr)
	{
	      if (curr != null)
	      {
	            c[pos] = curr.data;
	            getString(c, pos+1, curr.next);
	      }

	}
	// Find and return the index within the current MyStringBuilder2 where
		// String str LAST matches a sequence of characters within the current
		// MyStringBuilder22.  If str does not match any sequence of characters
		// within the current MyStringBuilder22, return -1.  Think carefully about
		// what you need to do for this method before implementing it.  For some
		// help with this see the Assignment 3 specifications.
		public int lastIndexOf(String str)
		{
			int index = recursiveIndex(str,0,firstC,0,-1);
			return index;
		}
		
		private int recursiveIndex(String str,int listPos,CNode currNode,int i,int lastIndex) {
			
				
		   if(listPos<length) {		//Recursive case
				if(i==0) {	//So we dont go to the end of the list when looking for a word
				 lastIndex = recursiveIndex(str,listPos+1,currNode.next,0,lastIndex);	//we have to fill the list
				}
				
			
				if(str.charAt(i) == currNode.data) {	 //char matches
					
					
					
					if(i==str.length()-1) {		//we have found the word
						if(listPos>lastIndex) {
						  lastIndex = listPos - str.length() +1;
						}
					}
					else if(i<str.length()-1){  	//We  have not made it to the end but the current character matches
						lastIndex = recursiveIndex(str,listPos+1,currNode.next,i+1,lastIndex);
					}
				
				}
			
			}	
		   else {	//BaseCase   //we are at the end and lastIndex is -1 unless there is a match
			lastIndex = -1;
			}
			
		return lastIndex;
			
					
}
		
		
		
		// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
		// in the return array corresponds to a part of the match of the array of
		// patterns in the argument.  If the overall match does not succeed, return
		// null.  For much more detail on the requirements of this method, see the
		// Assignment 3 specifications.
		public MyStringBuilder2 [] regMatch(String [] pats)
		{
			MyStringBuilder2 [] ans = new MyStringBuilder2[pats.length];		//Initialize empty array for the matches
			initalizeBuilderArray(ans,0);
			boolean found = recursiveRegMatch(pats,0,0,firstC,false,false,ans);
	
			return ans;
		}
			
		private void initalizeBuilderArray(MyStringBuilder2 [] ans,int count) {	
			if(count < ans.length) {
				ans[count] = new MyStringBuilder2();
				initalizeBuilderArray(ans,count+1);
			}
		}
			
	
		
		private boolean recursiveRegMatch(String [] patterns,int patternLoc,int numMatches,CNode currNode,boolean transitionState,boolean found,MyStringBuilder2 [] ans) {
			//boolean found;
			
		//System.out.println(ans[patternLoc].toString()+ " PatternLoc: "+ patternLoc+  " numMatches "+ numMatches);
		if(currNode == null) {// && patternLoc < patterns.length)	{
			return false;
		}
		if(transitionState && patterns[patternLoc].indexOf(currNode.data) == -1) {	//transition state & char does not match
			return false;
		}
		else {	
			//System.out.println(patterns[patternLoc].indexOf(currNode.data));
			if(patterns[patternLoc].indexOf(currNode.data)  >= 0){		//Char matches 
				ans[patternLoc].append(currNode.data); 					//Append the character to the same index of the pattern
				found = recursiveRegMatch(patterns,patternLoc,numMatches+1,currNode.next,false,found,ans); //Check keep matching currentPattern i
			}
			
			else {	//current character does not match  
					if(numMatches > 0) {	//We have found atleast 1 match for the current pattern			State 2
						
						if(patternLoc == patterns.length -1)	                //We are on the last pattern and have had atleast 1 match --> basecase
						     found = true;
						else 
						   found = recursiveRegMatch(patterns,patternLoc+1,0,currNode,true,found,ans);  //Move to the next pattern but stay on the same character
							if(!found) {
								
							}
								
					}
					else {	//we have no matches so far for this current pattern so stay on the same pattern and look at the next character in the string builder
						found = recursiveRegMatch(patterns,patternLoc,0,currNode.next,false,found,ans);
					}
						
			//	}
				//else {		//We are in the transition state and the current character does not match
					//System.out.println("transition state");
					//int theLength = ans[patternLoc-1].length();
					//ans[patternLoc-1].deleteCharAt(theLength);
					//System.out.println(ans[patternLoc-1].toString());
					
					//found = recursiveRegMatch(patterns,patternLoc-1,0,currNode,true,found,ans);
					
					
				//}
					
		    }
	     }

  return found;
}
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder2 class MAY access the
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





