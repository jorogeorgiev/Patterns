package com.clouway.abstractfactory.factorymethod;

/**
 * @author georgi.hristov@clouway.com
 */
public class FloorFactory implements HousePartFactory {
  @Override
  public HousePart createPart() {
    return new Floor();
  }
}
