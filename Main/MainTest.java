package Main;

public class MainTest {
    public static class Test{
        public Test(int first, int second){
            a=first;
            b=second;
        }
        public Test(Test test){
            a=test.a;
            b=test.b;
        }
        public int a=0;
        public int b=100;
    }
    public static void main(String[] argv){
        int a=0;
        int b=0;
        a=1;
        System.out.println(b);
        Integer c=0;
        Integer d=c;
        c=1;
        System.out.println(d);
        Test t=new Test(1,2);
        Test t1=t;
        System.out.println(t1.a+" "+t1.b);
        t.a=15;
        t.b=20;
        System.out.println(t1.a+" "+t1.b);
        Test t2=new Test(t);
        System.out.println(t2.a+" "+t2.b);
        t.a=5;
        t.b=2;
        System.out.println(t2.a+" "+t2.b);
    }
}
