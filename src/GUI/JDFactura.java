
package GUI;
import Conexion_BD.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JDFactura extends javax.swing.JDialog {

     //se especifican datos de conexion a base de datos//
    public JDFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Conexion basedatos = new Conexion();
        Connection conn;
        conn= basedatos.ObtenerConexion();
    }

    //se crea metodo guardar para validar primero si alguno de los campos esta vacio se dira que ingresen datos, si todos son
    //llenados entonces el sistema recopilara la informacion de los campos de la factura y enviara los datos a BD a sus 
    //respectivas tablas, datos de cliente a tabla clientes, datos de empresa a empresa y factura a factura//
       public void guardar(){
       int cod;
       String nombre1,nit1,dir1,nombre2, apellido2, nit2,dpi2,telefono2, dir2;
       nombre1= nombree.getText();
       nit1= nite.getText();
       dir1= direccione.getText();
       nombre2=nombresc.getText();
       apellido2=apellidosc.getText();
       nit2=nitc.getText();
       dpi2=dpic.getText();
       telefono2=telefonoc.getText();
       dir2=direccionc.getText();
       if(nombre1.equals("")|| nit1.equals("")||dir1.equals("")||nombre2.equals("")|| apellido2.equals("")||
               nit2.equals("")||dpi2.equals("")||dir2.equals("")||telefono2.equals("")){
           System.out.println("Porfavor ingrese todos los "
                   + "datos de los camos");
       }
       else {
           try{
               Conexion basedatos= new Conexion();
               Connection conn;
               conn= basedatos.ObtenerConexion();
               PreparedStatement ps= null;
               ResultSet consulta7 = null;
               
               String sql="insert datosempresa (nombre_empresa, nit_empresa, direccion_empresa, estado) values (?,?,?,1)";
               
               ps=conn.prepareStatement(sql);
               String nombres1= nombree.getText();
               ps.setString(1, nombres1);
               String nit= nite.getText();
               ps.setString(2, nit);
               String direcciones= direccione.getText();
               ps.setString(3, direcciones);
               ps.execute();
               
               PreparedStatement ps1= null;
               ResultSet consulta11 = null;
               String sql1="insert datoscliente (nombres_cliente, apellidos_cliente, dpi_cliente, telefono_cliente,"
                       + "direccion_cliente,nit_cliente, estado) values (?,?,?,?,?,?,1)";
               ps1=conn.prepareStatement(sql1);
               String nombres2= nombresc.getText();
               ps1.setString(1, nombres2);
               String apellidos= apellidosc.getText();
               ps1.setString(2, apellidos);
               String dpi= dpic.getText();
               ps1.setString(3, dpi);
               String telefono= telefonoc.getText();
               ps1.setString(4, telefono);
               String direccion= direccionc.getText();
               ps1.setString(5, direccion);
               String nit3= nitc.getText();
               ps1.setString(6, nit3);
               ps1.execute();
               
              PreparedStatement ps2= null;
               ResultSet consulta4 = null;
               String sql2="insert datosfactura (correlativo, articulo, preciounitario, preciototal,"
                       + "fecha,iva, descuento, estado) values (?,?,?,?,?,?,?,1)";
               ps2=conn.prepareStatement(sql2);
               String correlativo= correlativof.getText();
               ps2.setString(1, correlativo);
               String articulo= articulof.getText();
               ps2.setString(2, articulo);
               String preciou= preciouf.getText();
               ps2.setString(3, preciou);
               String preciot= preciotf.getText();
               ps2.setString(4, preciot);
               String fecha = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());
               ps2.setString(5, fecha);
               String iva= ivaf.getText();
               ps2.setString(6, iva);
               String descuento= descuentof.getText();
               ps2.setString(7, descuento);
               ps2.execute();
               
               JOptionPane.showMessageDialog(this, "Los datos han sido almacenados de forma correcta");
               mostrar();
               Limpiar();
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, "Error al almacenar la informacion");
           }
       }
   }
    
       //se crea metodo modificar que sera el encargado de ir a modificar los datos en las 3 tablas de la BD
       //segun sean las modificaciones realizadas en los campos de texto//
       public void Modificar (){
    int filInicio= tbldatos.getSelectedRow();
    int numfila= tbldatos.getSelectedRowCount();
    
    ArrayList<String>listapersona= new ArrayList<>();
    String cod= null;
    String cod2= null;
    String cod3= null;
    
    if(filInicio>=0){
        for(int i=0; i<numfila; i++){
            cod= String.valueOf(tbldatos.getValueAt(i+filInicio,3));
            cod2= String.valueOf(tbldatos.getValueAt(i+filInicio, 0));
            cod3= String.valueOf(tbldatos.getValueAt(i+filInicio, 9));
            listapersona.add(i,cod);
            listapersona.add(i,cod2);
            listapersona.add(i,cod3);
        }
        for (int j=0; j<numfila;j++){
            int resp = JOptionPane.showConfirmDialog(null, "Esta seguro que desea actualizar el registro:  "+listapersona.get(0)+"? ");
            if(resp==0){

                desbloquear();
                
                int filAfectada= 0;
                 try{
                     Conexion basedatos= new Conexion();
                     Connection conn;
                     conn= basedatos.ObtenerConexion();
                    PreparedStatement ps= null;
                     ResultSet consulta = null;
                     
                     ps= conn.prepareStatement("UPDATE datosempresa SET nombre_empresa=?, nit_empresa=?, direccion_empresa=? WHERE nombre_empresa='"+cod2+"'");
                     ps.setString(1, nombree.getText());
                     ps.setString(2, nite.getText());
                     ps.setString(3, direccione.getText());
                    
                     int res= ps.executeUpdate();
                     
                     PreparedStatement ps1= null;
                     ResultSet consulta1 = null;
                     
                     ps1= conn.prepareStatement("UPDATE datoscliente SET nombres_cliente=?, apellidos_cliente=?, "
                             + "dpi_cliente=?, telefono_cliente=?, direccion_cliente=? WHERE nombres_cliente='"+cod+"'");
                     
                     ps1.setString(1, nombresc.getText());
                     ps1.setString(2, apellidosc.getText());
                     ps1.setString(3, dpic.getText());
                     ps1.setString(4, telefonoc.getText());
                     ps1.setString(5, direccionc.getText());
                     int res1= ps1.executeUpdate();
                     
                     PreparedStatement ps3= null;
                     ResultSet consulta3 = null;
                     
                     ps3= conn.prepareStatement("UPDATE datosfactura SET correlativo=?,articulo=?, "
                             + "preciounitario=?, preciototal=?, fecha=?, iva=?, descuento=? WHERE correlativo='"+cod3+"'");
                     
                     ps3.setString(1, correlativof.getText());
                     ps3.setString(2, articulof.getText());
                     ps3.setString(3, preciouf.getText());
                     ps3.setString(4, preciotf.getText());
                     String fecha = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());
                     ps3.setString(5, fecha);
                     ps3.setString(6, ivaf.getText());
                     ps3.setString(7, descuentof.getText());
                     
                     int res3= ps3.executeUpdate();
                     
                     if(res>0&&res1>0&&res3>0){
                         JOptionPane.showMessageDialog(null, "Los datos han sido Actualizados");
                         mostrar();
                         Limpiar();
                     }else{
                         JOptionPane.showMessageDialog(null, "Error al Actualizar los datos");
                     }
                     conn.close();
                }catch(Exception e){
                    System.err.println(e);
                }
                
            }
        }
    }else{
        JOptionPane.showMessageDialog(null, "Porfavor elija un registro para Actualizar");
    }
}
      //se crea el metodo eliminar que se encargara de validar los registros con estado 1 (activo) y se encargara de ir a las
      //tablas e ir a desactivar los registros//
       public void Eliminar (){
    int filInicio= tbldatos.getSelectedRow();
    int numfila= tbldatos.getSelectedRowCount();
    
    ArrayList<String>listapersona= new ArrayList<>();
    String cod= null;
    
    if(filInicio>=0){
        for(int i=0; i<numfila; i++){
            cod= String.valueOf(tbldatos.getValueAt(i+filInicio,0));
            listapersona.add(i,cod);
        }
        for (int j=0; j<numfila;j++){
            int resp = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro:  "+listapersona.get(j)+"? ");
            if(resp==0){
                int filAfectada= 0;
                
                try{
                     Conexion basedatos= new Conexion();
                     Connection conn;
                     conn= basedatos.ObtenerConexion();
                     PreparedStatement ps= null;
                     ResultSet consulta = null;
                     ps= conn.prepareStatement("UPDATE datosempresa SET estado= 0 WHERE Nombre_Empresa='"+cod+"'");
                     
                     int res= ps.executeUpdate();

                     if(res>0){
                         JOptionPane.showMessageDialog(null, "Los datos han sido Eliminados");
                         mostrar();
                     }else{
                         JOptionPane.showMessageDialog(null, "Error al Eliminar los datos");
                     }
                     
                }catch(Exception e){
                    System.err.println(e);
                }
            }
        }
    }else{
        JOptionPane.showMessageDialog(null, "Porfavor elija un registro para eliminar");
    }
}
    // se crea metodo descuento para hacer los calculos que corresponden al descuento dependiendo del 
       //precio total y luego por el boton calcular el sistema mandara a llamar al metodo
    
       public void descuento(){
           String precioti= preciotf.getText();
           Integer preciotf1= Integer.valueOf (precioti);
           if(preciotf1>100&& preciotf1<1300){
                   double proceso=preciotf1*0.05;
                   descuentof.setText(String.valueOf(proceso));
               }else if(preciotf1>1300&&preciotf1<2000){
                   double proceso=preciotf1*0.15;
                   descuentof.setText(String.valueOf(proceso));
               }else if(preciotf1>2000&& preciotf1<10000){
                   double proceso=preciotf1*0.17;
                   descuentof.setText(String.valueOf(proceso));
               }else if(preciotf1>10000){
                   double proceso=preciotf1*0.25;
                   descuentof.setText(String.valueOf(proceso));
               }
       }
       
       //se crea metodo iva para calcular el iva del precio total, y se ejecurara por medio del boton calcular//
       public void iva(){
           String ivai= preciotf.getText();
           Integer ivaf1= Integer.valueOf (ivai);
           if(ivaf1>100&& ivaf1<1000){
                   double proceso1=ivaf1*0.07;
                   ivaf.setText(String.valueOf(proceso1));
               }else if(ivaf1>1000){
                   double proceso1=ivaf1*0.10;
                   ivaf.setText(String.valueOf(proceso1));
               }
                   
               
       }

       //el metodo mostrar se encargara de consultar los datos en las tablas correspondientes, hacer un conteo de columnas
       //por medio de ciclos las ira agregando a la tabla y de igual manera por medio de cilos ira agregando las filas
       //segun se encuentren almacenados datos en las 3 diferentes tablas de la BD//
