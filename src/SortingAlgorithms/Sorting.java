package SortingAlgorithms;

import java.util.Scanner ; 

public class Sorting
{
	
	// INITIALIZING
	int dim ; 
	static boolean condition ;
	int[] array = new int[dim] ; 
	public static Scanner input = new Scanner(System.in) ; 
	String choice = get_Choice();
	//----------------
	
	//ALGORITHM'S SETUP
	private static String get_Choice()
	{
		System.out.printf("Select Order Relation [ASC/DESC] (default ASC) : ");
		System.out.println("");
		String choice = input.nextLine().toUpperCase() ; 
		
		return choice ; 
		
	}
	
	  String  Set_Algo()
	{	
		
		String algo = "NULL"; 
		
		do {
			
			System.out.printf("Select Algorhitm [BUBBLE/SELECTION/INSERTION/SHELL/QUICK/MERGE] or [EXIT] to Close the Program : ");
			algo = input.nextLine().toUpperCase() ;
			System.out.println("");
			
		switch(algo)
		{
			case "BUBBLE" : Bubble(); break ; 
			case "SELECTION" : Selection(); break; 
			case "INSERTION" : Insertion(); break ; 
			case "SHELL" : Shell(); break;
			case "QUICK" : System.out.printf("Using Quick: "); System.out.println("");
						   Quick(this.array , 0 , this.array.length-1); break;
			case "MERGE" : System.out.printf("Using Merge: "); System.out.println("");
			   			   Merge(this.array , 0 , this.array.length-1); break;
						   
			case "EXIT" : System.out.println("End of Program..."); break;
			default: 
				algo = "NULL" ;  
				System.out.println("Invalid Input: ");
				break  ;
		}
			}while(algo.equals("NULL")) ;
		
		return algo ; 
		
	}
	//----------------------
	
	  
	  
	 //ALGORITHMS  
	 void  Bubble()
	{	
		
		System.out.printf("Using Bubble: ");
		System.out.println("");
		
		for(int i = 0 ; i< array.length ; i++)
		{
			for(int j = 1 ; j< array.length-i ; j++)
			{
				if(Order_Relation(array[j-1] , array[j] , choice)!=true)
				{
					Swap(array, j-1, j);
				}
			}
		}
		
		
	}
	
	 void Selection()
	{
		 
			System.out.printf("Using Selection: ");
			System.out.println("");
			
			for(int i = 0 ; i< array.length ; i++)
			{
				int min = i ;
				for(int j = i ; j < array.length ; j++)
				{
					if(Order_Relation(array[min] , array[j] , choice)!=true)
					{
			
						if(min!=j)
						Swap(array, min, j);
					}
				}
			}
	}
	 
	 void Insertion() {
		    System.out.printf("Using Insertion:");
		    System.out.println("");

		    for (int i = 1; i < array.length; i++) {
		        int x = array[i];      
		        int j = i - 1;

		        
		        while (j >= 0 && array[j] > x) {
		            array[j + 1] = array[j];
		            j--;
		        }

		        
		        array[j + 1] = x;
		    }
		}
	 
	 void Shell()
	 {
		 System.out.printf("Using Shell: ");
		 System.out.println("");
		 int inc = array.length/2;
		 
		 Order_Chain( array , inc) ; 
	 }
	
	 
	
	 void Quick(int[] array, int low, int high) {
		    if (low < high) 
		    {   
		        int pos = Partition(array, low, high);
		        Quick(array , low , pos-1 );
		        Quick(array , pos+1 , high);
		    }

		        

		}
	 
	 void Merge(int[] array , int low , int high)
	 {
		 if(low<high)
		 {
			 int med = (low+high)/2 ; 
			 Merge(array , low , med) ; 
			 Merge(array , med+1 , high); 
			 Union(array ,low , med , high);
		 }
	 }
	 //----------------------

	
		
	 
	
	 //SUPPORT METHODS
	 
