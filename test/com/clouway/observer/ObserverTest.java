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

  private Product apples;

  private Partner shopPartner;

  @Before
  public void setUp() {

    store = new Store();

    shopPartner = mock(Partner.class);

    store.attachObserver(shopPartner);

    apples = mock(Product.class);




  }


  interface Notification {

    void notifyObservers(List<Partner> observers, Product product);

  }

  class SellNotification implements Notification {

    @Override
    public void notifyObservers(List<Partner> partners, Product product) {
      for(Partner partner : partners){

        partner.onNewSell(product);

      }
    }
  }

  class SupplyNotification implements Notification {

    @Override
    public void notifyObservers(List<Partner> partners, Product product) {

     for(Partner partner : partners){

       partner.onNewSupply(product);

     }
    }
  }



  private class Store {

    private List<Partner> observers = Lists.newArrayList();

    private SellNotification sellNotification = new SellNotification();

    private SupplyNotification supplyNotification = new SupplyNotification();


    public void attachObserver(Partner partner) {

      observers.add(partner);

    }


    public void addProduct(Product product) {

     supplyNotification.notifyObservers(observers,product);

    }

    public void sellProduct(Product product) {

      sellNotification.notifyObservers(observers,product);

    }

  }






  interface Product {

  }


  interface Partner {

    void onNewSell(Product product);

    void onNewSupply(Product product);

  }

  @Test
  public void storeNotifiesObserversWhenNewProductSupplied() {

    store.addProduct(apples);

    verify(shopPartner).onNewSupply(apples);

  }


  @Test
  public void storeNotifiesObserversWhenProductSold() {

    store.sellProduct(apples);

    verify(shopPartner).onNewSell(apples);

  }


}
