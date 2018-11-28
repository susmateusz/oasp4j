package io.oasp.gastronomy.restaurant.offermanagement.logic.api.to;

import java.time.LocalDateTime;

import io.oasp.module.jpa.common.api.to.SearchCriteriaTo;

/**
 * @author MATSUS
 *
 */
public class SpecialSearchCriteriaTo extends SearchCriteriaTo {

  private String name;

  // private OfferEto offer;

  private LocalDateTime date;

  private Long offerNumber;

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  // /**
  // * @return offer
  // */
  // public OfferEto getOffer() {
  //
  // return this.offer;
  // }

  // /**
  // * @param offer new value of {@link #getoffer}.
  // */
  // public void setOffer(OfferEto offer) {
  //
  // this.offer = offer;
  // }

  /**
   * @return date
   */
  public LocalDateTime getDate() {

    return this.date;
  }

  /**
   * @param date new value of {@link #getdate}.
   */
  public void setDate(LocalDateTime date) {

    this.date = date;
  }

  /**
   * @param number
   */
  public void setOfferNumber(Long number) {

    this.offerNumber = number;
  }

  /**
   * @return setOfferNumber
   */
  public Long getOfferNumber() {

    return this.offerNumber;
  }

  /**
   * @param setOfferNumber new value of {@link #getsetOfferNumber}.
   */
  public void setSetOfferNumber(Long setOfferNumber) {

    this.offerNumber = setOfferNumber;
  }

}
