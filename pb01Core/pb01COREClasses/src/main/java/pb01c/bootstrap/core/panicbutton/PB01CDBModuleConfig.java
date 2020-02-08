package pb01c.bootstrap.core.panicbutton;

import javax.persistence.EntityManager;

import pb01c.db.entities.PB01CDBEntityForOrganization;
import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.patterns.Factory;
import r01f.patterns.MemoizedUponFactory;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.config.DBModuleConfigBase;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.persistence.db.config.DBModuleConfigWrapper;
import r01f.services.ids.ServiceIDs.CoreModule;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

/**
 * Wraps the {@link DBModuleConfig} in order to override the {@link DBModuleConfigBase}{@link #isFullTextSearchSupported(EntityManager)} method
 */
public class PB01CDBModuleConfig 
	 extends DBModuleConfigWrapper {
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Memoizes the full-text support so it's NOT computed again and again 
	 */
	private final MemoizedUponFactory<Boolean> _fullTextSearchSupported = new MemoizedUponFactory<Boolean>();
	 
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public PB01CDBModuleConfig(final DBModuleConfig _wrappedConfig) {
		super(_wrappedConfig);
	}
	public static final PB01CDBModuleConfig dbConfigFor(final XMLPropertiesForAppComponent xmlProps) {
		DBModuleConfig dbModuleConfig = DBModuleConfigBuilder.dbConfigFor(xmlProps);
		return new PB01CDBModuleConfig(dbModuleConfig);
	}
	public static PB01CDBModuleConfig dbModuleConfigFrom(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg) {
		DBModuleConfig dbModuleConfig = coreCfg.getSubModuleConfigFor(CoreModule.DBPERSISTENCE);
		return new PB01CDBModuleConfig(dbModuleConfig);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean isFullTextSearchSupported(final EntityManager entityManager) {
		return _fullTextSearchSupported.get(new Factory<Boolean>() {
													@Override
													public Boolean create() {
														// tries to run a full-text search... if it fails full text search is NOT enabled
														return DBModuleConfigBase.<PB01CDBEntityForOrganization>testFullText(entityManager,
																			 										   		PB01CDBEntityForOrganization.class,
																			 										   		"_nameSpanish");	// any full-text indexed col
													}
											});
	}
}
