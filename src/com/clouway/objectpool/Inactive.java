package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public class Inactive implements Connection {
  @Override
  public void defineAvailability(boolean availabilityState) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean isAvailable() {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

   @Override
  public void exploit() {
  }
}
