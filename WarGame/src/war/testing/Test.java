package war.testing;

import war.cards.components.War;

public class Test {

	public static void main(String[] args) throws Exception {
		War war = new War();
		System.out.println(war.getPlayer1CurrentCard().toString());
	}

}
