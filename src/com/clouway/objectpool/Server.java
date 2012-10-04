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

    Connection connection = new Inactive();

    for (Connection currentConnection : connections) {

      if (currentConnection.isAvailable()) {

        connection = currentConnection;

        currentConnection.setAvailability(false);

        break;

      }

    }

     isNull(connection);

     return connection;

   }


  public void release(Connection connectionToRelease) {

    Connection connection = null;

    for(Connection currentConnection : connections){

      if(connectionToRelease==currentConnection && !connectionToRelease.isAvailable()){

        connection = connectionToRelease;

        connection.setAvailability(true);

        break;

      } 
      
    }

    isNull(connection);

  }



  private void isNull(Connection connection) {

    if(connection == null){

       throw new UnknownConnectionException();

    }
  }
}
