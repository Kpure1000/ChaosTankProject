import Control.GatewayOut;
import Control.GatewayIn;

public class ServerMain {
    public static void main(String[] args) {

        gatewayOut = new GatewayOut();

        gatewayIn = new GatewayIn();
        gatewayIn.Open(gatewayOut);
        gatewayIn.Close();
    }

    private static GatewayIn gatewayIn;
    private static GatewayOut gatewayOut;

}
