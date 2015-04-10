package com.exadel.erusak.init

import java.util.concurrent.TimeUnit

import akka.actor.{Props, ActorSystem}
import com.exadel.erusak.akka.LightweightActor

case class KickOff()

/**
 *
 * Created by erusak on Aug 20 2014.
 */
object HolaActor  {

  def main(args: Array[String]) {
    val actorSystem = ActorSystem("init-akka-system")

    val actor = actorSystem.actorOf(Props[LightweightActor], "light-actor")

    actor ! KickOff

    TimeUnit.SECONDS.sleep(10)

    actorSystem.terminate()

  }
}
