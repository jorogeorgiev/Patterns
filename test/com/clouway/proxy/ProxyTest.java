package com.clouway.proxy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author georgi.hristov@clouway.com
 */
public class ProxyTest {


  @Test
  public void delegatorReadsFromDatabase() {

    String DATABASE = "initial record";

    Database db = new Database(DATABASE);

    DatabaseAccessDelegator delegator = new DatabaseAccessDelegator(db);

    assertThat(delegator.read(),is(DATABASE));

  }


  @Test
  public void delegatorWritesToDatabase() {

    String QUERY = "SELECT FROM name WHERE georgi";

    String DATABASE = "initial record";

    Database db = new Database(DATABASE);

    DatabaseAccessDelegator delegator = new DatabaseAccessDelegator(db);

    delegator.write(QUERY);

    assertThat(db.getRecord(), is(QUERY));

  }


}
