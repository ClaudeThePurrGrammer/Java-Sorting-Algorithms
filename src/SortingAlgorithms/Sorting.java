package SortingAlgorithms;

import java.util.Scanner ; 

public class Sorting
{
	int dim ; 
	static boolean condition ;
	int[] array = new int[dim] ; 
	public static Scanner input = new Scanner(System.in) ; 
	String choice = get_Choice();
	
	
	
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
			
			System.out.printf("Select Algorhitm [BUBBLE/SELECTION/INSERTION/SHELL/QUICK] or [EXIT] to Close the Program : ");
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
						   
			case "EXIT" : System.out.println("End of Program..."); break;
			default: 
				algo = "NULL" ;  
				System.out.println("Invalid Input: ");
				break  ;
		}
			}while(algo.equals("NULL")) ;
		
		return algo ; 
		
	}
	
	
//--------------------   ALGORITHMS  --------------------
	
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
	 
	 void Insertion()
		{
			 
				System.out.printf("Using Insertion: ");
				System.out.println("");
				int min_index = Get_Lowest_Index(array) ;
				Swap(array,min_index , 0);
				for(int i = 1 ; i<array.length-1 ; i++)
				{
					int x = array[i++] ;
					for(int j = i ; j<1 ; j--)
					{
						if(Order_Relation(array[j] , x , choice)==true) break;
					
						for(int h = i ; h > j ; h--)
						{
							array[h+1] = array[h] ; 
							array[j+1] = x;
						}
						
					
					}
				}
		}
	 
	 void Shell()
	 {
		 System.out.printf("Using Shell: ");
		 System.out.println("");
		 int inc = array.length/2;
		 
		 Order_Chain( array , inc) ; 
	 }
	
	 
	
	 void Quick(int[] array, int inf, int sup) {
		    if (inf < sup) {   
		        int pos = Partition(array, inf, sup);

		        
		        if ((pos - inf) < (sup - pos)) {
		            if (inf < pos - 1) Quick(array, inf, pos - 1);
		            if (pos < sup) Quick(array, pos, sup);
		        } else {
		            if (pos < sup) Quick(array, pos, sup);
		            if (inf < pos - 1) Quick(array, inf, pos - 1);
		        }
		    }
		}

	 private int Partition(int[] array, int inf, int sup) {
		    int med = (inf + sup) / 2;
		    int pivot = array[med];

		    Swap(array, inf, med);

		    int i = inf;
		    int j = sup;

		    while (i <= j) {
		        while (i <= sup && Order_Relation(array[i], pivot, choice)) i++;
		        while (j >= inf && Order_Relation(pivot, array[j], choice)) j--;

		        if (i <= j) {
		            Swap(array, i, j);
		            i++;
		            j--;
		        }
		    }
		    return i; // indice di separazione
		}
		
	 
	
	//--------------------   SUPPORT METHODS  --------------------
	 
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

	
	
	
	private  int Get_Lowest_Index(int[] array)
	{
		
		int min_index = 0 ;
		for(int i = 0 ; i<array.length ;i++)
		{
			if(array[i] < array[min_index])
				min_index = i ; 
		}
		return min_index ; 
	}
	
	
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
	
	
	/*private int Partition(int[] array, int inf, int sup) {
	    int med = (inf + sup) / 2;
	    int pivot = array[med];

	    Swap(array, inf, med);

	    int i = inf;
	    int j = sup;

	    while (i <= j) {
	        while (i <= sup && Order_Relation(array[i], pivot, choice)) {
	            i++;
	        }
	        while (j >= inf && Order_Relation(pivot, array[j], choice)) {
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
	*/
}
		
		
		
	
	

