package lxx.linearAlgebra.Util;

import java.util.UUID;

public class CodeUtil {

	/**
	 * ����һ������Ψһ�ԵĴ���
	 * @return ����Ψһ�ԵĴ���
	 */
	public static String generateUniqueCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
