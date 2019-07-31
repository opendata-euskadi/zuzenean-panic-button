package pb01.ui.vaadin.orgentity;

import lombok.extern.slf4j.Slf4j;
import r01f.patterns.FactoryFrom;
import r01f.ui.presenter.UIObjectDetailPresenter;
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

@Slf4j
public abstract class PB01DetailPresenterForOrgEntityBase<O extends X47BPersistableObjectOID,M extends X47BOrganizationalPersistableObject<O,?>,
											  			  V extends PB01ViewObjForOrganizationalEntityBase<O,?,M>,
											  			  C extends PB01COREMediatorForOrganizationalEntityBase<O,M>>
  		   implements UIObjectDetailPresenter<O,M,
  		   									  V> {

	private static final long serialVersionUID = 7844171401265835661L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final transient C _coreMediator;
	private final transient FactoryFrom<M,V> _viewObjFromModelObj;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01DetailPresenterForOrgEntityBase(final C mediator,
											   final FactoryFrom<M,V> viewObjFromModelObj) {
		_coreMediator = mediator;
		_viewObjFromModelObj = viewObjFromModelObj;
	}
/////////////////////////////////////////////////////////////////////////////////////////
// PUBLIC METHODS
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void onLoadRequested(final O oid,
								final UIPresenterSubscriber<V> presenterSubscriber) {
		// [1] - Use the api to load the object
		//		 ... or create a new one if oid == null)
		_coreMediator.load(oid,
						   obj -> {
										// [2] . Convert the model object into a view obj
										V viewObj = _viewObjFromModelObj.from(obj);

										// [3] - Tell the view to paint the object
										presenterSubscriber.onSuccess(viewObj);
								   });
	}
	@Override
	public void onSaveRequested(final M obj,
								final UIPresenterSubscriber<V> presenterSubscriber) {
		// [1] - Use the api to save the object
		_coreMediator.save(obj,
						   result -> {
										// [2] . Convert the model object into a view obj
										V viewObj = _viewObjFromModelObj.from(result);

										// [3] - Tell the view to paint the object
										presenterSubscriber.onSuccess(viewObj);
									 });
	}
	@Override
	public void onDeleteRequested(final O oid,
								  final UIPresenterSubscriber<V> presenterSubscriber) {
		// [1] - Use the api to delete the object
		_coreMediator.delete(oid,
							 obj -> {
										// [2] . Convert the model objet into a view obj
										V viewObj = _viewObjFromModelObj.from(obj);

										// [3] - Tell the view...
										presenterSubscriber.onSuccess(viewObj);
									});
	}
}
