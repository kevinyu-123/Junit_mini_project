package site.mini.junitproject.util;

import org.springframework.stereotype.Component;

 @Component
public class MailSenderApater implements MailSender {

    private Mail mail;

    public MailSenderApater(){
        this.mail = new Mail();
    }

    @Override
    public boolean send() {
        return mail.sendMail();
    }

    
    
}
