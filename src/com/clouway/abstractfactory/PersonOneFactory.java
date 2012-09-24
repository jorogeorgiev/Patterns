package com.clouway.abstractfactory;

/**
 * @author georgi.hristov@clouway.com
 */
public class PersonOneFactory implements PersonFactory {

  @Override
  public Person createInstance() {

    return new PersonImplOne();

  }
}
