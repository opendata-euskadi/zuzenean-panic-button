/**
 *
 */
package pb01.ui.vaadin.view;


import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01.ui.vaadin.serverpush.PB01ServerPushedMessageForAlarmRaised;
import r01f.ui.i18n.UII18NService;
import r01f.ui.vaadin.serverpush.VaadinServerPushedMessagesBroadcastListener;
import r01f.ui.vaadin.view.VaadinViewI18NMessagesCanBeUpdated;
import r01f.ui.vaadin.view.components.layout.VaadinViewDisplay;

@Accessors(prefix="_")
public class PB01ViewContainer
	 extends Panel
  implements VaadinViewI18NMessagesCanBeUpdated {

	private static final long serialVersionUID = -3921436524914595338L;
/////////////////////////////////////////////////////////////////////////////////////////
// 	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final UII18NService _i18n;
	/**
	 * The main layout container. The application views will placed here.
	 * (this display is handed to the {@link Navigator} at the UI type)
	 */
	@Getter protected final VaadinViewDisplay _viewDisplay;
/////////////////////////////////////////////////////////////////////////////////////////
// 	CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ViewContainer(final UII18NService i18n) {
		_i18n = i18n;
		super.setContent( new VerticalLayout() );

		super.setStyleName(ValoTheme.PANEL_BORDERLESS);
		this.getContent().setMargin(false);
		this.getContent().setSizeFull();

		_viewDisplay = new VaadinViewDisplay(new Panel());

		this.getContent().setSizeFull();
		this.getContent().addComponent(_viewDisplay.getContainer());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public VerticalLayout getContent() {
		return (VerticalLayout)super.getContent();
	}
	/**
	 * This method is called from the receiveBroadcast() method of the
	 * {@link VaadinServerPushedMessagesBroadcastListener} (the
	 * @param message
	 */
	public void showEventRaisedMessage(final PB01ServerPushedMessageForAlarmRaised message) {
		this.getContent().addComponent(new Label(_i18n.getMessage("pb01.alarm.new") + " > " +  message.asText()));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void updateI18NMessages(final UII18NService i18n) {
		// TODO update messages
	}
}
