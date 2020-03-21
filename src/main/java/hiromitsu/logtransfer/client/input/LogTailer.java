package hiromitsu.logtransfer.client.input;

import java.io.File;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

import hiromitsu.logtransfer.client.output.Adapter;

/**
 * 変更を検知してアダプターへ出力する
 */
public class LogTailer {
  private Adapter adapter;

  public LogTailer(Adapter adapter) {
    this.adapter = adapter;
  }

  public void startTailer(File monitoredFile) {
    TailerListener listener = new TailerListenerAdapter() {
      @Override
      public void fileRotated() {
        adapter.info("file is rotated");
      }

      @Override
      public void handle(final String line) {
        adapter.info(line);
      }

      @Override
      public void handle(final Exception ex) {
        adapter.error("exception is thrown: ", ex);
      }
    };

    Tailer tailer = new Tailer(monitoredFile, listener);
    Thread thread = new Thread(tailer);
    thread.start();
  }
}
