package x47b.api.context;

import lombok.experimental.Accessors;
import r01f.guids.CommonOIDs.UserCode;
import r01f.guids.CommonOIDs.WorkPlaceCode;
import r01f.locale.Language;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.securitycontext.SecurityContextUserDataBase;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import x47b.model.X47BModelObject;

@MarshallType(as="securityContextUserData")
@Accessors(prefix="_")
public class X47BSecurityContextUserData
	 extends SecurityContextUserDataBase
  implements X47BModelObject {

	private static final long serialVersionUID = 8114955252922390809L;
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BSecurityContextUserData(final UserCode userCode,final WorkPlaceCode workPlace,
									   final String name,final String surname,
									   final String displayName,
									   final Language prefLang,
									   final EMail email,final Phone phone) {
		super(userCode,workPlace,
			  name,surname,
			  displayName,
			  prefLang,
			  email,phone);
	}
	public X47BSecurityContextUserData(final UserCode userCode,final WorkPlaceCode workPlace,
									   final String name,final String surname,
									   final String displeyName,
									   final EMail email,final Phone phone) {
		this(userCode,workPlace,
			 name,surname,
			 displeyName,
			 Language.DEFAULT,
			 email,phone);
	}
	public X47BSecurityContextUserData(final UserCode userCode,final WorkPlaceCode workPlace,
									   final String name,final String surname,
									   final Language prefLang,
									   final EMail email,final Phone phone) {
		this(userCode,workPlace,
			 name,surname,
			 _displayNameFrom(name,surname),			// display name
			 prefLang,
			 email,phone);
	}
	public X47BSecurityContextUserData(final UserCode userCode,final WorkPlaceCode workPlace,
									   final String name,final String surname,
									   final EMail email,final Phone phone) {
		this(userCode,workPlace,
			 name,surname,
			 Language.DEFAULT,
			 email,phone);
	}
	public X47BSecurityContextUserData(final UserCode userCode,final WorkPlaceCode workPlace,
									   	  final String name,final String surname,
									   	  final Language prefLang) {
		this(userCode,workPlace,
			 name,surname,
			 _displayNameFrom(name,surname),			// display name
			 prefLang,
			 null,null);	// phone & email
	}
	public X47BSecurityContextUserData(final UserCode userCode,final WorkPlaceCode workPlace,
									   final String name,final String surname) {
		this(userCode,workPlace,
			 name,surname,
			 Language.DEFAULT);
	}
	public X47BSecurityContextUserData(final UserCode userCode,final WorkPlaceCode workPlace,
									   final String name,
									   final Language prefLang) {
		this(userCode,workPlace,
			 name,null,
			 name,			// display name
			 prefLang,
			 null,null);	// phone & email
	}
	public X47BSecurityContextUserData(final UserCode userCode,final WorkPlaceCode workPlace,
									   final String name) {
		this(userCode,workPlace,
			 name,
			 Language.DEFAULT);
	}
	public static X47BSecurityContextUserData createForMaster() {
		return new X47BSecurityContextUserData(UserCode.MASTER,WorkPlaceCode.forId("master"),
											   	  "master",
											   	  Language.DEFAULT);
	}
}
