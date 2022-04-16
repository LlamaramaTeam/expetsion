@file:Suppress("SameParameterValue", "MemberVisibilityCanBePrivate")

package org.llamarama.petty.blocks

import org.llamarama.petty.MainFile
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object PettyBlocks {
    private val BlockItemsRegistry = linkedMapOf<String, Item>()
    private val BlockRegistry = linkedMapOf<String, Block>()

    val COOL_BLOCK: Block

    /**
     * Register blocks in here.
     * [net.minecraft.item.BlockItem]'s gets added automatically (but can't be referenced atm).
     * If you wish to change the settings of the BlockItem implement your own methods for it.
     */
    init {
        COOL_BLOCK = addBlock("coolblock", Block(AbstractBlock.Settings.copy(Blocks.STONE)))
    }

    private fun addBlock(name: String, block: Block): Block {
        val correctedName = name.replace(" ", "").lowercase().trim()
        BlockRegistry[correctedName] = block
        BlockItemsRegistry[correctedName + "_item"] =
            (BlockItem(block, Item.Settings().maxCount(64).group(ItemGroup.MISC)))
        return block
    }

    fun registerBlocks() {
        BlockRegistry.forEach { (name, block) ->
            Registry.register(Registry.BLOCK, Identifier(MainFile.MOD_ID, name), block)
        }
        BlockItemsRegistry.forEach { (name, item) ->
            Registry.register(Registry.ITEM, Identifier(MainFile.MOD_ID, name), item)

        }
    }
}