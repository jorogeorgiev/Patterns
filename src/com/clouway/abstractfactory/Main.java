package com.clouway.abstractfactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author georgi.hristov@clouway.com
 */
public class Main {

  public static void main(String[] args) {

    List<PersonFactory> factories = new ArrayList<PersonFactory>();
    PersonFactory factory;

    Scanner scanner = new Scanner(System.in);
    if(scanner.nextLine().equals("1")){

      factory = new PersonOneFactory();
      factories.add(factory);

    } else{

      factory = new PersonTwoFactory();
      factories.add(factory);

    }

     Person person = factories.get(0).createInstance();


    System.out.println(person instanceof PersonImplOne);

    System.out.println(person instanceof PersonImplTwo);

  }
}
