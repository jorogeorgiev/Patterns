package com.clouway.singleton;

import org.junit.Test;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * @author georgi.hristov@clouway.com
 */
public class SingletonTest {



  @Test
  public void classCreatesOnlySingleInstance(){

    Singleton singleton = Singleton.getInstance();

    Singleton singleton1 = Singleton.getInstance();

    assertThat(singleton1,sameInstance(singleton));

  }

}
