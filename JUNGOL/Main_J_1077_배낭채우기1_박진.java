// Intermediate Coder - 1077 : 배낭채우기1

import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W = sc.nextInt();
        int[] weight = new int[N];
        int[] value = new int[N];
        double[] valuePerWeigth = new double[N];
        int weight_sum = 0;
        int result = 0;
         
        for (int i = 0; i < N; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
            valuePerWeigth[i] = value[i]/(double)weight[i];
        }
         
        int temp;
        double temp2;
         
        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                if (valuePerWeigth[i] < valuePerWeigth[j]) {
                    temp2 = valuePerWeigth[i];
                    valuePerWeigth[i] = valuePerWeigth[j];
                    valuePerWeigth[j] = temp2;
                     
                    temp = weight[i];
                    weight[i] = weight[j];
                    weight[j] = temp;
                     
                    temp = value[i];
                    value[i] = value[j];
                    value[j] = temp;
                }
                else if (valuePerWeigth[i] == valuePerWeigth[j]) {
                    if (weight[i] > weight[j]) {
                        temp2 = valuePerWeigth[i];
                        valuePerWeigth[i] = valuePerWeigth[j];
                        valuePerWeigth[j] = temp2;
                         
                        temp = weight[i];
                        weight[i] = weight[j];
                        weight[j] = temp;
                         
                        temp = value[i];
                        value[i] = value[j];
                        value[j] = temp;
                    }
                }
            }
        }
         
        for (int i = 0; i < N; i++) {
            if ( (weight_sum + weight[i]) <= W ) {
                weight_sum = weight_sum + weight[i];
                result = result + value[i];
                i--;
            }
        }
         
        System.out.println(result);
         
        sc.close();
    }
}
