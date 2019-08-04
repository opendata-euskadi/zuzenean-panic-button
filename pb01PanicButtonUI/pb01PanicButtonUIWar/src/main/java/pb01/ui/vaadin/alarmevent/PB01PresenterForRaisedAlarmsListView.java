package pb01.ui.vaadin.alarmevent;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
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
import r01f.facets.LangDependentNamed.HasLangDependentNamedFacet;
import r01f.locale.Language;
import r01f.locale.LanguageTexts;
import r01f.types.Range;
import r01f.types.TimeLapse;
import r01f.ui.presenter.UIPresenter;
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgObjectType;

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
    public PB01PresenterForRaisedAlarmsListView(@Named("uiPresenterEventBus") final EventBus eventBus,
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
    public void onRaisedAlarmsListDataNeeded(final X47BOrganizationID orgId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(orgId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    public void onRaisedAlarmsListDataNeeded(final X47BOrgDivisionID orgDivId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(orgDivId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    public void onRaisedAlarmsListDataNeeded(final X47BOrgDivisionServiceID orgDivSrvcId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(orgDivSrvcId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    public void onRaisedAlarmsListDataNeeded(final X47BOrgDivisionServiceLocationID orgDivSrvcLocId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(orgDivSrvcLocId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    public void onRaisedAlarmsListDataNeeded(final X47BWorkPlaceID wpId,final TimeLapse timeLapse,
    										 final Language lang,
    										 final UIPresenterSubscriber<Collection<PB01ViewAlarmEvent>> presenterSubscriber) {
    	_coreMediator.raisedAlarmsFor(wpId,_createeDateRange(timeLapse),
    								  alarms -> presenterSubscriber.onSuccess(FluentIterable.from(alarms)
			  																	  .transform(event -> _viewAlarmEventFrom(event,lang))
			  																	  .toList()));
    }
    private PB01ViewAlarmEvent _viewAlarmEventFrom(final X47BAlarmEvent event,
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
    private final Cache<X47BOrgObjectOID,LanguageTexts> _orgEntityNamesCache = CacheBuilder.newBuilder()
	    																			.expireAfterWrite(3,TimeUnit.MINUTES)	// cached entries expieres after being written
	    																			.build();
    private String _orgEntityName(final X47BOrgObjectOID oid,final Language lang) {
    	X47BOrgObjectType objType = X47BOrgObjectType.ofOIDType(oid.getClass());
    	LanguageTexts byLang = null;
    	try {
	    	byLang = _orgEntityNamesCache.get(oid,
					 						  () -> {
														log.info("...caching the name of an org objet of type {} with oid={}",
																 objType,oid);
														HasLangDependentNamedFacet orgEntity = null;
														if (oid instanceof X47BOrganizationOID) {
															orgEntity = _coreMediatorForOrg.load((X47BOrganizationOID)oid);
														}
														else if (oid instanceof X47BOrgDivisionOID) {
															orgEntity = _coreMediatorForOrgDiv.load((X47BOrgDivisionOID)oid);
														}
														else if (oid instanceof X47BOrgDivisionServiceOID) {
															orgEntity = _coreMediatorForOrgDivSrvc.load((X47BOrgDivisionServiceOID)oid);
														}
														else if (oid instanceof X47BOrgDivisionServiceLocationOID) {
															orgEntity = _coreMediatorForOrgDivSrvcLoc.load((X47BOrgDivisionServiceLocationOID)oid);
														}
														else if (oid instanceof X47BWorkPlaceOID) {
															orgEntity = _coreMediatorForWorkPlace.load((X47BWorkPlaceOID)oid);
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
