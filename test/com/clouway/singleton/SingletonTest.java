package com.clouway.singleton;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
