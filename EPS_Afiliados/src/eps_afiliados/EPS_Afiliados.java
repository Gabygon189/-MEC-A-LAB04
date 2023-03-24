import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

public class EPS_Afiliados extends JFrame implements ActionListener {
    private JTextField tfNombre, tfEdad, tfAfiliacion, tfCondicion;
    private JTextArea taTurnos;
    private JButton btnNuevoTurno, btnExtenderTiempo;
    private JLabel lblTurno, lblTiempoRestante, lblTurnosPendientes;
    private Queue<Paciente> colaTurnos;
    private Paciente pacienteEnCurso;
    private int tiempoRestante;

   public EPS_Afiliados() {
 
        setTitle("EPS");
        setSize(900, 600);
        JPanel panel1 = new JPanel();
        panel1.setSize(900, 600);
        panel1.setBackground(Color.RED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
       

        tfNombre = new JTextField();
        tfNombre.setFont(new Font("Verdana", Font.PLAIN, 60));
        tfNombre.setForeground(Color.GREEN);
        tfEdad = new JTextField();
        tfEdad.setFont(new Font("Verdana", Font.PLAIN, 60));
        tfEdad.setForeground(Color.GREEN);
        tfAfiliacion = new JTextField();
        tfAfiliacion.setFont(new Font("Verdana", Font.PLAIN, 60));
        tfAfiliacion.setForeground(Color.GREEN);
        tfCondicion = new JTextField();
        tfCondicion.setFont(new Font("Verdana", Font.PLAIN, 60));
        tfCondicion.setForeground(Color.GREEN);
        
        taTurnos = new JTextArea();
        taTurnos.setFont(new Font("Verdana", Font.PLAIN, 60));
        taTurnos.setForeground(Color.BLUE);
        JScrollPane scrollPane = new JScrollPane(taTurnos);
        btnNuevoTurno = new JButton("Tienes un nuevo turno");
        btnNuevoTurno.setFont(new Font("Verdana", Font.BOLD, 30));
        btnNuevoTurno.setForeground(Color.WHITE);
        btnNuevoTurno.setBackground(Color.BLUE);
        btnExtenderTiempo = new JButton("Extender tu tiempo");
        btnExtenderTiempo.setFont(new Font("Verdana", Font.BOLD, 30));
        btnExtenderTiempo.setForeground(Color.WHITE);
        btnExtenderTiempo.setBackground(Color.BLUE);
        lblTurno = new JLabel("Tu turno está curso:");
        lblTurno.setFont(new Font("Verdana", Font.BOLD, 30));
        lblTurno.setForeground(Color.BLUE);
        lblTiempoRestante = new JLabel("El tiempo restante:");
        lblTiempoRestante.setFont(new Font("Verdana", Font.BOLD, 30));
        lblTiempoRestante.setForeground(Color.BLUE);
        lblTurnosPendientes = new JLabel("Turnos faltantes:");
        lblTurnosPendientes.setFont(new Font("Verdana", Font.BOLD, 30));
        lblTurnosPendientes.setForeground(Color.BLUE);
        

     
       Font font = new Font("Verdana", Font.BOLD,16);
       JPanel panel = new JPanel();
       panel.add(new JLabel("Nombre")).setFont(font);
       panel.add(tfNombre);
       panel.add(new JLabel("Edad:")).setFont(font);
       panel.add(tfEdad);
       panel.add(new JLabel("Afiliación (POS o PC):")).setFont(font);
       panel.add(tfAfiliacion);
       Font font1 = new Font("Verdana", Font.BOLD, 18);
       panel.add(new JLabel("Condición especial (embarazo, limitación motriz, tercera edad): ")).setFont(font1);
       panel.add(tfCondicion);


        panel.setLayout(new GridLayout(4, 2));
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnNuevoTurno);
        btnNuevoTurno.setPreferredSize(new Dimension(300, 70));
        panelBotones.add(btnExtenderTiempo);
        btnExtenderTiempo.setPreferredSize(new Dimension(100, 70));
        JPanel panelTurnos = new JPanel();
        panelTurnos.setLayout(new BoxLayout(panelTurnos, BoxLayout.Y_AXIS));
        panelTurnos.add(lblTurno);
        panelTurnos.add(lblTiempoRestante);
        panelTurnos.add(lblTurnosPendientes);
        panelTurnos.add(taTurnos);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panel, BorderLayout.NORTH);
        container.add(panelBotones, BorderLayout.CENTER);
        container.add(panelTurnos, BorderLayout.SOUTH);

        // 
        colaTurnos = new LinkedList<Paciente>();

     
        btnNuevoTurno.addActionListener(this);
        btnExtenderTiempo.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNuevoTurno) {
            
            String nombre = tfNombre.getText();
            int edad = Integer.parseInt(tfEdad.getText());
            String afiliacion = tfAfiliacion.getText();
            String condicion = tfCondicion.getText();
            Paciente paciente;
            paciente = new Paciente(nombre, edad, afiliacion, condicion);


            colaTurnos.offer(paciente);

           
taTurnos.setText("Turnos faltantes:\n");
for (Paciente p : colaTurnos) {
taTurnos.append("- " + p.getNombre() + "\n");
}
       
        if (pacienteEnCurso == null) {
            atenderSiguientePaciente();
        }
    } else if (e.getSource() == btnExtenderTiempo) {
       
        tiempoRestante += 5;
        actualizarTiempoRestante();
    }
}

private void atenderSiguientePaciente() {
   
    pacienteEnCurso = colaTurnos.poll();

    
    lblTurno.setText("Turno en curso: " + pacienteEnCurso.getNombre());
    lblTurno.setFont(new Font("Verdana", Font.BOLD, 20));

   
    tiempoRestante = 7;
    actualizarTiempoRestante();

   
    taTurnos.setText("Turnos faltantes:\n");
    for (Paciente p : colaTurnos) {
        taTurnos.append("- " + p.getNombre() + "\n");
    }


    if (!colaTurnos.isEmpty()) {
        Timer timer = new Timer(6000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atenderSiguientePaciente();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}

private void actualizarTiempoRestante() {
  
    lblTiempoRestante.setText("Tiempo faltante: " + tiempoRestante + " segundos");

   
    if (tiempoRestante == 0) {
        atenderSiguienteUsuario();
    } else {
      
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                actualizarTiempoRestante();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}

    private void atenderSiguienteUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

private static class Paciente_activo {
    private String nombre;
    private char afiliacion;
    private int edad;
    private String condicion;

    public Paciente_activo(String nombre, int edad, char afiliacion, String condicion) {
        this.nombre = nombre;
        this.edad = edad;
        this.afiliacion = afiliacion;
        this.condicion = condicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public char getAfiliacion() {
        return afiliacion;
    }

    public String getCondicion() {
        return condicion;
    }
}

public static void main(String[] args) {

    EPS_Afiliados gui = new EPS_Afiliados();
    gui.setVisible(true);
}
}