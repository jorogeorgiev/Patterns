package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public class Inactive implements Connection {



  @Override
  public void setAvailability(boolean availabilityState) {


  }

  @Override
  public boolean isAvailable() {
    return false;
  }

   @Override
  public void exploit() {
  }
}
