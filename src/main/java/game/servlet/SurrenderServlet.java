package game.servlet;
import game.components.*;
import helper.MessageLogger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SurrenderServlet")
public class SurrenderServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Monopoly game = Monopoly.getInstance();
        Players currentPlayer=game.getCurrentPlayer();
        currentPlayer.setSurrendering(true);
        currentPlayer.bankrupt();
        // Check win condition
        if (game.getPlayersList().size() - game.getNumOfBankrupt() == 1) {
            game.end();
            HttpSession session = request.getSession();
            session.setAttribute("gameEnded", true);
            session.setAttribute("winnerName", game.getWinner().getName());
            request.setAttribute("nextTurn", false);
        }
        else {
        	HttpSession session = request.getSession();
            request.setAttribute("nextTurn", true);
            session.setAttribute("gameEnded", false);
        }
        String message=currentPlayer.getName()+" has surrendered";
        MessageLogger.add(request, message);
        request.setAttribute("canRollDice", false);
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
}