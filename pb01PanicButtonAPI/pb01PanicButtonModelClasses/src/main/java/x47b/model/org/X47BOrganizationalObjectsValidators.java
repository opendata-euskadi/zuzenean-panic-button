package x47b.model.org;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import r01f.locale.Language;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;
import x47b.model.X47BObjectsValidators;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class X47BOrganizationalObjectsValidators {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE
/////////////////////////////////////////////////////////////////////////////////////////
	public static <E extends X47BOrganizationalPersistableObjectBase<?,?,E>> Validates<E> createOrgObjectValidatorBase() {
		return new Validates<E>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<E> validate(final E obj) {
							// super valid?
							ObjectValidationResult<E> superValidResult = X47BObjectsValidators.<E>createEntityObjectValidatorBase()
																							  .validate(obj);
							// this valid?
							ObjectValidationResult<E> thisValidResult = null;
							// the model object MUST have a name
							if (obj.getNameByLanguage() == null
							 || !obj.getNameByLanguage().isTextDefinedFor(Language.SPANISH,Language.BASQUE)) {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
														.isNotValidBecause("The name is NOT valid");
							}

							// combine
							return ObjectValidationResultBuilder.on(obj)
																.combine(superValidResult,
																		 thisValidResult);
						}
			   };
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG
/////////////////////////////////////////////////////////////////////////////////////////
	// NOTHING

/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION
/////////////////////////////////////////////////////////////////////////////////////////
	public static Validates<X47BOrgDivision> createOrgDivisionValidator() {
		return new Validates<X47BOrgDivision>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<X47BOrgDivision> validate(final X47BOrgDivision obj) {
							// super valid?
							ObjectValidationResult<X47BOrgDivision> superValidResult = X47BOrganizationalObjectsValidators.<X47BOrgDivision>createOrgObjectValidatorBase()
																							  								   .validate(obj);
							// this valid?
							ObjectValidationResult<X47BOrgDivision> thisValidResult = null;
							if (obj.getOrgRef() == null
							 || obj.getOrgRef().getOid() == null
							 || obj.getOrgRef().getId() == null) {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
																	.isNotValidBecause("The organization reference is NOT valid");
							}
							// combine
							return ObjectValidationResultBuilder.on(obj)
																.combine(superValidResult,
																		 thisValidResult);
						}
		};
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE
/////////////////////////////////////////////////////////////////////////////////////////
	public static Validates<X47BOrgDivisionService> createOrgDivisionServiceValidator() {
		return new Validates<X47BOrgDivisionService>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<X47BOrgDivisionService> validate(final X47BOrgDivisionService obj) {
							// super valid?
							ObjectValidationResult<X47BOrgDivisionService> superValidResult = X47BOrganizationalObjectsValidators.<X47BOrgDivisionService>createOrgObjectValidatorBase()
																							  								   .validate(obj);
							// this valid?
							ObjectValidationResult<X47BOrgDivisionService> thisValidResult = null;
							if (obj.getOrgRef() == null
							 || obj.getOrgRef().getOid() == null
							 || obj.getOrgRef().getId() == null) {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
																	.isNotValidBecause("The organization reference is NOT valid");
							}
							if (obj.getOrgDivisionRef() == null
							 || obj.getOrgDivisionRef().getOid() == null
							 || obj.getOrgDivisionRef().getId() == null) {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
																	.isNotValidBecause("The division reference is NOT valid");
							}
							// combine
							return ObjectValidationResultBuilder.on(obj)
																.combine(superValidResult,
																		 thisValidResult);
						}
		};
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	ORG DIVISION SERVICE LOCATION
/////////////////////////////////////////////////////////////////////////////////////////
	public static Validates<X47BOrgDivisionServiceLocation> createOrgDivisionServiceLocationValidator() {
		return new Validates<X47BOrgDivisionServiceLocation>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<X47BOrgDivisionServiceLocation> validate(final X47BOrgDivisionServiceLocation obj) {
							// super valid?
							ObjectValidationResult<X47BOrgDivisionServiceLocation> superValidResult = X47BOrganizationalObjectsValidators.<X47BOrgDivisionServiceLocation>createOrgObjectValidatorBase()
																							  								   .validate(obj);
							// this valid?
							ObjectValidationResult<X47BOrgDivisionServiceLocation> thisValidResult = null;
							if (obj.getOrgRef() == null
							 || obj.getOrgRef().getOid() == null
							 || obj.getOrgRef().getId() == null) {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
																	.isNotValidBecause("The organization reference is NOT valid");
							}
							if (obj.getOrgDivisionRef() == null
							 || obj.getOrgDivisionRef().getOid() == null
							 || obj.getOrgDivisionRef().getId() == null) {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
																	.isNotValidBecause("The division reference is NOT valid");
							}
							if (obj.getOrgDivisionServiceRef() == null
							 || obj.getOrgDivisionServiceRef().getOid() == null
							 || obj.getOrgDivisionServiceRef().getId() == null) {
								thisValidResult = ObjectValidationResultBuilder.on(obj)
																	.isNotValidBecause("The service reference is NOT valid");
							}
							// combine
							return ObjectValidationResultBuilder.on(obj)
																.combine(superValidResult,
																		 thisValidResult);
						}
		};
	}
}
