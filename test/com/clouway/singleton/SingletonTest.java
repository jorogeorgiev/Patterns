package com.clouway.singleton;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author georgi.hristov@clouway.com
 */
public class SingletonTest {



  @Test
  public void classCreatesOnlySingleInstance(){

    List<Singleton> singletonList = Lists.newArrayList();

    Singleton singleton = new Singleton();

    singletonList.add(singleton);

    Singleton singleton1 = new Singleton();

    singletonList.add(singleton1);

    assertThat(singletonList.get(0),is(singletonList.get(1)));
  }




}
