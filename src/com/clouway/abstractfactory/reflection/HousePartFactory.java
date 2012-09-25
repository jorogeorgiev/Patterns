package com.clouway.abstractfactory.reflection;

/**
 * @author georgi.hristov@clouway.com
 */
public interface HousePartFactory {
  Object createInstance(String partType);
}
