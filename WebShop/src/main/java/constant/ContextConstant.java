package constant;

public final class ContextConstant {

    private ContextConstant() {
    }

    public static final String CAPTCHA_HANDLER = "captchaHandler";
    public static final String CAPTCHA_PRESERVER = "captchaPreserver";

    public static final String USER_SERVICE = "userService";
    public static final String AVATAR_SERVICE = "avatarService";
    public static final String CAPTCHA_SERVICE = "captchaService";

    public static final String HIDDEN_FIELD_CAPTCHA_HANDLER = "hiddenFieldCaptchaHandler";
    public static final String COOKIE_CAPTCHA_HANDLER = "cookieCaptchaHandler";
    public static final String SESSION_CAPTCHA_HANDLER = "sessionCaptchaHandler";

    public static final String SERVLET_DESTROYED = "Servlet Context destroyed";

    public static final String CONNECTION_POOL_NOT_CLOSED = "Connection pool wasn't closed";

    // connection pool properties
    public static final boolean DEFAULT_AUTO_COMMIT = false;
    public static final boolean ROLLBACK_ON_RETURN = true;
    public static final String DRIVER = "db.driver";
    public static final String URL = "db.url";
    public static final String USER_NAME = "db.username";
    public static final String DB_PASSWORD = "db.password";
    public static final String INIT_SIZE = "db.pool.initSize";
    public static final String MAX_SIZE = "db.pool.maxSize";

    public static final String CONNECTION_POOL_PROPERSTIES_FILE = "application.properties";

}
