package com.clouway.abstractfactory.factorymethod;

/**
 * @author georgi.hristov@clouway.com
 */
public class WindowFactory implements HousePartFactory {
  @Override
  public HousePart createPart() {
    return new Window();
  }
}
