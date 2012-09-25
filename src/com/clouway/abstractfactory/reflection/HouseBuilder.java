package com.clouway.abstractfactory.reflection;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author georgi.hristov@clouway.com
 */
public class HouseBuilder {

  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {


    List<HousePart> parts = Lists.newArrayList();

    HousePartFactoryImpl factory = new HousePartFactoryImpl();

    House house = new House(parts,factory);

    house.buildHouse();




  }



}
