package x47b.internal.services;

import lombok.Getter;
import lombok.experimental.Accessors;
import r01f.services.latinia.LatiniaServiceAPIData;
import r01f.types.Path;
import r01f.xmlproperties.XMLPropertiesForAppComponent;

@Accessors(prefix="_")
public class X47BNotifierConfigForLatinia
     extends X47BNotifierServiceConfigBase {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	@Getter private final LatiniaServiceAPIData _latiniaApiData;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR  
/////////////////////////////////////////////////////////////////////////////////////////
	private X47BNotifierConfigForLatinia(final boolean enabled,
							 			 final Path alertMsgTemplatePath,
							 			 final LatiniaServiceAPIData latiniaApiData) {
		super("messaging",
			  enabled,
			  alertMsgTemplatePath);
		_latiniaApiData = latiniaApiData;
	}
	private X47BNotifierConfigForLatinia(final XMLPropertiesForAppComponent props) {
		super("messaging",
			  props);
		_latiniaApiData = LatiniaServiceAPIData.createFrom(props,"notifier");
	}
	public static final X47BNotifierConfigForLatinia createFrom(final XMLPropertiesForAppComponent props) {
		return new X47BNotifierConfigForLatinia(props);
	}
}
