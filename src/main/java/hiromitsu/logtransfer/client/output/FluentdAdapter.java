package hiromitsu.logtransfer.client.output;

import java.util.HashMap;
import java.util.Map;

import org.fluentd.logger.FluentLogger;

/**
 * Fluentdへ出力する
 */
public class FluentdAdapter implements Adapter {

  // TODO 変更可能にする
  private static FluentLogger logger = FluentLogger.getLogger("app", "192.168.99.103", 24224);

  @Override
  public void info(String message) {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("level", "info");
    data.put("message", message);
    logger.log("output", data);
  }

  @Override
  public void error(String message, Exception ex) {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("level", "error");
    data.put("message", message);
    data.put("exception", ex);
    logger.log("output", data);
  }
}
