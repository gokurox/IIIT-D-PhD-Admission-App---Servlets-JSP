package ApplicantEnd;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class MainPage2
 */
@WebServlet("/MainPage2")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class MainPage2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String savepath = "D:\\IIIT-Delhi\\Courses\\Advanced_Programming\\BitBucket\\AP_CP_localRepo\\PhD Registration Form (Servlet)\\src\\DataBase";       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPage2() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("From Servlet2");
		String tempFileName = "tempDB.xlsx";
        File fileSaveDir = new File(savepath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        try{ 
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            part.write(savepath + "\\"+ fileName);
        }}
        catch(IOException e){
            RequestDispatcher tew = request.getRequestDispatcher("/mainpage.jsp");
            tew.forward(request, response);
            return;
        }
        RequestDispatcher tew = request.getRequestDispatcher("/mainpage.jsp");
        tew.forward(request, response);
        return;
//        request.setAttribute("message", "Upload has been done successfully!");
//        getServletContext().getRequestDispatcher("/message.jsp").forward(
//                request, response);        
	}
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}
