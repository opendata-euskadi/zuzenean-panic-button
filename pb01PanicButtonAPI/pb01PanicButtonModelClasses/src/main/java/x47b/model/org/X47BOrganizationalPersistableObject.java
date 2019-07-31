package x47b.model.org;

import java.util.Collection;
import java.util.Date;

import r01f.facets.LangDependentNamed.HasLangDependentNamedFacet;
import r01f.locale.Language;
import r01f.model.SummarizedModelObject;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import x47b.model.X47BPersistableObject;
import x47b.model.oids.X47BIDs.X47BPersistableObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

/**
 * Interface for every X47B organizational entity: {@link X47BOrganization}, {@link X47BOrgDivision}, {@link X47BOrgDivisionService}
 * @param <O>
 */
public interface X47BOrganizationalPersistableObject<O extends X47BPersistableObjectOID,ID extends X47BPersistableObjectID<O>> 
		 extends X47BPersistableObject<O,ID>,
				 HasLangDependentNamedFacet {
	/**
	 * Returns a summary in a given language
	 * @param lang
	 * @return
	 */
	public <T extends X47BOrganizationalPersistableObject<O,ID>> SummarizedModelObject<T> getSummarizedIn(Language lang);
	
	public Date getLastAlarmRaiseDate();
	public void setLastAlarmRaiseDate(Date date);
	
	public long getAlarmRaiseCount();
	public void setAlarmRaiseCount(long count);
	
	public Collection<Phone> getPhones();
	public void setPhones(Collection<Phone> phones);
	
	public Collection<EMail> getEmails();
	public void setEmails(Collection<EMail> emails);
}
