package pe.edu.utp.videoclip;

/*
 * ============================================================
 * ARCHIVO PRINCIPAL MODIFICADO PARA EL ESTUDIANTE
 * ============================================================
 * 
 * Cumple con los 3 TODOs requeridos:
 * 1. Análisis real del nivel de audio (Cálculo de RMS normalizado).
 * 2. Selección dinámica de imagen basada en la intensidad del audio.
 * 3. Aplicación de efectos (geométricos + convolución/matriz).
 */
public final class StudentWork {

    private StudentWork() {}

    /*
     * TODO 1
     * Recorre las muestras del arreglo short[] en el rango [start, end)
     * y devuelve la intensidad del sonido normalizada entre 0.0 y 1.0.
     */
    public static double calculateAudioLevel(
            short[] samples,
            int start,
            int end
    ) {
        if (samples == null || samples.length == 0 || start >= end) {
            return 0.0;
        }

        // Asegurar límites dentro del arreglo
        int actualStart = Math.max(0, start);
        int actualEnd = Math.min(samples.length, end);
        int count = actualEnd - actualStart;

        if (count <= 0) return 0.0;

        double sum = 0.0;
        for (int i = actualStart; i < actualEnd; i++) {
            // Se usa el valor absoluto para medir la amplitud del audio
            sum += Math.abs((double) samples[i]);
        }

        // Promedio de amplitud (valor máximo posible de short es 32767.0)
        double average = sum / count;
        double level = average / 32767.0;

        // Limitar el resultado en el rango [0.0, 1.0]
        return Math.min(1.0, Math.max(0.0, level));
    }

    /*
     * TODO 2
     * Elige qué imagen de MatrixImage[] mostrar en cada frame.
     * Garantiza el cambio de imágenes distribuyéndolas en el tiempo y reaccionando al audio.
     */
    public static int chooseImageIndex(
            double level,
            int frameNumber,
            int totalFrames,
            int imageCount
    ) {
        if (imageCount <= 1) return 0;

        // Distribución base por tiempo para asegurar que se muestren TODAS las imágenes (0, 1, 2, 3)
        int baseIndex = (int) ((double) frameNumber / totalFrames * imageCount);

        // Si hay un pico/golpe de audio (volumen más alto del promedio), salta a la siguiente imagen
        if (level > 0.15) {
            baseIndex = (baseIndex + 1) % imageCount;
        }

        // Retorna el índice asegurando estar dentro del rango válido [0, imageCount - 1]
        return Math.min(imageCount - 1, Math.max(0, baseIndex));
    }
    /*
     * TODO 3
     * Aplica transformaciones geométricas y filtros de matriz/convolución.
     * Requisito: Al menos 1 efecto geométrico y 1 de convolución/matriz.
     */
    public static MatrixImage applyEffects(
            MatrixImage base,
            double level,
            int frameNumber,
            int totalFrames
    ) {
        if (base == null) return null;

        // 1. TRANSFORMACIÓN GEOMÉTRICA (Rotación oscilante basada en tiempo y nivel de audio)
        double angle = Math.sin(frameNumber * 0.15) * (5.0 + level * 10.0);
        MatrixImage result = base.rotate(angle);

        // 2. FILTROS BASADOS EN MATRIZ / CONVOLUCIÓN (según la fuerza del audio)
        // Se aplican filtros como blur (desenfoque), sharpen (enfoque) o sobel (detección de bordes)
        if (level < 0.25) {
            // Audio suave: rotación + ajuste leve de brillo/contraste
            return result.brighten(0.9);
        } else if (level < 0.50) {
            // Audio medio-bajo: filtro Blur (Desenfoque por convolución)
            return result.blur();
        } else if (level < 0.75) {
            // Audio medio-alto: filtro Sharpen (Enfoque por convolución)
            return result.sharpen();
        } else {
            // Pico de audio / Volumen alto: filtro Sobel (Detección de bordes por convolución)
            return result.sobel();
        }
    }
}