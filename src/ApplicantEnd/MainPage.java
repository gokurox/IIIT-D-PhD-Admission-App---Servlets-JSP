package ApplicantEnd;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Global.MySQL;
@WebServlet("/MainPage")

public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public MainPage() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    RequestDispatcher tew = request.getRequestDispatcher("/mainpage.jsp");
    tew.forward(request, response);
    
    
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("check");
		String tmp = request.getParameter("email");
		System.out.println(tmp);
		tmp = request.getParameter("name");
		System.out.println(tmp);
		tmp = request.getParameter("aoc");
		System.out.println(tmp);
		tmp = request.getParameter("contact");
		System.out.println(tmp);
		tmp = request.getParameter("phdstream");
		System.out.println(tmp);
		tmp = request.getParameter("phdpref1");
		System.out.println(tmp);
		if("NULL".equals(request.getParameter("phdpref2")))
			System.out.println("zxcdf");
		else
			tmp = request.getParameter("phdpref2");
		System.out.println(tmp);
		tmp = request.getParameter("phdpref3");
		System.out.println(tmp);
		tmp = request.getParameter("gender");
		System.out.println(tmp);
		tmp = request.getParameter("category");
		System.out.println(tmp);
		tmp = request.getParameter("pd");
		System.out.println(tmp);
		tmp = request.getParameter("dob");
		System.out.println(tmp);
		tmp = request.getParameter("dp");
		System.out.println(tmp);
		tmp = request.getParameter("fname");
		System.out.println(tmp);
		tmp = request.getParameter("nat");
		System.out.println(tmp);
		tmp = request.getParameter("pmaddr");
		System.out.println(tmp);
		tmp = request.getParameter("pincode");
		System.out.println(tmp);
		tmp = request.getParameter("xboard");
		System.out.println(tmp);
		tmp = request.getParameter("xmarks");
		System.out.println(tmp);
		tmp = request.getParameter("xyear");
		System.out.println(tmp);
		tmp = request.getParameter("xiiboard");
		System.out.println(tmp);
		tmp = request.getParameter("xiimarks");
		System.out.println(tmp);
		tmp = request.getParameter("xiiyear");
		System.out.println(tmp);
		tmp = request.getParameter("degree");
		System.out.println(tmp);
		tmp = request.getParameter("department");
		System.out.println(tmp);
		tmp = request.getParameter("college");
		System.out.println(tmp);
		tmp = request.getParameter("university");
		System.out.println(tmp);
		tmp = request.getParameter("city");
		System.out.println(tmp);
		tmp = request.getParameter("state");
		System.out.println(tmp);
		tmp = request.getParameter("gradyear");
		System.out.println(tmp);
		tmp = request.getParameter("ch1");
		System.out.println("ch1" +tmp);
		tmp = request.getParameter("ch2");
		System.out.println("ch2" +tmp);
		tmp = request.getParameter("ch3");
		System.out.println("ch3" +tmp);
		tmp = request.getParameter("ch4");
		System.out.println("ch4" +tmp);		

		Double d,d2,d3;
		Integer i;
		// extract data from requests
		String q = "\"",qc = "\",";
		String c = ",";
		String NA = "NULL";
		
		// connection
		
		String sqlQuery = "INSERT INTO " + MySQL.getSQLtable() + " VALUES (";
		sqlQuery += q + request.getParameter("email") + qc;
		sqlQuery += q + request.getParameter("name") + qc;
		sqlQuery += q + request.getParameter("aoc") + qc;
		sqlQuery += q + request.getParameter("contact") + qc;
		sqlQuery += q + request.getParameter("phdstream") + qc;
		sqlQuery += q + request.getParameter("phdpref1") + qc;
		if("NULL".equals(request.getParameter("phdpref2")))
			sqlQuery += NA + c;
		else
		{
			sqlQuery +=q+request.getParameter("phdpref2")  + qc;			
		}
		
		if("NULL".equals(request.getParameter("phdpref3")))
			sqlQuery += NA + c;
		else
			sqlQuery +=q+request.getParameter("phdpref2")  + qc;
		sqlQuery += q + request.getParameter("gender") + qc;
		sqlQuery += q + request.getParameter("category") + qc;
		sqlQuery += q + request.getParameter("pd") + qc;
		sqlQuery += q + request.getParameter("dob") + qc;
		sqlQuery += q + request.getParameter("dp") + qc;
		sqlQuery += q + request.getParameter("fname") + qc;
		if("NULL".equals(request.getParameter("nat")))
			sqlQuery += NA + c;
		else
			sqlQuery +=q+request.getParameter("nat")  + qc;
		sqlQuery += q + request.getParameter("pmaddr") + qc;
		if(request.getParameter("pincode").isEmpty())
			sqlQuery += NA + c;									//pincode check
		else
			sqlQuery += q + request.getParameter("pincode") + qc;					
		sqlQuery += q + request.getParameter("xboard") + qc;
		d = Double.parseDouble(request.getParameter("xmarks"));		//xmarks
		sqlQuery += d + c;
		i = Integer.parseInt(request.getParameter("xyear"));        //xyear
		sqlQuery += i + c;		
		sqlQuery += q + request.getParameter("xiiboard") + qc;
		d = Double.parseDouble(request.getParameter("xiimarks"));		//xmarks
		sqlQuery += d + c;
		i = Integer.parseInt(request.getParameter("xiiyear"));        //xyear
		sqlQuery += i + c;
		sqlQuery += q + request.getParameter("degree") + qc;
		sqlQuery += q + request.getParameter("department") + qc;
		sqlQuery += q + request.getParameter("college") + qc;
		sqlQuery += q + request.getParameter("university") + qc;
		sqlQuery += q + request.getParameter("city") + qc;
		sqlQuery += q + request.getParameter("state") + qc;
		i = Integer.parseInt(request.getParameter("gradyear"));        //xyear
		sqlQuery += i + c;	
		if(request.getParameter("gradmarks")==null)         //marsk da janjat
		{
			sqlQuery += NA + c;
			sqlQuery += NA + c;
			sqlQuery += NA + c;
			sqlQuery += NA + c;			
		}
		else
		{
			sqlQuery += q + request.getParameter("gradmarks") + qc;
			if("CGPA".equals(request.getParameter("gradmarks")))
			{
				d = Double.parseDouble(request.getParameter("gradcgpa1"));		//gradcgpa1 normalization
				d2 = Double.parseDouble(request.getParameter("gradcgpa2"));		//xmarks
				d3 = (d/d2)*10;				
				sqlQuery += d3 + c;
				sqlQuery += NA + c;					
				d3 = (d3/10)*100;
				sqlQuery += d3 + c;				
			}
			else
			{
				sqlQuery += NA + c;			
				d = Double.parseDouble(request.getParameter("gradpercent"));		//gradcgpa1 normalization
				sqlQuery += d + c;
				sqlQuery += d + c;					
			}			
		}
		if("on".equals(request.getParameter("ch1")))
		{
			sqlQuery += q + "Yes" + qc;
			sqlQuery += q + request.getParameter("ecepref1") + qc;
			sqlQuery += q + request.getParameter("ecepref2") + qc;
			sqlQuery += q + request.getParameter("ecepref3") + qc;
			if("NULL".equals(request.getParameter("ecepref4")))
				sqlQuery += NA + c;
			else
				sqlQuery += q + request.getParameter("ecepref4") + qc;	
		}
		else
		{
			sqlQuery += q + "No" + qc;
			sqlQuery += NA + c;
			sqlQuery += NA + c;
			sqlQuery += NA + c;	
			sqlQuery += NA + c;
		}
		if("on".equals(request.getParameter("ch2")))
		{
			sqlQuery += q + "Yes" + qc;
		if(request.getParameter("pgdegree").isEmpty())
			sqlQuery += NA + c;									//pgcollegename check
		else
			sqlQuery += q + request.getParameter("pgdegree") + qc;
		if(request.getParameter("pgdepartment").isEmpty())
			sqlQuery += NA + c;									//pgcity  check
		else
			sqlQuery += q + request.getParameter("pgdepartment") + qc;
		if(request.getParameter("pgcollegename").isEmpty())
			sqlQuery += NA + c;									//pgstate  check
		else
			sqlQuery += q + request.getParameter("pgcollegename") + qc;
		if(request.getParameter("thesis").isEmpty())
			sqlQuery += NA + c;									//pgdepartment  check
		else
			sqlQuery += q + request.getParameter("thesis") + qc;
		if(request.getParameter("pgcity").isEmpty())
			sqlQuery += NA + c;									//pgdiscipline  check
		else
			sqlQuery += q + request.getParameter("pgcity") + qc;
		if(request.getParameter("pgstate").isEmpty())
			sqlQuery += NA + c;									//thesis  check
		else
			sqlQuery += q + request.getParameter("pgstate") + qc;
		i = Integer.parseInt(request.getParameter("pgyear"));        //xyear
		sqlQuery += i + c;	

		if(request.getParameter("pgmarks")==null)         //marsk da janjat
		{
			sqlQuery += NA + c;
			sqlQuery += NA + c;
			sqlQuery += NA + c;
			sqlQuery += NA + c;			
		}
		else
		{
			sqlQuery += q + request.getParameter("pgmarks") + qc;
			if("CGPA".equals(request.getParameter("pgmarks")))
			{
				d = Double.parseDouble(request.getParameter("pgcgpa1"));		//gradcgpa1 normalization
				d2 = Double.parseDouble(request.getParameter("pgcgpa2"));		//xmarks
				d3 = (d/d2)*10;				
				sqlQuery += d3 + c;
				sqlQuery += NA + c;					
				d3 = (d3/10)*100;
				sqlQuery += d3 + c;				
			}
			else
			{
				sqlQuery += NA + c;			
				d = Double.parseDouble(request.getParameter("pgpercent"));		//gradcgpa1 normalization
				sqlQuery += d + c;
				sqlQuery += d + c;					
			}			
		}
		}
		else
		{
			sqlQuery += q + "No" + qc;
			sqlQuery += NA + c;sqlQuery += NA + c;sqlQuery += NA + c;sqlQuery += NA + c;sqlQuery += NA + c;
			sqlQuery += NA + c;sqlQuery += NA + c;sqlQuery += NA + c;
			sqlQuery += NA + c;sqlQuery += NA + c;sqlQuery += NA + c;		
		}			
		if("on".equals(request.getParameter("ch3")))
		{
			sqlQuery +=q +  "Yes" + qc;
		if(request.getParameter("examname").isEmpty())
			sqlQuery += NA + c;									//pgcollegename check
		else
			sqlQuery += q + request.getParameter("examname") + qc;
		if(request.getParameter("subject").isEmpty())
			sqlQuery += NA + c;									//pgcity  check
		else
			sqlQuery += q + request.getParameter("subject") + qc;
		i = Integer.parseInt(request.getParameter("otheryear"));        //xyear
		sqlQuery += i + c;			
		if(request.getParameter("otherscore").isEmpty())
			sqlQuery += NA + c;									//pgdepartment  check
		else
		{
		d = Double.parseDouble(request.getParameter("otherscore"));		
			sqlQuery += d + c;
		}	
		if(request.getParameter("otherrank").isEmpty())
			sqlQuery += NA + c;									//pgdiscipline  check
		else
		{
		d = Double.parseDouble(request.getParameter("otherrank"));		
			sqlQuery += d + c;
		}	
		}
		else
		{
			sqlQuery += q + "No" + qc;
			sqlQuery += NA + c;
			sqlQuery += NA + c;
			sqlQuery += NA + c;	sqlQuery += NA + c;sqlQuery += NA + c;		
		}		
		if("on".equals(request.getParameter("ch4")))
		{
			sqlQuery += q + "Yes" + qc;
		if(request.getParameter("area").isEmpty())
			sqlQuery += NA + c;									//pgcollegename check
		else
			sqlQuery += q + request.getParameter("area") + qc;
		i = Integer.parseInt(request.getParameter("gateyear"));        //xyear
		sqlQuery += i + c;	
		if(request.getParameter("gatemarks").isEmpty())
			sqlQuery += NA + c;									//pgdiscipline  check
		else
		{
		d = Double.parseDouble(request.getParameter("gatemarks"));		
			sqlQuery += d + c;
		}	
		if(request.getParameter("gatescore").isEmpty())
			sqlQuery += NA + c;									//pgdepartment  check
		else
		{
		d = Double.parseDouble(request.getParameter("gatescore"));		
			sqlQuery += d + c;
		}	
		if(request.getParameter("gaterank").isEmpty())
			sqlQuery += NA + c;									//pgdiscipline  check
		else
		{
		d = Double.parseDouble(request.getParameter("gaterank"));		
			sqlQuery += d + c;
		}	
		}
		else
		{
			sqlQuery +=q +  "No" + qc;
			sqlQuery += NA + c;
			sqlQuery += NA + c;
			sqlQuery += NA + c;	sqlQuery += NA + c;sqlQuery += NA + c;		
		}				
		if(request.getParameter("ach").isEmpty())
			sqlQuery += NA + c;									//pgdiscipline  check
		else
			sqlQuery += q + request.getParameter("ach") + qc;
	      Date dNow = new Date( );
	      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");		
		sqlQuery += q + ft.format(dNow) + qc;		
    	RandomAccessFile f = new RandomAccessFile("D:\\IIIT-Delhi\\Courses\\Advanced_Programming\\BitBucket\\AP_CP_localRepo\\PhD Registration Form (Servlet)\\src\\DataBase\\ID.txt","rw");
		String emp = f.readLine();
		Integer x = Integer.parseInt(emp);		
		x++;
		sqlQuery += q + "PHD"+x+ q + ")";		
		String xs = x.toString();
		f.seek(0);
		f.write(xs.getBytes());
		f.close();			
		
		System.out.println(sqlQuery);
		MySQL.connect();
		
		if (!MySQL.doesTableExist(MySQL.getSQLtable())) {
			MySQL.createTable_unchecked(MySQL.getSQLtable());
		}
		
		
		
		Statement st = MySQL.getStatement();
		try {
			st.executeUpdate(sqlQuery);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MySQL.disconnect();		
		
		
	}

}
