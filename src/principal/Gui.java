/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import clases.CalculoExportacion;
import clases.CargaPesada;
import clases.CargaSuelta;
import clases.Cliente;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public final class Gui extends javax.swing.JFrame {
    
    //Instanciadores de clases.
    DefaultTableModel info = new DefaultTableModel(); //Instanciador para la jtable que va a mostrar los datos cliente.
    DefaultTableModel info1 = new DefaultTableModel();//Instanciador para tabla que mostrara datos de la exportación.
    DefaultTableModel info2 = new DefaultTableModel();//Instanciador de la tabla para desglosar los kilogramos
    DefaultTableModel infoPesada = new DefaultTableModel();//Instanciador para la tabla Carga pesada
    DefaultTableModel infoSuelta = new DefaultTableModel();//Instanciador para la tabla Carga suelta
    DefaultTableModel infoListaClientes;//Instanciador para la tabla de clientes
    DefaultTableModel infoAprobados;

    java.util.Date fecha = new Date(); //Instanciador de la fecha para mostrarla en la GUI.
    
    //Declaracion de los arrayList
    ArrayList<String>listaClientes=new ArrayList(); //Arraylist de clientes
    ArrayList<String>listaAprobados=new ArrayList(); //Arraylist para guardar aprobados
    ArrayList listaIds=new ArrayList(); //Arraylist para validar IDs de registrados
    ArrayList listaIdAprobadas=new ArrayList(); //Arraylist para validar las IDs de los aprobados
    int pos=0;
    
    //Creación de atributo de la clase lógica.
    private Cliente datos;
    private CalculoExportacion datos1; 
    private CargaPesada datosPesada;
    private CargaSuelta datosSuelta;
    
    private boolean pesada;
    private boolean presionar;
    private boolean mostrar;
    
    //Variables globales
    public static Double totalPesada = null;
    public static Double totalSuelta = null;
    public static String[] idClientes = null;
    public static String[] idAprobadas = null;
    
    //Variable del scanner de archivos
    private Scanner linea;

    public Gui() {
        initComponents();
        this.setTitle("Exporta-CR by Olman Dávila S"); //Título de la ventana de la GUI.
        this.setLocationRelativeTo(null); //Centrar la ventana de la GUI.
        this.presionar = false;
        this.mostrar = false;
        
        //Tabla cliente
        String[] titulo = new String[]{"Nombre", "Identificación", "Edad", "Dirección"}; //Nombre de las columnas de las tablas.
        info.setColumnIdentifiers(titulo);
        tablaCliente.setModel(info);
        
        //Tabla calculo
         String[] titulo1 = new String[]{"ID.Cotizacion", "Kilogramos", "Zona a exportar", "Ciudad a exportar", "Direccion a entregar", "Fecha"}; //Nombre de las columnas de las tablas.
        info1.setColumnIdentifiers(titulo1);
        tablaExportacion1.setModel(info1);
        
         String[] titulo2 = new String[]{"Kilogramos a Gramos", "Kilogramos a Libras", "Kilogramos a Toneladas"}; //Nombre de las columnas de las tablas.
        info2.setColumnIdentifiers(titulo2);
        tablaDesglosar.setModel(info2);
        
        //Tabla Pesada
        String[] titulo3 = new String[]{"Tipo de carga", "Pies del contenedor", "Tipo de servicio", "Costo en Dólares"}; //Nombre de las columnas de la tabla pesada.
        infoPesada.setColumnIdentifiers(titulo3);
        tablaPesada.setModel(infoPesada);
        
        //Tabla suelta
        String[] titulo4 = new String[]{"Pies de la carga","Tipo de servicio","Costo en Dólares"}; //Nombre de las columnas de la tabla pesada.
        infoSuelta.setColumnIdentifiers(titulo4);
        tablaSuelta.setModel(infoSuelta);
        
        //Tabla de la lista clientes
        infoListaClientes =(DefaultTableModel)this.tablaArchivoClientes.getModel();
        
         //Tabla de la lista Aprobados
        infoAprobados =(DefaultTableModel)this.tablaAprobados.getModel();
   
        //Agregar items a comboxBox zonas
        seleccionar_Zona1.addItem("Nueva Inglaterra");
        seleccionar_Zona1.addItem("Atlántico Medio");
        seleccionar_Zona1.addItem("Atlántico Sur");
        seleccionar_Zona1.addItem("Centro Norte Oriental");
        seleccionar_Zona1.addItem("Centro Sur Oriental");
        seleccionar_Zona1.addItem("Centro Norte Occidental");
        seleccionar_Zona1.addItem("Centro Sur Occidental");
        seleccionar_Zona1.addItem("Montaña");
        seleccionar_Zona1.addItem("Pacífico");
        
        //Agregar tipo carga al comboBox
        cbTipoCarga.addItem("Contenedor Refrigerado");
        cbTipoCarga.addItem("Contenedor no refrigerado");
        cbTipoCarga.addItem("Carga embalada");
        
        
        //Agregar pies de contenedor al comboBox
        cbPiesContenedor.addItem("20");
        cbPiesContenedor.addItem("40");
     
        //Agregar tipo de servicio 2 al comboBox
        cbTipoServicio2.addItem("Barco");
        cbTipoServicio2.addItem("Avión");
        
        //Condicional para que lea el archivo de entrada si existe
        File archivoClientes = new File("clientes_exportaciones.dat");
        File archivoExportacion = new File("exportaciones_aprobadas.dat");
        if(archivoClientes.exists()){
         mostrarClientes();
        }
        
        //Funcion para recorrer las ids aprobadas para validaciones
        if(archivoExportacion.exists()){
            recorrerAprobados(); 
        }   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pCliente = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        text_Id = new javax.swing.JTextField();
        label2 = new javax.swing.JLabel();
        text_Nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        spnEdad = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        text_Direccion = new javax.swing.JTextField();
        btn_RegistrarCliente = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        btn_Autocompletar = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        btn_Salir1 = new javax.swing.JButton();
        pExportacion = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        spnFecha = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        seleccionar_Zona1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        seleccionar_Zona2 = new javax.swing.JComboBox<>();
        text_Idcotizacion = new javax.swing.JTextField();
        btn_generarCotizacion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        spnKg = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        text_direccionEntrega = new javax.swing.JTextField();
        cbTipoCarga = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        spnPiesCarga = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        cbPiesContenedor = new javax.swing.JComboBox<>();
        cbTipoServicio1 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbTipoServicio2 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        btnCargaPesada = new javax.swing.JButton();
        btnCargaSuelta = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btn_Salir2 = new javax.swing.JButton();
        pCosto = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaExportacion1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaDesglosar = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaPesada = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaSuelta = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btn_SaveAprobados = new javax.swing.JButton();
        btn_Salir3 = new javax.swing.JButton();
        pRegistro = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaArchivoClientes = new javax.swing.JTable();
        btn_listaClientes = new javax.swing.JButton();
        btn_GuardarModify = new javax.swing.JButton();
        btn_Borrar = new javax.swing.JButton();
        btn_Salir4 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaAprobados = new javax.swing.JTable();
        txt_Busqueda = new javax.swing.JTextField();
        btn_Busqueda = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        pCliente.setBackground(new java.awt.Color(153, 255, 153));

        jPanel3.setBackground(new java.awt.Color(51, 255, 51));

        jLabel7.setFont(new java.awt.Font("Old English Text MT", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Exporta-CR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        label1.setText("Digite su identificacion:");

        text_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_IdActionPerformed(evt);
            }
        });
        text_Id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                text_IdKeyTyped(evt);
            }
        });

        label2.setText("Digite su nombre completo:");

        text_Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                text_NombreKeyTyped(evt);
            }
        });

        jLabel1.setText("Digite su edad:");

        spnEdad.setModel(new javax.swing.SpinnerNumberModel(18, 18, 100, 1));

        jPanel2.setBackground(new java.awt.Color(51, 255, 51));

        jLabel8.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Datos del cliente:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        label3.setText("Digite su direccion:");

        text_Direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                text_DireccionKeyTyped(evt);
            }
        });

        btn_RegistrarCliente.setBackground(new java.awt.Color(255, 153, 0));
        btn_RegistrarCliente.setText("Registrarse");
        btn_RegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegistrarClienteActionPerformed(evt);
            }
        });

        jLabel29.setText("Agregar cliente al registro");

        btn_Autocompletar.setText("Autocompletar");
        btn_Autocompletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AutocompletarActionPerformed(evt);
            }
        });

        jLabel30.setText("Autocompletar datos de ID registrado");

        btn_Salir1.setBackground(new java.awt.Color(255, 0, 0));
        btn_Salir1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        btn_Salir1.setText("Salir");
        btn_Salir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Salir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pClienteLayout = new javax.swing.GroupLayout(pCliente);
        pCliente.setLayout(pClienteLayout);
        pClienteLayout.setHorizontalGroup(
            pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pClienteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pClienteLayout.createSequentialGroup()
                        .addComponent(text_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btn_Autocompletar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pClienteLayout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pClienteLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btn_Salir1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(69, 69, 69))
            .addGroup(pClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pClienteLayout.createSequentialGroup()
                        .addGroup(pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pClienteLayout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(text_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pClienteLayout.createSequentialGroup()
                        .addGroup(pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_RegistrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pClienteLayout.setVerticalGroup(
            pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pClienteLayout.createSequentialGroup()
                .addGroup(pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Autocompletar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(text_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(pClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(35, 35, 35)
                .addComponent(label3)
                .addGap(18, 18, 18)
                .addComponent(text_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_RegistrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cliente", pCliente);

        pExportacion.setBackground(new java.awt.Color(153, 255, 153));

        jPanel4.setBackground(new java.awt.Color(51, 255, 51));

        jLabel9.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Datos de zona a exportar:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(51, 255, 51));

        jLabel10.setFont(new java.awt.Font("Old English Text MT", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Exporta-CR");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel5.setText("Fecha:");

        spnFecha.setModel(new javax.swing.SpinnerDateModel());
        spnFecha.setEditor(new javax.swing.JSpinner.DateEditor(spnFecha, "dd-MM-yyyy"));

        jLabel2.setText("Seleccione la zona de exportación");

        seleccionar_Zona1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                seleccionar_Zona1ItemStateChanged(evt);
            }
        });

        jLabel6.setText("Seleccione la Ciudad de exportación:");

        seleccionar_Zona2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        text_Idcotizacion.setEditable(false);

        btn_generarCotizacion.setText("Generar ID cotización");
        btn_generarCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generarCotizacionActionPerformed(evt);
            }
        });

        jLabel3.setText("Kilogramos a exportar:");

        spnKg.setModel(new javax.swing.SpinnerNumberModel(1.0d, 1.0d, null, 1.0d));

        jLabel4.setText("Digite la direccion de entrega:");

        text_direccionEntrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                text_direccionEntregaKeyTyped(evt);
            }
        });

        cbTipoCarga.setEnabled(false);
        cbTipoCarga.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoCargaItemStateChanged(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(51, 255, 51));

        jLabel11.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Seleccione el tipo exportación:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11))
        );

        jLabel13.setText("Pies de la carga:");

        spnPiesCarga.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        spnPiesCarga.setEnabled(false);

        jLabel14.setText("Pies del contenedor:");

        cbPiesContenedor.setEnabled(false);

        cbTipoServicio1.setEnabled(false);

        jLabel15.setText("Tipo de servicio:");

        jLabel18.setText("Tipo de servicio:");

        cbTipoServicio2.setEnabled(false);

        jLabel19.setText("Tipo carga:");

        btnCargaPesada.setText("Carga Pesada");
        btnCargaPesada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargaPesadaActionPerformed(evt);
            }
        });

        btnCargaSuelta.setText("Carga Suelta");
        btnCargaSuelta.setMaximumSize(new java.awt.Dimension(110, 32));
        btnCargaSuelta.setMinimumSize(new java.awt.Dimension(110, 32));
        btnCargaSuelta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargaSueltaActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel21.setText("Carga Pesada:");

        jLabel22.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel22.setText("Carga Suelta:");

        btn_Salir2.setBackground(new java.awt.Color(255, 0, 0));
        btn_Salir2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        btn_Salir2.setText("Salir");
        btn_Salir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Salir2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pExportacionLayout = new javax.swing.GroupLayout(pExportacion);
        pExportacion.setLayout(pExportacionLayout);
        pExportacionLayout.setHorizontalGroup(
            pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pExportacionLayout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel18)
                        .addComponent(jLabel13)))
                .addGap(216, 216, 216))
            .addGroup(pExportacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pExportacionLayout.createSequentialGroup()
                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(pExportacionLayout.createSequentialGroup()
                                .addComponent(text_Idcotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_generarCotizacion)))
                        .addGap(61, 61, 61)
                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pExportacionLayout.createSequentialGroup()
                                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(seleccionar_Zona2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pExportacionLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(spnKg, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                                .addComponent(spnFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pExportacionLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112))))
                    .addGroup(pExportacionLayout.createSequentialGroup()
                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pExportacionLayout.createSequentialGroup()
                                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(pExportacionLayout.createSequentialGroup()
                                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(seleccionar_Zona1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pExportacionLayout.createSequentialGroup()
                                                    .addComponent(jLabel19)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(cbTipoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pExportacionLayout.createSequentialGroup()
                                                    .addGap(151, 151, 151)
                                                    .addComponent(btnCargaPesada, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCargaSuelta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pExportacionLayout.createSequentialGroup()
                                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel14))
                                        .addGap(4, 4, 4)
                                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbPiesContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbTipoServicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(397, 397, 397)
                                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbTipoServicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnPiesCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(3, 3, 3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(btn_Salir2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(pExportacionLayout.createSequentialGroup()
                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(text_direccionEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pExportacionLayout.setVerticalGroup(
            pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExportacionLayout.createSequentialGroup()
                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pExportacionLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Salir2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCargaPesada, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(btnCargaSuelta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pExportacionLayout.createSequentialGroup()
                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(cbTipoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(spnPiesCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cbPiesContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pExportacionLayout.createSequentialGroup()
                        .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(cbTipoServicio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbTipoServicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seleccionar_Zona1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionar_Zona2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pExportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnKg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_Idcotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_generarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text_direccionEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Datos de Exportación", pExportacion);

        pCosto.setBackground(new java.awt.Color(153, 255, 153));

        btn_save.setText("Consultar datos");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "ID", "Edad", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCliente.setCellSelectionEnabled(true);
        tablaCliente.setEnabled(false);
        tablaCliente.setFocusable(false);
        jScrollPane2.setViewportView(tablaCliente);

        tablaExportacion1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID.Cotizacion", "Kilogramos", "Zona a exportar", "Direccion a entregar", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaExportacion1.setEnabled(false);
        jScrollPane4.setViewportView(tablaExportacion1);

        tablaDesglosar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Gramo", "Libras", "Tonelas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaDesglosar);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        tablaPesada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de Carga", "Pies del Contenedor", "Tipo del Servicio", "Costo en Dolares"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPesada.setEnabled(false);
        jScrollPane5.setViewportView(tablaPesada);

        tablaSuelta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pies de la carga", "Tipo de servicio", "Costo en Dolares"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaSuelta.setEnabled(false);
        jScrollPane6.setViewportView(tablaSuelta);

        jLabel12.setText("Carga Pesada:");

        jLabel16.setText("Carga Suelta:");

        jPanel10.setBackground(new java.awt.Color(51, 255, 51));

        jLabel17.setFont(new java.awt.Font("Old English Text MT", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Exporta-CR");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(51, 255, 51));

        jLabel20.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Detalles y Calculos:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel23.setText("Cliente:");

        jLabel24.setText("Detalles de la carga:");

        jLabel25.setText("Kilogramos desglosados:");

        btn_SaveAprobados.setText("Aprobar exportación");
        btn_SaveAprobados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveAprobadosActionPerformed(evt);
            }
        });

        btn_Salir3.setBackground(new java.awt.Color(255, 0, 0));
        btn_Salir3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        btn_Salir3.setText("Salir");
        btn_Salir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Salir3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pCostoLayout = new javax.swing.GroupLayout(pCosto);
        pCosto.setLayout(pCostoLayout);
        pCostoLayout.setHorizontalGroup(
            pCostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCostoLayout.createSequentialGroup()
                .addGroup(pCostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCostoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pCostoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel25))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pCostoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pCostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pCostoLayout.createSequentialGroup()
                                .addGroup(pCostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(496, 496, 496)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pCostoLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(pCostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_SaveAprobados, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pCostoLayout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Salir3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pCostoLayout.setVerticalGroup(
            pCostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCostoLayout.createSequentialGroup()
                .addGroup(pCostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pCostoLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pCostoLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(pCostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_SaveAprobados, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))))
        );

        jTabbedPane1.addTab("Costo", pCosto);

        pRegistro.setBackground(new java.awt.Color(153, 255, 153));

        tablaArchivoClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "ID", "Edad", "Dirección"
            }
        ));
        jScrollPane7.setViewportView(tablaArchivoClientes);

        btn_listaClientes.setText("Actualizar lista");
        btn_listaClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listaClientesActionPerformed(evt);
            }
        });

        btn_GuardarModify.setText("Guardar modificación");
        btn_GuardarModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarModifyActionPerformed(evt);
            }
        });

        btn_Borrar.setText("Eliminar Cliente");
        btn_Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BorrarActionPerformed(evt);
            }
        });

        btn_Salir4.setBackground(new java.awt.Color(255, 0, 0));
        btn_Salir4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        btn_Salir4.setText("Salir");
        btn_Salir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Salir4ActionPerformed(evt);
            }
        });

        tablaAprobados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "ID", "Tipo de exportación", "Fecha", "Costo en dolares", "Zona"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAprobados.setEnabled(false);
        jScrollPane8.setViewportView(tablaAprobados);
        if (tablaAprobados.getColumnModel().getColumnCount() > 0) {
            tablaAprobados.getColumnModel().getColumn(0).setResizable(false);
            tablaAprobados.getColumnModel().getColumn(1).setResizable(false);
            tablaAprobados.getColumnModel().getColumn(2).setResizable(false);
            tablaAprobados.getColumnModel().getColumn(3).setResizable(false);
            tablaAprobados.getColumnModel().getColumn(4).setResizable(false);
            tablaAprobados.getColumnModel().getColumn(5).setResizable(false);
        }

        txt_Busqueda.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.darcula.color1"));

        btn_Busqueda.setText("Buscar");
        btn_Busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BusquedaActionPerformed(evt);
            }
        });

        jLabel28.setText("Digite un ID, Zona o Carga a buscar:");

        jPanel12.setBackground(new java.awt.Color(51, 255, 51));

        jLabel31.setFont(new java.awt.Font("Old English Text MT", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Exporta-CR");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(51, 255, 51));

        jLabel32.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Informe de exportaciones:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(51, 255, 51));

        jLabel33.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Lista de clientes:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel26.setBackground(new java.awt.Color(102, 102, 102));
        jLabel26.setFont(new java.awt.Font("Showcard Gothic", 0, 10)); // NOI18N
        jLabel26.setText("Nota: Doble click en el dato a modificar, presione Enter y click en Guardar modificación.");

        javax.swing.GroupLayout pRegistroLayout = new javax.swing.GroupLayout(pRegistro);
        pRegistro.setLayout(pRegistroLayout);
        pRegistroLayout.setHorizontalGroup(
            pRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pRegistroLayout.createSequentialGroup()
                .addGroup(pRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pRegistroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btn_GuardarModify)
                        .addGap(41, 41, 41)
                        .addComponent(btn_listaClientes))
                    .addGroup(pRegistroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pRegistroLayout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pRegistroLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(txt_Busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Busqueda)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pRegistroLayout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pRegistroLayout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pRegistroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Salir4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addComponent(jScrollPane8)))
        );
        pRegistroLayout.setVerticalGroup(
            pRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pRegistroLayout.createSequentialGroup()
                .addGroup(pRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(pRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Borrar)
                    .addComponent(btn_GuardarModify)
                    .addComponent(btn_listaClientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Busqueda))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registro", pRegistro);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Salir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Salir4ActionPerformed
       salir();    
    }//GEN-LAST:event_btn_Salir4ActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed

        try{

            //Condicional para no dejar espacios blancos
            if(text_Nombre.getText().isEmpty() || text_Direccion.getText().isEmpty() || text_Id.getText().isEmpty() ||
                text_direccionEntrega.getText().isEmpty() || presionar == false  ){

                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos correctamente. Vuelva a intentarlo!");
                mostrar = false;
            }else{
                mostrar = true;
                datos = new Cliente();
                datos1 = new CalculoExportacion();
                datosPesada = new CargaPesada();
                datosSuelta = new CargaSuelta();

                //Capturar los datos cliente anotados
                datos.setNombre(this.text_Nombre.getText());
                datos.setId(this.text_Id.getText());
                datos.setEdad(Integer.parseInt(spnEdad.getValue().toString()));
                datos.setDireccion(this.text_Direccion.getText());

                

                //Capturar los datos de calculo
                datos1.setKg(Double.parseDouble(spnKg.getValue().toString()));
                datos1.setZona_exportacion(seleccionar_Zona1.getSelectedItem().toString());
                datos1.setZona_exportacion(seleccionar_Zona2.getSelectedItem().toString());
                datos1.setZona_entrega(this.text_direccionEntrega.getText());
                datos1.setFecha((Date) (spnFecha.getValue()));

                //Capturar datos a desglosar
                double totalGr = CalculoExportacion.gramos(datos1.getGramos(),datos1.getKg());
                datos1.setGramos(totalGr);

                double totalLb = CalculoExportacion.libras(datos1.getLibras(),datos1.getKg());
                datos1.setLibras(totalLb);

                double totalTn = CalculoExportacion.toneladas(datos1.getToneladas(),datos1.getKg());
                datos1.setToneladas(totalTn);

                //Condicional booleana para capturar datos si se pulsa el boton de la exportación
                if(pesada == true){
                    
                    //No exceder kg de carga
                    if(cbPiesContenedor.getSelectedItem().equals("20") && datos1.getKg() > 28000 || cbPiesContenedor.getSelectedItem().equals("40") && datos1.getKg() > 29000){

                        Toolkit.getDefaultToolkit().beep(); //Emite un sonido de alerta.
                        JOptionPane.showMessageDialog(null, "Ha excedido el número de Kilogramos!");

                    }else{
                        //Capturar datos tabla de carga pesada
                        datosPesada.setTipoCarga(cbTipoCarga.getSelectedItem().toString());
                        datosPesada.setPiesContenedor(cbPiesContenedor.getSelectedItem().toString());
                        datosPesada.setTipoServicio(cbTipoServicio1.getSelectedItem().toString());

                        //funcion de la formula costos de la cargas pesadas
                        totalPesada = CargaPesada.costoPesada(datos1.getKg(), datosPesada.getPiesContenedor(),datosPesada.getTipoCarga(),datosPesada.getTipoServicio(),datosPesada.getCosto());
                        datosPesada.setCosto(totalPesada);
                       
                        llenarPesada();

                        llenar();
                        llenar1();
                        llenar2();
                        

                    }

                }

                if (pesada == false){
                    //Capturar datos tabla suelta
                    datosSuelta.setPiesCarga(Double.parseDouble(spnPiesCarga.getValue().toString()));
                    datosSuelta.setTipoServicio(cbTipoServicio2.getSelectedItem().toString());

                    //funcion de la formula costos de la cargas  sueltas
                    totalSuelta = CargaSuelta.costoSuelta(datosSuelta.getCosto(),datosSuelta.getTipoServicio(),datosSuelta.getPiesCarga(),datos1.getKg());
                    datosSuelta.setCosto(totalSuelta);

                    llenarSuelta();
                    llenar();
                    llenar1();
                    llenar2();
                    

                }
            }
        } catch (Exception mensaje) { // Envía un mensaje de error en caso de no salir bien el ingreso de datos.
            JOptionPane.showMessageDialog(null, "Error 404. Vuelva a intentarlo!");
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btnCargaSueltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargaSueltaActionPerformed
        presionar = (true);//booleana para obligar a escoger un boton de exportación

        String tipoExportacion = "Suelta";
        //Activa botones de Exportación suelta
        pesada = (false);
        spnPiesCarga.setEnabled(true);
        cbTipoServicio2.setEnabled(true);

        //Desactivar botones de Exportacíon pesada
        cbTipoCarga.setEnabled(false);
        cbPiesContenedor.setEnabled(false);
        cbTipoServicio1.setEnabled(false);
    }//GEN-LAST:event_btnCargaSueltaActionPerformed

    private void btnCargaPesadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargaPesadaActionPerformed

        presionar = (true); //booleana para obligar a escoger un boton de exportación

        //Activa botones de Exportación pesada
        pesada = (true);
        cbTipoCarga.setEnabled(true);
        cbPiesContenedor.setEnabled(true);
        cbTipoServicio1.setEnabled(true);

        //Desactivar botones de Exportación suelta
        spnPiesCarga.setEnabled(false);
        cbTipoServicio2.setEnabled(false);
    }//GEN-LAST:event_btnCargaPesadaActionPerformed

    private void cbTipoCargaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoCargaItemStateChanged
        //condicional para anidar el comboBos de tipo de carga con tipo de Servicio pesado
        if(evt.getStateChange() == ItemEvent.SELECTED){
            this.cbTipoServicio1.setModel(new DefaultComboBoxModel(this.getCarga(this.cbTipoCarga.getSelectedItem().toString())));

        }
    }//GEN-LAST:event_cbTipoCargaItemStateChanged

    private void text_direccionEntregaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_direccionEntregaKeyTyped
        //Condicional de la dirección para no exceder más caracteres
        if (text_direccionEntrega.getText().length() > 20) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Ha excedido el número de carácteres!");
        }
        char letras = evt.getKeyChar();
            if (letras == '|') { //Permite solo letras y espacios.
            JOptionPane.showMessageDialog(null, "Caracter invalido");
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_text_direccionEntregaKeyTyped

    private void btn_generarCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generarCotizacionActionPerformed
        try{
            datos1 = new CalculoExportacion(); // Se inicializa la clase prestamo.
            int cotizar = CalculoExportacion.aleatorio(datos1.getId_cotizacion()); //Se llama a la función de aleatorio de la clase préstamo.
            text_Idcotizacion.setText(String.valueOf(cotizar)); //Se pega el texto del boton Id_Cotizador al espacio en blanco
        } catch (Exception aviso) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Vuelva a intentarlo");
        }
    }//GEN-LAST:event_btn_generarCotizacionActionPerformed

    private void text_DireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_DireccionKeyTyped
        //Condicional de la dirección para no exceder más caracteres
        if (text_Direccion.getText().length() > 20) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Ha excedido el número de carácteres!");
        }
            char letras = evt.getKeyChar();
            if (letras == '|') { //Permite solo letras y espacios.
            JOptionPane.showMessageDialog(null, "Caracter invalido");
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_text_DireccionKeyTyped

    private void text_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_NombreKeyTyped
        //Condicionales para solo agregar caracteres en el nombre
        char letras = evt.getKeyChar();
        if (text_Nombre.getText().length() > 50) {//Limita los caracteres
            evt.consume();
            Toolkit.getDefaultToolkit().beep(); //Emite un sonido de alerta.
            JOptionPane.showMessageDialog(null, "Ha excedido el número de caracteres!");
        }

        if (((letras < 'a' || letras > 'z') && (letras < 'A' || letras > 'Z')) && (letras != KeyEvent.VK_BACK_SPACE) && (letras != ' ')) { //Permite solo letras y espacios.
            JOptionPane.showMessageDialog(null, "Solo se permiten caracteres");
            evt.consume();
        }
    }//GEN-LAST:event_text_NombreKeyTyped

    private void text_IdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_IdKeyTyped
        char letras = evt.getKeyChar();
        //Condicional de la ID para no exceder más caracteres
        if (text_Id.getText().length() > 10){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Ha excedido el número de carácteres!");
        }
        if (letras == '|') { //Permite solo letras y espacios.
            JOptionPane.showMessageDialog(null, "Caracter invalido");
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
        
    }//GEN-LAST:event_text_IdKeyTyped

    private void text_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_IdActionPerformed

    private void btn_listaClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listaClientesActionPerformed
     //Funcion para borrar datos de la Jtable para mantenerla sin datos repetidos
     eliminarDatosClientes();
      
     //Leer los archivos de texto para llenar la tabla
     mostrarClientes();
     
     JOptionPane.showMessageDialog(null, "Lista actualizada");
    
    }//GEN-LAST:event_btn_listaClientesActionPerformed

    private void btn_GuardarModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarModifyActionPerformed

    modificarCliente();
    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
    eliminarDatosClientes();
    mostrarClientes();  

    }//GEN-LAST:event_btn_GuardarModifyActionPerformed

    private void btn_BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BorrarActionPerformed
     //Elimina el cliente marcado  
      eliminarCliente();
      modificarCliente();
     

    }//GEN-LAST:event_btn_BorrarActionPerformed

    private void btn_SaveAprobadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveAprobadosActionPerformed
           int opcion = JOptionPane.showConfirmDialog(this, "Acepta el costo cotizado de la exportación?", "Aceptar",JOptionPane.YES_NO_OPTION); 
           if(opcion == 0){ // opcion == 0 significa SI
             JOptionPane.showMessageDialog(null, "Revisando solicitud de exportación, un momento \n Click en aceptar...");
        
            try {
            Thread.sleep(2000);
            } catch (InterruptedException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        
             if(mostrar == false){
                JOptionPane.showMessageDialog(null, "Estimado " +this.text_Nombre.getText()+" debe consultar primero sus datos de exportacion");
             }else{
                
            datos = new Cliente();
        
           //Capturar los datos cliente aprobados
            datos.setNombre(this.text_Nombre.getText());
            datos.setId(this.text_Id.getText());
    
           //Capturar los datos de exportacion
             datos1.setZona_exportacion(seleccionar_Zona1.getSelectedItem().toString());
             datos1.setFecha((Date)(spnFecha.getValue()));
             
             //Condicional para verificar la exportación de un cliente existente en el registro o no
             if(listaIds.contains(datos.getId())){
                //Funciones para guardar al array las variables y pasarlas al archivo dat
                guardarArrayAprobados();
                guardarDatAprobados();
                
                JOptionPane.showMessageDialog(null, "Estimado " +this.text_Nombre.getText()+" su exportación fue aprobada con éxito!");
                //Funcion para recorrer las ids aprobadas para validaciones
                recorrerAprobados();
                reset();
                resetTablas();
                mostrar = false;
 
            }else{
             JOptionPane.showMessageDialog(null, "Estimado " +this.text_Nombre.getText()+" su exportación fue no aprobada, registre el ID"); 
            
        }      
       }                     
      }    
    }//GEN-LAST:event_btn_SaveAprobadosActionPerformed

    private void btn_RegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegistrarClienteActionPerformed
        datos = new Cliente();
        //Capturar los datos cliente anotados
          datos.setNombre(this.text_Nombre.getText());
          datos.setId(this.text_Id.getText());
          datos.setEdad(Integer.parseInt(spnEdad.getValue().toString()));
          datos.setDireccion(this.text_Direccion.getText());
        if(text_Nombre.getText().isEmpty() || text_Direccion.getText().isEmpty() || text_Id.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No debe dejar campos vacios");
        }else{
        
        if(listaIds.contains(datos.getId())){
            JOptionPane.showMessageDialog(null, "Este ID ya se encuentra registrado");
            
        }else{
            //Llamar la funcion de lista y archivos cliente para guardar el dat
            guardarArrayClientes();
            guardarDatClientes();
            //Funcion para borrar datos de la Jtable para mantenerla sin datos repetidos
            eliminarDatosClientes();
            //Leer los archivos de texto para llenar la tabla
            mostrarClientes();
          
            JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");  
            
        }      
      }
    }//GEN-LAST:event_btn_RegistrarClienteActionPerformed

    private void btn_BusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BusquedaActionPerformed
        //Funcion para borrar datos de la Jtable para mantenerla sin datos repetidos
        int fila= tablaAprobados.getRowCount();
        for(int i = fila-1;i >=0; i--){
         
         infoAprobados.removeRow(i);
        }
         buscarAprobados();
    }//GEN-LAST:event_btn_BusquedaActionPerformed

    private void btn_AutocompletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AutocompletarActionPerformed
        autoCompletar();
    }//GEN-LAST:event_btn_AutocompletarActionPerformed

    private void btn_Salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Salir1ActionPerformed
        salir();
    }//GEN-LAST:event_btn_Salir1ActionPerformed

    private void btn_Salir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Salir2ActionPerformed
        salir();
    }//GEN-LAST:event_btn_Salir2ActionPerformed

    private void btn_Salir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Salir3ActionPerformed
        salir();
    }//GEN-LAST:event_btn_Salir3ActionPerformed

    private void seleccionar_Zona1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_seleccionar_Zona1ItemStateChanged
         // Condicional para anidar los ComboBox de las Zonas
        if(evt.getStateChange() == ItemEvent.SELECTED){
            this.seleccionar_Zona2.setModel(new DefaultComboBoxModel(this.getZona(this.seleccionar_Zona1.getSelectedItem().toString())));

        }
    }//GEN-LAST:event_seleccionar_Zona1ItemStateChanged

    //Anidar Zonas de exportacion y ciudades respectivas
    public String[] getZona(String estado){
     String[] zona = new String[9];
     if(estado.equals("Nueva Inglaterra"))
     {
      zona[0] = "Maine";
      zona[1] = "Nuevo Hampshire";
      zona[2] = "Vermont";
      zona[3] = "Massachusetts";
      zona[4] = "Connecticut";
      zona[5] = "Rhode Island";
         
     }
 
     if(estado.equalsIgnoreCase("Atlántico Medio"))
     {
      zona[0] = "Nueva York";
      zona[1] = "Pensilvania";
      zona[2] = "Nueva Jersey";
         
     }
     
     if(estado.equalsIgnoreCase("Atlántico Sur"))
     {
      zona[0] = "Maryland";
      zona[1] = "Delaware";
      zona[2] = "Virginia Occidental";
      zona[3] = "Virginia";
      zona[4] = "Carolina del Norte";
      zona[5] = "Carolina del Sur";
      zona[6] = "Georgia";
      zona[7] = "Florida";
      
     }
     
     if(estado.equalsIgnoreCase("Centro Norte Oriental"))
     {
      zona[0] = "Michigan";
      zona[1] = "Wisconsin";
      zona[2] = "Illinois";
      zona[3] = "Indiana";
      zona[4] = "Ohio";
      
     }
     
      if(estado.equalsIgnoreCase("Centro Sur Oriental"))
     {
      zona[0] = "Kentucky";
      zona[1] = "Tennessee";
      zona[2] = "Alabama";
      zona[3] = "Misisipi";
      
     }
      
      if(estado.equalsIgnoreCase("Centro Norte Occidental"))
     {
      zona[0] = "Minnesota";
      zona[1] = "Dakota del Norte";
      zona[2] = "Dakota del Sur";
      zona[3] = "Iowa";
      zona[4] = "Nebraska";
      zona[5] = "Misuri";
      zona[6] = "Kansas";
      
     }
     
       if(estado.equalsIgnoreCase("Centro Sur Occidental"))
     {
      zona[0] = "Arkansas";
      zona[1] = "Oklahoma";
      zona[2] = "Texas";
      zona[3] = "Luisiana";
      
     }
      
       if(estado.equalsIgnoreCase("Montaña"))
     {
      zona[0] = "Montana";
      zona[1] = "Idaho";
      zona[2] = "Wyoming";
      zona[3] = "Colorado";
      zona[4] = "Utah";
      zona[5] = "Nevada";
      zona[6] = "Arizona";
      zona[7] = "Nuevo México";
      
     }
       
         if(estado.equalsIgnoreCase("Pacífico"))
     {
      zona[0] = "Alaska";
      zona[1] = "Washington";
      zona[2] = "Oregón";
      zona[3] = "California";
      zona[4] = "Hawái";
      
     }

      return zona;  
    }
    
    //Agregar servicios al comboBox de servios pesada
    public String[] getCarga(String carga){
     String[] servicio = new String[2];
     if(carga.equals("Contenedor Refrigerado")){
      servicio[0] = "Barco";
         }
     if(carga.equals("Contenedor no refrigerado")){
      servicio[0] = "Barco";
         }
     
     if(carga.equals("Carga embalada")){
      servicio[0] = "Barco";
      servicio[1] = "Avión";
         }
     return servicio;
      }
    
    public void llenar() { // Esta función captura los datos ingresados y los muestra en la tabla 1.
        info.addRow(new Object[]{
            text_Nombre.getText(), text_Id.getText(), spnEdad.getValue(), text_Direccion.getText() 
        });
    }
    
    //Capturar los datos de Calculo
    public void llenar1() { // Esta función captura los datos ingresados y los muestra en la tabla 2.
        info1.addRow(new Object[]{
            text_Idcotizacion.getText(),spnKg.getValue(), seleccionar_Zona1.getSelectedItem(), seleccionar_Zona2.getSelectedItem(), text_direccionEntrega.getText(), spnFecha.getValue()
        });
    }
    
   public void llenar2() { // Esta función captura los datos ingresados y los muestra en la tabla 3.
        info2.addRow(new Object[]{
            datos1.getGramos(),datos1.getLibras(),datos1.getToneladas()
        });
    }
   
   public void llenarPesada() { // Esta función captura los datos ingresados y los muestra en la tabla Pesada.
        infoPesada.addRow(new Object[]{
            cbTipoCarga.getSelectedItem(),cbPiesContenedor.getSelectedItem(),cbTipoServicio1.getSelectedItem(),datosPesada.getCosto()
        });
    }
   
   public void llenarSuelta() { // Esta función captura los datos ingresados y los muestra en la tabla Suelta.
        infoSuelta.addRow(new Object[]{
            spnPiesCarga.getValue(),cbTipoServicio2.getSelectedItem(),datosSuelta.getCosto()
        });
    }


      
      //Funcion para guardar los datos ingresados a la Arraylist
      public void guardarArrayClientes(){
          
        if(listaClientes.contains(text_Id)){
            //Condicional para no permitir registrar un ID igual
            JOptionPane.showMessageDialog(null, "ID ya registrado"); 
            
        }else{//Si no encuentra un id en la lista clientes lo guarda
            listaClientes.clear();
            listaClientes.add(datos.getNombre()+"|"+datos.getId()+"|"+String.valueOf(datos.getEdad())+"|"+datos.getDireccion()+"\n");
        }
   
      }
      
      //Función para guardar al Arraylist los aprobados
      public void guardarArrayAprobados(){

         String tipoServicio = null;
         String costoServicio = null;
         
         //Condicionales para las cargas pesadas y sueltas
         if(pesada == true){
             tipoServicio = "Carga pesada";
             costoServicio = String.valueOf(totalPesada);
         }
         if(pesada == false){
             tipoServicio = "Carga suelta";
             costoServicio = String.valueOf(totalSuelta);
         }
         
         listaAprobados.clear();
         listaAprobados.add(datos.getNombre()+"|"+datos.getId()+"|"+tipoServicio+"|"+String.valueOf(datos1.getFecha())+"|"+costoServicio+"|"+datos1.getZona_exportacion()+"\n");
         
      }
      
     //Funcion para guardar los datos de la Arraylist al documento
      public void guardarDatClientes(){
         try{
          
          FileWriter salvar = new FileWriter("clientes_exportaciones.dat",true);
          for(int i=0; i<listaClientes.size(); i++){
   
               salvar.write(listaClientes.get(i));

          }
            salvar.close();
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error"+e);  
      } 
   }
      
      //Función para guardar al dat los aprobados
      public void guardarDatAprobados(){
          try{
          
          FileWriter salvarAprobados = new FileWriter("exportaciones_aprobadas.dat",true);
          for(int i=0; i<listaAprobados.size(); i++){
   
               salvarAprobados.write(listaAprobados.get(i));
               
 
              }
                 salvarAprobados.close();
            
             }catch(IOException e){
                 JOptionPane.showMessageDialog(null, "Error"+e);  
        } 
     }
      
    //Funcion para modificar las lineas de la Jtable al archivo de texto
      public void modificarCliente(){
          try{
         FileWriter fw = new FileWriter("clientes_exportaciones.dat");
            for(int i=0; i<this.tablaArchivoClientes.getRowCount(); i++){
                 
                   //Escribir los datos de la tabla directamente en el archivo
                   fw.write(infoListaClientes.getValueAt(i, 0).toString()+"|");
                   fw.write(infoListaClientes.getValueAt(i, 1).toString()+"|");
                   fw.write(infoListaClientes.getValueAt(i, 2).toString()+"|");
                   fw.write(infoListaClientes.getValueAt(i, 3).toString()+"\n");
                   

            }
           fw.close();
 
       }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error"+e); 
       }    
      }
      
      //Funcion para eliminar la fila seleccionada del jtable y del archivo
      public void eliminarCliente(){
       boolean aprobado = false;
       boolean eliminar = false;
       int fila;
       fila = tablaArchivoClientes.getSelectedRow(); //fila sera igual a la seleccionada
       //Condicional de haber seleccionado una fila
       if(fila >= 0){
           //Recorrer los datos del ID de la tabla clientes para verificar si tiene exportaciones
           for(int i = 0; i<tablaArchivoClientes.getRowCount();i++){
               
               if(listaIdAprobadas.contains(tablaArchivoClientes.getValueAt(fila, 1))){
                   aprobado = true; //activar si encuentra un ID aprobado
  
                }else{
                   eliminar = true; //booleano para eliminar el cliente fuera del for
            }
          }
        }else{  //Condicional si no selecciona una fila
          JOptionPane.showMessageDialog(null, "Debe seleccionar una fila a eliminar");
       }
       
       //Condicional para eliminar cliente fuera del for
       if(eliminar == true){
           
          if(fila >= 0){
            int opcion = JOptionPane.showConfirmDialog(this, "Desea eliminar el cliente?", "Eliminar",JOptionPane.YES_NO_OPTION); 
                  if(opcion == 0){ // opcion == 0 significa SI en el OptionPane por lo cual lo elimina
                  
                  infoListaClientes.removeRow(fila);    
    
            }     
          }    
       }
       //Condicional para no eliminar cliente
       if(aprobado == true){
           
           JOptionPane.showMessageDialog(null, "Error: El cliente posee exportaciones aprobadas");
       }
      }
      
      //Borrar datos de la tabla cliente para refrescar y que no se repitan los datos mostrados
      public void eliminarDatosClientes(){
    
      //Funcion para borrar datos de la tabla
     int fila= tablaArchivoClientes.getRowCount();
     for(int i = fila-1;i >=0; i--){  
         
         infoListaClientes.removeRow(i);
        }    
      }
      
     //Funcion para leer el archivo dat de clientes en la tabla
      public void mostrarClientes(){    
      String nombre = null;
      String id = null;
      String edad = null;
      String direccion = null;
      
      listaIds.clear(); 

      String archivo = "clientes_exportaciones.dat";
      
      linea = null;
     
      File datosCliente = new File(archivo);   
      
      try{
        linea = new Scanner(datosCliente);
        
        while(linea.hasNext()){
          String lineaLeida = linea.nextLine();  //se lee una línea
          //Token para separar por simbolo |
          StringTokenizer tokens = new StringTokenizer(lineaLeida,"|");
     
          String[] datos = new String[tokens.countTokens()];
            int i = 0;
               
            if (datosCliente.exists()) {   //si la línea contiene el texto buscado se muestra por pantalla        
                while(tokens.hasMoreTokens()){ 
                  datos[i] = tokens.nextToken();
                 //Función para capturar los datos separados a variables
                    nombre=datos[0];
                    id=datos[1]; 
                    edad=datos[2];
                    direccion=datos[3];

                    i++;  
                    
             }
                
              //Mostrar las variables capturadas a la tabla Clientes
                infoListaClientes.addRow(new Object[]{nombre,id,edad,direccion});
                              
                //Guardar las variables ids a una lista para su posterior uso en las validaciones
                 idClientes = id.split("\n");          
                  for(i=0; i<idClientes.length; i++){
                   listaIds.add(idClientes[i]);     
             }             
           }
        }

      }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error"+e); 
          
      }
   }
      
      //Funcion para recorrer las ids aprobadas para validaciones
      public void recorrerAprobados(){
        //Instancia del archivo aprobados
        File archivo = new File("exportaciones_aprobadas.dat");
        try{
         linea = new Scanner(archivo);

        //LINEA LEIDA
          String lineaLeida;

        // MIENTRAS LA LINEA LEIDA NO SEA NULL
           while (linea.hasNext()) { //mientras no se llegue al final del fichero
             lineaLeida = linea.nextLine();  //se lee una línea
             //Token para separar por simbolo |
              StringTokenizer tokens = new StringTokenizer(lineaLeida,"|");
                    
               String[] datos = new String[tokens.countTokens()];
                int i = 0;
                 if(archivo.exists()) {           
                     while(tokens.hasMoreTokens()){
                        //Recorrer y guardar en datos lo encontrado en el texto 
                        datos[i] = tokens.nextToken();   
                        i++;    
                       }
                  
                  //Guardar las variables ids guardadas en datos, a la lista id aprobados para su posterior uso en las validaciones
                    idAprobadas = datos[1].split("\n");          
                    for(i=0; i<idAprobadas.length; i++){
                    listaIdAprobadas.add(idAprobadas[i]);     
                }          
             }
           }
         }catch(IOException e){
          JOptionPane.showMessageDialog(null, "Error"+e);     
       }      
    }
      
      public void buscarAprobados(){
          //Instancia del archivo aprobados
         File archivo = new File("exportaciones_aprobadas.dat");

          try{
               
            // SI EXISTE EL ARCHIVO
            if(archivo.exists()) {
                linea = new Scanner(archivo);

                // LINEA LEIDA
                String lineaLeida;
                
                String nombre=null;
                String id=null;
                String tipoExportacion=null;
                String fecha=null;
                String costo=null;
                String zonaEnvio=null;

                boolean contiene = false;
                // MIENTRAS LA LINEA LEIDA NO SEA NULL
                 while (linea.hasNext()) { //mientras no se llegue al final del fichero
                    lineaLeida = linea.nextLine();  //se lee una línea
                    //Token para separar por simbolo |
                    StringTokenizer tokens = new StringTokenizer(lineaLeida,"|");
                    
                    String[] datos = new String[tokens.countTokens()];
                    int i = 0;
                    if (lineaLeida.contains(txt_Busqueda.getText())) {   //si la línea contiene el texto buscado se muestra por pantalla        
                        while(tokens.hasMoreTokens()){
                          datos[i] = tokens.nextToken();
                          //Función para capturar los datos separados a variables
                             nombre=datos[0];
                             id=datos[1];
                             tipoExportacion=datos[2];
                             fecha=datos[3];
                             costo=datos[4];
                             zonaEnvio=datos[5];
                            
                            i++;    
                            
                            
                        }

                            if(id.contains(txt_Busqueda.getText()) || tipoExportacion.contains(txt_Busqueda.getText()) || zonaEnvio.contains(txt_Busqueda.getText()) ){
                            //Mostrar las variables capturadas a la tabla del informe
                              infoAprobados.addRow(new Object[]{nombre,id,tipoExportacion,fecha,costo,zonaEnvio});
                              contiene = true; 
                              
                       }else{                         
                        contiene = false; 
                        
                      }     
                     }
                  }
                 
                if(!contiene){ //si el archivo no contienen el texto se muestra un mensaje indicándolo
                    JOptionPane.showMessageDialog(null, txt_Busqueda.getText() + " no se encuentran coincidencias");
             }
            }
            
          }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error"+e); 
              
          } 
      }
      
         public void autoCompletar(){
         //Instancia del archivo aprobados
         File archivo = new File("clientes_exportaciones.dat");

          try{
             boolean contiene = false;  
            // SI EXISTE EL ARCHIVO
            if(archivo.exists()) {
                linea = new Scanner(archivo);

                // LINEA LEIDA
                String lineaLeida;
                
                String nombre=null;
                String id=null;
                String edad=null;
                String direccion=null;
                
                
                // MIENTRAS LA LINEA LEIDA NO SEA NULL
                 while (linea.hasNext()) { //mientras no se llegue al final del fichero
                    lineaLeida = linea.nextLine();  //se lee una línea
                    //Token para separar por simbolo |
                    StringTokenizer tokens = new StringTokenizer(lineaLeida,"|");
                    
                    String[] datos = new String[tokens.countTokens()];
                    int i = 0;
                    if (lineaLeida.contains(text_Id.getText())){   //si la línea contiene el texto buscado se muestra por pantalla        
                        while(tokens.hasMoreTokens()){
                          datos[i] = tokens.nextToken();
                          
                          //Función para capturar los datos separados a variables
                             nombre=datos[0];
                             id = datos[1];
                             edad=datos[2];
                             direccion=datos[3];
                             
                             i++;
                                 
                     } 
                        //Condicional que completa los espacios del ID encontrado
                            if (datos[1].equals(text_Id.getText())) { 
                               contiene = true;
                               text_Nombre.setText(nombre);
                               spnEdad.setValue(Integer.parseInt(edad));
                               text_Direccion.setText(direccion);
                                 
                         }else{
                                contiene = false;
                     }
                  }
                   
               }
            }
            
            if(!contiene){
             
                    JOptionPane.showMessageDialog(null, "Datos del ID no encontrados");
            }
            
         }catch(IOException e){
           JOptionPane.showMessageDialog(null, "Error"+e);      
      }   
   }
      
   
   public void reset() { // Esta función limpia los espacios de escritura en la GUI una vez de agregan a la tabla.
        text_Nombre.setText("");
        text_Id.setText("");
        text_Direccion.setText("");
        spnEdad.setValue(18);
        text_Idcotizacion.setText("");
        spnFecha.setValue(fecha);
        text_direccionEntrega.setText("");
        spnKg.setValue(1);
        seleccionar_Zona1.setSelectedIndex(0);
        cbTipoCarga.setSelectedIndex(0);
        cbPiesContenedor.setSelectedIndex(0);
        cbTipoServicio1.setSelectedIndex(0);
        cbTipoServicio2.setSelectedIndex(0);
        spnPiesCarga.setValue(1);
        
        //Desactivar botones de la carga suelta y pesada
        presionar = (false);
        cbTipoCarga.setEnabled(false);
        cbPiesContenedor.setEnabled(false);
        cbTipoServicio1.setEnabled(false);
        spnPiesCarga.setEnabled(false);
        cbTipoServicio2.setEnabled(false);
          
    }
   
   public void resetTablas(){
      int fila;
    //Borrar dato cliente de la tabla
     fila= tablaCliente.getRowCount();
     for(int i = fila-1;i >=0; i--){  
         
         info.removeRow(i);
        }    
     //Borrar dato exportacion pesada de la tabla
     fila= tablaPesada.getRowCount();
     for(int i = fila-1;i >=0; i--){  
         
         infoPesada.removeRow(i);
        } 
    //Borrar dato exportacion suelta de la tabla
     fila= tablaSuelta.getRowCount();
     for(int i = fila-1;i >=0; i--){  
         
         infoSuelta.removeRow(i);
        }
     //Borrar dato de exportacion de la tabla
     fila= tablaExportacion1.getRowCount();
     for(int i = fila-1;i >=0; i--){  
         
         info1.removeRow(i);
        }   
        //Borrar datos de desglosar
     fila= tablaDesglosar.getRowCount();
     for(int i = fila-1;i >=0; i--){  
         
         info2.removeRow(i);
        }  
   }
   
   public void salir(){
      int opcion = JOptionPane.showConfirmDialog(this, "Desea salir?", "Aceptar",JOptionPane.YES_NO_OPTION); 
           if(opcion == 0){ // opcion == 0 significa SI
             modificarCliente();
             System.exit(0); // Sale del programa.
           } 
       
   }
    

    
    /**
     * @param args the command line arguments
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargaPesada;
    private javax.swing.JButton btnCargaSuelta;
    private javax.swing.JButton btn_Autocompletar;
    private javax.swing.JButton btn_Borrar;
    private javax.swing.JButton btn_Busqueda;
    private javax.swing.JButton btn_GuardarModify;
    private javax.swing.JButton btn_RegistrarCliente;
    private javax.swing.JButton btn_Salir1;
    private javax.swing.JButton btn_Salir2;
    private javax.swing.JButton btn_Salir3;
    private javax.swing.JButton btn_Salir4;
    private javax.swing.JButton btn_SaveAprobados;
    private javax.swing.JButton btn_generarCotizacion;
    private javax.swing.JButton btn_listaClientes;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> cbPiesContenedor;
    private javax.swing.JComboBox<String> cbTipoCarga;
    private javax.swing.JComboBox<String> cbTipoServicio1;
    private javax.swing.JComboBox<String> cbTipoServicio2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JPanel pCliente;
    private javax.swing.JPanel pCosto;
    private javax.swing.JPanel pExportacion;
    private javax.swing.JPanel pRegistro;
    private javax.swing.JComboBox<String> seleccionar_Zona1;
    private javax.swing.JComboBox<String> seleccionar_Zona2;
    private javax.swing.JSpinner spnEdad;
    private javax.swing.JSpinner spnFecha;
    private javax.swing.JSpinner spnKg;
    private javax.swing.JSpinner spnPiesCarga;
    private javax.swing.JTable tablaAprobados;
    private javax.swing.JTable tablaArchivoClientes;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTable tablaDesglosar;
    private javax.swing.JTable tablaExportacion1;
    private javax.swing.JTable tablaPesada;
    private javax.swing.JTable tablaSuelta;
    private javax.swing.JTextField text_Direccion;
    private javax.swing.JTextField text_Id;
    private javax.swing.JTextField text_Idcotizacion;
    private javax.swing.JTextField text_Nombre;
    private javax.swing.JTextField text_direccionEntrega;
    private javax.swing.JTextField txt_Busqueda;
    // End of variables declaration//GEN-END:variables
}
