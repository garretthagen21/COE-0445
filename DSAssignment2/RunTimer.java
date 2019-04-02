public class RunTimer {
	
	MyStringBuilder theBuilder;
	int N;
	
	
	public RunTimer(int n) {	//Constructor for linkedSB
		theBuilder = new MyStringBuilder();
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
	
      return endTime - startTime;	
	   
	   
	   
	   
   }
  public long insert() {
	  long startTime = System.nanoTime();
		for(int i = 0; i<N ; i++) {
			theBuilder.insert(N/2,'a');
		}
		long endTime = System.nanoTime();
	
    return endTime - startTime;	
	  
	  
  }
   

		   
		   
		   
	   
 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
