package Modelo;

import Controlador.PersistanceController;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public ClienteDAO() {

    }
    
    /*
    public Cliente buscar(String dni) {
        Cliente cli = new Cliente();
        String sql = "Select * from cliente where Dni=" + dni;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cli.setId(rs.getInt(1));
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setTel(rs.getString(4));
                cli.setDir(rs.getString(5));
                cli.setEstado(rs.getString(6));
            }
        } catch (Exception c) {
        }
        return cli;
    }

    ************ Operaciones CRUD ***************
    public List listar() {
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setTel(rs.getString(4));
                cl.setDir(rs.getString(5));
                cl.setEstado(rs.getString(6));
                lista.add(cl);
            }
        } catch (Exception c) {
        }
        return lista;
    }

    public int agregar(Cliente cl) {
        String sql = "insert into cliente(Dni, Nombres, Telefono, Direccion, Estado)values(?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getTel());
            ps.setString(4, cl.getDir());
            ps.setString(5, cl.getEstado());
            ps.executeUpdate();
        } catch (Exception c) {
        }
        return r;
    }

    public Cliente listarId(int id) {
        Cliente cli = new Cliente();
        String sql = "select * from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //cli.setId(rs.getInt(1));
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setTel(rs.getString(4));
                cli.setDir(rs.getString(5));
                cli.setEstado(rs.getString(6));
            }
        } catch (Exception c) {
        }
        return cli;
    }

    public int actualizar(Cliente cl) {
        String sql = "update cliente set Dni=?, Nombres=?, Telefono=?, Direccion=?, Estado=? where IdCliente=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getTel());
            ps.setString(4, cl.getDir());
            ps.setString(5, cl.getEstado());
            ps.setInt(6, cl.getId());
            ps.executeUpdate();
        } catch (Exception c) {
            System.out.println("ERROR AL ACTUALIZAR CLIENTE:");
            c.printStackTrace();
        }
        return r;
    }

    public void delete(int id) {
        String sql = "delete from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception c) {
        }
    }
    */

    //Listar Clientes
    public List<Cliente> getListaClientes() {
        return PersistanceController.buscarPorClase(Cliente.class);
    }

    //Guardar Cliente 
    public boolean guardarCliente(Cliente cliente) {
        return PersistanceController.guardar(cliente);
    }
    
    //Editar Cliente
    public Cliente getEditarCliente(int idCliente) {
        return (Cliente) PersistanceController.buscarPorId(Cliente.class, idCliente);
    }

    //Actualizar Cliente
     public void actualizarEdicionDelCliente(Cliente cliente) { 
        PersistanceController.actualizar(cliente);
    }
    
    /*public boolean actualizarCliente(Cliente cliente) { //Actualización mas directa, pero da error
        PersistanceController.actualizar(cliente);
        return true;
    }*/

    //Eliminar Cliente
     public Object eliminarClientePorId(int idCliente) {
        return PersistanceController.eliminarPorId(idCliente, Cliente.class);
    }
    
    /*public void eliminarClientePorId(int idCliente) { //Eliminar Cliente sin retorno
        PersistanceController.eliminarPorId(idCliente, Cliente.class);
    }*/

}
