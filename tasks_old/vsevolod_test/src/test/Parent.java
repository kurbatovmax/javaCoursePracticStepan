package test;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/10/13
 * Time: 11:24 PM
 */
abstract class Parent
{
  static   {
	System.out.println("Parent static initializer");
  }
  {
	System.out.println("Parent non static initializer");
  }
  static int i = 1;
  Parent()
  {
	System.out.println("Parent constructor");
    //virtual function call
    doIt();
    System.out.println("object class: "+this.getClass().getName());
  }

  static String  g() {
      return  "Parent";
  }

  public abstract void doIt();
}
