package javax.vecmath;

import android.accounts.NetworkErrorException;
import java.io.Serializable;
import net.minecraft.enchantment.EnchantmentArrowInfinite;
import net.minecraft.world.storage.loot.properties.EntityPropertyManager;

public class Matrix3d implements Serializable, Cloneable {
   public static final long goto = 6837536777072402710L;
   public double case;
   public double short;
   public double long;
   public double new;
   public double const;
   public double false;
   public double null;
   public double int;
   public double final;
   private static final double this = 1.110223024E-16D;

   public Matrix3d(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5, double arg6, double arg7, double arg8) {
      thisx.case = arg0;
      thisx.short = arg1;
      thisx.long = arg2;
      thisx.new = arg3;
      thisx.const = arg4;
      thisx.false = arg5;
      thisx.null = arg6;
      thisx.int = arg7;
      thisx.final = arg8;
   }

   public Matrix3d(double[] arg0) {
      thisx.case = arg0[0];
      thisx.short = arg0[1];
      thisx.long = arg0[2];
      thisx.new = arg0[3];
      thisx.const = arg0[4];
      thisx.false = arg0[5];
      thisx.null = arg0[6];
      thisx.int = arg0[7];
      thisx.final = arg0[8];
   }

   public Matrix3d(Matrix3d arg0) {
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

   public Matrix3d(Matrix3f arg0) {
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

   public Matrix3d() {
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
      return thisx.case + EnchantmentArrowInfinite.null("?k") + thisx.short + NetworkErrorException.null("o*") + thisx.long + "\n" + thisx.new + EnchantmentArrowInfinite.null("?k") + thisx.const + NetworkErrorException.null("o*") + thisx.false + "\n" + thisx.null + EnchantmentArrowInfinite.null("?k") + thisx.int + NetworkErrorException.null("o*") + thisx.final + "\n";
   }

   public final void short() {
      thisx.case = 1.0D;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = 0.0D;
      thisx.const = 1.0D;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void if(double arg0) {
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      thisx.class(var4, var3);
      thisx.case = var3[0] * arg0;
      thisx.short = var3[1] * arg0;
      thisx.long = var3[2] * arg0;
      thisx.new = var3[3] * arg0;
      thisx.const = var3[4] * arg0;
      thisx.false = var3[5] * arg0;
      thisx.null = var3[6] * arg0;
      thisx.int = var3[7] * arg0;
      thisx.final = var3[8] * arg0;
   }

   public final void null(int arg0, int arg1, double arg2) {
      switch(arg0) {
      case 0:
         switch(arg1) {
         case 0:
            thisx.case = arg2;
            return;
         case 1:
            thisx.short = arg2;
            return;
         case 2:
            thisx.long = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxw{")));
         }
      case 1:
         switch(arg1) {
         case 0:
            thisx.new = arg2;
            return;
         case 1:
            thisx.const = arg2;
            return;
         case 2:
            thisx.false = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(NetworkErrorException.null("G\"~1c;9':")));
         }
      case 2:
         switch(arg1) {
         case 0:
            thisx.null = arg2;
            return;
         case 1:
            thisx.int = arg2;
            return;
         case 2:
            thisx.final = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxw{")));
         }
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(NetworkErrorException.null("G\"~1c;9':")));
      }
   }

   public final double null(int arg0, int arg1) {
      switch(arg0) {
      case 0:
         switch(arg1) {
         case 0:
            return thisx.case;
         case 1:
            return thisx.short;
         case 2:
            return thisx.long;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxwz")));
         }
      case 1:
         switch(arg1) {
         case 0:
            return thisx.new;
         case 1:
            return thisx.const;
         case 2:
            return thisx.false;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxwz")));
         }
      case 2:
         switch(arg1) {
         case 0:
            return thisx.null;
         case 1:
            return thisx.int;
         case 2:
            return thisx.final;
         }
      }

      throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxwz")));
   }

   public final void const(int arg0, Vector3d arg1) {
      if (arg0 == 0) {
         arg1.int = thisx.case;
         arg1.final = thisx.short;
         arg1.this = thisx.long;
      } else if (arg0 == 1) {
         arg1.int = thisx.new;
         arg1.final = thisx.const;
         arg1.this = thisx.false;
      } else {
         if (arg0 != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(NetworkErrorException.null("G\"~1c;9'8")));
         }

         arg1.int = thisx.null;
         arg1.final = thisx.int;
         arg1.this = thisx.final;
      }

   }

   public final void const(int arg0, double[] arg1) {
      if (arg0 == 0) {
         arg1[0] = thisx.case;
         arg1[1] = thisx.short;
         arg1[2] = thisx.long;
      } else if (arg0 == 1) {
         arg1[0] = thisx.new;
         arg1[1] = thisx.const;
         arg1[2] = thisx.false;
      } else {
         if (arg0 != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxwy")));
         }

         arg1[0] = thisx.null;
         arg1[1] = thisx.int;
         arg1[2] = thisx.final;
      }

   }

   public final void long(int arg0, Vector3d arg1) {
      if (arg0 == 0) {
         arg1.int = thisx.case;
         arg1.final = thisx.new;
         arg1.this = thisx.null;
      } else if (arg0 == 1) {
         arg1.int = thisx.short;
         arg1.final = thisx.const;
         arg1.this = thisx.int;
      } else {
         if (arg0 != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(NetworkErrorException.null("G\"~1c;9'>")));
         }

         arg1.int = thisx.long;
         arg1.final = thisx.false;
         arg1.this = thisx.final;
      }

   }

   public final void long(int arg0, double[] arg1) {
      if (arg0 == 0) {
         arg1[0] = thisx.case;
         arg1[1] = thisx.new;
         arg1[2] = thisx.null;
      } else if (arg0 == 1) {
         arg1[0] = thisx.short;
         arg1[1] = thisx.const;
         arg1[2] = thisx.int;
      } else {
         if (arg0 != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxw\u007f")));
         }

         arg1[0] = thisx.long;
         arg1[1] = thisx.false;
         arg1[2] = thisx.final;
      }

   }

   public final void class(int arg0, double arg1, double arg2, double arg3) {
      switch(arg0) {
      case 0:
         thisx.case = arg1;
         thisx.short = arg2;
         thisx.long = arg3;
         break;
      case 1:
         thisx.new = arg1;
         thisx.const = arg2;
         thisx.false = arg3;
         break;
      case 2:
         thisx.null = arg1;
         thisx.int = arg2;
         thisx.final = arg3;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(NetworkErrorException.null("G\"~1c;9'<")));
      }

   }

