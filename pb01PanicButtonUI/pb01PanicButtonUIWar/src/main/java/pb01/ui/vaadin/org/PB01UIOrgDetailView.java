package pb01.ui.vaadin.org;

import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import r01f.ui.i18n.UII18NService;
import r01f.ui.vaadin.annotations.VaadinViewComponentLabels;
import r01f.ui.vaadin.annotations.VaadinViewField;
import r01f.ui.vaadin.view.VaadinDetailView;
import r01f.ui.vaadin.view.VaadinViewHasVaadinViewObjectBinder;
import r01f.ui.vaadin.view.VaadinViews;
import x47b.model.org.X47BOrganization;

public class PB01UIOrgDetailView
	 extends VerticalLayout
  implements VaadinDetailView<X47BOrganization>,
  			 VaadinViewHasVaadinViewObjectBinder<PB01UIViewOrganization> {

	private static final long serialVersionUID = 1763832401233570646L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final UII18NService _i18n;
	private PB01UIViewOrganization _viewObj;
	private final Binder<PB01UIViewOrganization> _binder = new Binder<>(PB01UIViewOrganization.class);

/////////////////////////////////////////////////////////////////////////////////////////
//  UI
/////////////////////////////////////////////////////////////////////////////////////////
	@VaadinViewField(bindToViewObjectFieldNamed="oid")
	@VaadinViewComponentLabels(captionI18NKey="oid",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtOid = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed="nameES",required=true)
	@VaadinViewComponentLabels(captionI18NKey="orgEntity.name.es",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtNameES = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed="nameEU",required=true)
	@VaadinViewComponentLabels(captionI18NKey="orgEntity.name.eu",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtNameEU = new TextField();
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	PB01UIOrgDetailView(final UII18NService i18n) {
		_i18n = i18n;

		// style window
		this.setWidth(100,Unit.PERCENTAGE);
		this.setMargin(false);
		this.setStyleName("r01ui-window");

		// set components' labels & placeholders using the @VaadinViewComponentLabels annotation
		VaadinViews.using(_i18n)
				   .setI18NLabelsOf(this);

		// METHOD B) Manually
		_txtOid.setWidth(100,Unit.PERCENTAGE);
		_txtOid.setReadOnly(true);

		_txtNameES.setWidth(100,Unit.PERCENTAGE);
		_txtNameES.setRequiredIndicatorVisible(true);

		_txtNameEU.setWidth(100,Unit.PERCENTAGE);
		_txtNameEU.setRequiredIndicatorVisible(true);


		// Layout
		this.addComponent(_txtOid);
		this.addComponent(_txtNameES);
		this.addComponent(_txtNameEU);
	}
/////////////////////////////////////////////////////////////////////////////////////////
// 	VIEW OBJECT BINDING
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void bindViewTo(final PB01UIViewOrganization obj) {
		// Set the view object
		_viewObj = obj;

		// bind view components' fields to view object's fields
		// using @VaadinViewField annotation
		VaadinViews.using(_binder,_i18n)
				   .bindComponentsOf(this)
				   .toViewObjectOfType(PB01UIViewOrganization.class);

		// read the bean
		_binder.readBean(obj);
	}
	@Override
	public boolean writeBeanIfValid(final PB01UIViewOrganization viewObj) {
		return _binder.writeBeanIfValid(viewObj);
	}
	@Override
	public PB01UIViewOrganization getViewObject() {
		return _viewObj;
	}
	@Override
	public void setViewObject(final PB01UIViewOrganization viewObject) {
		_viewObj = viewObject;
	}
}