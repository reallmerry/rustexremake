package optifine;

import java.util.LinkedHashMap;
import java.util.Map;
import net.daporkchop.lib.primitive.lambda.CharLongDoubleConsumer;

public final class Differ {
   private final Map<Object, Map<String, Integer>> indexMappings = new LinkedHashMap();

   private Map<String, Integer> get(Object arg0) {
      Map var2 = (Map)this.indexMappings.computeIfAbsent(arg0, (arg0x) -> {
         return new LinkedHashMap();
      });
      return var2;
   }

   public void generate(Object arg0, Map<String, ?> arg1) {
      if (arg1 != null) {
         this.get(arg0).putAll(CharLongDoubleConsumer.computeIndexMapping(arg1));
      }

   }

   public Integer getIndex(String arg0, String arg1) {
      return arg1 == null ? null : (Integer)this.get(arg0).get(arg1);
   }
}
