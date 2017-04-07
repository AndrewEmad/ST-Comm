package Controllers;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DBModels.RegistrantDBModel;
import Entities.Registrant;
import ch.qos.logback.classic.Logger;

@RestController
public class RegistrantController {

	@RequestMapping("st-comm.com/signup")
	public boolean createAccount(/*@RequestParam String name, @RequestParam Date birthdate,
								 @RequestParam String gender, @RequestParam String mail,
								 @RequestParam String country, @RequestParam String password*/) {
		//Validate input
//		if(RegistrantDBModel.exists(name, mail) == true){
//			return false;
//		}
//		Registrant registrant = new Registrant();
//		registrant.setInfo(name, birthdate, gender, mail, country, password, false);
//		if(RegistrantDBModel.saveAccount(registrant) == false){
//			return false;
//		}
//		try {
//			new Test_Ahmed_Hussein().send();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return true;
	}

	@RequestMapping("st-comm.com/login")
	public boolean authenticate(String name, String password) {
		return true;
	}

	public boolean ValidateInput(String name, Date birthdate, String gender, String mail, String country,
			String password) {
		return false;
	}

	public void setConfirmed(String name, String activationCode) {
	}

	public void sendConfirmationMail() {
	}

}