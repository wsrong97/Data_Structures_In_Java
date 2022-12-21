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
   }
}
