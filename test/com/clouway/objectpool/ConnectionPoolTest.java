package com.clouway.objectpool;

import com.google.common.collect.Lists;
import org.hamcrest.Matcher;
import org.junit.Test;
import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
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

    assertConnectionType(1,1,0,Active.class);

  }

  @Test
  public void serverReturnsInactiveConnectionWhenActivesAreExceeded(){

    assertConnectionType(10,20,11,Inactive.class);

  }


  @Test
  public void serverReturnsInactiveConnectionWhenNoActivesAreSet(){

    assertConnectionType(0,1,0,Inactive.class);

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


  private void assertConnectionType(int availableConnections, int acquiredConnections , int connectionNumber , Class classType) {

    server = new Server(setUpAvailableConnections(availableConnections));

    assertTrue(acquireConnections(acquiredConnections).get(connectionNumber).getClass().equals(classType));

  }


}
