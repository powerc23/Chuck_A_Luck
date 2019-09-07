import javax.swing.JOptionPane;
import java.util.Scanner;


public class ChuckALuck {

	public static final String TRIPLE = "triple";
	public static final String FIELD = "field";
	public static final String HIGH = "high";
	public static final String LOW = "low";

	public static void main(String[] args)
	{
		double initialBalance = 0;
		double finalBalance = 0;
		try{
			String walletInput = JOptionPane.showInputDialog(null,"Enter the amount of cash in your wallet:");
			Scanner input = new Scanner(walletInput);
			if(input.hasNextDouble())
			{
				Double cashBalance = input.nextDouble();
				Double initialWalletBalance = cashBalance;
				initialBalance = initialWalletBalance;
				Wallet userWallet = new Wallet();
				userWallet.put(cashBalance);
				boolean finished = false;

				while(!finished)
				{
					if(userWallet.check() > 0)
					{
						finalBalance = userWallet.check();
						String betType = JOptionPane.showInputDialog(null,"What type of bet would you like to place"
								+ " ('triple', 'field', 'high' or 'low')?\nEnter 'quit' to exit");
						if(betType.equals("quit"))
						{
							JOptionPane.showMessageDialog(null, "Goodbye!");
							finished = true;
						}
						else if(betType.equals(TRIPLE) || betType.equals(FIELD) || betType.equals(HIGH) || betType.equals(LOW))
						{
							resolveBet(betType, userWallet);
						}
						else JOptionPane.showMessageDialog(null, "Input not valid!");

					}
					else finished = true;
				}
				endSummary(initialWalletBalance, userWallet.check());
			}
			else JOptionPane.showMessageDialog(null, "Input not valid!");
		}
		catch(Exception NullPointerException)
		{
			if (initialBalance > 0){
				endSummary(initialBalance, finalBalance);
			}
			else JOptionPane.showMessageDialog(null, "Goodbye!");

		}
	}



	public static void endSummary(double initialWalletBalance, double userWallet)
	{
		JOptionPane.showMessageDialog(null,"GAME OVER!\nYou started with: $" + initialWalletBalance + "\nYou finished with: $" + userWallet);
	}



	public static void resolveBet(String betType, Wallet userWallet)
	{
		String bet = JOptionPane.showInputDialog(null, "How much would you like to bet? \n Current Balance: $" + userWallet.check());
		Scanner input = new Scanner(bet);
		if(input.hasNextDouble())
		{
			Double betAmount = input.nextDouble();
			double walletBalance = userWallet.check();
			if(betAmount <= userWallet.check())
			{
				Dice dice1 = new Dice();
				Dice dice2 = new Dice();
				Dice dice3 = new Dice();
				int value1 = dice1.topFace();
				int value2 = dice2.topFace();
				int value3 = dice3.topFace();
				double betReturn = 0;
				int sumOnDie = value1 + value2 + value3;
				boolean triple;
				if(value1 == value2 && value2 == value3)
				{
					triple = true;
				}
				else
				{
					triple = false;
				}

				if(betType.equals(TRIPLE))
				{
					if(triple)
					{
						betReturn = betAmount * 30;
						userWallet.put(betReturn);
						JOptionPane.showMessageDialog(null, "You won!\nCurrent Balance: $" + userWallet.check());
					}
					else 
					{
						userWallet.get(betAmount);
						JOptionPane.showMessageDialog(null, "You lost!\nCurrent Balance: $" + userWallet.check());
					}
				}
				else if(betType.equals(FIELD))
				{
					if(sumOnDie < 8 && !triple || sumOnDie > 12 && !triple)
					{
						betReturn = betAmount;
						userWallet.put(betReturn);
						JOptionPane.showMessageDialog(null, "You won!\nCurrent Balance: $" + userWallet.check());
					}
					else
					{
						userWallet.get(betAmount);
						JOptionPane.showMessageDialog(null, "You lost!\nCurrent Balance: $" + userWallet.check());
					}
				}
				else if(betType.equals(HIGH))
				{
					if(sumOnDie > 10 && !triple)
					{
						betReturn = betAmount;
						userWallet.put(betReturn);
						JOptionPane.showMessageDialog(null, "You won!\nCurrent Balance: $" + userWallet.check());
					}
					else
					{
						userWallet.get(betAmount);
						JOptionPane.showMessageDialog(null, "You lost!\nCurrent Balance: $" + userWallet.check());
					}	
				}
				else if(betType.equals(LOW))
				{
					if(sumOnDie < 11 && !triple)
					{
						betReturn = betAmount;
						userWallet.put(betReturn);
						JOptionPane.showMessageDialog(null, "You won!\nCurrent Balance: $" + userWallet.check());
					}
					else
					{
						userWallet.get(betAmount);
						JOptionPane.showMessageDialog(null, "You lost!\nCurrent Balance: $" + userWallet.check());
					}
				}
			}
			else JOptionPane.showMessageDialog(null, "You do not have enough in your balance to place bet!");
		}
	}
}






