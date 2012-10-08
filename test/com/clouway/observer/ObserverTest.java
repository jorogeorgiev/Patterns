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

       notifyObservers(product);

    }

    public void addObserver(StockObserver observer){

      observers.add(observer);

    }

    public void notifyObservers(Product product){

      for(StockObserver observer : observers){

        observer.notifyAbout(product);

      }


    }


    public void sellProduct(Product apples) {

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

    store.addObserver(observer);

    store.addProduct(apples);

    verify(observer).notifyAbout(apples);

  }


  @Test
  public void storeUpdatesObserversWhenProductSold(){

    StockObserver observer = mock(StockObserver.class);

    Product apples = mock(Product.class);

    Store store = new Store();

    store.addObserver(observer);

    store.sellProduct(apples);

    verify(observer).notifyAbout(apples);



  }


}
