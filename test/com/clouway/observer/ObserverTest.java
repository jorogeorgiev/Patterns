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

    store.attachObserver(observer);

    apples = mock(Product.class);


  }

  private class Store {

    List<StockObserver> observers = Lists.newArrayList();


    public void attachObserver(StockObserver observer) {

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


    void addPartner(Partner shopPartner);

  }

  class MetroStockObserver implements StockObserver {

    private List<Partner> partners = Lists.newArrayList();

    @Override
    public void notifyAbout(Product product) {

      for (Partner partner : partners) {

        partner.notifyAbout(product);

      }

    }

    @Override
    public void addPartner(Partner partner) {

      partners.add(partner);

    }
  }


  interface Product {

  }


  interface Partner {

    void notifyAbout(Product product);

  }

  @Test
  public void storeNotifiesObserversWhenNewProductAdded() {

    store.addProduct(apples);

    verify(observer).notifyAbout(apples);

  }


  @Test
  public void storeNotifiesObserversWhenProductSold() {

    store.sellProduct(apples);

    verify(observer).notifyAbout(apples);

  }

  @Test
  public void observerNotifiesPartnersAboutProductChange() {

    Partner shopPartner = mock(Partner.class);

    MetroStockObserver metroObserver = new MetroStockObserver();

    metroObserver.addPartner(shopPartner);

    metroObserver.notifyAbout(apples);

    verify(shopPartner).notifyAbout(apples);

  }


}
