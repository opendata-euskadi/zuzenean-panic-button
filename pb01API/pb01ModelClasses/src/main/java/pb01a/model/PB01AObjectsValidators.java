package pb01a.model;

import java.util.Collection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pb01a.model.org.PB01AOrganizationalPersistableObjectBase;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01AObjectsValidators {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE
/////////////////////////////////////////////////////////////////////////////////////////
	public static <E extends PB01APersistableObjectBase<?,?,E>> Validates<E> createEntityObjectValidatorBase() {
		return new Validates<E>() {
						@Override
						public ObjectValidationResult<E> validate(final E obj) {
							if (obj.getOid() == null) return ObjectValidationResultBuilder.on(obj)
																			.isNotValidBecause("The oid is mandatory");
							return ObjectValidationResultBuilder.on(obj)
																.isValid();
						}
			   };
	}
	public static boolean isValidEmails(final String value) {
		Collection<EMail> emails = PB01AOrganizationalPersistableObjectBase.emailsCollectionFromString(value);
		boolean allValid = true;
		for (EMail email : emails) {
			if (!email.isValid()) {
				allValid = false;
				break;
			}
		}
		return allValid;
	}
	public static boolean isValidPhones(final String value) {
		Collection<Phone> phones = PB01AOrganizationalPersistableObjectBase.phonesCollectionFromString(value);
		boolean allValid = true;
		for (Phone phone : phones) {
			if (!phone.isValid()) {
				allValid = false;
				break;
			}
		}
		return allValid;
	}
}
