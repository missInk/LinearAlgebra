package lxx.linearAlgebra.Util;

public class linearEquation {
	/**
	 * 找到列主元素所在行
	 * 
	 * @param a
	 * @param i
	 * @param n
	 * @return
	 */
	private static int findMaxRow(double[][] a, int i, int n) {
		int ansRow = i;
		for (int r = i + 1; r < n + 1; ++r)
			if (a[ansRow][i] < a[r][i])
				ansRow = r;
		return ansRow;
	}

	/**
	 * 交换同一个矩阵中的两行
	 * 
	 * @param startColumn
	 * @param r1
	 * @param r2
	 * @param n
	 * @param a
	 */
	private static void swapRow(int startColumn, int r1, int r2, int n, double[][] a) {
		for (int j = startColumn; j < n + 1; ++j) {
			double t = a[r1][j];
			a[r1][j] = a[r2][j];
			a[r2][j] = t;

		}
	}

	/**
	 * 高斯列主元素消元法
	 * 
	 * @param a
	 * @param b
	 * @param x
	 * @param n
	 */
	public static void Gauss(double[][] a, double[] b, double[] x, int n)// 从1，开始
	{
		for (int i = 1; i < n; ++i) {
			// 找列主元素
			int maxr = findMaxRow(a, i, n);
			if (maxr != i) {
				swapRow(i, maxr, i, n, a);
				double tmp = b[maxr];
				b[maxr] = b[i];
				b[i] = tmp;
			}

			// 高斯消元主过程
			for (int r = i + 1; r < n + 1; ++r) {

				double l = a[r][i] / a[i][i];
				for (int j = i; j < n + 1; ++j) {
					a[r][j] = a[r][j] - l * a[i][j];
				}
				b[r] = b[r] - l * b[i];
			}
		}

		// 回带解
		for (int i = n; i > 0; --i) {
			for (int j = n; j > i; j--) {
				b[i] -= a[i][j] * x[j];
			}
			x[i] = b[i] / a[i][i];
		}
	}
}
