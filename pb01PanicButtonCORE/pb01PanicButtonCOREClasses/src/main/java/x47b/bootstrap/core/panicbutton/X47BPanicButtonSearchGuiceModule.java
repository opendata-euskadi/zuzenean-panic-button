package x47b.bootstrap.core.panicbutton;


import lombok.EqualsAndHashCode;
import r01f.bootstrap.persistence.SearchGuiceModuleBase;
import r01f.persistence.search.config.SearchModuleConfig;
import x47b.search.db.X47BPanicButtonSearcherProviders.X47BDBSearcherProviderForPanicButtonObject;

@EqualsAndHashCode(callSuper=true)				// This is important for guice modules
  class X47BPanicButtonSearchGuiceModule 
extends SearchGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public X47BPanicButtonSearchGuiceModule(final SearchModuleConfig searchModuleCfg) {
		super(searchModuleCfg,
			  X47BDBSearcherProviderForPanicButtonObject.class);
	}
}
