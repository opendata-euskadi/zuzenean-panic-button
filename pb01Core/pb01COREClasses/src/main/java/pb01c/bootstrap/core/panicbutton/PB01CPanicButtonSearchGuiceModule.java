package pb01c.bootstrap.core.panicbutton;


import com.google.common.collect.Lists;

import lombok.EqualsAndHashCode;
import pb01c.db.search.PB01CPanicButtonSearcherProviders.PB01CDBSearcherProviderForPanicButtonObject;
import r01f.bootstrap.persistence.SearchGuiceModuleBase;
import r01f.bootstrap.persistence.SearcherProviderBinding;
import r01f.persistence.search.config.SearchModuleConfig;

@EqualsAndHashCode(callSuper=true)				// This is important for guice modules
  class PB01CPanicButtonSearchGuiceModule
extends SearchGuiceModuleBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CPanicButtonSearchGuiceModule(final SearchModuleConfig searchModuleCfg) {
		super(searchModuleCfg,
			  // searcher providers
			  Lists.<SearcherProviderBinding<?,?>>newArrayList(
					  PB01CDBSearcherProviderForPanicButtonObject.createGuiceBinding()
			  ));
	}
}
