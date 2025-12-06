package SortingAlgorithms;
import java.util.Random;
import java.util.Arrays;


public class Main 
{
	// RANDOM INITIALIZING
	 static Random random = new Random();
	//--------------------

	
	
	
	public static void main(String args[])
	{
		//INITIALIZING
		Sorting sort = new Sorting();
		sort.dim = Integer.parseInt(args[0]) ; 
		int[] unsorted_array = RandomInitialization(sort.dim);
		//-------------
		
		//Start of Program
		sort.array = Arrays.copyOf(unsorted_array, unsorted_array.length)  ;
		System.out.println("unsorted_array values :  ") ;
		PrintArray(unsorted_array) ; 
		
		String s = null ; 
		
		do
		{
			s = sort.Set_Algo();
			if(!(s.equals("EXIT")) && !(s.equals("NULL")))
			PrintArray(sort.array); 
			sort.array = Arrays.copyOf(unsorted_array, unsorted_array.length)  ;
		}
		while(!(s.equals("EXIT"))) ; //End Of Program
			
		
		
		
		
		
		
	}
	
	//PRINT ARRAY VALUES
	private static void PrintArray(int[] array) 
	{
		
		for(int printindex = 0 ; printindex <= array.length -1 ; printindex++)
			
			System.out.println(" "+ (printindex+1)+"°: " + array[printindex]);
		
	}
	//--------------------
	
	//SETTING SEED 
	private static int[] RandomInitialization(int dim)
	{
		int[] initializable_array = new int[dim] ;
		for(int i = 0 ; i < initializable_array.length ; i++)
		initializable_array[i] = random.nextInt(101) ; 
		return initializable_array ; 
		
	}
	//---------------

}
		
