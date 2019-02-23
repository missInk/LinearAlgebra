package lxx.linearAlgebra.Util;

public class linearEquation {
	/**
	 * �ҵ�����Ԫ��������
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
	 * ����ͬһ�������е�����
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
	 * ��˹����Ԫ����Ԫ��
	 * 
	 * @param a
	 * @param b
	 * @param x
	 * @param n
	 */
	public static void Gauss(double[][] a, double[] b, double[] x, int n)// ��1����ʼ
	{
		for (int i = 1; i < n; ++i) {
			// ������Ԫ��
			int maxr = findMaxRow(a, i, n);
			if (maxr != i) {
				swapRow(i, maxr, i, n, a);
				double tmp = b[maxr];
				b[maxr] = b[i];
				b[i] = tmp;
			}

			// ��˹��Ԫ������
			for (int r = i + 1; r < n + 1; ++r) {

				double l = a[r][i] / a[i][i];
				for (int j = i; j < n + 1; ++j) {
					a[r][j] = a[r][j] - l * a[i][j];
				}
				b[r] = b[r] - l * b[i];
			}
		}

		// �ش���
		for (int i = n; i > 0; --i) {
			for (int j = n; j > i; j--) {
				b[i] -= a[i][j] * x[j];
			}
			x[i] = b[i] / a[i][i];
		}
	}
}
