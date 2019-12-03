package pb01.ui.vaadin.view.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import lombok.experimental.Accessors;
import r01f.ui.i18n.UII18NService;

@Accessors( prefix="_" )
public class PB01AcceptCancelDeleteButtonsLayout
	 extends VerticalLayout {

	private static final long serialVersionUID = 5892967547409836937L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	// Accept / cancel / delete
	private final Button _acceptButton = new Button();
	private final Button _cancelButton = new Button();
	private final Button _deleteButton = new Button();

	// Delete confirmation
	private final TextField _txtToCheckDeletion = new TextField();
	private final Button _deleteOKButton = new Button();
	private final Button _deleteCancelButton = new Button();

	private final PB01DeletionPuzzleCheck _deletionPuzzleCheck;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Creates a layout with two layers like:
	 *     ++++++++++++++++++++++++++++++++++++++++++++++++++++
	 *     +  ++++++++++++++++++++++++++++++++++++++++++++++  +
	 *     +  + [Cancel] [Accept]                 [Delete] +  +
	 *     +  ++++++++++++++++++++++++++++++++++++++++++++++  +
	 *     +                                                  +
	 *     +  ++++++++++++++++++++++++++++++++++++++++++++++  +
	 *     +  +  --------------------------                +  +
	 *     +  + | del confirm txt          | [OK] [cancel] +  +
	 *     +  +  --------------------------                +  +
	 *     +  ++++++++++++++++++++++++++++++++++++++++++++++  +
	 *     +                                                  +
	 *     ++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	public PB01AcceptCancelDeleteButtonsLayout(final UII18NService i18n,
											   final PB01DeletionPuzzleCheck deletionPuzzleCheck) {
		super.setSizeFull();

		_deletionPuzzleCheck = deletionPuzzleCheck;

		// [accept] [cancel] [delete]
		HorizontalLayout acdLayout = _createACDLayout(i18n);
		this.addComponent(acdLayout);

		// Delete confirm
		// 		[..text] [OK][cancel]
		HorizontalLayout delConfirmLayout = _createDelConfirmLayout(i18n);
		this.addComponent(delConfirmLayout);
		delConfirmLayout.setVisible(false);
	}
	private HorizontalLayout _createACDLayout(final UII18NService i18n) {
		// accept
		_acceptButton.setCaption(i18n.getMessage("pb01.common.buttons.save"));
		_acceptButton.setStyleName("primary");

		// cancel
		_cancelButton.setCaption(i18n.getMessage("pb01.common.buttons.cancel"));

		// delete
		_deleteButton.setCaption(i18n.getMessage("pb01.common.buttons.delete"));
		_deleteButton.setStyleName("danger");
		_deleteButton.addClickListener(event -> _enterDeletionCheckingMode());	// when clicking delete enter [deletion checking mode]

		// layout
		HorizontalLayout acdLayout = new HorizontalLayout();
		acdLayout.setSizeFull();
		acdLayout.addComponents(_cancelButton,
								_acceptButton,
								_deleteButton );

		acdLayout.setExpandRatio(_acceptButton,
							 	 1);
		acdLayout.setComponentAlignment(_deleteButton,
										Alignment.MIDDLE_RIGHT);
		return acdLayout;
	}
	private HorizontalLayout _createDelConfirmLayout(final UII18NService i18n) {
		HorizontalLayout delChkLayout = new HorizontalLayout();
		delChkLayout.setSizeFull();

		_deleteOKButton.setEnabled(false);
		_deleteOKButton.setIcon(VaadinIcons.ARROW_RIGHT);
		_deleteOKButton.setStyleName("danger");

		_deleteCancelButton.setIcon(VaadinIcons.ARROW_CIRCLE_LEFT);

		_txtToCheckDeletion.setSizeFull();
		// when the text to be checked changes, check if it puzzle is correct
		// ... so the OK button can be activated
		_txtToCheckDeletion.addValueChangeListener(event -> {	// enable the [delete ok] button if the puzzle is correct
																boolean delPuzzleOK = _deletionPuzzleCheck.check(event.getValue());
																_deleteOKButton.setEnabled(delPuzzleOK);
															});

		// if cancel, just exit del check mode
		_deleteCancelButton.addClickListener(event -> _exitDeletionCheckingMode());

		delChkLayout.addComponents(_txtToCheckDeletion,
								   _deleteOKButton,_deleteCancelButton);
		return delChkLayout;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private void _enterDeletionCheckingMode() {
		this.getComponent(0)	// the first HorizontalLayout for the [accept] [cancel] [delete] buttons
			.setVisible(false);
		this.getComponent(1)	// the second HorizontalLayout for the [..text] [OK][cancel] buttons
			.setVisible(true);
	}
	private void _exitDeletionCheckingMode() {
		_txtToCheckDeletion.setValue("");	// clear the previous puzzle text

		this.getComponent(0)	// the first HorizontalLayout for the [accept] [cancel] [delete] buttons
			.setVisible(true);
		this.getComponent(1)	// the second HorizontalLayout for the [..text] [OK][cancel] buttons
			.setVisible(false);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void addCancelButtonClickListner(final ClickListener clickListener) {
		_cancelButton.addClickListener(clickListener);
	}
	public void addAcceptButtonClickListner(final ClickListener clickListener) {
		_acceptButton.addClickListener(clickListener);
	}
	public void addDeleteButtonClickListener(final ClickListener clickListener) {
		// BEWARE!!! 	The click listener is attached to the [deletion confirm OK button]
		_deleteOKButton.addClickListener(event -> {	// just delegate to the given listener
													// ... but BEFORE, exit del check mode
														_exitDeletionCheckingMode();
														clickListener.buttonClick(event);
												  });
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void setCreatingNewRecordStatus() {
		_deleteButton.setVisible(false);
	}
	public void setEditingExistingRecordStatus() {
		_deleteButton.setVisible(true);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@FunctionalInterface
	public interface PB01DeletionPuzzleCheck {
		public boolean check(final String text);
	}
}
