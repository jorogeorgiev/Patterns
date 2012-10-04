package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public class RefusedConnection implements Connection{


  @Override
  public void use() {
    // TO SMTH - probably ask the client to retry to access Connection.
  }






}
