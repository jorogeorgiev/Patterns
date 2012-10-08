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

    store.addPartner(shopPartner);

    apples = mock(Product.class);

  }


  private class Store {

    private List<Partner> partners = Lists.newArrayList();


    public void addPartner(Partner partner) {

      partners.add(partner);

    }


    public void supplyProduct(Product product) {

      for(Partner partner : partners){

        partner.onNewSupply(product);

      }

    }

    public void sellProduct(Product product) {

      for(Partner partner : partners){

        partner.onNewSell(product);

      }

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

    store.supplyProduct(apples);

    verify(shopPartner).onNewSupply(apples);

  }


  @Test
  public void storeNotifiesObserversWhenProductSold() {

    store.sellProduct(apples);

    verify(shopPartner).onNewSell(apples);

  }


}
