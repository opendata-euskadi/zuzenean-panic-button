package x47b.bootstrap.core.panicbutton;

import lombok.EqualsAndHashCode;
import r01f.bootstrap.persistence.DBGuiceModuleBase;
import r01f.persistence.db.config.DBModuleConfig;


@EqualsAndHashCode(callSuper=true)				// This is important for guice modules
  class X47BPanicButtonDBGuiceModule
extends DBGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BPanicButtonDBGuiceModule(final DBModuleConfig dbConfig) {
		super(dbConfig);	// x47b / panic
	}
}
