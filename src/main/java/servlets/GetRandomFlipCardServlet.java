package servlets;

import config.ServletConfiguration;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.FlipCard;
import service.MemorizeService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getRandomFlipCard", urlPatterns = "/get")
public class GetRandomFlipCardServlet extends HttpServlet {
	private final MemorizeService service = ServletConfiguration.getService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		FlipCard flipCard = service.getRandomCard();
		resp.setContentType("text/html");
		PrintWriter pr = resp.getWriter();
		pr.println("<html>");
		pr.println("<body><p>Random flipcard</p>");
		pr.println("<p>" + flipCard.getNativeWord() + " - " + flipCard.getTranslationWord() + "<p>");
		pr.println("</body></html>");
		pr.close();
	}
}
