package pb01c.bootstrap.core.panicbutton;

import lombok.EqualsAndHashCode;
import r01f.bootstrap.persistence.DBGuiceModuleBase;
import r01f.persistence.db.config.DBModuleConfig;


@EqualsAndHashCode(callSuper=true)				// This is important for guice modules
  class PB01CPanicButtonDBGuiceModule
extends DBGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CPanicButtonDBGuiceModule(final DBModuleConfig dbConfig) {
		super(dbConfig);	// pb01c / panic
	}
}
