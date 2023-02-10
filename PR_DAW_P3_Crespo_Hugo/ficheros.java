import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Hugo Crespo
 */

public class ficheros {
    //Declaramos los botones que vamos a usar
    static String [] opciones = {"Crear","Leer","Escribir","Salir" };
    /**
     * @param args 
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        int op ; 
        boolean bucle =true ;
        while (bucle) {
        op = JOptionPane.showOptionDialog(null,"Elige operacion", "Gestión de ficheros", JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, opciones,opciones[0]);//se crea el panel para la seleccion de opciones
            //cada una de las opciones "crear fichero, leer escribir y salir"
        switch (op){
        case 0:
        crearFichero();
                break;
        case 1:
        leerFichero();
                break;
        case 2:
        escribirFichero();
                break;
        case 3:
        bucle=false;
        default:
            break;
            }   
        }
    }   
    //metodo nuevo fichero
    private static void crearFichero() {
    
        String ruta = null;
        String fichero= null;

        JFileChooser jFC = new JFileChooser();
        jFC.setDialogTitle("Crear fichero"); //nombre de la ventana
        int retorno = jFC.showOpenDialog(jFC); //muestra la ventana
        if (retorno == JFileChooser.APPROVE_OPTION);    
            ruta = jFC.getSelectedFile().getParent();
            File archivo =jFC.getSelectedFile();
            fichero = jFC.getName(archivo);
            System.out.println(ruta);
            System.out.println(fichero);

            File ficheroNuevo = new File(ruta,fichero);
            try{
                ficheroNuevo.createNewFile();
            } catch (IOException e) {
                System.out.println("error");
            }
    } 
    /**
     * 
     */
    private static void leerFichero() {
        String ruta = null ;
        String fichero= null;

        JFileChooser jFC = new JFileChooser();
        jFC.setDialogTitle("leer fichero");
        int retorno = jFC.showOpenDialog(jFC);
        
        if ( retorno == JFileChooser.APPROVE_OPTION) {
            
            ruta = jFC.getSelectedFile().getParent();
            File archivo = jFC.getSelectedFile();
            fichero = jFC.getName(archivo);
            System.out.println(ruta);
            System.out.println(fichero);
            
            
            try {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String datos = "";
                String linea = br.readLine();
                while (linea!=null) {                   
                    datos += linea +System.lineSeparator();
                    linea = br.readLine();
                }
                             
                JTextArea textArea = new JTextArea(datos);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                JScrollPane scroll = new JScrollPane(textArea);
                scroll.setPreferredSize(new Dimension(500,400));
                JOptionPane.showMessageDialog(null, scroll);

                
                }catch (IOException e) {
                    System.out.println("error");
                }  
            }  
        }
    
    private static void escribirFichero() throws FileNotFoundException {
        String ruta = null ;
        String fichero= null;

        JFileChooser jFC = new JFileChooser();
        jFC.setDialogTitle("escribir fichero");

        int retorno = jFC.showOpenDialog(jFC);
        
        if ( retorno == JFileChooser.APPROVE_OPTION) {
        
            ruta = jFC.getSelectedFile().getParent();
            File archivo = jFC.getSelectedFile();
            fichero = jFC.getName(archivo);
            System.out.println(ruta);
            System.out.println(fichero);
            
            try {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String datos = "";
                String linea = br.readLine();
                while (linea!=null) {                    
                    datos += linea +System.lineSeparator();
                    linea = br.readLine();
                }
                             
                JTextArea textArea = new JTextArea(datos);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(true);
                JScrollPane scroll = new JScrollPane(textArea);
                scroll.setPreferredSize(new Dimension(500,400));
                
                JOptionPane.showMessageDialog(null, scroll);
                                
                FileWriter fw = new FileWriter(archivo);
                
                BufferedWriter guardar = new BufferedWriter(fw);
                
                guardar.write(textArea.getText());
                
                guardar.flush();
                    
                
                }catch (IOException e) {
                    System.out.println("error");
                }
        }
    }
}
