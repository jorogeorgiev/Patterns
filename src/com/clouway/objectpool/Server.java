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

        break;

      } else {

        connectionToDispatch = new Inactive();

      }

    }

     return connectionToDispatch;

   }


  public void release(Connection connectionToRelease) {

    Connection abs =null;

    for(Connection connection : connections){

      if(connectionToRelease==connection && !connectionToRelease.isAvailable()){

        abs = connectionToRelease;

      }

    }

    if(abs!=null){

      abs.defineAvailability(true);

    } else{

      throw new UnknownConnectionException();

    }



  }
}
