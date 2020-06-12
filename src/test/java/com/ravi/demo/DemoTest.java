package com.ravi.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DemoTest {
  private Demo demo;
  @Before
  public void init(){
    demo = new Demo();
  }
  @After
  public void tearUp(){

  }
  @Test
  public void test_01(){
     int result = demo.sampleMethod();
     assert result == -1;
  }
}
