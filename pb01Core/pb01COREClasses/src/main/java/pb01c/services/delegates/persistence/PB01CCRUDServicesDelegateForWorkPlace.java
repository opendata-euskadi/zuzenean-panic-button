package pb01c.services.delegates.persistence;

import javax.persistence.EntityManager;

import com.google.common.eventbus.EventBus;

import pb01a.api.interfaces.PB01ACRUDServicesForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import pb01c.db.crud.PB01CDBCRUDForWorkPlace;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.model.persistence.CRUDResult;
import r01f.model.persistence.PersistenceRequestedOperation;
import r01f.objectstreamer.Marshaller;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.securitycontext.SecurityContext;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class PB01CCRUDServicesDelegateForWorkPlace
	 extends PB01CCRUDServicesDelegateForOrganizationalEntityBase<PB01AWorkPlaceOID,PB01AWorkPlaceID,PB01AWorkPlace>
  implements PB01ACRUDServicesForWorkPlace {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01CCRUDServicesDelegateForOrganization _orgCRUD;
	private final PB01CCRUDServicesDelegateForOrgDivision _orgDivisionCRUD;
	private final PB01CCRUDServicesDelegateForOrgDivisionService _orgDivisionServiceCRUD;
	private final PB01CCRUDServicesDelegateForOrgDivisionServiceLocation _orgDivisionServiceLocationCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CCRUDServicesDelegateForWorkPlace(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
												final EntityManager entityManager,
											    final Marshaller marshaller,
				  			   		   	   	    final EventBus eventBus) {
		super(coreCfg,
			  PB01AWorkPlace.class,
			  new PB01CDBCRUDForWorkPlace(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
					  					 entityManager,
					  					 marshaller),
			  eventBus);
		 _orgCRUD = new PB01CCRUDServicesDelegateForOrganization(coreCfg,
				  												entityManager,
				 												marshaller,
				 												eventBus);
		 _orgDivisionCRUD = new PB01CCRUDServicesDelegateForOrgDivision(coreCfg,
				 													   entityManager,
				 													   marshaller,
				 													   eventBus);
		 _orgDivisionServiceCRUD = new PB01CCRUDServicesDelegateForOrgDivisionService(coreCfg,
				 																	 entityManager,	
				 																	 marshaller,
				 																	 eventBus);
		 _orgDivisionServiceLocationCRUD = new PB01CCRUDServicesDelegateForOrgDivisionServiceLocation(coreCfg,
				 																					 entityManager,
				 																					 marshaller,
				 																					 eventBus);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  PARAMS VALIDATION ON CREATION / UPDATE
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<PB01AWorkPlace> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																	 			   	   final PersistenceRequestedOperation requestedOp,
																	 			   	   final PB01AWorkPlace workPlace) {
		// Ensure the hierarchy is correct
		if (workPlace.getOrgRef() == null || workPlace.getOrgDivisionRef() == null || workPlace.getOrgDivisionServiceRef() == null || workPlace.getOrgDivisionServiceLocationRef() == null) { 
			return ObjectValidationResultBuilder.on(workPlace)
												.isNotValidBecause("The organization, division, service and location are mandatory to create a {}",PB01AWorkPlace.class.getSimpleName());
		}
		if (requestedOp.isIn(PersistenceRequestedOperation.CREATE,
							 PersistenceRequestedOperation.UPDATE)) {
			PB01AOrganizationOID orgOid = workPlace.getOrgRef().getOid();
			PB01AOrgDivisionOID divOid = workPlace.getOrgDivisionRef().getOid();
			PB01AOrgDivisionServiceOID srvcOid = workPlace.getOrgDivisionServiceRef().getOid();
			PB01AOrgDivisionServiceLocationOID locOid = workPlace.getOrgDivisionServiceLocationRef().getOid();
			
			// try to load the org, division, service & location by it's ids
			CRUDResult<PB01AOrganization> existingOrgByOidLoadResult = _orgCRUD.load(securityContext,
																			 	    orgOid);
			CRUDResult<PB01AOrgDivision> existingDivByOidLoadResult = _orgDivisionCRUD.load(securityContext,
																				  		   divOid);
			CRUDResult<PB01AOrgDivisionService> existingSrvcByOidLoadResult = _orgDivisionServiceCRUD.load(securityContext,
																				  		  				  srvcOid);
			CRUDResult<PB01AOrgDivisionServiceLocation> existingLocByOidLoadResult = _orgDivisionServiceLocationCRUD.load(securityContext,
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
