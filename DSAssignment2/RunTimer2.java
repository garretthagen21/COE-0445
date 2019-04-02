public class RunTimer2 {
	
	StringBuilder theBuilder;
	int N;
	
	
	public RunTimer2(int n) {	//Constructor for linkedSB
		theBuilder = new StringBuilder();
		N = n;
	}
	
   
   public long append() {
	   
			long startTime = System.nanoTime();
			for(int i = 0; i<N ; i++) {
				theBuilder.append('a');
			}
			long endTime = System.nanoTime();
		
	return endTime - startTime;	
}
  
  public long delete() {
	   long startTime = System.nanoTime();
		for(int i = 0; i<N ; i++) {
			theBuilder.delete(0,1);
		}
		long endTime = System.nanoTime();
		System.out.println(theBuilder.toString());  
      return endTime - startTime;	
	   
	 
	   
	   
   }
  public long insert() {
	  
	  long startTime = System.nanoTime();
		for(int i = 0; i<N ; i++) {
			//System.out.println(theBuilder.toString());
			theBuilder.insert(N/2,'a');
		}
		long endTime = System.nanoTime();
	
    return endTime - startTime;	
	  
	  
  }
   

		   
		   
		   
	   
 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
