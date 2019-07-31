package pb01.ui.vaadin.orgentity;

import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.view.components.PB01AcceptCancelDeleteButtonsLayout;
import r01f.ui.i18n.UII18NService;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.ui.vaadin.view.VaadinDetailView;
import r01f.ui.vaadin.view.VaadinViewFactories.VaadinViewFactory;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

/**
 * Detail window (pop up)
 * <pre>
 * ++[PB01DetailWindowForXX]++++++++++++++++++++++++++++++++++++++++
 * +                                                               +
 * +   ++[PB01DetailViewForXX] +++++++++++++++++++++++++++++++++ + +
 * +   +                                                         + +
 * +   +                                                         + +
 * +   +      The form                                           + +
 * +   +                                                         + +
 * +   +                                                         + +
 * +   +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ +
 * +                                                               +
 * +   +[PB01AcceptCancelDeleteButtonsLayout]+++++++++++++++++++++ +
 * +   +   [Cancel] [Save]                            [Delete]   + +
 * +   +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ +
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * </pre>
 */
@Slf4j
public abstract class PB01DetailWindowForOrganizationalEntityBase<O extends X47BPersistableObjectOID,M extends X47BOrganizationalPersistableObject<O,? extends X47BPersistableObjectID<?>>,
														   		  V extends PB01ViewObjForOrganizationalEntityBase<O,? extends X47BPersistableObjectID<?>,M>,
														   		  C extends PB01COREMediatorForOrganizationalEntityBase<O,M>,	// detail CORE mediator
														   		  P extends PB01DetailPresenterForOrgEntityBase<O,M,V,C>,		// detail view presenter
														   		  D extends PB01DetailViewForOrgEntityBase<O,M,V>> 				// detail view
              extends Window
           implements VaadinDetailView<M> {

	private static final long serialVersionUID = 5732281917637898689L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	protected final UII18NService _i18n;

	// UI
	protected final D _detailView;
	protected final P _detailViewPresenter;

	protected final PB01AcceptCancelDeleteButtonsLayout _btnAcepCancDelete;

	// View object
	protected V _viewObj;

	//  OUTSIDE WORLD SUBSCRIBERS
	protected UIPresenterSubscriber<V> _saveSubscriber;
	protected UIPresenterSubscriber<V> _deleteSubscriber;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected PB01DetailWindowForOrganizationalEntityBase(final UII18NService i18n,
														  final VaadinViewFactory<D> detailViewFactory,
														  final P detailViewPresenter) {
		_i18n = i18n;
		_detailViewPresenter = detailViewPresenter;
		_detailView = detailViewFactory.from(i18n);

		// OK | CANCEL | DELETE
		_btnAcepCancDelete = new PB01AcceptCancelDeleteButtonsLayout( i18n );
		// - CANCEL
		_btnAcepCancDelete.addCancelButtonClickListner(event -> PB01DetailWindowForOrganizationalEntityBase.this.close() );
		// - OK
		_btnAcepCancDelete.addAcceptButtonClickListner(event -> {
																	// [1] - collect ui controls values into the view object
																	if (_detailView.writeBeanIfValid(_viewObj)) {
																		// [2] - tell the presenter to save
																		M obj = _viewObj.getWrappedModelObject();
																		_detailViewPresenter.onSaveRequested(obj,
																								   			 _saveSubscriber);
																	} else {
																		Notification.show(_i18n.getMessage("notification.empty.fields"));
																	}
																});
		// - DELETE
//		_btnAcepCancDelete.getDeleteButton()
//					.addClickListener((event) -> {
//													// set the delete confirm dialog obj oid
//													_deleteConfirmDialog.setObjToBeDeletedOid(_viewObj.getOid(),
//																							  _deleteSubscriber);
//
//													// show the dialog
//													UI.getCurrent()
//													  .addWindow( _deleteConfirmDialog );
//												  });

		// Layout
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin( true );
		layout.addComponent(_detailView);
		layout.addComponent(_btnAcepCancDelete);

		this.setContent(layout);
	}
/////////////////////////////////////////////////////////////////////////////////////////
// 	PUBLIC ENTRY POINT
/////////////////////////////////////////////////////////////////////////////////////////
	public void forEditing(final O oid,
			  		 	   final UIPresenterSubscriber<V> saveSubscriber,			// what to do after saving
			  		 	   final UIPresenterSubscriber<V> deleteSubscriber) {		// what to do after deleting
		if (oid == null) throw new IllegalArgumentException("An oid is needed to edit a record!");

		_saveSubscriber = saveSubscriber;
		_deleteSubscriber = deleteSubscriber;

		_detailViewPresenter.onLoadRequested(oid,
									 		 // what to do AFTER loading the data
								   			 UIPresenterSubscriber.from(
													// on success
												   	viewObj -> {
																	log.info( "... setting item with oid={} into view",
																			 viewObj.getOid() );
																	_viewObj = viewObj;		// store the view object

																	// bind the view object to the view
																	_detailView.bindViewTo(_viewObj);

																	// set the buttons status
																	_btnAcepCancDelete.setEditingExistingRecordStatus();
												   				},
												   	// on error
												   	th -> {
														// TODO show an alert
														log.error( "Error loading object with oid={}: {}",
																  oid,
																  th.getMessage(),th );
												   	}));
	}
}
