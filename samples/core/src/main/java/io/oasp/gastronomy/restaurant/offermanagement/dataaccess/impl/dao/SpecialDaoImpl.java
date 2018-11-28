package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.impl.dao;

import static com.querydsl.core.alias.Alias.$;

import java.time.DayOfWeek;
import java.util.List;

import javax.inject.Named;

import com.querydsl.core.alias.Alias;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.WeeklyPeriodEmbeddable;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.SpecialDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;

/**
 * @author MATSUS
 *
 */
@Named
public class SpecialDaoImpl extends ApplicationMasterDataDaoImpl<SpecialEntity> implements SpecialDao {

  /**
   * The constructor.
   */
  public SpecialDaoImpl() {

    super();
  }

  @Override
  protected Class<SpecialEntity> getEntityClass() {

    return SpecialEntity.class;
  }

  @Override
  public List<SpecialEntity> findSpecialOffers(SpecialSearchCriteriaTo criteriaTo) {

    SpecialEntity special = Alias.alias(SpecialEntity.class);
    EntityPathBase<SpecialEntity> alias = $(special);

    JPAQuery<SpecialEntity> query = new JPAQuery<SpecialEntity>(getEntityManager()).from(alias);

    if (criteriaTo.getName() != null) {
      query.where($(special.getName()).eq(criteriaTo.getName()));
    }

    if (criteriaTo.getOfferNumber() != null) {
      query.where($(special.getOffer().getId()).eq(criteriaTo.getOfferNumber()));
    }

    if (criteriaTo.getDate() != null) {
      WeeklyPeriodEmbeddable activePeriod = special.getActivePeriod();

      DayOfWeek day = criteriaTo.getDate().getDayOfWeek();
      int hour = criteriaTo.getDate().getHour();

      query.where($(activePeriod.getStartingDay()).loe(day));
      query.where($(activePeriod.getEndingDay()).goe(day));
      query.where($(activePeriod.getStartingHour()).loe(hour));
      query.where($(activePeriod.getEndingHour()).goe(hour));
    }
    return query.fetch();
  }

  @Override
  public Money findBestActiveSpecial(SpecialSearchCriteriaTo specialSearchCriteria) {

    // TODO
  }

}