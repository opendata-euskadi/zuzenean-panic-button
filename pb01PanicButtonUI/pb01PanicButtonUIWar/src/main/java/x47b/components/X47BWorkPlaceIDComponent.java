package x47b.components;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Componente para introducir el ID de un puesto de trabajo
 */
@Accessors(prefix="_")
public class X47BWorkPlaceIDComponent 
    extends X47BLocationIDComponent {
						
	private static final long serialVersionUID = -30541183662439832L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter @Setter private TextField _workPlaceId = new TextField();
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BWorkPlaceIDComponent(final HorizontalLayout l) {
		super(l);
		l.addComponent(new Label("  "));
		_workPlaceId.setStyleName("x47bentityID");
		_workPlaceId.setMaxLength(10);
		_workPlaceId.setWidth(100, Unit.PIXELS);
		_workPlaceId.setRequired(true);
		_workPlaceId.setRequired(true);
		l.addComponent(_workPlaceId);
		
	}
	
	

}
