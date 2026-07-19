import java.util.HashMap;
import java.util.Map;

public class Mates {
    /* =============================== */
    /* CONSTANTES                      */
    /* =============================== */
    public static final double  PI      = 3.14159265358979323846;
    public static final double  E       = 2.71828182845904523536;
    private static final double EPSILON = 0.000001;


    /* =============================== */
    /* ESTADÍSTICA Y ARRAYS            */
    /* =============================== */
    /**
     * Ordena un array de números enteros o decimales de menor a mayor sin modificar el original.
     *
     * @param lista Array de números enteros o decimales.
     * @return Una copia del array ordenada de menor a mayor.
     */
    public double[] ordenar(double[] lista) {

        double[] copia = new double[lista.length];

        for (int i = 0; i < lista.length; i++) {
            copia[i] = lista[i];
        }

        if (copia.length < 2) {
            return copia;
        }

        boolean cambios = false;

        for (int j = 0; j < copia.length; j++) {

            cambios = false;

            for (int k = 0; k < copia.length - 1 - j; k++) {

                if (copia[k] > copia[k + 1]) {
                    double temp = copia[k];
                    copia[k] = copia[k + 1];
                    copia[k + 1] = temp;
                    cambios = true;
                }
            }

            if (!cambios) {
                return copia;
            }
        }

        return copia;
    }

    /**
     * Calcula la suma de todos los elementos de un array.
     *
     * @param lista Array de números enteros o decimales.
     * @return La suma de todos los elementos del array o Double.NaN si está vacío.
     */
    public double sumatorio(double[] lista) {

        if (lista.length == 0) {
            return Double.NaN;
        }

        double resultado = 0;

        for (double elemento : lista) {
            resultado += elemento;
        }

        return resultado;
    }

    /**
     * Calcula la media aritmética de dos números enteros o decimales.
     *
     * @param x Número entero o decimal.
     * @param y Número entero o decimal.
     * @return La media aritmética de ambos números.
     */
    public double media(double x, double y) {
        return (x + y) / 2;
    }

    /**
     * Calcula la media aritmética de todos los elementos de un array.
     *
     * @param lista Array de números enteros o decimales.
     * @return La media aritmética del array o Double.NaN si está vacío.
     */
    public double media(double[] lista) {

        if (lista.length == 0) {
            return Double.NaN;
        }

        return sumatorio(lista) / lista.length;
    }

    /**
     * Calcula la mediana de un conjunto de números.
     *
     * @param lista Array de números enteros o decimales.
     * @return La mediana del array o Double.NaN si está vacío.
     */
    public double mediana(double[] lista) {

        if (lista.length == 0) {
            return Double.NaN;
        }

        double[] copia = ordenar(lista);

        if (copia.length % 2 != 0) {
            return copia[copia.length / 2];
        }

        return media(copia[copia.length / 2 - 1], copia[copia.length / 2]);
    }

    /**
     * Calcula la moda de un conjunto de números mediante búsquedas secuenciales.
     *
     * @param lista Array de números enteros o decimales.
     * @return La moda del array o Double.NaN si no existe una moda única.
     */
    public double moda(double[] lista) {

        if (lista.length == 0) {
            return Double.NaN;
        }

        double mayorFrecuencia = 0;
        double moda = 0;
        boolean empate = false;

        for (double elemento : lista) {

            double frecuencia = 0;

            for (int i = 0; i < lista.length; i++) {
                if (elemento == lista[i]) {
                    frecuencia++;
                }
            }

            if (frecuencia > mayorFrecuencia) {
                mayorFrecuencia = frecuencia;
                moda            = elemento;
                empate          = false;
            }

            if (frecuencia == mayorFrecuencia && elemento != moda) {
                empate = true;
            }
        }

        if (mayorFrecuencia == 1) {
            return Double.NaN;
        }

        if (empate) {
            return Double.NaN;
        }

        return moda;
    }

    /**
     * Calcula la moda de un conjunto de números utilizando un HashMap.
     *
     * @param lista Array de números enteros o decimales.
     * @return La moda del array o Double.NaN si no existe una moda única.
     */
    public double modaHashMap(double[] lista) {

        if (lista.length == 0) {
            return Double.NaN;
        }

        HashMap<Double, Integer> diccionario = new HashMap<>();

        for (double elemento : lista) {

            if (diccionario.containsKey(elemento)) {
                int temp = diccionario.get(elemento);
                temp++;
                diccionario.put(elemento, temp);
            } else {
                diccionario.put(elemento, 1);
            }
        }

        int     mayorFrecuencia = 0;
        double  moda            = Double.NaN;
        boolean hayEmpate       = false;

        for (Map.Entry<Double, Integer> entrada : diccionario.entrySet()) {

            int frecuencia = entrada.getValue();

            if (frecuencia > mayorFrecuencia) {
                mayorFrecuencia = frecuencia;
                moda            = entrada.getKey();
                hayEmpate       = false;
            } else if (frecuencia == mayorFrecuencia) {
                hayEmpate = true;
            }
        }

        if (hayEmpate) {
            return Double.NaN;
        }

        if (mayorFrecuencia == 1) {
            return Double.NaN;
        }

        return moda;
    }

