package com.yory3r.e_learning.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yory3r.e_learning.R;
import com.yory3r.e_learning.databinding.FragmentMathActivityKalkulatorBinding;
import com.yory3r.e_learning.model.Kalkulator;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MathActivityFragmentKalkulator extends Fragment
{
    private FragmentMathActivityKalkulatorBinding fragmentMathActivityKalkulatorBinding;

    TextView tvWorkings;
    TextView tvResult;

    String workings = "";
    String formula = "";
    String tempFormula = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        fragmentMathActivityKalkulatorBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_math_activity_kalkulator,container,false);
        fragmentMathActivityKalkulatorBinding.setFragmentMathActivityKalkulator(this);

        View view = fragmentMathActivityKalkulatorBinding.getRoot();

        tvWorkings = fragmentMathActivityKalkulatorBinding.tvWorking;
        tvResult = fragmentMathActivityKalkulatorBinding.tvResult;

        return view;
    }

    private void setWorkings(String givenValue)
    {
        workings = workings + givenValue;
        tvWorkings.setText(workings);
    }


    public void equalsOnClick(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try
        {
            result = (double)engine.eval(formula);
        }
        catch (ScriptException e)
        {
            Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
        {
            tvResult.setText(String.valueOf(result.doubleValue()));
        }
    }

    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();

        for(int a = 0; a < workings.length(); a++)
        {
            if (workings.charAt(a) == '^')
            {
                indexOfPowers.add(a);
            }
        }

        formula = workings;
        tempFormula = workings;

        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }

        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int a = index + 1; a< workings.length(); a++)
        {
            if(isNumeric(workings.charAt(a)))
            {
                numberRight = numberRight + workings.charAt(a);
            }
            else
            {
                break;
            }
        }

        for(int a = index - 1; a >= 0; a--)
        {
            if(isNumeric(workings.charAt(a)))
            {
                numberLeft = numberLeft + workings.charAt(a);
            }
            else
            {
                break;
            }
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
        {
            return true;
        }

        return false;
    }


    public void clearOnClick(View view)
    {
        tvWorkings.setText("");
        workings = "";
        tvResult.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracketsOnClick(View view)
    {
        if(leftBracket)
        {
            setWorkings("(");
            leftBracket = false;
        }
        else
        {
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void powerOfOnClick(View view)
    {
        setWorkings("^");
    }

    public void divisionOnClick(View view)
    {
        setWorkings("/");
    }

    public void sevenOnClick(View view)
    {
        setWorkings("7");
    }

    public void eightOnClick(View view)
    {
        setWorkings("8");
    }

    public void nineOnClick(View view)
    {
        setWorkings("9");
    }

    public void timesOnClick(View view)
    {
        setWorkings("*");
    }

    public void fourOnClick(View view)
    {
        setWorkings("4");
    }

    public void fiveOnClick(View view)
    {
        setWorkings("5");
    }

    public void sixOnClick(View view)
    {
        setWorkings("6");
    }

    public void minusOnClick(View view)
    {
        setWorkings("-");
    }

    public void oneOnClick(View view)
    {
        setWorkings("1");
    }

    public void twoOnClick(View view)
    {
        setWorkings("2");
    }

    public void threeOnClick(View view)
    {
        setWorkings("3");
    }

    public void plusOnClick(View view)
    {
        setWorkings("+");
    }

    public void decimalOnClick(View view)
    {
        setWorkings(".");
    }

    public void zeroOnClick(View view)
    {
        setWorkings("0");
    }
}