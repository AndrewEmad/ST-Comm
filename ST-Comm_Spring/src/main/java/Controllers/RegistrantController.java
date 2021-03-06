package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DBModels.RegistrantDBModel;
import Entities.Registrant;

@CrossOrigin(origins = "*") //allow services of this RestController to share data to 
							//any client side request
/**
 * 
 * @author Ahmed Hussein
 *
 */
@RestController
public class RegistrantController {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	HttpServletResponse httpServletResponse;
	
	/**
	 * 
	 * @param name
	 * @param birthdate
	 * @param gender
	 * @param mail
	 * @param country
	 * @param password
	 * @param type
	 * @return true if the account was created successfully and false otherwise
	 */
	@RequestMapping(method=RequestMethod.POST, value="/st-comm.com/signup")
	public boolean createAccount(@RequestParam String name, @RequestParam("birthdate") @DateTimeFormat(pattern="yyyy-MM-dd") Date birthdate,
								 @RequestParam String gender, @RequestParam String mail,
								 @RequestParam String country, @RequestParam String password,
								 @RequestParam String type) {
		try {
			if(RegistrantDBModel.exists(name, mail) == true){
				return false;
			}
		} catch (SQLException e1) { 
			return false;
		}
		Registrant registrant = new Registrant();
		registrant.setInfo(name, birthdate, gender, mail, country, password, false);
		try {
			int registrantCode;
			if(type.equals("student")){
				registrantCode = 1;
			}
			else{
				registrantCode = 2;
			}
			RegistrantDBModel.saveAccount(registrant, registrantCode);
			String activationCode = RegistrantDBModel.getActivationCode(name);
			sendConfirmationMail(activationCode, registrant);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param name
	 * @param password
	 * @return true if there is an existing account with the same name and password as provided
	 * 		   to the service, and false otherwise
	 */
	@RequestMapping(method=RequestMethod.POST, value="/st-comm.com/login")
	public boolean authenticate(@RequestParam String name, @RequestParam String password) {
		try {
			return RegistrantDBModel.authenticate(name, password);
		} catch (SQLException e) {
			return false;
		}
	}

	@RequestMapping("/st-comm.com/confirm/{name}/{activationCode}")
	public void setConfirmed(@PathVariable String name, @PathVariable String activationCode) {
		try {
			if(RegistrantDBModel.setConfirmed(name, activationCode))
				httpServletResponse.sendRedirect("/accountActivated.html");
			else
				httpServletResponse.sendRedirect("/accountActivationError.html");
		} catch (Exception e) {
			try {
				httpServletResponse.sendRedirect("/accountActivationError.html");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void sendConfirmationMail(String activationCode, Registrant registrant) {
		String name = registrant.getName();
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(registrant.getMail());
			helper.setSubject("ST-Comm account confirmation");
			helper.setText("<b>Hey "+name+",</b><br/>We're ready to activate your account. All we need to do is make sure this is your email address.<br/><a href=\"http://localhost:8090/st-comm.com/confirm/"+name+"/"+activationCode+"\">Click here to activate your account</a><br/>If you didn't create a ST-Comm account, just delete this email and everything will go back to the way it was.", true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param name: the name of the registrant whose type is being checked
	 * @return an integer indicating whether that name is a student name or a teacher name
	 */
	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/query/registrant-type")
	public int getRegistrantType(@RequestParam String name){
		int registrantCode = 0;
		try {
			registrantCode = RegistrantDBModel.getRegistrantType(name);
		} catch (SQLException e) {
			return 0;
		}
		return registrantCode;
	}
	
	public static void sendNotification(String msg, String registrantName){
		try {
			RegistrantDBModel.pushNotification(msg, registrantName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<String> getNotifications(String registrantName){
		try {
			return RegistrantDBModel.getNotifications(registrantName);
		} catch (SQLException e) {
			return new Vector<String>();
		}
	}
	
}