package com.github.ustc_zzzz.fmltutor.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.github.ustc_zzzz.fmltutor.achievement.AchievementLoader;
import com.github.ustc_zzzz.fmltutor.block.BlockLoader;
import com.github.ustc_zzzz.fmltutor.client.KeyLoader;
import com.github.ustc_zzzz.fmltutor.enchantment.EnchantmentLoader;
import com.github.ustc_zzzz.fmltutor.potion.PotionLoader;

/**
 * 加载事件的辅助类
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class EventLoader {
	public static final EventBus EVENT_BUS = new EventBus();
	
	public EventLoader() {
		MinecraftForge.EVENT_BUS.register(this);
		EventLoader.EVENT_BUS.register(this);
	}

	/**
	 * ChatComponentTranslation这个类的作用，就是把语言文件对应的信息翻译掉，翻译的本质方式是String类的format方法。
	 * 首先Minecraft会从对应的语言文件中获取ChatComponentTranslation类的第一个参数对应的内容，然后调用String类的format方法，使用后面的参数替换前面的格式符。
	 * 比如%s（字符串），%d（整数）。
	 * @param event
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (KeyLoader.showTime.isPressed()) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			World world = Minecraft.getMinecraft().theWorld;
			player.addChatMessage(new ChatComponentTranslation(
					"chat.fmltutor.time", world.getTotalWorldTime()));
		}
	}
	
	/**
	 * 捡起Item会触发 的事件
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerItemPickup(PlayerEvent.ItemPickupEvent event) {
		if (event.player.isServerWorld()) {
			String info = String.format("%s picks up: %s",
					event.player.getName(), event.pickedUp.getEntityItem());
			ConfigLoader.logger().info(info);
		}
	}

	/**
	 * 使用物品时，物品被激活时（即游戏中点右键时触发的方法）
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (!event.world.isRemote) {
			String info = String.format("%s interacts with: %s",
					event.entityPlayer.getName(), event.pos);
			ConfigLoader.logger().info(info);
		}
	}
	
	/**
	 * 当地上的草方块被右击时触发，将草方块附近更换为即将爆炸的TNT
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerClickGrassBlock(PlayerRightClickGrassBlockEvent event) {
		if (!event.world.isRemote) {
			BlockPos pos = event.pos;
			Entity tnt = new EntityTNTPrimed(event.world, pos.getX() + 0.5,
					pos.getY() + 0.5, pos.getZ() + 0.5, null);
			event.world.spawnEntityInWorld(tnt);
			//使成就可被获得
			event.entityPlayer.triggerAchievement(AchievementLoader.explosionFromGrassBlock);
		}
	}
	
	/**
	 * 定义一个新的事件类型
	 * 玩家右击草方块的事件，此事件是可被取消的
	 * 
	 * @author ZhuFeng 
	 * @date 2018年3月10日
	 */
	@Cancelable
	public static class PlayerRightClickGrassBlockEvent extends
			net.minecraftforge.event.entity.player.PlayerEvent {
		public final BlockPos pos;
		public final World world;

		public PlayerRightClickGrassBlockEvent(EntityPlayer player,
				BlockPos pos, World world) {
			super(player);
			this.pos = pos;
			this.world = world;
		}
	}
	
	/**
	 * 实体被激活时（即游戏中点右键时触发的事件）
	 * 
	 * 此事件含义是：当与实体互动时如果实体是猪，并且手持种子或者小麦时触发，猪会有8个爆炸伤害
	 * @param event
	 */
	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event) {
		EntityPlayer player = event.entityPlayer;
		if(player.isServerWorld() && event.target instanceof EntityPig) {
			EntityPig pig = (EntityPig) event.target;
			ItemStack stack = player.getCurrentEquippedItem();
			if(stack != null && (stack.getItem() == Items.wheat || stack.getItem() == Items.wheat_seeds)) {
				player.attackEntityFrom(new DamageSource("byPig").setDifficultyScaled().setExplosion(), 8.0F);
				player.worldObj.createExplosion(pig, pig.posX, pig.posY, pig.posZ, 2.0F, false);
				pig.setDead();
			}
		}
	}
	
	/**
	 * 方块被挖掘后即将掉落物品的事件
	 * 
	 * 在玩家手持存在“火焰灼烧”附魔的工具时，将其换成被灼烧过的物品掉落。
	 * @param event
	 */
	@SubscribeEvent
	public void onBlockHarvestDrops(BlockEvent.HarvestDropsEvent event) {
		if (!event.world.isRemote && event.harvester != null) {
			ItemStack itemStack = event.harvester.getHeldItem();
			if (EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.fireBurn.effectId, itemStack) > 0 	//是火焰灼烧附魔
					&& itemStack.getItem() != Items.shears) {												//工具不是剪刀
				for (int i = 0; i < event.drops.size(); ++i) {
					ItemStack stack = event.drops.get(i);
					ItemStack newStack = FurnaceRecipes.instance().getSmeltingResult(stack); 				//获取灼烧后的物品
					if (newStack != null) {
						newStack = newStack.copy();
						newStack.stackSize = stack.stackSize;
						event.drops.set(i, newStack);
					} else if (stack != null) {
						Block block = Block.getBlockFromItem(stack.getItem());
						boolean b = (block == null);
						if (!b && (block.isFlammable(event.world, event.pos, EnumFacing.DOWN)
								|| block.isFlammable(event.world, event.pos, EnumFacing.EAST)
								|| block.isFlammable(event.world, event.pos, EnumFacing.NORTH)
								|| block.isFlammable(event.world, event.pos, EnumFacing.SOUTH)
								|| block.isFlammable(event.world, event.pos, EnumFacing.UP)
								|| block.isFlammable(event.world, event.pos, EnumFacing.WEST))) {
							event.drops.remove(i);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 当实体收到伤害时触发的事件
	 * 
	 * 当该药水效果等级为一时，摔落效果带来的伤害减半，如果等级超过一，伤害置零。
	 * PotionEffect类和Potion类的区别就是PotionEffect是一个特殊化了的药水效果，该药水效果被赋予了时长和等级等。
	 * @param event
	 */
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event) {
		if (event.source.getDamageType().equals("fall")) {							//伤害类型如果是摔落伤害
			PotionEffect effect = event.entityLiving.getActivePotionEffect(PotionLoader.potionFallProtection);
			if (effect != null) {
				if (effect.getAmplifier() == 0) {
					event.ammount /= 2;
				} else {
					event.ammount = 0;
				}
			}
		}
	}
	
	/**
	 * 当实体生物死亡时触发的事件
	 * @param event
	 */
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {
		if (event.entityLiving instanceof EntityPlayer && event.source.getDamageType().equals("byPig")) {
			//使成就可被获得
			((EntityPlayer) event.entityLiving).triggerAchievement(AchievementLoader.worseThanPig);
		}
	}

	/**
	 * 当物品被制造出来时触发的事件
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		/**
		 * net.minecraft.world.World类有数个用于播放音乐的方法：
		 * 
		 * 我们先来看一下playSound方法：
		 * 前三个参数表示这个声音所在位置的坐标，分别为x、y、z。
		 * 第四个参数表示这个声音的名称，在上面的声音索引文件中有所提及。
		 * 第五个参数表示这个声音的响度，默认响度为1.0F。
		 * 第六个参数表示这个声音的音调，默认音调为1.0F。
		 * 最后一个参数表示这个声音是否有延迟，比如雷声就存在着延迟。
		 * 
		 * 我们再来看一下playSoundAtEntity方法：
		 * 第一个参数表示该实体，没有什么过多的解释。
		 * 第二个参数表示声音的名称，和上面一样。
		 * 最后两个参数分别表示声音的响度和音调，和上面的同样没有差别。
		 * 
		 * sounds.json文件含义：
		 * category表示的是这个声音的类型，总共有ambient（环境）、weather（天气）、player（玩家）、neutral（中立）、hostile（敌对）、block（方块）、record（唱片）、music（音乐）、master（控制）这八种类型。
		 * sounds表示的就是声音了，这里表示的声音存放在这个音乐索引文件所在目录下的sounds文件夹下，在这里就是assets.fmltutor.sounds包下，这里表示的声音是一个列表，在游戏中会随机选取其中一个所代表的声音播放。
		 * sounds表示的声音列表还可以有volume、pitch等选项，分别表示响度、音量等。
		 */
		event.player.worldObj.playSoundAtEntity(event.player, "fmltutor:fmltutor.test", 1.0F, 1.0F);
		
		if (event.crafting.getItem() == Item.getItemFromBlock(BlockLoader.grassBlock)) {
			//使成就可被获得
			event.player.triggerAchievement(AchievementLoader.buildGrassBlock);
		}
	}
}