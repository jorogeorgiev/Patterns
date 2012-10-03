package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public interface Connection {

  void defineAvailability(boolean availabilityState);

  boolean isAvailable();

  void exploit();
}