    /**
     * Calcula el rango de un conjunto de números.
     *
     * @param lista Array de números enteros o decimales.
     * @return La diferencia entre el mayor y el menor valor del array o Double.NaN si está vacío.
     */
    public double rango(double[] lista) {

        if (lista.length == 0) {
            return Double.NaN;
        }

        return max(lista) - min(lista);
    }

    /**
     * Calcula la varianza de un conjunto de números.
     *
     * @param lista Array de números enteros o decimales.
     * @return La varianza del array o Double.NaN si está vacío.
     */
    public double varianza(double[] lista) {

        if (lista.length == 0) {
            return Double.NaN;
        }

        double media = media(lista);
        double suma = 0;

        for (double elemento : lista) {
            suma += cuadrado(elemento - media);
        }

        return suma / lista.length;
    }

    /**
     * Calcula la desviación típica de un conjunto de números.
     *
     * @param lista Array de números enteros o decimales.
     * @return La desviación típica del array o Double.NaN si está vacío.
     */
    public double desviacionTipica(double[] lista) {
        return sqrt(varianza(lista));
    }


    /* =============================== */
    /* VALORES NUMÉRICOS               */
    /* =============================== */
    /**
     * Calcula el valor absoluto de un número entero o decimal.
     *
     * @param x Número entero o decimal.
     * @return El valor absoluto del número entero o decimal.
     */
    public double abs(double x) {

        if (x < 0) {
            return -x;
        }
        
        return x;
    }

    /**
     * Calcula el valor absoluto de un número entero.
     *
     * @param x Número entero.
     * @return El valor absoluto del número entero.
     */
    public int abs(int x) {

        if (x < 0) {
            return -x;
        }
        
        return x;
    }

    /**
     * Devuelve el mayor de dos números enteros o decimales.
     *
     * @param x Número entero o decimal.
     * @param y Número entero o decimal.
     * @return El mayor de ambos números.
     */
    public double max(double x, double y) {

        if (x > y) {
            return x;
        }

        return y;
    }

    /**
     * Devuelve el mayor valor de un array de números enteros o decimales.
     *
     * @param lista Array de números enteros o decimales.
     * @return El mayor valor del array o Double.NaN si está vacío.
     */
    public double max(double[] lista) {

        if (lista.length == 0) {
            return Double.NaN;
        }

        double maximo = lista[0];

        for (int i = 1; i < lista.length; i++) {
            if (lista[i] > maximo) {
                maximo = lista[i];
            }
        }

        return maximo;
    }

    /**
     * Devuelve el menor de dos números enteros o decimales.
     *
     * @param x Número entero o decimal.
     * @param y Número entero o decimal.
     * @return El menor de ambos números.
     */
    public double min(double x, double y) {

        if (x < y) {
            return x;
        }

        return y;
    }

    /**
     * Devuelve el menor valor de un array de números enteros o decimales.
     *
     * @param lista Array de números enteros o decimales.
     * @return El menor valor del array o Double.NaN si está vacío.
     */
    public double min(double[] lista) {

        if (lista.length == 0) {
            return Double.NaN;
        }

        double minimo = lista[0];

        for (int i = 1; i < lista.length; i++) {
            if (lista[i] < minimo) {
                minimo = lista[i];
            }
        }

        return minimo;
    }


    /* =============================== */
    /* POTENCIAS Y RAÍCES              */
    /* =============================== */
    /**
     * Calcula la raíz cuadrada de un número entero o decimal.
     *
     * @param x Número entero o decimal.
     * @return La raíz cuadrada del número entero o decimal o Double.NaN si es negativo.
     */
    public double sqrt(double x) {
        
        if (x < 0) {
            return Double.NaN;
        }
        
        if (x == 0) {
            return 0;
        }

        double aproximacion = x;
        double anterior;

        do {
            anterior = aproximacion;
            aproximacion = (anterior + x / anterior) / 2;

        } while (abs(aproximacion - anterior) > EPSILON);

        return aproximacion;
    }

    /**
     * Calcula la potencia de un número elevado a un exponente entero o decimal.
     *
     * @param base Número entero o decimal.
     * @param exponente Número entero o decimal.
     * @return El resultado de elevar el número al exponente indicado o Double.NaN si la operación no está definida.
     */
    public double potencia(double base, double exponente) {

        if (base == 0 && exponente == 0) {
            return Double.NaN;
        }

        if (base == 0 && exponente < 0) {
            return Double.NaN;
        }

        if (base == 0) {
            return 0;
        }

        if (base < 0) {

            if (exponente != suelo(exponente)) {
                return Double.NaN;
            }

            return potenciaEntera(base, (int) exponente);
        }

        return exp(exponente * ln(base));
    }

    /**
     * Calcula la potencia de un número elevado a un exponente entero.
     *
     * @param base Número entero o decimal.
     * @param exponente Número entero.
     * @return El resultado de elevar el número al exponente indicado.
     */
    public double potenciaEntera(double base, int exponente) {

        if (exponente == 0) {
            return 1;
        }

        boolean negativo = exponente < 0;

        if (negativo) {
            exponente = abs(exponente);
        }

        double resultado = 1;

        for (int i = 0; i < exponente; i++) {
            resultado *= base;
        }

        if (negativo) {
            return 1 / resultado;
        }

        return resultado;
    }

