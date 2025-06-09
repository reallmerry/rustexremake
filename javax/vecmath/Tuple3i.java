package javax.vecmath;

import breeze.generic.MappingUFuncLowPrio;
import breeze.math.Normed;
import java.io.Serializable;

public abstract class Tuple3i implements Serializable, Cloneable {
   public static final long null = -732740491767276200L;
   public int int;
   public int final;
   public int this;

   public Tuple3i(int arg0, int arg1, int arg2) {
      thisx.int = arg0;
      thisx.final = arg1;
      thisx.this = arg2;
   }

   public Tuple3i(int[] arg0) {
      thisx.int = arg0[0];
      thisx.final = arg0[1];
      thisx.this = arg0[2];
   }

   public Tuple3i(Tuple3i arg0) {
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple3i() {
      thisx.int = 0;
      thisx.final = 0;
      thisx.this = 0;
   }

   public final void null(int arg0, int arg1, int arg2) {
      thisx.int = arg0;
      thisx.final = arg1;
      thisx.this = arg2;
   }

   public final void class(int[] arg0) {
      thisx.int = arg0[0];
      thisx.final = arg0[1];
      thisx.this = arg0[2];
   }

   public final void do(Tuple3i arg0) {
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(int[] arg0) {
      arg0[0] = thisx.int;
      arg0[1] = thisx.final;
      arg0[2] = thisx.this;
   }

   public final void true(Tuple3i arg0) {
      arg0.int = thisx.int;
      arg0.final = thisx.final;
      arg0.this = thisx.this;
   }

   public final void class(Tuple3i arg0, Tuple3i arg1) {
      thisx.int = arg0.int + arg1.int;
      thisx.final = arg0.final + arg1.final;
      thisx.this = arg0.this + arg1.this;
   }

   public final void const(Tuple3i arg0) {
      thisx.int += arg0.int;
      thisx.final += arg0.final;
      thisx.this += arg0.this;
   }

   public final void null(Tuple3i arg0, Tuple3i arg1) {
      thisx.int = arg0.int - arg1.int;
      thisx.final = arg0.final - arg1.final;
      thisx.this = arg0.this - arg1.this;
   }

   public final void long(Tuple3i arg0) {
      thisx.int -= arg0.int;
      thisx.final -= arg0.final;
      thisx.this -= arg0.this;
   }

   public final void class(Tuple3i arg0) {
      thisx.int = -arg0.int;
      thisx.final = -arg0.final;
      thisx.this = -arg0.this;
   }

   public final void class() {
      thisx.int = -thisx.int;
      thisx.final = -thisx.final;
      thisx.this = -thisx.this;
   }

   public final void const(int arg0, Tuple3i arg1) {
      thisx.int = arg0 * arg1.int;
      thisx.final = arg0 * arg1.final;
      thisx.this = arg0 * arg1.this;
   }

   public final void do(int arg0) {
      thisx.int *= arg0;
      thisx.final *= arg0;
      thisx.this *= arg0;
   }

   public final void null(int arg0, Tuple3i arg1, Tuple3i arg2) {
      thisx.int = arg0 * arg1.int + arg2.int;
      thisx.final = arg0 * arg1.final + arg2.final;
      thisx.this = arg0 * arg1.this + arg2.this;
   }

   public final void long(int arg0, Tuple3i arg1) {
      thisx.int = arg0 * thisx.int + arg1.int;
      thisx.final = arg0 * thisx.final + arg1.final;
      thisx.this = arg0 * thisx.this + arg1.this;
   }

   public String toString() {
      return MappingUFuncLowPrio.null("8") + thisx.int + Normed.null("\u0011Y") + thisx.final + MappingUFuncLowPrio.null("Q0") + thisx.this + Normed.null("P");
   }

   public boolean equals(Object arg0) {
      try {
         Tuple3i var2 = (Tuple3i)arg0;
         return thisx.int == var2.int && thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = 31L * var1 + (long)thisx.int;
      var1 = 31L * var1 + (long)thisx.final;
      var1 = 31L * var1 + (long)thisx.this;
      return (int)(var1 ^ var1 >> 32);
   }

   public final void null(int arg0, int arg1, Tuple3i arg2) {
      if (arg2.int > arg1) {
         thisx.int = arg1;
      } else if (arg2.int < arg0) {
         thisx.int = arg0;
      } else {
         thisx.int = arg2.int;
      }

      if (arg2.final > arg1) {
         thisx.final = arg1;
      } else if (arg2.final < arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg2.final;
      }

      if (arg2.this > arg1) {
         thisx.this = arg1;
      } else if (arg2.this < arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg2.this;
      }

   }

   public final void class(int arg0, Tuple3i arg1) {
      if (arg1.int < arg0) {
         thisx.int = arg0;
      } else {
         thisx.int = arg1.int;
      }

      if (arg1.final < arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg1.final;
      }

      if (arg1.this < arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg1.this;
      }

   }

   public final void null(int arg0, Tuple3i arg1) {
      if (arg1.int > arg0) {
         thisx.int = arg0;
      } else {
         thisx.int = arg1.int;
      }

      if (arg1.final > arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg1.final;
      }

      if (arg1.this > arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg1.this;
      }

   }

   public final void null(Tuple3i arg0) {
      thisx.int = Math.abs(arg0.int);
      thisx.final = Math.abs(arg0.final);
      thisx.this = Math.abs(arg0.this);
   }

   public final void null(int arg0, int arg1) {
      if (thisx.int > arg1) {
         thisx.int = arg1;
      } else if (thisx.int < arg0) {
         thisx.int = arg0;
      }

      if (thisx.final > arg1) {
         thisx.final = arg1;
      } else if (thisx.final < arg0) {
         thisx.final = arg0;
      }

      if (thisx.this > arg1) {
         thisx.this = arg1;
      } else if (thisx.this < arg0) {
         thisx.this = arg0;
      }

   }

   public final void true(int arg0) {
      if (thisx.int < arg0) {
         thisx.int = arg0;
      }

      if (thisx.final < arg0) {
         thisx.final = arg0;
      }

      if (thisx.this < arg0) {
         thisx.this = arg0;
      }

   }

   public final void const(int arg0) {
      if (thisx.int > arg0) {
         thisx.int = arg0;
      }

      if (thisx.final > arg0) {
         thisx.final = arg0;
      }

      if (thisx.this > arg0) {
         thisx.this = arg0;
      }

   }

   public final void null() {
      thisx.int = Math.abs(thisx.int);
      thisx.final = Math.abs(thisx.final);
      thisx.this = Math.abs(thisx.this);
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public final int long() {
      return thisx.int;
   }

   public final void long(int arg0) {
      thisx.int = arg0;
   }

   public final int class() {
      return thisx.final;
   }

   public final void class(int arg0) {
      thisx.final = arg0;
   }

   public final int null() {
      return thisx.this;
   }

   public final void null(int arg0) {
      thisx.this = arg0;
   }
}
