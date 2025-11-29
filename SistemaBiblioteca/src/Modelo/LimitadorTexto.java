/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.text.*;

public class LimitadorTexto extends DocumentFilter {

    private int limite;

    public LimitadorTexto(int limite) {
        this.limite = limite;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        if (fb.getDocument().getLength() + string.length() <= limite) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        if (fb.getDocument().getLength() - length + text.length() <= limite) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
