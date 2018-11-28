package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao;

import java.util.List;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

/**
 * @author MATSUS
 *
 */
public interface SpecialDao extends ApplicationDao<SpecialEntity>, MasterDataDao<SpecialEntity> {

  List<SpecialEntity> findSpecialOffers(SpecialSearchCriteriaTo criteriaTo);

  /**
   * @param specialSearchCriteria
   * @return
   */
  Money findBestActiveSpecial(SpecialSearchCriteriaTo specialSearchCriteria);

}
