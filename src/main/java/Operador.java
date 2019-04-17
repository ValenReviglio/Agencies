import java.util.ArrayList;
import java.util.Collections;

public class Operador {
    public static <T extends Comparable> ArrayList<T> ordenarArreglo(ArrayList<T> arreglo) {
        Collections.sort(arreglo);
        return arreglo;
    }
}
