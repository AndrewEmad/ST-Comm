package Controllers;

import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DBModels.RegistrantDBModel;
import Entities.Registrant;

@RestController
public class RegistrantController {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	HttpServletResponse httpServletResponse;
	
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

	@RequestMapping(method=RequestMethod.POST, value="/st-comm.com/login")
	public boolean authenticate(@RequestParam String name, @RequestParam String password) {
		return RegistrantDBModel.authenticate(name, password);
	}

	public boolean ValidateInput(String name, Date birthdate, String gender, String mail, String country,
			String password) {
		return true;
	}

	@RequestMapping("/st-comm.com/confirm/{activationCode}")
	public  void setConfirmed(String name, @PathVariable String activationCode) throws IOException {
		if(RegistrantDBModel.setConfirmed(name, activationCode) == true){
			//go to home page
		}
		else{
			httpServletResponse.sendRedirect("http://localhost:8090/accountActivationError.html");
		}
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