    /**
     * Calcula el cuadrado de un número entero o decimal.
     *
     * @param x Número entero o decimal.
     * @return El cuadrado del número entero o decimal.
     */
    public double cuadrado(double x) {
        return x * x;
    }

    /**
     * Calcula el cubo de un número entero o decimal.
     *
     * @param x Número entero o decimal.
     * @return El cubo del número entero o decimal.
     */
    public double cubo(double x) {
        return x * x * x;
    }

    /**
     * Calcula el factorial de un número entero no negativo.
     *
     * @param x Número entero.
     * @return El factorial del número entero o Double.NaN si es negativo.
     */
    public double factorial(int x) {

        if (x < 0) {
            return Double.NaN;
        }

        double resultado = 1;

        for (int i = 2; i <= x; i++) {
            resultado *= i;
        }

        return resultado;
    }

    /**
     * Calcula el valor de la función exponencial del número indicado.
     *
     * @param x Número entero o decimal.
     * @return El valor de e elevado al número indicado.
     */
    public double exp(double x) {

        double suma = 1;

        double termino = 1;

        int i = 1;

        while (abs(termino) > EPSILON) {
            termino = termino * x / i;
            suma += termino;
            i++;
        }

        return suma;
    }

    /**
     * Calcula el logaritmo neperiano del número indicado.
     *
     * @param x Número entero o decimal.
     * @return El logaritmo neperiano del número indicado, Double.NaN si es negativo o Double.NEGATIVE_INFINITY si es cero.
     */
    public double ln(double x) {
        
        if (x < 0) {
            return Double.NaN;
        }

        if (x == 0) {
            return Double.NEGATIVE_INFINITY;
        }

        if (x == 1) {
            return 0;
        }

        double aproximacion = 0;
        double anterior, valorExp;

        do {
            anterior = aproximacion;
            valorExp = exp(aproximacion);

            aproximacion = aproximacion - (valorExp - x) / valorExp;

        } while (abs(aproximacion - anterior) > EPSILON);

        return aproximacion;
    }

    /**
     * Calcula el logaritmo de un número en una base determinada.
     *
     * @param x Número entero o decimal.
     * @param base Número entero o decimal.
     * @return El logaritmo del número en la base indicada o Double.NaN si los parámetros no son válidos.
     */
    public double log(double x, double base) {

        if (x <= 0) {
            return Double.NaN;
        }

        if (base <= 0 || base == 1) {
            return Double.NaN;
        }

        return ln(x) / ln(base);
    }


    /* =============================== */
    /* DIVISIBILIDAD Y NÚMEROS ENTEROS */
    /* =============================== */
    /**
     * Calcula el máximo común divisor de dos números enteros.
     *
     * @param x Número entero.
     * @param y Número entero.
     * @return El máximo común divisor de ambos números enteros.
     */
    public int mcd(int x, int y) {

        if (x == 0 && y == 0) {
            return 0;
        }

        x = abs(x);
        y = abs(y);

        int resto;

        while (y != 0) {
            resto = x % y;
            x = y;
            y = resto;
        }

        return x;
    }


    /*
    public double mcd(double x, double y) {

        double resto;

        resto = x % y;

        if (resto == 0) {
            return y;
        }

        return mcd(y, resto);
    }
    */

    /**
     * Calcula el mínimo común múltiplo de dos números enteros.
     *
     * @param x Número entero.
     * @param y Número entero.
     * @return El mínimo común múltiplo de ambos números enteros.
     */
    public int mcm(int x, int y) {

        x = abs(x);
        y = abs(y);

        if (x == 0 || y == 0) {
            return 0;
        }

        return (x / mcd(x, y)) * y; // (x * y) / mcd(x, y)
    }

