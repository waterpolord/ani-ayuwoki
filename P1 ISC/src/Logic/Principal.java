package Logic;
import java.io.*;
import java.util.ArrayList;

import Visual.Inicio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Principal implements Serializable{

	private ArrayList<Persona> Tpersonas;
	private ArrayList<Empresa> TEmpresas;
	private ArrayList<Empleo> TEmpleos;
	private ArrayList<Vacante> TVacantes;
	private int cantPersonas,cantEmpresas;
	private static Principal principal;
	

	public static void main(String args[]) throws ClassNotFoundException, SQLException {
           // DividirSTR("Robert Felix",1);
            new Inicio().setVisible(true); 
	}
	
	public Principal() {
		Tpersonas = new ArrayList();
		TEmpresas = new ArrayList();
		TEmpleos = new ArrayList();
		TVacantes = new ArrayList();
		cantEmpresas = 0;
		cantPersonas = 0;
	}
	
	public static Principal getInstance() {
		if(principal == null) {
			principal = new Principal();
		}
		return principal;
	}
	
	public static void setInstance(Principal carga) {
			principal = carga;
		
	}
	public void dataSalida() throws IOException, ClassNotFoundException{
                File salida = new File("Bolsa.dat");
		FileOutputStream guardar;
		guardar = new FileOutputStream(salida);
		ObjectOutputStream archivoSalida = new ObjectOutputStream(guardar);
		archivoSalida.writeObject(Principal.getInstance());
		archivoSalida.close();
    }
	
	public void dataEntrada() throws IOException, FileNotFoundException, ClassNotFoundException{
                File archivoEntrada = new File("Bolsa.dat");
		if(archivoEntrada.exists()) {
			FileInputStream file = new FileInputStream(archivoEntrada);
			ObjectInputStream entrada = new ObjectInputStream(file);
			Principal.setInstance((Principal) entrada.readObject());
			Tpersonas = Principal.getInstance().Tpersonas;
			TEmpresas = Principal.getInstance().TEmpresas;
			TEmpleos = Principal.getInstance().TEmpleos;
			TVacantes = Principal.getInstance().TVacantes;
			cantEmpresas = TEmpresas.size();
			cantPersonas = Tpersonas.size();
			entrada.close();
		}	
    	
    }
        public void Obtener() throws ClassNotFoundException, IOException{
            ResultSet cn,cn2;
            int cod_persona = 1;
            String Nombre = null,TipoEmpresa = null;
            // Agregando personas a singleton
            try {
                cn = Conexion.Connect.Consulta("SELECT * FROM Persona");
                while(cn.next()){
                    Persona aux = new Persona(cn.getString(1), cn.getString(2), cn.getString(3), cn.getString(4), cn.getDate(5),cn.getString(6),cn.getString(7), 
                            cn.getBoolean(8),cn.getString(9)) {
                        @Override
                        public void RetornarPersonas() throws Exception {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                    Tpersonas.add(aux);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"No se Pudieron obtener las personas desde la base de datos","Advertencia", 0);
            }
            try {
                cn = Conexion.Connect.Consulta("SELECT * FROM Persona");
                while(cn.next()){
                    Persona aux = new Persona(cn.getString(1), cn.getString(2), cn.getString(3), cn.getString(4), cn.getDate(5),cn.getString(6),cn.getString(7), 
                            cn.getBoolean(8),cn.getString(9)) {
                        @Override
                        public void RetornarPersonas() throws Exception {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                    Tpersonas.add(aux);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"No se Pudieron obtener las personas desde la base de datos","Advertencia", 0);
            }
            // Agregando empresas a singleton
            try {
                cn = Conexion.Connect.Consulta("SELECT * FROM Empresa");
                while(cn.next()){
                    
                    cn2 = Conexion.Connect.Consulta("SELECT primer_nombre FROM Persona INNER JOIN Empresa ON Empresa.cod_persona = '"
                            +cn.getInt(6)+"'");
                    while(cn2.next()){
                         Nombre = cn2.getString(1);
                    }
                    
                    cn2 = Conexion.Connect.Consulta("SELECT Nombre FROM Tipo_empresa INNER JOIN Empresa ON Empresa.cod_tipo_empresa = '"
                            +cn.getInt(7)+"'");
                    while(cn2.next()){
                         TipoEmpresa = cn2.getString(1);
                    }
                    
                    
                    Empresa aux = new Empresa(cn.getString(1), cn.getString(2), cn.getString(3), cn.getString(4),Nombre,TipoEmpresa) {
                        @Override
                        public void RetornarEmpresa() throws Exception {
                            
                        }
                    };
                     
                    aux.setMisVacantes(getVacantesEmpresa(aux.getNombre()));
                    for(Vacante vaca:aux.getMisVacantes()){
                        TVacantes.add(vaca);
                        }
                    TEmpresas.add(aux);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"No se Pudieron obtener las empresas desde la base de datos","Advertencia", 0);
            }
            
            
           
            
            
        }
        
        public ArrayList<Vacante> getVacantesEmpresa(String Empresa) throws ClassNotFoundException, SQLException, IOException{
            ArrayList<Vacante> aux = new ArrayList();
            ArrayList<Persona> perso = new ArrayList();
            Vacante vaca;
            ResultSet cn,cn2;
            Boolean[] bol = new Boolean[10];
            int i = 0;
            
            cn = Conexion.Connect.Consulta("SELECT cod_vacante_empresa,puesto_vacante,tipo_personal_vacante,[¿Habla otro Idioma?],[¿Vehiculo Propio?]"
                    + ",[¿Disponibilidad de Horario?],[¿Disposicion de Viaje?],[¿Dispuesto a Mudarse?],[¿Piensa ampliar sus conocimientos?]"
                    + ",[¿Trabajaría los fines de semana?],[¿Posee Experiencia de trabajos anteriores?],[¿Puede realizar más de una tarea a la vez?]"
                    + ",[¿Trabajas bien en equipo?],estado_vacante,cantidad_actual_puesto_vacante,monto,cantidad_inicia_puesto_vacante,codigo_vacante_reconocimiento"
                    + " FROM Vacante_Empresa INNER JOIN Empresa ON Empresa.cod_empresa = Vacante_Empresa.cod_empresa WHERE Empresa.nombre_empresa = '"+
                    Empresa+"'");
            
            while(cn.next()){
                for (int j = 0; j < 10; j++) {
                    bol[j] = cn.getBoolean(j+4);
                    
                }
                
                vaca = new Vacante(Principal.getInstance().buscarEmpresas(Empresa),cn.getString(2),cn.getString(3), bol,cn.getBoolean(14)
                        ,cn.getInt(15),cn.getInt(16),
                        cn.getInt(17),  cn.getInt(18));
                cn2 = Conexion.Connect.Consulta("SELECT primer_nombre FROM Persona INNER JOIN Persona_Vacante"
                        + "ON Persona.cod_Persona = Persona_Vacante.cod_Persona INNER JOIN Persona_Vacante ON Persona_Vacante.cod_vacante_empresa ='"+
                    cn.getInt(1)+"'");
                while(cn2.next()){
                    perso.add(Principal.getInstance().buscarPersonas(cn2.getString(1)));
                }
                vaca.setSolicitantes(perso);
                aux.add(vaca);
            }
            
            
            return aux;
        }

	public int getCantPerson() {
		return cantPersonas;
	}
	
	public int getEmpresas() {
		return cantEmpresas;
	}
	
	public ArrayList<Persona> getTpersonas() {
		return Tpersonas;
	}
	public void setTpersonas(Persona nueva) throws ClassNotFoundException, IOException {
		Tpersonas.add(nueva);
		cantPersonas++;
		dataSalida();
	}
	public ArrayList<Empresa> getTEmpresas() {
		return TEmpresas;
	}
	public void setTEmpresas(Empresa empresa) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		TEmpresas.add(empresa);
		cantEmpresas++;
		dataSalida();
	}
	public ArrayList<Empleo> getTEmpleos() {
		return TEmpleos;
	}
	public void setTEmpleos(Empleo nuevo) throws FileNotFoundException, ClassNotFoundException, IOException {
		TEmpleos.add(nuevo);
		dataSalida();
		
	}
	public ArrayList<Vacante> getTVacantes() {
		return TVacantes;
	}
	public void setTVacantes(Vacante vacante) throws FileNotFoundException, ClassNotFoundException, IOException {
		TVacantes.add(vacante);
		dataSalida();
		
	}
	
	public Boolean existeEmpresa(String txt) throws FileNotFoundException, ClassNotFoundException, IOException {
		dataEntrada();
		for(Empresa empre:TEmpresas) {
			if(empre.getCorreo().equalsIgnoreCase(txt)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean existeUser(String txt) throws FileNotFoundException, ClassNotFoundException, IOException {
		dataEntrada();
		for(Persona aux:Tpersonas) {
			if(aux.getCorreo().equalsIgnoreCase(txt) || aux.getNombre().equalsIgnoreCase(txt)) {
				return true;
			}
		}
		return false;
	}
	
	public Persona buscarPersonas(String txt) throws FileNotFoundException, ClassNotFoundException, IOException {
		//dataEntrada();
		for(Persona aux:Tpersonas) {
			if(aux.getCorreo().equalsIgnoreCase(txt) || aux.getNombre().equalsIgnoreCase(txt)) {
				return aux;
			}
		}
		return null;
	}
	public Empresa buscarEmpresas(String txt) throws FileNotFoundException, ClassNotFoundException, IOException {
		//dataEntrada();
		for(Empresa aux:TEmpresas) {
			if(aux.getCorreo().equalsIgnoreCase(txt) || aux.getNombre().equalsIgnoreCase(txt)) {
				return aux;
			}
		}
		return null;
	}
	//  ind = 0 Persona ind = 1 Empresa
	public Boolean ContraUser(String txt) throws FileNotFoundException, ClassNotFoundException, IOException {
		dataEntrada();
			for(Persona aux:Tpersonas) {
				if(aux.getClave().equalsIgnoreCase(txt))
					return true;
			}
			return false;
	}
	public Boolean ContraEmpresa(String txt) throws FileNotFoundException, ClassNotFoundException, IOException {
		dataEntrada();
		for(Empresa empre:TEmpresas) {
			if(empre.getClave().equalsIgnoreCase(txt))
				return true;
			}
		return false;
	}
	
	public ArrayList<String> RetornarObrerosDisponibles() {
		ArrayList<String> lista = new ArrayList<String>();
		for(Persona aux:Tpersonas) {
			if(aux instanceof Obrero && aux.getEstado() == true) {
				lista.add(aux.getNombre());
			}
		}
		return lista;
	}
	
	public ArrayList<String> RetornarUniversitariosDisponibles() {
		ArrayList<String> lista = new ArrayList<String>();
		for(Persona aux:Tpersonas) {
			if(aux instanceof Universitario && aux.getEstado() == true) {
				lista.add(aux.getNombre());
			}
		}
		return lista;
	}
	
	public ArrayList<String> RetornarTecnicosDisponibles() {
		ArrayList<String> lista = new ArrayList<String>();
		for(Persona aux:Tpersonas) {
			if(aux instanceof Tecnico && aux.getEstado() == true) {
				lista.add(aux.getNombre());
			}
		}
		return lista;
	}
	
	
	
	public Empresa SesionEmpresa() throws FileNotFoundException, ClassNotFoundException, IOException {
		dataEntrada();
		for(Empresa aux:TEmpresas) {
			if(aux.getSesion()) {
				return aux;
			}
		}
		return null;
	}
	
	public Persona SesionUser() throws FileNotFoundException, ClassNotFoundException, IOException {
		dataEntrada();
		for(Persona aux:Tpersonas) {
			if(aux.getSesion()) {
				return aux;
			}
		}
		return null;
	}
	
	public ArrayList<String> RetornarNombres(String txt, int ind) {
		ArrayList<String> lista = new ArrayList<String>();
		Character l1 = null,l2 = null;
		int i = 0,num = 0;
		if(ind == 0) {
			for(Persona aux:Tpersonas) {
				for(int j=0;j<txt.length();j++) {
					
					if(l1.toUpperCase(aux.getNombre().charAt(i)) == l2.toUpperCase(txt.charAt(j)))  {
						num++;
						if(num == txt.length()) {
							lista.add(aux.getNombre());
							num = 0;
						}
					}
					i++;
					
					if(j == txt.length()-1) {
						i = 0;
						num = 0;
					}
				}
			}
		}
		if(ind == 1) {
			for(Persona aux:Tpersonas) {
				if(aux instanceof Universitario) {
					for(int j=0;j<txt.length();j++) {
						
						if(l1.toUpperCase(aux.getNombre().charAt(i)) == l2.toUpperCase(txt.charAt(j)))  {
							num++;
							if(num == txt.length()) {
								lista.add(aux.getNombre());
								num = 0;
							}
						}
						i++;
						
						if(j == txt.length()-1) {
							i = 0;
							num = 0;
						}
					}
				}
			}
		}
		if(ind == 2) {
			for(Persona aux:Tpersonas) {
				if(aux instanceof Tecnico) {
					for(int j=0;j<txt.length();j++) {
						
						if(l1.toUpperCase(aux.getNombre().charAt(i)) == l2.toUpperCase(txt.charAt(j)))  {
							num++;
							if(num == txt.length()) {
								lista.add(aux.getNombre());
								num = 0;
							}
						}
						i++;
						
						if(j == txt.length()-1) {
							i = 0;
							num = 0;
						}
					}
				}
			}
		}
		if(ind == 3) {
			for(Persona aux:Tpersonas) {
				if(aux instanceof Obrero) {
					for(int j=0;j<txt.length();j++) {
						
						if(l1.toUpperCase(aux.getNombre().charAt(i)) == l2.toUpperCase(txt.charAt(j)))  {
							num++;
							if(num == txt.length()) {
								lista.add(aux.getNombre());
								num = 0;
							}
						}
						i++;
						
						if(j == txt.length()-1) {
							i = 0;
							num = 0;
						}
					}
				}
			}
		}
		
		return lista;
	}
	
	public int getPersonasDisponibles() {
		int cant = 0;
		for(Persona aux:Tpersonas) {
			if(aux.getEstado()) {
				cant++;
			}
		}
		return cant;
	}
        
        public static String DividirSTR(String cadena, int tipo){
            String aux = null;
            char letra;
            if(tipo == 1){
                for (int i = 0; i < cadena.length() || cadena.charAt(i) == ' '; i++) {

                    if( cadena.charAt(i) == ' ' ){
                        aux = cadena.substring(i,cadena.length());

                    }

                }
            }
            else{
                
            }
            
            return aux;
        }

}


