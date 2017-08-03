/*
 * Copyright (c) bdew, 2017
 * https://github.com/bdew/leafdecay
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.bdew.leafdecay

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

import scala.collection.JavaConversions._
import scala.util.Random

object UpdateHandler {
  def init(): Unit = {
    MinecraftForge.EVENT_BUS.register(this)
  }

  @SubscribeEvent
  def onUpdate(event: BlockEvent.NeighborNotifyEvent): Unit = {
    val world = event.getWorld
    event.getNotifiedSides foreach { facing =>
      val pos = event.getPos.offset(facing)
      val state = world.getBlockState(pos)
      val block = state.getBlock
      if (block.isLeaves(state, world, pos))
        world.scheduleUpdate(pos, block, Config.minDecayTicks + Random.nextInt(Config.maxDecayTicks - Config.minDecayTicks))
    }
  }
}
