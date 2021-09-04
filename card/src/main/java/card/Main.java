package card;

import carddao.*;
import cardtao.*;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		CardManager cardManager=CardManager.getInstance();
		CardDao cardDao=new CardDao();
		
		while_loop:
		while(true) {
			cardManager.printMainMenu();
			int menuNumber=cardManager.getMenuNumber();
			switch(menuNumber) {
			case 1:
				Card card=cardManager.inputBusinessCard();
				cardDao.addCard(card);
				break;				
			case 2:
				String searchKeyword=cardManager.getSearchKeyword();
				List<Card> cardList=cardDao.searchBusinessCard(searchKeyword);
				cardManager.printBusinessCards(cardList);
				break;
			case 3:
				cardManager.printExitMessage();
				break while_loop;
			default:
				cardManager.printErrorMessage();
				break;
			}
		}
	}
}
