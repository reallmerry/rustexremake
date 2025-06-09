package javax.vecmath;

import breeze.optimize.proximal.LogisticGenerator;
import com.sk89q.worldedit.InvalidItemException;
import java.io.Serializable;

public class GMatrix implements Serializable, Cloneable {
   public static final long const = 2777097312029690941L;
   private static final boolean false = false;
   public int null;
   public int int;
   public double[][] final;
   private static final double this = 1.0E-10D;

   public GMatrix(int arg0, int arg1) {
      thisx.final = new double[arg0][arg1];
      thisx.null = arg0;
      thisx.int = arg1;

      int var3;
      for(var3 = 0; var3 < arg0; ++var3) {
         for(int var4 = 0; var4 < arg1; ++var4) {
            thisx.final[var3][var4] = 0.0D;
         }
      }

      int var5;
      if (arg0 < arg1) {
         var5 = arg0;
      } else {
         var5 = arg1;
      }

      for(var3 = 0; var3 < var5; ++var3) {
         thisx.final[var3][var3] = 1.0D;
      }

   }

   public GMatrix(int arg0, int arg1, double[] arg2) {
      thisx.final = new double[arg0][arg1];
      thisx.null = arg0;
      thisx.int = arg1;

      for(int var4 = 0; var4 < arg0; ++var4) {
         for(int var5 = 0; var5 < arg1; ++var5) {
            thisx.final[var4][var5] = arg2[var4 * arg1 + var5];
         }
      }

   }

   public GMatrix(GMatrix arg0) {
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = new double[thisx.null][thisx.int];

      for(int var2 = 0; var2 < thisx.null; ++var2) {
         for(int var3 = 0; var3 < thisx.int; ++var3) {
            thisx.final[var2][var3] = arg0.final[var2][var3];
         }
      }

   }

   public final void else(GMatrix arg0) {
      if (thisx.int == arg0.null && thisx.int == arg0.int) {
         double[][] var5 = new double[thisx.null][thisx.int];

         for(int var2 = 0; var2 < thisx.null; ++var2) {
            for(int var3 = 0; var3 < thisx.int; ++var3) {
               var5[var2][var3] = 0.0D;

               for(int var4 = 0; var4 < thisx.int; ++var4) {
                  var5[var2][var3] += thisx.final[var2][var4] * arg0.final[var4][var3];
               }
            }
         }

         thisx.final = var5;
      } else {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\\\u0019z i=cd")));
      }
   }

