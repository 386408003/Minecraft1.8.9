package com.github.ustc_zzzz.fmltutor.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.Vec3;

/**
 * CommandBase相关方法：
 * getPlayer方法用于通过玩家名称获取对应的玩家实体，如果无法找到，则会抛出异常。
 * getCommandSenderAsPlayer方法用于获取输入该命令的玩家，如果该命令是命令方块等非玩家实体执行的，则会抛出异常。
 * getListOfStringsMatchingLastWord方法用于将当前输入的字符串匹配对应字符串数组中对应的字符串列表，常用于自动补全。
 * 
 * 
 * 补充一点：@p、@a这样的通配符在Minecraft执行这个命令之前就已经展开成特定的名称了。
 * 比如这个服务器有Alice和Bob两个人，现在执行命令/position @a，相当于执行了一次/position Alice和一次/position Bob
 * 
 * @author ZhuFeng 
 * @date 2018年3月24日
 */
public class CommandPosition extends CommandBase {
	/**
	 * 这个命令的名称，也就是一个斜线之后紧接着出现的那个。
	 */
	@Override
	public String getCommandName() {
		return "position";
	}

	/**
	 * 这个命令的用法，当玩家输入/help position的时候就会出现。
	 */
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "commands.position.usage";
	}

	/**
	 * 这个命令执行的时候会调用的方法。
	 * 这里的args参数指的是去掉命令名称本身之后剩下的参数。比如如果我们输入/position alice bob carol，args就会是以alice、bob、carol为顺序的数组，如果我们只输入/position命令本身，那么args数组就是空的。
	 */
	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length > 1) {
			throw new WrongUsageException("commands.position.usage");
		} else {
			//取出第一个参数对应的玩家实体，如果这个参数不存在就取自己
			EntityPlayerMP entityPlayerMP = args.length > 0 ? 
					CommandBase.getPlayer(sender, args[0]) : CommandBase.getCommandSenderAsPlayer(sender);
			Vec3 pos = entityPlayerMP.getPositionVector();
			//输出玩家的名字位置以及所在世界
			sender.addChatMessage(new ChatComponentTranslation(
					"commands.position.success", entityPlayerMP.getName(), pos,
					entityPlayerMP.worldObj.provider.getDimensionName()));
		}
	}
	
	/**
	 * 执行该命令所需要的等级。
	 * 等级一共分四种，对应的数字为1、2、3和4,等级为1代表任何玩家都可以执行，比如/ping这样的命令，等级为2代表命令方块可以执行，而等级4，则只有这个服务器的OP、还有单人模式下的作弊玩家可以执行。
	 */
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	/**
	 * 命令自动补全
	 * 当玩家输入第一个参数的部分内容，比如/position Ali，或者仅仅输入了一个空格的时候，这个方法会让系统会找到所有这个服务器上的玩家，并且把对应的提供给系统。
	 */
	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			String[] names = MinecraftServer.getServer().getAllUsernames();
			return CommandBase.getListOfStringsMatchingLastWord(args, names);
		}
		return null;
	}
}