package com.clouway.objectpool;

import java.util.List;

/**
 * @author georgi.hristov@clouway.com
 */
public class Server {

  private List<ConnectionType> connections;

  public Server(List<ConnectionType> connections) {

    this.connections = connections;

  }

  Connection dispatchConnection() {

    Connection connection = new RefusedConnection();

    for (ConnectionType currentConnection : connections) {

      if (currentConnection.isAvailable()) {

        connection = currentConnection;

        currentConnection.setAvailability(false);

        break;

      }

    }

    isNull(connection);

    return connection;

  }


  public void release(ConnectionType connectionToRelease) {

    ConnectionType connection = null;

    for(ConnectionType currentConnection : connections){

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

      throw new NoSuchConnectionException();

    }
  }

}
