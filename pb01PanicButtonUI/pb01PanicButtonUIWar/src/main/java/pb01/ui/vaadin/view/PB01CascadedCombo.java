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
import pb01.ui.vaadin.view.components.PB01VaadinComboItem;
import r01f.locale.Language;
import r01f.patterns.FactoryFrom;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.util.types.collections.CollectionUtils;
import r01f.util.types.collections.Lists;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceOID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrganizationOID;
import x47b.model.org.X47BOrgObjectType;
import x47b.model.org.X47BOrganizationalObjectRef;

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
 * @param <V>
 * @param <W>
 */
@Slf4j
@Accessors(prefix="_")
abstract class PB01CascadedCombo<O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>,
							     V extends PB01ViewObjForOrganizationalEntityBase<O,I,?>,
							     W extends Window & PB01DetailWindowForOrgEntity>
	 extends HorizontalLayout {

	private static final long serialVersionUID = -8987361238414099521L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final transient PB01MainViewPresenter _presenter;

	private final Language _lang;

	// the prop up window where the [org entity] details are edited
	private final W _detailPopUp;

	// oid & id from string factories (used to create the [org entity ref]
	private final FactoryFrom<String,O> _oidFactory;
	private final FactoryFrom<String,I> _idFactory;

	// The combo and the [create] [edit] buttons
	private final ComboBox<PB01VaadinComboItem> _combo;
	private final Button _createButton;
	private final Button _editButton;

	// The combo-selected item
	private X47BOrganizationalObjectRef<O,I> _selectedOrgEntityRef;

	// The parent & child combo (can be null)
	private final PB01CascadedCombo<?,?,?,?> _parentCombo;
	private Collection<PB01CascadedCombo<?,?,?,?>> _childCombos;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CascadedCombo(final PB01MainViewPresenter presenter,
							 final String caption,final Language lang,
							 final W popupWin,
							 final Class<O> oidType,final Class<I> idType,
							 final PB01CascadedCombo<?,?,?,?> parentCombo) {
		_presenter = presenter;

		_lang = lang;

		// the detail edit popup ref
		_detailPopUp = popupWin;

		// the parent & child combos
		_parentCombo = parentCombo;

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
	public X47BOrganizationalObjectRef<O,I> getSelectedOrgEntityRef() {
		return _selectedOrgEntityRef;
	}
	public void clearSelected() {
		_selectedOrgEntityRef = null;
		_combo.setValue(null);
	}
	public PB01VaadinComboItem getSelectedComboItem() {
		return _combo.getValue();
	}
	public PB01CascadedCombo<?,?,?,?> getParentCombo() {
		return _parentCombo;
	}
	public Collection<PB01CascadedCombo<?,?,?,?>> getChildCombos() {
		return _childCombos;
	}
	public void setChildCombos(final PB01CascadedCombo<?,?,?,?>... childCombos) {
		_childCombos = CollectionUtils.hasData(childCombos) ? Lists.newArrayList(childCombos)
															: null;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	REFRESH
/////////////////////////////////////////////////////////////////////////////////////////
	public void refreshAndSelect(final V viewObj) {
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
		_selectedOrgEntityRef = null;
		_combo.setValue(null);

		if (_parentCombo == null) {
			// it's the "root" combo
			_presenter.onOrgsComboDataNeeded(_lang,
											 this::_setComboItems);
		} else {
			final X47BOrganizationalObjectRef<?,?> parentOrgEntityRef = _parentCombo.getSelectedOrgEntityRef();
			if (parentOrgEntityRef == null) {
				_combo.setDataProvider(DataProvider.ofCollection(Lists.newArrayList()));
			} else {
				// use the parent combo oid to guess the object type
				X47BPersistableObjectOID parentObjOid = parentOrgEntityRef.getOid();
				final X47BOrgObjectType parentObjType = X47BOrgObjectType.ofOIDType(parentObjOid.getClass());

				// call the correct presenter method depending on the object type
				switch(parentObjType) {
				case ORGANIZATION:
					_presenter.onOrgDivisionsComboDataNeeded((X47BOrganizationOID)parentObjOid,
													 		 _lang,
													 		 this::_setComboItems);			// when loaded the combo data, just refresh the combo
					break;
				case ORG_DIVISION:
					_presenter.onOrgDivisionServicesComboDataNeeded((X47BOrgDivisionOID)parentObjOid,
													 		 		_lang,
													 		 		this::_setComboItems);	// when loaded the combo data, just refresh the combo
					break;
				case ORG_DIVISION_SERVICE:
					_presenter.onOrgDivisionServiceLocationsComboDataNeeded((X47BOrgDivisionServiceOID)parentObjOid,
													 		 				_lang,
													 		 				this::_setComboItems);	// when loaded the combo data, just refresh the combo
					break;
				case ORG_DIVISION_SERVICE_LOCATION:
					_presenter.onWorkPlacesComboDataNeeded((X47BOrgDivisionServiceLocationOID)parentObjOid,
													 	   _lang,
													 	   this::_setComboItems);	// when loaded the combo data, just refresh the combo
					break;
				case WORKPLACE:
					throw new IllegalStateException();
				default:
					throw new IllegalStateException();
				}
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
		// store the selected org entity ref
		_selectedOrgEntityRef = _combo.getValue() != null
											? _combo.getValue()
												    .getOrgEntityRefUsing(_oidFactory,
												     					  _idFactory)
											: null;

		// child combos
		if (CollectionUtils.hasData(_childCombos)) {
			for (PB01CascadedCombo<?,?,?,?> childCmb : _childCombos) {
				childCmb.refresh();				// reload content
				childCmb.clearSelected();		// reset the selected element the underlying combos
			}
		}

		// can edit org entity? only if there's something selected on the combo
		_editButton.setEnabled(_selectedOrgEntityRef != null);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	SHOW POP-UP
/////////////////////////////////////////////////////////////////////////////////////////
	private void _showOrgEntityDetailViewForCreatingNewRecord() {
		// after save at the detail view the action to be done i refresh the list & close the detail view
		final PB01OrgEntityDetailWinForCreateVisitor createVisitor =
					new PB01OrgEntityDetailWinForCreateVisitor(_orgEntityRefChain(),
												 		  	   // save subscriber: executed after save: refresh combos & grid
													 		   UIPresenterSubscriber.from(this::_onOrgEntitySaved,		// OK subscriber
												 		    						      this::_onPersistenceError));	// Error subscriber,
		_detailPopUp.openForCreating(createVisitor);

		// show the window
		UI.getCurrent()
		  .addWindow(_detailPopUp);
	}
	private void _showOrgEntityDetailViewForEditingExistingRecord() {
		_showOrgEntityDetailViewForEditingExistingRecord(_selectedOrgEntityRef);
	}
	private void _showOrgEntityDetailViewForEditingExistingRecord(final X47BOrganizationalObjectRef<O,I> orgEntityRef) {
		if (orgEntityRef == null) throw new IllegalStateException("NO org entity ref of the object to be edited!");

		// after save or delete at the detail view the action to be done is the same: refresh the list & close the detail view
		final PB01OrgEntityDetailWinForEditVisitor editVisitor =
					new PB01OrgEntityDetailWinForEditVisitor(orgEntityRef,
												 		  	 // save subscriber: executed after save: refresh combos & grid
													 		 UIPresenterSubscriber.from(this::_onOrgEntitySaved,	// OK subscriber
												 		    						    this::_onPersistenceError),	// Error subscriber,
													 		 // delete subscriber: executed after delete
													 		 UIPresenterSubscriber.from(this::_onOrgEntityDeleted,	// OK subscriber
											 		    						        this::_onPersistenceError));// Error subscriber
		_detailPopUp.openForEdit(editVisitor);

		// show the window
		UI.getCurrent()
		  .addWindow(_detailPopUp);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	AFTER SAVE or DELETE ON DETAIL POPUP WIN
/////////////////////////////////////////////////////////////////////////////////////////
	private void _onOrgEntitySaved(final V viewObj) {
		// refresh the combo
		this.refreshAndSelect(viewObj);

		// close the popup window
		_detailPopUp.close();
	}
	private void _onOrgEntityDeleted(final V viewObj) {
		// refresh the combo
		refresh();

		// close the popup
		_detailPopUp.close();
	}
	private void _onPersistenceError(final Throwable th) {
		log.error("Error while saving an entity: {}",
		  		  th.getMessage(),th);
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
	private List<X47BOrganizationalObjectRef<?,?>> _orgEntityRefChain() {
		final List<X47BOrganizationalObjectRef<?,?>> chain = Lists.newArrayList();
		_recurseOrgEntityRefChain(chain,
								  this);
		return chain;
	}
	private void _recurseOrgEntityRefChain(final List<X47BOrganizationalObjectRef<?,?>> chain,
										   final PB01CascadedCombo<?,?,?,?> currCombo) {
		if (currCombo == null) return;
		final X47BOrganizationalObjectRef<?,?> parentRef = currCombo.getParentCombo() != null ? currCombo.getParentCombo().getSelectedOrgEntityRef()
																						 	  : null;
		if (parentRef != null) {
			chain.add(parentRef);
			_recurseOrgEntityRefChain(chain,
								  	  currCombo.getParentCombo());	// BEWARE!! recursion
		}
	}
}
