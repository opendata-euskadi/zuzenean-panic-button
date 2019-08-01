package pb01.ui.vaadin.view;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import r01f.model.search.SearchResults;
import r01f.ui.coremediator.UICOREMediatorBase;
import r01f.ui.coremediator.UICOREMediatorSubscriber;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.search.X47BSearchFilterForPanicButtonOrganizationalEntity;
import x47b.model.search.X47BSearchResultItemForPanicButtonOrganizationalEntity;

@Slf4j
@Singleton
public class PB01MainViewCOREMediator
     extends UICOREMediatorBase<X47BPanicButtonClientAPI> {

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01MainViewCOREMediator(final X47BPanicButtonClientAPI api) {
		super(api);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	FILTER
/////////////////////////////////////////////////////////////////////////////////////////
	public void search(final X47BSearchFilterForPanicButtonOrganizationalEntity filter,
					   final int firstItemNum,final int numberOfItems,
					   final UICOREMediatorSubscriber<SearchResults<X47BSearchFilterForPanicButtonOrganizationalEntity,
												  				    X47BSearchResultItemForPanicButtonOrganizationalEntity>> subscriber) {
		final SearchResults<X47BSearchFilterForPanicButtonOrganizationalEntity,
						 X47BSearchResultItemForPanicButtonOrganizationalEntity> outResults = _api.entitySearchAPI()
																										.search(filter)
																										.fromItemAt(firstItemNum)
																										.returning(numberOfItems);
		subscriber.onSuccess(outResults);
	}
}
