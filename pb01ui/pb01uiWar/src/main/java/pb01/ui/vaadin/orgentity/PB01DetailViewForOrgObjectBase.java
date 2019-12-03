package pb01.ui.vaadin.orgentity;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import lombok.RequiredArgsConstructor;
import pb01.ui.vaadin.orgentity.organization.PB01ViewObjForOrganization;
import pb01.ui.vaadin.orgentity.orgdivision.PB01ViewObjForOrgDivision;
import pb01.ui.vaadin.orgentity.orgdivisionservice.PB01ViewObjForOrgDivisionService;
import pb01.ui.vaadin.orgentity.orgdivisionservicelocation.PB01ViewObjForOrgDivisionServiceLocation;
import pb01.ui.vaadin.orgentity.workplace.PB01ViewObjForWorkPlace;
import r01f.types.dirtytrack.DirtyTrackAdapter;
import r01f.ui.i18n.UII18NService;
import r01f.ui.vaadin.annotations.VaadinViewComponentLabels;
import r01f.ui.vaadin.annotations.VaadinViewField;
import r01f.ui.vaadin.view.VaadinDetailView;
import r01f.ui.vaadin.view.VaadinViewHasVaadinViewObjectBinder;
import r01f.ui.vaadin.view.VaadinViews;
import r01f.util.types.Strings;
import x47b.model.X47BObjectsValidators;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

public class PB01DetailViewForOrgObjectBase<O extends X47BOrgObjectOID,M extends X47BOrganizationalPersistableObject<O,? extends X47BPersistableObjectID<?>>,
											V extends PB01ViewObjForOrganizationalEntityBase<O,? extends X47BPersistableObjectID<?>,M>>
	 extends VerticalLayout
  implements VaadinDetailView<V>,
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
	@VaadinViewField(bindToViewObjectFieldNamed=PB01ViewObjForOrganizationalEntityBase.OID_FIELD)
	@VaadinViewComponentLabels(captionI18NKey="pb01.org.field.oid",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtOid = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed=PB01ViewObjForOrganizationalEntityBase.ID_FIELD,
					 required=true)
	@VaadinViewComponentLabels(captionI18NKey="pb01.org.field.id",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtId = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed=PB01ViewObjForOrganizationalEntityBase.NAME_ES_FIELD,
					 required=true)
	@VaadinViewComponentLabels(captionI18NKey="pb01.org.field.name.es",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtNameES = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed=PB01ViewObjForOrganizationalEntityBase.NAME_EU_FIELD,
					 required=true)
	@VaadinViewComponentLabels(captionI18NKey="pb01.org.field.name.eu",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtNameEU = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed=PB01ViewObjForOrganizationalEntityBase.PHONES_FIELD,
					 useValidatorType=PB01PhonesValidator.class)
	@VaadinViewComponentLabels(captionI18NKey="pb01.org.field.phones",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtPhones = new TextField();

	@VaadinViewField(bindToViewObjectFieldNamed=PB01ViewObjForOrganizationalEntityBase.EMAIL_FIELD,
					 useValidatorType=PB01EMailsValidator.class)
	@VaadinViewComponentLabels(captionI18NKey="pb01.org.field.emails",useCaptionI18NKeyAsPlaceHolderKey=true)
	private final TextField _txtEmail = new TextField();


/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected PB01DetailViewForOrgObjectBase(final UII18NService i18n,
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

		_txtPhones.setWidth(100,Unit.PERCENTAGE);
		_txtPhones.setRequiredIndicatorVisible(true);

		_txtEmail.setWidth(100,Unit.PERCENTAGE);
		_txtEmail.setRequiredIndicatorVisible(true);

		// Layout
		this.addComponent(_txtOid);
		this.addComponent(_txtId);
		this.addComponent(_txtNameES);
		this.addComponent(_txtNameEU);
		this.addComponent(_txtPhones);
		this.addComponent(_txtEmail);
	}
/////////////////////////////////////////////////////////////////////////////////////////
// 	VIEW OBJECT BINDING
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void bindViewTo(final V viewObj) {
		// Set the view object
		_viewObj = viewObj;

		// set the caption
		this.setCaption(_captionFor(viewObj));

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
/////////////////////////////////////////////////////////////////////////////////////////
//	CAPTION
/////////////////////////////////////////////////////////////////////////////////////////
	private String _captionFor(final V viewObj) {
		String i18nKey = null;
		if (viewObj instanceof PB01ViewObjForOrganization) {
			i18nKey = "pb01.org";
		} else if (viewObj instanceof PB01ViewObjForOrgDivision) {
			i18nKey = "pb01.org.division";
		} else if (viewObj instanceof PB01ViewObjForOrgDivisionService) {
			i18nKey = "pb01.org.service";
		} else if (viewObj instanceof PB01ViewObjForOrgDivisionServiceLocation) {
			i18nKey = "pb01.org.location";
		} else if (viewObj instanceof PB01ViewObjForWorkPlace) {
			i18nKey = "pb01.org.workPlace";
		} else {
			throw new IllegalArgumentException(viewObj.getClass() + " is NOT a supported org view object type!!");
		}
		boolean isNew = DirtyTrackAdapter.adapt(viewObj.getWrappedModelObject())
							 			 .getTrackingStatus().isThisNew();
		return _i18n.getMessage(i18nKey) + (isNew ? " (" + _i18n.getMessage("pb01.new") + ")"
												  : "");
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	VALIDATORS
/////////////////////////////////////////////////////////////////////////////////////////
	@RequiredArgsConstructor
	private static class PB01PhonesValidator
	   	      implements Validator<String> {

		private static final long serialVersionUID = -6088142603048630416L;

		private final UII18NService _i18n;

		@Override
		public ValidationResult apply(final String value,
									  final ValueContext context) {
			if (Strings.isNullOrEmpty(value)) return ValidationResult.ok();

			// beware!! this is called for every keystroke on the text field
			return X47BObjectsValidators.isValidPhones(value) ? ValidationResult.ok()
															  : ValidationResult.error(_i18n.getMessage("pb01.org.field.validation.phones"));
		}
	}
	@RequiredArgsConstructor
	private static class PB01EMailsValidator
	   	 	  implements Validator<String> {

		private static final long serialVersionUID = -6088142603048630416L;

		private final UII18NService _i18n;

		@Override
		public ValidationResult apply(final String value,
									  final ValueContext context) {
			if (Strings.isNullOrEmpty(value)) return ValidationResult.ok();

			// beware!! this is called for every keystroke on the text field
			return X47BObjectsValidators.isValidEmails(value) ? ValidationResult.ok()
															  : ValidationResult.error(_i18n.getMessage("pb01.org.field.validation.emails"));
		}
	}
	@Override
	public void readBean(V obj) {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}
}