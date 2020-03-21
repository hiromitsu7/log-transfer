package hiromitsu.logtransfer.client.output;

import org.apache.log4j.Logger;

/**
 * Log4jへ出力する
 */
public class Log4jAdapter implements Adapter {
  private static final Logger LOGGER = Logger.getLogger(Log4jAdapter.class);

  @Override
  public void info(String message) {
    LOGGER.info(message);
  }

  @Override
  public void error(String message, Exception ex) {
    LOGGER.error(message, ex);
  }
}
