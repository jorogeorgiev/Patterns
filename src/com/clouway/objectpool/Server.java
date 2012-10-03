package com.clouway.objectpool;

import java.util.List;

/**
 * @author georgi.hristov@clouway.com
 */
public class Server {

  private List<Connection> availableConnections;

  private Connection connection;

  private int index = 0;


  public Server(List<Connection> availableConnections){

    this.availableConnections = availableConnections;

  }


  Connection dispatchConnection() {

    if (index < availableConnections.size() && availableConnections.size()>1) {

      connection = availableConnections.get(index);

      index++;

      return connection;

    } else {

      return new Inactive();

    }


  }


}
