/*    */ package me.sin.endervaults.Utils.nbt;
/*    */ 
/*    */ import org.bukkit.block.BlockState;

/*    */
/*    */ public class NBTTileEntity extends NBTCompound
/*    */ {
/*    */   private final BlockState tile;
/*    */   
/*    */   public NBTTileEntity(BlockState tile) {
/* 10 */     super(null, null);
/* 11 */     this.tile = tile;
/*    */   }
/*    */   
/*    */   protected Object getCompound() {
/* 15 */     return NBTReflectionUtil.getTileEntityNBTTagCompound(this.tile);
/*    */   }
/*    */   
/*    */   protected void setCompound(Object tag) {
/* 19 */     NBTReflectionUtil.setTileEntityNBTTagCompound(this.tile, tag);
/*    */   }
/*    */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\NBTTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */