package com.clouway.abstractfactory.factorymethod;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Scanner;

/**
 * @author georgi.hristov@clouway.com
 */
public class House {

  public static void main(String[] args) {

    HousePartFactory factory;

    List<HousePart> parts = Lists.newArrayList();

    Scanner scanner = new Scanner(System.in);

    while(!(scanner.next()).equals(".")){
    if((scanner.nextLine()).equals("window")){
      factory = new WindowFactory();
      parts.add(factory.createPart());
    }  else if((scanner.nextLine()).equals("door")){
      factory = new DoorFactory();
      parts.add(factory.createPart());
    } else if((scanner.nextLine()).equals("floor")){
      factory = new FloorFactory();
      parts.add(factory.createPart());
    }

    }


  }

}
