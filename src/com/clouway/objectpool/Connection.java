package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public interface Connection {

  void setAvailability(boolean availabilityState);

  boolean isAvailable();

  void exploit();

}
