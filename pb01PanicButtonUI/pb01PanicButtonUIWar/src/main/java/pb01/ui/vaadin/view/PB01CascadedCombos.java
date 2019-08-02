package pb01.ui.vaadin.view;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pb01.ui.vaadin.orgentity.organization.PB01DetailWindowForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01DetailWindowForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01DetailWindowForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01DetailWindowForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.workplace.PB01DetailWindowForWorkPlace;
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
		 extends PB01CascadedCombo<X47BOrganizationOID,X47BOrganizationID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrganization(final UII18NService i18n,
												final PB01MainViewPresenter presenter,
												final PB01DetailWindowForOrganization popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.orgs"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BOrganizationOID.class,X47BOrganizationID.class);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrgDivision
		 extends PB01CascadedCombo<X47BOrgDivisionOID,X47BOrgDivisionID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrgDivision(final UII18NService i18n,
											   final PB01MainViewPresenter presenter,
											   final PB01DetailWindowForOrgDivision popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.divs"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BOrgDivisionOID.class,X47BOrgDivisionID.class);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrgDivisionService
		 extends PB01CascadedCombo<X47BOrgDivisionServiceOID,X47BOrgDivisionServiceID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrgDivisionService(final UII18NService i18n,
											   		  final PB01MainViewPresenter presenter,
											   		  final PB01DetailWindowForOrgDivisionService popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.srvcs"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BOrgDivisionServiceOID.class,X47BOrgDivisionServiceID.class);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE LOCATION
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrgDivisionServiceLocation
		 extends PB01CascadedCombo<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrgDivisionServiceLocation(final UII18NService i18n,
											   		  		  final PB01MainViewPresenter presenter,
											   		  		  final PB01DetailWindowForOrgDivisionServiceLocation popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.srvc.locs"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BOrgDivisionServiceLocationOID.class,X47BOrgDivisionServiceLocationID.class);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	WORKPLACE
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForWorkPlace
		 extends PB01CascadedCombo<X47BWorkPlaceOID,X47BWorkPlaceID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForWorkPlace(final UII18NService i18n,
											 final PB01MainViewPresenter presenter,
											 final PB01DetailWindowForWorkPlace popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.view.main.combo.org.div.srvc.loc.workplaces"),i18n.getCurrentLanguage(),
				  popupWin,
				  X47BWorkPlaceOID.class,X47BWorkPlaceID.class);
		}
	}
}
