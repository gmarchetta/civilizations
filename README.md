# Civilizaciones

En una galaxia lejana, existen tres civilizaciones. Vulcanos, Ferengis y Betasoides. Cada civilización vive en paz en su respectivo planeta. Dominan la predicción del clima mediante un complejo sistema informático.

Premisas:

● El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido horario. Su distancia con respecto al sol es de 500Km.

● El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido horario. Su distancia con respecto al sol es de 2000Km.

● El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido anti­horario, su distancia con respecto al sol es de 1000Km.

● Todas las órbitas son circulares.

Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el sistema solar experimenta un período de sequía.
Cuando los tres planetas no están alineados, forman entre sí un triángulo. Es sabido que en el momento en el que el sol se encuentra dentro del triángulo, el sistema solar experimenta un período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en su máximo.
Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están alineados entre sí pero no están alineados con el sol.

Realizar un programa informático para poder predecir en los próximos 10 años:
1. ¿Cuántos períodos de sequía habrá?
2. ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?
3. ¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?

Bonus:
Para poder utilizar el sistema como un servicio a las otras civilizaciones, los Vulcanos requieren tener una base de datos con las condiciones meteorológicas de todos los días y brindar una API REST de consulta sobre las condiciones de un día en particular.

1) Generar un modelo de datos con las condiciones de todos los días hasta 10 años en adelante
utilizando un job para calcularlas.
2) Generar una API REST la cual devuelve en formato JSON la condición climática del día
consultado.
3) Hostear el modelo de datos y la API REST en un cloud computing libre (como APP Engine o
Cloudfoudry) y enviar la URL para consulta:

Ej: GET → http://....../clima?dia=566 → Respuesta: {“dia”:566, “clima”:”lluvia”}


# Solución:

1) Para la solución del presente ejercicio, asumimos que los planetas y el sol tienen un tamaño de un punto.

2) Asumimos que el sol se ubica en el origen de coordenadas (0, 0).

3) Tenemos la ubicación de los planetas en coordenadas polares, y lo convertiremos a coordenadas cartesianas. Para esto:

Usamos la función coseno para obtener la coordenada x: cos(angulo) = x / distancia del planeta al sol. 

Usamos la función seno para y: sin(angulo) = y / distancia del planeta al sol

4) Para determinar si los tres planetas están alineados, comparamos la suma de la distancia (modulo del vector) entre el primer planeta y el segundo, mas la distancia entre el segundo y el tercero, contra la distancia entre el primero y el tercero. En caso de ser iguales, los puntos son colineares y los planetas están alineados. 

d = raiz(cuadrado(x2 - x1) + cuadrado(y2 - y1))

En caso de que se cumpla esta condición, debemos determinar si además están alineados con el sol. Para esto necesitamos determinar si el valor de la ordenada al origen de la recta a la que pertenecen los puntos es cero (en cuyo caso están alineados con el sol) o no (no están alineados con el sol). Para esto, teniendo en cuenta que la ecuación de la recta es y = ax + b, calculamos primero la pendiente de la recta a la que pertenecen los puntos (a):

a = YV - YF / XV - XF

Obtenido el valor a, podemos calcular la ordenada al origen reemplazando en la ecuación de la recta con cualquier punto:

YV = a*XV + b => YV - a*XV = b

Si b es distinto de 0, el sol no está alineado con los planetas y tenemos condiciones óptimas de presión y temperatura.

Si b es igual a 0, el sol está alineado con los planetas y tenemos un período de sequía.

5) Para el caso en que los planetas no estén alineados, necesitamos determinar si el sol está contenido dentro del triángulo que forman los 3 planetas. Para esto, llamando nuevamente la ubicación de cada planeta como F, V y B, y llamando al nuevo punto P, formamos 3 triángulos: FVP, FBP y VBP. Si el punto está contenido dentro del triángulo, debería cumplirse la siguiente igualdad: área(FVP) + área(FBP) + área(VBP) = área(FVB)

Para calcular el área de un triángulo escaleno, usaremos: raiz(s * (s-a) * (s-b) * (s-c))

Donde a, b y c son los lados del triángulo y s el semiperímetro: s = (a + b + c) / 2
 
Para conocer el valor de a, b y c, necesitamos calcular el módulo de los vectores que forman cada lado de los triángulos, usando la fórmula: 

raiz(cuadrado(x2-x1)+cuadrado(y2-y1))

6) Por último, utilizaremos una variable para guardar el MAX(área(FVB)) y la fecha en que se produce. En cada iteración, en caso de que sea el área más grande hasta el momento, sobreescribiremos el valor de dicha variable. Dicho valor representará el pico de intensidad del período de lluvia.