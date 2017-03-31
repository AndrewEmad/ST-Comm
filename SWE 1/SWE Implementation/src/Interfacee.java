import java.io.IOException;
import java.util.Scanner;


public class Interfacee {
	
	public void Startinterface() throws IOException{
		System.out.println("Hello to online Educational Games ");
		System.out.println("choose : ");
		System.out.println("1- student ") ;
		System.out.println("2- teacher ") ;
		int x = 0 ;
		Scanner s=new Scanner(System.in);
		x=s.nextInt();
	    
		RegOrSign(x);
	    
		
	}
	
	public void RegOrSign(int kind) throws IOException{
	    	System.out.println("choose 1 to signin :") ;
	    	System.out.println("choose 2 to Registeration :") ;
	    	int x = 0 ;
			Scanner s=new Scanner(System.in);
			x=s.nextInt();
			if(x==1){
				SigninForm(kind);
			}
			else if(x==2){
				RegisterationForm(kind);
			}
		    
	}
	
	
	public void RegisterationForm(int kind) throws IOException{
		Scanner in=new Scanner(System.in);
		Boolean flag=true;
		
		while(flag){
		String fname,lname,password,Email,gender;
		int age;
		System.out.println("Enter first name : ");
		fname=in.nextLine();
		
		System.out.println("Enter last name : ");
		lname=in.nextLine();
		
		System.out.println("Enter Email : ");
		Email=in.nextLine();
		System.out.println("Enter password : ");
		password=in.nextLine();
		
		System.out.println("Enter gender : ");
		gender=in.nextLine();
		
		System.out.println("Enter age : ");
		age=in.nextInt();
		in.nextLine();
		Control C=new Control();
		int option;

		if (kind==1){
			
			option=C.requestToCheckRegistration("Student", fname, lname, Email, password, age, gender);
			if(option==1){
				
				C.addmail(Email);
				C.addAll(fname, lname, Email, password, age, gender);
				flag=false;

			}
			else if(option ==2 || option ==3){
				flag =true;
			}
		}
		else if(kind==2){	
			
			option=C.requestToCheckRegistration("Teacher", fname, lname, Email, password, age, gender);
			if(option==5){
				C.Taddmail(Email);
				C.TaddAll(fname, lname, Email, password, age, gender);
				flag=false;
				
			}
			else if(option ==2 || option ==3){
				flag =true;
			}
		}
		
			
			}
		}
	
	public void GameInterface () {
		
		Control c = new Control () ;
		c.ReqTostartGame() ;
	}
	
	public void addInterface () {
		Control c = new Control () ;
		c.ReqToAddGame() ;
		
	} 
		
		public void SigninForm(int kind) throws IOException{
			Scanner in=new Scanner(System.in);
			String Email,password;
			Boolean flag=false;
			Control c=new Control();
			while(!flag){
				System.out.println("Enter Email : ");
				Email=in.nextLine();	
				System.out.println("Enter password : ");
				password=in.nextLine();
				if(kind==1){
					//student
					flag=c.ReqSingingValdation("Student", Email, password);
					if(flag){
						System.out.println("Signing in Successful");
						System.out.println("play game press 1 ") ;
						System.out.println("sign out press 2 ") ;
						//Scanner s=new Scanner(System.in); commented for testing
						int x = in.nextInt() ; //changed from s.nextInt() to in.nextInt()
						if (x==1) {
						GameInterface () ; }
						else 
							return ;
					}
					else
						System.out.println("Sign in Faild");

				}
				if(kind==2){
					//teacher
					flag=c.ReqSingingValdation("Teacher", Email, password);
					if(flag){
						//interface Add
						System.out.println("Signing in Successful");
						System.out.println("add game press 1 ") ;
						System.out.println("sign out press 2 ") ;
						//Scanner s=new Scanner(System.in); commented for testing
						int x = in.nextInt() ; //changed from s.nextInt() to in.nextInt()
						if (x==1) {
							addInterface(); 
							}
						else 
							return ;

					}
					else
						System.out.println("Sign in Faild");
				}
				
				
		}
		}
	}



