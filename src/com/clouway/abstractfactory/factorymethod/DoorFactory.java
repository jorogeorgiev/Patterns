package com.clouway.abstractfactory.factorymethod;

/**
 * @author georgi.hristov@clouway.com
 */
public class DoorFactory implements HousePartFactory {


  @Override
  public HousePart createPart() {
   return new Door();
  }
}
