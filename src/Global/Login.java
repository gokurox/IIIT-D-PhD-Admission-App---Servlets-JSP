package Global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String adminRedirectURI = "http://localhost:8080/PhD_Registration_Form_Servlet/Admin%20Homepage.html";
	
	private final String getTokenURL = "https://accounts.google.com/o/oauth2/token";
	private final String getUserInfoURL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    
	/* *********************************************************************************************/
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q = "\"";
		
		try
		{
			String code;
			String urlParameters;
			String line, oString;
			String accessToken;
			
			// Get Code
			code = request.getParameter ("code");
			urlParameters = "code=" + code +
							"&client_id=" + Global.clientID +
							"&client_secret=" + Global.clientSecret +
							"&redirect_uri=" + Global.redirectURL +
							"&grant_type=" + "authorization_code";
			
			// POST Parameters
			URL tokenurl = new URL (getTokenURL);
			URLConnection tokenurlConnect = tokenurl.openConnection();
			tokenurlConnect.setDoOutput (true);
			OutputStreamWriter out = new OutputStreamWriter (tokenurlConnect.getOutputStream());
			out.write (urlParameters);
			out.flush();
			out.close();
			
			
			// Get Output in String format
			oString = "";
			BufferedReader br = new BufferedReader (new InputStreamReader (tokenurlConnect.getInputStream()));
			while ((line = br.readLine()) != null) {
				oString += line;
			}
			br.close();
			
			
			// Get Access Token
			JsonObject json = (JsonObject) new JsonParser().parse (oString);
			accessToken = json.get ("access_token").getAsString();
			
			
			// Get User Info
			URL userurl = new URL (getUserInfoURL + accessToken);
			URLConnection userurlConnect = userurl.openConnection();
			oString = "";
			br = new BufferedReader (new InputStreamReader (userurlConnect.getInputStream()));
			while ((line = br.readLine()) != null)
				oString += line;
			
			System.out.println (oString);
			
			// Convert JSON to Client Data
			Global.activeClient = new Gson().fromJson (oString, ClientInfo.class);
			
		}
		catch (IOException ioexc)
		{
			ioexc.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		boolean isAdmin = false;
		String clientEmail = null;
		Statement SQLstatement = null;
		ResultSet SQLresultset = null;
		
		if (Global.activeClient != null)
			clientEmail = Global.activeClient.getEmail();
		else
			System.out.println ("USERINFO was not collected via GAuth");
		
		MySQL.connect();
		
		try
		{
			String sqlQuery;
			
			if (!MySQL.doesTableExist (MySQL.getSQLadmintable()))
				MySQL.createTable_unchecked (MySQL.getSQLadmintable());
			
			sqlQuery = "SELECT EMAIL FROM " + MySQL.getSQLadmintable() +
						" WHERE EMAIL = " + q + clientEmail + q;
			
			SQLstatement = MySQL.getStatement();
			SQLresultset = SQLstatement.executeQuery (sqlQuery);
			
			int NOR;
			SQLresultset.last();
			NOR = SQLresultset.getRow();
			SQLresultset.first();
			
			if (NOR == 1)
				isAdmin = true;
			else
				isAdmin = false;
			
			SQLresultset.close();
			SQLstatement.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		MySQL.disconnect();
		
		request.setAttribute ("activeClient", Global.activeClient.getName());
		
		if (isAdmin)
		{
			response.sendRedirect (response.encodeRedirectURL (adminRedirectURI));
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("/MainPage");
			rd.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
