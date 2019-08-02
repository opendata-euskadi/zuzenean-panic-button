package pb01.ui.vaadin.view;

import java.util.Collection;
import java.util.List;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgEntity;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgEntityVisitors.PB01OrgEntityDetailWinForCreateVisitor;
import pb01.ui.vaadin.orgentity.PB01DetailWindowForOrgEntityVisitors.PB01OrgEntityDetailWinForEditVisitor;
import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import pb01.ui.vaadin.view.PB01CascadedComboEvents.PB01ComboValueChangedEvent;
import pb01.ui.vaadin.view.PB01CascadedComboEvents.PB01ComboValueChangedEventListener;
import pb01.ui.vaadin.view.components.PB01VaadinComboItem;
import r01f.locale.Language;
import r01f.patterns.FactoryFrom;
import r01f.patterns.OnErrorSubscriber;
import r01f.patterns.OnSuccessSubscriber;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.util.types.collections.CollectionUtils;
import r01f.util.types.collections.Lists;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrgObjectRef;
import x47b.model.org.X47BOrgObjectType;

/**
 * A combo with a [create] & [edit] buttons:
 *    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *    +  ___________________________________                    +
 *    + [ Combo                           \/|  [Create] [Edit]  +
 *    + [___________________________________|                   +
 *    +                                                         +
 *    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * @param <O>
 * @param <I>
 */
