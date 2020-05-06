package mahmoud.nethercraftmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mahmoud.nethercraftmod.init.BlockLists;
import mahmoud.nethercraftmod.init.ItemLists;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("nethercraft")
public class Main 
{
	public static Main instance;
	public static final String modid = "nethercraft";
	private static final Logger logger = LogManager.getLogger(modid);
	
	public Main()
	{
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		logger.info("Setup Method registered.");
	}

	private void clientRegistries(final FMLClientSetupEvent event)
	{
		logger.info("clientRegistries Method registered.");
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisteryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
					ItemLists.gun = new Item(new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(location("gun")),
					
					ItemLists.bullet = new Item(new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(location("bullet")),
							
					ItemLists.golter_ingot = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("golter_ingot")),
					
					ItemLists.iron_stick = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("iron_stick"))
			);
			
			logger.info("item registered.");
		}
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
					BlockLists.golter_block = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL)).setRegistryName(location("golter_block"))
			);
			
			logger.info("block registered.");
		}
		private static ResourceLocation location(String name)
		{
			return new ResourceLocation(modid, name);
		}
	}	
}
