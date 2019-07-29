package x47b.model.org;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.aspects.interfaces.dirtytrack.ConvertToDirtyStateTrackable;
import r01f.facets.FullTextSummarizable;
import r01f.facets.LangDependentNamed;
import r01f.facets.Summarizable;
import r01f.facets.builders.SummarizableBuilder;
import r01f.facets.delegates.LangDependentNamedDelegate;
import r01f.locale.LanguageTexts;
import r01f.locale.LanguageTextsWrapper;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallField.DateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallDateFormat;
import r01f.objectstreamer.annotations.MarshallField.MarshallFieldAsXml;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.types.summary.Summary;
import r01f.types.summary.SummaryBuilder;
import r01f.util.types.Strings;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;
import x47b.model.X47BEntityObjectBase;
import x47b.model.oids.X47BIDs.X47BModelObjectID;
import x47b.model.oids.X47BOIDs.X47BPersistableObjectOID;

@ConvertToDirtyStateTrackable
@Accessors(prefix="_")
public abstract class X47BOrganizationalModelObjectBase<O extends X47BPersistableObjectOID,ID extends X47BModelObjectID<O>,
										 			   	SELF_TYPE extends X47BOrganizationalModelObjectBase<O,ID,SELF_TYPE>>
		      extends X47BEntityObjectBase<O,ID,SELF_TYPE>
		   implements X47BOrganizationalModelObject<O,ID>,
		   			  SelfValidates<SELF_TYPE> {

	private static final long serialVersionUID = 7579054159448752329L;

/////////////////////////////////////////////////////////////////////////////////////////
//  COMMON FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@MarshallField(as="name")
	@Getter @Setter protected LanguageTexts _nameByLanguage;
	
	@MarshallField(as="lastAlarmRaiseDate",
				   dateFormat=@MarshallDateFormat(use=DateFormat.CUSTOM,format="dd/MM/yyyy hh:mm:ss"))
	@Getter @Setter private Date _lastAlarmRaiseDate;
	
	@MarshallField(as="alarmRaiseCount")
	@Getter @Setter private long _alarmRaiseCount;

	@MarshallField(as="phones",
				   whenXml=@MarshallFieldAsXml(collectionElementName="phone"))
	@Getter @Setter private Collection<Phone> _phones;

	@MarshallField(as="emails",
				   whenXml=@MarshallFieldAsXml(collectionElementName="email"))
	@Getter @Setter private Collection<EMail> _emails;
/////////////////////////////////////////////////////////////////////////////////////////
//  REFERENCE
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @return a reference to this model object (a type that encapsulates the oid and the id)
	 */
	public X47BOrganizationalModelObjectRef<O,ID> getReference() {
		return new X47BOrganizationalModelObjectRef<O,ID>(_oid,_id);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  HasLangDependentNamedFacet
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public LangDependentNamed asLangDependentNamed() {
		return new LangDependentNamedDelegate<SELF_TYPE>((SELF_TYPE)this);
	}
	@SuppressWarnings("unchecked")
	@Getter private final transient LanguageTextsWrapper<SELF_TYPE> _name = LanguageTextsWrapper.atHasLang((SELF_TYPE)this);
/////////////////////////////////////////////////////////////////////////////////////////
//	SUMMARIES
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Summarizable asSummarizable() {
		Summary sum = SummaryBuilder.languageDependent()
									.create(this);
		return SummarizableBuilder.summarizableFrom(sum);
	}
	@Override
	public FullTextSummarizable asFullTextSummarizable() {
		return SummarizableBuilder.fullTextSummarizableFrom(this);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  VALIDATION
/////////////////////////////////////////////////////////////////////////////////////////
	@Override @SuppressWarnings("unchecked")
	public ObjectValidationResult<SELF_TYPE> validate() {
		return X47BOrganizationalModelObjectsValidators.<SELF_TYPE>createOrgObjectValidatorBase()
													   .validate((SELF_TYPE)this);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  ALARM RAISE COUNT
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Increases the alarm raise count and sets the last alarm raise date
	 * to the current date / time
	 */
	public void increaseAlarmRaiseCount() {
		_lastAlarmRaiseDate = new Date();
		_alarmRaiseCount = _alarmRaiseCount + 1;
	}
/////////////////////////////////////////////////////////////////////////////////////////
// AUX METHODS
/////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * Compose string with all phones separated by comma character.
	 * @return string with all phones separated by comma character, 
	 * 		   empty string if hasn´t phones.
	 */
	public final String convertPhonesToString() {
		String phonesText = "";
		if(_phones != null && !_phones.isEmpty()) {
			phonesText = Joiner.on(',')
								.skipNulls()
								.join(_phones);
		}
		return phonesText;
	}	
	/**
	 * Compose string with all emails separated by comma character.
	 * @return string with all emails separated by comma character, 
	 * 		   empty string if hasn´t phones.
	 */
	public final String convertEmailsToString() {
		String emailsText = "";
		if(_emails != null && !_emails.isEmpty()) {
			emailsText = Joiner.on(',')
								.skipNulls()
								.join(_emails);
		}
		return emailsText;
	}	
	/**
	 * Compose phone collection from string phones separated by comma character.
	 * @param splitedPhonesStr string phones separated by comma character
	 * @return phones collection or null if empty.
	 */
	public static final Collection<Phone> getPhonesCollectionFromSplittedString(final String splitedPhonesStr) {
		Collection<Phone> outObjs = null;         
        if(Strings.isNOTNullOrEmpty(splitedPhonesStr)) {
	        List<String> outObjsLst = Splitter.on(',')
        									  .trimResults()
        									  .splitToList(splitedPhonesStr);
	        if (!outObjsLst.isEmpty()) {
	        	outObjs = Sets.newHashSetWithExpectedSize(outObjsLst.size());
	            for (String phoneNumber: outObjsLst) {
	            	outObjs.add(Phone.create(phoneNumber));
	            }        	        	
	        }
        }
        return outObjs;
	}
	/**
	 * Compose emails collection from string emails separated by comma character.
	 * @param splitedEmailsStr string emails separated by comma character
	 * @return emails collection or null if empty.
	 */
	public static final Collection<EMail> getEmailsCollectionFromSplittedString(final String splitedEmailsStr) {
		Collection<EMail> outObjs = null;         
        if(Strings.isNOTNullOrEmpty(splitedEmailsStr)) {
	        List<String> outObjsLst = Splitter.on(',')
	        									.trimResults()
	        									.splitToList(splitedEmailsStr);
	        if (!outObjsLst.isEmpty()) {
	        	outObjs = Sets.newHashSetWithExpectedSize(outObjsLst.size());
	            for (String email: outObjsLst) {
	            	outObjs.add(EMail.create(email));
	            }        	        	
	        }
        }
        return outObjs;
	}
}
