package pb01.ui.vaadin.view;

import java.io.Serializable;

import com.vaadin.ui.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import pb01a.model.org.PB01AOrgObjectRef;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
abstract class PB01CascadedComboEvents {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static class PB01ComboValueChangedEvent<O extends PB01AOrgObjectOID,I extends PB01AOrgObjectID<O>>
				extends Component.Event {
		private static final long serialVersionUID = 6771268655053782852L;

		@Getter private final PB01AOrgObjectRef<O,I> _from;
		@Getter private final PB01AOrgObjectRef<O,I> _to;

		public PB01ComboValueChangedEvent(final Component source,
								  		  final PB01AOrgObjectRef<O,I> from,final PB01AOrgObjectRef<O,I> to) {
			super(source);
			_from = from;
			_to = to;
		}
	}
	@FunctionalInterface
	public interface PB01ComboValueChangedEventListener<O extends PB01AOrgObjectOID,I extends PB01AOrgObjectID<O>>
	         extends Serializable {
		void valueChanged(PB01ComboValueChangedEvent<O,I> event);
	}
}
