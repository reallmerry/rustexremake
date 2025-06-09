package javax.vecmath;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import net.daporkchop.lib.common.ref.ThreadRef;
import net.daporkchop.lib.primitive.lambda.LongCharLongConsumer;

public class VecMathI18N {
   public static String null(String arg0) {
      String var1;
      try {
         var1 = ResourceBundle.getBundle(LongCharLongConsumer.null("\u001d\r\u0001\r\u000fB\u0001\t\u0014\u0001\u0016\u0018\u001fB2\u0014\u0014\t\u0007\u0018\u001e\u0003\u0019?\u0003\u001e\u001e\u0002\u0010\u001f")).getString(arg0);
      } catch (MissingResourceException var3) {
         System.err.println(ThreadRef.null("\u0013P&x$A-|t\r\u000b\u000fep7G*GeY*Z.\\+Re@5\u000fe") + arg0);
         var1 = arg0;
      }

      return var1;
   }
}
