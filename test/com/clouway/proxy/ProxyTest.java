package com.clouway.proxy;

import org.junit.Before;
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

   private DatabaseAccessor accessor;
   private  ProxySQLDatabaseAccessor proxyAccessor;


  interface DatabaseAccessor {

    void writeToDatabase(String someQuery);

    String readFromDataBase();

  }

  @Before
  public void setUp(){
    proxyAccessor = new ProxySQLDatabaseAccessor("");
    accessor = mock(DatabaseAccessor.class);

  }



  class ProxySQLDatabaseAccessor implements DatabaseAccessor {

    private DatabaseAccessor sqlDatabaseAccessor = null;

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

        sqlDatabaseAccessor = accessor;

      }

    }

  }

  @Test
  public void proxyReadsFromDatabaseViaRealImpl(){

    proxyAccessor.readFromDataBase() ;

    verify(accessor).readFromDataBase();

  }


  @Test
  public void proxyWritesToDatabaseViaRealImpl(){

    proxyAccessor.writeToDatabase("");

    verify(accessor).writeToDatabase("");

  }

}
