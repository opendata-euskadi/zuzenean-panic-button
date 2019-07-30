package pb01.ui.vaadin.view;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.collect.FluentIterable;

import r01f.locale.Language;
import r01f.ui.presenter.UIPresenter;
import r01f.ui.presenter.UIPresenterSubscriber;
import r01f.ui.vaadin.view.VaadinViewMultiValueItem;

@Singleton
public class PB01MainViewPresenter
  implements UIPresenter {

    private static final long serialVersionUID = -4995313906513090391L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    private final transient PB01MainViewCOREMediator _coreMediator;

/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    public PB01MainViewPresenter(final PB01MainViewCOREMediator coreMediator) {
    	_coreMediator = coreMediator;
    }
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
    public void onOrgsComboDataNeeded(final Language lang,
                                      final UIPresenterSubscriber<Collection<VaadinViewMultiValueItem>> presenterSubscriber) {
    	_coreMediator.loadAllOrgs(lang,
    							  // just transform the Collection<M> into a Collection<VaadinViewMultiValueItem>
    							  orgs -> FluentIterable.from(orgs)
    							  						.transform(org -> new VaadinViewMultiValueItem(org.getOid(),org.getName())));
    }
}