    /**
     * Comprueba si un número entero es primo.
     *
     * @param x Número entero.
     * @return true si el número es primo, false en caso contrario.
     */
    public boolean esPrimo(int x) {

        if (x < 2) {
            return false;
        }

        int limite = (int) sqrt(x);

        for (int i = 2; i <= limite; i++) {
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Comprueba si un número entero es par.
     *
     * @param x Número entero.
     * @return true si el número es par, false en caso contrario.
     */
    public boolean esPar(int x) {
        return x % 2 == 0;
    }

    /**
     * Comprueba si un número entero es impar.
     *
     * @param x Número entero.
     * @return true si el número es impar, false en caso contrario.
     */
    public boolean esImpar(int x) {
        return !esPar(x);
    }

    /**
     * Comprueba si un número entero es perfecto.
     *
     * @param x Número entero.
     * @return true si el número es perfecto, false en caso contrario.
     */
    public boolean esPerfecto(int x) {

        if (x < 2) {
            return false;
        }

        int suma = 1;
        int divisor;

        int limite = (int) sqrt(x);

        for (int i = 2; i <= limite; i++) {
            if (x % i == 0) {
                suma += i;
                divisor = x / i;

                if (divisor != i) {
                    suma += divisor;
                }
            }
        }

        return suma == x;
    }


    /* =============================== */
    /* GEOMETRÍA                       */
    /* =============================== */
    /**
     * Calcula la longitud de la hipotenusa de un triángulo rectángulo.
     *
     * @param cateto1 Número entero o decimal.
     * @param cateto2 Número entero o decimal.
     * @return La longitud de la hipotenusa o Double.NaN si algún cateto es negativo.
     */
    public double hipotenusa(double cateto1, double cateto2) {

        if (cateto1 < 0 || cateto2 < 0) {
            return Double.NaN;
        }

        return sqrt(cuadrado(cateto1) + cuadrado(cateto2));
    }

    /**
     * Calcula la longitud de un cateto de un triángulo rectángulo.
     *
     * @param hipotenusa Número entero o decimal.
     * @param cateto Número entero o decimal.
     * @return La longitud del cateto o Double.NaN si los parámetros no son válidos.
     */
    public double cateto(double hipotenusa, double cateto) {

        if (hipotenusa < 0 || cateto < 0) {
            return Double.NaN;
        }

        if (hipotenusa < cateto) {
            return Double.NaN;
        }

        return sqrt(cuadrado(hipotenusa) - cuadrado(cateto));
    }

    /**
     * Calcula la distancia entre dos puntos del plano cartesiano.
     *
     * @param x1 Número entero o decimal que representa la coordenada X del primer punto.
     * @param y1 Número entero o decimal que representa la coordenada Y del primer punto.
     * @param x2 Número entero o decimal que representa la coordenada X del segundo punto.
     * @param y2 Número entero o decimal que representa la coordenada Y del segundo punto.
     * @return La distancia entre ambos puntos.
     */
    public double distancia(double x1, double y1, double x2, double y2) {
        
        double diferenciaX = abs(x2 - x1);
        double diferenciaY = abs(y2 - y1);

        return hipotenusa(diferenciaX, diferenciaY);
    }

    /**
     * Calcula el área de un círculo.
     *
     * @param radio Número entero o decimal.
     * @return El área del círculo o Double.NaN si el radio es negativo.
     */
    public double areaCirculo(double radio) {

        if (radio < 0) {
            return Double.NaN;
        }

        return PI * cuadrado(radio);
    }

    /**
     * Calcula el perímetro de un círculo.
     *
     * @param radio Número entero o decimal.
     * @return El perímetro del círculo o Double.NaN si el radio es negativo.
     */
    public double perimetroCirculo(double radio) {

        if (radio < 0) {
            return Double.NaN;
        }

        return 2 * PI * radio;
    }

    /**
     * Calcula la longitud de una circunferencia.
     *
     * @param radio Número entero o decimal.
     * @return La longitud de la circunferencia o Double.NaN si el radio es negativo.
     */
    public double longitudCircunferencia(double radio) {
        return perimetroCirculo(radio);
    }

    /**
     * Calcula el área de un triángulo.
     *
     * @param base Número entero o decimal.
     * @param altura Número entero o decimal.
     * @return El área del triángulo o Double.NaN si algún parámetro es negativo.
     */
    public double areaTriangulo(double base, double altura) {
        
        if (base < 0 || altura < 0) {
            return Double.NaN;
        }

        return (base * altura) / 2;
    }

    /**
     * Calcula el área de un rectángulo.
     *
     * @param base Número entero o decimal.
     * @param altura Número entero o decimal.
     * @return El área del rectángulo o Double.NaN si algún parámetro es negativo.
     */
    public double areaRectangulo(double base, double altura) {

        if (base < 0 || altura < 0) {
            return Double.NaN;
        }

        return base * altura;
    }

    /**
     * Calcula el área de un cuadrado.
     *
     * @param lado Número entero o decimal.
     * @return El área del cuadrado o Double.NaN si el lado es negativo.
     */
    public double areaCuadrado(double lado) {

        if (lado < 0) {
            return Double.NaN;
        }

        return cuadrado(lado); // return areaRectangulo(lado, lado);
    }

    /**
     * Calcula el volumen de una esfera.
     *
     * @param radio Número entero o decimal.
     * @return El volumen de la esfera o Double.NaN si el radio es negativo.
     */
    public double volumenEsfera(double radio) {

        if (radio < 0) {
            return Double.NaN;
        }

        return (4.0 / 3.0) * PI * cubo(radio);
    }

    /**
     * Calcula el volumen de un cubo.
     *
     * @param lado Número entero o decimal.
     * @return El volumen del cubo o Double.NaN si el lado es negativo.
     */
    public double volumenCubo(double lado) {

        if (lado < 0) {
            return Double.NaN;
        }

        return cubo(lado);
    }

    /**
     * Calcula el volumen de un cilindro.
     *
     * @param radio Número entero o decimal.
     * @param altura Número entero o decimal.
     * @return El volumen del cilindro o Double.NaN si algún parámetro es negativo.
     */
    public double volumenCilindro(double radio, double altura) {

        if (radio < 0 || altura < 0) {
            return Double.NaN;
        }

        return areaCirculo(radio) * altura;
    }


    /* =============================== */
    /* TRIGONOMETRÍA                   */
    /* =============================== */
    /**
     * Reduce un ángulo expresado en radianes al intervalo comprendido entre 0 y 2π.
     *
     * @param x Número entero o decimal.
     * @return El ángulo equivalente comprendido entre 0 y 2π.
     */
    public double reducirAngulo(double x) {

        double vueltas = suelo(x / (2 * PI));

        return x - vueltas * (2 * PI);
    }

    /*
    public double sinTaylor(double x) {

        x = reducirAngulo(x);

        if (x == 0) {
            return 0;
        }

        double suma = 0;
        double termino;
        int signo = 1;
        int n = 1;

        do {

            termino = potencia(x, n) / factorial(n);

            suma += signo * termino;

            signo *= -1;

            n += 2;

        } while (abs(termino) > EPSILON);

        return suma;
    }
    */

    /**
     * Calcula el seno de un ángulo expresado en radianes.
     *
     * @param x Número entero o decimal que representa el ángulo en radianes.
     * @return El seno del ángulo indicado.
     */
    public double sin(double x) {

        x = reducirAngulo(x);

        if (x == 0) {
            return 0;
        }

        double suma = x;
        double termino = x;
        int n = 1;
        double x2 = cuadrado(x);

        do {

            termino *= -x2 / ((n + 1) * (n + 2));

            suma += termino;

            n += 2;

        } while (abs(termino) > EPSILON);

        return suma;
    }

    /*
    public double cosTaylor(double x) {

        x = reducirAngulo(x);

        if (x == 0) {
            return 1;
        }

        double suma = 0;
        double termino;
        int signo = 1;
        int n = 0;

        do {

            termino = potencia(x, n) / factorial(n);

            suma += signo * termino;

            signo *= -1;

            n += 2;

        } while (abs(termino) > EPSILON);

        return suma;
    }
    */

    /**
     * Calcula el coseno de un ángulo expresado en radianes.
     *
     * @param x Número entero o decimal que representa el ángulo en radianes.
     * @return El coseno del ángulo indicado.
     */
    public double cos(double x) {

        x = reducirAngulo(x);

        if (x == 0) {
            return 1;
        }

        double suma = 1;
        double termino = 1;
        int n = 0;
        double x2 = cuadrado(x);

        do {

            termino *= -x2 / ((n + 1) * (n + 2));

            suma += termino;

            n += 2;

        } while (abs(termino) > EPSILON);

        return suma;
    }

    /**
     * Calcula la tangente de un ángulo expresado en radianes.
     *
     * @param x Número entero o decimal que representa el ángulo en radianes.
     * @return La tangente del ángulo indicado o Double.NaN si no está definida.
     */
    public double tan(double x) {

        double seno   = sin(x);
        double coseno = cos(x);

        if (abs(coseno) < EPSILON) {
            return Double.NaN;
        }

        return seno / coseno;
    }

    /**
     * Calcula el arco seno de un número entero o decimal.
     *
     * @param x Número entero o decimal comprendido entre -1 y 1.
     * @return El ángulo en radianes cuyo valor de seno es el número indicado o Double.NaN si está fuera del intervalo permitido.
     */
    public double asin(double x) {

        if (x < -1 || x > 1) {
            return Double.NaN;
        }

        if (x == 1) {
            return PI / 2;
        }

        if (x == -1) {
            return -PI / 2;
        }

        double raiz = sqrt(1 - cuadrado(x));

        return atan(x / raiz);
    }

    /**
     * Calcula el arco coseno de un número entero o decimal.
     *
     * @param x Número entero o decimal comprendido entre -1 y 1.
     * @return El ángulo en radianes cuyo valor de coseno es el número indicado o Double.NaN si está fuera del intervalo permitido.
     */
    public double acos(double x) {

        if (x < -1 || x > 1) {
            return Double.NaN;
        }

        return PI / 2 - asin(x);
    }

    /*
    public double atanTaylor(double x) {

        if (x == 0) {
            return 0;
        }

        if (x < 0) {
            return -atanTaylor(-x);
        }

        if (x > 1) {
            return PI / 2 - atanTaylor(1 / x);
        }

        double suma = 0;
        double termino;
        int signo = 1;
        int n = 1;

        do {

            termino = potencia(x, n) / n;

            suma += signo * termino;

            signo *= -1;

            n += 2;

        } while (abs(termino) > EPSILON);

        return suma;
    }
    */

    /**
     * Calcula el arco tangente de un número entero o decimal.
     *
     * @param x Número entero o decimal.
     * @return El ángulo en radianes cuyo valor de tangente es el número indicado.
     */
    public double atan(double x) {

        if (x == 0) {
            return 0;
        }

        if (x < 0) {
            return -atan(-x);
        }

        if (x > 1) {
            return PI / 2 - atan(1 / x);
        }

        double suma = x;
        double termino = x;
        int n = 1;
        double x2 = cuadrado(x);

        do {

            termino *= -x2 * n / (n + 2);

            suma += termino;

            n += 2;

        } while (abs(termino) > EPSILON);

        return suma;
    }


    /* =============================== */
    /* NÚMEROS ALEATORIOS              */
    /* =============================== */
    private long semilla = System.currentTimeMillis();

    private static final long A = 1664525;
    private static final long C = 1013904223;
    private static final long M = 4294967296L;

    /**
     * Genera un número decimal aleatorio comprendido entre 0 y 1.
     *
     * @return Un número decimal aleatorio mayor o igual que 0 y menor que 1.
     */
    public double aleatorio() {

        semilla = (A * semilla + C) % M;

        return (double) semilla / M;
    }

    /**
     * Genera un número decimal aleatorio comprendido entre dos valores.
     *
     * @param min Número entero o decimal.
     * @param max Número entero o decimal.
     * @return Un número decimal aleatorio comprendido entre ambos valores o Double.NaN si el intervalo no es válido.
     */
    public double aleatorio(double min, double max) {

        if (min >= max) {
            return Double.NaN;
        }

        return min + aleatorio() * (max - min);
    }

    /**
     * Genera un número entero aleatorio comprendido entre dos valores, ambos incluidos.
     *
     * @param min Número entero.
     * @param max Número entero.
     * @return Un número entero aleatorio comprendido entre ambos valores.
     */
    public int aleatorioEntero(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("Rango inválido");
        }

        return min + (int) (aleatorio() * (max - min + 1));
    }

    /**
     * Determina aleatoriamente si un suceso ocurre según la probabilidad indicada.
     *
     * @param probabilidad Número entero o decimal comprendido entre 0 y 1.
     * @return true si el suceso ocurre y false en caso contrario.
     */
    public boolean probabilidad(double probabilidad) {

        if (probabilidad < 0 || probabilidad > 1) {
            throw new IllegalArgumentException("Probabilidad inválida");
        }

        return aleatorio() < probabilidad;
    }

    /**
     * Genera un índice entero aleatorio válido para una longitud determinada.
     *
     * @param longitud Número entero mayor que 0.
     * @return Un índice entero aleatorio comprendido entre 0 y longitud - 1.
     */
    public int indiceAleatorio(int longitud) {

        if (longitud <= 0) {
            throw new IllegalArgumentException("Longitud inválida");
        }

        return aleatorioEntero(0, longitud - 1);
    }

    /**
     * Devuelve un elemento aleatorio de un array.
     *
     * @param lista Array de elementos.
     * @return Un elemento aleatorio del array indicado.
     */
    public <T> T elegir(T[] lista) {

        if (lista == null || lista.length == 0) {
            throw new IllegalArgumentException("Lista vacía");
        }

        return lista[indiceAleatorio(lista.length)];
    }


    /* =============================== */
    /* COMBINATORIA                    */
    /* =============================== */
    /**
     * Calcula el número de permutaciones posibles de un conjunto de elementos.
     *
     * @param x Número entero mayor o igual que 0.
     * @return El número de permutaciones posibles.
     */
    public double permutaciones(int x) {
        return factorial(x);
    }

    /**
     * Calcula el número de variaciones posibles al seleccionar r elementos de un conjunto de n elementos.
     *
     * @param n Número entero mayor o igual que 0.
     * @param r Número entero comprendido entre 0 y n.
     * @return El número de variaciones posibles o Double.NaN si los parámetros no son válidos.
     */
    public double variaciones(int n, int r) {

        if (n < 0 || r < 0 || r > n) {
            return Double.NaN;
        }

        return factorial(n) / factorial(n - r);
    }

    /**
     * Calcula el número de combinaciones posibles al seleccionar r elementos de un conjunto de n elementos.
     *
     * @param n Número entero mayor o igual que 0.
     * @param r Número entero comprendido entre 0 y n.
     * @return El número de combinaciones posibles o Double.NaN si los parámetros no son válidos.
     */
    public double combinaciones(int n, int r) {

        if (n < 0 || r < 0 || r > n) {
            return Double.NaN;
        }

        return variaciones(n, r) / factorial(r);
    }


    /* =============================== */
    /* PROGRESIONES                    */
    /* =============================== */
    /**
     * Calcula el término situado en una posición determinada de una progresión aritmética.
     *
     * @param primerTermino Número entero o decimal.
     * @param diferencia Número entero o decimal.
     * @param posicion Número entero mayor que 0.
     * @return El término correspondiente a la posición indicada o Double.NaN si la posición no es válida.
     */
    public double progresionAritmetica(double primerTermino, double diferencia, int posicion) {

        if (posicion < 1) {
            return Double.NaN;
        }

        return primerTermino + (posicion - 1) * diferencia;
    }

    /**
     * Calcula el término situado en una posición determinada de una progresión geométrica.
     *
     * @param primerTermino Número entero o decimal.
     * @param razon Número entero o decimal.
     * @param posicion Número entero mayor que 0.
     * @return El término correspondiente a la posición indicada o Double.NaN si la posición no es válida.
     */
    public double progresionGeometrica(double primerTermino, double razon, int posicion) {

        if (posicion < 1) {
            return Double.NaN;
        }

        return primerTermino * potencia(razon, posicion - 1);
    }


    /* =============================== */
    /* ÁLGEBRA                         */
    /* =============================== */
    /**
     * Resuelve una regla de tres simple directa.
     *
     * @param a Número entero o decimal.
     * @param b Número entero o decimal.
     * @param c Número entero o decimal.
     * @return El valor correspondiente de la proporción o Double.NaN si el primer parámetro es igual a 0.
     */
    public double reglaDeTres(double a, double b, double c) {

        if (a == 0) {
            return Double.NaN;
        }

        return b * c / a;
    }

    /**
     * Resuelve una ecuación de primer grado de la forma ax + b = 0.
     *
     * @param a Número entero o decimal.
     * @param b Número entero o decimal.
     * @return La solución de la ecuación o Double.NaN si no existe solución única.
     */
    public double ecuacionPrimerGrado(double a, double b) {

        if (a == 0) {
            return Double.NaN;
        }

        return -(b / a);
    }

    /**
     * Resuelve una ecuación de segundo grado de la forma ax² + bx + c = 0.
     *
     * @param a Número entero o decimal.
     * @param b Número entero o decimal.
     * @param c Número entero o decimal.
     * @return Un array con las soluciones de la ecuación o un array vacío si no existen soluciones reales.
     */
    public double[] ecuacionSegundoGrado(double a, double b, double c) {

        if (a == 0) {
            return new double[0];
        }

        double discriminante = cuadrado(b) - 4 * a * c;

        if (discriminante < 0) {
            return new double[0];
        }
        
        double raiz = sqrt(discriminante);

        if (discriminante == 0) {

            double[] resultado = new double[1];

            resultado[0] = (-b + raiz) / (2 * a);

            return resultado;
        }

        double[] resultado = new double[2];

        resultado[0] = (-b + raiz) / (2 * a);
        resultado[1] = (-b - raiz) / (2 * a);

        return resultado;
    }


    /* =============================== */
    /* FINANZAS Y PORCENTAJES          */
    /* =============================== */
    /**
     * Calcula el capital final aplicando interés simple.
     *
     * @param capital Número entero o decimal.
     * @param interes Número entero o decimal.
     * @param tiempo Número entero o decimal.
     * @return El capital final obtenido o Double.NaN si algún parámetro es negativo.
     */
    public double interesSimple(double capital, double interes, double tiempo) {

        if (capital < 0 || interes < 0 || tiempo < 0) {
            return Double.NaN;
        }

        return capital * (1 + interes * tiempo);
    }

    /**
     * Calcula el capital final aplicando interés compuesto.
     *
     * @param capital Número entero o decimal.
     * @param interes Número entero o decimal.
     * @param tiempo Número entero o decimal.
     * @return El capital final obtenido o Double.NaN si algún parámetro es negativo.
     */
    public double interesCompuesto(double capital, double interes, double tiempo) {

        if (capital < 0 || interes < 0 || tiempo < 0) {
            return Double.NaN;
        }

        return capital * potencia((1 + interes), tiempo);
    }

    /**
     * Calcula el porcentaje indicado de una cantidad.
     *
     * @param cantidad Número entero o decimal.
     * @param porcentaje Número entero o decimal.
     * @return El porcentaje correspondiente de la cantidad o Double.NaN si algún parámetro es negativo.
     */
    public double porcentaje(double cantidad, double porcentaje) {

        if (porcentaje < 0 || cantidad < 0) {
            return Double.NaN;
        }

        return cantidad * porcentaje / 100;
    }

    /**
     * Calcula el valor de una cantidad tras aplicarle un descuento porcentual.
     *
     * @param cantidad Número entero o decimal.
     * @param porcentaje Número entero o decimal.
     * @return La cantidad resultante tras aplicar el descuento o Double.NaN si los parámetros no son válidos.
     */
    public double descuento(double cantidad, double porcentaje) {

        if (porcentaje < 0 || porcentaje > 100 || cantidad < 0) {
            return Double.NaN;
        }

        return cantidad - porcentaje(cantidad, porcentaje);
    }

    /**
     * Calcula el valor de una cantidad tras aplicarle un incremento porcentual.
     *
     * @param cantidad Número entero o decimal.
     * @param porcentaje Número entero o decimal.
     * @return La cantidad resultante tras aplicar el incremento o Double.NaN si algún parámetro es negativo.
     */

    public double incremento(double cantidad, double porcentaje) {

        if (porcentaje < 0 || cantidad < 0) {
            return Double.NaN;
        }

        return cantidad + porcentaje(cantidad, porcentaje);
    }

    /**
     * Calcula el valor de una cantidad tras aplicarle el IVA indicado.
     *
     * @param cantidad Número entero o decimal.
     * @param porcentaje Número entero o decimal.
     * @return La cantidad resultante tras aplicar el IVA o Double.NaN si algún parámetro es negativo.
     */
    public double iva(double cantidad, double porcentaje) {

        if (porcentaje < 0 || cantidad < 0) {
            return Double.NaN;
        }

        return incremento(cantidad, porcentaje);
    }

    /**
     * Calcula qué porcentaje representa una cantidad respecto al total.
     *
     * @param parte Número entero o decimal.
     * @param total Número entero o decimal.
     * @return El porcentaje que representa la parte respecto al total o Double.NaN si los parámetros no son válidos.
     */
    public double porcentajeEntre(double parte, double total) {

        if (parte < 0 || total <= 0) {
            return Double.NaN;
        }

        return (parte * 100) / total;
    }


    /**
     * Calcula la parte entera inferior de un número entero o decimal.
     *
     * @param x Número entero o decimal.
     * @return El mayor número entero menor o igual que el número indicado.
     */
    public double suelo(double x) {

        int entero = (int) x;

        if (x >= 0 || x == entero) {
            return entero;
        }

        return entero - 1;
    }

    /**
     * Calcula la parte entera superior de un número entero o decimal.
     *
     * @param x Número entero o decimal.
     * @return El menor número entero mayor o igual que el número indicado.
     */
    public double techo(double x) {
        return -suelo(-x);
    }

    /**
     * Redondea un número entero o decimal al entero más cercano.
     *
     * @param x Número entero o decimal.
     * @return El número redondeado al entero más próximo.
     */
    public double redondeo(double x) {

        if (x < 0) {
            return -suelo(abs(x) + 0.5);
        }

        return suelo(x + 0.5);
    }

    /**
     * Elimina la parte decimal de un número entero o decimal sin realizar redondeo.
     *
     * @param x Número entero o decimal.
     * @return La parte entera del número indicado.
     */
    public double truncar(double x) {

        if (x < 0) {
            return techo(x);
        }

        return suelo(x);
    }

    /**
     * Obtiene la parte fraccionaria de un número entero o decimal.
     *
     * @param x Número entero o decimal.
     * @return La parte fraccionaria del número indicado.
     */
    public double parteFraccionaria(double x) {
        return x - suelo(x);
    }

    /**
     * Obtiene la parte decimal de un número entero o decimal conservando su signo.
     *
     * @param x Número entero o decimal.
     * @return La parte decimal del número indicado.
     */
    public double parteDecimal(double x) {
        return x - truncar(x);
    }


    /* =============================== */
    /* CONVERSIONES                    */
    /* =============================== */
    /**
     * Convierte un ángulo expresado en grados a radianes.
     *
     * @param grados Número entero o decimal.
     * @return El ángulo equivalente expresado en radianes.
     */
    public double gradosARadianes(double grados) {
        return grados * (PI / 180);
    }

    /**
     * Convierte un ángulo expresado en radianes a grados.
     *
     * @param radianes Número entero o decimal.
     * @return El ángulo equivalente expresado en grados.
     */
    public double radianesAGrados(double radianes) {
        return radianes * (180 / PI);
    }

    /**
     * Convierte una longitud expresada en metros a kilómetros.
     *
     * @param metros Número entero o decimal.
     * @return La longitud equivalente expresada en kilómetros.
     */
    public double metrosAKilometros(double metros) {
        return metros / 1000;
    }

    /**
     * Convierte una longitud expresada en kilómetros a metros.
     *
     * @param kilometros Número entero o decimal.
     * @return La longitud equivalente expresada en metros.
     */
    public double kilometrosAMetros(double kilometros) {
        return kilometros * 1000;
    }

    /**
     * Convierte una temperatura expresada en grados Celsius a grados Fahrenheit.
     *
     * @param celsius Número entero o decimal.
     * @return La temperatura equivalente expresada en grados Fahrenheit.
     */
    public double celsiusAFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    /**
     * Convierte una temperatura expresada en grados Fahrenheit a grados Celsius.
     *
     * @param fahrenheit Número entero o decimal.
     * @return La temperatura equivalente expresada en grados Celsius.
     */
    public double fahrenheitACelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    /**
     * Convierte una temperatura expresada en grados Celsius a Kelvin.
     *
     * @param celsius Número entero o decimal.
     * @return La temperatura equivalente expresada en Kelvin.
     */
    public double celsiusAKelvin(double celsius) {
        
        if (celsius < -273.15) {
            return Double.NaN;
        }

        return celsius + 273.15;
    }

    /**
     * Convierte una temperatura expresada en Kelvin a grados Celsius.
     *
     * @param kelvin Número entero o decimal.
     * @return La temperatura equivalente expresada en grados Celsius.
     */
    public double kelvinACelsius(double kelvin) {

        if (kelvin < 0) {
            return Double.NaN;
        }

        return kelvin - 273.15;
    }

    /**
     * Convierte una duración expresada en segundos a minutos.
     *
     * @param segundos Número entero o decimal.
     * @return La duración equivalente expresada en minutos.
     */
    public double segundosAMinutos(double segundos) {

        if (segundos < 0) {
            return Double.NaN;
        }

        return segundos / 60;
    }

    /**
     * Convierte una duración expresada en minutos a horas.
     *
     * @param minutos Número entero o decimal.
     * @return La duración equivalente expresada en horas.
     */
    public double minutosAHoras(double minutos) {

        if (minutos < 0) {
            return Double.NaN;
        }

        return minutos / 60;
    }

    /**
     * Convierte una duración expresada en horas a días.
     *
     * @param horas Número entero o decimal.
     * @return La duración equivalente expresada en días.
     */
    public double horasADias(double horas) {

        if (horas < 0) {
            return Double.NaN;
        }

        return horas / 24;
    }
}