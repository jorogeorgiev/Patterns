package com.clouway.objectpool;

/**
 * @author georgi.hristov@clouway.com
 */
public class Server {

  Connection dispatchConnection() {

    return new ConnectionImpl();

  }


}
