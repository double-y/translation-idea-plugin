package com.translate.ideaplugin

import org.jetbrains.plugins.scala.base.SimpleTestCase

/**
  * Created by yasudayousuke on 1/1/16.
  */
class SampleTest extends SimpleTestCase{
  def testSample: Unit = {
    assertMatches(1+1) {
      case 2 =>
    }
  }
}
