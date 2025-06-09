package javax.vecmath;

public class MismatchedSizeException extends RuntimeException {
   public MismatchedSizeException() {
   }

   public MismatchedSizeException(String arg0) {
      super(arg0);
   }

   public static String null(String arg0) {
      int var10000 = 4 << 4 ^ 3 << 1;
      int var10001 = 5 << 4 ^ 2 ^ 5;
      int var10002 = (3 ^ 5) << 4 ^ (2 ^ 5) << 1;
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
