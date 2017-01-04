import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class creates a generic stack that will be use for the postfixed Rational Calculator Application.
 * It extends the ArrayList class and performs basic arihtmetic operations.
 **/
abstract class MyStackGeneric<E> extends ArrayList<E>{
    //Variables
    int count = 0;
    
    /**
     * MyStackGeneric default constructor
     **/
    public MyStackGeneric(){
    }
    
    /**
     * readInput method
     * determines the appropriate path execution based on input value
     * @param input value to be pushed into the stack
     * @return string to print
     * @throws NumberFormatException, InputMismatchException
     **/
    public String readInput(java.util.Scanner input, boolean toSystemOut){
        //variables
        StringBuilder strB = new StringBuilder();
        String output;
        
        //reads the input until finished
        while(input.hasNext()){
            String userInput = input.next();
            
            //determines if the value is a number or an operator
            try{
                E c = (E)newElement(userInput);
                push(c);
                continue;
            }catch(NumberFormatException | InputMismatchException ex){ 

            }  
            try{
                if(userInput.length() > 1){
                    throw new InputMismatchException();
                }
                
                //determines what method to execute based on the type of operator
                switch(userInput){
                    case "+":
                    case "*":
                    case "-": binaryOperator(userInput);
                              break;
                    case "p": output = this.toString();
                        strB.append(output);
                        if( toSystemOut){
                            System.out.print(this.toString());
                        }
                        break;
                    default: throw new InputMismatchException();                   
                }

            }catch(Exception e){
                output = "Exception: " + e.getMessage() + "\n";
                strB.append(output);
                if(toSystemOut){
                    System.out.println(output);
                }
            }
        }
        //System.out.print("THIS.TOSTRING: " + this.toString());
        input.close();
        return strB.toString();   
    }
    
    /**
     * binaryOperator method
     * determines the apropriate arithmetic operation to be performed
     * @param operator to performs arithmetic operation
     * @return result of arithmetic operation
     * @throws ArithmeticException
     **/
    public E binaryOperator( String operator)throws ArithmeticException{
        if(super.size() < 2){
            throw new ArithmeticException("Need two operands");
        }
        E result = pop();
        switch( operator){
            case "+": result = (E)plus(pop(), result);
                      break;
            case "-": result = (E)minus(pop(), result);
                      break;
            case "*": result = (E)multiply(pop(), result);
                      break;
            default: throw new ArithmeticException( "Unknown operator");
        }
        push( result );
        return result;
    }
                
    /**
     * getSize method
     * @return size of the array
     **/
    public int getSize(){
        return count;
    }
    
    /**
     * clear method
     * resets the array back to a clear state
     **/
    @Override
    public void clear(){
        count = 0;
        super.clear();
    }

    /**
     * push method
     * @param i - element to push into the stack
     **/
    public void push(E i){
        this.add(i);
        count++;
    }
    
    /**
     * pop method
     * @return element popped
     **/
    public E pop(){
        E o = this.get(this.size()-1);
        this.remove(this.size()-1);
        count--;
        return o;
    }
    
    /**
     * peek method
     * @return top element on the stack
     **/
    public E peek(){
        E o = this.get(this.size()-1);
        return o;
    }
    
    /**
     * print method
     * prints all elements in the stack
     **/
    public void print(){
        System.out.println(toString());
    }
    
    /**
     * toString method
     * @return string containing all elements in the stack
     **/
    @Override
    public String toString(){
        
        StringBuilder strbu = new StringBuilder();
        Iterator it = this.listIterator();
        int temp = this.size();
        while(it.hasNext()){

            //if(temp-- > 1){
                strbu.append(" ");
            //}
            strbu.append(it.next());
        }
        strbu.append("\n");
        return strbu.toString();
    }
        
    /**
     * minus abstract method
     * substracts any two elements from the stack
     * @param o1 left element to perform a substraction
     * @param o2 right element to substract from the left element
     **/
    protected abstract E minus(E o1, E o2);

    /**
     * plus abstract method
     * adds any two elements from the stack
     * @param o1 left element to perform addition
     * @param o2 right element to add to the left element
     **/
    protected abstract E plus(E o1, E o2);

    /**
     * multiply abstract method
     * multiplies any two elements from the stack
     * @param o1 left element to perform multiplication
     * @param o2 right element to multiply against the left element
     **/
    protected abstract E multiply(E o1, E o2);
    
    /**
     * newElement abstract method
     * initializes a new element to a generic value w
     * @param w initial value
     **/
    protected abstract E newElement(String w);
    
    /**
     * zero abstract method
     * initializes a new element to a generic zero value
     **/
    protected abstract E zero();
    
}/***End of MyStackGeneric***/
