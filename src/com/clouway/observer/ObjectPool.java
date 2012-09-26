package com.clouway.observer;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author georgi.hristov@clouway.com
 */
public class ObjectPool {

  private static ObjectPool instance = new ObjectPool();

  private int index=0;

  private final  List<PoolObject> pool = Lists.newArrayList();

  private ObjectPool(){


  }

  public static ObjectPool getInstance(){

    return  instance;

  }

  public void buildPool(PoolObject object , int size) throws IllegalAccessException, InstantiationException {

    for(int i=0;i<size;i++){

      object = object.getClass().newInstance();

      pool.add(object);

    }

  }

  public PoolObject acquire(){

    return  pool.get(index);

  }

}
