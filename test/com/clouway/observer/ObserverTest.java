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

  private InventoryObserver observer;

  private Product apples;

  private Partner shopPartner;

  private InventoryObserverImpl metroObserver ;


  @Before
  public void setUp() {

    store = new Store();

    observer = mock(InventoryObserver.class);

    store.attachObserver(observer);

    apples = mock(Product.class);

    shopPartner = mock(Partner.class);

    metroObserver = new InventoryObserverImpl();
    \
    metroObserver.addPartner(shopPartner);

  }


  interface Notification {

    void notifyObservers(List<InventoryObserver> observers, Product product);

  }

  class SellNotification implements Notification {

    @Override
    public void notifyObservers(List<InventoryObserver> observers, Product product) {
      for(InventoryObserver observer : observers){

        observer.notifyOnSell(product);

      }
    }
  }

  class SupplyNotification implements Notification {

    @Override
    public void notifyObservers(List<InventoryObserver> observers, Product product) {
     for(InventoryObserver observer : observers){

       observer.notifyOnSupply(product);

     }
    }
  }



  private class Store {

    private List<InventoryObserver> observers = Lists.newArrayList();
    private SellNotification sellNotification = new SellNotification();
    private SupplyNotification supplyNotification = new SupplyNotification();

    public void attachObserver(InventoryObserver observer) {

      observers.add(observer);

    }


    public void addProduct(Product product) {

     supplyNotification.notifyObservers(observers,product);

    }

    public void sellProduct(Product product) {

      sellNotification.notifyObservers(observers,product);

    }

  }


  private interface InventoryObserver {


    void notifyOnSell(Product product);

    void notifyOnSupply(Product product);

    void addPartner(Partner shopPartner);

  }






  class InventoryObserverImpl implements InventoryObserver {

    private List<Partner> partners = Lists.newArrayList();


    @Override
    public void notifyOnSell(Product product) {
      for (Partner partner : partners) {

        partner.notifyOnSell(product);

      }
    }

    @Override
    public void notifyOnSupply(Product product) {
      for (Partner partner : partners) {

        partner.notifyOnSupply(product);

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

    void notifyOnSell(Product product);

    void notifyOnSupply(Product product);

  }

  @Test
  public void storeNotifiesObserversWhenNewProductSupplied() {

    store.addProduct(apples);

    verify(observer).notifyOnSupply(apples);

  }


  @Test
  public void storeNotifiesObserversWhenProductSold() {

    store.sellProduct(apples);

    verify(observer).notifyOnSell(apples);

  }

  @Test
  public void observerNotifiesPartnersOnProductSupply() {

    metroObserver.notifyOnSupply(apples);

    verify(shopPartner).notifyOnSupply(apples);

  }

  @Test
  public void observerNotifiesPartnersOnProductSell() {


    metroObserver.notifyOnSell(apples);

    verify(shopPartner).notifyOnSell(apples);

  }


}
