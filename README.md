# VideoClipMultiImagenThreads — Base Estudiantes

Proyecto base para crear una película generativa usando:

- audio `short[]`;
- imágenes `int[][]`;
- varias imágenes;
- frames;
- versión serial;
- versión paralela con threads.

## Main gráfico
`pe.edu.utp.videoclip.VideoClipGUI`

## Main consola
`pe.edu.utp.videoclip.VideoClipMain`

## Requisitos
- Java 17+
- Maven / NetBeans
- FFmpeg únicamente para ensamblar los frames y el WAV en un MP4
- sin librerías Java externas

## Carpeta de imágenes
Agregue o elimine archivos en:

`input/images/`

No es necesario editar el cargador.

## Archivo del estudiante
`StudentWork.java`

Ese archivo contiene los tres TODO de la práctica.

## Primera ejecución
El proyecto funciona con stubs:
- cambia imágenes por tiempo;
- aplica rotación básica;
- el audio todavía devuelve un nivel fijo de 0.25.

La tarea consiste en volverlo realmente reactivo al audio y personalizar el clip.

## Guía visual
Abra `INSTRUCCIONES_INTERACTIVAS.html` en un navegador. Incluye explicación completa y una demo de video embebida.
