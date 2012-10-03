package com.clouway.objectpool;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * @author georgi.hristov@clouway.com
 */


public class ConnectionPoolTest {



  @Test
  public void serverDispatchesConnectionToClient(){

       Connection connection;

       Server server = new Server();

       connection = server.dispatchConnection();

       assertTrue(connection != null);

  }






}
