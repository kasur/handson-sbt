package com.exadel.erusak.core.types

import com.exadel.erusak.core.types.HeterogeneousStorage.Key
import org.scalatest.FlatSpec

import scala.collection.mutable

/**
 * @author erusak.
 */
object HeterogeneousStorage {
  abstract class Key(name: String) {
    type Value
  }
}

class HeterogeneousStorage {
  val data = mutable.Map.empty[Key, Any]
  def get(key: Key) = {
    data.get(key).asInstanceOf[Option[key.Value]]
  }
  def set(key: Key)(value: key.Value) = {
    data.update(key, value)
  }
}

trait IntValued extends Key {
  type Value = Int
}

trait StringValued extends Key {
  type Value = String
}

object Keys {
  val fooInt = new Key("foo") with IntValued
  val barInt = new Key("foo") with IntValued
  val fooString = new Key("foo") with StringValued
  val barString = new Key("bar") with StringValued
}

class HeterogeneousStorageTest extends FlatSpec {
  val storage = new HeterogeneousStorage
  it should "work" in {
    storage.set(Keys.fooInt)(11)
    storage.set(Keys.barInt)(13)
    storage.set(Keys.fooString)("fooString")
    storage.set(Keys.barString)("barString")
    println(storage.data.keys)
    println(storage.data.values)
  }


}
