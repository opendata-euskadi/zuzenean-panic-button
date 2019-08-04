package x47b.model.org;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;

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
import r01f.types.contact.ValidatedContactID;
import r01f.types.summary.Summary;
import r01f.types.summary.SummaryBuilder;
import r01f.util.types.StringSplitter;
import r01f.util.types.Strings;
import r01f.util.types.collections.CollectionUtils;
import r01f.util.types.collections.Lists;
import r01f.validation.ObjectValidationResult;
import r01f.validation.SelfValidates;
import x47b.model.X47BPersistableObjectBase;
import x47b.model.oids.X47BOrganizationalIDs.X47BOrgObjectID;
import x47b.model.oids.X47BOrganizationalOIDs.X47BOrgObjectOID;

@ConvertToDirtyStateTrackable
@Accessors(prefix="_")
public abstract class X47BOrganizationalPersistableObjectBase<O extends X47BOrgObjectOID,ID extends X47BOrgObjectID<O>,
										 			   		  SELF_TYPE extends X47BOrganizationalPersistableObjectBase<O,ID,SELF_TYPE>>
		      extends X47BPersistableObjectBase<O,ID,SELF_TYPE>
		   implements X47BOrganizationalPersistableObject<O,ID>,
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
	public X47BOrgObjectRef<O,ID> getReference() {
		return new X47BOrgObjectRef<O,ID>(_oid,_id);
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
		return X47BOrganizationalObjectsValidators.<SELF_TYPE>createOrgObjectValidatorBase()
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
//
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String getPhonesAsString() {
		return _colToString(_phones);
	}
	@Override
	public void setPhonesFromString(final String phones) {
		_phones = X47BOrganizationalPersistableObjectBase.phonesCollectionFromString(phones);
	}
	@Override
	public String getEmailsAsString() {
		return _colToString(_emails);
	}
	@Override
	public void setEmailsFromString(final String emails) {
		_emails = X47BOrganizationalPersistableObjectBase.emailsCollectionFromString(emails);
	}
	public static String phonesAsString(final Collection<Phone> phones) {
		return _colToString(phones);
	}
	public static String emailsAsString(final Collection<EMail> emails) {
		return _colToString(emails);
	}
	/**
	 * Compose phone collection from string phones separated by comma, semicolon or space character.
	 * @param splitedPhonesStr
	 * @return phones collection or null if empty.
	 */
	public static Collection<Phone> phonesCollectionFromString(final String splitedPhonesStr) {
		Collection<String> phones = _split(splitedPhonesStr);
		if (CollectionUtils.isNullOrEmpty(phones)) return null;
		return FluentIterable.from(phones)
							 .transform(str -> Phone.of(str))
							 .toList();
	}
	/**
	 * Compose emails collection from string emails separated by comma, semicolon or space character.
	 * @param splitedEmailsStr
	 * @return emails collection or null if empty.
	 */
	public static Collection<EMail> emailsCollectionFromString(final String splitedEmailsStr) {
		Collection<String> emails = _split(splitedEmailsStr);
		if (CollectionUtils.isNullOrEmpty(emails)) return null;
		return FluentIterable.from(emails)
							 .transform(str -> EMail.of(str))
							 .toList();
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	private static String _colToString(final Collection<? extends ValidatedContactID> col) {
		if (CollectionUtils.isNullOrEmpty(col)) return null;
		StringBuilder str = new StringBuilder();
		for (Iterator<? extends ValidatedContactID> idIt = col.iterator(); idIt.hasNext(); ) {
			ValidatedContactID id = idIt.next();
			if (id == null) continue;
			str.append(id.asString());
			if (idIt.hasNext()) str.append("; ");
		}
		return str.toString();
	}
	private static Collection<String> _split(final String str) {
		if (Strings.isNullOrEmpty(str)) return null;
		Collection<String> s0 = Lists.newArrayList(str);
		Collection<String> s1 = _split(s0,' ');
		Collection<String> s2 = _split(s1,',');
		Collection<String> s3 = _split(s2,';');
		Collection<String> s4 = _split(s3,' ');
		Collection<String> s5 = _split(s4,'/');
		return FluentIterable.from(s5)
							 .filter(s -> Strings.isNOTNullOrEmpty(s))
							 .toList();
	}
	private static Collection<String> _split(final Collection<String> strs,
									  		 final char separator) {
		if (CollectionUtils.isNullOrEmpty(strs)) return null;
		Collection<String> outStrs = Lists.newArrayList();
		for (String str : strs) {
			Collection<String> strSplitted = StringSplitter.using(Splitter.on(separator))
												 .at(str)
												 .toCollection();
			outStrs.addAll(strSplitted);
		}
		return outStrs;
	}
}
