package site.mini.junitproject.util;

import org.springframework.stereotype.Component;

//Test 용
@Component
public class MailSenderStub implements MailSender {

    @Override
    public boolean send() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
