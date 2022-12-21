package Main;

import ErrorHandling.DataStructureException;
import List.ArrayList;

public class MainArrayListDebug {
    public static void main(String[] args) throws DataStructureException {
        //test1
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            arrayList.append(i);
        }
        System.out.println(arrayList);
        arrayList.insert(0,50);
        arrayList.insert(1,20);
        arrayList.insert(3,1000);
        System.out.println(arrayList);
        arrayList.remove(0);
        arrayList.remove(arrayList.size()-1);
        arrayList.remove((arrayList.size()-1)/2);
        System.out.println(arrayList);
        ArrayList<Double> arrayList1 = new ArrayList<Double>(100);
        System.out.println(arrayList1.isEmpty());
        System.out.println(arrayList1.find(19.));
        System.out.println("arrayList1.size="+arrayList1.size());
        for (int i = 0; i < 5; i++) {
            arrayList1.append((double)i);
        }
        ArrayList<Double> arrayList2=new ArrayList<Double>(arrayList1);
        System.out.println(arrayList2);
        System.out.println("arrayList2.size="+arrayList2.size());
        arrayList2.insert(4,0.);
        ArrayList<Integer> allPos=arrayList2.findAll(0.);
        System.out.println(arrayList2);
        System.out.println("allPos in arrayList2=");
        System.out.println(allPos);
    }
}
