package pb01.ui.vaadin.view;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
import r01f.ui.i18n.UII18NService;
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

@NoArgsConstructor(access=AccessLevel.PRIVATE)
abstract class PB01CascadedCombos {
/////////////////////////////////////////////////////////////////////////////////////////
//	ORGANIZATION
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrganization
		 extends PB01CascadedCombo<X47BOrganizationOID,X47BOrganizationID,
								   PB01ViewObjForOrganization,
								   PB01DetailWindowForOrganization> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrganization(final UII18NService i18n,
												final PB01MainViewPresenter presenter,
												final PB01DetailWindowForOrganization popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.orgs"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BOrganizationOID.class,X47BOrganizationID.class,
				  null);				// no parent combo
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrgDivision
		 extends PB01CascadedCombo<X47BOrgDivisionOID,X47BOrgDivisionID,
								   PB01ViewObjForOrgDivision,
								   PB01DetailWindowForOrgDivision> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrgDivision(final UII18NService i18n,
											   final PB01MainViewPresenter presenter,
											   final PB01DetailWindowForOrgDivision popupWin,
											   final PB01CascadedComboForOrganization parentCmb) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.divs"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BOrgDivisionOID.class,X47BOrgDivisionID.class,
				  parentCmb);				// parent combo
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrgDivisionService
		 extends PB01CascadedCombo<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID,
								   PB01ViewObjForOrgDivisionService,
								   PB01DetailWindowForOrgDivisionService> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrgDivisionService(final UII18NService i18n,
											   		  final PB01MainViewPresenter presenter,
											   		  final PB01DetailWindowForOrgDivisionService popupWin,
											   		  final PB01CascadedComboForOrgDivision parentCmb) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.srvcs"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BOrgDivisionServiceOID.class,X47BOrgDivisionServiceID.class,
				  parentCmb);					// parent combo

		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE LOCATION
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrgDivisionServiceLocation
		 extends PB01CascadedCombo<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,
								   PB01ViewObjForOrgDivisionServiceLocation,
								   PB01DetailWindowForOrgDivisionServiceLocation> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrgDivisionServiceLocation(final UII18NService i18n,
											   		  		  final PB01MainViewPresenter presenter,
											   		  		  final PB01DetailWindowForOrgDivisionServiceLocation popupWin,
											   		  		  final PB01CascadedComboForOrgDivisionService parentCmb) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.srvc.locs"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BOrgDivisionServiceLocationOID.class,X47BOrgDivisionServiceLocationID.class,
				  parentCmb);			// parent combo
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	WORKPLACE
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForWorkPlace
		 extends PB01CascadedCombo<X47BWorkPlaceOID,X47BWorkPlaceID,
								   PB01ViewObjForWorkPlace,
								   PB01DetailWindowForWorkPlace> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForWorkPlace(final UII18NService i18n,
											 final PB01MainViewPresenter presenter,
											 final PB01DetailWindowForWorkPlace popupWin,
											 final PB01CascadedComboForOrgDivisionServiceLocation parentCombo) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.srvc.loc.workplaces"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BWorkPlaceOID.class,X47BWorkPlaceID.class,
				  parentCombo);		// parent combo
		}
	}
}
