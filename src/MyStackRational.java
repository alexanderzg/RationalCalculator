import java.util.Iterator;
import java.util.Scanner;

/**
 * This class creates a stack of rational numbers that will be use for the postfixed Rational Calculator
 * Application.
 * It extends MystackGeneric class and performs basic arithmetic operations.
 **/
public class MyStackRational extends MyStackGeneric<Rational> {

    //Default constructor
    public MyStackRational(){
    }
    
    /**
     * main method
     * runs the initial input using the super readInput method
     * @param args the command line arguments
     **/
    public static void main(String[]args){
        MyStackRational temp = new MyStackRational(); 
        temp.readInput(new Scanner(System.in), true);  
    }
    
    /**
     * minus method
     * @param o1 left element to perform a subtraction
     * @param o2 right element to subtract from the left element
     * @return the result of o1 - o2 
     **/
    @Override
    protected Rational minus(Rational o1, Rational o2) {
        return o2.minus(o1);
    }

    /**
     * plus method
     * @param o1 left element to perform addition
     * @param o2 right element to add to the left element
     * @return the result of o1 + o2
     **/
    @Override
    protected Rational plus(Rational o1, Rational o2) {
         return o1.plus(o2);
    }

    /**
     * sum method
     * @return the sum of all elements in the rational stack
     **/
    protected String sum(){
        Rational sum = new Rational();
        Iterator it = this.listIterator();
        StringBuilder strbu = new StringBuilder();
        
        while(it.hasNext()){
            sum = sum.plus((Rational)it.next());
        }
        
        strbu.append(sum);
        return strbu.toString();
    }
    
    /**
     * inverse method
     * @param s element to perform inversion
     * @return the number inverted
     **/
    protected String inverse(String s){
        StringBuilder str = new StringBuilder();
        Rational temp = new Rational(s);
        long n = temp.getNumerator();
        long d = temp.getDenominator();
        //Rational temp2 = new Rational(d, n);
        return str.append(new Rational(d, n)).toString();
    }
    
    /**
     * multiply method
     * @param o1 left element to perform multiplication
     * @param o2 right element to multiply against the left element
     * @return the multiplication result of o1 * o2
     **/
    @Override
    protected Rational multiply(Rational o1, Rational o2) {
         return o1.multiply(o2);
    }

    /**
     * newElement method
     * @param w initial value
     * @return initializes a new element to value w
     **/
    @Override
    protected Rational newElement(String w) {
        Rational temp2 = new Rational();
        return temp2.setRationalSplit(w);               
    }

    /**
     * zero method
     * @return initializes a new element to a zero value
     **/
    @Override
    protected Rational zero() {
        return new Rational(0, 0);
    }
    
}
