package lxx.linearAlgebra.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineAlgebraUtil {

	/**
	 * ��ȡ�����ļ�������
	 * 
	 * @param file
	 *            �����ļ�
	 * @return �����ļ�����������ȡ�쳣����0���ļ�δ�ҵ�����-1
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
			System.out.println("�ļ�δ�ҵ�");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("��ȡ�ļ��쳣");
			e.printStackTrace();
		}
		return row;
	}

	/**
	 * ��ȡ�����ļ�������ʽ��������
	 * 
	 * @param file
	 *            ������ļ�
	 * @param row
	 *            �����ļ�������
	 * @return �����ļ�������ʽ�����������ļ�δ�ҵ�����-1���ļ�ÿһ�е�������һ������0
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
			System.out.println("�ļ�δ�ҵ�");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("��ȡ�ļ��쳣");
			e.printStackTrace();
		}
		return col;
	}

	/**
	 * ��һ���ļ�ת��Ϊ����ʽ
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
			System.out.println("�ļ�δ�ҵ�");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("�ļ���ȡ�쳣");
			e.printStackTrace();
		}catch (Exception e) {
			throw new RuntimeException("�ļ�����δ֪�쳣");
		}
		return lineAlgebra;
	}

	/**
	 * �������ʽ
	 * @param lineAlgebra ����ʽ
	 * @param row ����
	 * @param col ����
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
	 * ���涨�õ��ַ�������һ���ĸ�ʽ����Ϊָ�����е�����ʽ
	 * 
	 * @param value
	 *            ����һ��������ַ�������ʽ�磺1��2��3��4��5��
	 * @param row
	 *            ����ʽ������
	 * @param col
	 *            ����ʽ������
	 */
	public static double[][] toLineAlgebra(String value, int row, int col) {
		double[][] lineAlgebra = new double[row][col];
		String[] values = value.split(";");
		if (row * col != values.length) {
			throw new RuntimeException("����ʽ�����Ϲ淶");
		}
		for (int i = 0, index = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				try {
					lineAlgebra[i][j] = Double.parseDouble(values[index++]);
				} catch (Exception e) {
					throw new RuntimeException("����ʽ�����Ƿ��ַ�");
				}
			}
		}
		return lineAlgebra;
	}

	/**
	 * ��ȡ����ʽ��ֵ�� ԭ������ʽ�������������У����У�����Ԫ�������Ӧ��������ʽ�˻��ĺ͡�
	 * 
	 * @param p
	 *            ��������ʽ
	 * @param n
	 *            ����ʽ�Ľ���
	 * @return ����ʽ��ֵ
	 */
	public static double getValue(double[][] p, int n) {
		if (n == 1) // �����һ������ʽ,ֱ�ӷ��ظ�Ԫ��
			return p[0][0];

		double sum = 0; // �ۼ���ͱ���

		for (int j = 0; j < n; j++) {// �������һ�и�Ԫ��,p[n - 1][j]
			int pt = (n - 1) + j; // �����ж�ָ��

			double[][] p1 = new double[n][n];

			// �˹�����Ϊ�˰�����ʽ��ŵ���ʱ�����У����ı䵫ǰ����ʽ
			for (int row = 0; row < n; row++) {
				for (int col = 0; col < n; col++) {
					p1[row][col] = p[row][col];
				}
			}

			// �˹��̣���Ϊ�˽�Ԫ�� p[n-1][j] ������֮���ÿһ����ǰ�ƶ�һ�У�Ϊ�����ȡ��Ԫ�ض�Ӧ������ʽ��׼��
			for (int index = 0; index < n - 1; index++) {
				for (int index1 = j; index1 < n - 1; index1++) {
					p1[index][index1] = p1[index][index1 + 1];
				}
			}

			// �˹��̣���ȡ��ʱ���� p1 ���Ͻ� n-1 ������ʽ����ȡԪ�� p[n-1][j] ������ʽ
			double[][] temp = new double[n - 1][n - 1];
			for (int row = 0; row < n - 1; row++) {
				for (int col = 0; col < n - 1; col++) {
					temp[row][col] = p1[row][col];
				}
			}

			// ��ͣ�sum += Ԫ�� * ���� * ����ʽ
			// ��Ϊ������ʽ��ȥ��ĳһԪ�����ڵ��к���֮��ʣ�µ�Ԫ�ع��ɵ� n-1 ������ʽ
			// ��������ʽ���ʻ�������ʽ��������Ҫ�ݹ�������ʽ��ֵ
			sum += p[n - 1][j] * Math.pow(-1, pt) * getValue(temp, n - 1);
			// System.out.println(p[n - 1][j] + " * " + Math.pow(-1, pt) + " * " +
			// GetValue(p1, n - 1));
		}

		return sum;
	}

	/**
	 * ��γ��ɳڷ���SOR,Ŀǰ����֪������
	 * �����Է�����
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
			// ������ʽ����
			for (i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// ��Jacobi�Ĳ�֮ͬ��
					if (i < j) {
						sum += a[i][j] * y[j];
					} else if (i > j) {
						sum += a[i][j] * x[j];
					}
				}
				y[i] = (1 - m) * x[i] + (m * (b[i] - sum)) / a[i][i];
				sum = 0;
			}
			// �ﵽ���Ⱥ���ֹ
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
	 * ����˷� a���b��������a������x�����b������y���ʱ�ɽ������ a��b�õ����¾���c��c������y����a��������c������x����b������
	 * 
	 * @param a
	 *            ��˵ľ���
	 * @param b
	 *            ��˵ľ���
	 * @return a��b�õ����¾���
	 */
	public static double[][] matrixMultiplication(double a[][], double b[][]) {
		// ��a�����������b�����������ʱ�����ܽ��е�ˣ�����null
		if (a[0].length != b.length)
			return null;
		// c���������y��������x
		int y = a.length;
		int x = b[0].length;
		double c[][] = new double[y][x];
		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++)
				// c����ĵ�i�е�j������Ӧ����ֵ������a����ĵ�i�зֱ����b����ĵ�j��֮��
				for (int k = 0; k < b.length; k++)
					c[i][j] += a[i][k] * b[k][j];
		LineAlgebraUtil.sysoLineAlgebra(c, 2, 3);
		return c;
	}

	/**
	 * ����ӷ���������a�����������������b���������������ʱ�ɽ�����ӣ� a��b�õ����¾���c��c���������������ھ���a������������
	 * 
	 * @param a
	 *            ��ӵľ���
	 * @param b
	 *            ��ӵľ���
	 * @return a��b�õ����¾���
	 */
	public static double[][] matrixAdd(double a[][], double b[][]) {
		// ��a�����������������b�����������������ʱ�����ܽ��мӷ�������null
		if (a[0].length != b[0].length && a.length != b.length)
			return null;
		// c���������y��������x
		int y = a.length;
		int x = a[0].length;
		double c[][] = new double[y][x];
		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++) {
				// c����ĵ�i�е�j������Ӧ����ֵ������a[i][j]+b[i][j]
				c[i][j] = a[i][j] + b[i][j];
			}
		return c;
	}

	/**
	 * �����ת��
	 * 
	 * @param Matrix
	 *            ��ת�õľ���
	 * @param Line
	 *            ���������
	 * @param List
	 *            ���������
	 * @return ������Matrixת�ú�ľ���
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
	 * ��������
	 * 
	 * @param a
	 *            Double��n*n��ά���飬��ʼʱΪԭ���󣬷���ʱΪ�����
	 * @param n
	 *            ����Ľ���
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
			// ����Ԫ
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
			// �������У�����Ԫ������ k �� k ����
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
			// ����
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
		// �ָ����д���
		for (j = 0; j < n; j++)
			for (i = 0; i < n; i++)
				a[p[i]][j] = b[i][j];
	}

	/**
	 * ��������
	 * 
	 * @param Matrix
	 *            ���󷽳�
	 * @param error_
	 *            -1
	 * @param List
	 *            ����Ľ���
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
