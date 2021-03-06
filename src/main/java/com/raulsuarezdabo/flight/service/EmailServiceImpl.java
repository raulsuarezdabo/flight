/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.service;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class EmailServiceImpl implements EmailService {

    private MandrillApi mandrillApi;

    private Document doc;

    private Configuration freemarkerConfiguration;

    private static final String FROM_NAME = "Flight Now!";
    private static final String FROM_EMAIL = "no-reply@suarez.com.es";

    /**
     * parametrized constructor
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public EmailServiceImpl() throws ParserConfigurationException, SAXException, IOException {
        String key = this.getKey();
        this.mandrillApi = new MandrillApi(key);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream input = getClass().getResourceAsStream("mailing.xml");
        this.doc = builder.parse(input);
    }

    /**
     * Getter MandrillApi
     *
     * @return
     */
    public MandrillApi getMandrillApi() {
        return mandrillApi;
    }

    /**
     * Setter mandrillApi
     *
     * @param mandrillApi
     */
    public void setMandrillApi(MandrillApi mandrillApi) {
        this.mandrillApi = mandrillApi;
    }

    /**
     * Getter document
     *
     * @return
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * Setter document
     *
     * @param doc
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
        this.freemarkerConfiguration = freemarkerConfiguration;
    }

    public Configuration getFreemarkerConfiguration() {
        return freemarkerConfiguration;
    }

    /**
     * method to send an email
     *
     * @param receivers
     * @param content
     * @param type
     * @param locale
     */
    @Override
    public void sendMail(ArrayList<UserEntity> receivers, HashMap content, String type, Locale locale) {

        try {
            // create your message
            MandrillMessage message = new MandrillMessage();
            message.setSubject(this.getSubject(type, locale));
            message.setHtml(this.renderTemplate(type, content));
            message.setAutoText(true);
            message.setFromEmail(this.getFromEmail(type));
            message.setFromName(this.getFromName(type));
            // Receivers block
            ArrayList<Recipient> recipients = this.prepareRecipientList(receivers);
            message.setTo(recipients);
            message.setPreserveRecipients(true);
            ArrayList<String> tags = null;
            tags = this.getTags(type);
            message.setTags(tags);
            MandrillMessageStatus[] messageStatusReports = mandrillApi.messages().send(message, false);
        } catch (IOException | TemplateException | MandrillApiError ex) {
            Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * method to prepare the collection of user that is going to receive the
     * email
     *
     * @param users
     * @return
     */
    private ArrayList<Recipient> prepareRecipientList(ArrayList<UserEntity> users) {
        ArrayList<Recipient> recipients = new ArrayList<>();
        try {
            Iterator iterator = users.iterator();
            while (iterator.hasNext()) {
                UserEntity thisUser = (UserEntity) iterator.next();
                Recipient recipient = new Recipient();
                recipient.setName(thisUser.getName());
                recipient.setEmail(thisUser.getEmail());
                recipients.add(recipient);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return recipients;
    }

    /**
     * Method for loading the api key for mandrill
     *
     * @return
     */
    private String getKey() {
        ResourceBundle resource = ResourceBundle.getBundle("com.raulsuarezdabo.flight.mandrill");
        String key = resource.getString("key");
        return key;
    }

    /**
     * Method to find the tags that corresponts to the type
     *
     * @param type
     * @return
     */
    private ArrayList<String> getTags(String type) {
        ArrayList<String> tags = new ArrayList<>();
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/collection/item[@id='" + type + "']/tags/tag";

        try {
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(this.doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                tags.add(nodeList.item(i).getFirstChild().getNodeValue());
            }
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
        }
        return tags;
    }

    /**
     * Method to obtain the email from for sending the email
     *
     * @param type
     * @return
     */
    private String getFromEmail(String type) {
        String email = null;
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/collection/item[@id='" + type + "']/from/email";

        try {
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(this.doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                email = nodeList.item(i).getFirstChild().getNodeValue();
            }
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
        }
        return (email == null) ? FROM_EMAIL : email;
    }

    /**
     * Method to obtain the name from for sending the email
     *
     * @param type
     * @return
     */
    private String getFromName(String type) {
        String name = null;
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/collection/item[@id='" + type + "']/from/name";

        try {
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(this.doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                name = nodeList.item(i).getFirstChild().getNodeValue();
            }
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
        }
        return (name == null) ? FROM_NAME : name;
    }

    /**
     * Method to obtain the template from for sending the email
     *
     * @param type
     * @return
     */
    private String getTemplate(String type) {
        String template = null;
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/collection/item[@id='" + type + "']/template";

        try {
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(this.doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                template = nodeList.item(i).getFirstChild().getNodeValue();
            }
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
        }
        return template;
    }

    /**
     * Method to obtain the message with the correct language
     *
     * @param type
     * @param locale
     * @return
     */
    private String getSubject(String type, Locale locale) {
        String subject = null;
        String messages = null;
        if (locale.getLanguage().equals("es")) {
            messages = "com.raulsuarezdabo.flight.messages";
        } else {
            messages = "com.raulsuarezdabo.flight.messages_" + locale.getLanguage().toLowerCase();
        }
        ResourceBundle resource = ResourceBundle.getBundle(messages);
        subject = resource.getString("subjectEmail" + type.substring(0, 1).toUpperCase() + type.substring(1));

        return subject;
    }

    /**
     * Method that renders the template from the view
     *
     * @param type
     * @return
     */
    private String renderTemplate(String type, Object model) throws IOException, TemplateException {
        String template = this.getTemplate(type);
        return FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(template, "UTF-8"), model);
    }
}
