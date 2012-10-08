package com.clouway.observer;

import com.google.common.collect.Lists;
import com.sun.org.apache.bcel.internal.generic.StoreInstruction;
import org.junit.Test;

import java.applet.AppletStub;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author georgi.hristov@clouway.com
 */
public class ObserverTest {


  private class Store {

    public void add(Product product) {

    }
  }



  private interface StockObserver {


    void notifyAbout(Product product);


  }

  interface Product {

    void setAvailability(Boolean availability);

    Boolean isAvailable();

  }


  @Test
  public void storeUpdatesObserversWhenNewProductAdded() {

    StockObserver observer = mock(StockObserver.class);

    Product apples = mock(Product.class);

    List<StockObserver> observers = Lists.newArrayList();

    observers.add(observer);

    Store store = new Store();

    store.add(apples);

    verify(observer).notifyAbout(apples);

  }


}
