import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Control {
		public int requestToCheckRegistration(String kind,String fname,String lname ,String Email, String Password,int age,String gender) throws IOException
		{
			if(kind.equals("Student")){

				Student s=new Student(fname,lname,Password,Email ,gender,age);
				Boolean correct;
				correct=s.correction();
				if(correct){
					Boolean validate;
					validate=s.RequestToCheckRegistrationValidation();
					if(validate){

						System.out.println("Registeration Success");
						return 1;
					}
					else {
						System.out.println("Registration failed");
						return 2;
					}
				}
				else{
					System.out.println("Enter information again");
					return 3;
				}	
		}

		 else if (kind.equals("Teacher") ) {
				
			 Teacher t =new Teacher (fname,lname,Password,Email ,gender,age);
				Boolean correct;
				correct=t.correction();
				if(correct){
					Boolean validate;
					validate=t.RequestToCheckRegistrationValidation();
					if(validate){
						System.out.println("Registeration Success");
						return 5;
					}
					else {
						System.out.println("Registration failed");
						return 2;
					}
				}
				else{
					System.out.println("Enter information again");
					return 3;
				}
				
		}	
				return 0 ;
			}

		
		public void addmail(String Email){
			try{
			 File file =new File("E:\\FCI\\Third Year\\Second Term\\SWE-2\\20140127\\SW Implementation\\src\\Emails.txt");
             if(!file.exists()){
                 file.createNewFile();
              }
             FileWriter fw = new FileWriter(file,true);
             BufferedWriter bw = new BufferedWriter(fw);
             
             bw.write(Email);
             System.out.println(Email+" Email");
             bw.newLine();
             bw.close();
			}
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
		}
		
		public void addAll(String fname,String lname ,String Email, String Password,int age,String gender){
			Student s=new Student ();
		    s.setfName(fname);
			s.setlName(lname);
			s.setAge(age);
			s.setMail(Email);
			s.setPassword(Password);
			s.setGender(gender);

			try{
			 File fileData =new File("E:\\FCI\\Third Year\\Second Term\\SWE-2\\20140127\\SW Implementation\\src\\data.txt");
             if(!fileData.exists()){
            	 fileData.createNewFile();
              }
             FileWriter fw = new FileWriter(fileData,true);
             BufferedWriter bw = new BufferedWriter(fw);
             
             bw.write(s.getfName());
             bw.newLine();
             System.out.println(s.getfName()+"  Fname");
             bw.write(s.getlName());
             bw.newLine();
             bw.write(""+s.getAge());
             bw.newLine();
             bw.write(s.getMail());
             bw.newLine();
             bw.write(s.getPassword());
             bw.newLine();
             bw.write(s.getGender());
             bw.newLine();
             bw.close();
			}
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
		
			try{
				 File fileData =new File("E:\\FCI\\Third Year\\Second Term\\SWE-2\\20140127\\SW Implementation\\src\\signin.txt");
	             if(!fileData.exists()){
	            	 fileData.createNewFile();
	              }
	             FileWriter fw = new FileWriter(fileData,true);
	             BufferedWriter bw = new BufferedWriter(fw);
	             
	             bw.write(s.getMail());
	             bw.newLine();
	             bw.write(s.getPassword());
	             bw.newLine();
	            
	             bw.close();
				}
	        catch(IOException ioe)
	        {
	            System.err.println("IOException: " + ioe.getMessage());
	        }
			
		}
		
		
		public void Taddmail(String Email){
			try{
			 File file =new File("E:\\FCI\\Third Year\\Second Term\\SWE-2\\20140127\\SW Implementation\\src\\Temails.txt");
             if(!file.exists()){
                 file.createNewFile();
              }
             FileWriter fw = new FileWriter(file,true);
             BufferedWriter bw = new BufferedWriter(fw);
             
             bw.write(Email);
             System.out.println(Email+" Email");
             bw.newLine();
             bw.close();
			}
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
		}
		
		public void TaddAll(String fname,String lname ,String Email, String Password,int age,String gender){
			Teacher t =new Teacher ();
		    t.setfName(fname);
			t.setlName(lname);
			t.setAge(age);
			t.setMail(Email);
			t.setPassword(Password);
			t.setGender(gender);

			try{
			 File fileData =new File("E:\\FCI\\Third Year\\Second Term\\SWE-2\\20140127\\SW Implementation\\src\\Tdata.txt");
             if(!fileData.exists()){
            	 fileData.createNewFile();
              }
             FileWriter fw = new FileWriter(fileData,true);
             BufferedWriter bw = new BufferedWriter(fw);
             
             bw.write(t.getfName());
             bw.newLine();
             System.out.println(t.getfName()+"  Fname");
             bw.write(t.getlName());
             bw.newLine();
             bw.write(""+t.getAge());
             bw.newLine();
             bw.write(t.getMail());
             bw.newLine();
             bw.write(t.getPassword());
             bw.newLine();
             bw.write(t.getGender());
             bw.newLine();
             bw.close();
			}
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
			
			try{
				 File fileData =new File("E:\\FCI\\Third Year\\Second Term\\SWE-2\\20140127\\SW Implementation\\src\\signin.txt");
	             if(!fileData.exists()){
	            	 fileData.createNewFile();
	              }
	             FileWriter fw = new FileWriter(fileData,true);
	             BufferedWriter bw = new BufferedWriter(fw);
	             
	             bw.write(t.getMail());
	             bw.newLine();
	             bw.write(t.getPassword());
	             bw.newLine();
	            
	             bw.close();
				}
	        catch(IOException ioe)
	        {
	            System.err.println("IOException: " + ioe.getMessage());
	        }
		
		}
		  public boolean ReqSingingValdation (String kind,String email , String password ) throws IOException {
		    	Student s=new Student();
		    	Teacher t=new Teacher();
		    	Boolean valid=false;
			  if(kind.equals("Student")){
		    		s.setMail(email);
		    		s.setPassword(password);
		    		valid=s.checkSingingValdation();
		    	}
		    	
			  if(kind.equals("Teacher")){
		    		t.setMail(email);
		    		t.setPassword(password);
		    		valid=t.checkSingingValdation();
		    	}
			  
			  return valid;

		    }
		  
		  public void ReqTostartGame () {
			  
			  game g = new game () ;
			  g.play() ;
		  }
		  
		  public void ReqToAddGame () {
			  String Tname , Gname ;
			  Scanner s=new Scanner(System.in);
			  System.out.println("enter your name :") ;
			  Tname = s.nextLine() ;
			  System.out.println("enter game name :") ;
			  Gname = s.nextLine() ;
			  game g = new game () ;
			  g.addgame(Tname , Gname) ;

		  }
}





