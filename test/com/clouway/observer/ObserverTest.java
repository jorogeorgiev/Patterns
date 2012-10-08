package com.clouway.observer;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author georgi.hristov@clouway.com
 */
public class ObserverTest {


  private class Store {

    List<StockObserver> observers = Lists.newArrayList();


    public void addProduct(Product product) {

    }

    public void addObserver(StockObserver observer){


      observers.add(observer);

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

    Store store = new Store();

    store.addProduct(apples);

    store.addObserver(observer);

    verify(observer).notifyAbout(apples);

  }


}
