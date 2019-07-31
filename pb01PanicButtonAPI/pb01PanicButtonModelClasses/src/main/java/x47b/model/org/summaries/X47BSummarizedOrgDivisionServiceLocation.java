package x47b.model.org.summaries;

import java.util.Collection;
import java.util.Iterator;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.contact.ContactPhone;
import r01f.util.types.Strings;
import r01f.util.types.collections.CollectionUtils;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgDivisionServiceLocationID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgDivisionServiceLocationOID;
import x47b.model.org.X47BOrgDivisionServiceLocation;

@MarshallType(as="summarizedOrgDivisionServiceLocation")
@Accessors(prefix="_")
public class X47BSummarizedOrgDivisionServiceLocation 
	 extends X47BSummarizedOrganizationalModelObjectBase<X47BOrgDivisionServiceLocationOID,X47BOrgDivisionServiceLocationID,X47BOrgDivisionServiceLocation,
	 											 		 X47BSummarizedOrgDivisionServiceLocation> {

	private static final long serialVersionUID = -4373243410730886004L;
/////////////////////////////////////////////////////////////////////////////////////////
//  SERIALIZABLE FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="country",escape=true)
	@Getter @Setter private String _country;
	
	@MarshallField(as="territory",escape=true)
	@Getter @Setter private String _territory;
	
	@MarshallField(as="state",escape=true)
	@Getter @Setter private String _state;
	
	@MarshallField(as="municipality",escape=true)
	@Getter @Setter private String _municipality;

	@MarshallField(as="street",escape=true)
	@Getter @Setter private String _street;
	
	@MarshallField(as="phone",escape=true)
	@Getter @Setter private String _phone;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR & BUILDER
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSummarizedOrgDivisionServiceLocation() {
		super(X47BOrgDivisionServiceLocation.class);
	}
	public static X47BSummarizedOrgDivisionServiceLocation create() {
		return new X47BSummarizedOrgDivisionServiceLocation();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FLUENT API
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSummarizedOrgDivisionServiceLocation country(final String country) {
		_country = country;
		return this;
	}
	public X47BSummarizedOrgDivisionServiceLocation territory(final String territory) {
		_territory = territory;
		return this;
	}
	public X47BSummarizedOrgDivisionServiceLocation state(final String state) {
		_state = state;
		return this;
	}
	public X47BSummarizedOrgDivisionServiceLocation municipality(final String municipality) {
		_municipality = municipality;
		return this;
	}
	public X47BSummarizedOrgDivisionServiceLocation steet(final String street) {
		_street = street;
		return this;
	}
	public X47BSummarizedOrgDivisionServiceLocation phones(Collection<ContactPhone> phones) {
		if (CollectionUtils.hasData(phones)) {
			StringBuilder phonesStr = new StringBuilder();
			for (Iterator<ContactPhone> phoneIt = phones.iterator(); phoneIt.hasNext(); ) {
				ContactPhone phone = phoneIt.next();
				if (phone.getNumber() == null) continue;
				phonesStr.append(phone.getNumber().asString());
				if (phoneIt.hasNext()) phonesStr.append(" / ");
			}
			if (Strings.isNOTNullOrEmpty(phonesStr)) _phone = phonesStr.toString();
		}
		return this;
	}
}
