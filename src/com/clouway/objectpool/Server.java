package com.clouway.objectpool;

import java.util.List;

/**
 * @author georgi.hristov@clouway.com
 */
public class Server {


  private List<TypeConnection> connections;

  int availableConnection = 0;

  public Server(List<TypeConnection> connections) {

    this.connections = connections;

  }

  Connection dispatchConnection() {

    Connection connection = new RefusedConnection();

    for (TypeConnection currentConnection : connections) {

      if (currentConnection.isAvailable()) {

        connection = currentConnection;

        currentConnection.setAvailability(false);

        break;

      }

    }

    isNull(connection);

    return connection;

  }


  public void release(TypeConnection connectionToRelease) {

    TypeConnection connection = null;

    for(TypeConnection currentConnection : connections){

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
