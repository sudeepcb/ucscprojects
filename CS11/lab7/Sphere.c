/* 
 * Sphere.c
 * Sudeep Baniya
 * sucbaniy
 * lab7
 * Simple program to find surface area and volume
*/

#include<stdio.h>
#include<math.h>

int main(){
   double radius, volume, surfacearea;
   const double pi = 3.141592654;

   printf("Enter radius of the sphere: ");
   scanf("%lf", &radius);
   volume = 4.0/3.0*pi*pow(radius,3);
   surfacearea = 4*pi*pow(radius,2);
   printf("The volume is %f ",volume);
   printf("and the surface area is %f.\n",surfacearea);

   return 0;
}
