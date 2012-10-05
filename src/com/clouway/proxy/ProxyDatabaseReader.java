package com.clouway.proxy;

/**
 * @author georgi.hristov@clouway.com
 */
public class ProxyDatabaseReader {

  private Database db;

  private DatabaseAccessor dbAcessor = null;

  public ProxyDatabaseReader(Database db , DatabaseAccessor accessor){

    this.db = db;
    this.dbAcessor = accessor;
  }


  public String read() {

    if(dbAcessor == null){
       dbAcessor = new DatabaseAccessor(db);
    }

    return dbAcessor.read();
  }
}
