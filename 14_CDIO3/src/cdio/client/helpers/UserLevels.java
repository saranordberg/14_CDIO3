package cdio.client.helpers;

public class UserLevels {
	public static enum MenuLevel {
		
		ADMIN			(100),
		RAAVARE			(80),
		RECEPT			(80),
		RAAVAREBATCH	(60),
		PRODUKTBATCH	(60),
		AFVEJNING		(40);
		
		private int value;

		private MenuLevel(int value) {
			this.value = value;
		}
	}
	
	public static boolean HasRight(int userLevel, MenuLevel level)
	{
		return userLevel >= level.value;
	}
}