   public final void class(int arg0, Vector3d arg1) {
      switch(arg0) {
      case 0:
         thisx.case = arg1.int;
         thisx.short = arg1.final;
         thisx.long = arg1.this;
         break;
      case 1:
         thisx.new = arg1.int;
         thisx.const = arg1.final;
         thisx.false = arg1.this;
         break;
      case 2:
         thisx.null = arg1.int;
         thisx.int = arg1.final;
         thisx.final = arg1.this;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxw}")));
      }

   }

   public final void class(int arg0, double[] arg1) {
      switch(arg0) {
      case 0:
         thisx.case = arg1[0];
         thisx.short = arg1[1];
         thisx.long = arg1[2];
         break;
      case 1:
         thisx.new = arg1[0];
         thisx.const = arg1[1];
         thisx.false = arg1[2];
         break;
      case 2:
         thisx.null = arg1[0];
         thisx.int = arg1[1];
         thisx.final = arg1[2];
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(NetworkErrorException.null("G\"~1c;9'<")));
      }

   }

   public final void null(int arg0, double arg1, double arg2, double arg3) {
      switch(arg0) {
      case 0:
         thisx.case = arg1;
         thisx.new = arg2;
         thisx.null = arg3;
         break;
      case 1:
         thisx.short = arg1;
         thisx.const = arg2;
         thisx.int = arg3;
         break;
      case 2:
         thisx.long = arg1;
         thisx.false = arg2;
         thisx.final = arg3;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxwr")));
      }

   }

   public final void null(int arg0, Vector3d arg1) {
      switch(arg0) {
      case 0:
         thisx.case = arg1.int;
         thisx.new = arg1.final;
         thisx.null = arg1.this;
         break;
      case 1:
         thisx.short = arg1.int;
         thisx.const = arg1.final;
         thisx.int = arg1.this;
         break;
      case 2:
         thisx.long = arg1.int;
         thisx.false = arg1.final;
         thisx.final = arg1.this;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(NetworkErrorException.null("G\"~1c;9'3")));
      }

   }

   public final void null(int arg0, double[] arg1) {
      switch(arg0) {
      case 0:
         thisx.case = arg1[0];
         thisx.new = arg1[1];
         thisx.null = arg1[2];
         break;
      case 1:
         thisx.short = arg1[0];
         thisx.const = arg1[1];
         thisx.int = arg1[2];
         break;
      case 2:
         thisx.long = arg1[0];
         thisx.false = arg1[1];
         thisx.final = arg1[2];
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(EnchantmentArrowInfinite.null("\u0006r?a\"kxwr")));
      }

   }

   public final double char() {
      double[] var1 = new double[3];
      double[] var2 = new double[9];
      thisx.class(var1, var2);
      return null(var1);
   }

   public final void case(double arg0) {
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

   public final void class(double arg0, Matrix3d arg1) {
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

   public final void short(Matrix3d arg0, Matrix3d arg1) {
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

   public final void char(Matrix3d arg0) {
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

   public final void do(Matrix3d arg0, Matrix3d arg1) {
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

   public final void else(Matrix3d arg0) {
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

   public final void do() {
      double var1 = thisx.new;
      thisx.new = thisx.short;
      thisx.short = var1;
      var1 = thisx.null;
      thisx.null = thisx.long;
      thisx.long = var1;
      var1 = thisx.int;
      thisx.int = thisx.false;
      thisx.false = var1;
   }

   public final void new(Matrix3d arg0) {
      if (thisx != arg0) {
         thisx.case = arg0.case;
         thisx.short = arg0.new;
         thisx.long = arg0.null;
         thisx.new = arg0.short;
         thisx.const = arg0.const;
         thisx.false = arg0.int;
         thisx.null = arg0.long;
         thisx.int = arg0.false;
         thisx.final = arg0.final;
      } else {
         thisx.do();
      }

   }

   public final void null(Quat4d arg0) {
      thisx.case = 1.0D - 2.0D * arg0.int * arg0.int - 2.0D * arg0.final * arg0.final;
      thisx.new = 2.0D * (arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.null = 2.0D * (arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.short = 2.0D * (arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.const = 1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.final * arg0.final;
      thisx.int = 2.0D * (arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.long = 2.0D * (arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.false = 2.0D * (arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.final = 1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.int * arg0.int;
   }

   public final void null(AxisAngle4d arg0) {
      double var2 = Math.sqrt(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int);
      if (var2 < 1.110223024E-16D) {
         thisx.case = 1.0D;
         thisx.short = 0.0D;
         thisx.long = 0.0D;
         thisx.new = 0.0D;
         thisx.const = 1.0D;
         thisx.false = 0.0D;
         thisx.null = 0.0D;
         thisx.int = 0.0D;
         thisx.final = 1.0D;
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
         thisx.case = var14 * var4 * var4 + var12;
         thisx.short = var14 * var18 - var10 * var8;
         thisx.long = var14 * var16 + var10 * var6;
         thisx.new = var14 * var18 + var10 * var8;
         thisx.const = var14 * var6 * var6 + var12;
         thisx.false = var14 * var20 - var10 * var4;
         thisx.null = var14 * var16 - var10 * var6;
         thisx.int = var14 * var20 + var10 * var4;
         thisx.final = var14 * var8 * var8 + var12;
      }

   }

   public final void null(Quat4f arg0) {
      thisx.case = 1.0D - 2.0D * (double)arg0.int * (double)arg0.int - 2.0D * (double)arg0.final * (double)arg0.final;
      thisx.new = 2.0D * (double)(arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.null = 2.0D * (double)(arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.short = 2.0D * (double)(arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.const = 1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.final * (double)arg0.final;
      thisx.int = 2.0D * (double)(arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.long = 2.0D * (double)(arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.false = 2.0D * (double)(arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.final = 1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.int * (double)arg0.int;
   }

   public final void null(AxisAngle4f arg0) {
      double var2 = Math.sqrt((double)(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int));
      if (var2 < 1.110223024E-16D) {
         thisx.case = 1.0D;
         thisx.short = 0.0D;
         thisx.long = 0.0D;
         thisx.new = 0.0D;
         thisx.const = 1.0D;
         thisx.false = 0.0D;
         thisx.null = 0.0D;
         thisx.int = 0.0D;
         thisx.final = 1.0D;
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
         thisx.case = var14 * var4 * var4 + var12;
         thisx.short = var14 * var18 - var10 * var8;
         thisx.long = var14 * var16 + var10 * var6;
         thisx.new = var14 * var18 + var10 * var8;
         thisx.const = var14 * var6 * var6 + var12;
         thisx.false = var14 * var20 - var10 * var4;
         thisx.null = var14 * var16 - var10 * var6;
         thisx.int = var14 * var20 + var10 * var4;
         thisx.final = var14 * var8 * var8 + var12;
      }

   }

   public final void null(Matrix3f arg0) {
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

   public final void this(Matrix3d arg0) {
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

   public final void long(double[] arg0) {
      thisx.case = arg0[0];
      thisx.short = arg0[1];
      thisx.long = arg0[2];
      thisx.new = arg0[3];
      thisx.const = arg0[4];
      thisx.false = arg0[5];
      thisx.null = arg0[6];
      thisx.int = arg0[7];
      thisx.final = arg0[8];
   }

   public final void short(Matrix3d arg0) {
      thisx.do(arg0);
   }

   public final void true() {
      thisx.do(thisx);
   }

   private final void do(Matrix3d arg0) {
      double[] var2 = new double[9];
      int[] var3 = new int[3];
      double[] var5 = new double[]{arg0.case, arg0.short, arg0.long, arg0.new, arg0.const, arg0.false, arg0.null, arg0.int, arg0.final};
      if (!null(var5, var3)) {
         throw new SingularMatrixException(VecMathI18N.null(NetworkErrorException.null("\u000ek7x*rpnr8")));
      } else {
         for(int var4 = 0; var4 < 9; ++var4) {
            var2[var4] = 0.0D;
         }

         var2[0] = 1.0D;
         var2[4] = 1.0D;
         var2[8] = 1.0D;
         null(var5, var3, var2);
         thisx.case = var2[0];
         thisx.short = var2[1];
         thisx.long = var2[2];
         thisx.new = var2[3];
         thisx.const = var2[4];
         thisx.false = var2[5];
         thisx.null = var2[6];
         thisx.int = var2[7];
         thisx.final = var2[8];
      }
   }

   public static boolean null(double[] arg0, int[] arg1) {
      double[] var2 = new double[3];
      int var5 = 0;
      int var6 = 0;

      int var3;
      double var7;
      for(var3 = 3; var3-- != 0; var2[var6++] = 1.0D / var7) {
         var7 = 0.0D;
         int var4 = 3;

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

      for(var3 = 0; var3 < 3; ++var3) {
         int var8;
         int var10;
         double var11;
         int var18;
         int var19;
         for(var5 = 0; var5 < var3; ++var5) {
            var8 = var17 + 3 * var5 + var3;
            var11 = arg0[var8];
            var18 = var5;
            var19 = var17 + 3 * var5;

            for(var10 = var17 + var3; var18-- != 0; var10 += 3) {
               var11 -= arg0[var19] * arg0[var10];
               ++var19;
            }

            arg0[var8] = var11;
         }

         double var13 = 0.0D;
         var6 = -1;

         double var15;
         for(var5 = var3; var5 < 3; ++var5) {
            var8 = var17 + 3 * var5 + var3;
            var11 = arg0[var8];
            var18 = var3;
            var19 = var17 + 3 * var5;

            for(var10 = var17 + var3; var18-- != 0; var10 += 3) {
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
            throw new RuntimeException(VecMathI18N.null(EnchantmentArrowInfinite.null("^*g9z3 /\"x")));
         }

         if (var3 != var6) {
            var18 = 3;
            var19 = var17 + 3 * var6;

            for(var10 = var17 + 3 * var3; var18-- != 0; arg0[var10++] = var15) {
               var15 = arg0[var19];
               arg0[var19++] = arg0[var10];
            }

            var2[var6] = var2[var3];
         }

         arg1[var3] = var6;
         if (arg0[var17 + 3 * var3 + var3] == 0.0D) {
            return false;
         }

         if (var3 != 2) {
            var15 = 1.0D / arg0[var17 + 3 * var3 + var3];
            var8 = var17 + 3 * (var3 + 1) + var3;

            for(var5 = 2 - var3; var5-- != 0; var8 += 3) {
               arg0[var8] *= var15;
            }
         }
      }

      return true;
   }

   public static void null(double[] arg0, int[] arg1, double[] arg2) {
      byte var8 = 0;

      for(int var7 = 0; var7 < 3; ++var7) {
         int var9 = var7;
         int var4 = -1;

         int var10;
         for(int var3 = 0; var3 < 3; ++var3) {
            int var5 = arg1[var8 + var3];
            double var11 = arg2[var9 + 3 * var5];
            arg2[var9 + 3 * var5] = arg2[var9 + 3 * var3];
            if (var4 >= 0) {
               var10 = var3 * 3;

               for(int var6 = var4; var6 <= var3 - 1; ++var6) {
                  var11 -= arg0[var10 + var6] * arg2[var9 + 3 * var6];
               }
            } else if (var11 != 0.0D) {
               var4 = var3;
            }

            arg2[var9 + 3 * var3] = var11;
         }

         byte var13 = 6;
         arg2[var9 + 6] /= arg0[var13 + 2];
         var10 = var13 - 3;
         arg2[var9 + 3] = (arg2[var9 + 3] - arg0[var10 + 2] * arg2[var9 + 6]) / arg0[var10 + 1];
         var10 -= 3;
         arg2[var9 + 0] = (arg2[var9 + 0] - arg0[var10 + 1] * arg2[var9 + 3] - arg0[var10 + 2] * arg2[var9 + 6]) / arg0[var10 + 0];
      }

   }

   public final double else() {
      double var1 = thisx.case * (thisx.const * thisx.final - thisx.false * thisx.int) + thisx.short * (thisx.false * thisx.null - thisx.new * thisx.final) + thisx.long * (thisx.new * thisx.int - thisx.const * thisx.null);
      return var1;
   }

   public final void for(double arg0) {
      thisx.case = arg0;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = 0.0D;
      thisx.const = arg0;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = arg0;
   }

   public final void false(double arg0) {
      double var3 = Math.sin(arg0);
      double var5 = Math.cos(arg0);
      thisx.case = 1.0D;
      thisx.short = 0.0D;
      thisx.long = 0.0D;
      thisx.new = 0.0D;
      thisx.const = var5;
      thisx.false = -var3;
      thisx.null = 0.0D;
      thisx.int = var3;
      thisx.final = var5;
   }

   public final void float(double arg0) {
      double var3 = Math.sin(arg0);
      double var5 = Math.cos(arg0);
      thisx.case = var5;
      thisx.short = 0.0D;
      thisx.long = var3;
      thisx.new = 0.0D;
      thisx.const = 1.0D;
      thisx.false = 0.0D;
      thisx.null = -var3;
      thisx.int = 0.0D;
      thisx.final = var5;
   }

   public final void char(double arg0) {
      double var3 = Math.sin(arg0);
      double var5 = Math.cos(arg0);
      thisx.case = var5;
      thisx.short = -var3;
      thisx.long = 0.0D;
      thisx.new = var3;
      thisx.const = var5;
      thisx.false = 0.0D;
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 1.0D;
   }

   public final void else(double arg0) {
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

   public final void null(double arg0, Matrix3d arg1) {
      thisx.case = arg0 * arg1.case;
      thisx.short = arg0 * arg1.short;
      thisx.long = arg0 * arg1.long;
      thisx.new = arg0 * arg1.new;
      thisx.const = arg0 * arg1.const;
      thisx.false = arg0 * arg1.false;
      thisx.null = arg0 * arg1.null;
      thisx.int = arg0 * arg1.int;
      thisx.final = arg0 * arg1.final;
   }

   public final void true(Matrix3d arg0) {
      double var2 = thisx.case * arg0.case + thisx.short * arg0.new + thisx.long * arg0.null;
      double var4 = thisx.case * arg0.short + thisx.short * arg0.const + thisx.long * arg0.int;
      double var6 = thisx.case * arg0.long + thisx.short * arg0.false + thisx.long * arg0.final;
      double var8 = thisx.new * arg0.case + thisx.const * arg0.new + thisx.false * arg0.null;
      double var10 = thisx.new * arg0.short + thisx.const * arg0.const + thisx.false * arg0.int;
      double var12 = thisx.new * arg0.long + thisx.const * arg0.false + thisx.false * arg0.final;
      double var14 = thisx.null * arg0.case + thisx.int * arg0.new + thisx.final * arg0.null;
      double var16 = thisx.null * arg0.short + thisx.int * arg0.const + thisx.final * arg0.int;
      double var18 = thisx.null * arg0.long + thisx.int * arg0.false + thisx.final * arg0.final;
      thisx.case = var2;
      thisx.short = var4;
      thisx.long = var6;
      thisx.new = var8;
      thisx.const = var10;
      thisx.false = var12;
      thisx.null = var14;
      thisx.int = var16;
      thisx.final = var18;
   }

   public final void true(Matrix3d arg0, Matrix3d arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.case = arg0.case * arg1.case + arg0.short * arg1.new + arg0.long * arg1.null;
         thisx.short = arg0.case * arg1.short + arg0.short * arg1.const + arg0.long * arg1.int;
         thisx.long = arg0.case * arg1.long + arg0.short * arg1.false + arg0.long * arg1.final;
         thisx.new = arg0.new * arg1.case + arg0.const * arg1.new + arg0.false * arg1.null;
         thisx.const = arg0.new * arg1.short + arg0.const * arg1.const + arg0.false * arg1.int;
         thisx.false = arg0.new * arg1.long + arg0.const * arg1.false + arg0.false * arg1.final;
         thisx.null = arg0.null * arg1.case + arg0.int * arg1.new + arg0.final * arg1.null;
         thisx.int = arg0.null * arg1.short + arg0.int * arg1.const + arg0.final * arg1.int;
         thisx.final = arg0.null * arg1.long + arg0.int * arg1.false + arg0.final * arg1.final;
      } else {
         double var3 = arg0.case * arg1.case + arg0.short * arg1.new + arg0.long * arg1.null;
         double var5 = arg0.case * arg1.short + arg0.short * arg1.const + arg0.long * arg1.int;
         double var7 = arg0.case * arg1.long + arg0.short * arg1.false + arg0.long * arg1.final;
         double var9 = arg0.new * arg1.case + arg0.const * arg1.new + arg0.false * arg1.null;
         double var11 = arg0.new * arg1.short + arg0.const * arg1.const + arg0.false * arg1.int;
         double var13 = arg0.new * arg1.long + arg0.const * arg1.false + arg0.false * arg1.final;
         double var15 = arg0.null * arg1.case + arg0.int * arg1.new + arg0.final * arg1.null;
         double var17 = arg0.null * arg1.short + arg0.int * arg1.const + arg0.final * arg1.int;
         double var19 = arg0.null * arg1.long + arg0.int * arg1.false + arg0.final * arg1.final;
         thisx.case = var3;
         thisx.short = var5;
         thisx.long = var7;
         thisx.new = var9;
         thisx.const = var11;
         thisx.false = var13;
         thisx.null = var15;
         thisx.int = var17;
         thisx.final = var19;
      }

   }

   public final void const(Matrix3d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      var2[0] = thisx.case * arg0.case + thisx.short * arg0.new + thisx.long * arg0.null;
      var2[1] = thisx.case * arg0.short + thisx.short * arg0.const + thisx.long * arg0.int;
      var2[2] = thisx.case * arg0.long + thisx.short * arg0.false + thisx.long * arg0.final;
      var2[3] = thisx.new * arg0.case + thisx.const * arg0.new + thisx.false * arg0.null;
      var2[4] = thisx.new * arg0.short + thisx.const * arg0.const + thisx.false * arg0.int;
      var2[5] = thisx.new * arg0.long + thisx.const * arg0.false + thisx.false * arg0.final;
      var2[6] = thisx.null * arg0.case + thisx.int * arg0.new + thisx.final * arg0.null;
      var2[7] = thisx.null * arg0.short + thisx.int * arg0.const + thisx.final * arg0.int;
      var2[8] = thisx.null * arg0.long + thisx.int * arg0.false + thisx.final * arg0.final;
      class(var2, var4, var3);
      thisx.case = var3[0];
      thisx.short = var3[1];
      thisx.long = var3[2];
      thisx.new = var3[3];
      thisx.const = var3[4];
      thisx.false = var3[5];
      thisx.null = var3[6];
      thisx.int = var3[7];
      thisx.final = var3[8];
   }

   public final void const(Matrix3d arg0, Matrix3d arg1) {
      double[] var3 = new double[9];
      double[] var4 = new double[9];
      double[] var5 = new double[3];
      var3[0] = arg0.case * arg1.case + arg0.short * arg1.new + arg0.long * arg1.null;
      var3[1] = arg0.case * arg1.short + arg0.short * arg1.const + arg0.long * arg1.int;
      var3[2] = arg0.case * arg1.long + arg0.short * arg1.false + arg0.long * arg1.final;
      var3[3] = arg0.new * arg1.case + arg0.const * arg1.new + arg0.false * arg1.null;
      var3[4] = arg0.new * arg1.short + arg0.const * arg1.const + arg0.false * arg1.int;
      var3[5] = arg0.new * arg1.long + arg0.const * arg1.false + arg0.false * arg1.final;
      var3[6] = arg0.null * arg1.case + arg0.int * arg1.new + arg0.final * arg1.null;
      var3[7] = arg0.null * arg1.short + arg0.int * arg1.const + arg0.final * arg1.int;
      var3[8] = arg0.null * arg1.long + arg0.int * arg1.false + arg0.final * arg1.final;
      class(var3, var5, var4);
      thisx.case = var4[0];
      thisx.short = var4[1];
      thisx.long = var4[2];
      thisx.new = var4[3];
      thisx.const = var4[4];
      thisx.false = var4[5];
      thisx.null = var4[6];
      thisx.int = var4[7];
      thisx.final = var4[8];
   }

   public final void long(Matrix3d arg0, Matrix3d arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.case = arg0.case * arg1.case + arg0.new * arg1.short + arg0.null * arg1.long;
         thisx.short = arg0.case * arg1.new + arg0.new * arg1.const + arg0.null * arg1.false;
         thisx.long = arg0.case * arg1.null + arg0.new * arg1.int + arg0.null * arg1.final;
         thisx.new = arg0.short * arg1.case + arg0.const * arg1.short + arg0.int * arg1.long;
         thisx.const = arg0.short * arg1.new + arg0.const * arg1.const + arg0.int * arg1.false;
         thisx.false = arg0.short * arg1.null + arg0.const * arg1.int + arg0.int * arg1.final;
         thisx.null = arg0.long * arg1.case + arg0.false * arg1.short + arg0.final * arg1.long;
         thisx.int = arg0.long * arg1.new + arg0.false * arg1.const + arg0.final * arg1.false;
         thisx.final = arg0.long * arg1.null + arg0.false * arg1.int + arg0.final * arg1.final;
      } else {
         double var3 = arg0.case * arg1.case + arg0.new * arg1.short + arg0.null * arg1.long;
         double var5 = arg0.case * arg1.new + arg0.new * arg1.const + arg0.null * arg1.false;
         double var7 = arg0.case * arg1.null + arg0.new * arg1.int + arg0.null * arg1.final;
         double var9 = arg0.short * arg1.case + arg0.const * arg1.short + arg0.int * arg1.long;
         double var11 = arg0.short * arg1.new + arg0.const * arg1.const + arg0.int * arg1.false;
         double var13 = arg0.short * arg1.null + arg0.const * arg1.int + arg0.int * arg1.final;
         double var15 = arg0.long * arg1.case + arg0.false * arg1.short + arg0.final * arg1.long;
         double var17 = arg0.long * arg1.new + arg0.false * arg1.const + arg0.final * arg1.false;
         double var19 = arg0.long * arg1.null + arg0.false * arg1.int + arg0.final * arg1.final;
         thisx.case = var3;
         thisx.short = var5;
         thisx.long = var7;
         thisx.new = var9;
         thisx.const = var11;
         thisx.false = var13;
         thisx.null = var15;
         thisx.int = var17;
         thisx.final = var19;
      }

   }

   public final void class(Matrix3d arg0, Matrix3d arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.case = arg0.case * arg1.case + arg0.short * arg1.short + arg0.long * arg1.long;
         thisx.short = arg0.case * arg1.new + arg0.short * arg1.const + arg0.long * arg1.false;
         thisx.long = arg0.case * arg1.null + arg0.short * arg1.int + arg0.long * arg1.final;
         thisx.new = arg0.new * arg1.case + arg0.const * arg1.short + arg0.false * arg1.long;
         thisx.const = arg0.new * arg1.new + arg0.const * arg1.const + arg0.false * arg1.false;
         thisx.false = arg0.new * arg1.null + arg0.const * arg1.int + arg0.false * arg1.final;
         thisx.null = arg0.null * arg1.case + arg0.int * arg1.short + arg0.final * arg1.long;
         thisx.int = arg0.null * arg1.new + arg0.int * arg1.const + arg0.final * arg1.false;
         thisx.final = arg0.null * arg1.null + arg0.int * arg1.int + arg0.final * arg1.final;
      } else {
         double var3 = arg0.case * arg1.case + arg0.short * arg1.short + arg0.long * arg1.long;
         double var5 = arg0.case * arg1.new + arg0.short * arg1.const + arg0.long * arg1.false;
         double var7 = arg0.case * arg1.null + arg0.short * arg1.int + arg0.long * arg1.final;
         double var9 = arg0.new * arg1.case + arg0.const * arg1.short + arg0.false * arg1.long;
         double var11 = arg0.new * arg1.new + arg0.const * arg1.const + arg0.false * arg1.false;
         double var13 = arg0.new * arg1.null + arg0.const * arg1.int + arg0.false * arg1.final;
         double var15 = arg0.null * arg1.case + arg0.int * arg1.short + arg0.final * arg1.long;
         double var17 = arg0.null * arg1.new + arg0.int * arg1.const + arg0.final * arg1.false;
         double var19 = arg0.null * arg1.null + arg0.int * arg1.int + arg0.final * arg1.final;
         thisx.case = var3;
         thisx.short = var5;
         thisx.long = var7;
         thisx.new = var9;
         thisx.const = var11;
         thisx.false = var13;
         thisx.null = var15;
         thisx.int = var17;
         thisx.final = var19;
      }

   }

   public final void null(Matrix3d arg0, Matrix3d arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.case = arg0.case * arg1.case + arg0.new * arg1.new + arg0.null * arg1.null;
         thisx.short = arg0.case * arg1.short + arg0.new * arg1.const + arg0.null * arg1.int;
         thisx.long = arg0.case * arg1.long + arg0.new * arg1.false + arg0.null * arg1.final;
         thisx.new = arg0.short * arg1.case + arg0.const * arg1.new + arg0.int * arg1.null;
         thisx.const = arg0.short * arg1.short + arg0.const * arg1.const + arg0.int * arg1.int;
         thisx.false = arg0.short * arg1.long + arg0.const * arg1.false + arg0.int * arg1.final;
         thisx.null = arg0.long * arg1.case + arg0.false * arg1.new + arg0.final * arg1.null;
         thisx.int = arg0.long * arg1.short + arg0.false * arg1.const + arg0.final * arg1.int;
         thisx.final = arg0.long * arg1.long + arg0.false * arg1.false + arg0.final * arg1.final;
      } else {
         double var3 = arg0.case * arg1.case + arg0.new * arg1.new + arg0.null * arg1.null;
         double var5 = arg0.case * arg1.short + arg0.new * arg1.const + arg0.null * arg1.int;
         double var7 = arg0.case * arg1.long + arg0.new * arg1.false + arg0.null * arg1.final;
         double var9 = arg0.short * arg1.case + arg0.const * arg1.new + arg0.int * arg1.null;
         double var11 = arg0.short * arg1.short + arg0.const * arg1.const + arg0.int * arg1.int;
         double var13 = arg0.short * arg1.long + arg0.const * arg1.false + arg0.int * arg1.final;
         double var15 = arg0.long * arg1.case + arg0.false * arg1.new + arg0.final * arg1.null;
         double var17 = arg0.long * arg1.short + arg0.false * arg1.const + arg0.final * arg1.int;
         double var19 = arg0.long * arg1.long + arg0.false * arg1.false + arg0.final * arg1.final;
         thisx.case = var3;
         thisx.short = var5;
         thisx.long = var7;
         thisx.new = var9;
         thisx.const = var11;
         thisx.false = var13;
         thisx.null = var15;
         thisx.int = var17;
         thisx.final = var19;
      }

   }

   public final void const() {
      double[] var1 = new double[9];
      double[] var2 = new double[3];
      thisx.class(var2, var1);
      thisx.case = var1[0];
      thisx.short = var1[1];
      thisx.long = var1[2];
      thisx.new = var1[3];
      thisx.const = var1[4];
      thisx.false = var1[5];
      thisx.null = var1[6];
      thisx.int = var1[7];
      thisx.final = var1[8];
   }

   public final void long(Matrix3d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      var2[0] = arg0.case;
      var2[1] = arg0.short;
      var2[2] = arg0.long;
      var2[3] = arg0.new;
      var2[4] = arg0.const;
      var2[5] = arg0.false;
      var2[6] = arg0.null;
      var2[7] = arg0.int;
      var2[8] = arg0.final;
      class(var2, var4, var3);
      thisx.case = var3[0];
      thisx.short = var3[1];
      thisx.long = var3[2];
      thisx.new = var3[3];
      thisx.const = var3[4];
      thisx.false = var3[5];
      thisx.null = var3[6];
      thisx.int = var3[7];
      thisx.final = var3[8];
   }

   public final void long() {
      double var1 = 1.0D / Math.sqrt(thisx.case * thisx.case + thisx.new * thisx.new + thisx.null * thisx.null);
      thisx.case *= var1;
      thisx.new *= var1;
      thisx.null *= var1;
      var1 = 1.0D / Math.sqrt(thisx.short * thisx.short + thisx.const * thisx.const + thisx.int * thisx.int);
      thisx.short *= var1;
      thisx.const *= var1;
      thisx.int *= var1;
      thisx.long = thisx.new * thisx.int - thisx.const * thisx.null;
      thisx.false = thisx.short * thisx.null - thisx.case * thisx.int;
      thisx.final = thisx.case * thisx.const - thisx.short * thisx.new;
   }

   public final void class(Matrix3d arg0) {
      double var2 = 1.0D / Math.sqrt(arg0.case * arg0.case + arg0.new * arg0.new + arg0.null * arg0.null);
      thisx.case = arg0.case * var2;
      thisx.new = arg0.new * var2;
      thisx.null = arg0.null * var2;
      var2 = 1.0D / Math.sqrt(arg0.short * arg0.short + arg0.const * arg0.const + arg0.int * arg0.int);
      thisx.short = arg0.short * var2;
      thisx.const = arg0.const * var2;
      thisx.int = arg0.int * var2;
      thisx.long = thisx.new * thisx.int - thisx.const * thisx.null;
      thisx.false = thisx.short * thisx.null - thisx.case * thisx.int;
      thisx.final = thisx.case * thisx.const - thisx.short * thisx.new;
   }

   public boolean null(Matrix3d arg0) {
      try {
         return thisx.case == arg0.case && thisx.short == arg0.short && thisx.long == arg0.long && thisx.new == arg0.new && thisx.const == arg0.const && thisx.false == arg0.false && thisx.null == arg0.null && thisx.int == arg0.int && thisx.final == arg0.final;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Matrix3d var2 = (Matrix3d)arg0;
         return thisx.case == var2.case && thisx.short == var2.short && thisx.long == var2.long && thisx.new == var2.new && thisx.const == var2.const && thisx.false == var2.false && thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final;
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   public boolean null(Matrix3d arg0, double arg1) {
      double var4 = thisx.case - arg0.case;
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

   public int hashCode() {
      long var1 = 1L;
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

   public final void class() {
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

   public final void null(Matrix3d arg0) {
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

   public final void null(Tuple3d arg0) {
      double var2 = thisx.case * arg0.int + thisx.short * arg0.final + thisx.long * arg0.this;
      double var4 = thisx.new * arg0.int + thisx.const * arg0.final + thisx.false * arg0.this;
      double var6 = thisx.null * arg0.int + thisx.int * arg0.final + thisx.final * arg0.this;
      arg0.null(var2, var4, var6);
   }

   public final EntityPropertyManager null(EntityPropertyManager arg0) {
      double var2 = thisx.case * arg0.int + thisx.short * arg0.final + thisx.long * arg0.this;
      double var4 = thisx.new * arg0.int + thisx.const * arg0.final + thisx.false * arg0.this;
      double var6 = thisx.null * arg0.int + thisx.int * arg0.final + thisx.final * arg0.this;
      return new EntityPropertyManager(var2, var4, var6);
   }

   public final void null(Tuple3d arg0, Tuple3d arg1) {
      double var3 = thisx.case * arg0.int + thisx.short * arg0.final + thisx.long * arg0.this;
      double var5 = thisx.new * arg0.int + thisx.const * arg0.final + thisx.false * arg0.this;
      arg1.this = thisx.null * arg0.int + thisx.int * arg0.final + thisx.final * arg0.this;
      arg1.int = var3;
      arg1.final = var5;
   }

   public final void class(double[] arg0, double[] arg1) {
      double[] var3 = new double[]{thisx.case, thisx.short, thisx.long, thisx.new, thisx.const, thisx.false, thisx.null, thisx.int, thisx.final};
      class(var3, arg0, arg1);
   }

   public static void class(double[] arg0, double[] arg1, double[] arg2) {
      double[] var9 = new double[9];
      double[] var10 = new double[9];
      double[] var11 = new double[9];
      double[] var12 = new double[9];
      double[] var15 = new double[9];
      double[] var16 = new double[3];
      double[] var17 = new double[3];
      int var19 = 0;

      int var3;
      for(var3 = 0; var3 < 9; ++var3) {
         var15[var3] = arg0[var3];
      }

      double var5;
      if (arg0[3] * arg0[3] < 1.110223024E-16D) {
         var9[0] = 1.0D;
         var9[1] = 0.0D;
         var9[2] = 0.0D;
         var9[3] = 0.0D;
         var9[4] = 1.0D;
         var9[5] = 0.0D;
         var9[6] = 0.0D;
         var9[7] = 0.0D;
         var9[8] = 1.0D;
      } else if (arg0[0] * arg0[0] < 1.110223024E-16D) {
         var11[0] = arg0[0];
         var11[1] = arg0[1];
         var11[2] = arg0[2];
         arg0[0] = arg0[3];
         arg0[1] = arg0[4];
         arg0[2] = arg0[5];
         arg0[3] = -var11[0];
         arg0[4] = -var11[1];
         arg0[5] = -var11[2];
         var9[0] = 0.0D;
         var9[1] = 1.0D;
         var9[2] = 0.0D;
         var9[3] = -1.0D;
         var9[4] = 0.0D;
         var9[5] = 0.0D;
         var9[6] = 0.0D;
         var9[7] = 0.0D;
         var9[8] = 1.0D;
      } else {
         var5 = 1.0D / Math.sqrt(arg0[0] * arg0[0] + arg0[3] * arg0[3]);
         double var24 = arg0[0] * var5;
         double var32 = arg0[3] * var5;
         var11[0] = var24 * arg0[0] + var32 * arg0[3];
         var11[1] = var24 * arg0[1] + var32 * arg0[4];
         var11[2] = var24 * arg0[2] + var32 * arg0[5];
         arg0[3] = -var32 * arg0[0] + var24 * arg0[3];
         arg0[4] = -var32 * arg0[1] + var24 * arg0[4];
         arg0[5] = -var32 * arg0[2] + var24 * arg0[5];
         arg0[0] = var11[0];
         arg0[1] = var11[1];
         arg0[2] = var11[2];
         var9[0] = var24;
         var9[1] = var32;
         var9[2] = 0.0D;
         var9[3] = -var32;
         var9[4] = var24;
         var9[5] = 0.0D;
         var9[6] = 0.0D;
         var9[7] = 0.0D;
         var9[8] = 1.0D;
      }

      if (!(arg0[6] * arg0[6] < 1.110223024E-16D)) {
         if (arg0[0] * arg0[0] < 1.110223024E-16D) {
            var11[0] = arg0[0];
            var11[1] = arg0[1];
            var11[2] = arg0[2];
            arg0[0] = arg0[6];
            arg0[1] = arg0[7];
            arg0[2] = arg0[8];
            arg0[6] = -var11[0];
            arg0[7] = -var11[1];
            arg0[8] = -var11[2];
            var11[0] = var9[0];
            var11[1] = var9[1];
            var11[2] = var9[2];
            var9[0] = var9[6];
            var9[1] = var9[7];
            var9[2] = var9[8];
            var9[6] = -var11[0];
            var9[7] = -var11[1];
            var9[8] = -var11[2];
         } else {
            var5 = 1.0D / Math.sqrt(arg0[0] * arg0[0] + arg0[6] * arg0[6]);
            double var26 = arg0[0] * var5;
            double var34 = arg0[6] * var5;
            var11[0] = var26 * arg0[0] + var34 * arg0[6];
            var11[1] = var26 * arg0[1] + var34 * arg0[7];
            var11[2] = var26 * arg0[2] + var34 * arg0[8];
            arg0[6] = -var34 * arg0[0] + var26 * arg0[6];
            arg0[7] = -var34 * arg0[1] + var26 * arg0[7];
            arg0[8] = -var34 * arg0[2] + var26 * arg0[8];
            arg0[0] = var11[0];
            arg0[1] = var11[1];
            arg0[2] = var11[2];
            var11[0] = var26 * var9[0];
            var11[1] = var26 * var9[1];
            var9[2] = var34;
            var11[6] = -var9[0] * var34;
            var11[7] = -var9[1] * var34;
            var9[8] = var26;
            var9[0] = var11[0];
            var9[1] = var11[1];
            var9[6] = var11[6];
            var9[7] = var11[7];
         }
      }

      if (arg0[2] * arg0[2] < 1.110223024E-16D) {
         var10[0] = 1.0D;
         var10[1] = 0.0D;
         var10[2] = 0.0D;
         var10[3] = 0.0D;
         var10[4] = 1.0D;
         var10[5] = 0.0D;
         var10[6] = 0.0D;
         var10[7] = 0.0D;
         var10[8] = 1.0D;
      } else if (arg0[1] * arg0[1] < 1.110223024E-16D) {
         var11[2] = arg0[2];
         var11[5] = arg0[5];
         var11[8] = arg0[8];
         arg0[2] = -arg0[1];
         arg0[5] = -arg0[4];
         arg0[8] = -arg0[7];
         arg0[1] = var11[2];
         arg0[4] = var11[5];
         arg0[7] = var11[8];
         var10[0] = 1.0D;
         var10[1] = 0.0D;
         var10[2] = 0.0D;
         var10[3] = 0.0D;
         var10[4] = 0.0D;
         var10[5] = -1.0D;
         var10[6] = 0.0D;
         var10[7] = 1.0D;
         var10[8] = 0.0D;
      } else {
         var5 = 1.0D / Math.sqrt(arg0[1] * arg0[1] + arg0[2] * arg0[2]);
         double var28 = arg0[1] * var5;
         double var36 = arg0[2] * var5;
         var11[1] = var28 * arg0[1] + var36 * arg0[2];
         arg0[2] = -var36 * arg0[1] + var28 * arg0[2];
         arg0[1] = var11[1];
         var11[4] = var28 * arg0[4] + var36 * arg0[5];
         arg0[5] = -var36 * arg0[4] + var28 * arg0[5];
         arg0[4] = var11[4];
         var11[7] = var28 * arg0[7] + var36 * arg0[8];
         arg0[8] = -var36 * arg0[7] + var28 * arg0[8];
         arg0[7] = var11[7];
         var10[0] = 1.0D;
         var10[1] = 0.0D;
         var10[2] = 0.0D;
         var10[3] = 0.0D;
         var10[4] = var28;
         var10[5] = -var36;
         var10[6] = 0.0D;
         var10[7] = var36;
         var10[8] = var28;
      }

      if (!(arg0[7] * arg0[7] < 1.110223024E-16D)) {
         if (arg0[4] * arg0[4] < 1.110223024E-16D) {
            var11[3] = arg0[3];
            var11[4] = arg0[4];
            var11[5] = arg0[5];
            arg0[3] = arg0[6];
            arg0[4] = arg0[7];
            arg0[5] = arg0[8];
            arg0[6] = -var11[3];
            arg0[7] = -var11[4];
            arg0[8] = -var11[5];
            var11[3] = var9[3];
            var11[4] = var9[4];
            var11[5] = var9[5];
            var9[3] = var9[6];
            var9[4] = var9[7];
            var9[5] = var9[8];
            var9[6] = -var11[3];
            var9[7] = -var11[4];
            var9[8] = -var11[5];
         } else {
            var5 = 1.0D / Math.sqrt(arg0[4] * arg0[4] + arg0[7] * arg0[7]);
            double var30 = arg0[4] * var5;
            double var38 = arg0[7] * var5;
            var11[3] = var30 * arg0[3] + var38 * arg0[6];
            arg0[6] = -var38 * arg0[3] + var30 * arg0[6];
            arg0[3] = var11[3];
            var11[4] = var30 * arg0[4] + var38 * arg0[7];
            arg0[7] = -var38 * arg0[4] + var30 * arg0[7];
            arg0[4] = var11[4];
            var11[5] = var30 * arg0[5] + var38 * arg0[8];
            arg0[8] = -var38 * arg0[5] + var30 * arg0[8];
            arg0[5] = var11[5];
            var11[3] = var30 * var9[3] + var38 * var9[6];
            var9[6] = -var38 * var9[3] + var30 * var9[6];
            var9[3] = var11[3];
            var11[4] = var30 * var9[4] + var38 * var9[7];
            var9[7] = -var38 * var9[4] + var30 * var9[7];
            var9[4] = var11[4];
            var11[5] = var30 * var9[5] + var38 * var9[8];
            var9[8] = -var38 * var9[5] + var30 * var9[8];
            var9[5] = var11[5];
         }
      }

      var12[0] = arg0[0];
      var12[1] = arg0[4];
      var12[2] = arg0[8];
      var16[0] = arg0[1];
      var16[1] = arg0[5];
      if (!(var16[0] * var16[0] < 1.110223024E-16D) || !(var16[1] * var16[1] < 1.110223024E-16D)) {
         null(var12, var16, var9, var10);
      }

      var17[0] = var12[0];
      var17[1] = var12[1];
      var17[2] = var12[2];
      if (null(Math.abs(var17[0]), 1.0D) && null(Math.abs(var17[1]), 1.0D) && null(Math.abs(var17[2]), 1.0D)) {
         for(var3 = 0; var3 < 3; ++var3) {
            if (var17[var3] < 0.0D) {
               ++var19;
            }
         }

         if (var19 == 0 || var19 == 2) {
            arg1[0] = arg1[1] = arg1[2] = 1.0D;

            for(var3 = 0; var3 < 9; ++var3) {
               arg2[var3] = var15[var3];
            }

            return;
         }
      }

      null(var9, var11);
      null(var10, var12);
      null(arg0, var11, var12, var17, arg2, arg1);
   }

   public static void null(double[] arg0, double[] arg1, double[] arg2, double[] arg3, double[] arg4, double[] arg5) {
      int[] var6 = new int[3];
      int[] var7 = new int[3];
      double[] var13 = new double[3];
      double[] var14 = new double[9];
      if (arg3[0] < 0.0D) {
         arg3[0] = -arg3[0];
         arg2[0] = -arg2[0];
         arg2[1] = -arg2[1];
         arg2[2] = -arg2[2];
      }

      if (arg3[1] < 0.0D) {
         arg3[1] = -arg3[1];
         arg2[3] = -arg2[3];
         arg2[4] = -arg2[4];
         arg2[5] = -arg2[5];
      }

      if (arg3[2] < 0.0D) {
         arg3[2] = -arg3[2];
         arg2[6] = -arg2[6];
         arg2[7] = -arg2[7];
         arg2[8] = -arg2[8];
      }

      null(arg1, arg2, var14);
      if (null(Math.abs(arg3[0]), Math.abs(arg3[1])) && null(Math.abs(arg3[1]), Math.abs(arg3[2]))) {
         int var12;
         for(var12 = 0; var12 < 9; ++var12) {
            arg4[var12] = var14[var12];
         }

         for(var12 = 0; var12 < 3; ++var12) {
            arg5[var12] = arg3[var12];
         }
      } else {
         if (arg3[0] > arg3[1]) {
            if (arg3[0] > arg3[2]) {
               if (arg3[2] > arg3[1]) {
                  var6[0] = 0;
                  var6[1] = 2;
                  var6[2] = 1;
               } else {
                  var6[0] = 0;
                  var6[1] = 1;
                  var6[2] = 2;
               }
            } else {
               var6[0] = 2;
               var6[1] = 0;
               var6[2] = 1;
            }
         } else if (arg3[1] > arg3[2]) {
            if (arg3[2] > arg3[0]) {
               var6[0] = 1;
               var6[1] = 2;
               var6[2] = 0;
            } else {
               var6[0] = 1;
               var6[1] = 0;
               var6[2] = 2;
            }
         } else {
            var6[0] = 2;
            var6[1] = 1;
            var6[2] = 0;
         }

         var13[0] = arg0[0] * arg0[0] + arg0[1] * arg0[1] + arg0[2] * arg0[2];
         var13[1] = arg0[3] * arg0[3] + arg0[4] * arg0[4] + arg0[5] * arg0[5];
         var13[2] = arg0[6] * arg0[6] + arg0[7] * arg0[7] + arg0[8] * arg0[8];
         byte var8;
         byte var9;
         byte var10;
         if (var13[0] > var13[1]) {
            if (var13[0] > var13[2]) {
               if (var13[2] > var13[1]) {
                  var8 = 0;
                  var10 = 1;
                  var9 = 2;
               } else {
                  var8 = 0;
                  var9 = 1;
                  var10 = 2;
               }
            } else {
               var10 = 0;
               var8 = 1;
               var9 = 2;
            }
         } else if (var13[1] > var13[2]) {
            if (var13[2] > var13[0]) {
               var9 = 0;
               var10 = 1;
               var8 = 2;
            } else {
               var9 = 0;
               var8 = 1;
               var10 = 2;
            }
         } else {
            var10 = 0;
            var9 = 1;
            var8 = 2;
         }

         int var11 = var6[var8];
         arg5[0] = arg3[var11];
         var11 = var6[var9];
         arg5[1] = arg3[var11];
         var11 = var6[var10];
         arg5[2] = arg3[var11];
         var11 = var6[var8];
         arg4[0] = var14[var11];
         var11 = var6[var8] + 3;
         arg4[3] = var14[var11];
         var11 = var6[var8] + 6;
         arg4[6] = var14[var11];
         var11 = var6[var9];
         arg4[1] = var14[var11];
         var11 = var6[var9] + 3;
         arg4[4] = var14[var11];
         var11 = var6[var9] + 6;
         arg4[7] = var14[var11];
         var11 = var6[var10];
         arg4[2] = var14[var11];
         var11 = var6[var10] + 3;
         arg4[5] = var14[var11];
         var11 = var6[var10] + 6;
         arg4[8] = var14[var11];
      }

   }

   public static int null(double[] arg0, double[] arg1, double[] arg2, double[] arg3) {
      double[] var16 = new double[2];
      double[] var17 = new double[2];
      double[] var18 = new double[2];
      double[] var19 = new double[2];
      double[] var20 = new double[9];
      boolean var29 = true;
      double var30 = 4.89E-15D;
      double var32 = 1.0D;
      double var34 = -1.0D;
      boolean var7 = false;
      byte var36 = 1;
      if (Math.abs(arg1[1]) < 4.89E-15D || Math.abs(arg1[0]) < 4.89E-15D) {
         var7 = true;
      }

      double var21;
      double var23;
      for(int var6 = 0; var6 < 10 && !var7; ++var6) {
         double var8 = null(arg0[1], arg1[1], arg0[2]);
         double var25 = (Math.abs(arg0[0]) - var8) * (null(var32, arg0[0]) + var8 / arg0[0]);
         double var27 = arg1[0];
         null(var25, var27, var19, var17, 0, var36);
         var25 = var17[0] * arg0[0] + var19[0] * arg1[0];
         arg1[0] = var17[0] * arg1[0] - var19[0] * arg0[0];
         var27 = var19[0] * arg0[1];
         arg0[1] = var17[0] * arg0[1];
         double var14 = null(var25, var27, var18, var16, 0, var36);
         var36 = 0;
         arg0[0] = var14;
         var25 = var16[0] * arg1[0] + var18[0] * arg0[1];
         arg0[1] = var16[0] * arg0[1] - var18[0] * arg1[0];
         var27 = var18[0] * arg1[1];
         arg1[1] = var16[0] * arg1[1];
         var14 = null(var25, var27, var19, var17, 1, var36);
         arg1[0] = var14;
         var25 = var17[1] * arg0[1] + var19[1] * arg1[1];
         arg1[1] = var17[1] * arg1[1] - var19[1] * arg0[1];
         var27 = var19[1] * arg0[2];
         arg0[2] = var17[1] * arg0[2];
         var14 = null(var25, var27, var18, var16, 1, var36);
         arg0[1] = var14;
         var25 = var16[1] * arg1[1] + var18[1] * arg0[2];
         arg0[2] = var16[1] * arg0[2] - var18[1] * arg1[1];
         arg1[1] = var25;
         var21 = arg2[0];
         arg2[0] = var16[0] * var21 + var18[0] * arg2[3];
         arg2[3] = -var18[0] * var21 + var16[0] * arg2[3];
         var21 = arg2[1];
         arg2[1] = var16[0] * var21 + var18[0] * arg2[4];
         arg2[4] = -var18[0] * var21 + var16[0] * arg2[4];
         var21 = arg2[2];
         arg2[2] = var16[0] * var21 + var18[0] * arg2[5];
         arg2[5] = -var18[0] * var21 + var16[0] * arg2[5];
         var21 = arg2[3];
         arg2[3] = var16[1] * var21 + var18[1] * arg2[6];
         arg2[6] = -var18[1] * var21 + var16[1] * arg2[6];
         var21 = arg2[4];
         arg2[4] = var16[1] * var21 + var18[1] * arg2[7];
         arg2[7] = -var18[1] * var21 + var16[1] * arg2[7];
         var21 = arg2[5];
         arg2[5] = var16[1] * var21 + var18[1] * arg2[8];
         arg2[8] = -var18[1] * var21 + var16[1] * arg2[8];
         var23 = arg3[0];
         arg3[0] = var17[0] * var23 + var19[0] * arg3[1];
         arg3[1] = -var19[0] * var23 + var17[0] * arg3[1];
         var23 = arg3[3];
         arg3[3] = var17[0] * var23 + var19[0] * arg3[4];
         arg3[4] = -var19[0] * var23 + var17[0] * arg3[4];
         var23 = arg3[6];
         arg3[6] = var17[0] * var23 + var19[0] * arg3[7];
         arg3[7] = -var19[0] * var23 + var17[0] * arg3[7];
         var23 = arg3[1];
         arg3[1] = var17[1] * var23 + var19[1] * arg3[2];
         arg3[2] = -var19[1] * var23 + var17[1] * arg3[2];
         var23 = arg3[4];
         arg3[4] = var17[1] * var23 + var19[1] * arg3[5];
         arg3[5] = -var19[1] * var23 + var17[1] * arg3[5];
         var23 = arg3[7];
         arg3[7] = var17[1] * var23 + var19[1] * arg3[8];
         arg3[8] = -var19[1] * var23 + var17[1] * arg3[8];
         var20[0] = arg0[0];
         var20[1] = arg1[0];
         var20[2] = 0.0D;
         var20[3] = 0.0D;
         var20[4] = arg0[1];
         var20[5] = arg1[1];
         var20[6] = 0.0D;
         var20[7] = 0.0D;
         var20[8] = arg0[2];
         if (Math.abs(arg1[1]) < 4.89E-15D || Math.abs(arg1[0]) < 4.89E-15D) {
            var7 = true;
         }
      }

      if (Math.abs(arg1[1]) < 4.89E-15D) {
         null(arg0[0], arg1[0], arg0[1], arg0, var18, var16, var19, var17, 0);
         var21 = arg2[0];
         arg2[0] = var16[0] * var21 + var18[0] * arg2[3];
         arg2[3] = -var18[0] * var21 + var16[0] * arg2[3];
         var21 = arg2[1];
         arg2[1] = var16[0] * var21 + var18[0] * arg2[4];
         arg2[4] = -var18[0] * var21 + var16[0] * arg2[4];
         var21 = arg2[2];
         arg2[2] = var16[0] * var21 + var18[0] * arg2[5];
         arg2[5] = -var18[0] * var21 + var16[0] * arg2[5];
         var23 = arg3[0];
         arg3[0] = var17[0] * var23 + var19[0] * arg3[1];
         arg3[1] = -var19[0] * var23 + var17[0] * arg3[1];
         var23 = arg3[3];
         arg3[3] = var17[0] * var23 + var19[0] * arg3[4];
         arg3[4] = -var19[0] * var23 + var17[0] * arg3[4];
         var23 = arg3[6];
         arg3[6] = var17[0] * var23 + var19[0] * arg3[7];
         arg3[7] = -var19[0] * var23 + var17[0] * arg3[7];
      } else {
         null(arg0[1], arg1[1], arg0[2], arg0, var18, var16, var19, var17, 1);
         var21 = arg2[3];
         arg2[3] = var16[0] * var21 + var18[0] * arg2[6];
         arg2[6] = -var18[0] * var21 + var16[0] * arg2[6];
         var21 = arg2[4];
         arg2[4] = var16[0] * var21 + var18[0] * arg2[7];
         arg2[7] = -var18[0] * var21 + var16[0] * arg2[7];
         var21 = arg2[5];
         arg2[5] = var16[0] * var21 + var18[0] * arg2[8];
         arg2[8] = -var18[0] * var21 + var16[0] * arg2[8];
         var23 = arg3[1];
         arg3[1] = var17[0] * var23 + var19[0] * arg3[2];
         arg3[2] = -var19[0] * var23 + var17[0] * arg3[2];
         var23 = arg3[4];
         arg3[4] = var17[0] * var23 + var19[0] * arg3[5];
         arg3[5] = -var19[0] * var23 + var17[0] * arg3[5];
         var23 = arg3[7];
         arg3[7] = var17[0] * var23 + var19[0] * arg3[8];
         arg3[8] = -var19[0] * var23 + var17[0] * arg3[8];
      }

      return 0;
   }

   public static double long(double arg0, double arg1) {
      return arg0 > arg1 ? arg0 : arg1;
   }

   public static double class(double arg0, double arg1) {
      return arg0 < arg1 ? arg0 : arg1;
   }

   public static double null(double arg0, double arg1) {
      double var4 = arg0 >= 0.0D ? arg0 : -arg0;
      return arg1 >= 0.0D ? var4 : -var4;
   }

   public static double null(double arg0, double arg1, double arg2) {
      double var16 = Math.abs(arg0);
      double var18 = Math.abs(arg1);
      double var20 = Math.abs(arg2);
      double var10 = class(var16, var20);
      double var12 = long(var16, var20);
      double var6;
      double var28;
      if (var10 == 0.0D) {
         var28 = 0.0D;
         if (var12 != 0.0D) {
            var6 = class(var12, var18) / long(var12, var18);
         }
      } else {
         double var14;
         double var22;
         double var24;
         double var26;
         if (var18 < var12) {
            var22 = var10 / var12 + 1.0D;
            var24 = (var12 - var10) / var12;
            var6 = var18 / var12;
            var26 = var6 * var6;
            var14 = 2.0D / (Math.sqrt(var22 * var22 + var26) + Math.sqrt(var24 * var24 + var26));
            var28 = var10 * var14;
         } else {
            var26 = var12 / var18;
            if (var26 == 0.0D) {
               var28 = var10 * var12 / var18;
            } else {
               var22 = var10 / var12 + 1.0D;
               var24 = (var12 - var10) / var12;
               var6 = var22 * var26;
               double var8 = var24 * var26;
               var14 = 1.0D / (Math.sqrt(var6 * var6 + 1.0D) + Math.sqrt(var8 * var8 + 1.0D));
               var28 = var10 * var14 * var26;
               var28 += var28;
            }
         }
      }

      return var28;
   }

   public static int null(double arg0, double arg1, double arg2, double[] arg3, double[] arg4, double[] arg5, double[] arg6, double[] arg7, int arg8) {
      double var12 = 2.0D;
      double var14 = 1.0D;
      double var65 = arg3[0];
      double var63 = arg3[1];
      double var55 = 0.0D;
      double var57 = 0.0D;
      double var59 = 0.0D;
      double var61 = 0.0D;
      double var36 = 0.0D;
      double var44 = arg0;
      double var38 = Math.abs(arg0);
      double var48 = arg2;
      double var42 = Math.abs(arg2);
      byte var18 = 1;
      boolean var21;
      if (var42 > var38) {
         var21 = true;
      } else {
         var21 = false;
      }

      if (var21) {
         var18 = 3;
         var44 = arg2;
         var48 = arg0;
         double var19 = var38;
         var38 = var42;
         var42 = var19;
      }

      double var40 = Math.abs(arg1);
      if (var40 == 0.0D) {
         arg3[1] = var42;
         arg3[0] = var38;
         var55 = 1.0D;
         var57 = 1.0D;
         var59 = 0.0D;
         var61 = 0.0D;
      } else {
         boolean var52 = true;
         if (var40 > var38) {
            var18 = 2;
            if (var38 / var40 < 1.110223024E-16D) {
               var52 = false;
               var65 = var40;
               if (var42 > 1.0D) {
                  var63 = var38 / (var40 / var42);
               } else {
                  var63 = var38 / var40 * var42;
               }

               var55 = 1.0D;
               var59 = var48 / arg1;
               var61 = 1.0D;
               var57 = var44 / arg1;
            }
         }

         if (var52) {
            double var24 = var38 - var42;
            double var26;
            if (var24 == var38) {
               var26 = 1.0D;
            } else {
               var26 = var24 / var38;
            }

            double var28 = arg1 / var44;
            double var34 = 2.0D - var26;
            double var50 = var28 * var28;
            double var53 = var34 * var34;
            double var32 = Math.sqrt(var53 + var50);
            double var30;
            if (var26 == 0.0D) {
               var30 = Math.abs(var28);
            } else {
               var30 = Math.sqrt(var26 * var26 + var50);
            }

            double var22 = (var32 + var30) * 0.5D;
            if (var40 > var38) {
               var18 = 2;
               if (var38 / var40 < 1.110223024E-16D) {
                  var52 = false;
                  var65 = var40;
                  if (var42 > 1.0D) {
                     var63 = var38 / (var40 / var42);
                  } else {
                     var63 = var38 / var40 * var42;
                  }

                  var55 = 1.0D;
                  var59 = var48 / arg1;
                  var61 = 1.0D;
                  var57 = var44 / arg1;
               }
            }

            if (var52) {
               var24 = var38 - var42;
               if (var24 == var38) {
                  var26 = 1.0D;
               } else {
                  var26 = var24 / var38;
               }

               var28 = arg1 / var44;
               var34 = 2.0D - var26;
               var50 = var28 * var28;
               var53 = var34 * var34;
               var32 = Math.sqrt(var53 + var50);
               if (var26 == 0.0D) {
                  var30 = Math.abs(var28);
               } else {
                  var30 = Math.sqrt(var26 * var26 + var50);
               }

               var22 = (var32 + var30) * 0.5D;
               var63 = var42 / var22;
               var65 = var38 * var22;
               if (var50 == 0.0D) {
                  if (var26 == 0.0D) {
                     var34 = null(var12, var44) * null(var14, arg1);
                  } else {
                     var34 = arg1 / null(var24, var44) + var28 / var34;
                  }
               } else {
                  var34 = (var28 / (var32 + var34) + var28 / (var30 + var26)) * (var22 + 1.0D);
               }

               var26 = Math.sqrt(var34 * var34 + 4.0D);
               var57 = 2.0D / var26;
               var61 = var34 / var26;
               var55 = (var57 + var61 * var28) / var22;
               var59 = var48 / var44 * var61 / var22;
            }
         }

         if (var21) {
            arg5[0] = var61;
            arg4[0] = var57;
            arg7[0] = var59;
            arg6[0] = var55;
         } else {
            arg5[0] = var55;
            arg4[0] = var59;
            arg7[0] = var57;
            arg6[0] = var61;
         }

         if (var18 == 1) {
            var36 = null(var14, arg7[0]) * null(var14, arg5[0]) * null(var14, arg0);
         }

         if (var18 == 2) {
            var36 = null(var14, arg6[0]) * null(var14, arg5[0]) * null(var14, arg1);
         }

         if (var18 == 3) {
            var36 = null(var14, arg6[0]) * null(var14, arg4[0]) * null(var14, arg2);
         }

         arg3[arg8] = null(var65, var36);
         double var16 = var36 * null(var14, arg0) * null(var14, arg2);
         arg3[arg8 + 1] = null(var63, var16);
      }

      return 0;
   }

   public static double null(double arg0, double arg1, double[] arg2, double[] arg3, int arg4, int arg5) {
      double var27 = 2.002083095183101E-146D;
      double var29 = 4.9947976805055876E145D;
      double var13;
      double var15;
      double var25;
      if (arg1 == 0.0D) {
         var13 = 1.0D;
         var15 = 0.0D;
         var25 = arg0;
      } else if (arg0 == 0.0D) {
         var13 = 0.0D;
         var15 = 1.0D;
         var25 = arg1;
      } else {
         double var21 = arg0;
         double var23 = arg1;
         double var18 = long(Math.abs(arg0), Math.abs(arg1));
         int var17;
         int var20;
         if (var18 >= 4.9947976805055876E145D) {
            for(var20 = 0; var18 >= 4.9947976805055876E145D; var18 = long(Math.abs(var21), Math.abs(var23))) {
               ++var20;
               var21 *= 2.002083095183101E-146D;
               var23 *= 2.002083095183101E-146D;
            }

            var25 = Math.sqrt(var21 * var21 + var23 * var23);
            var13 = var21 / var25;
            var15 = var23 / var25;

            for(var17 = 1; var17 <= var20; ++var17) {
               var25 *= 4.9947976805055876E145D;
            }
         } else if (!(var18 <= 2.002083095183101E-146D)) {
            var25 = Math.sqrt(arg0 * arg0 + arg1 * arg1);
            var13 = arg0 / var25;
            var15 = arg1 / var25;
         } else {
            for(var20 = 0; var18 <= 2.002083095183101E-146D; var18 = long(Math.abs(var21), Math.abs(var23))) {
               ++var20;
               var21 *= 4.9947976805055876E145D;
               var23 *= 4.9947976805055876E145D;
            }

            var25 = Math.sqrt(var21 * var21 + var23 * var23);
            var13 = var21 / var25;
            var15 = var23 / var25;

            for(var17 = 1; var17 <= var20; ++var17) {
               var25 *= 2.002083095183101E-146D;
            }
         }

         if (Math.abs(arg0) > Math.abs(arg1) && var13 < 0.0D) {
            var13 = -var13;
            var15 = -var15;
            var25 = -var25;
         }
      }

      arg2[arg4] = var15;
      arg3[arg4] = var13;
      return var25;
   }

   public static void class(double[] arg0) {
      for(int var1 = 0; var1 < 3; ++var1) {
         System.out.println(arg0[var1 * 3 + 0] + " " + arg0[var1 * 3 + 1] + " " + arg0[var1 * 3 + 2] + "\n");
      }

   }

   public static void null(double[] arg0) {
      double var1 = arg0[0] * arg0[4] * arg0[8] + arg0[1] * arg0[5] * arg0[6] + arg0[2] * arg0[3] * arg0[7] - arg0[2] * arg0[4] * arg0[6] - arg0[0] * arg0[5] * arg0[7] - arg0[1] * arg0[3] * arg0[8];
      System.out.println(NetworkErrorException.null("n&~~*") + var1);
   }

   public static void null(double[] arg0, double[] arg1, double[] arg2) {
      double[] var4 = new double[]{arg0[0] * arg1[0] + arg0[1] * arg1[3] + arg0[2] * arg1[6], arg0[0] * arg1[1] + arg0[1] * arg1[4] + arg0[2] * arg1[7], arg0[0] * arg1[2] + arg0[1] * arg1[5] + arg0[2] * arg1[8], arg0[3] * arg1[0] + arg0[4] * arg1[3] + arg0[5] * arg1[6], arg0[3] * arg1[1] + arg0[4] * arg1[4] + arg0[5] * arg1[7], arg0[3] * arg1[2] + arg0[4] * arg1[5] + arg0[5] * arg1[8], arg0[6] * arg1[0] + arg0[7] * arg1[3] + arg0[8] * arg1[6], arg0[6] * arg1[1] + arg0[7] * arg1[4] + arg0[8] * arg1[7], arg0[6] * arg1[2] + arg0[7] * arg1[5] + arg0[8] * arg1[8]};

      for(int var3 = 0; var3 < 9; ++var3) {
         arg2[var3] = var4[var3];
      }

   }

   public static void null(double[] arg0, double[] arg1) {
      arg1[0] = arg0[0];
      arg1[1] = arg0[3];
      arg1[2] = arg0[6];
      arg1[3] = arg0[1];
      arg1[4] = arg0[4];
      arg1[5] = arg0[7];
      arg1[6] = arg0[2];
      arg1[7] = arg0[5];
      arg1[8] = arg0[8];
   }

   public static double null(double[] arg0) {
      if (arg0[0] > arg0[1]) {
         return arg0[0] > arg0[2] ? arg0[0] : arg0[2];
      } else {
         return arg0[1] > arg0[2] ? arg0[1] : arg0[2];
      }
   }

   private static final boolean null(double arg0, double arg1) {
      if (arg0 == arg1) {
         return true;
      } else {
         double var4 = 1.0E-6D;
         double var6 = 1.0E-4D;
         double var8 = Math.abs(arg0 - arg1);
         double var10 = Math.abs(arg0);
         double var12 = Math.abs(arg1);
         double var14 = var10 >= var12 ? var10 : var12;
         if (var8 < 1.0E-6D) {
            return true;
         } else {
            return var8 / var14 < 1.0E-4D;
         }
      }
   }

   public Object clone() {
      Matrix3d var1 = null;

      try {
         var1 = (Matrix3d)super.clone();
         return var1;
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }
   }

   public final double new() {
      return thisx.case;
   }

   public final void new(double arg0) {
      thisx.case = arg0;
   }

   public final double this() {
      return thisx.short;
   }

   public final void this(double arg0) {
      thisx.short = arg0;
   }

   public final double short() {
      return thisx.long;
   }

   public final void short(double arg0) {
      thisx.long = arg0;
   }

   public final double do() {
      return thisx.new;
   }

   public final void do(double arg0) {
      thisx.new = arg0;
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
}
