package com.clouway.abstractfactory.reflection;

import java.lang.reflect.InvocationTargetException;

/**
 * @author georgi.hristov@clouway.com
 */
public class HousePartFactoryImpl implements HousePartFactory {
 Object part;
  @Override
  public Object createInstance(String partType) {
    try {
     part = Class.forName("com.clouway.abstractfactory.reflection."+partType).getConstructor().newInstance();
    } catch (InstantiationException ignored) {

    } catch (IllegalAccessException ignored) {

    } catch (InvocationTargetException ignored) {

    } catch (NoSuchMethodException ignored) {

    } catch (ClassNotFoundException ignored) {

    }
    return part;
  }
}
