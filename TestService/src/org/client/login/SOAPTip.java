package org.client.login;

import java.io.FileInputStream;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class SOAPTip {

	public static void main(String args[]) {

		try {

			// First create the connection
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();
			// Next, create the actual message
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();

			// Create objects for the message parts
			SOAPPart soapPart = message.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			SOAPBody body = envelope.getBody();

			// Populate the body
			StreamSource preppedMsgSrc = new StreamSource(new FileInputStream(
					"./message/SOAP.msg"));
			soapPart.setContent(preppedMsgSrc);
			// Save the message
			message.saveChanges();

			// Check the input
			System.out.println("\nREQUEST:\n");
			message.writeTo(System.out);
			System.out.println();
			// Send the message and get a reply

			// Set the destination
			String destination = "http://localhost:8080/WebService/LoginService";
			// Send the message
			SOAPMessage reply = connection.call(message, destination);

			// Check the output
			System.out.println("\nRESPONSE:\n");
			// Create the transformer
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// Extract the content of the reply
			Source sourceContent = reply.getSOAPPart().getContent();
			// Set the output for the transformation
			StreamResult result = new StreamResult(System.out);
			transformer.transform(sourceContent, result);
			System.out.println();

			// Close the connection
			connection.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}