package pb01.ui.vaadin.orgentity;

import com.google.common.eventbus.EventBus;

import lombok.extern.slf4j.Slf4j;
import pb01.ui.vaadin.view.events.PB01OrgObjectChangedEvent;
import r01f.patterns.FactoryFrom;
import r01f.ui.presenter.UIObjectDetailPresenter;
import r01f.ui.presenter.UIPresenterSubscriber;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;
import x47b.model.org.X47BOrganizationalPersistableObject;

@Slf4j
public abstract class PB01PresenterForOrgObjectDetailBase<O extends X47BOrgObjectOID,M extends X47BOrganizationalPersistableObject<O,?>,
											  			  V extends PB01ViewObjForOrganizationalEntityBase<O,?,M>,
											  			  C extends PB01COREMediatorForOrganizationalEntityBase<O,M>>
  		   implements UIObjectDetailPresenter<O,V> {

	private static final long serialVersionUID = 7844171401265835661L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final transient EventBus _eventBus;
	private final transient C _coreMediator;
	private final transient FactoryFrom<M,V> _viewObjFromModelObj;

/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01PresenterForOrgObjectDetailBase(final EventBus eventBus,
											   final C mediator,
											   final FactoryFrom<M,V> viewObjFromModelObj) {
		_eventBus = eventBus;
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
										final V viewObj = _viewObjFromModelObj.from(obj);

										// [3] - Tell the view to paint the object
										presenterSubscriber.onSuccess(viewObj);
								   });
	}
	@Override
	public void onSaveRequested(final V viewObj,
								final UIPresenterSubscriber<V> presenterSubscriber) {
		// [0] - Get the model object from the view object
		final M obj = viewObj.getWrappedModelObject();

		// [1] - Use the api to save the object
		_coreMediator.save(obj,
						   result -> {
										// [1] - Raise an event to inform other presenters
							   			// 		 that an object has been changed
							   			// 		 (see PB01PresenterForRaisedAlarmsListView
							   			//		  which has a cache of the org object types)
										X47BOrgObjectOID changedOrgObjOid = result.getOid();
										_eventBus.post(new PB01OrgObjectChangedEvent(changedOrgObjOid));

							   			// [2] . Convert the model object into a view obj
										final V savedViewObj = _viewObjFromModelObj.from(result);

										// [3] - Tell the view to paint the object
										presenterSubscriber.onSuccess(savedViewObj);
									 });
	}
	@Override
	public void onDeleteRequested(final O oid,
								  final UIPresenterSubscriber<V> presenterSubscriber) {
		// [1] - Use the api to delete the object
		_coreMediator.delete(oid,
							 obj -> {
										// [2] . Convert the model objet into a view obj
										final V viewObj = _viewObjFromModelObj.from(obj);

										// [3] - Tell the view...
										presenterSubscriber.onSuccess(viewObj);
									});
	}
}
