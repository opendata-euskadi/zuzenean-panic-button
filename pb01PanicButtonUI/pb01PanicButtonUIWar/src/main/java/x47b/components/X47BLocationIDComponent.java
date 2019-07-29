package x47b.components;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import lombok.Getter;
import lombok.Setter;

/**
 * Componente para introducir el ID de una Localización
 */
public class X47BLocationIDComponent extends X47BOrganizationIDComponent {
					
	/**
	 * 
	 */
	private static final long serialVersionUID = 3048492242471484175L;	
	@Getter @Setter TextField locationId = new TextField();

	public X47BLocationIDComponent(HorizontalLayout l) {
		super(l);
		l.addComponent(new Label("  "));
		locationId.setStyleName("x47bentityID");
		locationId.setMaxLength(10);
		locationId.setWidth(100, Unit.PIXELS);
		locationId.setRequired(true);
		locationId.setData("ID");
		l.addComponent(locationId);
		
	}
	
	

}
