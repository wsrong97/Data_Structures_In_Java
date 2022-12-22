package Main;

import ErrorHandling.DataStructureException;
import List.ArrayList;
import List.LinkedList;
import Stack.Stack;

import java.util.HashMap;

public class MainStackDebug {
    public static void main(String[] argv) throws DataStructureException {
        Stack<Double, ArrayList<Double>> stack=new Stack<Double, ArrayList<Double>>(ArrayList<Double>::new);
        for (int i = 0; i < 10; i++) {
            stack.push((double)i);
        }
        System.out.println("stack="+stack);
        for (int i = 0; i < 5; i++) {
            stack.pop();
        }
        System.out.println("stack="+stack);
        for (int i = 0; i < 5; i++) {
            stack.push(Math.PI*(i+1));
        }
        System.out.println("stack="+stack);

        Stack<Double, LinkedList<Double>> stack1=new Stack<Double, LinkedList<Double>>(LinkedList<Double>::new);
        for (int i = 0; i < 10; i++) {
            stack1.push((double)i);
        }
        System.out.println("stack1="+stack1);
        for (int i = 0; i < 5; i++) {
            stack1.pop();
        }
        System.out.println("stack1="+stack1);
        for (int i = 0; i < 5; i++) {
            stack1.push(Math.PI*(i+1));
        }
        System.out.println("stack1="+stack1);
    }
}
