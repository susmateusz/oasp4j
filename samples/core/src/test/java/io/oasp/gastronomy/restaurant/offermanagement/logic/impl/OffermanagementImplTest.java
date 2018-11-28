package io.oasp.gastronomy.restaurant.offermanagement.logic.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.OfferDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.SpecialDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferCto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;
import io.oasp.module.beanmapping.common.api.BeanMapper;
import io.oasp.module.test.common.base.ModuleTest;

/**
 * This class tests the correct execution of the methods findOffer and findOfferCto belonging to the
 * {@link OffermanagementImpl}
 *
 */

public class OffermanagementImplTest extends ModuleTest {

  private static final long ID = 1;

  /**
   * The System Under Test (SUT)
   */

  private OffermanagementImpl offerManagementImpl;

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  @Mock
  private OfferDao offerDao;

  @Mock
  private BeanMapper beanMapper;

  @Mock
  private SpecialDao specialDao;

  /**
   * This method initializes the object {@link OffermanagementImpl} and assigns the mocked objects of the classes
   * {@link OfferDao} and {@link BeanMapper} to the attributes of the {@link OffermanagementImpl} object before tests,
   * if they are not null.
   */
  @Before
  public void init() {

    this.offerManagementImpl = new OffermanagementImpl();
    this.offerManagementImpl.setOfferDao(this.offerDao);
    this.offerManagementImpl.setBeanMapper(this.beanMapper);
    this.offerManagementImpl.setSpecialDao(this.specialDao);
  }

  /**
   * This method dereferences all object after each test
   */
  @After
  public void clean() {

    this.beanMapper = null;
    this.offerDao = null;
    this.offerManagementImpl = null;
    this.specialDao = null;
  }

  /**
   * This method tests the execution of the findOffer method belonging to the {@link OffermanagementImpl} class
   */
  @Test
  public void findOffer() {

    // given
    OfferEntity offerEntity = mock(OfferEntity.class);
    OfferEto offerEto = new OfferEto();

    when(this.offerDao.findOne(ID)).thenReturn(offerEntity);
    when(this.beanMapper.map(offerEntity, OfferEto.class)).thenReturn(offerEto);

    // when
    OfferEto responseOfferEto = this.offerManagementImpl.findOffer(ID);

    // then
    assertThat(responseOfferEto).isNotNull();
    assertThat(responseOfferEto).isEqualTo(offerEto);
  }

  /**
   * This method tests the execution of the findOfferCto method belonging to the {@link OffermanagementImpl} class
   */
  @Test
  public void findOfferCto() {

    // given
    OfferCto offerCto = new OfferCto();
    OfferEto offerEto = new OfferEto();

    offerCto.setOffer(offerEto);
    OfferEntity offerEntity = mock(OfferEntity.class);

    when(this.offerDao.findOne(ID)).thenReturn(offerEntity);
    when(this.beanMapper.map(offerEntity, OfferEto.class)).thenReturn(offerEto);

    // when
    OfferCto responseOfferCto = this.offerManagementImpl.findOfferCto(ID);

    // then
    assertThat(responseOfferCto).isNotNull();
    assertThat(responseOfferCto.getOffer()).isEqualTo(offerEto);

  }

  @Test
  public void testSaveSpecial() {

    SpecialEntity specialEntity = mock(SpecialEntity.class);
    SpecialEto specialEto = new SpecialEto();

    when(this.specialDao.save(specialEntity)).thenReturn(specialEntity);
    when(this.beanMapper.map(specialEto, SpecialEntity.class)).thenReturn(specialEntity);
    when(this.beanMapper.map(specialEntity, SpecialEto.class)).thenReturn(specialEto);

    SpecialEto responseSpecialEto = this.offerManagementImpl.saveSpecial(specialEto);

    assertThat(responseSpecialEto).isNotNull();
    assertThat(responseSpecialEto).isEqualTo(specialEto);
  }

  @Test
  public void testSaveSpecialFailure() {

    // given
    SpecialEntity specialEntity = mock(SpecialEntity.class);
    SpecialEto specialEto = new SpecialEto();
    SpecialEto falseSpecialEto = new SpecialEto();

    when(this.specialDao.save(specialEntity)).thenReturn(specialEntity);
    when(this.beanMapper.map(specialEto, SpecialEntity.class)).thenReturn(specialEntity);
    when(this.beanMapper.map(specialEntity, SpecialEto.class)).thenReturn(null);

    // when
    SpecialEto responseSpecialEto = this.offerManagementImpl.saveSpecial(specialEto);

    // then
    assertThat(responseSpecialEto).isNull();
  }

  @Test
  public void testDeleteSpecial() {

    // when
    this.offerManagementImpl.deleteSpecial(ID);

    // then
    Mockito.verify(this.specialDao).delete(ID);
  }

  // @Override
  // public Collection<SpecialEto> getActiveSpecials(SpecialSearchCriteriaTo criteriaTo) {
  //
  // return getBeanMapper().mapList(getSpecialDao().findSpecialOffers(criteriaTo), SpecialEto.class);
  // }
  @Test
  public void testGetActiveSpecials() {

    // given
    SpecialSearchCriteriaTo criteriaMock = mock(SpecialSearchCriteriaTo.class);
    List<SpecialEntity> specialEntitiesMock = Arrays.asList(mock(SpecialEntity.class), mock(SpecialEntity.class));
    List<SpecialEto> specialEtoMock = Arrays.asList(new SpecialEto(), new SpecialEto());
    when(this.specialDao.findSpecialOffers(criteriaMock)).thenReturn(specialEntitiesMock);
    when(this.beanMapper.mapList(specialEntitiesMock, SpecialEto.class)).thenReturn(specialEtoMock);

    // when
    Collection<SpecialEto> resultActiveSpecials = this.offerManagementImpl.getActiveSpecials(criteriaMock);

    // then
    assertThat(resultActiveSpecials).isNotNull();
    assertThat(resultActiveSpecials).hasSize(specialEntitiesMock.size());
    assertThat(resultActiveSpecials).containsAll(specialEtoMock);
  }
}
