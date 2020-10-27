package pnc.mesadmin.dto.AOPScheduler;

import pnc.mesadmin.entity.EmailMessageInfo;

import java.util.Set;

/**
 * Created by PNC on 2019/5/13.
 */
public class Gqrevice {
    private Set<String> emailAdressList;
    private EmailMessageInfo emailMessageInfo;
    private SendEmailDate emailDate;

    public Set<String> getEmailAdressList() {
        return emailAdressList;
    }

    public void setEmailAdressList(Set<String> emailAdressList) {
        this.emailAdressList = emailAdressList;
    }


    public EmailMessageInfo getEmailMessageInfo() {
        return emailMessageInfo;
    }

    public void setEmailMessageInfo(EmailMessageInfo emailMessageInfo) {
        this.emailMessageInfo = emailMessageInfo;
    }

    public SendEmailDate getEmailDate() {
        return emailDate;
    }

    public void setEmailDate(SendEmailDate emailDate) {
        this.emailDate = emailDate;
    }
}
