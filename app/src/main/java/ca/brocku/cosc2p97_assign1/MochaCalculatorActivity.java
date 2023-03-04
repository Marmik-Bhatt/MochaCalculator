package ca.brocku.cosc2p97_assign1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.lang.*;
import java.util.EmptyStackException;

/*

By:Marmik Bhatt
ST#: 5939780
Date: Oct 19 2021
Credit given to the Exp4J library which converts the string to the arithmetic equation for solving
source: https://www.objecthunter.net/exp4j/v0.3.11/
 */

public class MochaCalculatorActivity extends AppCompatActivity {


// Instance variables for the various checks and inputs for the calculator
    private boolean WildOperator;
    private double calcOutput = 0.0;
    private int beginParCount = 0;
    private int EndParCount = 0;
    private TextView outputText;
    private String CalcExpression= "";
    private String selectedNumb;
    private boolean CalcType = false;
    private int OperatorCount = 0;

    // Used for the  net express library that was imported for evaluating the expression
    Expression exp;
    private String Pl="+";
    private String Min="-";
    private String Del="del";
    private String Times="*";
    private String Divide ="/";
    private String Equal= "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the button variables to the specific ID for the layouts
        Button Nine =  findViewById(R.id.nine);
        Button Eight =  findViewById(R.id.eight);
        Button Seven =  findViewById(R.id.seven);
        Button Six = findViewById(R.id.six);
        Button Five =  findViewById(R.id.five);
        Button Four = findViewById(R.id.four);
        Button Three =  findViewById(R.id.three);
        Button Two =  findViewById(R.id.two);
        Button One = findViewById(R.id.one);
        Button Zero =  findViewById(R.id.zero);

        Button Plus =  findViewById(R.id.plus);
        Button Minus =  findViewById(R.id.minus);
        Button times =  findViewById(R.id.Times);
        Button Div =  findViewById(R.id.div);
        Button LeftP =  findViewById(R.id.leftP);
        Button RightP =  findViewById(R.id.rightP);
        Button decimal = findViewById(R.id.dot);
        Button equal=  findViewById(R.id.Equal);
        Button clear =  findViewById(R.id.Clear);
        Button del =   findViewById(R.id.Delete);

        outputText =  findViewById(R.id.calcOut);
        outputText.setText("");

