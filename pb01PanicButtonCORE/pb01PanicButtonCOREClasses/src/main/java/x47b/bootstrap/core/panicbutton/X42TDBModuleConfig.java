package x47b.bootstrap.core.panicbutton;

import javax.persistence.EntityManager;

import r01f.bootstrap.services.config.core.ServicesCoreBootstrapConfigWhenBeanExposed;
import r01f.patterns.Factory;
import r01f.patterns.MemoizedUponFactory;
import r01f.persistence.db.config.DBModuleConfig;
import r01f.persistence.db.config.DBModuleConfigBase;
import r01f.persistence.db.config.DBModuleConfigBuilder;
import r01f.persistence.db.config.DBModuleConfigWrapper;
import r01f.services.ids.ServiceIDs.CoreModule;
import r01f.xmlproperties.XMLPropertiesForAppComponent;
import x47b.db.entities.X47BDBEntityForOrganization;

/**
 * Wraps the {@link DBModuleConfig} in order to override the {@link DBModuleConfigBase}{@link #isFullTextSearchSupported(EntityManager)} method
 */
public class X42TDBModuleConfig 
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
	public X42TDBModuleConfig(final DBModuleConfig _wrappedConfig) {
		super(_wrappedConfig);
	}
	public static final X42TDBModuleConfig dbConfigFor(final XMLPropertiesForAppComponent xmlProps) {
		DBModuleConfig dbModuleConfig = DBModuleConfigBuilder.dbConfigFor(xmlProps);
		return new X42TDBModuleConfig(dbModuleConfig);
	}
	public static X42TDBModuleConfig dbModuleConfigFrom(final ServicesCoreBootstrapConfigWhenBeanExposed coreCfg) {
		DBModuleConfig dbModuleConfig = coreCfg.getSubModuleConfigFor(CoreModule.DBPERSISTENCE);
		return new X42TDBModuleConfig(dbModuleConfig);
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
														return DBModuleConfigBase.<X47BDBEntityForOrganization>testFullText(entityManager,
																			 										   		X47BDBEntityForOrganization.class,
																			 										   		"_nameSpanish");	// any full-text indexed col
													}
											});
	}
}
