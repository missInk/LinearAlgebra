package lxx.linearAlgebra.Util;

import java.util.UUID;

public class CodeUtil {

	/**
	 * 生成一个具有唯一性的代码
	 * @return 具有唯一性的代码
	 */
	public static String generateUniqueCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
