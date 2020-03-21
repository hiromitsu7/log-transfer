package hiromitsu.logtransfer.client.input;

import java.io.File;
import java.io.FileFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hiromitsu.logtransfer.client.output.Log4jAdapter;

/**
 * クライアント
 */
public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
  private static final String DIR_PATH = ".";
  private static final String REGEXP = ".*\\.log";

  public static final void main(String[] args) {

    File monitoredFile = findLastModifiedFile(DIR_PATH, REGEXP);
    LOGGER.info("monitored file: " + monitoredFile.getAbsolutePath());

    // 切り替える
    LogTailer tailer = new LogTailer(new Log4jAdapter());
    tailer.startTailer(monitoredFile);

    DirectoryObserver.startObserver(DIR_PATH);
  }

  private static File findLastModifiedFile(final String dirPath, final String regexp) {
    File lastModifiedFile = null;
    File dir = new File(dirPath);
    File[] files = dir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        return pathname.isFile() && pathname.getName().matches(regexp);
      }
    });
    for (File file : files) {
      if (lastModifiedFile == null) {
        lastModifiedFile = file;
      }
      if (file.lastModified() > lastModifiedFile.lastModified()) {
        lastModifiedFile = file;
      }
    }

    if (lastModifiedFile == null) {
      throw new IllegalArgumentException("監視対象ファイルなし");
    }

    return lastModifiedFile;
  }
}
