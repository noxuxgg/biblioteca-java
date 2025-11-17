/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.AnioDocumentFilter;
import Modelo.Autor;
import Modelo.AutorDAO;
import Modelo.Categoria;
import Modelo.CategoriaDAO;
import Modelo.Editorial;
import Modelo.EditorialDAO;
import Modelo.FuncionesExtra;
import Modelo.Libro;
import Modelo.LibroDAO;
import Modelo.Materia;
import Modelo.MateriaDAO;
import Modelo.Pais;
import Modelo.PaisDAO;
import Modelo.Prestamo;
import Modelo.PrestamoDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Modelo.Multa;
import Modelo.MultaDAO;
import Modelo.login;
import Reportes.Grafico;
import com.itextpdf.text.Image;
import Modelo.Multa_pagada;
import Modelo.Multa_pagadaDAO;
import java.sql.Timestamp;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.Document;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.text.AbstractDocument;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

//</editor-fold>
/**
 *
 * @author Henry Quispe
 */
public class Sistema extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Sistema.class.getName());

    /**
     * Creates new form Sistema
     */
    Categoria ca = new Categoria();
    CategoriaDAO categoria = new CategoriaDAO();
    Materia ma = new Materia();
    MateriaDAO materia = new MateriaDAO();
    Pais pa = new Pais();
    PaisDAO pais = new PaisDAO();
    Editorial ed = new Editorial();
    EditorialDAO editorial = new EditorialDAO();
    Autor au = new Autor();
    AutorDAO autor = new AutorDAO();
    Libro li = new Libro();
    LibroDAO libro = new LibroDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    FuncionesExtra fun = new FuncionesExtra();
    Usuario us = new Usuario();
    UsuarioDAO usuario = new UsuarioDAO();
    Prestamo pre = new Prestamo();
    PrestamoDAO prestamo = new PrestamoDAO();
    Multa mu = new Multa();
    MultaDAO multaDao = new MultaDAO();
    DefaultTableModel modeloMulta = new DefaultTableModel();
    Multa_pagada mp = new Multa_pagada();
    Multa_pagadaDAO multaPagadaDao = new Multa_pagadaDAO();
    

    
    
 

    public Sistema() {
        initComponents();
        LimpiarTable();
        this.setLocationRelativeTo(null);
        ((AbstractDocument) txtAnioLibro.getDocument()).setDocumentFilter(new AnioDocumentFilter());
        txtIdPais.setVisible(false);
        txtIdCategoria.setVisible(false);
        txtIdMateria.setVisible(false);
        txtIdAutor.setVisible(false);
        txtIdEditorial.setVisible(false);
        txtIdUsuario.setVisible(false);
        txtStockLibro.setText("1");
        txtStockLibro.setEditable(false);
        AutoCompleteDecorator.decorate(cboxPaisEditorial);
        editorial.ConsultarPais(cboxPaisEditorial);
        autor.ConsultarPais(cboxPaisAutor);
        AutoCompleteDecorator.decorate(cboxPaisAutor);

        libro.ConsultarAutor(cboxAutorLibro);
        AutoCompleteDecorator.decorate(cboxAutorLibro);
        libro.ConsultarMateria(cboxMateriaLibro);
        AutoCompleteDecorator.decorate(cboxMateriaLibro);
        libro.ConsultarEditorial(cboxEditorialLibro);
        AutoCompleteDecorator.decorate(cboxEditorialLibro);
        libro.ConsultarCategoria(cboxCategoriaLibro);
        AutoCompleteDecorator.decorate(cboxCategoriaLibro);
        libro.ConsultarEstado(cboxEstadoLibro);
        AutoCompleteDecorator.decorate(cboxEstadoLibro);

        libro.ConsultarAutor(cboxAutorLibro2);
        AutoCompleteDecorator.decorate(cboxAutorLibro2);
        libro.ConsultarCategoria(cboxCategoriaLibro2);
        AutoCompleteDecorator.decorate(cboxCategoriaLibro2);
        libro.ConsultarNombre(cboxNombreLibro2);
        AutoCompleteDecorator.decorate(cboxNombreLibro2);
        libro.ConsultarMateria(cboxMateriaLibro2);
        AutoCompleteDecorator.decorate(cboxMateriaLibro2);

        usuario.ConsultarCargo(cboxCargoUsuario);
        AutoCompleteDecorator.decorate(cboxCargoUsuario);
        usuario.ConsultarCarreras(cboxCarreraUsuario);
        AutoCompleteDecorator.decorate(cboxCarreraUsuario);
        usuario.ConsultarTipoUsuario(cboxTipoUsuario);
        AutoCompleteDecorator.decorate(cboxTipoUsuario);
        usuario.ConsultarEstadoUsuario(cboxEstadoPrestamoUsuario);
        AutoCompleteDecorator.decorate(cboxEstadoPrestamoUsuario);
  
        
        
        //PRESTAMO
        txtNombrePrestamo.setEditable(false);
        txtApellidoPrestamo.setEditable(false);
        txtTelefonoPrestamo.setEditable(false);
        txtDomicilioPrestamo.setEditable(false);
        txtidUsuarioPrestamo.setEditable(false);
        txtidPrestamo.setEditable(false);
        txtidLibroPrestamo.setEditable(false);
        txtEdicionPrestamo.setEditable(false);
        txtStockPrestamo.setEditable(false);
        txtFechaDevolucion.getDateEditor().setEnabled(false);
        List<String> TitulosLibros = new ArrayList();
        TitulosLibros = obtenerListaTitulos();
        String[] arrayTitulos = TitulosLibros.toArray(new String[0]);
        JList listaSugerenciasPrestamo = new JList(arrayTitulos);
        AutoCompleteDecorator.decorate(listaSugerenciasPrestamo, txtTituloPrestamo,ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
        //txtTituloPrestamo.getCaret().setSelectionVisible(false);


        // Llenar los valores iniciales según el filtro seleccionado
        actualizarComboValorUsuario();
        listarTodasLasMultas();    // Tabla principal de multas
        listarMultasPagadasEnTabla();     // Tabla de pagadas
        listarMultasSinPagar();    // Tabla de sin pagar

    }

    public Sistema(login priv) {
        initComponents();
        LimpiarTable();
        this.setLocationRelativeTo(null);
        txtIdPais.setVisible(false);
        txtIdCategoria.setVisible(false);
        txtIdMateria.setVisible(false);
        txtIdAutor.setVisible(false);
        txtIdEditorial.setVisible(false);
        txtIdUsuario.setVisible(false);
        AutoCompleteDecorator.decorate(cboxPaisEditorial);
        editorial.ConsultarPais(cboxPaisEditorial);
        autor.ConsultarPais(cboxPaisAutor);
        AutoCompleteDecorator.decorate(cboxPaisAutor);

        libro.ConsultarAutor(cboxAutorLibro);
        AutoCompleteDecorator.decorate(cboxAutorLibro);
        libro.ConsultarMateria(cboxMateriaLibro);
        AutoCompleteDecorator.decorate(cboxMateriaLibro);
        libro.ConsultarEditorial(cboxEditorialLibro);
        AutoCompleteDecorator.decorate(cboxEditorialLibro);
        libro.ConsultarCategoria(cboxCategoriaLibro);
        AutoCompleteDecorator.decorate(cboxCategoriaLibro);
        libro.ConsultarEstado(cboxEstadoLibro);
        AutoCompleteDecorator.decorate(cboxEstadoLibro);

        usuario.ConsultarCargo(cboxCargoUsuario);
        AutoCompleteDecorator.decorate(cboxCargoUsuario);
        usuario.ConsultarCarreras(cboxCarreraUsuario);
        AutoCompleteDecorator.decorate(cboxCarreraUsuario);
        usuario.ConsultarTipoUsuario(cboxTipoUsuario);
        AutoCompleteDecorator.decorate(cboxTipoUsuario);
        usuario.ConsultarEstadoUsuario(cboxEstadoPrestamoUsuario);
        AutoCompleteDecorator.decorate(cboxEstadoPrestamoUsuario);
        
       

        // Llenar los valores iniciales según el filtro seleccionado
        actualizarComboValorUsuario();
        //privilegios

        String tipo = (priv.getTipo() != null) ? priv.getTipo().toLowerCase() : "desconocido";

        if ("administrador".equals(tipo)) {
            JOptionPane.showMessageDialog(null, "Entro como administrador");
            System.out.println("Entró como administrador");
        } else if ("normal".equals(tipo)) {
            // USUARIO NORMAL
            btnPrestamo.setEnabled(false);
    
            btnGuardarUsuario.setEnabled(false);
            btnEliminarUsuario.setEnabled(false);
            btnReportes.setEnabled(false);
            btnAnalisis.setEnabled(false);
            btnAjuste.setEnabled(false);

            for (int i = cboxLibro.getItemCount() - 1; i >= 0; i--) {
                Object item = cboxLibro.getItemAt(i);
                if (!"Libros".equalsIgnoreCase(item.toString())) {
                    cboxLibro.removeItemAt(i);
                }
            }

            cboxLibro.setSelectedItem("Libros");

            //falta cerrar guardar eliminar y asi
            JOptionPane.showMessageDialog(null, "Entro como usuario normal");
            System.out.println("Entró como usuario normal");
        } else if ("reportes".equals(tipo)) {
            //USUARIO
            btnGuardarUsuario.setEnabled(false);
            btnEliminarUsuario.setEnabled(false);
            btnActualizarUsuario.setEnabled(false);
            btnNuevoUsuario.setEnabled(false);
            txtCarnetUsuario.setEnabled(false);
            txtNombreUsuario.setEnabled(false);
            txtApellidoUsuario.setEnabled(false);
            txtTelefonoUsuario.setEnabled(false);
            txtDomicilioUsuario.setEnabled(false);
            cboxCargoUsuario.setEnabled(false);
            cboxCarreraUsuario.setEnabled(false);
            cboxTipoUsuario.setEnabled(false);
            cboxEstadoPrestamoUsuario.setEnabled(false);

            JOptionPane.showMessageDialog(this, "Bienvenido usuario de reportes");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Tipo de usuario desconocido: " + tipo,
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void ListarPais() {
        LimpiarTable();
        List<Pais> ListarPais = pais.ListarPais();
        modelo = (DefaultTableModel) TablePais.getModel();
        Object[] obj = new Object[2];
        for (int i = 0; i < ListarPais.size(); i++) {
            obj[0] = ListarPais.get(i).getId_pais();
            obj[1] = ListarPais.get(i).getNombre();
            modelo.addRow(obj);
        }
        TablePais.setModel(modelo);
    }

    public void ListarCategoria() {
        LimpiarTable();
        List<Categoria> ListarCategoria = categoria.ListarCategoria();
        modelo = (DefaultTableModel) TableCategoria.getModel();
        Object[] obj = new Object[2];
        for (int i = 0; i < ListarCategoria.size(); i++) {
            obj[0] = ListarCategoria.get(i).getId_categoria();
            obj[1] = ListarCategoria.get(i).getCategoria();
            modelo.addRow(obj);
        }
        TableCategoria.setModel(modelo);
    }

    public void ListarMateria() {
        LimpiarTable();
        List<Materia> ListarMateria = materia.ListarMateria();
        modelo = (DefaultTableModel) TableMateria.getModel();
        Object[] obj = new Object[3];
        for (int i = 0; i < ListarMateria.size(); i++) {
            obj[0] = ListarMateria.get(i).getId_materia();
            obj[1] = ListarMateria.get(i).getSigla();
            obj[2] = ListarMateria.get(i).getNombre();
            modelo.addRow(obj);
        }
        TableMateria.setModel(modelo);
    }

    public void ListarEditorial() {
        LimpiarTable();
        List<Editorial> ListarEditorial = editorial.ListarEditorial();
        modelo = (DefaultTableModel) TableEditorial.getModel();
        Object[] obj = new Object[5];
        for (int i = 0; i < ListarEditorial.size(); i++) {
            obj[0] = ListarEditorial.get(i).getId_editorial();
            obj[1] = ListarEditorial.get(i).getNombre();
            obj[2] = ListarEditorial.get(i).getNombrePais();
            obj[3] = ListarEditorial.get(i).getDireccion();
            obj[4] = ListarEditorial.get(i).getTelefono();
            modelo.addRow(obj);
        }
        TableEditorial.setModel(modelo);
    }

    public void ListarAutor() {
        LimpiarTable();
        List<Autor> ListarAutor = autor.ListarAutor();
        modelo = (DefaultTableModel) TableAutor.getModel();
        Object[] obj = new Object[4];
        for (int i = 0; i < ListarAutor.size(); i++) {
            obj[0] = ListarAutor.get(i).getId_autor();
            obj[1] = ListarAutor.get(i).getNombre();
            obj[2] = ListarAutor.get(i).getApellido();
            obj[3] = ListarAutor.get(i).getNombrePais();
            modelo.addRow(obj);
        }
        TableAutor.setModel(modelo);
    }

    public void ListarLibro() {
        LimpiarTable();
        List<Libro> ListarLibro = libro.ListarLibro();
        modelo = (DefaultTableModel) TableLibro.getModel();
        Object[] obj = new Object[14];
        for (int i = 0; i < ListarLibro.size(); i++) {
            obj[0] = ListarLibro.get(i).getId_libro();
            obj[1] = ListarLibro.get(i).getTitulo();
            obj[2] = ListarLibro.get(i).getCodigo();
            obj[3] = ListarLibro.get(i).getNombreAutor();
            obj[4] = ListarLibro.get(i).getNombreEditorial();
            obj[5] = ListarLibro.get(i).getNombreMateria();
            obj[6] = ListarLibro.get(i).getNombreCategoria();
            obj[7] = ListarLibro.get(i).getNombreEstado();
            obj[8] = ListarLibro.get(i).getStock();
            obj[9] = ListarLibro.get(i).getFecha();
            obj[10] = ListarLibro.get(i).getAnio();
            obj[11] = ListarLibro.get(i).getEdicion();
            obj[12] = ListarLibro.get(i).getDescripcion();
            obj[13] = ListarLibro.get(i).getTipo();
            modelo.addRow(obj);
        }
        TableLibro.setModel(modelo);
    }

    public void ListarLibro2() {
        LimpiarTable();
        List<Libro> ListarLibro2 = libro.ListarLibro2();
        modelo = (DefaultTableModel) TableLibro2.getModel();
        Object[] obj = new Object[7];
        for (int i = 0; i < ListarLibro2.size(); i++) {
            obj[0] = ListarLibro2.get(i).getCodigo();
            obj[1] = ListarLibro2.get(i).getTitulo();
            obj[2] = ListarLibro2.get(i).getNombreAutor();
            obj[3] = ListarLibro2.get(i).getNombreMateria();
            obj[4] = ListarLibro2.get(i).getNombreCategoria();
            obj[5] = ListarLibro2.get(i).getNombreEstado();
            obj[6] = ListarLibro2.get(i).getTipo();
            modelo.addRow(obj);
        }
        TableLibro2.setModel(modelo);
    }

    public void ListarLibro3() {
        LimpiarTable();
        Libro libro3 = new Libro();
        libro3.setTitulo(cboxNombreLibro2.getSelectedItem().toString());
        libro3.setNombreCategoria(cboxCategoriaLibro2.getSelectedItem().toString());
        libro3.setNombreMateria(cboxMateriaLibro2.getSelectedItem().toString());
        libro3.setNombreAutor(cboxAutorLibro2.getSelectedItem().toString());
        List<Libro> ListarLibro3 = libro.ListarLibro3(libro3);
        modelo = (DefaultTableModel) TableLibro2.getModel();
        Object[] obj = new Object[7];
        for (int i = 0; i < ListarLibro3.size(); i++) {
            obj[0] = ListarLibro3.get(i).getCodigo();
            obj[1] = ListarLibro3.get(i).getTitulo();
            obj[2] = ListarLibro3.get(i).getNombreAutor();
            obj[3] = ListarLibro3.get(i).getNombreMateria();
            obj[4] = ListarLibro3.get(i).getNombreCategoria();
            obj[5] = ListarLibro3.get(i).getNombreEstado();
            obj[6] = ListarLibro3.get(i).getTipo();
            modelo.addRow(obj);
        }
        TableLibro2.setModel(modelo);
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    //DESDE AQUI COMIENZA MULTAS

   
    
    public void listarTodasLasMultas() {

    DefaultTableModel modelo = (DefaultTableModel) TableMultasUsuario.getModel();
    modelo.setRowCount(0);

    List<Multa> lista = multaDao.listarMultas(); // ← trae TODAS

    for (Multa m : lista) {
        modelo.addRow(new Object[]{
            m.getId_multa(),
            m.getId_prestamo(),
            m.getId_usuario(),
            m.getNombreUsuario(),
            m.getNombreLibro(),
            m.getDias_retraso(),
            m.getMonto(),
            m.getEstado()
        });
    }
   }

  
  
    public void listarMultasUsuario(String carnet) {
    DefaultTableModel modelo = (DefaultTableModel) TableMultasUsuario.getModel();
    modelo.setRowCount(0);

    List<Multa> lista = multaDao.listarMultasPorCarnet(carnet);

    for (Multa m : lista) {
        modelo.addRow(new Object[]{
            m.getId_multa(),
            m.getId_prestamo(),
            m.getId_usuario(),
            m.getNombreUsuario(),
            m.getNombreLibro(),
            m.getDias_retraso(),
            m.getMonto(),
            m.getEstado()
        });
    }
    } 
    
    
    public void listarMultasPagadas() {
    DefaultTableModel modelo = (DefaultTableModel) tableMultaspagadas.getModel();
    modelo.setRowCount(0);

    List<Multa> lista = multaDao.listarMultasPorEstado("Pagada");

    for (Multa m : lista) {
        modelo.addRow(new Object[]{
            m.getId_multa(),
            m.getId_prestamo(),
            m.getId_usuario(),
            m.getNombreUsuario(),
            m.getNombreLibro(),
            m.getDias_retraso(),
            m.getMonto(),
            m.getEstado()
        });
    }
    }
    public void listarMultasSinPagar() {
      DefaultTableModel modelo = (DefaultTableModel) TableMultassinpagar.getModel();
    modelo.setRowCount(0);

    List<Multa> lista = multaDao.listarMultasPorEstado("Activa");

    for (Multa m : lista) {
        modelo.addRow(new Object[]{
            m.getId_multa(),
            m.getId_prestamo(),
            m.getId_usuario(),
            m.getNombreUsuario(),
            m.getNombreLibro(),
            m.getDias_retraso(),
            m.getMonto(),
            m.getEstado()
        });
    }
    }
    public Integer obtenerIdMultaSeleccionada() {

    // Tabla 1
    int fila = TableMultasUsuario.getSelectedRow();
    if (fila != -1) {
        return Integer.valueOf(TableMultasUsuario.getValueAt(fila, 0).toString());
    }

    // Tabla 2
    fila = tableMultaspagadas.getSelectedRow();
    if (fila != -1) {
        return Integer.valueOf(tableMultaspagadas.getValueAt(fila, 0).toString());
    }

    // Tabla 3
    fila = TableMultassinpagar.getSelectedRow();
    if (fila != -1) {
        return Integer.valueOf(TableMultassinpagar.getValueAt(fila, 0).toString());
    }

    return null; // No seleccionó nada
    }
    public void listarMultasPagadasEnTabla() {
    DefaultTableModel modelo = (DefaultTableModel) tableMultaspagadas.getModel();
    modelo.setRowCount(0);

    List<Multa_pagada> lista = multaPagadaDao.listarPagosPagados(); // Estado = 1

    for (Multa_pagada mp : lista) {
        modelo.addRow(new Object[]{
            mp.getId_multa_pagada(),  // ID del pago
            mp.getId_multa(),         // ID de la multa original
            mp.getIdPrestamo(),       // ID del préstamo (si lo agregaste)
            mp.getNombreCompletoUsuario(),
            mp.getTituloLibro(),
            mp.getDiasRetraso(),
            mp.getMontoFormateado(),
            mp.getFechaFormateada()  // Fecha del pago
        });
    }
    }
    
    // ========== MÉTODO 2: Buscar por carnet (AMBAS TABLAS) ==========
/**
 * Busca multas Y pagos por carnet y los muestra en TableMultasUsuario
 */
    public void listarMultasYPagosPorCarnet(String carnet) {
        DefaultTableModel modelo = (DefaultTableModel) TableMultasUsuario.getModel();
        modelo.setRowCount(0);

        // 1. BUSCAR MULTAS ACTIVAS
        List<Multa> listMultas = multaDao.listarMultasPorCarnet(carnet);
        for (Multa m : listMultas) {
            modelo.addRow(new Object[]{
                "M-" + m.getId_multa(),  // Identificador con prefijo M-
                m.getId_prestamo(),
                m.getId_usuario(),
                m.getNombreUsuario(),
                m.getNombreLibro(),
                m.getDias_retraso(),
                m.getMonto(),
                m.getEstado()
            });
        }

    // 2. BUSCAR PAGOS DE MULTAS
    List<Multa_pagada> listPagos = multaPagadaDao.listarPagosPorCarnet(carnet);
    for (Multa_pagada mp : listPagos) {
        modelo.addRow(new Object[]{
            "P-" + mp.getId_multa_pagada(),  // Identificador con prefijo P-
            mp.getIdPrestamo(),
            mp.getId_multa(),  // Aquí mostramos el ID de la multa original
            mp.getNombreCompletoUsuario(),
            mp.getTituloLibro(),
            mp.getDiasRetraso(),
            mp.getMontoFormateado(),
            mp.getEstadoTexto() + " (" + mp.getFechaFormateada() + ")"
        });
    }
    }
    // ========== MÉTODO 3: Obtener ID seleccionado (detecta si es Multa o Pago) ==========
/**
 * Retorna el ID y tipo de registro seleccionado
 * @return Array [tipo, id] donde tipo = "MULTA" o "PAGO"
 */
public String[] obtenerIdYTipoSeleccionado() {
   int fila;
    String idStr;

    // ✅ Verificar TableMultasUsuario (puede tener multas activas Y pagos)
    fila = TableMultasUsuario.getSelectedRow();
    if (fila != -1) {
        idStr = TableMultasUsuario.getValueAt(fila, 0).toString();
        if (idStr.startsWith("M-")) {
            return new String[]{"MULTA", idStr.substring(2)};
        } else if (idStr.startsWith("P-")) {
            return new String[]{"PAGO", idStr.substring(2)};
        } else {
            // Si no tiene prefijo, asumir que es multa
            return new String[]{"MULTA", idStr};
        }
    }

    // ✅ Verificar TableMultassinpagar (solo multas activas)
    fila = TableMultassinpagar.getSelectedRow();
    if (fila != -1) {
        idStr = TableMultassinpagar.getValueAt(fila, 0).toString();
        if (idStr.startsWith("M-")) {
            return new String[]{"MULTA", idStr.substring(2)};
        } else {
            return new String[]{"MULTA", idStr};
        }
    }

    // ❌ NO verificar tableMultaspagadas - solo se usa para generar facturas

    return null; // No seleccionó nada en las tablas permitidas
}

private void generarFacturaPDF(Multa_pagada mp) {
    Document documento = new Document();
    
    try {
        // Crear directorio si no existe
        String directorioFacturas = "src/Factura";
        File directorio = new File(directorioFacturas);
        if (!directorio.exists()) {
            directorio.mkdirs();
            System.out.println("Directorio creado: " + directorioFacturas);
        }
        
        // Ruta completa del archivo
        String ruta = directorioFacturas + "/Factura_" + mp.getNumeroFactura() + ".pdf";
        
        PdfWriter.getInstance(documento, new FileOutputStream(ruta));
        documento.open();


        // ENCABEZADO
        Image logo = Image.getInstance("src/Img/SISINf.png"); // Ajusta la ruta
        logo.scaleToFit(100, 100);
        logo.setAlignment(Element.ALIGN_CENTER);
        documento.add(logo);

        Paragraph titulo = new Paragraph("FACTURA DE PAGO DE MULTA\n\n",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK));
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);

        // INFORMACIÓN DE LA FACTURA
        documento.add(new Paragraph("Número de Factura: " + mp.getNumeroFactura(),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        documento.add(new Paragraph("Fecha de Emisión: " + mp.getFechaFormateada()));
        documento.add(new Paragraph("\n"));

        // DATOS DEL CLIENTE
        Paragraph datosCliente = new Paragraph("DATOS DEL USUARIO",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.DARK_GRAY));
        documento.add(datosCliente);
        
        PdfPTable tablaCliente = new PdfPTable(2);
        tablaCliente.setWidthPercentage(100);
        tablaCliente.setWidths(new float[]{1, 2});
        
        tablaCliente.addCell("Nombre:");
        tablaCliente.addCell(mp.getNombreCompletoUsuario());
        tablaCliente.addCell("Carnet:");
        tablaCliente.addCell(mp.getCarnetUsuario());
        tablaCliente.addCell("Teléfono:");
        tablaCliente.addCell(mp.getTelefonoUsuario());
        tablaCliente.addCell("Domicilio:");
        tablaCliente.addCell(mp.getDomicilioUsuario());
        
        documento.add(tablaCliente);
        documento.add(new Paragraph("\n"));

        // DETALLE DEL PAGO
        Paragraph detalle = new Paragraph("DETALLE DEL PAGO",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.DARK_GRAY));
        documento.add(detalle);
        
        PdfPTable tablaDetalle = new PdfPTable(4);
        tablaDetalle.setWidthPercentage(100);
        tablaDetalle.setWidths(new float[]{3, 2, 1, 1});
        
        // Encabezados
        PdfPCell celda = new PdfPCell(new Phrase("Libro",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tablaDetalle.addCell(celda);
        
        celda = new PdfPCell(new Phrase("Concepto",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tablaDetalle.addCell(celda);
        
        celda = new PdfPCell(new Phrase("Días",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tablaDetalle.addCell(celda);
        
        celda = new PdfPCell(new Phrase("Monto",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tablaDetalle.addCell(celda);

        // Datos
        tablaDetalle.addCell(mp.getTituloLibro());
        tablaDetalle.addCell("Multa por retraso");
        tablaDetalle.addCell(String.valueOf(mp.getDiasRetraso()));
        tablaDetalle.addCell(mp.getMontoFormateado());

        documento.add(tablaDetalle);
        documento.add(new Paragraph("\n"));

        // TOTAL
        PdfPTable tablaTotal = new PdfPTable(2);
        tablaTotal.setWidthPercentage(50);
        tablaTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        PdfPCell celdaTotal = new PdfPCell(new Phrase("TOTAL:",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        celdaTotal.setBorder(Rectangle.NO_BORDER);
        celdaTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablaTotal.addCell(celdaTotal);
        
        celdaTotal = new PdfPCell(new Phrase(mp.getMontoFormateado(),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        celdaTotal.setBackgroundColor(BaseColor.YELLOW);
        celdaTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaTotal.addCell(celdaTotal);
        
        documento.add(tablaTotal);

        // PIE DE PÁGINA
        documento.add(new Paragraph("\n\n"));
        Paragraph pie = new Paragraph(
            "___________________________________\n\n" +
            "Gracias por su pago\n" +
            "Sistema de Gestión Realizado por intrepidos\n" +
            new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()),
            FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.GRAY));
        pie.setAlignment(Element.ALIGN_CENTER);
        documento.add(pie);

        documento.close();

        JOptionPane.showMessageDialog(null, 
            "Factura generada exitosamente en:\n" + ruta);

        // Abrir el PDF automáticamente
        java.awt.Desktop.getDesktop().open(new File(ruta));

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, 
            "Error al generar factura: " + e.getMessage());
        e.printStackTrace();
    }
}


private void agregarCeldaEncabezadoReporte(PdfPTable tabla, String texto) {
    PdfPCell celda = new PdfPCell(new Phrase(texto,
        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.WHITE)));
    celda.setBackgroundColor(BaseColor.DARK_GRAY);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
    celda.setPadding(5);
    tabla.addCell(celda);
}

/**
 * Agrega celda de resumen para reportes
 */
private void agregarCeldaResumenReporte(PdfPTable tabla, String etiqueta, String valor, BaseColor colorFondo) {
    PdfPCell celdaLabel = new PdfPCell(new Phrase(etiqueta,
        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
    celdaLabel.setBorder(Rectangle.NO_BORDER);
    celdaLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
    celdaLabel.setPadding(5);
    celdaLabel.setBackgroundColor(colorFondo);
    tabla.addCell(celdaLabel);

    PdfPCell celdaValor = new PdfPCell(new Phrase(valor,
        FontFactory.getFont(FontFactory.HELVETICA, 10)));
    celdaValor.setBorder(Rectangle.NO_BORDER);
    celdaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
    celdaValor.setPadding(5);
    celdaValor.setBackgroundColor(colorFondo);
    tabla.addCell(celdaValor);
}
    
    
    
    
    
    
  
    //HASTA AQUI SON LAS MULTAS

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel15 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelBiblioteca = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        cboxLibro = new javax.swing.JComboBox<>();
        btnPrestamo = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnMultas = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        TableLibro2 = new javax.swing.JTable();
        cboxNombreLibro2 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cboxAutorLibro2 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        cboxMateriaLibro2 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        cboxCategoriaLibro2 = new javax.swing.JComboBox<>();
        btnBuscarLibro2 = new javax.swing.JButton();
        btnMasPrestadoLibro2 = new javax.swing.JButton();
        btnTodosLibro = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        txtCodigoLibro = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        txtAnioLibro = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        txtEdicionLibro = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        txtDescripcionLibro = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableLibro = new javax.swing.JTable();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        txtTituloLibro = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        cboxAutorLibro = new javax.swing.JComboBox<>();
        cboxMateriaLibro = new javax.swing.JComboBox<>();
        cboxEditorialLibro = new javax.swing.JComboBox<>();
        cboxCategoriaLibro = new javax.swing.JComboBox<>();
        cboxEstadoLibro = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        Stock = new javax.swing.JLabel();
        txtStockLibro = new javax.swing.JTextField();
        btnGuardarLibro = new javax.swing.JButton();
        btnEliminarLibro = new javax.swing.JButton();
        btnActualizarLibro = new javax.swing.JButton();
        btnNuevoLibro = new javax.swing.JButton();
        txtIdLibro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cboxTipoLibro = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableEditorial = new javax.swing.JTable();
        jButton37 = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        cboxPaisEditorial = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        btnGuardarEditorial = new javax.swing.JButton();
        btnActualizarEditorial = new javax.swing.JButton();
        txtNombreEditorial = new javax.swing.JTextField();
        txtDireccionEditorial = new javax.swing.JTextField();
        txtTelefonoEditorial = new javax.swing.JTextField();
        btnEliminarEditorial = new javax.swing.JButton();
        btnNuevoEditorial = new javax.swing.JButton();
        txtIdEditorial = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableAutor = new javax.swing.JTable();
        jComboBox17 = new javax.swing.JComboBox<>();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        cboxPaisAutor = new javax.swing.JComboBox<>();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        btnGuardarAutor = new javax.swing.JButton();
        btnActualizarAutor = new javax.swing.JButton();
        txtNombreAutor = new javax.swing.JTextField();
        txtApellidoAutor = new javax.swing.JTextField();
        btnEliminarAutor = new javax.swing.JButton();
        btnNuevoAutor = new javax.swing.JButton();
        txtIdAutor = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtUsuarioPrestamo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtApellidoPrestamo = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        txtNombrePrestamo = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        txtTelefonoPrestamo = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        txtDomicilioPrestamo = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        txtCodigoPrestamo = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        txtTituloPrestamo = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        txtStockPrestamo = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        txtEdicionPrestamo = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        txtidUsuarioPrestamo = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        txtidLibroPrestamo = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        txtFechaDevolucion = new com.toedter.calendar.JDateChooser();
        jScrollPane11 = new javax.swing.JScrollPane();
        TablePrestamo = new javax.swing.JTable();
        btnGuardarPrestamo = new javax.swing.JButton();
        btnEliminarPrestamo = new javax.swing.JButton();
        txtidPrestamo = new javax.swing.JTextField();
        btnActualizarPrestamo = new javax.swing.JButton();
        btnDevolucionPrestamo = new javax.swing.JButton();
        btnPrestamosPDF = new javax.swing.JButton();
        btnGraficarPrestamo = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        txtBuscarCarnetMul = new javax.swing.JTextField();
        btnBuscarCarnetMul = new javax.swing.JButton();
        btnPagarMulta = new javax.swing.JButton();
        btnGenerarFactura = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableMultasUsuario = new javax.swing.JTable();
        btnActulizarMultas = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tableMultaspagadas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableMultassinpagar = new javax.swing.JTable();
        btnReporMultasPagadas = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnReporMultasSinPagadas = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txtNombreCategoria = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        btnGuardarCategoria = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        TableCategoria = new javax.swing.JTable();
        btnEliminarCategoria = new javax.swing.JButton();
        txtIdCategoria = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtSiglaMateria = new javax.swing.JTextField();
        txtNombreMateria = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        TableMateria = new javax.swing.JTable();
        btnGuardarMateria = new javax.swing.JButton();
        btnEliminarMateria = new javax.swing.JButton();
        txtIdMateria = new javax.swing.JTextField();
        btnActualizarMateria = new javax.swing.JButton();
        btnNuevoMateria = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        txtNombrePais = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        btnGuardarPais = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        TablePais = new javax.swing.JTable();
        txtIdPais = new javax.swing.JTextField();
        btnEliminarPais = new javax.swing.JButton();
        btnActualizarPais = new javax.swing.JButton();
        btnNuevoPais = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        txtCarnetUsuario = new javax.swing.JTextField();
        txtNombreUsuario = new javax.swing.JTextField();
        txtApellidoUsuario = new javax.swing.JTextField();
        txtDomicilioUsuario = new javax.swing.JTextField();
        txtTelefonoUsuario = new javax.swing.JTextField();
        cboxTipoUsuario = new javax.swing.JComboBox<>();
        cboxCargoUsuario = new javax.swing.JComboBox<>();
        cboxCarreraUsuario = new javax.swing.JComboBox<>();
        btnGuardarUsuario = new javax.swing.JButton();
        btnActualizarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        btnNuevoUsuario = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        TableUsuario = new javax.swing.JTable();
        jLabel69 = new javax.swing.JLabel();
        cboxEstadoPrestamoUsuario = new javax.swing.JComboBox<>();
        btnAplicarFiltroUsuario = new javax.swing.JButton();
        cboxEFiltroUsuario = new javax.swing.JComboBox<>();
        cboxFiltroValorUsuario = new javax.swing.JComboBox<>();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        btngenerarpdfusuario = new javax.swing.JButton();
        btnAjuste = new javax.swing.JButton();
        btnAnalisis = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/SISINf-128x128.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 14, -1, -1));

        jLabelBiblioteca.setText("BIBLIOTECA");
        getContentPane().add(jLabelBiblioteca, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 69, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnInicio.setText("INICIO");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        cboxLibro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Libros", "Autor", "Editorial", "Usuario", "Categoria", "Pais", "Materia" }));
        cboxLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxLibroActionPerformed(evt);
            }
        });

        btnPrestamo.setText("Prestamos");
        btnPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestamoActionPerformed(evt);
            }
        });

        btnReportes.setText("Reportes");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnMultas.setText("Multas");
        btnMultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboxLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnMultas, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMultas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReportes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboxLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrestamo, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 840, -1));

        jLabel3.setText("NUEVAS ADQUISICIONES");

        jLabel4.setText("MAS SOLICITADO EN LA SEMANA");

        jLabel9.setText("PROGRAMACION POO");

        jLabel10.setText("Aprende algo respecto a la programacion orientada");

        jLabel11.setText("PROGRAMACION POO");

        jLabel12.setText("Aprende algo respecto a la programacion orientada");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8435, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(333, 333, 333))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)))
                .addContainerGap(505, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel3);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jButton27.setText("Agregar libro");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/images (1)-30x30.png"))); // NOI18N
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/1126709-30x30.png"))); // NOI18N
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel17.setText("BUSQUEDA DE LIBROS");

        TableLibro2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "AUTOR", "MATERIA", "CATEGORIA", "ESTADO", "TIPO"
            }
        ));
        jScrollPane14.setViewportView(TableLibro2);
        if (TableLibro2.getColumnModel().getColumnCount() > 0) {
            TableLibro2.getColumnModel().getColumn(0).setMinWidth(75);
            TableLibro2.getColumnModel().getColumn(0).setMaxWidth(75);
            TableLibro2.getColumnModel().getColumn(4).setMinWidth(120);
            TableLibro2.getColumnModel().getColumn(4).setMaxWidth(120);
            TableLibro2.getColumnModel().getColumn(5).setMinWidth(100);
            TableLibro2.getColumnModel().getColumn(5).setMaxWidth(100);
            TableLibro2.getColumnModel().getColumn(6).setMinWidth(100);
            TableLibro2.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        cboxNombreLibro2.setEditable(true);

        jLabel18.setText("Nombre:");

        jLabel22.setText("Autor:");

        cboxAutorLibro2.setEditable(true);

        jLabel26.setText("Materia:");

        cboxMateriaLibro2.setEditable(true);

        jLabel27.setText("Categoria:");

        cboxCategoriaLibro2.setEditable(true);

        btnBuscarLibro2.setText("Buscar");
        btnBuscarLibro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLibro2ActionPerformed(evt);
            }
        });

        btnMasPrestadoLibro2.setText("Más prestados");

        btnTodosLibro.setText("Todos los libros");
        btnTodosLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosLibroActionPerformed(evt);
            }
        });

        jButton1.setText("Limpiar Campos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17)
                                    .addGroup(jPanel23Layout.createSequentialGroup()
                                        .addComponent(jButton28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton29))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(cboxMateriaLibro2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnMasPrestadoLibro2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTodosLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscarLibro2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel22))
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboxCategoriaLibro2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboxNombreLibro2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxAutorLibro2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)))
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 1134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jButton27)
                .addGap(127, 127, 127))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton28)
                                    .addComponent(jButton29))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addGroup(jPanel23Layout.createSequentialGroup()
                                        .addComponent(cboxNombreLibro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboxAutorLibro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboxMateriaLibro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26))
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboxCategoriaLibro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel27))))
                                .addGap(41, 41, 41)
                                .addComponent(btnBuscarLibro2)
                                .addGap(18, 18, 18)
                                .addComponent(btnMasPrestadoLibro2)
                                .addGap(18, 18, 18)
                                .addComponent(btnTodosLibro)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton27)))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7584, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel4);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        jLabel81.setText("Titulo:");

        jLabel82.setText("Codigo: ");

        jLabel83.setText("Autor: ");

        jLabel84.setText("Materia: ");

        txtCodigoLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoLibroActionPerformed(evt);
            }
        });

        jLabel86.setText("Editorial:");

        jLabel87.setText("Año:");

        txtAnioLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioLibroActionPerformed(evt);
            }
        });

        jLabel88.setText("Edicion:");

        txtEdicionLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdicionLibroActionPerformed(evt);
            }
        });

        jLabel89.setText("Categoria:");

        jLabel90.setText("Descripcion: ");

        txtDescripcionLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionLibroActionPerformed(evt);
            }
        });

        TableLibro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TITULO", "COD", "AUTOR", "EDITORIAL", "MATERIA", "CATEGORIA", "ESTADO", "STOCK", "FECHA", "AÑO", "EDICION", "DESCRIPCION", "Tipo"
            }
        ));
        TableLibro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableLibroMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(TableLibro);
        if (TableLibro.getColumnModel().getColumnCount() > 0) {
            TableLibro.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableLibro.getColumnModel().getColumn(0).setMaxWidth(200);
            TableLibro.getColumnModel().getColumn(1).setPreferredWidth(300);
            TableLibro.getColumnModel().getColumn(1).setMaxWidth(300);
            TableLibro.getColumnModel().getColumn(2).setPreferredWidth(80);
            TableLibro.getColumnModel().getColumn(2).setMaxWidth(200);
            TableLibro.getColumnModel().getColumn(3).setPreferredWidth(250);
            TableLibro.getColumnModel().getColumn(3).setMaxWidth(250);
            TableLibro.getColumnModel().getColumn(4).setPreferredWidth(170);
            TableLibro.getColumnModel().getColumn(4).setMaxWidth(170);
            TableLibro.getColumnModel().getColumn(5).setPreferredWidth(250);
            TableLibro.getColumnModel().getColumn(5).setMaxWidth(250);
            TableLibro.getColumnModel().getColumn(6).setPreferredWidth(250);
            TableLibro.getColumnModel().getColumn(6).setMaxWidth(250);
            TableLibro.getColumnModel().getColumn(7).setPreferredWidth(250);
            TableLibro.getColumnModel().getColumn(7).setMaxWidth(250);
            TableLibro.getColumnModel().getColumn(8).setPreferredWidth(100);
            TableLibro.getColumnModel().getColumn(8).setMaxWidth(100);
            TableLibro.getColumnModel().getColumn(9).setPreferredWidth(100);
            TableLibro.getColumnModel().getColumn(9).setMaxWidth(100);
            TableLibro.getColumnModel().getColumn(10).setPreferredWidth(80);
            TableLibro.getColumnModel().getColumn(10).setMaxWidth(200);
            TableLibro.getColumnModel().getColumn(11).setPreferredWidth(120);
            TableLibro.getColumnModel().getColumn(11).setMaxWidth(200);
            TableLibro.getColumnModel().getColumn(12).setPreferredWidth(150);
            TableLibro.getColumnModel().getColumn(12).setMaxWidth(200);
            TableLibro.getColumnModel().getColumn(13).setMinWidth(80);
            TableLibro.getColumnModel().getColumn(13).setMaxWidth(200);
        }

        jButton51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/images (1)-30x30.png"))); // NOI18N
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jButton52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/1126709-30x30.png"))); // NOI18N
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jButton53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/images-30x30.png"))); // NOI18N
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        txtTituloLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTituloLibroKeyTyped(evt);
            }
        });

        jLabel57.setText("LIBRO");

        cboxAutorLibro.setEditable(true);

        cboxMateriaLibro.setEditable(true);

        cboxEditorialLibro.setEditable(true);

        cboxCategoriaLibro.setEditable(true);

        cboxEstadoLibro.setEditable(true);

        jLabel58.setText("Estado:");

        Stock.setText("Stock:");

        txtStockLibro.setText("1");

        btnGuardarLibro.setText("Guardar");
        btnGuardarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarLibroActionPerformed(evt);
            }
        });

        btnEliminarLibro.setText("Eliminar");
        btnEliminarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLibroActionPerformed(evt);
            }
        });

        btnActualizarLibro.setText("Actualizar");
        btnActualizarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarLibroActionPerformed(evt);
            }
        });

        btnNuevoLibro.setText("Nuevo");
        btnNuevoLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoLibroActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo Libro:");

        cboxTipoLibro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Copia", "Original" }));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(255, 255, 255)
                                .addComponent(jLabel57))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel82)
                                            .addComponent(jLabel83)
                                            .addComponent(jLabel81))
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel31Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel31Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTituloLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel31Layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(cboxAutorLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel84)
                                            .addComponent(Stock))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtStockLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboxMateriaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel87)
                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                            .addComponent(jLabel86)
                                            .addGap(12, 12, 12)
                                            .addComponent(cboxEditorialLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel89)
                                            .addComponent(jLabel88))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEdicionLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboxCategoriaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAnioLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addComponent(jLabel58)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cboxEstadoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(64, 64, 64)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboxTipoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jButton53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton51))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnActualizarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNuevoLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminarLibro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGuardarLibro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(42, 42, 42)))
                        .addGap(61, 61, 61))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel90)
                            .addComponent(txtDescripcionLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(457, 841, Short.MAX_VALUE))))
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel81)
                                    .addComponent(txtTituloLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel82)
                                    .addComponent(txtCodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel83)
                                    .addComponent(cboxAutorLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel84)
                                    .addComponent(cboxMateriaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel86)
                                    .addComponent(cboxEditorialLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel31Layout.createSequentialGroup()
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel87)
                                            .addComponent(txtAnioLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnGuardarLibro))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel88)
                                            .addComponent(txtEdicionLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnEliminarLibro))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel89)
                                            .addComponent(cboxCategoriaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnActualizarLibro)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(cboxTipoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jButton51)
                    .addComponent(jLabel57)
                    .addComponent(jButton53)
                    .addComponent(jButton52))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStockLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboxEstadoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58)
                        .addComponent(Stock)
                        .addComponent(btnNuevoLibro)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDescripcionLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7850, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel5);

        jLabel54.setText("EDITORIAL");

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        TableEditorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "PAIS", "DIRECCION", "TELEFONO"
            }
        ));
        TableEditorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableEditorialMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TableEditorial);
        if (TableEditorial.getColumnModel().getColumnCount() > 0) {
            TableEditorial.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableEditorial.getColumnModel().getColumn(0).setMaxWidth(400);
        }

        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/images (1)-30x30.png"))); // NOI18N
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton37)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jButton37)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        jLabel70.setText("AGREGAR/EDITAR EDITORIAL");

        jLabel71.setText("Nombre: ");

        cboxPaisEditorial.setEditable(true);
        cboxPaisEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxPaisEditorialActionPerformed(evt);
            }
        });

        jLabel72.setText("Pais: ");

        jLabel73.setText("Direccion: ");

        jLabel74.setText("Telefono:");

        btnGuardarEditorial.setText("Guardar");
        btnGuardarEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEditorialActionPerformed(evt);
            }
        });

        btnActualizarEditorial.setText("Actualizar");
        btnActualizarEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEditorialActionPerformed(evt);
            }
        });

        txtNombreEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreEditorialActionPerformed(evt);
            }
        });

        txtDireccionEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionEditorialActionPerformed(evt);
            }
        });

        txtTelefonoEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoEditorialActionPerformed(evt);
            }
        });

        btnEliminarEditorial.setText("Eliminar");
        btnEliminarEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEditorialActionPerformed(evt);
            }
        });

        btnNuevoEditorial.setText("Nuevo");
        btnNuevoEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoEditorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel70)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombreEditorial))
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel72))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDireccionEditorial)
                                    .addComponent(cboxPaisEditorial, 0, 209, Short.MAX_VALUE)))
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEliminarEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(btnGuardarEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnNuevoEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnActualizarEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefonoEditorial)
                        .addGap(126, 126, 126))))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(txtIdEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel70)
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(txtNombreEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(cboxPaisEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(txtDireccionEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(txtTelefonoEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarEditorial)
                    .addComponent(btnActualizarEditorial))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarEditorial)
                    .addComponent(btnNuevoEditorial))
                .addGap(18, 18, 18)
                .addComponent(txtIdEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel54))
                .addContainerGap(7931, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel54)
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel6);

        jLabel75.setText("BUSCAR AUTOR");

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jLabel76.setText("Filtrar por");

        jTextField14.setText("Search");
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        TableAutor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "NACIONALIDAD"
            }
        ));
        TableAutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableAutorMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TableAutor);
        if (TableAutor.getColumnModel().getColumnCount() > 0) {
            TableAutor.getColumnModel().getColumn(0).setPreferredWidth(40);
            TableAutor.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "buscar por", "Item 2", "Item 3", "Item 4" }));
        jComboBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox17ActionPerformed(evt);
            }
        });

        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/images-30x30.png"))); // NOI18N
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/1126709-30x30.png"))); // NOI18N
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/images (1)-30x30.png"))); // NOI18N
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton46)
                        .addGap(26, 26, 26)
                        .addComponent(jButton45)
                        .addGap(18, 18, 18)
                        .addComponent(jButton44)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton44)
                    .addComponent(jButton45)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton46))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        jLabel77.setText("AGREGAR/EDITAR AUTOR");

        jLabel78.setText("Nombre: ");

        cboxPaisAutor.setEditable(true);
        cboxPaisAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxPaisAutorActionPerformed(evt);
            }
        });

        jLabel79.setText("Apellido:");

        jLabel80.setText("Nacionalidad: ");

        btnGuardarAutor.setText("Guardar");
        btnGuardarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAutorActionPerformed(evt);
            }
        });

        btnActualizarAutor.setText("Actualizar");
        btnActualizarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarAutorActionPerformed(evt);
            }
        });

        txtNombreAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreAutorActionPerformed(evt);
            }
        });

        txtApellidoAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoAutorActionPerformed(evt);
            }
        });

        btnEliminarAutor.setText("Eliminar");
        btnEliminarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAutorActionPerformed(evt);
            }
        });

        btnNuevoAutor.setText("Nuevo");
        btnNuevoAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAutorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel77)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel78)
                            .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(txtApellidoAutor)))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(btnGuardarAutor)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizarAutor))
                            .addComponent(cboxPaisAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(btnEliminarAutor)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevoAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtIdAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel77)
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(txtNombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txtApellidoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(cboxPaisAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarAutor)
                    .addComponent(btnGuardarAutor))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarAutor)
                    .addComponent(btnNuevoAutor))
                .addGap(18, 18, 18)
                .addComponent(txtIdAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel75))
                .addContainerGap(7940, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", jPanel7);

        jLabel5.setText("Registrar Prestamo");

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 878, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );

        jLabel14.setText("Confirmar Prestamo");

        jLabel15.setText("Carnet");

        txtUsuarioPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioPrestamoActionPerformed(evt);
            }
        });
        txtUsuarioPrestamo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioPrestamoKeyPressed(evt);
            }
        });

        jLabel16.setText("Nombre");

        txtApellidoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoPrestamoActionPerformed(evt);
            }
        });

        jLabel60.setText("Apellido");

        txtNombrePrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombrePrestamoActionPerformed(evt);
            }
        });

        jLabel85.setText("Telefono");

        txtTelefonoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoPrestamoActionPerformed(evt);
            }
        });

        jLabel91.setText("Domicilio");

        txtDomicilioPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioPrestamoActionPerformed(evt);
            }
        });

        jLabel92.setText("Codigo Libro");

        txtCodigoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoPrestamoActionPerformed(evt);
            }
        });
        txtCodigoPrestamo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoPrestamoKeyPressed(evt);
            }
        });

        jLabel93.setText("Titulo");

        txtTituloPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloPrestamoActionPerformed(evt);
            }
        });
        txtTituloPrestamo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTituloPrestamoKeyPressed(evt);
            }
        });

        jLabel94.setText("Edicion");

        txtStockPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockPrestamoActionPerformed(evt);
            }
        });

        jLabel95.setText("Stock");

        txtEdicionPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdicionPrestamoActionPerformed(evt);
            }
        });

        jLabel96.setText("ID Usuario");

        txtidUsuarioPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidUsuarioPrestamoActionPerformed(evt);
            }
        });

        jLabel97.setText("ID Libro");

        txtidLibroPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidLibroPrestamoActionPerformed(evt);
            }
        });

        jLabel98.setText("Fecha Devolucion");

        TablePrestamo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Prestamo", "Carnet", "Nombre", "Codigo Libro", "Libro", "Fecha de Prestamo", "Fecha de Devolucion", "Estado"
            }
        ));
        TablePrestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePrestamoMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(TablePrestamo);

        btnGuardarPrestamo.setText("Nuevo");
        btnGuardarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPrestamoActionPerformed(evt);
            }
        });

        btnEliminarPrestamo.setText("Eliminar");
        btnEliminarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPrestamoActionPerformed(evt);
            }
        });

        txtidPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidPrestamoActionPerformed(evt);
            }
        });

        btnActualizarPrestamo.setText("Actualizar");
        btnActualizarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPrestamoActionPerformed(evt);
            }
        });

        btnDevolucionPrestamo.setText("Registrar Devolucion");
        btnDevolucionPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolucionPrestamoActionPerformed(evt);
            }
        });

        btnPrestamosPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/1126709-30x30.png"))); // NOI18N
        btnPrestamosPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestamosPDFActionPerformed(evt);
            }
        });

        btnGraficarPrestamo.setText("Graficar");
        btnGraficarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarPrestamoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel14)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel60)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtApellidoPrestamo))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtUsuarioPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtNombrePrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(60, 60, 60)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addComponent(jLabel85)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(txtTelefonoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addComponent(jLabel91)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(txtDomicilioPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(0, 0, Short.MAX_VALUE))))
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addComponent(jLabel96)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtidUsuarioPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabel97)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtidLibroPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtidPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel98)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel94)
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtStockPrestamo)
                                            .addComponent(txtEdicionPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel92)
                                            .addComponent(jLabel93)
                                            .addComponent(jLabel95))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCodigoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTituloPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(49, 49, 49))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(btnGuardarPrestamo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnActualizarPrestamo)
                                        .addGap(82, 82, 82))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(btnEliminarPrestamo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPrestamosPDF)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnGraficarPrestamo)
                                        .addGap(63, 63, 63)))))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDevolucionPrestamo))))
                .addContainerGap(7911, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtidPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDevolucionPrestamo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtUsuarioPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel96)
                            .addComponent(txtidUsuarioPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtNombrePrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60)
                                    .addComponent(txtApellidoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel85)
                                    .addComponent(txtTelefonoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel91)
                                    .addComponent(txtDomicilioPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel92)
                            .addComponent(txtCodigoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel97)
                            .addComponent(txtidLibroPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel93)
                            .addComponent(txtTituloPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel94)
                            .addComponent(txtEdicionPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel95))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtStockPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel98)
                            .addComponent(txtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarPrestamo)
                            .addComponent(btnActualizarPrestamo))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarPrestamo)
                            .addComponent(btnPrestamosPDF)
                            .addComponent(btnGraficarPrestamo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel8);

        jLabel33.setText("PAGAR MULTA MULTAS");

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setText("Buscar Usuario con multa");

        txtBuscarCarnetMul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarCarnetMulActionPerformed(evt);
            }
        });

        btnBuscarCarnetMul.setText("Buscar");
        btnBuscarCarnetMul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCarnetMulActionPerformed(evt);
            }
        });

        btnPagarMulta.setText("Pagar Multa");
        btnPagarMulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarMultaActionPerformed(evt);
            }
        });

        btnGenerarFactura.setText("Generar  factura");
        btnGenerarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarFacturaActionPerformed(evt);
            }
        });

        jLabel6.setText("Carnet");

        TableMultasUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id_multa", "Id_prestamo", "Id_Usuario", "Usuario", "Libro", "Dias de retraso", "Monto", "Estado/Fecha"
            }
        ));
        jScrollPane2.setViewportView(TableMultasUsuario);

        btnActulizarMultas.setText("Actualizar Multas");
        btnActulizarMultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActulizarMultasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel34))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscarCarnetMul, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnBuscarCarnetMul)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnPagarMulta)
                        .addGap(56, 56, 56)
                        .addComponent(btnGenerarFactura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(229, 229, 229))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnActulizarMultas)
                .addGap(146, 146, 146))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarCarnetMul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnBuscarCarnetMul))
                .addGap(8, 8, 8)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActulizarMultas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPagarMulta)
                    .addComponent(btnGenerarFactura))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        tableMultaspagadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id_multa", "Id_prestamo", "Id_Usuario", "Usuario", "Libro", "Dias de retraso", "Monto", "Fecha Pago"
            }
        ));
        jScrollPane13.setViewportView(tableMultaspagadas);

        TableMultassinpagar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id_multa", "Id_prestamo", "Id_Usuario", "Usuario", "Libro", "Dias de retraso", "Monto", "Estado"
            }
        ));
        jScrollPane4.setViewportView(TableMultassinpagar);

        btnReporMultasPagadas.setText("Multas pagadas");
        btnReporMultasPagadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporMultasPagadasActionPerformed(evt);
            }
        });

        jLabel8.setText("Generar reporte de:");

        btnReporMultasSinPagadas.setText("Multas sin pagar");
        btnReporMultasSinPagadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporMultasSinPagadasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnReporMultasPagadas, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(btnReporMultasSinPagadas, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(btnReporMultasPagadas))
                    .addComponent(btnReporMultasSinPagadas))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel7.setText("MULTAS PAGADAS");

        jLabel13.setText("MULTAS SIN PAGAR");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(436, 436, 436)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel33)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8305, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab7", jPanel10);

        jLabel35.setText("Estadisticas");

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/182094.png"))); // NOI18N

        jLabel38.setText("Libros mas solicitados");

        jLabel39.setText("Descripcion");

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/182094.png"))); // NOI18N

        jLabel42.setText("Descripcion");

        jLabel43.setText("Libros sin solicitar");

        jLabel41.setText("Filtrar por:");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "semana", "Item 2", "Item 3", "Item 4" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/images (1)-30x30.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/1126709-30x30.png"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/images-30x30.png"))); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel44.setText("Filtros");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "programacion 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nuevo", "Item 2", "Item 3", "Item 4" }));
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });

        jLabel45.setText("Fecha Inicio");

        jTextField7.setText("   /      /");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.setText("   /      /");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel46.setText("Fecha Fin");

        jLabel47.setText("Resumen General");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel44))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel38)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(185, 185, 185)
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton16)
                                .addGap(18, 18, 18)
                                .addComponent(jButton17)
                                .addGap(18, 18, 18)
                                .addComponent(jButton18))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel42))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel43)))
                        .addGap(107, 107, 107)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(987, 8449, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel36))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel39)
                                        .addComponent(jLabel41)
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton18)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton17)
                                .addComponent(jButton16)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );

        jTabbedPane1.addTab("tab8", jPanel11);

        jButton19.setText("CAMBIAR CONTRASEÑA");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("PREGUNTAS FRECUENTES");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton19)
                    .addComponent(jButton20))
                .addContainerGap(9070, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jButton19)
                .addGap(34, 34, 34)
                .addComponent(jButton20)
                .addContainerGap(490, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab9", jPanel12);

        jLabel48.setText("CATEGORIA");

        txtNombreCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCategoriaActionPerformed(evt);
            }
        });

        jLabel49.setText("Nombre:");

        btnGuardarCategoria.setText("Guardar");
        btnGuardarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCategoriaActionPerformed(evt);
            }
        });

        TableCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CATEGORIA"
            }
        ));
        TableCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCategoriaMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(TableCategoria);
        if (TableCategoria.getColumnModel().getColumnCount() > 0) {
            TableCategoria.getColumnModel().getColumn(0).setPreferredWidth(100);
            TableCategoria.getColumnModel().getColumn(0).setMaxWidth(500);
            TableCategoria.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        btnEliminarCategoria.setText("Eliminar");
        btnEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCategoriaActionPerformed(evt);
            }
        });

        jButton33.setText("Actualizar");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setText("Nuevo");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardarCategoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(121, 121, 121)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8082, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardarCategoria)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarCategoria)
                        .addGap(18, 18, 18)
                        .addComponent(jButton33)
                        .addGap(18, 18, 18)
                        .addComponent(jButton34)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab10", jPanel24);

        jLabel50.setText("MATERIA");

        jLabel51.setText("Sigla:");

        jLabel52.setText("Nombre:");

        TableMateria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "SIGLA", "MATERIA"
            }
        ));
        TableMateria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMateriaMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(TableMateria);
        if (TableMateria.getColumnModel().getColumnCount() > 0) {
            TableMateria.getColumnModel().getColumn(0).setPreferredWidth(100);
            TableMateria.getColumnModel().getColumn(0).setMaxWidth(50);
            TableMateria.getColumnModel().getColumn(1).setPreferredWidth(200);
            TableMateria.getColumnModel().getColumn(2).setPreferredWidth(500);
        }

        btnGuardarMateria.setText("Guardar");
        btnGuardarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMateriaActionPerformed(evt);
            }
        });

        btnEliminarMateria.setText("Eliminar");
        btnEliminarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMateriaActionPerformed(evt);
            }
        });

        btnActualizarMateria.setText("Actualizar");
        btnActualizarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarMateriaActionPerformed(evt);
            }
        });

        btnNuevoMateria.setText("Nuevo");
        btnNuevoMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoMateriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreMateria)
                            .addComponent(txtSiglaMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnNuevoMateria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGuardarMateria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminarMateria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnActualizarMateria, javax.swing.GroupLayout.Alignment.LEADING))))))
                .addGap(79, 79, 79)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8056, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(txtSiglaMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(txtNombreMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtIdMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnGuardarMateria)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarMateria)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarMateria)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevoMateria)))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab11", jPanel26);

        txtNombrePais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombrePaisActionPerformed(evt);
            }
        });

        jLabel53.setText("PAIS");

        jLabel56.setText("Nombre:");

        btnGuardarPais.setText("Guardar");
        btnGuardarPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPaisActionPerformed(evt);
            }
        });

        TablePais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE"
            }
        ));
        TablePais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePaisMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(TablePais);
        if (TablePais.getColumnModel().getColumnCount() > 0) {
            TablePais.getColumnModel().getColumn(0).setPreferredWidth(40);
            TablePais.getColumnModel().getColumn(0).setMaxWidth(500);
            TablePais.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        btnEliminarPais.setText("Eliminar");
        btnEliminarPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPaisActionPerformed(evt);
            }
        });

        btnActualizarPais.setText("Actualizar");
        btnActualizarPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPaisActionPerformed(evt);
            }
        });

        btnNuevoPais.setText("Nuevo");
        btnNuevoPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdPais, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnGuardarPais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminarPais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnNuevoPais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnActualizarPais, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtNombrePais, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(72, 72, 72)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8185, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombrePais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardarPais)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarPais)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarPais)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevoPais)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab12", jPanel27);

        jLabel59.setText("USUARIO");

        jLabel61.setText("CARNET");

        jLabel62.setText("NOMBRE");

        jLabel63.setText("APELLIDO");

        jLabel64.setText("DOMICILIO");

        jLabel65.setText("TIPO ");

        jLabel66.setText("TELEFONO");

        jLabel67.setText("CARGO");

        jLabel68.setText("CARRERA");

        txtIdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdUsuarioActionPerformed(evt);
            }
        });

        txtCarnetUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarnetUsuarioActionPerformed(evt);
            }
        });
        txtCarnetUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCarnetUsuarioKeyPressed(evt);
            }
        });

        cboxTipoUsuario.setEditable(true);
        cboxTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxTipoUsuarioActionPerformed(evt);
            }
        });

        cboxCargoUsuario.setEditable(true);
        cboxCargoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxCargoUsuarioActionPerformed(evt);
            }
        });

        cboxCarreraUsuario.setEditable(true);
        cboxCarreraUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxCarreraUsuarioActionPerformed(evt);
            }
        });

        btnGuardarUsuario.setText("GUARDAR");
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        btnActualizarUsuario.setText("ACTUALIZAR");
        btnActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setText("ELIMINAR");
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        btnNuevoUsuario.setText("NUEVO");
        btnNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoUsuarioActionPerformed(evt);
            }
        });

        TableUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CARNET", "NOMBRE", "APELLIDO", "DOMICILIO", "TIPO", "TELEFONO", "CARGO", "CARRERA", "PRESTAMO"
            }
        ));
        TableUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUsuarioMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(TableUsuario);
        if (TableUsuario.getColumnModel().getColumnCount() > 0) {
            TableUsuario.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableUsuario.getColumnModel().getColumn(0).setMaxWidth(500);
            TableUsuario.getColumnModel().getColumn(1).setPreferredWidth(20);
            TableUsuario.getColumnModel().getColumn(2).setPreferredWidth(50);
            TableUsuario.getColumnModel().getColumn(3).setPreferredWidth(50);
            TableUsuario.getColumnModel().getColumn(4).setPreferredWidth(60);
            TableUsuario.getColumnModel().getColumn(5).setPreferredWidth(5);
            TableUsuario.getColumnModel().getColumn(6).setPreferredWidth(15);
            TableUsuario.getColumnModel().getColumn(7).setPreferredWidth(10);
            TableUsuario.getColumnModel().getColumn(8).setPreferredWidth(50);
            TableUsuario.getColumnModel().getColumn(9).setPreferredWidth(20);
        }

        jLabel69.setText("ESTADO");

        cboxEstadoPrestamoUsuario.setEditable(true);
        cboxEstadoPrestamoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxEstadoPrestamoUsuarioActionPerformed(evt);
            }
        });

        btnAplicarFiltroUsuario.setText("APLICAR");
        btnAplicarFiltroUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarFiltroUsuarioActionPerformed(evt);
            }
        });

        cboxEFiltroUsuario.setEditable(true);
        cboxEFiltroUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxEFiltroUsuarioItemStateChanged(evt);
            }
        });
        cboxEFiltroUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxEFiltroUsuarioActionPerformed(evt);
            }
        });

        cboxFiltroValorUsuario.setEditable(true);
        cboxFiltroValorUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxFiltroValorUsuarioActionPerformed(evt);
            }
        });

        jLabel99.setText("FILTRO:");

        jLabel100.setText("VALOR");

        btngenerarpdfusuario.setText("GENERAR PDF");
        btngenerarpdfusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerarpdfusuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62)
                            .addComponent(jLabel61)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64)
                            .addComponent(jLabel65)
                            .addComponent(jLabel66)
                            .addComponent(jLabel67)
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69))
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel32Layout.createSequentialGroup()
                                        .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(btnNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel32Layout.createSequentialGroup()
                                        .addComponent(btnGuardarUsuario)
                                        .addGap(31, 31, 31)
                                        .addComponent(btnActualizarUsuario))))
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefonoUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboxCargoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboxCarreraUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxEstadoPrestamoUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCarnetUsuario)
                                        .addComponent(txtNombreUsuario)
                                        .addComponent(txtApellidoUsuario)
                                        .addComponent(txtDomicilioUsuario)
                                        .addComponent(cboxTipoUsuario, 0, 215, Short.MAX_VALUE))))))
                    .addComponent(jLabel59))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(cboxEFiltroUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboxFiltroValorUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(929, 929, 929)
                        .addComponent(btnAplicarFiltroUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btngenerarpdfusuario)
                        .addGap(7179, 7179, 7179))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel59)
                                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAplicarFiltroUsuario)
                                    .addComponent(btngenerarpdfusuario))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(txtCarnetUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62)
                            .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(txtApellidoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64)
                            .addComponent(txtDomicilioUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(txtTelefonoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxCargoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(cboxCarreraUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxEstadoPrestamoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarUsuario)
                            .addComponent(btnActualizarUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevoUsuario)
                            .addComponent(btnEliminarUsuario)))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxEFiltroUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboxFiltroValorUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99)
                            .addComponent(jLabel100))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab13", jPanel32);

        btnAjuste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/74551-30x30.png"))); // NOI18N
        btnAjuste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjusteActionPerformed(evt);
            }
        });

        btnAnalisis.setText("ANALISIS Y ESTADISTICAS");
        btnAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisActionPerformed(evt);
            }
        });

        btnCerrar.setText("CERRAR SESION");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(268, 268, 268)
                            .addComponent(btnAnalisis)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCerrar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(817, 817, 817)
                            .addComponent(btnAjuste))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalisis)
                    .addComponent(btnCerrar))
                .addGap(21, 21, 21)
                .addComponent(btnAjuste)
                .addGap(79, 79, 79)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 890));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnalisisActionPerformed

    private void cboxLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxLibroActionPerformed
        // TODO add your handling code here:
        String seleccion = (String) cboxLibro.getSelectedItem();
        if (seleccion.equals("Pais")) {
            jTabbedPane1.setSelectedIndex(11);
            LimpiarTable();
            ListarPais();
        } else if (seleccion.equals("Categoria")) {
            jTabbedPane1.setSelectedIndex(9);
            LimpiarTable();
            ListarCategoria();
        } else if (seleccion.equals("Materia")) {
            jTabbedPane1.setSelectedIndex(10);
            LimpiarTable();
            ListarMateria();
        } else if (seleccion.equals("Editorial")) {
            jTabbedPane1.setSelectedIndex(3);
            fun.VaciarCombo(cboxPaisEditorial);
            editorial.ConsultarPais(cboxPaisEditorial);
            LimpiarTable();
            LimpiarEditorial();
            ListarEditorial();
        } else if (seleccion.equals("Autor")) {
            jTabbedPane1.setSelectedIndex(4);
            fun.VaciarCombo(cboxPaisAutor);
            autor.ConsultarPais(cboxPaisAutor);
            LimpiarTable();
            LimpiarAutor();
            ListarAutor();
        } else if (seleccion.equals("Libros")) {
            jTabbedPane1.setSelectedIndex(2);

            txtStockLibro.setText("1");
            fun.VaciarCombo(cboxAutorLibro);
            fun.VaciarCombo(cboxMateriaLibro);
            fun.VaciarCombo(cboxEditorialLibro);
            fun.VaciarCombo(cboxCategoriaLibro);
            fun.VaciarCombo(cboxEstadoLibro);

            libro.ConsultarAutor(cboxAutorLibro);
            libro.ConsultarMateria(cboxMateriaLibro);
            libro.ConsultarEditorial(cboxEditorialLibro);
            libro.ConsultarCategoria(cboxCategoriaLibro);
            libro.ConsultarEstado(cboxEstadoLibro);

            LimpiarLibro();
            LimpiarTable();
            ListarLibro();
        } else if (seleccion.equals("Usuario")) {
            jTabbedPane1.setSelectedIndex(12);
            fun.VaciarCombo(cboxCarreraUsuario);
            fun.VaciarCombo(cboxCargoUsuario);
            fun.VaciarCombo(cboxEstadoPrestamoUsuario);
            fun.VaciarCombo(cboxTipoUsuario);

            usuario.ConsultarCarreras(cboxCarreraUsuario);
            usuario.ConsultarCargo(cboxCargoUsuario);
            usuario.ConsultarEstadoUsuario(cboxEstadoPrestamoUsuario);
            usuario.ConsultarTipoUsuario(cboxTipoUsuario);

            LimpiarUsuario();
            LimpiarTable();
            ListarUsuario();
        }
    }//GEN-LAST:event_cboxLibroActionPerformed

    private void btnPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestamoActionPerformed
        // TODO add your handling code here:
        LimpiarTable();
        ListarPrestamo();
        jTabbedPane1.setSelectedIndex(5);

    }//GEN-LAST:event_btnPrestamoActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(7); 

        fun.VaciarCombo(cboxAutorLibro2);
        fun.VaciarCombo(cboxMateriaLibro2);
        fun.VaciarCombo(cboxCategoriaLibro2);
        fun.VaciarCombo(cboxNombreLibro2);

        libro.ConsultarAutor(cboxAutorLibro2);
        libro.ConsultarMateria(cboxMateriaLibro2);
        libro.ConsultarCategoria(cboxCategoriaLibro2);
        libro.ConsultarNombre(cboxNombreLibro2);

        LimpiarLibro2();
        LimpiarTable();

        ListarLibro2();
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Deseas cerrar sesión?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            // Cierra la ventana actual
            this.dispose();

            // Abre la ventana de Login
            Login login = new Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null); // centra la ventana
        }
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAjusteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjusteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAjusteActionPerformed
//DESDE AQUI COMIENZA MI INTERFAS DE MULTAS


    private void btngenerarpdfusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarpdfusuarioActionPerformed
        pdf();
    }//GEN-LAST:event_btngenerarpdfusuarioActionPerformed

    private void cboxFiltroValorUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxFiltroValorUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxFiltroValorUsuarioActionPerformed

    private void cboxEFiltroUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxEFiltroUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxEFiltroUsuarioActionPerformed

    private void cboxEFiltroUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxEFiltroUsuarioItemStateChanged
        /*if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            actualizarComboValorUsuario(); // método que llena cboxFiltroValorUsuario
        }*/
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String filtroSeleccionado = cboxEFiltroUsuario.getSelectedItem().toString();

            // Limpiar ComboBox de valores
            cboxFiltroValorUsuario.removeAllItems();

            if (filtroSeleccionado.equals("Sin filtro")) {
                // Si es Sin filtro, no ponemos valores y deshabilitamos el ComboBox
                cboxFiltroValorUsuario.setEnabled(false);
            } else {
                // Habilitamos el ComboBox de valores
                cboxFiltroValorUsuario.setEnabled(true);

                // Llenar los valores según el filtro
                switch (filtroSeleccionado) {
                    case "Tipo Usuario":
                    usuario.ConsultarTipoUsuario(cboxFiltroValorUsuario);
                    break;
                    case "Cargo":
                    usuario.ConsultarCargo(cboxFiltroValorUsuario);
                    break;
                    case "Carrera":
                    usuario.ConsultarCarreras(cboxFiltroValorUsuario);
                    break;
                    case "Estado Préstamo":
                    usuario.ConsultarEstadoUsuario(cboxFiltroValorUsuario);
                    break;
                }
            }
        }
    }//GEN-LAST:event_cboxEFiltroUsuarioItemStateChanged

    private void btnAplicarFiltroUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarFiltroUsuarioActionPerformed
        /*String filtro = "";
        String valor = "";

        if(cboxEFiltroUsuario.getSelectedItem() != null && cboxFiltroValorUsuario.getSelectedItem() != null){
            filtro = cboxEFiltroUsuario.getSelectedItem().toString();
            valor = cboxFiltroValorUsuario.getSelectedItem().toString();

            List<Usuario> listaFiltrada = usuario.listarPorFiltro(filtro, valor);
            LimpiarTable();
            LimpiarUsuario(); // Limpia la tabla antes de llenarla

            // Llenar la tabla con los resultados filtrados
            DefaultTableModel model = (DefaultTableModel) TableUsuario.getModel(); // Reemplaza 'tablaUsuarios' por tu JTable
            for(Usuario u : listaFiltrada){
                Object[] fila = new Object[]{
                    u.getId_usuario(),
                    u.getCarnet(),
                    u.getNombre(),
                    u.getApellido(),
                    u.getDomicilio(),
                    u.getTipoUsuarioNombre(),
                    u.getTelefono(),
                    u.getCargoNombre(),
                    u.getCarreraNombre(),
                    u.getEstadoPrestamo()
                };
                model.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un filtro y un valor.");
        }*/
        LimpiarTable(); // Limpiamos la tabla antes de mostrar

        String filtro = cboxEFiltroUsuario.getSelectedItem() != null ? cboxEFiltroUsuario.getSelectedItem().toString() : "";
        String valor = cboxFiltroValorUsuario.getSelectedItem() != null ? cboxFiltroValorUsuario.getSelectedItem().toString() : "";

        List<Usuario> lista;

        if (filtro.equals("Sin filtro")) {
            lista = usuario.ListarUsuario(); // Todos los usuarios activos
        } else {
            lista = usuario.listarPorFiltro(filtro, valor); // Aplicar filtro específico
        }

        DefaultTableModel model = (DefaultTableModel) TableUsuario.getModel();

        for (Usuario u : lista) {
            model.addRow(new Object[]{u.getId_usuario(), u.getCarnet(), u.getNombre(), u.getApellido(), u.getDomicilio(),
                u.getTipoUsuarioNombre(), u.getTelefono(), u.getCargoNombre(), u.getCarreraNombre(),
                u.getEstadoPrestamo()});
        }
    }//GEN-LAST:event_btnAplicarFiltroUsuarioActionPerformed

    private void cboxEstadoPrestamoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxEstadoPrestamoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxEstadoPrestamoUsuarioActionPerformed

    private void TableUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUsuarioMouseClicked
        int fila = TableUsuario.rowAtPoint(evt.getPoint());
        txtIdUsuario.setText(TableUsuario.getValueAt(fila, 0).toString());
        txtCarnetUsuario.setText(TableUsuario.getValueAt(fila, 1).toString());
        txtNombreUsuario.setText(TableUsuario.getValueAt(fila, 2).toString());
        txtApellidoUsuario.setText(TableUsuario.getValueAt(fila, 3).toString());
        txtDomicilioUsuario.setText(TableUsuario.getValueAt(fila, 4).toString());

        // Para los combobox, establecer el valor seleccionado
        cboxTipoUsuario.setSelectedItem(TableUsuario.getValueAt(fila, 5).toString());

        txtTelefonoUsuario.setText(TableUsuario.getValueAt(fila, 6).toString());

        cboxCargoUsuario.setSelectedItem(TableUsuario.getValueAt(fila, 7).toString());
        cboxCarreraUsuario.setSelectedItem(TableUsuario.getValueAt(fila, 8).toString());
        cboxEstadoPrestamoUsuario.setSelectedItem(TableUsuario.getValueAt(fila, 9).toString());
    }//GEN-LAST:event_TableUsuarioMouseClicked

    private void btnNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoUsuarioActionPerformed
        LimpiarUsuario();
    }//GEN-LAST:event_btnNuevoUsuarioActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        if (!"".equals(txtIdUsuario.getText())) {
            String nombreCompleto = txtNombreUsuario.getText() + " " + txtApellidoUsuario.getText();
            int pregunta = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de eliminar al usuario:\n"
                + "Nombre: " + nombreCompleto + "\n"
                + "Carnet: " + txtCarnetUsuario.getText(),
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (pregunta == JOptionPane.YES_OPTION) {
                try {
                    int id = Integer.parseInt(txtIdUsuario.getText());
                    boolean eliminado = usuario.EliminarUsuario(id);

                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                        LimpiarTable();
                        LimpiarUsuario();
                        ListarUsuario();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el usuario");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: ID de usuario inválido");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario de la tabla para eliminar");
        }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void btnActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarUsuarioActionPerformed
        if ("".equals(txtIdUsuario.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtCarnetUsuario.getText())
                && !"".equals(txtNombreUsuario.getText())
                && !"".equals(txtApellidoUsuario.getText())
                && !"".equals(txtDomicilioUsuario.getText())
                && !"".equals(txtTelefonoUsuario.getText())
                && !"".equals(cboxTipoUsuario.getSelectedItem())
                && !"".equals(cboxCargoUsuario.getSelectedItem())
                && !"".equals(cboxCarreraUsuario.getSelectedItem())) {

                us.setCarnet(txtCarnetUsuario.getText());
                us.setNombre(txtNombreUsuario.getText());
                us.setApellido(txtApellidoUsuario.getText());
                us.setDomicilio(txtDomicilioUsuario.getText());
                us.setTelefono(txtTelefonoUsuario.getText());
                us.setId_tipo_usuario(usuario.ObtenerIdTipoUsuario(cboxTipoUsuario.getSelectedItem().toString()));
                us.setId_cargo(usuario.ObtenerIdCargo(cboxCargoUsuario.getSelectedItem().toString()));
                us.setId_carrera(usuario.ObtenerIdCarrera(cboxCarreraUsuario.getSelectedItem().toString()));
                us.setId_usuario(Integer.parseInt(txtIdUsuario.getText()));
                us.setId_estado_prestamo(usuario.ObtenerIdEstadoPrestamo(cboxEstadoPrestamoUsuario.getSelectedItem().toString()));

                boolean resultado = usuario.ModificarUsuario(us);
                if (resultado) {
                    JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el usuario");
                }

                LimpiarTable();
                ListarUsuario();
                LimpiarUsuario();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos Carnet, Nombre, Apellido, Tipo Usuario, Cargo y Carrera son obligatorios");
            }
        }
    }//GEN-LAST:event_btnActualizarUsuarioActionPerformed

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed
        if (!"".equals(txtCarnetUsuario.getText())
            && !"".equals(txtNombreUsuario.getText())
            && !"".equals(txtApellidoUsuario.getText())
            && !"".equals(txtDomicilioUsuario.getText())
            && !"".equals(cboxTipoUsuario.getSelectedItem())
            && !"".equals(txtTelefonoUsuario.getText())
            && !"".equals(cboxCargoUsuario.getSelectedItem())
            && !"".equals(cboxCarreraUsuario.getSelectedItem())
            && !"".equals(cboxEstadoPrestamoUsuario.getSelectedItem())) {

            String carnet = txtCarnetUsuario.getText();
            if (usuario.existeCarnet(carnet)) {
                JOptionPane.showMessageDialog(null, "El carnet " + carnet + " ya está registrado. No se puede guardar.");
                return; // Salir del método sin guardar
            }

            boolean error;

            us.setCarnet(txtCarnetUsuario.getText());
            us.setNombre(txtNombreUsuario.getText());
            us.setApellido(txtApellidoUsuario.getText());
            us.setDomicilio(txtDomicilioUsuario.getText());
            us.setTelefono(txtTelefonoUsuario.getText());
            us.setId_tipo_usuario(usuario.ObtenerIdTipoUsuario(cboxTipoUsuario.getSelectedItem().toString()));
            us.setId_cargo(usuario.ObtenerIdCargo(cboxCargoUsuario.getSelectedItem().toString()));
            us.setId_carrera(usuario.ObtenerIdCarrera(cboxCarreraUsuario.getSelectedItem().toString()));
            us.setId_estado_prestamo(usuario.ObtenerIdEstadoPrestamo(cboxEstadoPrestamoUsuario.getSelectedItem().toString()));
            us.setEstado(1);
            error = usuario.RegistrarUsuario(us);
            if (error == true) {
                JOptionPane.showMessageDialog(null, "Usuario Registrado con Éxito!!!");
            }
            LimpiarTable();
            ListarUsuario();
        } else {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        }
    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void cboxCarreraUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxCarreraUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxCarreraUsuarioActionPerformed

    private void cboxCargoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxCargoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxCargoUsuarioActionPerformed

    private void cboxTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxTipoUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxTipoUsuarioActionPerformed

    private void txtCarnetUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarnetUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCarnetUsuario.getText())) {
                String cod = txtCarnetUsuario.getText();
                us = usuario.BuscarUsuario(cod);
                if (us.getNombre() != null) {
                    txtNombreUsuario.setText("" + us.getNombre());
                    txtApellidoUsuario.setText("" + us.getApellido());
                    txtDomicilioUsuario.setText("" + us.getDomicilio());
                    txtTelefonoUsuario.setText("" + us.getTelefono());
                    cboxTipoUsuario.setSelectedItem(us.getTipoUsuarioNombre());
                    cboxCargoUsuario.setSelectedItem(us.getCargoNombre());
                    cboxCarreraUsuario.setSelectedItem(us.getCarreraNombre());
                    cboxEstadoPrestamoUsuario.setSelectedItem(us.getEstadoPrestamo());
                    txtNombreUsuario.requestFocus();
                } else {
                    LimpiarUsuario();
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado con CI: " + cod);
                    txtCarnetUsuario.requestFocus();
                    txtCarnetUsuario.selectAll();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el CI del usuario a buscar");
                txtCarnetUsuario.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCarnetUsuarioKeyPressed

    private void txtCarnetUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarnetUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarnetUsuarioActionPerformed

    private void txtIdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdUsuarioActionPerformed

    private void btnNuevoPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPaisActionPerformed
        // TODO add your handling code here:
        LimpiarPais();
    }//GEN-LAST:event_btnNuevoPaisActionPerformed

    private void btnActualizarPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPaisActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdPais.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtNombrePais.getText())) {
                pa.setNombre(txtNombrePais.getText());
                pa.setId_pais(Integer.parseInt(txtIdPais.getText()));
                if (pais.existePais(txtNombrePais.getText()) == false) {
                    boolean resultado = pais.ModificarPais(pa);
                    if (resultado) {
                        JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito");
                        LimpiarTable();
                        LimpiarPais();
                        ListarPais();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El país " + txtNombrePais.getText() + " ya ha sido registrado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
            }
        }
    }//GEN-LAST:event_btnActualizarPaisActionPerformed

    private void btnEliminarPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPaisActionPerformed
        if (!"".equals(txtNombrePais.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar: " + txtNombrePais.getText());
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdPais.getText());
                pais.EliminarPais(id);
                LimpiarTable();
                LimpiarPais();
                ListarPais();
            }
        }
    }//GEN-LAST:event_btnEliminarPaisActionPerformed

    private void TablePaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePaisMouseClicked
        int fila = TablePais.rowAtPoint(evt.getPoint());
        txtIdPais.setText(TablePais.getValueAt(fila, 0).toString());
        txtNombrePais.setText(TablePais.getValueAt(fila, 1).toString());
    }//GEN-LAST:event_TablePaisMouseClicked

    private void btnGuardarPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPaisActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtNombrePais.getText())) {
            boolean error;
            if (pais.existePais(txtNombrePais.getText()) == false) {
                pa.setNombre(txtNombrePais.getText());
                pa.setEstado(1);
                error = pais.registrarPais(pa);
                if (error == true) {
                    JOptionPane.showMessageDialog(null, "País Registrado con Éxito!!!");
                    LimpiarPais();
                }
                LimpiarTable();
                ListarPais();
            } else {
                JOptionPane.showMessageDialog(null, "El país " + txtNombrePais.getText() + " ya ha sido registrado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos se encuentran vacios");
        }
    }//GEN-LAST:event_btnGuardarPaisActionPerformed

    private void txtNombrePaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombrePaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePaisActionPerformed

    private void btnNuevoMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoMateriaActionPerformed
        // TODO add your handling code here:
        LimpiarMateria();
    }//GEN-LAST:event_btnNuevoMateriaActionPerformed

    private void btnActualizarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarMateriaActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdMateria.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtNombreMateria.getText()) && !"".equals(txtSiglaMateria.getText())) {
                if (materia.existeMateria(txtSiglaMateria.getText()) == false) {
                    boolean error;
                    ma.setNombre(txtNombreMateria.getText());
                    ma.setSigla(txtSiglaMateria.getText());
                    ma.setId_materia(Integer.parseInt(txtIdMateria.getText()));
                    error = materia.ModificarMateria(ma);
                    if (error == true) {
                        LimpiarTable();
                        LimpiarMateria();
                        ListarMateria();
                        JOptionPane.showMessageDialog(null, "Campos actualizados con exito");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La materia con sigla " + txtSiglaMateria.getText() + " ya ha sido registrada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
            }
        }
    }//GEN-LAST:event_btnActualizarMateriaActionPerformed

    private void btnEliminarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMateriaActionPerformed
        if (!"".equals(txtNombreMateria.getText()) || !"".equals(txtSiglaMateria.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar: " + txtNombreMateria.getText());
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdMateria.getText());
                materia.EliminarMateria(id);
                LimpiarTable();
                LimpiarMateria();
                ListarMateria();
            }
        }
    }//GEN-LAST:event_btnEliminarMateriaActionPerformed

    private void btnGuardarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMateriaActionPerformed
        if (!"".equals(txtSiglaMateria.getText()) && !"".equals(txtNombreMateria.getText())) {
            boolean error;
            if (materia.existeMateria(txtSiglaMateria.getText()) == false) {
                ma.setSigla(txtSiglaMateria.getText());
                ma.setNombre(txtNombreMateria.getText());
                ma.setEstado(1);
                error = materia.RegistrarMateria(ma);
                if (error == true) {
                    JOptionPane.showMessageDialog(null, "Materia Registrada con Exito!!!");
                    LimpiarTable();
                    LimpiarMateria();
                    ListarMateria();
                }
            } else {
                JOptionPane.showMessageDialog(null, "La materia con la sigla " + txtSiglaMateria.getText() + " ya ha sido registrado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos se encuentra vacios");
        }
    }//GEN-LAST:event_btnGuardarMateriaActionPerformed

    private void TableMateriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMateriaMouseClicked
        // TODO add your handling code here:
        int fila = TableMateria.rowAtPoint(evt.getPoint());
        txtIdMateria.setText(TableMateria.getValueAt(fila, 0).toString());
        txtSiglaMateria.setText(TableMateria.getValueAt(fila, 1).toString());
        txtNombreMateria.setText(TableMateria.getValueAt(fila, 2).toString());
    }//GEN-LAST:event_TableMateriaMouseClicked

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
        LimpiarCategoria();
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdCategoria.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtNombreCategoria.getText())) {
                if (categoria.existeCategoria(txtNombreCategoria.getText()) == false) {
                    boolean error;
                    ca.setCategoria(txtNombreCategoria.getText());
                    ca.setId_categoria(Integer.parseInt(txtIdCategoria.getText()));
                    error = categoria.ModificarCategoria(ca);
                    if (error == true) {
                        LimpiarTable();
                        LimpiarCategoria();
                        ListarCategoria();
                        JOptionPane.showMessageDialog(null, "Campos actualizados con exito");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La categoría " + txtNombreCategoria.getText() + " ya ha sido registrada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios");
            }
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void btnEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCategoriaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtNombreCategoria.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar: " + txtNombreCategoria.getText());
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdCategoria.getText());
                categoria.EliminarCategoria(id);
                LimpiarTable();
                LimpiarCategoria();
                ListarCategoria();
            }
        }
    }//GEN-LAST:event_btnEliminarCategoriaActionPerformed

    private void TableCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCategoriaMouseClicked
        // TODO add your handling code here:
        int fila = TableCategoria.rowAtPoint(evt.getPoint());
        txtIdCategoria.setText(TableCategoria.getValueAt(fila, 0).toString());
        txtNombreCategoria.setText(TableCategoria.getValueAt(fila, 1).toString());
    }//GEN-LAST:event_TableCategoriaMouseClicked

    private void btnGuardarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCategoriaActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtNombreCategoria.getText())) {
            boolean error;
            if (categoria.existeCategoria(txtNombreCategoria.getText()) == false) {
                ca.setCategoria(txtNombreCategoria.getText());
                ca.setEstado(1);
                error = categoria.RegistrarCategoria(ca);
                if (error == true) {
                    JOptionPane.showMessageDialog(null, "Categoria Registrada con Exito!!!");
                    LimpiarTable();
                    LimpiarCategoria();
                    ListarCategoria();
                }
            } else {
                JOptionPane.showMessageDialog(null, "La categoria " + txtNombreCategoria.getText() + " ya ha sido registrada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos se encuentran vacios");
        }
    }//GEN-LAST:event_btnGuardarCategoriaActionPerformed

    private void txtNombreCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCategoriaActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox9ActionPerformed

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void btnActulizarMultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActulizarMultasActionPerformed
        actualizarTodasLasTablas();
        JOptionPane.showMessageDialog(null, "Tablas actualizadas");
        }

        public void actualizarTodasLasTablas() {
            String carnet = txtBuscarCarnetMul.getText().trim();

            if (!carnet.isEmpty()) {
                listarMultasYPagosPorCarnet(carnet);
            } else {
                listarTodasLasMultas();
            }

            listarMultasPagadasEnTabla();
            listarMultasSinPagar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActulizarMultasActionPerformed

    private void btnGenerarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarFacturaActionPerformed
        // Solo permite seleccionar de la tabla de multas pagadas
    int fila = tableMultaspagadas.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(null, 
            "⚠️ Seleccione un PAGO de la tabla de MULTAS PAGADAS\n\n" +
            "Esta opción solo genera facturas de pagos ya registrados",
            "Sin selección",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int idPago = Integer.parseInt(tableMultaspagadas.getValueAt(fila, 0).toString());

        // Obtener datos completos del pago para la factura
        Multa_pagada mp = multaPagadaDao.obtenerDatosFactura(idPago);

        if (mp == null) {
            JOptionPane.showMessageDialog(null, 
                "❌ Error: No se encontraron datos del pago\n\n" +
                "ID de pago: " + idPago,
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar que tenga todos los datos necesarios
        if (mp.getNumeroFactura() == null || mp.getNumeroFactura().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "️ Este pago no tiene factura asociada\n\n" +
                "Puede ser un registro antiguo sin factura",
                "Sin factura",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Generar PDF de la factura
        generarFacturaPDF(mp);
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, 
            "Error al leer el ID del pago seleccionado",
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnGenerarFacturaActionPerformed

    private void btnPagarMultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarMultaActionPerformed
       String[] resultado = obtenerIdYTipoSeleccionado();

    if (resultado == null) {
        JOptionPane.showMessageDialog(null, 
            "Seleccione una multa activa para pagar\n\n" +
            "Puede seleccionar desde:\n" +
            "• Tabla de Todas las Multas\n" +
            "• Tabla de Multas Sin Pagar",
            "Sin selección",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Verificar que sea una MULTA, no un PAGO
    if (!resultado[0].equals("MULTA")) {
        JOptionPane.showMessageDialog(null, 
            " Seleccione una MULTA activa, no un pago ya registrado",
            "Selección incorrecta",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    int idMulta = Integer.parseInt(resultado[1]);

    // Buscar información de la multa
    Multa multa = multaDao.buscarPorIdCompleto(idMulta);

    if (multa == null) {
        JOptionPane.showMessageDialog(null, 
            "❌ No se encontró la multa seleccionada");
        return;
    }

    // Validar que la multa esté en estado "Activa"
    if (!multa.getEstado().equals("Activa")) {
        JOptionPane.showMessageDialog(null, 
            "⚠️ Esta multa ya fue pagada o está inactiva\n" +
            "Estado actual: " + multa.getEstado(),
            "Multa no disponible",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Confirmar pago con información detallada
    int confirm = JOptionPane.showConfirmDialog(null,
        "💰 ¿CONFIRMAR PAGO DE MULTA?\n\n" +
        "═══════════════════════════════\n" +
        "ID Multa:       #" + idMulta + "\n" +
        "Usuario:        " + multa.getNombreUsuario() + "\n" +
        "Libro:          " + multa.getNombreLibro() + "\n" +
        "Días retraso:   " + multa.getDias_retraso() + "\n" +
        "Monto a pagar:  " + String.format("%.2f Bs", multa.getMonto()) + "\n" +
        "═══════════════════════════════",
        "Confirmar Pago",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);

    if (confirm != JOptionPane.YES_OPTION) {
        return; // Usuario canceló
    }

    // Crear registro de pago
    Multa_pagada mp = new Multa_pagada();
    mp.setId_multa(idMulta);
    mp.setFecha(new Timestamp(System.currentTimeMillis()));
    mp.setEstado(1);

    // Registrar pago en la base de datos
    if (multaPagadaDao.registrarPagoMulta(mp)) {
        JOptionPane.showMessageDialog(null, 
            "✅ PAGO REGISTRADO EXITOSAMENTE\n\n" +
            "✓ Multa marcada como pagada\n" +
            "✓ Factura generada automáticamente en la base de datos\n" +
            "✓ Estado actualizado correctamente",
            "Pago Exitoso",
            JOptionPane.INFORMATION_MESSAGE);

        // Preguntar si desea generar el PDF de la factura
        int verFactura = JOptionPane.showConfirmDialog(null,
            "📄 ¿Desea generar el PDF de la factura ahora?",
            "Generar Factura PDF",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (verFactura == JOptionPane.YES_OPTION) {
            // Buscar el pago recién creado usando el ID de la multa
            List<Multa_pagada> pagos = multaPagadaDao.listarPagosMultas();
            Multa_pagada pagoEncontrado = null;
            
            // Buscar el pago más reciente de esta multa
            for (Multa_pagada p : pagos) {
                if (p.getId_multa() == idMulta) {
                    pagoEncontrado = p;
                    break;
                }
            }
            
            if (pagoEncontrado != null) {
                // Obtener datos completos para la factura
                Multa_pagada pagoCompleto = multaPagadaDao.obtenerDatosFactura(
                    pagoEncontrado.getId_multa_pagada());
                
                if (pagoCompleto != null) {
                    // Debug info (opcional - puedes comentar estas líneas)
                    System.out.println("=== DATOS PARA FACTURA ===");
                    System.out.println("Nombre: " + pagoCompleto.getNombreCompletoUsuario());
                    System.out.println("Carnet: " + pagoCompleto.getCarnetUsuario());
                    System.out.println("Monto: " + pagoCompleto.getMontoFormateado());
                    System.out.println("Libro: " + pagoCompleto.getTituloLibro());
                    System.out.println("========================");
                    
                    generarFacturaPDF(pagoCompleto);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "⚠️ Error: No se pudieron obtener datos completos para la factura");
                }
            } else {
                JOptionPane.showMessageDialog(null, 
                    "⚠️ Error: No se encontró el pago registrado");
            }
        }

        // Actualizar todas las tablas
        actualizarTodasLasTablas();
        
    } else {
        JOptionPane.showMessageDialog(null, 
            "❌ Error al registrar el pago\n\n" +
            "Por favor, intente nuevamente o contacte al administrador",
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnPagarMultaActionPerformed

    private void btnBuscarCarnetMulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCarnetMulActionPerformed
        String carnet = txtBuscarCarnetMul.getText().trim();

        if (carnet.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un carnet para buscar");
            return;
        }

        listarMultasYPagosPorCarnet(carnet);
    }//GEN-LAST:event_btnBuscarCarnetMulActionPerformed

    private void txtBuscarCarnetMulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCarnetMulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCarnetMulActionPerformed

    private void btnGraficarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarPrestamoActionPerformed
        // TODO add your handling code here:
        Grafico.GraficarPrestamos("2025-10-18", "2025-11-07");
    }//GEN-LAST:event_btnGraficarPrestamoActionPerformed

    private void btnPrestamosPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestamosPDFActionPerformed
        // TODO add your handling code here:
        pdfPrestamos();
    }//GEN-LAST:event_btnPrestamosPDFActionPerformed

    private void btnDevolucionPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolucionPrestamoActionPerformed
      // Validar que hay un préstamo seleccionado
    if (txtidPrestamo.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null,
            " Debe seleccionar un préstamo para registrar la devolución",
            "Sin selección",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    String iden = txtidPrestamo.getText().trim();
    pre = prestamo.BuscarPrestamo(iden);

    if (pre == null) {
        JOptionPane.showMessageDialog(null,
            "No se encontró el préstamo con ID: " + iden,
            "Préstamo no encontrado",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verificar que el préstamo no esté ya devuelto
    if (Integer.parseInt(pre.getEstadoPrestamo()) != 1) {
        JOptionPane.showMessageDialog(null,
            " Este préstamo ya fue registrado como devuelto\n\n" +
            "ID Préstamo: " + iden,
            "Préstamo ya devuelto",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    // === CALCULAR MULTA ANTES DE CONFIRMAR ===
    int diasRetraso = 0;
    float montoMulta = 0;
    boolean tieneMulta = false;

    try {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaDevolucionDT = LocalDateTime.parse(pre.getFecha_devolucion(), formato);
        LocalDate fechaLimite = fechaDevolucionDT.toLocalDate();
        LocalDate hoy = LocalDate.now();
        
        long dias = ChronoUnit.DAYS.between(fechaLimite, hoy);
        
        if (dias > 0) {
            diasRetraso = (int) dias;
            montoMulta = diasRetraso * 1.0f; // 1 Bs por día
            tieneMulta = true;
        }
    } catch (Exception e) {
        System.out.println("Error al calcular días de retraso: " + e.getMessage());
    }

    // === CONFIRMAR DEVOLUCIÓN (CON INFO DE MULTA) ===
    String mensajeConfirmacion;
    
    if (tieneMulta) {
        mensajeConfirmacion = 
            " CONFIRMAR DEVOLUCIÓN CON MULTA\n\n" +
            "═════════════════════════════════\n" +
            "ID Préstamo:      " + iden + "\n" +
            "Días de retraso:  " + diasRetraso + " día(s)\n" +
            "Multa a generar:  " + String.format("%.2f Bs", montoMulta) + "\n" +
            "═════════════════════════════════\n\n" +
            "Se generará automáticamente una MULTA por retraso.\n" +
            "¿Desea continuar con la devolución?";
    } else {
        mensajeConfirmacion = 
            "CONFIRMAR DEVOLUCIÓN A TIEMPO\n\n" +
            "═════════════════════════════════\n" +
            "ID Préstamo: " + iden + "\n" +
            "Estado:      Sin retraso ✓\n" +
            "═════════════════════════════════\n\n" +
            "¿Desea registrar la devolución?";
    }

    int pregunta = JOptionPane.showConfirmDialog(null,
        mensajeConfirmacion,
        "Confirmar Devolución",
        JOptionPane.YES_NO_OPTION,
        tieneMulta ? JOptionPane.WARNING_MESSAGE : JOptionPane.QUESTION_MESSAGE);

    if (pregunta != JOptionPane.YES_OPTION) {
        return; // Usuario canceló
    }

    // === PROCESAR DEVOLUCIÓN ===
    try {
        int idPrestamo = Integer.parseInt(iden);

        // 1. Registrar devolución en la base de datos
        boolean devolucionExitosa = prestamo.DevolverPrestamo(idPrestamo);

        if (!devolucionExitosa) {
            JOptionPane.showMessageDialog(null,
                "Error al registrar la devolución en la base de datos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Generar multa automáticamente SI HAY RETRASO
        boolean multaRegistrada = false;
        if (tieneMulta) {
            Multa m = new Multa();
            m.setId_prestamo(idPrestamo);
            m.setId_usuario(pre.getId_usuario());
            m.setDias_retraso(diasRetraso);
            m.setMonto(montoMulta);
            m.setEstado("Activa");

            MultaDAO multaDAO = new MultaDAO();
            multaRegistrada = multaDAO.registrarMulta(m);

            if (!multaRegistrada) {
                JOptionPane.showMessageDialog(null,
                    " Advertencia: La devolución se registró pero hubo un error al generar la multa\n\n" +
                    "Deberá registrar la multa manualmente",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            }
        }

        // 3. Actualizar stock y estado del libro
        String codigoLibro = txtCodigoPrestamo.getText().trim();
        li = libro.BuscarLibro(codigoLibro);

        if (li != null) {
            int nuevoStock = li.getStock() + 1;
            prestamo.ActualizarStockLibro(nuevoStock, li.getId_libro());
            prestamo.ActualizarEstadoLibroPrestamo(5, li.getId_libro()); // estado 5 = Disponible
        }

        // 4. Mostrar mensaje de éxito
        String mensajeExito;
        if (tieneMulta && multaRegistrada) {
            mensajeExito = 
                "DEVOLUCIÓN REGISTRADA EXITOSAMENTE\n\n" +
                "═════════════════════════════════\n" +
                " Préstamo marcado como devuelto\n" +
                "Stock del libro actualizado\n" +
                " MULTA GENERADA:\n" +
                "   • Días de retraso: " + diasRetraso + "\n" +
                "   • Monto: " + String.format("%.2f Bs", montoMulta) + "\n" +
                "   • Estado: Activa (pendiente de pago)\n" +
                "═════════════════════════════════\n\n" +
                "El usuario deberá pagar la multa antes de realizar nuevos préstamos.";
        } else {
            mensajeExito = 
                "DEVOLUCIÓN REGISTRADA EXITOSAMENTE\n\n" +
                "═════════════════════════════════\n" +
                "Préstamo devuelto a tiempo\n" +
                "Stock del libro actualizado\n" +
                "Sin multas generadas\n" +
                "═════════════════════════════════";
        }

        JOptionPane.showMessageDialog(null,
            mensajeExito,
            "Devolución Exitosa",
            JOptionPane.INFORMATION_MESSAGE);

        // 5. Limpiar y actualizar tablas
        LimpiarTable();
        LimpiarPrestamo();
        ListarPrestamo();
        
        // 6. Si hay multas en el sistema, actualizar también esas tablas
        if (tieneMulta && multaRegistrada) {
            // Actualizar las tablas de multas si están visibles
            try {
                actualizarTodasLasTablas(); // Este método actualiza las tablas de multas
            } catch (Exception e) {
                System.out.println("No se pudieron actualizar tablas de multas: " + e.getMessage());
            }
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,
            "❌ Error: El ID del préstamo no es válido\n\n" + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
            "❌ Error inesperado al procesar la devolución:\n\n" + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnDevolucionPrestamoActionPerformed

    private void btnActualizarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPrestamoActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtidPrestamo.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            String co2 = txtCodigoPrestamo.getText();
            li = libro.BuscarLibro(co2);
            int StockActual2 = li.getStock() + 1;
            int EstadoActual2 =li.getId_estado();
            EstadoActual2=5;
            int idlibro2 = li.getId_libro();
            if (!"".equals(txtUsuarioPrestamo.getText()) && !"".equals(txtCodigoPrestamo.getText()) && !"".equals(txtFechaDevolucion.getDateFormatString())) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // Obtener fecha del JDateChooser y hora actual
                Date fechaSeleccionada = txtFechaDevolucion.getDate();
                Date horaActual = new Date(); // Hora actual
                // Combinar fecha seleccionada con hora actual
                Calendar calSeleccionada = Calendar.getInstance();
                calSeleccionada.setTime(fechaSeleccionada);
                Calendar calHoraActual = Calendar.getInstance();
                calHoraActual.setTime(horaActual);
                // Aplicar hora actual a la fecha seleccionada
                calSeleccionada.set(Calendar.HOUR_OF_DAY, calHoraActual.get(Calendar.HOUR_OF_DAY));
                calSeleccionada.set(Calendar.MINUTE, calHoraActual.get(Calendar.MINUTE));
                calSeleccionada.set(Calendar.SECOND, calHoraActual.get(Calendar.SECOND));

                String fechaDevolucionFormateada = sdf.format(calSeleccionada.getTime());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fechaHoraActual = sdf2.format(new Date());
                boolean error;
                pre.setId_usuario(Integer.parseInt(txtidUsuarioPrestamo.getText()));
                pre.setId_libro(Integer.parseInt(txtidLibroPrestamo.getText()));
                pre.setFecha_prestamo(fechaHoraActual);
                pre.setFecha_devolucion(fechaDevolucionFormateada);
                //pre.setFecha_devolucion(fechaDevolucionFormateada);
                pre.setId_prestamo(Integer.parseInt(txtidPrestamo.getText()));
                error=prestamo.ModificarPrestamo(pre);
                if (error==true){
                    String co3 = txtCodigoPrestamo.getText();
                    li = libro.BuscarLibro(co3);
                    int StockActual3 = li.getStock() - 1;
                    int EstadoActual3 =li.getId_estado();
                    EstadoActual3=2;
                    int idlibro3 = li.getId_libro();

                    // prestamo.ActualizarStockLibro(StockActual2, idlibro2);
                    //prestamo.ActualizarEstadoLibroPrestamo(EstadoActual2, idlibro2);
                    //prestamo.ActualizarStockLibro(StockActual3, idlibro3);
                    //prestamo.ActualizarEstadoLibroPrestamo(EstadoActual3, idlibro3);
                    LimpiarTable();
                    LimpiarPrestamo();
                    ListarPrestamo();
                    JOptionPane.showMessageDialog(null, "Campos actualizados con exito");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Los campos Carnet y Codigo Libro son obligatorios");
            }
        }
    }//GEN-LAST:event_btnActualizarPrestamoActionPerformed

    private void txtidPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidPrestamoActionPerformed

    private void btnEliminarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPrestamoActionPerformed
        // TODO add your handling code here:

        if (!"".equals(txtidPrestamo.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el Prestamo Nro. " + txtidPrestamo.getText());
            if (pregunta == 0) {
                int id = Integer.parseInt(txtidPrestamo.getText());
                prestamo.EliminarPrestamo(id);
                LimpiarTable();
                LimpiarPrestamo();
                ListarPrestamo();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Prestamo para eliminar");
        }
    }//GEN-LAST:event_btnEliminarPrestamoActionPerformed

    private void btnGuardarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPrestamoActionPerformed
        // TODO add your handling code here:
        String cod = txtUsuarioPrestamo.getText();
        us = usuario.BuscarUsuario(cod);
        if (us.getId_estado_prestamo() == 1) {
            String codlibro2=txtCodigoPrestamo.getText();
            li = libro.BuscarLibro(codlibro2);
            if(li.getId_estado()==5){
                if (!"".equals(txtidUsuarioPrestamo.getText()) || !"".equals(txtidLibroPrestamo.getText()) || !"".equals(txtFechaDevolucion.getDateFormatString())) {
                    /*   // Crear formateador para MySQL (YYYY-MM-DD)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    // Fecha de préstamo (automática - hoy)
                    Date fechaPrestamo = new Date();
                    String fechaPrestamoFormateada = sdf.format(fechaPrestamo);

                    // Fecha de devolución (del JDateChooser)
                    Date fechaDev = txtFechaDevolucion.getDate();
                    String fechaDevolucionFormateada = sdf.format(fechaDev);
                    */

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    // Obtener fecha del JDateChooser y hora actual
                    Date fechaSeleccionada = txtFechaDevolucion.getDate();
                    Date horaActual = new Date(); // Hora actual

                    // Combinar fecha seleccionada con hora actual
                    Calendar calSeleccionada = Calendar.getInstance();
                    calSeleccionada.setTime(fechaSeleccionada);

                    Calendar calHoraActual = Calendar.getInstance();
                    calHoraActual.setTime(horaActual);

                    // Aplicar hora actual a la fecha seleccionada
                    calSeleccionada.set(Calendar.HOUR_OF_DAY, calHoraActual.get(Calendar.HOUR_OF_DAY));
                    calSeleccionada.set(Calendar.MINUTE, calHoraActual.get(Calendar.MINUTE));
                    calSeleccionada.set(Calendar.SECOND, calHoraActual.get(Calendar.SECOND));

                    String fechaDevolucionFormateada = sdf.format(calSeleccionada.getTime());
                    pre.setId_usuario(Integer.parseInt(txtidUsuarioPrestamo.getText()));
                    pre.setId_libro(Integer.parseInt(txtidLibroPrestamo.getText()));
                    pre.setFecha_devolucion(fechaDevolucionFormateada);

                    //pre.setFecha_prestamo(fechaPrestamoFormateada);
                    // pre.setEstado("Activo");
                    JOptionPane.showMessageDialog(null, "Se registro el prestamo correctamente");
                    //LimpiarTable();
                    //ListarPrestamo();
                    boolean errores;
                    errores = prestamo.RegistrarPrestamo(pre);
                    if (errores == true){
                        //prestamo.RegistrarPrestamo(pre);
                        String co = txtCodigoPrestamo.getText();
                        li = libro.BuscarLibro(co);
                        int StockActual = li.getStock() - 1;
                        int EstadoActual=li.getId_estado();
                        EstadoActual=2;
                        int idlibro = li.getId_libro();
                        prestamo.ActualizarStockLibro(StockActual, idlibro);
                        prestamo.ActualizarEstadoLibroPrestamo(EstadoActual, idlibro);

                        LimpiarTable();
                        LimpiarPrestamo();
                        ListarPrestamo();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Los campos estan vacios");
                }
            }else{
                JOptionPane.showMessageDialog(null,"El libro ya se encuentra en prestamo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no esta habilitado para realizar un prestamo");
        }
    }//GEN-LAST:event_btnGuardarPrestamoActionPerformed

    private void TablePrestamoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePrestamoMouseClicked
        // TODO add your handling code here:
        int fila = TablePrestamo.rowAtPoint(evt.getPoint());
        txtidPrestamo.setText(TablePrestamo.getValueAt(fila, 0).toString());
        txtUsuarioPrestamo.setText(TablePrestamo.getValueAt(fila, 1).toString());
        txtCodigoPrestamo.setText(TablePrestamo.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TablePrestamoMouseClicked

    private void txtidLibroPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidLibroPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidLibroPrestamoActionPerformed

    private void txtidUsuarioPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidUsuarioPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidUsuarioPrestamoActionPerformed

    private void txtEdicionPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdicionPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdicionPrestamoActionPerformed

    private void txtStockPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockPrestamoActionPerformed

    private void txtTituloPrestamoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTituloPrestamoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!"".equals(txtTituloPrestamo.getText())) {
                String titulo = txtTituloPrestamo.getText();
                li = libro.BuscarLibroPorTitulo(titulo);
                if (li.getCodigo()!= null) {
                    txtidLibroPrestamo.setText("" + li.getId_libro());
                    txtCodigoPrestamo.setText("" + li.getCodigo());
                    txtEdicionPrestamo.setText("" + li.getEdicion());
                    txtStockPrestamo.setText("" + li.getStock());
                    //REQUESTFOCUS

                } else {
                    //REQUESTFOCUS
                    JOptionPane.showMessageDialog(null, "El libro no esta registrado");
                    txtidLibroPrestamo.setText("");
                    txtCodigoPrestamo.setText("");
                    txtEdicionPrestamo.setText("");
                    txtStockPrestamo.setText("");
                    txtTituloPrestamo.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el Titulo del libro");

                txtTituloPrestamo.requestFocus();
            }

        }

    }//GEN-LAST:event_txtTituloPrestamoKeyPressed

    private void txtTituloPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloPrestamoActionPerformed

    private void txtCodigoPrestamoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPrestamoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!"".equals(txtCodigoPrestamo.getText())) {
                String cod = txtCodigoPrestamo.getText();
                li = libro.BuscarLibro(cod);
                if (li.getTitulo() != null) {
                    txtidLibroPrestamo.setText("" + li.getId_libro());
                    txtTituloPrestamo.setText("" + li.getTitulo());
                    txtEdicionPrestamo.setText("" + li.getEdicion());
                    txtStockPrestamo.setText("" + li.getStock());
                    //REQUESTFOCUS

                } else {
                    //REQUESTFOCUS
                    JOptionPane.showMessageDialog(null, "El libro no esta registrado");
                    txtidLibroPrestamo.setText("");
                    txtTituloPrestamo.setText("");
                    txtEdicionPrestamo.setText("");
                    txtStockPrestamo.setText("");
                    txtCodigoPrestamo.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del libro");

                txtCodigoPrestamo.requestFocus();
            }

        }
    }//GEN-LAST:event_txtCodigoPrestamoKeyPressed

    private void txtCodigoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoPrestamoActionPerformed

    private void txtDomicilioPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDomicilioPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDomicilioPrestamoActionPerformed

    private void txtTelefonoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoPrestamoActionPerformed

    private void txtNombrePrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombrePrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePrestamoActionPerformed

    private void txtApellidoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPrestamoActionPerformed

    private void txtUsuarioPrestamoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioPrestamoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!"".equals(txtUsuarioPrestamo.getText())) {
                String cod = txtUsuarioPrestamo.getText();
                us = usuario.BuscarUsuario(cod);
                // String errores = prestamo.validarCarnetPres(cod);
                // if (!errores.isEmpty()) {
                    //   JOptionPane.showMessageDialog(null, "Errores de validación:\n" + errores);
                    //}else{
                    if (us.getNombre() != null) {
                        txtidUsuarioPrestamo.setText("" + us.getId_usuario());
                        txtNombrePrestamo.setText("" + us.getNombre());
                        txtApellidoPrestamo.setText("" + us.getApellido());
                        txtTelefonoPrestamo.setText("" + us.getTelefono());
                        txtDomicilioPrestamo.setText("" + us.getDomicilio());
                        //REQUESTFOCUS

                    } else {
                        //REQUESTFOCUS
                        JOptionPane.showMessageDialog(null, "El usuario no esta registrado");
                        txtidUsuarioPrestamo.setText("");
                        txtNombrePrestamo.setText("");
                        txtApellidoPrestamo.setText("");
                        txtTelefonoPrestamo.setText("");
                        txtDomicilioPrestamo.setText("");
                        txtUsuarioPrestamo.requestFocus();
                    }

                    //}
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el carnet del usuario");
                txtUsuarioPrestamo.requestFocus();
            }

        }
    }//GEN-LAST:event_txtUsuarioPrestamoKeyPressed

    private void txtUsuarioPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioPrestamoActionPerformed

    private void btnNuevoAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAutorActionPerformed
        // TODO add your handling code here:
        LimpiarAutor();
    }//GEN-LAST:event_btnNuevoAutorActionPerformed

    private void btnEliminarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAutorActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdAutor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar: " + txtNombreAutor.getText());
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdAutor.getText());
                autor.EliminarAutor(id);
                System.out.println(id);
                LimpiarTable();
                LimpiarAutor();
                ListarAutor();
            }
        }
    }//GEN-LAST:event_btnEliminarAutorActionPerformed

    private void txtApellidoAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoAutorActionPerformed

    private void txtNombreAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreAutorActionPerformed

    private void btnActualizarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarAutorActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdAutor.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtNombreAutor.getText()) && !"".equals(cboxPaisAutor.getSelectedItem())) {
                boolean error;
                au.setNombre(txtNombreAutor.getText());
                au.setId_pais(autor.ObtenerIdPais(cboxPaisAutor.getSelectedItem().toString()));
                au.setApellido(txtApellidoAutor.getText());
                au.setId_autor(Integer.parseInt(txtIdAutor.getText()));
                error = autor.ModificarAutor(au);
                if (error == true) {
                    JOptionPane.showMessageDialog(null, "Campos actualizados con exito");
                    LimpiarTable();
                    LimpiarAutor();
                    ListarAutor();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los campos Nombre y Pais son obligatorios");
            }
        }
    }//GEN-LAST:event_btnActualizarAutorActionPerformed

    private void btnGuardarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAutorActionPerformed
        // TODO add your handling code here:
        if ((!"".equals(txtNombreAutor.getText()) && !"".equals(cboxPaisAutor.getSelectedItem()))) {
            boolean error;
            au.setNombre(txtNombreAutor.getText());
            au.setApellido(txtApellidoAutor.getText());
            au.setId_pais((autor.ObtenerIdPais(cboxPaisAutor.getSelectedItem().toString())));
            au.setEstado(1);
            error = autor.RegistrarAutor(au);
            if (error == true) {
                JOptionPane.showMessageDialog(null, "Autor Registrado con Exito!!!");
                LimpiarTable();
                LimpiarAutor();
                ListarAutor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos Nombre y Pais son obligatorios");
        }
    }//GEN-LAST:event_btnGuardarAutorActionPerformed

    private void cboxPaisAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxPaisAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxPaisAutorActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jComboBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox17ActionPerformed

    private void TableAutorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableAutorMouseClicked
        // TODO add your handling code here:
        int fila = TableAutor.rowAtPoint(evt.getPoint());
        txtIdAutor.setText(TableAutor.getValueAt(fila, 0).toString());
        txtNombreAutor.setText(TableAutor.getValueAt(fila, 1).toString());
        txtApellidoAutor.setText(TableAutor.getValueAt(fila, 2).toString());
        cboxPaisAutor.setSelectedItem(TableAutor.getValueAt(fila, 3).toString());
    }//GEN-LAST:event_TableAutorMouseClicked

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void btnNuevoEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoEditorialActionPerformed
        // TODO add your handling code here:
        LimpiarEditorial();
    }//GEN-LAST:event_btnNuevoEditorialActionPerformed

    private void btnEliminarEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEditorialActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdEditorial.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar: " + txtNombreEditorial.getText());
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdEditorial.getText());
                editorial.EliminarEditorial(id);
                LimpiarTable();
                LimpiarEditorial();
                ListarEditorial();
            }
        }
    }//GEN-LAST:event_btnEliminarEditorialActionPerformed

    private void txtTelefonoEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoEditorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoEditorialActionPerformed

    private void txtDireccionEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionEditorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionEditorialActionPerformed

    private void txtNombreEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreEditorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEditorialActionPerformed

    private void btnActualizarEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEditorialActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdEditorial.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtNombreEditorial.getText()) && !"".equals(cboxPaisEditorial.getSelectedItem())) {
                boolean error;
                ed.setNombre(txtNombreEditorial.getText());
                ed.setId_Pais(editorial.ObtenerIdPais(cboxPaisEditorial.getSelectedItem().toString()));
                ed.setDireccion(txtDireccionEditorial.getText());
                ed.setTelefono(txtTelefonoEditorial.getText());
                ed.setId_editorial(Integer.parseInt(txtIdEditorial.getText()));
                error = editorial.ModificarEditorial(ed);
                if (error == true) {
                    JOptionPane.showMessageDialog(null, "Campos actualizados con exito");
                    LimpiarTable();
                    LimpiarEditorial();
                    ListarEditorial();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los campos Nombre y Pais son obligatorios");
            }
        }
    }//GEN-LAST:event_btnActualizarEditorialActionPerformed

    private void btnGuardarEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEditorialActionPerformed
        // TODO add your handling code here:
        if ((!"".equals(txtNombreEditorial.getText()) && !"".equals(cboxPaisEditorial.getSelectedItem()))) {
            boolean error;
            ed.setNombre(txtNombreEditorial.getText());
            ed.setDireccion(txtDireccionEditorial.getText());
            ed.setTelefono(txtTelefonoEditorial.getText());
            ed.setId_Pais((editorial.ObtenerIdPais(cboxPaisEditorial.getSelectedItem().toString())));
            ed.setEstado(1);
            error = editorial.RegistrarEditorial(ed);
            if (error == true) {
                JOptionPane.showMessageDialog(null, "Editorial Registrada con Exito!!!");
                LimpiarTable();
                LimpiarEditorial();
                ListarEditorial();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos Nombre y Pais son obligatorios");
        }
    }//GEN-LAST:event_btnGuardarEditorialActionPerformed

    private void cboxPaisEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxPaisEditorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxPaisEditorialActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton37ActionPerformed

    private void TableEditorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEditorialMouseClicked
        int fila = TableEditorial.rowAtPoint(evt.getPoint());
        txtIdEditorial.setText(TableEditorial.getValueAt(fila, 0).toString());
        txtNombreEditorial.setText(TableEditorial.getValueAt(fila, 1).toString());
        cboxPaisEditorial.setSelectedItem(TableEditorial.getValueAt(fila, 2).toString());
        txtDireccionEditorial.setText(TableEditorial.getValueAt(fila, 3).toString());
        txtTelefonoEditorial.setText(TableEditorial.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableEditorialMouseClicked

    private void btnNuevoLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoLibroActionPerformed
        // TODO add your handling code here:
        LimpiarLibro();
    }//GEN-LAST:event_btnNuevoLibroActionPerformed

    private void btnActualizarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarLibroActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdLibro.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtTituloLibro.getText()) && !"".equals(txtCodigoLibro.getText()) && !"".equals(cboxEstadoLibro.getSelectedItem()) && !"".equals(txtStockLibro.getText()) && !"".equals(txtAnioLibro.getText())) {
                boolean error;
                li.setId_libro(Integer.parseInt(txtIdLibro.getText()));
                li.setTitulo(txtTituloLibro.getText());
                li.setCodigo(txtCodigoLibro.getText());
                if (!"".equals(cboxAutorLibro.getSelectedItem().toString())) {
                    li.setId_autor(libro.ObtenerIdAutor(cboxAutorLibro.getSelectedItem().toString()));
                }
                if (!"".equals(cboxMateriaLibro.getSelectedItem().toString())) {
                    li.setId_materia(libro.ObtenerIdMateria(cboxMateriaLibro.getSelectedItem().toString()));
                }
                li.setStock(1);
                if (!"".equals(cboxEditorialLibro.getSelectedItem().toString())) {
                    li.setId_editorial(libro.ObtenerIdEditorial(cboxEditorialLibro.getSelectedItem().toString()));
                }
                if (!"".equals(txtAnioLibro.getText())) {
                    li.setAnio(Integer.parseInt(txtAnioLibro.getText()));
                }
                li.setEdicion(txtEdicionLibro.getText());
                if (!"".equals(cboxCategoriaLibro.getSelectedItem().toString())) {
                    li.setId_categoria(libro.ObtenerIdCategoria(cboxCategoriaLibro.getSelectedItem().toString()));
                }
                if (!"".equals(cboxEstadoLibro.getSelectedItem().toString())) {
                    li.setId_estado(libro.ObtenerIdEstado(cboxEstadoLibro.getSelectedItem().toString()));
                }
                li.setDescripcion(txtDescripcionLibro.getText());
                li.setEstado(1);
                li.setTipo(cboxTipoLibro.getSelectedItem().toString());
                error = libro.ModificarLibro(li);
                // System.out.println(li.getId_libro()+" "+li.getTitulo() + " " + li.getCodigo() + " " + li.getId_autor() + " " + li.getId_materia() + " " + li.getStock() + " " + li.getId_editorial() + " " + li.getAnio() + " " + li.getEdicion() + " " + li.getId_categoria() + " " + li.getId_estado() + " " + li.getDescripcion() + " " + li.getEstado()+" "+li.getTipo());
                if (error == true) {
                    JOptionPane.showMessageDialog(null, "Campos actualizados con exito");
                    LimpiarTable();
                    LimpiarLibro();
                    ListarLibro();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los campos Titulo, Código, Estado, Stock y Año son obligatorios");
            }
        }
    }//GEN-LAST:event_btnActualizarLibroActionPerformed

    private void btnEliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLibroActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdLibro.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar: " + txtTituloLibro.getText());
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdLibro.getText());
                libro.EliminarLibro(id);
                LimpiarTable();
                LimpiarLibro();
                ListarLibro();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Libro para eliminar");
        }
    }//GEN-LAST:event_btnEliminarLibroActionPerformed

    private void btnGuardarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarLibroActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtTituloLibro.getText()) && !"".equals(txtCodigoLibro.getText()) && !"".equals(cboxEstadoLibro.getSelectedItem()) && !"".equals(txtStockLibro.getText()) && !"".equals(txtAnioLibro.getText())) {
            boolean error;
            li.setTitulo(txtTituloLibro.getText());
            li.setCodigo(txtCodigoLibro.getText());
            if (!"".equals(cboxAutorLibro.getSelectedItem().toString())) {
                li.setId_autor(libro.ObtenerIdAutor(cboxAutorLibro.getSelectedItem().toString()));
            }
            if (!"".equals(cboxMateriaLibro.getSelectedItem().toString())) {
                li.setId_materia(libro.ObtenerIdMateria(cboxMateriaLibro.getSelectedItem().toString()));
            }
            li.setStock(1);
            if (!"".equals(cboxEditorialLibro.getSelectedItem().toString())) {
                li.setId_editorial(libro.ObtenerIdEditorial(cboxEditorialLibro.getSelectedItem().toString()));
            }
            if (!"".equals(txtAnioLibro.getText())) {
                li.setAnio(Integer.parseInt(txtAnioLibro.getText()));
            }
            li.setEdicion(txtEdicionLibro.getText());
            if (!"".equals(cboxCategoriaLibro.getSelectedItem().toString())) {
                li.setId_categoria(libro.ObtenerIdCategoria(cboxCategoriaLibro.getSelectedItem().toString()));
            }
            if (!"".equals(cboxEstadoLibro.getSelectedItem().toString())) {
                li.setId_estado(libro.ObtenerIdEstado(cboxEstadoLibro.getSelectedItem().toString()));
            }
            li.setDescripcion(txtDescripcionLibro.getText());
            li.setEstado(1);
            li.setTipo(cboxTipoLibro.getSelectedItem().toString());
            //System.out.println(li.getTitulo() + " " + li.getCodigo() + " " + li.getId_autor() + " " + li.getId_materia() + " " + li.getStock() + " " + li.getId_editorial() + " " + li.getAnio() + " " + li.getEdicion() + " " + li.getId_categoria() + " " + li.getId_estado() + " " + li.getDescripcion() + " " + li.getEstado());
            error = libro.RegistrarLibro(li);
            if (error == true) {
                JOptionPane.showMessageDialog(null, "Libro Registrado con Exito!!!");
                LimpiarTable();
                LimpiarLibro();
                ListarLibro();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos Titulo, Código, Estado, Stock y Año son obligatorios");
        }
    }//GEN-LAST:event_btnGuardarLibroActionPerformed

    private void txtTituloLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTituloLibroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloLibroKeyTyped

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
        pdfLibros();
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed

    private void TableLibroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableLibroMouseClicked
        // TODO add your handling code here:
        // autor, editorial, materia, categoria, edicion, descripcion
        // categoria, editorial, autor, materia
        li.resetearValores();
        LimpiarLibro();
        int fila = TableLibro.rowAtPoint(evt.getPoint());
        txtIdLibro.setText(TableLibro.getValueAt(fila, 0).toString());
        txtTituloLibro.setText(TableLibro.getValueAt(fila, 1).toString());
        txtCodigoLibro.setText(TableLibro.getValueAt(fila, 2).toString());
        Object autor = TableLibro.getValueAt(fila, 3);
        if (autor != null) {
            cboxAutorLibro.setSelectedItem(autor.toString());
        }
        Object editorial = TableLibro.getValueAt(fila, 4);
        if (editorial != null) {
            cboxEditorialLibro.setSelectedItem(editorial.toString());
        }
        Object materia = TableLibro.getValueAt(fila, 5);
        if (materia != null) {
            cboxMateriaLibro.setSelectedItem(materia.toString());
        }
        Object categoria = TableLibro.getValueAt(fila, 6);
        if (categoria != null) {
            cboxCategoriaLibro.setSelectedItem(categoria.toString());
        }
        cboxEstadoLibro.setSelectedItem(TableLibro.getValueAt(fila, 7).toString());
        txtStockLibro.setText(TableLibro.getValueAt(fila, 8).toString());
        txtAnioLibro.setText(TableLibro.getValueAt(fila, 10).toString());
        txtEdicionLibro.setText(TableLibro.getValueAt(fila, 11).toString());
        txtDescripcionLibro.setText(TableLibro.getValueAt(fila, 12).toString());
        cboxTipoLibro.setSelectedItem(TableLibro.getValueAt(fila, 13).toString());
    }//GEN-LAST:event_TableLibroMouseClicked

    private void txtDescripcionLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionLibroActionPerformed

    private void txtEdicionLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdicionLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdicionLibroActionPerformed

    private void txtAnioLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioLibroActionPerformed

    private void txtCodigoLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoLibroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        LimpiarLibro2();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTodosLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosLibroActionPerformed
        // TODO add your handling code here:
        ListarLibro2();
    }//GEN-LAST:event_btnTodosLibroActionPerformed

    private void btnBuscarLibro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLibro2ActionPerformed
        // TODO add your handling code here:
        String titulo = cboxNombreLibro2.getSelectedItem() != null ? cboxNombreLibro2.getSelectedItem().toString() : "";
        String categoria = cboxCategoriaLibro2.getSelectedItem() != null ? cboxCategoriaLibro2.getSelectedItem().toString() : "";
        String materia = cboxMateriaLibro2.getSelectedItem() != null ? cboxMateriaLibro2.getSelectedItem().toString() : "";
        String autor = cboxAutorLibro2.getSelectedItem() != null ? cboxAutorLibro2.getSelectedItem().toString() : "";
        if (!libro.validarSeleccionCombo(cboxNombreLibro2, titulo)) {
            JOptionPane.showMessageDialog(null, "El TÍTULO no es válido o no está en la lista.");
            return;
        }
        if (!libro.validarSeleccionCombo(cboxCategoriaLibro2, categoria)) {
            JOptionPane.showMessageDialog(null, "La CATEGORÍA no es válida o no está en la lista.");
            return;
        }
        if (!libro.validarSeleccionCombo(cboxMateriaLibro2, materia)) {
            JOptionPane.showMessageDialog(null, "La MATERIA no es válida o no está en la lista.");
            return;
        }
        if (!libro.validarSeleccionCombo(cboxAutorLibro2, autor)) {
            JOptionPane.showMessageDialog(null, "El AUTOR no es válido o no está en la lista.");
            return;
        }
        ListarLibro3();
    }//GEN-LAST:event_btnBuscarLibro2ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        pdfLibros();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void btnReporMultasPagadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporMultasPagadasActionPerformed
       // Obtener todas las multas pagadas
    List<Multa_pagada> lista = multaPagadaDao.listarPagosPagados();

    if (lista.isEmpty()) {
        JOptionPane.showMessageDialog(null,
            "No hay multas pagadas registradas en el sistema",
            "Sin registros",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    Document documento = new Document(PageSize.A4.rotate()); // Horizontal

    try {
        // Crear directorio si no existe
        String dirReportes = "src/Pdf";
        File dir = new File(dirReportes);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Nombre del archivo con fecha y hora
        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String ruta = dirReportes + "/Reporte_Multas_PAGADAS_" + fecha + ".pdf";

        PdfWriter.getInstance(documento, new FileOutputStream(ruta));
        documento.open();

        // ========== LOGO Y ENCABEZADO ==========
        try {
            Image logo = Image.getInstance("src/Img/SISINf.png");
            logo.scaleToFit(80, 80);
            logo.setAlignment(Element.ALIGN_CENTER);
            documento.add(logo);
        } catch (Exception e) {
            System.out.println("Logo no encontrado: " + e.getMessage());
        }

        // TÍTULO
        Paragraph titulo = new Paragraph("REPORTE DE MULTAS PAGADAS\n",
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY));
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);

        Paragraph subtitulo = new Paragraph("Sistema de Gestión Bibliotecaria\n\n",
            FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.GRAY));
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(subtitulo);

        // FECHA DE GENERACIÓN
        Paragraph fechaGen = new Paragraph(
            "Fecha de generación: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n\n",
            FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK));
        fechaGen.setAlignment(Element.ALIGN_RIGHT);
        documento.add(fechaGen);

        documento.add(new Paragraph("\n"));

        // ========== TABLA DE MULTAS PAGADAS ==========
        PdfPTable tabla = new PdfPTable(9);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{0.7f, 0.8f, 1f, 2f, 2.2f, 0.8f, 1f, 1.5f, 2f});

        // ENCABEZADOS
        agregarCeldaEncabezadoReporte(tabla, "ID");
        agregarCeldaEncabezadoReporte(tabla, "Multa");
        agregarCeldaEncabezadoReporte(tabla, "Préstamo");
        agregarCeldaEncabezadoReporte(tabla, "Usuario");
        agregarCeldaEncabezadoReporte(tabla, "Libro");
        agregarCeldaEncabezadoReporte(tabla, "Días");
        agregarCeldaEncabezadoReporte(tabla, "Monto");
        agregarCeldaEncabezadoReporte(tabla, "Num. Factura");
        agregarCeldaEncabezadoReporte(tabla, "Fecha Pago");

        // DATOS Y CÁLCULO DE TOTALES
        float totalRecaudado = 0;
        int totalDias = 0;

        // Agrupar por mes para estadísticas
        Map<String, Float> recaudadoPorMes = new HashMap<>();
        Map<String, Integer> pagosPorMes = new HashMap<>();

        SimpleDateFormat mesAnio = new SimpleDateFormat("MM/yyyy");

        for (Multa_pagada mp : lista) {
            // ID Pago
            PdfPCell celda = new PdfPCell(new Phrase(String.valueOf(mp.getId_multa_pagada()),
                FontFactory.getFont(FontFactory.HELVETICA, 8)));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);

            // ID Multa
            celda = new PdfPCell(new Phrase(String.valueOf(mp.getId_multa()),
                FontFactory.getFont(FontFactory.HELVETICA, 8)));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);

            // ID Préstamo
            celda = new PdfPCell(new Phrase(String.valueOf(mp.getIdPrestamo()),
                FontFactory.getFont(FontFactory.HELVETICA, 8)));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);

            // Usuario
            tabla.addCell(new Phrase(mp.getNombreCompletoUsuario(),
                FontFactory.getFont(FontFactory.HELVETICA, 8)));

            // Libro
            tabla.addCell(new Phrase(mp.getTituloLibro() != null ? mp.getTituloLibro() : "N/A",
                FontFactory.getFont(FontFactory.HELVETICA, 8)));

            // Días de retraso
            celda = new PdfPCell(new Phrase(String.valueOf(mp.getDiasRetraso()),
                FontFactory.getFont(FontFactory.HELVETICA, 8)));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);

            // Monto
            celda = new PdfPCell(new Phrase(mp.getMontoFormateado(),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8)));
            celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
            celda.setBackgroundColor(new BaseColor(230, 255, 230)); // Verde claro
            tabla.addCell(celda);

            // Número de factura
            tabla.addCell(new Phrase(mp.getNumeroFactura() != null ? mp.getNumeroFactura() : "N/A",
                FontFactory.getFont(FontFactory.HELVETICA, 7)));

            // Fecha de pago
            String fechaPago = mp.getFechaFormateada();
            tabla.addCell(new Phrase(fechaPago,
                FontFactory.getFont(FontFactory.HELVETICA, 8)));

            // Acumular totales
            totalRecaudado += mp.getMontoPagado();
            totalDias += mp.getDiasRetraso();

            // Agrupar por mes
            try {
                String mes = mesAnio.format(mp.getFecha());
                recaudadoPorMes.put(mes, recaudadoPorMes.getOrDefault(mes, 0f) + mp.getMontoPagado());
                pagosPorMes.put(mes, pagosPorMes.getOrDefault(mes, 0) + 1);
            } catch (Exception e) {
                System.out.println("Error al agrupar por mes: " + e.getMessage());
            }
        }

        documento.add(tabla);

        // ========== RESUMEN POR MES ==========
        if (!recaudadoPorMes.isEmpty()) {
            documento.add(new Paragraph("\n"));
            
            Paragraph tituloMeses = new Paragraph("RECAUDACIÓN POR MES",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.DARK_GRAY));
            tituloMeses.setAlignment(Element.ALIGN_CENTER);
            documento.add(tituloMeses);
            documento.add(new Paragraph("\n"));

            PdfPTable tablaMeses = new PdfPTable(3);
            tablaMeses.setWidthPercentage(60);
            tablaMeses.setHorizontalAlignment(Element.ALIGN_CENTER);

            // Encabezados
            agregarCeldaEncabezadoReporte(tablaMeses, "Mes/Año");
            agregarCeldaEncabezadoReporte(tablaMeses, "Cantidad");
            agregarCeldaEncabezadoReporte(tablaMeses, "Monto Recaudado");

            // Ordenar meses
            List<String> mesesOrdenados = new ArrayList<>(recaudadoPorMes.keySet());
            Collections.sort(mesesOrdenados);

            for (String mes : mesesOrdenados) {
                PdfPCell celda = new PdfPCell(new Phrase(mes,
                    FontFactory.getFont(FontFactory.HELVETICA, 10)));
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(5);
                tablaMeses.addCell(celda);

                celda = new PdfPCell(new Phrase(String.valueOf(pagosPorMes.get(mes)),
                    FontFactory.getFont(FontFactory.HELVETICA, 10)));
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(5);
                tablaMeses.addCell(celda);

                celda = new PdfPCell(new Phrase(String.format("%.2f Bs", recaudadoPorMes.get(mes)),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                celda.setPadding(5);
                celda.setBackgroundColor(new BaseColor(230, 255, 230));
                tablaMeses.addCell(celda);
            }

            documento.add(tablaMeses);
        }

        // ========== RESUMEN GENERAL ==========
        documento.add(new Paragraph("\n"));

        PdfPTable tablaResumen = new PdfPTable(2);
        tablaResumen.setWidthPercentage(60);
        tablaResumen.setHorizontalAlignment(Element.ALIGN_RIGHT);

        // Total de pagos
        agregarCeldaResumenReporte(tablaResumen, "Total de multas pagadas:", 
            String.valueOf(lista.size()), BaseColor.LIGHT_GRAY);

        // Total de días
        agregarCeldaResumenReporte(tablaResumen, "Total días de retraso:", 
            String.valueOf(totalDias), BaseColor.LIGHT_GRAY);

        // Promedio
        float promedioRecaudado = lista.size() > 0 ? totalRecaudado / lista.size() : 0;
        agregarCeldaResumenReporte(tablaResumen, "Promedio por multa:", 
            String.format("%.2f Bs", promedioRecaudado), BaseColor.LIGHT_GRAY);

        // TOTAL RECAUDADO (destacado)
        PdfPCell celdaLabel = new PdfPCell(new Phrase("TOTAL RECAUDADO:",
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13)));
        celdaLabel.setBorder(Rectangle.BOX);
        celdaLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celdaLabel.setPadding(8);
        celdaLabel.setBackgroundColor(new BaseColor(200, 255, 200));
        tablaResumen.addCell(celdaLabel);

        PdfPCell celdaValor = new PdfPCell(new Phrase(String.format("%.2f Bs", totalRecaudado),
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13)));
        celdaValor.setBorder(Rectangle.BOX);
        celdaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaValor.setPadding(8);
        celdaValor.setBackgroundColor(BaseColor.YELLOW);
        tablaResumen.addCell(celdaValor);

        documento.add(tablaResumen);

        // ========== PIE DE PÁGINA ==========
        documento.add(new Paragraph("\n\n"));
        Paragraph pie = new Paragraph(
            "___________________________________________\n\n" +
            "Este reporte incluye todas las multas pagadas registradas en el sistema.\n" +
            "Sistema de Gestión Bibliotecaria - Reportes Automáticos\n" +
            "Generado por: Los intrepidos",
            FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.GRAY));
        pie.setAlignment(Element.ALIGN_CENTER);
        documento.add(pie);

        documento.close();

        // ========== MENSAJE DE ÉXITO ==========
        String mensajeExito = String.format(
            "✅ REPORTE GENERADO EXITOSAMENTE\n\n" +
            "════════════════════════════════\n" +
            "Total de pagos:      %d\n" +
            "Total días retraso:  %d\n" +
            "Total recaudado:     %.2f Bs\n" +
            "Promedio por pago:   %.2f Bs\n" +
            "Meses registrados:   %d\n" +
            "════════════════════════════════\n\n" +
            "Archivo guardado en:\n%s",
            lista.size(),
            totalDias,
            totalRecaudado,
            promedioRecaudado,
            recaudadoPorMes.size(),
            ruta
        );

        JOptionPane.showMessageDialog(null, mensajeExito,
            "Reporte Generado", JOptionPane.INFORMATION_MESSAGE);

        // Abrir el PDF automáticamente
        java.awt.Desktop.getDesktop().open(new File(ruta));

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
            "❌ Error al generar el reporte:\n\n" + e.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnReporMultasPagadasActionPerformed

    private void btnReporMultasSinPagadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporMultasSinPagadasActionPerformed
      List<Multa> lista = multaDao.listarMultasPorEstado("Activa");

    if (lista.isEmpty()) {
        JOptionPane.showMessageDialog(null, 
            "¡Excelente! No hay multas pendientes de pago",
            "Sin multas pendientes",
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    Document documento = new Document(PageSize.A4.rotate()); // Horizontal

    try {
        // Crear directorio si no existe
        String dirReportes = "src/Pdf";
        File dir = new File(dirReportes);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("Directorio creado: " + dirReportes);
        }

        // Nombre del archivo con fecha y hora
        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String ruta = dirReportes + "/Reporte_Multas_SIN_PAGAR_" + fecha + ".pdf";

        PdfWriter.getInstance(documento, new FileOutputStream(ruta));
        documento.open();

        // ========== LOGO Y ENCABEZADO ==========
        try {
            Image logo = Image.getInstance("src/Img/SISINf.png");
            logo.scaleToFit(80, 80);
            logo.setAlignment(Element.ALIGN_CENTER);
            documento.add(logo);
        } catch (Exception e) {
            System.out.println("Logo no encontrado: " + e.getMessage());
        }

        // TÍTULO
        Paragraph titulo = new Paragraph("REPORTE DE MULTAS SIN PAGAR\n",
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY));
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);

        Paragraph subtitulo = new Paragraph("Sistema de Gestión Bibliotecaria\n\n",
            FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.GRAY));
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(subtitulo);

        // FECHA DE GENERACIÓN
        Paragraph fechaGen = new Paragraph(
            "Fecha de generación: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n\n",
            FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK));
        fechaGen.setAlignment(Element.ALIGN_RIGHT);
        documento.add(fechaGen);

        documento.add(new Paragraph("\n"));

        // ========== TABLA DE MULTAS ==========
        PdfPTable tabla = new PdfPTable(8);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{0.7f, 1f, 2f, 2.5f, 1f, 1f, 1.2f, 1.3f});

        // ENCABEZADOS
        agregarCeldaEncabezadoReporte(tabla, "ID");
        agregarCeldaEncabezadoReporte(tabla, "Préstamo");
        agregarCeldaEncabezadoReporte(tabla, "Usuario");
        agregarCeldaEncabezadoReporte(tabla, "Libro");
        agregarCeldaEncabezadoReporte(tabla, "ID Usuario");
        agregarCeldaEncabezadoReporte(tabla, "Días");
        agregarCeldaEncabezadoReporte(tabla, "Monto");
        agregarCeldaEncabezadoReporte(tabla, "Estado");

        // DATOS Y CÁLCULO DE TOTALES
        float totalMonto = 0;
        int totalDias = 0;

        for (Multa m : lista) {
            // ID Multa
            PdfPCell celda = new PdfPCell(new Phrase(String.valueOf(m.getId_multa()),
                FontFactory.getFont(FontFactory.HELVETICA, 9)));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);

            // ID Préstamo
            celda = new PdfPCell(new Phrase(String.valueOf(m.getId_prestamo()),
                FontFactory.getFont(FontFactory.HELVETICA, 9)));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);

            // Usuario
            tabla.addCell(new Phrase(m.getNombreUsuario() != null ? m.getNombreUsuario() : "N/A",
                FontFactory.getFont(FontFactory.HELVETICA, 9)));

            // Libro
            tabla.addCell(new Phrase(m.getNombreLibro() != null ? m.getNombreLibro() : "N/A",
                FontFactory.getFont(FontFactory.HELVETICA, 9)));

            // ID Usuario
            celda = new PdfPCell(new Phrase(String.valueOf(m.getId_usuario()),
                FontFactory.getFont(FontFactory.HELVETICA, 9)));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);

            // Días de retraso
            celda = new PdfPCell(new Phrase(String.valueOf(m.getDias_retraso()),
                FontFactory.getFont(FontFactory.HELVETICA, 9)));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setBackgroundColor(new BaseColor(255, 230, 230)); // Rosa claro
            tabla.addCell(celda);

            // Monto
            celda = new PdfPCell(new Phrase(String.format("%.2f Bs", m.getMonto()),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9)));
            celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(celda);

            // Estado
            celda = new PdfPCell(new Phrase(m.getEstado(),
                FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.RED)));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setBackgroundColor(new BaseColor(255, 240, 240));
            tabla.addCell(celda);

            // Acumular totales
            totalMonto += m.getMonto();
            totalDias += m.getDias_retraso();
        }

        documento.add(tabla);

        // ========== RESUMEN Y TOTALES ==========
        documento.add(new Paragraph("\n"));

        PdfPTable tablaResumen = new PdfPTable(2);
        tablaResumen.setWidthPercentage(60);
        tablaResumen.setHorizontalAlignment(Element.ALIGN_RIGHT);

        // Total de multas
        agregarCeldaResumenReporte(tablaResumen, "Total de multas sin pagar:", 
            String.valueOf(lista.size()), BaseColor.LIGHT_GRAY);

        // Total de días acumulados
        agregarCeldaResumenReporte(tablaResumen, "Total días de retraso acumulados:", 
            String.valueOf(totalDias), BaseColor.LIGHT_GRAY);

        // Monto promedio
        float montoPromedio = lista.size() > 0 ? totalMonto / lista.size() : 0;
        agregarCeldaResumenReporte(tablaResumen, "Monto promedio por multa:", 
            String.format("%.2f Bs", montoPromedio), BaseColor.LIGHT_GRAY);

        // TOTAL A RECAUDAR (destacado)
        PdfPCell celdaLabel = new PdfPCell(new Phrase("TOTAL A RECAUDAR:",
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13)));
        celdaLabel.setBorder(Rectangle.BOX);
        celdaLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celdaLabel.setPadding(8);
        celdaLabel.setBackgroundColor(new BaseColor(255, 200, 200));
        tablaResumen.addCell(celdaLabel);

        PdfPCell celdaValor = new PdfPCell(new Phrase(String.format("%.2f Bs", totalMonto),
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13)));
        celdaValor.setBorder(Rectangle.BOX);
        celdaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaValor.setPadding(8);
        celdaValor.setBackgroundColor(BaseColor.YELLOW);
        tablaResumen.addCell(celdaValor);

        documento.add(tablaResumen);

        // ========== PIE DE PÁGINA ==========
        documento.add(new Paragraph("\n\n"));
        Paragraph pie = new Paragraph(
            "___________________________________________\n\n" +
            "Este reporte muestra todas las multas pendientes de pago al momento de su generación.\n" +
            "Sistema de Gestión Bibliotecaria - Reportes Automáticos\n" +
            "Generado por: Los intrepidos",
            FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.GRAY));
        pie.setAlignment(Element.ALIGN_CENTER);
        documento.add(pie);

        documento.close();

        // ========== MENSAJE DE ÉXITO ==========
        String mensajeExito = String.format(
            "✅ REPORTE GENERADO EXITOSAMENTE\n\n" +
            "════════════════════════════════\n" +
            "Total de multas:     %d\n" +
            "Total días retraso:  %d\n" +
            "Total a recaudar:    %.2f Bs\n" +
            "Promedio por multa:  %.2f Bs\n" +
            "════════════════════════════════\n\n" +
            "Archivo guardado en:\n%s",
            lista.size(),
            totalDias,
            totalMonto,
            montoPromedio,
            ruta
        );

        JOptionPane.showMessageDialog(null, mensajeExito,
            "Reporte Generado", JOptionPane.INFORMATION_MESSAGE);

        // Abrir el PDF automáticamente
        java.awt.Desktop.getDesktop().open(new File(ruta));

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
            "Error al generar el reporte:\n\n" + e.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnReporMultasSinPagadasActionPerformed

    private void btnMultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultasActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(6); 
    }//GEN-LAST:event_btnMultasActionPerformed

    public void ListarUsuario() {
        LimpiarTable();
        List<Usuario> ListarUsuario = usuario.ListarUsuario();
        modelo = (DefaultTableModel) TableUsuario.getModel();
        Object[] obj = new Object[10];
        for (int i = 0; i < ListarUsuario.size(); i++) {
            obj[0] = ListarUsuario.get(i).getId_usuario();
            obj[1] = ListarUsuario.get(i).getCarnet();
            obj[2] = ListarUsuario.get(i).getNombre();
            obj[3] = ListarUsuario.get(i).getApellido();
            obj[4] = ListarUsuario.get(i).getDomicilio();
            obj[5] = ListarUsuario.get(i).getTipoUsuarioNombre();  // NOMBRE en lugar de ID
            obj[6] = ListarUsuario.get(i).getTelefono();
            obj[7] = ListarUsuario.get(i).getCargoNombre();        // NOMBRE en lugar de ID
            obj[8] = ListarUsuario.get(i).getCarreraNombre();      // NOMBRE en lugar de ID
            obj[9] = ListarUsuario.get(i).getEstadoPrestamo();      // NOMBRE en lugar de ID

            modelo.addRow(obj);
        }
        TableUsuario.setModel(modelo);
    }

    public void ListarPrestamo() {
        LimpiarTable();
        List<Prestamo> ListarPre = prestamo.ListarPrestamo();
        modelo = (DefaultTableModel) TablePrestamo.getModel();
        Object[] obj = new Object[8];
        for (int i = 0; i < ListarPre.size(); i++) {
            obj[0] = ListarPre.get(i).getId_prestamo();
            obj[1] = ListarPre.get(i).getCarnetUsuario();
            obj[2] = ListarPre.get(i).getNombreUsuario();
            obj[3] = ListarPre.get(i).getTituloLibro();
            obj[4] = ListarPre.get(i).getCodigoLibro();
            obj[5] = ListarPre.get(i).getFecha_prestamo();
            obj[6] = ListarPre.get(i).getFecha_devolucion();  // NOMBRE en lugar de ID
            obj[7] = ListarPre.get(i).getEstadoPrestamo();
            modelo.addRow(obj);
        }
        TablePrestamo.setModel(modelo);
    }
    
      public List<String> obtenerListaTitulos() {
    List<String> listaTitulos = new ArrayList();
    List<Libro> listarLibro = libro.ListarLibro();
    
    for (int i = 0; i < listarLibro.size(); i++) {
        listaTitulos.add(listarLibro.get(i).getTitulo());
    }
    
    return listaTitulos;
}

    /**
     * @param args the command line arguments
     *
     *
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Sistema().setVisible(true));

        //Login login = new Login();
        //login.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Stock;
    private javax.swing.JTable TableAutor;
    private javax.swing.JTable TableCategoria;
    private javax.swing.JTable TableEditorial;
    private javax.swing.JTable TableLibro;
    private javax.swing.JTable TableLibro2;
    private javax.swing.JTable TableMateria;
    private javax.swing.JTable TableMultasUsuario;
    private javax.swing.JTable TableMultassinpagar;
    private javax.swing.JTable TablePais;
    private javax.swing.JTable TablePrestamo;
    private javax.swing.JTable TableUsuario;
    private javax.swing.JButton btnActualizarAutor;
    private javax.swing.JButton btnActualizarEditorial;
    private javax.swing.JButton btnActualizarLibro;
    private javax.swing.JButton btnActualizarMateria;
    private javax.swing.JButton btnActualizarPais;
    private javax.swing.JButton btnActualizarPrestamo;
    private javax.swing.JButton btnActualizarUsuario;
    private javax.swing.JButton btnActulizarMultas;
    private javax.swing.JButton btnAjuste;
    private javax.swing.JButton btnAnalisis;
    private javax.swing.JButton btnAplicarFiltroUsuario;
    private javax.swing.JButton btnBuscarCarnetMul;
    private javax.swing.JButton btnBuscarLibro2;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnDevolucionPrestamo;
    private javax.swing.JButton btnEliminarAutor;
    private javax.swing.JButton btnEliminarCategoria;
    private javax.swing.JButton btnEliminarEditorial;
    private javax.swing.JButton btnEliminarLibro;
    private javax.swing.JButton btnEliminarMateria;
    private javax.swing.JButton btnEliminarPais;
    private javax.swing.JButton btnEliminarPrestamo;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnGenerarFactura;
    private javax.swing.JButton btnGraficarPrestamo;
    private javax.swing.JButton btnGuardarAutor;
    private javax.swing.JButton btnGuardarCategoria;
    private javax.swing.JButton btnGuardarEditorial;
    private javax.swing.JButton btnGuardarLibro;
    private javax.swing.JButton btnGuardarMateria;
    private javax.swing.JButton btnGuardarPais;
    private javax.swing.JButton btnGuardarPrestamo;
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMasPrestadoLibro2;
    private javax.swing.JButton btnMultas;
    private javax.swing.JButton btnNuevoAutor;
    private javax.swing.JButton btnNuevoEditorial;
    private javax.swing.JButton btnNuevoLibro;
    private javax.swing.JButton btnNuevoMateria;
    private javax.swing.JButton btnNuevoPais;
    private javax.swing.JButton btnNuevoUsuario;
    private javax.swing.JButton btnPagarMulta;
    private javax.swing.JButton btnPrestamo;
    private javax.swing.JButton btnPrestamosPDF;
    private javax.swing.JButton btnReporMultasPagadas;
    private javax.swing.JButton btnReporMultasSinPagadas;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnTodosLibro;
    private javax.swing.JButton btngenerarpdfusuario;
    private javax.swing.JComboBox<String> cboxAutorLibro;
    private javax.swing.JComboBox<String> cboxAutorLibro2;
    private javax.swing.JComboBox<String> cboxCargoUsuario;
    private javax.swing.JComboBox<String> cboxCarreraUsuario;
    private javax.swing.JComboBox<String> cboxCategoriaLibro;
    private javax.swing.JComboBox<String> cboxCategoriaLibro2;
    private javax.swing.JComboBox<String> cboxEFiltroUsuario;
    private javax.swing.JComboBox<String> cboxEditorialLibro;
    private javax.swing.JComboBox<String> cboxEstadoLibro;
    private javax.swing.JComboBox<String> cboxEstadoPrestamoUsuario;
    private javax.swing.JComboBox<String> cboxFiltroValorUsuario;
    private javax.swing.JComboBox<String> cboxLibro;
    private javax.swing.JComboBox<String> cboxMateriaLibro;
    private javax.swing.JComboBox<String> cboxMateriaLibro2;
    private javax.swing.JComboBox<String> cboxNombreLibro2;
    private javax.swing.JComboBox<String> cboxPaisAutor;
    private javax.swing.JComboBox<String> cboxPaisEditorial;
    private javax.swing.JComboBox<String> cboxTipoLibro;
    private javax.swing.JComboBox<String> cboxTipoUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelBiblioteca;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTable tableMultaspagadas;
    private javax.swing.JTextField txtAnioLibro;
    private javax.swing.JTextField txtApellidoAutor;
    private javax.swing.JTextField txtApellidoPrestamo;
    private javax.swing.JTextField txtApellidoUsuario;
    private javax.swing.JTextField txtBuscarCarnetMul;
    private javax.swing.JTextField txtCarnetUsuario;
    private javax.swing.JTextField txtCodigoLibro;
    private javax.swing.JTextField txtCodigoPrestamo;
    private javax.swing.JTextField txtDescripcionLibro;
    private javax.swing.JTextField txtDireccionEditorial;
    private javax.swing.JTextField txtDomicilioPrestamo;
    private javax.swing.JTextField txtDomicilioUsuario;
    private javax.swing.JTextField txtEdicionLibro;
    private javax.swing.JTextField txtEdicionPrestamo;
    private com.toedter.calendar.JDateChooser txtFechaDevolucion;
    private javax.swing.JTextField txtIdAutor;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtIdEditorial;
    private javax.swing.JTextField txtIdLibro;
    private javax.swing.JTextField txtIdMateria;
    private javax.swing.JTextField txtIdPais;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNombreAutor;
    private javax.swing.JTextField txtNombreCategoria;
    private javax.swing.JTextField txtNombreEditorial;
    private javax.swing.JTextField txtNombreMateria;
    private javax.swing.JTextField txtNombrePais;
    private javax.swing.JTextField txtNombrePrestamo;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtSiglaMateria;
    private javax.swing.JTextField txtStockLibro;
    private javax.swing.JTextField txtStockPrestamo;
    private javax.swing.JTextField txtTelefonoEditorial;
    private javax.swing.JTextField txtTelefonoPrestamo;
    private javax.swing.JTextField txtTelefonoUsuario;
    private javax.swing.JTextField txtTituloLibro;
    private javax.swing.JTextField txtTituloPrestamo;
    private javax.swing.JTextField txtUsuarioPrestamo;
    private javax.swing.JTextField txtidLibroPrestamo;
    private javax.swing.JTextField txtidPrestamo;
    private javax.swing.JTextField txtidUsuarioPrestamo;
    // End of variables declaration//GEN-END:variables

    private void LimpiarMateria() {
        txtIdMateria.setText("");
        txtSiglaMateria.setText("");
        txtNombreMateria.setText("");
    }

    private void LimpiarPais() {
        txtIdPais.setText("");
        txtNombrePais.setText("");
    }

    private void LimpiarCategoria() {
        txtIdCategoria.setText("");
        txtNombreCategoria.setText("");
    }

    private void LimpiarEditorial() {
        txtIdEditorial.setText("");
        txtNombreEditorial.setText("");
        txtDireccionEditorial.setText("");
        txtTelefonoEditorial.setText("");
        cboxPaisEditorial.setSelectedItem("");
    }

    private void LimpiarAutor() {
        txtIdAutor.setText("");
        txtNombreAutor.setText("");
        txtApellidoAutor.setText("");
        cboxPaisAutor.setSelectedItem("");
    }

    private void LimpiarUsuario() {
        txtIdUsuario.setText("");
        txtCarnetUsuario.setText("");
        txtNombreUsuario.setText("");
        txtApellidoUsuario.setText("");
        txtDomicilioUsuario.setText("");
        cboxTipoUsuario.setSelectedItem("");
        txtTelefonoUsuario.setText("");
        cboxCargoUsuario.setSelectedItem("");
        cboxCarreraUsuario.setSelectedItem("");
        cboxEstadoPrestamoUsuario.setSelectedItem("");
    }

    private void LimpiarLibro() {
        txtIdLibro.setText("");
        txtTituloLibro.setText("");
        txtCodigoLibro.setText("");
        cboxAutorLibro.setSelectedItem("");
        cboxMateriaLibro.setSelectedItem("");
        cboxEditorialLibro.setSelectedItem("");
        txtAnioLibro.setText("");
        txtEdicionLibro.setText("");
        cboxCategoriaLibro.setSelectedItem("");
        cboxEstadoLibro.setSelectedItem("");
        txtDescripcionLibro.setText("");
    }

    private void LimpiarLibro2() {
        cboxNombreLibro2.setSelectedItem("");
        cboxAutorLibro2.setSelectedItem("");
        cboxMateriaLibro2.setSelectedItem("");
        cboxCategoriaLibro2.setSelectedItem("");
    }

    private void LimpiarPrestamo() {
        txtidPrestamo.setText("");
        txtUsuarioPrestamo.setText("");
        txtidUsuarioPrestamo.setText("");
        txtNombrePrestamo.setText("");
        txtApellidoPrestamo.setText("");
        txtTelefonoPrestamo.setText("");
        txtDomicilioPrestamo.setText("");
        txtCodigoPrestamo.setText("");
        txtidLibroPrestamo.setText("");
        txtTituloPrestamo.setText("");
        txtEdicionPrestamo.setText("");
        txtStockPrestamo.setText("");
    }

    private void pdf() {
        try {
            // Ruta del archivo
            File file = new File("src/pdf/usuarios.pdf");
            FileOutputStream archivo = new FileOutputStream(file);

            // Documento horizontal (A4 landscape)
            Document doc = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Imagen del encabezado
            Image img = Image.getInstance("src/Img/SISINf.png");
            img.scaleToFit(90, 90);

            // Fecha
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(date) + "\n\n");

            // ENCABEZADO
            PdfPTable encabezado = new PdfPTable(4);
            encabezado.setWidthPercentage(100);
            encabezado.getDefaultCell().setBorder(0);

            float[] columnasEncabezado = new float[]{20f, 30f, 70f, 40f};
            encabezado.setWidths(columnasEncabezado);
            encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            encabezado.addCell(img);
            encabezado.addCell("");
            encabezado.addCell(new Paragraph("\n\nSISTEMA DE BIBLIOTECA\nREPORTE DE USUARIOS",
                    new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD)));

            encabezado.addCell(fecha);

            doc.add(encabezado);

            // Título principal
            Paragraph titulo = new Paragraph();
            titulo.add(Chunk.NEWLINE);
            titulo.add(new Paragraph("LISTADO DE USUARIOS FILTRADOS\n\n",
                    new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);

            // Tabla de datos
            PdfPTable tabla = new PdfPTable(TableUsuario.getColumnCount()); // 9 columnas
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);

            // Ajustar anchos de columnas (opcional)
            float[] medidaCeldas = {10f, 10f, 20f, 20f, 25f, 20f, 20f, 20f, 20f, 20f};
            tabla.setWidths(medidaCeldas);

            // Encabezados de tabla
            Font fontHeader = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
            PdfPCell celdaHeader;

            for (int i = 0; i < TableUsuario.getColumnCount(); i++) {
                celdaHeader = new PdfPCell(new Phrase(TableUsuario.getColumnName(i), fontHeader));
                celdaHeader.setBackgroundColor(BaseColor.DARK_GRAY);
                celdaHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaHeader.setPadding(5f);
                tabla.addCell(celdaHeader);
            }

            // Fuente para los datos
            Font fontDatos = new Font(Font.FontFamily.HELVETICA, 9);

            // Recorrer filas de JTable
            for (int i = 0; i < TableUsuario.getRowCount(); i++) {
                for (int j = 0; j < TableUsuario.getColumnCount(); j++) {
                    String valorCelda = TableUsuario.getValueAt(i, j).toString();
                    tabla.addCell(new Phrase(valorCelda, fontDatos));
                }
            }

            // Agregar tabla al documento
            doc.add(tabla);

            // Cerrar
            doc.close();
            archivo.close();

            JOptionPane.showMessageDialog(null, "PDF generado correctamente en: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + e.getMessage());
        }
    }
//auxiliar para filtrar

    private void actualizarComboValorUsuario() {
        Object item = cboxEFiltroUsuario.getSelectedItem();
        if (item == null) {
            return; // evita el error
        }
        String filtro = item.toString();
        // Limpiar el combo antes de llenarlo
        cboxFiltroValorUsuario.removeAllItems();

        switch (filtro) {
            case "Tipo Usuario":
                usuario.ConsultarTipoUsuario(cboxFiltroValorUsuario);
                break;
            case "Cargo":
                usuario.ConsultarCargo(cboxFiltroValorUsuario);
                break;
            case "Carrera":
                usuario.ConsultarCarreras(cboxFiltroValorUsuario);
                break;
            case "Estado Préstamo":
                usuario.ConsultarEstadoUsuario(cboxFiltroValorUsuario);
                break;
            default:
                // No hacer nada
                break;
        }
    }

    private void pdfLibros() {
        try {
            // Ruta del archivo
            File file = new File("src/pdf/libro.pdf");
            FileOutputStream archivo = new FileOutputStream(file);

            // Documento horizontal (A4 landscape)
            Document doc = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            // Imagen del encabezado
            Image img = Image.getInstance("src/Img/SISINf.png");
            img.scaleToFit(90, 90);

            // Fecha
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(date) + "\n\n");

            // ENCABEZADO
            PdfPTable encabezado = new PdfPTable(4);
            encabezado.setWidthPercentage(100);
            encabezado.getDefaultCell().setBorder(0);

            float[] columnasEncabezado = new float[]{20f, 30f, 70f, 40f};
            encabezado.setWidths(columnasEncabezado);
            encabezado.setHorizontalAlignment(Element.ALIGN_MIDDLE);

            encabezado.addCell(img);
            encabezado.addCell("");
            encabezado.addCell(new Paragraph("\n\nSISTEMA DE BIBLIOTECA\nREPORTE DE LIBROS",
                    new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD)));
            encabezado.setHorizontalAlignment(Element.ALIGN_MIDDLE);

            encabezado.addCell(fecha);

            doc.add(encabezado);

            // Título principal
            Paragraph titulo = new Paragraph();
            titulo.add(Chunk.NEWLINE);
            titulo.add(new Paragraph("LISTADO DE LIBROS\n\n",
                    new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);

            // Tabla de datos
            PdfPTable tabla = new PdfPTable(TableLibro2.getColumnCount()); // 9 columnas
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);

            // Ajustar anchos de columnas (opcional)
            float[] medidaCeldas = {20f, 40f, 40f, 40f, 40f, 20f, 20f};
            tabla.setWidths(medidaCeldas);

            // Encabezados de tabla
            Font fontHeader = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
            PdfPCell celdaHeader;

            for (int i = 0; i < TableLibro2.getColumnCount(); i++) {
                celdaHeader = new PdfPCell(new Phrase(TableLibro2.getColumnName(i), fontHeader));
                celdaHeader.setBackgroundColor(BaseColor.DARK_GRAY);
                celdaHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                celdaHeader.setPadding(5f);
                tabla.addCell(celdaHeader);
            }

            // Fuente para los datos
            Font fontDatos = new Font(Font.FontFamily.HELVETICA, 9);

            // Recorrer filas de JTable
            for (int i = 0; i < TableLibro2.getRowCount(); i++) {
                for (int j = 0; j < TableLibro2.getColumnCount(); j++) {
                    Object celdaObjeto = TableLibro2.getValueAt(i, j);
                    String valorCelda;
                    if (celdaObjeto != null) {
                        valorCelda = celdaObjeto.toString();
                    } else {
                        valorCelda = "";
                    }
                    tabla.addCell(new Phrase(valorCelda, fontDatos));
                }
            }

            // Titulo Autor Materia Estado
            // Agregar tabla al documento
            doc.add(tabla);

            // Cerrar
            doc.close();
            archivo.close();

            JOptionPane.showMessageDialog(null, "PDF generado correctamente en: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + e.getMessage());
        }
    }
    
    
    private void pdfPrestamos() {
    try {
        // Ruta del archivo
        File file = new File("src/pdf/prestamos.pdf");
        FileOutputStream archivo = new FileOutputStream(file);

        // Documento horizontal (A4 landscape)
        Document doc = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(doc, archivo);
        doc.open();

        // Imagen del encabezado
        Image img = Image.getInstance("src/Img/SISINf.png");
        img.scaleToFit(90, 90);

        // Fecha
        Paragraph fecha = new Paragraph();
        Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
        fecha.add(Chunk.NEWLINE);
        Date date = new Date();
        fecha.add("Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(date) + "\n\n");

        // ENCABEZADO
        PdfPTable encabezado = new PdfPTable(4);
        encabezado.setWidthPercentage(100);
        encabezado.getDefaultCell().setBorder(0);

        float[] columnasEncabezado = new float[]{20f, 30f, 70f, 40f};
        encabezado.setWidths(columnasEncabezado);
        encabezado.setHorizontalAlignment(Element.ALIGN_MIDDLE);

        encabezado.addCell(img);
        encabezado.addCell("");
        encabezado.addCell(new Paragraph("\n\nSISTEMA DE BIBLIOTECA\nREPORTE DE PRÉSTAMOS",
                new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD)));
        encabezado.setHorizontalAlignment(Element.ALIGN_MIDDLE);

        encabezado.addCell(fecha);

        doc.add(encabezado);

        // Título principal
        Paragraph titulo = new Paragraph();
        titulo.add(Chunk.NEWLINE);
        titulo.add(new Paragraph("LISTADO DE PRÉSTAMOS\n\n",
                new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));
        titulo.setAlignment(Element.ALIGN_CENTER);
        doc.add(titulo);

        // Tabla de datos - AJUSTADA PARA PRÉSTAMOS
        PdfPTable tabla = new PdfPTable(8); // 8 columnas para préstamos
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(10f);
        tabla.setSpacingAfter(10f);

        // Ajustar anchos de columnas para préstamos
        float[] medidaCeldas = {15f, 25f, 40f, 25f, 40f, 30f, 30f, 30f};
        tabla.setWidths(medidaCeldas);

        // Encabezados de tabla PRÉSTAMOS
        Font fontHeader = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
        PdfPCell celdaHeader;

        // Definir encabezados manualmente para préstamos
        String[] encabezadosPrestamos = {
            "ID_Prestamo", "Carnet", "Nombre", "Código Libro", 
            "Libro", "Fecha Préstamo", "Fecha Devolución", "Estado"
        };

        for (String encabezadoStr : encabezadosPrestamos) {
            celdaHeader = new PdfPCell(new Phrase(encabezadoStr, fontHeader));
            celdaHeader.setBackgroundColor(BaseColor.DARK_GRAY);
            celdaHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaHeader.setPadding(5f);
            tabla.addCell(celdaHeader);
        }

        // Fuente para los datos
        Font fontDatos = new Font(Font.FontFamily.HELVETICA, 8);

        // Recorrer filas de TablePrestamo
        for (int i = 0; i < TablePrestamo.getRowCount(); i++) {
            for (int j = 0; j < TablePrestamo.getColumnCount(); j++) {
                Object celdaObjeto = TablePrestamo.getValueAt(i, j);
                String valorCelda;
                if (celdaObjeto != null) {
                    valorCelda = celdaObjeto.toString();
                    
                    // Acortar texto muy largo para mejor visualización
                    if (valorCelda.length() > 30) {
                        valorCelda = valorCelda.substring(0, 27) + "...";
                    }
                } else {
                    valorCelda = "";
                }
                
                PdfPCell celda = new PdfPCell(new Phrase(valorCelda, fontDatos));
                celda.setPadding(3f);
                tabla.addCell(celda);
            }
        }

        // Agregar tabla al documento
        doc.add(tabla);

        // Pie de página con estadísticas
        Paragraph estadisticas = new Paragraph();
        estadisticas.add(Chunk.NEWLINE);
        estadisticas.add("Total de préstamos: " + TablePrestamo.getRowCount());
        estadisticas.setAlignment(Element.ALIGN_RIGHT);
        doc.add(estadisticas);

        // Cerrar
        doc.close();
        archivo.close();

        JOptionPane.showMessageDialog(null, "PDF de préstamos generado correctamente en: " + file.getAbsolutePath());

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al generar PDF de préstamos: " + e.getMessage());
    }
}

    class txtBuscarCarnet {

        static String getText() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public txtBuscarCarnet() {
        }
    }

}
