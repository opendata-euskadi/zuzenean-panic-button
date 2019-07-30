package pb01.ui.vaadin.view;

import javax.inject.Inject;

import com.vaadin.navigator.View;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;

import lombok.extern.slf4j.Slf4j;
import r01f.ui.i18n.UII18NService;
import r01f.ui.vaadin.view.VaadinViewMultiValueItem;

@Slf4j
public class PB01MainView
 	 extends VerticalLayout
  implements View {

	private static final long serialVersionUID = 4570960508688725887L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final transient UII18NService _i18n;
	private final transient PB01MainViewPresenter _presenter;

	private final ComboBox<VaadinViewMultiValueItem> _orgCmb;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01MainView(final UII18NService i18n,
						final PB01MainViewPresenter presenter) {
		_i18n = i18n;
		_presenter = presenter;

		_orgCmb = new ComboBox<>(_i18n.getMessage("pb01.view.main.combo.orgs"));
		_orgCmb.setItemCaptionGenerator(VaadinViewMultiValueItem::getValue);
	}
	public void refreshList() {
		log.info("...refresh list");
		_presenter.onOrgsComboDataNeeded(_i18n.getCurrentLanguage(),
										 orgs -> _orgCmb.setItems(orgs));
	}
}
