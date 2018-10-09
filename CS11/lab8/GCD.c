/* 
 * Sphere.c
 * Sudeep Baniya
 * sucbaniy
 * lab8
 * Find the GCD of two user inputed numbers
*/
#include<stdio.h>
#include<stdlib.h>

int main(){
   int i, j, n, m;
   int x , y, z;
   char str[100];
   
   for (i=0; i<1; i++){
       
       printf("Enter a positive integer: ");
       while (1){
          n = scanf(" %d", &x);
          while(n!=1){
             scanf("%s", str);
             printf("Please enter a positive integer: ");
             n = scanf(" %d", &x);
           }
           
           if (x>0) break;
           printf("Please enter a positive integer; ");
         }
       }
       
       for (j=0; j<1; j++){
    
          printf("Enter another positive integer: ");
          while(1){
             m = scanf("%d", &y);
             while (n!=1){
                scanf("%s",str);
                printf("Please enter a positive integer: ");
                m = scanf(" %d", &y);
               }
               
               if (y>0) break;
               printf("Please enter a positive integer: ");
              }
              printf("The GCD of %d and %d is ", x , y);
             }
             while(x!=y){
                if(x>y)
                x-=y;
                else
                y-=x;
            }
            printf("%d\n",x);
            return 0;
}
