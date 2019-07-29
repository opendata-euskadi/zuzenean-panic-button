package x47b.client.api.sub.delegates;

import java.util.Collection;

import javax.inject.Provider;

import r01f.model.persistence.FindResult;
import r01f.objectstreamer.Marshaller;
import r01f.securitycontext.SecurityContext;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;
import r01f.types.TimeLapse;
import x47b.api.interfaces.X47BFindServicesForAlarmEvent;
import x47b.model.X47BAlarmEvent;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BPanicButtonOIDs.X47BAlarmEventOID;

public class X47BClientAPIDelegateForAlarmEventFindServices
	 extends ClientAPIDelegateForModelObjectFindServices<X47BAlarmEventOID,X47BAlarmEvent> {;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BClientAPIDelegateForAlarmEventFindServices(final Provider<SecurityContext> securityContextProvider,
														  final Marshaller modelObjectsMarshaller,
														  final X47BFindServicesForAlarmEvent findServicesProxy) {
		super(securityContextProvider,
			  modelObjectsMarshaller,
			  findServicesProxy);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  EXTENSION METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Loads all alarms from an organization raised within the last minute
	 * @param id 
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BOrganizationID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from an organization raised within the provided time lapse
	 * @param id  
	 * @param timeLapse
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BOrganizationID id,
													 final TimeLapse timeLapse) {
		FindResult<X47BAlarmEvent> findResult = this.getServiceProxyAs(X47BFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		timeLapse);
		Collection<X47BAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms from a division raised within the last minute
	 * @param id 
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BOrgDivisionID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from a division raised within the provided time lapse
	 * @param id  
	 * @param timeLapse
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BOrgDivisionID id,
													 final TimeLapse timeLapse) {
		FindResult<X47BAlarmEvent> findResult = this.getServiceProxyAs(X47BFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		timeLapse);
		Collection<X47BAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms from a service raised within the last minute
	 * @param id 
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BOrgDivisionServiceID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from a service raised within the provided time lapse
	 * @param id  
	 * @param timeLapse
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BOrgDivisionServiceID id,
													 final TimeLapse timeLapse) {
		FindResult<X47BAlarmEvent> findResult = this.getServiceProxyAs(X47BFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		timeLapse);
		Collection<X47BAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms from a location raised within the last minute
	 * @param id 
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BOrgDivisionServiceLocationID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from a location raised within the provided time lapse
	 * @param id  
	 * @param timeLapse
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BOrgDivisionServiceLocationID id,
													 final TimeLapse timeLapse) {
		FindResult<X47BAlarmEvent> findResult = this.getServiceProxyAs(X47BFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		timeLapse);
		Collection<X47BAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
	/**
	 * Loads all alarms from a work place raised within the last minute
	 * @param id  
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BWorkPlaceID id) {
		return this.findBySourceId(id,
								   TimeLapse.createFor("1m"));	// last minute
	}
	/**
	 * Loads all alarms from a work place raised within the provided time lapse
	 * @param id  
	 * @param timeLapse
	 * @return
	 */
	public Collection<X47BAlarmEvent> findBySourceId(final X47BWorkPlaceID id,
													 final TimeLapse timeLapse) {
		FindResult<X47BAlarmEvent> findResult = this.getServiceProxyAs(X47BFindServicesForAlarmEvent.class)
														.findBySourceId(this.getSecurityContext(),
															   	  		id,
															   	  		timeLapse);
		Collection<X47BAlarmEvent> outAlarmEvents = findResult.getOrThrow();
		return outAlarmEvents;
	}
}
