package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public class Active implements Connection{

  private boolean available =true;

  @Override
  public void setAvailability(boolean availabilityState) {

    this.available =availabilityState;

  }

  public boolean isAvailable(){

    return available;

  }

  @Override
  public void exploit() {
  }

}
