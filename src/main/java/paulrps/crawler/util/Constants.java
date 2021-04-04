package paulrps.crawler.util;

public class Constants {
  private Constants() {}

  static {
    LINE_SEPARATOR = System.lineSeparator();
  }

  public static final String TAB_SPACE = "\t";
  public static final String LINE_SEPARATOR;
  public static final String CHROME_USER_AGENT =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36";
}
