package tw.com.rex;

import tw.com.rex.base.BaseAction;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Rex on 2017/07/25.
 */
public class DownloadAction extends BaseAction {

    private String fileName;
    private InputStream fileInputStream;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) throws UnsupportedEncodingException {
        this.fileName = new String(fileName.getBytes(), "ISO8859-1");
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String fileDownload() throws Exception {
        String fileName = "chromedriver_win32.zip";
        Path path = Paths.get("D:/Downloads/" + fileName);
        if (!Files.exists(path)) {
            fileName = "中文測試.txt";
            path = Paths.get("D:/Downloads/" + fileName);
        }
        setFileName(URLEncoder.encode("中文測試檔", "UTF-8"));
        setFileInputStream(Files.newInputStream(path, StandardOpenOption.READ));
        return "fileDownload";
    }

}
