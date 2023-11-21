package test;

import org.junit.Test;

public class simpleTest {
    @Test
    public void testAdd(){
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(1,2));
    }

}
