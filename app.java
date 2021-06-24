package mysql;
import java.sql.*;
import java.util.Scanner;
import java.io.*;
public class sql
{
public static void main (String args[]) throws IOException
{
Scanner sc=new Scanner (System.in);
Connection con =null;
Statement stmt=null;
int choice_variable;
// Declare common
variables if any
try{
// Load the driver class
Class.forName("com.mysql.jdbc.Driver");
// Create the connection object by specifying the connection url, userid and
password
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","
mymacbook0588#");
// Create the statement object
stmt=con.createStatement();
do
{ 
System.out.println("\n\n*** Banking Management System***");
// Display the menu
System.out.println("Enter your choice (1-9):");
// Accept user's choice
 choice_variable=sc.nextInt();
switch(choice_variable)
{
case 1:
// Display customer records break;
int m=0;
String sqlstmt="select * from customer order by CUST_NO";
ResultSet rs=stmt.executeQuery(sqlstmt);
while(rs.next())
{
m++;
 System.out.print(String.format("%-6s\t",rs.getString(1)));
System.out.print(String.format("%-20s\t",rs.getString(2)));
System.out.print(String.format("%-11s\t",rs.getString(3)));
System.out.print(String.format("%-10s\n",rs.getString(4)));
}
System.out.println(m+ "row displayed");
break;
case 2:
sc.nextLine();
System.out.println("enter the cust_no of the customer");
String cno=sc.nextLine();
System.out.println("enter the name of the customer");
String name=sc.nextLine();
System.out.println("enter the phone no. of the customer");
long phn=Long.parseLong(sc.nextLine());
System.out.println("enter the city of the customer"); 
String city=sc.nextLine();
String insstr="insert into CUSTOMER
values('"+cno+"','"+name+"',"+phn+",'"+city+"')";
// sql query
int n=stmt.executeUpdate(insstr);
// n returns the number of rows added
System.out.println(n+" \nrows inserted\n\n");
break;
// Add customer record
// Accept input for each column from user break;
case 3:
sc.nextLine();
System.out.println("enter the cust_no of the customer");
String cn=sc.nextLine(); // readLine() is used to take string
type user input String delstr="delete from CUSTOMER
where CUST_NO='"+cn+"'";// sql query
n=stmt.executeUpdate(delstr);// n returns the number of
rows added System.out.println("\n"+n+" rows deleted\n");
break;
// Delete customer record
// Accept customer number from user
case 4:
sc.nextLine();
System.out.println("enter the cust_no of the customer");
String cn4=sc.nextLine();
String updstr="";// since it is getting modified inside switch case
// Update customer record
// Accept customer number from user 
System.out.println("Enter 1: For Name "
+ "2: For Phone no "
+ "3: For City to update:");
int choice_variable_1=sc.nextInt();
switch(choice_variable_1)
{
case 1:
sc.nextLine();
// Update customer's name
//display_record();
System.out.println("enter the name of the
customer"); String name4=sc.nextLine();
updstr="update CUSTOMER set NAME='"+name4+"' where
CUST_NO='"+cn4+"'";// sql query break;
//stmt.close();
case 2:
sc.nextLine();
System.out.println("enter the phone no. of the
customer"); long
phn4=Long.parseLong(sc.nextLine());
updstr="update CUSTOMER set PHONE_NO="+phn4+" where
CUST_NO='"+cn4+"'";// sql query break; 
// Update customer's phone number
case 3:
sc.nextLine();
// Update customer's city break;
System.out.println("enter the city of the
customer"); String city4=sc.nextLine();
updstr="update CUSTOMER set CITY='"+city4+"' where CUST_NO='"+cn4+"'";//
sql query
break;
}
n=stmt.executeUpdate(updstr);// n returns the number of rows added
System.out.println("\n"+n+" rows updated\n"); break; case 5:
sc.nextLine();
System.out.println("enter the cust_no of the customer");
String cn5=sc.nextLine();
String sqlstr5="select * from CUSTOMER natural join DEPOSITOR natural join
ACCOUNT natural join BRANCH where CUST_NO='"+cn5+"'";
ResultSet rs5=stmt.executeQuery(sqlstr5);// ResultSet reference is a matrix
System.out.println("\nCUST_NO\tNAME\t\tPHONE_NO\tCITY\tACC_NO\tTYPE\t
BALANCE\t\tBR_CODE\ t\tBRANCH_NAME\t\tBRANCH_CITY\t\n");
while(rs5.next())
{
System.out.print(rs5.getString("cust_no")+"\t");// these indices are the column
number of the column 
System.out.print(rs5.getString("name")+"\t");
System.out.print(rs5.getString("phone_no")+"\t");
System.out.print(rs5.getString("city")+"\t");
System.out.print(rs5.getString("account_no")+"\t");
System.out.print(rs5.getString("type")+"\t");
System.out.print(rs5.getString("balance")+"\t\t");
System.out.print(rs5.getString("branch_code")+"\t\t");
System.out.print(rs5.getString("branch_name")+"\t\t");
System.out.println(rs5.getString("branch_city")+"\n");
}
break;
case 6:// Display loan details
// Accept customer number from user
// Display the number of loans the customer has or // Congratulation if he
customer has no loan break; int z=0; sc.nextLine();
System.out.println("enter the cust_no of the customer");
String cn6=sc.nextLine();
String sqlstr6="select * from CUSTOMER natural join LOAN natural join BRANCH
where CUST_NO='"+cn6+"'";
ResultSet rs6=stmt.executeQuery(sqlstr6);// ResultSet reference is a matrix
System.out.println("\nCUST_NO\t\tNAME\t\tPHONE_NO\tCITY\t\tLOAN_NO\t\t"
+
"AMOUNT\t\tBR_CODE\t\tBRANCH_NAME\t\tBRANCH_CITY\n")
; while(rs6.next())
{ 
System.out.print(rs6.getString("cust_no")+"\t\t");// these indices are the column
number of the column
System.out.print(rs6.getString("name")+"\t");
System.out.print(rs6.getString("phone_no")+"\t");
System.out.print(rs6.getString("city")+"\t\t");
System.out.print(rs6.getString("loan_no")+"\t\t");
System.out.print(rs6.getString("amount")+"\t\t");
System.out.print(rs6.getString("branch_code")+"\t\t");
System.out.print(rs6.getString("branch_na
me")+"\t\t");
System.out.println(rs6.getString("branch_ci
ty")+"\n"); z++;
}
if(z==0)
System.out.println("congrats !!! you
have no loan\n\n"); break; case 7:
sc.nextLine();
//Deposit Money to an Account
System.out.println("enter the account number");
String acc7=sc.nextLine();
System.out.println("enter the amount to be
deposited"); int
amt7=Integer.parseInt(sc.nextLine());
String sqlstr71="select BALANCE from ACCOUNT where
ACCOUNT_NO='"+acc7+"'"; 
ResultSet rs71=stmt.executeQuery(sqlstr71);// ResultSet
reference is a matrix System.out.print("Previous balance
is: \t"); while(rs71.next())
System.out.println(rs71.getString("balance")+"\n");
updstr="update ACCOUNT set BALANCE=BALANCE+"+amt7+" where
ACCOUNT_NO='"+acc7+"'";// sql query
n=stmt.executeUpdate(updstr);// n returns the number of rows added
//System.out.println("\n"+n+" rows updated\n");
String sqlstr72="select BALANCE from ACCOUNT where
ACCOUNT_NO='"+acc7+"'";
ResultSet rs72=stmt.executeQuery(sqlstr72);// ResultSet
reference is a matrix System.out.print("Updated balance is:
\t"); while(rs72.next())
System.out.println(rs72.getString("balance
")+"\n"); break;
case 8:
sc.nextLine();//Withdraw
money //Withdraw
Money from an Account
int bal8=0;
System.out.println("enter the account number");
String acc8=sc.nextLine(); 
System.out.println("enter the amount to be
withdraw"); int
amt8=Integer.parseInt(sc.nextLine());
String sqlstr81="select BALANCE from ACCOUNT where
ACCOUNT_NO='"+acc8+"'";
ResultSet rs81=stmt.executeQuery(sqlstr81);// ResultSet
reference is a matrix System.out.print("Previous balance is:
\t"); while(rs81.next())
{
System.out.println(rs81.getString("balance")+"\n");
bal8=Integer.parseInt(rs81.getString("balance"));
}
if(bal8>=amt8)
{
updstr="update ACCOUNT set BALANCE=BALANCE-"+amt8+" where
ACCOUNT_NO='"+acc8+"'";// sql query
n=stmt.executeUpdate(updstr);// n returns the number of rows added
//System.out.println("\n"+n+" rows updated\n");
String sqlstr82="select BALANCE from ACCOUNT where
ACCOUNT_NO='"+acc8+"'";
ResultSet rs82=stmt.executeQuery(sqlstr82);// ResultSet
reference is a matrix System.out.print("Updated balance is:
\t"); while(rs82.next())
System.out.println(rs82.getString("balance")+"\n");
}
else 
System.out.println("Insufficient
Balance !!!!!\n"); break; case 9:
//exit case stmt.close();
con.close();
System.out.println("\nT
hank you\n");
System.exit(0); break;
default:
// Handle wrong choice of option
System.out.println("\nWrong choice\n");
}
}
while(t
rue); }
//try
closing
catch(E
xceptio
n e)
{ // Handling exception}
}
}
}
