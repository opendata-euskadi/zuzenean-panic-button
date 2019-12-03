package x47b.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import x47b.api.interfaces.X47BCRUDServicesForWorkPlace;
import x47b.db.crud.X47BDBCRUDForWorkPlace;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BCRUDServicesDelegateForWorkPlace
	 extends X47BCRUDServicesDelegateForOrganizationalEntityBase<X47BWorkPlaceOID,X47BWorkPlaceID,X47BWorkPlace>
  implements X47BCRUDServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BCRUDServicesDelegateForOrganization _orgCRUD;
	private final X47BCRUDServicesDelegateForOrgDivision _orgDivisionCRUD;
	private final X47BCRUDServicesDelegateForOrgDivisionService _orgDivisionServiceCRUD;
	private final X47BCRUDServicesDelegateForOrgDivisionServiceLocation _orgDivisionServiceLocationCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesDelegateForWorkPlace(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												final EntityManager entityManager,
											    final Marshaller marshaller,
				  			   		   	   	    final EventBus eventBus) {
		super(coreCfg,
			  X47BWorkPlace.class,
			  new X47BDBCRUDForWorkPlace(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					 entityManager,
					  					 marshaller),
			  eventBus);
		 _orgCRUD = new X47BCRUDServicesDelegateForOrganization(coreCfg,
				  												entityManager,
				 												marshaller,
				 												eventBus);
		 _orgDivisionCRUD = new X47BCRUDServicesDelegateForOrgDivision(coreCfg,
				 													   entityManager,
				 													   marshaller,
				 													   eventBus);
		 _orgDivisionServiceCRUD = new X47BCRUDServicesDelegateForOrgDivisionService(coreCfg,
				 																	 entityManager,	
				 																	 marshaller,
				 																	 eventBus);
		 _orgDivisionServiceLocationCRUD = new X47BCRUDServicesDelegateForOrgDivisionServiceLocation(coreCfg,
				 																					 entityManager,
				 																					 marshaller,
				 																					 eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PARAMS VALIDATION ON CREATION / UPDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<X47BWorkPlace> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																	 			   	   final PersistenceRequestedOperation requestedOp,
																	 			   	   final X47BWorkPlace workPlace) {
		// Ensure the hierarchy is correct
		if (workPlace.getOrgRef() == null || workPlace.getOrgDivisionRef() == null || workPlace.getOrgDivisionServiceRef() == null || workPlace.getOrgDivisionServiceLocationRef() == null) { 
			return ObjectValidationResultBuilder.on(workPlace)
												.isNotValidBecause("The organization, division, service and location are mandatory to create a {}",X47BWorkPlace.class.getSimpleName());
		}
		if (requestedOp.isIn(PersistenceRequestedOperation.CREATE,
							 PersistenceRequestedOperation.UPDATE)) {
			X47BOrganizationOID orgOid = workPlace.getOrgRef().getOid();
			X47BOrgDivisionOID divOid = workPlace.getOrgDivisionRef().getOid();
			X47BOrgDivisionServiceOID srvcOid = workPlace.getOrgDivisionServiceRef().getOid();
			X47BOrgDivisionServiceLocationOID locOid = workPlace.getOrgDivisionServiceLocationRef().getOid();
			
			// try to load the org, division, service & location by it's ids
			CRUDResult<X47BOrganization> existingOrgByOidLoadResult = _orgCRUD.load(securityContext,
																			 	    orgOid);
			CRUDResult<X47BOrgDivision> existingDivByOidLoadResult = _orgDivisionCRUD.load(securityContext,
																				  		   divOid);
			CRUDResult<X47BOrgDivisionService> existingSrvcByOidLoadResult = _orgDivisionServiceCRUD.load(securityContext,
																				  		  				  srvcOid);
			CRUDResult<X47BOrgDivisionServiceLocation> existingLocByOidLoadResult = _orgDivisionServiceLocationCRUD.load(securityContext,
																				  		  				 				 locOid);
			
			if (existingOrgByOidLoadResult.hasFailed() || existingDivByOidLoadResult.hasFailed() || existingSrvcByOidLoadResult.hasFailed() || existingLocByOidLoadResult.hasFailed()) {
				return ObjectValidationResultBuilder.on(workPlace)
													 .isNotValidBecause("The {} with oid={} sets an INVALID orgOid={}, divisionOid={}, serviceOid={} or locationOid={}",
															 			workPlace.getClass().getSimpleName(),workPlace.getOid(),
															 			orgOid,divOid,srvcOid,locOid);	
				
			} else if (existingOrgByOidLoadResult.getOrThrow().getId().isNOT(workPlace.getOrgRef().getId())
				    || existingDivByOidLoadResult.getOrThrow().getId().isNOT(workPlace.getOrgDivisionRef().getId())
				    || existingSrvcByOidLoadResult.getOrThrow().getId().isNOT(workPlace.getOrgDivisionServiceRef().getId())
				    || existingLocByOidLoadResult.getOrThrow().getId().isNOT(workPlace.getOrgDivisionServiceLocationRef().getId())) {
				return ObjectValidationResultBuilder.on(workPlace)
													 .isNotValidBecause("The {} with oid={} sets an INVALID orgId={}, divisionId={}, serviceId={} or location={}",
															 			workPlace.getClass().getSimpleName(),workPlace.getOid(),
															 			workPlace.getOrgRef().getId(),workPlace.getOrgDivisionRef().getId(),workPlace.getOrgDivisionServiceRef().getId(),workPlace.getOrgDivisionServiceLocationRef().getId());	
			}
		}
		return super.validateModelObjBeforeCreateOrUpdate(securityContext,
														  requestedOp,
														  workPlace);
	}

}
