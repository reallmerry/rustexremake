package optifine.xdelta;

import net.daporkchop.lib.primitive.lambda.IntBoolIntConsumer;

public enum SeekableSource {
   new(""),
   const(IntBoolIntConsumer.null("\u00023\u0010?\u001e,")),
   false(IntBoolIntConsumer.null("\u00169\u0004=\u0017>\u0003(")),
   null(IntBoolIntConsumer.null("\u0015>\u0017>\u0003)\u0014?")),
   int(IntBoolIntConsumer.null("8\u001e6\u00014\u00022\u0005>"));

   private String final;

   private SeekableSource(String arg2) {
      thisx.final = arg2;
   }

   public String null() {
      return thisx.final;
   }
}
