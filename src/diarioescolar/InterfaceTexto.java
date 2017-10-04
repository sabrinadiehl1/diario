/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarioescolar;

import java.util.Scanner;

/**
 *
 * @author sabrina.diehl
 */
public class InterfaceTexto {
    
    private Diario diario;
    private Scanner reader;
     
    public InterfaceTexto(){
        this.reader = new Scanner(System.in);
         reader.useDelimiter("\\n");
    }
    
    public void executa(){
        
           boolean sair = false;
        
        inicializaDiario();
        
        while (!sair) {
            String opcao = imprimirMenu();
            
            switch (opcao){
                case "1":
                cadastrarAluno();
                break;
                case "2":
                cadastrarExame();
                break;
                case "3":
                listarNotas();
                break;
                case "4":
                mostrarDiario();
                break;
  
            }
        }
    }
    
    public String imprimirMenu(){
        System.out.println("-----------------------------------");
        System.out.println("Diário de Classe");
        System.out.println();
        System.out.println("1 - Cadastrar Aluno");
        System.out.println("2 - Cadastrar Exame");
        System.out.println("3 - Listar notas do Aluno");
        System.out.println("4 - Mostrar diário");
        System.out.println("S - Sair");
        System.out.println();
                
        System.out.print("Escolha: ");
        
        return reader.next().toLowerCase();
    }
    
     private void inicializaDiario() {
        System.out.println("Diário de Classe");
        System.out.println();
        System.out.print("Entre o nome da disciplina: ");
        String disciplina = reader.next();
        
        diario = new Diario(disciplina);
    }
    
    private void cadastrarAluno(){
        System.out.println("Cadastro de Aluno");
        System.out.print("Digite o nome do aluno: ");
        String nome = reader.next();
        System.out.print("Digite a matricula: ");
        int matricula = reader.nextInt();
        System.out.println("Digite a data de nascimento: ");
        String dataNascimento = reader.next(); 
        diario.addAluno(new Aluno(nome, matricula, dataNascimento));
        System.out.println("Aluno cadastrado!");
        
    }
    
    private void cadastrarExame(){
        Aluno alunoSelecionado;
        System.out.println("---CADASTRO DE EXAME---");
        listarAlunos();
        System.out.println("Digite a matrícula do aluno");
        int matricula = reader.nextInt();
        
        alunoSelecionado = diario.getAluno(matricula);
        
        if (alunoSelecionado == null) {
            System.out.println("Aluno inválido");   
        } else {        
            System.out.println("Digite a data");
            String data = reader.next();
            System.out.println("Digite a nota do aluno");
            double nota = reader.nextDouble();
            diario.addExame(new Exame(alunoSelecionado, data, nota));
            System.out.println("Exame cadastrado");
        
        }
    }
    
    
    private int getMatriculaAluno() {
        System.out.print("Entre a matrícula do aluno: ");
        return reader.nextInt();
    }
    
    private void listarAlunos() {
        System.out.println("Alunos");
        
        for (Aluno aluno : diario.getAlunos().values()) {
            System.out.println(aluno.getMatricula() + " - " + aluno.getNome());
        }
    }
    
    
    private void listarNotas(){
        System.out.println("Listagem de notas");
        System.out.println();
        listarAlunos();
        System.out.println();
        int matricula = getMatriculaAluno();
        System.out.println("Notas para o aluno: " + diario.getAluno(matricula).getNome());
        System.out.println();
        
        for (double nota : diario.getNotasAluno(matricula)) {
            System.out.print(nota + " ");
        }
        System.out.println();
    }
    
    
 private void mostrarDiario(){
        System.out.println("DIÁRIO DE CLASSE");
        System.out.println("Disciplia: " + diario.getDisciplina());
        System.out.println("---------------------------------------");
        for( int matricula : diario.getDiario().keySet() )  {
            System.out.print(diario.getAluno(matricula).getNome() + "\t");
            // diario.getDiario().get(matricula) -> retorna todos os exames cuja matrícula é "matricula"
            for(Exame exame : diario.getDiario().get(matricula)) {
                System.out.print(" | " + exame.getNota());
            }
            System.out.println();
        }
    }
  
    
}