        //Assigning a listener for each button
        Nine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {SelectedNum(9);}

        });

        Eight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                SelectedNum(8);
            }

        });

        Seven.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                SelectedNum(7);
            }

        });
        Six.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                SelectedNum(6);
            }

        });
        Five.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                SelectedNum(5);
            }

        });

        Four.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                SelectedNum(4);
            }

        });
        Three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                SelectedNum(3);
            }

        });
        Two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                SelectedNum(2);
            }

        });

        One.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                SelectedNum(1);
            }

        });

        Zero.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                SelectedNum(0);
            }

        });

        decimal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                CalcType = true;
                SelectedSymbol(".");
            }

        });
        LeftP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                CalcType = true;
                SelectedSymbol("(");
            }

        });
        RightP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {

                CalcType = true;
                SelectedSymbol(")");
            }

        });

        times.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                CalcService(Times);
            }

        });
        Div.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                CalcService(Divide);
            }

        });
        Plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                CalcService(Pl);
            }

        });
        Minus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                CalcService(Min);
            }

        });
        del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                CalcService(Del);
            }

        });
        equal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                CalcService(Equal);
            }

        });

        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View views) {
                clearField();
            }

        });




    }

      /*
       This function checks which input was clicked and depending on the value clicked goes into the SelectedSymbol Function
       or number function.
       */
    public void CalcService(String value) {

        String inputVal = value;

        if(inputVal.equalsIgnoreCase("+")){

            SelectedSymbol(inputVal);
        }
        else if(inputVal.equalsIgnoreCase("-")){

            SelectedSymbol(inputVal);
        }
        else if  (inputVal.equalsIgnoreCase("*")) {

            SelectedSymbol("*");

        }  else if  (inputVal.equalsIgnoreCase("/")){

            SelectedSymbol("/");

        } else if (inputVal.equalsIgnoreCase("del")){

            try{
                 if(!this.CalcExpression.isEmpty()) {
                     //just for single digit deletion
                     if(this.CalcExpression.length()==1){

                         this.CalcExpression = "";
                         outputText.setText("");
                         clearField();

                     } else if(this.CalcExpression.length() > 1) {

                            //this takes the string
                         String resultingOp = this.CalcExpression.substring(0,this.CalcExpression.length()-1);
                         System.out.println(resultingOp);
                              this.CalcExpression = resultingOp;
                           this.WildOperator = false;
                     }

                 } else {
                     //Do nothing
                 }

                 // we need to check if after deletion of an Operand if there is any Operators which are not proceeded by an Operand
                if(this.CalcExpression.length() >0) {
                    char[] individualChars = this.CalcExpression.toCharArray();
                    char finalval = individualChars[individualChars.length - 1];
                    if (finalval == '+' || finalval == '-' || finalval == '*' || finalval == '/') {
                        this.WildOperator = true;

                    }
                }
           //setting the output of the text
                outputText.setText(this.CalcExpression);

            }catch(StringIndexOutOfBoundsException ex) {

            }

        } else if (inputVal.equalsIgnoreCase("=")) {
         //depending on the the type of Calculator Basic or Advanced will determine how the equation will be solved
            if(!this.CalcType) {
            //follows the operand->operator->operand-> operator rule
                BasicArith(this.CalcExpression,inputVal);

            } else {
               //does not to allow for negatives and parenthesis and such
                AdvancedArith(this.CalcExpression);
            }

        }
    }

    //this is to clear the output field
    public void clearField() {
     outputText.setText("");
     this.calcOutput = 0.0;
     this.selectedNumb = "";
     this.CalcExpression = "";
     this.OperatorCount = 0;
     this.beginParCount = 0;
     this.EndParCount = 0;
     this.CalcType = false;

    }

 //if a symbol is selected go into this function to add it to the output
    public void SelectedSymbol(String val) {

        String inputedSym = val;
        boolean repeatCheck = false;
        //this allows for negatives first
        if (this.CalcExpression.length() == 0 && val.equals("-")) {
            this.WildOperator = false;
            this.CalcExpression = this.CalcExpression.concat(inputedSym);
            this.outputText.setText(this.CalcExpression);
        }

        if (this.CalcExpression.isEmpty() && val.equals(".")) {

            //do nothing
        } else {
            //this is for the rule of repeating operators
            if (this.CalcExpression.length() >= 1) {
                char[] individualChars = this.CalcExpression.toCharArray();
                char finalval = individualChars[individualChars.length - 1];

                if ((finalval == '+' || finalval == '-' || finalval == '*' || finalval == '/')) {
                    if (val.equals("(")) {


                    } else {
                        repeatCheck = true;
                        this.CalcExpression = this.CalcExpression.substring(0, this.CalcExpression.length() - 1);
                        this.CalcExpression = this.CalcExpression.concat(inputedSym);
                        this.outputText.setText(this.CalcExpression);
                        this.WildOperator = true; // need to be careful w this
                    }

                } else {
                    //depending on the arithmetic type go and do the basic arithmetic if allowed
                    if (!this.CalcType) {
                        this.OperatorCount++;
                        if (this.OperatorCount > 1) {
                            BasicArith(this.CalcExpression, val);

                        }
                        if (!inputedSym.equals("=") && this.OperatorCount > 1) {
                            this.OperatorCount = 1;
                        } else {
                            if (inputedSym.equals("=")) {

                                BasicArith(this.CalcExpression, val);
                                this.OperatorCount = 0;

                            } else {
                                this.CalcExpression = this.CalcExpression.concat(inputedSym);
                                this.outputText.setText(this.CalcExpression);
                                this.WildOperator = true;
                            }
                        }
                    }
                }
            }
            //this does the housekeeping for if it is advanced arithmetic for decimals and Parenthesis
            if (this.CalcType) {
                if (val.equals("(")) {

                    this.WildOperator = true;
                    this.beginParCount = this.beginParCount + 1;

                    if (!repeatCheck) {

                        this.CalcExpression = this.CalcExpression.concat(inputedSym);
                        if(this.CalcExpression.contains(")(")){

                            this.WildOperator = true;
                        }
                    }
                    this.outputText.setText(this.CalcExpression);

                } else if (val.equals((")"))) {


                    this.EndParCount = this.EndParCount + 1;
                    this.WildOperator = false;
                    if(this.CalcExpression.contains("()") ||this.CalcExpression.contains("(.)") ){

                        this.WildOperator = true;
                    }

                    if (!repeatCheck) {
                        this.CalcExpression = this.CalcExpression.concat(inputedSym);
                        if(this.CalcExpression.contains("()") || this.CalcExpression.contains("(.)") ){

                            this.WildOperator = true;
                        }
                    }
                    this.outputText.setText(this.CalcExpression);
                    //rule for if decimals are given
                } else if (val.equals(".")) {
                    char[] individualChars = this.CalcExpression.toCharArray();
                    char finalval = individualChars[individualChars.length - 1];
                    if (finalval == '.') {
                        this.WildOperator = true;
                    } else {
                        this.WildOperator = true;
                        this.CalcExpression = this.CalcExpression.concat(inputedSym);
                        this.outputText.setText(this.CalcExpression);
                    }
                } else {
                    if (this.CalcExpression.length() >= 1) {
                        char[] individualChars = this.CalcExpression.toCharArray();
                        char finalval = individualChars[individualChars.length - 1];
                        if ((finalval == '+' || finalval == '-' || finalval == '*' || finalval == '/')) {

                            this.CalcExpression = this.CalcExpression.substring(0, this.CalcExpression.length() - 1);
                            this.CalcExpression = this.CalcExpression.concat(inputedSym);
                            this.outputText.setText(this.CalcExpression);

                        } else {

                            this.WildOperator = true;
                            this.CalcExpression = this.CalcExpression.concat(inputedSym);
                            this.outputText.setText(this.CalcExpression);
                        }
                    }
                }
            }
        }
    }
     //if a number is selected add it to the output
    public void SelectedNum(int num){
        this.WildOperator = false;
        String InputedNum="";
        InputedNum = Integer.toString(num);
        this.CalcExpression = this.CalcExpression.concat(InputedNum);
        outputText.setText(this.CalcExpression);

    }

    public void BasicArith(String val,String Oper) {

        try {
              //this is for basic arithmetic errors
            if(this.CalcExpression.isEmpty()) {
               // outputText.setText("Empty Expression!");
                throw new ArithmeticException();
            }
           //if there is a trailing operator
           if(this.WildOperator) {

               throw new ArithmeticException();
           }
            //exp4j library which takes the string and turns it into an expression to solve
            exp = new ExpressionBuilder(this.CalcExpression).build();
            this.calcOutput = exp.evaluate();
            String res = Double.toString(this.calcOutput);
            if(!Oper.equalsIgnoreCase("=")){
                outputText.setText(res=res.concat(Oper));
                this.CalcExpression = res;
                this.WildOperator = true;
            } else{

                outputText.setText(res);
                this.CalcExpression = res;
            }

        }catch(ArithmeticException ex) {
         //catch any errors
            if(!this.CalcExpression.isEmpty()) {
                outputText.setText("Error");
                this.CalcExpression="";
                this.beginParCount=0;
                this.EndParCount=0;
                this.OperatorCount = 0;
            }

        }

    }
 //this is for the advanced arithmetic for the parenthesis
    public void AdvancedArith(String val) {

        try {
            if (this.CalcExpression.equals("")) {

              throw new ArithmeticException();
            }

            if(this.WildOperator){

                throw new ArithmeticException();
            }

            if(this.beginParCount != this.EndParCount){

                throw new ArithmeticException();

            }

            exp = new ExpressionBuilder(this.CalcExpression).build();
            this.calcOutput = exp.evaluate();
            String output = Double.toString(this.calcOutput);
            this.outputText.setText(output);
            this.CalcExpression = output;

        }catch(ArithmeticException exc) {

            if(!this.CalcExpression.isEmpty()){
                this.outputText.setText("Error");
                this.CalcExpression="";
                this.beginParCount=0;
                this.EndParCount=0;
                this.OperatorCount = 0;

            }

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString("Calc Value",outputText.getText().toString());
        outState.putString("ExpVal",this.CalcExpression);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        outputText.setText(savedInstanceState.getString("Calc Value"));
        this.CalcExpression = savedInstanceState.getString("ExpVal");
    }
}