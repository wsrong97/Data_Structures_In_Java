package Main;
import ErrorHandling.DataStructureException;
import List.LinkedList;
public class MainLinkedListDebug {
   public static void main(String argv[]) throws DataStructureException {
      LinkedList<Integer> linkedList=new LinkedList<Integer>();
      for (int i = 0; i < 10; i++) {
         linkedList.append(i);
      }
      System.out.println("linkedList="+linkedList);
      System.out.println("linkedList.isEmpty()="+linkedList.isEmpty());
      System.out.println("linkedList.size()="+linkedList.size());
      System.out.println("linkedList.find(5)="+linkedList.find(5));
      LinkedList<Integer> linkedList1=new LinkedList<Integer>(linkedList);
      System.out.println("linkedList1="+linkedList1);
      linkedList1.insert(2,3);
      System.out.println("linkedList1="+linkedList1);
      System.out.println("linkedList1.findAll(3)="+linkedList1.findAll(3));
      linkedList1.set(3,100);
      System.out.println("linkedList1="+linkedList1);
      linkedList1.remove(7);
      System.out.println("linkedList1="+linkedList1);
      linkedList1.remove(0);
      System.out.println("linkedList1="+linkedList1);
      linkedList1.remove(linkedList1.size()-1);
      System.out.println("linkedList1="+linkedList1);
      linkedList1.insert(0,91);
      System.out.println("linkedList1="+linkedList1);
      linkedList1.insert(linkedList.size()-1,92);
      System.out.println("linkedList1="+linkedList1);
      System.out.println("linkedList1.get(3)="+linkedList1.get(3));
      LinkedList<Integer> ll=new LinkedList<Integer>();
      for (int i = 0; i < 10; i++) {
         ll.append(i);
      }
      System.out.println("ll="+ll);
      System.out.println("ll.size="+ll.size());
      ll.resize(5);
      System.out.println("ll="+ll);
      System.out.println("ll.size="+ll.size());
      ll.resize(10);
      System.out.println("ll="+ll);
      System.out.println("ll.size="+ll.size());
      for (int i = 0; i < 10; i++) {
         ll.remove(0) ;
      }
      System.out.println("ll="+ll);
      System.out.println("ll.size="+ll.size());
      for (int i = 0; i < 15; i++) {
         ll.append(i);
      }
      for (int i = 0; i < 15; i++) {
         ll.insert(0,i);
      }
      System.out.println("ll="+ll);
   }
}
