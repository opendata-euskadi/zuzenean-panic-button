package pb01a.model.org.summaries;

import java.util.Collection;
import java.util.Iterator;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgDivisionServiceLocationID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgDivisionServiceLocationOID;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.types.contact.ContactPhone;
import r01f.util.types.Strings;
import r01f.util.types.collections.CollectionUtils;

@MarshallType(as="summarizedOrgDivisionServiceLocation")
@Accessors(prefix="_")
public class PB01ASummarizedOrgDivisionServiceLocation 
	 extends PB01ASummarizedOrganizationalModelObjectBase<PB01AOrgDivisionServiceLocationOID,PB01AOrgDivisionServiceLocationID,PB01AOrgDivisionServiceLocation,
	 											 		 PB01ASummarizedOrgDivisionServiceLocation> {

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
	public PB01ASummarizedOrgDivisionServiceLocation() {
		super(PB01AOrgDivisionServiceLocation.class);
	}
	public static PB01ASummarizedOrgDivisionServiceLocation create() {
		return new PB01ASummarizedOrgDivisionServiceLocation();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  FLUENT API
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ASummarizedOrgDivisionServiceLocation country(final String country) {
		_country = country;
		return this;
	}
	public PB01ASummarizedOrgDivisionServiceLocation territory(final String territory) {
		_territory = territory;
		return this;
	}
	public PB01ASummarizedOrgDivisionServiceLocation state(final String state) {
		_state = state;
		return this;
	}
	public PB01ASummarizedOrgDivisionServiceLocation municipality(final String municipality) {
		_municipality = municipality;
		return this;
	}
	public PB01ASummarizedOrgDivisionServiceLocation steet(final String street) {
		_street = street;
		return this;
	}
	public PB01ASummarizedOrgDivisionServiceLocation phones(Collection<ContactPhone> phones) {
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
