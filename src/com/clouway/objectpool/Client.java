package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public class Client {

  private Server server;



  public void connectTo(Server server) {

    this.server = server;

  }

  public void acquiteConnection() {

     server.dispatchConnection();

  }
}
