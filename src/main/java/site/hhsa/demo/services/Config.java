package site.hhsa.demo.services;

public class Config {
    private final String ACCOUNT_SID = "AC0aab0858a7c9ff5624bbbc06e5afa9ed";
    private final String AUTH_TOKEN = "f72c35ea6aa47a734b0cfab7fbb6b5a6";
    private final String phn_num = "+12109412256";

    public Config() {
    }

    public String getACCOUNT_SID() {
        return ACCOUNT_SID;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public String getPhn_num() {
        return phn_num;
    }
}
