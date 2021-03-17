package Evaluacion_Tecnica_CT;

import java.util.*;

public class Main {
	
	public static void ej1() {
		int maxValue = Integer.MIN_VALUE;
		int[] arrayOfIntegers = {5, 8, 0, -10, 44, 89, 1, 3, 7, 77, 12, -3, 4};
		int arraySize = arrayOfIntegers.length;
		for (int i = 0; i < arraySize; i++) {
			if (maxValue == Integer.MIN_VALUE)
				maxValue = arrayOfIntegers[i];
			else
				if (maxValue < arrayOfIntegers[i])
					maxValue = arrayOfIntegers[i];
		}
		System.out.print("El mayor valor encontrado en el arreglo es: " + maxValue);
	}
	public static void ej2() {
		int sum = 0;
		int[] arrayOfIntegers = {6, 7, 5, 4, 3, 1, 2, 3, 5, 6, 7, 9, 0, 0, 1, 2, 4, 1, 2, 3, 5, 1, 2};
		int i = 0;
		int maxSum = 13;
		int arraySize = arrayOfIntegers.length;
		while (i < arraySize) {
			int j=i;
			while ((j < arraySize) && (sum < maxSum)) {
				sum = sum + arrayOfIntegers[j];
				if (sum == maxSum) {
					System.out.print("Los elementos entre (" + i + ", "  + j + ") suman " + maxSum + "\n");
				}
				j++;
			}
			sum=0;
			i++;
		}
	}
	
	/*
	public static int getListSize_ej3(List list) {
		if (list == null)
		return 0;
		else
		{
			int listSize = 1;
			List auxList = list;
			while (auxList.getNext() != null) {
				listSize ++;
			}
			auxList = list;
			while (auxList.getPrevious() != null) {
				listSize ++;
			}
		return listSize;
		}
	*/
	
	public static boolean es_palindromo_ej4(String palindrome) {
		String auxString = palindrome.toLowerCase();
		auxString = auxString.replace(" ", "");
		int i = 0;
		int j = auxString.length()-1;
		int half = j/2;
		System.out.print(auxString.charAt(i));
		System.out.print(auxString.charAt(j));
		while ((auxString.charAt(i) == auxString.charAt(j)) && (i < half))
		{
			System.out.print("i: " + i);
			System.out.print("j: " + j);
			i++;
			j--;
		}
		if (i < half)
			return false;
		return true;
	}
	
	public static void showMatrix_ej5(char matrix[][], int n, int m) {
		for (int a=0; a < n; a++) {
			for (int b=0; b < m; b++) {
				System.out.print(matrix[a][b]+" ");
			}
			System.out.print("\n");
		}
	}
	public static char[][] initializeMatrix_ej5(int n, int m) {
		//Metodo que inicializa la matriz segun columnas y filas dadas,
		//utilizando una probabilidad para generar obstaculos en el camino.
		char matrix[][] = new char[n][m];
		for (int f = 0; f < n; f++) {
			for (int c = 0; c < m; c++) {
				if ((f==0 && c==0) || (f==(n-1) && c==(m-1)))
					matrix[f][c] = ' ';
				else
				{
					//Como la casilla (0, 0) y (n, m) deben estar libres, considero los casos especiales,
					//para que ambas casillas permanezcan sin obstaculos.
					if (Math.random() < 0.3)
						matrix[f][c] = 'x';
					else
						matrix[f][c] = ' ';
				}
			}
		}
		return matrix;
	}
	public static boolean validPosition_ej5(int position[], int n, int m) {
		//Funcion que me asegura que no me vaya de rango de la matriz
		if ((0 <= position[0]) && (position[0] < n) && (0 <= position[1]) && (position[1] < m))
			return true;
		return false;
	}
	public static void matrixPathBacktracking_ej5(char matrix[][], List<String> solution,int fileMovement[], int columnMovement[], int position[], boolean found[], int n, int m) {
		//Funcion que busca un camino válido desde la posicion inicial,
		//hasta la posicion (n,m) de la matriz, utilizando la tecnica de backtracking
		if (!found[0]) {
			if ((position[0] == (n-1)) && position[1] == (m-1))
			{
				//Si llego a la posición objetivo, imprimo todo el camino desde el inicio hasta el final
				System.out.println("Resultado v�lido: ");
				for (int it = 0; it < solution.size(); it++) {
					System.out.println(solution.get(it) + " ");
				}
				found[0] = true;
			}
			
			else if ((validPosition_ej5(position, n, m)) && matrix[position[0]][position[1]] != 'x' )
			{
				int i = 0;
				while ((!found[0]) && (i < 4))
				{
					//Me muevo hacia una de las 4 opciones
					matrix[position[0]][position[1]] = 'p';
					position[0] = position[0] + fileMovement[i];
					position[1] = position[1] + columnMovement[i];
					//Si no hay nada en esa posicion, coloco una p para marcar donde estoy parado
					if ((validPosition_ej5(position, n, m)) && matrix[position[0]][position[1]] != 'p' )
					{
						int size = solution.size();
						String auxString = "("+ position[0] + ", " + position[1] + ")";
						solution.add(size,auxString);
						matrixPathBacktracking_ej5(matrix, solution, fileMovement, columnMovement, position, found, n, m);
						solution.remove(size);
					}
					//Vuelvo mi paso hacia atras
					position[0] = position[0] - fileMovement[i];
					position[1] = position[1] - columnMovement[i];
					matrix[position[0]][position[1]] = ' ';
					i++;
				}
			}
		}
	}
	public static void ej5(int n, int m) {
		char matrix[][] = initializeMatrix_ej5(n,m);
	    int fileMovement[]= {1, 0, -1, 0};
	    int columnMovement[]= {0, 1, 0, -1};
	    int position[] = {0,0};
	    boolean found[] = {false};
		showMatrix_ej5(matrix, n, m);
		List<String> movements = new ArrayList<String>();
		matrixPathBacktracking_ej5(matrix, movements, fileMovement, columnMovement, position, found, n, m);
		if(!found[0])
			System.out.println("No existe un camino valido para esta matriz");
	}
	public static void ej4() {
		String palindromeString = "Misiones";
		boolean b = es_palindromo_ej4(palindromeString);
		System.out.print(b);
	}
	
	public static void main(String[] args) {
		/*
		ej1();
		ej2();
		ej4();
		*/
		ej5(4, 5);
	}
}