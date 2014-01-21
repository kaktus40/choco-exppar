/**
 *  Copyright (c) 1999-2014, Ecole des Mines de Nantes
 *  All rights reserved.
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the Ecole des Mines de Nantes nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.testng.Assert;
import org.testng.annotations.Test;
import solver.Solver;
import solver.constraints.Constraint;
import solver.constraints.ExpressionLexer;
import solver.constraints.ExpressionParser;
import solver.exception.SolverException;
import solver.variables.IntVar;
import solver.variables.VF;

/**
 * <br/>
 *
 * @author Charles Prud'homme
 * @since 20/01/2014
 */
public class ExpressionFactory {


    static Constraint[] parser(String expression, IntVar... vars) {
        ExpressionLexer lexer = new ExpressionLexer(new ANTLRInputStream(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser p = new ExpressionParser(tokens);
        p.setBuildParseTree(true);
        return p.assignment(vars[0].getSolver(), vars).constraints;
    }


    @Test(expectedExceptions = SolverException.class)
    public void test0() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 1, 0, 10, solver);
        Constraint[] cstr = parser("{1} = 5;", vars);
    }

    @Test
    public void test1() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 1, 0, 10, solver);
        Constraint[] cstr = parser("{0} = 5;", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 1);
    }

    @Test
    public void test1_1() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 1, 0, 10, solver);
        Constraint[] cstr = parser("{0} - 2 = 5 + -3;", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 1);
    }

    @Test
    public void test2() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 1, 0, 10, solver);
        Constraint[] cstr = parser("5 = {0};", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 1);
    }

    @Test
    public void test3() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 2, 0, 10, solver);
        Constraint[] cstr = parser("{1} = {0};", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 11);
    }

    @Test
    public void test4() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 1, 0, 10, solver);
        Constraint[] cstr = parser("5 = 4;", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 0);
    }

    @Test
    public void test5() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 4, 0, 10, solver);
        Constraint[] cstr = parser("{0} + 2.{1} - 3 = -2.{2} + {3} - 10;", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 8);
    }

    @Test
    public void test6() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 4, 0, 10, solver);
        Constraint[] cstr = parser("{0} + 2.{1} - 3 != -2.{2} + {3} - 10;", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 14633);
    }

    @Test
    public void test7() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 4, 0, 10, solver);
        Constraint[] cstr = parser("{0} + 2.{1} - 3 > -2.{2} + {3} - 10;", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 14625);
    }

    @Test
    public void test8() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 4, 0, 10, solver);
        Constraint[] cstr = parser("{0} + 2.{1} - 3 < -2.{2} + {3} - 10;", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 8);
    }

    @Test
    public void test9() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 4, 0, 10, solver);
        Constraint[] cstr = parser("{0} + 2.{1} - 3 >= -2.{2} + {3} - 10;", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 14633);
    }

    @Test
    public void test10() {
        Solver solver = new Solver();
        IntVar[] vars = VF.enumeratedArray("v", 4, 0, 10, solver);
        Constraint[] cstr = parser("{0} + 2.{1} + -3 <= -2.{2} + {3} - 10;", vars);
        Assert.assertEquals(cstr.length, 1);
        solver.post((cstr[0]));
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 16);
    }

}
