package pb01.ui.vaadin.view;

import java.io.Serializable;

import com.vaadin.ui.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;
import x47b.model.org.X47BOrgObjectRef;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
abstract class PB01CascadedComboEvents {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static class PB01ComboValueChangedEvent<O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>>
				extends Component.Event {
		private static final long serialVersionUID = 6771268655053782852L;

		@Getter private final X47BOrgObjectRef<O,I> _from;
		@Getter private final X47BOrgObjectRef<O,I> _to;

		public PB01ComboValueChangedEvent(final Component source,
								  		  final X47BOrgObjectRef<O,I> from,final X47BOrgObjectRef<O,I> to) {
			super(source);
			_from = from;
			_to = to;
		}
	}
	@FunctionalInterface
	public interface PB01ComboValueChangedEventListener<O extends X47BPersistableObjectOID,I extends X47BPersistableObjectID<O>>
	         extends Serializable {
		void valueChanged(PB01ComboValueChangedEvent<O,I> event);
	}
}
