package pb01.ui.vaadin.alarmevent;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.FluentIterable;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.organization.PB01COREMediatorForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01COREMediatorForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01COREMediatorForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01COREMediatorForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.workplace.PB01COREMediatorForWorkPlace;
import pb01.ui.vaadin.view.events.PB01OrgObjectChangedEvent;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgObjectType;
import r01f.facets.LangDependentNamed.HasLangDependentNamedFacet;
import r01f.inject.annotations.EventBusSingleton;
import r01f.locale.Language;
import r01f.locale.LanguageTexts;
import r01f.types.Range;
import r01f.types.TimeLapse;
import r01f.ui.presenter.UIPresenter;
import r01f.ui.presenter.UIPresenterSubscriber;

@Slf4j
@Singleton
public class PB01PresenterForRaisedAlarmsListView
  implements UIPresenter {

    private static final long serialVersionUID = -4995313906513090391L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    private final transient PB01COREMediatorForRaisedAlarmsListView _coreMediator;

    private final transient PB01COREMediatorForOrganization _coreMediatorForOrg;
    private final transient PB01COREMediatorForOrgDivision _coreMediatorForOrgDiv;
    private final transient PB01COREMediatorForOrgDivisionService _coreMediatorForOrgDivSrvc;
    private final transient PB01COREMediatorForOrgDivisionServiceLocation _coreMediatorForOrgDivSrvcLoc;
    private final transient PB01COREMediatorForWorkPlace _coreMediatorForWorkPlace;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    public PB01PresenterForRaisedAlarmsListView(@EventBusSingleton(usedFor="uiPresenter") final EventBus eventBus,
    											final PB01COREMediatorForRaisedAlarmsListView coreMediator,
    										 	// org entities core mediators
    										 	final PB01COREMediatorForOrganization coreMediatorForOrg,
    										 	final PB01COREMediatorForOrgDivision coreMediatorForOrgDiv,
    										 	final PB01COREMediatorForOrgDivisionService coreMediatorForOrgDivSrvc,
    										 	final PB01COREMediatorForOrgDivisionServiceLocation coreMediatorForOrgDivSrvcLoc,
    										 	final PB01COREMediatorForWorkPlace coreMediatorForWorkPlace) {
    	eventBus.register(this);	// subscribe this presenter to PB01OrgObjectChanged events

    	_coreMediator = coreMediator;

    	_coreMediatorForOrg = coreMediatorForOrg;
    	_coreMediatorForOrgDiv = coreMediatorForOrgDiv;
    	_coreMediatorForOrgDivSrvc = coreMediatorForOrgDivSrvc;
    	_coreMediatorForOrgDivSrvcLoc = coreMediatorForOrgDivSrvcLoc;
    	_coreMediatorForWorkPlace = coreMediatorForWorkPlace;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    public void onRaisedAlarmsListDataNeeded(final PB01AOrganizationID orgId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(orgId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    public void onRaisedAlarmsListDataNeeded(final PB01AOrgDivisionID orgDivId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(orgDivId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    public void onRaisedAlarmsListDataNeeded(final PB01AOrgDivisionServiceID orgDivSrvcId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(orgDivSrvcId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    public void onRaisedAlarmsListDataNeeded(final PB01AOrgDivisionServiceLocationID orgDivSrvcLocId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(orgDivSrvcLocId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    public void onRaisedAlarmsListDataNeeded(final PB01AWorkPlaceID wpId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(wpId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    private PB01ViewAlarmEvent _viewAlarmEventFrom(final PB01AAlarmEvent event,
    										   	   final Language lang) {
    	PB01ViewAlarmEvent outViewEvent = new PB01ViewAlarmEvent(event);
    	if (event.getOrganization() != null) 	outViewEvent.setOrganizationName(_orgEntityName(event.getOrganization().getOid(),lang));
    	if (event.getDivision() != null)		outViewEvent.setDivisionName(_orgEntityName(event.getDivision().getOid(),lang));
    	if (event.getService() != null)			outViewEvent.setServiceName(_orgEntityName(event.getService().getOid(),lang));
    	if (event.getLocation() != null)		outViewEvent.setLocationName(_orgEntityName(event.getLocation().getOid(),lang));
    	if (event.getWorkPlace() != null)		outViewEvent.setWorkPlaceName(_orgEntityName(event.getWorkPlace().getOid(),lang));
    	return outViewEvent;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private Range<Date> _createeDateRange(final TimeLapse timeLapse) {
		TimeLapse defTimeLapse = TimeLapse.createFor("1d");	// 1 day

		TimeLapse theTimeLapse = timeLapse != null ? timeLapse : defTimeLapse;

		Date now = new Date();
		Date startDate = new Date(now.getTime() - theTimeLapse.asMilis());	// time before
		Date endDate = now;

		return Range.closed(startDate,endDate);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG ENTITY NAME CACHE (events thrown by org object detail edit presenters)
/////////////////////////////////////////////////////////////////////////////////////////
	@Subscribe
	public void onOrgObjectChanged(final PB01OrgObjectChangedEvent orgObjChangeEvent) {
		log.info("An org object of type {} with oid={} has changed: refresh the entity names cache",
				 orgObjChangeEvent.getObjType(),orgObjChangeEvent.getOid());
		_orgEntityNamesCache.invalidate(orgObjChangeEvent.getOid());
	}
    private final Cache<PB01AOrgObjectOID,LanguageTexts> _orgEntityNamesCache = CacheBuilder.newBuilder()
	    																			.expireAfterWrite(3,TimeUnit.MINUTES)	// cached entries expieres after being written
	    																			.build();
    private String _orgEntityName(final PB01AOrgObjectOID oid,final Language lang) {
    	PB01AOrgObjectType objType = PB01AOrgObjectType.ofOIDType(oid.getClass());
    	LanguageTexts byLang = null;
    	try {
	    	byLang = _orgEntityNamesCache.get(oid,
					 						  () -> {
														log.info("...caching the name of an org objet of type {} with oid={}",
																 objType,oid);
														HasLangDependentNamedFacet orgEntity = null;
														if (oid instanceof PB01AOrganizationOID) {
															orgEntity = _coreMediatorForOrg.load((PB01AOrganizationOID)oid);
														}
														else if (oid instanceof PB01AOrgDivisionOID) {
															orgEntity = _coreMediatorForOrgDiv.load((PB01AOrgDivisionOID)oid);
														}
														else if (oid instanceof PB01AOrgDivisionServiceOID) {
															orgEntity = _coreMediatorForOrgDivSrvc.load((PB01AOrgDivisionServiceOID)oid);
														}
														else if (oid instanceof PB01AOrgDivisionServiceLocationOID) {
															orgEntity = _coreMediatorForOrgDivSrvcLoc.load((PB01AOrgDivisionServiceLocationOID)oid);
														}
														else if (oid instanceof PB01AWorkPlaceOID) {
															orgEntity = _coreMediatorForWorkPlace.load((PB01AWorkPlaceOID)oid);
														}
														if (orgEntity == null) throw new IllegalStateException(oid + " is NOT a valid " + objType);
														return orgEntity.getNameByLanguage();
					 						  		});
    	} catch (ExecutionException exEx) {
    		log.error("Error while retrieving the {} with oid={} name: {}",
    				  objType,oid,
    				  exEx.getMessage(),exEx);
    	}
    	String out = null;
    	if (byLang != null) {
    		if (lang.is(Language.SPANISH)) {
    			out = byLang.get(Language.SPANISH);
    		} else if (lang.is(Language.BASQUE)) {
    			out = byLang.get(Language.BASQUE);
    		} else {
    			throw new IllegalArgumentException("Not supported language!!");
    		}
    	} else {
    		out = oid.asString();	// return something
    	}
    	return out;
    }
}