@Slf4j
@Accessors(prefix="_")
abstract class PB01CascadedCombo<O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>>
	   extends HorizontalLayout {

	private static final long serialVersionUID = -8987361238414099521L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final transient PB01MainViewPresenter _presenter;

	private final X47BOrgObjectType _orgObjectType;
	private final Language _lang;

	// the prop up window where the [org entity] details are edited
	private final PB01DetailWindowForOrgEntity _detailPopUp;

	// oid & id from string factories (used to create the [org entity ref]
	private final FactoryFrom<String,O> _oidFactory;
	private final FactoryFrom<String,I> _idFactory;

	// The combo and the [create] [edit] buttons
	private final ComboBox<PB01VaadinComboItem> _combo;
	private final Button _createButton;
	private final Button _editButton;

	// The combo-selected item
	private X47BOrgObjectRef<O,I> _selectedOrgEntityRef;

	// The parent & child combo (can be null)
	private PB01CascadedCombo<?,?> _parentCombo;
	private Collection<PB01CascadedCombo<?,?>> _childCombos;

	private PB01ComboValueChangedEventListener<O,I> _valueChangeEventListener;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CascadedCombo(final PB01MainViewPresenter presenter,
							 final String caption,final Language lang,
							 final PB01DetailWindowForOrgEntity popupWin,
							 final Class<O> oidType,final Class<I> idType) {
		_presenter = presenter;

		// type & lang
		_orgObjectType = X47BOrgObjectType.ofOIDType(oidType);
		_lang = lang;

		// the detail edit popup ref
		_detailPopUp = popupWin;

		// oid & id factories
		_oidFactory = X47BOrgObjectType.factoryForOIDType(oidType);
		_idFactory = X47BOrgObjectType.factoryForIDType(idType);

		// Create the layout
		//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//    +  ___________________________________                    +
		//    + [ Combo                           \/|  [Create] [Edit]  +
		//    + [___________________________________|                   +
		//    +                                                         +
		//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// combo
		_combo = new ComboBox<>(caption);
		_combo.setItemCaptionGenerator(PB01VaadinComboItem::getValue);
		_combo.addValueChangeListener(this::_onComboValueChanged);

		// create & edit buttons
		_createButton = new Button(VaadinIcons.PLUS);
		_createButton.setEnabled(_parentCombo == null);		// creation is ALWAYS enabled on root combo
		_createButton.addClickListener(event -> _showOrgEntityDetailViewForCreatingNewRecord());	// show the item detail dialog

		_editButton = new Button(VaadinIcons.EDIT);
		_editButton.setEnabled(false);
		_editButton.addClickListener(event -> _showOrgEntityDetailViewForEditingExistingRecord());	// show the item detail dialog

		this.addComponents(_combo,
						   _createButton,_editButton);
		this.setComponentAlignment(_createButton,Alignment.BOTTOM_LEFT);
		this.setComponentAlignment(_editButton,Alignment.BOTTOM_LEFT);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	GET & SET
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BOrgObjectRef<O,I> getSelectedOrgEntityRef() {
		return _selectedOrgEntityRef;
	}
	public void clearSelected() {
		_selectedOrgEntityRef = null;
		_combo.setValue(null);
	}
	public PB01VaadinComboItem getSelectedComboItem() {
		return _combo.getValue();
	}
	public PB01CascadedCombo<?,?> getParentCombo() {
		return _parentCombo;
	}
	public Collection<PB01CascadedCombo<?,?>> getChildCombos() {
		return _childCombos;
	}
	public void setParentCombo(final PB01CascadedCombo<?,?> parentCombo) {
		_parentCombo = parentCombo;
	}
	public void setChildCombos(final PB01CascadedCombo<?,?>... childCombos) {
		_childCombos = CollectionUtils.hasData(childCombos) ? Lists.newArrayList(childCombos)
															: null;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EDIT POPUP
/////////////////////////////////////////////////////////////////////////////////////////
	private Window _getDetailEditPopUpWindow() {
		return (Window)_detailPopUp;
	}
	private void _openDetailEditPopUp() {
		UI.getCurrent()
		  .addWindow(_getDetailEditPopUpWindow());
	}
	private void _closeDetailEditPopUp() {
		_getDetailEditPopUpWindow().close();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EVENT
/////////////////////////////////////////////////////////////////////////////////////////
	public void setValueChangeEventListener(final PB01ComboValueChangedEventListener<O,I> valueChangeEventListener) {
		_valueChangeEventListener = valueChangeEventListener;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	REFRESH
/////////////////////////////////////////////////////////////////////////////////////////
	public <V extends PB01ViewObjForOrganizationalEntityBase<O,I,?>> void refreshAndSelect(final V viewObj) {
		final PB01VaadinComboItem cmbItem = viewObj != null ? PB01VaadinComboItem.FROM_VIEW_OBJ.apply(viewObj,
																		 	  						  _lang)
															: null;
		this.refreshAndSelect(cmbItem);
	}
	public void refreshAndSelect(final PB01VaadinComboItem cmbItem) {
		this.refresh();
		if (cmbItem != null) _combo.setValue(cmbItem);
	}
	public void refresh() {
		log.debug("[CascadedCombo] refresh combo of type {}",
				  _orgObjectType);
		_selectedOrgEntityRef = null;
		_combo.setValue(null);

		if (_parentCombo == null) {
			// it's the "root" combo
			_presenter.onOrgsComboDataNeeded(_lang,
											 this::_setComboItems);
		} else {
			final X47BOrgObjectRef<?,?> parentOrgEntityRef = _parentCombo.getSelectedOrgEntityRef();
			if (parentOrgEntityRef == null) {
				// if the parent combo does NOT have a selected item, the combo must be empty
				_combo.setDataProvider(DataProvider.ofCollection(Lists.newArrayList()));
			} else {
				// tell the presenter to load the items filtering by the parent combo item
				_presenter.onOrgEntityComboDataNeeded(parentOrgEntityRef,
													  _lang,
													  this::_setComboItems);
			}
		}

		// can create org entity? only if there's something selected on the PARENT combo
		_createButton.setEnabled((_parentCombo == null)
								 		||
								 (_parentCombo != null
							   && _parentCombo.getSelectedOrgEntityRef() != null));
	}
	private void _setComboItems(final Collection<PB01VaadinComboItem> items) {
 		final ListDataProvider<PB01VaadinComboItem> dataProvider = DataProvider.ofCollection(items);
 		_combo.setDataProvider(dataProvider);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	VALUE CHANGE
/////////////////////////////////////////////////////////////////////////////////////////
	private void _onComboValueChanged(final ValueChangeEvent<PB01VaadinComboItem> event) {
		// prev selected org entity ref
		X47BOrgObjectRef<O,I> prevSelectedOrgEntityRef = _selectedOrgEntityRef;

		// store the selected org entity ref
		_selectedOrgEntityRef = _combo.getValue() != null
											? _combo.getValue()
												    .getOrgEntityRefUsing(_oidFactory,
												     					  _idFactory)
											: null;
		log.debug("[CascadedCombo] of type {} changed value from {} to {}",
				  _orgObjectType,
				  prevSelectedOrgEntityRef != null ? prevSelectedOrgEntityRef.getId() : "null",
				  _selectedOrgEntityRef != null ? _selectedOrgEntityRef.getId() : "null");
		// child combos
		if (CollectionUtils.hasData(_childCombos)) {
			log.debug("[CascadedCombo]: Refresh {} child combos",_childCombos.size());
			for (PB01CascadedCombo<?,?> childCmb : _childCombos) {
				childCmb.refresh();				// reload content
				childCmb.clearSelected();		// reset the selected element the underlying combos
			}
		}

		// can edit org entity? only if there's something selected on the combo
		_editButton.setEnabled(_selectedOrgEntityRef != null);

		// event
		if (_valueChangeEventListener != null) _valueChangeEventListener.valueChanged(new PB01ComboValueChangedEvent<O,I>(this,
																														  prevSelectedOrgEntityRef,_selectedOrgEntityRef));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SHOW POP-UP
/////////////////////////////////////////////////////////////////////////////////////////
	private void _showOrgEntityDetailViewForCreatingNewRecord() {
		// after save at the detail view the action to be done i refresh the list & close the detail view
		final PB01OrgEntityDetailWinForCreateVisitor createVisitor =
					new PB01OrgEntityDetailWinForCreateVisitor(_orgEntityRefChain(),
												 		  	   // save subscriber: executed after save: refresh combos & grid
													 		   UIPresenterSubscriber.from(new PB01CascadedComboOnSaveSuccessSuscriber(),	// OK subscriber
												 		    						      new PB01CascadedComboOnErrorsSuscriber()));						// Error subscriber,
		_detailPopUp.openForCreating(createVisitor);

		// show the window
		_openDetailEditPopUp();
	}
	private void _showOrgEntityDetailViewForEditingExistingRecord() {
		_showOrgEntityDetailViewForEditingExistingRecord(_selectedOrgEntityRef);
	}
	private void _showOrgEntityDetailViewForEditingExistingRecord(final X47BOrgObjectRef<O,I> orgEntityRef) {
		if (orgEntityRef == null) throw new IllegalStateException("NO org entity ref of the object to be edited!");

		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		final PB01OrgEntityDetailWinForEditVisitor editVisitor =
					new PB01OrgEntityDetailWinForEditVisitor(orgEntityRef,
												 		  	 // save subscriber: executed after save: refresh combos & grid
													 		 UIPresenterSubscriber.from(new PB01CascadedComboOnSaveSuccessSuscriber(),		// OK subscriber
												 		    						    new PB01CascadedComboOnErrorsSuscriber()),
													 		 UIPresenterSubscriber.from(new PB01CascadedComboOnDeleteSuccessSuscriber(),	// OK subscriber
											 		    						        new PB01CascadedComboOnErrorsSuscriber()));// Error subscriber
		_detailPopUp.openForEdit(editVisitor);

		// show the window
		_openDetailEditPopUp();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	AFTER SAVE or DELETE ON DETAIL POPUP WIN
/////////////////////////////////////////////////////////////////////////////////////////
	private class PB01CascadedComboOnSaveSuccessSuscriber
	   implements OnSuccessSubscriber<PB01ViewObjForOrganizationalEntityBase<O,I,?>> {
		@Override
		public void onSuccess(final PB01ViewObjForOrganizationalEntityBase<O,I,?> viewObj) {
			// refresh the combo
			PB01CascadedCombo.this.refreshAndSelect(viewObj);

			// close the popup window
			_closeDetailEditPopUp();
		}
	}
	private class PB01CascadedComboOnDeleteSuccessSuscriber
	   implements OnSuccessSubscriber<PB01ViewObjForOrganizationalEntityBase<O,I,?>> {
		@Override
		public void onSuccess(final PB01ViewObjForOrganizationalEntityBase<O,I,?> viewObj) {
			// refresh the combo
			refresh();

			// close the popup
			_closeDetailEditPopUp();
		}
	}
	private class PB01CascadedComboOnErrorsSuscriber
	   implements OnErrorSubscriber {
		@Override
		public void onError(final Throwable th) {
			log.error("Error while saving an entity: {}",
			  		  th.getMessage(),th);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Composes a chain of references to org entity objects:
	 * Since every combo has two fields:
	 * 		- a parent combo pointer
	 * 		- the [org entity] ref of the currently selected [org entity]
	 * ... so if for example, the user has selected until the [org division service location] combo:
	 * <pre>
	 * 		[Organizations \/] <--
	 *                            |
	 * 			     [Org Divisions \/]  <---
	 *                                       |
	 * 				       [Org Division Services \/]  <---
	 *                                                     |
	 * 					       [Org Division Service Locations \/]
	 *
	 * 						       [WorkPlaces Locations \/]
	 * </pre>
	 * if this function is called on the deepest selected level: [org division service location],
	 * it will return the following refs (in reverse order):
	 * 		[org division service location] -> [org division service] -> [org division] -> [org]
	 * @return
	 */
	private List<X47BOrgObjectRef<?,?>> _orgEntityRefChain() {
		final List<X47BOrgObjectRef<?,?>> chain = Lists.newArrayList();
		_recurseOrgEntityRefChain(chain,
								  this);
		return chain;
	}
	private void _recurseOrgEntityRefChain(final List<X47BOrgObjectRef<?,?>> chain,
										   final PB01CascadedCombo<?,?> currCombo) {
		if (currCombo == null) return;
		final X47BOrgObjectRef<?,?> parentRef = currCombo.getParentCombo() != null ? currCombo.getParentCombo().getSelectedOrgEntityRef()
																						 	  : null;
		if (parentRef != null) {
			chain.add(parentRef);
			_recurseOrgEntityRefChain(chain,
								  	  currCombo.getParentCombo());	// BEWARE!! recursion
		}
	}
}
