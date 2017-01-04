import java.util.InputMismatchException;
import java.util.Iterator;

/**
 * This class creates rational numbers that will be use for the postfixed Rational Calculator
 * Application.
 * It extends the Number class, and it implements the Comparable interface.
 **/
class Rational extends Number implements Comparable<Rational> {
  // Data fields for numerator and denominator
  private long numerator = 0;
  private long denominator = 1;

  /**
   * Rational default Constructor 
   * creates a rational number with default properties
   **/
  public Rational() {
    this(0, 1);
  }

  /**
   * Rational non-default constructor
   * creates a rational number from a string
   * @param word string to parse into a rational number
   **/
  public Rational(String word){
      setRationalSplit(word);
  }

  /**
   * Rational non-default constructor
   * creates a rational using specified numerator and denominator
   * @param n rational number numerator
   * @param d rational number denominator
   **/
  public Rational(long n, long d) {
    long gcd = gcd(n, d);
    this.numerator = ((d > 0) ? 1 : -1) * n / gcd;
    this.denominator = Math.abs(d) / gcd;
  }

  /**
   * setRationalSplit method
   * @param word input to parse and create the numerator and denominator
   * @return a rational number
   * @throws InputMismatchException
   **/
  public Rational setRationalSplit(String word)throws InputMismatchException{
    long n = 0;
    long d = 0;
    String[] temp = word.split("/");

        if(temp.length == 2){
            n= Long.parseLong(temp[0]);
            d= Long.parseLong(temp[1]);
        }else if(temp.length == 1){
            n= Long.parseLong(temp[0]);
            d= 1;
        }else{
            throw new RuntimeException("Unknown Rational word->");
        }

        long gcd = gcd(n, d);
        this.numerator = ((d > 0) ? 1 : -1) * n / gcd;
        this.denominator = Math.abs(d) / gcd;

        if(n == 0){
            this.denominator = 1;
        }

    return this;
  }

  /**
   * gcd method
   * @param n rational number numerator
   * @param g rational number denominator
   * @return the greatest common denonminator
   * @throws ArithmeticException
   **/
  private static long gcd(long n, long d)throws ArithmeticException {
    if(d == 0){
        throw new ArithmeticException("ArithmeticException / 0");
    }
    long n1 = Math.abs(n);
    long n2 = Math.abs(d);
    int gcd = 1;

    for (int k = 1; k <= n1 && k <= n2; k++) {
      if (n1 % k == 0 && n2 % k == 0)
        gcd = k;
    }
    return gcd;
  }

  /**
   * getNumerator method
   * @return rational number numerator
   **/
  public long getNumerator() {
    return numerator;
  }

  /**
   * getNumerator method
   * @return rational number denominator
   **/
  public long getDenominator() {
    return denominator;
  }

   /**
    * plus method
    * @param secondRational number to add
    * @return the addition result of two rational numbers
    **/
   public Rational plus(Rational secondRational) {
    
     long n = numerator * secondRational.getDenominator() +
              denominator * secondRational.getNumerator();
     long d = denominator * secondRational.getDenominator();
    
     return new Rational(n, d);
   }
  
  /**
   * minus method
   * @param secondRational number to substract
   * @return the substraction result of two rational numbers
   **/
  public Rational minus(Rational secondRational) {
    
    long n = numerator * secondRational.getDenominator() -
             denominator * secondRational.getNumerator();
    long d = denominator * secondRational.getDenominator();
    
    if(n == 0){
        return new Rational(0, 1);
    }
    return new Rational(n, d);
  }

  /**
   * multiply method
   * @param secondRational number to multiply
   * @return the multiplication result of two rational numbers
   **/
  public Rational multiply(Rational secondRational) {
    
    long n = numerator * secondRational.getNumerator();
    long d = denominator * secondRational.getDenominator();
    
    return new Rational(n, d);
  }

  /**
   * divide method
   * @param secondRational number to divide
   * @return the division result of two rational numbers
   **/
  public Rational divide(Rational secondRational) {
    long n = numerator * secondRational.getDenominator();
    long d = denominator * secondRational.numerator;
    return new Rational(n, d);
  }

  /**
   * toString method
   * @return string containing all elements in the stack
   **/
  @Override
  public String toString() {
    if (denominator == 1)
      return numerator + "";
    else
      return numerator + "/" + denominator;
  }

  /**
   * equals method
   * @param compares with second element
   * @return boolean value of equality
   **/
  @Override
  public boolean equals(Object other) {
    if ((this.minus((Rational)(other))).getNumerator() == 0)
      return true;
    else
      return false;
  }

  /**
   * intValue method
   * @return integer value cast from Number Class
   **/
  @Override
  public int intValue() {
    return (int)doubleValue();
  }

  /**
   * floatValue method
   * @return float value cast from Number Class
   **/
  @Override
  public float floatValue() {
    return (float)doubleValue();
  }

  /**
   * doubleValue method
   * @return double value cast from Number Class
   **/
  @Override
  public double doubleValue() {
    return numerator * 1.0 / denominator;
  }

  /**
   * longValue method
   * @return long value cast from Number Class
   **/
  @Override
  public long longValue() {
    return (long)doubleValue();
  }

  /**
   * compareTo method
   * @return the result of the implementation of the compareTo metho in Comparable
   **/
  @Override
  public int compareTo(Rational o) {
    if (this.minus(o).getNumerator() > 0)
      return 1;
    else if (this.minus(o).getNumerator() < 0)
      return -1;
    else
      return 0;
  }
}
