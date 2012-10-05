package com.clouway.proxy;

/**
 * @author georgi.hristov@clouway.com
 */
public class DatabaseAccessDelegator  implements  DatabaseService{

  private ProxyDatabaseReader dbReader;
  private ProxyDatabaseWriter dbWriter;
  private DatabaseAccessor accessor;
  private Database db;

  public DatabaseAccessDelegator(Database db){

    this.db=db;

  }


  @Override
  public String read() {

    dbReader = new ProxyDatabaseReader(db,accessor);

    return dbReader.read();

  }

  @Override
  public void write(String data) {

    dbWriter = new ProxyDatabaseWriter(db,accessor);

    dbWriter.write((data));

  }

}
