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

  private Server server;
  private Connection connection;
  private List<Connection> initialConnections;
  private List<Connection> acquiredConnections;
  private Integer initialConnectionsCount;
  private Integer acquiredConnectionsCount;
  private Integer elementToAcquire;


  @Before
  public void setUp() {

    initialConnections = Lists.newArrayList();

    acquiredConnections = Lists.newArrayList();


  }


  interface CallBack {

    Connection getConnection();

  }

  class ActiveInstance implements CallBack {


    @Override
    public Connection getConnection() {

      return new Active();

    }

  }


  class ServerConnection implements CallBack {

    @Override
    public Connection getConnection() {

      return server.dispatchConnection();

    }

  }


  @Test
  public void serverDispatchesActiveConnectionToClient() {

    initialConnectionsCount = 1;

    acquiredConnectionsCount = 1;

    elementToAcquire = 0;

    setUpServer(initialConnectionsCount);

    acquireConnections(acquiredConnectionsCount);

    assertTrue(acquiredConnections.get(elementToAcquire).getClass().equals(Active.class));

  }

  @Test
  public void serverDispatchConnectionIfAvailable() {

    initialConnectionsCount = 10;

    acquiredConnectionsCount = 20;

    elementToAcquire = 5;

    setUpServer(initialConnectionsCount);

    acquireConnections(acquiredConnectionsCount);

    assertTrue(acquiredConnections.get(elementToAcquire).getClass().equals(Active.class));

  }

  @Test
  public void serverDispatchesInactiveConnectionWhenActivesAreExceeded() {

    initialConnectionsCount = 10;

    acquiredConnectionsCount = 20;

    elementToAcquire = 10;

    setUpServer(initialConnectionsCount);

    acquireConnections(acquiredConnectionsCount);

    assertTrue(acquiredConnections.get(elementToAcquire).getClass().equals(Inactive.class));

  }


  @Test
  public void serverDispatchesInactiveConnectionWhenNoActivesAreSet() {

    initialConnectionsCount = 0;

    acquiredConnectionsCount = 1;

    elementToAcquire =0;

    setUpServer(initialConnectionsCount);

    acquireConnections(acquiredConnectionsCount);

    assertTrue(acquiredConnections.get(elementToAcquire).getClass().equals(Inactive.class));
  }


  @Test
  public void serverDoesNotDispatchSameConnectionTwice() {

    initialConnectionsCount = 1;

    setUpServer(initialConnectionsCount);

    server.dispatchConnection();

    connection = server.dispatchConnection();

    assertTrue(connection.getClass().equals(Inactive.class));

  }


  @Test
  public void serverReleasesAConnection() {

    initialConnectionsCount = 1;

    setUpServer(initialConnectionsCount);

    connection = new ServerConnection().getConnection();

    server.release(connection);

    assertTrue(connection.isAvailable());

  }


  @Test(expected = UnknownConnectionException.class)

  public void serverThrowExceptionOnUnknownConnectionRelease() {

    initialConnectionsCount = 1;

    setUpServer(initialConnectionsCount);

    connection = new ActiveInstance().getConnection();

    server.release(connection);

  }


  @Test(expected = UnknownConnectionException.class)

  public void serverDoesNotReleaseSameConnectionTwice() {

    initialConnectionsCount = 1;

    setUpServer(initialConnectionsCount);

    connection = new ActiveInstance().getConnection();

    server.release(connection);

    server.release(connection);

  }


  private void setUpServer(int connectionsCount) {

    buildConnections(connectionsCount, initialConnections, new ActiveInstance());

    server = new Server(initialConnections);

  }


  private void acquireConnections(int connectionsCount) {

    buildConnections(connectionsCount, acquiredConnections, new ServerConnection());

  }

  private void buildConnections(int connectionsCount, List<Connection> connections, CallBack connectionCallBack) {

    for (int i = 0; i < connectionsCount; i++) {

      connections.add(i, connectionCallBack.getConnection());

    }

  }
}
