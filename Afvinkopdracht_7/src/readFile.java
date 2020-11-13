import java.io.File;

/**
 * Class om een file op te slaan.
 */
public class readFile {

    protected File file;
    protected String fileName;

    public readFile(File file) {
        this.file = file;
        setFileName();
    }

    public File getFile() {
        return this.file;
    }

    public void setFileName() {
        this.fileName = this.file.getName();
    }

    public String getFileName() {
        return this.fileName;
    }
}
