package com.clouway.abstractfactory.reflection;

/**
 * @author georgi.hristov@clouway.com
 */
public class HousePartFactoryImpl implements HousePartFactory {
  @Override
  public HousePart createInstance(String partType) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    return (HousePart) Class.forName(this.getClass().getPackage().getName() + "." + partType).newInstance();
  }
}
