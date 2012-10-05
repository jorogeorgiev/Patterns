package com.clouway.proxy;

/**
 * @author georgi.hristov@clouway.com
 */
public class ProxyDatabaseWriter{

  private Database db;

  private DatabaseAccessor dbAccessor = null;

  public ProxyDatabaseWriter(Database db , DatabaseAccessor accessor){

    this.db=db;

    this.dbAccessor = accessor;

  }


  public  void write(String data ) {

    if(dbAccessor == null){

      dbAccessor = new DatabaseAccessor(db);

    }


    dbAccessor.write(data);
  }
}
