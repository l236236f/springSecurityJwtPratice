package ssj.security.common.constants;

import ssj.security.helper.ConfigHelper;

import java.util.Properties;

/**
 * @author harveylo
 * @description Spring Security 配置常量
 */
public class SecurityConstants {

  public static final String CONFIG_PATH = "/jwt.properties";

  public static final Properties properties = ConfigHelper.getConfig(SecurityConstants.CONFIG_PATH);

  public static final String PREFIX = properties.getProperty("jwt.token.prefix");

  public static final String HEADER = properties.getProperty("jwt.token.header");

  public static final String[] SWAGGER_WHITE_LIST = properties.getProperty("swagger.whitelist").split(",");

  public static final String[] SYSTEM_WHITE_LIST = properties.getProperty("system.whitelist").split(",");

  public static final String JWT_SECRET_KEY = properties.getProperty("jwt.secret.key");
  /**
   * 角色的Key
   **/
  public static final String ROLE_CLAIMS = "rol";

  /**
   * rememberMe  false 過期時間 定義 "5分鐘"
   */
  public static final long EXPIRATION = 5 * 60L;

  /**
   * rememberMe 為 true 過期時間 "一小時"
   */
  public static final long EXPIRATION_REMEMBER = 60 * 60L;

  /**
   * JWT設定
   */
  public static final String TOKEN_TYPE = "JWT";


  private SecurityConstants() {
  }

}
