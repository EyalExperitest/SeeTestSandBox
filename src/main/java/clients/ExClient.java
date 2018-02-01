package clients;

import com.experitest.client.Client;

/**
 * Created by eyal.neumann on 1/10/2018.
 */
public class ExClient extends Client {
    protected final String host;
    protected final int port;
    protected final boolean useSessionID;

    public ExClient(String host, int port, boolean useSessionID) {
        super(host, port, useSessionID);
        this.host=host;
        this.port=port;
        this.useSessionID=useSessionID;
    }
}
