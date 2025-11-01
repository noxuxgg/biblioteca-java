/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class AnioDocumentFilter extends DocumentFilter {

    private int limite = 4; // Solo aceptamos 4 dígitos

    // Método para manejar la inserción de texto
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        // Si la longitud actual + la longitud del nuevo texto no excede el límite
        if ((fb.getDocument().getLength() + string.length()) <= limite) {
            // Opcional: Asegurarse de que solo sean dígitos
            if (string.matches("\\d*")) {
                super.insertString(fb, offset, string, attr);
            }
        }
    }

    // Método para manejar el reemplazo de texto (al pegar o editar)
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        // Si la nueva longitud total no excede el límite
        if ((fb.getDocument().getLength() - length + text.length()) <= limite) {
            // Opcional: Asegurarse de que solo sean dígitos
            if (text.matches("\\d*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
