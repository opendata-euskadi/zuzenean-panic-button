package pb01.ui.vaadin.view;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pb01.ui.vaadin.orgentity.organization.PB01DetailWindowForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01DetailWindowForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01DetailWindowForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01DetailWindowForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.workplace.PB01DetailWindowForWorkPlace;
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
import r01f.ui.i18n.UII18NService;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
abstract class PB01CascadedCombos {
/////////////////////////////////////////////////////////////////////////////////////////
//	ORGANIZATION
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrganization
		 extends PB01CascadedCombo<PB01AOrganizationOID,PB01AOrganizationID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrganization(final UII18NService i18n,
												final PB01MainViewPresenter presenter,
												final PB01DetailWindowForOrganization popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.org.multiple"),i18n.getCurrentLanguage(),
				  popupWin,
				  PB01AOrganizationOID.class,PB01AOrganizationID.class);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrgDivision
		 extends PB01CascadedCombo<PB01AOrgDivisionOID,PB01AOrgDivisionID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrgDivision(final UII18NService i18n,
											   final PB01MainViewPresenter presenter,
											   final PB01DetailWindowForOrgDivision popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.org.division.multiple"),i18n.getCurrentLanguage(),
				  popupWin,
				  PB01AOrgDivisionOID.class,PB01AOrgDivisionID.class);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrgDivisionService
		 extends PB01CascadedCombo<PB01AOrgDivisionServiceOID,PB01AOrgDivisionServiceID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrgDivisionService(final UII18NService i18n,
											   		  final PB01MainViewPresenter presenter,
											   		  final PB01DetailWindowForOrgDivisionService popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.org.service.multiple"),i18n.getCurrentLanguage(),
				  popupWin,
				  PB01AOrgDivisionServiceOID.class,PB01AOrgDivisionServiceID.class);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE LOCATION
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForOrgDivisionServiceLocation
		 extends PB01CascadedCombo<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForOrgDivisionServiceLocation(final UII18NService i18n,
											   		  		  final PB01MainViewPresenter presenter,
											   		  		  final PB01DetailWindowForOrgDivisionServiceLocation popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.org.location.multiple"),i18n.getCurrentLanguage(),
				  popupWin,
				  PB01AOrgDivisionServiceLocationOID.class,PB01AOrgDivisionServiceLocationID.class);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	WORKPLACE
/////////////////////////////////////////////////////////////////////////////////////////
	static class PB01CascadedComboForWorkPlace
		 extends PB01CascadedCombo<PB01AWorkPlaceOID,PB01AWorkPlaceID> {
		private static final long serialVersionUID = -782483124752935372L;

		public PB01CascadedComboForWorkPlace(final UII18NService i18n,
											 final PB01MainViewPresenter presenter,
											 final PB01DetailWindowForWorkPlace popupWin) {
			super(presenter,
				  i18n.getMessage("pb01.org.workPlace.multiple"),i18n.getCurrentLanguage(),
				  popupWin,
				  PB01AWorkPlaceOID.class,PB01AWorkPlaceID.class);
		}
	}
}
