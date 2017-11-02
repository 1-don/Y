import java.lang.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class jason {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/prac1";

   static final String USER = "TEB72";
   static final String PASS = "";
   
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs=null;
	int ch,rn,sn,mark;
	String ename,eadd,eclass,dob;
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
   try{
      Class.forName("com.mysql.jdbc.Driver");


      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
	sql = "select * from Student";
do
{
System.out.println("\n\n**MENU**");
System.out.println("1.Select");
System.out.println("2.Insert");
System.out.println("3.Update");
System.out.println("4.Delete");
System.out.println("5.Count()");
System.out.println("6.sum()");
System.out.println("7.max()");
System.out.println("8.min()");
System.out.println("9.avg()");
System.out.println("10.Exit");
System.out.println("\n--------------------\nEnter your choice :");
ch=Integer.parseInt(input.readLine());

switch(ch)
{

      case 1:	
			sql = "SELECT *FROM Student ";
			rs = stmt.executeQuery(sql);
  	    		while(rs.next())
			{
      	
         			String a  = rs.getString("stud_name");
	         		//String date = rs.getString("DOB");
        	 		int b = rs.getInt("roll_no");
				int mk = rs.getInt("marks");
				System.out.print("\nROLLNO: " + b);
     				System.out.print(", NAME: " + a);
        	 		System.out.print(", Marks gain: " + mk);
			        
			}
      	  		rs.close();
			
	
	break;
	case 2:
	
		System.out.println("\nEnter Student Name:");
		ename = input.readLine();
		System.out.println("\nEnter Student RollNo:");
		rn = Integer.parseInt(input.readLine());
		System.out.println("\nEnter Student BirthDate:");
		dob = input.readLine();
		System.out.println("\nEnter Student Marks:");
		mark = Integer.parseInt(input.readLine());
	
		sql = "insert into Student" + " values("+ rn +",'"+ ename +"','" + dob +"'," + mark +");";
		System.out.println(sql);
		stmt.executeUpdate(sql);
		System.out.println("\nRecords inserted sucessfully");
		break;
	case 3:
		System.out.println("\nEnter RollNo to update:");
		rn = Integer.parseInt(input.readLine());
		System.out.println("Enter New Name:");
		ename = input.readLine();
		sql= "update Student set stud_name='"+ ename + "' where roll_no= "+ rn+";";
		stmt.executeUpdate(sql);
		System.out.println("\nTable Updated successfully");
		break;
	case 4:
		System.out.println("\nEnter RollNo to Delete:");
		rn = Integer.parseInt(input.readLine());
		sql="delete from Student where roll_no= "+rn;
		stmt.executeUpdate(sql);
		System.out.println("\nDelete successful!!!\n");
		break;
	case 5:
		sql="SELECT count(*) FROM Student; ";
		rs = stmt.executeQuery(sql);
		while(rs.next())
		{
		 String cnt = rs.getString(1);
		 System.out.print("Total number of students :"+cnt);
		}
		break;
	case 6:
		sql="SELECT SUM(marks) FROM Student";
		rs = stmt.executeQuery(sql);
		rs.next();
		String sum = rs.getString(1);
		System.out.println("Sum of marks gained by students :"+sum);
		break;


	case 7: 
		sql="SELECT * from Student where marks=(select MAX(marks) FROM Student)";
		rs = stmt.executeQuery(sql);
		System.out.println("Student having Maximum marks :\n\t\n");
		while(rs.next())
		{
		 int RollNo  = rs.getInt("roll_no");
		 String Name = rs.getString("stud_name");
		 int KT = rs.getInt("marks");
		System.out.println("\n\tRollNO:"+RollNo+"\t\tName:" +Name+"\tKT:"+KT+"\n");
		}
		break;

	case 8: 
		sql="SELECT * from Student where marks=(select MIN(marks) FROM Student)";
		rs = stmt.executeQuery(sql);
		System.out.println("Student having Minimum marks :\n\t\n");
		while(rs.next())
		{
		 int RollNo  = rs.getInt("roll_no");
		 String Name = rs.getString("stud_name");
		 int KT = rs.getInt("marks");
		System.out.println("\n\tRollNO:"+RollNo+"\t\tName:" +Name+"\tKT:"+KT+"\n");
		}
		break;
	case 9:
		sql="SELECT avg(marks) FROM Student; ";
		rs = stmt.executeQuery(sql);
		System.out.println("Average :");
		while(rs.next())
		{
		 String avg = rs.getString(1);
		 System.out.print(avg);
		}
		break;
	
	case 10:
		System.exit(0);
		break;
		}
	}while(ch!=10);	

      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){

      se.printStackTrace();
   }catch(Exception e){

      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("\nGoodbye!");
}}


