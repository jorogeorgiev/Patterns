package com.clouway.objectpool;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author georgi.hristov@clouway.com
 */


public class ConnectionPoolTest {

  @Test
  public void serverDispatchConnectionToClient() {

    Server server = mock(Server.class);

    Client client  = new Client();

    client.connectTo(server);

    client.acquiteConnection();

    verify(server).dispatchConnection();

  }


}
