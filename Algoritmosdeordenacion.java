/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosdeordenacion;

import java.util.Random;

/**
 *
 * @author Gabriel
 */
public class Algoritmosdeordenacion {

    public static void main(String[] args) {

        long start, end;
        //int[] numeros = {5, 6, 1, 0, 3};
        int[] numeros= new int[100000];
        for(int i=0; i<numeros.length; i++){
            Random r;
            r = new Random();
            numeros[i]= r.nextInt(1000000);
        }
        
      
        start = System.currentTimeMillis();
        int[] ordenadoburbuja = Metodoburbuja(numeros);
        end = System.currentTimeMillis();
        System.out.println("Metodo burbuja");
        //imprimir(ordenadoburbuja);
        Time(start, end);

        start = System.currentTimeMillis();
        int[] Otroordenadoburbuja = Otrometodoburbuja(numeros);
        end = System.currentTimeMillis();
        System.out.println("Metodo burbuja mejorado");
        //imprimir(Otroordenadoburbuja);
        Time(start, end);

        start = System.currentTimeMillis();
        int[] ordenadoinserccion = Inserccion(numeros);
        end = System.currentTimeMillis();
        System.out.println("Metodo Inserccion");
        //imprimir(ordenadoinserccion);
        Time(start, end);

        start = System.currentTimeMillis();
        int[] ordenadoSeleccion = Seleccion(numeros);
        end = System.currentTimeMillis();
        System.out.println("Metodo seleccion");
        //imprimir(ordenadoinserccion);
        Time(start, end);

        start = System.currentTimeMillis();
        int[] ordenadometgesort = Mergesort(numeros, 0, numeros.length);
        end = System.currentTimeMillis();
        System.out.println("Metodo Mergesor");
        //imprimir(ordenadoinserccion);
        Time(start, end);

        start = System.currentTimeMillis();
        int[] ordenadoquicksort = Quicksort(numeros, 0, numeros.length - 1);
        end = System.currentTimeMillis();
        System.out.println("Metodo Quick sort");
        //imprimir(ordenadoinserccion);
        Time(start, end);
    }

    public static void Time(long start, long end) {
        System.out.println("Tardo en ejecutarse " + (end - start) + " S.");
    }

    public static int[] Quicksort(int[] numeros, int a, int b) {
        int desde = a, hasta = b;
        int aux;
        int pivote = numeros[(desde + hasta) / 2];
        while (desde <= hasta) {
            while (numeros[desde] < pivote) {
                desde++;
            }
            while (numeros[hasta] > pivote) {
                hasta--;
            }
            if (desde <= hasta) {
                aux = numeros[desde];
                numeros[desde] = numeros[hasta];
                numeros[hasta] = aux;
                desde++;
                hasta--;
            }
        }
        if (a < hasta) {
            Quicksort(numeros, a, hasta);
        }
        if (desde < b) {
            Quicksort(numeros, desde, b);
        }
        return numeros;
    }

    public static int[] Mergesort(int[] numeros, int init, int n) {
        //Dividir
        int n1, n2;
        if (n > 1) {
            n1 = n / 2;
            n2 = n - n1;
            Mergesort(numeros, init, n1);// Volver aplicar metgesort del primer subgrupo
            Mergesort(numeros, init + n1, n2);//Aplicar metgesort subgrupo dos
            numeros = Merge(numeros, init, n1, n2);
        }
        return numeros;
    }

    public static int[] Merge(int[] numeros, int init, int n1, int n2) {
        int[] auxiliar = new int[n1 + n2];
        int temp = 0, temp1 = 0, temp2 = 0;
        while ((temp1 < n1) && (temp2 < n2)) {
            if (numeros[init + temp1] < numeros[init + n1 + temp2]) {
                auxiliar[temp++] = numeros[init + (temp1++)];
            } else {
                auxiliar[temp++] = numeros[init + 1 + (temp2++)];
            }
        }
        while (temp1 < n1) {
            auxiliar[temp++] = numeros[init + (temp1++)];
        }
        while (temp2 < n2) {
            auxiliar[temp++] = numeros[init + n1 + (temp2++)];
        }
        for (int i = 0; i < n1 + n2; i++) {
            numeros[init + i] = auxiliar[i];
        }
        return numeros;
    }

    public static int[] Seleccion(int[] numeros) {
        int menor, posicion;
        for (int i = 0; i < numeros.length; i++) {
            menor = numeros[i];
            posicion = i;
            for (int j = i + 1; j < numeros.length; j++) {
                if (menor > numeros[j]) {
                    menor = numeros[j];
                    posicion = j;
                }
            }
            if (i != posicion) {
                numeros[posicion] = numeros[i];
                numeros[i] = menor;
            }
        }
        return numeros;
    }

    public static int[] Metodoburbuja(int[] numeros) {
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros.length - 1; j++) {
                if (numeros[j] > numeros[j + 1]) {
                    //intercambiar numeros
                    int aux = numeros[j + 1];
                    numeros[j + 1] = numeros[j];
                    numeros[j] = aux;
                }
            }
        }
        return numeros;
    }

    public static int[] Otrometodoburbuja(int[] numeros) {
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < i; j++) {
                if (numeros[j] > numeros[j + 1]) {
                    //intercambiar numeros
                    int aux = numeros[j + 1];
                    numeros[j + 1] = numeros[j];
                    numeros[j] = aux;
                }
            }
        }
        return numeros;
    }

    public static int[] Inserccion(int[] numeros) {
        int num, j;
        for (int i = 1; i < numeros.length; i++) {
            num = numeros[i];
            j = i - 1;
            while ((numeros[j] > num) && (j >= 0)) {
                numeros[j + 1] = numeros[j];
                j--;
            }
        }
        return numeros;
    }

    public static void imprimir(int[] numeros) {
        System.out.print("El array numeros ordenado es: {");
        for (int i = 0; i < numeros.length-1; i++) {
            System.out.print(numeros[i]+", ");
        }
        System.out.print(numeros.length-1+"}\n");
    }
}
