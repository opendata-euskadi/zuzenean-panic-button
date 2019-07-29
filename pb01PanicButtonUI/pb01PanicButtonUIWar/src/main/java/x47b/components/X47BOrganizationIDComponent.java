package x47b.components;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import lombok.Getter;
import lombok.Setter;

/**
 * Componente para introducir el ID de una organización
 */
public class X47BOrganizationIDComponent extends CustomComponent {
	
	private static final long serialVersionUID = -592557090414007776L;
	
	@Getter HorizontalLayout idLayout;		
	@Getter @Setter TextField organizationId = new TextField();
	
		
	public X47BOrganizationIDComponent(HorizontalLayout l) {
		super();		
		idLayout = l;
		idLayout.setSpacing(true);
		setCaption("Id");
		organizationId.setStyleName("x47bentityID");
		organizationId.setMaxLength(10);
		organizationId.setWidth(100, Unit.PIXELS);
		organizationId.setRequired(true);
		organizationId.setData("ID");
		idLayout.addComponent(organizationId);
		
		//organizationId.addTextChangeListener(validIDChangeListener);
		organizationId.setImmediate(true);					
	}	
}
