package lesson3;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson2.Database;
import lesson2.FlipCard;
import lesson2.MemorizeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FirstServlet extends HttpServlet {
	private final MemorizeService service = new MemorizeService(new Database());


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader br = req.getReader();
		List<String> lines = br.lines().collect(Collectors.toCollection(ArrayList::new));
		Iterator<String> iterator = lines.iterator();
		while (iterator.hasNext()) {
			String line = iterator.next();
			String nativeWord = Arrays.stream(line.split("-")).findFirst().orElse("default");
			String translation = Arrays.stream(line.split("-")).skip(1).findFirst().orElse("default");
			FlipCard flipCard = new FlipCard(nativeWord, translation);
			service.addFlipCard(flipCard);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pr = resp.getWriter();
		pr.println("<html><head><title>Hello World Servlet</title></head>");
		pr.println("<body>");
		pr.println("<h1>Hello World!</h1>");
		pr.println("</body></html>");
		pr.close();
	}
}
