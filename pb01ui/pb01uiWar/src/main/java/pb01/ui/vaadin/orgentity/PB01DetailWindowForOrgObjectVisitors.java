package pb01.ui.vaadin.orgentity;

import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pb01.ui.vaadin.orgentity.organization.PB01DetailWindowForOrganization;
import pb01.ui.vaadin.orgentity.organization.PB01ViewObjForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01DetailWindowForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivision.PB01ViewObjForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01DetailWindowForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01ViewObjForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01DetailWindowForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01ViewObjForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.workplace.PB01DetailWindowForWorkPlace;
import pb01.ui.vaadin.orgentity.workplace.PB01ViewObjForWorkPlace;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrganizationID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AWorkPlaceID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrganizationOID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AWorkPlaceOID;
import pb01a.model.org.PB01AOrgObjectRef;
import r01f.ui.presenter.UIPresenterSubscriber;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01DetailWindowForOrgObjectVisitors {
/////////////////////////////////////////////////////////////////////////////////////////
//	EDIT VISITOR
/////////////////////////////////////////////////////////////////////////////////////////
	@RequiredArgsConstructor
	public static class PB01OrgObjectDetailWinForEditVisitor {
		private final PB01AOrgObjectRef<?,?> _orgEntityRef;
		private final UIPresenterSubscriber<?> _saveSubscriber;
		private final UIPresenterSubscriber<?> _deleteSubscriber;

		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForOrganization orgWin) {
			final PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> orgRef = (PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>)_orgEntityRef;
			orgWin.forEditing(orgRef.getOid(),
							  (UIPresenterSubscriber<PB01ViewObjForOrganization>)_saveSubscriber,
							  (UIPresenterSubscriber<PB01ViewObjForOrganization>)_deleteSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForOrgDivision orgDivWin) {
			final PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID> orgDivRef = (PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID>)_orgEntityRef;
			orgDivWin.forEditing(orgDivRef.getOid(),
								 (UIPresenterSubscriber<PB01ViewObjForOrgDivision>)_saveSubscriber,
								 (UIPresenterSubscriber<PB01ViewObjForOrgDivision>)_deleteSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForOrgDivisionService orgDivSrvcWin) {
			final PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> orgDivSrvcRef = (PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID>)_orgEntityRef;
			orgDivSrvcWin.forEditing(orgDivSrvcRef.getOid(),
								     (UIPresenterSubscriber<PB01ViewObjForOrgDivisionService>)_saveSubscriber,
								     (UIPresenterSubscriber<PB01ViewObjForOrgDivisionService>)_deleteSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForOrgDivisionServiceLocation orgDivSrvcLocWin) {
			final PB01AOrgObjectRef<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID> orgDivSrvcLocRef = (PB01AOrgObjectRef<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID>)_orgEntityRef;
			orgDivSrvcLocWin.forEditing(orgDivSrvcLocRef.getOid(),
								        (UIPresenterSubscriber<PB01ViewObjForOrgDivisionServiceLocation>)_saveSubscriber,
								        (UIPresenterSubscriber<PB01ViewObjForOrgDivisionServiceLocation>)_deleteSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForWorkPlace workPlaceWin) {
			final PB01AOrgObjectRef<PB01AWorkPlaceOID,PB01AWorkPlaceID> workPlaceRef = (PB01AOrgObjectRef<PB01AWorkPlaceOID,PB01AWorkPlaceID>)_orgEntityRef;
			workPlaceWin.forEditing(workPlaceRef.getOid(),
								    (UIPresenterSubscriber<PB01ViewObjForWorkPlace>)_saveSubscriber,
								    (UIPresenterSubscriber<PB01ViewObjForWorkPlace>)_deleteSubscriber);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	CREATE VISITOR
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Every concrete sub-type of {@link PB01DetailWindowForOrganizationalEntityBase}
	 * exposes a different "forCreating" since the params to create a new object difers
	 * ie:
	 * 		- creating an [org] object does NOT require any parent object info
	 * 		- ... but creating an [org division] object requieres an [org] reference (oid & id)
	 * 		- ... in the same way, creating an [org division service] object requires both
	 * 			  an [org] and [org division] references (oid & id)
	 * 		- ... and so on
	 * Since every "forCreating" method in concrete sub-types of {@link PB01DetailWindowForOrganizationalEntityBase}
	 * is different from each other, a VISITOR pattern is used
	 */
	@RequiredArgsConstructor
	public static class PB01OrgObjectDetailWinForCreateVisitor {
		private final List<PB01AOrgObjectRef<?,?>> _orgEntityRefChain;
		private final UIPresenterSubscriber<?> _saveSubscriber;

		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForOrganization orgWin) {
			orgWin.forCreating((UIPresenterSubscriber<PB01ViewObjForOrganization>)_saveSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForOrgDivision orgDivWin) {
			if (_orgEntityRefChain.size() != 1) throw new IllegalStateException("chain size is " + _orgEntityRefChain.size() + "; expected 1");

			final PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> orgRef = (PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>)_orgEntityRefChain.get(0);
			orgDivWin.forCreating(orgRef,
								  (UIPresenterSubscriber<PB01ViewObjForOrgDivision>)_saveSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForOrgDivisionService orgDivSrvcWin) {
			if (_orgEntityRefChain.size() != 2) throw new IllegalStateException("chain size is " + _orgEntityRefChain.size() + "; expected 2");

			final PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> orgRef = (PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>)_orgEntityRefChain.get(1);
			final PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID> orgDivRef = (PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID>)_orgEntityRefChain.get(0);
			orgDivSrvcWin.forCreating(orgRef,orgDivRef,
								      (UIPresenterSubscriber<PB01ViewObjForOrgDivisionService>)_saveSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForOrgDivisionServiceLocation orgDivSrvcLocWin) {
			if (_orgEntityRefChain.size() != 3) throw new IllegalStateException("chain size is " + _orgEntityRefChain.size() + "; expected 3");

			final PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> orgRef = (PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>)_orgEntityRefChain.get(2);
			final PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID> orgDivRef = (PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID>)_orgEntityRefChain.get(1);
			final PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> orgDivSrvcRef = (PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID>)_orgEntityRefChain.get(0);
			orgDivSrvcLocWin.forCreating(orgRef,orgDivRef,orgDivSrvcRef,
								         (UIPresenterSubscriber<PB01ViewObjForOrgDivisionServiceLocation>)_saveSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForWorkPlace workPlaceWin) {
			if (_orgEntityRefChain.size() != 4) throw new IllegalStateException("chain size is " + _orgEntityRefChain.size() + "; expected 4");

			final PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID> orgRef = (PB01AOrgObjectRef<PB01AOrganizationOID,PB01AOrganizationID>)_orgEntityRefChain.get(3);
			final PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID> orgDivRef = (PB01AOrgObjectRef<PB01AOrgDivisionOID,PB01AOrgDivisionID>)_orgEntityRefChain.get(2);
			final PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> orgDivSrvcRef = (PB01AOrgObjectRef<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID>)_orgEntityRefChain.get(1);
			final PB01AOrgObjectRef<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID> workPlaceRef = (PB01AOrgObjectRef<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID>)_orgEntityRefChain.get(0);
			workPlaceWin.forCreating(orgRef,orgDivRef,orgDivSrvcRef,workPlaceRef,
								     (UIPresenterSubscriber<PB01ViewObjForWorkPlace>)_saveSubscriber);
		}
	}
}
