package com.clouway.abstractfactory.factorymethod;

/**
 * @author georgi.hristov@clouway.com
 */
public class DoorFactory implements HousePartsFactory{


  @Override
  public HousePartObject createObject() {
   return new Door();
  }
}
