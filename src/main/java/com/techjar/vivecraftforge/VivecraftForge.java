package com.techjar.vivecraftforge;

import com.techjar.vivecraftforge.eventhandler.EventHandlerServer;
import com.techjar.vivecraftforge.network.ChannelHandler;
import com.techjar.vivecraftforge.util.LogHelper;
import com.techjar.vivecraftforge.util.Util;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import net.minecraftforge.forgespi.language.IModInfo;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Mod("vivecraftforgeextensions")
public class VivecraftForge {
	public static IModInfo MOD_INFO;

	public static boolean isVivecraftInstalled;

	public VivecraftForge() {
		MOD_INFO = ModLoadingContext.get().getActiveContainer().getModInfo();
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.config);
		eventBus.addListener(this::onSetup);
		MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
		MinecraftForge.EVENT_BUS.register(new EventHandlerServer());

		/* This field is public in Vivecraft, but protected in MC.
		   Therfore, if this field is protected, then we are in MC. But if it's public, we're in Vivecraft.

		   Vivecraft doesn't expose itself to Forge (it goes outside of Forge as-is), so we have to use
		   this hacky way to figure out that it's there.

		   NOTE: If another mod makes this public, we'll think we're in Vivecraft!*/
		Field field = ObfuscationReflectionHelper.findField(Matrix4f.class, "field_226575_a_");
		isVivecraftInstalled = !Modifier.isProtected(field.getModifiers());
		if (isVivecraftInstalled) {
			System.out.println("Vivecraft is installed! Won't fix things Vivecraft already fixes!");
		} else {
			System.out.println("Vivecraft isn't installed! Will perform VFE functions!");
		}
	}

	private void onSetup(FMLCommonSetupEvent event) {
		ChannelHandler.init();
	}

	private void onServerStarting(FMLServerStartingEvent event) {
		if (Config.printMoney.get())
			LogHelper.warning(Util.getMoney());
	}
}
