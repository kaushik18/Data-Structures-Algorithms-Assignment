// Kaushik Nadimpalli
// CS3345.003
// Project 5
// kxn160430

/* Project Description: Generate the list randomly every time using the java.util.Random class.
Allow the user to choose the size of the array. The program should display the result of sorting 
the array of that size using different pivot choices. First element as pivot, Randomly choosing the 
pivot element, Choosing the median of 3 randomly chosen elements as the pivot, Median of first center 
and last element (book technique). Also, the program will print the random unsorted list and the random
sorted list. For better clarity purposes, for the sorted output file, different names will be used depending
on which way the user asked the unsorted list to be sorted, since he/she has 4 options to pick from. 
Furthermore, we also print the runtime calculation to the console. The user can record this data and see
how it differs when he/she uses other options with similar sizes. */


// All necessary classes imported
import java.util.*;
import java.io.IOException; //for error checking and input validation purposes
import java.io.PrintWriter; // writing to console and later file extensions for formatting
//formats representations of objects to a text-output stream. It implements all of the print methods found in PrintStream.
import java.io.File; //writing to files sorted and unsorted lists
import java.io.FileWriter; //same as above
import java.time.Duration; // calculating runtime

public class ProjectFive
{
   public static void OptionOne( int startVar, int endVar,int X[])
   {
	   /* The purpose of this method is to use the first element 
		   as the pivot and sort the method accordingly. */
		   
	   int x = startVar; //beginning of array
       int y = endVar; //end of the array
       if (endVar - startVar >= 1)
       {
           int pvt = X[startVar]; //set the first point as the pivot
		   // that is the whole purpose of this option
           while (y > x)
			   //continue as long as array is greater than size 1.
           {
               while (X[x] <= pvt && x <= endVar && y > x)
			   {
                   x++; //assuming x is starting point in array
				   // assuming pivot is greater than starting point
				   // moving starting point towards pivot so it becomes pivot
			   }
               while (X[y] > pvt && y >= startVar && y >= x)
			   {
                   y--; // opposite to above.
				   // reallocate and move decrement end node 
				   // so that pivot is allocated at the end.
			   }
			   
               if (y > x) //if ending point is greater than starting point
			   {
                   swapVars( x, y, X); //swap variables x and y in array
			   }
           }

           swapVars(startVar, y,X); //else swap starting point with ending point
           OptionOne( startVar, y - 1, X); //function call for second recursive check for method
           OptionOne(y + 1, endVar, X); //function call for third recursive check for method
       }
       else
       {
           return; //return nothing, error validation/checking is done below in main.
       }
   }

   public static void OptionTwo(int startVar, int endVar, int[] X)
   {
	   /* The purpose of this method will be to randomly choose the pivot element*/
	   
   	  if (startVar - endVar <= 0)
	  {
           return; //returns nothing is first element is less than last element.
	  }
      else
       {
           Random random = new Random(); //random class used since we have to find pivot randomly
           int pvtin = startVar + random.nextInt(endVar - startVar + 1);
		   //finds pivot randomly from array using method check validation.

           swapVars(pvtin, endVar,X);
  		   int pvt = X[endVar];
           int pointerMethod = pointerMethod(startVar, endVar, pvt, X);
		   
		   //Here we are enssuring the elements are swapped so that pivot is randomly selected
		   // pointer method is used to swap the pointers which hold the location of elements in array

           OptionTwo(startVar, pointerMethod - 1, X); //second recursive check for method
           OptionTwo(pointerMethod + 1, endVar, X); //third recursive check for method
       }

   }
   
   public static void randomChoiceOptionTwo( int startVar, int endVar,int[] X) 
   {
	   /* In this method we randomly choose our pivot and use that pivot to sort the 
		   array using in place quicksort principles */
       if (startVar >= endVar)
	   {
           return; 
	   }
       
	   int x = startVar; //variable declarations
       int y = endVar;
       int first = startVar; //variable declarations

       int nextVar = startVar < endVar ? startVar + 1 : endVar; //set next var by incrementing starting point
	   //only when starting point is less than ending point....advanced decision making principles
       int nextNextVar = startVar + 1 < endVar ? startVar + 2 : endVar;
       int pvt = Math.max(Math.min(X[first], X[nextVar]), Math.min(Math.max(X[first], X[nextVar]), X[nextNextVar]));
	   //pivot is set to the max value of minimum of first element, and each consecutive point in array

       while (x <= y)
       {
           while (X[x] < pvt) //if start is less than pivot
           {
               x++; //increment start point in array
           }

           while (X[y] > pvt) //if pivot is less than end point
           {
               y--; //decrement end point
 		   }
          
		   if (x <= y)
           {
         	   swapVars(x,y,X); //same principles adhered from previous method Option One.
               x++;
               y--;
           }
       }

       if (startVar < endVar)
	   {
           randomChoiceOptionTwo(startVar, endVar-1, X); //randomly choose variables used to calculate pivot
	   }
       if (startVar > endVar)
	   {
           randomChoiceOptionTwo(startVar+1, endVar,X); //randomly choose variables used to calculate pivot
	   }
   }

