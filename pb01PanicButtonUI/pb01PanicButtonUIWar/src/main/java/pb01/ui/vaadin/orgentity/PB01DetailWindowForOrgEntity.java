package pb01.ui.vaadin.orgentity;

import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgEntityVisitors.PB01OrgEntityDetailWinForCreateVisitor;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgEntityVisitors.PB01OrgEntityDetailWinForEditVisitor;

public interface PB01DetailWindowForOrgEntity {
	/**
	 * Opens a detail window for CREATING a NEW org entity object
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
	 *
	 * @param visitor
	 */
	public void openForCreating(final PB01OrgEntityDetailWinForCreateVisitor visitor);
	/**
	 * Opens a detail window for EDITING a NEW org entity object
	 * @param visitor
	 */
	public void openForEdit(final PB01OrgEntityDetailWinForEditVisitor visitor);
}
