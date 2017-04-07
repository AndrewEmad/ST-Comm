package Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import DBModels.RegistrantDBModel;
import Entities.Registrant;
import ch.qos.logback.classic.Logger;

@RestController
public class RegistrantController {

	@Autowired
	JavaMailSender javaMailSender;
	
	@RequestMapping(method=RequestMethod.POST, value="/st-comm.com/signup")
	public boolean createAccount(@RequestParam String name, @RequestParam("birthdate") @DateTimeFormat(pattern="yyyy-MM-dd") Date birthdate,
								 @RequestParam String gender, @RequestParam String mail,
								 @RequestParam String country, @RequestParam String password) {
		//Validate input
		if(RegistrantDBModel.exists(name, mail) == true){
			return false;
		}
		Registrant registrant = new Registrant();
		registrant.setInfo(name, birthdate, gender, mail, country, password, false);
		if(RegistrantDBModel.saveAccount(registrant) == false){
			return false;
		}
		try {
			String activationCode = RegistrantDBModel.getActivationCode(name);
			sendConfirmationMail(activationCode, registrant);
		} catch (MessagingException e) {
			return false;
		}
		return true;
	}

	@RequestMapping("/st-comm.com/login")
	public boolean authenticate(String name, String password) {
		return true;
	}

	public boolean ValidateInput(String name, Date birthdate, String gender, String mail, String country,
			String password) {
		return true;
	}

	@RequestMapping("/st-comm.com/confirm/{activationCode}")
	public  void setConfirmed(String name, @PathVariable String activationCode) {
//		RegistrantDBModel.setConfirmed(name, activationCode);
//		return new ModelAndView("redirect:" + "accountActivationError.html");
	}

	public void sendConfirmationMail(String activationCode, Registrant registrant) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setTo(registrant.getMail());
		helper.setSubject("ST-Comm account confirmation");
		helper.setText("<b>Hey "+registrant.getName()+",</b><br/>We're ready to activate your account. All we need to do is make sure this is your email address.<br/><a href=\"http://localhost:8090/st-comm.com/confirm/"+activationCode+"\">Click here to activate your account</a><br/>If you didn't create a ST-Comm account, just delete this email and everything will go back to the way it was.", true);
		javaMailSender.send(message);
	}

}