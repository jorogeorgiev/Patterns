package com.clouway.abstractfactory.reflection;

/**
 * @author georgi.hristov@clouway.com
 */

public interface HousePartFactory {

   HousePart createInstance(String partType);

}
