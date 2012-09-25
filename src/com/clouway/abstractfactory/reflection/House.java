package com.clouway.abstractfactory.reflection;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * @author georgi.hristov@clouway.com
 */
public class House {

  private List<HousePart> parts;

  private HousePartFactory factory;

  public House( List<HousePart> parts,HousePartFactory factory) {

    this.parts = parts;

    this.factory = factory;

  }

  public void buildHouse() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

    parts.add(factory.createInstance("Door"));

    parts.add(factory.createInstance("Window"));

    parts.add(factory.createInstance("Ceiling"));

    parts.add(factory.createInstance("Floor"));

  }


}
