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

import java.io.File

import net.minecraftforge.common.config.Configuration

object Config {
  var minDecayTicks = 4
  var maxDecayTicks = 11

  def load(file: File): Unit = {
    val config = new Configuration(file)
    minDecayTicks = config.get("main", "MinDecayTicks", 4, "Minimum ticks for leaf decay", 1, Integer.MAX_VALUE).getInt()
    maxDecayTicks = config.get("main", "MaxDecayTicks", 11, "Maximum ticks for leaf decay", minDecayTicks, Integer.MAX_VALUE).getInt()
    if (config.hasChanged)
      config.save()
  }
}
