package com.clouway.objectpool;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author georgi.hristov@clouway.com
 */
public class ConnectionPoolTest {


  private List<PoolingConnection> connections;
  private List<Connection> acquiredConnections;
  private Server server;
  private ConnectionCallBack connectionCallBack;

  @Before
  public void setUp() {

    connections = Lists.newArrayList();

    acquiredConnections = Lists.newArrayList();

    connectionCallBack = new ConnectionCallBack();

  }


   class ConnectionCallBack{

    public Connection callConnection(String connection){

      if(connection.matches("[0-9]+")){

       return acquiredConnections.get(Integer.valueOf(connection));

      } else if(connection.equals("new")){

        return new PoolingConnectionImpl();

      } else{

        return new RefusedConnection();

      }

    }

  }




  @Test
  public void serverDispatchesConnection() {

    int initialConnections = 1;
    int connectionsToAcquire = 1;
    String connectionNumber = "0";
    Class connectionTypeClass = PoolingConnectionImpl.class;
    assertDispatch(initialConnections, connectionsToAcquire, connectionNumber, connectionTypeClass);

  }


  @Test
  public void serverDoesNotDispatchSameConnectionTwice() {

    int initialConnections = 1;
    int connectionsToAcquire = 2;
    String connectionNumber = "1";
    Class connectionTypeClass = RefusedConnection.class;
    assertDispatch(initialConnections, connectionsToAcquire, connectionNumber, connectionTypeClass);

  }

  @Test
  public void serverReleasesDispatchedConnection() {

    int initialConnections = 1;
    int connectionsToAcquire = 1;
    String connection = "0";
    Class connectionTypeClass = PoolingConnectionImpl.class;
    assertRelease(initialConnections,connectionsToAcquire,connection,connectionTypeClass);
  }


  @Test (expected = NoSuchPoolingConnetionException.class)
  public void serverDoesNotReleaseConnectionOutsidePool() {

    int initialConnections = 1;
    int connectionsToAcquire = 1;
    String connectionNumber = "new";
    Class connectionTypeClass = RefusedConnection.class;
    assertRelease(initialConnections,connectionsToAcquire,connectionNumber,connectionTypeClass);
  }


  private void initiateServerWithConnections(int count) {

    buildConnections(count);

    server = new Server(connections);
  }


  public List<PoolingConnection> buildConnections(int connectionCount) {

    for (int i = 1; i <= connectionCount; i++) {

      connections.add(new PoolingConnectionImpl());

    }

    return connections;

  }

  private void acquireConnections(int count) {

    for (int i = 0; i < count; i++) {

      acquiredConnections.add(server.dispatchConnection());

    }

  }


  private void assertDispatch(int initialConnections, int connectionsToAcquire, String connectionNumber, Class connectionTypeClass) {

    initiateServerWithConnections(initialConnections);

    acquireConnections(connectionsToAcquire);

    assertTrue(connectionCallBack.callConnection(connectionNumber).getClass().equals(connectionTypeClass));

  }


  private void assertRelease(int initialConnections, int connectionsToAcquire, String connectionNumber, Class className) {

    initiateServerWithConnections(initialConnections);

    acquireConnections(connectionsToAcquire);

    server.release((PoolingConnection)connectionCallBack.callConnection(connectionNumber));

    acquireConnections(1);

    assertTrue(acquiredConnections.get(1).getClass().equals(className));

  }


}
