package ibxm;

import com.alibaba.druid.sql.dialect.mysql.ast.statement.MysqlShowStcStatement;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.gui.chat.IChatListener;
import net.optifine.shaders.uniform.ShaderParameterIndexed;

public class Envelope implements ClientBrandRetriever {
   public MysqlShowStcStatement this;

   public Envelope(MysqlShowStcStatement arg0) {
      thisx.this = arg0;
   }

   public void null(byte[] arg0) {
      thisx.this.null(arg0);
   }

   public byte[] null() {
      return thisx.this.null();
   }

   public void null(IChatListener arg0) {
      thisx.this.null(arg0.null().null().final);
   }

   public IChatListener null() throws IOException {
      return ShaderParameterIndexed.null(thisx.this.null());
   }

   public void null(String arg0) {
      thisx.this.null(arg0);
   }

   public String null(int arg0) {
      return thisx.this.null(arg0);
   }

   public void class(int arg0) {
      thisx.this.writeInt(arg0);
   }

   public int class() {
      return thisx.this.readInt();
   }

   public void null(float arg0) {
      thisx.this.writeFloat(arg0);
   }

   public float null() {
      return thisx.this.readFloat();
   }

   public void null(double arg0) {
      thisx.this.writeDouble(arg0);
   }

   public double null() {
      return thisx.this.readDouble();
   }

   public void null(boolean arg0) {
      thisx.this.writeBoolean(arg0);
   }

   public boolean null() {
      return thisx.this.readBoolean();
   }

   public ClientBrandRetriever null() {
      return new Envelope(new MysqlShowStcStatement(thisx.this.duplicate()));
   }

   public void null(ClientBrandRetriever arg0) {
      thisx.this = ((Envelope)arg0).this;
   }

   public short null() {
      return thisx.this.readShort();
   }

   public void null(short arg0) {
      thisx.this.writeShort(arg0);
   }

   public byte null() {
      return thisx.this.readByte();
   }

   public void null(byte arg0) {
      thisx.this.writeByte(arg0);
   }

   public void null(long arg0) {
      thisx.this.writeLong(arg0);
   }

   public long null() {
      return thisx.this.readLong();
   }

   public ByteBuf null(byte[] arg0) {
      thisx.this.readBytes(arg0);
      return null;
   }

   public int long() {
      return thisx.this.readableBytes();
   }

   public void null() {
      thisx.this.release();
   }

   public void null(int arg0) {
      thisx.this.null(arg0);
   }

   public int null() {
      return thisx.this.null();
   }
}
