package com.clouway.objectpool;

import org.junit.Test;

import static com.google.common.base.Preconditions.checkArgument;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * @author georgi.hristov@clouway.com
 */


public class ConnectionPoolTest {

 class Connection{

   void use() {
     //To change body of created methods use File | Settings | File Templates.
   }

 }

  interface Server{

   Connection dispatchConnection();

  }


  class Client{

    private Connection connection;

    private Boolean acquired=false;

    private Server server;


    public void connectTo(Server server){

      this.server = server;

    }

    void acquireConnection() {

       connection=server.dispatchConnection();

       acquired=true;

    }

    Boolean hasAcquiredConnection(){

      return acquired;

    }

  }


  @Test
  public void serverDispatchConnectionToClient(){

      Server server = mock(Server.class);

      Client client = new Client();

      client.connectTo(server);

      client.acquireConnection();

      doReturn(new Connection()).when(server).dispatchConnection();

      assertTrue(client.hasAcquiredConnection());

  }

}
