package src;

import org.junit.Test;

public class InboundTest {

    @Test
    public void test() throws Exception {

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><TRUMPIA><PUSH_ID>289054852</PUSH_ID><INBOUND_ID>66076129</INBOUND_ID><SUBSCRIPTION_UID>229874806</SUBSCRIPTION_UID><PHONENUMBER>7142348577</PHONENUMBER><KEYWORD>paulkim2</KEYWORD><DATA_CAPTURE /><CONTENTS /><ATTACHMENT /><DATASET_ID>0</DATASET_ID><DATASET_NAME>Master Account</DATASET_NAME></TRUMPIA>";
        Inbound inbound = new Inbound();
        inbound.execute(xml);
    }

}
