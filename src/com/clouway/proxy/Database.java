package com.clouway.proxy;

/**
 * @author georgi.hristov@clouway.com
 */
public class Database {

  private String database;


  public Database(String database){

    this.database = database;

  }

  public void makeQuery(String query){

    database = query;

  }

  public String getRecord(){

    return  database;

  }

}
