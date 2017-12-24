package com.example.drswitch.random;

/**
 * Created by DrSwitch on 24.12.2017.
 */

public class MyRandom {
    static int k=115; // случайное большое целое число
    static int m=58474; // случайное большое целое число
    static int A0=500;
    static int N=10000;

    static int intk=10; //количество интервалов

    static float[] A = new float[N];
    static float[] Z = new float[N];

    public String outtext(){
        String str="";
        /*
        float[] A = new float[N];
		float[] Z = new float[N];
		*/

        int[] n = new int[intk]; // количество циферок на каждом интервале

        A[0]=A0;
        Z[0]=A[0]/m;
        int flag=0, T=10000;
        for(int i=1;i<N;i++){
            A[i]=(k*A[i-1])%m;
            Z[i]=A[i]/m;
            //System.out.format("A[%d] = %9.0f | Z[%d] = %1.4f%n", i,A[i],i,Z[i]);

            if(flag==0){
                if((A[1]==A[i])&&(i>1)){
                    T=i;
                    flag=1;
                }
            }
        }
        float del=intk;
        float fi=0;
        float li=1/del;

        float p; //вероятность
        float nf=N;
        float sumP=0; //сумма квадратов отклонений
        float sumS=0; //сумма абсолютных отклонений
        float maxotkl=0; //максимальное отклонение
        for(int i=0;i<intk;i++){
            n[i]=0;
            for(int j=0;j<N;j++){
                if((Z[j]<li)&&(Z[j]>fi)){
                    n[i]++;
                }
            }
            p=n[i]/nf;
            sumP=sumP+(p-1/del)*(p-1/del);
            sumS=sumS+(Math.abs(p-1/del));
            if(maxotkl<p) maxotkl=p;
            str += "На n[" +i+ "] (от "+fi+" до "+li+") кол циферок = "+n[i]+"\nВероятность = "+p+"\n";
            //System.out.format("на n[%d] (от %1.4f до %1.4f) кол циферок = %5d | Вероятность = %.2f%n", i,fi,li,n[i],p);
            fi=li;
            li=li+1/del;
        }

        str += "\n";

        float sumZ=0; //мат ожидание
        for(int i=0;i<N;i++){
            sumZ=sumZ+Z[i];
        }
        sumZ=sumZ/N;

        float sumD=0; //дисперсия
        for(int i=0;i<N;i++){
            sumD=sumD+Z[i]*Z[i];
        }
        sumD=sumD/N;
        sumD=sumD-sumZ*sumZ;

        //тестирование независимости ДСВ
        float R1=0;
        int S=3;
        float fN=N, fS=S;
        for(S=1;S<4;S++){
            for(int i=0;i<N-S;i++){
                R1=R1+Z[i]*Z[i+S];
            }
            R1=-3+(12/(fN-fS))*R1;
            str += R1 + "\n";
            //System.out.format("%n%.9f",R1);
        }
        str += "\n";
        for(S=1;S<4;S++){
            fS=S;
            str += dsv(fN,fS) + "\n";
            System.out.format("%n%.9f",dsv(fN,fS));
        }

        str += "\n";

        str += "T = " + T + " Флаг = " + flag +
                "\nСумм Кватдрата отклонения = " + sumP +
                "\nCумма абсолютных отклонений = " + sumS +
                "\nМаксимальное отклонение = " + maxotkl +
                "\nМат ожидание = " + sumZ +
                "\nДисперсия = " + sumD;


       /* System.out.format("%nЦиферка T >= %d%nФлаг = %d"
                        + "%nСумм Квад откл = %.9f"
                        + "%nCумма абсолютных отклонений = %.9f"
                        + "%nМаксимальное отклонение = %.9f"
                        + "%nМат ожидание = %.9f"
                        + "%nДисперсия = %.9f"
                , T,flag,sumP,sumS,maxotkl,sumZ,sumD);
            */

        return str;
    }



    //забацать функции
    static float dsv(float fN, float fS){
        float R1=0;
        int S=1;
        for(int i=0;i<N-S;i++){
            R1=R1+Z[i]*Z[i+S];
        }
        R1=-3+(12/(fN-fS))*R1;

        return R1;
    }
}
