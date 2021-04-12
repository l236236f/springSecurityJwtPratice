package ssj.security.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigHelper {
  private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHelper.class);

  ConfigHelper() {
  }

  public static Properties getConfig(String path) {
    String var10000 = System.getProperty("user.dir");
    String filePath = var10000 + path;
    Properties config = new Properties();

    try {
      InputStreamReader inputStream = new InputStreamReader(new FileInputStream(filePath), Charset.forName("utf-8"));
      Throwable var4 = null;

      try {
        config.load(inputStream);
      } catch (Throwable var16) {
        var4 = var16;
        throw var16;
      } finally {
        if (var4 != null) {
          try {
            inputStream.close();
          } catch (Throwable var15) {
            var4.addSuppressed(var15);
          }
        } else {
          inputStream.close();
        }

      }
    } catch (FileNotFoundException var18) {
      LOGGER.debug("System can not open config file: " + filePath, var18);
    } catch (IOException var19) {
      LOGGER.debug("System read config file fail: " + filePath, var19);
    } catch (Exception var20) {
      LOGGER.debug("System read config file fail ", var20);
    }

    return config;
  }

  static List<String> parseMultiTopics(String topicCompose) {
    return Arrays.asList(topicCompose.replaceAll(" ", "").split(","));
  }
}
