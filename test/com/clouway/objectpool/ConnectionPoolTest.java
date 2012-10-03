package com.clouway.objectpool;

import com.google.common.collect.Lists;
import org.junit.Test;
import sun.org.mozilla.javascript.internal.Context;

import java.util.List;
import static org.junit.Assert.assertTrue;


/**
 * @author georgi.hristov@clouway.com
 */


public class ConnectionPoolTest {

  private Server server;

  interface CallBack{

    Connection getConnection();

  }

  class CreateNewInstance implements CallBack{


    @Override
    public Connection getConnection() {
      return new Active();
    }
  }


  class InstanceFromServer implements CallBack{

    @Override
    public Connection getConnection() {
      return server.dispatchConnection();
    }

  }



  @Test
  public void serverDispatchesActiveConnectionToClient(){

    server = new Server(setUpAvailableConnections(1));

    assertTrue(acquireConnections(1).get(0) instanceof Active);

  }




  @Test
  public void serverReturnsInactiveConnectionWhenActivesAreExceeded(){

    server = new Server(setUpAvailableConnections(10));

    assertTrue(acquireConnections(20).get(11) instanceof Inactive);

  }



  @Test
  public void serverReturnsInactiveConnectionWhenNoActivesAreSet(){

    server = new Server(setUpAvailableConnections(0));

    assertTrue(acquireConnections(1).get(0) instanceof Inactive);

  }




  private List<Connection> acquireConnections(int connectionsToAcquire) {

    return getConnection(connectionsToAcquire, new InstanceFromServer());

  }

  private List<Connection> setUpAvailableConnections(int numberAvailableConnections ) {

    return getConnection(numberAvailableConnections, new CreateNewInstance());

  }


  private List<Connection> getConnection(int connectionsToAcquire , CallBack connection) {

    List<Connection> connections = Lists.newArrayList();

    for(int i=0;i<=connectionsToAcquire;i++){

      connections.add(connection.getConnection());

    }
    return connections;

  }

}
