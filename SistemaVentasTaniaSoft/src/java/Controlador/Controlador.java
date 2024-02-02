
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Cliente cl = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto pr = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    int ide;
    int idc;
    int idp;

    Venta ve = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;       
    int cod;        //El codigo de la venta es el id de la venta (IdVentas en la base de datos). 
    String descripcion;
    int precio;
    int cant;        
    int subtotal;
    int totalPagar;

    String numeroserie = ""; 
    VentaDAO vdao = new VentaDAO();
    
    public Controlador(){
        PersistanceController.makeEntitiMF();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        
        //MENU EMPLEADOS
        if (menu.equals("Empleado")) {
            switch (accion) {

                case "Listar":
                    //List lista = edao.listar(); 
                    List lista = edao.getListaEmpleados(); 
                    
                    request.setAttribute("empleados", lista); //Aquí se esta definiendo empleados como un atributo del arrays lista. 
                    break;

                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUser");
                    
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    
                    //edao.agregar(em);
                    edao.guardarEmpleado(em); 

                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    
                    //Empleado e = edao.listarId(ide); 
                    Empleado e = edao.getEditarEmpleado(ide); 
                    
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                case "Actualizar": 
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUser");

                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    
                    System.out.println("EMPLEADO ACTUALIZADO: "+ em);
                    
                //edao.actualizar(em);
                edao.actualizarEdicionDelEmpleado(em);

                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;


                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    
                    //edao.delete(ide);
                    edao.eliminarEmpleadoPorId(ide);
                    
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        
        //MENU CLIENTES
        if (menu.equals("Cliente")) {
            switch (accion) {

                case "Listar":
                    List lista = cdao.getListaClientes();//cdao.listar();
                    
                    request.setAttribute("clientes", lista); //Aquí se esta definiendo clientes como un atributo del arrays lista. 
                    break;

                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String dir = request.getParameter("txtDir");
                    String est = request.getParameter("txtEstado");
                    
                    cl.setDni(dni);
                    cl.setNom(nom);
                    cl.setTel(tel);
                    cl.setDir(dir);
                    cl.setEstado(est);
                    //cdao.agregar(cl);
                    cdao.guardarCliente(cl);

                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break; 

                case "Editar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    
                    Cliente c = cdao.getEditarCliente(idc);//cdao.listarId(idc);
                    
                    System.out.println("EL CLIENTE ES: "+c);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "Actualizar": //Este falta por codificar a JPA
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String dir1 = request.getParameter("txtDir");
                    String est1 = request.getParameter("txtEstado");

                    cl.setDni(dni1);
                    cl.setNom(nom1);
                    cl.setTel(tel1);
                    cl.setDir(dir1);
                    cl.setEstado(est1);
                    cl.setId(idc);
                    
                    System.out.println("CLIENTE ACTUALIAR: "+ cl);
                    
                    //cdao.actualizar(cl);
                    cdao.actualizarEdicionDelCliente(cl);

                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "Delete": 
                    idc = Integer.parseInt(request.getParameter("id"));
                    //cdao.delete(idc);
                    cdao.eliminarClientePorId(idc);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }

        //MENU PRODUCTOS
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    //List lista = pdao.listar();
                    List lista = pdao.getListaProductos();
                    
                    request.setAttribute("productos", lista); //Aquí se esta definiendo productos como un atributo del arrays lista.
                    break;

                case "Agregar":
                    String nom = request.getParameter("txtNombres");
                    Integer pre = Integer.parseInt(request.getParameter("txtPrecio"));
                    Integer sto = Integer.parseInt(request.getParameter("txtStock"));
                    String des = request.getParameter("txtDescripcion");
                    String est = request.getParameter("txtEstado");
                    
                    pr.setNom(nom);
                    pr.setPrecio(pre);
                    pr.setStock(sto);
                    pr.setDescripcion(des);
                    pr.setEstado(est);
                    
                    //pdao.agregar(pr);
                    pdao.guardarProducto(pr);

                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    
                    //Producto p = pdao.listarId(idp);
                    Producto p = pdao.getProductoPorId(idp);
                    
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Actualizar": 
                    String nom1 = request.getParameter("txtNombres");
                    String pre1 = request.getParameter("txtPrecio");
                    String sto1 = request.getParameter("txtStock");
                    String des1 = request.getParameter("txtDescripcion");
                    String est1 = request.getParameter("txtEstado");

                    Integer precio1 = Integer.parseInt(pre1);
                    Integer stock1 = Integer.parseInt(sto1);

                    pr.setNom(nom1);
                    pr.setPrecio(precio1);
                    pr.setStock(stock1);
                    pr.setDescripcion(des1);
                    pr.setEstado(est1);
                    pr.setId(idp);
                    pdao.actualizar(pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Delete": 
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(idp);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }

        //MENU NUEVA VENTA
        if (menu.equals("NuevaVenta")) { //Este falta por codificar a JPA

            ve = new Venta();
            //numeroserie
            request.setAttribute("nserie", numeroserie);

            switch (accion) {
                //Este es el boton buscar, para buscar cliente en nueva venta. 
                case "BuscarCliente":
                    String dni = request.getParameter("DNIcliente");

                    cl.setDni(dni);
                    //cl = cdao.buscar(dni);

                    request.setAttribute("cl", cl);
                    request.setAttribute("nserie", numeroserie);
                    break;

                //Este es el boton buscar, para buscar producto en nueva venta.  
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("IDproducto"));
                    pr = pdao.listarId(id);

                    request.setAttribute("cl", cl);
                    request.setAttribute("producto", pr);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    //System.out.println("PRID: "+pr.getId()+"___");
                    break;

                case "AgregarProducto":
                    request.setAttribute("nserie", numeroserie);
                    request.setAttribute("cl", cl);

                    totalPagar = 0;
                    item = item + 1;
                    cod = pr.getId(); //HAY UN ERROR AQUI, YA QUE NO ESTA MANDANDO EL ID DEL PRODUCTO SINO UN 0. 
                    descripcion = request.getParameter("nomproducto");
                    precio = Integer.parseInt(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;

                    ve = new Venta();

                    ve.setItem(item);
                    ve.setIdproducto(cod);
                    ve.setDescripcionP(descripcion);
                    ve.setPrecio(precio);
                    ve.setCantidad(cant);
                    ve.setSubtotal(subtotal);

                    lista.add(ve);

                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }

                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);
                    break;

                case "eliminarproducto":
                    request.setAttribute("nserie", numeroserie);
                    request.setAttribute("cl", cl);
                    cod = pr.getId();
                    System.out.println("LLEGO AL ELIMINAR PRODUCOTO");
                    
                    totalPagar=0;
                    int idprod=Integer.parseInt(request.getParameter("id"));
                    System.out.println("EL ID DEL PRODUCTO: "+idprod);
                    
                    for (int i = 0; i < lista.size(); i++) {
                        
                        if(idprod==lista.get(i).getIdproducto()){
                            System.out.println("ENTRO EN LA ELIMINACION");
                            
                            System.out.println("ELEMENTO ELIMINADO: "+lista.remove(i));                
                        }else{
                            totalPagar = totalPagar + lista.get(i).getSubtotal();    
                        }
                        
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);

                    break;

                case "GenerarVenta":
                    //request.setAttribute("nserie", numeroserie);
                    System.out.println("NUMERO DE SERIE: " + numeroserie);

                    for (int i = 0; i < lista.size(); i++) {
                        Producto p = new Producto();

                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();

                        ProductoDAO aO = new ProductoDAO();
                        p = aO.buscar(idproducto);

                        int stockActual = p.getStock() - cantidad;

                        aO.actualizarstock(idproducto, stockActual);
                    }

                    //Guardar Venta
                    ve.setIdcliente((int)cl.getId());
                    ve.setIdempleado(2);
                    ve.setNumserie(numeroserie);
                    ve.setFecha("2019-06-14");
                    System.out.println("EL TOTAL A pAGAR Es: " + totalPagar); 
                    ve.setMonto(totalPagar);
                    ve.setEstado("1");

                    int resp = vdao.guardarVenta(ve);
                    System.out.println("EL RESULTADO: " + resp + "___");

                    //Guardar detalle ventas
                    System.out.println("ID DETALLE VENTA: " + vdao.IdVentas());

                    int idv = Integer.parseInt(vdao.IdVentas());

                    for (int i = 0; i < lista.size(); i++) {
                        ve = new Venta();

                        ve.setId(idv);
                        ve.setIdproducto(lista.get(i).getIdproducto());
                        ve.setCantidad(lista.get(i).getCantidad());
                        ve.setPrecio(lista.get(i).getPrecio());

                        vdao.GuardarDetalleVentas(ve);
                    }

                    numeroserie = vdao.getConsecutivoFactura(); //La variable "numeroserie" almacena el maximo numero de serie que existe en la base datos. 
                    request.setAttribute("nserie", numeroserie);
                    
                    lista.clear();
                    
                    break;

                case "Cancelar":
                    lista.clear();
                    totalPagar = 0;
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    break;

                default: //Hay un error en el numero de serie que esta provocando que no se incremente al generar una venta. (Posiblemente tambien lo este afectando el contenido de la clase GenerarSerie)
                    ve = new Venta();
                    lista = new ArrayList<>();
                    item = 0;
                    totalPagar = 0;

                    numeroserie = vdao.getConsecutivoFactura(); //La variable "numeroserie" almacena el maximo numero de serie que existe en la base datos. 
                    request.setAttribute("nserie", numeroserie);

                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
