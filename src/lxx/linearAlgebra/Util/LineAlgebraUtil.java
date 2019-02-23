package lxx.linearAlgebra.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineAlgebraUtil {

	/**
	 * 获取传入文件的行数
	 * 
	 * @param file
	 *            传入文件
	 * @return 传入文件的行数，读取异常返回0，文件未找到返回-1
	 */
	public static int getRow(File file) {
		int row = -1;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			row = 0;
			while (reader.readLine() != null) {
				row++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("读取文件异常");
			e.printStackTrace();
		}
		return row;
	}

	/**
	 * 获取传入文件（行列式）的列数
	 * 
	 * @param file
	 *            传入的文件
	 * @param row
	 *            传入文件的行数
	 * @return 传入文件（行列式）的列数，文件未找到返回-1，文件每一行的列数不一样返回0
	 */
	public static int getCol(File file, int row) {
		int col = -1;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String colString = reader.readLine();
			col = colString.split(";").length;
			for (int i = 1; i < row; i++) {
				colString = reader.readLine();
				if (col != colString.split(";").length) {
					return 0;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("读取文件异常");
			e.printStackTrace();
		}
		return col;
	}

	/**
	 * 将一个文件转换为行列式
	 */
	public static double[][] toLineAlgebra(File file) {
		int row = getRow(file);
		int col = getCol(file, row);
		double[][] lineAlgebra = new double[row][col];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			for (int i = 0; i < row; i++) {
				String line = reader.readLine();
				String[] values = line.split(";");
				for (int j = 0; j < col; j++) {
					lineAlgebra[i][j] = Double.parseDouble(values[j]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件读取异常");
			e.printStackTrace();
		}catch (Exception e) {
			throw new RuntimeException("文件出现未知异常");
		}
		return lineAlgebra;
	}

	/**
	 * 输出行列式
	 * @param lineAlgebra 行列式
	 * @param row 行数
	 * @param col 列数
	 */
	public static void sysoLineAlgebra(double[][] lineAlgebra, int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(lineAlgebra[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * 将规定好的字符串按照一定的格式划分为指定行列的行列式
	 * 
	 * @param value
	 *            具有一定规则的字符串，格式如：1；2；3；4；5；
	 * @param row
	 *            行列式的行数
	 * @param col
	 *            行列式的列数
	 */
	public static double[][] toLineAlgebra(String value, int row, int col) {
		double[][] lineAlgebra = new double[row][col];
		String[] values = value.split(";");
		if (row * col != values.length) {
			throw new RuntimeException("行列式不符合规范");
		}
		for (int i = 0, index = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				try {
					lineAlgebra[i][j] = Double.parseDouble(values[index++]);
				} catch (Exception e) {
					throw new RuntimeException("行列式包含非法字符");
				}
			}
		}
		return lineAlgebra;
	}

	/**
	 * 获取行列式的值。 原理：行列式等于它的任意列（或行）各个元素与其对应代数余子式乘积的和。
	 * 
	 * @param p
	 *            求解的行列式
	 * @param n
	 *            行列式的阶数
	 * @return 行列式的值
	 */
	public static double getValue(double[][] p, int n) {
		if (n == 1) // 如果是一阶行列式,直接返回该元素
			return p[0][0];

		double sum = 0; // 累加求和变量

		for (int j = 0; j < n; j++) {// 遍历最后一行各元素,p[n - 1][j]
			int pt = (n - 1) + j; // 符号判断指数

			double[][] p1 = new double[n][n];

			// 此过程是为了把行列式存放到临时数组中，不改变但前行列式
			for (int row = 0; row < n; row++) {
				for (int col = 0; col < n; col++) {
					p1[row][col] = p[row][col];
				}
			}

			// 此过程，是为了将元素 p[n-1][j] 所在列之后的每一列向前移动一列，为后面获取该元素对应的余子式做准备
			for (int index = 0; index < n - 1; index++) {
				for (int index1 = j; index1 < n - 1; index1++) {
					p1[index][index1] = p1[index][index1 + 1];
				}
			}

			// 此过程，截取临时数组 p1 左上角 n-1 阶行列式，提取元素 p[n-1][j] 的余子式
			double[][] temp = new double[n - 1][n - 1];
			for (int row = 0; row < n - 1; row++) {
				for (int col = 0; col < n - 1; col++) {
					temp[row][col] = p1[row][col];
				}
			}

			// 求和：sum += 元素 * 符号 * 余子式
			// 因为，余子式是去除某一元素所在的行和列之后剩下的元素构成的 n-1 阶行列式
			// 即，余子式本质还是行列式，所以需要递归求行列式的值
			sum += p[n - 1][j] * Math.pow(-1, pt) * getValue(temp, n - 1);
			// System.out.println(p[n - 1][j] + " * " + Math.pow(-1, pt) + " * " +
			// GetValue(p1, n - 1));
		}

		return sum;
	}

	/**
	 * 逐次超松弛方法SOR,目前还不知道扎用
	 * 解线性方程组
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] SOR(double[][] a, double[] b, double m) {
		int n = a.length;
		double sum = 0;
		double e = 0.001;
		double z;
		int i;
		double[] x = new double[n];
		double[] y = new double[n];
		while (true) {
			// 按迭代式迭代
			for (i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 跟Jacobi的不同之处
					if (i < j) {
						sum += a[i][j] * y[j];
					} else if (i > j) {
						sum += a[i][j] * x[j];
					}
				}
				y[i] = (1 - m) * x[i] + (m * (b[i] - sum)) / a[i][i];
				sum = 0;
			}
			// 达到精度后终止
			i = 0;
			while (i < n) {
				z = Math.abs(x[i] - y[i]);
				if (z > e)
					break;
				i++;
			}
			if (i != n) {
				for (i = 0; i < n; i++)
					x[i] = y[i];
			} else if (i == n)
				break;
		}
		return y;
	}

	/**
	 * 矩阵乘法 a点乘b，当矩阵a的列数x与矩阵b的行数y相等时可进行相乘 a乘b得到的新矩阵c，c的行数y等于a的行数，c的列数x等于b的列数
	 * 
	 * @param a
	 *            相乘的矩阵
	 * @param b
	 *            相乘的矩阵
	 * @return a乘b得到的新矩阵
	 */
	public static double[][] matrixMultiplication(double a[][], double b[][]) {
		// 当a的列数与矩阵b的行数不相等时，不能进行点乘，返回null
		if (a[0].length != b.length)
			return null;
		// c矩阵的行数y，与列数x
		int y = a.length;
		int x = b[0].length;
		double c[][] = new double[y][x];
		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++)
				// c矩阵的第i行第j列所对应的数值，等于a矩阵的第i行分别乘以b矩阵的第j列之和
				for (int k = 0; k < b.length; k++)
					c[i][j] += a[i][k] * b[k][j];
		LineAlgebraUtil.sysoLineAlgebra(c, 2, 3);
		return c;
	}

	/**
	 * 矩阵加法，当矩阵a的行数和列数与矩阵b的行数和列数相等时可进行相加， a加b得到的新矩阵c，c的行数和列数等于矩阵a的行数和列数
	 * 
	 * @param a
	 *            相加的矩阵
	 * @param b
	 *            相加的矩阵
	 * @return a加b得到的新矩阵
	 */
	public static double[][] matrixAdd(double a[][], double b[][]) {
		// 当a的行数和列数与矩阵b的行数和列数不相等时，不能进行加法，返回null
		if (a[0].length != b[0].length && a.length != b.length)
			return null;
		// c矩阵的行数y，与列数x
		int y = a.length;
		int x = a[0].length;
		double c[][] = new double[y][x];
		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++) {
				// c矩阵的第i行第j列所对应的数值，等于a[i][j]+b[i][j]
				c[i][j] = a[i][j] + b[i][j];
			}
		return c;
	}

	/**
	 * 矩阵的转置
	 * 
	 * @param Matrix
	 *            被转置的矩阵
	 * @param Line
	 *            矩阵的行数
	 * @param List
	 *            矩阵的列数
	 * @return 将矩阵Matrix转置后的矩阵
	 */
	public static double[][] Transpose(double[][] Matrix, int Line, int List) {
		double[][] MatrixC = new double[List][Line];
		for (int i = 0; i < Line; i++) {
			for (int j = 0; j < List; j++) {
				MatrixC[j][i] = Matrix[i][j];
			}
		}
		return MatrixC;
	}

	/**
	 * 求矩阵的逆
	 * 
	 * @param a
	 *            Double型n*n二维数组，开始时为原矩阵，返回时为逆矩阵
	 * @param n
	 *            矩阵的阶数
	 */
	public static void Mrinv(double[][] a, int n) {
		int i, j, row, col, k;
		double max, temp;
		int[] p = new int[n];
		double[][] b = new double[n][n];
		for (i = 0; i < n; i++) {
			p[i] = i;
			b[i][i] = 1;
		}

		for (k = 0; k < n; k++) {
			// 找主元
			max = 0;
			row = col = i;
			for (i = k; i < n; i++)
				for (j = k; j < n; j++) {
					temp = Math.abs(b[i][j]);
					if (max < temp) {
						max = temp;
						row = i;
						col = j;
					}
				}
			// 交换行列，将主元调整到 k 行 k 列上
			if (row != k) {
				for (j = 0; j < n; j++) {
					temp = a[row][j];
					a[row][j] = a[k][j];
					a[k][j] = temp;
					temp = b[row][j];
					b[row][j] = b[k][j];
					b[k][j] = temp;
				}
				i = p[row];
				p[row] = p[k];
				p[k] = i;
			}
			if (col != k) {
				for (i = 0; i < n; i++) {
					temp = a[i][col];
					a[i][col] = a[i][k];
					a[i][k] = temp;
				}
			}
			// 处理
			for (j = k + 1; j < n; j++)
				a[k][j] /= a[k][k];
			for (j = 0; j < n; j++)
				b[k][j] /= a[k][k];
			a[k][k] = 1;

			for (j = k + 1; j < n; j++) {
				for (i = 0; i < k; i++)
					a[i][j] -= a[i][k] * a[k][j];
				for (i = k + 1; i < n; i++)
					a[i][j] -= a[i][k] * a[k][j];
			}
			for (j = 0; j < n; j++) {
				for (i = 0; i < k; i++)
					b[i][j] -= a[i][k] * b[k][j];
				for (i = k + 1; i < n; i++)
					b[i][j] -= a[i][k] * b[k][j];
			}
			for (i = 0; i < k; i++)
				a[i][k] = 0;
			a[k][k] = 1;
		}
		// 恢复行列次序；
		for (j = 0; j < n; j++)
			for (i = 0; i < n; i++)
				a[p[i]][j] = b[i][j];
	}

	/**
	 * 求矩阵的秩
	 * 
	 * @param Matrix
	 *            矩阵方程
	 * @param error_
	 *            -1
	 * @param List
	 *            矩阵的阶数
	 * @return
	 */
	public static int Rank(double[][] Matrix, int error_, int List) {
		int n = List;
		int m = Matrix.length;
		int i = 0;
		int i1;
		int j = 0;
		int j1;
		double temp1;
		if (m > n) {
			i = m;
			m = n;
			n = i;
			i = 1;
		}
		m -= 1;
		n -= 1;
		double[][] temp = new double[m + 1][n + 1];
		if (i == 0) {
			for (i = 0; i <= m; i++) {
				for (j = 0; j <= n; j++) {
					temp[i][j] = Matrix[i][j];
				}

			}
		} else {
			for (i = 0; i <= m; i++) {
				for (j = 0; j <= n; j++) {
					temp[i][j] = Matrix[j][i];
				}
			}
		}
		if (m == 0) {
			i = 0;
			while (i <= n) {
				if (Matrix[0][i] != 0) {
					return 1;
				}
				i += 1;
			}
			return 0;
		}
		double error0;
		if (error_ == -1) {
			error0 = Math.pow(0.1, 10);
		} else {
			error0 = Math.pow(0.1, error_);
		}
		i = 0;
		while (i <= m) {
			j = 0;
			while (j <= n) {
				if (temp[i][j] != 0) {
					error0 *= temp[i][j];
					i = m;
					break;
				}
				j += 1;
			}
			i += 1;
		}
		double error1;
		for (i = 0; i <= m; i++) {
			j = 0;
			while (j <= n) {
				if (temp[i][j] != 0) {
					break;
				}
				j += 1;
			}
			if (j <= n) {
				i1 = 0;
				while (i1 <= m) {
					if (temp[i1][j] != 0 && i1 != i) {
						temp1 = temp[i][j] / temp[i1][j];
						error1 = Math.abs((temp[i][j] - temp[i1][j] * temp1)) * 100;
						error1 += error0;
						for (j1 = 0; j1 <= n; j1++) {
							temp[i1][j1] = temp[i][j1] - temp[i1][j1] * temp1;
							if (Math.abs(temp[i1][j1]) < error1) {
								temp[i1][j1] = 0;
							}
						}

					}
					i1 += 1;
				}
			}
		}

		i1 = 0;
		for (i = 0; i <= m; i++) {
			for (j = 0; j <= n; j++) {
				if (temp[i][j] != 0) {
					i1 += 1;
					break;
				}
			}
		}
		return i1;
	}

}
