package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public interface ConnectionType extends Connection{

  void setAvailability(boolean availabilityState);

  boolean isAvailable();

  public void use();

}
