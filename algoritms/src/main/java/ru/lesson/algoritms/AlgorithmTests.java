package ru.lesson.algoritms;

public class AlgorithmTests {

  public static void main(String[] args) {
    //poiskMinVMassive(new int[]{1, 2, 5, 8, 20, 32, -5, 7, 19});
    //sortByBubble(new int[]{1, 2, 5, 8, 20, 32, -5, 7, 19});
    sortViborom(new int[]{2, 5, 1, 20, 8, 32, 5, 7, 19});
  }

  //Поиск минимального элемента в массиве------------------------
  public static void poiskMinVMassive(int[] arr) {
    int minValue = arr[0];
    int minIndex = 0;

    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < minValue) {
        minValue = arr[i];
        minIndex = i;
      }
    }
    System.out.println("Минимальное значение: " + minValue);
    System.out.println("Индекс: " + minIndex);
  }

  //Сортировка элементов массива методом пузырька---------------------------
  public static void sortByBubble(int[] arr) {
    boolean isSorted = false;
    while (!isSorted) {
      isSorted = true;
      for (int i = 1; i < arr.length; i++) {
        if (arr[i] < arr[i - 1]) {
          int temp = arr[i];
          arr[i] = arr[i - 1];
          arr[i - 1] = temp;
          isSorted = false;
        }
      }
      printArray(arr);
    }
  }

  //Сортировка выбором-----------------------
  public static int min(int[] arr, int start) {
    int minValue = arr[start];
    int minIndex = start;

    for (int i = start + 1; i < arr.length; i++) {
      if (arr[i] < minValue) {
        minValue = arr[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

  public static void sortViborom(int[] arr) {
    for (int step = 0; step < arr.length; step++) {
      printArray(arr);
      int index = min(arr, step);
      int tmp = arr[step];
      arr[step] = arr[index];
      arr[index] = tmp;
    }
  }



  private static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println("");
  }
}
