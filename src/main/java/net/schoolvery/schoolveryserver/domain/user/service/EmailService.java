package net.schoolvery.schoolveryserver.domain.user.service;

public interface EmailService {
    String sendEmailMessage(String to) throws Exception;
}
