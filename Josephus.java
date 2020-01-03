// Name: J4-18
// Date: 11/18/19

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      ListNode head, p;
      head = p = new ListNode("A", null);
      p.setNext(head);
      p = insert(p, "B");
      p = insert(p, "C");
      p = insert(p, "D");
      print(p);
        
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner infile = new Scanner(f);
      String b = infile.nextLine();
      ListNode a = new ListNode(b, null);
      a.setNext(a);
      for (int i = 0; i < n - 1; i++)
      {
         b = infile.nextLine();
         insert(a, b);
      }
      return a;
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      ListNode end = p.getNext(); 
      while(end.getNext() != p)
      {
         end = end.getNext();
      }
      ListNode node = new ListNode(obj, p);
      end.setNext(node);
      return p; 

   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      while(p.getNext() != p)
	   {
         print(p);
		   p = remove(p, count);
	   }  
      print(p);
	   return p;

   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      ListNode temp = p;
   	for(int i = 0; i < count - 2; i++)
	   {
		   temp = temp.getNext();
	   }
	   temp.setNext(temp.getNext().getNext());
	   return temp.getNext();

   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      ListNode start = p;
      System.out.print(p.getValue() + " ");
	   p = p.getNext();
	   while(p != start)
	   {
		   System.out.print(p.getValue() +" ");
		   p = p.getNext();
	   }
      System.out.println();

   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      for(int i = 0; i < pos - 1; i++)
	   {
		   p = p.getNext();
	   }
	   p.setValue(obj);
   }  
}

