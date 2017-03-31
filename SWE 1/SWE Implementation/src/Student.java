import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.StyledEditorKit.BoldAction;


public class Student {
	private String fname=null,lname=null,password=null,Email=null, gender=null;
    private int age=-1;

    public Student() {
    }

    public Student(String fname,String lname,String password,String mail ,String gender, int age) {
            this.fname = fname;
            this.lname=lname;
            this.password = password;
            this.Email = mail;
            this.gender = gender;
            this.age = age;
    }

    public void setfName(String name) {
        this.fname = name;
    }
    public void setlName(String name) {
        this.lname = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.Email = mail;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public String getfName() {
        return fname;
    }
    public String getlName() {
        return lname;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return Email;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Boolean correction(){

    	if(fname.equals(null)||lname.equals(null)||password.equals(null)||Email.equals(null)||gender.equals(null)||age==-1){

    		return false;
    	}
    	else{
    		return true;
    	}

    }
    public Boolean RequestToCheckRegistrationValidation()throws IOException{

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("E:\\FCI\\Third Year\\Second Term\\SWE-2\\20140127\\SW Implementation\\src\\Emails.txt"));
             String str;
                str=br.readLine();
            while (str!=null) {
                if (str.equals(this.getMail())) {

                    return false;
                }

                str=br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public boolean checkSingingValdation () throws IOException {


    	BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("E:\\FCI\\Third Year\\Second Term\\SWE-2\\20140127\\SW Implementation\\src\\signin.txt"));
             String mail , pass ;
                mail=br.readLine();

            while (mail!=null) {
                pass=br.readLine();
                if (mail.equals(this.getMail()) && pass.equals(this.getPassword())) {

                    return true;
                }

                mail=br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;

    }

}
