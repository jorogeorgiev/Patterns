package com.clouway.objectpool;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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


  @Test
  public void serverDispatchesTenConnections(){

    List<Connection> connections = Lists.newArrayList();

    Server server = new Server();

    for(int i=1;i<=20;i++){

      connections.add(server.dispatchConnection());

    }

    assertThat(connections.size(),is(10));

  }




}