public void mostrar(){
       Conexion basedatos= new Conexion();
               Connection conn;
               conn= basedatos.ObtenerConexion();
               PreparedStatement ps= null;
               ResultSet consulta = null, consulta1 = null, consulta2=null;

               try{
                   Statement comando = conn.createStatement();
                   Statement comando1 = conn.createStatement();
                   Statement comando2 = conn.createStatement();
                   consulta= comando.executeQuery("select nombre_empresa, nit_empresa, direccion_empresa from datosempresa where estado !=0;");
                   consulta1= comando1.executeQuery("select nombres_cliente, apellidos_cliente, dpi_cliente, telefono_cliente,direccion_cliente,"
                           + "nit_cliente from datoscliente where estado !=0;");
                   consulta2= comando2.executeQuery("select correlativo, articulo, preciounitario, preciototal,fecha,"
                           + "iva, descuento from datosfactura where estado !=0;");
                   DefaultTableModel modelo= new DefaultTableModel();
                   this.tbldatos.setModel(modelo);
                   ResultSetMetaData rmd = consulta.getMetaData();
                   ResultSetMetaData rmd1= consulta1.getMetaData();
                   ResultSetMetaData rmd2= consulta2.getMetaData();
                   
                   int numcol = rmd.getColumnCount();
                   int numcol1= rmd1.getColumnCount();
                   int numcol2= rmd2.getColumnCount();
                   
                   for(int i=0; i<numcol;i++){
                       modelo.addColumn(rmd.getColumnLabel(i+1));
                       }
                   for(int i=0; i<numcol1;i++){
                       modelo.addColumn(rmd1.getColumnLabel(i+1));
                   }
                   for(int i=0; i<numcol2;i++){
                       modelo.addColumn(rmd2.getColumnLabel(i+1));
                   }
                        while(consulta.next()&& consulta1.next()&& consulta2.next()){
                       Object [] fila= new Object[numcol2+15];
                       
                               
                       for(int i=0; i<numcol;i++){
                       fila[i]= consulta.getObject(i+1);
                       
                       for(int a=0; a<numcol1;a++){
                            
                            fila[3+a]= consulta1.getObject(a+1);
                       }
                       for(int e=0; e<numcol2;e++){
                            
                            fila[9+e]= consulta2.getObject(e+1);
                       }
                       }
                       modelo.addRow(fila);
                       
                       }      

                   consulta1.first();
                   
               }catch(Exception e){
                   System.out.println("Error"+e);
               }
               
   }


