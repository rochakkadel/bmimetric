import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BMI_CSC215_Metric_RochakKadel
{

    static Scanner input = new Scanner(System.in);

    //main method
    public static void main(String[] args)
    {

        welcomeMessage();

        String name = getName();

        int height = getHeight(name);

        double currentWeight = getCurrentWeight(name);

        double bmi = bmiCalculation(height, currentWeight);

        String weightStatus = weightStatus(bmi);

        summaryReport(name, bmi, weightStatus);

        double[] lowHighWeight = getLowHighWeight(name);
        double lowWeight = lowHighWeight[0];
        double highWeight = lowHighWeight[1];

        tableReport(height, currentWeight, lowWeight, highWeight);

        endingMessage();

    }


    //METHODS

    //welcome message
    static void welcomeMessage() {
        System.out.println();
        System.out.println(
                "-------------------------------------------------------------------------" );
        System.out.println("-- Wellcome to:" );
        System.out.printf("-- %32s Computation, Rochak Kadel, Metric Version   \n", "BODY MASS " +
                "INDEX" +
                "(BMI)" );
        System.out.println(
                "-------------------------------------------------------------------------" );
        System.out.println();
    }


    //get name
    static String getName() {
        System.out.print("Please enter your full name: " );
        return input.nextLine();
    }


    //get height
    static int getHeight(String name)
    {
        System.out.printf("Please enter height in centimeters for %s: ", name);
        return input.nextInt();
    }


    //get weight
    static double getCurrentWeight(String name)
    {
        System.out.printf("Please enter weight in kilograms for %s: ", name);
        return input.nextDouble();
    }


    // get high or low weight
    static double[] getLowHighWeight(String name)
    {
        double[] weight = new double[2];

        System.out.printf("Please enter LOW weight in kilograms for %s: ", name);
        weight[0] = input.nextDouble();

        System.out.printf("Please enter HIGH weight in kilograms for %s: ", name);
        weight[1] = input.nextDouble();

        return weight;
    }


    //calculate BMI
    static double bmiCalculation(int height, double weight)
    {
        return weight / Math.pow(height, 2) * 10000;
    }

    //calculate weight status
    static String weightStatus(double bmi)
    {
        return (bmi < 18.5) ? "Underweight" :
                (bmi <= 24.9) ? "Healthy Weight" :
                  (bmi <= 29.9) ? "Overweight" : "Obesity";
    }


    //summary report
    static void summaryReport(String name, double bmi, String weightStatus)
    {
        System.out.println();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a" ));

        System.out.printf("-- SUMMARY REPORT for %s\n", name.toUpperCase());
        System.out.printf("-- Date and Time: %25s \n", time);

        int rounded = (int) Math.round(bmi);
        System.out.printf("-- BMI: %21.2f (or %d if rounded) \n", bmi, rounded);

        System.out.printf("-- %-20s %s \n", "Weight Status: ", weightStatus);
        System.out.println();
    }


    //form table
    static void tableReport(int height, double currentWeight, double lowWeight, double highWeight)
    {
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s | %-8s | %-35s | \n", "Weight", "BMI", "WEIGHT");
        System.out.println("-------------------------------------------------------------");

        String yellowHighlight = "\u001B[43;30m";
        String reset = "\u001B[0m";

        double bmi = bmiCalculation(height, lowWeight);
        String status = weightStatus(bmi);
        System.out.printf("%-10.2f | %-8.3f | %-20s %s(LOW)%s %10s \n", lowWeight, bmi, status,
                yellowHighlight,reset, "|");

        for(double weight = lowWeight + 2.5; weight <= highWeight; weight += 2.50)
        {
            bmi = bmiCalculation(height, weight);
            status = weightStatus(bmi);
            System.out.printf("%-10.2f | %-8.3f | %-35s | \n", weight, bmi, status);


            if(weight < currentWeight && weight + 2.5 > currentWeight)
            {
                bmi = bmiCalculation(height, currentWeight);
                String currentWeightStatus = weightStatus(bmi);
                System.out.printf("%-10.2f | %-8.3f | %-35s | \n",
                        currentWeight, bmi, currentWeightStatus + " (this)");
                weight = currentWeight;
            }
        }

        bmi = bmiCalculation(height, highWeight);
        status = weightStatus(bmi);
        System.out.printf("%-10.2f | %-8.3f | %-20s %s(HIGH)%s %9s \n",
                highWeight, bmi, status, yellowHighlight, reset, "|");

        System.out.println("-------------------------------------------------------------");
        System.out.println();
    }


    //ending message
    static void endingMessage()
    {
        System.out.println();
        System.out.println(
                "-------------------------------------------------------------------------");
        System.out.println("-- Thank you for using my program");
        System.out.println(
                "-------------------------------------------------------------------------");
        System.out.println();
    }

}
