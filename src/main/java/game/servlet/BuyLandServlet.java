package game.servlet;
import game.components.*;
import helper.MessageLogger;

import java.io.IOException;
import java.util.List;

import enums.EffectEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BuyLandServlet")
public class BuyLandServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Monopoly game = Monopoly.getInstance();
        Players currentPlayer = game.getCurrentPlayer();

        String decision = request.getParameter("decision");
        int gridIndex = currentPlayer.getLocation();
        Grids currentGrid = game.getGridsList().get(gridIndex);
        String message = "";
        Lands land = (Lands) currentGrid;

        if ("yes".equals(decision)) {
        	EffectEnum result = land.effect(currentPlayer);
            switch(result) {
            	case EffectEnum.LAND_PURCHASED:
            		message=currentPlayer.getName() + " bought " + land.getGridName() + " for $" + land.getPrice();
            		break;
            	case EffectEnum.CANNOT_AFFORD_LAND:
            		message="Not enough money to buy " + land.getGridName();
            	default:
            		System.out.println("something wrong in buylandservlet, fix it");
            		break;
            }
        } else {
            message=currentPlayer.getName()+" decided to save money for better place";
        }
        
        MessageLogger.add(request, message);

        // Auto-move to next player after short wait, or handle it via frontend
        request.setAttribute("nextTurn", true); // JSP can use this to auto-trigger next turn
        request.setAttribute("canRollDice", false);
        //HttpSession session = request.getSession();
        //session.setAttribute("gameEnded", false);
        request.getRequestDispatcher("/game.jsp").forward(request, response);

    }
}