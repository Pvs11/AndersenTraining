package servlets;

import config.ServletConfiguration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.FlipCard;
import service.MemorizeService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addServlet", urlPatterns = "/add")
public class AddServlet extends HttpServlet {
	private final MemorizeService service = ServletConfiguration.getService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pr = resp.getWriter();
		pr.println("<html>");
		pr.println("<body><p>Here you can add your words in fields below<p>");
		pr.println("<form name=\"addForm\" method=\"post\" action=\"/webAppExploded/add\">");
		pr.println("nativeWord: <input type=\"text\" name=\"nativeWord\"/><br/>");
		pr.println("translation: <input type=\"text\" name=\"translation\"/><br/>");
		pr.println("<input type=\"submit\" value=\"add\"></form>");
		pr.println("</body></html>");
		pr.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nativeWord = req.getParameter("nativeWord");
		String translation = req.getParameter("translation");
		boolean addSuccessful = service.addFlipCard(new FlipCard(nativeWord, translation));
		resp.setContentType("text/html");
		PrintWriter pr = resp.getWriter();
		pr.println("<html>");
		if (addSuccessful) {
			pr.println("<body><p>Thanks! Your word pair has been added<p>");
		} else {
			pr.println("<body><p>It is pointless to add empty card! Try again<p>");
		}
		pr.println("<a href=\"/webAppExploded\">home page</a>");
		pr.println("</body></html>");
		pr.close();
	}
}
