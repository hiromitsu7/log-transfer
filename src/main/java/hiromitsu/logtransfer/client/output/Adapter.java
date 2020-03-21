package hiromitsu.logtransfer.client.output;

/**
 * ログ出力を仲介する
 */
public interface Adapter {

  void info(String message);

  void error(String message, Exception ex);
}