	 private static boolean Order_Relation(int el_arr_x, int el_arr_y , String s) 
		{
			
				switch(s)
				{
					case "ASC" : 
						if(el_arr_x <= el_arr_y)
							condition = true ; 
						else condition = false ;
						break ; 
						
					case "DESC" : 
						if(el_arr_x >= el_arr_y)
							condition =  true ; 
						else condition =  false ; 
						break ;
						
					default : 
						if(el_arr_x <= el_arr_y)
							condition = true ; 
						else condition = false ;
						break ; 
						
						

				}
				
				return condition ; 
				
				
				
		}
		
	 
	 
	
	private static void Swap(int[] array, int pos_a, int pos_b) {
	    int temp = array[pos_a];
	    array[pos_a] = array[pos_b];
	    array[pos_b] = temp;
	}

	
	
	
	/*private  int Get_Lowest_Index()
	{
		
		int min_index = 0 ;
		for(int i = 0 ; i<array.length ;i++)
		{
			if(array[i] < array[min_index])
				min_index = i ; 
		}
		return min_index ; 
	}
	
	*/
	
	private void Order_Chain(int[] array, int inc) 
	{
		if(inc < 1)
		 return  ; 
		else
			{

			for(int i = 0 ; i<inc ; i++)
			{
				Swap_Chain(array,inc,i) ; 
			}
			 
			 inc/=2; 
			 Order_Chain(array , inc);
			}
		
		
		
		
		
	}
	
	
	private void Swap_Chain(int[] array , int inc , int i )
	{
		int k = i+inc;
		while(k<array.length)
		{
			boolean inserted = false ; 
			int x , current , previous ; 
			
			x = array[k];
			current = k ; 
			previous = current-inc ;
			
			while(previous>=0 && !inserted)
			{
				if(Order_Relation(array[previous] , x , choice)!=true)
				{
					array[current] = array[previous];
					current = previous ;
					previous = previous-inc ;
				}
				else 
					inserted = true  ;
				
					
			}
			array[current] = x ; 
		
			k+=inc;
		}
	}
	// FOR QUICKSORT
	private static boolean Order_Relation_Left(int a, int pivot, String s) {
	    switch(s) {
	        case "ASC":  return a < pivot;
	        case "DESC": return a > pivot;
	        default:     return a < pivot;
	    }
	}

	private static boolean Order_Relation_Right(int a, int pivot, String s) {
	    switch(s) {
	        case "ASC":  return a > pivot;
	        case "DESC": return a < pivot;
	        default:     return a > pivot;
	    }
	}
	//----------------------
	
	
	 private int Partition(int[] array, int low, int high) {
		 int mid = low + (high - low) / 2;   
	        int pivot = array[mid];             

	        int i = low;
	        int j = high;

	        while (i <= j) {
	        	
	        	while (i <= high && Order_Relation_Left(array[i], pivot, choice)) {
	        	    i++;
	        	}
	        	while (j >= low && Order_Relation_Right(array[j], pivot, choice)) {
	        	    j--;
	        	}
	           
	            if (i <= j) {
	                Swap(array, i, j);
	                i++;
	                j--;
	            }
	        }
	        return i;
	    }
	 //------------------------


	private void Union(int array[] , int low , int medium ,  int high)
	{
		int l = low  , new_m = medium+1 , x = 0;
		int[] aux = new int[high - l+1] ; 
		int start = medium , end = high ;
		// ciclo principale: confronta elementi delle due metà
		while(l <= medium && new_m <= high) {
		    if(Order_Relation(array[l], array[new_m], choice) == true) {
		        aux[x] = array[l];
		        l++;
		    } else {
		        aux[x] = array[new_m];
		        new_m++;
		    }
		    x++;
		}

		// copia gli elementi rimasti della prima metà (se ce ne sono)
		while(l <= medium) {
		    aux[x] = array[l];
		    l++;
		    x++;
		}

		// copia gli elementi rimasti della seconda metà (se ce ne sono)
		while(new_m <= high) {
		    aux[x] = array[new_m];
		    new_m++;
		    x++;
		}
		
		
		while(l <= medium) aux[x++] = array[l++];
		while(new_m <= high) aux[x++] = array[new_m++];
		
		for(start = 0 ; start < x  ; start++)
		{
			array[low+start] = aux[start];
		}
		
	}
	
} // End Of Class
		
		
		
	
	

