import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

public class EPS_Afiliados extends JFrame implements ActionListener {
    private JTextField tfNombre, tfEdad, tfAfiliacion, tfCondicion;
    private final JTextArea taTurnos;
    private final JButton btnNuevoTurno;
    private final JButton btnExtenderTiempo;
    private final JLabel lblTurno;
    private final JLabel lblTiempoRestante;
    private final JLabel lblTurnosPendientes;
    private final Queue<Paciente> colaTurnos;
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
        tfNombre.setFont(new Font("Verdana", Font.PLAIN, 30));
        tfNombre.setForeground(Color.GREEN);
        tfEdad = new JTextField();
        tfEdad.setFont(new Font("Verdana", Font.PLAIN, 30));
        tfEdad.setForeground(Color.GREEN);
        tfAfiliacion = new JTextField();
        tfAfiliacion.setFont(new Font("Verdana", Font.PLAIN, 30));
        tfAfiliacion.setForeground(Color.GREEN);
        tfCondicion = new JTextField();
        tfCondicion.setFont(new Font("Verdana", Font.PLAIN, 30));
        tfCondicion.setForeground(Color.GREEN);
        
        taTurnos = new JTextArea();
        taTurnos.setFont(new Font("Verdana", Font.PLAIN, 20));
        taTurnos.setForeground(Color.BLACK);
        btnNuevoTurno = new JButton("Turno siguiente");
        btnNuevoTurno.setFont(new Font("Verdana", Font.BOLD, 15));
        btnNuevoTurno.setForeground(Color.WHITE);
        btnNuevoTurno.setBackground(Color.BLACK);
        btnExtenderTiempo = new JButton("Extender el tiempo");
        btnExtenderTiempo.setFont(new Font("Verdana", Font.BOLD, 15));
        btnExtenderTiempo.setForeground(Color.WHITE);
        btnExtenderTiempo.setBackground(Color.BLACK);
        lblTurno = new JLabel("Turno en curso:");
        lblTurno.setFont(new Font("Verdana", Font.BOLD, 15));
        lblTurno.setForeground(Color.BLACK);
        lblTiempoRestante = new JLabel("El tiempo restante:");
        lblTiempoRestante.setFont(new Font("Verdana", Font.BOLD, 15));
        lblTiempoRestante.setForeground(Color.BLACK);
        lblTurnosPendientes = new JLabel("Turnos faltantes:");
        lblTurnosPendientes.setFont(new Font("Verdana", Font.BOLD, 15));
        lblTurnosPendientes.setForeground(Color.BLACK);
        

     
       Font font = new Font("Verdana", Font.BOLD,20);
       JPanel panel = new JPanel();
       panel.add(new JLabel("Nombre")).setFont(font);
       panel.add(tfNombre);
       panel.add(new JLabel("Edad:")).setFont(font);
       panel.add(tfEdad);
       panel.add(new JLabel("Afiliación (POS o PC):")).setFont(font);
       panel.add(tfAfiliacion);
       Font font1 = new Font("Verdana", Font.BOLD, 20);
       panel.add(new JLabel("Condición especial (embarazo, limitación motriz, tercera edad): ")).setFont(font1);
       panel.add(tfCondicion);


        panel.setLayout(new GridLayout(4, 2));
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnNuevoTurno);
        btnNuevoTurno.setPreferredSize(new Dimension(100, 60));
        panelBotones.add(btnExtenderTiempo);
        btnExtenderTiempo.setPreferredSize(new Dimension(100, 60));
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
        colaTurnos = new LinkedList<>();

     
        btnNuevoTurno.addActionListener(this);
        btnExtenderTiempo.addActionListener(this);
    }

    /**
     *
     * @param e
     */
    @Override
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
       
        tiempoRestante += 7;
        actualizarTiempoRestante();
    }
}

private void atenderSiguientePaciente() {
   
    pacienteEnCurso = colaTurnos.poll();

    
    lblTurno.setText("Turno en curso: " + pacienteEnCurso.getNombre());
    lblTurno.setFont(new Font("Verdana", Font.BOLD, 25));

   
    tiempoRestante = 7;
    actualizarTiempoRestante();

   
    taTurnos.setText("Turnos faltantes:\n");
    for (Paciente p : colaTurnos) {
        taTurnos.append("- " + p.getNombre() + "\n");
    }


    if (!colaTurnos.isEmpty()) {
        Timer timer = new Timer(7000, (ActionEvent e) -> {
            atenderSiguientePaciente();
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
      
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            tiempoRestante--;
            actualizarTiempoRestante();
        });
        timer.setRepeats(false);
        timer.start();
    }
}

    private void atenderSiguienteUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

private static class Pacienteactivo {
    private final String nombre;
    private final char afiliacion;
    private final int edad;
    private final String condicion;

    public Pacienteactivo(String nombre, int edad, char afiliacion, String condicion) {
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