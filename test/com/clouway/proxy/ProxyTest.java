package com.clouway.proxy;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author georgi.hristov@clouway.com
 */
public class ProxyTest {

  private final String DATABASE_RECORD = "DATABASE";
  private String RECORDED_VALUE;


  interface DatabaseAccessor {

    void writeToDatabase(String someQuery);

    String readFromDataBase();

  }

  class Database {

    public void write(String query) {

      RECORDED_VALUE = query;

    }

    public String read() {

      return DATABASE_RECORD;

    }

  }


  class SQLDatabaseAccessor implements DatabaseAccessor {

    private Database database;

    public SQLDatabaseAccessor(String dbFilePath) {

      database = loadDatabase(dbFilePath);

    }

    private Database loadDatabase(String dbFilePath) {

      // SOME IMPL FOR LOADING THE DB INTO MEMORY
      return new Database();
    }


    @Override
    public void writeToDatabase(String someQuery) {

      database.write(someQuery);

    }

    @Override
    public String readFromDataBase() {
      return database.read();
    }

  }


  class ProxySQLDatabaseAccessor implements DatabaseAccessor {

    private SQLDatabaseAccessor sqlDatabaseAccessor = null;

    private String dbFilePath;


    public ProxySQLDatabaseAccessor(String dbFilePath) {

      this.dbFilePath = dbFilePath;

    }

    @Override
    public void writeToDatabase(String someQuery) {

      createInstance();

      sqlDatabaseAccessor.writeToDatabase(someQuery);

    }

    @Override
    public String readFromDataBase() {

      createInstance();

      return sqlDatabaseAccessor.readFromDataBase();

    }

    private void createInstance() {

      if (sqlDatabaseAccessor == null) {

        sqlDatabaseAccessor = new SQLDatabaseAccessor(dbFilePath);

      }

    }

  }

  class FailedSQLProxyDatabaseAccessor implements  DatabaseAccessor{

    private SQLDatabaseAccessor sqlDatabaseAccessor = null;

    private String dbFilePath;

    public FailedSQLProxyDatabaseAccessor(String dbFilePath) {

      this.dbFilePath = dbFilePath;

    }


    @Override
    public void writeToDatabase(String someQuery) {

      sqlDatabaseAccessor.writeToDatabase(someQuery);

    }

    @Override
    public String readFromDataBase() {

      return  sqlDatabaseAccessor.readFromDataBase();

    }
  }





  @Test
  public void proxyReadsFromDatabaseViaRealImpl(){

    ProxySQLDatabaseAccessor accessor = new ProxySQLDatabaseAccessor("");

    assertThat(accessor.readFromDataBase(),is(DATABASE_RECORD));

  }


  @Test
  public void proxyWritesToDatabaseViaRealImpl(){

    String query = "SELECT FROM * WHERE Georgi";

    ProxySQLDatabaseAccessor accessor = new ProxySQLDatabaseAccessor("");

    accessor.writeToDatabase(query);

    assertThat(RECORDED_VALUE,is(query));

  }

  @Test(expected = NullPointerException.class)

  public void proxyThrowsExceptionOnReadWhenThereIsNoRealObject(){

   FailedSQLProxyDatabaseAccessor accessor = new FailedSQLProxyDatabaseAccessor("");

   accessor.readFromDataBase();

  }

  @Test(expected = NullPointerException.class)

  public void proxyThrowsExceptionOnWriteWhenThereIsNoRealObject(){

   FailedSQLProxyDatabaseAccessor accessor = new FailedSQLProxyDatabaseAccessor("");

   accessor.writeToDatabase("");

  }


}
