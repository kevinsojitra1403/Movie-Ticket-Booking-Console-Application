// imports
import java.util.*;
import java.sql.*;
//main class 
class mba
{
// creating a register method	
	public static void register()
	{		
// try block , it prompts user to enter required details for registration		
		try {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String name= sc.next();
		System.out.print("Enter your username: ");
		String uname= sc.next();
		System.out.print("Enter your phone number: ");
		long phone= sc.nextLong();
		System.out.print("Enter your email: ");
		String email= sc.next();
		System.out.print("Enter your password: ");
		String pswd= sc.next();
//db connecction 		
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","system","root");
	    Statement stmt = con.createStatement();
	    stmt.executeUpdate("insert into registration(username, name, phone, email, password) values('"+uname+"','"+name+"',"+phone+",'"+email+"','"+pswd+"')"); 
		System.out.println("Registration sucessful");
	     }
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
// login method for customer 	
	public static int login()
	{
		System.out.println(" 1. Signup ");
		System.out.println(" 2. Login ");
		System.out.print("select your option: ");
        Scanner sc = new Scanner(System.in); 
        int cus =sc.nextInt();
        int stat = 0;       
        if(cus==1)
        {
// calling register method     
        	mba m = new mba();
        	m.register();
        }
        if(cus==2) 
        {
        	try 
        	{
        	System.out.println("Enter the username: ");
        	String username = sc.next();
            System.out.println("Enter the Password: ");
            String pswd = sc.next();
  		    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","system","root");
		    Statement stmt = con.createStatement();
//after user enters the credentials it checks these credentials if they exist in registration table or not 		    
		    ResultSet r = stmt.executeQuery("select * from registration");		    
		    while(r.next())
		    {
		    	String user = r.getString(2);
		        String psswd = r.getString(6);  
		        if(username.equals(user) && pswd.equals(psswd))
		        {
		        	System.out.println("login succesfull...");
		        	stat = 1;
		        }		        		      
		    }
            }
        
        catch(Exception e)
        {
        	System.out.println(e);
        }
        }
        return stat;
	     }
//main function 
	public static void main(String []args)
	{
		try 
		{
		  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","system","root"); 
		  Statement stmt;
//creating object of demo class 
		  Demo d = new Demo();  
//creating object of scanner class 
		  Scanner sc=new Scanner(System.in);
		  int status=1;
		  do 
		 {
			System.out.print("####Movie Booking Application#### \n \n");
			System.out.println("  1. Customer ");
			System.out.println("  2. Admin ");
			System.out.print("Kindly enter your choice: ");
			int ch = sc.nextInt();
			switch(ch)
			{
//Customer  			
			case 1:
				int login = login();
				if(login==1) 
				{
					d.getname();
				}
				else
				{
					continue;
				}
			    break;
//admin 			
			 case 2:
				System.out.print("Enter Username: ");
				String usrn = sc.next();
				System.out.print("Enter your Password: ");
				String pswd = sc.next();	
				if(usrn.equals("kevin") && pswd.equals("admin"))
				{
					System.out.println("1. Add Movie");
					System.out.println("2. Update Movie");
					System.out.println("3. Delete");
					System.out.print("Kindly enter your choice: ");
					int ach = sc.nextInt();
					switch(ach)
					{
// to add movie 					
					case 1:					
					      System.out.print("Enter the movie name: ");
					      String mname = sc.next();
					      System.out.print("Show Date: ");
					      String sdate = sc.next();
					      System.out.print("Show Time: ");
					      String stime = sc.next();
					      System.out.print("Price: ");
					      float mp = sc.nextFloat();
					      System.out.print("available Seat: ");
					      int ms = sc.nextInt();
					      System.out.print("Theatre Name: ");
					      String tname = sc.next();					  
					      stmt = con.createStatement();	
//inserting into movie table					      
					      stmt.executeUpdate("insert into movie values('" +mname + "','"+sdate +"','"+stime+"',"+mp +","+ms+",'"+tname+"')");
					      System.out.println("New show inserted succesfully!!!.."); 
					      break;
// updating movie 					  
					 case 2:
						  System.out.print("Enter the movie name: ");
						  String name = sc.next();
						  System.out.print("Show Date: ");
						  String date = sc.next();
						  System.out.print("Show Time: ");
						  String time = sc.next();
						  System.out.print("Price: ");
						  float p = sc.nextFloat();
						  System.out.print("available Seat: ");
						  int s = sc.nextInt();
						  System.out.print("Theatre Name: ");
						  String tn = sc.next();				
						  stmt = con.createStatement();
						  //System.out.println("updated successfully");
// executing update query to update movie from movie table 						 
						  int u=stmt.executeUpdate("update movie set name='" +name + "',showdate='"+date +"',showtime='"+time+"',price="+p +",seat="+s+",theatrename='"+tn+"' where name='"+name+"'");
						  System.out.println(u);
						  if(u>0) {
	                    	  System.out.println("updated successfully");
	                      }
						  break;
					
//Deleting movie			
				     case 3:
						 System.out.print("Enter the movie name: ");
						 String mn = sc.next();						  
						 stmt = con.createStatement();
//executing delete query in order to delete movie from movie table
						 stmt.executeUpdate("delete from  movie where name='"+mn+"'");
	                     break;     
					}
					break;
				}
				else
				{
					System.out.println("Invalid Credentials kindly enter correct username and password: ");
				}				
			    break;				
			}
			System.out.println("Do you want to continue(enter: 1 for yes and 0 for no )");
			status=sc.nextInt();
		}
		  while(status==1);
		   System.out.print("Successful");
		}
		catch(Exception e)
		{	
			System.out.println("Invalid Imput");
		  
		}
	}
}

//creating demo class
class Demo{
// creating object of scanner class		
	static Scanner sc = new Scanner(System.in);
// creating getname method		
		public static void getname() 
		{
			System.out.println("Welcome ");
			getmovie();
		}
// creating getmovie method
		public static void getmovie() 
		{		
			try {
	        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","system","root");
	        Statement stmt = con.createStatement();
	        System.out.println("Select the movie to watch: ");
//executing select query to show the movie name  to user 
			  ResultSet rs = stmt.executeQuery("select name from movie ");
			  while(rs.next()) {
			  System.out.print("Name of the movie  : "+rs.getString(1)+"\n");
			  }
// asking user to enter movie name from the above list in order to view details of that movie			 
	       System.out.println("kindly enter the movie name  from the available movies to view details: ");
	       String md = sc.next();
	        rs = stmt.executeQuery("select * from movie where name='"+md+"'");
	        int a=0;
	       while(rs.next())   
			 { System.out.print("Name of the movie: "+rs.getString(1)+"\n");
			   System.out.print("Theater Name: "+rs.getString(6)+"\n");
			   System.out.print("Total No of Tickets: "+rs.getInt(5)+"\n");
			   System.out.println("Total cost of tickets: "+rs.getInt(4)+"\n");
			   System.out.println("Show Date: "+rs.getString(2)+"\n"); 
//storing price of mentioned movie from movie table to integer a 			   
			   a=rs.getInt(4);
			 }
//declaring integer n which is number of tickets	        
			int n;
			System.out.print("do you want to book the ticket?");
			System.out.print(" 1.yes ");
			System.out.print(" 2. no ");
			int yn = sc.nextInt();
			if(yn == 1)
			{			
			System.out.println("How many seats you want? ");
			n = sc.nextInt();
			int [] arr = new int[n];
			
			 System.out.println("Please enter  seat numbers you want to book? "); 
			 for(int i=0; i<n; i++) 
			 { 
				 arr[i] = sc.nextInt();
			 }
			 
//here the total amount is calculated by multiplying number of tickets to actual price of movie tickets from movie table			
			int amount = n * a;
			System.out.println("Total amout to pay: "+amount);
			System.out.println("Please select your payment mode: ");
//inititalizing variabe mode for payment mode options			
			int mode = 1;
			while(mode ==1) {
				System.out.println("1. upi");
				System.out.println("2. cash");
				System.out.println("3. debit card");
				System.out.println("4. netbanking");
				 mode = sc.nextInt();
				switch(mode) {
				case 1:
					System.out.println("Welcome to upi");
				    break;
				case 2:
					System.out.println("kindly deposite the cash to the counter");
				    break;
				case 3:
					System.out.println("kindly endter the following debit card details");
				    break;
				case 4:
					System.out.println("kindly do the transaction carefully");
				    break;
				}
				System.out.println("Amount to be paid: "+amount);
				System.out.print("Enter the amount: ");
				int amountpay = sc.nextInt();
				
				if(amountpay == amount) {
					System.out.println("your payment is successfully: ");
					System.out.println("your seat has been booked succesfully: ");
					System.out.println("Shukriya: ");
					break;
				}
				else {
					System.out.println("Payment failed please try again..");
					break;
				}
			}
			}
			else 
			{
				System.out.println("Thank you for visiting");
				
			}
			}catch(Exception e) 
			{
	            System.out.println();
	        }
		}		    
	}