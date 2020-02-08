package pb01t.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.google.common.collect.Lists;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import pb01a.bootstrap.client.panicbutton.PB01APanicButtonClientBootstrapConfigBuilder;
import pb01a.common.internal.P01AAppCodes;
import pb01c.bootstrap.core.panicbutton.PB01CPanicButtonCOREServicesBootstrapConfigBuilder;
import r01f.bootstrap.services.config.ServicesBootstrapConfig;
import r01f.bootstrap.services.config.ServicesBootstrapConfigBuilder;
import r01f.bootstrap.services.config.client.ServicesClientBootstrapConfig;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfig;
import r01f.exceptions.Throwables;
import r01f.test.api.TestAPIBase;
import r01f.xmlproperties.XMLPropertiesBuilder;
import r01f.xmlproperties.XMLPropertiesForApp;

/**
 * JVM arguments:
 * -javaagent:D:/tools_workspaces/eclipse/local_libs/aspectj/lib/aspectjweaver.jar -Daj.weaving.verbose=true
 */
@Accessors(prefix="_")
@RequiredArgsConstructor
public abstract class PB01TPanicButtonAPITestBase 
	   		  extends TestAPIBase {
/////////////////////////////////////////////////////////////////////////////////////////
//  JUnit
/////////////////////////////////////////////////////////////////////////////////////////
	@BeforeClass 
	public static void setUpBeforeClass() {
		try {
			// [0] - Load properties
			XMLPropertiesForApp xmlProps = XMLPropertiesBuilder.createForApp(P01AAppCodes.CORE_APPCODE)
															   .notUsingCache();
			// [1] - Create the modules bootstrap config
			ServicesClientBootstrapConfig clientBootCfg = PB01APanicButtonClientBootstrapConfigBuilder.buildClientBootstrapConfig();
			ServicesCoreBootstrapConfig coreBootCfg = PB01CPanicButtonCOREServicesBootstrapConfigBuilder.buildCoreBootstrapConfig(xmlProps);
			ServicesBootstrapConfig bootstrapCfg = ServicesBootstrapConfigBuilder
															.forClient(clientBootCfg)
														    .ofCoreModules(coreBootCfg)
														    .coreEventsHandledSynchronously()
														    .build();
			// [2] - Setup
			 _setUpBeforeClass(Lists.newArrayList(bootstrapCfg));
		} catch(Exception ex) {
			ex.printStackTrace(System.out);
			Throwables.throwUnchecked(ex);
		}
	}
	@AfterClass
	public static void tearDownAfterClass()  {
		// [99]-Tear things down
		try {
			_tearDownAfterClass();
		} catch(Exception ex) {
			ex.printStackTrace(System.out);
			Throwables.throwUnchecked(ex);
		}
	}
}
