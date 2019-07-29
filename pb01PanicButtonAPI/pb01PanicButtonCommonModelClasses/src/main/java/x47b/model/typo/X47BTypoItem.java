package x47b.model.typo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.debug.Debuggable;
import r01f.locale.Language;
import r01f.locale.LanguageTexts;
import r01f.locale.LanguageTexts.LangTextNotFoundBehabior;
import r01f.locale.LanguageTextsMapBacked;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.util.types.Strings;
import x47b.model.oids.X47BTypoIDs.X47BTypoID;

@Accessors(prefix="_")
public abstract class X47BTypoItem<ID extends X47BTypoID,
								   SELF_TYPE extends X47BTypoItem<ID,SELF_TYPE>> 
           implements Debuggable,
           			  Comparable<SELF_TYPE>,
           			  Serializable {
	private static final long serialVersionUID = -573014791381997984L;
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="type",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private ID _id;
	
	@MarshallField(as="quickCode",
			   	   whenXml=@MarshallFieldAsXml(attr=true))
	@Getter @Setter private String _quickCode;
	
	@MarshallField(as="typeName")
	@Getter @Setter private LanguageTexts _name;	
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	protected X47BTypoItem() {
		// nothing
	}
	protected X47BTypoItem(final ID id) {
		_id = id;
	}
	protected X47BTypoItem(final ID id,final String quickCode) {
		_id = id;
		_quickCode = quickCode;
	}
	protected X47BTypoItem(final ID id,final String quickCode,
						   final LanguageTexts name) {
		_id = id;
		_quickCode = quickCode;
		_name = name;
	}
	protected X47BTypoItem(final ID id,final String quickCode,
						   final String nameES,final String nameEU) {
		_id = id;
		_quickCode = quickCode;
		if (nameES != null || nameEU != null) {
			_name = new LanguageTextsMapBacked(LangTextNotFoundBehabior.RETURN_NULL)
								.add(Language.SPANISH,nameES)
								.add(Language.BASQUE,nameEU);
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int compareTo(final SELF_TYPE other) {
		int outComp = 0;
		
		if (other == null) return 1;
		
		if (_quickCode == null && other.getQuickCode() == null) return 0;
		if (_quickCode == null && other.getQuickCode() != null) return -1;
		if (_quickCode != null && other.getQuickCode() == null) return 1;
		
		outComp = _quickCode.compareTo(other.getQuickCode());
		
		return outComp;
	}
	public int compareUsingNameTo(final SELF_TYPE other,
								  final Language lang) {
		int outComp = 0;
		
		if (other == null) return 1;
		
		if ((_name == null || _name.get(lang) == null) && (other.getName() == null || other.getName().get(lang) == null)) return 0;
		if ((_name == null || _name.get(lang) == null) && (other.getName() != null && other.getName().get(lang) != null)) return -1;
		if ((_name != null && _name.get(lang) != null) && (other.getName() == null || other.getName().get(lang) == null)) return 1;
		
		outComp = _name.get(lang).compareTo(other.getName().get(lang));
		
		return outComp;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public CharSequence debugInfo() {
		return Strings.customized("{}: {} ({})",
								  _id,_name.get(Language.DEFAULT),_quickCode);
	}

}
