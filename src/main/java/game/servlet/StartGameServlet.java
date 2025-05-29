package game.servlet;
import game.components.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/StartGameServlet")
public class StartGameServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StartGameServlet: hit.");
		String[] playerNames = {
            request.getParameter("playerName1"),
            request.getParameter("playerName2"),
            request.getParameter("playerName3"),
            request.getParameter("playerName4")
        };
        String[] playerColors = {
                request.getParameter("playerColor1"),
                request.getParameter("playerColor2"),
                request.getParameter("playerColor3"),
                request.getParameter("playerColor4")
            };
        for(String i: playerNames) {
        	if(i==null || i.trim().isEmpty()) {
        		request.setAttribute("error", "Player name can't be empty or all spaces");
                request.getRequestDispatcher("title.jsp").forward(request, response);
                return;
        	}
        }

        // Get Monopoly instance and initialize players
        Monopoly game = Monopoly.getInstance();
        game.initializePlayers(playerNames, playerColors);
        game.nextPlayer();

        // Store game in session
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(8);
        session.setAttribute("monopoly", game);
        session.setAttribute("gameEnded", false);
        session.setAttribute("winnerName", null);

        request.setAttribute("canRollDice", true);
        // Redirect to game page
        System.out.println("StartGameServlet: game initialized.");

        response.sendRedirect(request.getContextPath() + "/game.jsp");
    }
}