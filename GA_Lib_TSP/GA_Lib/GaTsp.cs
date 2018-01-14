using System;
using System.IO;
using System.Collections.Generic;
using System.Text;
using Metaheuristic;

namespace GA_Lib
{
    class Program : GA      //TSP Problem Solver Class
    {
        int numRepetitiveRuns = 1;  //重複執行次數
        int city  = 34;
        double[,]   distance;
        double[,]   node;
        int         count = 0;
        double resultbest = double.MaxValue;
        static double DisplayStatisticInfo(int N, double Sum, double SumOfSQR)
        {
            double STDDev = Math.Sqrt((N * SumOfSQR - Math.Pow(Sum, 2)) / Math.Pow(N, 2));       //Caculate Standard Deviation
            Console.WriteLine("=========================================");
            Console.WriteLine("           Average = {0}", Sum / N);
            Console.WriteLine("Standart Deviation = {0}", STDDev);
            return STDDev;
        }
        static void Main(string[] args)
        {
            Program ga = new Program();
            double Sum = 0.0, SumOfSQR = 0.0;
            for (int i = 0; i < ga.numRepetitiveRuns; i++)
            {
                Console.WriteLine("=========================================");
                Console.WriteLine("Exam : {0}th time", i + 1);
                ga.init_bays29();
                ga.Init(50, 34, 0, 33, GAOption.EncodingType.Integer, GAOption.RepeatableOption.NonRepeatable);
                ga.SetStrategy(GAOption.Select.Tournament, GAOption.Crossover.NonRepeatableInteger.OX, GAOption.Mutation.NonRepeatableInteger.Two_Swap);
                ga.Run(10000, 0.8, 0.3);
                Sum += ga.GBestFitness;
                SumOfSQR += Math.Pow(ga.GBestFitness, 2);
                Console.WriteLine("GBEST FIT = {0}", ga.GBestFitness);
                if(ga.GBestFitness < ga.resultbest)
                    ga.resultbest = ga.GBestFitness;
            }
            double SSD = DisplayStatisticInfo(ga.numRepetitiveRuns, Sum, SumOfSQR);
            
            StreamWriter fileWriter = new StreamWriter("GA-23TSP.csv");
            fileWriter.WriteLine("Best, Average, Std Deviation");
            fileWriter.WriteLine(ga.resultbest + "," + Sum / ga.numRepetitiveRuns + "," + SSD);
            //*****************  最佳解內容  *******************************
             
            Console.Write("GBest tour: ");
            for (int i = 0; i < ga.GBest.Length; i++)
            {
                Console.Write("{0} ", ga.GBest[i]);
            }
            Console.WriteLine();
            for (int i = 0; i < ga.GBest.Length; i++)
            {
                Console.WriteLine("{0} ", ga.GBest[i]);
            }
            fileWriter.WriteLine("GBest tour");
            for (int i = 0; i < ga.GBest.Length; i++)
            {
                fileWriter.Write("{0},", ga.GBest[i]);
            }
            
            //************************************************

            fileWriter.Close();
            Console.Read();
        }
        public override double Fitness(double[] solution)
        {
            count++;
            double sum = 0;
            for (int j = 0; j < solution.Length - 1; j++)
                sum = sum + distance[(int)solution[j], (int)solution[j + 1]];
            sum = sum + distance[(int)solution[solution.GetLength(0) - 1], (int)solution[0]];
            return sum;
        }
        //public void init_tsp()      //座標讀檔
        //{
        //    //StreamReader str1 = new StreamReader("tsp70.txt");
        //    StreamReader str1 = new StreamReader("tsp_34.txt");
        //    string all = str1.ReadToEnd();
        //    string[] data = all.Split(' ', '\n', ',');
        //    int count = 0;
        //    node = new double[city, 2];
        //    distance = new double[city, city];
        //    //Console.WriteLine(cost.GetLength(0));
        //    for (int i = 0; i < city; i++)
        //    {
        //        for (int j = 0; j < 2; j++)
        //        {
        //            node[i, j] = Convert.ToDouble(data[count]);
        //            count++;
        //        }
        //    }
        //    for (int i = 0; i < city; i++)
        //    {
        //        for (int j = 0; j < city; j++)
        //        {
        //            //此處用Euclidean distance; 進階版應改成經緯度弧線距離
        //            distance[i, j] = Math.Sqrt(Math.Pow(node[i, 0] - node[j, 0], 2.0) + Math.Pow(node[i, 1] - node[j, 1], 2.0));
        //        }
        //    }
        //}

        //public void init_tsp2()
        //{
        //    StreamReader str1 = new StreamReader("tsp_34.txt");
        //    string all = str1.ReadToEnd();
        //    string[] data = all.Split(' ', '\n', ',');
        //    int count = 0;
        //    distance = new double[city, city];

        //    for (int i = 0; i < city; i++)
        //    {
        //        for (int j = 0; j < city; j++)
        //        {
        //            distance[i, j] = Convert.ToDouble(data[count]);
        //            count++;
        //        }
        //    }
        //}  //Distance 矩陣讀檔

        public void init_bays29()
        {
            distance = new double[city, city];
            StreamReader str = new StreamReader("tsp_34.txt"); //n*n distance matrix
            string all = str.ReadToEnd();
            string[] c = all.Split(new char[] { ' ', '\n' });
            int index = 0;
            for (int i = 0; i < 34; i++)
            {
                for (int j = 0; j < 34; j++)
                {
                    for (int k = index; k < c.Length; k++)
                    {
                        if (c[k] != "")
                        {
                            index = k + 1;
                            break;
                        }
                    }
                    distance[i, j] = Double.Parse(c[index - 1]);
                    //Visibility[i, j] = 1 / distance[i][j];
                }
            }
        }
        //Demo : Advanced GA_Proces Customization
        /*
        public override void Run(int Iteration, double PC, double PM)
        {
            
            //this.PC = PC;
            //this.PM = PM;
          
            //Generate_Population(ENOCODE.NonRepeatNumetric);
          
            for (int i = 0; i < Iteration; i++)
            {
                //Evaluate_Population();
                
                //Reproduce_Population(GAOption.Select.Elite);

                //Crossover_Population(GAOption.Crossover.NonRepeatNumetric.Order, PC);
                
                //Mutation_Population(GAOption.Mutation.Numetric.Swap_Two_Position, PM);
            }
            //Evaluate_Population();
        }
         */
    }
}
