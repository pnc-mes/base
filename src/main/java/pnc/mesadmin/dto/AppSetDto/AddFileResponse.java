package pnc.mesadmin.dto.AppSetDto;


/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-04-02
 **/
public class AddFileResponse {
    private String file;
    private String fileName;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
