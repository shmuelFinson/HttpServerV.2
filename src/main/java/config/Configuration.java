package config;

public class Configuration {

    private int port;
    private String webroot;
    private String create_record;
    private String read_record;
    private String update_record;
    private String delete_record;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebroot() {
        return webroot;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

    public String getCreate_record() {
        return create_record;
    }

    public void setCreate_record(String create_record) {
        this.create_record = create_record;
    }

    public String getRead_record() {
        return read_record;
    }

    public void setRead_record(String read_record) {
        this.read_record = read_record;
    }


    public String getUpdate_record() {
        return update_record;
    }

    public void setUpdate_record(String update_record) {
        this.update_record = update_record;
    }

    public String getDelete_record() {
        return delete_record;
    }

    public void setDelete_record(String delete_record) {
        this.delete_record = delete_record;
    }


}
