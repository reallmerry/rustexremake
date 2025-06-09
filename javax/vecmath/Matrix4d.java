package javax.vecmath;

import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlFlushStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MysqlCreateFullTextAnalyzerStatement;
import java.io.Serializable;

public class Matrix4d implements Serializable, Cloneable {
   public static final long for = 8223903484171633710L;
   public double char;
   public double float;
   public double break;
   public double catch;
   public double do;
   public double else;
   public double goto;
   public double case;
   public double short;
   public double long;
   public double new;
   public double const;
   public double false;
   public double null;
   public double int;
   public double final;
   private static final double this = 1.0E-10D;

   public Matrix4d(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5, double arg6, double arg7, double arg8, double arg9, double arg10, double arg11, double arg12, double arg13, double arg14, double arg15) {
      thisx.char = arg0;
      thisx.float = arg1;
      thisx.break = arg2;
      thisx.catch = arg3;
      thisx.do = arg4;
      thisx.else = arg5;
      thisx.goto = arg6;
      thisx.case = arg7;
      thisx.short = arg8;
      thisx.long = arg9;
      thisx.new = arg10;
      thisx.const = arg11;
      thisx.false = arg12;
      thisx.null = arg13;
      thisx.int = arg14;
      thisx.final = arg15;
   }

   public Matrix4d(double[] arg0) {
      thisx.char = arg0[0];
      thisx.float = arg0[1];
      thisx.break = arg0[2];
      thisx.catch = arg0[3];
      thisx.do = arg0[4];
      thisx.else = arg0[5];
      thisx.goto = arg0[6];
      thisx.case = arg0[7];
      thisx.short = arg0[8];
      thisx.long = arg0[9];
      thisx.new = arg0[10];
      thisx.const = arg0[11];
      thisx.false = arg0[12];
      thisx.null = arg0[13];
      thisx.int = arg0[14];
      thisx.final = arg0[15];
   }

