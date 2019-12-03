package pb01.ui.vaadin.ui.login;

import javax.inject.Inject;

import lombok.experimental.Accessors;
import r01f.guids.CommonOIDs.Password;
import r01f.guids.CommonOIDs.UserCode;
import r01f.ui.presenter.UIPresenter;
import x47b.api.context.X47BSecurityContextUserData;

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
	public X47BSecurityContextUserData userPasswordLogIn(final UserCode user,final Password password) {
		return _authCoreMediator.userPasswordLogIn(user,password);
	}
}
