package com.clouway.abstractfactory;

/**
 * @author georgi.hristov@clouway.com
 */
public class PersonTwoFactory implements PersonFactory {


  @Override
  public Person createInstance() {

    return new PersonImplTwo();

  }


}
