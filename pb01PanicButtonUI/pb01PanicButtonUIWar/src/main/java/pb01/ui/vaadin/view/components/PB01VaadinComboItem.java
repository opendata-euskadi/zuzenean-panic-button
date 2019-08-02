package pb01.ui.vaadin.view.components;

import pb01.ui.vaadin.orgentity.PB01ViewObjForOrganizationalEntityBase;
import r01f.locale.Language;
import r01f.patterns.FactoryFrom;
import r01f.ui.vaadin.view.VaadinViewMultiValueItem;
import r01f.util.types.Strings;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrgObjectRef;
import x47b.model.org.X47BOrganizationalPersistableObject;
import x47b.model.org.summaries.X47BSummarizedOrganizationalObject;

public class PB01VaadinComboItem
	 extends VaadinViewMultiValueItem {

	private static final long serialVersionUID = -1456361360366480379L;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01VaadinComboItem(final String id,final String name) {
		super(id,name);
	}
	public PB01VaadinComboItem(final X47BPersistableObjectOID oid,final X47BPersistableObjectID<?> id,
				   			   final String name) {
		this(Strings.customized("{}#{}",
   							    oid,id),
			 name);
	}
	public PB01VaadinComboItem(final X47BOrgObjectRef<?,?> objRef,
							   final String name) {
		this(objRef.getOid(),objRef.getId(),
			 name);
	}
	public PB01VaadinComboItem(final X47BOrganizationalPersistableObject<?,?> obj,
							   final Language lang) {
		this(obj.getOid(),obj.getId(),
			 obj.getName().getIn(lang).or("no name in " + lang));
	}
	public PB01VaadinComboItem(final X47BSummarizedOrganizationalObject<?,?,?> objSum) {
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
	public <O extends X47BPersistableObjectOID> O getOidUsing(final FactoryFrom<String,O> factory) {
		return factory.from(_id.split("#")[0]);
	}
	public <I extends X47BPersistableObjectID<?>> I getIdUsing(final FactoryFrom<String,I> factory) {
		return factory.from(_id.split("#")[1]);
	}
	public <O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>> X47BOrgObjectRef<O,I> getOrgEntityRefUsing(final FactoryFrom<String,O> oidFactory,
																																	 	   final FactoryFrom<String,I> idFactory) {
		O oid = this.getOidUsing(oidFactory);
		I id = this.getIdUsing(idFactory);
		return new X47BOrgObjectRef<O,I>(oid,id);
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
	    PB01VaadinComboItem apply(final X47BOrganizationalPersistableObject<?,?> obj,
	    						  final Language lang);
	}
	@FunctionalInterface
	public interface PB01VaadinComboItemFromObjRefFactory {
	    PB01VaadinComboItem apply(final X47BOrgObjectRef<?,?> objRef,
							   	  final String name);
	}
	@FunctionalInterface
	public interface PB01VaadinComboItemFromObjSummaryFactory {
	    PB01VaadinComboItem apply(final X47BSummarizedOrganizationalObject<?,?,?> objSum);
	}
	@FunctionalInterface
	public interface PB01VaadinComboItemFromViewObjFactory {
	    PB01VaadinComboItem apply(final PB01ViewObjForOrganizationalEntityBase<?,?,?> viewObj,
	    						  final Language lang);
	}
}
