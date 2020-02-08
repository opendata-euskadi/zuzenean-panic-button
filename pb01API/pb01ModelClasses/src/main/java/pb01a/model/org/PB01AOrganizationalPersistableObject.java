package pb01a.model.org;

import java.util.Collection;
import java.util.Date;

import pb01a.model.PB01APersistableObject;
import pb01a.model.oids.PB01AOrganizationalIDs.PB01AOrgObjectID;
import pb01a.model.oids.PB01AOrganizationalOIDs.PB01AOrgObjectOID;
import r01f.facets.LangDependentNamed.HasLangDependentNamedFacet;
import r01f.locale.Language;
import r01f.model.SummarizedModelObject;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;

/**
 * Interface for every PB01 organizational entity: {@link PB01AOrganization}, {@link PB01AOrgDivision}, {@link PB01AOrgDivisionService}
 * @param <O>
 */
public interface PB01AOrganizationalPersistableObject<O extends PB01AOrgObjectOID,ID extends PB01AOrgObjectID<O>>
		 extends PB01APersistableObject<O,ID>,
				 HasLangDependentNamedFacet {
	/**
	 * Returns a summary in a given language
	 * @param lang
	 * @return
	 */
	public <T extends PB01AOrganizationalPersistableObject<O,ID>> SummarizedModelObject<T> getSummarizedIn(Language lang);

	public Date getLastAlarmRaiseDate();
	public void setLastAlarmRaiseDate(Date date);

	public long getAlarmRaiseCount();
	public void setAlarmRaiseCount(long count);

	public Collection<Phone> getPhones();
	public void setPhones(Collection<Phone> phones);
	public String getPhonesAsString();
	public void setPhonesFromString(String phones);

	public Collection<EMail> getEmails();
	public void setEmails(Collection<EMail> emails);
	public String getEmailsAsString();
	public void setEmailsFromString(String emails);
}
