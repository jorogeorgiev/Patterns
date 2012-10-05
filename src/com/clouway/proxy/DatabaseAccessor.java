package com.clouway.proxy;

/**
 * @author georgi.hristov@clouway.com
 */
public class DatabaseAccessor  implements  DatabaseService{

  private Database db;

  public DatabaseAccessor(Database db){

     this.db = loadDB(db);

  }

  private Database loadDB(Database db){

    return db;

  }


  public String read() {

     return db.getRecord();

  }

  @Override
  public void write(String data) {

    db.makeQuery(data);

  }
}
