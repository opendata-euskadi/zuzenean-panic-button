package pb01a.model.org;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pb01a.model.PB01AObjectsValidators;
import r01f.locale.Language;
import r01f.validation.ObjectValidationResult;
import r01f.validation.ObjectValidationResultBuilder;
import r01f.validation.Validates;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public abstract class PB01AOrganizationalObjectsValidators {
/////////////////////////////////////////////////////////////////////////////////////////
//	BASE
/////////////////////////////////////////////////////////////////////////////////////////
	public static <E extends PB01AOrganizationalPersistableObjectBase<?,?,E>> Validates<E> createOrgObjectValidatorBase() {
		return new Validates<E>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<E> validate(final E obj) {
							// super valid?
							ObjectValidationResult<E> superValidResult = PB01AObjectsValidators.<E>createEntityObjectValidatorBase()
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
	public static Validates<PB01AOrgDivision> createOrgDivisionValidator() {
		return new Validates<PB01AOrgDivision>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<PB01AOrgDivision> validate(final PB01AOrgDivision obj) {
							// super valid?
							ObjectValidationResult<PB01AOrgDivision> superValidResult = PB01AOrganizationalObjectsValidators.<PB01AOrgDivision>createOrgObjectValidatorBase()
																							  								   .validate(obj);
							// this valid?
							ObjectValidationResult<PB01AOrgDivision> thisValidResult = null;
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
	public static Validates<PB01AOrgDivisionService> createOrgDivisionServiceValidator() {
		return new Validates<PB01AOrgDivisionService>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<PB01AOrgDivisionService> validate(final PB01AOrgDivisionService obj) {
							// super valid?
							ObjectValidationResult<PB01AOrgDivisionService> superValidResult = PB01AOrganizationalObjectsValidators.<PB01AOrgDivisionService>createOrgObjectValidatorBase()
																							  								   .validate(obj);
							// this valid?
							ObjectValidationResult<PB01AOrgDivisionService> thisValidResult = null;
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
	public static Validates<PB01AOrgDivisionServiceLocation> createOrgDivisionServiceLocationValidator() {
		return new Validates<PB01AOrgDivisionServiceLocation>() {
						@Override @SuppressWarnings("unchecked")
						public ObjectValidationResult<PB01AOrgDivisionServiceLocation> validate(final PB01AOrgDivisionServiceLocation obj) {
							// super valid?
							ObjectValidationResult<PB01AOrgDivisionServiceLocation> superValidResult = PB01AOrganizationalObjectsValidators.<PB01AOrgDivisionServiceLocation>createOrgObjectValidatorBase()
																							  								   .validate(obj);
							// this valid?
							ObjectValidationResult<PB01AOrgDivisionServiceLocation> thisValidResult = null;
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
