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
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrganizationID;
import x47b.model.oids.X47BOrganizationalIDs.X47BWorkPlaceID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BWorkPlaceOID;
import x47b.model.org.X47BOrgObjectRef;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01DetailWindowForOrgObjectVisitors {
/////////////////////////////////////////////////////////////////////////////////////////
//	EDIT VISITOR
/////////////////////////////////////////////////////////////////////////////////////////
	@RequiredArgsConstructor
	public static class PB01OrgObjectDetailWinForEditVisitor {
		private final X47BOrgObjectRef<?,?> _orgEntityRef;
		private final UIPresenterSubscriber<?> _saveSubscriber;
		private final UIPresenterSubscriber<?> _deleteSubscriber;

		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForOrganization orgWin) {
			final X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID> orgRef = (X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID>)_orgEntityRef;
			orgWin.forEditing(orgRef.getOid(),
							  (UIPresenterSubscriber<PB01ViewObjForOrganization>)_saveSubscriber,
							  (UIPresenterSubscriber<PB01ViewObjForOrganization>)_deleteSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForOrgDivision orgDivWin) {
			final X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivRef = (X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID>)_orgEntityRef;
			orgDivWin.forEditing(orgDivRef.getOid(),
								 (UIPresenterSubscriber<PB01ViewObjForOrgDivision>)_saveSubscriber,
								 (UIPresenterSubscriber<PB01ViewObjForOrgDivision>)_deleteSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForOrgDivisionService orgDivSrvcWin) {
			final X47BOrgObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcRef = (X47BOrgObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID>)_orgEntityRef;
			orgDivSrvcWin.forEditing(orgDivSrvcRef.getOid(),
								     (UIPresenterSubscriber<PB01ViewObjForOrgDivisionService>)_saveSubscriber,
								     (UIPresenterSubscriber<PB01ViewObjForOrgDivisionService>)_deleteSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForOrgDivisionServiceLocation orgDivSrvcLocWin) {
			final X47BOrgObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> orgDivSrvcLocRef = (X47BOrgObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID>)_orgEntityRef;
			orgDivSrvcLocWin.forEditing(orgDivSrvcLocRef.getOid(),
								        (UIPresenterSubscriber<PB01ViewObjForOrgDivisionServiceLocation>)_saveSubscriber,
								        (UIPresenterSubscriber<PB01ViewObjForOrgDivisionServiceLocation>)_deleteSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forEditingExistentOn(final PB01DetailWindowForWorkPlace workPlaceWin) {
			final X47BOrgObjectRef<X47BWorkPlaceOID,X47BWorkPlaceID> workPlaceRef = (X47BOrgObjectRef<X47BWorkPlaceOID,X47BWorkPlaceID>)_orgEntityRef;
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
		private final List<X47BOrgObjectRef<?,?>> _orgEntityRefChain;
		private final UIPresenterSubscriber<?> _saveSubscriber;

		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForOrganization orgWin) {
			orgWin.forCreating((UIPresenterSubscriber<PB01ViewObjForOrganization>)_saveSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForOrgDivision orgDivWin) {
			if (_orgEntityRefChain.size() != 1) throw new IllegalStateException("chain size is " + _orgEntityRefChain.size() + "; expected 1");

			final X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID> orgRef = (X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID>)_orgEntityRefChain.get(0);
			orgDivWin.forCreating(orgRef,
								  (UIPresenterSubscriber<PB01ViewObjForOrgDivision>)_saveSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForOrgDivisionService orgDivSrvcWin) {
			if (_orgEntityRefChain.size() != 2) throw new IllegalStateException("chain size is " + _orgEntityRefChain.size() + "; expected 2");

			final X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID> orgRef = (X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID>)_orgEntityRefChain.get(1);
			final X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivRef = (X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID>)_orgEntityRefChain.get(0);
			orgDivSrvcWin.forCreating(orgRef,orgDivRef,
								      (UIPresenterSubscriber<PB01ViewObjForOrgDivisionService>)_saveSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForOrgDivisionServiceLocation orgDivSrvcLocWin) {
			if (_orgEntityRefChain.size() != 3) throw new IllegalStateException("chain size is " + _orgEntityRefChain.size() + "; expected 3");

			final X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID> orgRef = (X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID>)_orgEntityRefChain.get(2);
			final X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivRef = (X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID>)_orgEntityRefChain.get(1);
			final X47BOrgObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcRef = (X47BOrgObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID>)_orgEntityRefChain.get(0);
			orgDivSrvcLocWin.forCreating(orgRef,orgDivRef,orgDivSrvcRef,
								         (UIPresenterSubscriber<PB01ViewObjForOrgDivisionServiceLocation>)_saveSubscriber);
		}
		@SuppressWarnings("unchecked")
		public void forCreatingNewOn(final PB01DetailWindowForWorkPlace workPlaceWin) {
			if (_orgEntityRefChain.size() != 4) throw new IllegalStateException("chain size is " + _orgEntityRefChain.size() + "; expected 4");

			final X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID> orgRef = (X47BOrgObjectRef<X47BOrganizationOID,X47BOrganizationID>)_orgEntityRefChain.get(3);
			final X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID> orgDivRef = (X47BOrgObjectRef<X47BOrgDivisionOID,X47BOrgDivisionID>)_orgEntityRefChain.get(2);
			final X47BOrgObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> orgDivSrvcRef = (X47BOrgObjectRef<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID>)_orgEntityRefChain.get(1);
			final X47BOrgObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> workPlaceRef = (X47BOrgObjectRef<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID>)_orgEntityRefChain.get(0);
			workPlaceWin.forCreating(orgRef,orgDivRef,orgDivSrvcRef,workPlaceRef,
								     (UIPresenterSubscriber<PB01ViewObjForWorkPlace>)_saveSubscriber);
		}
	}
}
