package game.servlet;
import game.components.*;
import helper.MessageLogger;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/NextTurnServlet")
public class NextTurnServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String message="";
        Monopoly game = Monopoly.getInstance();
        game.nextPlayer();
        Players currentPlayer = game.getCurrentPlayer();
        
        if(currentPlayer.getBankrupt()) {
        	request.setAttribute("nextTurn", true);
        	request.setAttribute("canRollDice", false);
        }
        else if (currentPlayer.isInJail() && currentPlayer.getNumOfReleaseCard() > 0) {
            // Ask for confirmation
            request.setAttribute("confirmAction", true);
            request.setAttribute("confirmYesAction", "UseReleaseCardServlet");
            message=currentPlayer.getName() + " is in jail. Use a release card?";
        }
        else if(currentPlayer.isInJail()) {
        	currentPlayer.reduceJailTime(1);
        	message=currentPlayer.getName()+" is in jail for "+currentPlayer.getInJailTurns()+" more turn";
        	request.setAttribute("nextTurn", true);
        	request.setAttribute("canRollDice", false);
        }
        else {
        	request.setAttribute("canRollDice", true);
        	//session.setAttribute("gameEnded", false);
        }

        MessageLogger.add(request, message);
        // Check if someone won
        if (game.getPlayersList().size() - game.getNumOfBankrupt() == 1) {
            game.end(); // sets winner info in session
            session.setAttribute("gameEnded", true);
            session.setAttribute("winnerName", game.getWinner().getName());
        }

        // Forward to game.jsp
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }
}
