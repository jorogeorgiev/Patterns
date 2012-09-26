package com.clouway.singleton;

import com.sun.xml.internal.ws.server.SingletonResolver;

/**
 * @author georgi.hristov@clouway.com
 */
public class Singleton {
  private static Singleton instance = new Singleton();

  private Singleton(){


  }

  public static Singleton getInstance(){

    return  instance;

  }


}
