parser grammar ExpressionParser;

options{
    language = Java;
    //output=AST;
    tokenVocab=ExpressionLexer;
}

@header {
/*
 * Copyright (c) 1999-2014, Ecole des Mines de Nantes
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Ecole des Mines de Nantes nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import solver.variables.VariableFactory;
import solver.Solver;
import solver.variables.IntVar;
import solver.constraints.Constraint;
import solver.constraints.ICF;
import solver.exception.SolverException;
import solver.constraints.Operator;
import solver.variables.VF;

import java.util.Arrays;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
}

@members{


// the solver
private Solver mSolver;

private IntVar[] mVars;

private List<IntVar> evars;

private TIntList ecoeffs;

private int tmp;

private int result;

private Operator comp_op;

}

assignment [Solver aSolver, IntVar[] ivars] returns [Constraint[] constraints]
locals [List<Constraint> mConstraints = new ArrayList<Constraint>()]
@init{
    this.mSolver = aSolver;
    this.mVars = ivars.clone();
    this.evars = new ArrayList<IntVar>();
    this.ecoeffs = new TIntArrayList();
    this.comp_op = Operator.NONE;
    this.result = 0;
    this.tmp = Integer.MIN_VALUE;
}
@after{
    $constraints = new Constraint[$mConstraints.size()];
    $mConstraints.toArray($constraints);
}
    :
    (expression SC
    {
        $mConstraints.add(ICF.scalar(evars.toArray(new IntVar[0]),ecoeffs.toArray(),comp_op.toString(),VF.fixed(result, mSolver)));
        // then clear data structures
        evars.clear();
        ecoeffs.clear();
        comp_op = Operator.NONE;
        result = 0;
        tmp = Integer.MIN_VALUE;
    }
    )+
    ;


assgnt
    : EQ    {comp_op=Operator.EQ;}
    | NT EQ {comp_op=Operator.NQ;}
    | GT EQ {comp_op=Operator.GE;}
    | LT EQ {comp_op=Operator.LE;}
    | GT    {comp_op=Operator.GT;}
    | LT    {comp_op=Operator.LT;}
    ;

operator returns [Operator ope]
    : PL {$ope=Operator.PL;}
    | MN {$ope=Operator.MN;}
    ;


var returns [IntVar aVar]
    : LB i=INT RB
    {
    int idx = Integer.parseInt($i.text);
    if(idx <0 || idx > mVars.length - 1){
        throw new SolverException("The "+idx+"^h variable from "+Arrays.toString(mVars)+" is null or out of bounds.");
    }
    $aVar = mVars[idx];
    }
    ;

atom
    : i=INT
    {
        tmp = Integer.parseInt($i.text);
    }
    | v=var
    {
            evars.add($v.aVar);
            ecoeffs.add(1);
    }
    | (v=var DT i=INT | i=INT DT v=var)
    {
        evars.add($v.aVar);
        ecoeffs.add(Integer.parseInt($i.text));
    }
    ;

shexp
    : atom
    {
         if(tmp > Integer.MIN_VALUE){
            if(comp_op == Operator.NONE){ // LHS
                result -= tmp;
            }else{ // RHS
                result += tmp;
            }
            // then reset tmp
            tmp = Integer.MIN_VALUE;
         }
    }
     (o=operator atom
    {
        if(tmp > Integer.MIN_VALUE){
            if(comp_op == Operator.NONE){ // LHS
                result += ($o.ope == Operator.PL?-tmp:tmp);
            }else{ // RHS
                result += ($o.ope == Operator.PL?tmp:-tmp);
            }
            // then reset tmp
            tmp = Integer.MIN_VALUE;
        }else if($o.ope == Operator.MN){
            int cidx = evars.size()-1;
            int c = ecoeffs.get(cidx);
            ecoeffs.set(cidx,-c);
        }
    }
    )*
    ;

expression
locals[int a = 0]
@after{
    int cidx = evars.size();
    for(int i = $a; i < cidx; i++){
        int c = ecoeffs.get(i);
        ecoeffs.set(i,-c);
    }
}
    : shexp assgnt {$a = evars.size();} shexp
    ;