// login database,table details  contains  1.column username(String), 2.column password(String)
import java.sql.*;
import java.util.*;
class Login
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","rootpasswordgiven");
            st = con.createStatement();
            int c=0;
            int ch =0;
            do
            {
            System.out.println("enter 1:login \n 2:signup \n 3:quit  :");
                 ch=sc.nextInt();
                switch(ch)
                {
                    case 1:login(rs,st,sc);
                            break;
                    case 2:signup(rs,st,sc);
                            break;
                    case 3:break;
                    default :System.out.println("enter any choice 1:login \n 2:signup \n 3:quit  :");
                            break;
                }
                

            }while(ch!=3);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void signup(ResultSet rs,Statement st,Scanner sc)throws Exception
    {
        System.out.println("enter name :");
        String un= sc.next();
        System.out.println("enter pwd:");
        String pw1= sc.next();
        System.out.println("Re-enter pwd:");
        String pw2= sc.next();
        while(!pw1.equals(pw2))
        {
            System.out.println("enter corect pwd remember:");
            pw1=sc.next();
            System.out.println("Re-enter pwd remember");
            pw2=sc.next();
        }
        st.executeUpdate("insert into login.details values('"+un+"','"+pw1+"')");
        System.out.println("Updation completes :");
    }

    public static void login(ResultSet rs,Statement st,Scanner sc) throws Exception
    {
        int c=0;
            do
            {
                System.out.println("enter correst  username");
                String s = sc.next();
                rs  = st.executeQuery("select * from login.details");
                while(rs.next())
                {
                    String su=rs.getString("username") ;
                    if(su.equals(s))
                    {
                        c++;
                        System.out.println("enter your password :");
                        String sp = sc.next();
                        while(!sp.equals(rs.getString("password")))
                        {   
                            
                            System.out.println("Re-enter your correct password :");
                             sp = sc.next();
                        }
                        System.out.println("welcoms you");

                       
                    }
                }
            }while(c==0);
    }
    
}

