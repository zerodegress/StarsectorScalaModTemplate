package com.example

import com.fs.starfarer.api.BaseModPlugin
import com.fs.starfarer.api.Global
import org.apache.log4j.Level

class StarsectorScalaModTemplate extends BaseModPlugin {
  override def onApplicationLoad: Unit = {
    Global
      .getLogger(classOf[StarsectorScalaModTemplate])
      .log(Level.INFO, "I'm scala mod!");
  }
}
