package es.unican.is2.practica5b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoOrdenadoTest {

    private ConjuntoOrdenado<Integer> sut;

    @BeforeEach
    public void setUp() {
        sut = new ConjuntoOrdenado<Integer>();
    }

    @Test
    public void testGet() {

        // Caso en el que la lista se encuentra lista vacia
        // Realmente es el mismo caso que una lista con cosas y que 
        // el indice sobre pasa el tamaño de la misma 
		assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));

        // Anhadimos un elemento y comprobamos el primer elementos
        sut.add(2);
        assertEquals(2, sut.get(0));

        // Se anhiaden mas elementos y se comprueban los distintos casos
        sut.add(1);
        sut.add(4);
        sut.add(3);

        // Comprobamos un indice intermedio y el del final
        assertEquals(3, sut.get(2));
        assertEquals(4, sut.get(3));

        // Se comprueba si el indice se sale de los valores validos
        // aplicando AVL
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-100));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(4));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(100));
    }

    @Test 
    public void testAdd() {

        // Anhadimos a la lista su primer elemento
        assertTrue(sut.add(4));
        assertEquals(1, sut.size());
        assertEquals(4, sut.get(0));

        // Se anhade un elemento a la lista de un unico elemento
        assertTrue(sut.add(2));
        assertEquals(2, sut.size());
        assertEquals(2, sut.get(0));
        assertEquals(4, sut.get(1));

        // Se anhade un elemento pequenho (va al principio 
        // de la lista)
        assertTrue(sut.add(1));
        assertEquals(3, sut.size());
        assertEquals(1, sut.get(0));
        assertEquals(2, sut.get(1));
        assertEquals(4, sut.get(2));

        // Se anhade un elemento mediano (en el 
        // medio de la lista)
        assertTrue(sut.add(3));
        assertEquals(4, sut.size());
        assertEquals(1, sut.get(0));
        assertEquals(2, sut.get(1));
        assertEquals(3, sut.get(2));
        assertEquals(4, sut.get(3));

        // Se anhade un elemento grande (va en el
        // final de la lista)
        assertTrue(sut.add(5));
        assertEquals(5, sut.size());
        assertEquals(1, sut.get(0));
        assertEquals(2, sut.get(1));
        assertEquals(3, sut.get(2));
        assertEquals(4, sut.get(3));
        assertEquals(5, sut.get(4));

        // Se intenta anhadir un elemento repetido
        assertFalse(sut.add(5));
        assertEquals(5, sut.size());
        assertEquals(1, sut.get(0));
        assertEquals(2, sut.get(1));
        assertEquals(3, sut.get(2));
        assertEquals(4, sut.get(3));
        assertEquals(5, sut.get(4));

        // Se anhade un elemento Null
        assertThrows(NullPointerException.class, () -> sut.add(null));
        assertEquals(5, sut.size()); // Comprobamos que no se haya modificado nada
        assertEquals(1, sut.get(0));
        assertEquals(2, sut.get(1));
        assertEquals(3, sut.get(2));
        assertEquals(4, sut.get(3));
        assertEquals(5, sut.get(4));

    }
    
    @Test
    public void testRemove() {

        // Creamos una lista con varios elementos
        sut.add(1);
        sut.add(2);
        sut.add(3);
        sut.add(4);

        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-100));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(4));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(100));
        assertEquals(4, sut.size()); // Comprobamos que no se haya modificado nada
        assertEquals(1, sut.get(0));
        assertEquals(2, sut.get(1));
        assertEquals(3, sut.get(2));
        assertEquals(4, sut.get(3));

        // Elimina elemento del medio
        assertEquals(2, sut.remove(1));
        assertEquals(3, sut.size());
        assertEquals(1, sut.get(0));
        assertEquals(3, sut.get(1));
        assertEquals(4, sut.get(2));

        // Elimina elemento del final
        assertEquals(4, sut.remove(2));
        assertEquals(2, sut.size());
        assertEquals(1, sut.get(0));
        assertEquals(3, sut.get(1));

        // Elimina elemento del principio
        assertEquals(1, sut.remove(0));
        assertEquals(1, sut.size());
        assertEquals(3, sut.get(0));

        // Deja la lista vacia
        assertEquals(3, sut.remove(0));
        assertEquals(0, sut.size());

        //Intenta sacar elementos de una lista vacia
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(0));
        assertEquals(0, sut.size());

    }

    @Test
    public void testSize() {
        // Tamanho de la lista vacia
        assertEquals(0, sut.size());

        // Anhadimos elementos y comprobamos el tamanho
        sut.add(1);
        assertEquals(1, sut.size());
        sut.add(2);
        assertEquals(2, sut.size());

        // Quitamos uno y comprobamos que disminuye
        sut.remove(0);
        assertEquals(1, sut.size());
    }

    @Test
    public void testClear() {

        // Vacia una lista vacia
        sut.clear();
        assertEquals(0, sut.size());

        // Vacia una lista con un elemento
        sut.add(1);
        sut.clear();
        assertEquals(0, sut.size());
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));

        // Vacia una lista con varios elemento
        sut.add(1);
        sut.add(2);
        sut.clear();
        assertEquals(0, sut.size());
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));
    }

}
