package pb01.ui.vaadin.view.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

import lombok.experimental.Accessors;
import r01f.ui.i18n.UII18NService;

@Accessors( prefix="_" )
public class PB01AcceptCancelDeleteButtonsLayout
	 extends HorizontalLayout {

	private static final long serialVersionUID = 5892967547409836937L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final Button _acceptButton = new Button();
	private final Button _cancelButton = new Button();
	private final Button _deleteButton = new Button();
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01AcceptCancelDeleteButtonsLayout( final UII18NService i18n ) {
		super.setSizeFull();

		// accept
		_acceptButton.setCaption( i18n.getMessage("save") );
		_acceptButton.setStyleName("primary");

		// cancel
		_cancelButton.setCaption( i18n.getMessage("cancel") );

		// delete
		_deleteButton.setCaption( i18n.getMessage("delete") );
		_deleteButton.setStyleName("danger");

		// layout
		this.addComponents( _cancelButton,
							_acceptButton,
							_deleteButton );

		this.setExpandRatio( _acceptButton,
							 1 );
		this.setComponentAlignment( _deleteButton,
									Alignment.MIDDLE_RIGHT );
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
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public void setCreatingNewRecordStatus() {
		_deleteButton.setVisible(false);
	}
	public void setEditingExistingRecordStatus() {
		_deleteButton.setVisible(true);
	}
}
