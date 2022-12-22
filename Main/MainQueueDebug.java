package Main;

import ErrorHandling.DataStructureException;
import List.ArrayList;
import List.LinkedList;
import Queue.Queue;

public class MainQueueDebug {
    public static void main(String[] argv) throws DataStructureException {
        Queue<Double, ArrayList<Double>> queue=new Queue<Double, ArrayList<Double>>(ArrayList::new);
        for (int i = 0; i < 10; i++) {
            queue.push((double)i);
        }
        System.out.println("queue="+queue);
        for (int i = 0; i < 5; i++) {
            queue.pop();
        }
        System.out.println("queue="+queue);
        for (int i = 0; i < 5; i++) {
            queue.push(Math.PI*(i+1));
        }
        System.out.println("queue="+queue);

        Queue<Double, LinkedList<Double>> queue1=new Queue<Double, LinkedList<Double>>(LinkedList::new);
        for (int i = 0; i < 10; i++) {
            queue1.push((double)i);
        }
        System.out.println("queue1="+queue1);
        for (int i = 0; i < 5; i++) {
            queue1.pop();
        }
        System.out.println("queue1="+queue1);
        for (int i = 0; i < 5; i++) {
            queue1.push(Math.PI*(i+1));
        }
        System.out.println("queue1="+queue1);
    }
}
