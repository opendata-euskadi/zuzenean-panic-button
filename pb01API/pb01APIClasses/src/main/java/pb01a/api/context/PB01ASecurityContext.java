package pb01a.api.context;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import r01f.guids.CommonOIDs.AppCode;
import r01f.guids.CommonOIDs.AuthenticatedActorID;
import r01f.guids.CommonOIDs.TenantID;
import r01f.guids.CommonOIDs.UserCode;
import r01f.locale.Language;
import r01f.objectstreamer.annotations.MarshallField;
import r01f.objectstreamer.annotations.MarshallType;
import r01f.securitycontext.SecurityContextBase;

/**
 * API {@link UserContext} implementation
 */
@MarshallType(as="context")
@Accessors(prefix="_")
@NoArgsConstructor
public class PB01ASecurityContext
     extends SecurityContextBase {

    private static final long serialVersionUID = 1315691985185065475L;
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    @MarshallField(as="userData")
    @Getter @Setter private PB01ASecurityContextUserData _userData;

	public Language getPrefLang() {
		return this.getPrefLangOrDefault(Language.DEFAULT);
	}
	public Language getPrefLangOrDefault(final Language def) {
		return _userData != null && _userData.getPrefLang() != null
						? _userData.getPrefLang()
						: def;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////
	public static PB01ASecurityContext userLoginToSecurityContext(final PB01ASecurityContextUserData userData) {
		PB01ASecurityContext outCtx = new PB01ASecurityContext();
		outCtx.setCreateDate(new Date());
		outCtx.setAuthenticatedActorId(AuthenticatedActorID.forUser(userData.getUser()));
		outCtx.setUserData(userData);	// no email & no phone
		return outCtx;
	}
    /**
     * This constructor is MANDATORY since {@link PB01ASecurityContext} is mapped as a @HeaderParam
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
    public PB01ASecurityContext(final String usrContextXML) {
        PB01ASecurityContext usrCtx = new PB01ASecurityContext();
        _authenticatedActorId = usrCtx.getAuthenticatedActorId();
        _tenantId = usrCtx.getTenantId() != null ? usrCtx.getTenantId()
                                                 : TenantID.DEFAULT;
        _createDate = new Date();
    }
	/**
	 * Creates a {@link R01DemoSecurityContext} instance from the user data
	 * @param userData
	 */
	public PB01ASecurityContext(final PB01ASecurityContextUserData userData) {
		this(userData != null && userData.getUser() != null ? userData.getUser()
															: UserCode.ANONYMOUS);
		_userData = userData;
	}
	/**
	 * Creates a {@link R01DemoSecurityContext} instance from the user data
	 * @param userData
	 * @param tenantId
	 */
	public PB01ASecurityContext(final PB01ASecurityContextUserData userData,
							   final TenantID tenantId) {
		this(userData != null && userData.getUser() != null ? userData.getUser()
															: UserCode.ANONYMOUS,
			 tenantId);
		_userData = userData;
	}
    /**
     * Constructor from the {@link AppCode}.
     * Usually this method uses some kind of security system to build the context
     * @param appCode
     */
    public PB01ASecurityContext(final AppCode appCode) {
        this(AuthenticatedActorID.forApp(appCode));
    }
    /**
     * Constructor from the {@link UserCode}.
     * @param userCode
     */
    public PB01ASecurityContext(final UserCode userCode) {
        this(AuthenticatedActorID.forUser(userCode));
    }
    /**
     * Constructor from the {@link AuthenticatedActorID}.
     * @param authActor
     */
    public PB01ASecurityContext(final AuthenticatedActorID authActor) {
        this(authActor,
             TenantID.DEFAULT);
    }
    /**
     * Constructor from the {@link AppCode} and tenantId
     * Usually this method uses some kind of security system to build the context
     * @param appCode
     * @param tenantId
     */
    public PB01ASecurityContext(final AppCode appCode,
                                  final TenantID tenantId) {
        this(AuthenticatedActorID.forApp(appCode),
             tenantId);
    }
    /**
     * Constructor from the {@link UserCode} and tenantId
     * @param userCode
     * @param tenantId
     */
    public PB01ASecurityContext(final UserCode userCode,
                                  final TenantID tenantId) {
        this(AuthenticatedActorID.forUser(userCode),
             tenantId);
    }
    /**
     * Constructor from the {@link AuthenticatedActorID} and tenantId
     * @param authActor
     * @param tenantId
     */
    public PB01ASecurityContext(final AuthenticatedActorID authActor,
                                  final TenantID tenantId) {
        super(authActor,
              tenantId);
    }
}
