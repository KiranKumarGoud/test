package com.excelra.mvc.model.Mail;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ResourceLoader;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import java.util.Properties;
import java.util.ResourceBundle;




/***
 * <p></p>
 * @author priyanka.veidhey
 */
public class Mail {

    /**
     * get the properties of the outlook and smtp configuration
     * according to the internatilization
     */

     ResourceBundle resourceBundle = ResourceBundle.getBundle("GVK_MAILS");

    String port = resourceBundle.getString("mail.port");
    String mailHost = resourceBundle.getString("mail.host");
    String from = resourceBundle.getString("mail.from");
    String SMTP_AUTH_USER = resourceBundle.getString("mail.from");
    String SMTP_AUTH_PWD = resourceBundle.getString("mail.auth.pwd");


    public void sendMail(String [] recipients,String [] cc_recipients, String subject,String body)
    {
        boolean debug = false;

        try{
            //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", mailHost);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");

            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getDefaultInstance(props, auth);

            session.setDebug(debug);

            // create a message

            Message msg = new MimeMessage(session);

            // set the from and to address
            // InternetAddress addressFrom = new InternetAddress(from);
            // msg.setFrom(addressFrom);
            msg.setContent(body, "text/HTML");
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++)
            {

                addressTo[i] = new InternetAddress(recipients[i]);
            }


            msg.setRecipients(Message.RecipientType.TO, addressTo);

            if(cc_recipients != null && cc_recipients.length>0) {
                String[] ccStr =  cc_recipients[0].split(",");

                InternetAddress[] addressCC = new InternetAddress[ccStr.length];
                for (int i = 0; i < ccStr.length; i++)
                {

                    if(ccStr[i]!=null)
                        addressCC[i] = new InternetAddress(ccStr[i]);
                }
                msg.setRecipients(Message.RecipientType.CC, addressCC);
            }
	       /*for (int i = 0; i < cc_recipients.length; i++)
	       {
	    	   System.out.println("cc_recipients:   "+cc_recipients[i]);
	    	   if(cc_recipients[i]!=null)
	    	   addressCC[i] = new InternetAddress(cc_recipients[i]);
	       }*/





            Transport tr = session.getTransport("smtp");

            //tr.connect();
            tr.connect(mailHost, SMTP_AUTH_USER, SMTP_AUTH_PWD);

            msg.saveChanges();
            msg.setFrom(new InternetAddress(from));// don't forget this
            msg.setSubject(subject);

            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();

            // Setting the Subject and Content Type
            //msg.setSubject(subject);
            //msg.setContent(message, "text/plain");
            //Transport.send(msg);
        }
        catch(SendFailedException se)
        {
            //se.printStackTrace();
            String className = new Throwable().getStackTrace()[0].getClassName();
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            Constants.writeExceptionLog(className, methodName, se);

        }
        catch(Exception ex)
        {
            //ex.printStackTrace();
            String className = new Throwable().getStackTrace()[0].getClassName();
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            Constants.writeExceptionLog(className, methodName, ex);
        }
    }

    public void getMailFrmProperty(BeanForMail beanForMail) throws Exception
    {
        System.out.println("getMailFrmProperty is called...");
        String [] recipients = null;
        String [] cc_recipients = null;
        if(!Constants.checkNull(beanForMail.getToAddress()).equals(""))
            recipients=getMailArray(beanForMail.getToAddress());
        if(!Constants.checkNull(beanForMail.getCcAddress()).equals(""))
            cc_recipients=getMailArray(beanForMail.getCcAddress());
        String subject=beanForMail.getSubject();
        String body=beanForMail.getBody();

        if(recipients != null)
            sendMail(recipients,cc_recipients,subject,body);


    }

    public void getMailforUser(BeanForMail beanForMail) throws Exception
    {


        String [] recipients=new String []{beanForMail.getToAddress()};
        String cc=beanForMail.getCcAddress();
        String [] cc_recipients=null;
        if(cc!=null)
            cc_recipients=new String []{cc};
        else
            cc_recipients=new String []{};

        String subject=beanForMail.getSubject();
        String body=beanForMail.getBody();

        sendMail(recipients,cc_recipients,subject,body);
    }

    public String [] getMailArray(String mail)
    {

        System.out.println("getMailArray is called...");
        String [] emailList = null;


        ResourceBundle rb= ResourceBundle.getBundle("GVK_MAILS");

        String toEmail=rb.getString(mail);


        if(toEmail.equals(""))
        {
            emailList= new String[0];
        }else {
            if(toEmail.indexOf(",")>0) {

                emailList=toEmail.split(",");
            }
            else
                emailList= new String[]{toEmail};
        }
        for(String s:emailList) {
            System.out.println("in getMailArray s mails are");
        }
        return emailList;
    }
    public static void main(String args[]) throws Exception
    {
        try{
            Mail  mail  = new  Mail();

            mail.sendMail(emailList,emailListcc,"test mail from gostardbNext","gostarNext is an online application , data export notification for completion will implements soon... Just an emial test");
            System.out.println("Sucessfully Sent mail to All Users");
        }catch(Exception e){
            //e.printStackTrace();
            String className = new Throwable().getStackTrace()[0].getClassName();
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            Constants.writeExceptionLog(className, methodName, e);
        }
    }

    private class SMTPAuthenticator extends Authenticator
    {

        public javax.mail.PasswordAuthentication getPasswordAuthentication()
        {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
    private static final String[] emailList = {"priyanka.veidhey@excelra.com"};
    private static final String[] emailListcc = {"shreekanth.gummadi@excelra.com,deepsingh.chouhan@excelra.com","suresh.velishala@Excelra.com"};



}
