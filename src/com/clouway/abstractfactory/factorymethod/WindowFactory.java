package com.clouway.abstractfactory.factorymethod;

/**
 * @author georgi.hristov@clouway.com
 */
public class WindowFactory implements  HousePartsFactory {
  @Override
  public HousePartObject createObject() {
    return new Window();
  }
}
