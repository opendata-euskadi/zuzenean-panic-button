package x47b.bootstrap.core.panicbutton;


import com.google.common.collect.Lists;

import lombok.EqualsAndHashCode;
import r01f.bootstrap.persistence.SearchGuiceModuleBase;
import r01f.bootstrap.persistence.SearcherProviderBinding;
import r01f.persistence.search.config.SearchModuleConfig;
import x47b.db.search.X47BPanicButtonSearcherProviders.X47BDBSearcherProviderForPanicButtonObject;

@EqualsAndHashCode(callSuper=true)				// This is important for guice modules
  class X47BPanicButtonSearchGuiceModule
extends SearchGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public X47BPanicButtonSearchGuiceModule(final SearchModuleConfig searchModuleCfg) {
		super(searchModuleCfg,
			  // searcher providers
			  Lists.<SearcherProviderBinding<?,?>>newArrayList(
					  X47BDBSearcherProviderForPanicButtonObject.createGuiceBinding()
			  ));
	}
}
