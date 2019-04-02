public class Assig2B{
	
	
	
	
	
	 
	 
	public static void main(String [] args) {
		
		int N = Integer.parseInt(args[0]);
		//int N = 160000;
		long msbAppend=0, sbAppend=0, sAppend=0;
		long msbDelete=0, sbDelete=0, sDelete=0;
		long msbInsert=0, sbInsert=0, sInsert=0;
	
		
	
		
		
		
		for(int i=0;i<3;i++) { //main loop
			
			if(i==0) {
			MyStringBuilder linkedSB = new MyStringBuilder();
		     msbAppend = runMySBLoop(N,"append",linkedSB);
			 msbDelete = runMySBLoop(N,"delete",linkedSB);		
			 msbInsert = runMySBLoop(N,"insert",linkedSB);
			 
				
			}
			if(i==1) {
			   StringBuilder arraySB = new StringBuilder();
               sbAppend = runSBLoop(N,"append",arraySB);
			   sbDelete = runSBLoop(N,"delete",arraySB);			
		       sbInsert = runSBLoop(N,"insert",arraySB);
			}
			if(i==2) {
				char s [] = new char[N];
				
				for(int y = 0;y<N;y++) {
					s[i] = 'a';
				}
				
				String theString = new String();		//For concatenate
				sAppend = runSLoop(N,"append",theString);
				String deleteString = new String(s); //For delete 
				sDelete = runSLoop(N,"delete",deleteString);			//Delete string of size N
				String insertString = new String();
				sInsert = runSLoop(N,"insert",insertString);
			}
			
		}
		
		System.out.println("\t\t--Total Time--");
		System.out.println();
		System.out.println("Class\t\tAppend\t\tDelete\t\tInsert");
		System.out.println();
		System.out.println("MySB\t\t"+msbAppend+"\t\t"+msbDelete+"\t\t"+msbInsert);
		System.out.println("SB\t\t"+sbAppend+"\t\t"+sbDelete+"\t\t"+sbInsert);
		System.out.println("String\t\t"+sAppend+"\t\t"+sDelete+"\t\t"+sInsert);
		System.out.println();
		System.out.println();
		System.out.println("\t--Time Per Operation--");
		System.out.println();
		System.out.println("Class\t\tAppend\t\tDelete\t\tInsert");
		System.out.println();
		System.out.println("MySB\t\t"+msbAppend/N+"\t\t"+msbDelete/N+"\t\t"+msbInsert/N);
		System.out.println("SB\t\t"+sbAppend/N+"\t\t"+sbDelete/N+"\t\t"+sbInsert/N);
		System.out.println("String\t\t"+sAppend/N+"\t\t"+sDelete/N+"\t\t"+sInsert/N);
		
			
			
			
			
}
		
	public static long runMySBLoop(int N,String operation,MyStringBuilder linkedSB) {
		long startTime = 0;
		long endTime = 0;
		
		if(operation.equals("append")) {
			startTime = System.nanoTime();
			for(int i = 0; i<N ; i++) {
				linkedSB.append('a');
			}
			endTime = System.nanoTime();
		}
		
		else if(operation.equals("delete")) {
			startTime = System.nanoTime();
			for(int i = 0; i<N ; i++) {
				linkedSB.delete(0,1);
			}
			endTime = System.nanoTime();
		}
		else {
			startTime = System.nanoTime();
			
			for(int i = 0; i<N ; i++) {
				linkedSB.insert(linkedSB.length()/2,'a');
			}
			endTime = System.nanoTime();
		}
	
	return endTime - startTime;
}
	
	
	
	
	public static long runSBLoop(int N,String operation,StringBuilder arraySB) {
		long startTime = 0;
		long endTime = 0;
		if(operation.equals("append")) {
			startTime = System.nanoTime();
			for(int i = 0; i<N ; i++) {
				arraySB.append('a');
			}
			endTime = System.nanoTime();
		}
		
		else if(operation.equals("delete")) {
			startTime = System.nanoTime();
			for(int i = 0; i<N ; i++) {
				arraySB.delete(0,1);
			}
			endTime = System.nanoTime();
		}
		else {
			startTime = System.nanoTime();
			
			for(int i = 0; i<N ; i++) {
				arraySB.insert(arraySB.length()/2,'a');
			}
			endTime = System.nanoTime();
		}
	
	return endTime - startTime;
  }
	
	
	
public static long runSLoop(int N,String operation,String s) {
	long startTime = 0;
	long endTime = 0;
	
	if(operation.equals("append")) {
		startTime = System.nanoTime();
		for(int i = 0; i<N ; i++) {
			s.concat("a");	//Concat string -- Very similar to append
		}
		endTime = System.nanoTime();
	}
	
	else if(operation.equals("delete")) {
		int strLength = s.length();
		startTime = System.nanoTime();
		
        for(int i = 0; i<strLength ; i++) {
			s.substring(0,strLength-1);			//access the index & remove it (simulated)
			strLength--;
			
		}
		endTime = System.nanoTime();
	}
	
	else {			//Insert
		startTime = System.nanoTime();
		for(int j=0;j<N;j++) {		//Do this operation N times
			
			
			String subFront = s.substring(0,(s.length()/2));			//get the first half of the string
			String subBack = s.substring(s.length()/2,s.length());	//get the second half
			s = new String(subFront+"a"+subBack);	//build a new one
		   }
		  endTime = System.nanoTime();
	}
	
        return endTime - startTime;
	
	}
}
	
