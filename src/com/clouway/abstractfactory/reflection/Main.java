package com.clouway.abstractfactory.reflection;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author georgi.hristov@clouway.com
 */
public class Main {

  public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

    HousePartFactoryImpl factory = new HousePartFactoryImpl();

    String doorType = "Door";

    String windowType = "Window";

    Door door = (Door) factory.createInstance(doorType);

    Window window = (Window) factory.createInstance(windowType);

  }
}
