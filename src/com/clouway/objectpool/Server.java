package com.clouway.objectpool;

import java.util.List;

/**
 * @author georgi.hristov@clouway.com
 */
public class Server {

  private List<Connection> connections;


  public Server(List<Connection> connections){

     this.connections = connections;

  }


  Connection dispatchConnection() {

    Connection connectionToDispatch = new Inactive();

    for (Connection connection : connections) {

      if (connection.isAvailable()) {

        connection.defineAvailability(false);

        connectionToDispatch = connection;

      } else {

        connectionToDispatch = new Inactive();

      }

    }

     return connectionToDispatch;

   }


}
