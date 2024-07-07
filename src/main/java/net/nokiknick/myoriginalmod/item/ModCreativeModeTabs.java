package net.nokiknick.myoriginalmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokiknick.myoriginalmod.MyOriginalMod;
import net.nokiknick.myoriginalmod.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MyOriginalMod.MODID);

    public static final RegistryObject<CreativeModeTab> MY_ORIGINAL_MOD = CREATIVE_MODE_TABS.register("my_original_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GARNET.get()))
                    .title(Component.translatable("creativetab.my_original_mod_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        //ITEMS:
                        pOutput.accept(ModItems.GARNET.get());
                        pOutput.accept(ModItems.CRUSHED_GARNET.get());

                        //BLOCKS:
                        pOutput.accept(ModBlocks.GARNET_BLOCK.get());
                        pOutput.accept(ModBlocks.GARNET_ORE.get());
                    })
                    .build());

    public static void register (IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
