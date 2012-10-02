package com.clouway.objectpool;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.base.Preconditions.checkArgument;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author georgi.hristov@clouway.com
 */


public class ConnectionPoolTest {

  private Server server;
  private Client client;


  @Before
  public void setUp() {

    server = mock(Server.class);

    client = new Client();

    client.connectTo(server);

  }


  class Connection {

    void use() {
    }

  }


  interface Server {

    Connection dispatchConnection();

    void releaseConnection(Connection connection);


  }


  class Client {

    private Connection connection;

    private boolean isAcquired = false;

    private Server server;


    public void connectTo(Server server) {

      this.server = server;

    }

    void acquireConnection() {

      if (!isAcquired) {

        connection = server.dispatchConnection();

        isAcquired = true;

      }

    }

    Boolean hasAcquiredConnection() {

      return (connection != null);

    }

  }


  @Test
  public void serverDispatchConnectionToClient() {

    acquireConnections(1);

    assertTrue(client.hasAcquiredConnection());

  }

  @Test
  public void clientAcquiersOnlyOneActiveConnection() {

    acquireConnections(2);

    verify(server, times(1)).dispatchConnection();

  }


  private void acquireConnections(int connectionCount) {

    doReturn(new Connection()).when(server).dispatchConnection();

    for(int i=0;i<connectionCount;i++){

      client.acquireConnection();

      client.acquireConnection();
    }

  }

}