   public static void optionThree(int startVar, int endVar,int[] X) 
   {
	   //* Purpose of method is to ensure 3 randomly chosen variables determine pivot
       ArrayList<Integer> arrayListOne = new ArrayList<Integer>();
       int y = X.length;

	   //array list implementation to sort the values
       for (int x = 0; x < y; x++) 
	   {
           arrayListOne.add(X[x]); //add to arraylist
       }

       Collections.shuffle(arrayListOne);  //Shuffle method helps us ensure list is randomly sorted 
	   // as such, there is no bias to think it is already sorted in a way that might give advantage to 
	   //one of the options in the program
	   
       for (int x = 0; x < arrayListOne.size(); x++) 
	   {
           X[x] = arrayListOne.get(x).intValue(); //gets the value from the arraylist

       }
	   
	   randomChoiceOptionTwo( startVar, endVar,X); //the three variables used to choose the pivot randomly
   }

   public static void optionFour(int startVar, int endVar,int[] X) 
   {
       optionFourMethodTwo(startVar, endVar,X); //calls function below
   }

   public static void optionFourMethodTwo( int startVar, int endVar,int[] X) 
   {
	   /*Purpose of this method is to ensure that the median of the first center
		   and the last element is used to determine the pivot from the list*/
	   
       if (startVar >= endVar)
	   {
		   return;
       }
	   
	   int x = startVar; //variable declarations and instantiations
       int y = endVar; 
       int firstVar = startVar;
       int nextVar = (startVar+endVar)/2; //select median value between points
	   
       int nextNextVar = startVar + 1 < endVar ? startVar + 2 : endVar;
       int pvt = Math.max(Math.min(X[firstVar], X[nextVar]), Math.min(Math.max(X[firstVar], X[nextVar]), X[nextNextVar]));

       while (x <= y)
       {
           while (X[x] < pvt)
           {
               x++;
           }
   		   while (X[y] > pvt)
           {
 				y--;
           }

 			if (x <= y)
           {
              swapVars(x,y,X);
               x++;
               y--;
           }
       }

       //Sorting recursively. Book technique.
       if (startVar < endVar)
	   {
           optionFourMethodTwo(startVar, endVar-1,X); //select median when start of array is less than end of array
	   }
       if (startVar > endVar)
	   {
           optionFourMethodTwo(startVar+1, endVar,X); //select median when end of array is less than start of array
	   }
   }
   
   public static int pointerMethod(int leftVar, int rightVar, long pvt, int[] X)
   {
	   /*Method helps in Option 2 to randomly select our elements that determine pivot */
       int ptr1 = leftVar - 1; //variable declarations of left and right pointers
       int ptr2 = rightVar;

       while (true)
       {

           while (X[++ptr1] < pvt)
		   {
             ;
		   }
           while (ptr2 > 0 && X[--ptr2] > pvt)
		   {
             ;
		   }
           if (ptr1 >= ptr2)
		   {
               break;
		   }
           else
		   {
               swapVars(ptr1, ptr2,X); //swap accordingly
		   }
       }

       swapVars(ptr1, rightVar, X);
       return ptr1;
   }

   public static void swapVars(int num1, int num2, int[] X)
   {
	   /*This method is used to swap 2 elements in the list */
       int temporaryVariable = X[num1];
       X[num1] = X[num2];
       X[num2] = temporaryVariable;
   }

   static void printA(int[] sorted_list)
   {
	   /*Method used to print our array to console later in main */
       for (int i = 0; i < sorted_list.length; i++)
	   {
           System.out.print(sorted_list[i] + " ");
	   }
   }

