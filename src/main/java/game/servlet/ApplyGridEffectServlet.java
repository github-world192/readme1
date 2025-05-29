package game.servlet;
import game.components.*;
import helper.MessageLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import enums.EffectEnum;

@WebServlet("/ApplyGridEffectServlet")
public class ApplyGridEffectServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Monopoly game = Monopoly.getInstance();
        Players currentPlayer = game.getCurrentPlayer();
        Grids currentGrid = game.getGridsList().get(currentPlayer.getLocation());

        String message = "";
        
     // Check if it's a Land and needs purchase confirmation
        if (currentGrid instanceof Lands) {
            Lands land = (Lands) currentGrid;

            if (land.getOwner() == null) {
                // Defer to buy confirmation JSP
            	message=currentPlayer.getName() + " stop at " + land.getGridName() + ". Would you like to buy it? $"+land.getPrice();
            	MessageLogger.add(request, message);
            	request.setAttribute("confirmAction", true);
            	request.setAttribute("confirmYesAction", "BuyLandServlet");
                request.getRequestDispatcher("/game.jsp").forward(request, response);
                return; //Stop here. Wait for player decision in BuyLandServlet
            }
        }
        
        //else it's other type of grid, just apply effect
        EffectEnum result = currentGrid.effect(currentPlayer);

        switch (result) {
            case EffectEnum.PAID_TOLL:
                message = currentPlayer.getName() + " paid toll to " + ((Lands) currentGrid).getOwner().getName() + ".";
                break;
            case EffectEnum.GO_TO_JAIL:
                message = currentPlayer.getName() + " is sent to jail for 2 turns.";
                break;
            case EffectEnum.VISIT_JAIL:
            	ArrayList<String> temp=new ArrayList<String>();
            	for(Players i:game.getPlayersList()) {
            		if(i.isInJail()) {
            			temp.add(i.getName());
            		}
            	}
            	if(!temp.isEmpty()) {
            		message="Say hi to "+String.join(", ", temp);
            	}
            	message="You are visiting jail. "+message;
                break;
            case EffectEnum.FREE_PARKING:
            	message="What a nice place for parking.";
            	break;
            case EffectEnum.CHANCE_CARD:
            	message="You draw a chance card. "+game.getLastDrawnCard().getDescription();
            	break;
            case EffectEnum.COMMUNITY_CHEST_CARD:
            	message="You draw a community chest card. "+game.getLastDrawnCard().getDescription();
            	break;
            case EffectEnum.NONE:
            	break;
            default:
                System.out.println("Unhandled EffectEnum: " + result);
                break;
        }
        MessageLogger.add(request, message);

        // After effect, check for bankruptcy
        for (Players p : game.getPlayersList()) {
            if (p.getMoney() < 0 && !p.getBankrupt()) {
                p.bankrupt();
                message=p.getName()+" is bankrupt";
                MessageLogger.add(request, message);
            }
        }

        // Check win condition
        if (game.getPlayersList().size() - game.getNumOfBankrupt() == 1) {
            game.end(); // This should set winner info and handle end-state
            HttpSession session = request.getSession();
            session.setAttribute("gameEnded", true);
            session.setAttribute("winnerName", game.getWinner().getName());
        }
        
        // Set attributes and forward to game.jsp
        request.setAttribute("nextTurn", true);
        request.setAttribute("canRollDice", false);
        //HttpSession session = request.getSession();
        //session.setAttribute("gameEnded", false);
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
}