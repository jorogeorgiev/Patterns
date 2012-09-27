package com.clouway.objectpool;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author georgi.hristov@clouway.com
 */
public class PoolTest {

  class FakeObject {
  }


  class FakeClient {

    private FakeObject object;

    public void useObject(FakeObject object) {

      this.object = object;

    }


    public Object returnUsedObject() {

      return object;

    }

  }

  @Test
  public void clientUseAcquiredObjectFromRemoteRepository() {

    //fakeObjects - represent an arraylist which is a remote repository for the client

    List<FakeObject> fakeObjects = Lists.newArrayList();

    fakeObjects.add(new FakeObject());
    fakeObjects.add(new FakeObject());


    FakeClient client = new FakeClient();

    // client must use an object which is provided to him so we need an object provider. so that client is capable of using an object

    client.useObject(fakeObjects.get(1));

    assertTrue(client.returnUsedObject() instanceof FakeObject);


  }

}
