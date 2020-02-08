package pb01.ui.vaadin.view.components;

import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import pb01a.model.oids.PB01AIDs.PB01APersistableObjectID;
import pb01a.model.oids.PB01AOIDs.PB01APersistableObjectOID;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrgObjectRef;
import pb01a.model.org.PB01AOrganizationalPersistableObject;
import pb01a.model.org.summaries.PB01ASummarizedOrganizationalObject;
import r01f.locale.Language;
import r01f.patterns.FactoryFrom;
import r01f.ui.vaadin.view.VaadinViewMultiValueItem;
import r01f.util.types.Strings;

public class PB01VaadinComboItem
	 extends VaadinViewMultiValueItem {

	private static final long serialVersionUID = -1456361360366480379L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01VaadinComboItem(final String id,final String name) {
		super(id,name);
	}
	public PB01VaadinComboItem(final PB01APersistableObjectOID oid,final PB01APersistableObjectID<?> id,
				   			   final String name) {
		this(Strings.customized("{}#{}",
   							    oid,id),
			 name);
	}
	public PB01VaadinComboItem(final PB01AOrgObjectRef<?,?> objRef,
							   final String name) {
		this(objRef.getOid(),objRef.getId(),
			 name);
	}
	public PB01VaadinComboItem(final PB01AOrganizationalPersistableObject<?,?> obj,
							   final Language lang) {
		this(obj.getOid(),obj.getId(),
			 obj.getName().getIn(lang).or("no name in " + lang));
	}
	public PB01VaadinComboItem(final PB01ASummarizedOrganizationalObject<?,?,?> objSum) {
		this(objSum.getOid(),objSum.getId(),
			 objSum.getName());
	}
	public PB01VaadinComboItem(final PB01ViewObjForOrganizationalEntityBase<?,?,?> viewObj,
							   final Language lang) {
		this(viewObj.getOid(),viewObj.getId(),
			 lang.is(Language.SPANISH) ? viewObj.getNameES()
					 				   : viewObj.getNameEU());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public <O extends PB01AOrgObjectOID> O getOidUsing(final FactoryFrom<String,O> factory) {
		return factory.from(_id.split("#")[0]);
	}
	public <I extends PB01APersistableObjectID<?>> I getIdUsing(final FactoryFrom<String,I> factory) {
		return factory.from(_id.split("#")[1]);
	}
	public <O extends PB01AOrgObjectOID,I extends PB01AOrgObjectID<O>> PB01AOrgObjectRef<O,I> getOrgObjectRefUsing(final FactoryFrom<String,O> oidFactory,
																												final FactoryFrom<String,I> idFactory) {
		O oid = this.getOidUsing(oidFactory);
		I id = this.getIdUsing(idFactory);
		return new PB01AOrgObjectRef<O,I>(oid,id);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	TRANSFORMS AN ORGANIZATIONAL ENTITY SUMMARY INTO A Vaadin COMBO ITEM
/////////////////////////////////////////////////////////////////////////////////////////
    // The combo item is an object like id:value where:
    //		- id is composed as oid#id (so both the object oid and id are available)
    //		- value is the object name

	public static PB01VaadinComboItemFromObjFactory FROM_OBJ = PB01VaadinComboItem::new;
	public static PB01VaadinComboItemFromObjRefFactory FROM_OBJ_REF = PB01VaadinComboItem::new;
	public static PB01VaadinComboItemFromObjSummaryFactory FROM_OBJ_SUMMARY = PB01VaadinComboItem::new;
	public static PB01VaadinComboItemFromViewObjFactory FROM_VIEW_OBJ = PB01VaadinComboItem::new;

	@FunctionalInterface
	public interface PB01VaadinComboItemFromObjFactory {
	    PB01VaadinComboItem apply(final PB01AOrganizationalPersistableObject<?,?> obj,
	    						  final Language lang);
	}
	@FunctionalInterface
	public interface PB01VaadinComboItemFromObjRefFactory {
	    PB01VaadinComboItem apply(final PB01AOrgObjectRef<?,?> objRef,
							   	  final String name);
	}
	@FunctionalInterface
	public interface PB01VaadinComboItemFromObjSummaryFactory {
	    PB01VaadinComboItem apply(final PB01ASummarizedOrganizationalObject<?,?,?> objSum);
	}
	@FunctionalInterface
	public interface PB01VaadinComboItemFromViewObjFactory {
	    PB01VaadinComboItem apply(final PB01ViewObjForOrganizationalEntityBase<?,?,?> viewObj,
	    						  final Language lang);
	}
}
