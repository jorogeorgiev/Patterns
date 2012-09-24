package com.clouway.abstractfactory.factorymethod;

/**
 * @author georgi.hristov@clouway.com
 */
public class FloorFactory implements HousePartsFactory {
  @Override
  public HousePartObject createObject() {
    return new Floor();
  }
}
