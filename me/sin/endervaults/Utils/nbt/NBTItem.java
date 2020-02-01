/*    */ package me.sin.endervaults.Utils.nbt;
/*    */ 
/*    */ import org.bukkit.inventory.ItemStack;

/*    */
/*    */ public class NBTItem extends NBTCompound
/*    */ {
/*    */   private ItemStack bukkitItem;
/*    */   
/*    */   public NBTItem(ItemStack item) {
/* 10 */     super(null, null);
/* 11 */     this.bukkitItem = item.clone();
/*    */   }
/*    */   
/*    */   protected Object getCompound() {
/* 15 */     return NBTReflectionUtil.getItemRootNBTTagCompound(NBTReflectionUtil.getNMSItemStack(this.bukkitItem));
/*    */   }
/*    */   
/*    */   protected void setCompound(Object tag) {
/* 19 */     this.bukkitItem = NBTReflectionUtil.getBukkitItemStack(NBTReflectionUtil.setNBTTag(tag, NBTReflectionUtil.getNMSItemStack(this.bukkitItem)));
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 23 */     return this.bukkitItem;
/*    */   }
/*    */   
/*    */   protected void setItem(ItemStack item)
/*    */   {
/* 28 */     this.bukkitItem = item;
/*    */   }
/*    */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\NBTItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */