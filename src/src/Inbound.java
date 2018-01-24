/* This file is part of Inbound Sample.

The Inbound Sample is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

The Inbound Sample is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with The Inbound.  If not, see <http://www.gnu.org/licenses/>.

 * Program Work Flow
 * 1. Receive xml message as a String.
 * 2. Check form, retrieve parameters.
 * 3. Write parameters in CSV log file.
 *
 */

package src;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Inbound {

    private final static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    // Parameters
    private String timeStamp;
    private String pushId;
    private String inboundId;
    private String subscriptionUid;
    private String phoneNumber;
    private String contents;
    private String keyword;
    private String dataCapture;
    private String attachment;
    private String datasetId;
    private String datasetName;

    public Inbound() {

    }

    public void execute(String xml) throws Exception {

        if (getValueFromExpression(xml, "TRUMPIA").isEmpty()) {
            return;
        }

        parsing(xml);

        writeLog();
    }

    // Getters for parameters
    public String getTimeStamp() {

        return timeStamp;
    }

    public String getPushId() {

        return pushId;
    }

    public String getInboundId() {

        return inboundId;
    }

    public String getSubscriptionUid() {

        return subscriptionUid;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public String getContents() {

        return contents;
    }

    public String getKeyword() {

        return keyword;
    }

    public String getDataCapture() {

        return dataCapture;
    }

    public String getAttachment() {

        return attachment;
    }

    public String getDatasetId() {

        return datasetId;
    }

    public String getDatasetName() {

        return datasetName;
    }

    // This function parses String to parameters
    private void parsing(String inboundMsg) throws Exception {

        timeStamp = getValueFromExpression(inboundMsg, "/TRUMPIA/TIME_STAMP");
        pushId = getValueFromExpression(inboundMsg, "/TRUMPIA/PUSH_ID");
        inboundId = getValueFromExpression(inboundMsg, "/TRUMPIA/INBOUND_ID");
        subscriptionUid = getValueFromExpression(inboundMsg, "/TRUMPIA/SUBSCRIPTION_UID");
        phoneNumber = getValueFromExpression(inboundMsg, "/TRUMPIA/PHONENUMBER");
        keyword = getValueFromExpression(inboundMsg, "/TRUMPIA/KEYWORD");
        dataCapture = getValueFromExpression(inboundMsg, "/TRUMPIA/DATACAPTURE");
        contents = getValueFromExpression(inboundMsg, "/TRUMPIA/contents");
        attachment = getValueFromExpression(inboundMsg, "/TRUMPIA/ATTACHMENT");
        datasetId = getValueFromExpression(inboundMsg, "/TRUMPIA/DATASET_ID");
    }

    // This functions get values from expression.
    private String getValueFromExpression(String xml, String expression) throws Exception {

        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));

        XPath xPath = XPathFactory.newInstance().newXPath();

        return xPath.compile(expression).evaluate(xmlDocument);
    }

    // This function writes parameters in CSV file.
    private void writeLog() throws IOException {

        String trumpiaLog = "c:/documents/TrumpiaLog.csv";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(trumpiaLog, true), "MS949"));
        writer.write("SUBSCRIPTION_UID," + getSubscriptionUid() + "\r\n");
        writer.write("PHONENUMBER," + getPhoneNumber() + "\r\n");
        writer.write("KEYWORD," + getKeyword() + "\r\n");
        writer.write("CONTENTS," + getContents() + "\r\n");
        writer.close();
    }
}
