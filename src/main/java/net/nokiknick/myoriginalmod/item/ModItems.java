package net.nokiknick.myoriginalmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokiknick.myoriginalmod.MyOriginalMod;
import net.nokiknick.myoriginalmod.item.custom.FuelItem;
import net.nokiknick.myoriginalmod.item.custom.MetalDetectorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MyOriginalMod.MODID);

    public static final RegistryObject<Item> GARNET = ITEMS.register("garnet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRUSHED_GARNET = ITEMS.register("crushed_garnet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties().food(ModFoods.BANANA)));
    public static final RegistryObject<Item> CRYSTALLISED_COAL = ITEMS.register("crystallised_coal",
            () -> new FuelItem(new Item.Properties(), 3200));

    public static void Register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
