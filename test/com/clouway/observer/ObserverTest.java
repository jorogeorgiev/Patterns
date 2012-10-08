package com.clouway.observer;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author georgi.hristov@clouway.com
 */
public class ObserverTest {

  private Store store;

  private StockObserver observer;

  private Product apples;



  @Before
  public void setUp() {

    store = new Store();

    observer = mock(StockObserver.class);

    store.addObserver(observer);

    apples = mock(Product.class);


  }


  private class Store {

    List<StockObserver> observers = Lists.newArrayList();


    public void addObserver(StockObserver observer) {

      observers.add(observer);

    }


    public void addProduct(Product product) {

      notifyObservers(product);

    }

    public void sellProduct(Product product) {

      notifyObservers(product);

    }

    public void notifyObservers(Product product) {

      for (StockObserver observer : observers) {

        observer.notifyAbout(product);

      }

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

    store.addProduct(apples);

    verify(observer).notifyAbout(apples);

  }


  @Test
  public void storeUpdatesObserversWhenProductSold() {

    store.sellProduct(apples);

    verify(observer).notifyAbout(apples);

  }


}
