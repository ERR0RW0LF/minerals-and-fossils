package com.err0rw0lf;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MineralsAndFossilsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(MineralsAndFossilsItemTagProvider::new);
		pack.addProvider(MineralsAndFossilsBlockTagProvider::new);
		pack.addProvider(MineralsAndFossilsRecipeProvider::new);
		pack.addProvider(MineralsAndFossilsBlockLootTableProvider::new);
	}
}
