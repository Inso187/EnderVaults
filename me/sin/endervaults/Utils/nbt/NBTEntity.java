/*    */ package me.sin.endervaults.Utils.nbt;
/*    */ 
/*    */ import org.bukkit.entity.Entity;

/*    */
/*    */ public class NBTEntity extends NBTCompound
/*    */ {
/*    */   private final Entity ent;
/*    */   
/*    */   public NBTEntity(Entity entity) {
/* 10 */     super(null, null);
/* 11 */     this.ent = entity;
/*    */   }
/*    */   
/*    */   protected Object getCompound() {
/* 15 */     return NBTReflectionUtil.getEntityNBTTagCompound(NBTReflectionUtil.getNMSEntity(this.ent));
/*    */   }
/*    */   
/*    */   protected void setCompound(Object tag) {
/* 19 */     NBTReflectionUtil.setEntityNBTTag(tag, NBTReflectionUtil.getNMSEntity(this.ent));
/*    */   }
/*    */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\NBTEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */