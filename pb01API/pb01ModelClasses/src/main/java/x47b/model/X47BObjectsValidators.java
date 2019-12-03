package x47b.model;

import java.util.Collection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;
import x47b.model.org.X47BOrganizationalPersistableObjectBase;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BObjectsValidators {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE
/////////////////////////////////////////////////////////////////////////////////////////
	public static <E extends X47BPersistableObjectBase<?,?,E>> Validates<E> createEntityObjectValidatorBase() {
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
		Collection<EMail> emails = X47BOrganizationalPersistableObjectBase.emailsCollectionFromString(value);
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
		Collection<Phone> phones = X47BOrganizationalPersistableObjectBase.phonesCollectionFromString(value);
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
