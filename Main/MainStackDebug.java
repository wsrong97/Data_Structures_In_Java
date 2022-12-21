package Main;

import ErrorHandling.DataStructureException;
import List.ArrayList;
import Stack.Stack;
public class MainStackDebug {
    public static void main(String[] argv) throws DataStructureException {
        Stack<Double, ArrayList<Double>> stack=new Stack<Double, ArrayList<Double>>();
        for (int i = 0; i < 10; i++) {
            stack.push((double)i);
        }
        System.out.println("stack="+stack);
    }
}
