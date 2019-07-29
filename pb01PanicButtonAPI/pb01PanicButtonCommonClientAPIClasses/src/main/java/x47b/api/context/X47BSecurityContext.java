package x47b.api.context;

import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import r01f.guids.CommonOIDs.AppCode;
import r01f.guids.CommonOIDs.AuthenticatedActorID;
import r01f.guids.CommonOIDs.TenantID;
import r01f.guids.CommonOIDs.UserCode;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.securitycontext.SecurityContextBase;

/**
 * API {@link UserContext} implementation
 */
@MarshallType(as="context")
@Accessors(prefix="_")
@NoArgsConstructor
public class X47BSecurityContext 
     extends SecurityContextBase {

	private static final long serialVersionUID = 1315691985185065475L;
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * This constructor is MANDATORY since {@link X47BSecurityContext} is mapped as a @HeaderParam
	 * at REST jesrouces.
	 * This types of params NEEDS either:
	 * 		<ul>
	 *			<li>Be a primitive type</li>
	 *			<li>Have a constructor that accepts a single String argument</li>
	 *			<li>Have a static method named valueOf or fromString that accepts a single String argument (see, for example, Integer.valueOf(String))</li>
	 *			<li>Have a registered implementation of ParamConverterProvider JAX-RS extension SPI that returns a ParamConverter instance capable of a "from string" conversion for the type.</li>
	 *			<li>Be List<T>, Set<T> or SortedSet<T>, where T satisfies 2, 3 or 4 above. The resulting collection is read-only.</li>
	 *		</ul>
	 * @param usrContextXML
	 */
	public X47BSecurityContext(final String usrContextXML) {
		X47BSecurityContext usrCtx = new X47BSecurityContext();
		_authenticatedActorId = usrCtx.getAuthenticatedActorId();
		_tenantId = usrCtx.getTenantId() != null ? usrCtx.getTenantId()
												 : TenantID.DEFAULT; 
		_createDate = new Date();
	}
	/**
	 * Constructor from the {@link AppCode}.
	 * Usually this method uses some kind of security system to build the context
	 * @param appCode 
	 */
	public X47BSecurityContext(final AppCode appCode) {
		this(AuthenticatedActorID.forApp(appCode));
	}
	/**
	 * Constructor from the {@link UserCode}.
	 * @param userCode
	 */
	public X47BSecurityContext(final UserCode userCode) {
		this(AuthenticatedActorID.forUser(userCode));
	}
	/**
	 * Constructor from the {@link AuthenticatedActorID}.
	 * @param authActor
	 */
	public X47BSecurityContext(final AuthenticatedActorID authActor) {
		this(authActor,
			 TenantID.DEFAULT);
	}
	/**
	 * Constructor from the {@link AppCode} and tenantId
	 * Usually this method uses some kind of security system to build the context
	 * @param appCode 
	 * @param tenantId
	 */
	public X47BSecurityContext(final AppCode appCode,
						   final TenantID tenantId) {
		this(AuthenticatedActorID.forApp(appCode),
			 tenantId);
	}
	/**
	 * Constructor from the {@link UserCode} and tenantId
	 * @param userCode
	 * @param tenantId
	 */
	public X47BSecurityContext(final UserCode userCode,
						   final TenantID tenantId) {
		this(AuthenticatedActorID.forUser(userCode),
			 tenantId);
	}
	/**
	 * Constructor from the {@link AuthenticatedActorID} and tenantId
	 * @param authActor
	 * @param tenantId
	 */
	public X47BSecurityContext(final AuthenticatedActorID authActor,
						   final TenantID tenantId) {
		super(authActor,
			  tenantId);
	}
}
