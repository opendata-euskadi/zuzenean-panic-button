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
import x47b.api.interfaces.X47BCRUDServicesForOrgDivisionServiceLocation;
import x47b.db.crud.X47BDBCRUDForOrgDivisionServiceLocation;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;

/**
 * Service layer delegated type for CRUD (Create/Read/Update/Delete) operations
 */
public class X47BCRUDServicesDelegateForOrgDivisionServiceLocation
	 extends X47BCRUDServicesDelegateForOrganizationalEntityBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation>
  implements X47BCRUDServicesForOrgDivisionServiceLocation {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final X47BCRUDServicesDelegateForOrganization _orgCRUD;
	private final X47BCRUDServicesDelegateForOrgDivision _orgDivisionCRUD;
	private final X47BCRUDServicesDelegateForOrgDivisionService _orgDivisionServiceCRUD;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BCRUDServicesDelegateForOrgDivisionServiceLocation(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg,
																 final EntityManager entityManager,
															     final Marshaller marshaller,
								  			   		   	   	     final EventBus eventBus) {
		super(coreCfg,
			  X47BOrgDivisionServiceLocation.class,
			  new X47BDBCRUDForOrgDivisionServiceLocation(DBModuleConfigBuilder.dbModuleConfigFrom(coreCfg),
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
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ObjectValidationResult<X47BOrgDivisionServiceLocation> validateModelObjBeforeCreateOrUpdate(final SecurityContext securityContext,
																							   		   final PersistenceRequestedOperation requestedOp,
																							   		   final X47BOrgDivisionServiceLocation loc) {
		// Ensure the hierarchy is correct
		if (loc.getOrgRef() == null || loc.getOrgDivisionRef() == null || loc.getOrgDivisionServiceRef() == null) { 
			return ObjectValidationResultBuilder.on(loc)
												.isNotValidBecause("The organization, division and service are mandatory to create a {}",X47BOrgDivisionServiceLocation.class.getSimpleName());
		}
		if (requestedOp.isIn(PersistenceRequestedOperation.CREATE,
							 PersistenceRequestedOperation.UPDATE)) {
			X47BOrganizationOID orgOid = loc.getOrgRef().getOid();
			X47BOrgDivisionOID divOid = loc.getOrgDivisionRef().getOid();
			X47BOrgDivisionServiceOID srvcOid = loc.getOrgDivisionServiceRef().getOid();
			
			// try to load the org, division & service by it's ids					
			CRUDResult<X47BOrganization> existingOrgByOidLoadResult = _orgCRUD.load(securityContext,
																			 	    orgOid);
			CRUDResult<X47BOrgDivision> existingDivByOidLoadResult = _orgDivisionCRUD.load(securityContext,
																				  		   divOid);
			CRUDResult<X47BOrgDivisionService> existingSrvcByOidLoadResult = _orgDivisionServiceCRUD.load(securityContext,
																				  		  				  srvcOid);
			
			if (existingOrgByOidLoadResult.hasFailed() || existingDivByOidLoadResult.hasFailed() || existingSrvcByOidLoadResult.hasFailed()) {
				return ObjectValidationResultBuilder.on(loc)
													 .isNotValidBecause("The {} with oid={} sets an INVALID orgOid={}, divisionOid={} or serviceOid={}",
															 			loc.getClass().getSimpleName(),loc.getOid(),
															 			orgOid,divOid,srvcOid);					
			} else if (existingOrgByOidLoadResult.getOrThrow().getId().isNOT(loc.getOrgRef().getId())
				    || existingDivByOidLoadResult.getOrThrow().getId().isNOT(loc.getOrgDivisionRef().getId())
				    || existingSrvcByOidLoadResult.getOrThrow().getId().isNOT(loc.getOrgDivisionServiceRef().getId())) {
				return ObjectValidationResultBuilder.on(loc)
													 .isNotValidBecause("The {} with oid={} sets an INVALID orgId={}, divisionId={} or serviceId={}",
															 			loc.getClass().getSimpleName(),loc.getOid(),
															 			loc.getOrgRef().getId(),loc.getOrgDivisionRef().getId(),loc.getOrgDivisionServiceRef().getId());	
			}
		}
		return super.validateModelObjBeforeCreateOrUpdate(securityContext,
														  requestedOp,
														  loc);
	}

}
