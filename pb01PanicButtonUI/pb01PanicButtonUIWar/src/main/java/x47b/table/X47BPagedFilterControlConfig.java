package x47b.table;

import org.tepi.filtertable.paged.PagedFilterControlConfig;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickListener;

import lombok.Getter;
import lombok.Setter;

public class X47BPagedFilterControlConfig 
	 extends PagedFilterControlConfig {	
	@Getter @Setter ValueChangeListener itemsPerPageChangeListener;
	@Getter @Setter ValueChangeListener currentPageTextFieldListener;
	@Getter @Setter ClickListener prevBtnClickListener;
	@Getter @Setter ClickListener nextBtnClickListener;
	@Getter @Setter ClickListener firstBtnClickListener;
	@Getter @Setter ClickListener lastBtnClickListener;
	@Getter @Setter int initialItemsPerPage=10;
	
	
}