   public static void main(String args[]) throws IOException
   {
	   //Get user input
       System.out.println("Enter the size");
       Random random = new Random();
       Scanner sc= new Scanner(System.in);
	   
	   //ask for user output
	   
	   while(!sc.hasNextInt()) 
	   {
	       sc.next();
		   System.out.println("You have entered an invalid size for the array. It must have an integer scope.");
		   System.out.println("Please enter the size of array again below.");
	   }
	   
	   int N = sc.nextInt();
       int[] arr = new int[N];
	   //array that stores and displays numbers

       for (int x = 0; x < N; x++)
	   {
           arr[x] = Math.abs(random.nextInt(100));
	   }
  
       int[] list= new int[N];
       list=arr;
	   System.out.println("\nOriginal Sequence: \n");
       printA(arr);
	   
	   //Print original random array and give user option to choose from following.
	   System.out.println("\n");
       System.out.println("\n1. First element as pivot.\n");
	   System.out.println("2. Randomly choosing the pivot element. \n");
 	   System.out.println("3. Choosing the median of 3 randomly chosen elements as the pivot. \n");
	   System.out.println("4. Median of first center and last element (book technique). \n");
	   
	   System.out.println("\nPlease provide how you want to sort this list from one of the options above:");
	   
	   Scanner sc1 = new Scanner(System.in); 
	   while(!sc1.hasNextInt()) 
	   {
	       sc1.next();
		   System.out.println("Please enter an integer value. The options are integers.");
	   }	 
		   
	   int c = sc1.nextInt(); 
	   
		//Try and catch to print the unsorted list to file named unsorted
		try {
				PrintWriter unsorted = new PrintWriter(new File("unsorted.txt"));
 
				for (int i = 0; i < arr.length; i++)
				unsorted.println(arr[i] + " ");
				unsorted.close();
			}
			
		catch(IOException e) 
		{
			System.out.println(e);
		}
			
		//Printing runtime to console	   
		long startTime = System.nanoTime();
		long finishTime = System.nanoTime();
		Duration elapsedTime = Duration.ofNanos(finishTime - startTime);
		//return elapsedTime;
	    System.out.println();
		System.out.println("Runtime is:" + elapsedTime);				       
       
	   switch(c) //switch case to determine which way to sort the pivot and list
       {
      	 case 1: //option 1
     	  {
           System.out.println("\n Taking first element as Pivot: ");
           OptionOne(0, N - 1,arr);
           printA(arr);
		   
		   try 
		   {		   
			   PrintWriter sorted = new PrintWriter(new FileWriter("sortedFirstOption.txt", true), true);
		  	   sorted.println("Randomly choosing the pvt element: ");
 
		   	 	for (int x = 0; x < arr.length; x++)
				{
		  		  sorted.println(arr[x] + " ");
			  	}
				
		   	 sorted.close();
		   }		   
		   catch(IOException e) 
		   {
		  	 System.out.println(e);
		   }
		   
           break;
       }

       case 2: //option 2
       {

           arr=list;
           System.out.println("\n choose pvt randomnly: ");
           OptionTwo(0, N - 1,arr);
           printA(arr);
		   
		   try 
		   {
			   PrintWriter sorted2 = new PrintWriter(new FileWriter("sortedSecondOption.txt", true), true);
		 	   sorted2.println("Randomly choosing the pvt element: ");
 
		 	  for (int x = 0; x < arr.length; x++)
			  {
				sorted2.println(arr[x] + " ");
			  }
		   
		   	sorted2.close();
		   }
		   catch(IOException e)
		   {
		   System.out.println(e);
		   }

           break;
       }

       case 3: //option 3
       {
           arr=list;
           System.out.println("\n median of 3 randomnly chosen elements: ");
           optionThree(0, N - 1,arr);
           printA(arr);
		   
		   try 
		   {
		  	 PrintWriter sorted3 = new PrintWriter(new FileWriter("sortedThirdOption.txt", true), true);
			 sorted3.println("Randomly choosing the pvt element: ");
 
		   	for (int x = 0; x < arr.length; x++)
			{
		   		sorted3.println(arr[x] + " ");
			}
		   
		   	sorted3.close();
		   }
		   catch(IOException e)
		   {
		   System.out.println(e);
		   }
		   
           break;
       }

       case 4: //option 4
       {
           arr=list;
           System.out.println("\n first center and last element: ");
           optionFour(0, N - 1,arr);
           printA(arr);
		   
		   try 
		   {
		  	 PrintWriter sorted4 = new PrintWriter(new FileWriter("sortedFourthOption.txt", true), true);
			   sorted4.println("Randomly choosing the pvt element: ");
 
		   	for (int x = 0; x < arr.length; x++)
		   	{
			 sorted4.println(arr[x] + " ");
		   	}
		   
		  	 sorted4.close();
		   }
		   catch(IOException e) 
		   {
		   System.out.println(e);
		   }

           break;
       }	   
       default:System.out.println("\n Please enter a valid option: 1, 2, 3, or 4.");
	   //In case user does not enter any of the valid outputs we use this default thread exception.
      }
   }
}