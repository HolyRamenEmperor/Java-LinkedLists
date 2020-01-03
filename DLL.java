// Name: J4-18
// Date: 11/20/19

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head.getNext();
      for (int i = 0; i < index; i++)
      {
         pointer = pointer.getNext();
      }
      DLNode node = new DLNode(obj, pointer.getPrev(), pointer);
      pointer.setPrev(node);
      pointer.getPrev().getPrev().setNext(node);
      size++;                              
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head.getNext();
      for (int i = 0; i < index; i++)
      {
         pointer = pointer.getNext();
      }
      return pointer.getValue();
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head.getNext();
      for (int i = 0; i < index; i++)
      {
         pointer = pointer.getNext();
      }
      Object target = pointer.getValue();
      DLNode node = new DLNode(obj, pointer.getPrev(), pointer.getNext());
      pointer.getPrev().setNext(node);
      pointer.getNext().setPrev(node);
      return target;

   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head.getNext();
      for (int i = 0; i < index; i++)
      {
         pointer = pointer.getNext();
      }
      Object target = pointer.getValue();
      pointer.getPrev().setNext(pointer.getNext());
      pointer.getNext().setPrev(pointer.getPrev());
      size--;
      return target;
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      DLNode insert = new DLNode(obj, head, head.getNext());
      insert.getNext().setPrev(insert);
      head.setNext(insert);
      size++;
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      DLNode insert = new DLNode(obj, head.getPrev(), head);
      insert.getPrev().setNext(insert);
      head.setPrev(insert);
      size++;
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      if(head.getNext() == null)
      {
         return null;
      }
      Object first = head.getNext().getValue(); 
      head.setNext(head.getNext().getNext());
      head.getNext().setPrev(head);
      size--;
      return first;     
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(head.getNext() == null)
      {
         return null;
      }
      Object last = head.getPrev().getValue();
      head.setPrev(head.getPrev().getPrev());
      head.getPrev().setNext(head);
      size--;
      return last;
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      DLNode pointer = head;
      String str = "[";
      while(pointer.getNext() != head)
      {
         pointer = pointer.getNext();
         str += pointer.getValue(); 
         if(pointer.getNext() != head)
         {
            str += ", ";
         }
      }
      str += "]";
      return str; 
   }
}