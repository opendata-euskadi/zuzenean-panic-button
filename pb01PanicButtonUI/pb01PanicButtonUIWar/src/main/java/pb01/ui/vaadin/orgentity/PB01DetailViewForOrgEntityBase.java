package pb01.ui.vaadin.orgentity;

import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import r01f.ui.i18n.UII18NService;
import r01f.ui.vaadin.annotations.VaadinViewComponentLabels;
import r01f.ui.vaadin.annotations.VaadinViewField;
import r01f.ui.vaadin.view.VaadinDetailView;
import r01f.ui.vaadin.view.VaadinViewHasVaadinViewObjectBinder;
import r01f.ui.vaadin.view.VaadinViews;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

public class PB01DetailViewForOrgEntityBase<O extends X47BPersistableObjectOID,M extends X47BOrganizationalPersistableObject<O,? extends X47BPersistableObjectID<?>>,
											V extends PB01ViewObjForOrganizationalEntityBase<O,? extends X47BPersistableObjectID<?>,M>>
	 extends VerticalLayout
  implements VaadinDetailView<M>,
  			 VaadinViewHasVaadinViewObjectBinder<V> {

	private static final long serialVersionUID = 1763832401233570646L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final UII18NService _i18n;
	private final Binder<V> _binder;
	private final Class<V> _viewObjType;
	private V _viewObj;

/////////////////////////////////////////////////////////////////////////////////////////
//  UI
/////////////////////////////////////////////////////////////////////////////////////////
	@VaadinViewField(bindToViewObjectFieldNamed="oid")
	@VaadinViewComponentLabels(captionI18NKey="oid",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtOid = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed="id",required=true)
	@VaadinViewComponentLabels(captionI18NKey="id",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtId = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed="nameES",required=true)
	@VaadinViewComponentLabels(captionI18NKey="orgEntity.name.es",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtNameES = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed="nameEU",required=true)
	@VaadinViewComponentLabels(captionI18NKey="orgEntity.name.eu",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtNameEU = new TextField();
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected PB01DetailViewForOrgEntityBase(final UII18NService i18n,
								   			 final Class<V> viewObjType) {
		_i18n = i18n;

		_viewObjType = viewObjType;
		_binder = new Binder<>(viewObjType);

		// style window
		this.setWidth(100,Unit.PERCENTAGE);
		this.setMargin(false);
		this.setStyleName("r01ui-window");

		// set components' labels & placeholders using the @VaadinViewComponentLabels annotation
		VaadinViews.using(_i18n)
				   .setI18NLabelsOf(this);

		// style
		_txtOid.setWidth(100,Unit.PERCENTAGE);
		_txtOid.setReadOnly(true);

		_txtId.setWidth(100,Unit.PERCENTAGE);
		_txtId.setRequiredIndicatorVisible(true);

		_txtNameES.setWidth(100,Unit.PERCENTAGE);
		_txtNameES.setRequiredIndicatorVisible(true);

		_txtNameEU.setWidth(100,Unit.PERCENTAGE);
		_txtNameEU.setRequiredIndicatorVisible(true);


		// Layout
		this.addComponent(_txtOid);
		this.addComponent(_txtId);
		this.addComponent(_txtNameES);
		this.addComponent(_txtNameEU);
	}
/////////////////////////////////////////////////////////////////////////////////////////
// 	VIEW OBJECT BINDING
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void bindViewTo(final V viewObj) {
		// Set the view object
		_viewObj = viewObj;

		// bind view components' fields to view object's fields
		// using @VaadinViewField annotation
		VaadinViews.using(_binder,_i18n)
				   .bindComponentsOf(this)
				   .toViewObjectOfType(_viewObjType);

		// read the bean
		_binder.readBean(viewObj);
	}
	@Override
	public boolean writeBeanIfValid(final V viewObj) {
		return _binder.writeBeanIfValid(viewObj);
	}
	@Override
	public V getViewObject() {
		return _viewObj;
	}
	@Override
	public void setViewObject(final V viewObject) {
		_viewObj = viewObject;
	}
}