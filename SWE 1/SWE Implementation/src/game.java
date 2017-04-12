/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author win7
 */
import java.util.Scanner;
import java.util.Vector;


public class game {

    public void MCQgame1math (  ){
        int answer;
        int score =0 ;
        System.out.println("   MCQ game 1 in Math  ");
        System.out.println("Q1 : 4 + 6 = {12 , 23,10,4}  ");
         Scanner s =new Scanner(System.in);
         System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 10 )
                    {     score++;
                        System.out.println(" correct answer :D your score is " + score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        System.out.println("Q2 : 12 * 2 = { 24 , 25 , 12 , 14} ");
        System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 24 )
                    {
                         score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;
                    }
        
        System.out.println("Q3 : 25 / 5 = { 6 , 5 , 12 , 4} ");
        System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 5 )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
         System.out.println("Q4 : 20 - 5 = { 15 , 25 , 120 , 14} ");
        System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 15 )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
    }
    
    
    ////////////////// game 2 in math  MCQ
    public void MCQgame2math (  ){
        int answer;
        int score =0 ;
        System.out.println("  MCQ game 2 in Math  ");
        System.out.println("Q1 : 4 ^ 2 = {16 , 8 ,10,4}  ");
         Scanner s =new Scanner(System.in);
         System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 8 )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        System.out.println("Q2 : 9 * 9 = { 24 , 81 , 12 , 14} ");
        System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 81 )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        
        System.out.println("Q3 : 250 / 5 = { 60 , 50 , 129 , 499} ");
        System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 50 )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
         System.out.println("Q4 : 30 + 12 = { 44 , 42 , 120 , 14} ");
        System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 42 )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
    }
    
     // game 1  in math T or F
    
    public void TFgame1math (  ){
        Scanner in=new Scanner(System.in); 
        String answer;
        int score =0 ;
        System.out.println("   T Or F game 1 in Math  ");
        System.out.println("Q1 : 4 + 6 = 10  ");
         Scanner s =new Scanner(System.in);
         System.out.println("your answer is ? "); 
                 answer = in.nextLine();
                    if(answer.equals("T") )
                    {
                        score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else if (answer.equals("F") ){
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        System.out.println("Q2 : 12 * 2 = 23 ");
        System.out.println("your answer is ? ");
          answer = in.nextLine();
         
                if(answer.equals("F") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else if (answer.equals("T") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        
        System.out.println("Q3 : 25 / 5 = 4 ");
        System.out.println("your answer is ? ");
	  answer = in.nextLine();
                    if(answer.equals("F") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                     else if (answer.equals("T") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
         System.out.println("Q4 : 20 - 5 = 15 ");
        System.out.println("your answer is ? ");
	 answer = in.nextLine();
                    if(answer.equals("T") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else if (answer.equals("F")) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
    }
    // game 2  in math T or F
    
    public void TFgame2math (  ){
        Scanner in=new Scanner(System.in); 
        String answer;
        int score =0 ;
        System.out.println("   T Or F game 2 in Math  ");
        System.out.println("Q1 : 2 * 2 = 8  ");
         Scanner s =new Scanner(System.in);
         System.out.println("your answer is ? "); 
                 answer = in.nextLine();
                    if(answer.equals("F" ))
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else if (answer.equals("T") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        System.out.println("Q2 : 12 + 2 = 23 ");
        System.out.println("your answer is ? ");
          answer = in.nextLine();
                    if(answer.equals("F") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else if (answer.equals("T") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        
        System.out.println("Q3 : 25 / 25 = 1 ");
        System.out.println("your answer is ? ");
	  answer = in.nextLine();
                    if(answer.equals("T") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else if (answer.equals("F") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
         System.out.println("Q4 : 20 - 5 = 10 ");
        System.out.println("your answer is ? ");
	 answer = in.nextLine();
                    if(answer.equals("F") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                     else if (answer.equals("T") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
    }
    
    public void MCQgame1science (  ){
        int answer;
        int score =0 ;
        System.out.println("   MCQ game 1 in science  ");
        System.out.println("Q1 : How many oxygen atoms in ozone ? = {1,2,3,4}  ");
         Scanner s =new Scanner(System.in);
         System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 3 )
                    {     score++;
                        System.out.println(" correct answer :D your score is " + score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        System.out.println("Q2 : How many elements discovered till now ? = { 112,120,135,210} ");
        System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 112 )
                    {
                         score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        
        System.out.println("Q3 : How many colors in rainbow ? = {7,5,8,10} ");
        System.out.println("your answer is ? ");
		answer = s.nextInt();
                    if(answer == 7 )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
         
    }
    
    
    
    public void MCQgame2science (  ){
    	   int answer;
           int score =0 ;
           System.out.println("   MCQ game 1 in science  ");
           System.out.println("Q1 : How many oxygen atoms in ozone ? = {1,2,3,4}  ");
            Scanner s =new Scanner(System.in);
            System.out.println("your answer is ? ");
   		answer = s.nextInt();
                       if(answer == 3 )
                       {     score++;
                           System.out.println(" correct answer :D your score is " + score );
                       }
                       else {
                           System.out.println(" wrong answer :( your score "+ score );
                           return;

                       }
           System.out.println("Q2 : How many elements discovered till now ? = { 112,120,135,210} ");
           System.out.println("your answer is ? ");
   		answer = s.nextInt();
                       if(answer == 112 )
                       {
                            score++;
                           System.out.println(" correct answer :D your score is "+ score );
                       }
                       else {
                           System.out.println(" wrong answer :( your score "+ score );
                           return;

                       }
           
           System.out.println("Q3 : How many colors in rainbow ? = {7,5,8,10} ");
           System.out.println("your answer is ? ");
   		answer = s.nextInt();
                       if(answer == 7 )
                       { score++;
                           System.out.println(" correct answer :D your score is "+ score );
                           return;

                       }
                       else {
                           System.out.println(" wrong answer :( your score "+ score );
                           return;

                       }
            
    }
    
     // game 1  in science T or F
    
    public void TFgame1science (  ){
        Scanner in=new Scanner(System.in); 
        String answer;
        int score =0 ;
        System.out.println("   T Or F game 1 in science  ");
        System.out.println("Q1 : Number of oxygen atoms in ozone is 3 ?  ");
         Scanner s =new Scanner(System.in);
         System.out.println("your answer is ? "); 
                 answer = in.nextLine();
                    if(answer.equals("T") )
                    {
                        score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else if (answer.equals("F") ){
                        System.out.println(" wrong answer :( your score "+ score );
                        return; 
                     
                    }
        System.out.println("Q2 : Number of elements discovered till now is 120 ? ");
        System.out.println("your answer is ? ");
          answer = in.nextLine();
         
                if(answer.equals("F") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else if (answer.equals("T") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        
        System.out.println("Q3 : Number of colours in rainbow is 8 ? ");
        System.out.println("your answer is ? ");
	  answer = in.nextLine();
                    if(answer.equals("F") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                     else if (answer.equals("T") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
         
    }
    // game 2  in science T or F
    
    public void TFgame2science (  ){
        Scanner in=new Scanner(System.in); 
        String answer;
        int score =0 ;
        System.out.println("   T Or F game 1 in science  ");
        System.out.println("Q1 : Number of oxygen atoms in ozone is 3 ?  ");
         Scanner s =new Scanner(System.in);
         System.out.println("your answer is ? "); 
                 answer = in.nextLine();
                    if(answer.equals("T") )
                    {
                        score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                    else if (answer.equals("F") ){
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        System.out.println("Q2 : Number of elements discovered till now is 120 ? ");
        System.out.println("your answer is ? ");
          answer = in.nextLine();
         
                if(answer.equals("F") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                        return;

                    }
                    else if (answer.equals("T") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
        
        System.out.println("Q3 : Number of colours in rainbow is 8 ? ");
        System.out.println("your answer is ? ");
	  answer = in.nextLine();
                    if(answer.equals("F") )
                    { score++;
                        System.out.println(" correct answer :D your score is "+ score );
                    }
                     else if (answer.equals("T") ) {
                        System.out.println(" wrong answer :( your score "+ score );
                        return;

                    }
       }  
public void play(  ){
	System.out.println(" choose subject :");
	System.out.println(" 1- math :");
	System.out.println(" 2- science  :");
	   int su;
	   Scanner s =new Scanner(System.in);
		su = s.nextInt();
		if (su == 1){
			System.out.println(" choose category :");
			System.out.println(" 1- true or false :");
			System.out.println(" 2- multi choice :");
			int ca;
			   Scanner s1 =new Scanner(System.in);
				ca = s1.nextInt();
				if(ca == 1){
					System.out.println(" 1- game 1 && 2-game 2  ");
					 int g;
                                             g = s1.nextInt();
                                             if(g == 1)
                                               TFgame1math();
                                             else if (g == 2)
                                                 TFgame2math();
                                             else 
                                                 System.out.println(" not found !");
                                             
				}
					else if   (ca == 2){
				 System.out.println(" 1- game 1 && 2-game 2  ");
					 int g;
                                             g = s1.nextInt();
                                             if(g == 1)
                                               MCQgame1math();
                                             else if (g == 2)
                                                 MCQgame2math();
                                             else 
                                                 System.out.println(" not found !");
		} }
                    else if  (su == 2){
                 
                    	System.out.println(" choose category :");
            			System.out.println(" 1- true or false :");
            			System.out.println(" 2- multi choice :");
            			int caa;
            			   Scanner s1 =new Scanner(System.in);
            				caa = s1.nextInt();
            				if(caa == 1){

            					System.out.println(" 1- game 1 && 2-game 2  ");
            					 int g;
                                                         g = s1.nextInt();
                                                         if(g == 1)
                                                           TFgame1science();
                                                         else if (g == 2)
                                                             TFgame2science();
                                                         else 
                                                             System.out.println(" not found !");
                                                         
            				}
            					else if   (caa == 2){
            				 System.out.println(" 1- game 1 && 2-game 2  ");
            					 int g;
                                                         g = s1.nextInt();
                                                         if(g == 1)
                                                           MCQgame1science();
                                                         else if (g == 2)
                                                             MCQgame2science();
                                                         else 
                                                             System.out.println(" not found !");
		}
                    }
                    }




public void addgame (String teacherName , String gameName )
{
    System.out.println(" choose Suitable Subject for your game : ");
    System.out.println("1- Math  2- science ");
     int sg , gg ,numQ;
     Vector<String> game = new Vector ();
     String Q , Answers,Manswers;
     Vector <String> modelAnswers = new Vector() ;
    
   Scanner s =new Scanner(System.in);
	sg = s.nextInt();
       System.out.println("choose suitable category for your game :");
       System.out.println("1- T Or F   2-MCQ");
                  gg = s.nextInt();
         System.out.println("the game Name is :" + gameName );
        if (gg == 1)//True and false
        {
            System.out.println(" enter the numbers of Questions ");
            numQ = s.nextInt();
            String ans;
            for(int i= 0 ; i< numQ ; i++)
            { System.out.println("Enter The Question");
            if(i == 0){
                s.nextLine();
            } 
                Q = s.nextLine();
                game.add(Q);
      
            System.out.println("please enter the correct answer for this question :");
                Manswers = s.nextLine();
                modelAnswers.add(Manswers);
             
            } 
            System.out.println("game is added successfully :D ");

        }
        else if (gg == 2 )//mcq
      
        	 System.out.println(" enter the numbers of Questions ");
       
             numQ = s.nextInt();
             String ans;
             for(int i= 0 ; i< numQ ; i++)
             {   
            	 System.out.println("Enter The Question");
                 if(i == 0){
                s.nextLine();
                     } 
                 Q = s.nextLine();
                 game.add(Q);
                 s.nextLine();
                 for(int j=0;j<4;j++){
                 System.out.print("Enter The chooices");
                 ans=s.nextLine();
                 game.add(ans);
                 }
             
             System.out.println("please enter the correct answer for this question :");
            
                 Manswers = s.nextLine();
                 modelAnswers.add(Manswers);
                 
              
             }
             System.out.println("game is added successfully :D ");


}

} 