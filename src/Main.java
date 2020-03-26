import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        try {
            List<String> newConteudos = new ArrayList<>();

            String caminho = getCaminhotxt();
            List<String> conteudos = carregaConteudoTxt(caminho);
            if (conteudos == null) {
                System.out.println("Não foi possível encontrar arquivo");
                return;
            }

            for (String s : conteudos) {
                String conteudo = s.replaceAll("^\\{}\\[]\\(\\)", "");
                if (conteudo.isBlank())
                    continue;

                char[] arrayDosChars = conteudo.toCharArray();
                verificaConteudoDoTxt(newConteudos, s, arrayDosChars);
            }

            escreverNoTxtResultado(newConteudos, caminho);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void verificaConteudoDoTxt(List<String> newConteudos, String s, char[] arrayDosChars) {
        String conteudo;
        Boolean taOkay = simbolosEstamBatendos(arrayDosChars);
        if (taOkay) {
            conteudo = s + " - OK";
            newConteudos.add(conteudo);
        } else {
            conteudo = s + " - Inválido";
            newConteudos.add(conteudo);
        }
    }

    private static void escreverNoTxtResultado(List<String> newConteudos, String caminho) throws IOException {
        FileWriter writer = new FileWriter(caminho, true);
        for (String newConteudo : newConteudos) {
            writer.write(newConteudo + "\n");
        }
        writer.close();
    }

    private static List<String> carregaConteudoTxt(String caminho) throws IOException {
        List<String> conteudos = Files.readAllLines(Paths.get(caminho));
        if(conteudos.isEmpty()) {
            System.out.println("Arquivo Vazio");
            return null;
        }
        return conteudos;
    }

    private static String getCaminhotxt() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bem-vindo ao desafio");
        System.out.println("Informe o caminho do seu arquivo .txt =)");
        return scan.next();
    }

    static boolean parEstaBatendo(char character1, char character2) {
        if (character1 == '(' && character2 == ')')
            return true;
        else if (character1 == '{' && character2 == '}')
            return true;
        else if (character1 == '[' && character2 == ']')
            return true;
        else
            return false;
    }

    static boolean simbolosEstamBatendos(char exp[]) {
        Stack st = new Stack();

        for(int i=0;i<exp.length;i++)
        {
            if (exp[i] == '{' || exp[i] == '(' || exp[i] == '[')
                st.push(exp[i]);

            if (exp[i] == '}' || exp[i] == ')' || exp[i] == ']')
            {
                if (st.isEmpty())
                {
                    return false;
                }
                else if ( !parEstaBatendo((Character) st.pop(), exp[i]) )
                {
                    return false;
                }
            }
        }
        if (st.isEmpty())
            return true;
        else
        {
            return false;
        }
    }
}
