package net.nokiknick.myoriginalmod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokiknick.myoriginalmod.MyOriginalMod;
import net.nokiknick.myoriginalmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MyOriginalMod.MODID);

    public static final RegistryObject<Block> GARNET_BLOCK = registerBlock("garnet_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> GARNET_ORE = registerBlock("garnet_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).sound(SoundType.AMETHYST)));

    //Helper methods:
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    };

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}