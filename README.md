# Escuela Colombiana de Ingeniería Julio Garavito
## Arquitecturas de Software (ARSW)

### :pushpin: Daniel Felipe Hernández Mancipe

<br/>

# :hammer_and_wrench: LOC (Lines Of Code) Counting

Programa para contar las líneas de código de un archivo.

| **File** | **Lines of code** |
| :-: | :-: |
| App | 79 |
| Counter | 8 |
| CounterImpl | 16 |
| LOCCounter | 9 |
| PhyCounter | 3 |
| Filter | 5 |
| BlankFilter | 15 |
| JavaCommentFilter | 39 |
| CounterImplTest | 127 |
| **Total** | **301** |

En total el proyecto tiene 301 líneas de código y requirió 10 horas en total.

$\frac{LOC}{h}=\frac{301}{10}=30.1$

## Getting Started

### Prerequisites

* Java >= 11.x
* Maven >= 3.x
* Git >= 2.x
* JUnit 4.x

### Installing

Simplemente clone el repositorio:

```
git clone https://github.com/danielhndz/ARSW-LOC-Counting.git
```

Luego compile el proyecto con maven:

```
mvn compile
```

Si salió bien, debería tener una salida similar a esta:

![](../media/mvn_compile.png?raw?=true)

### Using

Para ejecutar correctamente siempre debe estar en el folder del proyecto. Suponiendo que el proyecto está en la carpeta `Descargas`, haga lo siguiente:

* En una consola dirígase al folder del proyecto

![](../media/move_to_folder.png?raw=true)

* Antes de usarlo, se listan los archivos con el nombre `App.java` que se encuentra en el directorio `home`, el que la aplicación usa como raíz para buscar los archivos por el nombre dado, para mi caso es `/home/daniel/`

![](../media/finding_files.png?raw=true)

Como se observa, se encuentran 3 archivos, entre los cuales uno [**pertenece a este proyecto**](/src/main/java/edu/escuelaing/arsw/labs/locc/App.java), su salida puede ser similar a la mía.

El archivo en cuestión tiene **91** líneas según `Visual Studio Code`, dado que éste añade una línea vacía al final:

![](../media/vscode_App.java.png)

Por otro lado, según `Mousepad`, que no le añade nada al archivo, tiene **90** líneas:

![](../media/mousepad_App.java.png)

* Ahora, se procede a ejecutar el programa con el mismo input del ejemplo anterior, en modo `phy`, para que tenga en cuenta todas las líneas

![](../media/phy_App.java.png?raw=true)

Como se observa, el archivo tiene **90** líneas según el programa.

* Ahora se hace la misma ejecución que la anterior pero en modo `loc`, para que no tenga en cuenta las líneas en blanco ni los comentarios de línea ni bloque de Java

![](../media/loc_App.java.png?raw=true)

Como se observa, el archivo tiene **79** líneas de código, según el programa.

## Running the tests

Apuntando hacia el directorio del proyecto, ejecute en una línea de comandos

```
mvn test
```

Se debe obtener una salida similar a esta:

![](../media/mvn_test1.png)

![](../media/mvn_test2.png)

### Break down into end to end tests

Los dos tests están en [CounterImplTest](/src/test/java/edu/escuelaing/arsw/labs/locc/counter/CounterImplTest.java), ambos prueban el conteo en modo `phy`, uno lo hace para el archivo [CounterImpl](/src/main/java/edu/escuelaing/arsw/labs/locc/counter/CounterImpl.java), y el otro para ese mismo archivo [CounterImplTest](/src/test/java/edu/escuelaing/arsw/labs/locc/counter/CounterImplTest.java).

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Git](https://git-scm.com/) - Version Management
* [JUnit4](https://junit.org/junit4/) - Unit testing framework for Java

## Design Metaphor

* Autor: Daniel Hernández
* Última modificación: 08/06/2022

### Class Diagram

![](../media/class_diagram.png?raw=true)

El programa se divide en *Filters* y *Counters*.

Los *Filters* implementan la interfaz [`Filter`](/src/main/java/edu/escuelaing/arsw/labs/locc/filter/Filter.java). Un `Filter` tiene un método `filter` que recibe una lista de String y la devuelve filtrada. [`BlankFilter`](/src/main/java/edu/escuelaing/arsw/labs/locc/filter/BlankFilter.java) filtra las líneas vacías o en blanco y [`JavaCommentFilter`](/src/main/java/edu/escuelaing/arsw/labs/locc/filter/JavaCommentFilter.java) filtra los comentarios de Java.

Los *Counters* extienden la clase [`CounterImpl`](/src/main/java/edu/escuelaing/arsw/labs/locc/counter/CounterImpl.java), la cual es una implementación de la interfaz [`Counter`](/src/main/java/edu/escuelaing/arsw/labs/locc/counter/Counter.java). Un `Counter` tiene un atributo `filters`, una lista de `Filter`, y un método `count` que recibe una lista de String y devuelve un entero. La implementación de `count` en `CounterImpl` aplica, en orden, cada `Filter` de `filters` a la lista de String y luego cuenta las líneas. `PhyCounter` es un `CounterImpl` sin filtros en `filters`, `LOCCounter` es un `CounterImpl` con `BlankFilter` y `JavaCommentFilter` en `filters`.

## Authors

* **Daniel Hernández** - *Initial work* - [danielhndz](https://github.com/danielhndz)

## License

This project is licensed under the GPLv3 License - see the [LICENSE.md](LICENSE.md) file for details

## Javadoc

Para generar Javadocs independientes para el proyecto en la carpeta `/target/site/apidocs` ejecute:

```
mvn javadoc:javadoc
```

Debería tener una salida similar a esta:

![](../media/mvn_javadoc1.png?raw=true)

![](../media/mvn_javadoc2.png?raw=true)
