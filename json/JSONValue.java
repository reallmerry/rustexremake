package optifine.json;

import com.alibaba.druid.sql.dialect.mysql.ast.statement.MysqlShowStcStatement;
import java.io.IOException;
import net.daporkchop.fp2.compat.vanilla.biome.layer.c.NativeFastLayerIsland;
import net.daporkchop.lib.primitive.lambda.ByteCharShortFunction;
import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.world.WorldServerDemo;
import net.minecraftforge.fml.common.network.internal.OpenGuiHandler;

public class JSONValue implements ModelPolarBear<WorldServerDemo> {
   public int long;
   public int new;
   public int const;
   public int false;
   public byte null;
   public byte int;
   public boolean final;
   public boolean this;

   public JSONValue() {
   }

   public JSONValue(int arg0) {
      thisx.long = arg0;
   }

   public void class(MysqlShowStcStatement arg0) throws IOException {
      thisx.long = arg0.null();
   }

   public void null(MysqlShowStcStatement arg0) throws IOException {
      arg0.null(thisx.long);
   }

   public void null(WorldServerDemo arg0) {
      arg0.null(thisx);
   }

   public String toString() {
      return OpenGuiHandler.null("\u0017\u0005&\u0002&\u0012\r") + super.toString();
   }

   public NativeFastLayerIsland null(ByteCharShortFunction arg0) {
      return arg0.null(thisx.long);
   }

   public int long() {
      return thisx.new;
   }

   public int class() {
      return thisx.const;
   }

   public int null() {
      return thisx.false;
   }

   public byte class() {
      return thisx.null;
   }

   public byte null() {
      return thisx.int;
   }

   public boolean class() {
      return thisx.this;
   }

   public boolean null() {
      return thisx.final;
   }

   public static class a extends JSONValue {
      public a() {
         this.this = true;
      }

      public a(int arg0, long arg1, long arg2, long arg3, byte arg4, byte arg5, boolean arg6) {
         super(arg0);
         this.new = (int)arg1;
         this.const = (int)arg2;
         this.false = (int)arg3;
         this.null = arg4;
         this.int = arg5;
         this.final = arg6;
         this.this = true;
      }

      public void class(MysqlShowStcStatement arg0) throws IOException {
         super.class(arg0);
         this.new = arg0.readShort();
         this.const = arg0.readShort();
         this.false = arg0.readShort();
         this.null = arg0.readByte();
         this.int = arg0.readByte();
         this.final = arg0.readBoolean();
      }

      public void null(MysqlShowStcStatement arg0) throws IOException {
         super.null(arg0);
         arg0.writeShort(this.new);
         arg0.writeShort(this.const);
         arg0.writeShort(this.false);
         arg0.writeByte(this.null);
         arg0.writeByte(this.int);
         arg0.writeBoolean(this.final);
      }
   }

   public static class b extends JSONValue {
      public b() {
         this.this = true;
      }

      public b(int arg0, byte arg1, byte arg2, boolean arg3) {
         super(arg0);
         this.null = arg1;
         this.int = arg2;
         this.this = true;
         this.final = arg3;
      }

      public void class(MysqlShowStcStatement arg0) throws IOException {
         super.class(arg0);
         this.null = arg0.readByte();
         this.int = arg0.readByte();
         this.final = arg0.readBoolean();
      }

      public void null(MysqlShowStcStatement arg0) throws IOException {
         super.null(arg0);
         arg0.writeByte(this.null);
         arg0.writeByte(this.int);
         arg0.writeBoolean(this.final);
      }
   }

   public static class c extends JSONValue {
      public c() {
      }

      public c(int arg0, long arg1, long arg2, long arg3, boolean arg4) {
         super(arg0);
         this.new = (int)arg1;
         this.const = (int)arg2;
         this.false = (int)arg3;
         this.final = arg4;
      }

      public void class(MysqlShowStcStatement arg0) throws IOException {
         super.class(arg0);
         this.new = arg0.readShort();
         this.const = arg0.readShort();
         this.false = arg0.readShort();
         this.final = arg0.readBoolean();
      }

      public void null(MysqlShowStcStatement arg0) throws IOException {
         super.null(arg0);
         arg0.writeShort(this.new);
         arg0.writeShort(this.const);
         arg0.writeShort(this.false);
         arg0.writeBoolean(this.final);
      }
   }
}
