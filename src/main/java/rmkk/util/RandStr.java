package rmkk.util;

public class RandStr
{
	public static String getRandomByModel(String startStr){
		return startStr+Long.toHexString(System.currentTimeMillis());
	}
}
