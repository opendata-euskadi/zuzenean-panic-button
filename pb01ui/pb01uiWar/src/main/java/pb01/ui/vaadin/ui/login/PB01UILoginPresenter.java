package pb01.ui.vaadin.ui.login;

import javax.inject.Inject;

import lombok.experimental.Accessors;
import pb01a.api.context.PB01ASecurityContextUserData;
import r01f.guids.CommonOIDs.Password;
import r01f.guids.CommonOIDs.UserCode;
import r01f.ui.presenter.UIPresenter;

@Accessors(prefix="_")
public class PB01UILoginPresenter
  implements UIPresenter {

	private static final long serialVersionUID = -4290934932756711025L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	private final PB01UILoginCOREMediator _authCoreMediator;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@Inject
	public PB01UILoginPresenter(final PB01UILoginCOREMediator authCoreMediator) {
		_authCoreMediator = authCoreMediator;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01ASecurityContextUserData userPasswordLogIn(final UserCode user,final Password password) {
		return _authCoreMediator.userPasswordLogIn(user,password);
	}
}
