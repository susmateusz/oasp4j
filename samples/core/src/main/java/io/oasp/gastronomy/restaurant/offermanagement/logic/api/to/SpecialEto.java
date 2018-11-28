package io.oasp.gastronomy.restaurant.offermanagement.logic.api.to;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.Special;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.WeeklyPeriod;
import io.oasp.module.basic.common.api.to.AbstractTo;

/**
 * @author MATSUS
 *
 */
public class SpecialEto extends AbstractTo implements Special {

  private Long id;

  private int modificationCounter;

  private OfferEto offer;

  private WeeklyPeriod activePeriod;

  /**
   * @return offer
   */
  public OfferEto getOffer() {

    return this.offer;
  }

  /**
   * @param offer new value of {@link #getoffer}.
   */
  public void setOffer(OfferEto offer) {

    this.offer = offer;
  }

  /**
   * @return activePeriod
   */
  public WeeklyPeriod getActivePeriod() {

    return this.activePeriod;
  }

  /**
   * @param activePeriod new value of {@link #getactivePeriod}.
   */
  public void setActivePeriod(WeeklyPeriod activePeriod) {

    this.activePeriod = activePeriod;
  }

  /**
   * @return specialPrice
   */
  public Money getSpecialPrice() {

    return this.specialPrice;
  }

  /**
   * @param specialPrice new value of {@link #getspecialPrice}.
   */
  public void setSpecialPrice(Money specialPrice) {

    this.specialPrice = specialPrice;
  }

  private Money specialPrice;

  @Override
  public void setId(Long id) {

    this.id = id;

    // TODO Auto-generated method stub

  }

  @Override
  public void setModificationCounter(int version) {

    this.modificationCounter = version;
  }

  @Override
  public Long getId() {

    return this.id;
  }

  @Override
  public int getModificationCounter() {

    return this.modificationCounter;
  }

}
