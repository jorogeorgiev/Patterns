package com.clouway.objectpool;

import java.util.List;

/**
 * @author georgi.hristov@clouway.com
 */
public class Server {

  private List<PoolingConnection> connections;

  public Server(List<PoolingConnection> connections) {

    this.connections = connections;

  }

  Connection dispatchConnection() {

    Connection connection = new RefusedConnection();

    for (PoolingConnection currentConnection : connections) {

      if (currentConnection.isAvailable()) {

        connection = currentConnection;

        currentConnection.setAvailability(false);

        break;

      }

    }

    return connection;

  }


  public void release(PoolingConnection connectionToRelease) {

    PoolingConnection connection = null;

    for(PoolingConnection currentConnection : connections){

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

      throw new NoSuchPoolingConnetionException();

    }
  }

}
