package x47b.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BEntityObjectsValidators {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE	
/////////////////////////////////////////////////////////////////////////////////////////
	public static <E extends X47BEntityObjectBase<?,?,E>> Validates<E> createEntityObjectValidatorBase() {
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
}
