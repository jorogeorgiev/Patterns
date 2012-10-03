package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public class Active implements Connection{

  private boolean availabilityState =true;

  @Override
  public void defineAvailability(boolean availabilityState) {

    this.availabilityState=availabilityState;

  }

  public boolean isAvailable(){

    return availabilityState;

  }

  @Override
  public void exploit() {
  }

}
