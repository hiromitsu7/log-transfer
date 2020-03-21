package hiromitsu.logtransfer.client.input;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 指定されたディレクトリ内の変更を監視する
 */
public class DirectoryObserver {

  private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryObserver.class);
  private static final long POLLING_INTERVAL = 1000;

  public static void startObserver(String dirPath) {
    FileAlterationListener listener = new FileAlterationListenerAdaptor() {
      @Override
      public void onFileCreate(final File file) {
        LOGGER.info("file created: " + file.getAbsolutePath());
      }

      @Override
      public void onFileChange(final File file) {
        LOGGER.info("file changed: " + file.getAbsolutePath());
      }
    };

    File directory = new File(dirPath);
    FileAlterationObserver observer = new FileAlterationObserver(directory);
    FileAlterationMonitor monitor = new FileAlterationMonitor(POLLING_INTERVAL);

    observer.addListener(listener);
    monitor.addObserver(observer);

    try {
      LOGGER.info("start monitoring ...");
      Thread thread = new Thread(monitor);
      thread.start();
    } catch (Exception e) {
      LOGGER.error("monitorでエラー発生", e);
    }
  }
}
