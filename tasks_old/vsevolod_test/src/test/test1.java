package test;

//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


public  class test1 extends Parent
{
  static int i = 2;
  static   {
	System.out.println("Static initializer");
  }
  {
	System.out.println("Non static initializer");
  }

  public test1()
  {
	//super();
	System.out.println("Constructor");
  }

  public static void main(String[] args) {
    System.out.println("main()");
    test.test1 m = new test.test1();
    Parent p = m;
    final int k=10;

    Parent main1 = new test.test1()
        {
         	{
                System.out.println("Anonymous initializer");
            }

 		    public void doIt() {
//              System.out.println("super: ");
//              super.doIt();
              System.out.println("Anonymous doIt() "+k);
//              new Throwable().printStackTrace(System.out);
        }
  	};

//    JButton b = new JButton("one");
//    b.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            ((JButton)e.getSource() ).setText("two");
//            //To change body of implemented methods use File | Settings | File Templates.
//        }
//    });

    System.out.println(p.i+ "  "+m.i);
    p.doIt();
    main1.doIt();
    System.out.println(g());
   // System.out.println(p.getClass().getName());
    //System.out.println(main1.getClass().getName()
    //  + "super: "+main1.getClass().getSuperclass().getName()
   // );

 }

  public void doIt() {
      System.out.println("Main.doIt()");
  }
}