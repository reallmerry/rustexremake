package javax.vecmath;

public class VecMathUtil {
   private VecMathUtil() {
   }

   public static final long null(long arg0, long arg1) {
      arg0 *= 31L;
      return arg0 + arg1;
   }

   public static final long null(long arg0, float arg1) {
      arg0 *= 31L;
      return arg1 == 0.0F ? arg0 : arg0 + (long)Float.floatToIntBits(arg1);
   }

   public static final long null(long arg0, double arg1) {
      arg0 *= 31L;
      return arg1 == 0.0D ? arg0 : arg0 + Double.doubleToLongBits(arg1);
   }

   public static final int null(long arg0) {
      return (int)(arg0 ^ arg0 >> 32);
   }
}
