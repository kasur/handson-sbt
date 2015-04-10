import com.exadel.erusak.core.variance.{Orange, Apple, FruitSlicer}

val appleSlicer = new FruitSlicer[Apple]

appleSlicer.slice(new Orange(1.1d))


