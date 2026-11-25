import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CadastroAluno extends JFrame {
    private JTextField txtNome;
    private JTextField txtIdade;
    private JComboBox cmbCurso;
    private ButtonGroup grupoTurno;
    private JRadioButton rbMatutino;
    private JRadioButton rbNoturno;
    private JRadioButton rbVespertino;
    private JCheckBox chkComputador;
    private JButton btnCadastrar;
    private JButton btnLimpar;
    private JLabel lblStatus;
    private JPanel painelPrincipal;

    public CadastroAluno() {
        setContentPane(painelPrincipal);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cadastro de Alunos");
        btnCadastrar.addActionListener(e ->  cadastrarAluno());

        grupoTurno = new ButtonGroup();
        grupoTurno.add(rbMatutino);
        grupoTurno.add(rbNoturno);
        grupoTurno.add(rbVespertino);
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno();
            }
        });
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void cadastrarAluno() {
            String nome = txtNome.getText();
            String textoIdade = txtIdade.getText();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite o nome do aluno");
                txtNome.requestFocus();
                return;
            }
            if (textoIdade.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite a idade do aluno");
                txtIdade.requestFocus();
                return;
            }

            int idade = 0;
            try{
                idade = Integer.parseInt(txtIdade.getText());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Digite uma idade válida");
                return;
            }

            if(cmbCurso.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(null, "Selecione um curso");
                return;
            }
            String curso = cmbCurso.getSelectedItem().toString();
            String turno = "";
            if (rbMatutino.isSelected()) {
                turno = "Matutino";
            } else if (rbVespertino.isSelected()) {
                turno = "Vespertino";
            } else if (rbNoturno.isSelected()) {
                turno = "Noturno";
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um turno");
                return;
            }

            boolean possuiComputador = chkComputador.isSelected();

        try {
            AlunoDAO alunoDAO = new AlunoDAO();

            alunoDAO.inserir(
                    nome,
                    idade,
                    curso,
                    turno,
                    possuiComputador
            );

            String mensagem = "Aluno cadastrado com sucesso!";
            JOptionPane.showMessageDialog(this, mensagem);

            lblStatus.setText("Aluno " + nome +" cadastrado com sucesso!");

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar aluno!");
            System.out.println(e.getMessage());
            e.printStackTrace(); // Isso imprime o erro completo em vermelho no console
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        cmbCurso.setSelectedIndex(0);
        chkComputador.setSelected(false);
        rbMatutino.setSelected(false);
        rbNoturno.setSelected(false);
        rbVespertino.setSelected(false);
    }

    public static void main(String[] args) {
        CadastroAluno frame = new CadastroAluno();
        frame.setVisible(true);
    }
}
