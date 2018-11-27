package io.oasp.gastronomy.restaurant.offermanagement.dataaccess;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.WeeklyPeriodEmbeddable;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.OfferDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.SpecialDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;
import io.oasp.module.test.common.base.ComponentTest;

/**
 * @author MATSUS
 *
 */
@Transactional
@SpringBootTest(classes = { SpringBootApp.class })
public class SpecialDaoTest extends ComponentTest {

  @Inject
  private SpecialDao specialDao;

  @Inject
  private OfferDao offerDao;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private OfferEntity offer;

  private SpecialEntity special;

  @Override
  public void doSetUp() {

    this.offer = prepareOfferMock();
    this.special = prepareSpecial(this.offer);
  }

  @Override
  protected void doTearDown() {

    this.offerDao.delete(this.offer);
  }

  @Test
  public void testSaveEntity() {

    this.specialDao.save(this.special);

    assertThat(this.special.getId()).isNotNull();
  }

  @Test
  public void testCouldNotSaveEntity() {

    this.expectedException.expect(JpaObjectRetrievalFailureException.class);
    this.special.setId(99L);

    this.specialDao.save(this.special);
  }

  @Test
  public void testShouldFindOffer() {

    this.specialDao.save(this.special);

    SpecialSearchCriteriaTo criteriaTo = new SpecialSearchCriteriaTo();
    criteriaTo.setDate(LocalDateTime.of(2018, 11, 29, 14, 0));

    Collection<SpecialEntity> resultOffers = this.specialDao.findSpecialOffers(criteriaTo);

    assertThat(resultOffers).isNotEmpty();
  }

  @Test
  public void testIncorrectDay() {

    this.specialDao.save(this.special);

    SpecialSearchCriteriaTo criteriaTo = new SpecialSearchCriteriaTo();
    criteriaTo.setDate(LocalDateTime.of(2018, 11, 27, 14, 0));

    Collection<SpecialEntity> resultOffers = this.specialDao.findSpecialOffers(criteriaTo);

    assertThat(resultOffers).isEmpty();
  }

  @Test
  public void testIncorrectHour() {

    this.specialDao.save(this.special);

    SpecialSearchCriteriaTo criteriaTo = new SpecialSearchCriteriaTo();
    criteriaTo.setDate(LocalDateTime.of(2018, 11, 29, 16, 0));

    Collection<SpecialEntity> resultOffers = this.specialDao.findSpecialOffers(criteriaTo);

    assertThat(resultOffers).isEmpty();
  }

  /**
   *
   */
  private SpecialEntity prepareSpecial(OfferEntity offer) {

    SpecialEntity special = new SpecialEntity();
    special.setName("Happy Hours");

    special.setActivePeriod(createWeeklyPeriod());
    special.setOffer(offer);

    return special;
  }

  /**
   * @return
   */
  private OfferEntity prepareOfferMock() {

    OfferEntity offer = new OfferEntity();
    offer.setNumber(102L);
    offer.setPrice(new Money(12));
    this.offerDao.save(offer);
    // OfferEntity mock = Mockito.mock(OfferEntity.class);
    // Mockito.when(mock.getId()).thenReturn(0L);
    return offer;
  }

  /**
   * @return
   */
  private WeeklyPeriodEmbeddable createWeeklyPeriod() {

    WeeklyPeriodEmbeddable weeklyPeriod = new WeeklyPeriodEmbeddable();
    weeklyPeriod.setStartingDay(DayOfWeek.WEDNESDAY);
    weeklyPeriod.setStartingHour(12);
    weeklyPeriod.setEndingDay(DayOfWeek.FRIDAY);
    weeklyPeriod.setEndingHour(14);
    return weeklyPeriod;
  }

}