   public final void do(GMatrix arg0, GMatrix arg1) {
      if (arg0.int == arg1.null && thisx.null == arg0.null && thisx.int == arg1.int) {
         double[][] var6 = new double[thisx.null][thisx.int];

         for(int var3 = 0; var3 < arg0.null; ++var3) {
            for(int var4 = 0; var4 < arg1.int; ++var4) {
               var6[var3][var4] = 0.0D;

               for(int var5 = 0; var5 < arg0.int; ++var5) {
                  var6[var3][var4] += arg0.final[var3][var5] * arg1.final[var5][var4];
               }
            }
         }

         thisx.final = var6;
      } else {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("l)J\u0010Y\rSU")));
      }
   }

   public final void null(GVector arg0, GVector arg1) {
      if (thisx.null < arg0.null()) {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\\\u0019z i=cf")));
      } else if (thisx.int < arg1.null()) {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("l)J\u0010Y\rSW")));
      } else {
         for(int var3 = 0; var3 < arg0.null(); ++var3) {
            for(int var4 = 0; var4 < arg1.null(); ++var4) {
               thisx.final[var3][var4] = arg0.final[var3] * arg1.final[var4];
            }
         }

      }
   }

   public final void new(GMatrix arg0) {
      if (thisx.null != arg0.null) {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\\\u0019z i=c`")));
      } else if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("l)J\u0010Y\rSQ")));
      } else {
         for(int var2 = 0; var2 < thisx.null; ++var2) {
            for(int var3 = 0; var3 < thisx.int; ++var3) {
               thisx.final[var2][var3] += arg0.final[var2][var3];
            }
         }

      }
   }

   public final void true(GMatrix arg0, GMatrix arg1) {
      if (arg1.null != arg0.null) {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\\\u0019z i=cb")));
      } else if (arg1.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("l)J\u0010Y\rSS")));
      } else if (thisx.int == arg0.int && thisx.null == arg0.null) {
         for(int var3 = 0; var3 < thisx.null; ++var3) {
            for(int var4 = 0; var4 < thisx.int; ++var4) {
               thisx.final[var3][var4] = arg0.final[var3][var4] + arg1.final[var3][var4];
            }
         }

      } else {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\\\u0019z i=cl")));
      }
   }

   public final void this(GMatrix arg0) {
      if (thisx.null != arg0.null) {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("l)J\u0010Y\rS]")));
      } else if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,)l")));
      } else {
         for(int var2 = 0; var2 < thisx.null; ++var2) {
            for(int var3 = 0; var3 < thisx.int; ++var3) {
               thisx.final[var2][var3] -= arg0.final[var2][var3];
            }
         }

      }
   }

   public final void const(GMatrix arg0, GMatrix arg1) {
      if (arg1.null != arg0.null) {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("#f\u0005_\u0016B\u001c\u001aT")));
      } else if (arg1.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,*e")));
      } else if (thisx.null == arg0.null && thisx.int == arg0.int) {
         for(int var3 = 0; var3 < thisx.null; ++var3) {
            for(int var4 = 0; var4 < thisx.int; ++var4) {
               thisx.final[var3][var4] = arg0.final[var3][var4] - arg1.final[var3][var4];
            }
         }

      } else {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("#f\u0005_\u0016B\u001c\u001aV")));
      }
   }

   public final void do() {
      for(int var1 = 0; var1 < thisx.null; ++var1) {
         for(int var2 = 0; var2 < thisx.int; ++var2) {
            thisx.final[var1][var2] = -thisx.final[var1][var2];
         }
      }

   }

   public final void short(GMatrix arg0) {
      if (thisx.null == arg0.null && thisx.int == arg0.int) {
         for(int var2 = 0; var2 < thisx.null; ++var2) {
            for(int var3 = 0; var3 < thisx.int; ++var3) {
               thisx.final[var2][var3] = -arg0.final[var2][var3];
            }
         }

      } else {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,*g")));
      }
   }

   public final void true() {
      int var1;
      for(var1 = 0; var1 < thisx.null; ++var1) {
         for(int var2 = 0; var2 < thisx.int; ++var2) {
            thisx.final[var1][var2] = 0.0D;
         }
      }

      int var3;
      if (thisx.null < thisx.int) {
         var3 = thisx.null;
      } else {
         var3 = thisx.int;
      }

      for(var1 = 0; var1 < var3; ++var1) {
         thisx.final[var1][var1] = 1.0D;
      }

   }

   public final void const() {
      for(int var1 = 0; var1 < thisx.null; ++var1) {
         for(int var2 = 0; var2 < thisx.int; ++var2) {
            thisx.final[var1][var2] = 0.0D;
         }
      }

   }

   public final void long() {
      int var1;
      for(var1 = 0; var1 < thisx.null; ++var1) {
         for(int var2 = 0; var2 < thisx.int; ++var2) {
            thisx.final[var1][var2] = -thisx.final[var1][var2];
         }
      }

      int var3;
      if (thisx.null < thisx.int) {
         var3 = thisx.null;
      } else {
         var3 = thisx.int;
      }

      for(var1 = 0; var1 < var3; ++var1) {
         int var10002 = thisx.final[var1][var1]++;
      }

   }

   public final void class() {
      thisx.null(thisx);
   }

   public final void do(GMatrix arg0) {
      thisx.null(arg0);
   }

   public final void null(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, GMatrix arg6) {
      int var8;
      int var9;
      if (thisx != arg6) {
         for(var8 = 0; var8 < arg2; ++var8) {
            for(var9 = 0; var9 < arg3; ++var9) {
               arg6.final[arg4 + var8][arg5 + var9] = thisx.final[arg0 + var8][arg1 + var9];
            }
         }
      } else {
         double[][] var10 = new double[arg2][arg3];

         for(var8 = 0; var8 < arg2; ++var8) {
            for(var9 = 0; var9 < arg3; ++var9) {
               var10[var8][var9] = thisx.final[arg0 + var8][arg1 + var9];
            }
         }

         for(var8 = 0; var8 < arg2; ++var8) {
            for(var9 = 0; var9 < arg3; ++var9) {
               arg6.final[arg4 + var8][arg5 + var9] = var10[var8][var9];
            }
         }
      }

   }

   public final void null(int arg0, int arg1) {
      double[][] var3 = new double[arg0][arg1];
      int var6;
      if (thisx.null < arg0) {
         var6 = thisx.null;
      } else {
         var6 = arg0;
      }

      int var7;
      if (thisx.int < arg1) {
         var7 = thisx.int;
      } else {
         var7 = arg1;
      }

      for(int var4 = 0; var4 < var6; ++var4) {
         for(int var5 = 0; var5 < var7; ++var5) {
            var3[var4][var5] = thisx.final[var4][var5];
         }
      }

      thisx.null = arg0;
      thisx.int = arg1;
      thisx.final = var3;
   }

   public final void null(double[] arg0) {
      for(int var2 = 0; var2 < thisx.null; ++var2) {
         for(int var3 = 0; var3 < thisx.int; ++var3) {
            thisx.final[var2][var3] = arg0[thisx.int * var2 + var3];
         }
      }

   }

   public final void class(Matrix3f arg0) {
      if (thisx.int < 3 || thisx.null < 3) {
         thisx.int = 3;
         thisx.null = 3;
         thisx.final = new double[thisx.null][thisx.int];
      }

      thisx.final[0][0] = (double)arg0.case;
      thisx.final[0][1] = (double)arg0.short;
      thisx.final[0][2] = (double)arg0.long;
      thisx.final[1][0] = (double)arg0.new;
      thisx.final[1][1] = (double)arg0.const;
      thisx.final[1][2] = (double)arg0.false;
      thisx.final[2][0] = (double)arg0.null;
      thisx.final[2][1] = (double)arg0.int;
      thisx.final[2][2] = (double)arg0.final;

      for(int var2 = 3; var2 < thisx.null; ++var2) {
         for(int var3 = 3; var3 < thisx.int; ++var3) {
            thisx.final[var2][var3] = 0.0D;
         }
      }

   }

   public final void class(Matrix3d arg0) {
      if (thisx.null < 3 || thisx.int < 3) {
         thisx.final = new double[3][3];
         thisx.null = 3;
         thisx.int = 3;
      }

      thisx.final[0][0] = arg0.case;
      thisx.final[0][1] = arg0.short;
      thisx.final[0][2] = arg0.long;
      thisx.final[1][0] = arg0.new;
      thisx.final[1][1] = arg0.const;
      thisx.final[1][2] = arg0.false;
      thisx.final[2][0] = arg0.null;
      thisx.final[2][1] = arg0.int;
      thisx.final[2][2] = arg0.final;

      for(int var2 = 3; var2 < thisx.null; ++var2) {
         for(int var3 = 3; var3 < thisx.int; ++var3) {
            thisx.final[var2][var3] = 0.0D;
         }
      }

   }

   public final void class(Matrix4f arg0) {
      if (thisx.null < 4 || thisx.int < 4) {
         thisx.final = new double[4][4];
         thisx.null = 4;
         thisx.int = 4;
      }

      thisx.final[0][0] = (double)arg0.char;
      thisx.final[0][1] = (double)arg0.float;
      thisx.final[0][2] = (double)arg0.break;
      thisx.final[0][3] = (double)arg0.catch;
      thisx.final[1][0] = (double)arg0.do;
      thisx.final[1][1] = (double)arg0.else;
      thisx.final[1][2] = (double)arg0.goto;
      thisx.final[1][3] = (double)arg0.case;
      thisx.final[2][0] = (double)arg0.short;
      thisx.final[2][1] = (double)arg0.long;
      thisx.final[2][2] = (double)arg0.new;
      thisx.final[2][3] = (double)arg0.const;
      thisx.final[3][0] = (double)arg0.false;
      thisx.final[3][1] = (double)arg0.null;
      thisx.final[3][2] = (double)arg0.int;
      thisx.final[3][3] = (double)arg0.final;

      for(int var2 = 4; var2 < thisx.null; ++var2) {
         for(int var3 = 4; var3 < thisx.int; ++var3) {
            thisx.final[var2][var3] = 0.0D;
         }
      }

   }

   public final void class(Matrix4d arg0) {
      if (thisx.null < 4 || thisx.int < 4) {
         thisx.final = new double[4][4];
         thisx.null = 4;
         thisx.int = 4;
      }

      thisx.final[0][0] = arg0.char;
      thisx.final[0][1] = arg0.float;
      thisx.final[0][2] = arg0.break;
      thisx.final[0][3] = arg0.catch;
      thisx.final[1][0] = arg0.do;
      thisx.final[1][1] = arg0.else;
      thisx.final[1][2] = arg0.goto;
      thisx.final[1][3] = arg0.case;
      thisx.final[2][0] = arg0.short;
      thisx.final[2][1] = arg0.long;
      thisx.final[2][2] = arg0.new;
      thisx.final[2][3] = arg0.const;
      thisx.final[3][0] = arg0.false;
      thisx.final[3][1] = arg0.null;
      thisx.final[3][2] = arg0.int;
      thisx.final[3][3] = arg0.final;

      for(int var2 = 4; var2 < thisx.null; ++var2) {
         for(int var3 = 4; var3 < thisx.int; ++var3) {
            thisx.final[var2][var3] = 0.0D;
         }
      }

   }

   public final void true(GMatrix arg0) {
      if (thisx.null < arg0.null || thisx.int < arg0.int) {
         thisx.null = arg0.null;
         thisx.int = arg0.int;
         thisx.final = new double[thisx.null][thisx.int];
      }

      int var2;
      int var3;
      for(var2 = 0; var2 < Math.min(thisx.null, arg0.null); ++var2) {
         for(var3 = 0; var3 < Math.min(thisx.int, arg0.int); ++var3) {
            thisx.final[var2][var3] = arg0.final[var2][var3];
         }
      }

      for(var2 = arg0.null; var2 < thisx.null; ++var2) {
         for(var3 = arg0.int; var3 < thisx.int; ++var3) {
            thisx.final[var2][var3] = 0.0D;
         }
      }

   }

   public final int class() {
      return thisx.null;
   }

   public final int null() {
      return thisx.int;
   }

   public final double null(int arg0, int arg1) {
      return thisx.final[arg0][arg1];
   }

   public final void null(int arg0, int arg1, double arg2) {
      thisx.final[arg0][arg1] = arg2;
   }

   public final void const(int arg0, double[] arg1) {
      for(int var3 = 0; var3 < thisx.int; ++var3) {
         arg1[var3] = thisx.final[arg0][var3];
      }

   }

   public final void const(int arg0, GVector arg1) {
      if (arg1.null() < thisx.int) {
         arg1.null(thisx.int);
      }

      for(int var3 = 0; var3 < thisx.int; ++var3) {
         arg1.final[var3] = thisx.final[arg0][var3];
      }

   }

   public final void long(int arg0, double[] arg1) {
      for(int var3 = 0; var3 < thisx.null; ++var3) {
         arg1[var3] = thisx.final[var3][arg0];
      }

   }

   public final void long(int arg0, GVector arg1) {
      if (arg1.null() < thisx.null) {
         arg1.null(thisx.null);
      }

      for(int var3 = 0; var3 < thisx.null; ++var3) {
         arg1.final[var3] = thisx.final[var3][arg0];
      }

   }

   public final void null(Matrix3d arg0) {
      if (thisx.null >= 3 && thisx.int >= 3) {
         arg0.case = thisx.final[0][0];
         arg0.short = thisx.final[0][1];
         arg0.long = thisx.final[0][2];
         arg0.new = thisx.final[1][0];
         arg0.const = thisx.final[1][1];
         arg0.false = thisx.final[1][2];
         arg0.null = thisx.final[2][0];
         arg0.int = thisx.final[2][1];
         arg0.final = thisx.final[2][2];
      } else {
         arg0.class();
         if (thisx.int > 0) {
            if (thisx.null > 0) {
               arg0.case = thisx.final[0][0];
               if (thisx.null > 1) {
                  arg0.new = thisx.final[1][0];
                  if (thisx.null > 2) {
                     arg0.null = thisx.final[2][0];
                  }
               }
            }

            if (thisx.int > 1) {
               if (thisx.null > 0) {
                  arg0.short = thisx.final[0][1];
                  if (thisx.null > 1) {
                     arg0.const = thisx.final[1][1];
                     if (thisx.null > 2) {
                        arg0.int = thisx.final[2][1];
                     }
                  }
               }

               if (thisx.int > 2 && thisx.null > 0) {
                  arg0.long = thisx.final[0][2];
                  if (thisx.null > 1) {
                     arg0.false = thisx.final[1][2];
                     if (thisx.null > 2) {
                        arg0.final = thisx.final[2][2];
                     }
                  }
               }
            }
         }
      }

   }

   public final void null(Matrix3f arg0) {
      if (thisx.null >= 3 && thisx.int >= 3) {
         arg0.case = (float)thisx.final[0][0];
         arg0.short = (float)thisx.final[0][1];
         arg0.long = (float)thisx.final[0][2];
         arg0.new = (float)thisx.final[1][0];
         arg0.const = (float)thisx.final[1][1];
         arg0.false = (float)thisx.final[1][2];
         arg0.null = (float)thisx.final[2][0];
         arg0.int = (float)thisx.final[2][1];
         arg0.final = (float)thisx.final[2][2];
      } else {
         arg0.class();
         if (thisx.int > 0) {
            if (thisx.null > 0) {
               arg0.case = (float)thisx.final[0][0];
               if (thisx.null > 1) {
                  arg0.new = (float)thisx.final[1][0];
                  if (thisx.null > 2) {
                     arg0.null = (float)thisx.final[2][0];
                  }
               }
            }

            if (thisx.int > 1) {
               if (thisx.null > 0) {
                  arg0.short = (float)thisx.final[0][1];
                  if (thisx.null > 1) {
                     arg0.const = (float)thisx.final[1][1];
                     if (thisx.null > 2) {
                        arg0.int = (float)thisx.final[2][1];
                     }
                  }
               }

               if (thisx.int > 2 && thisx.null > 0) {
                  arg0.long = (float)thisx.final[0][2];
                  if (thisx.null > 1) {
                     arg0.false = (float)thisx.final[1][2];
                     if (thisx.null > 2) {
                        arg0.final = (float)thisx.final[2][2];
                     }
                  }
               }
            }
         }
      }

   }

   public final void null(Matrix4d arg0) {
      if (thisx.null >= 4 && thisx.int >= 4) {
         arg0.char = thisx.final[0][0];
         arg0.float = thisx.final[0][1];
         arg0.break = thisx.final[0][2];
         arg0.catch = thisx.final[0][3];
         arg0.do = thisx.final[1][0];
         arg0.else = thisx.final[1][1];
         arg0.goto = thisx.final[1][2];
         arg0.case = thisx.final[1][3];
         arg0.short = thisx.final[2][0];
         arg0.long = thisx.final[2][1];
         arg0.new = thisx.final[2][2];
         arg0.const = thisx.final[2][3];
         arg0.false = thisx.final[3][0];
         arg0.null = thisx.final[3][1];
         arg0.int = thisx.final[3][2];
         arg0.final = thisx.final[3][3];
      } else {
         arg0.class();
         if (thisx.int > 0) {
            if (thisx.null > 0) {
               arg0.char = thisx.final[0][0];
               if (thisx.null > 1) {
                  arg0.do = thisx.final[1][0];
                  if (thisx.null > 2) {
                     arg0.short = thisx.final[2][0];
                     if (thisx.null > 3) {
                        arg0.false = thisx.final[3][0];
                     }
                  }
               }
            }

            if (thisx.int > 1) {
               if (thisx.null > 0) {
                  arg0.float = thisx.final[0][1];
                  if (thisx.null > 1) {
                     arg0.else = thisx.final[1][1];
                     if (thisx.null > 2) {
                        arg0.long = thisx.final[2][1];
                        if (thisx.null > 3) {
                           arg0.null = thisx.final[3][1];
                        }
                     }
                  }
               }

               if (thisx.int > 2) {
                  if (thisx.null > 0) {
                     arg0.break = thisx.final[0][2];
                     if (thisx.null > 1) {
                        arg0.goto = thisx.final[1][2];
                        if (thisx.null > 2) {
                           arg0.new = thisx.final[2][2];
                           if (thisx.null > 3) {
                              arg0.int = thisx.final[3][2];
                           }
                        }
                     }
                  }

                  if (thisx.int > 3 && thisx.null > 0) {
                     arg0.catch = thisx.final[0][3];
                     if (thisx.null > 1) {
                        arg0.case = thisx.final[1][3];
                        if (thisx.null > 2) {
                           arg0.const = thisx.final[2][3];
                           if (thisx.null > 3) {
                              arg0.final = thisx.final[3][3];
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   public final void null(Matrix4f arg0) {
      if (thisx.null >= 4 && thisx.int >= 4) {
         arg0.char = (float)thisx.final[0][0];
         arg0.float = (float)thisx.final[0][1];
         arg0.break = (float)thisx.final[0][2];
         arg0.catch = (float)thisx.final[0][3];
         arg0.do = (float)thisx.final[1][0];
         arg0.else = (float)thisx.final[1][1];
         arg0.goto = (float)thisx.final[1][2];
         arg0.case = (float)thisx.final[1][3];
         arg0.short = (float)thisx.final[2][0];
         arg0.long = (float)thisx.final[2][1];
         arg0.new = (float)thisx.final[2][2];
         arg0.const = (float)thisx.final[2][3];
         arg0.false = (float)thisx.final[3][0];
         arg0.null = (float)thisx.final[3][1];
         arg0.int = (float)thisx.final[3][2];
         arg0.final = (float)thisx.final[3][3];
      } else {
         arg0.class();
         if (thisx.int > 0) {
            if (thisx.null > 0) {
               arg0.char = (float)thisx.final[0][0];
               if (thisx.null > 1) {
                  arg0.do = (float)thisx.final[1][0];
                  if (thisx.null > 2) {
                     arg0.short = (float)thisx.final[2][0];
                     if (thisx.null > 3) {
                        arg0.false = (float)thisx.final[3][0];
                     }
                  }
               }
            }

            if (thisx.int > 1) {
               if (thisx.null > 0) {
                  arg0.float = (float)thisx.final[0][1];
                  if (thisx.null > 1) {
                     arg0.else = (float)thisx.final[1][1];
                     if (thisx.null > 2) {
                        arg0.long = (float)thisx.final[2][1];
                        if (thisx.null > 3) {
                           arg0.null = (float)thisx.final[3][1];
                        }
                     }
                  }
               }

               if (thisx.int > 2) {
                  if (thisx.null > 0) {
                     arg0.break = (float)thisx.final[0][2];
                     if (thisx.null > 1) {
                        arg0.goto = (float)thisx.final[1][2];
                        if (thisx.null > 2) {
                           arg0.new = (float)thisx.final[2][2];
                           if (thisx.null > 3) {
                              arg0.int = (float)thisx.final[3][2];
                           }
                        }
                     }
                  }

                  if (thisx.int > 3 && thisx.null > 0) {
                     arg0.catch = (float)thisx.final[0][3];
                     if (thisx.null > 1) {
                        arg0.case = (float)thisx.final[1][3];
                        if (thisx.null > 2) {
                           arg0.const = (float)thisx.final[2][3];
                           if (thisx.null > 3) {
                              arg0.final = (float)thisx.final[3][3];
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   public final void const(GMatrix arg0) {
      int var4;
      if (thisx.int < arg0.int) {
         var4 = thisx.int;
      } else {
         var4 = arg0.int;
      }

      int var5;
      if (thisx.null < arg0.null) {
         var5 = thisx.null;
      } else {
         var5 = arg0.null;
      }

      int var2;
      int var3;
      for(var2 = 0; var2 < var5; ++var2) {
         for(var3 = 0; var3 < var4; ++var3) {
            arg0.final[var2][var3] = thisx.final[var2][var3];
         }
      }

      for(var2 = var5; var2 < arg0.null; ++var2) {
         for(var3 = 0; var3 < arg0.int; ++var3) {
            arg0.final[var2][var3] = 0.0D;
         }
      }

      for(var3 = var4; var3 < arg0.int; ++var3) {
         for(var2 = 0; var2 < var5; ++var2) {
            arg0.final[var2][var3] = 0.0D;
         }
      }

   }

   public final void class(int arg0, double[] arg1) {
      for(int var3 = 0; var3 < thisx.int; ++var3) {
         thisx.final[arg0][var3] = arg1[var3];
      }

   }

   public final void class(int arg0, GVector arg1) {
      for(int var3 = 0; var3 < thisx.int; ++var3) {
         thisx.final[arg0][var3] = arg1.final[var3];
      }

   }

   public final void null(int arg0, double[] arg1) {
      for(int var3 = 0; var3 < thisx.null; ++var3) {
         thisx.final[var3][arg0] = arg1[var3];
      }

   }

   public final void null(int arg0, GVector arg1) {
      for(int var3 = 0; var3 < thisx.null; ++var3) {
         thisx.final[var3][arg0] = arg1.final[var3];
      }

   }

   public final void long(GMatrix arg0, GMatrix arg1) {
      if (arg0.null == arg1.int && thisx.null == arg0.int && thisx.int == arg1.null) {
         int var3;
         int var4;
         int var5;
         if (arg0 != thisx && arg1 != thisx) {
            for(var3 = 0; var3 < thisx.null; ++var3) {
               for(var4 = 0; var4 < thisx.int; ++var4) {
                  thisx.final[var3][var4] = 0.0D;

                  for(var5 = 0; var5 < arg0.null; ++var5) {
                     double[] var10000 = thisx.final[var3];
                     var10000[var4] += arg0.final[var5][var3] * arg1.final[var4][var5];
                  }
               }
            }
         } else {
            double[][] var6 = new double[thisx.null][thisx.int];

            for(var3 = 0; var3 < thisx.null; ++var3) {
               for(var4 = 0; var4 < thisx.int; ++var4) {
                  var6[var3][var4] = 0.0D;

                  for(var5 = 0; var5 < arg0.null; ++var5) {
                     var6[var3][var4] += arg0.final[var5][var3] * arg1.final[var4][var5];
                  }
               }
            }

            thisx.final = var6;
         }

      } else {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("#f\u0005_\u0016B\u001c\u001aP")));
      }
   }

   public final void class(GMatrix arg0, GMatrix arg1) {
      if (arg0.int == arg1.int && thisx.int == arg1.null && thisx.null == arg0.null) {
         int var3;
         int var4;
         int var5;
         if (arg0 != thisx && arg1 != thisx) {
            for(var3 = 0; var3 < thisx.null; ++var3) {
               for(var4 = 0; var4 < thisx.int; ++var4) {
                  thisx.final[var3][var4] = 0.0D;

                  for(var5 = 0; var5 < arg0.int; ++var5) {
                     double[] var10000 = thisx.final[var3];
                     var10000[var4] += arg0.final[var3][var5] * arg1.final[var4][var5];
                  }
               }
            }
         } else {
            double[][] var6 = new double[thisx.null][thisx.int];

            for(var3 = 0; var3 < thisx.null; ++var3) {
               for(var4 = 0; var4 < thisx.int; ++var4) {
                  var6[var3][var4] = 0.0D;

                  for(var5 = 0; var5 < arg0.int; ++var5) {
                     var6[var3][var4] += arg0.final[var3][var5] * arg1.final[var4][var5];
                  }
               }
            }

            thisx.final = var6;
         }

      } else {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,*a")));
      }
   }

   public final void null(GMatrix arg0, GMatrix arg1) {
      if (arg0.null == arg1.null && thisx.int == arg1.int && thisx.null == arg0.int) {
         int var3;
         int var4;
         int var5;
         if (arg0 != thisx && arg1 != thisx) {
            for(var3 = 0; var3 < thisx.null; ++var3) {
               for(var4 = 0; var4 < thisx.int; ++var4) {
                  thisx.final[var3][var4] = 0.0D;

                  for(var5 = 0; var5 < arg0.null; ++var5) {
                     double[] var10000 = thisx.final[var3];
                     var10000[var4] += arg0.final[var5][var3] * arg1.final[var5][var4];
                  }
               }
            }
         } else {
            double[][] var6 = new double[thisx.null][thisx.int];

            for(var3 = 0; var3 < thisx.null; ++var3) {
               for(var4 = 0; var4 < thisx.int; ++var4) {
                  var6[var3][var4] = 0.0D;

                  for(var5 = 0; var5 < arg0.null; ++var5) {
                     var6[var3][var4] += arg0.final[var5][var3] * arg1.final[var5][var4];
                  }
               }
            }

            thisx.final = var6;
         }

      } else {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("#f\u0005_\u0016B\u001c\u001aR")));
      }
   }

   public final void null() {
      int var1;
      int var2;
      if (thisx.null != thisx.int) {
         var1 = thisx.null;
         thisx.null = thisx.int;
         thisx.int = var1;
         double[][] var3 = new double[thisx.null][thisx.int];

         for(var1 = 0; var1 < thisx.null; ++var1) {
            for(var2 = 0; var2 < thisx.int; ++var2) {
               var3[var1][var2] = thisx.final[var2][var1];
            }
         }

         thisx.final = var3;
      } else {
         for(var1 = 0; var1 < thisx.null; ++var1) {
            for(var2 = 0; var2 < var1; ++var2) {
               double var5 = thisx.final[var1][var2];
               thisx.final[var1][var2] = thisx.final[var2][var1];
               thisx.final[var2][var1] = var5;
            }
         }
      }

   }

   public final void long(GMatrix arg0) {
      if (thisx.null == arg0.int && thisx.int == arg0.null) {
         if (arg0 != thisx) {
            for(int var2 = 0; var2 < thisx.null; ++var2) {
               for(int var3 = 0; var3 < thisx.int; ++var3) {
                  thisx.final[var2][var3] = arg0.final[var3][var2];
               }
            }
         } else {
            thisx.null();
         }

      } else {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,*c")));
      }
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer(thisx.null * thisx.int * 8);

      for(int var2 = 0; var2 < thisx.null; ++var2) {
         for(int var3 = 0; var3 < thisx.int; ++var3) {
            var1.append(thisx.final[var2][var3]).append(" ");
         }

         var1.append("\n");
      }

      return var1.toString();
   }

   private static void class(GMatrix arg0) {
      for(int var1 = 0; var1 < arg0.null; ++var1) {
         for(int var2 = 0; var2 < arg0.int; ++var2) {
            if (Math.abs(arg0.final[var1][var2]) < 1.0E-10D) {
               System.out.print(LogisticGenerator.null("D\u001bJ\u001bD\u000bD\u000bD"));
            } else {
               System.out.print(" " + arg0.final[var1][var2]);
            }
         }

         System.out.print("\n");
      }

   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, (long)thisx.null);
      var1 = VecMathUtil.null(var1, (long)thisx.int);

      for(int var3 = 0; var3 < thisx.null; ++var3) {
         for(int var4 = 0; var4 < thisx.int; ++var4) {
            var1 = VecMathUtil.null(var1, thisx.final[var3][var4]);
         }
      }

      return VecMathUtil.null(var1);
   }

   public boolean null(GMatrix arg0) {
      try {
         if (thisx.null == arg0.null && thisx.int == arg0.int) {
            for(int var2 = 0; var2 < thisx.null; ++var2) {
               for(int var3 = 0; var3 < thisx.int; ++var3) {
                  if (thisx.final[var2][var3] != arg0.final[var2][var3]) {
                     return false;
                  }
               }
            }

            return true;
         } else {
            return false;
         }
      } catch (NullPointerException var4) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         GMatrix var2 = (GMatrix)arg0;
         if (thisx.null == var2.null && thisx.int == var2.int) {
            for(int var3 = 0; var3 < thisx.null; ++var3) {
               for(int var4 = 0; var4 < thisx.int; ++var4) {
                  if (thisx.final[var3][var4] != var2.final[var3][var4]) {
                     return false;
                  }
               }
            }

            return true;
         } else {
            return false;
         }
      } catch (ClassCastException var5) {
         return false;
      } catch (NullPointerException var6) {
         return false;
      }
   }

   /** @deprecated */
   public boolean null(GMatrix arg0, float arg1) {
      return thisx.null(arg0, (double)arg1);
   }

   public boolean null(GMatrix arg0, double arg1) {
      if (thisx.null == arg0.null && thisx.int == arg0.int) {
         for(int var4 = 0; var4 < thisx.null; ++var4) {
            for(int var5 = 0; var5 < thisx.int; ++var5) {
               double var6 = thisx.final[var4][var5] - arg0.final[var4][var5];
               if ((var6 < 0.0D ? -var6 : var6) > arg1) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public final double null() {
      int var2;
      if (thisx.null < thisx.int) {
         var2 = thisx.null;
      } else {
         var2 = thisx.int;
      }

      double var3 = 0.0D;

      for(int var1 = 0; var1 < var2; ++var1) {
         var3 += thisx.final[var1][var1];
      }

      return var3;
   }

   public final int null(GMatrix arg0, GMatrix arg1, GMatrix arg2) {
      if (thisx.int == arg2.int && thisx.int == arg2.null) {
         if (thisx.null == arg0.null && thisx.null == arg0.int) {
            if (thisx.null == arg1.null && thisx.int == arg1.int) {
               if (thisx.null == 2 && thisx.int == 2 && thisx.final[1][0] == 0.0D) {
                  arg0.true();
                  arg2.true();
                  if (thisx.final[0][1] == 0.0D) {
                     return 2;
                  } else {
                     double[] var4 = new double[1];
                     double[] var5 = new double[1];
                     double[] var6 = new double[1];
                     double[] var7 = new double[1];
                     double[] var8 = new double[]{thisx.final[0][0], thisx.final[1][1]};
                     null(thisx.final[0][0], thisx.final[0][1], thisx.final[1][1], var8, var4, var6, var5, var7, 0);
                     null(0, (GMatrix)arg0, (double[])var6, (double[])var4);
                     class(0, arg2, var7, var5);
                     return 2;
                  }
               } else {
                  return null(thisx, arg0, arg1, arg2);
               }
            } else {
               throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,)b")));
            }
         } else {
            throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("#f\u0005_\u0016B\u001c\u0019Q")));
         }
      } else {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,*l")));
      }
   }

   public final int null(GMatrix arg0, GVector arg1) {
      int var3 = arg0.null * arg0.int;
      double[] var4 = new double[var3];
      int[] var5 = new int[1];
      int[] var6 = new int[arg0.null];
      if (thisx.null != thisx.int) {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("#f\u0005_\u0016B\u001c\u001a]")));
      } else if (thisx.null != arg0.null) {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,)c")));
      } else if (thisx.int != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(LogisticGenerator.null("#f\u0005_\u0016B\u001c\u0019S")));
      } else if (arg0.null != arg1.null()) {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,)d")));
      } else {
         int var7;
         int var8;
         for(var7 = 0; var7 < thisx.null; ++var7) {
            for(var8 = 0; var8 < thisx.int; ++var8) {
               var4[var7 * thisx.int + var8] = thisx.final[var7][var8];
            }
         }

         if (!null(arg0.null, var4, var6, var5)) {
            throw new SingularMatrixException(VecMathI18N.null(LogisticGenerator.null("#f\u0005_\u0016B\u001c\u0019U")));
         } else {
            for(var7 = 0; var7 < thisx.null; ++var7) {
               for(var8 = 0; var8 < thisx.int; ++var8) {
                  arg0.final[var7][var8] = var4[var7 * thisx.int + var8];
               }
            }

            for(var7 = 0; var7 < arg0.null; ++var7) {
               arg1.final[var7] = (double)var6[var7];
            }

            return var5[0];
         }
      }
   }

   public final void null(double arg0) {
      int var5;
      if (thisx.null < thisx.int) {
         var5 = thisx.null;
      } else {
         var5 = thisx.int;
      }

      int var3;
      for(var3 = 0; var3 < thisx.null; ++var3) {
         for(int var4 = 0; var4 < thisx.int; ++var4) {
            thisx.final[var3][var4] = 0.0D;
         }
      }

      for(var3 = 0; var3 < var5; ++var3) {
         thisx.final[var3][var3] = arg0;
      }

   }

   public final void null(GMatrix arg0) {
      int var2 = arg0.null * arg0.int;
      double[] var3 = new double[var2];
      double[] var4 = new double[var2];
      int[] var5 = new int[arg0.null];
      int[] var6 = new int[1];
      if (arg0.null != arg0.int) {
         throw new MismatchedSizeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,)f")));
      } else {
         int var7;
         int var8;
         for(var7 = 0; var7 < thisx.null; ++var7) {
            for(var8 = 0; var8 < thisx.int; ++var8) {
               var3[var7 * thisx.int + var8] = arg0.final[var7][var8];
            }
         }

         if (!null(arg0.null, var3, var5, var6)) {
            throw new SingularMatrixException(VecMathI18N.null(LogisticGenerator.null("#f\u0005_\u0016B\u001c\u0019U")));
         } else {
            for(var7 = 0; var7 < var2; ++var7) {
               var4[var7] = 0.0D;
            }

            for(var7 = 0; var7 < thisx.int; ++var7) {
               var4[var7 + var7 * thisx.int] = 1.0D;
            }

            null(arg0.null, var3, var5, var4);

            for(var7 = 0; var7 < thisx.null; ++var7) {
               for(var8 = 0; var8 < thisx.int; ++var8) {
                  thisx.final[var7][var8] = var4[var7 * thisx.int + var8];
               }
            }

         }
      }
   }

   public static boolean null(int arg0, double[] arg1, int[] arg2, int[] arg3) {
      double[] var4 = new double[arg0];
      int var7 = 0;
      int var8 = 0;
      arg3[0] = 1;

      int var5;
      int var6;
      double var10;
      double var12;
      for(var5 = arg0; var5-- != 0; var4[var8++] = 1.0D / var10) {
         var10 = 0.0D;
         var6 = arg0;

         while(var6-- != 0) {
            var12 = arg1[var7++];
            var12 = Math.abs(var12);
            if (var12 > var10) {
               var10 = var12;
            }
         }

         if (var10 == 0.0D) {
            return false;
         }
      }

      byte var9 = 0;

      for(var6 = 0; var6 < arg0; ++var6) {
         int var15;
         int var16;
         int var17;
         int var18;
         double var19;
         for(var5 = 0; var5 < var6; ++var5) {
            var16 = var9 + arg0 * var5 + var6;
            var19 = arg1[var16];
            var15 = var5;
            var17 = var9 + arg0 * var5;

            for(var18 = var9 + var6; var15-- != 0; var18 += arg0) {
               var19 -= arg1[var17] * arg1[var18];
               ++var17;
            }

            arg1[var16] = var19;
         }

         var10 = 0.0D;
         int var14 = -1;

         for(var5 = var6; var5 < arg0; ++var5) {
            var16 = var9 + arg0 * var5 + var6;
            var19 = arg1[var16];
            var15 = var6;
            var17 = var9 + arg0 * var5;

            for(var18 = var9 + var6; var15-- != 0; var18 += arg0) {
               var19 -= arg1[var17] * arg1[var18];
               ++var17;
            }

            arg1[var16] = var19;
            if ((var12 = var4[var5] * Math.abs(var19)) >= var10) {
               var10 = var12;
               var14 = var5;
            }
         }

         if (var14 < 0) {
            throw new RuntimeException(VecMathI18N.null(InvalidItemException.null("\u0013V5o&r,)`")));
         }

         if (var6 != var14) {
            var15 = arg0;
            var17 = var9 + arg0 * var14;

            for(var18 = var9 + arg0 * var6; var15-- != 0; arg1[var18++] = var12) {
               var12 = arg1[var17];
               arg1[var17++] = arg1[var18];
            }

            var4[var14] = var4[var6];
            arg3[0] = -arg3[0];
         }

         arg2[var6] = var14;
         if (arg1[var9 + arg0 * var6 + var6] == 0.0D) {
            return false;
         }

         if (var6 != arg0 - 1) {
            var12 = 1.0D / arg1[var9 + arg0 * var6 + var6];
            var16 = var9 + arg0 * (var6 + 1) + var6;

            for(var5 = arg0 - 1 - var6; var5-- != 0; var16 += arg0) {
               arg1[var16] *= var12;
            }
         }
      }

      return true;
   }

   public static void null(int arg0, double[] arg1, int[] arg2, double[] arg3) {
      byte var9 = 0;

      for(int var8 = 0; var8 < arg0; ++var8) {
         int var10 = var8;
         int var5 = -1;

         int var4;
         int var7;
         int var11;
         for(var4 = 0; var4 < arg0; ++var4) {
            int var6 = arg2[var9 + var4];
            double var15 = arg3[var10 + arg0 * var6];
            arg3[var10 + arg0 * var6] = arg3[var10 + arg0 * var4];
            if (var5 >= 0) {
               var11 = var4 * arg0;

               for(var7 = var5; var7 <= var4 - 1; ++var7) {
                  var15 -= arg1[var11 + var7] * arg3[var10 + arg0 * var7];
               }
            } else if (var15 != 0.0D) {
               var5 = var4;
            }

            arg3[var10 + arg0 * var4] = var15;
         }

         for(var4 = 0; var4 < arg0; ++var4) {
            int var12 = arg0 - 1 - var4;
            var11 = arg0 * var12;
            double var13 = 0.0D;

            for(var7 = 1; var7 <= var4; ++var7) {
               var13 += arg1[var11 + arg0 - var7] * arg3[var10 + arg0 * (arg0 - var7)];
            }

            arg3[var10 + arg0 * var12] = (arg3[var10 + arg0 * var12] - var13) / arg1[var11 + var12];
         }
      }

   }

   public static int null(GMatrix arg0, GMatrix arg1, GMatrix arg2, GMatrix arg3) {
      GMatrix var27 = new GMatrix(arg0.null, arg0.int);
      GMatrix var28 = new GMatrix(arg0.null, arg0.int);
      GMatrix var29 = new GMatrix(arg0.null, arg0.int);
      GMatrix var30 = new GMatrix(arg0);
      int var24;
      int var25;
      if (var30.null >= var30.int) {
         var25 = var30.int;
         var24 = var30.int - 1;
      } else {
         var25 = var30.null;
         var24 = var30.null;
      }

      int var26;
      if (var30.null > var30.int) {
         var26 = var30.null;
      } else {
         var26 = var30.int;
      }

      double[] var31 = new double[var26];
      double[] var32 = new double[var25];
      double[] var33 = new double[var24];
      boolean var11 = false;
      arg1.true();
      arg3.true();
      int var7 = var30.null;
      int var8 = var30.int;

      int var4;
      for(int var9 = 0; var9 < var25; ++var9) {
         int var5;
         int var6;
         double[] var10000;
         double var18;
         int var10002;
         double var20;
         double var22;
         if (var7 > 1) {
            var18 = 0.0D;

            for(var4 = 0; var4 < var7; ++var4) {
               var18 += var30.final[var4 + var9][var9] * var30.final[var4 + var9][var9];
            }

            var18 = Math.sqrt(var18);
            if (var30.final[var9][var9] == 0.0D) {
               var31[0] = var18;
            } else {
               var31[0] = var30.final[var9][var9] + null(var18, var30.final[var9][var9]);
            }

            for(var4 = 1; var4 < var7; ++var4) {
               var31[var4] = var30.final[var9 + var4][var9];
            }

            var20 = 0.0D;

            for(var4 = 0; var4 < var7; ++var4) {
               var20 += var31[var4] * var31[var4];
            }

            var20 = 2.0D / var20;
            var5 = var9;

            while(true) {
               if (var5 >= var30.null) {
                  for(var4 = var9; var4 < var30.null; ++var4) {
                     var10002 = var28.final[var4][var4]++;
                  }

                  var22 = 0.0D;

                  for(var4 = var9; var4 < var30.null; ++var4) {
                     var22 += var28.final[var9][var4] * var30.final[var4][var9];
                  }

                  var30.final[var9][var9] = var22;

                  for(var5 = var9; var5 < var30.null; ++var5) {
                     for(var6 = var9 + 1; var6 < var30.int; ++var6) {
                        var27.final[var5][var6] = 0.0D;

                        for(var4 = var9; var4 < var30.int; ++var4) {
                           var10000 = var27.final[var5];
                           var10000[var6] += var28.final[var5][var4] * var30.final[var4][var6];
                        }
                     }
                  }

                  for(var5 = var9; var5 < var30.null; ++var5) {
                     for(var6 = var9 + 1; var6 < var30.int; ++var6) {
                        var30.final[var5][var6] = var27.final[var5][var6];
                     }
                  }

                  for(var5 = var9; var5 < var30.null; ++var5) {
                     for(var6 = 0; var6 < var30.int; ++var6) {
                        var27.final[var5][var6] = 0.0D;

                        for(var4 = var9; var4 < var30.int; ++var4) {
                           var10000 = var27.final[var5];
                           var10000[var6] += var28.final[var5][var4] * arg1.final[var4][var6];
                        }
                     }
                  }

                  for(var5 = var9; var5 < var30.null; ++var5) {
                     for(var6 = 0; var6 < var30.int; ++var6) {
                        arg1.final[var5][var6] = var27.final[var5][var6];
                     }
                  }

                  --var7;
                  break;
               }

               for(var6 = var9; var6 < var30.null; ++var6) {
                  var28.final[var5][var6] = -var20 * var31[var5 - var9] * var31[var6 - var9];
               }

               ++var5;
            }
         }

         if (var8 > 2) {
            var18 = 0.0D;

            for(var4 = 1; var4 < var8; ++var4) {
               var18 += var30.final[var9][var9 + var4] * var30.final[var9][var9 + var4];
            }

            var18 = Math.sqrt(var18);
            if (var30.final[var9][var9 + 1] == 0.0D) {
               var31[0] = var18;
            } else {
               var31[0] = var30.final[var9][var9 + 1] + null(var18, var30.final[var9][var9 + 1]);
            }

            for(var4 = 1; var4 < var8 - 1; ++var4) {
               var31[var4] = var30.final[var9][var9 + var4 + 1];
            }

            var20 = 0.0D;

            for(var4 = 0; var4 < var8 - 1; ++var4) {
               var20 += var31[var4] * var31[var4];
            }

            var20 = 2.0D / var20;

            for(var5 = var9 + 1; var5 < var8; ++var5) {
               for(var6 = var9 + 1; var6 < var30.int; ++var6) {
                  var29.final[var5][var6] = -var20 * var31[var5 - var9 - 1] * var31[var6 - var9 - 1];
               }
            }

            for(var4 = var9 + 1; var4 < var30.int; ++var4) {
               var10002 = var29.final[var4][var4]++;
            }

            var22 = 0.0D;

            for(var4 = var9; var4 < var30.int; ++var4) {
               var22 += var29.final[var4][var9 + 1] * var30.final[var9][var4];
            }

            var30.final[var9][var9 + 1] = var22;

            for(var5 = var9 + 1; var5 < var30.null; ++var5) {
               for(var6 = var9 + 1; var6 < var30.int; ++var6) {
                  var27.final[var5][var6] = 0.0D;

                  for(var4 = var9 + 1; var4 < var30.int; ++var4) {
                     var10000 = var27.final[var5];
                     var10000[var6] += var29.final[var4][var6] * var30.final[var5][var4];
                  }
               }
            }

            for(var5 = var9 + 1; var5 < var30.null; ++var5) {
               for(var6 = var9 + 1; var6 < var30.int; ++var6) {
                  var30.final[var5][var6] = var27.final[var5][var6];
               }
            }

            for(var5 = 0; var5 < var30.null; ++var5) {
               for(var6 = var9 + 1; var6 < var30.int; ++var6) {
                  var27.final[var5][var6] = 0.0D;

                  for(var4 = var9 + 1; var4 < var30.int; ++var4) {
                     var10000 = var27.final[var5];
                     var10000[var6] += var29.final[var4][var6] * arg3.final[var5][var4];
                  }
               }
            }

            for(var5 = 0; var5 < var30.null; ++var5) {
               for(var6 = var9 + 1; var6 < var30.int; ++var6) {
                  arg3.final[var5][var6] = var27.final[var5][var6];
               }
            }

            --var8;
         }
      }

      for(var4 = 0; var4 < var25; ++var4) {
         var32[var4] = var30.final[var4][var4];
      }

      for(var4 = 0; var4 < var24; ++var4) {
         var33[var4] = var30.final[var4][var4 + 1];
      }

      if (var30.null == 2 && var30.int == 2) {
         double[] var34 = new double[1];
         double[] var35 = new double[1];
         double[] var36 = new double[1];
         double[] var37 = new double[1];
         null(var32[0], var33[0], var32[1], var32, var36, var34, var37, var35, 0);
         null(0, (GMatrix)arg1, (double[])var34, (double[])var36);
         class(0, arg3, var35, var37);
         return 2;
      } else {
         null(0, var33.length - 1, var32, var33, arg1, arg3);
         int var38 = var32.length;
         return var38;
      }
   }

   public static void null(int arg0, int arg1, double[] arg2, double[] arg3, GMatrix arg4, GMatrix arg5) {
      double[] var24 = new double[1];
      double[] var25 = new double[1];
      double[] var26 = new double[1];
      double[] var27 = new double[1];
      new GMatrix(arg4.int, arg5.null);
      boolean var29 = true;
      double var30 = 4.89E-15D;
      double var32 = 1.0D;
      double var34 = -1.0D;
      boolean var11 = false;
      double var20 = 0.0D;
      double var22 = 0.0D;

      for(int var8 = 0; var8 < 2 && !var11; ++var8) {
         int var6;
         for(var6 = arg0; var6 <= arg1; ++var6) {
            if (var6 == arg0) {
               int var10;
               if (arg3.length == arg2.length) {
                  var10 = arg1;
               } else {
                  var10 = arg1 + 1;
               }

               double var12 = null(arg2[var10 - 1], arg3[arg1], arg2[var10]);
               var20 = (Math.abs(arg2[var6]) - var12) * (null(var32, arg2[var6]) + var12 / arg2[var6]);
               var22 = arg3[var6];
            }

            double var14 = null(var20, var22, var27, var25);
            if (var6 != arg0) {
               arg3[var6 - 1] = var14;
            }

            var20 = var25[0] * arg2[var6] + var27[0] * arg3[var6];
            arg3[var6] = var25[0] * arg3[var6] - var27[0] * arg2[var6];
            var22 = var27[0] * arg2[var6 + 1];
            arg2[var6 + 1] = var25[0] * arg2[var6 + 1];
            class(var6, arg5, var25, var27);
            var14 = null(var20, var22, var26, var24);
            arg2[var6] = var14;
            var20 = var24[0] * arg3[var6] + var26[0] * arg2[var6 + 1];
            arg2[var6 + 1] = var24[0] * arg2[var6 + 1] - var26[0] * arg3[var6];
            if (var6 < arg1) {
               var22 = var26[0] * arg3[var6 + 1];
               arg3[var6 + 1] = var24[0] * arg3[var6 + 1];
            }

            null(var6, arg4, var24, var26);
         }

         if (arg2.length == arg3.length) {
            null(var20, var22, var27, var25);
            var20 = var25[0] * arg2[var6] + var27[0] * arg3[var6];
            arg3[var6] = var25[0] * arg3[var6] - var27[0] * arg2[var6];
            arg2[var6 + 1] = var25[0] * arg2[var6 + 1];
            class(var6, arg5, var25, var27);
         }

         while(arg1 - arg0 > 1 && Math.abs(arg3[arg1]) < 4.89E-15D) {
            --arg1;
         }

         for(int var9 = arg1 - 2; var9 > arg0; --var9) {
            if (Math.abs(arg3[var9]) < 4.89E-15D) {
               null(var9 + 1, arg1, arg2, arg3, arg4, arg5);

               for(arg1 = var9 - 1; arg1 - arg0 > 1 && Math.abs(arg3[arg1]) < 4.89E-15D; --arg1) {
               }
            }
         }

         if (arg1 - arg0 <= 1 && Math.abs(arg3[arg0 + 1]) < 4.89E-15D) {
            var11 = true;
         }
      }

      if (Math.abs(arg3[1]) < 4.89E-15D) {
         null(arg2[arg0], arg3[arg0], arg2[arg0 + 1], arg2, var26, var24, var27, var25, 0);
         arg3[arg0] = 0.0D;
         arg3[arg0 + 1] = 0.0D;
      }

      null(arg0, arg4, var24, var26);
      class(arg0, arg5, var25, var27);
   }

   private static void null(double[] arg0, double[] arg1) {
      System.out.println(LogisticGenerator.null("!\u0017\u000bY") + arg0[0] + " " + arg0[1] + " " + arg0[2]);
      System.out.println(InvalidItemException.null("1;i") + arg1[0] + " " + arg1[1]);
   }

   private static void class(int arg0, GMatrix arg1, double[] arg2, double[] arg3) {
      for(int var4 = 0; var4 < arg1.null; ++var4) {
         double var5 = arg1.final[var4][arg0];
         arg1.final[var4][arg0] = arg2[0] * var5 + arg3[0] * arg1.final[var4][arg0 + 1];
         arg1.final[var4][arg0 + 1] = -arg3[0] * var5 + arg2[0] * arg1.final[var4][arg0 + 1];
      }

   }

   private static void class(double[] arg0, double[] arg1, int arg2, GMatrix arg3) {
      double[] var10 = new double[1];
      double[] var11 = new double[1];
      GMatrix var13 = new GMatrix(arg3.null, arg3.int);
      GMatrix var14 = new GMatrix(arg3.null, arg3.int);
      double var4 = arg1[arg2];
      double var6 = arg0[arg2];

      int var12;
      for(var12 = arg2; var12 > 0; --var12) {
         double var8 = null(var4, var6, var11, var10);
         var4 = -arg1[var12 - 1] * var11[0];
         var6 = arg0[var12 - 1];
         arg0[var12] = var8;
         arg1[var12 - 1] *= var10[0];
         class(var12, arg2 + 1, arg3, var10, var11, var13, var14);
      }

      arg0[var12 + 1] = null(var4, var6, var11, var10);
      class(var12, arg2 + 1, arg3, var10, var11, var13, var14);
   }

   private static void null(double[] arg0, double[] arg1, int arg2, GMatrix arg3) {
      double[] var10 = new double[1];
      double[] var11 = new double[1];
      GMatrix var13 = new GMatrix(arg3.null, arg3.int);
      GMatrix var14 = new GMatrix(arg3.null, arg3.int);
      double var6 = arg1[arg2];
      double var4 = arg0[arg2 + 1];

      int var12;
      for(var12 = arg2; var12 < arg3.int - 2; ++var12) {
         double var8 = null(var4, var6, var11, var10);
         var6 = -arg1[var12 + 1] * var11[0];
         var4 = arg0[var12 + 2];
         arg0[var12 + 1] = var8;
         arg1[var12 + 1] *= var10[0];
         null(arg2, var12 + 1, arg3, var10, var11, var13, var14);
      }

      arg0[var12 + 1] = null(var4, var6, var11, var10);
      null(arg2, var12 + 1, arg3, var10, var11, var13, var14);
   }

   private static void class(int arg0, int arg1, GMatrix arg2, double[] arg3, double[] arg4, GMatrix arg5, GMatrix arg6) {
      for(int var7 = 0; var7 < arg2.null; ++var7) {
         double var8 = arg2.final[var7][arg0];
         arg2.final[var7][arg0] = arg3[0] * var8 - arg4[0] * arg2.final[var7][arg1];
         arg2.final[var7][arg1] = arg4[0] * var8 + arg3[0] * arg2.final[var7][arg1];
      }

      System.out.println(LogisticGenerator.null("\u0010D\u0014YD\u000bD\u000bY") + arg0);
      System.out.println(InvalidItemException.null("6t o;v&;i") + arg1);
      System.out.println(LogisticGenerator.null("H\u000bX\u0016\u000bY") + arg3[0]);
      System.out.println(InvalidItemException.null("h=u&;i") + arg4[0]);
      System.out.println(LogisticGenerator.null("!\t\u000bY"));
      class(arg6);
      System.out.println(InvalidItemException.null("\u0011\";i"));
      class(arg5);
      arg6.do(arg6, arg5);
      System.out.println(LogisticGenerator.null("!\u0010\u0001\t\u000bY"));
      class(arg6);
   }

   private static void null(int arg0, int arg1, GMatrix arg2, double[] arg3, double[] arg4, GMatrix arg5, GMatrix arg6) {
      for(int var7 = 0; var7 < arg2.int; ++var7) {
         double var8 = arg2.final[arg0][var7];
         arg2.final[arg0][var7] = arg3[0] * var8 - arg4[0] * arg2.final[arg1][var7];
         arg2.final[arg1][var7] = arg4[0] * var8 + arg3[0] * arg2.final[arg1][var7];
      }

      System.out.println(InvalidItemException.null("^vi"));
      class(arg6);
      System.out.println(LogisticGenerator.null("n^Y"));
      class(arg5);
      arg6.do(arg5, arg6);
      System.out.println(InvalidItemException.null("^o~vi"));
      class(arg6);
   }

   private static void null(int arg0, GMatrix arg1, double[] arg2, double[] arg3) {
      for(int var4 = 0; var4 < arg1.int; ++var4) {
         double var5 = arg1.final[arg0][var4];
         arg1.final[arg0][var4] = arg2[0] * var5 + arg3[0] * arg1.final[arg0 + 1][var4];
         arg1.final[arg0 + 1][var4] = -arg3[0] * var5 + arg2[0] * arg1.final[arg0 + 1][var4];
      }

   }

   private static void null(GMatrix arg0, GMatrix arg1, GMatrix arg2) {
      GMatrix var3 = new GMatrix(arg0.int, arg0.null);
      var3.do(arg1, var3);
      var3.do(var3, arg2);
      System.out.println(LogisticGenerator.null("n\u000b\t\u000bY\u000bn") + null(var3));
   }

   private static String null(GMatrix arg0) {
      StringBuffer var1 = new StringBuffer(arg0.null * arg0.int * 8);

      for(int var2 = 0; var2 < arg0.null; ++var2) {
         for(int var3 = 0; var3 < arg0.int; ++var3) {
            if (Math.abs(arg0.final[var2][var3]) < 1.0E-9D) {
               var1.append(InvalidItemException.null("d5d+d+t"));
            } else {
               var1.append(arg0.final[var2][var3]).append(" ");
            }
         }

         var1.append("\n");
      }

      return var1.toString();
   }

   private static void null(double[] arg0, double[] arg1, GMatrix arg2, GMatrix arg3) {
      GMatrix var5 = new GMatrix(arg2.int, arg3.null);
      System.out.println(LogisticGenerator.null("\u000bnXD\u0016D"));

      int var4;
      for(var4 = 0; var4 < arg0.length; ++var4) {
         System.out.println(" " + arg0[var4]);
      }

      System.out.println(InvalidItemException.null(";^~t&t"));

      for(var4 = 0; var4 < arg1.length; ++var4) {
         System.out.println(" " + arg1[var4]);
      }

      System.out.println(LogisticGenerator.null("\u000bn^D\u000bY\u000bn") + arg2.toString());
      System.out.println(InvalidItemException.null(";^mt;i;^") + arg3.toString());
      var5.true();

      for(var4 = 0; var4 < arg0.length; ++var4) {
         var5.final[var4][var4] = arg0[var4];
      }

      for(var4 = 0; var4 < arg1.length; ++var4) {
         var5.final[var4][var4 + 1] = arg1[var4];
      }

      System.out.println(LogisticGenerator.null("\u000bnFD\u000bY\u000bn") + var5.toString());
      var5.null(arg2, var5);
      var5.class(var5, arg3);
      System.out.println(InvalidItemException.null("t\u0011tnzo&z:h$t'~~v~mzo&z:h$t'~t;i;^") + var5.toString());
   }

   public static double long(double arg0, double arg1) {
      return arg0 > arg1 ? arg0 : arg1;
   }

   public static double class(double arg0, double arg1) {
      return arg0 < arg1 ? arg0 : arg1;
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
            if (var38 / var40 < 1.0E-10D) {
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
               if (var38 / var40 < 1.0E-10D) {
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

   public static double null(double arg0, double arg1, double[] arg2, double[] arg3) {
      double var25 = 2.002083095183101E-146D;
      double var27 = 4.9947976805055876E145D;
      double var11;
      double var13;
      double var23;
      if (arg1 == 0.0D) {
         var11 = 1.0D;
         var13 = 0.0D;
         var23 = arg0;
      } else if (arg0 == 0.0D) {
         var11 = 0.0D;
         var13 = 1.0D;
         var23 = arg1;
      } else {
         double var19 = arg0;
         double var21 = arg1;
         double var16 = long(Math.abs(arg0), Math.abs(arg1));
         int var15;
         int var18;
         if (var16 >= 4.9947976805055876E145D) {
            for(var18 = 0; var16 >= 4.9947976805055876E145D; var16 = long(Math.abs(var19), Math.abs(var21))) {
               ++var18;
               var19 *= 2.002083095183101E-146D;
               var21 *= 2.002083095183101E-146D;
            }

            var23 = Math.sqrt(var19 * var19 + var21 * var21);
            var11 = var19 / var23;
            var13 = var21 / var23;

            for(var15 = 1; var15 <= var18; ++var15) {
               var23 *= 4.9947976805055876E145D;
            }
         } else if (!(var16 <= 2.002083095183101E-146D)) {
            var23 = Math.sqrt(arg0 * arg0 + arg1 * arg1);
            var11 = arg0 / var23;
            var13 = arg1 / var23;
         } else {
            for(var18 = 0; var16 <= 2.002083095183101E-146D; var16 = long(Math.abs(var19), Math.abs(var21))) {
               ++var18;
               var19 *= 4.9947976805055876E145D;
               var21 *= 4.9947976805055876E145D;
            }

            var23 = Math.sqrt(var19 * var19 + var21 * var21);
            var11 = var19 / var23;
            var13 = var21 / var23;

            for(var15 = 1; var15 <= var18; ++var15) {
               var23 *= 2.002083095183101E-146D;
            }
         }

         if (Math.abs(arg0) > Math.abs(arg1) && var11 < 0.0D) {
            var11 = -var11;
            var13 = -var13;
            var23 = -var23;
         }
      }

      arg2[0] = var13;
      arg3[0] = var11;
      return var23;
   }

   public static double null(double arg0, double arg1) {
      double var4 = arg0 >= 0.0D ? arg0 : -arg0;
      return arg1 >= 0.0D ? var4 : -var4;
   }

   public Object clone() {
      GMatrix var1 = null;

      try {
         var1 = (GMatrix)super.clone();
      } catch (CloneNotSupportedException var4) {
         throw new InternalError();
      }

      var1.final = new double[thisx.null][thisx.int];

      for(int var2 = 0; var2 < thisx.null; ++var2) {
         for(int var3 = 0; var3 < thisx.int; ++var3) {
            var1.final[var2][var3] = thisx.final[var2][var3];
         }
      }

      return var1;
   }
}
