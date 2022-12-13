import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

    /**
     *@Turma: 123 - POO (PROF. JULIO SILVEIRA)
     * @authores:
     * João Paulo de Jesus Nunes : 2017103211
     * Kevin Flávio Nascimento dos Santos : 2018101746
     * Ramon Alberto da Silva : 2019101165
     * Matheus Ferreira Lima : 2018101624
     */
    public class AppAV2 {

        public static void main(String[] args) throws FileNotFoundException, IOException {

            ArrayList<String> linhasDoArquivo = new ArrayList<>();

            try {

                FileInputStream arqLanca  = new FileInputStream("lancamentos.txt");
                Scanner         scanLanca   = new Scanner (arqLanca);
                FileWriter      arqSaldos = new FileWriter ("saldos.txt");

                while (scanLanca.hasNext()) {
                    linhasDoArquivo.add(scanLanca.nextLine());
                }

                //  Ordenando os correntistas em ordem alfabética
                Comparator<String> comparador = new Comparator<>() {
                    public int compare(String s1, String s2) {
                        return s1.trim().split(":")[1].replaceAll(" ","").compareToIgnoreCase(s2.trim().split(":")[1].replaceAll(" ",""));
                    }
                };
                linhasDoArquivo.sort(comparador);

                // Processando as movimentações dos clientes

                for (String linha : linhasDoArquivo)
                {
                    String []infoLinha = linha.trim().split(":");
                    String  nome  = infoLinha[1];
                    String  cpf  = infoLinha[0];


                    // Extraindo as movimentações financeiras

                    String [] saldo = infoLinha[2].trim().replaceAll(" +", " ").split (" ");
                    double saldoFinal = 0;

                    double Lancamento1 = Double.parseDouble (saldo[0]);
                    saldoFinal = saldoFinal + Lancamento1;
                    for(int i=1;i<saldo.length;i++){
                        saldoFinal= saldoFinal + Double.parseDouble(saldo[i]);
                    }

                    arqSaldos.write ("correntista: " + nome + " CPF: " + cpf+
                            ("\nSALDO INICIAL: R$ ") + Lancamento1 +
                            "\nSALDO FINAL: R$ " + saldoFinal + "\n");
                }
                arqSaldos.close();
            }
            catch (IOException e) {
                System.out.println ("Exceção de I/O: " + e.getMessage());
            }


        }

    }