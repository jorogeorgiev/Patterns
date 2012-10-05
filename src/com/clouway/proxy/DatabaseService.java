package com.clouway.proxy;

/**
 * @author georgi.hristov@clouway.com
 */
public interface DatabaseService {

  String read();

  void write(String data);

}