// el metodo cargar mandara los datos de mi seleccion en la tabla actual hacia los campos de la factura
// para de esta manera poder editarlos a gusto y luego aplicar el metodo de modificar para que los cambios
//se efectuen correctamente//

public void cargar(){
    if(tbldatos.getSelectedRowCount()>0){
        nombree.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 0).toString());
        nite.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 1).toString());
        direccione.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 2).toString());
        nombresc.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 3).toString());
        apellidosc.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 4).toString());
        dpic.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 5).toString());
        telefonoc.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 6).toString());
        direccionc.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 7).toString());
        nitc.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 8).toString());
        correlativof.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 9).toString());
        articulof.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 10).toString());
        preciouf.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 11).toString());
        preciotf.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 12).toString());
        ivaf.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 14).toString());
        descuentof.setText(tbldatos.getValueAt(tbldatos.getSelectedRow(), 15).toString());
        
        btnmostrar.setEnabled(false);
                btnmodificar.setEnabled(false);
                btneliminar.setEnabled(false);
                btnlimpiar.setEnabled(false);
                btnsalir.setEnabled(false);
                btncrear.setEnabled(false);
                btnaceptar.setEnabled(true);
    }
}

// se crea el metodo limpiar sera el encargado de mandar textos vacios a los campos de texto de la factura//
 public void Limpiar(){
        nombree.setText("");
        nite.setText("");
        direccione.setText("");
        nombresc.setText("");
        apellidosc.setText("");
        dpic.setText("");
        telefonoc.setText("");
        direccionc.setText("");
        nitc.setText("");
        correlativof.setText("");
        articulof.setText("");
        preciouf.setText("");
        preciotf.setText("");
        ivaf.setText("");
        descuentof.setText("");
        jDateChooser1.setDateFormatString("");
    }

 //se crea el metodo desbloquear para media vez se confirme que se han modificado con exito los registros de las bases de datos
 //los botones adicionales se liberaran//
    void desbloquear(){
    btncrear.setEnabled(true);
    btnmodificar.setEnabled(true);
    btneliminar.setEnabled(true);
    btnaceptar.setEnabled(true);
    btnmostrar.setEnabled(true);
    btnlimpiar.setEnabled(true);
    btnsalir.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombree = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nite = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        direccione = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombresc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        apellidosc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dpic = new javax.swing.JTextField();
        telefonoc = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        direccionc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        nitc = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        correlativof = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        articulof = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        preciouf = new javax.swing.JTextField();
        Label10 = new javax.swing.JLabel();
        preciotf = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ivaf = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        descuentof = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldatos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btncrear = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btnaceptar = new javax.swing.JButton();
        btnmostrar = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Footlight MT Light", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setText("Sistema de Facturación");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Datos de empresa");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("NIT:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Dirección:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombree, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(nite, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(direccione, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(nite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(direccione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Datos del Cliente");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Nombres:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Apellidos:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("DPI:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Telefono: ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Dirección:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("NIT:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(direccionc))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dpic)
                                    .addComponent(nombresc))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(apellidosc, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(telefonoc)
                                    .addComponent(nitc))))
                        .addGap(29, 29, 29))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nombresc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(apellidosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(direccionc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(nitc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Datos de la Factura");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Correlativo:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Articulo:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Precio Unitario:");

        Label10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Label10.setText("Precio Total:");

        preciotf.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                preciotfInputMethodTextChanged(evt);
            }
        });
        preciotf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preciotfActionPerformed(evt);
            }
        });
        preciotf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                preciotfKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                preciotfKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Fecha:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("IVA:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Descuento:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(correlativof)
                                .addComponent(articulof, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(preciouf, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(44, 44, 44)
                                .addComponent(ivaf)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(Label10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(preciotf))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descuentof)))))
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(correlativof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(articulof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(preciouf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label10)
                    .addComponent(preciotf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(ivaf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(descuentof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbldatos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbldatos);

        btncrear.setText("Crear");
        btncrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrearActionPerformed(evt);
            }
        });

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnmodificar.setText("Modificar");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btnaceptar.setText("Aceptar");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        btnmostrar.setText("Mostrar");
        btnmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmostrarActionPerformed(evt);
            }
        });

        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btncrear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btneliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnmodificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnaceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnmostrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnlimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsalir)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncrear)
                    .addComponent(btneliminar)
                    .addComponent(btnmodificar)
                    .addComponent(btnaceptar)
                    .addComponent(btnmostrar)
                    .addComponent(btnlimpiar)
                    .addComponent(btnsalir))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jButton1.setText("Calcular");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 49, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(171, 171, 171))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(jButton1)
                .addGap(4, 4, 4)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearActionPerformed
        guardar();
    }//GEN-LAST:event_btncrearActionPerformed

    private void btnmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmostrarActionPerformed
        mostrar();
    }//GEN-LAST:event_btnmostrarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        cargar();
        
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        Modificar();
    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
       Limpiar();
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void preciotfInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_preciotfInputMethodTextChanged
       
    }//GEN-LAST:event_preciotfInputMethodTextChanged

    private void preciotfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preciotfActionPerformed
        
    }//GEN-LAST:event_preciotfActionPerformed

    private void preciotfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preciotfKeyPressed
        
    }//GEN-LAST:event_preciotfKeyPressed

    private void preciotfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preciotfKeyReleased
        
    }//GEN-LAST:event_preciotfKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        descuento();
        iva();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnsalirActionPerformed

   
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
            java.util.logging.Logger.getLogger(JDFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDFactura dialog = new JDFactura(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label10;
    private javax.swing.JTextField apellidosc;
    private javax.swing.JTextField articulof;
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncrear;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnmostrar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JTextField correlativof;
    private javax.swing.JTextField descuentof;
    private javax.swing.JTextField direccionc;
    private javax.swing.JTextField direccione;
    private javax.swing.JTextField dpic;
    private javax.swing.JTextField ivaf;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nitc;
    private javax.swing.JTextField nite;
    private javax.swing.JTextField nombree;
    private javax.swing.JTextField nombresc;
    private javax.swing.JTextField preciotf;
    private javax.swing.JTextField preciouf;
    private javax.swing.JTable tbldatos;
    private javax.swing.JTextField telefonoc;
    // End of variables declaration//GEN-END:variables
}
