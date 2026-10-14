package client;

public class RequestInfo {

    private String url;
    private String body;
    private String token;

    public RequestInfo(String url, String body) {
        this.url = url;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBody() {
        return body;
    }
}