   public Matrix4d(Quat4d arg0, Vector3d arg1, double arg2) {
      thisx.char = arg2 * (1.0D - 2.0D * arg0.int * arg0.int - 2.0D * arg0.final * arg0.final);
      thisx.do = arg2 * 2.0D * (arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.short = arg2 * 2.0D * (arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.float = arg2 * 2.0D * (arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.else = arg2 * (1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.final * arg0.final);
      thisx.long = arg2 * 2.0D * (arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.break = arg2 * 2.0D * (arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.goto = arg2 * 2.0D * (arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.new = arg2 * (1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.int * arg0.int);
      thisx.catch = arg1.int;
      thisx.case = arg1.final;
      thisx.const = arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public Matrix4d(Quat4f arg0, Vector3d arg1, double arg2) {
      thisx.char = arg2 * (1.0D - 2.0D * (double)arg0.int * (double)arg0.int - 2.0D * (double)arg0.final * (double)arg0.final);
      thisx.do = arg2 * 2.0D * (double)(arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.short = arg2 * 2.0D * (double)(arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.float = arg2 * 2.0D * (double)(arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.else = arg2 * (1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.final * (double)arg0.final);
      thisx.long = arg2 * 2.0D * (double)(arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.break = arg2 * 2.0D * (double)(arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.goto = arg2 * 2.0D * (double)(arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.new = arg2 * (1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.int * (double)arg0.int);
      thisx.catch = arg1.int;
      thisx.case = arg1.final;
      thisx.const = arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public Matrix4d(Matrix4d arg0) {
      thisx.char = arg0.char;
      thisx.float = arg0.float;
      thisx.break = arg0.break;
      thisx.catch = arg0.catch;
      thisx.do = arg0.do;
      thisx.else = arg0.else;
      thisx.goto = arg0.goto;
      thisx.case = arg0.case;
      thisx.short = arg0.short;
      thisx.long = arg0.long;
      thisx.new = arg0.new;
      thisx.const = arg0.const;
      thisx.false = arg0.false;
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
   }

   public Matrix4d(Matrix4f arg0) {
      thisx.char = (double)arg0.char;
      thisx.float = (double)arg0.float;
      thisx.break = (double)arg0.break;
      thisx.catch = (double)arg0.catch;
      thisx.do = (double)arg0.do;
      thisx.else = (double)arg0.else;
      thisx.goto = (double)arg0.goto;
      thisx.case = (double)arg0.case;
      thisx.short = (double)arg0.short;
      thisx.long = (double)arg0.long;
      thisx.new = (double)arg0.new;
      thisx.const = (double)arg0.const;
      thisx.false = (double)arg0.false;
      thisx.null = (double)arg0.null;
      thisx.int = (double)arg0.int;
      thisx.final = (double)arg0.final;
   }

   public Matrix4d(Matrix3f arg0, Vector3d arg1, double arg2) {
      thisx.char = (double)arg0.case * arg2;
      thisx.float = (double)arg0.short * arg2;
      thisx.break = (double)arg0.long * arg2;
      thisx.catch = arg1.int;
      thisx.do = (double)arg0.new * arg2;
      thisx.else = (double)arg0.const * arg2;
      thisx.goto = (double)arg0.false * arg2;
      thisx.case = arg1.final;
      thisx.short = (double)arg0.null * arg2;
      thisx.long = (double)arg0.int * arg2;
      thisx.new = (double)arg0.final * arg2;
      thisx.const = arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public Matrix4d(Matrix3d arg0, Vector3d arg1, double arg2) {
      thisx.char = arg0.case * arg2;
      thisx.float = arg0.short * arg2;
      thisx.break = arg0.long * arg2;
      thisx.catch = arg1.int;
      thisx.do = arg0.new * arg2;
      thisx.else = arg0.const * arg2;
      thisx.goto = arg0.false * arg2;
      thisx.case = arg1.final;
      thisx.short = arg0.null * arg2;
      thisx.long = arg0.int * arg2;
      thisx.new = arg0.final * arg2;
      thisx.const = arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public Matrix4d() {
      thisx.char = 0.0D;
      thisx.float = 0.0D;
      thisx.break = 0.0D;
      thisx.catch = 0.0D;
      thisx.do = 0.0D;
      thisx.else = 0.0D;
      thisx.goto = 0.0D;
      thisx.case = 0.0D;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = 0.0D;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 0.0D;
   }

   public String toString() {
      return thisx.char + MySqlFlushStatement.null("\u000b3") + thisx.float + MysqlCreateFullTextAnalyzerStatement.null("q\\") + thisx.break + MySqlFlushStatement.null("\u000b3") + thisx.catch + "\n" + thisx.do + MysqlCreateFullTextAnalyzerStatement.null("q\\") + thisx.else + MySqlFlushStatement.null("\u000b3") + thisx.goto + MysqlCreateFullTextAnalyzerStatement.null("q\\") + thisx.case + "\n" + thisx.short + MySqlFlushStatement.null("\u000b3") + thisx.long + MysqlCreateFullTextAnalyzerStatement.null("q\\") + thisx.new + MySqlFlushStatement.null("\u000b3") + thisx.const + "\n" + thisx.false + MysqlCreateFullTextAnalyzerStatement.null("q\\") + thisx.null + MySqlFlushStatement.null("\u000b3") + thisx.int + MysqlCreateFullTextAnalyzerStatement.null("q\\") + thisx.final + "\n";
   }

   public final void true() {
      thisx.char = 1.0D;
      thisx.float = 0.0D;
      thisx.break = 0.0D;
      thisx.catch = 0.0D;
      thisx.do = 0.0D;
      thisx.else = 1.0D;
      thisx.goto = 0.0D;
      thisx.case = 0.0D;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = 1.0D;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(int arg0, int arg1, double arg2) {
      switch(arg0) {
      case 0:
         switch(arg1) {
         case 0:
            thisx.char = arg2;
            return;
         case 1:
            thisx.float = arg2;
            return;
         case 2:
            thisx.break = arg2;
            return;
         case 3:
            thisx.catch = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MySqlFlushStatement.null("^FgUz_'C#")));
         }
      case 1:
         switch(arg1) {
         case 0:
            thisx.do = arg2;
            return;
         case 1:
            thisx.else = arg2;
            return;
         case 2:
            thisx.goto = arg2;
            return;
         case 3:
            thisx.case = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9L")));
         }
      case 2:
         switch(arg1) {
         case 0:
            thisx.short = arg2;
            return;
         case 1:
            thisx.long = arg2;
            return;
         case 2:
            thisx.new = arg2;
            return;
         case 3:
            thisx.const = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MySqlFlushStatement.null("^FgUz_'C#")));
         }
      case 3:
         switch(arg1) {
         case 0:
            thisx.false = arg2;
            return;
         case 1:
            thisx.null = arg2;
            return;
         case 2:
            thisx.int = arg2;
            return;
         case 3:
            thisx.final = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9L")));
         }
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MySqlFlushStatement.null("^FgUz_'C#")));
      }
   }

   public final double null(int arg0, int arg1) {
      switch(arg0) {
      case 0:
         switch(arg1) {
         case 0:
            return thisx.char;
         case 1:
            return thisx.float;
         case 2:
            return thisx.break;
         case 3:
            return thisx.catch;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9M")));
         }
      case 1:
         switch(arg1) {
         case 0:
            return thisx.do;
         case 1:
            return thisx.else;
         case 2:
            return thisx.goto;
         case 3:
            return thisx.case;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9M")));
         }
      case 2:
         switch(arg1) {
         case 0:
            return thisx.short;
         case 1:
            return thisx.long;
         case 2:
            return thisx.new;
         case 3:
            return thisx.const;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9M")));
         }
      case 3:
         switch(arg1) {
         case 0:
            return thisx.false;
         case 1:
            return thisx.null;
         case 2:
            return thisx.int;
         case 3:
            return thisx.final;
         }
      }

      throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9M")));
   }

   public final void const(int arg0, Vector4d arg1) {
      if (arg0 == 0) {
         arg1.null = thisx.char;
         arg1.int = thisx.float;
         arg1.final = thisx.break;
         arg1.this = thisx.catch;
      } else if (arg0 == 1) {
         arg1.null = thisx.do;
         arg1.int = thisx.else;
         arg1.final = thisx.goto;
         arg1.this = thisx.case;
      } else if (arg0 == 2) {
         arg1.null = thisx.short;
         arg1.int = thisx.long;
         arg1.final = thisx.new;
         arg1.this = thisx.const;
      } else {
         if (arg0 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MySqlFlushStatement.null("^FgUz_'C!")));
         }

         arg1.null = thisx.false;
         arg1.int = thisx.null;
         arg1.final = thisx.int;
         arg1.this = thisx.final;
      }

   }

   public final void const(int arg0, double[] arg1) {
      if (arg0 == 0) {
         arg1[0] = thisx.char;
         arg1[1] = thisx.float;
         arg1[2] = thisx.break;
         arg1[3] = thisx.catch;
      } else if (arg0 == 1) {
         arg1[0] = thisx.do;
         arg1[1] = thisx.else;
         arg1[2] = thisx.goto;
         arg1[3] = thisx.case;
      } else if (arg0 == 2) {
         arg1[0] = thisx.short;
         arg1[1] = thisx.long;
         arg1[2] = thisx.new;
         arg1[3] = thisx.const;
      } else {
         if (arg0 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9N")));
         }

         arg1[0] = thisx.false;
         arg1[1] = thisx.null;
         arg1[2] = thisx.int;
         arg1[3] = thisx.final;
      }

   }

   public final void long(int arg0, Vector4d arg1) {
      if (arg0 == 0) {
         arg1.null = thisx.char;
         arg1.int = thisx.do;
         arg1.final = thisx.short;
         arg1.this = thisx.false;
      } else if (arg0 == 1) {
         arg1.null = thisx.float;
         arg1.int = thisx.else;
         arg1.final = thisx.long;
         arg1.this = thisx.null;
      } else if (arg0 == 2) {
         arg1.null = thisx.break;
         arg1.int = thisx.goto;
         arg1.final = thisx.new;
         arg1.this = thisx.int;
      } else {
         if (arg0 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MySqlFlushStatement.null("^FgUz_'C ")));
         }

         arg1.null = thisx.catch;
         arg1.int = thisx.case;
         arg1.final = thisx.const;
         arg1.this = thisx.final;
      }

   }

   public final void long(int arg0, double[] arg1) {
      if (arg0 == 0) {
         arg1[0] = thisx.char;
         arg1[1] = thisx.do;
         arg1[2] = thisx.short;
         arg1[3] = thisx.false;
      } else if (arg0 == 1) {
         arg1[0] = thisx.float;
         arg1[1] = thisx.else;
         arg1[2] = thisx.long;
         arg1[3] = thisx.null;
      } else if (arg0 == 2) {
         arg1[0] = thisx.break;
         arg1[1] = thisx.goto;
         arg1[2] = thisx.new;
         arg1[3] = thisx.int;
      } else {
         if (arg0 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9O")));
         }

         arg1[0] = thisx.catch;
         arg1[1] = thisx.case;
         arg1[2] = thisx.const;
         arg1[3] = thisx.final;
      }

   }

   public final void true(Matrix3d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      arg0.case = var2[0];
      arg0.short = var2[1];
      arg0.long = var2[2];
      arg0.new = var2[3];
      arg0.const = var2[4];
      arg0.false = var2[5];
      arg0.null = var2[6];
      arg0.int = var2[7];
      arg0.final = var2[8];
   }

   public final void true(Matrix3f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      arg0.case = (float)var2[0];
      arg0.short = (float)var2[1];
      arg0.long = (float)var2[2];
      arg0.new = (float)var2[3];
      arg0.const = (float)var2[4];
      arg0.false = (float)var2[5];
      arg0.null = (float)var2[6];
      arg0.int = (float)var2[7];
      arg0.final = (float)var2[8];
   }

   public final double null(Matrix3d arg0, Vector3d arg1) {
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      thisx.null(var4, var3);
      arg0.case = var3[0];
      arg0.short = var3[1];
      arg0.long = var3[2];
      arg0.new = var3[3];
      arg0.const = var3[4];
      arg0.false = var3[5];
      arg0.null = var3[6];
      arg0.int = var3[7];
      arg0.final = var3[8];
      arg1.int = thisx.catch;
      arg1.final = thisx.case;
      arg1.this = thisx.const;
      return Matrix3d.null(var4);
   }

   public final double null(Matrix3f arg0, Vector3d arg1) {
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      thisx.null(var4, var3);
      arg0.case = (float)var3[0];
      arg0.short = (float)var3[1];
      arg0.long = (float)var3[2];
      arg0.new = (float)var3[3];
      arg0.const = (float)var3[4];
      arg0.false = (float)var3[5];
      arg0.null = (float)var3[6];
      arg0.int = (float)var3[7];
      arg0.final = (float)var3[8];
      arg1.int = thisx.catch;
      arg1.final = thisx.case;
      arg1.this = thisx.const;
      return Matrix3d.null(var4);
   }

   public final void long(Quat4f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      double var4 = 0.25D * (1.0D + var2[0] + var2[4] + var2[8]);
      if (!((var4 < 0.0D ? -var4 : var4) < 1.0E-30D)) {
         arg0.this = (float)Math.sqrt(var4);
         var4 = 0.25D / (double)arg0.this;
         arg0.null = (float)((var2[7] - var2[5]) * var4);
         arg0.int = (float)((var2[2] - var2[6]) * var4);
         arg0.final = (float)((var2[3] - var2[1]) * var4);
      } else {
         arg0.this = 0.0F;
         var4 = -0.5D * (var2[4] + var2[8]);
         if (!((var4 < 0.0D ? -var4 : var4) < 1.0E-30D)) {
            arg0.null = (float)Math.sqrt(var4);
            var4 = 0.5D / (double)arg0.null;
            arg0.int = (float)(var2[3] * var4);
            arg0.final = (float)(var2[6] * var4);
         } else {
            arg0.null = 0.0F;
            var4 = 0.5D * (1.0D - var2[8]);
            if (!((var4 < 0.0D ? -var4 : var4) < 1.0E-30D)) {
               arg0.int = (float)Math.sqrt(var4);
               arg0.final = (float)(var2[7] / (2.0D * (double)arg0.int));
            } else {
               arg0.int = 0.0F;
               arg0.final = 1.0F;
            }
         }
      }
   }

   public final void long(Quat4d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      double var4 = 0.25D * (1.0D + var2[0] + var2[4] + var2[8]);
      if (!((var4 < 0.0D ? -var4 : var4) < 1.0E-30D)) {
         arg0.this = Math.sqrt(var4);
         var4 = 0.25D / arg0.this;
         arg0.null = (var2[7] - var2[5]) * var4;
         arg0.int = (var2[2] - var2[6]) * var4;
         arg0.final = (var2[3] - var2[1]) * var4;
      } else {
         arg0.this = 0.0D;
         var4 = -0.5D * (var2[4] + var2[8]);
         if (!((var4 < 0.0D ? -var4 : var4) < 1.0E-30D)) {
            arg0.null = Math.sqrt(var4);
            var4 = 0.5D / arg0.null;
            arg0.int = var2[3] * var4;
            arg0.final = var2[6] * var4;
         } else {
            arg0.null = 0.0D;
            var4 = 0.5D * (1.0D - var2[8]);
            if (!((var4 < 0.0D ? -var4 : var4) < 1.0E-30D)) {
               arg0.int = Math.sqrt(var4);
               arg0.final = var2[7] / (2.0D * arg0.int);
            } else {
               arg0.int = 0.0D;
               arg0.final = 1.0D;
            }
         }
      }
   }

   public final void const(Vector3d arg0) {
      arg0.int = thisx.catch;
      arg0.final = thisx.case;
      arg0.this = thisx.const;
   }

   public final void const(Matrix3f arg0) {
      arg0.case = (float)thisx.char;
      arg0.short = (float)thisx.float;
      arg0.long = (float)thisx.break;
      arg0.new = (float)thisx.do;
      arg0.const = (float)thisx.else;
      arg0.false = (float)thisx.goto;
      arg0.null = (float)thisx.short;
      arg0.int = (float)thisx.long;
      arg0.final = (float)thisx.new;
   }

   public final void const(Matrix3d arg0) {
      arg0.case = thisx.char;
      arg0.short = thisx.float;
      arg0.long = thisx.break;
      arg0.new = thisx.do;
      arg0.const = thisx.else;
      arg0.false = thisx.goto;
      arg0.null = thisx.short;
      arg0.int = thisx.long;
      arg0.final = thisx.new;
   }

   public final double enum() {
      double[] var1 = new double[9];
      double[] var2 = new double[3];
      thisx.null(var2, var1);
      return Matrix3d.null(var2);
   }

   public final void long(Matrix3d arg0) {
      thisx.char = arg0.case;
      thisx.float = arg0.short;
      thisx.break = arg0.long;
      thisx.do = arg0.new;
      thisx.else = arg0.const;
      thisx.goto = arg0.false;
      thisx.short = arg0.null;
      thisx.long = arg0.int;
      thisx.new = arg0.final;
   }

   public final void long(Matrix3f arg0) {
      thisx.char = (double)arg0.case;
      thisx.float = (double)arg0.short;
      thisx.break = (double)arg0.long;
      thisx.do = (double)arg0.new;
      thisx.else = (double)arg0.const;
      thisx.goto = (double)arg0.false;
      thisx.short = (double)arg0.null;
      thisx.long = (double)arg0.int;
      thisx.new = (double)arg0.final;
   }

   public final void break(double arg0) {
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      thisx.null(var4, var3);
      thisx.char = var3[0] * arg0;
      thisx.float = var3[1] * arg0;
      thisx.break = var3[2] * arg0;
      thisx.do = var3[3] * arg0;
      thisx.else = var3[4] * arg0;
      thisx.goto = var3[5] * arg0;
      thisx.short = var3[6] * arg0;
      thisx.long = var3[7] * arg0;
      thisx.new = var3[8] * arg0;
   }

   public final void class(int arg0, double arg1, double arg2, double arg3, double arg4) {
      switch(arg0) {
      case 0:
         thisx.char = arg1;
         thisx.float = arg2;
         thisx.break = arg3;
         thisx.catch = arg4;
         break;
      case 1:
         thisx.do = arg1;
         thisx.else = arg2;
         thisx.goto = arg3;
         thisx.case = arg4;
         break;
      case 2:
         thisx.short = arg1;
         thisx.long = arg2;
         thisx.new = arg3;
         thisx.const = arg4;
         break;
      case 3:
         thisx.false = arg1;
         thisx.null = arg2;
         thisx.int = arg3;
         thisx.final = arg4;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MySqlFlushStatement.null("^FgUz_'C'")));
      }

   }

   public final void class(int arg0, Vector4d arg1) {
      switch(arg0) {
      case 0:
         thisx.char = arg1.null;
         thisx.float = arg1.int;
         thisx.break = arg1.final;
         thisx.catch = arg1.this;
         break;
      case 1:
         thisx.do = arg1.null;
         thisx.else = arg1.int;
         thisx.goto = arg1.final;
         thisx.case = arg1.this;
         break;
      case 2:
         thisx.short = arg1.null;
         thisx.long = arg1.int;
         thisx.new = arg1.final;
         thisx.const = arg1.this;
         break;
      case 3:
         thisx.false = arg1.null;
         thisx.null = arg1.int;
         thisx.int = arg1.final;
         thisx.final = arg1.this;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9H")));
      }

   }

   public final void class(int arg0, double[] arg1) {
      switch(arg0) {
      case 0:
         thisx.char = arg1[0];
         thisx.float = arg1[1];
         thisx.break = arg1[2];
         thisx.catch = arg1[3];
         break;
      case 1:
         thisx.do = arg1[0];
         thisx.else = arg1[1];
         thisx.goto = arg1[2];
         thisx.case = arg1[3];
         break;
      case 2:
         thisx.short = arg1[0];
         thisx.long = arg1[1];
         thisx.new = arg1[2];
         thisx.const = arg1[3];
         break;
      case 3:
         thisx.false = arg1[0];
         thisx.null = arg1[1];
         thisx.int = arg1[2];
         thisx.final = arg1[3];
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MySqlFlushStatement.null("^FgUz_'C'")));
      }

   }

   public final void null(int arg0, double arg1, double arg2, double arg3, double arg4) {
      switch(arg0) {
      case 0:
         thisx.char = arg1;
         thisx.do = arg2;
         thisx.short = arg3;
         thisx.false = arg4;
         break;
      case 1:
         thisx.float = arg1;
         thisx.else = arg2;
         thisx.long = arg3;
         thisx.null = arg4;
         break;
      case 2:
         thisx.break = arg1;
         thisx.goto = arg2;
         thisx.new = arg3;
         thisx.int = arg4;
         break;
      case 3:
         thisx.catch = arg1;
         thisx.case = arg2;
         thisx.const = arg3;
         thisx.final = arg4;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9K")));
      }

   }

   public final void null(int arg0, Vector4d arg1) {
      switch(arg0) {
      case 0:
         thisx.char = arg1.null;
         thisx.do = arg1.int;
         thisx.short = arg1.final;
         thisx.false = arg1.this;
         break;
      case 1:
         thisx.float = arg1.null;
         thisx.else = arg1.int;
         thisx.long = arg1.final;
         thisx.null = arg1.this;
         break;
      case 2:
         thisx.break = arg1.null;
         thisx.goto = arg1.int;
         thisx.new = arg1.final;
         thisx.int = arg1.this;
         break;
      case 3:
         thisx.catch = arg1.null;
         thisx.case = arg1.int;
         thisx.const = arg1.final;
         thisx.final = arg1.this;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MySqlFlushStatement.null("^FgUz_'C$")));
      }

   }

   public final void null(int arg0, double[] arg1) {
      switch(arg0) {
      case 0:
         thisx.char = arg1[0];
         thisx.do = arg1[1];
         thisx.short = arg1[2];
         thisx.false = arg1[3];
         break;
      case 1:
         thisx.float = arg1[0];
         thisx.else = arg1[1];
         thisx.long = arg1[2];
         thisx.null = arg1[3];
         break;
      case 2:
         thisx.break = arg1[0];
         thisx.goto = arg1[1];
         thisx.new = arg1[2];
         thisx.int = arg1[3];
         break;
      case 3:
         thisx.catch = arg1[0];
         thisx.case = arg1[1];
         thisx.const = arg1[2];
         thisx.final = arg1[3];
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("1<\b/\u0015%H9K")));
      }

   }

   public final void super(double arg0) {
      thisx.char += arg0;
      thisx.float += arg0;
      thisx.break += arg0;
      thisx.catch += arg0;
      thisx.do += arg0;
      thisx.else += arg0;
      thisx.goto += arg0;
      thisx.case += arg0;
      thisx.short += arg0;
      thisx.long += arg0;
      thisx.new += arg0;
      thisx.const += arg0;
      thisx.false += arg0;
      thisx.null += arg0;
      thisx.int += arg0;
      thisx.final += arg0;
   }

   public final void class(double arg0, Matrix4d arg1) {
      thisx.char = arg1.char + arg0;
      thisx.float = arg1.float + arg0;
      thisx.break = arg1.break + arg0;
      thisx.catch = arg1.catch + arg0;
      thisx.do = arg1.do + arg0;
      thisx.else = arg1.else + arg0;
      thisx.goto = arg1.goto + arg0;
      thisx.case = arg1.case + arg0;
      thisx.short = arg1.short + arg0;
      thisx.long = arg1.long + arg0;
      thisx.new = arg1.new + arg0;
      thisx.const = arg1.const + arg0;
      thisx.false = arg1.false + arg0;
      thisx.null = arg1.null + arg0;
      thisx.int = arg1.int + arg0;
      thisx.final = arg1.final + arg0;
   }

   public final void do(Matrix4d arg0, Matrix4d arg1) {
      thisx.char = arg0.char + arg1.char;
      thisx.float = arg0.float + arg1.float;
      thisx.break = arg0.break + arg1.break;
      thisx.catch = arg0.catch + arg1.catch;
      thisx.do = arg0.do + arg1.do;
      thisx.else = arg0.else + arg1.else;
      thisx.goto = arg0.goto + arg1.goto;
      thisx.case = arg0.case + arg1.case;
      thisx.short = arg0.short + arg1.short;
      thisx.long = arg0.long + arg1.long;
      thisx.new = arg0.new + arg1.new;
      thisx.const = arg0.const + arg1.const;
      thisx.false = arg0.false + arg1.false;
      thisx.null = arg0.null + arg1.null;
      thisx.int = arg0.int + arg1.int;
      thisx.final = arg0.final + arg1.final;
   }

   public final void this(Matrix4d arg0) {
      thisx.char += arg0.char;
      thisx.float += arg0.float;
      thisx.break += arg0.break;
      thisx.catch += arg0.catch;
      thisx.do += arg0.do;
      thisx.else += arg0.else;
      thisx.goto += arg0.goto;
      thisx.case += arg0.case;
      thisx.short += arg0.short;
      thisx.long += arg0.long;
      thisx.new += arg0.new;
      thisx.const += arg0.const;
      thisx.false += arg0.false;
      thisx.null += arg0.null;
      thisx.int += arg0.int;
      thisx.final += arg0.final;
   }

   public final void true(Matrix4d arg0, Matrix4d arg1) {
      thisx.char = arg0.char - arg1.char;
      thisx.float = arg0.float - arg1.float;
      thisx.break = arg0.break - arg1.break;
      thisx.catch = arg0.catch - arg1.catch;
      thisx.do = arg0.do - arg1.do;
      thisx.else = arg0.else - arg1.else;
      thisx.goto = arg0.goto - arg1.goto;
      thisx.case = arg0.case - arg1.case;
      thisx.short = arg0.short - arg1.short;
      thisx.long = arg0.long - arg1.long;
      thisx.new = arg0.new - arg1.new;
      thisx.const = arg0.const - arg1.const;
      thisx.false = arg0.false - arg1.false;
      thisx.null = arg0.null - arg1.null;
      thisx.int = arg0.int - arg1.int;
      thisx.final = arg0.final - arg1.final;
   }

   public final void short(Matrix4d arg0) {
      thisx.char -= arg0.char;
      thisx.float -= arg0.float;
      thisx.break -= arg0.break;
      thisx.catch -= arg0.catch;
      thisx.do -= arg0.do;
      thisx.else -= arg0.else;
      thisx.goto -= arg0.goto;
      thisx.case -= arg0.case;
      thisx.short -= arg0.short;
      thisx.long -= arg0.long;
      thisx.new -= arg0.new;
      thisx.const -= arg0.const;
      thisx.false -= arg0.false;
      thisx.null -= arg0.null;
      thisx.int -= arg0.int;
      thisx.final -= arg0.final;
   }

   public final void const() {
      double var1 = thisx.do;
      thisx.do = thisx.float;
      thisx.float = var1;
      var1 = thisx.short;
      thisx.short = thisx.break;
      thisx.break = var1;
      var1 = thisx.false;
      thisx.false = thisx.catch;
      thisx.catch = var1;
      var1 = thisx.long;
      thisx.long = thisx.goto;
      thisx.goto = var1;
      var1 = thisx.null;
      thisx.null = thisx.case;
      thisx.case = var1;
      var1 = thisx.int;
      thisx.int = thisx.const;
      thisx.const = var1;
   }

   public final void do(Matrix4d arg0) {
      if (thisx != arg0) {
         thisx.char = arg0.char;
         thisx.float = arg0.do;
         thisx.break = arg0.short;
         thisx.catch = arg0.false;
         thisx.do = arg0.float;
         thisx.else = arg0.else;
         thisx.goto = arg0.long;
         thisx.case = arg0.null;
         thisx.short = arg0.break;
         thisx.long = arg0.goto;
         thisx.new = arg0.new;
         thisx.const = arg0.int;
         thisx.false = arg0.catch;
         thisx.null = arg0.case;
         thisx.int = arg0.const;
         thisx.final = arg0.final;
      } else {
         thisx.const();
      }

   }

   public final void null(double[] arg0) {
      thisx.char = arg0[0];
      thisx.float = arg0[1];
      thisx.break = arg0[2];
      thisx.catch = arg0[3];
      thisx.do = arg0[4];
      thisx.else = arg0[5];
      thisx.goto = arg0[6];
      thisx.case = arg0[7];
      thisx.short = arg0[8];
      thisx.long = arg0[9];
      thisx.new = arg0[10];
      thisx.const = arg0[11];
      thisx.false = arg0[12];
      thisx.null = arg0[13];
      thisx.int = arg0[14];
      thisx.final = arg0[15];
   }

   public final void class(Matrix3f arg0) {
      thisx.char = (double)arg0.case;
      thisx.float = (double)arg0.short;
      thisx.break = (double)arg0.long;
      thisx.catch = 0.0D;
      thisx.do = (double)arg0.new;
      thisx.else = (double)arg0.const;
      thisx.goto = (double)arg0.false;
      thisx.case = 0.0D;
      thisx.short = (double)arg0.null;
      thisx.long = (double)arg0.int;
      thisx.new = (double)arg0.final;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void class(Matrix3d arg0) {
      thisx.char = arg0.case;
      thisx.float = arg0.short;
      thisx.break = arg0.long;
      thisx.catch = 0.0D;
      thisx.do = arg0.new;
      thisx.else = arg0.const;
      thisx.goto = arg0.false;
      thisx.case = 0.0D;
      thisx.short = arg0.null;
      thisx.long = arg0.int;
      thisx.new = arg0.final;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void class(Quat4d arg0) {
      thisx.char = 1.0D - 2.0D * arg0.int * arg0.int - 2.0D * arg0.final * arg0.final;
      thisx.do = 2.0D * (arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.short = 2.0D * (arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.float = 2.0D * (arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.else = 1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.final * arg0.final;
      thisx.long = 2.0D * (arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.break = 2.0D * (arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.goto = 2.0D * (arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.new = 1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.int * arg0.int;
      thisx.catch = 0.0D;
      thisx.case = 0.0D;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void class(AxisAngle4d arg0) {
      double var2 = Math.sqrt(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int);
      if (var2 < 1.0E-10D) {
         thisx.char = 1.0D;
         thisx.float = 0.0D;
         thisx.break = 0.0D;
         thisx.do = 0.0D;
         thisx.else = 1.0D;
         thisx.goto = 0.0D;
         thisx.short = 0.0D;
         thisx.long = 0.0D;
         thisx.new = 1.0D;
      } else {
         var2 = 1.0D / var2;
         double var4 = arg0.false * var2;
         double var6 = arg0.null * var2;
         double var8 = arg0.int * var2;
         double var10 = Math.sin(arg0.final);
         double var12 = Math.cos(arg0.final);
         double var14 = 1.0D - var12;
         double var16 = var4 * var8;
         double var18 = var4 * var6;
         double var20 = var6 * var8;
         thisx.char = var14 * var4 * var4 + var12;
         thisx.float = var14 * var18 - var10 * var8;
         thisx.break = var14 * var16 + var10 * var6;
         thisx.do = var14 * var18 + var10 * var8;
         thisx.else = var14 * var6 * var6 + var12;
         thisx.goto = var14 * var20 - var10 * var4;
         thisx.short = var14 * var16 - var10 * var6;
         thisx.long = var14 * var20 + var10 * var4;
         thisx.new = var14 * var8 * var8 + var12;
      }

      thisx.catch = 0.0D;
      thisx.case = 0.0D;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void class(Quat4f arg0) {
      thisx.char = 1.0D - 2.0D * (double)arg0.int * (double)arg0.int - 2.0D * (double)arg0.final * (double)arg0.final;
      thisx.do = 2.0D * (double)(arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.short = 2.0D * (double)(arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.float = 2.0D * (double)(arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.else = 1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.final * (double)arg0.final;
      thisx.long = 2.0D * (double)(arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.break = 2.0D * (double)(arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.goto = 2.0D * (double)(arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.new = 1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.int * (double)arg0.int;
      thisx.catch = 0.0D;
      thisx.case = 0.0D;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(AxisAngle4f arg0) {
      double var2 = Math.sqrt((double)(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int));
      if (var2 < 1.0E-10D) {
         thisx.char = 1.0D;
         thisx.float = 0.0D;
         thisx.break = 0.0D;
         thisx.do = 0.0D;
         thisx.else = 1.0D;
         thisx.goto = 0.0D;
         thisx.short = 0.0D;
         thisx.long = 0.0D;
         thisx.new = 1.0D;
      } else {
         var2 = 1.0D / var2;
         double var4 = (double)arg0.false * var2;
         double var6 = (double)arg0.null * var2;
         double var8 = (double)arg0.int * var2;
         double var10 = Math.sin((double)arg0.final);
         double var12 = Math.cos((double)arg0.final);
         double var14 = 1.0D - var12;
         double var16 = var4 * var8;
         double var18 = var4 * var6;
         double var20 = var6 * var8;
         thisx.char = var14 * var4 * var4 + var12;
         thisx.float = var14 * var18 - var10 * var8;
         thisx.break = var14 * var16 + var10 * var6;
         thisx.do = var14 * var18 + var10 * var8;
         thisx.else = var14 * var6 * var6 + var12;
         thisx.goto = var14 * var20 - var10 * var4;
         thisx.short = var14 * var16 - var10 * var6;
         thisx.long = var14 * var20 + var10 * var4;
         thisx.new = var14 * var8 * var8 + var12;
      }

      thisx.catch = 0.0D;
      thisx.case = 0.0D;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(Quat4d arg0, Vector3d arg1, double arg2) {
      thisx.char = arg2 * (1.0D - 2.0D * arg0.int * arg0.int - 2.0D * arg0.final * arg0.final);
      thisx.do = arg2 * 2.0D * (arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.short = arg2 * 2.0D * (arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.float = arg2 * 2.0D * (arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.else = arg2 * (1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.final * arg0.final);
      thisx.long = arg2 * 2.0D * (arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.break = arg2 * 2.0D * (arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.goto = arg2 * 2.0D * (arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.new = arg2 * (1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.int * arg0.int);
      thisx.catch = arg1.int;
      thisx.case = arg1.final;
      thisx.const = arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(Quat4f arg0, Vector3d arg1, double arg2) {
      thisx.char = arg2 * (1.0D - 2.0D * (double)arg0.int * (double)arg0.int - 2.0D * (double)arg0.final * (double)arg0.final);
      thisx.do = arg2 * 2.0D * (double)(arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.short = arg2 * 2.0D * (double)(arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.float = arg2 * 2.0D * (double)(arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.else = arg2 * (1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.final * (double)arg0.final);
      thisx.long = arg2 * 2.0D * (double)(arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.break = arg2 * 2.0D * (double)(arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.goto = arg2 * 2.0D * (double)(arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.new = arg2 * (1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.int * (double)arg0.int);
      thisx.catch = arg1.int;
      thisx.case = arg1.final;
      thisx.const = arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(Quat4f arg0, Vector3f arg1, float arg2) {
      thisx.char = (double)arg2 * (1.0D - 2.0D * (double)arg0.int * (double)arg0.int - 2.0D * (double)arg0.final * (double)arg0.final);
      thisx.do = (double)arg2 * 2.0D * (double)(arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.short = (double)arg2 * 2.0D * (double)(arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.float = (double)arg2 * 2.0D * (double)(arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.else = (double)arg2 * (1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.final * (double)arg0.final);
      thisx.long = (double)arg2 * 2.0D * (double)(arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.break = (double)arg2 * 2.0D * (double)(arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.goto = (double)arg2 * 2.0D * (double)(arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.new = (double)arg2 * (1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.int * (double)arg0.int);
      thisx.catch = (double)arg1.int;
      thisx.case = (double)arg1.final;
      thisx.const = (double)arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(Matrix4f arg0) {
      thisx.char = (double)arg0.char;
      thisx.float = (double)arg0.float;
      thisx.break = (double)arg0.break;
      thisx.catch = (double)arg0.catch;
      thisx.do = (double)arg0.do;
      thisx.else = (double)arg0.else;
      thisx.goto = (double)arg0.goto;
      thisx.case = (double)arg0.case;
      thisx.short = (double)arg0.short;
      thisx.long = (double)arg0.long;
      thisx.new = (double)arg0.new;
      thisx.const = (double)arg0.const;
      thisx.false = (double)arg0.false;
      thisx.null = (double)arg0.null;
      thisx.int = (double)arg0.int;
      thisx.final = (double)arg0.final;
   }

   public final void true(Matrix4d arg0) {
      thisx.char = arg0.char;
      thisx.float = arg0.float;
      thisx.break = arg0.break;
      thisx.catch = arg0.catch;
      thisx.do = arg0.do;
      thisx.else = arg0.else;
      thisx.goto = arg0.goto;
      thisx.case = arg0.case;
      thisx.short = arg0.short;
      thisx.long = arg0.long;
      thisx.new = arg0.new;
      thisx.const = arg0.const;
      thisx.false = arg0.false;
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
   }

   public final void const(Matrix4d arg0) {
      thisx.long(arg0);
   }

   public final void long() {
      thisx.long(thisx);
   }

   public final void long(Matrix4d arg0) {
      double[] var2 = new double[16];
      int[] var3 = new int[4];
      double[] var7 = new double[]{arg0.char, arg0.float, arg0.break, arg0.catch, arg0.do, arg0.else, arg0.goto, arg0.case, arg0.short, arg0.long, arg0.new, arg0.const, arg0.false, arg0.null, arg0.int, arg0.final};
      if (!null(var7, var3)) {
         throw new SingularMatrixException(VecMathI18N.null(MySqlFlushStatement.null("jrSaNk\u0013w\u0016#")));
      } else {
         for(int var4 = 0; var4 < 16; ++var4) {
            var2[var4] = 0.0D;
         }

         var2[0] = 1.0D;
         var2[5] = 1.0D;
         var2[10] = 1.0D;
         var2[15] = 1.0D;
         null(var7, var3, var2);
         thisx.char = var2[0];
         thisx.float = var2[1];
         thisx.break = var2[2];
         thisx.catch = var2[3];
         thisx.do = var2[4];
         thisx.else = var2[5];
         thisx.goto = var2[6];
         thisx.case = var2[7];
         thisx.short = var2[8];
         thisx.long = var2[9];
         thisx.new = var2[10];
         thisx.const = var2[11];
         thisx.false = var2[12];
         thisx.null = var2[13];
         thisx.int = var2[14];
         thisx.final = var2[15];
      }
   }

   public static boolean null(double[] arg0, int[] arg1) {
      double[] var2 = new double[4];
      int var5 = 0;
      int var6 = 0;

      int var3;
      double var7;
      for(var3 = 4; var3-- != 0; var2[var6++] = 1.0D / var7) {
         var7 = 0.0D;
         int var4 = 4;

         while(var4-- != 0) {
            double var9 = arg0[var5++];
            var9 = Math.abs(var9);
            if (var9 > var7) {
               var7 = var9;
            }
         }

         if (var7 == 0.0D) {
            return false;
         }
      }

      byte var17 = 0;

      for(var3 = 0; var3 < 4; ++var3) {
         int var8;
         int var10;
         double var11;
         int var18;
         int var19;
         for(var5 = 0; var5 < var3; ++var5) {
            var8 = var17 + 4 * var5 + var3;
            var11 = arg0[var8];
            var18 = var5;
            var19 = var17 + 4 * var5;

            for(var10 = var17 + var3; var18-- != 0; var10 += 4) {
               var11 -= arg0[var19] * arg0[var10];
               ++var19;
            }

            arg0[var8] = var11;
         }

         double var13 = 0.0D;
         var6 = -1;

         double var15;
         for(var5 = var3; var5 < 4; ++var5) {
            var8 = var17 + 4 * var5 + var3;
            var11 = arg0[var8];
            var18 = var3;
            var19 = var17 + 4 * var5;

            for(var10 = var17 + var3; var18-- != 0; var10 += 4) {
               var11 -= arg0[var19] * arg0[var10];
               ++var19;
            }

            arg0[var8] = var11;
            if ((var15 = var2[var5] * Math.abs(var11)) >= var13) {
               var13 = var15;
               var6 = var5;
            }
         }

         if (var6 < 0) {
            throw new RuntimeException(VecMathI18N.null(MysqlCreateFullTextAnalyzerStatement.null("\u0010\u001d)\u000e4\u0004i\u0018lM")));
         }

         if (var3 != var6) {
            var18 = 4;
            var19 = var17 + 4 * var6;

            for(var10 = var17 + 4 * var3; var18-- != 0; arg0[var10++] = var15) {
               var15 = arg0[var19];
               arg0[var19++] = arg0[var10];
            }

            var2[var6] = var2[var3];
         }

         arg1[var3] = var6;
         if (arg0[var17 + 4 * var3 + var3] == 0.0D) {
            return false;
         }

         if (var3 != 3) {
            var15 = 1.0D / arg0[var17 + 4 * var3 + var3];
            var8 = var17 + 4 * (var3 + 1) + var3;

            for(var5 = 3 - var3; var5-- != 0; var8 += 4) {
               arg0[var8] *= var15;
            }
         }
      }

      return true;
   }

   public static void null(double[] arg0, int[] arg1, double[] arg2) {
      byte var8 = 0;

      for(int var7 = 0; var7 < 4; ++var7) {
         int var9 = var7;
         int var4 = -1;

         int var10;
         for(int var3 = 0; var3 < 4; ++var3) {
            int var5 = arg1[var8 + var3];
            double var11 = arg2[var9 + 4 * var5];
            arg2[var9 + 4 * var5] = arg2[var9 + 4 * var3];
            if (var4 >= 0) {
               var10 = var3 * 4;

               for(int var6 = var4; var6 <= var3 - 1; ++var6) {
                  var11 -= arg0[var10 + var6] * arg2[var9 + 4 * var6];
               }
            } else if (var11 != 0.0D) {
               var4 = var3;
            }

            arg2[var9 + 4 * var3] = var11;
         }

         byte var13 = 12;
         arg2[var9 + 12] /= arg0[var13 + 3];
         var10 = var13 - 4;
         arg2[var9 + 8] = (arg2[var9 + 8] - arg0[var10 + 3] * arg2[var9 + 12]) / arg0[var10 + 2];
         var10 -= 4;
         arg2[var9 + 4] = (arg2[var9 + 4] - arg0[var10 + 2] * arg2[var9 + 8] - arg0[var10 + 3] * arg2[var9 + 12]) / arg0[var10 + 1];
         var10 -= 4;
         arg2[var9 + 0] = (arg2[var9 + 0] - arg0[var10 + 1] * arg2[var9 + 4] - arg0[var10 + 2] * arg2[var9 + 8] - arg0[var10 + 3] * arg2[var9 + 12]) / arg0[var10 + 0];
      }

   }

   public final double final() {
      double var1 = thisx.char * (thisx.else * thisx.new * thisx.final + thisx.goto * thisx.const * thisx.null + thisx.case * thisx.long * thisx.int - thisx.case * thisx.new * thisx.null - thisx.else * thisx.const * thisx.int - thisx.goto * thisx.long * thisx.final);
      var1 -= thisx.float * (thisx.do * thisx.new * thisx.final + thisx.goto * thisx.const * thisx.false + thisx.case * thisx.short * thisx.int - thisx.case * thisx.new * thisx.false - thisx.do * thisx.const * thisx.int - thisx.goto * thisx.short * thisx.final);
      var1 += thisx.break * (thisx.do * thisx.long * thisx.final + thisx.else * thisx.const * thisx.false + thisx.case * thisx.short * thisx.null - thisx.case * thisx.long * thisx.false - thisx.do * thisx.const * thisx.null - thisx.else * thisx.short * thisx.final);
      var1 -= thisx.catch * (thisx.do * thisx.long * thisx.int + thisx.else * thisx.new * thisx.false + thisx.goto * thisx.short * thisx.null - thisx.goto * thisx.long * thisx.false - thisx.do * thisx.new * thisx.null - thisx.else * thisx.short * thisx.int);
      return var1;
   }

   public final void void(double arg0) {
      thisx.char = arg0;
      thisx.float = 0.0D;
      thisx.break = 0.0D;
      thisx.catch = 0.0D;
      thisx.do = 0.0D;
      thisx.else = arg0;
      thisx.goto = 0.0D;
      thisx.case = 0.0D;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = arg0;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void long(Vector3d arg0) {
      thisx.char = 1.0D;
      thisx.float = 0.0D;
      thisx.break = 0.0D;
      thisx.catch = arg0.int;
      thisx.do = 0.0D;
      thisx.else = 1.0D;
      thisx.goto = 0.0D;
      thisx.case = arg0.final;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = 1.0D;
      thisx.const = arg0.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(double arg0, Vector3d arg1) {
      thisx.char = arg0;
      thisx.float = 0.0D;
      thisx.break = 0.0D;
      thisx.catch = arg1.int;
      thisx.do = 0.0D;
      thisx.else = arg0;
      thisx.goto = 0.0D;
      thisx.case = arg1.final;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = arg0;
      thisx.const = arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(Vector3d arg0, double arg1) {
      thisx.char = arg1;
      thisx.float = 0.0D;
      thisx.break = 0.0D;
      thisx.catch = arg1 * arg0.int;
      thisx.do = 0.0D;
      thisx.else = arg1;
      thisx.goto = 0.0D;
      thisx.case = arg1 * arg0.final;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = arg1;
      thisx.const = arg1 * arg0.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(Matrix3f arg0, Vector3f arg1, float arg2) {
      thisx.char = (double)(arg0.case * arg2);
      thisx.float = (double)(arg0.short * arg2);
      thisx.break = (double)(arg0.long * arg2);
      thisx.catch = (double)arg1.int;
      thisx.do = (double)(arg0.new * arg2);
      thisx.else = (double)(arg0.const * arg2);
      thisx.goto = (double)(arg0.false * arg2);
      thisx.case = (double)arg1.final;
      thisx.short = (double)(arg0.null * arg2);
      thisx.long = (double)(arg0.int * arg2);
      thisx.new = (double)(arg0.final * arg2);
      thisx.const = (double)arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void null(Matrix3d arg0, Vector3d arg1, double arg2) {
      thisx.char = arg0.case * arg2;
      thisx.float = arg0.short * arg2;
      thisx.break = arg0.long * arg2;
      thisx.catch = arg1.int;
      thisx.do = arg0.new * arg2;
      thisx.else = arg0.const * arg2;
      thisx.goto = arg0.false * arg2;
      thisx.case = arg1.final;
      thisx.short = arg0.null * arg2;
      thisx.long = arg0.int * arg2;
      thisx.new = arg0.final * arg2;
      thisx.const = arg1.this;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void class(Vector3d arg0) {
      thisx.catch = arg0.int;
      thisx.case = arg0.final;
      thisx.const = arg0.this;
   }

   public final void try(double arg0) {
      double var3 = Math.sin(arg0);
      double var5 = Math.cos(arg0);
      thisx.char = 1.0D;
      thisx.float = 0.0D;
      thisx.break = 0.0D;
      thisx.catch = 0.0D;
      thisx.do = 0.0D;
      thisx.else = var5;
      thisx.goto = -var3;
      thisx.case = 0.0D;
      thisx.short = 0.0D;
      thisx.long = var3;
      thisx.new = var5;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void int(double arg0) {
      double var3 = Math.sin(arg0);
      double var5 = Math.cos(arg0);
      thisx.char = var5;
      thisx.float = 0.0D;
      thisx.break = var3;
      thisx.catch = 0.0D;
      thisx.do = 0.0D;
      thisx.else = 1.0D;
      thisx.goto = 0.0D;
      thisx.case = 0.0D;
      thisx.short = -var3;
      thisx.long = 0.0D;
      thisx.new = var5;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void enum(double arg0) {
      double var3 = Math.sin(arg0);
      double var5 = Math.cos(arg0);
      thisx.char = var5;
      thisx.float = -var3;
      thisx.break = 0.0D;
      thisx.catch = 0.0D;
      thisx.do = var3;
      thisx.else = var5;
      thisx.goto = 0.0D;
      thisx.case = 0.0D;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = 1.0D;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void final(double arg0) {
      thisx.char *= arg0;
      thisx.float *= arg0;
      thisx.break *= arg0;
      thisx.catch *= arg0;
      thisx.do *= arg0;
      thisx.else *= arg0;
      thisx.goto *= arg0;
      thisx.case *= arg0;
      thisx.short *= arg0;
      thisx.long *= arg0;
      thisx.new *= arg0;
      thisx.const *= arg0;
      thisx.false *= arg0;
      thisx.null *= arg0;
      thisx.int *= arg0;
      thisx.final *= arg0;
   }

   public final void null(double arg0, Matrix4d arg1) {
      thisx.char = arg1.char * arg0;
      thisx.float = arg1.float * arg0;
      thisx.break = arg1.break * arg0;
      thisx.catch = arg1.catch * arg0;
      thisx.do = arg1.do * arg0;
      thisx.else = arg1.else * arg0;
      thisx.goto = arg1.goto * arg0;
      thisx.case = arg1.case * arg0;
      thisx.short = arg1.short * arg0;
      thisx.long = arg1.long * arg0;
      thisx.new = arg1.new * arg0;
      thisx.const = arg1.const * arg0;
      thisx.false = arg1.false * arg0;
      thisx.null = arg1.null * arg0;
      thisx.int = arg1.int * arg0;
      thisx.final = arg1.final * arg0;
   }

   public final void class(Matrix4d arg0) {
      double var2 = thisx.char * arg0.char + thisx.float * arg0.do + thisx.break * arg0.short + thisx.catch * arg0.false;
      double var4 = thisx.char * arg0.float + thisx.float * arg0.else + thisx.break * arg0.long + thisx.catch * arg0.null;
      double var6 = thisx.char * arg0.break + thisx.float * arg0.goto + thisx.break * arg0.new + thisx.catch * arg0.int;
      double var8 = thisx.char * arg0.catch + thisx.float * arg0.case + thisx.break * arg0.const + thisx.catch * arg0.final;
      double var10 = thisx.do * arg0.char + thisx.else * arg0.do + thisx.goto * arg0.short + thisx.case * arg0.false;
      double var12 = thisx.do * arg0.float + thisx.else * arg0.else + thisx.goto * arg0.long + thisx.case * arg0.null;
      double var14 = thisx.do * arg0.break + thisx.else * arg0.goto + thisx.goto * arg0.new + thisx.case * arg0.int;
      double var16 = thisx.do * arg0.catch + thisx.else * arg0.case + thisx.goto * arg0.const + thisx.case * arg0.final;
      double var18 = thisx.short * arg0.char + thisx.long * arg0.do + thisx.new * arg0.short + thisx.const * arg0.false;
      double var20 = thisx.short * arg0.float + thisx.long * arg0.else + thisx.new * arg0.long + thisx.const * arg0.null;
      double var22 = thisx.short * arg0.break + thisx.long * arg0.goto + thisx.new * arg0.new + thisx.const * arg0.int;
      double var24 = thisx.short * arg0.catch + thisx.long * arg0.case + thisx.new * arg0.const + thisx.const * arg0.final;
      double var26 = thisx.false * arg0.char + thisx.null * arg0.do + thisx.int * arg0.short + thisx.final * arg0.false;
      double var28 = thisx.false * arg0.float + thisx.null * arg0.else + thisx.int * arg0.long + thisx.final * arg0.null;
      double var30 = thisx.false * arg0.break + thisx.null * arg0.goto + thisx.int * arg0.new + thisx.final * arg0.int;
      double var32 = thisx.false * arg0.catch + thisx.null * arg0.case + thisx.int * arg0.const + thisx.final * arg0.final;
      thisx.char = var2;
      thisx.float = var4;
      thisx.break = var6;
      thisx.catch = var8;
      thisx.do = var10;
      thisx.else = var12;
      thisx.goto = var14;
      thisx.case = var16;
      thisx.short = var18;
      thisx.long = var20;
      thisx.new = var22;
      thisx.const = var24;
      thisx.false = var26;
      thisx.null = var28;
      thisx.int = var30;
      thisx.final = var32;
   }

   public final void const(Matrix4d arg0, Matrix4d arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.char = arg0.char * arg1.char + arg0.float * arg1.do + arg0.break * arg1.short + arg0.catch * arg1.false;
         thisx.float = arg0.char * arg1.float + arg0.float * arg1.else + arg0.break * arg1.long + arg0.catch * arg1.null;
         thisx.break = arg0.char * arg1.break + arg0.float * arg1.goto + arg0.break * arg1.new + arg0.catch * arg1.int;
         thisx.catch = arg0.char * arg1.catch + arg0.float * arg1.case + arg0.break * arg1.const + arg0.catch * arg1.final;
         thisx.do = arg0.do * arg1.char + arg0.else * arg1.do + arg0.goto * arg1.short + arg0.case * arg1.false;
         thisx.else = arg0.do * arg1.float + arg0.else * arg1.else + arg0.goto * arg1.long + arg0.case * arg1.null;
         thisx.goto = arg0.do * arg1.break + arg0.else * arg1.goto + arg0.goto * arg1.new + arg0.case * arg1.int;
         thisx.case = arg0.do * arg1.catch + arg0.else * arg1.case + arg0.goto * arg1.const + arg0.case * arg1.final;
         thisx.short = arg0.short * arg1.char + arg0.long * arg1.do + arg0.new * arg1.short + arg0.const * arg1.false;
         thisx.long = arg0.short * arg1.float + arg0.long * arg1.else + arg0.new * arg1.long + arg0.const * arg1.null;
         thisx.new = arg0.short * arg1.break + arg0.long * arg1.goto + arg0.new * arg1.new + arg0.const * arg1.int;
         thisx.const = arg0.short * arg1.catch + arg0.long * arg1.case + arg0.new * arg1.const + arg0.const * arg1.final;
         thisx.false = arg0.false * arg1.char + arg0.null * arg1.do + arg0.int * arg1.short + arg0.final * arg1.false;
         thisx.null = arg0.false * arg1.float + arg0.null * arg1.else + arg0.int * arg1.long + arg0.final * arg1.null;
         thisx.int = arg0.false * arg1.break + arg0.null * arg1.goto + arg0.int * arg1.new + arg0.final * arg1.int;
         thisx.final = arg0.false * arg1.catch + arg0.null * arg1.case + arg0.int * arg1.const + arg0.final * arg1.final;
      } else {
         double var3 = arg0.char * arg1.char + arg0.float * arg1.do + arg0.break * arg1.short + arg0.catch * arg1.false;
         double var5 = arg0.char * arg1.float + arg0.float * arg1.else + arg0.break * arg1.long + arg0.catch * arg1.null;
         double var7 = arg0.char * arg1.break + arg0.float * arg1.goto + arg0.break * arg1.new + arg0.catch * arg1.int;
         double var9 = arg0.char * arg1.catch + arg0.float * arg1.case + arg0.break * arg1.const + arg0.catch * arg1.final;
         double var11 = arg0.do * arg1.char + arg0.else * arg1.do + arg0.goto * arg1.short + arg0.case * arg1.false;
         double var13 = arg0.do * arg1.float + arg0.else * arg1.else + arg0.goto * arg1.long + arg0.case * arg1.null;
         double var15 = arg0.do * arg1.break + arg0.else * arg1.goto + arg0.goto * arg1.new + arg0.case * arg1.int;
         double var17 = arg0.do * arg1.catch + arg0.else * arg1.case + arg0.goto * arg1.const + arg0.case * arg1.final;
         double var19 = arg0.short * arg1.char + arg0.long * arg1.do + arg0.new * arg1.short + arg0.const * arg1.false;
         double var21 = arg0.short * arg1.float + arg0.long * arg1.else + arg0.new * arg1.long + arg0.const * arg1.null;
         double var23 = arg0.short * arg1.break + arg0.long * arg1.goto + arg0.new * arg1.new + arg0.const * arg1.int;
         double var25 = arg0.short * arg1.catch + arg0.long * arg1.case + arg0.new * arg1.const + arg0.const * arg1.final;
         double var27 = arg0.false * arg1.char + arg0.null * arg1.do + arg0.int * arg1.short + arg0.final * arg1.false;
         double var29 = arg0.false * arg1.float + arg0.null * arg1.else + arg0.int * arg1.long + arg0.final * arg1.null;
         double var31 = arg0.false * arg1.break + arg0.null * arg1.goto + arg0.int * arg1.new + arg0.final * arg1.int;
         double var33 = arg0.false * arg1.catch + arg0.null * arg1.case + arg0.int * arg1.const + arg0.final * arg1.final;
         thisx.char = var3;
         thisx.float = var5;
         thisx.break = var7;
         thisx.catch = var9;
         thisx.do = var11;
         thisx.else = var13;
         thisx.goto = var15;
         thisx.case = var17;
         thisx.short = var19;
         thisx.long = var21;
         thisx.new = var23;
         thisx.const = var25;
         thisx.false = var27;
         thisx.null = var29;
         thisx.int = var31;
         thisx.final = var33;
      }

   }

   public final void long(Matrix4d arg0, Matrix4d arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.char = arg0.char * arg1.char + arg0.do * arg1.float + arg0.short * arg1.break + arg0.false * arg1.catch;
         thisx.float = arg0.char * arg1.do + arg0.do * arg1.else + arg0.short * arg1.goto + arg0.false * arg1.case;
         thisx.break = arg0.char * arg1.short + arg0.do * arg1.long + arg0.short * arg1.new + arg0.false * arg1.const;
         thisx.catch = arg0.char * arg1.false + arg0.do * arg1.null + arg0.short * arg1.int + arg0.false * arg1.final;
         thisx.do = arg0.float * arg1.char + arg0.else * arg1.float + arg0.long * arg1.break + arg0.null * arg1.catch;
         thisx.else = arg0.float * arg1.do + arg0.else * arg1.else + arg0.long * arg1.goto + arg0.null * arg1.case;
         thisx.goto = arg0.float * arg1.short + arg0.else * arg1.long + arg0.long * arg1.new + arg0.null * arg1.const;
         thisx.case = arg0.float * arg1.false + arg0.else * arg1.null + arg0.long * arg1.int + arg0.null * arg1.final;
         thisx.short = arg0.break * arg1.char + arg0.goto * arg1.float + arg0.new * arg1.break + arg0.int * arg1.catch;
         thisx.long = arg0.break * arg1.do + arg0.goto * arg1.else + arg0.new * arg1.goto + arg0.int * arg1.case;
         thisx.new = arg0.break * arg1.short + arg0.goto * arg1.long + arg0.new * arg1.new + arg0.int * arg1.const;
         thisx.const = arg0.break * arg1.false + arg0.goto * arg1.null + arg0.new * arg1.int + arg0.int * arg1.final;
         thisx.false = arg0.catch * arg1.char + arg0.case * arg1.float + arg0.const * arg1.break + arg0.final * arg1.catch;
         thisx.null = arg0.catch * arg1.do + arg0.case * arg1.else + arg0.const * arg1.goto + arg0.final * arg1.case;
         thisx.int = arg0.catch * arg1.short + arg0.case * arg1.long + arg0.const * arg1.new + arg0.final * arg1.const;
         thisx.final = arg0.catch * arg1.false + arg0.case * arg1.null + arg0.const * arg1.int + arg0.final * arg1.final;
      } else {
         double var3 = arg0.char * arg1.char + arg0.do * arg1.float + arg0.short * arg1.break + arg0.false * arg1.catch;
         double var5 = arg0.char * arg1.do + arg0.do * arg1.else + arg0.short * arg1.goto + arg0.false * arg1.case;
         double var7 = arg0.char * arg1.short + arg0.do * arg1.long + arg0.short * arg1.new + arg0.false * arg1.const;
         double var9 = arg0.char * arg1.false + arg0.do * arg1.null + arg0.short * arg1.int + arg0.false * arg1.final;
         double var11 = arg0.float * arg1.char + arg0.else * arg1.float + arg0.long * arg1.break + arg0.null * arg1.catch;
         double var13 = arg0.float * arg1.do + arg0.else * arg1.else + arg0.long * arg1.goto + arg0.null * arg1.case;
         double var15 = arg0.float * arg1.short + arg0.else * arg1.long + arg0.long * arg1.new + arg0.null * arg1.const;
         double var17 = arg0.float * arg1.false + arg0.else * arg1.null + arg0.long * arg1.int + arg0.null * arg1.final;
         double var19 = arg0.break * arg1.char + arg0.goto * arg1.float + arg0.new * arg1.break + arg0.int * arg1.catch;
         double var21 = arg0.break * arg1.do + arg0.goto * arg1.else + arg0.new * arg1.goto + arg0.int * arg1.case;
         double var23 = arg0.break * arg1.short + arg0.goto * arg1.long + arg0.new * arg1.new + arg0.int * arg1.const;
         double var25 = arg0.break * arg1.false + arg0.goto * arg1.null + arg0.new * arg1.int + arg0.int * arg1.final;
         double var27 = arg0.catch * arg1.char + arg0.case * arg1.float + arg0.const * arg1.break + arg0.final * arg1.catch;
         double var29 = arg0.catch * arg1.do + arg0.case * arg1.else + arg0.const * arg1.goto + arg0.final * arg1.case;
         double var31 = arg0.catch * arg1.short + arg0.case * arg1.long + arg0.const * arg1.new + arg0.final * arg1.const;
         double var33 = arg0.catch * arg1.false + arg0.case * arg1.null + arg0.const * arg1.int + arg0.final * arg1.final;
         thisx.char = var3;
         thisx.float = var5;
         thisx.break = var7;
         thisx.catch = var9;
         thisx.do = var11;
         thisx.else = var13;
         thisx.goto = var15;
         thisx.case = var17;
         thisx.short = var19;
         thisx.long = var21;
         thisx.new = var23;
         thisx.const = var25;
         thisx.false = var27;
         thisx.null = var29;
         thisx.int = var31;
         thisx.final = var33;
      }

   }

   public final void class(Matrix4d arg0, Matrix4d arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.char = arg0.char * arg1.char + arg0.float * arg1.float + arg0.break * arg1.break + arg0.catch * arg1.catch;
         thisx.float = arg0.char * arg1.do + arg0.float * arg1.else + arg0.break * arg1.goto + arg0.catch * arg1.case;
         thisx.break = arg0.char * arg1.short + arg0.float * arg1.long + arg0.break * arg1.new + arg0.catch * arg1.const;
         thisx.catch = arg0.char * arg1.false + arg0.float * arg1.null + arg0.break * arg1.int + arg0.catch * arg1.final;
         thisx.do = arg0.do * arg1.char + arg0.else * arg1.float + arg0.goto * arg1.break + arg0.case * arg1.catch;
         thisx.else = arg0.do * arg1.do + arg0.else * arg1.else + arg0.goto * arg1.goto + arg0.case * arg1.case;
         thisx.goto = arg0.do * arg1.short + arg0.else * arg1.long + arg0.goto * arg1.new + arg0.case * arg1.const;
         thisx.case = arg0.do * arg1.false + arg0.else * arg1.null + arg0.goto * arg1.int + arg0.case * arg1.final;
         thisx.short = arg0.short * arg1.char + arg0.long * arg1.float + arg0.new * arg1.break + arg0.const * arg1.catch;
         thisx.long = arg0.short * arg1.do + arg0.long * arg1.else + arg0.new * arg1.goto + arg0.const * arg1.case;
         thisx.new = arg0.short * arg1.short + arg0.long * arg1.long + arg0.new * arg1.new + arg0.const * arg1.const;
         thisx.const = arg0.short * arg1.false + arg0.long * arg1.null + arg0.new * arg1.int + arg0.const * arg1.final;
         thisx.false = arg0.false * arg1.char + arg0.null * arg1.float + arg0.int * arg1.break + arg0.final * arg1.catch;
         thisx.null = arg0.false * arg1.do + arg0.null * arg1.else + arg0.int * arg1.goto + arg0.final * arg1.case;
         thisx.int = arg0.false * arg1.short + arg0.null * arg1.long + arg0.int * arg1.new + arg0.final * arg1.const;
         thisx.final = arg0.false * arg1.false + arg0.null * arg1.null + arg0.int * arg1.int + arg0.final * arg1.final;
      } else {
         double var3 = arg0.char * arg1.char + arg0.float * arg1.float + arg0.break * arg1.break + arg0.catch * arg1.catch;
         double var5 = arg0.char * arg1.do + arg0.float * arg1.else + arg0.break * arg1.goto + arg0.catch * arg1.case;
         double var7 = arg0.char * arg1.short + arg0.float * arg1.long + arg0.break * arg1.new + arg0.catch * arg1.const;
         double var9 = arg0.char * arg1.false + arg0.float * arg1.null + arg0.break * arg1.int + arg0.catch * arg1.final;
         double var11 = arg0.do * arg1.char + arg0.else * arg1.float + arg0.goto * arg1.break + arg0.case * arg1.catch;
         double var13 = arg0.do * arg1.do + arg0.else * arg1.else + arg0.goto * arg1.goto + arg0.case * arg1.case;
         double var15 = arg0.do * arg1.short + arg0.else * arg1.long + arg0.goto * arg1.new + arg0.case * arg1.const;
         double var17 = arg0.do * arg1.false + arg0.else * arg1.null + arg0.goto * arg1.int + arg0.case * arg1.final;
         double var19 = arg0.short * arg1.char + arg0.long * arg1.float + arg0.new * arg1.break + arg0.const * arg1.catch;
         double var21 = arg0.short * arg1.do + arg0.long * arg1.else + arg0.new * arg1.goto + arg0.const * arg1.case;
         double var23 = arg0.short * arg1.short + arg0.long * arg1.long + arg0.new * arg1.new + arg0.const * arg1.const;
         double var25 = arg0.short * arg1.false + arg0.long * arg1.null + arg0.new * arg1.int + arg0.const * arg1.final;
         double var27 = arg0.false * arg1.char + arg0.null * arg1.float + arg0.int * arg1.break + arg0.final * arg1.catch;
         double var29 = arg0.false * arg1.do + arg0.null * arg1.else + arg0.int * arg1.goto + arg0.final * arg1.case;
         double var31 = arg0.false * arg1.short + arg0.null * arg1.long + arg0.int * arg1.new + arg0.final * arg1.const;
         double var33 = arg0.false * arg1.false + arg0.null * arg1.null + arg0.int * arg1.int + arg0.final * arg1.final;
         thisx.char = var3;
         thisx.float = var5;
         thisx.break = var7;
         thisx.catch = var9;
         thisx.do = var11;
         thisx.else = var13;
         thisx.goto = var15;
         thisx.case = var17;
         thisx.short = var19;
         thisx.long = var21;
         thisx.new = var23;
         thisx.const = var25;
         thisx.false = var27;
         thisx.null = var29;
         thisx.int = var31;
         thisx.final = var33;
      }

   }

   public final void null(Matrix4d arg0, Matrix4d arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.char = arg0.char * arg1.char + arg0.do * arg1.do + arg0.short * arg1.short + arg0.false * arg1.false;
         thisx.float = arg0.char * arg1.float + arg0.do * arg1.else + arg0.short * arg1.long + arg0.false * arg1.null;
         thisx.break = arg0.char * arg1.break + arg0.do * arg1.goto + arg0.short * arg1.new + arg0.false * arg1.int;
         thisx.catch = arg0.char * arg1.catch + arg0.do * arg1.case + arg0.short * arg1.const + arg0.false * arg1.final;
         thisx.do = arg0.float * arg1.char + arg0.else * arg1.do + arg0.long * arg1.short + arg0.null * arg1.false;
         thisx.else = arg0.float * arg1.float + arg0.else * arg1.else + arg0.long * arg1.long + arg0.null * arg1.null;
         thisx.goto = arg0.float * arg1.break + arg0.else * arg1.goto + arg0.long * arg1.new + arg0.null * arg1.int;
         thisx.case = arg0.float * arg1.catch + arg0.else * arg1.case + arg0.long * arg1.const + arg0.null * arg1.final;
         thisx.short = arg0.break * arg1.char + arg0.goto * arg1.do + arg0.new * arg1.short + arg0.int * arg1.false;
         thisx.long = arg0.break * arg1.float + arg0.goto * arg1.else + arg0.new * arg1.long + arg0.int * arg1.null;
         thisx.new = arg0.break * arg1.break + arg0.goto * arg1.goto + arg0.new * arg1.new + arg0.int * arg1.int;
         thisx.const = arg0.break * arg1.catch + arg0.goto * arg1.case + arg0.new * arg1.const + arg0.int * arg1.final;
         thisx.false = arg0.catch * arg1.char + arg0.case * arg1.do + arg0.const * arg1.short + arg0.final * arg1.false;
         thisx.null = arg0.catch * arg1.float + arg0.case * arg1.else + arg0.const * arg1.long + arg0.final * arg1.null;
         thisx.int = arg0.catch * arg1.break + arg0.case * arg1.goto + arg0.const * arg1.new + arg0.final * arg1.int;
         thisx.final = arg0.catch * arg1.catch + arg0.case * arg1.case + arg0.const * arg1.const + arg0.final * arg1.final;
      } else {
         double var3 = arg0.char * arg1.char + arg0.do * arg1.do + arg0.short * arg1.short + arg0.false * arg1.false;
         double var5 = arg0.char * arg1.float + arg0.do * arg1.else + arg0.short * arg1.long + arg0.false * arg1.null;
         double var7 = arg0.char * arg1.break + arg0.do * arg1.goto + arg0.short * arg1.new + arg0.false * arg1.int;
         double var9 = arg0.char * arg1.catch + arg0.do * arg1.case + arg0.short * arg1.const + arg0.false * arg1.final;
         double var11 = arg0.float * arg1.char + arg0.else * arg1.do + arg0.long * arg1.short + arg0.null * arg1.false;
         double var13 = arg0.float * arg1.float + arg0.else * arg1.else + arg0.long * arg1.long + arg0.null * arg1.null;
         double var15 = arg0.float * arg1.break + arg0.else * arg1.goto + arg0.long * arg1.new + arg0.null * arg1.int;
         double var17 = arg0.float * arg1.catch + arg0.else * arg1.case + arg0.long * arg1.const + arg0.null * arg1.final;
         double var19 = arg0.break * arg1.char + arg0.goto * arg1.do + arg0.new * arg1.short + arg0.int * arg1.false;
         double var21 = arg0.break * arg1.float + arg0.goto * arg1.else + arg0.new * arg1.long + arg0.int * arg1.null;
         double var23 = arg0.break * arg1.break + arg0.goto * arg1.goto + arg0.new * arg1.new + arg0.int * arg1.int;
         double var25 = arg0.break * arg1.catch + arg0.goto * arg1.case + arg0.new * arg1.const + arg0.int * arg1.final;
         double var27 = arg0.catch * arg1.char + arg0.case * arg1.do + arg0.const * arg1.short + arg0.final * arg1.false;
         double var29 = arg0.catch * arg1.float + arg0.case * arg1.else + arg0.const * arg1.long + arg0.final * arg1.null;
         double var31 = arg0.catch * arg1.break + arg0.case * arg1.goto + arg0.const * arg1.new + arg0.final * arg1.int;
         double var33 = arg0.catch * arg1.catch + arg0.case * arg1.case + arg0.const * arg1.const + arg0.final * arg1.final;
         thisx.char = var3;
         thisx.float = var5;
         thisx.break = var7;
         thisx.catch = var9;
         thisx.do = var11;
         thisx.else = var13;
         thisx.goto = var15;
         thisx.case = var17;
         thisx.short = var19;
         thisx.long = var21;
         thisx.new = var23;
         thisx.const = var25;
         thisx.false = var27;
         thisx.null = var29;
         thisx.int = var31;
         thisx.final = var33;
      }

   }

   public boolean null(Matrix4d arg0) {
      try {
         return thisx.char == arg0.char && thisx.float == arg0.float && thisx.break == arg0.break && thisx.catch == arg0.catch && thisx.do == arg0.do && thisx.else == arg0.else && thisx.goto == arg0.goto && thisx.case == arg0.case && thisx.short == arg0.short && thisx.long == arg0.long && thisx.new == arg0.new && thisx.const == arg0.const && thisx.false == arg0.false && thisx.null == arg0.null && thisx.int == arg0.int && thisx.final == arg0.final;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Matrix4d var2 = (Matrix4d)arg0;
         return thisx.char == var2.char && thisx.float == var2.float && thisx.break == var2.break && thisx.catch == var2.catch && thisx.do == var2.do && thisx.else == var2.else && thisx.goto == var2.goto && thisx.case == var2.case && thisx.short == var2.short && thisx.long == var2.long && thisx.new == var2.new && thisx.const == var2.const && thisx.false == var2.false && thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final;
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   /** @deprecated */
   public boolean null(Matrix4d arg0, float arg1) {
      return thisx.null(arg0, (double)arg1);
   }

   public boolean null(Matrix4d arg0, double arg1) {
      double var4 = thisx.char - arg0.char;
      if ((var4 < 0.0D ? -var4 : var4) > arg1) {
         return false;
      } else {
         var4 = thisx.float - arg0.float;
         if ((var4 < 0.0D ? -var4 : var4) > arg1) {
            return false;
         } else {
            var4 = thisx.break - arg0.break;
            if ((var4 < 0.0D ? -var4 : var4) > arg1) {
               return false;
            } else {
               var4 = thisx.catch - arg0.catch;
               if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                  return false;
               } else {
                  var4 = thisx.do - arg0.do;
                  if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                     return false;
                  } else {
                     var4 = thisx.else - arg0.else;
                     if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                        return false;
                     } else {
                        var4 = thisx.goto - arg0.goto;
                        if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                           return false;
                        } else {
                           var4 = thisx.case - arg0.case;
                           if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                              return false;
                           } else {
                              var4 = thisx.short - arg0.short;
                              if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                                 return false;
                              } else {
                                 var4 = thisx.long - arg0.long;
                                 if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                                    return false;
                                 } else {
                                    var4 = thisx.new - arg0.new;
                                    if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                                       return false;
                                    } else {
                                       var4 = thisx.const - arg0.const;
                                       if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                                          return false;
                                       } else {
                                          var4 = thisx.false - arg0.false;
                                          if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                                             return false;
                                          } else {
                                             var4 = thisx.null - arg0.null;
                                             if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                                                return false;
                                             } else {
                                                var4 = thisx.int - arg0.int;
                                                if ((var4 < 0.0D ? -var4 : var4) > arg1) {
                                                   return false;
                                                } else {
                                                   var4 = thisx.final - arg0.final;
                                                   return !((var4 < 0.0D ? -var4 : var4) > arg1);
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.char);
      var1 = VecMathUtil.null(var1, thisx.float);
      var1 = VecMathUtil.null(var1, thisx.break);
      var1 = VecMathUtil.null(var1, thisx.catch);
      var1 = VecMathUtil.null(var1, thisx.do);
      var1 = VecMathUtil.null(var1, thisx.else);
      var1 = VecMathUtil.null(var1, thisx.goto);
      var1 = VecMathUtil.null(var1, thisx.case);
      var1 = VecMathUtil.null(var1, thisx.short);
      var1 = VecMathUtil.null(var1, thisx.long);
      var1 = VecMathUtil.null(var1, thisx.new);
      var1 = VecMathUtil.null(var1, thisx.const);
      var1 = VecMathUtil.null(var1, thisx.false);
      var1 = VecMathUtil.null(var1, thisx.null);
      var1 = VecMathUtil.null(var1, thisx.int);
      var1 = VecMathUtil.null(var1, thisx.final);
      return VecMathUtil.null(var1);
   }

   public final void null(Tuple4d arg0, Tuple4d arg1) {
      double var3 = thisx.char * arg0.null + thisx.float * arg0.int + thisx.break * arg0.final + thisx.catch * arg0.this;
      double var5 = thisx.do * arg0.null + thisx.else * arg0.int + thisx.goto * arg0.final + thisx.case * arg0.this;
      double var7 = thisx.short * arg0.null + thisx.long * arg0.int + thisx.new * arg0.final + thisx.const * arg0.this;
      arg1.this = thisx.false * arg0.null + thisx.null * arg0.int + thisx.int * arg0.final + thisx.final * arg0.this;
      arg1.null = var3;
      arg1.int = var5;
      arg1.final = var7;
   }

   public final void null(Tuple4d arg0) {
      double var2 = thisx.char * arg0.null + thisx.float * arg0.int + thisx.break * arg0.final + thisx.catch * arg0.this;
      double var4 = thisx.do * arg0.null + thisx.else * arg0.int + thisx.goto * arg0.final + thisx.case * arg0.this;
      double var6 = thisx.short * arg0.null + thisx.long * arg0.int + thisx.new * arg0.final + thisx.const * arg0.this;
      arg0.this = thisx.false * arg0.null + thisx.null * arg0.int + thisx.int * arg0.final + thisx.final * arg0.this;
      arg0.null = var2;
      arg0.int = var4;
      arg0.final = var6;
   }

   public final void null(Tuple4f arg0, Tuple4f arg1) {
      float var3 = (float)(thisx.char * (double)arg0.null + thisx.float * (double)arg0.int + thisx.break * (double)arg0.final + thisx.catch * (double)arg0.this);
      float var4 = (float)(thisx.do * (double)arg0.null + thisx.else * (double)arg0.int + thisx.goto * (double)arg0.final + thisx.case * (double)arg0.this);
      float var5 = (float)(thisx.short * (double)arg0.null + thisx.long * (double)arg0.int + thisx.new * (double)arg0.final + thisx.const * (double)arg0.this);
      arg1.this = (float)(thisx.false * (double)arg0.null + thisx.null * (double)arg0.int + thisx.int * (double)arg0.final + thisx.final * (double)arg0.this);
      arg1.null = var3;
      arg1.int = var4;
      arg1.final = var5;
   }

   public final void null(Tuple4f arg0) {
      float var2 = (float)(thisx.char * (double)arg0.null + thisx.float * (double)arg0.int + thisx.break * (double)arg0.final + thisx.catch * (double)arg0.this);
      float var3 = (float)(thisx.do * (double)arg0.null + thisx.else * (double)arg0.int + thisx.goto * (double)arg0.final + thisx.case * (double)arg0.this);
      float var4 = (float)(thisx.short * (double)arg0.null + thisx.long * (double)arg0.int + thisx.new * (double)arg0.final + thisx.const * (double)arg0.this);
      arg0.this = (float)(thisx.false * (double)arg0.null + thisx.null * (double)arg0.int + thisx.int * (double)arg0.final + thisx.final * (double)arg0.this);
      arg0.null = var2;
      arg0.int = var3;
      arg0.final = var4;
   }

   public final void null(Point3d arg0, Point3d arg1) {
      double var3 = thisx.char * arg0.int + thisx.float * arg0.final + thisx.break * arg0.this + thisx.catch;
      double var5 = thisx.do * arg0.int + thisx.else * arg0.final + thisx.goto * arg0.this + thisx.case;
      arg1.this = thisx.short * arg0.int + thisx.long * arg0.final + thisx.new * arg0.this + thisx.const;
      arg1.int = var3;
      arg1.final = var5;
   }

   public final void null(Point3d arg0) {
      double var2 = thisx.char * arg0.int + thisx.float * arg0.final + thisx.break * arg0.this + thisx.catch;
      double var4 = thisx.do * arg0.int + thisx.else * arg0.final + thisx.goto * arg0.this + thisx.case;
      arg0.this = thisx.short * arg0.int + thisx.long * arg0.final + thisx.new * arg0.this + thisx.const;
      arg0.int = var2;
      arg0.final = var4;
   }

   public final void null(Point3f arg0, Point3f arg1) {
      float var3 = (float)(thisx.char * (double)arg0.int + thisx.float * (double)arg0.final + thisx.break * (double)arg0.this + thisx.catch);
      float var4 = (float)(thisx.do * (double)arg0.int + thisx.else * (double)arg0.final + thisx.goto * (double)arg0.this + thisx.case);
      arg1.this = (float)(thisx.short * (double)arg0.int + thisx.long * (double)arg0.final + thisx.new * (double)arg0.this + thisx.const);
      arg1.int = var3;
      arg1.final = var4;
   }

   public final void null(Point3f arg0) {
      float var2 = (float)(thisx.char * (double)arg0.int + thisx.float * (double)arg0.final + thisx.break * (double)arg0.this + thisx.catch);
      float var3 = (float)(thisx.do * (double)arg0.int + thisx.else * (double)arg0.final + thisx.goto * (double)arg0.this + thisx.case);
      arg0.this = (float)(thisx.short * (double)arg0.int + thisx.long * (double)arg0.final + thisx.new * (double)arg0.this + thisx.const);
      arg0.int = var2;
      arg0.final = var3;
   }

   public final void null(Vector3d arg0, Vector3d arg1) {
      double var3 = thisx.char * arg0.int + thisx.float * arg0.final + thisx.break * arg0.this;
      double var5 = thisx.do * arg0.int + thisx.else * arg0.final + thisx.goto * arg0.this;
      arg1.this = thisx.short * arg0.int + thisx.long * arg0.final + thisx.new * arg0.this;
      arg1.int = var3;
      arg1.final = var5;
   }

   public final void null(Vector3d arg0) {
      double var2 = thisx.char * arg0.int + thisx.float * arg0.final + thisx.break * arg0.this;
      double var4 = thisx.do * arg0.int + thisx.else * arg0.final + thisx.goto * arg0.this;
      arg0.this = thisx.short * arg0.int + thisx.long * arg0.final + thisx.new * arg0.this;
      arg0.int = var2;
      arg0.final = var4;
   }

   public final void null(Vector3f arg0, Vector3f arg1) {
      float var3 = (float)(thisx.char * (double)arg0.int + thisx.float * (double)arg0.final + thisx.break * (double)arg0.this);
      float var4 = (float)(thisx.do * (double)arg0.int + thisx.else * (double)arg0.final + thisx.goto * (double)arg0.this);
      arg1.this = (float)(thisx.short * (double)arg0.int + thisx.long * (double)arg0.final + thisx.new * (double)arg0.this);
      arg1.int = var3;
      arg1.final = var4;
   }

   public final void null(Vector3f arg0) {
      float var2 = (float)(thisx.char * (double)arg0.int + thisx.float * (double)arg0.final + thisx.break * (double)arg0.this);
      float var3 = (float)(thisx.do * (double)arg0.int + thisx.else * (double)arg0.final + thisx.goto * (double)arg0.this);
      arg0.this = (float)(thisx.short * (double)arg0.int + thisx.long * (double)arg0.final + thisx.new * (double)arg0.this);
      arg0.int = var2;
      arg0.final = var3;
   }

   public final void null(Matrix3d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.char = arg0.case * var3[0];
      thisx.float = arg0.short * var3[1];
      thisx.break = arg0.long * var3[2];
      thisx.do = arg0.new * var3[0];
      thisx.else = arg0.const * var3[1];
      thisx.goto = arg0.false * var3[2];
      thisx.short = arg0.null * var3[0];
      thisx.long = arg0.int * var3[1];
      thisx.new = arg0.final * var3[2];
   }

   public final void null(Matrix3f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.char = (double)arg0.case * var3[0];
      thisx.float = (double)arg0.short * var3[1];
      thisx.break = (double)arg0.long * var3[2];
      thisx.do = (double)arg0.new * var3[0];
      thisx.else = (double)arg0.const * var3[1];
      thisx.goto = (double)arg0.false * var3[2];
      thisx.short = (double)arg0.null * var3[0];
      thisx.long = (double)arg0.int * var3[1];
      thisx.new = (double)arg0.final * var3[2];
   }

   public final void null(Quat4f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.char = (1.0D - (double)(2.0F * arg0.int * arg0.int) - (double)(2.0F * arg0.final * arg0.final)) * var3[0];
      thisx.do = 2.0D * (double)(arg0.null * arg0.int + arg0.this * arg0.final) * var3[0];
      thisx.short = 2.0D * (double)(arg0.null * arg0.final - arg0.this * arg0.int) * var3[0];
      thisx.float = 2.0D * (double)(arg0.null * arg0.int - arg0.this * arg0.final) * var3[1];
      thisx.else = (1.0D - (double)(2.0F * arg0.null * arg0.null) - (double)(2.0F * arg0.final * arg0.final)) * var3[1];
      thisx.long = 2.0D * (double)(arg0.int * arg0.final + arg0.this * arg0.null) * var3[1];
      thisx.break = 2.0D * (double)(arg0.null * arg0.final + arg0.this * arg0.int) * var3[2];
      thisx.goto = 2.0D * (double)(arg0.int * arg0.final - arg0.this * arg0.null) * var3[2];
      thisx.new = (1.0D - (double)(2.0F * arg0.null * arg0.null) - (double)(2.0F * arg0.int * arg0.int)) * var3[2];
   }

   public final void null(Quat4d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.char = (1.0D - 2.0D * arg0.int * arg0.int - 2.0D * arg0.final * arg0.final) * var3[0];
      thisx.do = 2.0D * (arg0.null * arg0.int + arg0.this * arg0.final) * var3[0];
      thisx.short = 2.0D * (arg0.null * arg0.final - arg0.this * arg0.int) * var3[0];
      thisx.float = 2.0D * (arg0.null * arg0.int - arg0.this * arg0.final) * var3[1];
      thisx.else = (1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.final * arg0.final) * var3[1];
      thisx.long = 2.0D * (arg0.int * arg0.final + arg0.this * arg0.null) * var3[1];
      thisx.break = 2.0D * (arg0.null * arg0.final + arg0.this * arg0.int) * var3[2];
      thisx.goto = 2.0D * (arg0.int * arg0.final - arg0.this * arg0.null) * var3[2];
      thisx.new = (1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.int * arg0.int) * var3[2];
   }

   public final void null(AxisAngle4d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      double var4 = 1.0D / Math.sqrt(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int);
      double var6 = arg0.false * var4;
      double var8 = arg0.null * var4;
      double var10 = arg0.int * var4;
      double var12 = Math.sin(arg0.final);
      double var14 = Math.cos(arg0.final);
      double var16 = 1.0D - var14;
      double var18 = arg0.false * arg0.int;
      double var20 = arg0.false * arg0.null;
      double var22 = arg0.null * arg0.int;
      thisx.char = (var16 * var6 * var6 + var14) * var3[0];
      thisx.float = (var16 * var20 - var12 * var10) * var3[1];
      thisx.break = (var16 * var18 + var12 * var8) * var3[2];
      thisx.do = (var16 * var20 + var12 * var10) * var3[0];
      thisx.else = (var16 * var8 * var8 + var14) * var3[1];
      thisx.goto = (var16 * var22 - var12 * var6) * var3[2];
      thisx.short = (var16 * var18 - var12 * var8) * var3[0];
      thisx.long = (var16 * var22 + var12 * var6) * var3[1];
      thisx.new = (var16 * var10 * var10 + var14) * var3[2];
   }

   public final void class() {
      thisx.char = 0.0D;
      thisx.float = 0.0D;
      thisx.break = 0.0D;
      thisx.catch = 0.0D;
      thisx.do = 0.0D;
      thisx.else = 0.0D;
      thisx.goto = 0.0D;
      thisx.case = 0.0D;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = 0.0D;
      thisx.const = 0.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 0.0D;
   }

   public final void null() {
      thisx.char = -thisx.char;
      thisx.float = -thisx.float;
      thisx.break = -thisx.break;
      thisx.catch = -thisx.catch;
      thisx.do = -thisx.do;
      thisx.else = -thisx.else;
      thisx.goto = -thisx.goto;
      thisx.case = -thisx.case;
      thisx.short = -thisx.short;
      thisx.long = -thisx.long;
      thisx.new = -thisx.new;
      thisx.const = -thisx.const;
      thisx.false = -thisx.false;
      thisx.null = -thisx.null;
      thisx.int = -thisx.int;
      thisx.final = -thisx.final;
   }

   public final void null(Matrix4d arg0) {
      thisx.char = -arg0.char;
      thisx.float = -arg0.float;
      thisx.break = -arg0.break;
      thisx.catch = -arg0.catch;
      thisx.do = -arg0.do;
      thisx.else = -arg0.else;
      thisx.goto = -arg0.goto;
      thisx.case = -arg0.case;
      thisx.short = -arg0.short;
      thisx.long = -arg0.long;
      thisx.new = -arg0.new;
      thisx.const = -arg0.const;
      thisx.false = -arg0.false;
      thisx.null = -arg0.null;
      thisx.int = -arg0.int;
      thisx.final = -arg0.final;
   }

   private final void null(double[] arg0, double[] arg1) {
      double[] var3 = new double[]{thisx.char, thisx.float, thisx.break, thisx.do, thisx.else, thisx.goto, thisx.short, thisx.long, thisx.new};
      Matrix3d.class(var3, arg0, arg1);
   }

   public Object clone() {
      Matrix4d var1 = null;

      try {
         var1 = (Matrix4d)super.clone();
         return var1;
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }
   }

   public final double if() {
      return thisx.char;
   }

   public final void if(double arg0) {
      thisx.char = arg0;
   }

   public final double case() {
      return thisx.float;
   }

   public final void case(double arg0) {
      thisx.float = arg0;
   }

   public final double for() {
      return thisx.break;
   }

   public final void for(double arg0) {
      thisx.break = arg0;
   }

   public final double false() {
      return thisx.do;
   }

   public final void false(double arg0) {
      thisx.do = arg0;
   }

   public final double float() {
      return thisx.else;
   }

   public final void float(double arg0) {
      thisx.else = arg0;
   }

   public final double char() {
      return thisx.goto;
   }

   public final void char(double arg0) {
      thisx.goto = arg0;
   }

   public final double else() {
      return thisx.short;
   }

   public final void else(double arg0) {
      thisx.short = arg0;
   }

   public final double new() {
      return thisx.long;
   }

   public final void new(double arg0) {
      thisx.long = arg0;
   }

   public final double this() {
      return thisx.new;
   }

   public final void this(double arg0) {
      thisx.new = arg0;
   }

   public final double short() {
      return thisx.catch;
   }

   public final void short(double arg0) {
      thisx.catch = arg0;
   }

   public final double do() {
      return thisx.case;
   }

   public final void do(double arg0) {
      thisx.case = arg0;
   }

   public final double true() {
      return thisx.const;
   }

   public final void true(double arg0) {
      thisx.const = arg0;
   }

   public final double const() {
      return thisx.false;
   }

   public final void const(double arg0) {
      thisx.false = arg0;
   }

   public final double long() {
      return thisx.null;
   }

   public final void long(double arg0) {
      thisx.null = arg0;
   }

   public final double class() {
      return thisx.int;
   }

   public final void class(double arg0) {
      thisx.int = arg0;
   }

   public final double null() {
      return thisx.final;
   }

   public final void null(double arg0) {
      thisx.final = arg0;
   }

   public static String null(String arg0) {
      int var10000 = 2 << 3 ^ 2 ^ 5;
      int var10001 = (2 ^ 5) << 4 ^ (3 ^ 5) << 1;
      int var10002 = (2 ^ 5) << 4 ^ 3 << 1;
      int var10003 = arg0.length();
      char[] var10004 = new char[var10003];
      boolean var10006 = true;
      int var5 = var10003 - 1;
      var10003 = var10002;
      int var3;
      var10002 = var3 = var5;
      char[] var1 = var10004;
      int var4 = var10003;
      var10000 = var10002;

      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {
         var10001 = var3;
         char var6 = arg0.charAt(var3);
         --var3;
         var1[var10001] = (char)(var6 ^ var2);
         if (var3 < 0) {
            break;
         }

         var10002 = var3--;
         var1[var10002] = (char)(arg0.charAt(var10002) ^ var4);
      }

      return new String(var1);
   }
}
