package com.clouway.objectpool;

import com.clouway.objectpool.FakeObjectImpl;
import com.clouway.objectpool.ObjectPool;
import com.clouway.objectpool.PoolObject;
import com.clouway.objectpool.PoolObjectImpl;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author georgi.hristov@clouway.com
 */
public class ObjectPoolTest {

  // first we must ensure that the pool is created only once  so that we have a single pool.

  @Test
  public void classMakesOnlyOneInstance() {

    ObjectPool pool1 = ObjectPool.getInstance();

    ObjectPool pool2 = ObjectPool.getInstance();

    assertThat(pool2, sameInstance(pool1));

  }

  // check whether a pool with one type objects is created and no other pool can be created with other type of objects

  @Test
  public void poolCreatesOnePoolWithObjects() throws InstantiationException, IllegalAccessException, NoResourceException {

    ObjectPool pool = ObjectPool.getInstance();

    pool.buildPool(new PoolObjectImpl(),10);

    pool.buildPool(new FakeObjectImpl(),10);

    assertTrue(pool.acquire() instanceof PoolObjectImpl);

  }

  @Test (expected = NoResourceException.class)
  public void clientGetNotificationIfNoResourcesArAvailable() throws InstantiationException, IllegalAccessException, NoResourceException {

     List<PoolObject> acquiredObjects = Lists.newArrayList();

     int size = 10;

     ObjectPool pool = ObjectPool.getInstance();

     pool.buildPool(new PoolObjectImpl(),size);

     for(int i=0;i<=size;i++){

       FakeClient client = new FakeClient(pool);

       acquiredObjects.add(client.returnAcquiredObject());

     }

  }







  class FakeClient{

    private ObjectPool pool;

    public FakeClient(ObjectPool pool){

      this.pool = pool;

    }

    public PoolObject returnAcquiredObject() throws NoResourceException {



      return pool.acquire();

    }


  }

}
